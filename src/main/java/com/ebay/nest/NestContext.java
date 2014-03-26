package com.ebay.nest;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.antlr.runtime.TokenRewriteStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;

import cascading.pipe.Pipe;
import cascading.tap.Tap;
import cascading.tuple.Fields;

import com.ebay.nest.utils.FileUtils;
import com.ebay.nest2.TaskRunner;

public class NestContext {

	private boolean isHDFSCleanup;
	private TokenRewriteStream tokenRewriteStream;
	private Configuration conf;
	String executionId;
	private int current_id = 0;
	// source name --> Tap
	private Map<String, Tap> sourceTapMap = new LinkedHashMap<String, Tap>();
	// sink name --> Tap
	private Map<String, Tap> sinkTapMap = new LinkedHashMap<String, Tap>();
	// source name, pipe name, sink name--> Pipe
	private Map<String, Pipe> pipeMap = new HashMap<String, Pipe>();
	// source name, sink name, global pipe name --> Fields
	private Map<String, Fields> sinkFields = new HashMap<String, Fields>();
	// Pipe (global and local) --> Fields
	private Map<Pipe, Fields> pipeFields = new HashMap<Pipe, Fields>();
	// Pipe --> source, source, source
	private Map<Pipe, Set<String>> pipeSource = new HashMap<Pipe, Set<String>>();
	//
	private Map<String, TableSchema_remove> schemaMap = new HashMap<String, TableSchema_remove>();

	private Map<String, Table> tableMap = new HashMap<String, Table>();
	// Properties
	private Properties properties;

	transient public Vector<String> skipJars = new Vector<String>(2);

	private static ThreadLocal<ArrayList<String>> packageImportList = new ThreadLocal<ArrayList<String>>();

	private final Map<String, ContentSummary> pathToCS = new ConcurrentHashMap<String, ContentSummary>();

	private Path nonLocalScratchPath;

	private String localScratchDir;

	private String scratchDirPermission;

	private static final String MR_PREFIX = "-mr-";
	private static final String EXT_PREFIX = "-ext-";
	private static final String LOCAL_PREFIX = "-local-";

	private final Map<String, Path> fsScratchDirs = new HashMap<String, Path>();

	protected int pathid = 10000;
	protected boolean explain = false;

	private static final Log LOG = LogFactory.getLog("Nest.Context");

	protected String cmd = "";

	public NestContext(Configuration conf) throws IOException {
		this(conf, generateExecutionId());
	}

	public static String generateExecutionId() {
		Random rand = new Random();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS");
		String executionId = "hive_" + format.format(new Date()) + "_" + Math.abs(rand.nextLong());
		return executionId;
	}

	public NestContext(Configuration conf, String executionId) {
		this.conf = conf;
		this.executionId = executionId;

		nonLocalScratchPath = new Path(NestConf.getVar(conf, NestConf.ConfVars.SCRATCHDIR), executionId);
		localScratchDir = new Path(NestConf.getVar(conf, NestConf.ConfVars.LOCALSCRATCHDIR), executionId).toUri()
				.getPath();
		scratchDirPermission = NestConf.getVar(conf, NestConf.ConfVars.SCRATCHDIRPERMISSION);
	}

