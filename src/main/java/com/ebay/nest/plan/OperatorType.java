package com.ebay.nest.plan;

public enum OperatorType {
	
	JOIN(0),

	MAPJOIN(1),

	EXTRACT(2),

	FILTER(3),

	FORWARD(4),

	GROUPBY(5),

	LIMIT(6),

	SCRIPT(7),

	SELECT(8),

	TABLESCAN(9),

	FILESINK(10),

	REDUCESINK(11),

	UNION(12),

	UDTF(13),

	LATERALVIEWJOIN(14),

	LATERALVIEWFORWARD(15),

	HASHTABLESINK(16),

	HASHTABLEDUMMY(17),

	PTF(18),

	MUX(19),

	DEMUX(20);

	private final int value;

	private OperatorType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static OperatorType findByValue(int value) {
		switch (value) {
		case 0:
			return JOIN;
		case 1:
			return MAPJOIN;
		case 2:
			return EXTRACT;
		case 3:
			return FILTER;
		case 4:
			return FORWARD;
		case 5:
			return GROUPBY;
		case 6:
			return LIMIT;
		case 7:
			return SCRIPT;
		case 8:
			return SELECT;
		case 9:
			return TABLESCAN;
		case 10:
			return FILESINK;
		case 11:
			return REDUCESINK;
		case 12:
			return UNION;
		case 13:
			return UDTF;
		case 14:
			return LATERALVIEWJOIN;
		case 15:
			return LATERALVIEWFORWARD;
		case 16:
			return HASHTABLESINK;
		case 17:
			return HASHTABLEDUMMY;
		case 18:
			return PTF;
		case 19:
			return MUX;
		case 20:
			return DEMUX;
		default:
			return null;
		}
	}
}
