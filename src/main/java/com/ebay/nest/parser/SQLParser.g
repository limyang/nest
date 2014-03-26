parser grammar SQLParser;

options
{
tokenVocab=SQLLexer;
output=AST;
ASTLabelType=CommonTree;
backtrack=false;
k=3;
}

import Expr;

tokens {
TOK_ROOT;
TOK_DELETE;
TOK_INSERT;
TOK_UPDATE;
TOK_SELECT;
TOK_DEFINE;
TOK_SELECTDI;
TOK_SELEXPR;
TOK_FROM;
TOK_DIR;
TOK_LOCAL_DIR;
TOK_TABREF;
TOK_SUBQUERY;
TOK_DESTINATION;
TOK_ALLCOLREF;
TOK_TABLE_OR_COL;
TOK_FUNCTION;
TOK_FUNCTIONSTAR;
TOK_WHERE;
TOK_OP_EQ;
TOK_OP_NE;
TOK_OP_LE;
TOK_OP_LT;
TOK_OP_GE;
TOK_OP_GT;
TOK_OP_DIV;
TOK_OP_ADD;
TOK_OP_SUB;
TOK_OP_MUL;
TOK_OP_MOD;
TOK_OP_BITAND;
TOK_OP_BITNOT;
TOK_OP_BITOR;
TOK_OP_BITXOR;
TOK_OP_AND;
TOK_OP_OR;
TOK_OP_NOT;
TOK_OP_LIKE;
TOK_TRUE;
TOK_FALSE;
TOK_EXPLIST;
TOK_GROUPBY;
TOK_HAVING;
TOK_ORDERBY;
TOK_UNION;
TOK_JOIN;
TOK_LEFTOUTERJOIN;
TOK_RIGHTOUTERJOIN;
TOK_FULLOUTERJOIN;
TOK_CROSSJOIN;
TOK_UNIQUEJOIN;
TOK_NULL;
TOK_ISNULL;
TOK_ISNOTNULL;
TOK_INTEGER;
TOK_BOOLEAN;
TOK_FLOAT;
TOK_DATE;
TOK_DATELITERAL;
TOK_DATETIME;
TOK_TIMESTAMP;
TOK_BINARY;
TOK_DECIMAL;
TOK_LEFTSEMIJOIN;
TOK_STRINGLITERALSEQUENCE;
TOK_TABSORTCOLNAMEASC;
TOK_TABSORTCOLNAMEDESC;
TOK_TMP_FILE;
TOK_TABNAME;
TOK_META;
TOK_PATH;
TOK_DEFINE_TABLE_STATEMENT;
TOK_DEFINE_SOURCE_STATEMENT;
TOK_DEFINE_TARGET_STATEMENT;
TOK_OVERWRITE_TARGET_STATEMENT;
TOK_CREATE_VIEW_STATEMENT;
TOK_SUBQUERY_STATEMENT;
TOK_UPDATE_STATEMENT;
TOK_DELETE_STATEMENT;
TOK_INSERT_STATEMENT;
TOK_INVOKE_STATEMENT;
TOK_INVOKE;
TOK_PARAMETERS;
TOK_PARAMETER;
TOK_PARAMETER_VALUE;
TOK_PARAMETER_ARRAY;
TOK_SETITEM;
TOK_SELECT_QUERY;
TOK_UPDATE_QUERY;
TOK_DELETE_QUERY;
TOK_INSERT_QUERY;
TOK_ALIAS;
TOK_VIEWNAME;
TOK_FUNCTIONDI;
TOK_WINDOWSPEC;
TOK_PARTITIONINGSPEC;
TOK_DISTRIBUTEBY;
TOK_WINDOWRANGE;
TOK_WINDOWVALUES;
TOK_NULL_STATEMENT;
TOK_QUALIFY;
TOK_SMALLINT;
TOK_BYTEINT;
TOK_BIGINT;
TOK_CHAR;
TOK_TIME;
TOK_HINTLIST;
TOK_HINT;
TOK_MAPJOIN;
TOK_STREAMTABLE;
TOK_HOLD_DDLTIME;
TOK_HINTARGLIST;
TOK_DATA_TYPE;
TOK_DATA_ATTRIBUTE;
TOK_COLUMN;

}


