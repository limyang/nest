package com.ebay.nest._remove.method;
//package com.ebay.ddi.seller.sql2cascading.method;
//
//import java.util.Map;
//
//import cascading.pipe.Pipe;
//
//import com.ebay.ddi.seller.sql2cascading.context.Context;
//import com.ebay.ddi.seller.sql2cascading.method.logic.UpsertLogic;
//
//public class DDIStgUpsertMethod extends DDIMethod {
//
//	public DDIStgUpsertMethod(Context context, Map<String, Object> params) {
//		super(context, params);
//	}
//
//	public void invoke() throws Exception {
//		setSinkSourceMap(targetTable(), stgTable(), baseTable());
//		Pipe targetPipe = null;
//		if (params.isPositionMode()) {
//			// position mode
//			int[] joinPos = params.getJoinOnPos();
//			UpsertLogic upsertLogic = new UpsertLogic(basePipe(), stgPipe(),
//					baseFields(), stgFields(), joinPos);
//			targetPipe = upsertLogic.build();
//		} else {
//			// name mode
//			String[] joinColumns = params.getJoinOnNames();
//			UpsertLogic upsertLogic = new UpsertLogic(basePipe(), stgPipe(),
//					baseFields(), stgFields(), joinColumns);
//			targetPipe = upsertLogic.build();
//		}
//		setResult(targetTable().tagName(), targetPipe, targetFields());
//	}
//
//}
