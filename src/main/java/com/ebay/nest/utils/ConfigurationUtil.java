package com.ebay.nest.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;

public class ConfigurationUtil {

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

}