@header {
package com.ebay.nest.parser;

import java.util.Collection;
import java.util.HashMap;
}


@members {
ArrayList<ParseError> errors = new ArrayList<ParseError>();
Stack msgs = new Stack<String>();

@Override
public Object recoverFromMismatchedSet(IntStream input,RecognitionException re, BitSet follow) throws RecognitionException {
	throw re;
}

@Override
public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
	errors.add(new ParseError(this, e, tokenNames));
}

@Override
public String getErrorHeader(RecognitionException e) {
	String header = null;
	if (e.charPositionInLine < 0 && input.LT(-1) != null) {
		Token t = input.LT(-1);
		header = "line " + t.getLine() + ":" + t.getCharPositionInLine();
	} else {
		header = super.getErrorHeader(e);
	}
	
	return header;
}
  
@Override
public String getErrorMessage(RecognitionException e, String[] tokenNames) {
	String msg = null;

	String[] xlateNames = new String[tokenNames.length];
	for (int i = 0; i < tokenNames.length; ++i) {
		xlateNames[i] = tokenNames[i];
	}

	if (e instanceof NoViableAltException) {
		@SuppressWarnings("unused")
		NoViableAltException nvae = (NoViableAltException) e;
		// for development, can add
		// "decision=<<"+nvae.grammarDecisionDescription+">>"
		// and "(decision="+nvae.decisionNumber+") and
		// "state "+nvae.stateNumber
		msg = "cannot recognize input near"
				+ (input.LT(1) != null ? " " + getTokenErrorDisplay(input.LT(1)) : "")
				+ (input.LT(2) != null ? " " + getTokenErrorDisplay(input.LT(2)) : "")
				+ (input.LT(3) != null ? " " + getTokenErrorDisplay(input.LT(3)) : "");
	} else if (e instanceof MismatchedTokenException) {
		MismatchedTokenException mte = (MismatchedTokenException) e;
		msg = super.getErrorMessage(e, xlateNames) + (input.LT(-1) == null ? "":" near '" + input.LT(-1).getText()) + "'";
	} else if (e instanceof FailedPredicateException) {
		FailedPredicateException fpe = (FailedPredicateException) e;
		msg = "Failed to recognize predicate '" + fpe.token.getText() + "'. Failed rule: '" + fpe.ruleName + "'";
	} else {
		msg = super.getErrorMessage(e, xlateNames);
	}

	if (msgs.size() > 0) {
		msg = msg + " in " + msgs.peek();
	}
    return msg;
}
}

@rulecatch {
catch (RecognitionException e) {
 reportError(e);
  throw e;
}
}

all
	:
	statements? EOF!
	;

statements
	:
	statement SEMICOLON? (statement SEMICOLON?)*
		->^(TOK_ROOT statement*)
	;

statement
	: 
	defineSourceStatement
	|defineTargetStatement
	|overwriteTargetStatement
	|createViewStatement
	|insertStatement
	|updateStatement
	|deleteStatement
	|invokeStatement
	|nullStatement
	;
	
nullStatement
	:
	SEMICOLON!
	;
	
defineTableStatement
	:
	KW_DEFINE (KW_SET|KW_MULTISET)? KW_TABLE tableName (LSQUARE (columnName dataType dataAttribute* )+  RSQUARE)?
		->^(TOK_DEFINE_TABLE_STATEMENT tableName ^(TOK_COLUMN columnName dataType dataAttribute)* )
	;
	
columnName
	:
	identifier
	;
	