	public NestContext(Properties properties) {
		this.properties = properties;
		this.conf = new Configuration();
		this.executionId = generateExecutionId();
		System.setProperties(this.properties);
		String nestJar = JarUtil.findContainingJar(NestEngine.class);
		String hadoopJar = JarUtil.findContainingJar(FileSystem.class);
		if (nestJar != null) {
			skipJars.add(nestJar);
			if (!nestJar.equals(hadoopJar))
				skipJars.add(hadoopJar);
		}

		nonLocalScratchPath = new Path(NestConf.getVar(conf, NestConf.ConfVars.SCRATCHDIR), executionId);
		localScratchDir = new Path(NestConf.getVar(conf, NestConf.ConfVars.LOCALSCRATCHDIR), executionId).toUri()
				.getPath();
		scratchDirPermission = NestConf.getVar(conf, NestConf.ConfVars.SCRATCHDIRPERMISSION);
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public void setHDFSCleanup(boolean isHDFSCleanup) {
		this.isHDFSCleanup = isHDFSCleanup;
	}

	// TO support UDF Jar in future!!
	private static class ContextClassLoader extends URLClassLoader {

		public ContextClassLoader(ClassLoader classLoader) {
			this(new URL[0], classLoader);
		}

		public ContextClassLoader(URL[] urls, ClassLoader classLoader) {
			super(urls, classLoader);
		}

		public void addURL(URL url) {
			super.addURL(url);
		}
	};

	public static Class resolveClassName(String name) throws NestException {
		for (String prefix : getPackageImportList()) {
			Class c;
			try {
				c = Class.forName(prefix + name, true, NestContext.classloader);
				return c;
			} catch (ClassNotFoundException e) {

			} catch (UnsupportedClassVersionError e) {
				int errCode = 1069;
				String msg = "Problem resolving class version numbers for class " + name;
				throw new NestException(msg, errCode, NestException.INPUT, e);
			}

		}

		int errCode = 1070;
		String msg = "Could not resolve " + name + " using imports: " + packageImportList.get();
		throw new NestException(msg, errCode, NestException.INPUT);
	}

	public static Object instantiateFuncFromSpec(FuncSpec funcSpec) {
		Object ret;
		String className = funcSpec.getClassName();
		String[] args = funcSpec.getCtorArgs();
		Class objClass = null;

		try {
			objClass = resolveClassName(className);
		} catch (NestException ioe) {
			throw new RuntimeException("Cannot instantiate: " + className, ioe);
		}

		try {
			if (args != null && args.length > 0) {
				Class paramTypes[] = new Class[args.length];
				for (int i = 0; i < paramTypes.length; i++) {
					paramTypes[i] = String.class;
				}
				Constructor c = objClass.getConstructor(paramTypes);
				ret = c.newInstance((Object[]) args);
			} else {
				ret = objClass.newInstance();
			}
		} catch (NoSuchMethodException nme) {
			// Second chance
			try {
				Constructor c = objClass.getConstructor(String[].class);
				Object[] wrappedArgs = new Object[1];
				wrappedArgs[0] = args;
				ret = c.newInstance(wrappedArgs);
			} catch (Throwable e) {
				// bad luck
				StringBuilder sb = new StringBuilder();
				sb.append("could not instantiate '");
				sb.append(className);
				sb.append("' with arguments '");
				sb.append(Arrays.toString(args));
				sb.append("'");
				throw new RuntimeException(sb.toString(), e);
			}
		} catch (Throwable e) {
			// bad luck
			StringBuilder sb = new StringBuilder();
			sb.append("could not instantiate '");
			sb.append(className);
			sb.append("' with arguments '");
			sb.append(Arrays.toString(args));
			sb.append("'");
			throw new RuntimeException(sb.toString(), e);
		}
		return ret;
	}

	public static ArrayList<String> getPackageImportList() {
		if (packageImportList.get() == null) {
			ArrayList<String> importlist = new ArrayList<String>();
			importlist.add("");
			importlist.add("java.lang.");
			importlist.add("com.ebay.nest.udf.");
			packageImportList.set(importlist);
		}
		return packageImportList.get();
	}

	static private ContextClassLoader classloader = new ContextClassLoader(NestContext.class.getClassLoader());

	public static ClassLoader getClassLoader() {
		return classloader;
	}

	public static void setClassLoader(ClassLoader cl) {
		if (cl instanceof ContextClassLoader) {
			classloader = (ContextClassLoader) cl;
		} else {
			classloader = new ContextClassLoader(cl);
		}
	}

	public void setTableSchema(String source, TableSchema_remove schema) {
		schemaMap.put(source, schema);
	}

	public TableSchema_remove getTableSchema(String source) {
		return schemaMap.get(source);
	}

	public void setTable(String source, Table table) {
		tableMap.put(source, table);
	}

	public Table getTable(String source) {
		return tableMap.get(source);
	}

	public void addPipeSource(Pipe pipe, String source) {

		Set<String> sources = pipeSource.get(pipe);
		if (sources == null) {

			sources = new HashSet<String>();
			pipeSource.put(pipe, sources);
		}
		sources.add(source);

	}

	public void addPipeSourceSet(Pipe pipe, Set<String> source) {

		Set<String> sources = pipeSource.get(pipe);
		if (sources == null) {

			sources = new HashSet<String>();
			pipeSource.put(pipe, sources);
		}
		sources.addAll(source);

	}

	public Set<String> getPipeSource(Pipe pipe) {

		return pipeSource.get(pipe);
	}

	public void setProperty(String key, String value) {

		properties.setProperty(key, value);
		System.setProperty(key, value);
	}

	public String getProperty(String key) {

		String v = properties.getProperty(key);
		return v;
	}

	public void setTokenRewriteStream(TokenRewriteStream tokenRewriteStream) {
		assert (this.tokenRewriteStream == null);
		this.tokenRewriteStream = tokenRewriteStream;
	}

	public TokenRewriteStream getTokenRewriteStream() {
		return tokenRewriteStream;
	}

	public void setPipeFields(Pipe pipe, Fields fields) {
		pipeFields.put(pipe, fields);
	}

	public Fields getFieldsForPipe(Pipe pipe) {
		return pipeFields.get(pipe);
	}

	public void setSinkFields(String name, Fields fields) {
		sinkFields.put(name.toLowerCase(), fields);
	}

	public Fields getSinkFields(String name) {
		return sinkFields.get(name.toLowerCase());
	}

	public void setPipe(String name, Pipe pipe) {
		pipeMap.put(name.toLowerCase(), pipe);
	}

	public Pipe getPipeForName(String name) {
		return pipeMap.get(name.toLowerCase());
	}

	public void addSourceTap(String name, Tap tap) {
		sourceTapMap.put(name.toLowerCase(), tap);
	}

	public Set<String> getSources() {
		return sourceTapMap.keySet();
	}

	public Tap getTapForSource(String source) {
		return sourceTapMap.get(source.toLowerCase());
	}

	public Map<String, Tap> getSourceTapMap() {
		return sourceTapMap;
	}

	public void addSinkTap(String name, Tap tap) {
		sinkTapMap.put(name.toLowerCase(), tap);
	}

	public Set<String> getSinks() {
		return sinkTapMap.keySet();
	}

	public Tap getTapForSink(String name) {
		return sinkTapMap.get(name.toLowerCase());
	}

	public int getCurrentID() {
		return current_id;
	}

	public int nextId() {

		current_id++;
		return current_id;
	}

	public NestContext() {
		this(generateExecutionId());
	}

	public NestContext(String executionId) {

		this.executionId = executionId;

	}

	public static void setPackageImportList(ArrayList<String> list) {
		packageImportList.set(list);
	}

	public void addCS(String path, ContentSummary cs) {
		pathToCS.put(path, cs);
	}

	public ContentSummary getCS(Path path) {
		return getCS(path.toString());
	}

	public ContentSummary getCS(String path) {
		return pathToCS.get(path);
	}

	public Map<String, ContentSummary> getPathToCS() {
		return pathToCS;
	}

	public Path getMRTmpPath() {
		return new Path(getMRScratchDir(), MR_PREFIX + nextPathId());
	}

	private String nextPathId() {
		return Integer.toString(pathid++);
	}

	public Path getMRScratchDir() {

		try {
			Path dir = FileUtils.makeQualified(nonLocalScratchPath, conf);
			URI uri = dir.toUri();
			Path newScratchDir = getScratchDir(uri.getScheme(), uri.getAuthority(), !explain, uri.getPath());
			LOG.info("New scratch dir is " + newScratchDir);
			return newScratchDir;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Error while making MR scratch " + "directory - check filesystem config ("
					+ e.getCause() + ")", e);
		}
	}

	private Path getScratchDir(String scheme, String authority, boolean mkdir, String scratchDir) {

		String fileSystem = scheme + ":" + authority;
		Path dir = fsScratchDirs.get(fileSystem + "-" + TaskRunner.getTaskRunnerID());

		if (dir == null) {
			Path dirPath = new Path(scheme, authority, scratchDir + "-" + TaskRunner.getTaskRunnerID());
			if (mkdir) {
				try {
					FileSystem fs = dirPath.getFileSystem(conf);
					dirPath = new Path(fs.makeQualified(dirPath).toString());
					if (!fs.mkdirs(dirPath)) {
						throw new RuntimeException("Cannot make directory: " + dirPath.toString());
					} else {
						FsPermission fsPermission = new FsPermission(Short.parseShort(scratchDirPermission.trim(), 8));
						fs.setPermission(dirPath, fsPermission);
					}
					if (isHDFSCleanup) {
						fs.deleteOnExit(dirPath);
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			dir = dirPath;
			fsScratchDirs.put(fileSystem + "-" + TaskRunner.getTaskRunnerID(), dir);

		}
		return dir;
	}

}
