package com.ebay.nest.metastore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ebay.nest.NestContext;

public class RetryingMetadataClient implements InvocationHandler {

	private static final Log LOG = LogFactory.getLog(RetryingMetadataClient.class.getName());

	private final IMetadataClient base;
	private final int retryLimit;
	private final int retryDelaySeconds;

	protected RetryingMetadataClient(int retryLimit, int retryDelaySeconds, NestContext context,
			Class<? extends IMetadataClient> msClientClass) throws MetaException {
		this.retryLimit = retryLimit;
		this.retryDelaySeconds = retryDelaySeconds;

		this.base = (IMetadataClient) MetadataFactory.newInstance(msClientClass, new Class[] { NestContext.class },
				new Object[] { context });
	}

	public static IMetadataClient getProxy(NestContext context, String mscClassName) throws MetaException {

		Class<? extends IMetadataClient> baseClass = (Class<? extends IMetadataClient>) MetadataFactory
				.getClass(mscClassName);

		RetryingMetadataClient handler = new RetryingMetadataClient(3, 5, context, baseClass);

		return (IMetadataClient) Proxy.newProxyInstance(RetryingMetadataClient.class.getClassLoader(),
				baseClass.getInterfaces(), handler);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object ret = null;
		int retriesMade = 0;
		while (true) {
			MetaException caughtException;
			try {
				if (retriesMade > 0) {
					base.reconnect();
				}
				ret = method.invoke(base, args);
				break;
			} catch (UndeclaredThrowableException e) {
				throw e.getCause();
			} catch (InvocationTargetException e) {
				if ((e.getCause() instanceof MetaException)
						&& e.getCause().getMessage().matches("JDO[a-zA-Z]*Exception")) {
					caughtException = (MetaException) e.getCause();
				} else {
					throw e.getCause();
				}
			}

			if (retriesMade >= retryLimit) {
				throw caughtException;
			}
			retriesMade++;
			LOG.warn("MetaStoreClient lost connection. Attempting to reconnect.", caughtException);
			Thread.sleep(retryDelaySeconds * 1000);
		}
		return ret;
	}

}
