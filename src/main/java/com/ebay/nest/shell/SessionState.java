package com.ebay.nest.shell;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;

import com.ebay.nest.NestConf;

public class SessionState {
	private static final Log LOG = LogFactory.getLog(SessionState.class);

	protected NestConf conf;
	protected boolean isSilent;

	protected boolean isVerbose;

	protected ShellHistory hiveHist;

	public InputStream in;
	public PrintStream out;
	public PrintStream info;
	public PrintStream err;

	public PrintStream childOut;

	public PrintStream childErr;

	protected File tmpOutputFile;

	private Map<String, String> hiveVariables;

	private Map<String, List<List<String>>> stackTraces;

	private Map<String, List<String>> localMapRedErrors;

	private String currentDatabase;

	public File getTmpOutputFile() {
		return tmpOutputFile;
	}

	public void setTmpOutputFile(File f) {
		tmpOutputFile = f;
	}

	public boolean getIsSilent() {
		if (conf != null) {
			return conf.getBoolean("session.silent", false);
		} else {
			return isSilent;
		}
	}

	public void setIsSilent(boolean isSilent) {
		if (conf != null) {
			conf.setBoolean("session.silent", isSilent);
		}
		this.isSilent = isSilent;
	}

	public boolean getIsVerbose() {
		return isVerbose;
	}

	public NestConf getConf() {
		return conf;
	}

	public void setIsVerbose(boolean isVerbose) {
		this.isVerbose = isVerbose;
	}

	public SessionState(NestConf conf) {
		this.conf = conf;
		isSilent = conf.getBoolean("session.silent", false);

		conf.set("session.id", makeSessionId());

	}

	public void setCmd(String cmdString) {
		conf.set("query.string", cmdString);
	}

	public String getCmd() {
		return (conf.get("query.string"));
	}

	public String getQueryId() {
		return (conf.get("query.id"));
	}

	public Map<String, String> getHiveVariables() {
		if (hiveVariables == null) {
			hiveVariables = new HashMap<String, String>();
		}
		return hiveVariables;
	}

	public void setHiveVariables(Map<String, String> hiveVariables) {
		this.hiveVariables = hiveVariables;
	}

	public String getSessionId() {
		return (conf.get("session.id"));
	}

	private static ThreadLocal<SessionState> tss = new ThreadLocal<SessionState>();

	public static SessionState start(NestConf conf) {
		SessionState ss = new SessionState(conf);
		return start(ss);
	}

	public static void setCurrentSessionState(SessionState session) {
		tss.set(session);
	}

