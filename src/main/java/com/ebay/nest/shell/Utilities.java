package com.ebay.nest.shell;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Utilities {

	public static ClassLoader addToClassPath(ClassLoader cloader, String[] newPaths) throws Exception {
		URLClassLoader loader = (URLClassLoader) cloader;
		List<URL> curPath = Arrays.asList(loader.getURLs());
		ArrayList<URL> newPath = new ArrayList<URL>();

		for (URL onePath : curPath) {
			newPath.add(onePath);
		}
		curPath = newPath;
		for (String onestr : newPaths) {
			if (StringUtils.indexOf(onestr, "file://") == 0) {
				onestr = StringUtils.substring(onestr, 7);
			}

			URL oneurl = (new File(onestr)).toURI().toURL();
			if (!curPath.contains(oneurl)) {
				curPath.add(oneurl);
			}
		}
		return new URLClassLoader(curPath.toArray(new URL[0]), loader);
	}

	public static void removeFromClassPath(String[] pathsToRemove) throws Exception {
		Thread curThread = Thread.currentThread();
		URLClassLoader loader = (URLClassLoader) curThread.getContextClassLoader();
		Set<URL> newPath = new HashSet<URL>(Arrays.asList(loader.getURLs()));

		for (String onestr : pathsToRemove) {
			if (StringUtils.indexOf(onestr, "file://") == 0) {
				onestr = StringUtils.substring(onestr, 7);
			}

			URL oneurl = (new File(onestr)).toURI().toURL();
			newPath.remove(oneurl);
		}

		loader = new URLClassLoader(newPath.toArray(new URL[0]));
		curThread.setContextClassLoader(loader);
		SessionState.get().getConf().setClassLoader(loader);
	}

	public static String realFile(String newFile, Configuration conf) throws IOException {
		Path path = new Path(newFile);
		URI pathURI = path.toUri();
		FileSystem fs;

		if (pathURI.getScheme() == null) {
			fs = FileSystem.getLocal(conf);
		} else {
			fs = path.getFileSystem(conf);
		}

		if (!fs.exists(path)) {
			return null;
		}

		String file = path.makeQualified(fs).toString();
		if (StringUtils.startsWith(file, "file:/") && !StringUtils.startsWith(file, "file:///")) {
			file = "file:///" + file.substring("file:/".length());
		}
		return file;
	}

}
