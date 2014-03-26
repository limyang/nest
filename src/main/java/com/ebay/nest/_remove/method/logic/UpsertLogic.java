package com.ebay.nest._remove.method.logic;
//package com.ebay.nest.method.logic;
//
//import cascading.pipe.CoGroup;
//import cascading.pipe.Each;
//import cascading.pipe.Pipe;
//import cascading.pipe.joiner.OuterJoin;
//import cascading.tuple.Fields;
//
//import com.ebay.nest.operation.UpsertOP;
//
//public class UpsertLogic extends GeneralLogic {
//
//	public UpsertLogic(Pipe basePipe, Pipe incPipe, Fields baseFields,
//			Fields incFields, String[] joinNames) {
//		super(basePipe, incPipe, baseFields, incFields, joinNames);
//	}
//
//	public UpsertLogic(Pipe basePipe, Pipe incPipe, Fields baseFields,
//			Fields incFields, int[] joinPos) {
//		super(basePipe, incPipe, baseFields, incFields, joinPos);
//	}
//
//	@Override
//	public Pipe build() throws Exception {
//		alignIncPipeAndFields();
//		prefixIncPipeAndFields();
//		Pipe pipe = new CoGroup(basePipe(), joinFields(), incPipe(),
//				prefixedJoinFields(), new OuterJoin());
//		UpsertOP upsert = new UpsertOP(allFields(), joinNames());
//		pipe = new Each(pipe, Fields.ALL, upsert, Fields.RESULTS);
//		pipe = retainAndCastToBase(pipe);
//		return pipe;
//	}
//}
