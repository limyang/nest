package com.ebay.nest.analyzer;

import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.scheme.hadoop.SequenceFile;
import cascading.scheme.hadoop.TextDelimited;
import cascading.scheme.hadoop.TextLine;
import cascading.tap.SinkMode;
import cascading.tap.Tap;
import cascading.tap.hadoop.Hfs;
import cascading.tap.local.FileTap;
import cascading.tuple.Fields;

import com.ebay.nest.NestConf;
import com.ebay.nest.NestException;
import com.ebay.nest.PathUtil;
import com.ebay.nest.SchemaUtil;
import com.ebay.nest.TableSchema_remove;
import com.ebay.nest.PathUtil.PathInfo;
import com.ebay.nest.cascading.assembly.Format;
import com.ebay.nest.cascading.operation.CastTypeOP;
import com.ebay.nest.metastore.MetaException;
import com.ebay.nest.metastore.MetadataUtil;
import com.ebay.nest.metastore.TableMetadata;
import com.ebay.nest.parser.ASTNode;
import com.ebay.nest.parser.SQLParser;
import com.ebay.nest.utils.EbaySequenceFile;
import com.ebay.nest.utils.StringUtil;

public class TapAnalyzer extends Analyzer {

	public TapAnalyzer(StatementType type) throws NestException {
		super(type);
	}

	public void analyzeInternal(ASTNode child) throws SemanticException {

		ASTNode source = (ASTNode) child.getChild(0);
		String name = null;
		String db = null;
		String path = null;
		String tb = null;
		boolean isSource = true;
		for (int source_child_pos = 0; source_child_pos < source.getChildCount(); source_child_pos++) {
			ASTNode source_element = (ASTNode) source.getChild(source_child_pos);
			switch (source_element.getToken().getType()) {
			case SQLParser.TOK_TABNAME:
				name = source_element.getChild(0).getText().toLowerCase();
				break;
			case SQLParser.TOK_PATH:
				path = StringUtil.unescapeString(source_element.getChild(0).getText());
				break;
			case SQLParser.TOK_META:
				db = source_element.getChild(0).getText().toLowerCase();
				tb = source_element.getChild(1).getText().toLowerCase();
				break;
			case SQLParser.KW_SOURCE:
				isSource = true;
				break;
			case SQLParser.KW_TARGET:
				isSource = false;
				break;
			}
		}

		PathInfo pathInfo = PathUtil.getPathInfo(path, tb, ctx);
		TableDef_remove tab = new TableDef_remove(name, db, tb, pathInfo.getFullPath(), pathInfo.getTapFormatType());
		try {
			if (isSource) {
				processSource(tab);
			} else {
				processSink(tab);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new SemanticException();
		}

	}

	private void processSource(TableDef_remove table) throws SemanticException, MetaException {
		TapFormatType tapType = table.getTapType();
		String name = table.getName();
		String db = table.getDB();
		String tb = table.getTB();
		String path = table.getPath();
		Tap tap = null;
		Pipe pipe = null;
		if (db == null || table == null) {
			throw new MetaException("Missing Meta For Table " + db + "." + table);
		}
		TableMetadata meta = MetadataUtil.getMetadata(db, tb);
		TableSchema_remove schema = SchemaUtil.getTableSchema(meta);
		ctx.setTableSchema(name, schema);
		Fields fields = MetadataUtil.getFields(db, tb);

		switch (tapType) {
		case SEQUENCE: {
			String delimeter;
			try {
				delimeter = com.ebay.nest.utils.MetadataUtil.getFieldDelimiter(tb);
			} catch (Exception ex) {
				delimeter = NestConf.FIELD_DELIMETER;
			}
			tap = new Hfs(new EbaySequenceFile(new Fields("line")), path, SinkMode.KEEP);
			pipe = new Format(name, delimeter, fields);

			CastTypeOP castTypeOP = new CastTypeOP(fields, meta);
			pipe = new Each(pipe, Fields.ALL, castTypeOP, Fields.RESULTS);
			break;
		}
		case TEXT: {
			if ("1".equals(ctx.getProperty("use_local_connector"))) {
				tap = new FileTap(new cascading.scheme.local.TextLine(), path + "/" + tb + ".dat", SinkMode.KEEP);

			} else {
				tap = new Hfs(new TextLine(), path, SinkMode.KEEP);

			}
			pipe = new Format(name, NestConf.FIELD_DELIMETER, fields);

			CastTypeOP castTypeOP = new CastTypeOP(fields, meta);
			pipe = new Each(pipe, Fields.ALL, castTypeOP, Fields.RESULTS);
			break;
		}
		case HFS:
			break;
		case CVS: {
			if ("1".equals(ctx.getProperty("use_local_connector"))) {
				tap = new FileTap(new cascading.scheme.local.TextLine(), path, SinkMode.KEEP);
			} else {
				tap = new Hfs(new TextLine(), path, SinkMode.KEEP);

			}
			pipe = new Format(name, ",", fields);
			CastTypeOP castTypeOP = new CastTypeOP(fields, meta);
			pipe = new Each(pipe, Fields.ALL, castTypeOP, Fields.RESULTS);
			break;
		}
		case FILE:
		default:
			tap = new Hfs(new TextLine(), path, SinkMode.KEEP);
			pipe = new Pipe("init");
			break;

		}
		ctx.setPipeFields(pipe, fields);
		ctx.addPipeSource(pipe, name);
		ctx.addSourceTap(name, tap);
		ctx.setPipe(name, pipe);
	}

	private void processSink(TableDef_remove table) throws SemanticException, MetaException {
		TapFormatType tapType = table.getTapType();
		String name = table.getName();
		String db = table.getDB();
		String tb = table.getTB();
		String path = table.getPath();
		Fields fields = null;
		if (db != null && tb != null) {
			fields = MetadataUtil.getFields(db, tb);
		}
		Tap tap = null;
		switch (tapType) {
		case SEQUENCE:
			tap = new Hfs(new EbaySequenceFile(new Fields("line")), path, SinkMode.REPLACE);
			break;
		case TEXT:
			if ("1".equals(ctx.getProperty("use_local_connector"))) {
				tap = new FileTap(new cascading.scheme.local.TextDelimited(false, NestConf.FIELD_DELIMETER), path + "/"
						+ tb + ".dat", SinkMode.REPLACE);
			} else {
				tap = new Hfs(new TextDelimited(), path, SinkMode.REPLACE);
			}
			// tap = new Hfs(new FormattedSequenceFile(new Fields("line")), path,
			// SinkMode.REPLACE);
			break;
		case HFS:
			break;
		case CVS:
			if ("1".equals(ctx.getProperty("use_local_connector"))) {
				tap = new FileTap(new cascading.scheme.local.TextLine(), path, SinkMode.REPLACE);
			} else {
				tap = new Hfs(new TextLine(), path, SinkMode.REPLACE);

			}
			break;
		case FILE:

		default:
			break;
		}
		ctx.addSinkTap(name, tap);
		ctx.setSinkFields(name, fields);

	}
}
