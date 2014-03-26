package com.ebay.nest;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ebay.nest.analyzer.SemanticException;

public abstract class UDF<T extends Data> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected NestLogger nestLogger;

	public abstract Class valideParameterType(Class[] params) throws SemanticException;

	protected NestProgressable reporter;

	protected Log log = LogFactory.getLog(getClass());

	protected Type returnType;

	public UDF() {
		Map<TypeVariable<?>, Type> typesByTypeVariable = new HashMap<TypeVariable<?>, Type>();
		Class<?> cls = getClass();
		Type type = cls.getGenericSuperclass();
		cls = cls.getSuperclass();
		while (UDF.class.isAssignableFrom(cls)) {
			TypeVariable<? extends Class<?>>[] typeParams = cls.getTypeParameters();
			if (type instanceof ParameterizedType) {
				ParameterizedType pType = (ParameterizedType) type;
				Type[] typeArgs = pType.getActualTypeArguments();
				for (int i = 0; i < typeParams.length; i++) {
					typesByTypeVariable.put(typeParams[i], typeArgs[i]);
				}
			}
			type = cls.getGenericSuperclass();
			cls = cls.getSuperclass();
		}

		Type targetType = UDF.class.getTypeParameters()[0];
		while (targetType != null && targetType instanceof TypeVariable) {
			targetType = typesByTypeVariable.get(targetType);
		}
		if (targetType == null || targetType instanceof GenericArrayType || targetType instanceof WildcardType) {
			throw new RuntimeException(String.format(
					"Failed to determine concrete type for type parameter T of EvalFunc<T> for derived class '%s'",
					getClass().getName()));
		}
		returnType = targetType;

	}

	public Type getReturnType() {
		return returnType;
	}

	public final void progress() {
		if (reporter != null)
			reporter.progress();
		else
			warn("No reporter object provided to UDF.", null);
	}

	public final void warn(String msg, Enum warningEnum) {
		if (nestLogger != null)
			nestLogger.warn(this, msg, warningEnum);
		else
			log.warn("No logger object provided to UDF: " + this.getClass().getName() + ". " + msg);
	}

	public void finish() {
	}

	abstract public T exec(Data[] input) throws IOException;

	public NestProgressable getReporter() {
		return reporter;
	}

	public final void setReporter(NestProgressable reporter) {
		this.reporter = reporter;
	}

	public List<String> getCacheFiles() {
		return null;
	}

	public NestLogger getNestLogger() {
		return nestLogger;
	}

	public final void setNestLogger(NestLogger nestLogger) {
		this.nestLogger = nestLogger;
	}

	public Log getLogger() {
		return log;
	}

}
