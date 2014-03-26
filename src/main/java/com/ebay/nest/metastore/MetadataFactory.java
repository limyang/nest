package com.ebay.nest.metastore;

import java.lang.reflect.Constructor;

import com.ebay.nest.JarUtil;
import com.ebay.nest.NestContext;

public class MetadataFactory {

	private static volatile MetadataFactory instance = null;

	private IMetadataClient metaStoreClient;

	private String metadataClientClassName = "com.ebay.nest.metadata.TeradataMetadataClient";

	private NestContext ctx;

	private MetadataFactory() {
		// do not remove!
	}

	public static MetadataFactory getInstance() {
		if (instance == null) {
			synchronized (MetadataFactory.class) {
				if (instance == null) {
					instance = new MetadataFactory();
				}
			}
		}
		return instance;
	}

	public MetadataFactory setContext(NestContext ctx) {
		this.ctx = ctx;
		return this;
	}

	public MetadataFactory setMatadataClientClassName(String metadataClientClassName) {
		this.metadataClientClassName = metadataClientClassName;
		return this;
	}

	public TableMetadata getMetadata(String database, String table) throws MetaException {

		return getMetaStoreClient(ctx).getMetadata(database, table);
	}

	private IMetadataClient getMetaStoreClient(NestContext ctx) throws MetaException {
		if (metaStoreClient == null) {
			metaStoreClient = createMetaStoreClient(ctx);
			metaStoreClient.connect();
		}
		return metaStoreClient;
	}

	private IMetadataClient createMetaStoreClient(NestContext ctx) throws MetaException {

		return RetryingMetadataClient.getProxy(ctx, metadataClientClassName);
	}

	public static Class<?> getClass(String rawStoreClassName) throws MetaException {
		try {
			return Class.forName(rawStoreClassName, true, JarUtil.getClassLoader());
		} catch (ClassNotFoundException e) {
			throw new MetaException(rawStoreClassName + " class not found");
		}
	}

	public static <T> T newInstance(Class<T> theClass, Class<?>[] parameterTypes, Object[] initargs) {
		if (parameterTypes.length != initargs.length) {
			throw new IllegalArgumentException(
					"Number of constructor parameter types doesn't match number of arguments");
		}
		for (int i = 0; i < parameterTypes.length; i++) {
			Class<?> clazz = parameterTypes[i];
			if (!(clazz.isInstance(initargs[i]))) {
				throw new IllegalArgumentException("Object : " + initargs[i] + " is not an instance of " + clazz);
			}
		}

		try {
			Constructor<T> meth = theClass.getDeclaredConstructor(parameterTypes);
			meth.setAccessible(true);
			return meth.newInstance(initargs);
		} catch (Exception e) {
			throw new RuntimeException("Unable to instantiate " + theClass.getName(), e);
		}
	}
}
