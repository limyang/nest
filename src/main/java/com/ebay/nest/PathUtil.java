package com.ebay.nest;

import in.masr.utils.FilenameU;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ebay.nest._remove.ddi.Uow;
import com.ebay.nest.analyzer.TapFormatType;

public class PathUtil {
	public static PathInfo getPathInfo(String path, String metadata, NestContext ctx) {
		PathInfo pathInfo = new PathInfo();
		boolean isLocal = (ctx.getProperty("is_local") == null) ? false
				: (ctx.getProperty("is_local").equals("1") ? true : false);
		Uow uowFrom = new Uow(ctx.getProperty("uow_from"));
		Uow uowTo = new Uow(ctx.getProperty("uow_to"));
		Pattern pattern = Pattern.compile("^\\s*([^\\s:]+)\\s*(:([^\\s]+))?\\s*$");
		Matcher matcher = pattern.matcher(path);
		String type = null;
		String tablePath = null;

		if (matcher.find()) {
			type = matcher.group(1).toLowerCase();
			tablePath = matcher.group(3);
		}

		String num = "";
		Pattern pattern2 = Pattern.compile("(\\d+)$");
		Matcher matcher2 = pattern2.matcher(type);
		if (matcher2.find()) {
			if (matcher2.group(1).length() == 1) {
				num = "0" + matcher2.group(1);
			} else {
				num = matcher2.group(1);
			}
		} else {
			num = "00";
		}

		String fullPath = null;
		if (type.startsWith("hfs")) {
			pathInfo.setTapFormatType(TapFormatType.SEQUENCE);
			if (!tablePath.startsWith("/")) {
				fullPath = FilenameU.join(tablePath, tablePath);
			} else
				fullPath = tablePath;
		} else if (type.startsWith("stg_ut")) {
			pathInfo.setTapFormatType(TapFormatType.TEXT);
			if (tablePath != null) {
				fullPath = FilenameU.join(tablePath, metadata, "stg", uowTo.getPathUtilDay(), num);
			}
		} else if (type.startsWith("stg_uf")) {
			pathInfo.setTapFormatType(TapFormatType.TEXT);
			if (tablePath != null) {
				fullPath = FilenameU.join(tablePath, metadata, "stg", uowFrom.getPathUtilDay(), num);
			}
		} else if (type.startsWith("ss_ut")) {
			pathInfo.setTapFormatType(TapFormatType.SEQUENCE);
			if (tablePath != null) {
				fullPath = FilenameU.join(tablePath, metadata, "snapshot", uowTo.getPathUtilDay(), num);
			}
		} else if (type.startsWith("ss_uf")) {
			pathInfo.setTapFormatType(TapFormatType.SEQUENCE);
			if (tablePath != null) {
				fullPath = FilenameU.join(tablePath, metadata, "snapshot", uowFrom.getPathUtilDay(), num);
			}
		} else if ("csv".equalsIgnoreCase(type)) {
			if (!tablePath.startsWith("/")) {
				fullPath = FilenameU.join(tablePath, tablePath);
			} else {
				fullPath = tablePath;
			}
			pathInfo.setTapFormatType(TapFormatType.CVS);
		} else if ("file".equalsIgnoreCase(type)) {
			if (!tablePath.startsWith("/")) {
				fullPath = FilenameU.join(tablePath, tablePath);
			}
			fullPath = tablePath;
			pathInfo.setTapFormatType(TapFormatType.FILE);
		} else if ("text".equalsIgnoreCase(type)) {
			if (!tablePath.startsWith("/")) {
				fullPath = FilenameU.join(tablePath, tablePath);
			}
			fullPath = tablePath;
			pathInfo.setTapFormatType(TapFormatType.TEXT);
		}
		pathInfo.setFullPath(fullPath);

		if (isLocal && pathInfo.getTapFormatType() == TapFormatType.SEQUENCE) {
			pathInfo.setTapFormatType(TapFormatType.TEXT);
		}
		return pathInfo;
	}

	public static class PathInfo {
		private String fullPath;
		private TapFormatType tapFormatType;

		public PathInfo() {
		}

		public String getFullPath() {
			return fullPath;
		}

		public void setFullPath(String fullPath) {
			this.fullPath = fullPath;
		}

		public TapFormatType getTapFormatType() {
			return tapFormatType;
		}

		public void setTapFormatType(TapFormatType tapFormatType) {
			this.tapFormatType = tapFormatType;
		}

	}
}
