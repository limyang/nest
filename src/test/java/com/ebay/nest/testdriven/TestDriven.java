//package com.ebay.nest.testdriven;
//
//import in.masr.home.Home;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import junit.framework.Test;
//import junit.framework.TestSuite;
//
//import org.apache.commons.configuration.PropertiesConfiguration;
//import org.apache.commons.io.FileUtils;
//
//import com.ebay.nest.util.StringU;
//
//public class TestDriven {
//
//	private static PropertiesConfiguration config;
//
//	public static Test suite() throws IOException {
//		Home.init(TestDriven.class, "config.ini");
//		config = Home.properties("config.ini");
//		TestSuite suite = new TestSuite("Test for CTLG");
//		String list = config.getString("local_test_lis");
//		String[] split = list.split(",");
//		for (String str : split) {
//			test(str, suite);
//		}
//		return suite;
//	}
//
//	private static void test(String filePath, TestSuite suite)
//			throws IOException {
//		String content = FileUtils.readFileToString(new File(filePath));
//		content = StringU.dos2unix(content);
//		String lines[] = content.split("\n");
//		for (String line : lines) {
//			line = line.trim();
//			if (line.startsWith("#") || line.isEmpty()) {
//				continue;
//			}
//			String[] items = getInfo(line);
//			TestSuite ts = null;
//			if (items[1].equals("")) {
//				ts = new TestSuite("Test for " + items[0]);
//			} else {
//				ts = new TestSuite("Test for " + items[0] + "  [" + items[1]
//						+ "]");
//			}
//			CQLTest potTest = new CQLTest(items[0]);
//			ts.addTest(potTest);
//			suite.addTest(ts);
//		}
//	}
//
//	private static String[] getInfo(String line) {
//		Pattern pattern = Pattern.compile("(\\S+)\\s*#?([\\s\\S]*?)$",
//				Pattern.MULTILINE);
//		Matcher matcher = pattern.matcher(line);
//		if (matcher.find()) {
//			return new String[] { matcher.group(1).trim(),
//					matcher.group(2).trim() };
//		}
//		return null;
//	}
//}
