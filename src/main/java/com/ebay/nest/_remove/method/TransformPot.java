package com.ebay.nest._remove.method;
//package com.ebay.ddi.seller.sql2cascading.method;
//
//import java.util.List;
//import java.util.Map;
//
//import com.ebay.ddi.seller.potengine.cqlparser.TransformParser;
//import com.ebay.ddi.seller.potengine.cqlparser.pojo.CQLTable;
//import com.ebay.ddi.seller.potengine.logicpot.TransformLogic;
//import com.ebay.ddi.seller.sql2cascading.pojo.SQLPipe;
//
//public class TransformPot extends Method {
//
//	protected TransformParser parser;
//
//	public TransformPot(TransformParser parser) {
//		super(parser);
//		this.parser = parser;
//	}
//
//	@Override
//	public SQLPipe execute() throws Exception {
//		DDiTable sourceTable = firstSourceTable();
//		Map<String, String> transformMap = parser.getTransformMap();
//		Map<String, List<String>> columnMapping = parser.getColumnMapping();
//		TransformLogic transformLogic = new TransformLogic(
//				potPipe(sourceTable), transformMap, columnMapping);
//		SQLPipe potPipe = transformLogic.build();
//		return potPipe;
//	}
//
//}
