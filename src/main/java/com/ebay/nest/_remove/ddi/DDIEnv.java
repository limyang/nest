package com.ebay.nest._remove.ddi;
//package com.ebay.nest.ddi;
//
//import org.apache.commons.io.FilenameUtils;
//
//import cascading.pipe.Each;
//import cascading.pipe.Pipe;
//import cascading.scheme.hadoop.SequenceFile;
//import cascading.scheme.hadoop.TextDelimited;
//import cascading.scheme.hadoop.TextLine;
//import cascading.tap.SinkMode;
//import cascading.tap.Tap;
//import cascading.tap.hadoop.Hfs;
//import cascading.tuple.Fields;
//
//import com.ebay.nest.assembly.Format;
//import com.ebay.nest.core.Context;
//import com.ebay.nest.metadata.MetadataU;
//import com.ebay.nest.operation.CastTypeOP;
//import com.ebay.nest.operation.EmptyStringToNullOP;
//
//public class DDIEnv {
//
//	private Uow uowFrom;
//	private Uow uowTo;
//	private String baseDir;
//	private boolean isLocal;
//
//	public DDIEnv(Uow uowFrom, Uow uowTo, String baseDir, boolean isLocal) {
//		this.uowFrom = uowFrom;
//		this.uowTo = uowTo;
//		this.baseDir = baseDir;
//		this.isLocal = isLocal;
//	}
//
//	public DDIEnv(Context context) {
//		this.uowFrom = new Uow(context.getProperty("uow_from"));
//		this.uowTo = new Uow(context.getProperty("uow_to"));
//		this.baseDir = context.getProperty("base_data_dir");
//		String test = context.getProperty("is_local");
//		isLocal = test.equals("1") ? true : false;
//	}
//
//	public Tap getSourceTap(DDiTable table) throws Exception {
//		String path = getPath(table);
//		if (!isLocal && table.isSnapshot()) {
//			// run in prod env and in snapshot path
//			return new Hfs(new SequenceFile(MetadataU.getFields(table.getDB(),
//					table.getTB())), path, SinkMode.KEEP);
//		}
//		return new Hfs(new TextLine(), path);
//	}
//
//	public Tap getSinkTap(DDiTable table) throws Exception {
//		String path = getPath(table);
//		if (!isLocal && table.isSnapshot()) {
//			// run in prod env and in snapshot path
//			return new Hfs(new SequenceFile(MetadataU.getFields(table.getDB(),
//					table.getTB())), path, SinkMode.REPLACE);
//		}
//		// other wise it is text line
//		return new Hfs(
//				new TextDelimited(false, Character.toString((char) 127)), path,
//				SinkMode.REPLACE);
//	}
//
//	public Pipe createPipe(DDiTable table) throws Exception {
//		Pipe pipe = null;
//		if (!isLocal && table.isSnapshot()) {
//			pipe = new Pipe(table.tagName());
//		} else {
//			pipe = new Format(table.tagName(), MetadataU.getFields(
//					table.getDB(), table.getTB()));
//		}
//
//		String[] fromNames = MetadataU.getFieldNames(table.getDB(),
//				table.getTB());
//		EmptyStringToNullOP emptyStringToNullOP = new EmptyStringToNullOP(
//				new Fields(fromNames));
//		pipe = new Each(pipe, Fields.ALL, emptyStringToNullOP, Fields.RESULTS);
//		CastTypeOP castTypeOP = new CastTypeOP(new Fields(fromNames),
//				MetadataU.getMetadata(table.getDB(), table.getTB()));
//		pipe = new Each(pipe, Fields.ALL, castTypeOP, Fields.RESULTS);
//		return pipe;
//	}
//
//	public String getPath(DDiTable table) {
//		if (table.isSnapshot() && table.isUowFrom()) {
//			return FilenameUtils.normalize(baseDir + "/" + table.getTB()
//					+ "/snapshot/" + uowFrom.getPathUtilDay00());
//		}
//
//		if (table.isSnapshot() && table.isUowTo()) {
//			return FilenameUtils.normalize(baseDir + "/" + table.getTB()
//					+ "/snapshot/" + uowTo.getPathUtilDay00());
//		}
//
//		if (table.isStaging() && table.isUowFrom()) {
//			return FilenameUtils.normalize(baseDir + "/" + table.getTB()
//					+ "/stg/" + uowFrom.getPathUtilDay00());
//		}
//
//		if (table.isStaging() && table.isUowTo()) {
//			return FilenameUtils.normalize(baseDir + "/" + table.getTB()
//					+ "/stg/" + uowTo.getPathUtilDay00());
//		}
//		return null;
//	}
//
//}
