package com.ebay.nest._remove.method.logic;
//package com.ebay.ddi.seller.sql2cascading.method.logic;
//
//import java.util.List;
//import java.util.Map;
//
//import cascading.operation.expression.ExpressionFunction;
//import cascading.pipe.Each;
//import cascading.pipe.Pipe;
//import cascading.tuple.Fields;
//
//import com.ebay.ddi.seller.potengine.cqlparser.pojo.CQLTable;
//import com.ebay.ddi.seller.potengine.utils.FieldsU;
//import com.ebay.ddi.seller.sql2cascading.pojo.SQLFields;
//import com.ebay.ddi.seller.sql2cascading.pojo.SQLPipe;
//
//public class TransformLogic implements Logic {
//
//	private SQLPipe sourcePotPipe;
//	private Map<String, String> trMap;
//	private Map<String, List<String>> columnMapping;
//
//	public TransformLogic(SQLPipe sourcePotPipt, Map<String, String> trMap,
//			Map<String, List<String>> columnMapping) {
//		this.sourcePotPipe = sourcePotPipt;
//		this.trMap = trMap;
//		this.columnMapping = columnMapping;
//	}
//
//	@Override
//	public SQLPipe build() throws Exception {
//		SQLFields sourcePotFields = sourcePotPipe.potFields();
//		Pipe pipe = sourcePotPipe.pipe();
//		for (String key : trMap.keySet()) {
//			List<String> fromColumnNameList = columnMapping.get(key);
//			String[] fromColumnNames = fromColumnNameList
//					.toArray(new String[0]);
//			Class[] types = sourcePotFields.getPrimeTypes(fromColumnNames);
//			String expression = trMap.get(key);
//			ExpressionFunction expFunc = new ExpressionFunction(
//					new Fields(key), expression, fromColumnNames, types);
//			Fields fromFields = FieldsU.fields(fromColumnNames);
//			pipe = new Each(pipe, fromFields, expFunc, Fields.REPLACE);
//		}
//		return new SQLPipe(pipe, sourcePotFields);
//	}
//}
