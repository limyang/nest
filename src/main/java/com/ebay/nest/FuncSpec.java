package com.ebay.nest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FuncSpec implements Serializable, Cloneable {

	private static final long serialVersionUID = -1L;
	String className = null;
	String[] ctorArgs = null;


	public FuncSpec(String className, String ctorArg) {
		this.className = className;
		this.ctorArgs = new String[1];
		this.ctorArgs[0] = ctorArg;
	}

	public FuncSpec(String className, String[] ctorArgs) {
		this.className = className;
		this.ctorArgs = ctorArgs;
	}

	public FuncSpec(String funcSpec) {
		this.className = getClassNameFromSpec(funcSpec);
		List<String> args = parseArguments(getArgStringFromSpec(funcSpec));
		if (args.size() > 0) {
			this.ctorArgs = new String[args.size()];
			int i = 0;
			for (Iterator<String> iterator = args.iterator(); iterator.hasNext();) {
				this.ctorArgs[i++] = iterator.next();

			}
		}

	}

	public static String getClassNameFromSpec(String funcSpec) {
		int paren = funcSpec.indexOf('(');
		if (paren != -1)
			return funcSpec.substring(0, paren);
		else
			return funcSpec;
	}

	public static String getArgStringFromSpec(String funcSpec) {
		int paren = funcSpec.indexOf('(');
		if (paren != -1)
			return funcSpec.substring(paren + 1);
		else
			return "";
	}

	public static List<String> parseArguments(String argString) {
		List<String> args = new ArrayList<String>();

		int startIndex = 0;
		int endIndex;
		while (startIndex < argString.length()) {
			while (startIndex < argString.length() && argString.charAt(startIndex++) != '\'')
				;
			endIndex = startIndex;
			while (endIndex < argString.length() && argString.charAt(endIndex) != '\'') {
				if (argString.charAt(endIndex) == '\\')
					endIndex++;
				endIndex++;
			}
			if (endIndex < argString.length()) {
				args.add(argString.substring(startIndex, endIndex));
			}
			startIndex = endIndex + 1;
		}
		return args;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String[] getCtorArgs() {
		return ctorArgs;
	}

	public void setCtorArgs(String[] ctorArgs) {
		this.ctorArgs = ctorArgs;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(className);

		if (ctorArgs != null) {
			sb.append("(");
			for (int i = 0; i < ctorArgs.length; i++) {
				sb.append("'");
				sb.append(ctorArgs[i]);
				sb.append("'");
				if (i != ctorArgs.length - 1) {
					sb.append(",");
				}
			}
			sb.append(")");

		}

		return sb.toString();
	}

	public boolean equals(Object other) {
		if (other != null && other instanceof FuncSpec) {
			FuncSpec ofs = (FuncSpec) other;
			if (!className.equals(ofs.className))
				return false;
			if (ctorArgs == null && ofs.ctorArgs != null || ctorArgs != null && ofs.ctorArgs == null) {
				return false;
			}

			if (ctorArgs != null && ofs.ctorArgs != null) {
				if (ctorArgs.length != ofs.ctorArgs.length)
					return false;
				for (int i = 0; i < ctorArgs.length; i++) {
					if (!ctorArgs[i].equals(ofs.ctorArgs[i]))
						return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public int hashCode() {
		return ctorArgs == null ? getClassName().hashCode() : getClassName().hashCode() + ctorArgs.length;
	}

	public FuncSpec clone() throws CloneNotSupportedException {
		String[] args = null;
		if (ctorArgs != null) {
			args = new String[ctorArgs.length];
			for (int i = 0; i < ctorArgs.length; i++) {
				args[i] = ctorArgs[i];
			}
		}
		return new FuncSpec(className, args);
	}

}