dataType
	:
	KW_INTEGER
		->^(TOK_DATA_TYPE KW_INTEGER)
	|KW_SMALLINT
		->^(TOK_DATA_TYPE KW_SMALLINT)
	|KW_BIGINT
		->^(TOK_DATA_TYPE KW_BIGINT)
	|KW_BYTEINT
		->^(TOK_DATA_TYPE KW_BYTEINT)
	|KW_DATE
		->^(TOK_DATA_TYPE KW_DATE)
	|KW_TIME  ( LSQUARE Number RSQUARE)? (KW_WITH KW_TIME_ZONE)?
		->^(TOK_DATA_TYPE KW_TIME Number?)
	|KW_TIMESTAMP  ( LSQUARE Number RSQUARE)? (KW_WITH KW_TIME_ZONE)?
		->^(TOK_DATA_TYPE KW_TIMESTAMP Number?)
	|KW_INTERVAL KW_YEAR (LSQUARE Number RSQUARE)? (KW_TO KW_MONTH)
		->^(TOK_DATA_TYPE KW_INTERVAL KW_YEAR Number?)
	|KW_INTERVAL KW_MONTH (LSQUARE Number RSQUARE)?
		->^(TOK_DATA_TYPE KW_INTERVAL KW_MONTH Number?)
	|KW_INTERVAL KW_DAY (LSQUARE Number RSQUARE)? (KW_TO KW_HOUR|KW_MINUTE|KW_SECOND )?
		->^(TOK_DATA_TYPE KW_INTERVAL KW_DAY Number?)
	|KW_INTERVAL KW_HOUR (LSQUARE Number RSQUARE)? (KW_TO KW_MINUTE|KW_SECOND)?
		->^(TOK_DATA_TYPE KW_INTERVAL KW_HOUR Number?)
	|KW_INTERVAL KW_MINUTE (LSQUARE Number RSQUARE)? (KW_TO KE_SECOND)?
		->^(TOK_DATA_TYPE KW_INTERVAL KW_MINUTE Number?)
	|KW_INTERVAL KW_SECOND (LSQUARE Number (COMMA Number)? RSQUARE)?
		->^(TOK_DATA_TYPE KW_INTERVAL KW_SECOND Number?)
	|KW_FLOAT
		->^(TOK_DATA_TYPE KW_FLOAT)
	|KW_DECIMAL (LSQUARE Number (COMMA Number)? RSQUARE)?
		->^(TOK_DATA_TYPE KW_DECIMAL Number*)
	|KW_CHAR (LSQUARE Number RSQUARE)?
		->^(TOK_DATA_TYPE KW_CHAR Number?)
	|KW_VARCHAR LSQUARE Number RSQUARE
		->^(TOK_DATA_TYPE KW_VARCHAR Number)
	|KW_BYTE (LSQUARE Number RSQUARE)?
		->^(TOK_DATA_TYPE KW_BYTE Number*)
	|KW_VARBYTE LSQUARE Number RSQUARE
		->^(TOK_DATA_TYPE KW_VARBYTE Number)
	|KW_PERIOD KW_TIME?
		->^(TOK_DATA_TYPE KW_PERIOD KW_TIME)
	|KW_PERIOD KW_DATE
		->^(TOK_DATA_TYPE KW_PERIOD KW_DATE)
	|KW_PERIOD KW_TIMESTAMP  ( LSQUARE Number RSQUARE)? (KW_WITH KW_TIME_ZONE)?
		->^(TOK_DATA_TYPE KW_PERIOD KW_TIMESTAMP Number?)
	;
	
dataAttribute
	:
	KW_UPPERCASE
		->^(TOK_DATA_ATTRIBUTE KW_UPPERCASE)
	| (KW_NOT)? KW_CASESPECIFIC
		->^(TOK_DATA_ATTRIBUTE KW_CASESPECIFIC KW_NOT?)
	|KW_FORMAT  StringLiteral
		->^(TOK_DATA_ATTRIBUTE KW_FORMAT StringLiteral)
	|KW_WITH KW_DEFAULT
		->^(TOK_DATA_ATTRIBUTE KW_DEFAULT)
	|(KW_NOT)? KW_NULL
		->^(TOK_DATA_ATTRIBUTE KW_NULL KW_NOT?)

	;	
	
defineSourceStatement
	:
	KW_DEFINE KW_SOURCE name=tableName (KW_AS|KW_WITH)? KW_META meta=metaName KW_PATH path=pathString
		->^(TOK_DEFINE_SOURCE_STATEMENT ^(TOK_DEFINE KW_SOURCE $name $meta $path))
	;
	
defineTargetStatement
	:
	KW_DEFINE KW_TARGET name=tableName (KW_AS|KW_WITH)? (KW_META meta=metaName)? KW_PATH path=pathString
		->^(TOK_DEFINE_TARGET_STATEMENT ^(TOK_DEFINE KW_TARGET $name $meta? $path))
	;
	
metaName
    :
	db=identifier DOT tab=identifier
    	->^(TOK_META $db $tab)
    ;

