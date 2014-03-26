package com.ebay.nest;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;

import cascading.cascade.Cascade;
import cascading.cascade.CascadeConnector;
import cascading.flow.Flow;
import cascading.flow.FlowConnector;
import cascading.flow.FlowDef;
import cascading.flow.hadoop.HadoopFlowConnector;
import cascading.flow.local.LocalFlowConnector;
import cascading.property.AppProps;
import cascading.stats.CascadingStats;
import cascading.stats.FlowStats;
import cascading.stats.FlowStepStats;

public abstract class AbstractApp extends Configured implements Tool {

	private static final Log LOG = LogFactory.getLog(NestEngine.class);
	protected Properties properties;
	protected CmdLineParser opts;
	protected Configuration conf;

	public AbstractApp() {
		this.conf = new Configuration(false);
		this.properties = new Properties();
	}

	public int run(String[] args) {
		try {
			GenericOptionsParser parser = new GenericOptionsParser(conf, args);
			conf = parser.getConfiguration();
			properties.putAll(ConfUtil.toProperties(conf));
			PropertiesUtil.loadNestDefaultProperties(properties);
			opts = new CmdLineParser(super.getClass().getSimpleName(), args);
			registerOption();
			this.properties.putAll(parseOption());
		} catch (Exception e) {
			e.printStackTrace();
			printUsage();
			return -1;
		}
		try {
			initMapReduceProperties();
			FlowDef flowDef = buildCascade();
			Flow flow = getFlowConnector().connect(flowDef);
			CascadeConnector connector = new CascadeConnector(getProperties());
			Cascade cascade = connector.connect(getClass().getName(), flow);
			cascade.complete();
			logFlowStats(flow);

			return 0;
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		return -1;
	}

	protected void printUsage() {
		opts.printUsage();
	}

	protected FlowConnector getFlowConnector() {
		if ("1".equals(properties.get("use_local_connector"))) {
			// AppProps.setApplicationJarClass(getProperties(),
			// AbstractApp.class);
			return new LocalFlowConnector(getProperties());
		} else {
			return new HadoopFlowConnector(getProperties());
		}
	}

	protected Properties getProperties() {
		return this.properties;
	}

	private void initMapReduceProperties() {
		Iterator<Map.Entry<String, String>> iterator = getConf().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			if (LOG.isDebugEnabled()) {
				logDEBUG("Conf entry: " + ((String) entry.getKey()) + "=" + ((String) entry.getValue()));
			}
			if ((((String) entry.getKey()).equals("mapred.job.queue.name"))
					&& (((String) entry.getValue()).equals("default"))) {
				continue;
			}

			this.properties.put(entry.getKey(), entry.getValue());
		}
		String serializations = this.properties.getProperty("io.serializations");
		if ((serializations == null)
				|| (!(serializations.contains("org.apache.hadoop.io.serializer.JavaSerialization")))) {
			this.properties.put("io.serializations", serializations
					+ ",org.apache.hadoop.io.serializer.JavaSerialization");
		}

		String os = System.getProperty("os.name").toLowerCase();
		logINFO("Operation system:" + os);
		if ((os.indexOf("nix") >= 0) || (os.indexOf("nux") >= 0) || (os.indexOf("aix") > 0)
				|| (os.indexOf("sunos") >= 0)) {
			String compressionCodecs = this.properties.getProperty("io.compression.codecs");
			if ((compressionCodecs == null) || (!(compressionCodecs.contains("lzo")))) {
				this.properties.put("io.compression.codecs", compressionCodecs
						+ ",com.hadoop.compression.lzo.LzoCodec,com.hadoop.compression.lzo.LzopCodec");
			}

		}

		if (os.indexOf("win") >= 0) {
			this.properties.put("fs.file.impl", "com.conga.services.hadoop.patch.HADOOP_7682.WinLocalFileSystem");
		}

		this.properties.put("mapreduce.job.complete.cancel.delegation.tokens", "false");

		AppProps.setApplicationJarClass(this.properties, super.getClass());

		if (LOG.isDebugEnabled())
			for (Map.Entry entry : this.properties.entrySet())
				logDEBUG("Application properties: " + entry.getKey().toString() + "=" + entry.getValue().toString());
	}

	protected void setProperty(String key, String value) {
		this.properties.setProperty(key, value);
	}

	protected void logDEBUG(String log) {
		LOG.debug("[" + super.getClass().getName() + "] " + log);
	}

	protected void logINFO(String log) {
		LOG.info("[" + super.getClass().getName() + "] " + log);
	}

	protected abstract void registerOption();

	protected abstract Properties parseOption() throws Exception;

	protected abstract FlowDef buildCascade() throws Exception;

	protected void logFlowStats(Flow flow) {
		FlowStats flowStats = flow.getFlowStats();
		Collection<String> counterGroups = flowStats.getCounterGroups();

		logINFO("Flow Name=" + flow.getName());

		logINFO("Flow ID=" + flow.getID());

		logINFO("Flow Duration=" + flowStats.getDuration());

		logINFO("# of Steps=" + flowStats.getStepsCount());

		logINFO("# of Counter Groups=" + counterGroups.size());

		List<FlowStepStats> flowStepStats = flowStats.getFlowStepStats();
		// drill down each step stats
		for (FlowStepStats stepStats : flowStepStats) {
			logINFO("Step Name=" + stepStats.getName());

			logINFO("Step ID=" + stepStats.getID());

			logINFO("Step Duration=" + stepStats.getDuration());

			logCounterGroups(stepStats);
		}
	}

	private void logCounterGroups(CascadingStats stats) {
		for (String grp : stats.getCounterGroups()) {
			Collection<String> countersFor = stats.getCountersFor(grp);
			for (String counter : countersFor) {
				logINFO(grp + ":" + counter + "=" + stats.getCounterValue(grp, counter));
			}

		}
	}

}
