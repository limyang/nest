package com.ebay.nest._remove.method.logic;
//package com.ebay.ddi.seller.sql2cascading.method.logic;
//
//import java.util.Map;
//
//import cascading.operation.Insert;
//import cascading.pipe.Each;
//import cascading.pipe.Pipe;
//import cascading.pipe.assembly.Discard;
//import cascading.pipe.assembly.Retain;
//import cascading.tuple.Fields;
//
//import com.ebay.ddi.seller.potengine.cqlparser.pojo.CQLTable;
//import com.ebay.ddi.seller.sql2cascading.operation.SplitDatetimeOP;
//import com.ebay.ddi.seller.sql2cascading.pojo.PotField;
//import com.ebay.ddi.seller.sql2cascading.pojo.SQLFields;
//import com.ebay.ddi.seller.sql2cascading.pojo.SQLPipe;
//import com.ebay.ddi.seller.sql2cascading.pojo.StringPair;
//
//public class SplitDateTimeLogic implements Logic {
//
//	private SQLPipe sourcePotPipe;
//	private Map<String, StringPair> mappingMap;
//
//	public SplitDateTimeLogic(SQLPipe sourcePotPipe,
//			Map<String, StringPair> mappingMap) {
//		this.sourcePotPipe = sourcePotPipe;
//		this.mappingMap = mappingMap;
//	}
//
//	@Override
//	public SQLPipe build() throws Exception {
//		Pipe pipe = sourcePotPipe.pipe();
//		SQLFields sourcePotFields = sourcePotPipe.potFields();
//		SQLFields targetPotFields = new SQLFields(sourcePotFields);
//		for (String key : mappingMap.keySet()) {
//			StringPair colPair = mappingMap.get(key);
//			String dateCol = colPair.v1;
//			String timeCol = colPair.v2;
//
//			targetPotFields.append(new PotField(dateCol, java.util.Date.class));
//			targetPotFields.append(new PotField(timeCol, java.sql.Time.class));
//
//			pipe = new Each(pipe, Fields.ALL, new Insert(new Fields(dateCol),
//					new Object[] { null }), Fields.ALL);
//			pipe = new Each(pipe, Fields.ALL, new Insert(new Fields(timeCol),
//					new Object[] { null }), Fields.ALL);
//
//			SplitDatetimeOP splitter = new SplitDatetimeOP(
//					targetPotFields.fields(), key, dateCol, timeCol);
//			pipe = new Each(pipe, Fields.ALL, splitter, Fields.RESULTS);
//			targetPotFields.remove(key);
//			pipe = new Discard(pipe, new Fields(key));
//		}
//		pipe = new Retain(pipe, targetPotFields.fields());
//		return new SQLPipe(pipe, targetPotFields);
//	}
//}
