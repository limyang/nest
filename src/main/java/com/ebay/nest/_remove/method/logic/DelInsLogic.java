package com.ebay.nest._remove.method.logic;
//package com.ebay.nest.method.logic;
//
//import cascading.pipe.CoGroup;
//import cascading.pipe.Each;
//import cascading.pipe.Merge;
//import cascading.pipe.Pipe;
//import cascading.pipe.joiner.LeftJoin;
//import cascading.tuple.Fields;
//
//import com.ebay.nest.assembly.Prefix;
//import com.ebay.nest.method.ColumnMode;
//import com.ebay.nest.operation.MissJoinFilter;
//
//public class DelInsLogic extends GeneralLogic {
//
//	public DelInsLogic(Pipe basePipe, Pipe incPipe, Fields baseFields,
//			Fields incFields, String[] joinNames) {
//		super(basePipe, incPipe, baseFields, incFields, joinNames);
//	}
//
//	public DelInsLogic(Pipe basePipe, Pipe incPipe, Fields baseFields,
//			Fields incFields, int[] joinPos) {
//		super(basePipe, incPipe, baseFields, incFields, joinPos);
//	}
//
//	public Pipe build() throws Exception {
//		alignIncPipeAndFields();
//		Fields alignedIncFields = incFields();
//		Pipe alignedIncPipe = incPipe();
//		prefixIncPipeAndFields();
//		Pipe pipe = new CoGroup(basePipe(), joinFields(), incPipe(),
//				prefixedJoinFields(), new LeftJoin());
//		MissJoinFilter missJoinFilter = new MissJoinFilter(allFields(),
//				joinNames());
//		Pipe newBasePipe = new Each(pipe, Fields.ALL, missJoinFilter,
//				Fields.RESULTS);
//		newBasePipe = retainToBase(newBasePipe);
//		DirectLogic directLogic = new DirectLogic(alignedIncPipe,
//				alignedIncFields, baseFields(), ColumnMode.NAME_MODE);
//		Pipe newIncPipe = directLogic.build();
//		return new Merge(newBasePipe, newIncPipe);
//	}
//}
