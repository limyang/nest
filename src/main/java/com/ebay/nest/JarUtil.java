package com.ebay.nest;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.Enumeration;

public class JarUtil {

	public static String findContainingJar(Class clazz) {
		ClassLoader loader = NestContext.getClassLoader();
		String class_file = clazz.getName().replaceAll("\\.", "/") + ".class";
		try {
			Enumeration<URL> itr = null;
			if (loader instanceof URLClassLoader) {
				itr = ((URLClassLoader) loader).findResources(class_file);
			}
			if (itr == null || !itr.hasMoreElements()) {
				itr = loader.getResources(class_file);
			}
			for (; itr.hasMoreElements();) {
				URL url = (URL) itr.nextElement();
				if ("jar".equals(url.getProtocol())) {
					String toReturn = url.getPath();
					if (toReturn.startsWith("file:")) {
						toReturn = toReturn.substring("file:".length());
					}
					toReturn = toReturn.replaceAll("\\+", "%2B");
					toReturn = URLDecoder.decode(toReturn, "UTF-8");
					return toReturn.replaceAll("!.*$", "");
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public static ClassLoader getClassLoader() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = JarUtil.class.getClassLoader();
		}
		return classLoader;
	}

}
