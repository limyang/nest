package com.ebay.nest._remove.method;
//package com.ebay.nest.method;
//
//import java.util.Map;
//
//import com.ebay.nest.core.Context;
//import com.ebay.nest.method.logic.DelInsLogic;
//
//import cascading.pipe.Pipe;
//
//public class DelInsMethod extends Method {
//
//	public DelInsMethod(Context context, Map<String, Object> params) {
//		super(context, params);
//	}
//
//	@Override
//	public void invoke() throws Exception {
//		setSinkSourceMap(targetTable(), baseTable(), stgTable());
//		Pipe targetPipe = null;
//		if (params.isPositionMode()) {
//			// position mode
//			int[] joinPos = params.getJoinOnPos();
//			DelInsLogic delInsLogic = new DelInsLogic(basePipe(), stgPipe(),
//					baseFields(), stgFields(), joinPos);
//			targetPipe = delInsLogic.build();
//		} else {
//			String[] joinColumns = params.getJoinOnNames();
//			DelInsLogic delInsLogic = new DelInsLogic(basePipe(), stgPipe(),
//					baseFields(), stgFields(), joinColumns);
//			targetPipe = delInsLogic.build();
//		}
//		setResult(targetTable().tagName(), targetPipe, targetFields());
//	}
//
//}
