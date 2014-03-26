package com.ebay.nest;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Properties;
import java.io.PrintWriter;
import java.lang.AssertionError;

public class CmdLineParser {
	private String[] mArgs;
	private HashMap<Character, ValueExpected> mShort;
	private HashMap<Character, String> mDescription;
	private HashMap<Character, String> mOptName;
	private HashMap<String, Character> mLong;
	private int mArgNum;
	private String mVal;
	private String program;

	public enum ValueExpected {
		REQUIRED, OPTIONAL, NOT_ACCEPTED
	};

	public static final char EndOfOpts = '-';

	public CmdLineParser(String program, String[] args) {
		mArgs = args;
		mArgNum = 0;
		mShort = new HashMap<Character, ValueExpected>();
		mLong = new HashMap<String, Character>();
		mDescription = new HashMap<Character, String>();
		mOptName = new HashMap<Character, String>();
	}

	public void printUsage() {
		PrintWriter pw = new PrintWriter(System.out);
		StringBuffer sb = new StringBuffer();
		sb=sb.append(program + " Usage:\n");
		pw.println(sb);
	}

	public void registerOpt(char c, String s, ValueExpected ve, String name, String description) {
		if (c == '-') {
			throw new AssertionError("CmdLineParser:  '-' is not a legal single character designator.");
		}

		Character cc = Character.valueOf(c);
		if (mShort.put(cc, ve) != null) {
			throw new AssertionError("CmdLineParser:  You have already registered option " + cc.toString());
		}

		if (mLong.put(s, cc) != null) {
			throw new AssertionError("CmdLineParser:  You have already registered option " + s);
		}

		mDescription.put(cc, description);
		mOptName.put(cc, name);

	}

	public char getNextOpt() throws ParseException {
		if (mArgNum >= mArgs.length)
			return EndOfOpts;

		int offset = 1;
		mVal = null;
		try {
			String arg = mArgs[mArgNum];
			if (arg.charAt(0) != '-') {
				mArgNum--;
				return EndOfOpts;
			}

			for (int i = 1; i < arg.length() && arg.charAt(i) == '-'; i++)
				offset++;

			if (offset == arg.length())
				return EndOfOpts;

			Character cc = null;
			if (arg.substring(offset).length() == 1) {
				cc = Character.valueOf(arg.substring(offset).charAt(0));
			} else {
				cc = mLong.get(arg.substring(offset));
				if (cc == null) {
					Integer ii = Integer.valueOf(mArgNum + 1);
					String errMsg = "Found unknown option (" + arg + ") at position " + ii.toString();
					throw new ParseException(errMsg, mArgNum);
				}
			}

			ValueExpected ve = mShort.get(cc);
			if (ve == null) {
				Integer ii = Integer.valueOf(mArgNum + 1);
				String errMsg = "Found unknown option (" + arg + ") at position " + ii.toString();
				throw new ParseException(errMsg, mArgNum);
			}

			switch (ve) {
			case NOT_ACCEPTED:
				return cc.charValue();

			case REQUIRED:
				if (mArgNum + 1 >= mArgs.length || mArgs[mArgNum + 1].charAt(0) == '-') {
					String errMsg = "Option " + arg + " requires a value but you did not provide one.";
					throw new ParseException(errMsg, mArgNum);
				}
				mVal = mArgs[++mArgNum];
				return cc.charValue();

			case OPTIONAL:
				if (mArgNum + 1 < mArgs.length && mArgs[mArgNum + 1].charAt(0) != '-') {
					mVal = mArgs[++mArgNum];
				}
				return cc.charValue();

			default:
				throw new AssertionError("Unknown valueExpected state");

			}

		} finally {
			mArgNum++;
		}
	}

	public String[] getRemainingArgs() {
		if (mArgNum == mArgs.length)
			return null;

		String[] remainders = new String[mArgs.length - mArgNum];
		System.arraycopy(mArgs, mArgNum, remainders, 0, remainders.length);
		return remainders;
	}

	public String getValStr() {
		return mVal;
	}

	public Integer getValInt() throws NumberFormatException {
		if (mVal == null)
			return null;
		else
			return new Integer(mVal);
	}

	public static Properties parseProperties(String s) {

		Properties p = new Properties();
		if (s == null || "".equals(s.trim())) {
			return p;
		}
		s = s.trim();
		String[] sps = s.split(";");
		for (String sp : sps) {
			String key;
			String value;
			int index = sp.indexOf('=');
			if (index != -1) {
				key = sp.substring(0, index);
				value = sp.substring(index + 1);
			} else {
				key = sp;
				value = null;
			}
			p.setProperty(key, value);

		}

		return p;
	}

}
