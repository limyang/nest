package com.ebay.nest._remove.method.logic;
//package com.ebay.ddi.seller.sql2cascading.method.logic;
//
//import java.util.Collections;
//
//import cascading.operation.aggregator.First;
//import cascading.pipe.Every;
//import cascading.pipe.GroupBy;
//import cascading.pipe.Pipe;
//import cascading.tuple.Fields;
//
//import com.ebay.ddi.seller.potengine.cqlparser.pojo.OrderByPair;
//import com.ebay.ddi.seller.potengine.utils.FieldsU;
//import com.ebay.ddi.seller.sql2cascading.pojo.SQLFields;
//import com.ebay.ddi.seller.sql2cascading.pojo.SQLPipe;
//
//public class DedupLogic implements Logic {
//
//	private SQLPipe sourcePotPipe;
//	private String[] partitions;
//	private OrderByPair[] orderByPairs;
//
//	public DedupLogic(SQLPipe sourcePotPipe, String[] partitions,
//			OrderByPair[] orderByPairs) {
//		this.sourcePotPipe = sourcePotPipe;
//		this.partitions = partitions;
//		this.orderByPairs = orderByPairs;
//	}
//
//	public SQLPipe build() {
//		Pipe pipe = sourcePotPipe.pipe();
//		SQLFields sourcePotFields = sourcePotPipe.potFields();
//		Fields partitionFields = FieldsU.fields(partitions);
//		Fields orderByFields = new Fields();
//		for (OrderByPair pair : orderByPairs) {
//			orderByFields = orderByFields.append(new Fields(pair.name));
//			if (pair.direction.equals(OrderByPair.DESC)) {
//				orderByFields.setComparator(pair.name,
//						Collections.reverseOrder());
//			}
//		}
//		pipe = new GroupBy(pipe, partitionFields, orderByFields);
//		pipe = new Every(pipe, Fields.ALL, new First(), Fields.RESULTS);
//		return new SQLPipe(pipe, sourcePotFields);
//	}
//}