pathString
	:
	path=StringLiteral
		->^(TOK_PATH $path)
	;
	
overwriteTargetStatement
	:
   	KW_OVERWRITE KW_TARGET name=tableName KW_AS queryStatement
   		->^(TOK_OVERWRITE_TARGET_STATEMENT ^(TOK_DESTINATION $name) queryStatement)
	;
	
createViewStatement
	:
   	KW_CREATE KW_VIEW name=pipeName KW_AS queryStatement
   		->^(TOK_CREATE_VIEW_STATEMENT ^(TOK_DESTINATION $name) queryStatement) 
	;
	
queryStatement
	:
	selectQuery | updateQuery | insertQuery | deleteQuery
	;

updateStatement
	:
	updateQuery
		->^(TOK_UPDATE_STATEMENT updateQuery)
	;
	
insertStatement
	:
	insertQuery
		->^(TOK_INSERT_STATEMENT insertQuery)
	;
	
deleteStatement
	:
	deleteQuery
		->^(TOK_DELETE_STATEMENT deleteQuery)
	;
	
selectQuery
	:
	selectClause
	fromClause
	whereClause?
   	groupByClause?
   	havingClause?
   	orderByClause?
   	qualifyClause?
		-> ^(TOK_SELECT_QUERY fromClause selectClause whereClause? groupByClause? havingClause? orderByClause? qualifyClause?)
   ;
   
updateQuery
	:
	KW_UPDATE name=updateTable
	fromClause
	KW_SET setItem (COMMA  setItem)* 
	whereClause?
		->^(TOK_UPDATE_QUERY fromClause ^(TOK_UPDATE $name setItem+)  whereClause?)
	;
	
deleteQuery
	:
	KW_DELETE (name=deleteTable)?
	fromClause
	whereClause?
		->^(TOK_DELETE_QUERY fromClause ^(TOK_DELETE $name?) whereClause?)
	;
	
insertQuery
	:
	KW_INSERT (KW_INTO)? name=tableName LPAREN  identifier (COMMA identifier)*  RPAREN (KW_AS)?
	selectClause 
	fromClause 
	whereClause?
	groupByClause? 
	havingClause?
	orderByClause?
	qualifyClause?
		-> ^(TOK_INSERT_QUERY fromClause ^(TOK_INSERT $name identifier+) selectClause whereClause? groupByClause? havingClause? orderByClause? qualifyClause?)
	;
	
setItem
	:
	identifier EQUAL expression
		->^(TOK_SETITEM identifier expression)
	;
	
pipeName
	:
	pipe=identifier
		-> ^(TOK_VIEWNAME $pipe)
    ;
	   
   
havingClause
    :
    KW_HAVING havingCondition -> ^(TOK_HAVING havingCondition)
    ;
    
havingCondition
    :
    expression
    ;
    
orderByClause
    :
    KW_ORDER KW_BY columnRefOrder ( COMMA columnRefOrder)* 
    	-> ^(TOK_ORDERBY columnRefOrder+)
    ;
    
qualifyClause
	:
	KW_QUALIFY expression
		->^(TOK_QUALIFY expression)
	;     
    
    
columnRefOrder
    : 
    expression (asc=KW_ASC | desc=KW_DESC)?
    -> {$desc == null}? ^(TOK_TABSORTCOLNAMEASC expression)
    ->                  ^(TOK_TABSORTCOLNAMEDESC expression)
    ;
    
selectClause
	:
	KW_SELECT (KW_ALL | dist=KW_DISTINCT)? selectList
	-> {$dist != null}? ^(TOK_SELECTDI selectList)
	-> ^(TOK_SELECT  selectList)
    ;
    
groupByClause
    :
    KW_GROUP KW_BY
    groupByExpression
    ( COMMA groupByExpression )*
    	-> ^(TOK_GROUPBY groupByExpression+)
    ;
    
groupByExpression
    :
    expression
    ;

selectList
	:
	selectItem (COMMA  selectItem)* -> selectItem+
	;

selectItem
	:
	selectExpression (KW_AS? identifier)?
		-> ^(TOK_SELEXPR selectExpression identifier*)
	;

selectExpression
    :
    expression | tableAllColumns
    ;

selectExpressionList
    :
    selectExpression (COMMA selectExpression)* -> ^(TOK_EXPLIST selectExpression+)
    ;