	public static SessionState start(SessionState startSs) {

		setCurrentSessionState(startSs);

		if (startSs.getTmpOutputFile() == null) {

			try {
				startSs.setTmpOutputFile(createTempFile(startSs.getConf()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return startSs;
	}

	private static File createTempFile(NestConf conf) throws IOException {
		String lScratchDir = conf.get("nest.tmp.dir",
				System.getProperty("java.io.tmpdir") + File.separator + System.getProperty("user.name"));

		File tmpDir = new File(lScratchDir);
		String sessionID = conf.get("session.id");
		if (!tmpDir.exists()) {
			if (!tmpDir.mkdirs()) {

				if (!tmpDir.exists()) {
					throw new RuntimeException("Unable to create log directory " + lScratchDir);
				}
			}
		}
		File tmpFile = File.createTempFile(sessionID, ".pipeout", tmpDir);
		tmpFile.deleteOnExit();
		return tmpFile;
	}

	public static SessionState get() {
		return tss.get();
	}

	private static String makeSessionId() {
		return UUID.randomUUID().toString();
	}

	public static class LogHelper {

		protected Log LOG;
		protected boolean isSilent;

		public LogHelper(Log LOG) {
			this(LOG, false);
		}

		public LogHelper(Log LOG, boolean isSilent) {
			this.LOG = LOG;
			this.isSilent = isSilent;
		}

		public PrintStream getOutStream() {
			SessionState ss = SessionState.get();
			return ((ss != null) && (ss.out != null)) ? ss.out : System.out;
		}

		public PrintStream getInfoStream() {
			SessionState ss = SessionState.get();
			return ((ss != null) && (ss.info != null)) ? ss.info : getErrStream();
		}

		public PrintStream getErrStream() {
			SessionState ss = SessionState.get();
			return ((ss != null) && (ss.err != null)) ? ss.err : System.err;
		}

		public PrintStream getChildOutStream() {
			SessionState ss = SessionState.get();
			return ((ss != null) && (ss.childOut != null)) ? ss.childOut : System.out;
		}

		public PrintStream getChildErrStream() {
			SessionState ss = SessionState.get();
			return ((ss != null) && (ss.childErr != null)) ? ss.childErr : System.err;
		}

		public boolean getIsSilent() {
			SessionState ss = SessionState.get();
			// use the session or the one supplied in constructor
			return (ss != null) ? ss.getIsSilent() : isSilent;
		}

		public void printInfo(String info) {
			printInfo(info, null);
		}

		public void printInfo(String info, String detail) {
			if (!getIsSilent()) {
				getInfoStream().println(info);
			}
			LOG.info(info + StringUtils.defaultString(detail));
		}

		public void printError(String error) {
			printError(error, null);
		}

		public void printError(String error, String detail) {
			getErrStream().println(error);
			LOG.error(error + StringUtils.defaultString(detail));
		}
	}

	private static LogHelper _console;

	public static LogHelper getConsole() {
		if (_console == null) {
			Log LOG = LogFactory.getLog("SessionState");
			_console = new LogHelper(LOG);
		}
		return _console;
	}

	public static String validateFile(Set<String> curFiles, String newFile) {
		SessionState ss = SessionState.get();
		LogHelper console = getConsole();
		Configuration conf = (ss == null) ? new Configuration() : ss.getConf();

		try {
			if (Utilities.realFile(newFile, conf) != null) {
				return newFile;
			} else {
				console.printError(newFile + " does not exist");
				return null;
			}
		} catch (IOException e) {
			console.printError("Unable to validate " + newFile + "\nException: " + e.getMessage(), "\n"
					+ org.apache.hadoop.util.StringUtils.stringifyException(e));
			return null;
		}
	}

	public static boolean registerJar(String newJar) {
		LogHelper console = getConsole();
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			ClassLoader newLoader = Utilities.addToClassPath(loader, StringUtils.split(newJar, ","));
			Thread.currentThread().setContextClassLoader(newLoader);
			SessionState.get().getConf().setClassLoader(newLoader);
			console.printInfo("Added " + newJar + " to class path");
			return true;
		} catch (Exception e) {
			console.printError("Unable to register " + newJar + "\nException: " + e.getMessage(), "\n"
					+ org.apache.hadoop.util.StringUtils.stringifyException(e));
			return false;
		}
	}

	public static boolean unregisterJar(String jarsToUnregister) {
		LogHelper console = getConsole();
		try {
			Utilities.removeFromClassPath(StringUtils.split(jarsToUnregister, ","));
			console.printInfo("Deleted " + jarsToUnregister + " from class path");
			return true;
		} catch (Exception e) {
			console.printError("Unable to unregister " + jarsToUnregister + "\nException: " + e.getMessage(), "\n"
					+ org.apache.hadoop.util.StringUtils.stringifyException(e));
			return false;
		}
	}

	/**
	 * ResourceHook.
	 * 
	 */
	public static interface ResourceHook {
		String preHook(Set<String> cur, String s);

		boolean postHook(Set<String> cur, String s);
	}

	/**
	 * ResourceType.
	 * 
	 */
	public static enum ResourceType {
		FILE(new ResourceHook() {
			public String preHook(Set<String> cur, String s) {
				return validateFile(cur, s);
			}

			public boolean postHook(Set<String> cur, String s) {
				return true;
			}
		}),

		JAR(new ResourceHook() {
			public String preHook(Set<String> cur, String s) {
				String newJar = validateFile(cur, s);
				if (newJar != null) {
					return (registerJar(newJar) ? newJar : null);
				} else {
					return null;
				}
			}

			public boolean postHook(Set<String> cur, String s) {
				return unregisterJar(s);
			}
		}),

		ARCHIVE(new ResourceHook() {
			public String preHook(Set<String> cur, String s) {
				return validateFile(cur, s);
			}

			public boolean postHook(Set<String> cur, String s) {
				return true;
			}
		});

		public ResourceHook hook;

		ResourceType(ResourceHook hook) {
			this.hook = hook;
		}
	};

	public static ResourceType find_resource_type(String s) {

		s = s.trim().toUpperCase();

		try {
			return ResourceType.valueOf(s);
		} catch (IllegalArgumentException e) {
		}

		// try singular
		if (s.endsWith("S")) {
			s = s.substring(0, s.length() - 1);
		} else {
			return null;
		}

		try {
			return ResourceType.valueOf(s);
		} catch (IllegalArgumentException e) {
		}
		return null;
	}

	private final HashMap<ResourceType, Set<String>> resource_map = new HashMap<ResourceType, Set<String>>();

	public void add_builtin_resource(ResourceType t, String value) {
		getResourceMap(t).add(value);
	}

	private Set<String> getResourceMap(ResourceType t) {
		Set<String> result = resource_map.get(t);
		if (result == null) {
			result = new HashSet<String>();
			resource_map.put(t, result);
		}
		return result;
	}

	public boolean delete_resource(ResourceType t, String value) {
		if (resource_map.get(t) == null) {
			return false;
		}
		if (t.hook != null) {
			if (!t.hook.postHook(resource_map.get(t), value)) {
				return false;
			}
		}
		return (resource_map.get(t).remove(value));
	}

	public Set<String> list_resource(ResourceType t, List<String> filter) {
		if (resource_map.get(t) == null) {
			return null;
		}
		Set<String> orig = resource_map.get(t);
		if (filter == null) {
			return orig;
		} else {
			Set<String> fnl = new HashSet<String>();
			for (String one : orig) {
				if (filter.contains(one)) {
					fnl.add(one);
				}
			}
			return fnl;
		}
	}

	public void delete_resource(ResourceType t) {
		if (resource_map.get(t) != null) {
			for (String value : resource_map.get(t)) {
				delete_resource(t, value);
			}
			resource_map.remove(t);
		}
	}

	public void setStackTraces(Map<String, List<List<String>>> stackTraces) {
		this.stackTraces = stackTraces;
	}

	public Map<String, List<List<String>>> getStackTraces() {
		return stackTraces;
	}

	public Map<String, List<String>> getLocalMapRedErrors() {
		return localMapRedErrors;
	}

	public void addLocalMapRedErrors(String id, List<String> localMapRedErrors) {
		if (!this.localMapRedErrors.containsKey(id)) {
			this.localMapRedErrors.put(id, new ArrayList<String>());
		}

		this.localMapRedErrors.get(id).addAll(localMapRedErrors);
	}

	public void setLocalMapRedErrors(Map<String, List<String>> localMapRedErrors) {
		this.localMapRedErrors = localMapRedErrors;
	}

	public String getCurrentDatabase() {
		if (currentDatabase == null) {
			currentDatabase = "default";
		}
		return currentDatabase;
	}

	public void setCurrentDatabase(String currentDatabase) {
		this.currentDatabase = currentDatabase;
	}

	public void close() {
		File resourceDir = new File(getConf().get("nest.tmp.dir"));
		LOG.debug("Removing resource dir " + resourceDir);
		try {
			if (resourceDir.exists()) {
				FileUtils.deleteDirectory(resourceDir);
			}
		} catch (IOException e) {
			LOG.info("Error removing session resource dir " + resourceDir, e);
		}
	}

}
