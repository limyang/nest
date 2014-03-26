package com.ebay.nest.utils;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class FileUtils {

	public static Path makeQualified(Path path, Configuration conf) throws IOException {

		if (!path.isAbsolute()) {
			return path.makeQualified(FileSystem.get(conf));
		}

		URI fsUri = FileSystem.getDefaultUri(conf);
		URI pathUri = path.toUri();

		String scheme = pathUri.getScheme();
		String authority = pathUri.getAuthority();
		if (scheme == null) {
			scheme = fsUri.getScheme();
			authority = fsUri.getAuthority();
			if (authority == null) {
				authority = "";
			}
		} else {
			if (authority == null) {
				if (scheme.equals(fsUri.getScheme()) && fsUri.getAuthority() != null) {
					authority = fsUri.getAuthority();
				} else {
					authority = "";
				}
			}
		}

		return new Path(scheme, authority, pathUri.getPath());
	}

}
