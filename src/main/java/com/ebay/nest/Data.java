package com.ebay.nest;

import java.io.Serializable;

public interface Data extends Serializable {

	public TypeInfo getAttribute();

	public String toString();

	// internal use only
	public static final byte UNKNOWN = 0;
	public static final byte NULL = -1;
	public static final byte BOOLEAN = 1;

	public static final byte BYTEINT = 10; 
	public static final byte SMALLINT = 11;
	public static final byte INTEGER = 12;
	public static final byte BIGINT = 13;
	public static final byte FLOAT = 14;
	public static final byte DECIMAL = 15;
	public static final byte NUMERIC =16;
	
	
	public static final byte DATE = 20;
	public static final byte TIME = 21;
	public static final byte TIMESTAMP = 22;
	public static final byte DATETIME = 23;
	
	public static final byte BYTE = 30;
	public static final byte VARBYTE=31;
	public static final byte BLOB = 32;
	
	public static final byte CHAR = 40;
	public static final byte VARCHAR=41;
	public static final byte CLOB = 42;
	
	public static final byte INTERVAL=50;
	
	public static final byte PEROID=60;

	public static final byte TUPLE = 127;

}
