package com.ebay.nest._remove.method.logic;
//package com.ebay.nest.method.logic;
//
//import cascading.pipe.Each;
//import cascading.pipe.Pipe;
//import cascading.pipe.assembly.Rename;
//import cascading.pipe.assembly.Retain;
//import cascading.tuple.Fields;
//
//import com.ebay.nest.assembly.Prefix;
//import com.ebay.nest.metadata.MetadataU;
//import com.ebay.nest.method.ColumnMode;
//import com.ebay.nest.operation.CastTypeOP;
//import com.ebay.nest.util.FieldsU;
//
//public abstract class GeneralLogic implements Logic {
//
//	protected static final String INC_PREFIX = "INC-";
//
//	protected Pipe basePipe;
//	protected Pipe incPipe;
//	protected Fields baseFields;
//	protected Fields incFields;
//	protected String[] joinNames;
//	protected int[] joinPos;
//	protected ColumnMode type;
//	protected String dbtb;
//
//	public GeneralLogic(Pipe basePipe, Pipe incPipe, Fields baseFields,
//			Fields incFields, String[] joinNames) {
//		this(basePipe, incPipe, baseFields, incFields, ColumnMode.NAME_MODE);
//		this.joinNames = joinNames;
//	}
//
//	public GeneralLogic(Pipe basePipe, Pipe incPipe, Fields baseFields,
//			Fields incFields, int[] joinPos) {
//		this(basePipe, incPipe, baseFields, incFields, ColumnMode.POSITION_MODE);
//		this.joinPos = joinPos;
//	}
//
//	private GeneralLogic(Pipe basePipe, Pipe incPipe, Fields baseFields,
//			Fields incFields, ColumnMode type) {
//		this(incPipe, baseFields, incFields, type);
//		this.basePipe = basePipe;
//	}
//
//	public void setTargetTable(String dbtb) {
//		this.dbtb = dbtb;
//	}
//
//	public GeneralLogic(Pipe incPipe, Fields baseFields, Fields incFields,
//			ColumnMode type) {
//		this.incPipe = incPipe;
//		this.baseFields = baseFields;
//		this.incFields = incFields;
//		this.type = type;
//
//	}
//
//	protected void alignIncPipeAndFields() {
//		if (type == ColumnMode.POSITION_MODE) {
//			int minLength = Math.min(baseFields.size(), incFields.size());
//			Fields subBaseFields = FieldsU.sub(baseFields, 0, minLength);
//			Pipe pipe = new Rename(incPipe, incFields, subBaseFields);
//			pipe = new Retain(pipe, subBaseFields);
//			incPipe = pipe;
//			Fields fields = FieldsU.rename(incFields, 0,
//					FieldsU.names(subBaseFields));
//			fields = FieldsU.sub(fields, 0, minLength);
//			incFields = fields;
//		} else {
//			Fields fields = FieldsU
//					.select(incFields, FieldsU.names(baseFields));
//			incPipe = new Retain(incPipe, fields);
//			incFields = FieldsU.select(incFields, FieldsU.names(baseFields));
//		}
//	}
//
//	protected void prefixIncPipeAndFields() {
//		incPipe = new Prefix(incPipe, INC_PREFIX, incFields);
//		incFields = FieldsU.prefix(incFields, INC_PREFIX);
//	}
//
//	protected Pipe retainAndCastToBase(Pipe pipe) {
//		pipe = new Retain(pipe, baseFields);
//		CastTypeOP castTypeOP = new CastTypeOP(baseFields,
//				MetadataU.getMetadata(database, table));
//		pipe = new Each(pipe, Fields.ALL, castTypeOP, Fields.RESULTS);
//		return pipe;
//	}
//
//	protected Pipe retainToBase(Pipe pipe) {
//		pipe = new Retain(pipe, baseFields);
//		return pipe;
//	}
//
//	protected Fields joinFields() {
//		return new Fields(joinNames());
//	}
//
//	protected Fields prefixedJoinFields() {
//		return FieldsU.prefix(joinFields(), INC_PREFIX);
//	}
//
//	protected String[] joinNames() {
//		if (type == ColumnMode.POSITION_MODE) {
//			return FieldsU.names(baseFields, joinPos);
//		} else {
//			return joinNames;
//		}
//	}
//
//	protected Fields allFields() {
//		Fields allFields = new Fields(FieldsU.names(baseFields()));
//		allFields = allFields.append(new Fields(FieldsU.names(incFields())));
//		return allFields;
//	}
//
//	public Pipe basePipe() {
//		return basePipe;
//	}
//
//	public Pipe incPipe() {
//		return incPipe;
//	}
//
//	public Fields baseFields() {
//		return baseFields;
//	}
//
//	public Fields incFields() {
//		return incFields;
//	}
//
//}
