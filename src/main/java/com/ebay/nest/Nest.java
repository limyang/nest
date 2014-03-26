package com.ebay.nest;

import in.masr.utils.ArrayU;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.tuple.Fields;

import com.ebay.nest.analyzer.Analyzer;
import com.ebay.nest.analyzer.RootAnalyzer;
import com.ebay.nest.analyzer.StatementType;
import com.ebay.nest.cascading.operation.CastToStringOP;
import com.ebay.nest.parser.ASTNode;
import com.ebay.nest.parser.ASTUtils;
import com.ebay.nest.parser.ParseDriver;
import com.ebay.nest.parser.ParseException;
import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.utils.CQLParserU;
import com.ebay.nest.utils.FieldsUtil;

public class Nest {
	protected ParseDriver pd = new ParseDriver();
	protected NestContext context = new NestContext();

	public Pipe sql(String sql) throws ParseException, NestException {
		String pipeName = "pipe_" + System.currentTimeMillis() + "_" + Math.random();
		pipeName = pipeName.replaceAll("\\.", "_");
		sql = "define pipe " + pipeName + " as " + sql + ";";
		ASTNode tree = pd.parse(sql, context);
		if (tree == null) {
			return null;
		}
		tree = ASTUtils.findRootNonNullToken(tree);
		Analyzer analyzer = new RootAnalyzer(StatementType.ROOT);
		analyzer.analyze(tree, context);
		return context.getPipeForName(pipeName);
	}

	public void set(String name, Pipe pipe, Fields fields) {
		if (name == null || pipe == null || fields == null) {
			throw new IllegalArgumentException("name or pipe or fields cannot be null");
		}
		if (ArrayU.contains(CQLParserU.SPECIAL_KEYWORDS, name.toLowerCase())) {
			throw new IllegalArgumentException("\"" + name + "\" is a keyword in Nest, please use another name.");
		}
		int fieldsSize = fields.size();
		Class[] types = new Class[fieldsSize];
		for (int i = 0; i < fieldsSize; i++) {
			types[i] = CHAR.class;
		}
		String[] names = FieldsUtil.names(fields);
		fields = new Fields(names, types);

		pipe = new Each(pipe, new CastToStringOP(fields), Fields.RESULTS);

		context.setPipe(name, pipe);
		context.setPipeFields(pipe, fields);
		context.addPipeSource(pipe, name);
	}

}
