package com.ebay.nest._remove.method.logic;
//package com.ebay.nest.method.logic;
//
//import in.masr.utils.ArrayU;
//import cascading.pipe.Each;
//import cascading.pipe.Pipe;
//import cascading.tuple.Fields;
//
//import com.ebay.nest.assembly.Align;
//import com.ebay.nest.ddi.Audit;
//import com.ebay.nest.method.ColumnMode;
//import com.ebay.nest.operation.CreateAuditOP;
//import com.ebay.nest.util.FieldsU;
//
//public class DirectLogic extends GeneralLogic {
//
//	public DirectLogic(String sourceName,String 
//			ColumnMode type) {
//		super(stgPipe, targetFields, stgFields, type);
//		
//	}
//
//	@Override
//	public Pipe build() throws Exception {
//		alignIncPipeAndFields();
//		Pipe pipe = new Align(incPipe(), incFields(), baseFields());
//		if (ArrayU.hasCommon(Audit.CREATE_COLUMNS, FieldsU.names(baseFields()))) {
//			CreateAuditOP createAuditOP = new CreateAuditOP(baseFields());
//			pipe = new Each(pipe, Fields.ALL, createAuditOP, Fields.RESULTS);
//		}
//		pipe = retainAndCastToBase(pipe);
//		return pipe;
//	}
//}
