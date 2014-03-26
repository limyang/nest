package com.ebay.nest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

import cascading.flow.FlowDef;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.tap.Tap;
import cascading.tuple.Fields;

import com.ebay.nest._remove.ddi.Uow;
import com.ebay.nest.analyzer.Analyzer;
import com.ebay.nest.analyzer.ErrorMsg;
import com.ebay.nest.analyzer.RootAnalyzer;
import com.ebay.nest.analyzer.StatementType;
import com.ebay.nest.cascading.operation.FinalOutputOP;
import com.ebay.nest.cascading.operation.OneLine;
import com.ebay.nest.metastore.MetadataFactory;
import com.ebay.nest.parser.ASTNode;
import com.ebay.nest.parser.ASTUtils;
import com.ebay.nest.parser.ParseDriver;
import com.ebay.nest.parser.ParseException;
import com.ebay.nest.utils.FieldsUtil;

public class NestEngine extends AbstractApp {

	protected String cqlContent;
	protected String fileName;
	protected ParseDriver pd = new ParseDriver();
	protected NestContext ctx;
	private static final Log LOG = LogFactory.getLog(NestEngine.class);

	protected void registerOption() {
		opts.registerOpt('c', "cql", CmdLineParser.ValueExpected.REQUIRED,
				"CQL", "Indicate CQL File Path");
		opts.registerOpt('l', "local", CmdLineParser.ValueExpected.OPTIONAL,
				null, "Indicate whether it is local environment");
		opts.registerOpt('p', "properties",
				CmdLineParser.ValueExpected.OPTIONAL, "PROPERTIES",
				"Pass properties");
		opts.registerOpt('f', "uow_from", CmdLineParser.ValueExpected.REQUIRED,
				"UOW_FROM", "UOW_FROM");
		opts.registerOpt('t', "uow_to", CmdLineParser.ValueExpected.REQUIRED,
				"UOW_TO", "UOW_TO");
		opts.registerOpt('X', "all", CmdLineParser.ValueExpected.OPTIONAL,
				null, "Indicate Log Level");
	}

	protected Properties parseOption() throws Exception {
		Properties properties = new Properties();
		char opt;
		while ((opt = opts.getNextOpt()) != CmdLineParser.EndOfOpts) {
			switch (opt) {
			case 'c':
				fileName = opts.getValStr();
				if (!new File(fileName).exists()) {
					System.err.println("CQL file " + cqlContent
							+ " is not found! Please check.");
					throw new IllegalArgumentException();
				}
				this.cqlContent = FileUtils
						.readFileToString(new File(fileName));
				break;
			case 'l':
				properties.put("is_local", "1");
				//properties.put("use_local_connector", "1");
				break;
			case 'p':
				String strArgPro = opts.getValStr();
				Properties argP = CmdLineParser.parseProperties(strArgPro);
				properties.putAll(argP);
				break;
			case 'f':
				String uowFrom = opts.getValStr();
				Uow uf = new Uow(uowFrom);
				properties.put("uow_from_date",
						uf.getYear() + "-" + uf.getMonth() + "-" + uf.getDay());
				properties.put("uow_from", uowFrom);

				break;
			case 't':
				String uowTo = opts.getValStr();
				Uow ut = new Uow(uowTo);
				properties.put("uow_to_date",
						ut.getYear() + "-" + ut.getMonth() + "-" + ut.getDay());
				properties.put("uow_to", uowTo);
				break;
			case 'X':
				// properties.put("io.sort.mb", "1000");
				break;
			default:
				Character cc = Character.valueOf(opt);
				throw new AssertionError("Unhandled option " + cc.toString());

			}
		}

		return properties;

	}

	protected FlowDef buildCascade() throws Exception {
		ASTNode tree;
		initContext();
		initMSC();
		tree = compile();
		analyse(tree);
		return buildFlow();
	}

	private void initContext() throws IOException {
		properties.put("cascading.flow.job.pollinginterval", "5");
		properties.put("io.serializations",
				"com.ebay.nest.io.BigIntSerialization,"
						+ "com.ebay.nest.io.ByteIntSerialization,"
						+ "com.ebay.nest.io.DecimalSerialization,"
						+ "com.ebay.nest.io.FloatSerialization,"
						+ "com.ebay.nest.io.IntegerSerialization,"
						+ "com.ebay.nest.io.SmallIntSerialization,"
						+ "com.ebay.nest.io.NumericSerialization,"
						+ "com.ebay.nest.io.CharSerialization,"
						+ "com.ebay.nest.io.DateSerialization,"
						+ "com.ebay.nest.io.TimeSerialization,"
						+ "com.ebay.nest.io.TimestampSerialization,"
						+ "com.ebay.nest.io.ByteSerialization,"
						+ "com.ebay.nest.io.BooleanSerialization");

		this.ctx = new NestContext(properties);

	}

	private void initMSC() {
		MetadataFactory
				.getInstance()
				.setContext(ctx)
				.setMatadataClientClassName(
						"com.ebay.nest.metadata.TeradataMetadataClient");
		ErrorMsg.init(cqlContent);

	}

	public ASTNode compile() throws ParseException {
		LOG.info("Parsing CQL:\n\n" + cqlContent);
		ASTNode tree = pd.parse(cqlContent, ctx);
		if (tree == null) {
			return null;
		}
		tree = ASTUtils.findRootNonNullToken(tree);
		LOG.debug(tree.prettyDump());
		return tree;

	}

	public void analyse(ASTNode rootTree) throws Exception {
		if (rootTree == null || rootTree.getChildCount() == 0) {
			return;
		}
		Analyzer analyzer = new RootAnalyzer(StatementType.ROOT);
		analyzer.analyze(rootTree, ctx);
	}

	private FlowDef buildFlow() {
		FlowDef flowDef = new FlowDef();
		flowDef.setName(fileName);
		for (String sink : ctx.getSinks()) {
			Pipe destPipe = ctx.getPipeForName(sink);
			if (destPipe == null) {
				continue;
			}
			Map<String, Tap> sourceMap = new HashMap<String, Tap>();
			Set<String> sources = ctx.getPipeSource(destPipe);
			for (String source : sources) {
				sourceMap.put(source, ctx.getSourceTapMap().get(source));
			}
			Fields sinkFields = ctx.getSinkFields(sink);
			if (sinkFields == null) {
				sinkFields = ctx.getFieldsForPipe(destPipe);
			}
			if (sinkFields != null) {
				FinalOutputOP finalOutputOP = new FinalOutputOP(sinkFields,
						FieldsUtil.names(sinkFields));
				destPipe = new Each(destPipe, Fields.ALL, finalOutputOP,
						Fields.RESULTS);
			}

			//String delimeter = NestConf.FIELD_DELIMETER;
			//destPipe = new Each(destPipe, new OneLine("line", delimeter));

			Tap sinkTap = ctx.getTapForSink(sink);
			flowDef.addTail(destPipe);
			flowDef.addSink(destPipe, sinkTap);
			flowDef.addSources(sourceMap);

		}

		return flowDef;
	}

	public static void main(String[] args) throws Exception {
		NestEngine nestEngine = new NestEngine();
		int ret = ToolRunner.run(new Configuration(), nestEngine, args);
		if (ret != 0) {
			throw new NestException("Job failed!!!");
		}
	}

}
