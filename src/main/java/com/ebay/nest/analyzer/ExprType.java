package com.ebay.nest.analyzer;

public enum ExprType {

	OP_FUNC,

	OP_DOT,

	OP_PLUS,

	OP_MINUS,

	OP_DIVIDE,

	OP_MOD,

	OP_MULTIPLY,

	OP_OR,

	OP_AND,

	OP_EQ,

	OP_NE,

	OP_LESS,

	OP_LE,

	OP_GREAT,

	OP_GE,

	OP_NOOP,

	OP_BOOL, //deprecated!

	BOOLEAN,

	BYTEINT,

	SMALLINT,

	INTEGER,

	BIGINT,

	FLOAT,

	DECIMAL,

	NULL,

	CHAR,

	DATE,

	TIME,

	TIMESTAMP,

	INTERVAL,

	PERIOD,

}
