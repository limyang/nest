package com.ebay.nest._remove.method;
//package com.ebay.nest.method;
//
//import java.util.Map;
//
//import cascading.pipe.Pipe;
//
//import com.ebay.nest.core.Context;
//import com.ebay.nest.method.logic.DirectLogic;
//
//public class DirectMethod extends Method {
//
//	public DirectMethod(Context context, Map<String, Object> params) {
//		super(context, params);
//	}
//
//	public void invoke() throws Exception {
//		DirectLogic directLogic = new DirectLogic(stgPipe(), stgFields(),
//				targetFields(), params.getColumnMode());
//		Pipe pipe = directLogic.build();
//		setResult(targetTable().tagName(), pipe, targetFields(),
//				getPipe(stgTable()));
//	}
//}
