package com.ebay.nest;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;

public class ConfUtil {

	public static Configuration toConfiguration(Properties properties) {
		assert properties != null;
		final Configuration config = new Configuration(false);
		final Enumeration<Object> iter = properties.keys();
		while (iter.hasMoreElements()) {
			final String key = (String) iter.nextElement();
			final String val = properties.getProperty(key);
			config.set(key, val);
		}
		return config;
	}

	public static Properties toProperties(Configuration configuration) {
		Properties properties = new Properties();
		assert configuration != null;
		Iterator<Map.Entry<String, String>> iter = configuration.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = iter.next();
			properties.put(entry.getKey(), entry.getValue());
		}
		return properties;
	}

	public static void mergeConf(Configuration origConf, Configuration replaceConf) {
		for (Entry<String, String> entry : replaceConf) {
			origConf.set(entry.getKey(), entry.getValue());
		}

	}

	public static Properties getLocalFSProperties() {
		Configuration localConf;

		localConf = new Configuration(true);
		Method unsetMethod = null;
		try {
			unsetMethod = localConf.getClass().getMethod("unset", new Class[] { String.class });
		} catch (Exception e) {
		}
		if (unsetMethod != null) {
			try {
				unsetMethod.invoke(localConf, new Object[] { "mapreduce.job.cache.files" });
			} catch (Exception e) {
				// Should not happen
			}
		}

		Properties props = ConfUtil.toProperties(localConf);
		props.setProperty("fs.default.name", "file:///");
		return props;
	}
}
