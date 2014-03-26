package com.ebay.nest._remove.method;
//package com.ebay.ddi.seller.sql2cascading.method;
//
//import com.ebay.ddi.seller.potengine.cqlparser.DedupParser;
//import com.ebay.ddi.seller.potengine.cqlparser.pojo.CQLTable;
//import com.ebay.ddi.seller.potengine.cqlparser.pojo.OrderByPair;
//import com.ebay.ddi.seller.potengine.logicpot.DedupLogic;
//import com.ebay.ddi.seller.sql2cascading.pojo.SQLPipe;
//
//public class DedupMethod extends Method {
//
//	public DedupMethod(DedupParser parser) {
//	}
//
//	@Override
//	public SQLPipe execute() throws Exception {
//		DDiTable sourceTable = firstSourceTable();
//		String[] partitions = parser.getPartitonByColumns();
//		OrderByPair[] orderByPairs = parser.getOrderByColumns();
//
//		DedupLogic dedupLogic = new DedupLogic(potPipe(sourceTable),
//				partitions, orderByPairs);
//		return dedupLogic.build();
//	}
//
//}
