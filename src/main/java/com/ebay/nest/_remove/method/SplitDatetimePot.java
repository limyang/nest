package com.ebay.nest._remove.method;
//package com.ebay.ddi.seller.sql2cascading.method;
//
//import java.util.Map;
//
//import com.ebay.ddi.seller.potengine.cqlparser.SplitDatetimeParser;
//import com.ebay.ddi.seller.potengine.cqlparser.pojo.CQLTable;
//import com.ebay.ddi.seller.potengine.logicpot.SplitDateTimeLogic;
//import com.ebay.ddi.seller.sql2cascading.pojo.SQLPipe;
//import com.ebay.ddi.seller.sql2cascading.pojo.StringPair;
//
//public class SplitDatetimePot extends Method {
//
//	protected SplitDatetimeParser parser;
//
//	public SplitDatetimePot(SplitDatetimeParser parser) {
//		super(parser);
//		this.parser = parser;
//	}
//
//	@Override
//	public SQLPipe execute() throws Exception {
//		DDiTable sourceTable = firstSourceTable();
//		Map<String, StringPair> mappingMap = parser.getMappingMap();
//		SplitDateTimeLogic splitDateTimeLogic = new SplitDateTimeLogic(
//				potPipe(sourceTable), mappingMap);
//		SQLPipe potPipe = splitDateTimeLogic.build();
//		return potPipe;
//	}
//
//}