tableAllColumns
	: 
	STAR
		-> ^(TOK_ALLCOLREF)
    |tableName DOT STAR
		-> ^(TOK_ALLCOLREF tableName)
    ;

tableOrColumn
    :
    identifier -> ^(TOK_TABLE_OR_COL identifier)
    ;

expressionList
    :
    expression (COMMA expression)* -> ^(TOK_EXPLIST expression+)
    ;

fromClause
    :
    KW_FROM joinSource -> ^(TOK_FROM joinSource)
    ;

joinSource
    : fromSource ( joinToken^ fromSource (KW_ON! expression)? )*
    ;

joinToken
    :
      KW_JOIN                      -> TOK_JOIN
    | KW_INNER KW_JOIN             -> TOK_JOIN
    | COMMA						   -> TOK_JOIN
    | KW_CROSS KW_JOIN             -> TOK_CROSSJOIN
    | KW_LEFT  (KW_OUTER)? KW_JOIN -> TOK_LEFTOUTERJOIN
    | KW_RIGHT (KW_OUTER)? KW_JOIN -> TOK_RIGHTOUTERJOIN
    | KW_FULL  (KW_OUTER)? KW_JOIN -> TOK_FULLOUTERJOIN
    | KW_LEFT KW_SEMI KW_JOIN      -> TOK_LEFTSEMIJOIN
    ;

fromSource
    :
    tableSource | subQuerySource
    ;

tableSource
    : tabname=tableName (KW_AS? alias=identifier)?
		-> ^(TOK_TABREF $tabname  $alias?)
    ;
    
updateTable
	: (tabname=tableName KW_AS?)? alias=identifier
		-> ^(TOK_ALIAS $alias $tabname?)
	;
	
deleteTable
	: (tabname=tableName KW_AS?)? alias=identifier
		-> ^(TOK_ALIAS $alias $tabname?)
	;

tableName
    :
	db=identifier DOT tab=identifier
    	-> ^(TOK_TABNAME $db $tab)
    |
	tab=identifier
		-> ^(TOK_TABNAME $tab)
    ;

subQuerySource
    :
    LPAREN subQuery RPAREN identifier -> ^(TOK_SUBQUERY subQuery identifier)
    ;
    
subQuery
	:
	queryStatement
		-> ^(TOK_SUBQUERY_STATEMENT  ^(TOK_DESTINATION ^(TOK_DIR TOK_TMP_FILE)) queryStatement )
	;

whereClause
    :
    KW_WHERE searchCondition -> ^(TOK_WHERE searchCondition)
    ;

searchCondition
    :
    expression
    ;
    
hintClause
    :
    DIVIDE STAR PLUS hintList STAR DIVIDE -> ^(TOK_HINTLIST hintList)
    ;

hintList
    :
    hintItem (COMMA hintItem)* -> hintItem+
    ;

hintItem

    :
    hintName (LPAREN hintArgs RPAREN)? -> ^(TOK_HINT hintName hintArgs?)
    ;

hintName
    :
    KW_MAPJOIN -> TOK_MAPJOIN
    | KW_STREAMTABLE -> TOK_STREAMTABLE
    | KW_HOLD_DDLTIME -> TOK_HOLD_DDLTIME
    ;

hintArgs
    :
    hintArgName (COMMA hintArgName)* -> ^(TOK_HINTARGLIST hintArgName+)
    ;

hintArgName
    :
    identifier
    ;    
    
    
    
invokeStatement
    :
    KW_INVOKE Identifier LPAREN parameters? RPAREN
       ->^(TOK_INVOKE Identifier parameters? )
	;
	
parameters
    :
    parameter(COMMA parameter)+
      ->^(TOK_PARAMETERS parameter*)
    ;
    
parameter
    :
    Identifier COLON parameterValue
      ->^(TOK_PARAMETER Identifier parameterValue)
    ;

    
parameterValue
    :
    paramValIdentifier | parameterArray  
    ;
      

parameterArray
    :
    LSQUARE (parameterValue(COMMA parameterValue)*)?  RSQUARE
      ->^(TOK_PARAMETER_ARRAY parameterValue*)
    ;
    
paramValIdentifier
    :
    Identifier | StringLiteral | Number | ByteLengthLiteral
    ;
    