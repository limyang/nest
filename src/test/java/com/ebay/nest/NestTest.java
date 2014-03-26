//package com.ebay.nest;
//
//import org.junit.Test;
//
//import cascading.flow.Flow;
//import cascading.flow.FlowDef;
//import cascading.flow.hadoop.HadoopFlowConnector;
//import cascading.pipe.Pipe;
//import cascading.scheme.hadoop.TextDelimited;
//import cascading.scheme.hadoop.TextLine;
//import cascading.tap.SinkMode;
//import cascading.tap.Tap;
//import cascading.tap.hadoop.Hfs;
//import cascading.tuple.Fields;
//
//import com.ebay.nest.analyzer.SemanticException;
//import com.ebay.nest.assembly.Format;
//import com.ebay.nest.metadata.MetaException;
//import com.ebay.nest.metadata.TableMetadata;
//import com.ebay.nest.metadata.TeradataMetadataClient;
//import com.ebay.nest.parser.ParseException;
//
//public class NestTest {
//	@Test
//	public void testSelect() throws MetaException, ParseException,
//			SemanticException {
//		TeradataMetadataClient client = new TeradataMetadataClient();
//		client.connect();
//		Nest nest = new Nest();
//		TableMetadata baseMeta = client.getMetadata("gdw_tables",
//				"ctlg_bld_c3_audit_log");
//		TableMetadata stgMeta = client.getMetadata("working",
//				"ctlg_bld_c3_audit_log_w");
//
//		Tap baseTap = new Hfs(
//				new TextLine(),
//				"/home/coolmore/workspace/eclipse/Nest/data/ctlg_bld_c3_audit_log/snapshot/2013/08/19/00");
//		Tap stgTap = new Hfs(
//				new TextLine(),
//				"/home/coolmore/workspace/eclipse/Nest/data/ctlg_bld_c3_audit_log/in/2013/08/20/00");
//		// Tap stgTap = new Hfs(new TextLine(),
//		// "data/ctlg_bld_c3_audit_log/in/2013/08/19/00");
//		Tap sinkTap = new Hfs(
//				new TextDelimited(false, Character.toString((char) 127)),
//				"/home/coolmore/workspace/eclipse/Nest/data/ctlg_bld_c3_audit_log/snapshot/2013/08/20/00",
//				SinkMode.REPLACE);
//
//		Fields baseFields = new Fields(baseMeta.getFieldNames());
//		Fields stgFields = new Fields(stgMeta.getFieldNames());
//		Pipe basePipe = new Format("base", baseFields);
//		Pipe stgPipe = new Format("stg", stgFields);
//
//		nest.set("base", basePipe, baseFields);
//		nest.set("stg", stgPipe, stgFields);
//		Pipe pipe = nest
//				.sql("select b.c3_audit_log_id  from base b, stg s where cast(b.c3_audit_log_id as decimal)=cast(s.c3_audit_log_id as decimal) ");
//
//		FlowDef flowDef = new FlowDef();
//		flowDef.addSource("base", baseTap).addSource("stg", stgTap)
//				.addTailSink(pipe, sinkTap);
//		Flow flow = new HadoopFlowConnector().connect(flowDef);
//		flow.complete();
//		client.close();
//	}
//}
