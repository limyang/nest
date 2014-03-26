package com.ebay.nest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesUtil {
	private static final String PROPERTIES_FILE = "/nest.ini";
	private final static Log log = LogFactory.getLog(PropertiesUtil.class);

	public static void loadNestDefaultProperties(Properties properties) {
		loadPropertiesFromClasspath(properties, PROPERTIES_FILE);
		loadPropertiesFromFile(properties, System.getProperty("user.home") + "/.nest.ini");
		loadPropertiesFromFile(properties, "nest.ini");

		if (log.isDebugEnabled()) {
			for (Object o : properties.keySet()) {
				String propertyName = (String) o;
				StringBuilder sb = new StringBuilder();
				sb.append("Found property ");
				sb.append(propertyName);
				sb.append("=");
				sb.append(properties.get(propertyName).toString());
				log.debug(sb.toString());
			}
		}

		properties.putAll(System.getProperties());
	}

	public static void loadPropertiesFromFile(Properties properties, String fileName) {
		BufferedInputStream bis = null;
		Properties filePro = new Properties();
		try {
			System.out.println("YLM:filename:"+fileName);
			System.err.println("YLM:filename:"+fileName);
			File file = new File(fileName);
			if (file.exists()) {
				bis = new BufferedInputStream(new FileInputStream(file));
				filePro.load(bis);
			}else {
				System.err.println("Unable to find:"+fileName);
				log.error("Unable to find :"+fileName);
			}
		} catch (Exception e) {
			log.error("unable to parse :", e);
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (Exception e) {
				}
		}

		properties.putAll(filePro);
	}

	private static void loadPropertiesFromClasspath(Properties properties, String fileName) {
		InputStream inputStream = null;
		Class<PropertiesUtil> clazz = PropertiesUtil.class;
		try {
			inputStream = clazz.getResourceAsStream(fileName);
			if (inputStream == null) {
				String msg = "no " + fileName + " configuration file available in the classpath";
				log.debug(msg);
			} else {
				properties.load(inputStream);
			}
		} catch (Exception e) {
			log.error("unable to parse " + fileName + " :", e);
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (Exception e) {
				}
		}
	}

	public static Properties loadNestDefaultProperties() {
		Properties properties = new Properties();
		loadNestDefaultProperties(properties);
		return properties;
	}

}
