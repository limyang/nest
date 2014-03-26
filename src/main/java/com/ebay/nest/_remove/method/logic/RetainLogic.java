package com.ebay.nest._remove.method.logic;
//package com.ebay.ddi.seller.sql2cascading.method.logic;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import cascading.pipe.Pipe;
//import cascading.pipe.assembly.Rename;
//import cascading.pipe.assembly.Retain;
//import cascading.tuple.Fields;
//
//import com.ebay.ddi.seller.sql2cascading.pojo.PotField;
//import com.ebay.ddi.seller.sql2cascading.pojo.SQLFields;
//import com.ebay.ddi.seller.sql2cascading.pojo.SQLPipe;
//
//public class RetainLogic implements Logic {
//
//	private SQLPipe sourcePotPipe;
//	private Map<String, String> mapping;
//
//	public RetainLogic(SQLPipe sourcePotPipe, Map<String, String> mapping) {
//		this.sourcePotPipe = sourcePotPipe;
//		this.mapping = mapping;
//	}
//
//	public SQLPipe build() {
//		Pipe pipe = sourcePotPipe.pipe();
//		SQLFields sourcePotFields = sourcePotPipe.potFields();
//		int size = mapping.size();
//		String[] originNames = new String[size];
//		String[] targetNames = new String[size];
//		SQLFields targetPotFields = new SQLFields();
//		int i = 0;
//		for (String target : mapping.keySet()) {
//			String origin = mapping.get(target);
//			originNames[i] = origin;
//			targetNames[i] = target;
//			i++;
//
//			PotField originPotField = sourcePotFields.getPotField(origin);
//			PotField targetPotField = new PotField(target,
//					originPotField.type());
//			targetPotFields.append(targetPotField);
//		}
//		Fields originFields = new Fields(originNames);
//		Fields targetFields = new Fields(targetNames);
//		pipe = new Retain(pipe, originFields);
//		pipe = new Rename(pipe, originFields, targetFields);
//		pipe = new Retain(pipe, targetFields);
//		return new SQLPipe(pipe, targetPotFields);
//	}
//}
