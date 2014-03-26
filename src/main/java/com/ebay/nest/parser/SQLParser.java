// $ANTLR 3.5 SQLParser.g 2013-11-27 01:50:16

package com.ebay.nest.parser;

import java.util.Collection;
import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class SQLParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "AMPERSAND", "BITWISEOR", "BITWISEXOR", 
		"ByteLengthLiteral", "COLON", "COMMA", "CharSetName", "DIVIDE", "DOLLAR", 
		"DOT", "DecimalLiteral", "Digit", "EQUAL", "EQUAL_NS", "Exponent", "GREATERTHAN", 
		"GREATERTHANOREQUALTO", "HexDigit", "Identifier", "KW_ADD", "KW_ALL", 
		"KW_AND", "KW_ARRAY", "KW_AS", "KW_ASC", "KW_BEFORE", "KW_BETWEEN", "KW_BIGINT", 
		"KW_BINARY", "KW_BOOLEAN", "KW_BOTH", "KW_BY", "KW_BYTEINT", "KW_CASE", 
		"KW_CASESPECIFIC", "KW_CAST", "KW_CHAR", "KW_CONCATENATE", "KW_CONTINUE", 
		"KW_CREATE", "KW_CROSS", "KW_CURSOR", "KW_DATABASE", "KW_DATE", "KW_DATETIME", 
		"KW_DAY", "KW_DECIMAL", "KW_DEFAULT", "KW_DEFINE", "KW_DELETE", "KW_DELIMITED", 
		"KW_DESC", "KW_DISABLE", "KW_DISTINCT", "KW_DISTRIBUTE", "KW_DOUBLE", 
		"KW_ELSE", "KW_ENABLE", "KW_END", "KW_ESCAPED", "KW_EXCLUSIVE", "KW_EXISTS", 
		"KW_EXPLAIN", "KW_EXPORT", "KW_FALSE", "KW_FETCH", "KW_FIELDS", "KW_FILEFORMAT", 
		"KW_FLOAT", "KW_FORMAT", "KW_FROM", "KW_FULL", "KW_FUNCTION", "KW_GRANT", 
		"KW_GROUP", "KW_HAVING", "KW_HOUR", "KW_IF", "KW_IMPORT", "KW_IN", "KW_INDEX", 
		"KW_INNER", "KW_INPUTFORMAT", "KW_INSERT", "KW_INT", "KW_INTEGER", "KW_INTERVAL", 
		"KW_INTO", "KW_INVOKE", "KW_IS", "KW_JOIN", "KW_LEFT", "KW_LIKE", "KW_LIMIT", 
		"KW_LOAD", "KW_LOCATION", "KW_LOCK", "KW_LOCKS", "KW_LONG", "KW_MACRO", 
		"KW_MAP", "KW_MAPJOIN", "KW_META", "KW_MINUTE", "KW_MONTH", "KW_NOT", 
		"KW_NULL", "KW_OF", "KW_ON", "KW_OPTION", "KW_OR", "KW_ORDER", "KW_OUT", 
		"KW_OUTER", "KW_OUTPUTFORMAT", "KW_OVER", "KW_OVERWRITE", "KW_PARTITION", 
		"KW_PATH", "KW_PERCENT", "KW_PERIOD", "KW_PROCEDURE", "KW_QUALIFY", "KW_RANGE", 
		"KW_READ", "KW_READONLY", "KW_REVOKE", "KW_RIGHT", "KW_ROWS", "KW_SECOND", 
		"KW_SELECT", "KW_SEMI", "KW_SET", "KW_SHARED", "KW_SMALLINT", "KW_SORT", 
		"KW_SOURCE", "KW_STRING", "KW_STRUCT", "KW_TABLE", "KW_TARGET", "KW_TEMPORARY", 
		"KW_TERMINATED", "KW_THEN", "KW_TIME", "KW_TIMESTAMP", "KW_TIME_ZONE", 
		"KW_TRIGGER", "KW_TRUE", "KW_UNDO", "KW_UNION", "KW_UNLOCK", "KW_UNSIGNED", 
		"KW_UPDATE", "KW_UPPERCASE", "KW_USE", "KW_USING", "KW_UTC", "KW_UTCTIMESTAMP", 
		"KW_VIEW", "KW_VOLATILE", "KW_WHEN", "KW_WHERE", "KW_WHILE", "KW_WITH", 
		"KW_YEAR", "LCURLY", "LESSTHAN", "LESSTHANOREQUALTO", "LPAREN", "LSQUARE", 
		"Letter", "MINUS", "ML_COMMENT", "MOD", "NOTEQUAL", "Number", "PLUS", 
		"QUESTION", "RCURLY", "RPAREN", "RSQUARE", "RegexComponent", "SEMICOLON", 
		"SL_COMMENT", "STAR", "StringLiteral", "TILDE", "WS", "DIV", "KE_SECOND", 
		"KW_BYTE", "KW_CURRENT", "KW_FOLLOWING", "KW_HOLD_DDLTIME", "KW_MULTISET", 
		"KW_PRECEDING", "KW_REGEXP", "KW_RLIKE", "KW_ROW", "KW_STREAMTABLE", "KW_TO", 
		"KW_UNBOUNDED", "KW_VARBYTE", "KW_VARCHAR", "TOK_ALIAS", "TOK_ALLCOLREF", 
		"TOK_BIGINT", "TOK_BINARY", "TOK_BOOLEAN", "TOK_BYTEINT", "TOK_CHAR", 
		"TOK_COLUMN", "TOK_CREATE_VIEW_STATEMENT", "TOK_CROSSJOIN", "TOK_DATA_ATTRIBUTE", 
		"TOK_DATA_TYPE", "TOK_DATE", "TOK_DATELITERAL", "TOK_DATETIME", "TOK_DECIMAL", 
		"TOK_DEFINE", "TOK_DEFINE_SOURCE_STATEMENT", "TOK_DEFINE_TABLE_STATEMENT", 
		"TOK_DEFINE_TARGET_STATEMENT", "TOK_DELETE", "TOK_DELETE_QUERY", "TOK_DELETE_STATEMENT", 
		"TOK_DESTINATION", "TOK_DIR", "TOK_DISTRIBUTEBY", "TOK_EXPLIST", "TOK_FALSE", 
		"TOK_FLOAT", "TOK_FROM", "TOK_FULLOUTERJOIN", "TOK_FUNCTION", "TOK_FUNCTIONDI", 
		"TOK_FUNCTIONSTAR", "TOK_GROUPBY", "TOK_HAVING", "TOK_HINT", "TOK_HINTARGLIST", 
		"TOK_HINTLIST", "TOK_HOLD_DDLTIME", "TOK_INSERT", "TOK_INSERT_QUERY", 
		"TOK_INSERT_STATEMENT", "TOK_INTEGER", "TOK_INVOKE", "TOK_INVOKE_STATEMENT", 
		"TOK_ISNOTNULL", "TOK_ISNULL", "TOK_JOIN", "TOK_LEFTOUTERJOIN", "TOK_LEFTSEMIJOIN", 
		"TOK_LOCAL_DIR", "TOK_MAPJOIN", "TOK_META", "TOK_NULL", "TOK_NULL_STATEMENT", 
		"TOK_OP_ADD", "TOK_OP_AND", "TOK_OP_BITAND", "TOK_OP_BITNOT", "TOK_OP_BITOR", 
		"TOK_OP_BITXOR", "TOK_OP_DIV", "TOK_OP_EQ", "TOK_OP_GE", "TOK_OP_GT", 
		"TOK_OP_LE", "TOK_OP_LIKE", "TOK_OP_LT", "TOK_OP_MOD", "TOK_OP_MUL", "TOK_OP_NE", 
		"TOK_OP_NOT", "TOK_OP_OR", "TOK_OP_SUB", "TOK_ORDERBY", "TOK_OVERWRITE_TARGET_STATEMENT", 
		"TOK_PARAMETER", "TOK_PARAMETERS", "TOK_PARAMETER_ARRAY", "TOK_PARAMETER_VALUE", 
		"TOK_PARTITIONINGSPEC", "TOK_PATH", "TOK_QUALIFY", "TOK_RIGHTOUTERJOIN", 
		"TOK_ROOT", "TOK_SELECT", "TOK_SELECTDI", "TOK_SELECT_QUERY", "TOK_SELEXPR", 
		"TOK_SETITEM", "TOK_SMALLINT", "TOK_STREAMTABLE", "TOK_STRINGLITERALSEQUENCE", 
		"TOK_SUBQUERY", "TOK_SUBQUERY_STATEMENT", "TOK_TABLE_OR_COL", "TOK_TABNAME", 
		"TOK_TABREF", "TOK_TABSORTCOLNAMEASC", "TOK_TABSORTCOLNAMEDESC", "TOK_TIME", 
		"TOK_TIMESTAMP", "TOK_TMP_FILE", "TOK_TRUE", "TOK_UNION", "TOK_UNIQUEJOIN", 
		"TOK_UPDATE", "TOK_UPDATE_QUERY", "TOK_UPDATE_STATEMENT", "TOK_VIEWNAME", 
		"TOK_WHERE", "TOK_WINDOWRANGE", "TOK_WINDOWSPEC", "TOK_WINDOWVALUES"
	};
	public static final int EOF=-1;
	public static final int AMPERSAND=4;
	public static final int BITWISEOR=5;
	public static final int BITWISEXOR=6;
	public static final int ByteLengthLiteral=7;
	public static final int COLON=8;
	public static final int COMMA=9;
	public static final int CharSetName=10;
	public static final int DIVIDE=11;
	public static final int DOLLAR=12;
	public static final int DOT=13;
	public static final int DecimalLiteral=14;
	public static final int Digit=15;
	public static final int EQUAL=16;
	public static final int EQUAL_NS=17;
	public static final int Exponent=18;
	public static final int GREATERTHAN=19;
	public static final int GREATERTHANOREQUALTO=20;
	public static final int HexDigit=21;
	public static final int Identifier=22;
	public static final int KW_ADD=23;
	public static final int KW_ALL=24;
	public static final int KW_AND=25;
	public static final int KW_ARRAY=26;
	public static final int KW_AS=27;
	public static final int KW_ASC=28;
	public static final int KW_BEFORE=29;
	public static final int KW_BETWEEN=30;
	public static final int KW_BIGINT=31;
	public static final int KW_BINARY=32;
	public static final int KW_BOOLEAN=33;
	public static final int KW_BOTH=34;
	public static final int KW_BY=35;
	public static final int KW_BYTEINT=36;
	public static final int KW_CASE=37;
	public static final int KW_CASESPECIFIC=38;
	public static final int KW_CAST=39;
	public static final int KW_CHAR=40;
	public static final int KW_CONCATENATE=41;
	public static final int KW_CONTINUE=42;
	public static final int KW_CREATE=43;
	public static final int KW_CROSS=44;
	public static final int KW_CURSOR=45;
	public static final int KW_DATABASE=46;
	public static final int KW_DATE=47;
	public static final int KW_DATETIME=48;
	public static final int KW_DAY=49;
	public static final int KW_DECIMAL=50;
	public static final int KW_DEFAULT=51;
	public static final int KW_DEFINE=52;
	public static final int KW_DELETE=53;
	public static final int KW_DELIMITED=54;
	public static final int KW_DESC=55;
	public static final int KW_DISABLE=56;
	public static final int KW_DISTINCT=57;
	public static final int KW_DISTRIBUTE=58;
	public static final int KW_DOUBLE=59;
	public static final int KW_ELSE=60;
	public static final int KW_ENABLE=61;
	public static final int KW_END=62;
	public static final int KW_ESCAPED=63;
	public static final int KW_EXCLUSIVE=64;
	public static final int KW_EXISTS=65;
	public static final int KW_EXPLAIN=66;
	public static final int KW_EXPORT=67;
	public static final int KW_FALSE=68;
	public static final int KW_FETCH=69;
	public static final int KW_FIELDS=70;
	public static final int KW_FILEFORMAT=71;
	public static final int KW_FLOAT=72;
	public static final int KW_FORMAT=73;
	public static final int KW_FROM=74;
	public static final int KW_FULL=75;
	public static final int KW_FUNCTION=76;
	public static final int KW_GRANT=77;
	public static final int KW_GROUP=78;
	public static final int KW_HAVING=79;
	public static final int KW_HOUR=80;
	public static final int KW_IF=81;
	public static final int KW_IMPORT=82;
	public static final int KW_IN=83;
	public static final int KW_INDEX=84;
	public static final int KW_INNER=85;
	public static final int KW_INPUTFORMAT=86;
	public static final int KW_INSERT=87;
	public static final int KW_INT=88;
	public static final int KW_INTEGER=89;
	public static final int KW_INTERVAL=90;
	public static final int KW_INTO=91;
	public static final int KW_INVOKE=92;
	public static final int KW_IS=93;
	public static final int KW_JOIN=94;
	public static final int KW_LEFT=95;
	public static final int KW_LIKE=96;
	public static final int KW_LIMIT=97;
	public static final int KW_LOAD=98;
	public static final int KW_LOCATION=99;
	public static final int KW_LOCK=100;
	public static final int KW_LOCKS=101;
	public static final int KW_LONG=102;
	public static final int KW_MACRO=103;
	public static final int KW_MAP=104;
	public static final int KW_MAPJOIN=105;
	public static final int KW_META=106;
	public static final int KW_MINUTE=107;
	public static final int KW_MONTH=108;
	public static final int KW_NOT=109;
	public static final int KW_NULL=110;
	public static final int KW_OF=111;
	public static final int KW_ON=112;
	public static final int KW_OPTION=113;
	public static final int KW_OR=114;
	public static final int KW_ORDER=115;
	public static final int KW_OUT=116;
	public static final int KW_OUTER=117;
	public static final int KW_OUTPUTFORMAT=118;
	public static final int KW_OVER=119;
	public static final int KW_OVERWRITE=120;
	public static final int KW_PARTITION=121;
	public static final int KW_PATH=122;
	public static final int KW_PERCENT=123;
	public static final int KW_PERIOD=124;
	public static final int KW_PROCEDURE=125;
	public static final int KW_QUALIFY=126;
	public static final int KW_RANGE=127;
	public static final int KW_READ=128;
	public static final int KW_READONLY=129;
	public static final int KW_REVOKE=130;
	public static final int KW_RIGHT=131;
	public static final int KW_ROWS=132;
	public static final int KW_SECOND=133;
	public static final int KW_SELECT=134;
	public static final int KW_SEMI=135;
	public static final int KW_SET=136;
	public static final int KW_SHARED=137;
	public static final int KW_SMALLINT=138;
	public static final int KW_SORT=139;
	public static final int KW_SOURCE=140;
	public static final int KW_STRING=141;
	public static final int KW_STRUCT=142;
	public static final int KW_TABLE=143;
	public static final int KW_TARGET=144;
	public static final int KW_TEMPORARY=145;
	public static final int KW_TERMINATED=146;
	public static final int KW_THEN=147;
	public static final int KW_TIME=148;
	public static final int KW_TIMESTAMP=149;
	public static final int KW_TIME_ZONE=150;
	public static final int KW_TRIGGER=151;
	public static final int KW_TRUE=152;
	public static final int KW_UNDO=153;
	public static final int KW_UNION=154;
	public static final int KW_UNLOCK=155;
	public static final int KW_UNSIGNED=156;
	public static final int KW_UPDATE=157;
	public static final int KW_UPPERCASE=158;
	public static final int KW_USE=159;
	public static final int KW_USING=160;
	public static final int KW_UTC=161;
	public static final int KW_UTCTIMESTAMP=162;
	public static final int KW_VIEW=163;
	public static final int KW_VOLATILE=164;
	public static final int KW_WHEN=165;
	public static final int KW_WHERE=166;
	public static final int KW_WHILE=167;
	public static final int KW_WITH=168;
	public static final int KW_YEAR=169;
	public static final int LCURLY=170;
	public static final int LESSTHAN=171;
	public static final int LESSTHANOREQUALTO=172;
	public static final int LPAREN=173;
	public static final int LSQUARE=174;
	public static final int Letter=175;
	public static final int MINUS=176;
	public static final int ML_COMMENT=177;
	public static final int MOD=178;
	public static final int NOTEQUAL=179;
	public static final int Number=180;
	public static final int PLUS=181;
	public static final int QUESTION=182;
	public static final int RCURLY=183;
	public static final int RPAREN=184;
	public static final int RSQUARE=185;
	public static final int RegexComponent=186;
	public static final int SEMICOLON=187;
	public static final int SL_COMMENT=188;
	public static final int STAR=189;
	public static final int StringLiteral=190;
	public static final int TILDE=191;
	public static final int WS=192;
	public static final int DIV=197;
	public static final int KE_SECOND=206;
	public static final int KW_BYTE=213;
	public static final int KW_CURRENT=218;
	public static final int KW_FOLLOWING=226;
	public static final int KW_HOLD_DDLTIME=227;
	public static final int KW_MULTISET=233;
	public static final int KW_PRECEDING=239;
	public static final int KW_REGEXP=241;
	public static final int KW_RLIKE=242;
	public static final int KW_ROW=243;
	public static final int KW_STREAMTABLE=246;
	public static final int KW_TO=250;
	public static final int KW_UNBOUNDED=252;
	public static final int KW_VARBYTE=253;
	public static final int KW_VARCHAR=254;
	public static final int TOK_ALIAS=270;
	public static final int TOK_ALLCOLREF=271;
	public static final int TOK_BIGINT=272;
	public static final int TOK_BINARY=273;
	public static final int TOK_BOOLEAN=274;
	public static final int TOK_BYTEINT=275;
	public static final int TOK_CHAR=276;
	public static final int TOK_COLUMN=277;
	public static final int TOK_CREATE_VIEW_STATEMENT=278;
	public static final int TOK_CROSSJOIN=279;
	public static final int TOK_DATA_ATTRIBUTE=280;
	public static final int TOK_DATA_TYPE=281;
	public static final int TOK_DATE=282;
	public static final int TOK_DATELITERAL=283;
	public static final int TOK_DATETIME=284;
	public static final int TOK_DECIMAL=285;
	public static final int TOK_DEFINE=286;
	public static final int TOK_DEFINE_SOURCE_STATEMENT=287;
	public static final int TOK_DEFINE_TABLE_STATEMENT=288;
	public static final int TOK_DEFINE_TARGET_STATEMENT=289;
	public static final int TOK_DELETE=290;
	public static final int TOK_DELETE_QUERY=291;
	public static final int TOK_DELETE_STATEMENT=292;
	public static final int TOK_DESTINATION=293;
	public static final int TOK_DIR=294;
	public static final int TOK_DISTRIBUTEBY=295;
	public static final int TOK_EXPLIST=296;
	public static final int TOK_FALSE=297;
	public static final int TOK_FLOAT=298;
	public static final int TOK_FROM=299;
	public static final int TOK_FULLOUTERJOIN=300;
	public static final int TOK_FUNCTION=301;
	public static final int TOK_FUNCTIONDI=302;
	public static final int TOK_FUNCTIONSTAR=303;
	public static final int TOK_GROUPBY=304;
	public static final int TOK_HAVING=305;
	public static final int TOK_HINT=306;
	public static final int TOK_HINTARGLIST=307;
	public static final int TOK_HINTLIST=308;
	public static final int TOK_HOLD_DDLTIME=309;
	public static final int TOK_INSERT=310;
	public static final int TOK_INSERT_QUERY=311;
	public static final int TOK_INSERT_STATEMENT=312;
	public static final int TOK_INTEGER=313;
	public static final int TOK_INVOKE=314;
	public static final int TOK_INVOKE_STATEMENT=315;
	public static final int TOK_ISNOTNULL=316;
	public static final int TOK_ISNULL=317;
	public static final int TOK_JOIN=318;
	public static final int TOK_LEFTOUTERJOIN=319;
	public static final int TOK_LEFTSEMIJOIN=320;
	public static final int TOK_LOCAL_DIR=321;
	public static final int TOK_MAPJOIN=322;
	public static final int TOK_META=323;
	public static final int TOK_NULL=324;
	public static final int TOK_NULL_STATEMENT=325;
	public static final int TOK_OP_ADD=326;
	public static final int TOK_OP_AND=327;
	public static final int TOK_OP_BITAND=328;
	public static final int TOK_OP_BITNOT=329;
	public static final int TOK_OP_BITOR=330;
	public static final int TOK_OP_BITXOR=331;
	public static final int TOK_OP_DIV=332;
	public static final int TOK_OP_EQ=333;
	public static final int TOK_OP_GE=334;
	public static final int TOK_OP_GT=335;
	public static final int TOK_OP_LE=336;
	public static final int TOK_OP_LIKE=337;
	public static final int TOK_OP_LT=338;
	public static final int TOK_OP_MOD=339;
	public static final int TOK_OP_MUL=340;
	public static final int TOK_OP_NE=341;
	public static final int TOK_OP_NOT=342;
	public static final int TOK_OP_OR=343;
	public static final int TOK_OP_SUB=344;
	public static final int TOK_ORDERBY=345;
	public static final int TOK_OVERWRITE_TARGET_STATEMENT=346;
	public static final int TOK_PARAMETER=347;
	public static final int TOK_PARAMETERS=348;
	public static final int TOK_PARAMETER_ARRAY=349;
	public static final int TOK_PARAMETER_VALUE=350;
	public static final int TOK_PARTITIONINGSPEC=351;
	public static final int TOK_PATH=352;
	public static final int TOK_QUALIFY=353;
	public static final int TOK_RIGHTOUTERJOIN=354;
	public static final int TOK_ROOT=355;
	public static final int TOK_SELECT=356;
	public static final int TOK_SELECTDI=357;
	public static final int TOK_SELECT_QUERY=358;
	public static final int TOK_SELEXPR=359;
	public static final int TOK_SETITEM=360;
	public static final int TOK_SMALLINT=361;
	public static final int TOK_STREAMTABLE=362;
	public static final int TOK_STRINGLITERALSEQUENCE=363;
	public static final int TOK_SUBQUERY=364;
	public static final int TOK_SUBQUERY_STATEMENT=365;
	public static final int TOK_TABLE_OR_COL=366;
	public static final int TOK_TABNAME=367;
	public static final int TOK_TABREF=368;
	public static final int TOK_TABSORTCOLNAMEASC=369;
	public static final int TOK_TABSORTCOLNAMEDESC=370;
	public static final int TOK_TIME=371;
	public static final int TOK_TIMESTAMP=372;
	public static final int TOK_TMP_FILE=373;
	public static final int TOK_TRUE=374;
	public static final int TOK_UNION=375;
	public static final int TOK_UNIQUEJOIN=376;
	public static final int TOK_UPDATE=377;
	public static final int TOK_UPDATE_QUERY=378;
	public static final int TOK_UPDATE_STATEMENT=379;
	public static final int TOK_VIEWNAME=380;
	public static final int TOK_WHERE=381;
	public static final int TOK_WINDOWRANGE=382;
	public static final int TOK_WINDOWSPEC=383;
	public static final int TOK_WINDOWVALUES=384;

	// delegates
	public SQLParser_Expr gExpr;
	public Parser[] getDelegates() {
		return new Parser[] {gExpr};
	}

	// delegators


	public SQLParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public SQLParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
		gExpr = new SQLParser_Expr(input, state, this);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
		gExpr.setTreeAdaptor(this.adaptor);
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return SQLParser.tokenNames; }
	@Override public String getGrammarFileName() { return "SQLParser.g"; }


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


	public static class all_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "all"
	// SQLParser.g:213:1: all : ( statements )? EOF !;
	public final SQLParser.all_return all() throws RecognitionException {
		SQLParser.all_return retval = new SQLParser.all_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token EOF2=null;
		ParserRuleReturnScope statements1 =null;

		CommonTree EOF2_tree=null;

		try {
			// SQLParser.g:214:2: ( ( statements )? EOF !)
			// SQLParser.g:215:2: ( statements )? EOF !
			{
			root_0 = (CommonTree)adaptor.nil();


			// SQLParser.g:215:2: ( statements )?
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0==KW_CREATE||(LA1_0 >= KW_DEFINE && LA1_0 <= KW_DELETE)||LA1_0==KW_INSERT||LA1_0==KW_INVOKE||LA1_0==KW_OVERWRITE||LA1_0==KW_UPDATE||LA1_0==SEMICOLON) ) {
				alt1=1;
			}
			switch (alt1) {
				case 1 :
					// SQLParser.g:215:2: statements
					{
					pushFollow(FOLLOW_statements_in_all421);
					statements1=statements();
					state._fsp--;

					adaptor.addChild(root_0, statements1.getTree());

					}
					break;

			}

			EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_all424); 
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "all"


	public static class statements_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "statements"
	// SQLParser.g:218:1: statements : statement ( SEMICOLON )? ( statement ( SEMICOLON )? )* -> ^( TOK_ROOT ( statement )* ) ;
	public final SQLParser.statements_return statements() throws RecognitionException {
		SQLParser.statements_return retval = new SQLParser.statements_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SEMICOLON4=null;
		Token SEMICOLON6=null;
		ParserRuleReturnScope statement3 =null;
		ParserRuleReturnScope statement5 =null;

		CommonTree SEMICOLON4_tree=null;
		CommonTree SEMICOLON6_tree=null;
		RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
		RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");

		try {
			// SQLParser.g:219:2: ( statement ( SEMICOLON )? ( statement ( SEMICOLON )? )* -> ^( TOK_ROOT ( statement )* ) )
			// SQLParser.g:220:2: statement ( SEMICOLON )? ( statement ( SEMICOLON )? )*
			{
			pushFollow(FOLLOW_statement_in_statements437);
			statement3=statement();
			state._fsp--;

			stream_statement.add(statement3.getTree());
			// SQLParser.g:220:12: ( SEMICOLON )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==SEMICOLON) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// SQLParser.g:220:12: SEMICOLON
					{
					SEMICOLON4=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_statements439);  
					stream_SEMICOLON.add(SEMICOLON4);

					}
					break;

			}

			// SQLParser.g:220:23: ( statement ( SEMICOLON )? )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==KW_CREATE||(LA4_0 >= KW_DEFINE && LA4_0 <= KW_DELETE)||LA4_0==KW_INSERT||LA4_0==KW_INVOKE||LA4_0==KW_OVERWRITE||LA4_0==KW_UPDATE||LA4_0==SEMICOLON) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// SQLParser.g:220:24: statement ( SEMICOLON )?
					{
					pushFollow(FOLLOW_statement_in_statements443);
					statement5=statement();
					state._fsp--;

					stream_statement.add(statement5.getTree());
					// SQLParser.g:220:34: ( SEMICOLON )?
					int alt3=2;
					int LA3_0 = input.LA(1);
					if ( (LA3_0==SEMICOLON) ) {
						alt3=1;
					}
					switch (alt3) {
						case 1 :
							// SQLParser.g:220:34: SEMICOLON
							{
							SEMICOLON6=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_statements445);  
							stream_SEMICOLON.add(SEMICOLON6);

							}
							break;

					}

					}
					break;

				default :
					break loop4;
				}
			}

			// AST REWRITE
			// elements: statement
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 221:3: -> ^( TOK_ROOT ( statement )* )
			{
				// SQLParser.g:221:5: ^( TOK_ROOT ( statement )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_ROOT, "TOK_ROOT"), root_1);
				// SQLParser.g:221:16: ( statement )*
				while ( stream_statement.hasNext() ) {
					adaptor.addChild(root_1, stream_statement.nextTree());
				}
				stream_statement.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "statements"


	public static class statement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "statement"
	// SQLParser.g:224:1: statement : ( defineSourceStatement | defineTargetStatement | overwriteTargetStatement | createViewStatement | insertStatement | updateStatement | deleteStatement | invokeStatement | nullStatement );
	public final SQLParser.statement_return statement() throws RecognitionException {
		SQLParser.statement_return retval = new SQLParser.statement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope defineSourceStatement7 =null;
		ParserRuleReturnScope defineTargetStatement8 =null;
		ParserRuleReturnScope overwriteTargetStatement9 =null;
		ParserRuleReturnScope createViewStatement10 =null;
		ParserRuleReturnScope insertStatement11 =null;
		ParserRuleReturnScope updateStatement12 =null;
		ParserRuleReturnScope deleteStatement13 =null;
		ParserRuleReturnScope invokeStatement14 =null;
		ParserRuleReturnScope nullStatement15 =null;


		try {
			// SQLParser.g:225:2: ( defineSourceStatement | defineTargetStatement | overwriteTargetStatement | createViewStatement | insertStatement | updateStatement | deleteStatement | invokeStatement | nullStatement )
			int alt5=9;
			switch ( input.LA(1) ) {
			case KW_DEFINE:
				{
				int LA5_1 = input.LA(2);
				if ( (LA5_1==KW_SOURCE) ) {
					alt5=1;
				}
				else if ( (LA5_1==KW_TARGET) ) {
					alt5=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 5, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case KW_OVERWRITE:
				{
				alt5=3;
				}
				break;
			case KW_CREATE:
				{
				alt5=4;
				}
				break;
			case KW_INSERT:
				{
				alt5=5;
				}
				break;
			case KW_UPDATE:
				{
				alt5=6;
				}
				break;
			case KW_DELETE:
				{
				alt5=7;
				}
				break;
			case KW_INVOKE:
				{
				alt5=8;
				}
				break;
			case SEMICOLON:
				{
				alt5=9;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}
			switch (alt5) {
				case 1 :
					// SQLParser.g:226:2: defineSourceStatement
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_defineSourceStatement_in_statement471);
					defineSourceStatement7=defineSourceStatement();
					state._fsp--;

					adaptor.addChild(root_0, defineSourceStatement7.getTree());

					}
					break;
				case 2 :
					// SQLParser.g:227:3: defineTargetStatement
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_defineTargetStatement_in_statement475);
					defineTargetStatement8=defineTargetStatement();
					state._fsp--;

					adaptor.addChild(root_0, defineTargetStatement8.getTree());

					}
					break;
				case 3 :
					// SQLParser.g:228:3: overwriteTargetStatement
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_overwriteTargetStatement_in_statement479);
					overwriteTargetStatement9=overwriteTargetStatement();
					state._fsp--;

					adaptor.addChild(root_0, overwriteTargetStatement9.getTree());

					}
					break;
				case 4 :
					// SQLParser.g:229:3: createViewStatement
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_createViewStatement_in_statement483);
					createViewStatement10=createViewStatement();
					state._fsp--;

					adaptor.addChild(root_0, createViewStatement10.getTree());

					}
					break;
				case 5 :
					// SQLParser.g:230:3: insertStatement
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insertStatement_in_statement487);
					insertStatement11=insertStatement();
					state._fsp--;

					adaptor.addChild(root_0, insertStatement11.getTree());

					}
					break;
				case 6 :
					// SQLParser.g:231:3: updateStatement
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_updateStatement_in_statement491);
					updateStatement12=updateStatement();
					state._fsp--;

					adaptor.addChild(root_0, updateStatement12.getTree());

					}
					break;
				case 7 :
					// SQLParser.g:232:3: deleteStatement
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_deleteStatement_in_statement495);
					deleteStatement13=deleteStatement();
					state._fsp--;

					adaptor.addChild(root_0, deleteStatement13.getTree());

					}
					break;
				case 8 :
					// SQLParser.g:233:3: invokeStatement
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_invokeStatement_in_statement499);
					invokeStatement14=invokeStatement();
					state._fsp--;

					adaptor.addChild(root_0, invokeStatement14.getTree());

					}
					break;
				case 9 :
					// SQLParser.g:234:3: nullStatement
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_nullStatement_in_statement503);
					nullStatement15=nullStatement();
					state._fsp--;

					adaptor.addChild(root_0, nullStatement15.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "statement"


	public static class nullStatement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "nullStatement"
	// SQLParser.g:237:1: nullStatement : SEMICOLON !;
	public final SQLParser.nullStatement_return nullStatement() throws RecognitionException {
		SQLParser.nullStatement_return retval = new SQLParser.nullStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SEMICOLON16=null;

		CommonTree SEMICOLON16_tree=null;

		try {
			// SQLParser.g:238:2: ( SEMICOLON !)
			// SQLParser.g:239:2: SEMICOLON !
			{
			root_0 = (CommonTree)adaptor.nil();


			SEMICOLON16=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_nullStatement516); 
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "nullStatement"


	public static class defineTableStatement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "defineTableStatement"
	// SQLParser.g:242:1: defineTableStatement : KW_DEFINE ( KW_SET | KW_MULTISET )? KW_TABLE tableName ( LSQUARE ( columnName dataType ( dataAttribute )* )+ RSQUARE )? -> ^( TOK_DEFINE_TABLE_STATEMENT tableName ( ^( TOK_COLUMN columnName dataType dataAttribute ) )* ) ;
	public final SQLParser.defineTableStatement_return defineTableStatement() throws RecognitionException {
		SQLParser.defineTableStatement_return retval = new SQLParser.defineTableStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_DEFINE17=null;
		Token KW_SET18=null;
		Token KW_MULTISET19=null;
		Token KW_TABLE20=null;
		Token LSQUARE22=null;
		Token RSQUARE26=null;
		ParserRuleReturnScope tableName21 =null;
		ParserRuleReturnScope columnName23 =null;
		ParserRuleReturnScope dataType24 =null;
		ParserRuleReturnScope dataAttribute25 =null;

		CommonTree KW_DEFINE17_tree=null;
		CommonTree KW_SET18_tree=null;
		CommonTree KW_MULTISET19_tree=null;
		CommonTree KW_TABLE20_tree=null;
		CommonTree LSQUARE22_tree=null;
		CommonTree RSQUARE26_tree=null;
		RewriteRuleTokenStream stream_KW_DEFINE=new RewriteRuleTokenStream(adaptor,"token KW_DEFINE");
		RewriteRuleTokenStream stream_KW_MULTISET=new RewriteRuleTokenStream(adaptor,"token KW_MULTISET");
		RewriteRuleTokenStream stream_LSQUARE=new RewriteRuleTokenStream(adaptor,"token LSQUARE");
		RewriteRuleTokenStream stream_RSQUARE=new RewriteRuleTokenStream(adaptor,"token RSQUARE");
		RewriteRuleTokenStream stream_KW_TABLE=new RewriteRuleTokenStream(adaptor,"token KW_TABLE");
		RewriteRuleTokenStream stream_KW_SET=new RewriteRuleTokenStream(adaptor,"token KW_SET");
		RewriteRuleSubtreeStream stream_dataType=new RewriteRuleSubtreeStream(adaptor,"rule dataType");
		RewriteRuleSubtreeStream stream_tableName=new RewriteRuleSubtreeStream(adaptor,"rule tableName");
		RewriteRuleSubtreeStream stream_dataAttribute=new RewriteRuleSubtreeStream(adaptor,"rule dataAttribute");
		RewriteRuleSubtreeStream stream_columnName=new RewriteRuleSubtreeStream(adaptor,"rule columnName");

		try {
			// SQLParser.g:243:2: ( KW_DEFINE ( KW_SET | KW_MULTISET )? KW_TABLE tableName ( LSQUARE ( columnName dataType ( dataAttribute )* )+ RSQUARE )? -> ^( TOK_DEFINE_TABLE_STATEMENT tableName ( ^( TOK_COLUMN columnName dataType dataAttribute ) )* ) )
			// SQLParser.g:244:2: KW_DEFINE ( KW_SET | KW_MULTISET )? KW_TABLE tableName ( LSQUARE ( columnName dataType ( dataAttribute )* )+ RSQUARE )?
			{
			KW_DEFINE17=(Token)match(input,KW_DEFINE,FOLLOW_KW_DEFINE_in_defineTableStatement530);  
			stream_KW_DEFINE.add(KW_DEFINE17);

			// SQLParser.g:244:12: ( KW_SET | KW_MULTISET )?
			int alt6=3;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==KW_SET) ) {
				alt6=1;
			}
			else if ( (LA6_0==KW_MULTISET) ) {
				alt6=2;
			}
			switch (alt6) {
				case 1 :
					// SQLParser.g:244:13: KW_SET
					{
					KW_SET18=(Token)match(input,KW_SET,FOLLOW_KW_SET_in_defineTableStatement533);  
					stream_KW_SET.add(KW_SET18);

					}
					break;
				case 2 :
					// SQLParser.g:244:20: KW_MULTISET
					{
					KW_MULTISET19=(Token)match(input,KW_MULTISET,FOLLOW_KW_MULTISET_in_defineTableStatement535);  
					stream_KW_MULTISET.add(KW_MULTISET19);

					}
					break;

			}

			KW_TABLE20=(Token)match(input,KW_TABLE,FOLLOW_KW_TABLE_in_defineTableStatement539);  
			stream_KW_TABLE.add(KW_TABLE20);

			pushFollow(FOLLOW_tableName_in_defineTableStatement541);
			tableName21=tableName();
			state._fsp--;

			stream_tableName.add(tableName21.getTree());
			// SQLParser.g:244:53: ( LSQUARE ( columnName dataType ( dataAttribute )* )+ RSQUARE )?
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==LSQUARE) ) {
				alt9=1;
			}
			switch (alt9) {
				case 1 :
					// SQLParser.g:244:54: LSQUARE ( columnName dataType ( dataAttribute )* )+ RSQUARE
					{
					LSQUARE22=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_defineTableStatement544);  
					stream_LSQUARE.add(LSQUARE22);

					// SQLParser.g:244:62: ( columnName dataType ( dataAttribute )* )+
					int cnt8=0;
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( (LA8_0==Identifier) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// SQLParser.g:244:63: columnName dataType ( dataAttribute )*
							{
							pushFollow(FOLLOW_columnName_in_defineTableStatement547);
							columnName23=columnName();
							state._fsp--;

							stream_columnName.add(columnName23.getTree());
							pushFollow(FOLLOW_dataType_in_defineTableStatement549);
							dataType24=dataType();
							state._fsp--;

							stream_dataType.add(dataType24.getTree());
							// SQLParser.g:244:83: ( dataAttribute )*
							loop7:
							while (true) {
								int alt7=2;
								int LA7_0 = input.LA(1);
								if ( (LA7_0==KW_CASESPECIFIC||LA7_0==KW_FORMAT||(LA7_0 >= KW_NOT && LA7_0 <= KW_NULL)||LA7_0==KW_UPPERCASE||LA7_0==KW_WITH) ) {
									alt7=1;
								}

								switch (alt7) {
								case 1 :
									// SQLParser.g:244:83: dataAttribute
									{
									pushFollow(FOLLOW_dataAttribute_in_defineTableStatement551);
									dataAttribute25=dataAttribute();
									state._fsp--;

									stream_dataAttribute.add(dataAttribute25.getTree());
									}
									break;

								default :
									break loop7;
								}
							}

							}
							break;

						default :
							if ( cnt8 >= 1 ) break loop8;
							EarlyExitException eee = new EarlyExitException(8, input);
							throw eee;
						}
						cnt8++;
					}

					RSQUARE26=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_defineTableStatement558);  
					stream_RSQUARE.add(RSQUARE26);

					}
					break;

			}

			// AST REWRITE
			// elements: dataType, dataAttribute, tableName, columnName
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 245:3: -> ^( TOK_DEFINE_TABLE_STATEMENT tableName ( ^( TOK_COLUMN columnName dataType dataAttribute ) )* )
			{
				// SQLParser.g:245:5: ^( TOK_DEFINE_TABLE_STATEMENT tableName ( ^( TOK_COLUMN columnName dataType dataAttribute ) )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DEFINE_TABLE_STATEMENT, "TOK_DEFINE_TABLE_STATEMENT"), root_1);
				adaptor.addChild(root_1, stream_tableName.nextTree());
				// SQLParser.g:245:44: ( ^( TOK_COLUMN columnName dataType dataAttribute ) )*
				while ( stream_dataType.hasNext()||stream_dataAttribute.hasNext()||stream_columnName.hasNext() ) {
					// SQLParser.g:245:44: ^( TOK_COLUMN columnName dataType dataAttribute )
					{
					CommonTree root_2 = (CommonTree)adaptor.nil();
					root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_COLUMN, "TOK_COLUMN"), root_2);
					adaptor.addChild(root_2, stream_columnName.nextTree());
					adaptor.addChild(root_2, stream_dataType.nextTree());
					adaptor.addChild(root_2, stream_dataAttribute.nextTree());
					adaptor.addChild(root_1, root_2);
					}

				}
				stream_dataType.reset();
				stream_dataAttribute.reset();
				stream_columnName.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "defineTableStatement"


	public static class columnName_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "columnName"
	// SQLParser.g:248:1: columnName : identifier ;
	public final SQLParser.columnName_return columnName() throws RecognitionException {
		SQLParser.columnName_return retval = new SQLParser.columnName_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope identifier27 =null;


		try {
			// SQLParser.g:249:2: ( identifier )
			// SQLParser.g:250:2: identifier
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_identifier_in_columnName594);
			identifier27=identifier();
			state._fsp--;

			adaptor.addChild(root_0, identifier27.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "columnName"


	public static class dataType_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "dataType"
	// SQLParser.g:253:1: dataType : ( KW_INTEGER -> ^( TOK_DATA_TYPE KW_INTEGER ) | KW_SMALLINT -> ^( TOK_DATA_TYPE KW_SMALLINT ) | KW_BIGINT -> ^( TOK_DATA_TYPE KW_BIGINT ) | KW_BYTEINT -> ^( TOK_DATA_TYPE KW_BYTEINT ) | KW_DATE -> ^( TOK_DATA_TYPE KW_DATE ) | KW_TIME ( LSQUARE Number RSQUARE )? ( KW_WITH KW_TIME_ZONE )? -> ^( TOK_DATA_TYPE KW_TIME ( Number )? ) | KW_TIMESTAMP ( LSQUARE Number RSQUARE )? ( KW_WITH KW_TIME_ZONE )? -> ^( TOK_DATA_TYPE KW_TIMESTAMP ( Number )? ) | KW_INTERVAL KW_YEAR ( LSQUARE Number RSQUARE )? ( KW_TO KW_MONTH ) -> ^( TOK_DATA_TYPE KW_INTERVAL KW_YEAR ( Number )? ) | KW_INTERVAL KW_MONTH ( LSQUARE Number RSQUARE )? -> ^( TOK_DATA_TYPE KW_INTERVAL KW_MONTH ( Number )? ) | KW_INTERVAL KW_DAY ( LSQUARE Number RSQUARE )? ( KW_TO KW_HOUR | KW_MINUTE | KW_SECOND )? -> ^( TOK_DATA_TYPE KW_INTERVAL KW_DAY ( Number )? ) | KW_INTERVAL KW_HOUR ( LSQUARE Number RSQUARE )? ( KW_TO KW_MINUTE | KW_SECOND )? -> ^( TOK_DATA_TYPE KW_INTERVAL KW_HOUR ( Number )? ) | KW_INTERVAL KW_MINUTE ( LSQUARE Number RSQUARE )? ( KW_TO KE_SECOND )? -> ^( TOK_DATA_TYPE KW_INTERVAL KW_MINUTE ( Number )? ) | KW_INTERVAL KW_SECOND ( LSQUARE Number ( COMMA Number )? RSQUARE )? -> ^( TOK_DATA_TYPE KW_INTERVAL KW_SECOND ( Number )? ) | KW_FLOAT -> ^( TOK_DATA_TYPE KW_FLOAT ) | KW_DECIMAL ( LSQUARE Number ( COMMA Number )? RSQUARE )? -> ^( TOK_DATA_TYPE KW_DECIMAL ( Number )* ) | KW_CHAR ( LSQUARE Number RSQUARE )? -> ^( TOK_DATA_TYPE KW_CHAR ( Number )? ) | KW_VARCHAR LSQUARE Number RSQUARE -> ^( TOK_DATA_TYPE KW_VARCHAR Number ) | KW_BYTE ( LSQUARE Number RSQUARE )? -> ^( TOK_DATA_TYPE KW_BYTE ( Number )* ) | KW_VARBYTE LSQUARE Number RSQUARE -> ^( TOK_DATA_TYPE KW_VARBYTE Number ) | KW_PERIOD ( KW_TIME )? -> ^( TOK_DATA_TYPE KW_PERIOD KW_TIME ) | KW_PERIOD KW_DATE -> ^( TOK_DATA_TYPE KW_PERIOD KW_DATE ) | KW_PERIOD KW_TIMESTAMP ( LSQUARE Number RSQUARE )? ( KW_WITH KW_TIME_ZONE )? -> ^( TOK_DATA_TYPE KW_PERIOD KW_TIMESTAMP ( Number )? ) );
	public final SQLParser.dataType_return dataType() throws RecognitionException {
		SQLParser.dataType_return retval = new SQLParser.dataType_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_INTEGER28=null;
		Token KW_SMALLINT29=null;
		Token KW_BIGINT30=null;
		Token KW_BYTEINT31=null;
		Token KW_DATE32=null;
		Token KW_TIME33=null;
		Token LSQUARE34=null;
		Token Number35=null;
		Token RSQUARE36=null;
		Token KW_WITH37=null;
		Token KW_TIME_ZONE38=null;
		Token KW_TIMESTAMP39=null;
		Token LSQUARE40=null;
		Token Number41=null;
		Token RSQUARE42=null;
		Token KW_WITH43=null;
		Token KW_TIME_ZONE44=null;
		Token KW_INTERVAL45=null;
		Token KW_YEAR46=null;
		Token LSQUARE47=null;
		Token Number48=null;
		Token RSQUARE49=null;
		Token KW_TO50=null;
		Token KW_MONTH51=null;
		Token KW_INTERVAL52=null;
		Token KW_MONTH53=null;
		Token LSQUARE54=null;
		Token Number55=null;
		Token RSQUARE56=null;
		Token KW_INTERVAL57=null;
		Token KW_DAY58=null;
		Token LSQUARE59=null;
		Token Number60=null;
		Token RSQUARE61=null;
		Token KW_TO62=null;
		Token KW_HOUR63=null;
		Token KW_MINUTE64=null;
		Token KW_SECOND65=null;
		Token KW_INTERVAL66=null;
		Token KW_HOUR67=null;
		Token LSQUARE68=null;
		Token Number69=null;
		Token RSQUARE70=null;
		Token KW_TO71=null;
		Token KW_MINUTE72=null;
		Token KW_SECOND73=null;
		Token KW_INTERVAL74=null;
		Token KW_MINUTE75=null;
		Token LSQUARE76=null;
		Token Number77=null;
		Token RSQUARE78=null;
		Token KW_TO79=null;
		Token KE_SECOND80=null;
		Token KW_INTERVAL81=null;
		Token KW_SECOND82=null;
		Token LSQUARE83=null;
		Token Number84=null;
		Token COMMA85=null;
		Token Number86=null;
		Token RSQUARE87=null;
		Token KW_FLOAT88=null;
		Token KW_DECIMAL89=null;
		Token LSQUARE90=null;
		Token Number91=null;
		Token COMMA92=null;
		Token Number93=null;
		Token RSQUARE94=null;
		Token KW_CHAR95=null;
		Token LSQUARE96=null;
		Token Number97=null;
		Token RSQUARE98=null;
		Token KW_VARCHAR99=null;
		Token LSQUARE100=null;
		Token Number101=null;
		Token RSQUARE102=null;
		Token KW_BYTE103=null;
		Token LSQUARE104=null;
		Token Number105=null;
		Token RSQUARE106=null;
		Token KW_VARBYTE107=null;
		Token LSQUARE108=null;
		Token Number109=null;
		Token RSQUARE110=null;
		Token KW_PERIOD111=null;
		Token KW_TIME112=null;
		Token KW_PERIOD113=null;
		Token KW_DATE114=null;
		Token KW_PERIOD115=null;
		Token KW_TIMESTAMP116=null;
		Token LSQUARE117=null;
		Token Number118=null;
		Token RSQUARE119=null;
		Token KW_WITH120=null;
		Token KW_TIME_ZONE121=null;

		CommonTree KW_INTEGER28_tree=null;
		CommonTree KW_SMALLINT29_tree=null;
		CommonTree KW_BIGINT30_tree=null;
		CommonTree KW_BYTEINT31_tree=null;
		CommonTree KW_DATE32_tree=null;
		CommonTree KW_TIME33_tree=null;
		CommonTree LSQUARE34_tree=null;
		CommonTree Number35_tree=null;
		CommonTree RSQUARE36_tree=null;
		CommonTree KW_WITH37_tree=null;
		CommonTree KW_TIME_ZONE38_tree=null;
		CommonTree KW_TIMESTAMP39_tree=null;
		CommonTree LSQUARE40_tree=null;
		CommonTree Number41_tree=null;
		CommonTree RSQUARE42_tree=null;
		CommonTree KW_WITH43_tree=null;
		CommonTree KW_TIME_ZONE44_tree=null;
		CommonTree KW_INTERVAL45_tree=null;
		CommonTree KW_YEAR46_tree=null;
		CommonTree LSQUARE47_tree=null;
		CommonTree Number48_tree=null;
		CommonTree RSQUARE49_tree=null;
		CommonTree KW_TO50_tree=null;
		CommonTree KW_MONTH51_tree=null;
		CommonTree KW_INTERVAL52_tree=null;
		CommonTree KW_MONTH53_tree=null;
		CommonTree LSQUARE54_tree=null;
		CommonTree Number55_tree=null;
		CommonTree RSQUARE56_tree=null;
		CommonTree KW_INTERVAL57_tree=null;
		CommonTree KW_DAY58_tree=null;
		CommonTree LSQUARE59_tree=null;
		CommonTree Number60_tree=null;
		CommonTree RSQUARE61_tree=null;
		CommonTree KW_TO62_tree=null;
		CommonTree KW_HOUR63_tree=null;
		CommonTree KW_MINUTE64_tree=null;
		CommonTree KW_SECOND65_tree=null;
		CommonTree KW_INTERVAL66_tree=null;
		CommonTree KW_HOUR67_tree=null;
		CommonTree LSQUARE68_tree=null;
		CommonTree Number69_tree=null;
		CommonTree RSQUARE70_tree=null;
		CommonTree KW_TO71_tree=null;
		CommonTree KW_MINUTE72_tree=null;
		CommonTree KW_SECOND73_tree=null;
		CommonTree KW_INTERVAL74_tree=null;
		CommonTree KW_MINUTE75_tree=null;
		CommonTree LSQUARE76_tree=null;
		CommonTree Number77_tree=null;
		CommonTree RSQUARE78_tree=null;
		CommonTree KW_TO79_tree=null;
		CommonTree KE_SECOND80_tree=null;
		CommonTree KW_INTERVAL81_tree=null;
		CommonTree KW_SECOND82_tree=null;
		CommonTree LSQUARE83_tree=null;
		CommonTree Number84_tree=null;
		CommonTree COMMA85_tree=null;
		CommonTree Number86_tree=null;
		CommonTree RSQUARE87_tree=null;
		CommonTree KW_FLOAT88_tree=null;
		CommonTree KW_DECIMAL89_tree=null;
		CommonTree LSQUARE90_tree=null;
		CommonTree Number91_tree=null;
		CommonTree COMMA92_tree=null;
		CommonTree Number93_tree=null;
		CommonTree RSQUARE94_tree=null;
		CommonTree KW_CHAR95_tree=null;
		CommonTree LSQUARE96_tree=null;
		CommonTree Number97_tree=null;
		CommonTree RSQUARE98_tree=null;
		CommonTree KW_VARCHAR99_tree=null;
		CommonTree LSQUARE100_tree=null;
		CommonTree Number101_tree=null;
		CommonTree RSQUARE102_tree=null;
		CommonTree KW_BYTE103_tree=null;
		CommonTree LSQUARE104_tree=null;
		CommonTree Number105_tree=null;
		CommonTree RSQUARE106_tree=null;
		CommonTree KW_VARBYTE107_tree=null;
		CommonTree LSQUARE108_tree=null;
		CommonTree Number109_tree=null;
		CommonTree RSQUARE110_tree=null;
		CommonTree KW_PERIOD111_tree=null;
		CommonTree KW_TIME112_tree=null;
		CommonTree KW_PERIOD113_tree=null;
		CommonTree KW_DATE114_tree=null;
		CommonTree KW_PERIOD115_tree=null;
		CommonTree KW_TIMESTAMP116_tree=null;
		CommonTree LSQUARE117_tree=null;
		CommonTree Number118_tree=null;
		CommonTree RSQUARE119_tree=null;
		CommonTree KW_WITH120_tree=null;
		CommonTree KW_TIME_ZONE121_tree=null;
		RewriteRuleTokenStream stream_KW_MONTH=new RewriteRuleTokenStream(adaptor,"token KW_MONTH");
		RewriteRuleTokenStream stream_KW_VARCHAR=new RewriteRuleTokenStream(adaptor,"token KW_VARCHAR");
		RewriteRuleTokenStream stream_KW_TIMESTAMP=new RewriteRuleTokenStream(adaptor,"token KW_TIMESTAMP");
		RewriteRuleTokenStream stream_LSQUARE=new RewriteRuleTokenStream(adaptor,"token LSQUARE");
		RewriteRuleTokenStream stream_KW_BYTE=new RewriteRuleTokenStream(adaptor,"token KW_BYTE");
		RewriteRuleTokenStream stream_KW_CHAR=new RewriteRuleTokenStream(adaptor,"token KW_CHAR");
		RewriteRuleTokenStream stream_KW_SMALLINT=new RewriteRuleTokenStream(adaptor,"token KW_SMALLINT");
		RewriteRuleTokenStream stream_KW_DECIMAL=new RewriteRuleTokenStream(adaptor,"token KW_DECIMAL");
		RewriteRuleTokenStream stream_KW_BYTEINT=new RewriteRuleTokenStream(adaptor,"token KW_BYTEINT");
		RewriteRuleTokenStream stream_KW_SECOND=new RewriteRuleTokenStream(adaptor,"token KW_SECOND");
		RewriteRuleTokenStream stream_KW_FLOAT=new RewriteRuleTokenStream(adaptor,"token KW_FLOAT");
		RewriteRuleTokenStream stream_RSQUARE=new RewriteRuleTokenStream(adaptor,"token RSQUARE");
		RewriteRuleTokenStream stream_KW_PERIOD=new RewriteRuleTokenStream(adaptor,"token KW_PERIOD");
		RewriteRuleTokenStream stream_KW_WITH=new RewriteRuleTokenStream(adaptor,"token KW_WITH");
		RewriteRuleTokenStream stream_KW_YEAR=new RewriteRuleTokenStream(adaptor,"token KW_YEAR");
		RewriteRuleTokenStream stream_KW_BIGINT=new RewriteRuleTokenStream(adaptor,"token KW_BIGINT");
		RewriteRuleTokenStream stream_KW_DATE=new RewriteRuleTokenStream(adaptor,"token KW_DATE");
		RewriteRuleTokenStream stream_KW_HOUR=new RewriteRuleTokenStream(adaptor,"token KW_HOUR");
		RewriteRuleTokenStream stream_KW_TIME_ZONE=new RewriteRuleTokenStream(adaptor,"token KW_TIME_ZONE");
		RewriteRuleTokenStream stream_KW_TIME=new RewriteRuleTokenStream(adaptor,"token KW_TIME");
		RewriteRuleTokenStream stream_Number=new RewriteRuleTokenStream(adaptor,"token Number");
		RewriteRuleTokenStream stream_KW_VARBYTE=new RewriteRuleTokenStream(adaptor,"token KW_VARBYTE");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_KW_TO=new RewriteRuleTokenStream(adaptor,"token KW_TO");
		RewriteRuleTokenStream stream_KW_INTEGER=new RewriteRuleTokenStream(adaptor,"token KW_INTEGER");
		RewriteRuleTokenStream stream_KW_DAY=new RewriteRuleTokenStream(adaptor,"token KW_DAY");
		RewriteRuleTokenStream stream_KW_MINUTE=new RewriteRuleTokenStream(adaptor,"token KW_MINUTE");
		RewriteRuleTokenStream stream_KW_INTERVAL=new RewriteRuleTokenStream(adaptor,"token KW_INTERVAL");
		RewriteRuleTokenStream stream_KE_SECOND=new RewriteRuleTokenStream(adaptor,"token KE_SECOND");

		try {
			// SQLParser.g:254:2: ( KW_INTEGER -> ^( TOK_DATA_TYPE KW_INTEGER ) | KW_SMALLINT -> ^( TOK_DATA_TYPE KW_SMALLINT ) | KW_BIGINT -> ^( TOK_DATA_TYPE KW_BIGINT ) | KW_BYTEINT -> ^( TOK_DATA_TYPE KW_BYTEINT ) | KW_DATE -> ^( TOK_DATA_TYPE KW_DATE ) | KW_TIME ( LSQUARE Number RSQUARE )? ( KW_WITH KW_TIME_ZONE )? -> ^( TOK_DATA_TYPE KW_TIME ( Number )? ) | KW_TIMESTAMP ( LSQUARE Number RSQUARE )? ( KW_WITH KW_TIME_ZONE )? -> ^( TOK_DATA_TYPE KW_TIMESTAMP ( Number )? ) | KW_INTERVAL KW_YEAR ( LSQUARE Number RSQUARE )? ( KW_TO KW_MONTH ) -> ^( TOK_DATA_TYPE KW_INTERVAL KW_YEAR ( Number )? ) | KW_INTERVAL KW_MONTH ( LSQUARE Number RSQUARE )? -> ^( TOK_DATA_TYPE KW_INTERVAL KW_MONTH ( Number )? ) | KW_INTERVAL KW_DAY ( LSQUARE Number RSQUARE )? ( KW_TO KW_HOUR | KW_MINUTE | KW_SECOND )? -> ^( TOK_DATA_TYPE KW_INTERVAL KW_DAY ( Number )? ) | KW_INTERVAL KW_HOUR ( LSQUARE Number RSQUARE )? ( KW_TO KW_MINUTE | KW_SECOND )? -> ^( TOK_DATA_TYPE KW_INTERVAL KW_HOUR ( Number )? ) | KW_INTERVAL KW_MINUTE ( LSQUARE Number RSQUARE )? ( KW_TO KE_SECOND )? -> ^( TOK_DATA_TYPE KW_INTERVAL KW_MINUTE ( Number )? ) | KW_INTERVAL KW_SECOND ( LSQUARE Number ( COMMA Number )? RSQUARE )? -> ^( TOK_DATA_TYPE KW_INTERVAL KW_SECOND ( Number )? ) | KW_FLOAT -> ^( TOK_DATA_TYPE KW_FLOAT ) | KW_DECIMAL ( LSQUARE Number ( COMMA Number )? RSQUARE )? -> ^( TOK_DATA_TYPE KW_DECIMAL ( Number )* ) | KW_CHAR ( LSQUARE Number RSQUARE )? -> ^( TOK_DATA_TYPE KW_CHAR ( Number )? ) | KW_VARCHAR LSQUARE Number RSQUARE -> ^( TOK_DATA_TYPE KW_VARCHAR Number ) | KW_BYTE ( LSQUARE Number RSQUARE )? -> ^( TOK_DATA_TYPE KW_BYTE ( Number )* ) | KW_VARBYTE LSQUARE Number RSQUARE -> ^( TOK_DATA_TYPE KW_VARBYTE Number ) | KW_PERIOD ( KW_TIME )? -> ^( TOK_DATA_TYPE KW_PERIOD KW_TIME ) | KW_PERIOD KW_DATE -> ^( TOK_DATA_TYPE KW_PERIOD KW_DATE ) | KW_PERIOD KW_TIMESTAMP ( LSQUARE Number RSQUARE )? ( KW_WITH KW_TIME_ZONE )? -> ^( TOK_DATA_TYPE KW_PERIOD KW_TIMESTAMP ( Number )? ) )
			int alt31=22;
			switch ( input.LA(1) ) {
			case KW_INTEGER:
				{
				alt31=1;
				}
				break;
			case KW_SMALLINT:
				{
				alt31=2;
				}
				break;
			case KW_BIGINT:
				{
				alt31=3;
				}
				break;
			case KW_BYTEINT:
				{
				alt31=4;
				}
				break;
			case KW_DATE:
				{
				alt31=5;
				}
				break;
			case KW_TIME:
				{
				alt31=6;
				}
				break;
			case KW_TIMESTAMP:
				{
				alt31=7;
				}
				break;
			case KW_INTERVAL:
				{
				switch ( input.LA(2) ) {
				case KW_YEAR:
					{
					alt31=8;
					}
					break;
				case KW_MONTH:
					{
					alt31=9;
					}
					break;
				case KW_DAY:
					{
					alt31=10;
					}
					break;
				case KW_HOUR:
					{
					alt31=11;
					}
					break;
				case KW_MINUTE:
					{
					alt31=12;
					}
					break;
				case KW_SECOND:
					{
					alt31=13;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 31, 8, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case KW_FLOAT:
				{
				alt31=14;
				}
				break;
			case KW_DECIMAL:
				{
				alt31=15;
				}
				break;
			case KW_CHAR:
				{
				alt31=16;
				}
				break;
			case KW_VARCHAR:
				{
				alt31=17;
				}
				break;
			case KW_BYTE:
				{
				alt31=18;
				}
				break;
			case KW_VARBYTE:
				{
				alt31=19;
				}
				break;
			case KW_PERIOD:
				{
				switch ( input.LA(2) ) {
				case KW_DATE:
					{
					alt31=21;
					}
					break;
				case KW_TIMESTAMP:
					{
					alt31=22;
					}
					break;
				case Identifier:
				case KW_CASESPECIFIC:
				case KW_FORMAT:
				case KW_NOT:
				case KW_NULL:
				case KW_TIME:
				case KW_UPPERCASE:
				case KW_WITH:
				case RSQUARE:
					{
					alt31=20;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 31, 15, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}
			switch (alt31) {
				case 1 :
					// SQLParser.g:255:2: KW_INTEGER
					{
					KW_INTEGER28=(Token)match(input,KW_INTEGER,FOLLOW_KW_INTEGER_in_dataType607);  
					stream_KW_INTEGER.add(KW_INTEGER28);

					// AST REWRITE
					// elements: KW_INTEGER
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 256:3: -> ^( TOK_DATA_TYPE KW_INTEGER )
					{
						// SQLParser.g:256:5: ^( TOK_DATA_TYPE KW_INTEGER )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_INTEGER.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// SQLParser.g:257:3: KW_SMALLINT
					{
					KW_SMALLINT29=(Token)match(input,KW_SMALLINT,FOLLOW_KW_SMALLINT_in_dataType620);  
					stream_KW_SMALLINT.add(KW_SMALLINT29);

					// AST REWRITE
					// elements: KW_SMALLINT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 258:3: -> ^( TOK_DATA_TYPE KW_SMALLINT )
					{
						// SQLParser.g:258:5: ^( TOK_DATA_TYPE KW_SMALLINT )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_SMALLINT.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// SQLParser.g:259:3: KW_BIGINT
					{
					KW_BIGINT30=(Token)match(input,KW_BIGINT,FOLLOW_KW_BIGINT_in_dataType633);  
					stream_KW_BIGINT.add(KW_BIGINT30);

					// AST REWRITE
					// elements: KW_BIGINT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 260:3: -> ^( TOK_DATA_TYPE KW_BIGINT )
					{
						// SQLParser.g:260:5: ^( TOK_DATA_TYPE KW_BIGINT )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_BIGINT.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 4 :
					// SQLParser.g:261:3: KW_BYTEINT
					{
					KW_BYTEINT31=(Token)match(input,KW_BYTEINT,FOLLOW_KW_BYTEINT_in_dataType646);  
					stream_KW_BYTEINT.add(KW_BYTEINT31);

					// AST REWRITE
					// elements: KW_BYTEINT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 262:3: -> ^( TOK_DATA_TYPE KW_BYTEINT )
					{
						// SQLParser.g:262:5: ^( TOK_DATA_TYPE KW_BYTEINT )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_BYTEINT.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 5 :
					// SQLParser.g:263:3: KW_DATE
					{
					KW_DATE32=(Token)match(input,KW_DATE,FOLLOW_KW_DATE_in_dataType659);  
					stream_KW_DATE.add(KW_DATE32);

					// AST REWRITE
					// elements: KW_DATE
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 264:3: -> ^( TOK_DATA_TYPE KW_DATE )
					{
						// SQLParser.g:264:5: ^( TOK_DATA_TYPE KW_DATE )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_DATE.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 6 :
					// SQLParser.g:265:3: KW_TIME ( LSQUARE Number RSQUARE )? ( KW_WITH KW_TIME_ZONE )?
					{
					KW_TIME33=(Token)match(input,KW_TIME,FOLLOW_KW_TIME_in_dataType672);  
					stream_KW_TIME.add(KW_TIME33);

					// SQLParser.g:265:12: ( LSQUARE Number RSQUARE )?
					int alt10=2;
					int LA10_0 = input.LA(1);
					if ( (LA10_0==LSQUARE) ) {
						alt10=1;
					}
					switch (alt10) {
						case 1 :
							// SQLParser.g:265:14: LSQUARE Number RSQUARE
							{
							LSQUARE34=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_dataType677);  
							stream_LSQUARE.add(LSQUARE34);

							Number35=(Token)match(input,Number,FOLLOW_Number_in_dataType679);  
							stream_Number.add(Number35);

							RSQUARE36=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_dataType681);  
							stream_RSQUARE.add(RSQUARE36);

							}
							break;

					}

					// SQLParser.g:265:39: ( KW_WITH KW_TIME_ZONE )?
					int alt11=2;
					int LA11_0 = input.LA(1);
					if ( (LA11_0==KW_WITH) ) {
						int LA11_1 = input.LA(2);
						if ( (LA11_1==KW_TIME_ZONE) ) {
							alt11=1;
						}
					}
					switch (alt11) {
						case 1 :
							// SQLParser.g:265:40: KW_WITH KW_TIME_ZONE
							{
							KW_WITH37=(Token)match(input,KW_WITH,FOLLOW_KW_WITH_in_dataType686);  
							stream_KW_WITH.add(KW_WITH37);

							KW_TIME_ZONE38=(Token)match(input,KW_TIME_ZONE,FOLLOW_KW_TIME_ZONE_in_dataType688);  
							stream_KW_TIME_ZONE.add(KW_TIME_ZONE38);

							}
							break;

					}

					// AST REWRITE
					// elements: KW_TIME, Number
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 266:3: -> ^( TOK_DATA_TYPE KW_TIME ( Number )? )
					{
						// SQLParser.g:266:5: ^( TOK_DATA_TYPE KW_TIME ( Number )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_TIME.nextNode());
						// SQLParser.g:266:29: ( Number )?
						if ( stream_Number.hasNext() ) {
							adaptor.addChild(root_1, stream_Number.nextNode());
						}
						stream_Number.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 7 :
					// SQLParser.g:267:3: KW_TIMESTAMP ( LSQUARE Number RSQUARE )? ( KW_WITH KW_TIME_ZONE )?
					{
					KW_TIMESTAMP39=(Token)match(input,KW_TIMESTAMP,FOLLOW_KW_TIMESTAMP_in_dataType706);  
					stream_KW_TIMESTAMP.add(KW_TIMESTAMP39);

					// SQLParser.g:267:17: ( LSQUARE Number RSQUARE )?
					int alt12=2;
					int LA12_0 = input.LA(1);
					if ( (LA12_0==LSQUARE) ) {
						alt12=1;
					}
					switch (alt12) {
						case 1 :
							// SQLParser.g:267:19: LSQUARE Number RSQUARE
							{
							LSQUARE40=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_dataType711);  
							stream_LSQUARE.add(LSQUARE40);

							Number41=(Token)match(input,Number,FOLLOW_Number_in_dataType713);  
							stream_Number.add(Number41);

							RSQUARE42=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_dataType715);  
							stream_RSQUARE.add(RSQUARE42);

							}
							break;

					}

					// SQLParser.g:267:44: ( KW_WITH KW_TIME_ZONE )?
					int alt13=2;
					int LA13_0 = input.LA(1);
					if ( (LA13_0==KW_WITH) ) {
						int LA13_1 = input.LA(2);
						if ( (LA13_1==KW_TIME_ZONE) ) {
							alt13=1;
						}
					}
					switch (alt13) {
						case 1 :
							// SQLParser.g:267:45: KW_WITH KW_TIME_ZONE
							{
							KW_WITH43=(Token)match(input,KW_WITH,FOLLOW_KW_WITH_in_dataType720);  
							stream_KW_WITH.add(KW_WITH43);

							KW_TIME_ZONE44=(Token)match(input,KW_TIME_ZONE,FOLLOW_KW_TIME_ZONE_in_dataType722);  
							stream_KW_TIME_ZONE.add(KW_TIME_ZONE44);

							}
							break;

					}

					// AST REWRITE
					// elements: KW_TIMESTAMP, Number
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 268:3: -> ^( TOK_DATA_TYPE KW_TIMESTAMP ( Number )? )
					{
						// SQLParser.g:268:5: ^( TOK_DATA_TYPE KW_TIMESTAMP ( Number )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_TIMESTAMP.nextNode());
						// SQLParser.g:268:34: ( Number )?
						if ( stream_Number.hasNext() ) {
							adaptor.addChild(root_1, stream_Number.nextNode());
						}
						stream_Number.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 8 :
					// SQLParser.g:269:3: KW_INTERVAL KW_YEAR ( LSQUARE Number RSQUARE )? ( KW_TO KW_MONTH )
					{
					KW_INTERVAL45=(Token)match(input,KW_INTERVAL,FOLLOW_KW_INTERVAL_in_dataType740);  
					stream_KW_INTERVAL.add(KW_INTERVAL45);

					KW_YEAR46=(Token)match(input,KW_YEAR,FOLLOW_KW_YEAR_in_dataType742);  
					stream_KW_YEAR.add(KW_YEAR46);

					// SQLParser.g:269:23: ( LSQUARE Number RSQUARE )?
					int alt14=2;
					int LA14_0 = input.LA(1);
					if ( (LA14_0==LSQUARE) ) {
						alt14=1;
					}
					switch (alt14) {
						case 1 :
							// SQLParser.g:269:24: LSQUARE Number RSQUARE
							{
							LSQUARE47=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_dataType745);  
							stream_LSQUARE.add(LSQUARE47);

							Number48=(Token)match(input,Number,FOLLOW_Number_in_dataType747);  
							stream_Number.add(Number48);

							RSQUARE49=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_dataType749);  
							stream_RSQUARE.add(RSQUARE49);

							}
							break;

					}

					// SQLParser.g:269:49: ( KW_TO KW_MONTH )
					// SQLParser.g:269:50: KW_TO KW_MONTH
					{
					KW_TO50=(Token)match(input,KW_TO,FOLLOW_KW_TO_in_dataType754);  
					stream_KW_TO.add(KW_TO50);

					KW_MONTH51=(Token)match(input,KW_MONTH,FOLLOW_KW_MONTH_in_dataType756);  
					stream_KW_MONTH.add(KW_MONTH51);

					}

					// AST REWRITE
					// elements: Number, KW_INTERVAL, KW_YEAR
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 270:3: -> ^( TOK_DATA_TYPE KW_INTERVAL KW_YEAR ( Number )? )
					{
						// SQLParser.g:270:5: ^( TOK_DATA_TYPE KW_INTERVAL KW_YEAR ( Number )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_INTERVAL.nextNode());
						adaptor.addChild(root_1, stream_KW_YEAR.nextNode());
						// SQLParser.g:270:41: ( Number )?
						if ( stream_Number.hasNext() ) {
							adaptor.addChild(root_1, stream_Number.nextNode());
						}
						stream_Number.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 9 :
					// SQLParser.g:271:3: KW_INTERVAL KW_MONTH ( LSQUARE Number RSQUARE )?
					{
					KW_INTERVAL52=(Token)match(input,KW_INTERVAL,FOLLOW_KW_INTERVAL_in_dataType775);  
					stream_KW_INTERVAL.add(KW_INTERVAL52);

					KW_MONTH53=(Token)match(input,KW_MONTH,FOLLOW_KW_MONTH_in_dataType777);  
					stream_KW_MONTH.add(KW_MONTH53);

					// SQLParser.g:271:24: ( LSQUARE Number RSQUARE )?
					int alt15=2;
					int LA15_0 = input.LA(1);
					if ( (LA15_0==LSQUARE) ) {
						alt15=1;
					}
					switch (alt15) {
						case 1 :
							// SQLParser.g:271:25: LSQUARE Number RSQUARE
							{
							LSQUARE54=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_dataType780);  
							stream_LSQUARE.add(LSQUARE54);

							Number55=(Token)match(input,Number,FOLLOW_Number_in_dataType782);  
							stream_Number.add(Number55);

							RSQUARE56=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_dataType784);  
							stream_RSQUARE.add(RSQUARE56);

							}
							break;

					}

					// AST REWRITE
					// elements: Number, KW_INTERVAL, KW_MONTH
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 272:3: -> ^( TOK_DATA_TYPE KW_INTERVAL KW_MONTH ( Number )? )
					{
						// SQLParser.g:272:5: ^( TOK_DATA_TYPE KW_INTERVAL KW_MONTH ( Number )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_INTERVAL.nextNode());
						adaptor.addChild(root_1, stream_KW_MONTH.nextNode());
						// SQLParser.g:272:42: ( Number )?
						if ( stream_Number.hasNext() ) {
							adaptor.addChild(root_1, stream_Number.nextNode());
						}
						stream_Number.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 10 :
					// SQLParser.g:273:3: KW_INTERVAL KW_DAY ( LSQUARE Number RSQUARE )? ( KW_TO KW_HOUR | KW_MINUTE | KW_SECOND )?
					{
					KW_INTERVAL57=(Token)match(input,KW_INTERVAL,FOLLOW_KW_INTERVAL_in_dataType804);  
					stream_KW_INTERVAL.add(KW_INTERVAL57);

					KW_DAY58=(Token)match(input,KW_DAY,FOLLOW_KW_DAY_in_dataType806);  
					stream_KW_DAY.add(KW_DAY58);

					// SQLParser.g:273:22: ( LSQUARE Number RSQUARE )?
					int alt16=2;
					int LA16_0 = input.LA(1);
					if ( (LA16_0==LSQUARE) ) {
						alt16=1;
					}
					switch (alt16) {
						case 1 :
							// SQLParser.g:273:23: LSQUARE Number RSQUARE
							{
							LSQUARE59=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_dataType809);  
							stream_LSQUARE.add(LSQUARE59);

							Number60=(Token)match(input,Number,FOLLOW_Number_in_dataType811);  
							stream_Number.add(Number60);

							RSQUARE61=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_dataType813);  
							stream_RSQUARE.add(RSQUARE61);

							}
							break;

					}

					// SQLParser.g:273:48: ( KW_TO KW_HOUR | KW_MINUTE | KW_SECOND )?
					int alt17=4;
					switch ( input.LA(1) ) {
						case KW_TO:
							{
							alt17=1;
							}
							break;
						case KW_MINUTE:
							{
							alt17=2;
							}
							break;
						case KW_SECOND:
							{
							alt17=3;
							}
							break;
					}
					switch (alt17) {
						case 1 :
							// SQLParser.g:273:49: KW_TO KW_HOUR
							{
							KW_TO62=(Token)match(input,KW_TO,FOLLOW_KW_TO_in_dataType818);  
							stream_KW_TO.add(KW_TO62);

							KW_HOUR63=(Token)match(input,KW_HOUR,FOLLOW_KW_HOUR_in_dataType820);  
							stream_KW_HOUR.add(KW_HOUR63);

							}
							break;
						case 2 :
							// SQLParser.g:273:63: KW_MINUTE
							{
							KW_MINUTE64=(Token)match(input,KW_MINUTE,FOLLOW_KW_MINUTE_in_dataType822);  
							stream_KW_MINUTE.add(KW_MINUTE64);

							}
							break;
						case 3 :
							// SQLParser.g:273:73: KW_SECOND
							{
							KW_SECOND65=(Token)match(input,KW_SECOND,FOLLOW_KW_SECOND_in_dataType824);  
							stream_KW_SECOND.add(KW_SECOND65);

							}
							break;

					}

					// AST REWRITE
					// elements: Number, KW_INTERVAL, KW_DAY
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 274:3: -> ^( TOK_DATA_TYPE KW_INTERVAL KW_DAY ( Number )? )
					{
						// SQLParser.g:274:5: ^( TOK_DATA_TYPE KW_INTERVAL KW_DAY ( Number )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_INTERVAL.nextNode());
						adaptor.addChild(root_1, stream_KW_DAY.nextNode());
						// SQLParser.g:274:40: ( Number )?
						if ( stream_Number.hasNext() ) {
							adaptor.addChild(root_1, stream_Number.nextNode());
						}
						stream_Number.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 11 :
					// SQLParser.g:275:3: KW_INTERVAL KW_HOUR ( LSQUARE Number RSQUARE )? ( KW_TO KW_MINUTE | KW_SECOND )?
					{
					KW_INTERVAL66=(Token)match(input,KW_INTERVAL,FOLLOW_KW_INTERVAL_in_dataType845);  
					stream_KW_INTERVAL.add(KW_INTERVAL66);

					KW_HOUR67=(Token)match(input,KW_HOUR,FOLLOW_KW_HOUR_in_dataType847);  
					stream_KW_HOUR.add(KW_HOUR67);

					// SQLParser.g:275:23: ( LSQUARE Number RSQUARE )?
					int alt18=2;
					int LA18_0 = input.LA(1);
					if ( (LA18_0==LSQUARE) ) {
						alt18=1;
					}
					switch (alt18) {
						case 1 :
							// SQLParser.g:275:24: LSQUARE Number RSQUARE
							{
							LSQUARE68=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_dataType850);  
							stream_LSQUARE.add(LSQUARE68);

							Number69=(Token)match(input,Number,FOLLOW_Number_in_dataType852);  
							stream_Number.add(Number69);

							RSQUARE70=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_dataType854);  
							stream_RSQUARE.add(RSQUARE70);

							}
							break;

					}

					// SQLParser.g:275:49: ( KW_TO KW_MINUTE | KW_SECOND )?
					int alt19=3;
					int LA19_0 = input.LA(1);
					if ( (LA19_0==KW_TO) ) {
						alt19=1;
					}
					else if ( (LA19_0==KW_SECOND) ) {
						alt19=2;
					}
					switch (alt19) {
						case 1 :
							// SQLParser.g:275:50: KW_TO KW_MINUTE
							{
							KW_TO71=(Token)match(input,KW_TO,FOLLOW_KW_TO_in_dataType859);  
							stream_KW_TO.add(KW_TO71);

							KW_MINUTE72=(Token)match(input,KW_MINUTE,FOLLOW_KW_MINUTE_in_dataType861);  
							stream_KW_MINUTE.add(KW_MINUTE72);

							}
							break;
						case 2 :
							// SQLParser.g:275:66: KW_SECOND
							{
							KW_SECOND73=(Token)match(input,KW_SECOND,FOLLOW_KW_SECOND_in_dataType863);  
							stream_KW_SECOND.add(KW_SECOND73);

							}
							break;

					}

					// AST REWRITE
					// elements: KW_HOUR, Number, KW_INTERVAL
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 276:3: -> ^( TOK_DATA_TYPE KW_INTERVAL KW_HOUR ( Number )? )
					{
						// SQLParser.g:276:5: ^( TOK_DATA_TYPE KW_INTERVAL KW_HOUR ( Number )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_INTERVAL.nextNode());
						adaptor.addChild(root_1, stream_KW_HOUR.nextNode());
						// SQLParser.g:276:41: ( Number )?
						if ( stream_Number.hasNext() ) {
							adaptor.addChild(root_1, stream_Number.nextNode());
						}
						stream_Number.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 12 :
					// SQLParser.g:277:3: KW_INTERVAL KW_MINUTE ( LSQUARE Number RSQUARE )? ( KW_TO KE_SECOND )?
					{
					KW_INTERVAL74=(Token)match(input,KW_INTERVAL,FOLLOW_KW_INTERVAL_in_dataType883);  
					stream_KW_INTERVAL.add(KW_INTERVAL74);

					KW_MINUTE75=(Token)match(input,KW_MINUTE,FOLLOW_KW_MINUTE_in_dataType885);  
					stream_KW_MINUTE.add(KW_MINUTE75);

					// SQLParser.g:277:25: ( LSQUARE Number RSQUARE )?
					int alt20=2;
					int LA20_0 = input.LA(1);
					if ( (LA20_0==LSQUARE) ) {
						alt20=1;
					}
					switch (alt20) {
						case 1 :
							// SQLParser.g:277:26: LSQUARE Number RSQUARE
							{
							LSQUARE76=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_dataType888);  
							stream_LSQUARE.add(LSQUARE76);

							Number77=(Token)match(input,Number,FOLLOW_Number_in_dataType890);  
							stream_Number.add(Number77);

							RSQUARE78=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_dataType892);  
							stream_RSQUARE.add(RSQUARE78);

							}
							break;

					}

					// SQLParser.g:277:51: ( KW_TO KE_SECOND )?
					int alt21=2;
					int LA21_0 = input.LA(1);
					if ( (LA21_0==KW_TO) ) {
						alt21=1;
					}
					switch (alt21) {
						case 1 :
							// SQLParser.g:277:52: KW_TO KE_SECOND
							{
							KW_TO79=(Token)match(input,KW_TO,FOLLOW_KW_TO_in_dataType897);  
							stream_KW_TO.add(KW_TO79);

							KE_SECOND80=(Token)match(input,KE_SECOND,FOLLOW_KE_SECOND_in_dataType899);  
							stream_KE_SECOND.add(KE_SECOND80);

							}
							break;

					}

					// AST REWRITE
					// elements: KW_INTERVAL, KW_MINUTE, Number
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 278:3: -> ^( TOK_DATA_TYPE KW_INTERVAL KW_MINUTE ( Number )? )
					{
						// SQLParser.g:278:5: ^( TOK_DATA_TYPE KW_INTERVAL KW_MINUTE ( Number )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_INTERVAL.nextNode());
						adaptor.addChild(root_1, stream_KW_MINUTE.nextNode());
						// SQLParser.g:278:43: ( Number )?
						if ( stream_Number.hasNext() ) {
							adaptor.addChild(root_1, stream_Number.nextNode());
						}
						stream_Number.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 13 :
					// SQLParser.g:279:3: KW_INTERVAL KW_SECOND ( LSQUARE Number ( COMMA Number )? RSQUARE )?
					{
					KW_INTERVAL81=(Token)match(input,KW_INTERVAL,FOLLOW_KW_INTERVAL_in_dataType919);  
					stream_KW_INTERVAL.add(KW_INTERVAL81);

					KW_SECOND82=(Token)match(input,KW_SECOND,FOLLOW_KW_SECOND_in_dataType921);  
					stream_KW_SECOND.add(KW_SECOND82);

					// SQLParser.g:279:25: ( LSQUARE Number ( COMMA Number )? RSQUARE )?
					int alt23=2;
					int LA23_0 = input.LA(1);
					if ( (LA23_0==LSQUARE) ) {
						alt23=1;
					}
					switch (alt23) {
						case 1 :
							// SQLParser.g:279:26: LSQUARE Number ( COMMA Number )? RSQUARE
							{
							LSQUARE83=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_dataType924);  
							stream_LSQUARE.add(LSQUARE83);

							Number84=(Token)match(input,Number,FOLLOW_Number_in_dataType926);  
							stream_Number.add(Number84);

							// SQLParser.g:279:41: ( COMMA Number )?
							int alt22=2;
							int LA22_0 = input.LA(1);
							if ( (LA22_0==COMMA) ) {
								alt22=1;
							}
							switch (alt22) {
								case 1 :
									// SQLParser.g:279:42: COMMA Number
									{
									COMMA85=(Token)match(input,COMMA,FOLLOW_COMMA_in_dataType929);  
									stream_COMMA.add(COMMA85);

									Number86=(Token)match(input,Number,FOLLOW_Number_in_dataType931);  
									stream_Number.add(Number86);

									}
									break;

							}

							RSQUARE87=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_dataType935);  
							stream_RSQUARE.add(RSQUARE87);

							}
							break;

					}

					// AST REWRITE
					// elements: KW_INTERVAL, KW_SECOND, Number
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 280:3: -> ^( TOK_DATA_TYPE KW_INTERVAL KW_SECOND ( Number )? )
					{
						// SQLParser.g:280:5: ^( TOK_DATA_TYPE KW_INTERVAL KW_SECOND ( Number )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_INTERVAL.nextNode());
						adaptor.addChild(root_1, stream_KW_SECOND.nextNode());
						// SQLParser.g:280:43: ( Number )?
						if ( stream_Number.hasNext() ) {
							adaptor.addChild(root_1, stream_Number.nextNode());
						}
						stream_Number.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 14 :
					// SQLParser.g:281:3: KW_FLOAT
					{
					KW_FLOAT88=(Token)match(input,KW_FLOAT,FOLLOW_KW_FLOAT_in_dataType955);  
					stream_KW_FLOAT.add(KW_FLOAT88);

					// AST REWRITE
					// elements: KW_FLOAT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 282:3: -> ^( TOK_DATA_TYPE KW_FLOAT )
					{
						// SQLParser.g:282:5: ^( TOK_DATA_TYPE KW_FLOAT )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_FLOAT.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 15 :
					// SQLParser.g:283:3: KW_DECIMAL ( LSQUARE Number ( COMMA Number )? RSQUARE )?
					{
					KW_DECIMAL89=(Token)match(input,KW_DECIMAL,FOLLOW_KW_DECIMAL_in_dataType968);  
					stream_KW_DECIMAL.add(KW_DECIMAL89);

					// SQLParser.g:283:14: ( LSQUARE Number ( COMMA Number )? RSQUARE )?
					int alt25=2;
					int LA25_0 = input.LA(1);
					if ( (LA25_0==LSQUARE) ) {
						alt25=1;
					}
					switch (alt25) {
						case 1 :
							// SQLParser.g:283:15: LSQUARE Number ( COMMA Number )? RSQUARE
							{
							LSQUARE90=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_dataType971);  
							stream_LSQUARE.add(LSQUARE90);

							Number91=(Token)match(input,Number,FOLLOW_Number_in_dataType973);  
							stream_Number.add(Number91);

							// SQLParser.g:283:30: ( COMMA Number )?
							int alt24=2;
							int LA24_0 = input.LA(1);
							if ( (LA24_0==COMMA) ) {
								alt24=1;
							}
							switch (alt24) {
								case 1 :
									// SQLParser.g:283:31: COMMA Number
									{
									COMMA92=(Token)match(input,COMMA,FOLLOW_COMMA_in_dataType976);  
									stream_COMMA.add(COMMA92);

									Number93=(Token)match(input,Number,FOLLOW_Number_in_dataType978);  
									stream_Number.add(Number93);

									}
									break;

							}

							RSQUARE94=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_dataType982);  
							stream_RSQUARE.add(RSQUARE94);

							}
							break;

					}

					// AST REWRITE
					// elements: Number, KW_DECIMAL
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 284:3: -> ^( TOK_DATA_TYPE KW_DECIMAL ( Number )* )
					{
						// SQLParser.g:284:5: ^( TOK_DATA_TYPE KW_DECIMAL ( Number )* )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_DECIMAL.nextNode());
						// SQLParser.g:284:32: ( Number )*
						while ( stream_Number.hasNext() ) {
							adaptor.addChild(root_1, stream_Number.nextNode());
						}
						stream_Number.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 16 :
					// SQLParser.g:285:3: KW_CHAR ( LSQUARE Number RSQUARE )?
					{
					KW_CHAR95=(Token)match(input,KW_CHAR,FOLLOW_KW_CHAR_in_dataType1000);  
					stream_KW_CHAR.add(KW_CHAR95);

					// SQLParser.g:285:11: ( LSQUARE Number RSQUARE )?
					int alt26=2;
					int LA26_0 = input.LA(1);
					if ( (LA26_0==LSQUARE) ) {
						alt26=1;
					}
					switch (alt26) {
						case 1 :
							// SQLParser.g:285:12: LSQUARE Number RSQUARE
							{
							LSQUARE96=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_dataType1003);  
							stream_LSQUARE.add(LSQUARE96);

							Number97=(Token)match(input,Number,FOLLOW_Number_in_dataType1005);  
							stream_Number.add(Number97);

							RSQUARE98=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_dataType1007);  
							stream_RSQUARE.add(RSQUARE98);

							}
							break;

					}

					// AST REWRITE
					// elements: KW_CHAR, Number
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 286:3: -> ^( TOK_DATA_TYPE KW_CHAR ( Number )? )
					{
						// SQLParser.g:286:5: ^( TOK_DATA_TYPE KW_CHAR ( Number )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_CHAR.nextNode());
						// SQLParser.g:286:29: ( Number )?
						if ( stream_Number.hasNext() ) {
							adaptor.addChild(root_1, stream_Number.nextNode());
						}
						stream_Number.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 17 :
					// SQLParser.g:287:3: KW_VARCHAR LSQUARE Number RSQUARE
					{
					KW_VARCHAR99=(Token)match(input,KW_VARCHAR,FOLLOW_KW_VARCHAR_in_dataType1025);  
					stream_KW_VARCHAR.add(KW_VARCHAR99);

					LSQUARE100=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_dataType1027);  
					stream_LSQUARE.add(LSQUARE100);

					Number101=(Token)match(input,Number,FOLLOW_Number_in_dataType1029);  
					stream_Number.add(Number101);

					RSQUARE102=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_dataType1031);  
					stream_RSQUARE.add(RSQUARE102);

					// AST REWRITE
					// elements: Number, KW_VARCHAR
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 288:3: -> ^( TOK_DATA_TYPE KW_VARCHAR Number )
					{
						// SQLParser.g:288:5: ^( TOK_DATA_TYPE KW_VARCHAR Number )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_VARCHAR.nextNode());
						adaptor.addChild(root_1, stream_Number.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 18 :
					// SQLParser.g:289:3: KW_BYTE ( LSQUARE Number RSQUARE )?
					{
					KW_BYTE103=(Token)match(input,KW_BYTE,FOLLOW_KW_BYTE_in_dataType1046);  
					stream_KW_BYTE.add(KW_BYTE103);

					// SQLParser.g:289:11: ( LSQUARE Number RSQUARE )?
					int alt27=2;
					int LA27_0 = input.LA(1);
					if ( (LA27_0==LSQUARE) ) {
						alt27=1;
					}
					switch (alt27) {
						case 1 :
							// SQLParser.g:289:12: LSQUARE Number RSQUARE
							{
							LSQUARE104=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_dataType1049);  
							stream_LSQUARE.add(LSQUARE104);

							Number105=(Token)match(input,Number,FOLLOW_Number_in_dataType1051);  
							stream_Number.add(Number105);

							RSQUARE106=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_dataType1053);  
							stream_RSQUARE.add(RSQUARE106);

							}
							break;

					}

					// AST REWRITE
					// elements: KW_BYTE, Number
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 290:3: -> ^( TOK_DATA_TYPE KW_BYTE ( Number )* )
					{
						// SQLParser.g:290:5: ^( TOK_DATA_TYPE KW_BYTE ( Number )* )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_BYTE.nextNode());
						// SQLParser.g:290:29: ( Number )*
						while ( stream_Number.hasNext() ) {
							adaptor.addChild(root_1, stream_Number.nextNode());
						}
						stream_Number.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 19 :
					// SQLParser.g:291:3: KW_VARBYTE LSQUARE Number RSQUARE
					{
					KW_VARBYTE107=(Token)match(input,KW_VARBYTE,FOLLOW_KW_VARBYTE_in_dataType1071);  
					stream_KW_VARBYTE.add(KW_VARBYTE107);

					LSQUARE108=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_dataType1073);  
					stream_LSQUARE.add(LSQUARE108);

					Number109=(Token)match(input,Number,FOLLOW_Number_in_dataType1075);  
					stream_Number.add(Number109);

					RSQUARE110=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_dataType1077);  
					stream_RSQUARE.add(RSQUARE110);

					// AST REWRITE
					// elements: KW_VARBYTE, Number
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 292:3: -> ^( TOK_DATA_TYPE KW_VARBYTE Number )
					{
						// SQLParser.g:292:5: ^( TOK_DATA_TYPE KW_VARBYTE Number )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_VARBYTE.nextNode());
						adaptor.addChild(root_1, stream_Number.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 20 :
					// SQLParser.g:293:3: KW_PERIOD ( KW_TIME )?
					{
					KW_PERIOD111=(Token)match(input,KW_PERIOD,FOLLOW_KW_PERIOD_in_dataType1092);  
					stream_KW_PERIOD.add(KW_PERIOD111);

					// SQLParser.g:293:13: ( KW_TIME )?
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==KW_TIME) ) {
						alt28=1;
					}
					switch (alt28) {
						case 1 :
							// SQLParser.g:293:13: KW_TIME
							{
							KW_TIME112=(Token)match(input,KW_TIME,FOLLOW_KW_TIME_in_dataType1094);  
							stream_KW_TIME.add(KW_TIME112);

							}
							break;

					}

					// AST REWRITE
					// elements: KW_PERIOD, KW_TIME
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 294:3: -> ^( TOK_DATA_TYPE KW_PERIOD KW_TIME )
					{
						// SQLParser.g:294:5: ^( TOK_DATA_TYPE KW_PERIOD KW_TIME )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_PERIOD.nextNode());
						adaptor.addChild(root_1, stream_KW_TIME.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 21 :
					// SQLParser.g:295:3: KW_PERIOD KW_DATE
					{
					KW_PERIOD113=(Token)match(input,KW_PERIOD,FOLLOW_KW_PERIOD_in_dataType1110);  
					stream_KW_PERIOD.add(KW_PERIOD113);

					KW_DATE114=(Token)match(input,KW_DATE,FOLLOW_KW_DATE_in_dataType1112);  
					stream_KW_DATE.add(KW_DATE114);

					// AST REWRITE
					// elements: KW_PERIOD, KW_DATE
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 296:3: -> ^( TOK_DATA_TYPE KW_PERIOD KW_DATE )
					{
						// SQLParser.g:296:5: ^( TOK_DATA_TYPE KW_PERIOD KW_DATE )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_PERIOD.nextNode());
						adaptor.addChild(root_1, stream_KW_DATE.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 22 :
					// SQLParser.g:297:3: KW_PERIOD KW_TIMESTAMP ( LSQUARE Number RSQUARE )? ( KW_WITH KW_TIME_ZONE )?
					{
					KW_PERIOD115=(Token)match(input,KW_PERIOD,FOLLOW_KW_PERIOD_in_dataType1127);  
					stream_KW_PERIOD.add(KW_PERIOD115);

					KW_TIMESTAMP116=(Token)match(input,KW_TIMESTAMP,FOLLOW_KW_TIMESTAMP_in_dataType1129);  
					stream_KW_TIMESTAMP.add(KW_TIMESTAMP116);

					// SQLParser.g:297:27: ( LSQUARE Number RSQUARE )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==LSQUARE) ) {
						alt29=1;
					}
					switch (alt29) {
						case 1 :
							// SQLParser.g:297:29: LSQUARE Number RSQUARE
							{
							LSQUARE117=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_dataType1134);  
							stream_LSQUARE.add(LSQUARE117);

							Number118=(Token)match(input,Number,FOLLOW_Number_in_dataType1136);  
							stream_Number.add(Number118);

							RSQUARE119=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_dataType1138);  
							stream_RSQUARE.add(RSQUARE119);

							}
							break;

					}

					// SQLParser.g:297:54: ( KW_WITH KW_TIME_ZONE )?
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0==KW_WITH) ) {
						int LA30_1 = input.LA(2);
						if ( (LA30_1==KW_TIME_ZONE) ) {
							alt30=1;
						}
					}
					switch (alt30) {
						case 1 :
							// SQLParser.g:297:55: KW_WITH KW_TIME_ZONE
							{
							KW_WITH120=(Token)match(input,KW_WITH,FOLLOW_KW_WITH_in_dataType1143);  
							stream_KW_WITH.add(KW_WITH120);

							KW_TIME_ZONE121=(Token)match(input,KW_TIME_ZONE,FOLLOW_KW_TIME_ZONE_in_dataType1145);  
							stream_KW_TIME_ZONE.add(KW_TIME_ZONE121);

							}
							break;

					}

					// AST REWRITE
					// elements: KW_PERIOD, Number, KW_TIMESTAMP
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 298:3: -> ^( TOK_DATA_TYPE KW_PERIOD KW_TIMESTAMP ( Number )? )
					{
						// SQLParser.g:298:5: ^( TOK_DATA_TYPE KW_PERIOD KW_TIMESTAMP ( Number )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_TYPE, "TOK_DATA_TYPE"), root_1);
						adaptor.addChild(root_1, stream_KW_PERIOD.nextNode());
						adaptor.addChild(root_1, stream_KW_TIMESTAMP.nextNode());
						// SQLParser.g:298:44: ( Number )?
						if ( stream_Number.hasNext() ) {
							adaptor.addChild(root_1, stream_Number.nextNode());
						}
						stream_Number.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dataType"


	public static class dataAttribute_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "dataAttribute"
	// SQLParser.g:301:1: dataAttribute : ( KW_UPPERCASE -> ^( TOK_DATA_ATTRIBUTE KW_UPPERCASE ) | ( KW_NOT )? KW_CASESPECIFIC -> ^( TOK_DATA_ATTRIBUTE KW_CASESPECIFIC ( KW_NOT )? ) | KW_FORMAT StringLiteral -> ^( TOK_DATA_ATTRIBUTE KW_FORMAT StringLiteral ) | KW_WITH KW_DEFAULT -> ^( TOK_DATA_ATTRIBUTE KW_DEFAULT ) | ( KW_NOT )? KW_NULL -> ^( TOK_DATA_ATTRIBUTE KW_NULL ( KW_NOT )? ) );
	public final SQLParser.dataAttribute_return dataAttribute() throws RecognitionException {
		SQLParser.dataAttribute_return retval = new SQLParser.dataAttribute_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_UPPERCASE122=null;
		Token KW_NOT123=null;
		Token KW_CASESPECIFIC124=null;
		Token KW_FORMAT125=null;
		Token StringLiteral126=null;
		Token KW_WITH127=null;
		Token KW_DEFAULT128=null;
		Token KW_NOT129=null;
		Token KW_NULL130=null;

		CommonTree KW_UPPERCASE122_tree=null;
		CommonTree KW_NOT123_tree=null;
		CommonTree KW_CASESPECIFIC124_tree=null;
		CommonTree KW_FORMAT125_tree=null;
		CommonTree StringLiteral126_tree=null;
		CommonTree KW_WITH127_tree=null;
		CommonTree KW_DEFAULT128_tree=null;
		CommonTree KW_NOT129_tree=null;
		CommonTree KW_NULL130_tree=null;
		RewriteRuleTokenStream stream_KW_DEFAULT=new RewriteRuleTokenStream(adaptor,"token KW_DEFAULT");
		RewriteRuleTokenStream stream_StringLiteral=new RewriteRuleTokenStream(adaptor,"token StringLiteral");
		RewriteRuleTokenStream stream_KW_UPPERCASE=new RewriteRuleTokenStream(adaptor,"token KW_UPPERCASE");
		RewriteRuleTokenStream stream_KW_NULL=new RewriteRuleTokenStream(adaptor,"token KW_NULL");
		RewriteRuleTokenStream stream_KW_CASESPECIFIC=new RewriteRuleTokenStream(adaptor,"token KW_CASESPECIFIC");
		RewriteRuleTokenStream stream_KW_WITH=new RewriteRuleTokenStream(adaptor,"token KW_WITH");
		RewriteRuleTokenStream stream_KW_FORMAT=new RewriteRuleTokenStream(adaptor,"token KW_FORMAT");
		RewriteRuleTokenStream stream_KW_NOT=new RewriteRuleTokenStream(adaptor,"token KW_NOT");

		try {
			// SQLParser.g:302:2: ( KW_UPPERCASE -> ^( TOK_DATA_ATTRIBUTE KW_UPPERCASE ) | ( KW_NOT )? KW_CASESPECIFIC -> ^( TOK_DATA_ATTRIBUTE KW_CASESPECIFIC ( KW_NOT )? ) | KW_FORMAT StringLiteral -> ^( TOK_DATA_ATTRIBUTE KW_FORMAT StringLiteral ) | KW_WITH KW_DEFAULT -> ^( TOK_DATA_ATTRIBUTE KW_DEFAULT ) | ( KW_NOT )? KW_NULL -> ^( TOK_DATA_ATTRIBUTE KW_NULL ( KW_NOT )? ) )
			int alt34=5;
			switch ( input.LA(1) ) {
			case KW_UPPERCASE:
				{
				alt34=1;
				}
				break;
			case KW_NOT:
				{
				int LA34_2 = input.LA(2);
				if ( (LA34_2==KW_CASESPECIFIC) ) {
					alt34=2;
				}
				else if ( (LA34_2==KW_NULL) ) {
					alt34=5;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 34, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case KW_CASESPECIFIC:
				{
				alt34=2;
				}
				break;
			case KW_FORMAT:
				{
				alt34=3;
				}
				break;
			case KW_WITH:
				{
				alt34=4;
				}
				break;
			case KW_NULL:
				{
				alt34=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 34, 0, input);
				throw nvae;
			}
			switch (alt34) {
				case 1 :
					// SQLParser.g:303:2: KW_UPPERCASE
					{
					KW_UPPERCASE122=(Token)match(input,KW_UPPERCASE,FOLLOW_KW_UPPERCASE_in_dataAttribute1174);  
					stream_KW_UPPERCASE.add(KW_UPPERCASE122);

					// AST REWRITE
					// elements: KW_UPPERCASE
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 304:3: -> ^( TOK_DATA_ATTRIBUTE KW_UPPERCASE )
					{
						// SQLParser.g:304:5: ^( TOK_DATA_ATTRIBUTE KW_UPPERCASE )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_ATTRIBUTE, "TOK_DATA_ATTRIBUTE"), root_1);
						adaptor.addChild(root_1, stream_KW_UPPERCASE.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// SQLParser.g:305:4: ( KW_NOT )? KW_CASESPECIFIC
					{
					// SQLParser.g:305:4: ( KW_NOT )?
					int alt32=2;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==KW_NOT) ) {
						alt32=1;
					}
					switch (alt32) {
						case 1 :
							// SQLParser.g:305:5: KW_NOT
							{
							KW_NOT123=(Token)match(input,KW_NOT,FOLLOW_KW_NOT_in_dataAttribute1189);  
							stream_KW_NOT.add(KW_NOT123);

							}
							break;

					}

					KW_CASESPECIFIC124=(Token)match(input,KW_CASESPECIFIC,FOLLOW_KW_CASESPECIFIC_in_dataAttribute1193);  
					stream_KW_CASESPECIFIC.add(KW_CASESPECIFIC124);

					// AST REWRITE
					// elements: KW_NOT, KW_CASESPECIFIC
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 306:3: -> ^( TOK_DATA_ATTRIBUTE KW_CASESPECIFIC ( KW_NOT )? )
					{
						// SQLParser.g:306:5: ^( TOK_DATA_ATTRIBUTE KW_CASESPECIFIC ( KW_NOT )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_ATTRIBUTE, "TOK_DATA_ATTRIBUTE"), root_1);
						adaptor.addChild(root_1, stream_KW_CASESPECIFIC.nextNode());
						// SQLParser.g:306:42: ( KW_NOT )?
						if ( stream_KW_NOT.hasNext() ) {
							adaptor.addChild(root_1, stream_KW_NOT.nextNode());
						}
						stream_KW_NOT.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// SQLParser.g:307:3: KW_FORMAT StringLiteral
					{
					KW_FORMAT125=(Token)match(input,KW_FORMAT,FOLLOW_KW_FORMAT_in_dataAttribute1209);  
					stream_KW_FORMAT.add(KW_FORMAT125);

					StringLiteral126=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_dataAttribute1212);  
					stream_StringLiteral.add(StringLiteral126);

					// AST REWRITE
					// elements: StringLiteral, KW_FORMAT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 308:3: -> ^( TOK_DATA_ATTRIBUTE KW_FORMAT StringLiteral )
					{
						// SQLParser.g:308:5: ^( TOK_DATA_ATTRIBUTE KW_FORMAT StringLiteral )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_ATTRIBUTE, "TOK_DATA_ATTRIBUTE"), root_1);
						adaptor.addChild(root_1, stream_KW_FORMAT.nextNode());
						adaptor.addChild(root_1, stream_StringLiteral.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 4 :
					// SQLParser.g:309:3: KW_WITH KW_DEFAULT
					{
					KW_WITH127=(Token)match(input,KW_WITH,FOLLOW_KW_WITH_in_dataAttribute1227);  
					stream_KW_WITH.add(KW_WITH127);

					KW_DEFAULT128=(Token)match(input,KW_DEFAULT,FOLLOW_KW_DEFAULT_in_dataAttribute1229);  
					stream_KW_DEFAULT.add(KW_DEFAULT128);

					// AST REWRITE
					// elements: KW_DEFAULT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 310:3: -> ^( TOK_DATA_ATTRIBUTE KW_DEFAULT )
					{
						// SQLParser.g:310:5: ^( TOK_DATA_ATTRIBUTE KW_DEFAULT )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_ATTRIBUTE, "TOK_DATA_ATTRIBUTE"), root_1);
						adaptor.addChild(root_1, stream_KW_DEFAULT.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 5 :
					// SQLParser.g:311:3: ( KW_NOT )? KW_NULL
					{
					// SQLParser.g:311:3: ( KW_NOT )?
					int alt33=2;
					int LA33_0 = input.LA(1);
					if ( (LA33_0==KW_NOT) ) {
						alt33=1;
					}
					switch (alt33) {
						case 1 :
							// SQLParser.g:311:4: KW_NOT
							{
							KW_NOT129=(Token)match(input,KW_NOT,FOLLOW_KW_NOT_in_dataAttribute1243);  
							stream_KW_NOT.add(KW_NOT129);

							}
							break;

					}

					KW_NULL130=(Token)match(input,KW_NULL,FOLLOW_KW_NULL_in_dataAttribute1247);  
					stream_KW_NULL.add(KW_NULL130);

					// AST REWRITE
					// elements: KW_NOT, KW_NULL
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 312:3: -> ^( TOK_DATA_ATTRIBUTE KW_NULL ( KW_NOT )? )
					{
						// SQLParser.g:312:5: ^( TOK_DATA_ATTRIBUTE KW_NULL ( KW_NOT )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATA_ATTRIBUTE, "TOK_DATA_ATTRIBUTE"), root_1);
						adaptor.addChild(root_1, stream_KW_NULL.nextNode());
						// SQLParser.g:312:34: ( KW_NOT )?
						if ( stream_KW_NOT.hasNext() ) {
							adaptor.addChild(root_1, stream_KW_NOT.nextNode());
						}
						stream_KW_NOT.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dataAttribute"


	public static class defineSourceStatement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "defineSourceStatement"
	// SQLParser.g:316:1: defineSourceStatement : KW_DEFINE KW_SOURCE name= tableName ( KW_AS | KW_WITH )? KW_META meta= metaName KW_PATH path= pathString -> ^( TOK_DEFINE_SOURCE_STATEMENT ^( TOK_DEFINE KW_SOURCE $name $meta $path) ) ;
	public final SQLParser.defineSourceStatement_return defineSourceStatement() throws RecognitionException {
		SQLParser.defineSourceStatement_return retval = new SQLParser.defineSourceStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_DEFINE131=null;
		Token KW_SOURCE132=null;
		Token KW_AS133=null;
		Token KW_WITH134=null;
		Token KW_META135=null;
		Token KW_PATH136=null;
		ParserRuleReturnScope name =null;
		ParserRuleReturnScope meta =null;
		ParserRuleReturnScope path =null;

		CommonTree KW_DEFINE131_tree=null;
		CommonTree KW_SOURCE132_tree=null;
		CommonTree KW_AS133_tree=null;
		CommonTree KW_WITH134_tree=null;
		CommonTree KW_META135_tree=null;
		CommonTree KW_PATH136_tree=null;
		RewriteRuleTokenStream stream_KW_AS=new RewriteRuleTokenStream(adaptor,"token KW_AS");
		RewriteRuleTokenStream stream_KW_DEFINE=new RewriteRuleTokenStream(adaptor,"token KW_DEFINE");
		RewriteRuleTokenStream stream_KW_SOURCE=new RewriteRuleTokenStream(adaptor,"token KW_SOURCE");
		RewriteRuleTokenStream stream_KW_PATH=new RewriteRuleTokenStream(adaptor,"token KW_PATH");
		RewriteRuleTokenStream stream_KW_WITH=new RewriteRuleTokenStream(adaptor,"token KW_WITH");
		RewriteRuleTokenStream stream_KW_META=new RewriteRuleTokenStream(adaptor,"token KW_META");
		RewriteRuleSubtreeStream stream_pathString=new RewriteRuleSubtreeStream(adaptor,"rule pathString");
		RewriteRuleSubtreeStream stream_tableName=new RewriteRuleSubtreeStream(adaptor,"rule tableName");
		RewriteRuleSubtreeStream stream_metaName=new RewriteRuleSubtreeStream(adaptor,"rule metaName");

		try {
			// SQLParser.g:317:2: ( KW_DEFINE KW_SOURCE name= tableName ( KW_AS | KW_WITH )? KW_META meta= metaName KW_PATH path= pathString -> ^( TOK_DEFINE_SOURCE_STATEMENT ^( TOK_DEFINE KW_SOURCE $name $meta $path) ) )
			// SQLParser.g:318:2: KW_DEFINE KW_SOURCE name= tableName ( KW_AS | KW_WITH )? KW_META meta= metaName KW_PATH path= pathString
			{
			KW_DEFINE131=(Token)match(input,KW_DEFINE,FOLLOW_KW_DEFINE_in_defineSourceStatement1274);  
			stream_KW_DEFINE.add(KW_DEFINE131);

			KW_SOURCE132=(Token)match(input,KW_SOURCE,FOLLOW_KW_SOURCE_in_defineSourceStatement1276);  
			stream_KW_SOURCE.add(KW_SOURCE132);

			pushFollow(FOLLOW_tableName_in_defineSourceStatement1280);
			name=tableName();
			state._fsp--;

			stream_tableName.add(name.getTree());
			// SQLParser.g:318:37: ( KW_AS | KW_WITH )?
			int alt35=3;
			int LA35_0 = input.LA(1);
			if ( (LA35_0==KW_AS) ) {
				alt35=1;
			}
			else if ( (LA35_0==KW_WITH) ) {
				alt35=2;
			}
			switch (alt35) {
				case 1 :
					// SQLParser.g:318:38: KW_AS
					{
					KW_AS133=(Token)match(input,KW_AS,FOLLOW_KW_AS_in_defineSourceStatement1283);  
					stream_KW_AS.add(KW_AS133);

					}
					break;
				case 2 :
					// SQLParser.g:318:44: KW_WITH
					{
					KW_WITH134=(Token)match(input,KW_WITH,FOLLOW_KW_WITH_in_defineSourceStatement1285);  
					stream_KW_WITH.add(KW_WITH134);

					}
					break;

			}

			KW_META135=(Token)match(input,KW_META,FOLLOW_KW_META_in_defineSourceStatement1289);  
			stream_KW_META.add(KW_META135);

			pushFollow(FOLLOW_metaName_in_defineSourceStatement1293);
			meta=metaName();
			state._fsp--;

			stream_metaName.add(meta.getTree());
			KW_PATH136=(Token)match(input,KW_PATH,FOLLOW_KW_PATH_in_defineSourceStatement1295);  
			stream_KW_PATH.add(KW_PATH136);

			pushFollow(FOLLOW_pathString_in_defineSourceStatement1299);
			path=pathString();
			state._fsp--;

			stream_pathString.add(path.getTree());
			// AST REWRITE
			// elements: path, meta, name, KW_SOURCE
			// token labels: 
			// rule labels: retval, name, path, meta
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name",name!=null?name.getTree():null);
			RewriteRuleSubtreeStream stream_path=new RewriteRuleSubtreeStream(adaptor,"rule path",path!=null?path.getTree():null);
			RewriteRuleSubtreeStream stream_meta=new RewriteRuleSubtreeStream(adaptor,"rule meta",meta!=null?meta.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 319:3: -> ^( TOK_DEFINE_SOURCE_STATEMENT ^( TOK_DEFINE KW_SOURCE $name $meta $path) )
			{
				// SQLParser.g:319:5: ^( TOK_DEFINE_SOURCE_STATEMENT ^( TOK_DEFINE KW_SOURCE $name $meta $path) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DEFINE_SOURCE_STATEMENT, "TOK_DEFINE_SOURCE_STATEMENT"), root_1);
				// SQLParser.g:319:35: ^( TOK_DEFINE KW_SOURCE $name $meta $path)
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DEFINE, "TOK_DEFINE"), root_2);
				adaptor.addChild(root_2, stream_KW_SOURCE.nextNode());
				adaptor.addChild(root_2, stream_name.nextTree());
				adaptor.addChild(root_2, stream_meta.nextTree());
				adaptor.addChild(root_2, stream_path.nextTree());
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "defineSourceStatement"


	public static class defineTargetStatement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "defineTargetStatement"
	// SQLParser.g:322:1: defineTargetStatement : KW_DEFINE KW_TARGET name= tableName ( KW_AS | KW_WITH )? ( KW_META meta= metaName )? KW_PATH path= pathString -> ^( TOK_DEFINE_TARGET_STATEMENT ^( TOK_DEFINE KW_TARGET $name ( $meta)? $path) ) ;
	public final SQLParser.defineTargetStatement_return defineTargetStatement() throws RecognitionException {
		SQLParser.defineTargetStatement_return retval = new SQLParser.defineTargetStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_DEFINE137=null;
		Token KW_TARGET138=null;
		Token KW_AS139=null;
		Token KW_WITH140=null;
		Token KW_META141=null;
		Token KW_PATH142=null;
		ParserRuleReturnScope name =null;
		ParserRuleReturnScope meta =null;
		ParserRuleReturnScope path =null;

		CommonTree KW_DEFINE137_tree=null;
		CommonTree KW_TARGET138_tree=null;
		CommonTree KW_AS139_tree=null;
		CommonTree KW_WITH140_tree=null;
		CommonTree KW_META141_tree=null;
		CommonTree KW_PATH142_tree=null;
		RewriteRuleTokenStream stream_KW_AS=new RewriteRuleTokenStream(adaptor,"token KW_AS");
		RewriteRuleTokenStream stream_KW_DEFINE=new RewriteRuleTokenStream(adaptor,"token KW_DEFINE");
		RewriteRuleTokenStream stream_KW_PATH=new RewriteRuleTokenStream(adaptor,"token KW_PATH");
		RewriteRuleTokenStream stream_KW_WITH=new RewriteRuleTokenStream(adaptor,"token KW_WITH");
		RewriteRuleTokenStream stream_KW_META=new RewriteRuleTokenStream(adaptor,"token KW_META");
		RewriteRuleTokenStream stream_KW_TARGET=new RewriteRuleTokenStream(adaptor,"token KW_TARGET");
		RewriteRuleSubtreeStream stream_pathString=new RewriteRuleSubtreeStream(adaptor,"rule pathString");
		RewriteRuleSubtreeStream stream_tableName=new RewriteRuleSubtreeStream(adaptor,"rule tableName");
		RewriteRuleSubtreeStream stream_metaName=new RewriteRuleSubtreeStream(adaptor,"rule metaName");

		try {
			// SQLParser.g:323:2: ( KW_DEFINE KW_TARGET name= tableName ( KW_AS | KW_WITH )? ( KW_META meta= metaName )? KW_PATH path= pathString -> ^( TOK_DEFINE_TARGET_STATEMENT ^( TOK_DEFINE KW_TARGET $name ( $meta)? $path) ) )
			// SQLParser.g:324:2: KW_DEFINE KW_TARGET name= tableName ( KW_AS | KW_WITH )? ( KW_META meta= metaName )? KW_PATH path= pathString
			{
			KW_DEFINE137=(Token)match(input,KW_DEFINE,FOLLOW_KW_DEFINE_in_defineTargetStatement1334);  
			stream_KW_DEFINE.add(KW_DEFINE137);

			KW_TARGET138=(Token)match(input,KW_TARGET,FOLLOW_KW_TARGET_in_defineTargetStatement1336);  
			stream_KW_TARGET.add(KW_TARGET138);

			pushFollow(FOLLOW_tableName_in_defineTargetStatement1340);
			name=tableName();
			state._fsp--;

			stream_tableName.add(name.getTree());
			// SQLParser.g:324:37: ( KW_AS | KW_WITH )?
			int alt36=3;
			int LA36_0 = input.LA(1);
			if ( (LA36_0==KW_AS) ) {
				alt36=1;
			}
			else if ( (LA36_0==KW_WITH) ) {
				alt36=2;
			}
			switch (alt36) {
				case 1 :
					// SQLParser.g:324:38: KW_AS
					{
					KW_AS139=(Token)match(input,KW_AS,FOLLOW_KW_AS_in_defineTargetStatement1343);  
					stream_KW_AS.add(KW_AS139);

					}
					break;
				case 2 :
					// SQLParser.g:324:44: KW_WITH
					{
					KW_WITH140=(Token)match(input,KW_WITH,FOLLOW_KW_WITH_in_defineTargetStatement1345);  
					stream_KW_WITH.add(KW_WITH140);

					}
					break;

			}

			// SQLParser.g:324:54: ( KW_META meta= metaName )?
			int alt37=2;
			int LA37_0 = input.LA(1);
			if ( (LA37_0==KW_META) ) {
				alt37=1;
			}
			switch (alt37) {
				case 1 :
					// SQLParser.g:324:55: KW_META meta= metaName
					{
					KW_META141=(Token)match(input,KW_META,FOLLOW_KW_META_in_defineTargetStatement1350);  
					stream_KW_META.add(KW_META141);

					pushFollow(FOLLOW_metaName_in_defineTargetStatement1354);
					meta=metaName();
					state._fsp--;

					stream_metaName.add(meta.getTree());
					}
					break;

			}

			KW_PATH142=(Token)match(input,KW_PATH,FOLLOW_KW_PATH_in_defineTargetStatement1358);  
			stream_KW_PATH.add(KW_PATH142);

			pushFollow(FOLLOW_pathString_in_defineTargetStatement1362);
			path=pathString();
			state._fsp--;

			stream_pathString.add(path.getTree());
			// AST REWRITE
			// elements: name, path, meta, KW_TARGET
			// token labels: 
			// rule labels: retval, name, path, meta
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name",name!=null?name.getTree():null);
			RewriteRuleSubtreeStream stream_path=new RewriteRuleSubtreeStream(adaptor,"rule path",path!=null?path.getTree():null);
			RewriteRuleSubtreeStream stream_meta=new RewriteRuleSubtreeStream(adaptor,"rule meta",meta!=null?meta.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 325:3: -> ^( TOK_DEFINE_TARGET_STATEMENT ^( TOK_DEFINE KW_TARGET $name ( $meta)? $path) )
			{
				// SQLParser.g:325:5: ^( TOK_DEFINE_TARGET_STATEMENT ^( TOK_DEFINE KW_TARGET $name ( $meta)? $path) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DEFINE_TARGET_STATEMENT, "TOK_DEFINE_TARGET_STATEMENT"), root_1);
				// SQLParser.g:325:35: ^( TOK_DEFINE KW_TARGET $name ( $meta)? $path)
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DEFINE, "TOK_DEFINE"), root_2);
				adaptor.addChild(root_2, stream_KW_TARGET.nextNode());
				adaptor.addChild(root_2, stream_name.nextTree());
				// SQLParser.g:325:65: ( $meta)?
				if ( stream_meta.hasNext() ) {
					adaptor.addChild(root_2, stream_meta.nextTree());
				}
				stream_meta.reset();

				adaptor.addChild(root_2, stream_path.nextTree());
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "defineTargetStatement"


	public static class metaName_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "metaName"
	// SQLParser.g:328:1: metaName : db= identifier DOT tab= identifier -> ^( TOK_META $db $tab) ;
	public final SQLParser.metaName_return metaName() throws RecognitionException {
		SQLParser.metaName_return retval = new SQLParser.metaName_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token DOT143=null;
		ParserRuleReturnScope db =null;
		ParserRuleReturnScope tab =null;

		CommonTree DOT143_tree=null;
		RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// SQLParser.g:329:5: (db= identifier DOT tab= identifier -> ^( TOK_META $db $tab) )
			// SQLParser.g:330:2: db= identifier DOT tab= identifier
			{
			pushFollow(FOLLOW_identifier_in_metaName1403);
			db=identifier();
			state._fsp--;

			stream_identifier.add(db.getTree());
			DOT143=(Token)match(input,DOT,FOLLOW_DOT_in_metaName1405);  
			stream_DOT.add(DOT143);

			pushFollow(FOLLOW_identifier_in_metaName1409);
			tab=identifier();
			state._fsp--;

			stream_identifier.add(tab.getTree());
			// AST REWRITE
			// elements: tab, db
			// token labels: 
			// rule labels: db, retval, tab
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_db=new RewriteRuleSubtreeStream(adaptor,"rule db",db!=null?db.getTree():null);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_tab=new RewriteRuleSubtreeStream(adaptor,"rule tab",tab!=null?tab.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 331:6: -> ^( TOK_META $db $tab)
			{
				// SQLParser.g:331:8: ^( TOK_META $db $tab)
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_META, "TOK_META"), root_1);
				adaptor.addChild(root_1, stream_db.nextTree());
				adaptor.addChild(root_1, stream_tab.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "metaName"


	public static class pathString_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "pathString"
	// SQLParser.g:334:1: pathString : path= StringLiteral -> ^( TOK_PATH $path) ;
	public final SQLParser.pathString_return pathString() throws RecognitionException {
		SQLParser.pathString_return retval = new SQLParser.pathString_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token path=null;

		CommonTree path_tree=null;
		RewriteRuleTokenStream stream_StringLiteral=new RewriteRuleTokenStream(adaptor,"token StringLiteral");

		try {
			// SQLParser.g:335:2: (path= StringLiteral -> ^( TOK_PATH $path) )
			// SQLParser.g:336:2: path= StringLiteral
			{
			path=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_pathString1442);  
			stream_StringLiteral.add(path);

			// AST REWRITE
			// elements: path
			// token labels: path
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleTokenStream stream_path=new RewriteRuleTokenStream(adaptor,"token path",path);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 337:3: -> ^( TOK_PATH $path)
			{
				// SQLParser.g:337:5: ^( TOK_PATH $path)
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_PATH, "TOK_PATH"), root_1);
				adaptor.addChild(root_1, stream_path.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "pathString"


	public static class overwriteTargetStatement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "overwriteTargetStatement"
	// SQLParser.g:340:1: overwriteTargetStatement : KW_OVERWRITE KW_TARGET name= tableName KW_AS queryStatement -> ^( TOK_OVERWRITE_TARGET_STATEMENT ^( TOK_DESTINATION $name) queryStatement ) ;
	public final SQLParser.overwriteTargetStatement_return overwriteTargetStatement() throws RecognitionException {
		SQLParser.overwriteTargetStatement_return retval = new SQLParser.overwriteTargetStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_OVERWRITE144=null;
		Token KW_TARGET145=null;
		Token KW_AS146=null;
		ParserRuleReturnScope name =null;
		ParserRuleReturnScope queryStatement147 =null;

		CommonTree KW_OVERWRITE144_tree=null;
		CommonTree KW_TARGET145_tree=null;
		CommonTree KW_AS146_tree=null;
		RewriteRuleTokenStream stream_KW_AS=new RewriteRuleTokenStream(adaptor,"token KW_AS");
		RewriteRuleTokenStream stream_KW_OVERWRITE=new RewriteRuleTokenStream(adaptor,"token KW_OVERWRITE");
		RewriteRuleTokenStream stream_KW_TARGET=new RewriteRuleTokenStream(adaptor,"token KW_TARGET");
		RewriteRuleSubtreeStream stream_tableName=new RewriteRuleSubtreeStream(adaptor,"rule tableName");
		RewriteRuleSubtreeStream stream_queryStatement=new RewriteRuleSubtreeStream(adaptor,"rule queryStatement");

		try {
			// SQLParser.g:341:2: ( KW_OVERWRITE KW_TARGET name= tableName KW_AS queryStatement -> ^( TOK_OVERWRITE_TARGET_STATEMENT ^( TOK_DESTINATION $name) queryStatement ) )
			// SQLParser.g:342:5: KW_OVERWRITE KW_TARGET name= tableName KW_AS queryStatement
			{
			KW_OVERWRITE144=(Token)match(input,KW_OVERWRITE,FOLLOW_KW_OVERWRITE_in_overwriteTargetStatement1468);  
			stream_KW_OVERWRITE.add(KW_OVERWRITE144);

			KW_TARGET145=(Token)match(input,KW_TARGET,FOLLOW_KW_TARGET_in_overwriteTargetStatement1470);  
			stream_KW_TARGET.add(KW_TARGET145);

			pushFollow(FOLLOW_tableName_in_overwriteTargetStatement1474);
			name=tableName();
			state._fsp--;

			stream_tableName.add(name.getTree());
			KW_AS146=(Token)match(input,KW_AS,FOLLOW_KW_AS_in_overwriteTargetStatement1476);  
			stream_KW_AS.add(KW_AS146);

			pushFollow(FOLLOW_queryStatement_in_overwriteTargetStatement1478);
			queryStatement147=queryStatement();
			state._fsp--;

			stream_queryStatement.add(queryStatement147.getTree());
			// AST REWRITE
			// elements: queryStatement, name
			// token labels: 
			// rule labels: retval, name
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name",name!=null?name.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 343:6: -> ^( TOK_OVERWRITE_TARGET_STATEMENT ^( TOK_DESTINATION $name) queryStatement )
			{
				// SQLParser.g:343:8: ^( TOK_OVERWRITE_TARGET_STATEMENT ^( TOK_DESTINATION $name) queryStatement )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_OVERWRITE_TARGET_STATEMENT, "TOK_OVERWRITE_TARGET_STATEMENT"), root_1);
				// SQLParser.g:343:41: ^( TOK_DESTINATION $name)
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DESTINATION, "TOK_DESTINATION"), root_2);
				adaptor.addChild(root_2, stream_name.nextTree());
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, stream_queryStatement.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "overwriteTargetStatement"


	public static class createViewStatement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "createViewStatement"
	// SQLParser.g:346:1: createViewStatement : KW_CREATE KW_VIEW name= pipeName KW_AS queryStatement -> ^( TOK_CREATE_VIEW_STATEMENT ^( TOK_DESTINATION $name) queryStatement ) ;
	public final SQLParser.createViewStatement_return createViewStatement() throws RecognitionException {
		SQLParser.createViewStatement_return retval = new SQLParser.createViewStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_CREATE148=null;
		Token KW_VIEW149=null;
		Token KW_AS150=null;
		ParserRuleReturnScope name =null;
		ParserRuleReturnScope queryStatement151 =null;

		CommonTree KW_CREATE148_tree=null;
		CommonTree KW_VIEW149_tree=null;
		CommonTree KW_AS150_tree=null;
		RewriteRuleTokenStream stream_KW_AS=new RewriteRuleTokenStream(adaptor,"token KW_AS");
		RewriteRuleTokenStream stream_KW_CREATE=new RewriteRuleTokenStream(adaptor,"token KW_CREATE");
		RewriteRuleTokenStream stream_KW_VIEW=new RewriteRuleTokenStream(adaptor,"token KW_VIEW");
		RewriteRuleSubtreeStream stream_pipeName=new RewriteRuleSubtreeStream(adaptor,"rule pipeName");
		RewriteRuleSubtreeStream stream_queryStatement=new RewriteRuleSubtreeStream(adaptor,"rule queryStatement");

		try {
			// SQLParser.g:347:2: ( KW_CREATE KW_VIEW name= pipeName KW_AS queryStatement -> ^( TOK_CREATE_VIEW_STATEMENT ^( TOK_DESTINATION $name) queryStatement ) )
			// SQLParser.g:348:5: KW_CREATE KW_VIEW name= pipeName KW_AS queryStatement
			{
			KW_CREATE148=(Token)match(input,KW_CREATE,FOLLOW_KW_CREATE_in_createViewStatement1513);  
			stream_KW_CREATE.add(KW_CREATE148);

			KW_VIEW149=(Token)match(input,KW_VIEW,FOLLOW_KW_VIEW_in_createViewStatement1515);  
			stream_KW_VIEW.add(KW_VIEW149);

			pushFollow(FOLLOW_pipeName_in_createViewStatement1519);
			name=pipeName();
			state._fsp--;

			stream_pipeName.add(name.getTree());
			KW_AS150=(Token)match(input,KW_AS,FOLLOW_KW_AS_in_createViewStatement1521);  
			stream_KW_AS.add(KW_AS150);

			pushFollow(FOLLOW_queryStatement_in_createViewStatement1523);
			queryStatement151=queryStatement();
			state._fsp--;

			stream_queryStatement.add(queryStatement151.getTree());
			// AST REWRITE
			// elements: queryStatement, name
			// token labels: 
			// rule labels: retval, name
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name",name!=null?name.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 349:6: -> ^( TOK_CREATE_VIEW_STATEMENT ^( TOK_DESTINATION $name) queryStatement )
			{
				// SQLParser.g:349:8: ^( TOK_CREATE_VIEW_STATEMENT ^( TOK_DESTINATION $name) queryStatement )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_CREATE_VIEW_STATEMENT, "TOK_CREATE_VIEW_STATEMENT"), root_1);
				// SQLParser.g:349:36: ^( TOK_DESTINATION $name)
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DESTINATION, "TOK_DESTINATION"), root_2);
				adaptor.addChild(root_2, stream_name.nextTree());
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, stream_queryStatement.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "createViewStatement"


	public static class queryStatement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "queryStatement"
	// SQLParser.g:352:1: queryStatement : ( selectQuery | updateQuery | insertQuery | deleteQuery );
	public final SQLParser.queryStatement_return queryStatement() throws RecognitionException {
		SQLParser.queryStatement_return retval = new SQLParser.queryStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope selectQuery152 =null;
		ParserRuleReturnScope updateQuery153 =null;
		ParserRuleReturnScope insertQuery154 =null;
		ParserRuleReturnScope deleteQuery155 =null;


		try {
			// SQLParser.g:353:2: ( selectQuery | updateQuery | insertQuery | deleteQuery )
			int alt38=4;
			switch ( input.LA(1) ) {
			case KW_SELECT:
				{
				alt38=1;
				}
				break;
			case KW_UPDATE:
				{
				alt38=2;
				}
				break;
			case KW_INSERT:
				{
				alt38=3;
				}
				break;
			case KW_DELETE:
				{
				alt38=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 38, 0, input);
				throw nvae;
			}
			switch (alt38) {
				case 1 :
					// SQLParser.g:354:2: selectQuery
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_selectQuery_in_queryStatement1556);
					selectQuery152=selectQuery();
					state._fsp--;

					adaptor.addChild(root_0, selectQuery152.getTree());

					}
					break;
				case 2 :
					// SQLParser.g:354:16: updateQuery
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_updateQuery_in_queryStatement1560);
					updateQuery153=updateQuery();
					state._fsp--;

					adaptor.addChild(root_0, updateQuery153.getTree());

					}
					break;
				case 3 :
					// SQLParser.g:354:30: insertQuery
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insertQuery_in_queryStatement1564);
					insertQuery154=insertQuery();
					state._fsp--;

					adaptor.addChild(root_0, insertQuery154.getTree());

					}
					break;
				case 4 :
					// SQLParser.g:354:44: deleteQuery
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_deleteQuery_in_queryStatement1568);
					deleteQuery155=deleteQuery();
					state._fsp--;

					adaptor.addChild(root_0, deleteQuery155.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "queryStatement"


	public static class updateStatement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "updateStatement"
	// SQLParser.g:357:1: updateStatement : updateQuery -> ^( TOK_UPDATE_STATEMENT updateQuery ) ;
	public final SQLParser.updateStatement_return updateStatement() throws RecognitionException {
		SQLParser.updateStatement_return retval = new SQLParser.updateStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope updateQuery156 =null;

		RewriteRuleSubtreeStream stream_updateQuery=new RewriteRuleSubtreeStream(adaptor,"rule updateQuery");

		try {
			// SQLParser.g:358:2: ( updateQuery -> ^( TOK_UPDATE_STATEMENT updateQuery ) )
			// SQLParser.g:359:2: updateQuery
			{
			pushFollow(FOLLOW_updateQuery_in_updateStatement1580);
			updateQuery156=updateQuery();
			state._fsp--;

			stream_updateQuery.add(updateQuery156.getTree());
			// AST REWRITE
			// elements: updateQuery
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 360:3: -> ^( TOK_UPDATE_STATEMENT updateQuery )
			{
				// SQLParser.g:360:5: ^( TOK_UPDATE_STATEMENT updateQuery )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_UPDATE_STATEMENT, "TOK_UPDATE_STATEMENT"), root_1);
				adaptor.addChild(root_1, stream_updateQuery.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "updateStatement"


	public static class insertStatement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insertStatement"
	// SQLParser.g:363:1: insertStatement : insertQuery -> ^( TOK_INSERT_STATEMENT insertQuery ) ;
	public final SQLParser.insertStatement_return insertStatement() throws RecognitionException {
		SQLParser.insertStatement_return retval = new SQLParser.insertStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope insertQuery157 =null;

		RewriteRuleSubtreeStream stream_insertQuery=new RewriteRuleSubtreeStream(adaptor,"rule insertQuery");

		try {
			// SQLParser.g:364:2: ( insertQuery -> ^( TOK_INSERT_STATEMENT insertQuery ) )
			// SQLParser.g:365:2: insertQuery
			{
			pushFollow(FOLLOW_insertQuery_in_insertStatement1602);
			insertQuery157=insertQuery();
			state._fsp--;

			stream_insertQuery.add(insertQuery157.getTree());
			// AST REWRITE
			// elements: insertQuery
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 366:3: -> ^( TOK_INSERT_STATEMENT insertQuery )
			{
				// SQLParser.g:366:5: ^( TOK_INSERT_STATEMENT insertQuery )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_INSERT_STATEMENT, "TOK_INSERT_STATEMENT"), root_1);
				adaptor.addChild(root_1, stream_insertQuery.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insertStatement"


	public static class deleteStatement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "deleteStatement"
	// SQLParser.g:369:1: deleteStatement : deleteQuery -> ^( TOK_DELETE_STATEMENT deleteQuery ) ;
	public final SQLParser.deleteStatement_return deleteStatement() throws RecognitionException {
		SQLParser.deleteStatement_return retval = new SQLParser.deleteStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope deleteQuery158 =null;

		RewriteRuleSubtreeStream stream_deleteQuery=new RewriteRuleSubtreeStream(adaptor,"rule deleteQuery");

		try {
			// SQLParser.g:370:2: ( deleteQuery -> ^( TOK_DELETE_STATEMENT deleteQuery ) )
			// SQLParser.g:371:2: deleteQuery
			{
			pushFollow(FOLLOW_deleteQuery_in_deleteStatement1624);
			deleteQuery158=deleteQuery();
			state._fsp--;

			stream_deleteQuery.add(deleteQuery158.getTree());
			// AST REWRITE
			// elements: deleteQuery
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 372:3: -> ^( TOK_DELETE_STATEMENT deleteQuery )
			{
				// SQLParser.g:372:5: ^( TOK_DELETE_STATEMENT deleteQuery )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DELETE_STATEMENT, "TOK_DELETE_STATEMENT"), root_1);
				adaptor.addChild(root_1, stream_deleteQuery.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "deleteStatement"


	public static class selectQuery_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selectQuery"
	// SQLParser.g:375:1: selectQuery : selectClause fromClause ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( qualifyClause )? -> ^( TOK_SELECT_QUERY fromClause selectClause ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( qualifyClause )? ) ;
	public final SQLParser.selectQuery_return selectQuery() throws RecognitionException {
		SQLParser.selectQuery_return retval = new SQLParser.selectQuery_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope selectClause159 =null;
		ParserRuleReturnScope fromClause160 =null;
		ParserRuleReturnScope whereClause161 =null;
		ParserRuleReturnScope groupByClause162 =null;
		ParserRuleReturnScope havingClause163 =null;
		ParserRuleReturnScope orderByClause164 =null;
		ParserRuleReturnScope qualifyClause165 =null;

		RewriteRuleSubtreeStream stream_whereClause=new RewriteRuleSubtreeStream(adaptor,"rule whereClause");
		RewriteRuleSubtreeStream stream_orderByClause=new RewriteRuleSubtreeStream(adaptor,"rule orderByClause");
		RewriteRuleSubtreeStream stream_groupByClause=new RewriteRuleSubtreeStream(adaptor,"rule groupByClause");
		RewriteRuleSubtreeStream stream_havingClause=new RewriteRuleSubtreeStream(adaptor,"rule havingClause");
		RewriteRuleSubtreeStream stream_qualifyClause=new RewriteRuleSubtreeStream(adaptor,"rule qualifyClause");
		RewriteRuleSubtreeStream stream_selectClause=new RewriteRuleSubtreeStream(adaptor,"rule selectClause");
		RewriteRuleSubtreeStream stream_fromClause=new RewriteRuleSubtreeStream(adaptor,"rule fromClause");

		try {
			// SQLParser.g:376:2: ( selectClause fromClause ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( qualifyClause )? -> ^( TOK_SELECT_QUERY fromClause selectClause ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( qualifyClause )? ) )
			// SQLParser.g:377:2: selectClause fromClause ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( qualifyClause )?
			{
			pushFollow(FOLLOW_selectClause_in_selectQuery1646);
			selectClause159=selectClause();
			state._fsp--;

			stream_selectClause.add(selectClause159.getTree());
			pushFollow(FOLLOW_fromClause_in_selectQuery1649);
			fromClause160=fromClause();
			state._fsp--;

			stream_fromClause.add(fromClause160.getTree());
			// SQLParser.g:379:2: ( whereClause )?
			int alt39=2;
			int LA39_0 = input.LA(1);
			if ( (LA39_0==KW_WHERE) ) {
				alt39=1;
			}
			switch (alt39) {
				case 1 :
					// SQLParser.g:379:2: whereClause
					{
					pushFollow(FOLLOW_whereClause_in_selectQuery1652);
					whereClause161=whereClause();
					state._fsp--;

					stream_whereClause.add(whereClause161.getTree());
					}
					break;

			}

			// SQLParser.g:380:5: ( groupByClause )?
			int alt40=2;
			int LA40_0 = input.LA(1);
			if ( (LA40_0==KW_GROUP) ) {
				alt40=1;
			}
			switch (alt40) {
				case 1 :
					// SQLParser.g:380:5: groupByClause
					{
					pushFollow(FOLLOW_groupByClause_in_selectQuery1659);
					groupByClause162=groupByClause();
					state._fsp--;

					stream_groupByClause.add(groupByClause162.getTree());
					}
					break;

			}

			// SQLParser.g:381:5: ( havingClause )?
			int alt41=2;
			int LA41_0 = input.LA(1);
			if ( (LA41_0==KW_HAVING) ) {
				alt41=1;
			}
			switch (alt41) {
				case 1 :
					// SQLParser.g:381:5: havingClause
					{
					pushFollow(FOLLOW_havingClause_in_selectQuery1666);
					havingClause163=havingClause();
					state._fsp--;

					stream_havingClause.add(havingClause163.getTree());
					}
					break;

			}

			// SQLParser.g:382:5: ( orderByClause )?
			int alt42=2;
			int LA42_0 = input.LA(1);
			if ( (LA42_0==KW_ORDER) ) {
				alt42=1;
			}
			switch (alt42) {
				case 1 :
					// SQLParser.g:382:5: orderByClause
					{
					pushFollow(FOLLOW_orderByClause_in_selectQuery1673);
					orderByClause164=orderByClause();
					state._fsp--;

					stream_orderByClause.add(orderByClause164.getTree());
					}
					break;

			}

			// SQLParser.g:383:5: ( qualifyClause )?
			int alt43=2;
			int LA43_0 = input.LA(1);
			if ( (LA43_0==KW_QUALIFY) ) {
				alt43=1;
			}
			switch (alt43) {
				case 1 :
					// SQLParser.g:383:5: qualifyClause
					{
					pushFollow(FOLLOW_qualifyClause_in_selectQuery1680);
					qualifyClause165=qualifyClause();
					state._fsp--;

					stream_qualifyClause.add(qualifyClause165.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: orderByClause, selectClause, fromClause, havingClause, groupByClause, whereClause, qualifyClause
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 384:3: -> ^( TOK_SELECT_QUERY fromClause selectClause ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( qualifyClause )? )
			{
				// SQLParser.g:384:6: ^( TOK_SELECT_QUERY fromClause selectClause ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( qualifyClause )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_SELECT_QUERY, "TOK_SELECT_QUERY"), root_1);
				adaptor.addChild(root_1, stream_fromClause.nextTree());
				adaptor.addChild(root_1, stream_selectClause.nextTree());
				// SQLParser.g:384:49: ( whereClause )?
				if ( stream_whereClause.hasNext() ) {
					adaptor.addChild(root_1, stream_whereClause.nextTree());
				}
				stream_whereClause.reset();

				// SQLParser.g:384:62: ( groupByClause )?
				if ( stream_groupByClause.hasNext() ) {
					adaptor.addChild(root_1, stream_groupByClause.nextTree());
				}
				stream_groupByClause.reset();

				// SQLParser.g:384:77: ( havingClause )?
				if ( stream_havingClause.hasNext() ) {
					adaptor.addChild(root_1, stream_havingClause.nextTree());
				}
				stream_havingClause.reset();

				// SQLParser.g:384:91: ( orderByClause )?
				if ( stream_orderByClause.hasNext() ) {
					adaptor.addChild(root_1, stream_orderByClause.nextTree());
				}
				stream_orderByClause.reset();

				// SQLParser.g:384:106: ( qualifyClause )?
				if ( stream_qualifyClause.hasNext() ) {
					adaptor.addChild(root_1, stream_qualifyClause.nextTree());
				}
				stream_qualifyClause.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectQuery"


	public static class updateQuery_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "updateQuery"
	// SQLParser.g:387:1: updateQuery : KW_UPDATE name= updateTable fromClause KW_SET setItem ( COMMA setItem )* ( whereClause )? -> ^( TOK_UPDATE_QUERY fromClause ^( TOK_UPDATE $name ( setItem )+ ) ( whereClause )? ) ;
	public final SQLParser.updateQuery_return updateQuery() throws RecognitionException {
		SQLParser.updateQuery_return retval = new SQLParser.updateQuery_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_UPDATE166=null;
		Token KW_SET168=null;
		Token COMMA170=null;
		ParserRuleReturnScope name =null;
		ParserRuleReturnScope fromClause167 =null;
		ParserRuleReturnScope setItem169 =null;
		ParserRuleReturnScope setItem171 =null;
		ParserRuleReturnScope whereClause172 =null;

		CommonTree KW_UPDATE166_tree=null;
		CommonTree KW_SET168_tree=null;
		CommonTree COMMA170_tree=null;
		RewriteRuleTokenStream stream_KW_UPDATE=new RewriteRuleTokenStream(adaptor,"token KW_UPDATE");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_KW_SET=new RewriteRuleTokenStream(adaptor,"token KW_SET");
		RewriteRuleSubtreeStream stream_updateTable=new RewriteRuleSubtreeStream(adaptor,"rule updateTable");
		RewriteRuleSubtreeStream stream_whereClause=new RewriteRuleSubtreeStream(adaptor,"rule whereClause");
		RewriteRuleSubtreeStream stream_setItem=new RewriteRuleSubtreeStream(adaptor,"rule setItem");
		RewriteRuleSubtreeStream stream_fromClause=new RewriteRuleSubtreeStream(adaptor,"rule fromClause");

		try {
			// SQLParser.g:388:2: ( KW_UPDATE name= updateTable fromClause KW_SET setItem ( COMMA setItem )* ( whereClause )? -> ^( TOK_UPDATE_QUERY fromClause ^( TOK_UPDATE $name ( setItem )+ ) ( whereClause )? ) )
			// SQLParser.g:389:2: KW_UPDATE name= updateTable fromClause KW_SET setItem ( COMMA setItem )* ( whereClause )?
			{
			KW_UPDATE166=(Token)match(input,KW_UPDATE,FOLLOW_KW_UPDATE_in_updateQuery1725);  
			stream_KW_UPDATE.add(KW_UPDATE166);

			pushFollow(FOLLOW_updateTable_in_updateQuery1729);
			name=updateTable();
			state._fsp--;

			stream_updateTable.add(name.getTree());
			pushFollow(FOLLOW_fromClause_in_updateQuery1732);
			fromClause167=fromClause();
			state._fsp--;

			stream_fromClause.add(fromClause167.getTree());
			KW_SET168=(Token)match(input,KW_SET,FOLLOW_KW_SET_in_updateQuery1735);  
			stream_KW_SET.add(KW_SET168);

			pushFollow(FOLLOW_setItem_in_updateQuery1737);
			setItem169=setItem();
			state._fsp--;

			stream_setItem.add(setItem169.getTree());
			// SQLParser.g:391:17: ( COMMA setItem )*
			loop44:
			while (true) {
				int alt44=2;
				int LA44_0 = input.LA(1);
				if ( (LA44_0==COMMA) ) {
					alt44=1;
				}

				switch (alt44) {
				case 1 :
					// SQLParser.g:391:18: COMMA setItem
					{
					COMMA170=(Token)match(input,COMMA,FOLLOW_COMMA_in_updateQuery1740);  
					stream_COMMA.add(COMMA170);

					pushFollow(FOLLOW_setItem_in_updateQuery1743);
					setItem171=setItem();
					state._fsp--;

					stream_setItem.add(setItem171.getTree());
					}
					break;

				default :
					break loop44;
				}
			}

			// SQLParser.g:392:2: ( whereClause )?
			int alt45=2;
			int LA45_0 = input.LA(1);
			if ( (LA45_0==KW_WHERE) ) {
				alt45=1;
			}
			switch (alt45) {
				case 1 :
					// SQLParser.g:392:2: whereClause
					{
					pushFollow(FOLLOW_whereClause_in_updateQuery1749);
					whereClause172=whereClause();
					state._fsp--;

					stream_whereClause.add(whereClause172.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: setItem, whereClause, name, fromClause
			// token labels: 
			// rule labels: retval, name
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name",name!=null?name.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 393:3: -> ^( TOK_UPDATE_QUERY fromClause ^( TOK_UPDATE $name ( setItem )+ ) ( whereClause )? )
			{
				// SQLParser.g:393:5: ^( TOK_UPDATE_QUERY fromClause ^( TOK_UPDATE $name ( setItem )+ ) ( whereClause )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_UPDATE_QUERY, "TOK_UPDATE_QUERY"), root_1);
				adaptor.addChild(root_1, stream_fromClause.nextTree());
				// SQLParser.g:393:35: ^( TOK_UPDATE $name ( setItem )+ )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_UPDATE, "TOK_UPDATE"), root_2);
				adaptor.addChild(root_2, stream_name.nextTree());
				if ( !(stream_setItem.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_setItem.hasNext() ) {
					adaptor.addChild(root_2, stream_setItem.nextTree());
				}
				stream_setItem.reset();

				adaptor.addChild(root_1, root_2);
				}

				// SQLParser.g:393:65: ( whereClause )?
				if ( stream_whereClause.hasNext() ) {
					adaptor.addChild(root_1, stream_whereClause.nextTree());
				}
				stream_whereClause.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "updateQuery"


	public static class deleteQuery_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "deleteQuery"
	// SQLParser.g:396:1: deleteQuery : KW_DELETE (name= deleteTable )? fromClause ( whereClause )? -> ^( TOK_DELETE_QUERY fromClause ^( TOK_DELETE ( $name)? ) ( whereClause )? ) ;
	public final SQLParser.deleteQuery_return deleteQuery() throws RecognitionException {
		SQLParser.deleteQuery_return retval = new SQLParser.deleteQuery_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_DELETE173=null;
		ParserRuleReturnScope name =null;
		ParserRuleReturnScope fromClause174 =null;
		ParserRuleReturnScope whereClause175 =null;

		CommonTree KW_DELETE173_tree=null;
		RewriteRuleTokenStream stream_KW_DELETE=new RewriteRuleTokenStream(adaptor,"token KW_DELETE");
		RewriteRuleSubtreeStream stream_whereClause=new RewriteRuleSubtreeStream(adaptor,"rule whereClause");
		RewriteRuleSubtreeStream stream_deleteTable=new RewriteRuleSubtreeStream(adaptor,"rule deleteTable");
		RewriteRuleSubtreeStream stream_fromClause=new RewriteRuleSubtreeStream(adaptor,"rule fromClause");

		try {
			// SQLParser.g:397:2: ( KW_DELETE (name= deleteTable )? fromClause ( whereClause )? -> ^( TOK_DELETE_QUERY fromClause ^( TOK_DELETE ( $name)? ) ( whereClause )? ) )
			// SQLParser.g:398:2: KW_DELETE (name= deleteTable )? fromClause ( whereClause )?
			{
			KW_DELETE173=(Token)match(input,KW_DELETE,FOLLOW_KW_DELETE_in_deleteQuery1786);  
			stream_KW_DELETE.add(KW_DELETE173);

			// SQLParser.g:398:12: (name= deleteTable )?
			int alt46=2;
			int LA46_0 = input.LA(1);
			if ( (LA46_0==Identifier) ) {
				alt46=1;
			}
			switch (alt46) {
				case 1 :
					// SQLParser.g:398:13: name= deleteTable
					{
					pushFollow(FOLLOW_deleteTable_in_deleteQuery1791);
					name=deleteTable();
					state._fsp--;

					stream_deleteTable.add(name.getTree());
					}
					break;

			}

			pushFollow(FOLLOW_fromClause_in_deleteQuery1796);
			fromClause174=fromClause();
			state._fsp--;

			stream_fromClause.add(fromClause174.getTree());
			// SQLParser.g:400:2: ( whereClause )?
			int alt47=2;
			int LA47_0 = input.LA(1);
			if ( (LA47_0==KW_WHERE) ) {
				alt47=1;
			}
			switch (alt47) {
				case 1 :
					// SQLParser.g:400:2: whereClause
					{
					pushFollow(FOLLOW_whereClause_in_deleteQuery1799);
					whereClause175=whereClause();
					state._fsp--;

					stream_whereClause.add(whereClause175.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: fromClause, name, whereClause
			// token labels: 
			// rule labels: retval, name
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name",name!=null?name.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 401:3: -> ^( TOK_DELETE_QUERY fromClause ^( TOK_DELETE ( $name)? ) ( whereClause )? )
			{
				// SQLParser.g:401:5: ^( TOK_DELETE_QUERY fromClause ^( TOK_DELETE ( $name)? ) ( whereClause )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DELETE_QUERY, "TOK_DELETE_QUERY"), root_1);
				adaptor.addChild(root_1, stream_fromClause.nextTree());
				// SQLParser.g:401:35: ^( TOK_DELETE ( $name)? )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DELETE, "TOK_DELETE"), root_2);
				// SQLParser.g:401:49: ( $name)?
				if ( stream_name.hasNext() ) {
					adaptor.addChild(root_2, stream_name.nextTree());
				}
				stream_name.reset();

				adaptor.addChild(root_1, root_2);
				}

				// SQLParser.g:401:56: ( whereClause )?
				if ( stream_whereClause.hasNext() ) {
					adaptor.addChild(root_1, stream_whereClause.nextTree());
				}
				stream_whereClause.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "deleteQuery"


	public static class insertQuery_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insertQuery"
	// SQLParser.g:404:1: insertQuery : KW_INSERT ( KW_INTO )? name= tableName LPAREN identifier ( COMMA identifier )* RPAREN ( KW_AS )? selectClause fromClause ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( qualifyClause )? -> ^( TOK_INSERT_QUERY fromClause ^( TOK_INSERT $name ( identifier )+ ) selectClause ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( qualifyClause )? ) ;
	public final SQLParser.insertQuery_return insertQuery() throws RecognitionException {
		SQLParser.insertQuery_return retval = new SQLParser.insertQuery_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_INSERT176=null;
		Token KW_INTO177=null;
		Token LPAREN178=null;
		Token COMMA180=null;
		Token RPAREN182=null;
		Token KW_AS183=null;
		ParserRuleReturnScope name =null;
		ParserRuleReturnScope identifier179 =null;
		ParserRuleReturnScope identifier181 =null;
		ParserRuleReturnScope selectClause184 =null;
		ParserRuleReturnScope fromClause185 =null;
		ParserRuleReturnScope whereClause186 =null;
		ParserRuleReturnScope groupByClause187 =null;
		ParserRuleReturnScope havingClause188 =null;
		ParserRuleReturnScope orderByClause189 =null;
		ParserRuleReturnScope qualifyClause190 =null;

		CommonTree KW_INSERT176_tree=null;
		CommonTree KW_INTO177_tree=null;
		CommonTree LPAREN178_tree=null;
		CommonTree COMMA180_tree=null;
		CommonTree RPAREN182_tree=null;
		CommonTree KW_AS183_tree=null;
		RewriteRuleTokenStream stream_KW_AS=new RewriteRuleTokenStream(adaptor,"token KW_AS");
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_KW_INTO=new RewriteRuleTokenStream(adaptor,"token KW_INTO");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleTokenStream stream_KW_INSERT=new RewriteRuleTokenStream(adaptor,"token KW_INSERT");
		RewriteRuleSubtreeStream stream_whereClause=new RewriteRuleSubtreeStream(adaptor,"rule whereClause");
		RewriteRuleSubtreeStream stream_tableName=new RewriteRuleSubtreeStream(adaptor,"rule tableName");
		RewriteRuleSubtreeStream stream_orderByClause=new RewriteRuleSubtreeStream(adaptor,"rule orderByClause");
		RewriteRuleSubtreeStream stream_groupByClause=new RewriteRuleSubtreeStream(adaptor,"rule groupByClause");
		RewriteRuleSubtreeStream stream_havingClause=new RewriteRuleSubtreeStream(adaptor,"rule havingClause");
		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
		RewriteRuleSubtreeStream stream_qualifyClause=new RewriteRuleSubtreeStream(adaptor,"rule qualifyClause");
		RewriteRuleSubtreeStream stream_selectClause=new RewriteRuleSubtreeStream(adaptor,"rule selectClause");
		RewriteRuleSubtreeStream stream_fromClause=new RewriteRuleSubtreeStream(adaptor,"rule fromClause");

		try {
			// SQLParser.g:405:2: ( KW_INSERT ( KW_INTO )? name= tableName LPAREN identifier ( COMMA identifier )* RPAREN ( KW_AS )? selectClause fromClause ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( qualifyClause )? -> ^( TOK_INSERT_QUERY fromClause ^( TOK_INSERT $name ( identifier )+ ) selectClause ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( qualifyClause )? ) )
			// SQLParser.g:406:2: KW_INSERT ( KW_INTO )? name= tableName LPAREN identifier ( COMMA identifier )* RPAREN ( KW_AS )? selectClause fromClause ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( qualifyClause )?
			{
			KW_INSERT176=(Token)match(input,KW_INSERT,FOLLOW_KW_INSERT_in_insertQuery1833);  
			stream_KW_INSERT.add(KW_INSERT176);

			// SQLParser.g:406:12: ( KW_INTO )?
			int alt48=2;
			int LA48_0 = input.LA(1);
			if ( (LA48_0==KW_INTO) ) {
				alt48=1;
			}
			switch (alt48) {
				case 1 :
					// SQLParser.g:406:13: KW_INTO
					{
					KW_INTO177=(Token)match(input,KW_INTO,FOLLOW_KW_INTO_in_insertQuery1836);  
					stream_KW_INTO.add(KW_INTO177);

					}
					break;

			}

			pushFollow(FOLLOW_tableName_in_insertQuery1842);
			name=tableName();
			state._fsp--;

			stream_tableName.add(name.getTree());
			LPAREN178=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_insertQuery1844);  
			stream_LPAREN.add(LPAREN178);

			pushFollow(FOLLOW_identifier_in_insertQuery1847);
			identifier179=identifier();
			state._fsp--;

			stream_identifier.add(identifier179.getTree());
			// SQLParser.g:406:57: ( COMMA identifier )*
			loop49:
			while (true) {
				int alt49=2;
				int LA49_0 = input.LA(1);
				if ( (LA49_0==COMMA) ) {
					alt49=1;
				}

				switch (alt49) {
				case 1 :
					// SQLParser.g:406:58: COMMA identifier
					{
					COMMA180=(Token)match(input,COMMA,FOLLOW_COMMA_in_insertQuery1850);  
					stream_COMMA.add(COMMA180);

					pushFollow(FOLLOW_identifier_in_insertQuery1852);
					identifier181=identifier();
					state._fsp--;

					stream_identifier.add(identifier181.getTree());
					}
					break;

				default :
					break loop49;
				}
			}

			RPAREN182=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_insertQuery1857);  
			stream_RPAREN.add(RPAREN182);

			// SQLParser.g:406:85: ( KW_AS )?
			int alt50=2;
			int LA50_0 = input.LA(1);
			if ( (LA50_0==KW_AS) ) {
				alt50=1;
			}
			switch (alt50) {
				case 1 :
					// SQLParser.g:406:86: KW_AS
					{
					KW_AS183=(Token)match(input,KW_AS,FOLLOW_KW_AS_in_insertQuery1860);  
					stream_KW_AS.add(KW_AS183);

					}
					break;

			}

			pushFollow(FOLLOW_selectClause_in_insertQuery1865);
			selectClause184=selectClause();
			state._fsp--;

			stream_selectClause.add(selectClause184.getTree());
			pushFollow(FOLLOW_fromClause_in_insertQuery1869);
			fromClause185=fromClause();
			state._fsp--;

			stream_fromClause.add(fromClause185.getTree());
			// SQLParser.g:409:2: ( whereClause )?
			int alt51=2;
			int LA51_0 = input.LA(1);
			if ( (LA51_0==KW_WHERE) ) {
				alt51=1;
			}
			switch (alt51) {
				case 1 :
					// SQLParser.g:409:2: whereClause
					{
					pushFollow(FOLLOW_whereClause_in_insertQuery1873);
					whereClause186=whereClause();
					state._fsp--;

					stream_whereClause.add(whereClause186.getTree());
					}
					break;

			}

			// SQLParser.g:410:2: ( groupByClause )?
			int alt52=2;
			int LA52_0 = input.LA(1);
			if ( (LA52_0==KW_GROUP) ) {
				alt52=1;
			}
			switch (alt52) {
				case 1 :
					// SQLParser.g:410:2: groupByClause
					{
					pushFollow(FOLLOW_groupByClause_in_insertQuery1877);
					groupByClause187=groupByClause();
					state._fsp--;

					stream_groupByClause.add(groupByClause187.getTree());
					}
					break;

			}

			// SQLParser.g:411:2: ( havingClause )?
			int alt53=2;
			int LA53_0 = input.LA(1);
			if ( (LA53_0==KW_HAVING) ) {
				alt53=1;
			}
			switch (alt53) {
				case 1 :
					// SQLParser.g:411:2: havingClause
					{
					pushFollow(FOLLOW_havingClause_in_insertQuery1882);
					havingClause188=havingClause();
					state._fsp--;

					stream_havingClause.add(havingClause188.getTree());
					}
					break;

			}

			// SQLParser.g:412:2: ( orderByClause )?
			int alt54=2;
			int LA54_0 = input.LA(1);
			if ( (LA54_0==KW_ORDER) ) {
				alt54=1;
			}
			switch (alt54) {
				case 1 :
					// SQLParser.g:412:2: orderByClause
					{
					pushFollow(FOLLOW_orderByClause_in_insertQuery1886);
					orderByClause189=orderByClause();
					state._fsp--;

					stream_orderByClause.add(orderByClause189.getTree());
					}
					break;

			}

			// SQLParser.g:413:2: ( qualifyClause )?
			int alt55=2;
			int LA55_0 = input.LA(1);
			if ( (LA55_0==KW_QUALIFY) ) {
				alt55=1;
			}
			switch (alt55) {
				case 1 :
					// SQLParser.g:413:2: qualifyClause
					{
					pushFollow(FOLLOW_qualifyClause_in_insertQuery1890);
					qualifyClause190=qualifyClause();
					state._fsp--;

					stream_qualifyClause.add(qualifyClause190.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: fromClause, havingClause, qualifyClause, whereClause, selectClause, name, orderByClause, identifier, groupByClause
			// token labels: 
			// rule labels: retval, name
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_name=new RewriteRuleSubtreeStream(adaptor,"rule name",name!=null?name.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 414:3: -> ^( TOK_INSERT_QUERY fromClause ^( TOK_INSERT $name ( identifier )+ ) selectClause ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( qualifyClause )? )
			{
				// SQLParser.g:414:6: ^( TOK_INSERT_QUERY fromClause ^( TOK_INSERT $name ( identifier )+ ) selectClause ( whereClause )? ( groupByClause )? ( havingClause )? ( orderByClause )? ( qualifyClause )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_INSERT_QUERY, "TOK_INSERT_QUERY"), root_1);
				adaptor.addChild(root_1, stream_fromClause.nextTree());
				// SQLParser.g:414:36: ^( TOK_INSERT $name ( identifier )+ )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_INSERT, "TOK_INSERT"), root_2);
				adaptor.addChild(root_2, stream_name.nextTree());
				if ( !(stream_identifier.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_identifier.hasNext() ) {
					adaptor.addChild(root_2, stream_identifier.nextTree());
				}
				stream_identifier.reset();

				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, stream_selectClause.nextTree());
				// SQLParser.g:414:81: ( whereClause )?
				if ( stream_whereClause.hasNext() ) {
					adaptor.addChild(root_1, stream_whereClause.nextTree());
				}
				stream_whereClause.reset();

				// SQLParser.g:414:94: ( groupByClause )?
				if ( stream_groupByClause.hasNext() ) {
					adaptor.addChild(root_1, stream_groupByClause.nextTree());
				}
				stream_groupByClause.reset();

				// SQLParser.g:414:109: ( havingClause )?
				if ( stream_havingClause.hasNext() ) {
					adaptor.addChild(root_1, stream_havingClause.nextTree());
				}
				stream_havingClause.reset();

				// SQLParser.g:414:123: ( orderByClause )?
				if ( stream_orderByClause.hasNext() ) {
					adaptor.addChild(root_1, stream_orderByClause.nextTree());
				}
				stream_orderByClause.reset();

				// SQLParser.g:414:138: ( qualifyClause )?
				if ( stream_qualifyClause.hasNext() ) {
					adaptor.addChild(root_1, stream_qualifyClause.nextTree());
				}
				stream_qualifyClause.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insertQuery"


	public static class setItem_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "setItem"
	// SQLParser.g:417:1: setItem : identifier EQUAL expression -> ^( TOK_SETITEM identifier expression ) ;
	public final SQLParser.setItem_return setItem() throws RecognitionException {
		SQLParser.setItem_return retval = new SQLParser.setItem_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token EQUAL192=null;
		ParserRuleReturnScope identifier191 =null;
		ParserRuleReturnScope expression193 =null;

		CommonTree EQUAL192_tree=null;
		RewriteRuleTokenStream stream_EQUAL=new RewriteRuleTokenStream(adaptor,"token EQUAL");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// SQLParser.g:418:2: ( identifier EQUAL expression -> ^( TOK_SETITEM identifier expression ) )
			// SQLParser.g:419:2: identifier EQUAL expression
			{
			pushFollow(FOLLOW_identifier_in_setItem1941);
			identifier191=identifier();
			state._fsp--;

			stream_identifier.add(identifier191.getTree());
			EQUAL192=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_setItem1943);  
			stream_EQUAL.add(EQUAL192);

			pushFollow(FOLLOW_expression_in_setItem1945);
			expression193=expression();
			state._fsp--;

			stream_expression.add(expression193.getTree());
			// AST REWRITE
			// elements: expression, identifier
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 420:3: -> ^( TOK_SETITEM identifier expression )
			{
				// SQLParser.g:420:5: ^( TOK_SETITEM identifier expression )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_SETITEM, "TOK_SETITEM"), root_1);
				adaptor.addChild(root_1, stream_identifier.nextTree());
				adaptor.addChild(root_1, stream_expression.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "setItem"


	public static class pipeName_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "pipeName"
	// SQLParser.g:423:1: pipeName : pipe= identifier -> ^( TOK_VIEWNAME $pipe) ;
	public final SQLParser.pipeName_return pipeName() throws RecognitionException {
		SQLParser.pipeName_return retval = new SQLParser.pipeName_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope pipe =null;

		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// SQLParser.g:424:2: (pipe= identifier -> ^( TOK_VIEWNAME $pipe) )
			// SQLParser.g:425:2: pipe= identifier
			{
			pushFollow(FOLLOW_identifier_in_pipeName1971);
			pipe=identifier();
			state._fsp--;

			stream_identifier.add(pipe.getTree());
			// AST REWRITE
			// elements: pipe
			// token labels: 
			// rule labels: retval, pipe
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_pipe=new RewriteRuleSubtreeStream(adaptor,"rule pipe",pipe!=null?pipe.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 426:3: -> ^( TOK_VIEWNAME $pipe)
			{
				// SQLParser.g:426:6: ^( TOK_VIEWNAME $pipe)
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_VIEWNAME, "TOK_VIEWNAME"), root_1);
				adaptor.addChild(root_1, stream_pipe.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "pipeName"


	public static class havingClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "havingClause"
	// SQLParser.g:430:1: havingClause : KW_HAVING havingCondition -> ^( TOK_HAVING havingCondition ) ;
	public final SQLParser.havingClause_return havingClause() throws RecognitionException {
		SQLParser.havingClause_return retval = new SQLParser.havingClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_HAVING194=null;
		ParserRuleReturnScope havingCondition195 =null;

		CommonTree KW_HAVING194_tree=null;
		RewriteRuleTokenStream stream_KW_HAVING=new RewriteRuleTokenStream(adaptor,"token KW_HAVING");
		RewriteRuleSubtreeStream stream_havingCondition=new RewriteRuleSubtreeStream(adaptor,"rule havingCondition");

		try {
			// SQLParser.g:431:5: ( KW_HAVING havingCondition -> ^( TOK_HAVING havingCondition ) )
			// SQLParser.g:432:5: KW_HAVING havingCondition
			{
			KW_HAVING194=(Token)match(input,KW_HAVING,FOLLOW_KW_HAVING_in_havingClause2011);  
			stream_KW_HAVING.add(KW_HAVING194);

			pushFollow(FOLLOW_havingCondition_in_havingClause2013);
			havingCondition195=havingCondition();
			state._fsp--;

			stream_havingCondition.add(havingCondition195.getTree());
			// AST REWRITE
			// elements: havingCondition
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 432:31: -> ^( TOK_HAVING havingCondition )
			{
				// SQLParser.g:432:34: ^( TOK_HAVING havingCondition )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_HAVING, "TOK_HAVING"), root_1);
				adaptor.addChild(root_1, stream_havingCondition.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "havingClause"


	public static class havingCondition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "havingCondition"
	// SQLParser.g:435:1: havingCondition : expression ;
	public final SQLParser.havingCondition_return havingCondition() throws RecognitionException {
		SQLParser.havingCondition_return retval = new SQLParser.havingCondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope expression196 =null;


		try {
			// SQLParser.g:436:5: ( expression )
			// SQLParser.g:437:5: expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_expression_in_havingCondition2046);
			expression196=expression();
			state._fsp--;

			adaptor.addChild(root_0, expression196.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "havingCondition"


	public static class orderByClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "orderByClause"
	// SQLParser.g:440:1: orderByClause : KW_ORDER KW_BY columnRefOrder ( COMMA columnRefOrder )* -> ^( TOK_ORDERBY ( columnRefOrder )+ ) ;
	public final SQLParser.orderByClause_return orderByClause() throws RecognitionException {
		SQLParser.orderByClause_return retval = new SQLParser.orderByClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_ORDER197=null;
		Token KW_BY198=null;
		Token COMMA200=null;
		ParserRuleReturnScope columnRefOrder199 =null;
		ParserRuleReturnScope columnRefOrder201 =null;

		CommonTree KW_ORDER197_tree=null;
		CommonTree KW_BY198_tree=null;
		CommonTree COMMA200_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_KW_ORDER=new RewriteRuleTokenStream(adaptor,"token KW_ORDER");
		RewriteRuleTokenStream stream_KW_BY=new RewriteRuleTokenStream(adaptor,"token KW_BY");
		RewriteRuleSubtreeStream stream_columnRefOrder=new RewriteRuleSubtreeStream(adaptor,"rule columnRefOrder");

		try {
			// SQLParser.g:441:5: ( KW_ORDER KW_BY columnRefOrder ( COMMA columnRefOrder )* -> ^( TOK_ORDERBY ( columnRefOrder )+ ) )
			// SQLParser.g:442:5: KW_ORDER KW_BY columnRefOrder ( COMMA columnRefOrder )*
			{
			KW_ORDER197=(Token)match(input,KW_ORDER,FOLLOW_KW_ORDER_in_orderByClause2071);  
			stream_KW_ORDER.add(KW_ORDER197);

			KW_BY198=(Token)match(input,KW_BY,FOLLOW_KW_BY_in_orderByClause2073);  
			stream_KW_BY.add(KW_BY198);

			pushFollow(FOLLOW_columnRefOrder_in_orderByClause2075);
			columnRefOrder199=columnRefOrder();
			state._fsp--;

			stream_columnRefOrder.add(columnRefOrder199.getTree());
			// SQLParser.g:442:35: ( COMMA columnRefOrder )*
			loop56:
			while (true) {
				int alt56=2;
				int LA56_0 = input.LA(1);
				if ( (LA56_0==COMMA) ) {
					alt56=1;
				}

				switch (alt56) {
				case 1 :
					// SQLParser.g:442:37: COMMA columnRefOrder
					{
					COMMA200=(Token)match(input,COMMA,FOLLOW_COMMA_in_orderByClause2079);  
					stream_COMMA.add(COMMA200);

					pushFollow(FOLLOW_columnRefOrder_in_orderByClause2081);
					columnRefOrder201=columnRefOrder();
					state._fsp--;

					stream_columnRefOrder.add(columnRefOrder201.getTree());
					}
					break;

				default :
					break loop56;
				}
			}

			// AST REWRITE
			// elements: columnRefOrder
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 443:6: -> ^( TOK_ORDERBY ( columnRefOrder )+ )
			{
				// SQLParser.g:443:9: ^( TOK_ORDERBY ( columnRefOrder )+ )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_ORDERBY, "TOK_ORDERBY"), root_1);
				if ( !(stream_columnRefOrder.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_columnRefOrder.hasNext() ) {
					adaptor.addChild(root_1, stream_columnRefOrder.nextTree());
				}
				stream_columnRefOrder.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "orderByClause"


	public static class qualifyClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "qualifyClause"
	// SQLParser.g:446:1: qualifyClause : KW_QUALIFY expression -> ^( TOK_QUALIFY expression ) ;
	public final SQLParser.qualifyClause_return qualifyClause() throws RecognitionException {
		SQLParser.qualifyClause_return retval = new SQLParser.qualifyClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_QUALIFY202=null;
		ParserRuleReturnScope expression203 =null;

		CommonTree KW_QUALIFY202_tree=null;
		RewriteRuleTokenStream stream_KW_QUALIFY=new RewriteRuleTokenStream(adaptor,"token KW_QUALIFY");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");

		try {
			// SQLParser.g:447:2: ( KW_QUALIFY expression -> ^( TOK_QUALIFY expression ) )
			// SQLParser.g:448:2: KW_QUALIFY expression
			{
			KW_QUALIFY202=(Token)match(input,KW_QUALIFY,FOLLOW_KW_QUALIFY_in_qualifyClause2117);  
			stream_KW_QUALIFY.add(KW_QUALIFY202);

			pushFollow(FOLLOW_expression_in_qualifyClause2119);
			expression203=expression();
			state._fsp--;

			stream_expression.add(expression203.getTree());
			// AST REWRITE
			// elements: expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 449:3: -> ^( TOK_QUALIFY expression )
			{
				// SQLParser.g:449:5: ^( TOK_QUALIFY expression )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_QUALIFY, "TOK_QUALIFY"), root_1);
				adaptor.addChild(root_1, stream_expression.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "qualifyClause"


	public static class columnRefOrder_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "columnRefOrder"
	// SQLParser.g:453:1: columnRefOrder : expression (asc= KW_ASC |desc= KW_DESC )? -> {$desc == null}? ^( TOK_TABSORTCOLNAMEASC expression ) -> ^( TOK_TABSORTCOLNAMEDESC expression ) ;
	public final SQLParser.columnRefOrder_return columnRefOrder() throws RecognitionException {
		SQLParser.columnRefOrder_return retval = new SQLParser.columnRefOrder_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token asc=null;
		Token desc=null;
		ParserRuleReturnScope expression204 =null;

		CommonTree asc_tree=null;
		CommonTree desc_tree=null;
		RewriteRuleTokenStream stream_KW_DESC=new RewriteRuleTokenStream(adaptor,"token KW_DESC");
		RewriteRuleTokenStream stream_KW_ASC=new RewriteRuleTokenStream(adaptor,"token KW_ASC");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");

		try {
			// SQLParser.g:454:5: ( expression (asc= KW_ASC |desc= KW_DESC )? -> {$desc == null}? ^( TOK_TABSORTCOLNAMEASC expression ) -> ^( TOK_TABSORTCOLNAMEDESC expression ) )
			// SQLParser.g:455:5: expression (asc= KW_ASC |desc= KW_DESC )?
			{
			pushFollow(FOLLOW_expression_in_columnRefOrder2161);
			expression204=expression();
			state._fsp--;

			stream_expression.add(expression204.getTree());
			// SQLParser.g:455:16: (asc= KW_ASC |desc= KW_DESC )?
			int alt57=3;
			int LA57_0 = input.LA(1);
			if ( (LA57_0==KW_ASC) ) {
				alt57=1;
			}
			else if ( (LA57_0==KW_DESC) ) {
				alt57=2;
			}
			switch (alt57) {
				case 1 :
					// SQLParser.g:455:17: asc= KW_ASC
					{
					asc=(Token)match(input,KW_ASC,FOLLOW_KW_ASC_in_columnRefOrder2166);  
					stream_KW_ASC.add(asc);

					}
					break;
				case 2 :
					// SQLParser.g:455:30: desc= KW_DESC
					{
					desc=(Token)match(input,KW_DESC,FOLLOW_KW_DESC_in_columnRefOrder2172);  
					stream_KW_DESC.add(desc);

					}
					break;

			}

			// AST REWRITE
			// elements: expression, expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 456:5: -> {$desc == null}? ^( TOK_TABSORTCOLNAMEASC expression )
			if (desc == null) {
				// SQLParser.g:456:25: ^( TOK_TABSORTCOLNAMEASC expression )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_TABSORTCOLNAMEASC, "TOK_TABSORTCOLNAMEASC"), root_1);
				adaptor.addChild(root_1, stream_expression.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}

			else // 457:5: -> ^( TOK_TABSORTCOLNAMEDESC expression )
			{
				// SQLParser.g:457:25: ^( TOK_TABSORTCOLNAMEDESC expression )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_TABSORTCOLNAMEDESC, "TOK_TABSORTCOLNAMEDESC"), root_1);
				adaptor.addChild(root_1, stream_expression.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "columnRefOrder"


	public static class selectClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selectClause"
	// SQLParser.g:460:1: selectClause : KW_SELECT ( KW_ALL |dist= KW_DISTINCT )? selectList -> {$dist != null}? ^( TOK_SELECTDI selectList ) -> ^( TOK_SELECT selectList ) ;
	public final SQLParser.selectClause_return selectClause() throws RecognitionException {
		SQLParser.selectClause_return retval = new SQLParser.selectClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token dist=null;
		Token KW_SELECT205=null;
		Token KW_ALL206=null;
		ParserRuleReturnScope selectList207 =null;

		CommonTree dist_tree=null;
		CommonTree KW_SELECT205_tree=null;
		CommonTree KW_ALL206_tree=null;
		RewriteRuleTokenStream stream_KW_ALL=new RewriteRuleTokenStream(adaptor,"token KW_ALL");
		RewriteRuleTokenStream stream_KW_SELECT=new RewriteRuleTokenStream(adaptor,"token KW_SELECT");
		RewriteRuleTokenStream stream_KW_DISTINCT=new RewriteRuleTokenStream(adaptor,"token KW_DISTINCT");
		RewriteRuleSubtreeStream stream_selectList=new RewriteRuleSubtreeStream(adaptor,"rule selectList");

		try {
			// SQLParser.g:461:2: ( KW_SELECT ( KW_ALL |dist= KW_DISTINCT )? selectList -> {$dist != null}? ^( TOK_SELECTDI selectList ) -> ^( TOK_SELECT selectList ) )
			// SQLParser.g:462:2: KW_SELECT ( KW_ALL |dist= KW_DISTINCT )? selectList
			{
			KW_SELECT205=(Token)match(input,KW_SELECT,FOLLOW_KW_SELECT_in_selectClause2236);  
			stream_KW_SELECT.add(KW_SELECT205);

			// SQLParser.g:462:12: ( KW_ALL |dist= KW_DISTINCT )?
			int alt58=3;
			int LA58_0 = input.LA(1);
			if ( (LA58_0==KW_ALL) ) {
				alt58=1;
			}
			else if ( (LA58_0==KW_DISTINCT) ) {
				alt58=2;
			}
			switch (alt58) {
				case 1 :
					// SQLParser.g:462:13: KW_ALL
					{
					KW_ALL206=(Token)match(input,KW_ALL,FOLLOW_KW_ALL_in_selectClause2239);  
					stream_KW_ALL.add(KW_ALL206);

					}
					break;
				case 2 :
					// SQLParser.g:462:22: dist= KW_DISTINCT
					{
					dist=(Token)match(input,KW_DISTINCT,FOLLOW_KW_DISTINCT_in_selectClause2245);  
					stream_KW_DISTINCT.add(dist);

					}
					break;

			}

			pushFollow(FOLLOW_selectList_in_selectClause2249);
			selectList207=selectList();
			state._fsp--;

			stream_selectList.add(selectList207.getTree());
			// AST REWRITE
			// elements: selectList, selectList
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 463:2: -> {$dist != null}? ^( TOK_SELECTDI selectList )
			if (dist != null) {
				// SQLParser.g:463:22: ^( TOK_SELECTDI selectList )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_SELECTDI, "TOK_SELECTDI"), root_1);
				adaptor.addChild(root_1, stream_selectList.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}

			else // 464:2: -> ^( TOK_SELECT selectList )
			{
				// SQLParser.g:464:5: ^( TOK_SELECT selectList )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_SELECT, "TOK_SELECT"), root_1);
				adaptor.addChild(root_1, stream_selectList.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectClause"


	public static class groupByClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "groupByClause"
	// SQLParser.g:467:1: groupByClause : KW_GROUP KW_BY groupByExpression ( COMMA groupByExpression )* -> ^( TOK_GROUPBY ( groupByExpression )+ ) ;
	public final SQLParser.groupByClause_return groupByClause() throws RecognitionException {
		SQLParser.groupByClause_return retval = new SQLParser.groupByClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_GROUP208=null;
		Token KW_BY209=null;
		Token COMMA211=null;
		ParserRuleReturnScope groupByExpression210 =null;
		ParserRuleReturnScope groupByExpression212 =null;

		CommonTree KW_GROUP208_tree=null;
		CommonTree KW_BY209_tree=null;
		CommonTree COMMA211_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_KW_GROUP=new RewriteRuleTokenStream(adaptor,"token KW_GROUP");
		RewriteRuleTokenStream stream_KW_BY=new RewriteRuleTokenStream(adaptor,"token KW_BY");
		RewriteRuleSubtreeStream stream_groupByExpression=new RewriteRuleSubtreeStream(adaptor,"rule groupByExpression");

		try {
			// SQLParser.g:468:5: ( KW_GROUP KW_BY groupByExpression ( COMMA groupByExpression )* -> ^( TOK_GROUPBY ( groupByExpression )+ ) )
			// SQLParser.g:469:5: KW_GROUP KW_BY groupByExpression ( COMMA groupByExpression )*
			{
			KW_GROUP208=(Token)match(input,KW_GROUP,FOLLOW_KW_GROUP_in_groupByClause2295);  
			stream_KW_GROUP.add(KW_GROUP208);

			KW_BY209=(Token)match(input,KW_BY,FOLLOW_KW_BY_in_groupByClause2297);  
			stream_KW_BY.add(KW_BY209);

			pushFollow(FOLLOW_groupByExpression_in_groupByClause2303);
			groupByExpression210=groupByExpression();
			state._fsp--;

			stream_groupByExpression.add(groupByExpression210.getTree());
			// SQLParser.g:471:5: ( COMMA groupByExpression )*
			loop59:
			while (true) {
				int alt59=2;
				int LA59_0 = input.LA(1);
				if ( (LA59_0==COMMA) ) {
					alt59=1;
				}

				switch (alt59) {
				case 1 :
					// SQLParser.g:471:7: COMMA groupByExpression
					{
					COMMA211=(Token)match(input,COMMA,FOLLOW_COMMA_in_groupByClause2311);  
					stream_COMMA.add(COMMA211);

					pushFollow(FOLLOW_groupByExpression_in_groupByClause2313);
					groupByExpression212=groupByExpression();
					state._fsp--;

					stream_groupByExpression.add(groupByExpression212.getTree());
					}
					break;

				default :
					break loop59;
				}
			}

			// AST REWRITE
			// elements: groupByExpression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 472:6: -> ^( TOK_GROUPBY ( groupByExpression )+ )
			{
				// SQLParser.g:472:9: ^( TOK_GROUPBY ( groupByExpression )+ )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_GROUPBY, "TOK_GROUPBY"), root_1);
				if ( !(stream_groupByExpression.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_groupByExpression.hasNext() ) {
					adaptor.addChild(root_1, stream_groupByExpression.nextTree());
				}
				stream_groupByExpression.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "groupByClause"


	public static class groupByExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "groupByExpression"
	// SQLParser.g:475:1: groupByExpression : expression ;
	public final SQLParser.groupByExpression_return groupByExpression() throws RecognitionException {
		SQLParser.groupByExpression_return retval = new SQLParser.groupByExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope expression213 =null;


		try {
			// SQLParser.g:476:5: ( expression )
			// SQLParser.g:477:5: expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_expression_in_groupByExpression2355);
			expression213=expression();
			state._fsp--;

			adaptor.addChild(root_0, expression213.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "groupByExpression"


	public static class selectList_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selectList"
	// SQLParser.g:480:1: selectList : selectItem ( COMMA selectItem )* -> ( selectItem )+ ;
	public final SQLParser.selectList_return selectList() throws RecognitionException {
		SQLParser.selectList_return retval = new SQLParser.selectList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA215=null;
		ParserRuleReturnScope selectItem214 =null;
		ParserRuleReturnScope selectItem216 =null;

		CommonTree COMMA215_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_selectItem=new RewriteRuleSubtreeStream(adaptor,"rule selectItem");

		try {
			// SQLParser.g:481:2: ( selectItem ( COMMA selectItem )* -> ( selectItem )+ )
			// SQLParser.g:482:2: selectItem ( COMMA selectItem )*
			{
			pushFollow(FOLLOW_selectItem_in_selectList2370);
			selectItem214=selectItem();
			state._fsp--;

			stream_selectItem.add(selectItem214.getTree());
			// SQLParser.g:482:13: ( COMMA selectItem )*
			loop60:
			while (true) {
				int alt60=2;
				int LA60_0 = input.LA(1);
				if ( (LA60_0==COMMA) ) {
					alt60=1;
				}

				switch (alt60) {
				case 1 :
					// SQLParser.g:482:14: COMMA selectItem
					{
					COMMA215=(Token)match(input,COMMA,FOLLOW_COMMA_in_selectList2373);  
					stream_COMMA.add(COMMA215);

					pushFollow(FOLLOW_selectItem_in_selectList2376);
					selectItem216=selectItem();
					state._fsp--;

					stream_selectItem.add(selectItem216.getTree());
					}
					break;

				default :
					break loop60;
				}
			}

			// AST REWRITE
			// elements: selectItem
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 482:34: -> ( selectItem )+
			{
				if ( !(stream_selectItem.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_selectItem.hasNext() ) {
					adaptor.addChild(root_0, stream_selectItem.nextTree());
				}
				stream_selectItem.reset();

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectList"


	public static class selectItem_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selectItem"
	// SQLParser.g:485:1: selectItem : selectExpression ( ( KW_AS )? identifier )? -> ^( TOK_SELEXPR selectExpression ( identifier )* ) ;
	public final SQLParser.selectItem_return selectItem() throws RecognitionException {
		SQLParser.selectItem_return retval = new SQLParser.selectItem_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_AS218=null;
		ParserRuleReturnScope selectExpression217 =null;
		ParserRuleReturnScope identifier219 =null;

		CommonTree KW_AS218_tree=null;
		RewriteRuleTokenStream stream_KW_AS=new RewriteRuleTokenStream(adaptor,"token KW_AS");
		RewriteRuleSubtreeStream stream_selectExpression=new RewriteRuleSubtreeStream(adaptor,"rule selectExpression");
		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// SQLParser.g:486:2: ( selectExpression ( ( KW_AS )? identifier )? -> ^( TOK_SELEXPR selectExpression ( identifier )* ) )
			// SQLParser.g:487:2: selectExpression ( ( KW_AS )? identifier )?
			{
			pushFollow(FOLLOW_selectExpression_in_selectItem2395);
			selectExpression217=selectExpression();
			state._fsp--;

			stream_selectExpression.add(selectExpression217.getTree());
			// SQLParser.g:487:19: ( ( KW_AS )? identifier )?
			int alt62=2;
			int LA62_0 = input.LA(1);
			if ( (LA62_0==Identifier||LA62_0==KW_AS) ) {
				alt62=1;
			}
			switch (alt62) {
				case 1 :
					// SQLParser.g:487:20: ( KW_AS )? identifier
					{
					// SQLParser.g:487:20: ( KW_AS )?
					int alt61=2;
					int LA61_0 = input.LA(1);
					if ( (LA61_0==KW_AS) ) {
						alt61=1;
					}
					switch (alt61) {
						case 1 :
							// SQLParser.g:487:20: KW_AS
							{
							KW_AS218=(Token)match(input,KW_AS,FOLLOW_KW_AS_in_selectItem2398);  
							stream_KW_AS.add(KW_AS218);

							}
							break;

					}

					pushFollow(FOLLOW_identifier_in_selectItem2401);
					identifier219=identifier();
					state._fsp--;

					stream_identifier.add(identifier219.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: selectExpression, identifier
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 488:3: -> ^( TOK_SELEXPR selectExpression ( identifier )* )
			{
				// SQLParser.g:488:6: ^( TOK_SELEXPR selectExpression ( identifier )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_SELEXPR, "TOK_SELEXPR"), root_1);
				adaptor.addChild(root_1, stream_selectExpression.nextTree());
				// SQLParser.g:488:37: ( identifier )*
				while ( stream_identifier.hasNext() ) {
					adaptor.addChild(root_1, stream_identifier.nextTree());
				}
				stream_identifier.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectItem"


	public static class selectExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selectExpression"
	// SQLParser.g:491:1: selectExpression : ( expression | tableAllColumns );
	public final SQLParser.selectExpression_return selectExpression() throws RecognitionException {
		SQLParser.selectExpression_return retval = new SQLParser.selectExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope expression220 =null;
		ParserRuleReturnScope tableAllColumns221 =null;


		try {
			// SQLParser.g:492:5: ( expression | tableAllColumns )
			int alt63=2;
			switch ( input.LA(1) ) {
			case DecimalLiteral:
			case KW_CASE:
			case KW_CAST:
			case KW_DATE:
			case KW_FALSE:
			case KW_NOT:
			case KW_NULL:
			case KW_TRUE:
			case LPAREN:
			case MINUS:
			case Number:
			case PLUS:
			case StringLiteral:
			case TILDE:
				{
				alt63=1;
				}
				break;
			case Identifier:
				{
				int LA63_10 = input.LA(2);
				if ( (LA63_10==EOF||(LA63_10 >= AMPERSAND && LA63_10 <= BITWISEXOR)||LA63_10==COMMA||LA63_10==DIVIDE||(LA63_10 >= EQUAL && LA63_10 <= EQUAL_NS)||(LA63_10 >= GREATERTHAN && LA63_10 <= GREATERTHANOREQUALTO)||LA63_10==Identifier||LA63_10==KW_AND||LA63_10==KW_AS||LA63_10==KW_BETWEEN||LA63_10==KW_FROM||LA63_10==KW_IN||LA63_10==KW_IS||LA63_10==KW_LIKE||LA63_10==KW_NOT||LA63_10==KW_OR||(LA63_10 >= LESSTHAN && LA63_10 <= LSQUARE)||LA63_10==MINUS||(LA63_10 >= MOD && LA63_10 <= NOTEQUAL)||LA63_10==PLUS||LA63_10==RPAREN||LA63_10==STAR||LA63_10==DIV||(LA63_10 >= KW_REGEXP && LA63_10 <= KW_RLIKE)) ) {
					alt63=1;
				}
				else if ( (LA63_10==DOT) ) {
					int LA63_17 = input.LA(3);
					if ( (LA63_17==STAR) ) {
						alt63=2;
					}
					else if ( (LA63_17==Identifier) ) {
						alt63=1;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 63, 17, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 63, 10, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case STAR:
				{
				alt63=2;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 63, 0, input);
				throw nvae;
			}
			switch (alt63) {
				case 1 :
					// SQLParser.g:493:5: expression
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_expression_in_selectExpression2434);
					expression220=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression220.getTree());

					}
					break;
				case 2 :
					// SQLParser.g:493:18: tableAllColumns
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_tableAllColumns_in_selectExpression2438);
					tableAllColumns221=tableAllColumns();
					state._fsp--;

					adaptor.addChild(root_0, tableAllColumns221.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectExpression"


	public static class selectExpressionList_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selectExpressionList"
	// SQLParser.g:496:1: selectExpressionList : selectExpression ( COMMA selectExpression )* -> ^( TOK_EXPLIST ( selectExpression )+ ) ;
	public final SQLParser.selectExpressionList_return selectExpressionList() throws RecognitionException {
		SQLParser.selectExpressionList_return retval = new SQLParser.selectExpressionList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA223=null;
		ParserRuleReturnScope selectExpression222 =null;
		ParserRuleReturnScope selectExpression224 =null;

		CommonTree COMMA223_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_selectExpression=new RewriteRuleSubtreeStream(adaptor,"rule selectExpression");

		try {
			// SQLParser.g:497:5: ( selectExpression ( COMMA selectExpression )* -> ^( TOK_EXPLIST ( selectExpression )+ ) )
			// SQLParser.g:498:5: selectExpression ( COMMA selectExpression )*
			{
			pushFollow(FOLLOW_selectExpression_in_selectExpressionList2459);
			selectExpression222=selectExpression();
			state._fsp--;

			stream_selectExpression.add(selectExpression222.getTree());
			// SQLParser.g:498:22: ( COMMA selectExpression )*
			loop64:
			while (true) {
				int alt64=2;
				int LA64_0 = input.LA(1);
				if ( (LA64_0==COMMA) ) {
					alt64=1;
				}

				switch (alt64) {
				case 1 :
					// SQLParser.g:498:23: COMMA selectExpression
					{
					COMMA223=(Token)match(input,COMMA,FOLLOW_COMMA_in_selectExpressionList2462);  
					stream_COMMA.add(COMMA223);

					pushFollow(FOLLOW_selectExpression_in_selectExpressionList2464);
					selectExpression224=selectExpression();
					state._fsp--;

					stream_selectExpression.add(selectExpression224.getTree());
					}
					break;

				default :
					break loop64;
				}
			}

			// AST REWRITE
			// elements: selectExpression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 498:48: -> ^( TOK_EXPLIST ( selectExpression )+ )
			{
				// SQLParser.g:498:51: ^( TOK_EXPLIST ( selectExpression )+ )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_EXPLIST, "TOK_EXPLIST"), root_1);
				if ( !(stream_selectExpression.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_selectExpression.hasNext() ) {
					adaptor.addChild(root_1, stream_selectExpression.nextTree());
				}
				stream_selectExpression.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectExpressionList"


	public static class tableAllColumns_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "tableAllColumns"
	// SQLParser.g:501:1: tableAllColumns : ( STAR -> ^( TOK_ALLCOLREF ) | tableName DOT STAR -> ^( TOK_ALLCOLREF tableName ) );
	public final SQLParser.tableAllColumns_return tableAllColumns() throws RecognitionException {
		SQLParser.tableAllColumns_return retval = new SQLParser.tableAllColumns_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token STAR225=null;
		Token DOT227=null;
		Token STAR228=null;
		ParserRuleReturnScope tableName226 =null;

		CommonTree STAR225_tree=null;
		CommonTree DOT227_tree=null;
		CommonTree STAR228_tree=null;
		RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
		RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
		RewriteRuleSubtreeStream stream_tableName=new RewriteRuleSubtreeStream(adaptor,"rule tableName");

		try {
			// SQLParser.g:502:2: ( STAR -> ^( TOK_ALLCOLREF ) | tableName DOT STAR -> ^( TOK_ALLCOLREF tableName ) )
			int alt65=2;
			int LA65_0 = input.LA(1);
			if ( (LA65_0==STAR) ) {
				alt65=1;
			}
			else if ( (LA65_0==Identifier) ) {
				alt65=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 65, 0, input);
				throw nvae;
			}

			switch (alt65) {
				case 1 :
					// SQLParser.g:503:2: STAR
					{
					STAR225=(Token)match(input,STAR,FOLLOW_STAR_in_tableAllColumns2491);  
					stream_STAR.add(STAR225);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 504:3: -> ^( TOK_ALLCOLREF )
					{
						// SQLParser.g:504:6: ^( TOK_ALLCOLREF )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_ALLCOLREF, "TOK_ALLCOLREF"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// SQLParser.g:505:6: tableName DOT STAR
					{
					pushFollow(FOLLOW_tableName_in_tableAllColumns2506);
					tableName226=tableName();
					state._fsp--;

					stream_tableName.add(tableName226.getTree());
					DOT227=(Token)match(input,DOT,FOLLOW_DOT_in_tableAllColumns2508);  
					stream_DOT.add(DOT227);

					STAR228=(Token)match(input,STAR,FOLLOW_STAR_in_tableAllColumns2510);  
					stream_STAR.add(STAR228);

					// AST REWRITE
					// elements: tableName
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 506:3: -> ^( TOK_ALLCOLREF tableName )
					{
						// SQLParser.g:506:6: ^( TOK_ALLCOLREF tableName )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_ALLCOLREF, "TOK_ALLCOLREF"), root_1);
						adaptor.addChild(root_1, stream_tableName.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tableAllColumns"


	public static class tableOrColumn_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "tableOrColumn"
	// SQLParser.g:509:1: tableOrColumn : identifier -> ^( TOK_TABLE_OR_COL identifier ) ;
	public final SQLParser.tableOrColumn_return tableOrColumn() throws RecognitionException {
		SQLParser.tableOrColumn_return retval = new SQLParser.tableOrColumn_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope identifier229 =null;

		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// SQLParser.g:510:5: ( identifier -> ^( TOK_TABLE_OR_COL identifier ) )
			// SQLParser.g:511:5: identifier
			{
			pushFollow(FOLLOW_identifier_in_tableOrColumn2541);
			identifier229=identifier();
			state._fsp--;

			stream_identifier.add(identifier229.getTree());
			// AST REWRITE
			// elements: identifier
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 511:16: -> ^( TOK_TABLE_OR_COL identifier )
			{
				// SQLParser.g:511:19: ^( TOK_TABLE_OR_COL identifier )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_TABLE_OR_COL, "TOK_TABLE_OR_COL"), root_1);
				adaptor.addChild(root_1, stream_identifier.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tableOrColumn"


	public static class expressionList_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expressionList"
	// SQLParser.g:514:1: expressionList : expression ( COMMA expression )* -> ^( TOK_EXPLIST ( expression )+ ) ;
	public final SQLParser.expressionList_return expressionList() throws RecognitionException {
		SQLParser.expressionList_return retval = new SQLParser.expressionList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA231=null;
		ParserRuleReturnScope expression230 =null;
		ParserRuleReturnScope expression232 =null;

		CommonTree COMMA231_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");

		try {
			// SQLParser.g:515:5: ( expression ( COMMA expression )* -> ^( TOK_EXPLIST ( expression )+ ) )
			// SQLParser.g:516:5: expression ( COMMA expression )*
			{
			pushFollow(FOLLOW_expression_in_expressionList2570);
			expression230=expression();
			state._fsp--;

			stream_expression.add(expression230.getTree());
			// SQLParser.g:516:16: ( COMMA expression )*
			loop66:
			while (true) {
				int alt66=2;
				int LA66_0 = input.LA(1);
				if ( (LA66_0==COMMA) ) {
					alt66=1;
				}

				switch (alt66) {
				case 1 :
					// SQLParser.g:516:17: COMMA expression
					{
					COMMA231=(Token)match(input,COMMA,FOLLOW_COMMA_in_expressionList2573);  
					stream_COMMA.add(COMMA231);

					pushFollow(FOLLOW_expression_in_expressionList2575);
					expression232=expression();
					state._fsp--;

					stream_expression.add(expression232.getTree());
					}
					break;

				default :
					break loop66;
				}
			}

			// AST REWRITE
			// elements: expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 516:36: -> ^( TOK_EXPLIST ( expression )+ )
			{
				// SQLParser.g:516:39: ^( TOK_EXPLIST ( expression )+ )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_EXPLIST, "TOK_EXPLIST"), root_1);
				if ( !(stream_expression.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_expression.hasNext() ) {
					adaptor.addChild(root_1, stream_expression.nextTree());
				}
				stream_expression.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expressionList"


	public static class fromClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "fromClause"
	// SQLParser.g:519:1: fromClause : KW_FROM joinSource -> ^( TOK_FROM joinSource ) ;
	public final SQLParser.fromClause_return fromClause() throws RecognitionException {
		SQLParser.fromClause_return retval = new SQLParser.fromClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_FROM233=null;
		ParserRuleReturnScope joinSource234 =null;

		CommonTree KW_FROM233_tree=null;
		RewriteRuleTokenStream stream_KW_FROM=new RewriteRuleTokenStream(adaptor,"token KW_FROM");
		RewriteRuleSubtreeStream stream_joinSource=new RewriteRuleSubtreeStream(adaptor,"rule joinSource");

		try {
			// SQLParser.g:520:5: ( KW_FROM joinSource -> ^( TOK_FROM joinSource ) )
			// SQLParser.g:521:5: KW_FROM joinSource
			{
			KW_FROM233=(Token)match(input,KW_FROM,FOLLOW_KW_FROM_in_fromClause2607);  
			stream_KW_FROM.add(KW_FROM233);

			pushFollow(FOLLOW_joinSource_in_fromClause2609);
			joinSource234=joinSource();
			state._fsp--;

			stream_joinSource.add(joinSource234.getTree());
			// AST REWRITE
			// elements: joinSource
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 521:24: -> ^( TOK_FROM joinSource )
			{
				// SQLParser.g:521:27: ^( TOK_FROM joinSource )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_FROM, "TOK_FROM"), root_1);
				adaptor.addChild(root_1, stream_joinSource.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fromClause"


	public static class joinSource_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "joinSource"
	// SQLParser.g:524:1: joinSource : fromSource ( joinToken ^ fromSource ( KW_ON ! expression )? )* ;
	public final SQLParser.joinSource_return joinSource() throws RecognitionException {
		SQLParser.joinSource_return retval = new SQLParser.joinSource_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_ON238=null;
		ParserRuleReturnScope fromSource235 =null;
		ParserRuleReturnScope joinToken236 =null;
		ParserRuleReturnScope fromSource237 =null;
		ParserRuleReturnScope expression239 =null;

		CommonTree KW_ON238_tree=null;

		try {
			// SQLParser.g:525:5: ( fromSource ( joinToken ^ fromSource ( KW_ON ! expression )? )* )
			// SQLParser.g:525:7: fromSource ( joinToken ^ fromSource ( KW_ON ! expression )? )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_fromSource_in_joinSource2634);
			fromSource235=fromSource();
			state._fsp--;

			adaptor.addChild(root_0, fromSource235.getTree());

			// SQLParser.g:525:18: ( joinToken ^ fromSource ( KW_ON ! expression )? )*
			loop68:
			while (true) {
				int alt68=2;
				int LA68_0 = input.LA(1);
				if ( (LA68_0==COMMA||LA68_0==KW_CROSS||LA68_0==KW_FULL||LA68_0==KW_INNER||(LA68_0 >= KW_JOIN && LA68_0 <= KW_LEFT)||LA68_0==KW_RIGHT) ) {
					alt68=1;
				}

				switch (alt68) {
				case 1 :
					// SQLParser.g:525:20: joinToken ^ fromSource ( KW_ON ! expression )?
					{
					pushFollow(FOLLOW_joinToken_in_joinSource2638);
					joinToken236=joinToken();
					state._fsp--;

					root_0 = (CommonTree)adaptor.becomeRoot(joinToken236.getTree(), root_0);
					pushFollow(FOLLOW_fromSource_in_joinSource2641);
					fromSource237=fromSource();
					state._fsp--;

					adaptor.addChild(root_0, fromSource237.getTree());

					// SQLParser.g:525:42: ( KW_ON ! expression )?
					int alt67=2;
					int LA67_0 = input.LA(1);
					if ( (LA67_0==KW_ON) ) {
						alt67=1;
					}
					switch (alt67) {
						case 1 :
							// SQLParser.g:525:43: KW_ON ! expression
							{
							KW_ON238=(Token)match(input,KW_ON,FOLLOW_KW_ON_in_joinSource2644); 
							pushFollow(FOLLOW_expression_in_joinSource2647);
							expression239=expression();
							state._fsp--;

							adaptor.addChild(root_0, expression239.getTree());

							}
							break;

					}

					}
					break;

				default :
					break loop68;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "joinSource"


	public static class joinToken_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "joinToken"
	// SQLParser.g:528:1: joinToken : ( KW_JOIN -> TOK_JOIN | KW_INNER KW_JOIN -> TOK_JOIN | COMMA -> TOK_JOIN | KW_CROSS KW_JOIN -> TOK_CROSSJOIN | KW_LEFT ( KW_OUTER )? KW_JOIN -> TOK_LEFTOUTERJOIN | KW_RIGHT ( KW_OUTER )? KW_JOIN -> TOK_RIGHTOUTERJOIN | KW_FULL ( KW_OUTER )? KW_JOIN -> TOK_FULLOUTERJOIN | KW_LEFT KW_SEMI KW_JOIN -> TOK_LEFTSEMIJOIN );
	public final SQLParser.joinToken_return joinToken() throws RecognitionException {
		SQLParser.joinToken_return retval = new SQLParser.joinToken_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_JOIN240=null;
		Token KW_INNER241=null;
		Token KW_JOIN242=null;
		Token COMMA243=null;
		Token KW_CROSS244=null;
		Token KW_JOIN245=null;
		Token KW_LEFT246=null;
		Token KW_OUTER247=null;
		Token KW_JOIN248=null;
		Token KW_RIGHT249=null;
		Token KW_OUTER250=null;
		Token KW_JOIN251=null;
		Token KW_FULL252=null;
		Token KW_OUTER253=null;
		Token KW_JOIN254=null;
		Token KW_LEFT255=null;
		Token KW_SEMI256=null;
		Token KW_JOIN257=null;

		CommonTree KW_JOIN240_tree=null;
		CommonTree KW_INNER241_tree=null;
		CommonTree KW_JOIN242_tree=null;
		CommonTree COMMA243_tree=null;
		CommonTree KW_CROSS244_tree=null;
		CommonTree KW_JOIN245_tree=null;
		CommonTree KW_LEFT246_tree=null;
		CommonTree KW_OUTER247_tree=null;
		CommonTree KW_JOIN248_tree=null;
		CommonTree KW_RIGHT249_tree=null;
		CommonTree KW_OUTER250_tree=null;
		CommonTree KW_JOIN251_tree=null;
		CommonTree KW_FULL252_tree=null;
		CommonTree KW_OUTER253_tree=null;
		CommonTree KW_JOIN254_tree=null;
		CommonTree KW_LEFT255_tree=null;
		CommonTree KW_SEMI256_tree=null;
		CommonTree KW_JOIN257_tree=null;
		RewriteRuleTokenStream stream_KW_INNER=new RewriteRuleTokenStream(adaptor,"token KW_INNER");
		RewriteRuleTokenStream stream_KW_RIGHT=new RewriteRuleTokenStream(adaptor,"token KW_RIGHT");
		RewriteRuleTokenStream stream_KW_OUTER=new RewriteRuleTokenStream(adaptor,"token KW_OUTER");
		RewriteRuleTokenStream stream_KW_JOIN=new RewriteRuleTokenStream(adaptor,"token KW_JOIN");
		RewriteRuleTokenStream stream_KW_LEFT=new RewriteRuleTokenStream(adaptor,"token KW_LEFT");
		RewriteRuleTokenStream stream_KW_CROSS=new RewriteRuleTokenStream(adaptor,"token KW_CROSS");
		RewriteRuleTokenStream stream_KW_SEMI=new RewriteRuleTokenStream(adaptor,"token KW_SEMI");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_KW_FULL=new RewriteRuleTokenStream(adaptor,"token KW_FULL");

		try {
			// SQLParser.g:529:5: ( KW_JOIN -> TOK_JOIN | KW_INNER KW_JOIN -> TOK_JOIN | COMMA -> TOK_JOIN | KW_CROSS KW_JOIN -> TOK_CROSSJOIN | KW_LEFT ( KW_OUTER )? KW_JOIN -> TOK_LEFTOUTERJOIN | KW_RIGHT ( KW_OUTER )? KW_JOIN -> TOK_RIGHTOUTERJOIN | KW_FULL ( KW_OUTER )? KW_JOIN -> TOK_FULLOUTERJOIN | KW_LEFT KW_SEMI KW_JOIN -> TOK_LEFTSEMIJOIN )
			int alt72=8;
			switch ( input.LA(1) ) {
			case KW_JOIN:
				{
				alt72=1;
				}
				break;
			case KW_INNER:
				{
				alt72=2;
				}
				break;
			case COMMA:
				{
				alt72=3;
				}
				break;
			case KW_CROSS:
				{
				alt72=4;
				}
				break;
			case KW_LEFT:
				{
				int LA72_5 = input.LA(2);
				if ( (LA72_5==KW_SEMI) ) {
					alt72=8;
				}
				else if ( (LA72_5==KW_JOIN||LA72_5==KW_OUTER) ) {
					alt72=5;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 72, 5, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case KW_RIGHT:
				{
				alt72=6;
				}
				break;
			case KW_FULL:
				{
				alt72=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 72, 0, input);
				throw nvae;
			}
			switch (alt72) {
				case 1 :
					// SQLParser.g:530:7: KW_JOIN
					{
					KW_JOIN240=(Token)match(input,KW_JOIN,FOLLOW_KW_JOIN_in_joinToken2675);  
					stream_KW_JOIN.add(KW_JOIN240);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 530:36: -> TOK_JOIN
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_JOIN, "TOK_JOIN"));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// SQLParser.g:531:7: KW_INNER KW_JOIN
					{
					KW_INNER241=(Token)match(input,KW_INNER,FOLLOW_KW_INNER_in_joinToken2708);  
					stream_KW_INNER.add(KW_INNER241);

					KW_JOIN242=(Token)match(input,KW_JOIN,FOLLOW_KW_JOIN_in_joinToken2710);  
					stream_KW_JOIN.add(KW_JOIN242);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 531:36: -> TOK_JOIN
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_JOIN, "TOK_JOIN"));
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// SQLParser.g:532:7: COMMA
					{
					COMMA243=(Token)match(input,COMMA,FOLLOW_COMMA_in_joinToken2734);  
					stream_COMMA.add(COMMA243);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 532:21: -> TOK_JOIN
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_JOIN, "TOK_JOIN"));
					}


					retval.tree = root_0;

					}
					break;
				case 4 :
					// SQLParser.g:533:7: KW_CROSS KW_JOIN
					{
					KW_CROSS244=(Token)match(input,KW_CROSS,FOLLOW_KW_CROSS_in_joinToken2754);  
					stream_KW_CROSS.add(KW_CROSS244);

					KW_JOIN245=(Token)match(input,KW_JOIN,FOLLOW_KW_JOIN_in_joinToken2756);  
					stream_KW_JOIN.add(KW_JOIN245);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 533:36: -> TOK_CROSSJOIN
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_CROSSJOIN, "TOK_CROSSJOIN"));
					}


					retval.tree = root_0;

					}
					break;
				case 5 :
					// SQLParser.g:534:7: KW_LEFT ( KW_OUTER )? KW_JOIN
					{
					KW_LEFT246=(Token)match(input,KW_LEFT,FOLLOW_KW_LEFT_in_joinToken2780);  
					stream_KW_LEFT.add(KW_LEFT246);

					// SQLParser.g:534:16: ( KW_OUTER )?
					int alt69=2;
					int LA69_0 = input.LA(1);
					if ( (LA69_0==KW_OUTER) ) {
						alt69=1;
					}
					switch (alt69) {
						case 1 :
							// SQLParser.g:534:17: KW_OUTER
							{
							KW_OUTER247=(Token)match(input,KW_OUTER,FOLLOW_KW_OUTER_in_joinToken2784);  
							stream_KW_OUTER.add(KW_OUTER247);

							}
							break;

					}

					KW_JOIN248=(Token)match(input,KW_JOIN,FOLLOW_KW_JOIN_in_joinToken2788);  
					stream_KW_JOIN.add(KW_JOIN248);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 534:36: -> TOK_LEFTOUTERJOIN
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_LEFTOUTERJOIN, "TOK_LEFTOUTERJOIN"));
					}


					retval.tree = root_0;

					}
					break;
				case 6 :
					// SQLParser.g:535:7: KW_RIGHT ( KW_OUTER )? KW_JOIN
					{
					KW_RIGHT249=(Token)match(input,KW_RIGHT,FOLLOW_KW_RIGHT_in_joinToken2800);  
					stream_KW_RIGHT.add(KW_RIGHT249);

					// SQLParser.g:535:16: ( KW_OUTER )?
					int alt70=2;
					int LA70_0 = input.LA(1);
					if ( (LA70_0==KW_OUTER) ) {
						alt70=1;
					}
					switch (alt70) {
						case 1 :
							// SQLParser.g:535:17: KW_OUTER
							{
							KW_OUTER250=(Token)match(input,KW_OUTER,FOLLOW_KW_OUTER_in_joinToken2803);  
							stream_KW_OUTER.add(KW_OUTER250);

							}
							break;

					}

					KW_JOIN251=(Token)match(input,KW_JOIN,FOLLOW_KW_JOIN_in_joinToken2807);  
					stream_KW_JOIN.add(KW_JOIN251);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 535:36: -> TOK_RIGHTOUTERJOIN
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_RIGHTOUTERJOIN, "TOK_RIGHTOUTERJOIN"));
					}


					retval.tree = root_0;

					}
					break;
				case 7 :
					// SQLParser.g:536:7: KW_FULL ( KW_OUTER )? KW_JOIN
					{
					KW_FULL252=(Token)match(input,KW_FULL,FOLLOW_KW_FULL_in_joinToken2819);  
					stream_KW_FULL.add(KW_FULL252);

					// SQLParser.g:536:16: ( KW_OUTER )?
					int alt71=2;
					int LA71_0 = input.LA(1);
					if ( (LA71_0==KW_OUTER) ) {
						alt71=1;
					}
					switch (alt71) {
						case 1 :
							// SQLParser.g:536:17: KW_OUTER
							{
							KW_OUTER253=(Token)match(input,KW_OUTER,FOLLOW_KW_OUTER_in_joinToken2823);  
							stream_KW_OUTER.add(KW_OUTER253);

							}
							break;

					}

					KW_JOIN254=(Token)match(input,KW_JOIN,FOLLOW_KW_JOIN_in_joinToken2827);  
					stream_KW_JOIN.add(KW_JOIN254);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 536:36: -> TOK_FULLOUTERJOIN
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_FULLOUTERJOIN, "TOK_FULLOUTERJOIN"));
					}


					retval.tree = root_0;

					}
					break;
				case 8 :
					// SQLParser.g:537:7: KW_LEFT KW_SEMI KW_JOIN
					{
					KW_LEFT255=(Token)match(input,KW_LEFT,FOLLOW_KW_LEFT_in_joinToken2839);  
					stream_KW_LEFT.add(KW_LEFT255);

					KW_SEMI256=(Token)match(input,KW_SEMI,FOLLOW_KW_SEMI_in_joinToken2841);  
					stream_KW_SEMI.add(KW_SEMI256);

					KW_JOIN257=(Token)match(input,KW_JOIN,FOLLOW_KW_JOIN_in_joinToken2843);  
					stream_KW_JOIN.add(KW_JOIN257);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 537:36: -> TOK_LEFTSEMIJOIN
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_LEFTSEMIJOIN, "TOK_LEFTSEMIJOIN"));
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "joinToken"


	public static class fromSource_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "fromSource"
	// SQLParser.g:540:1: fromSource : ( tableSource | subQuerySource );
	public final SQLParser.fromSource_return fromSource() throws RecognitionException {
		SQLParser.fromSource_return retval = new SQLParser.fromSource_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope tableSource258 =null;
		ParserRuleReturnScope subQuerySource259 =null;


		try {
			// SQLParser.g:541:5: ( tableSource | subQuerySource )
			int alt73=2;
			int LA73_0 = input.LA(1);
			if ( (LA73_0==Identifier) ) {
				alt73=1;
			}
			else if ( (LA73_0==LPAREN) ) {
				alt73=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 73, 0, input);
				throw nvae;
			}

			switch (alt73) {
				case 1 :
					// SQLParser.g:542:5: tableSource
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_tableSource_in_fromSource2873);
					tableSource258=tableSource();
					state._fsp--;

					adaptor.addChild(root_0, tableSource258.getTree());

					}
					break;
				case 2 :
					// SQLParser.g:542:19: subQuerySource
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_subQuerySource_in_fromSource2877);
					subQuerySource259=subQuerySource();
					state._fsp--;

					adaptor.addChild(root_0, subQuerySource259.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fromSource"


	public static class tableSource_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "tableSource"
	// SQLParser.g:545:1: tableSource : tabname= tableName ( ( KW_AS )? alias= identifier )? -> ^( TOK_TABREF $tabname ( $alias)? ) ;
	public final SQLParser.tableSource_return tableSource() throws RecognitionException {
		SQLParser.tableSource_return retval = new SQLParser.tableSource_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_AS260=null;
		ParserRuleReturnScope tabname =null;
		ParserRuleReturnScope alias =null;

		CommonTree KW_AS260_tree=null;
		RewriteRuleTokenStream stream_KW_AS=new RewriteRuleTokenStream(adaptor,"token KW_AS");
		RewriteRuleSubtreeStream stream_tableName=new RewriteRuleSubtreeStream(adaptor,"rule tableName");
		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// SQLParser.g:546:5: (tabname= tableName ( ( KW_AS )? alias= identifier )? -> ^( TOK_TABREF $tabname ( $alias)? ) )
			// SQLParser.g:546:7: tabname= tableName ( ( KW_AS )? alias= identifier )?
			{
			pushFollow(FOLLOW_tableName_in_tableSource2896);
			tabname=tableName();
			state._fsp--;

			stream_tableName.add(tabname.getTree());
			// SQLParser.g:546:25: ( ( KW_AS )? alias= identifier )?
			int alt75=2;
			int LA75_0 = input.LA(1);
			if ( (LA75_0==Identifier||LA75_0==KW_AS) ) {
				alt75=1;
			}
			switch (alt75) {
				case 1 :
					// SQLParser.g:546:26: ( KW_AS )? alias= identifier
					{
					// SQLParser.g:546:26: ( KW_AS )?
					int alt74=2;
					int LA74_0 = input.LA(1);
					if ( (LA74_0==KW_AS) ) {
						alt74=1;
					}
					switch (alt74) {
						case 1 :
							// SQLParser.g:546:26: KW_AS
							{
							KW_AS260=(Token)match(input,KW_AS,FOLLOW_KW_AS_in_tableSource2899);  
							stream_KW_AS.add(KW_AS260);

							}
							break;

					}

					pushFollow(FOLLOW_identifier_in_tableSource2904);
					alias=identifier();
					state._fsp--;

					stream_identifier.add(alias.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: tabname, alias
			// token labels: 
			// rule labels: retval, alias, tabname
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_alias=new RewriteRuleSubtreeStream(adaptor,"rule alias",alias!=null?alias.getTree():null);
			RewriteRuleSubtreeStream stream_tabname=new RewriteRuleSubtreeStream(adaptor,"rule tabname",tabname!=null?tabname.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 547:3: -> ^( TOK_TABREF $tabname ( $alias)? )
			{
				// SQLParser.g:547:6: ^( TOK_TABREF $tabname ( $alias)? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_TABREF, "TOK_TABREF"), root_1);
				adaptor.addChild(root_1, stream_tabname.nextTree());
				// SQLParser.g:547:30: ( $alias)?
				if ( stream_alias.hasNext() ) {
					adaptor.addChild(root_1, stream_alias.nextTree());
				}
				stream_alias.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tableSource"


	public static class updateTable_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "updateTable"
	// SQLParser.g:550:1: updateTable : (tabname= tableName ( KW_AS )? )? alias= identifier -> ^( TOK_ALIAS $alias ( $tabname)? ) ;
	public final SQLParser.updateTable_return updateTable() throws RecognitionException {
		SQLParser.updateTable_return retval = new SQLParser.updateTable_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_AS261=null;
		ParserRuleReturnScope tabname =null;
		ParserRuleReturnScope alias =null;

		CommonTree KW_AS261_tree=null;
		RewriteRuleTokenStream stream_KW_AS=new RewriteRuleTokenStream(adaptor,"token KW_AS");
		RewriteRuleSubtreeStream stream_tableName=new RewriteRuleSubtreeStream(adaptor,"rule tableName");
		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// SQLParser.g:551:2: ( (tabname= tableName ( KW_AS )? )? alias= identifier -> ^( TOK_ALIAS $alias ( $tabname)? ) )
			// SQLParser.g:551:4: (tabname= tableName ( KW_AS )? )? alias= identifier
			{
			// SQLParser.g:551:4: (tabname= tableName ( KW_AS )? )?
			int alt77=2;
			int LA77_0 = input.LA(1);
			if ( (LA77_0==Identifier) ) {
				int LA77_1 = input.LA(2);
				if ( (LA77_1==DOT||LA77_1==Identifier||LA77_1==KW_AS) ) {
					alt77=1;
				}
			}
			switch (alt77) {
				case 1 :
					// SQLParser.g:551:5: tabname= tableName ( KW_AS )?
					{
					pushFollow(FOLLOW_tableName_in_updateTable2943);
					tabname=tableName();
					state._fsp--;

					stream_tableName.add(tabname.getTree());
					// SQLParser.g:551:23: ( KW_AS )?
					int alt76=2;
					int LA76_0 = input.LA(1);
					if ( (LA76_0==KW_AS) ) {
						alt76=1;
					}
					switch (alt76) {
						case 1 :
							// SQLParser.g:551:23: KW_AS
							{
							KW_AS261=(Token)match(input,KW_AS,FOLLOW_KW_AS_in_updateTable2945);  
							stream_KW_AS.add(KW_AS261);

							}
							break;

					}

					}
					break;

			}

			pushFollow(FOLLOW_identifier_in_updateTable2952);
			alias=identifier();
			state._fsp--;

			stream_identifier.add(alias.getTree());
			// AST REWRITE
			// elements: alias, tabname
			// token labels: 
			// rule labels: retval, alias, tabname
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_alias=new RewriteRuleSubtreeStream(adaptor,"rule alias",alias!=null?alias.getTree():null);
			RewriteRuleSubtreeStream stream_tabname=new RewriteRuleSubtreeStream(adaptor,"rule tabname",tabname!=null?tabname.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 552:3: -> ^( TOK_ALIAS $alias ( $tabname)? )
			{
				// SQLParser.g:552:6: ^( TOK_ALIAS $alias ( $tabname)? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_ALIAS, "TOK_ALIAS"), root_1);
				adaptor.addChild(root_1, stream_alias.nextTree());
				// SQLParser.g:552:26: ( $tabname)?
				if ( stream_tabname.hasNext() ) {
					adaptor.addChild(root_1, stream_tabname.nextTree());
				}
				stream_tabname.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "updateTable"


	public static class deleteTable_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "deleteTable"
	// SQLParser.g:555:1: deleteTable : (tabname= tableName ( KW_AS )? )? alias= identifier -> ^( TOK_ALIAS $alias ( $tabname)? ) ;
	public final SQLParser.deleteTable_return deleteTable() throws RecognitionException {
		SQLParser.deleteTable_return retval = new SQLParser.deleteTable_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_AS262=null;
		ParserRuleReturnScope tabname =null;
		ParserRuleReturnScope alias =null;

		CommonTree KW_AS262_tree=null;
		RewriteRuleTokenStream stream_KW_AS=new RewriteRuleTokenStream(adaptor,"token KW_AS");
		RewriteRuleSubtreeStream stream_tableName=new RewriteRuleSubtreeStream(adaptor,"rule tableName");
		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// SQLParser.g:556:2: ( (tabname= tableName ( KW_AS )? )? alias= identifier -> ^( TOK_ALIAS $alias ( $tabname)? ) )
			// SQLParser.g:556:4: (tabname= tableName ( KW_AS )? )? alias= identifier
			{
			// SQLParser.g:556:4: (tabname= tableName ( KW_AS )? )?
			int alt79=2;
			int LA79_0 = input.LA(1);
			if ( (LA79_0==Identifier) ) {
				int LA79_1 = input.LA(2);
				if ( (LA79_1==DOT||LA79_1==Identifier||LA79_1==KW_AS) ) {
					alt79=1;
				}
			}
			switch (alt79) {
				case 1 :
					// SQLParser.g:556:5: tabname= tableName ( KW_AS )?
					{
					pushFollow(FOLLOW_tableName_in_deleteTable2982);
					tabname=tableName();
					state._fsp--;

					stream_tableName.add(tabname.getTree());
					// SQLParser.g:556:23: ( KW_AS )?
					int alt78=2;
					int LA78_0 = input.LA(1);
					if ( (LA78_0==KW_AS) ) {
						alt78=1;
					}
					switch (alt78) {
						case 1 :
							// SQLParser.g:556:23: KW_AS
							{
							KW_AS262=(Token)match(input,KW_AS,FOLLOW_KW_AS_in_deleteTable2984);  
							stream_KW_AS.add(KW_AS262);

							}
							break;

					}

					}
					break;

			}

			pushFollow(FOLLOW_identifier_in_deleteTable2991);
			alias=identifier();
			state._fsp--;

			stream_identifier.add(alias.getTree());
			// AST REWRITE
			// elements: alias, tabname
			// token labels: 
			// rule labels: retval, alias, tabname
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_alias=new RewriteRuleSubtreeStream(adaptor,"rule alias",alias!=null?alias.getTree():null);
			RewriteRuleSubtreeStream stream_tabname=new RewriteRuleSubtreeStream(adaptor,"rule tabname",tabname!=null?tabname.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 557:3: -> ^( TOK_ALIAS $alias ( $tabname)? )
			{
				// SQLParser.g:557:6: ^( TOK_ALIAS $alias ( $tabname)? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_ALIAS, "TOK_ALIAS"), root_1);
				adaptor.addChild(root_1, stream_alias.nextTree());
				// SQLParser.g:557:26: ( $tabname)?
				if ( stream_tabname.hasNext() ) {
					adaptor.addChild(root_1, stream_tabname.nextTree());
				}
				stream_tabname.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "deleteTable"


	public static class tableName_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "tableName"
	// SQLParser.g:560:1: tableName : (db= identifier DOT tab= identifier -> ^( TOK_TABNAME $db $tab) |tab= identifier -> ^( TOK_TABNAME $tab) );
	public final SQLParser.tableName_return tableName() throws RecognitionException {
		SQLParser.tableName_return retval = new SQLParser.tableName_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token DOT263=null;
		ParserRuleReturnScope db =null;
		ParserRuleReturnScope tab =null;

		CommonTree DOT263_tree=null;
		RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// SQLParser.g:561:5: (db= identifier DOT tab= identifier -> ^( TOK_TABNAME $db $tab) |tab= identifier -> ^( TOK_TABNAME $tab) )
			int alt80=2;
			int LA80_0 = input.LA(1);
			if ( (LA80_0==Identifier) ) {
				int LA80_1 = input.LA(2);
				if ( (LA80_1==DOT) ) {
					int LA80_2 = input.LA(3);
					if ( (LA80_2==STAR) ) {
						alt80=2;
					}
					else if ( (LA80_2==Identifier) ) {
						alt80=1;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 80, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA80_1==EOF||LA80_1==COMMA||LA80_1==Identifier||LA80_1==KW_AS||(LA80_1 >= KW_CREATE && LA80_1 <= KW_CROSS)||(LA80_1 >= KW_DEFINE && LA80_1 <= KW_DELETE)||LA80_1==KW_FULL||(LA80_1 >= KW_GROUP && LA80_1 <= KW_HAVING)||LA80_1==KW_INNER||LA80_1==KW_INSERT||LA80_1==KW_INVOKE||(LA80_1 >= KW_JOIN && LA80_1 <= KW_LEFT)||LA80_1==KW_META||LA80_1==KW_ON||LA80_1==KW_ORDER||LA80_1==KW_OVERWRITE||LA80_1==KW_PATH||LA80_1==KW_QUALIFY||LA80_1==KW_RIGHT||LA80_1==KW_SET||LA80_1==KW_UPDATE||LA80_1==KW_WHERE||LA80_1==KW_WITH||(LA80_1 >= LPAREN && LA80_1 <= LSQUARE)||LA80_1==RPAREN||LA80_1==SEMICOLON) ) {
					alt80=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 80, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 80, 0, input);
				throw nvae;
			}

			switch (alt80) {
				case 1 :
					// SQLParser.g:562:2: db= identifier DOT tab= identifier
					{
					pushFollow(FOLLOW_identifier_in_tableName3023);
					db=identifier();
					state._fsp--;

					stream_identifier.add(db.getTree());
					DOT263=(Token)match(input,DOT,FOLLOW_DOT_in_tableName3025);  
					stream_DOT.add(DOT263);

					pushFollow(FOLLOW_identifier_in_tableName3029);
					tab=identifier();
					state._fsp--;

					stream_identifier.add(tab.getTree());
					// AST REWRITE
					// elements: db, tab
					// token labels: 
					// rule labels: db, retval, tab
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_db=new RewriteRuleSubtreeStream(adaptor,"rule db",db!=null?db.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_tab=new RewriteRuleSubtreeStream(adaptor,"rule tab",tab!=null?tab.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 563:6: -> ^( TOK_TABNAME $db $tab)
					{
						// SQLParser.g:563:9: ^( TOK_TABNAME $db $tab)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_TABNAME, "TOK_TABNAME"), root_1);
						adaptor.addChild(root_1, stream_db.nextTree());
						adaptor.addChild(root_1, stream_tab.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// SQLParser.g:565:2: tab= identifier
					{
					pushFollow(FOLLOW_identifier_in_tableName3057);
					tab=identifier();
					state._fsp--;

					stream_identifier.add(tab.getTree());
					// AST REWRITE
					// elements: tab
					// token labels: 
					// rule labels: retval, tab
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_tab=new RewriteRuleSubtreeStream(adaptor,"rule tab",tab!=null?tab.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 566:3: -> ^( TOK_TABNAME $tab)
					{
						// SQLParser.g:566:6: ^( TOK_TABNAME $tab)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_TABNAME, "TOK_TABNAME"), root_1);
						adaptor.addChild(root_1, stream_tab.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tableName"


	public static class subQuerySource_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "subQuerySource"
	// SQLParser.g:569:1: subQuerySource : LPAREN subQuery RPAREN identifier -> ^( TOK_SUBQUERY subQuery identifier ) ;
	public final SQLParser.subQuerySource_return subQuerySource() throws RecognitionException {
		SQLParser.subQuerySource_return retval = new SQLParser.subQuerySource_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LPAREN264=null;
		Token RPAREN266=null;
		ParserRuleReturnScope subQuery265 =null;
		ParserRuleReturnScope identifier267 =null;

		CommonTree LPAREN264_tree=null;
		CommonTree RPAREN266_tree=null;
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_subQuery=new RewriteRuleSubtreeStream(adaptor,"rule subQuery");
		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// SQLParser.g:570:5: ( LPAREN subQuery RPAREN identifier -> ^( TOK_SUBQUERY subQuery identifier ) )
			// SQLParser.g:571:5: LPAREN subQuery RPAREN identifier
			{
			LPAREN264=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_subQuerySource3089);  
			stream_LPAREN.add(LPAREN264);

			pushFollow(FOLLOW_subQuery_in_subQuerySource3091);
			subQuery265=subQuery();
			state._fsp--;

			stream_subQuery.add(subQuery265.getTree());
			RPAREN266=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_subQuerySource3093);  
			stream_RPAREN.add(RPAREN266);

			pushFollow(FOLLOW_identifier_in_subQuerySource3095);
			identifier267=identifier();
			state._fsp--;

			stream_identifier.add(identifier267.getTree());
			// AST REWRITE
			// elements: subQuery, identifier
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 571:39: -> ^( TOK_SUBQUERY subQuery identifier )
			{
				// SQLParser.g:571:42: ^( TOK_SUBQUERY subQuery identifier )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_SUBQUERY, "TOK_SUBQUERY"), root_1);
				adaptor.addChild(root_1, stream_subQuery.nextTree());
				adaptor.addChild(root_1, stream_identifier.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "subQuerySource"


	public static class subQuery_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "subQuery"
	// SQLParser.g:574:1: subQuery : queryStatement -> ^( TOK_SUBQUERY_STATEMENT ^( TOK_DESTINATION ^( TOK_DIR TOK_TMP_FILE ) ) queryStatement ) ;
	public final SQLParser.subQuery_return subQuery() throws RecognitionException {
		SQLParser.subQuery_return retval = new SQLParser.subQuery_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope queryStatement268 =null;

		RewriteRuleSubtreeStream stream_queryStatement=new RewriteRuleSubtreeStream(adaptor,"rule queryStatement");

		try {
			// SQLParser.g:575:2: ( queryStatement -> ^( TOK_SUBQUERY_STATEMENT ^( TOK_DESTINATION ^( TOK_DIR TOK_TMP_FILE ) ) queryStatement ) )
			// SQLParser.g:576:2: queryStatement
			{
			pushFollow(FOLLOW_queryStatement_in_subQuery3124);
			queryStatement268=queryStatement();
			state._fsp--;

			stream_queryStatement.add(queryStatement268.getTree());
			// AST REWRITE
			// elements: queryStatement
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 577:3: -> ^( TOK_SUBQUERY_STATEMENT ^( TOK_DESTINATION ^( TOK_DIR TOK_TMP_FILE ) ) queryStatement )
			{
				// SQLParser.g:577:6: ^( TOK_SUBQUERY_STATEMENT ^( TOK_DESTINATION ^( TOK_DIR TOK_TMP_FILE ) ) queryStatement )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_SUBQUERY_STATEMENT, "TOK_SUBQUERY_STATEMENT"), root_1);
				// SQLParser.g:577:32: ^( TOK_DESTINATION ^( TOK_DIR TOK_TMP_FILE ) )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DESTINATION, "TOK_DESTINATION"), root_2);
				// SQLParser.g:577:50: ^( TOK_DIR TOK_TMP_FILE )
				{
				CommonTree root_3 = (CommonTree)adaptor.nil();
				root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DIR, "TOK_DIR"), root_3);
				adaptor.addChild(root_3, (CommonTree)adaptor.create(TOK_TMP_FILE, "TOK_TMP_FILE"));
				adaptor.addChild(root_2, root_3);
				}

				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, stream_queryStatement.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "subQuery"


	public static class whereClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "whereClause"
	// SQLParser.g:580:1: whereClause : KW_WHERE searchCondition -> ^( TOK_WHERE searchCondition ) ;
	public final SQLParser.whereClause_return whereClause() throws RecognitionException {
		SQLParser.whereClause_return retval = new SQLParser.whereClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_WHERE269=null;
		ParserRuleReturnScope searchCondition270 =null;

		CommonTree KW_WHERE269_tree=null;
		RewriteRuleTokenStream stream_KW_WHERE=new RewriteRuleTokenStream(adaptor,"token KW_WHERE");
		RewriteRuleSubtreeStream stream_searchCondition=new RewriteRuleSubtreeStream(adaptor,"rule searchCondition");

		try {
			// SQLParser.g:581:5: ( KW_WHERE searchCondition -> ^( TOK_WHERE searchCondition ) )
			// SQLParser.g:582:5: KW_WHERE searchCondition
			{
			KW_WHERE269=(Token)match(input,KW_WHERE,FOLLOW_KW_WHERE_in_whereClause3164);  
			stream_KW_WHERE.add(KW_WHERE269);

			pushFollow(FOLLOW_searchCondition_in_whereClause3166);
			searchCondition270=searchCondition();
			state._fsp--;

			stream_searchCondition.add(searchCondition270.getTree());
			// AST REWRITE
			// elements: searchCondition
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 582:30: -> ^( TOK_WHERE searchCondition )
			{
				// SQLParser.g:582:33: ^( TOK_WHERE searchCondition )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_WHERE, "TOK_WHERE"), root_1);
				adaptor.addChild(root_1, stream_searchCondition.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "whereClause"


	public static class searchCondition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "searchCondition"
	// SQLParser.g:585:1: searchCondition : expression ;
	public final SQLParser.searchCondition_return searchCondition() throws RecognitionException {
		SQLParser.searchCondition_return retval = new SQLParser.searchCondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope expression271 =null;


		try {
			// SQLParser.g:586:5: ( expression )
			// SQLParser.g:587:5: expression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_expression_in_searchCondition3195);
			expression271=expression();
			state._fsp--;

			adaptor.addChild(root_0, expression271.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "searchCondition"


	public static class hintClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "hintClause"
	// SQLParser.g:590:1: hintClause : DIVIDE STAR PLUS hintList STAR DIVIDE -> ^( TOK_HINTLIST hintList ) ;
	public final SQLParser.hintClause_return hintClause() throws RecognitionException {
		SQLParser.hintClause_return retval = new SQLParser.hintClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token DIVIDE272=null;
		Token STAR273=null;
		Token PLUS274=null;
		Token STAR276=null;
		Token DIVIDE277=null;
		ParserRuleReturnScope hintList275 =null;

		CommonTree DIVIDE272_tree=null;
		CommonTree STAR273_tree=null;
		CommonTree PLUS274_tree=null;
		CommonTree STAR276_tree=null;
		CommonTree DIVIDE277_tree=null;
		RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
		RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
		RewriteRuleTokenStream stream_DIVIDE=new RewriteRuleTokenStream(adaptor,"token DIVIDE");
		RewriteRuleSubtreeStream stream_hintList=new RewriteRuleSubtreeStream(adaptor,"rule hintList");

		try {
			// SQLParser.g:591:5: ( DIVIDE STAR PLUS hintList STAR DIVIDE -> ^( TOK_HINTLIST hintList ) )
			// SQLParser.g:592:5: DIVIDE STAR PLUS hintList STAR DIVIDE
			{
			DIVIDE272=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_hintClause3220);  
			stream_DIVIDE.add(DIVIDE272);

			STAR273=(Token)match(input,STAR,FOLLOW_STAR_in_hintClause3222);  
			stream_STAR.add(STAR273);

			PLUS274=(Token)match(input,PLUS,FOLLOW_PLUS_in_hintClause3224);  
			stream_PLUS.add(PLUS274);

			pushFollow(FOLLOW_hintList_in_hintClause3226);
			hintList275=hintList();
			state._fsp--;

			stream_hintList.add(hintList275.getTree());
			STAR276=(Token)match(input,STAR,FOLLOW_STAR_in_hintClause3228);  
			stream_STAR.add(STAR276);

			DIVIDE277=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_hintClause3230);  
			stream_DIVIDE.add(DIVIDE277);

			// AST REWRITE
			// elements: hintList
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 592:43: -> ^( TOK_HINTLIST hintList )
			{
				// SQLParser.g:592:46: ^( TOK_HINTLIST hintList )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_HINTLIST, "TOK_HINTLIST"), root_1);
				adaptor.addChild(root_1, stream_hintList.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "hintClause"


	public static class hintList_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "hintList"
	// SQLParser.g:595:1: hintList : hintItem ( COMMA hintItem )* -> ( hintItem )+ ;
	public final SQLParser.hintList_return hintList() throws RecognitionException {
		SQLParser.hintList_return retval = new SQLParser.hintList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA279=null;
		ParserRuleReturnScope hintItem278 =null;
		ParserRuleReturnScope hintItem280 =null;

		CommonTree COMMA279_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_hintItem=new RewriteRuleSubtreeStream(adaptor,"rule hintItem");

		try {
			// SQLParser.g:596:5: ( hintItem ( COMMA hintItem )* -> ( hintItem )+ )
			// SQLParser.g:597:5: hintItem ( COMMA hintItem )*
			{
			pushFollow(FOLLOW_hintItem_in_hintList3259);
			hintItem278=hintItem();
			state._fsp--;

			stream_hintItem.add(hintItem278.getTree());
			// SQLParser.g:597:14: ( COMMA hintItem )*
			loop81:
			while (true) {
				int alt81=2;
				int LA81_0 = input.LA(1);
				if ( (LA81_0==COMMA) ) {
					alt81=1;
				}

				switch (alt81) {
				case 1 :
					// SQLParser.g:597:15: COMMA hintItem
					{
					COMMA279=(Token)match(input,COMMA,FOLLOW_COMMA_in_hintList3262);  
					stream_COMMA.add(COMMA279);

					pushFollow(FOLLOW_hintItem_in_hintList3264);
					hintItem280=hintItem();
					state._fsp--;

					stream_hintItem.add(hintItem280.getTree());
					}
					break;

				default :
					break loop81;
				}
			}

			// AST REWRITE
			// elements: hintItem
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 597:32: -> ( hintItem )+
			{
				if ( !(stream_hintItem.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_hintItem.hasNext() ) {
					adaptor.addChild(root_0, stream_hintItem.nextTree());
				}
				stream_hintItem.reset();

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "hintList"


	public static class hintItem_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "hintItem"
	// SQLParser.g:600:1: hintItem : hintName ( LPAREN hintArgs RPAREN )? -> ^( TOK_HINT hintName ( hintArgs )? ) ;
	public final SQLParser.hintItem_return hintItem() throws RecognitionException {
		SQLParser.hintItem_return retval = new SQLParser.hintItem_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LPAREN282=null;
		Token RPAREN284=null;
		ParserRuleReturnScope hintName281 =null;
		ParserRuleReturnScope hintArgs283 =null;

		CommonTree LPAREN282_tree=null;
		CommonTree RPAREN284_tree=null;
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_hintName=new RewriteRuleSubtreeStream(adaptor,"rule hintName");
		RewriteRuleSubtreeStream stream_hintArgs=new RewriteRuleSubtreeStream(adaptor,"rule hintArgs");

		try {
			// SQLParser.g:602:5: ( hintName ( LPAREN hintArgs RPAREN )? -> ^( TOK_HINT hintName ( hintArgs )? ) )
			// SQLParser.g:603:5: hintName ( LPAREN hintArgs RPAREN )?
			{
			pushFollow(FOLLOW_hintName_in_hintItem3293);
			hintName281=hintName();
			state._fsp--;

			stream_hintName.add(hintName281.getTree());
			// SQLParser.g:603:14: ( LPAREN hintArgs RPAREN )?
			int alt82=2;
			int LA82_0 = input.LA(1);
			if ( (LA82_0==LPAREN) ) {
				alt82=1;
			}
			switch (alt82) {
				case 1 :
					// SQLParser.g:603:15: LPAREN hintArgs RPAREN
					{
					LPAREN282=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_hintItem3296);  
					stream_LPAREN.add(LPAREN282);

					pushFollow(FOLLOW_hintArgs_in_hintItem3298);
					hintArgs283=hintArgs();
					state._fsp--;

					stream_hintArgs.add(hintArgs283.getTree());
					RPAREN284=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_hintItem3300);  
					stream_RPAREN.add(RPAREN284);

					}
					break;

			}

			// AST REWRITE
			// elements: hintArgs, hintName
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 603:40: -> ^( TOK_HINT hintName ( hintArgs )? )
			{
				// SQLParser.g:603:43: ^( TOK_HINT hintName ( hintArgs )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_HINT, "TOK_HINT"), root_1);
				adaptor.addChild(root_1, stream_hintName.nextTree());
				// SQLParser.g:603:63: ( hintArgs )?
				if ( stream_hintArgs.hasNext() ) {
					adaptor.addChild(root_1, stream_hintArgs.nextTree());
				}
				stream_hintArgs.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "hintItem"


	public static class hintName_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "hintName"
	// SQLParser.g:606:1: hintName : ( KW_MAPJOIN -> TOK_MAPJOIN | KW_STREAMTABLE -> TOK_STREAMTABLE | KW_HOLD_DDLTIME -> TOK_HOLD_DDLTIME );
	public final SQLParser.hintName_return hintName() throws RecognitionException {
		SQLParser.hintName_return retval = new SQLParser.hintName_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_MAPJOIN285=null;
		Token KW_STREAMTABLE286=null;
		Token KW_HOLD_DDLTIME287=null;

		CommonTree KW_MAPJOIN285_tree=null;
		CommonTree KW_STREAMTABLE286_tree=null;
		CommonTree KW_HOLD_DDLTIME287_tree=null;
		RewriteRuleTokenStream stream_KW_HOLD_DDLTIME=new RewriteRuleTokenStream(adaptor,"token KW_HOLD_DDLTIME");
		RewriteRuleTokenStream stream_KW_MAPJOIN=new RewriteRuleTokenStream(adaptor,"token KW_MAPJOIN");
		RewriteRuleTokenStream stream_KW_STREAMTABLE=new RewriteRuleTokenStream(adaptor,"token KW_STREAMTABLE");

		try {
			// SQLParser.g:607:5: ( KW_MAPJOIN -> TOK_MAPJOIN | KW_STREAMTABLE -> TOK_STREAMTABLE | KW_HOLD_DDLTIME -> TOK_HOLD_DDLTIME )
			int alt83=3;
			switch ( input.LA(1) ) {
			case KW_MAPJOIN:
				{
				alt83=1;
				}
				break;
			case KW_STREAMTABLE:
				{
				alt83=2;
				}
				break;
			case KW_HOLD_DDLTIME:
				{
				alt83=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 83, 0, input);
				throw nvae;
			}
			switch (alt83) {
				case 1 :
					// SQLParser.g:608:5: KW_MAPJOIN
					{
					KW_MAPJOIN285=(Token)match(input,KW_MAPJOIN,FOLLOW_KW_MAPJOIN_in_hintName3334);  
					stream_KW_MAPJOIN.add(KW_MAPJOIN285);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 608:16: -> TOK_MAPJOIN
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_MAPJOIN, "TOK_MAPJOIN"));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// SQLParser.g:609:7: KW_STREAMTABLE
					{
					KW_STREAMTABLE286=(Token)match(input,KW_STREAMTABLE,FOLLOW_KW_STREAMTABLE_in_hintName3346);  
					stream_KW_STREAMTABLE.add(KW_STREAMTABLE286);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 609:22: -> TOK_STREAMTABLE
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_STREAMTABLE, "TOK_STREAMTABLE"));
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// SQLParser.g:610:7: KW_HOLD_DDLTIME
					{
					KW_HOLD_DDLTIME287=(Token)match(input,KW_HOLD_DDLTIME,FOLLOW_KW_HOLD_DDLTIME_in_hintName3358);  
					stream_KW_HOLD_DDLTIME.add(KW_HOLD_DDLTIME287);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 610:23: -> TOK_HOLD_DDLTIME
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_HOLD_DDLTIME, "TOK_HOLD_DDLTIME"));
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "hintName"


	public static class hintArgs_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "hintArgs"
	// SQLParser.g:613:1: hintArgs : hintArgName ( COMMA hintArgName )* -> ^( TOK_HINTARGLIST ( hintArgName )+ ) ;
	public final SQLParser.hintArgs_return hintArgs() throws RecognitionException {
		SQLParser.hintArgs_return retval = new SQLParser.hintArgs_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA289=null;
		ParserRuleReturnScope hintArgName288 =null;
		ParserRuleReturnScope hintArgName290 =null;

		CommonTree COMMA289_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_hintArgName=new RewriteRuleSubtreeStream(adaptor,"rule hintArgName");

		try {
			// SQLParser.g:614:5: ( hintArgName ( COMMA hintArgName )* -> ^( TOK_HINTARGLIST ( hintArgName )+ ) )
			// SQLParser.g:615:5: hintArgName ( COMMA hintArgName )*
			{
			pushFollow(FOLLOW_hintArgName_in_hintArgs3383);
			hintArgName288=hintArgName();
			state._fsp--;

			stream_hintArgName.add(hintArgName288.getTree());
			// SQLParser.g:615:17: ( COMMA hintArgName )*
			loop84:
			while (true) {
				int alt84=2;
				int LA84_0 = input.LA(1);
				if ( (LA84_0==COMMA) ) {
					alt84=1;
				}

				switch (alt84) {
				case 1 :
					// SQLParser.g:615:18: COMMA hintArgName
					{
					COMMA289=(Token)match(input,COMMA,FOLLOW_COMMA_in_hintArgs3386);  
					stream_COMMA.add(COMMA289);

					pushFollow(FOLLOW_hintArgName_in_hintArgs3388);
					hintArgName290=hintArgName();
					state._fsp--;

					stream_hintArgName.add(hintArgName290.getTree());
					}
					break;

				default :
					break loop84;
				}
			}

			// AST REWRITE
			// elements: hintArgName
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 615:38: -> ^( TOK_HINTARGLIST ( hintArgName )+ )
			{
				// SQLParser.g:615:41: ^( TOK_HINTARGLIST ( hintArgName )+ )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_HINTARGLIST, "TOK_HINTARGLIST"), root_1);
				if ( !(stream_hintArgName.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_hintArgName.hasNext() ) {
					adaptor.addChild(root_1, stream_hintArgName.nextTree());
				}
				stream_hintArgName.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "hintArgs"


	public static class hintArgName_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "hintArgName"
	// SQLParser.g:618:1: hintArgName : identifier ;
	public final SQLParser.hintArgName_return hintArgName() throws RecognitionException {
		SQLParser.hintArgName_return retval = new SQLParser.hintArgName_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope identifier291 =null;


		try {
			// SQLParser.g:619:5: ( identifier )
			// SQLParser.g:620:5: identifier
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_identifier_in_hintArgName3420);
			identifier291=identifier();
			state._fsp--;

			adaptor.addChild(root_0, identifier291.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "hintArgName"


	public static class invokeStatement_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "invokeStatement"
	// SQLParser.g:625:1: invokeStatement : KW_INVOKE Identifier LPAREN ( parameters )? RPAREN -> ^( TOK_INVOKE Identifier ( parameters )? ) ;
	public final SQLParser.invokeStatement_return invokeStatement() throws RecognitionException {
		SQLParser.invokeStatement_return retval = new SQLParser.invokeStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_INVOKE292=null;
		Token Identifier293=null;
		Token LPAREN294=null;
		Token RPAREN296=null;
		ParserRuleReturnScope parameters295 =null;

		CommonTree KW_INVOKE292_tree=null;
		CommonTree Identifier293_tree=null;
		CommonTree LPAREN294_tree=null;
		CommonTree RPAREN296_tree=null;
		RewriteRuleTokenStream stream_KW_INVOKE=new RewriteRuleTokenStream(adaptor,"token KW_INVOKE");
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_parameters=new RewriteRuleSubtreeStream(adaptor,"rule parameters");

		try {
			// SQLParser.g:626:5: ( KW_INVOKE Identifier LPAREN ( parameters )? RPAREN -> ^( TOK_INVOKE Identifier ( parameters )? ) )
			// SQLParser.g:627:5: KW_INVOKE Identifier LPAREN ( parameters )? RPAREN
			{
			KW_INVOKE292=(Token)match(input,KW_INVOKE,FOLLOW_KW_INVOKE_in_invokeStatement3459);  
			stream_KW_INVOKE.add(KW_INVOKE292);

			Identifier293=(Token)match(input,Identifier,FOLLOW_Identifier_in_invokeStatement3461);  
			stream_Identifier.add(Identifier293);

			LPAREN294=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_invokeStatement3463);  
			stream_LPAREN.add(LPAREN294);

			// SQLParser.g:627:33: ( parameters )?
			int alt85=2;
			int LA85_0 = input.LA(1);
			if ( (LA85_0==Identifier) ) {
				alt85=1;
			}
			switch (alt85) {
				case 1 :
					// SQLParser.g:627:33: parameters
					{
					pushFollow(FOLLOW_parameters_in_invokeStatement3465);
					parameters295=parameters();
					state._fsp--;

					stream_parameters.add(parameters295.getTree());
					}
					break;

			}

			RPAREN296=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_invokeStatement3468);  
			stream_RPAREN.add(RPAREN296);

			// AST REWRITE
			// elements: Identifier, parameters
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 628:8: -> ^( TOK_INVOKE Identifier ( parameters )? )
			{
				// SQLParser.g:628:10: ^( TOK_INVOKE Identifier ( parameters )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_INVOKE, "TOK_INVOKE"), root_1);
				adaptor.addChild(root_1, stream_Identifier.nextNode());
				// SQLParser.g:628:34: ( parameters )?
				if ( stream_parameters.hasNext() ) {
					adaptor.addChild(root_1, stream_parameters.nextTree());
				}
				stream_parameters.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "invokeStatement"


	public static class parameters_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "parameters"
	// SQLParser.g:631:1: parameters : parameter ( COMMA parameter )+ -> ^( TOK_PARAMETERS ( parameter )* ) ;
	public final SQLParser.parameters_return parameters() throws RecognitionException {
		SQLParser.parameters_return retval = new SQLParser.parameters_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA298=null;
		ParserRuleReturnScope parameter297 =null;
		ParserRuleReturnScope parameter299 =null;

		CommonTree COMMA298_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_parameter=new RewriteRuleSubtreeStream(adaptor,"rule parameter");

		try {
			// SQLParser.g:632:5: ( parameter ( COMMA parameter )+ -> ^( TOK_PARAMETERS ( parameter )* ) )
			// SQLParser.g:633:5: parameter ( COMMA parameter )+
			{
			pushFollow(FOLLOW_parameter_in_parameters3505);
			parameter297=parameter();
			state._fsp--;

			stream_parameter.add(parameter297.getTree());
			// SQLParser.g:633:14: ( COMMA parameter )+
			int cnt86=0;
			loop86:
			while (true) {
				int alt86=2;
				int LA86_0 = input.LA(1);
				if ( (LA86_0==COMMA) ) {
					alt86=1;
				}

				switch (alt86) {
				case 1 :
					// SQLParser.g:633:15: COMMA parameter
					{
					COMMA298=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameters3507);  
					stream_COMMA.add(COMMA298);

					pushFollow(FOLLOW_parameter_in_parameters3509);
					parameter299=parameter();
					state._fsp--;

					stream_parameter.add(parameter299.getTree());
					}
					break;

				default :
					if ( cnt86 >= 1 ) break loop86;
					EarlyExitException eee = new EarlyExitException(86, input);
					throw eee;
				}
				cnt86++;
			}

			// AST REWRITE
			// elements: parameter
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 634:7: -> ^( TOK_PARAMETERS ( parameter )* )
			{
				// SQLParser.g:634:9: ^( TOK_PARAMETERS ( parameter )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_PARAMETERS, "TOK_PARAMETERS"), root_1);
				// SQLParser.g:634:26: ( parameter )*
				while ( stream_parameter.hasNext() ) {
					adaptor.addChild(root_1, stream_parameter.nextTree());
				}
				stream_parameter.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "parameters"


	public static class parameter_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "parameter"
	// SQLParser.g:637:1: parameter : Identifier COLON parameterValue -> ^( TOK_PARAMETER Identifier parameterValue ) ;
	public final SQLParser.parameter_return parameter() throws RecognitionException {
		SQLParser.parameter_return retval = new SQLParser.parameter_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token Identifier300=null;
		Token COLON301=null;
		ParserRuleReturnScope parameterValue302 =null;

		CommonTree Identifier300_tree=null;
		CommonTree COLON301_tree=null;
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
		RewriteRuleSubtreeStream stream_parameterValue=new RewriteRuleSubtreeStream(adaptor,"rule parameterValue");

		try {
			// SQLParser.g:638:5: ( Identifier COLON parameterValue -> ^( TOK_PARAMETER Identifier parameterValue ) )
			// SQLParser.g:639:5: Identifier COLON parameterValue
			{
			Identifier300=(Token)match(input,Identifier,FOLLOW_Identifier_in_parameter3550);  
			stream_Identifier.add(Identifier300);

			COLON301=(Token)match(input,COLON,FOLLOW_COLON_in_parameter3552);  
			stream_COLON.add(COLON301);

			pushFollow(FOLLOW_parameterValue_in_parameter3554);
			parameterValue302=parameterValue();
			state._fsp--;

			stream_parameterValue.add(parameterValue302.getTree());
			// AST REWRITE
			// elements: parameterValue, Identifier
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 640:7: -> ^( TOK_PARAMETER Identifier parameterValue )
			{
				// SQLParser.g:640:9: ^( TOK_PARAMETER Identifier parameterValue )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_PARAMETER, "TOK_PARAMETER"), root_1);
				adaptor.addChild(root_1, stream_Identifier.nextNode());
				adaptor.addChild(root_1, stream_parameterValue.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "parameter"


	public static class parameterValue_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "parameterValue"
	// SQLParser.g:644:1: parameterValue : ( paramValIdentifier | parameterArray );
	public final SQLParser.parameterValue_return parameterValue() throws RecognitionException {
		SQLParser.parameterValue_return retval = new SQLParser.parameterValue_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope paramValIdentifier303 =null;
		ParserRuleReturnScope parameterArray304 =null;


		try {
			// SQLParser.g:645:5: ( paramValIdentifier | parameterArray )
			int alt87=2;
			int LA87_0 = input.LA(1);
			if ( (LA87_0==ByteLengthLiteral||LA87_0==Identifier||LA87_0==Number||LA87_0==StringLiteral) ) {
				alt87=1;
			}
			else if ( (LA87_0==LSQUARE) ) {
				alt87=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 87, 0, input);
				throw nvae;
			}

			switch (alt87) {
				case 1 :
					// SQLParser.g:646:5: paramValIdentifier
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_paramValIdentifier_in_parameterValue3595);
					paramValIdentifier303=paramValIdentifier();
					state._fsp--;

					adaptor.addChild(root_0, paramValIdentifier303.getTree());

					}
					break;
				case 2 :
					// SQLParser.g:646:26: parameterArray
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_parameterArray_in_parameterValue3599);
					parameterArray304=parameterArray();
					state._fsp--;

					adaptor.addChild(root_0, parameterArray304.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "parameterValue"


	public static class parameterArray_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "parameterArray"
	// SQLParser.g:650:1: parameterArray : LSQUARE ( parameterValue ( COMMA parameterValue )* )? RSQUARE -> ^( TOK_PARAMETER_ARRAY ( parameterValue )* ) ;
	public final SQLParser.parameterArray_return parameterArray() throws RecognitionException {
		SQLParser.parameterArray_return retval = new SQLParser.parameterArray_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LSQUARE305=null;
		Token COMMA307=null;
		Token RSQUARE309=null;
		ParserRuleReturnScope parameterValue306 =null;
		ParserRuleReturnScope parameterValue308 =null;

		CommonTree LSQUARE305_tree=null;
		CommonTree COMMA307_tree=null;
		CommonTree RSQUARE309_tree=null;
		RewriteRuleTokenStream stream_LSQUARE=new RewriteRuleTokenStream(adaptor,"token LSQUARE");
		RewriteRuleTokenStream stream_RSQUARE=new RewriteRuleTokenStream(adaptor,"token RSQUARE");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_parameterValue=new RewriteRuleSubtreeStream(adaptor,"rule parameterValue");

		try {
			// SQLParser.g:651:5: ( LSQUARE ( parameterValue ( COMMA parameterValue )* )? RSQUARE -> ^( TOK_PARAMETER_ARRAY ( parameterValue )* ) )
			// SQLParser.g:652:5: LSQUARE ( parameterValue ( COMMA parameterValue )* )? RSQUARE
			{
			LSQUARE305=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_parameterArray3629);  
			stream_LSQUARE.add(LSQUARE305);

			// SQLParser.g:652:13: ( parameterValue ( COMMA parameterValue )* )?
			int alt89=2;
			int LA89_0 = input.LA(1);
			if ( (LA89_0==ByteLengthLiteral||LA89_0==Identifier||LA89_0==LSQUARE||LA89_0==Number||LA89_0==StringLiteral) ) {
				alt89=1;
			}
			switch (alt89) {
				case 1 :
					// SQLParser.g:652:14: parameterValue ( COMMA parameterValue )*
					{
					pushFollow(FOLLOW_parameterValue_in_parameterArray3632);
					parameterValue306=parameterValue();
					state._fsp--;

					stream_parameterValue.add(parameterValue306.getTree());
					// SQLParser.g:652:28: ( COMMA parameterValue )*
					loop88:
					while (true) {
						int alt88=2;
						int LA88_0 = input.LA(1);
						if ( (LA88_0==COMMA) ) {
							alt88=1;
						}

						switch (alt88) {
						case 1 :
							// SQLParser.g:652:29: COMMA parameterValue
							{
							COMMA307=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameterArray3634);  
							stream_COMMA.add(COMMA307);

							pushFollow(FOLLOW_parameterValue_in_parameterArray3636);
							parameterValue308=parameterValue();
							state._fsp--;

							stream_parameterValue.add(parameterValue308.getTree());
							}
							break;

						default :
							break loop88;
						}
					}

					}
					break;

			}

			RSQUARE309=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_parameterArray3643);  
			stream_RSQUARE.add(RSQUARE309);

			// AST REWRITE
			// elements: parameterValue
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 653:7: -> ^( TOK_PARAMETER_ARRAY ( parameterValue )* )
			{
				// SQLParser.g:653:9: ^( TOK_PARAMETER_ARRAY ( parameterValue )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_PARAMETER_ARRAY, "TOK_PARAMETER_ARRAY"), root_1);
				// SQLParser.g:653:31: ( parameterValue )*
				while ( stream_parameterValue.hasNext() ) {
					adaptor.addChild(root_1, stream_parameterValue.nextTree());
				}
				stream_parameterValue.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "parameterArray"


	public static class paramValIdentifier_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "paramValIdentifier"
	// SQLParser.g:656:1: paramValIdentifier : ( Identifier | StringLiteral | Number | ByteLengthLiteral );
	public final SQLParser.paramValIdentifier_return paramValIdentifier() throws RecognitionException {
		SQLParser.paramValIdentifier_return retval = new SQLParser.paramValIdentifier_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set310=null;

		CommonTree set310_tree=null;

		try {
			// SQLParser.g:657:5: ( Identifier | StringLiteral | Number | ByteLengthLiteral )
			// SQLParser.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set310=input.LT(1);
			if ( input.LA(1)==ByteLengthLiteral||input.LA(1)==Identifier||input.LA(1)==Number||input.LA(1)==StringLiteral ) {
				input.consume();
				adaptor.addChild(root_0, (CommonTree)adaptor.create(set310));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}

		catch (RecognitionException e) {
		 reportError(e);
		  throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "paramValIdentifier"

	// Delegated rules
	public SQLParser_Expr.window_frame_return window_frame() throws RecognitionException { return gExpr.window_frame(); }

	public SQLParser_Expr.precedenceBitwiseOrOperator_return precedenceBitwiseOrOperator() throws RecognitionException { return gExpr.precedenceBitwiseOrOperator(); }

	public SQLParser_Expr.precedenceEqualOperator_return precedenceEqualOperator() throws RecognitionException { return gExpr.precedenceEqualOperator(); }

	public SQLParser_Expr.castExpression_return castExpression() throws RecognitionException { return gExpr.castExpression(); }

	public SQLParser_Expr.precedenceUnarySuffixExpression_return precedenceUnarySuffixExpression() throws RecognitionException { return gExpr.precedenceUnarySuffixExpression(); }

	public SQLParser_Expr.precedenceEqualNegatableOperator_return precedenceEqualNegatableOperator() throws RecognitionException { return gExpr.precedenceEqualNegatableOperator(); }

	public SQLParser_Expr.function_return function() throws RecognitionException { return gExpr.function(); }

	public SQLParser_Expr.precedencePlusExpression_return precedencePlusExpression() throws RecognitionException { return gExpr.precedencePlusExpression(); }

	public SQLParser_Expr.precedenceNotOperator_return precedenceNotOperator() throws RecognitionException { return gExpr.precedenceNotOperator(); }

	public SQLParser_Expr.precedenceStarOperator_return precedenceStarOperator() throws RecognitionException { return gExpr.precedenceStarOperator(); }

	public SQLParser_Expr.precedenceBitwiseOrExpression_return precedenceBitwiseOrExpression() throws RecognitionException { return gExpr.precedenceBitwiseOrExpression(); }

	public SQLParser_Expr.partitioningSpec_return partitioningSpec() throws RecognitionException { return gExpr.partitioningSpec(); }

	public SQLParser_Expr.precedenceEqualExpression_return precedenceEqualExpression() throws RecognitionException { return gExpr.precedenceEqualExpression(); }

	public SQLParser_Expr.precedenceOrOperator_return precedenceOrOperator() throws RecognitionException { return gExpr.precedenceOrOperator(); }

	public SQLParser_Expr.precedenceBitwiseXorExpression_return precedenceBitwiseXorExpression() throws RecognitionException { return gExpr.precedenceBitwiseXorExpression(); }

	public SQLParser_Expr.precedenceBitwiseXorOperator_return precedenceBitwiseXorOperator() throws RecognitionException { return gExpr.precedenceBitwiseXorOperator(); }

	public SQLParser_Expr.expressions_return expressions() throws RecognitionException { return gExpr.expressions(); }

	public SQLParser_Expr.window_value_expression_return window_value_expression() throws RecognitionException { return gExpr.window_value_expression(); }

	public SQLParser_Expr.precedenceAndOperator_return precedenceAndOperator() throws RecognitionException { return gExpr.precedenceAndOperator(); }

	public SQLParser_Expr.dateLiteral_return dateLiteral() throws RecognitionException { return gExpr.dateLiteral(); }

	public SQLParser_Expr.precedencePlusOperator_return precedencePlusOperator() throws RecognitionException { return gExpr.precedencePlusOperator(); }

	public SQLParser_Expr.precedenceOrExpression_return precedenceOrExpression() throws RecognitionException { return gExpr.precedenceOrExpression(); }

	public SQLParser_Expr.atomExpression_return atomExpression() throws RecognitionException { return gExpr.atomExpression(); }

	public SQLParser_Expr.stringLiteralSequence_return stringLiteralSequence() throws RecognitionException { return gExpr.stringLiteralSequence(); }

	public SQLParser_Expr.window_frame_boundary_return window_frame_boundary() throws RecognitionException { return gExpr.window_frame_boundary(); }

	public SQLParser_Expr.precedenceUnaryPrefixExpression_return precedenceUnaryPrefixExpression() throws RecognitionException { return gExpr.precedenceUnaryPrefixExpression(); }

	public SQLParser_Expr.nullCondition_return nullCondition() throws RecognitionException { return gExpr.nullCondition(); }

	public SQLParser_Expr.partitionByClause_return partitionByClause() throws RecognitionException { return gExpr.partitionByClause(); }

	public SQLParser_Expr.expression_return expression() throws RecognitionException { return gExpr.expression(); }

	public SQLParser_Expr.precedenceAmpersandOperator_return precedenceAmpersandOperator() throws RecognitionException { return gExpr.precedenceAmpersandOperator(); }

	public SQLParser_Expr.primitiveType_return primitiveType() throws RecognitionException { return gExpr.primitiveType(); }

	public SQLParser_Expr.window_frame_start_boundary_return window_frame_start_boundary() throws RecognitionException { return gExpr.window_frame_start_boundary(); }

	public SQLParser_Expr.constant_return constant() throws RecognitionException { return gExpr.constant(); }

	public SQLParser_Expr.precedenceNotExpression_return precedenceNotExpression() throws RecognitionException { return gExpr.precedenceNotExpression(); }

	public SQLParser_Expr.window_specification_return window_specification() throws RecognitionException { return gExpr.window_specification(); }

	public SQLParser_Expr.functionName_return functionName() throws RecognitionException { return gExpr.functionName(); }

	public SQLParser_Expr.precedenceAndExpression_return precedenceAndExpression() throws RecognitionException { return gExpr.precedenceAndExpression(); }

	public SQLParser_Expr.window_range_expression_return window_range_expression() throws RecognitionException { return gExpr.window_range_expression(); }

	public SQLParser_Expr.caseExpression_return caseExpression() throws RecognitionException { return gExpr.caseExpression(); }

	public SQLParser_Expr.identifier_return identifier() throws RecognitionException { return gExpr.identifier(); }

	public SQLParser_Expr.precedenceAmpersandExpression_return precedenceAmpersandExpression() throws RecognitionException { return gExpr.precedenceAmpersandExpression(); }

	public SQLParser_Expr.precedenceUnaryOperator_return precedenceUnaryOperator() throws RecognitionException { return gExpr.precedenceUnaryOperator(); }

	public SQLParser_Expr.precedenceFieldExpression_return precedenceFieldExpression() throws RecognitionException { return gExpr.precedenceFieldExpression(); }

	public SQLParser_Expr.precedenceStarExpression_return precedenceStarExpression() throws RecognitionException { return gExpr.precedenceStarExpression(); }

	public SQLParser_Expr.booleanValue_return booleanValue() throws RecognitionException { return gExpr.booleanValue(); }

	public SQLParser_Expr.whenExpression_return whenExpression() throws RecognitionException { return gExpr.whenExpression(); }



	public static final BitSet FOLLOW_statements_in_all421 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_EOF_in_all424 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_statement_in_statements437 = new BitSet(new long[]{0x0030080000000002L,0x0100000010800000L,0x0800000020000000L});
	public static final BitSet FOLLOW_SEMICOLON_in_statements439 = new BitSet(new long[]{0x0030080000000002L,0x0100000010800000L,0x0800000020000000L});
	public static final BitSet FOLLOW_statement_in_statements443 = new BitSet(new long[]{0x0030080000000002L,0x0100000010800000L,0x0800000020000000L});
	public static final BitSet FOLLOW_SEMICOLON_in_statements445 = new BitSet(new long[]{0x0030080000000002L,0x0100000010800000L,0x0800000020000000L});
	public static final BitSet FOLLOW_defineSourceStatement_in_statement471 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_defineTargetStatement_in_statement475 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_overwriteTargetStatement_in_statement479 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createViewStatement_in_statement483 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insertStatement_in_statement487 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_updateStatement_in_statement491 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_deleteStatement_in_statement495 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_invokeStatement_in_statement499 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nullStatement_in_statement503 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SEMICOLON_in_nullStatement516 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_DEFINE_in_defineTableStatement530 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008100L,0x0000020000000000L});
	public static final BitSet FOLLOW_KW_SET_in_defineTableStatement533 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_KW_MULTISET_in_defineTableStatement535 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_KW_TABLE_in_defineTableStatement539 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_tableName_in_defineTableStatement541 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_defineTableStatement544 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_columnName_in_defineTableStatement547 = new BitSet(new long[]{0x0004811080000000L,0x1000000006000100L,0x0000000000300400L,0x6000000000200000L});
	public static final BitSet FOLLOW_dataType_in_defineTableStatement549 = new BitSet(new long[]{0x0000004000400000L,0x0000600000000200L,0x0200010040000000L});
	public static final BitSet FOLLOW_dataAttribute_in_defineTableStatement551 = new BitSet(new long[]{0x0000004000400000L,0x0000600000000200L,0x0200010040000000L});
	public static final BitSet FOLLOW_RSQUARE_in_defineTableStatement558 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_columnName594 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_INTEGER_in_dataType607 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_SMALLINT_in_dataType620 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_BIGINT_in_dataType633 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_BYTEINT_in_dataType646 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_DATE_in_dataType659 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_TIME_in_dataType672 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000410000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_dataType677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType679 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_dataType681 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_KW_WITH_in_dataType686 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_KW_TIME_ZONE_in_dataType688 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_TIMESTAMP_in_dataType706 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000410000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_dataType711 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType713 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_dataType715 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_KW_WITH_in_dataType720 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_KW_TIME_ZONE_in_dataType722 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_INTERVAL_in_dataType740 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_KW_YEAR_in_dataType742 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000400000000000L,0x0400000000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_dataType745 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType747 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_dataType749 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0400000000000000L});
	public static final BitSet FOLLOW_KW_TO_in_dataType754 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_KW_MONTH_in_dataType756 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_INTERVAL_in_dataType775 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_KW_MONTH_in_dataType777 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_dataType780 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType782 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_dataType784 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_INTERVAL_in_dataType804 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_KW_DAY_in_dataType806 = new BitSet(new long[]{0x0000000000000002L,0x0000080000000000L,0x0000400000000020L,0x0400000000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_dataType809 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType811 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_dataType813 = new BitSet(new long[]{0x0000000000000002L,0x0000080000000000L,0x0000000000000020L,0x0400000000000000L});
	public static final BitSet FOLLOW_KW_TO_in_dataType818 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_KW_HOUR_in_dataType820 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_MINUTE_in_dataType822 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_SECOND_in_dataType824 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_INTERVAL_in_dataType845 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_KW_HOUR_in_dataType847 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000400000000020L,0x0400000000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_dataType850 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType852 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_dataType854 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L,0x0400000000000000L});
	public static final BitSet FOLLOW_KW_TO_in_dataType859 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_KW_MINUTE_in_dataType861 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_SECOND_in_dataType863 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_INTERVAL_in_dataType883 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_KW_MINUTE_in_dataType885 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000400000000000L,0x0400000000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_dataType888 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType890 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_dataType892 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000000L,0x0400000000000000L});
	public static final BitSet FOLLOW_KW_TO_in_dataType897 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000004000L});
	public static final BitSet FOLLOW_KE_SECOND_in_dataType899 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_INTERVAL_in_dataType919 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_KW_SECOND_in_dataType921 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_dataType924 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType926 = new BitSet(new long[]{0x0000000000000200L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_COMMA_in_dataType929 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType931 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_dataType935 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_FLOAT_in_dataType955 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_DECIMAL_in_dataType968 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_dataType971 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType973 = new BitSet(new long[]{0x0000000000000200L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_COMMA_in_dataType976 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType978 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_dataType982 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_CHAR_in_dataType1000 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_dataType1003 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType1005 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_dataType1007 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_VARCHAR_in_dataType1025 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_dataType1027 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType1029 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_dataType1031 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_BYTE_in_dataType1046 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_dataType1049 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType1051 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_dataType1053 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_VARBYTE_in_dataType1071 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_dataType1073 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType1075 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_dataType1077 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_PERIOD_in_dataType1092 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_KW_TIME_in_dataType1094 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_PERIOD_in_dataType1110 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_KW_DATE_in_dataType1112 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_PERIOD_in_dataType1127 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_KW_TIMESTAMP_in_dataType1129 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000410000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_dataType1134 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_dataType1136 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_dataType1138 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_KW_WITH_in_dataType1143 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_KW_TIME_ZONE_in_dataType1145 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_UPPERCASE_in_dataAttribute1174 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_NOT_in_dataAttribute1189 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_KW_CASESPECIFIC_in_dataAttribute1193 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_FORMAT_in_dataAttribute1209 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_StringLiteral_in_dataAttribute1212 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_WITH_in_dataAttribute1227 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_KW_DEFAULT_in_dataAttribute1229 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_NOT_in_dataAttribute1243 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_KW_NULL_in_dataAttribute1247 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_DEFINE_in_defineSourceStatement1274 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_KW_SOURCE_in_defineSourceStatement1276 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_tableName_in_defineSourceStatement1280 = new BitSet(new long[]{0x0000000008000000L,0x0000040000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_KW_AS_in_defineSourceStatement1283 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_KW_WITH_in_defineSourceStatement1285 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_KW_META_in_defineSourceStatement1289 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_metaName_in_defineSourceStatement1293 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
	public static final BitSet FOLLOW_KW_PATH_in_defineSourceStatement1295 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_pathString_in_defineSourceStatement1299 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_DEFINE_in_defineTargetStatement1334 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_KW_TARGET_in_defineTargetStatement1336 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_tableName_in_defineTargetStatement1340 = new BitSet(new long[]{0x0000000008000000L,0x0400040000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_KW_AS_in_defineTargetStatement1343 = new BitSet(new long[]{0x0000000000000000L,0x0400040000000000L});
	public static final BitSet FOLLOW_KW_WITH_in_defineTargetStatement1345 = new BitSet(new long[]{0x0000000000000000L,0x0400040000000000L});
	public static final BitSet FOLLOW_KW_META_in_defineTargetStatement1350 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_metaName_in_defineTargetStatement1354 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
	public static final BitSet FOLLOW_KW_PATH_in_defineTargetStatement1358 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_pathString_in_defineTargetStatement1362 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_metaName1403 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DOT_in_metaName1405 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_identifier_in_metaName1409 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_StringLiteral_in_pathString1442 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_OVERWRITE_in_overwriteTargetStatement1468 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_KW_TARGET_in_overwriteTargetStatement1470 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_tableName_in_overwriteTargetStatement1474 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_KW_AS_in_overwriteTargetStatement1476 = new BitSet(new long[]{0x0020000000000000L,0x0000000000800000L,0x0000000020000040L});
	public static final BitSet FOLLOW_queryStatement_in_overwriteTargetStatement1478 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_CREATE_in_createViewStatement1513 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_KW_VIEW_in_createViewStatement1515 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_pipeName_in_createViewStatement1519 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_KW_AS_in_createViewStatement1521 = new BitSet(new long[]{0x0020000000000000L,0x0000000000800000L,0x0000000020000040L});
	public static final BitSet FOLLOW_queryStatement_in_createViewStatement1523 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selectQuery_in_queryStatement1556 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_updateQuery_in_queryStatement1560 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insertQuery_in_queryStatement1564 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_deleteQuery_in_queryStatement1568 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_updateQuery_in_updateStatement1580 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insertQuery_in_insertStatement1602 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_deleteQuery_in_deleteStatement1624 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selectClause_in_selectQuery1646 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_fromClause_in_selectQuery1649 = new BitSet(new long[]{0x0000000000000002L,0x400800000000C000L,0x0000004000000000L});
	public static final BitSet FOLLOW_whereClause_in_selectQuery1652 = new BitSet(new long[]{0x0000000000000002L,0x400800000000C000L});
	public static final BitSet FOLLOW_groupByClause_in_selectQuery1659 = new BitSet(new long[]{0x0000000000000002L,0x4008000000008000L});
	public static final BitSet FOLLOW_havingClause_in_selectQuery1666 = new BitSet(new long[]{0x0000000000000002L,0x4008000000000000L});
	public static final BitSet FOLLOW_orderByClause_in_selectQuery1673 = new BitSet(new long[]{0x0000000000000002L,0x4000000000000000L});
	public static final BitSet FOLLOW_qualifyClause_in_selectQuery1680 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_UPDATE_in_updateQuery1725 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_updateTable_in_updateQuery1729 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_fromClause_in_updateQuery1732 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_KW_SET_in_updateQuery1735 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_setItem_in_updateQuery1737 = new BitSet(new long[]{0x0000000000000202L,0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_COMMA_in_updateQuery1740 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_setItem_in_updateQuery1743 = new BitSet(new long[]{0x0000000000000202L,0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_whereClause_in_updateQuery1749 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_DELETE_in_deleteQuery1786 = new BitSet(new long[]{0x0000000000400000L,0x0000000000000400L});
	public static final BitSet FOLLOW_deleteTable_in_deleteQuery1791 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_fromClause_in_deleteQuery1796 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_whereClause_in_deleteQuery1799 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_INSERT_in_insertQuery1833 = new BitSet(new long[]{0x0000000000400000L,0x0000000008000000L});
	public static final BitSet FOLLOW_KW_INTO_in_insertQuery1836 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_tableName_in_insertQuery1842 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_LPAREN_in_insertQuery1844 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_identifier_in_insertQuery1847 = new BitSet(new long[]{0x0000000000000200L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_COMMA_in_insertQuery1850 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_identifier_in_insertQuery1852 = new BitSet(new long[]{0x0000000000000200L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_insertQuery1857 = new BitSet(new long[]{0x0000000008000000L,0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_KW_AS_in_insertQuery1860 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_selectClause_in_insertQuery1865 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_fromClause_in_insertQuery1869 = new BitSet(new long[]{0x0000000000000002L,0x400800000000C000L,0x0000004000000000L});
	public static final BitSet FOLLOW_whereClause_in_insertQuery1873 = new BitSet(new long[]{0x0000000000000002L,0x400800000000C000L});
	public static final BitSet FOLLOW_groupByClause_in_insertQuery1877 = new BitSet(new long[]{0x0000000000000002L,0x4008000000008000L});
	public static final BitSet FOLLOW_havingClause_in_insertQuery1882 = new BitSet(new long[]{0x0000000000000002L,0x4008000000000000L});
	public static final BitSet FOLLOW_orderByClause_in_insertQuery1886 = new BitSet(new long[]{0x0000000000000002L,0x4000000000000000L});
	public static final BitSet FOLLOW_qualifyClause_in_insertQuery1890 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_setItem1941 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_EQUAL_in_setItem1943 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_setItem1945 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_pipeName1971 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_HAVING_in_havingClause2011 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_havingCondition_in_havingClause2013 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_havingCondition2046 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_ORDER_in_orderByClause2071 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_KW_BY_in_orderByClause2073 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_columnRefOrder_in_orderByClause2075 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_COMMA_in_orderByClause2079 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_columnRefOrder_in_orderByClause2081 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_KW_QUALIFY_in_qualifyClause2117 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_qualifyClause2119 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_columnRefOrder2161 = new BitSet(new long[]{0x0080000010000002L});
	public static final BitSet FOLLOW_KW_ASC_in_columnRefOrder2166 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_DESC_in_columnRefOrder2172 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_SELECT_in_selectClause2236 = new BitSet(new long[]{0x020080A001404000L,0x0000600000000010L,0xE031200001000000L});
	public static final BitSet FOLLOW_KW_ALL_in_selectClause2239 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xE031200001000000L});
	public static final BitSet FOLLOW_KW_DISTINCT_in_selectClause2245 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xE031200001000000L});
	public static final BitSet FOLLOW_selectList_in_selectClause2249 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_GROUP_in_groupByClause2295 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_KW_BY_in_groupByClause2297 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_groupByExpression_in_groupByClause2303 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_COMMA_in_groupByClause2311 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_groupByExpression_in_groupByClause2313 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_expression_in_groupByExpression2355 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selectItem_in_selectList2370 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_COMMA_in_selectList2373 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xE031200001000000L});
	public static final BitSet FOLLOW_selectItem_in_selectList2376 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_selectExpression_in_selectItem2395 = new BitSet(new long[]{0x0000000008400002L});
	public static final BitSet FOLLOW_KW_AS_in_selectItem2398 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_identifier_in_selectItem2401 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_selectExpression2434 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tableAllColumns_in_selectExpression2438 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selectExpression_in_selectExpressionList2459 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_COMMA_in_selectExpressionList2462 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xE031200001000000L});
	public static final BitSet FOLLOW_selectExpression_in_selectExpressionList2464 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_STAR_in_tableAllColumns2491 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tableName_in_tableAllColumns2506 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DOT_in_tableAllColumns2508 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x2000000000000000L});
	public static final BitSet FOLLOW_STAR_in_tableAllColumns2510 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_tableOrColumn2541 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_expressionList2570 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_COMMA_in_expressionList2573 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_expressionList2575 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_KW_FROM_in_fromClause2607 = new BitSet(new long[]{0x0000000000400000L,0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_joinSource_in_fromClause2609 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fromSource_in_joinSource2634 = new BitSet(new long[]{0x0000100000000202L,0x00000000C0200800L,0x0000000000000008L});
	public static final BitSet FOLLOW_joinToken_in_joinSource2638 = new BitSet(new long[]{0x0000000000400000L,0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_fromSource_in_joinSource2641 = new BitSet(new long[]{0x0000100000000202L,0x00010000C0200800L,0x0000000000000008L});
	public static final BitSet FOLLOW_KW_ON_in_joinSource2644 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_joinSource2647 = new BitSet(new long[]{0x0000100000000202L,0x00000000C0200800L,0x0000000000000008L});
	public static final BitSet FOLLOW_KW_JOIN_in_joinToken2675 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_INNER_in_joinToken2708 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_KW_JOIN_in_joinToken2710 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COMMA_in_joinToken2734 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_CROSS_in_joinToken2754 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_KW_JOIN_in_joinToken2756 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_LEFT_in_joinToken2780 = new BitSet(new long[]{0x0000000000000000L,0x0020000040000000L});
	public static final BitSet FOLLOW_KW_OUTER_in_joinToken2784 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_KW_JOIN_in_joinToken2788 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_RIGHT_in_joinToken2800 = new BitSet(new long[]{0x0000000000000000L,0x0020000040000000L});
	public static final BitSet FOLLOW_KW_OUTER_in_joinToken2803 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_KW_JOIN_in_joinToken2807 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_FULL_in_joinToken2819 = new BitSet(new long[]{0x0000000000000000L,0x0020000040000000L});
	public static final BitSet FOLLOW_KW_OUTER_in_joinToken2823 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_KW_JOIN_in_joinToken2827 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_LEFT_in_joinToken2839 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_KW_SEMI_in_joinToken2841 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_KW_JOIN_in_joinToken2843 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tableSource_in_fromSource2873 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subQuerySource_in_fromSource2877 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tableName_in_tableSource2896 = new BitSet(new long[]{0x0000000008400002L});
	public static final BitSet FOLLOW_KW_AS_in_tableSource2899 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_identifier_in_tableSource2904 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tableName_in_updateTable2943 = new BitSet(new long[]{0x0000000008400000L});
	public static final BitSet FOLLOW_KW_AS_in_updateTable2945 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_identifier_in_updateTable2952 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tableName_in_deleteTable2982 = new BitSet(new long[]{0x0000000008400000L});
	public static final BitSet FOLLOW_KW_AS_in_deleteTable2984 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_identifier_in_deleteTable2991 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_tableName3023 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_DOT_in_tableName3025 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_identifier_in_tableName3029 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_tableName3057 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_subQuerySource3089 = new BitSet(new long[]{0x0020000000000000L,0x0000000000800000L,0x0000000020000040L});
	public static final BitSet FOLLOW_subQuery_in_subQuerySource3091 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_subQuerySource3093 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_identifier_in_subQuerySource3095 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_queryStatement_in_subQuery3124 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_WHERE_in_whereClause3164 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_searchCondition_in_whereClause3166 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_searchCondition3195 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DIVIDE_in_hintClause3220 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x2000000000000000L});
	public static final BitSet FOLLOW_STAR_in_hintClause3222 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0020000000000000L});
	public static final BitSet FOLLOW_PLUS_in_hintClause3224 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L,0x0000000000000000L,0x0040000800000000L});
	public static final BitSet FOLLOW_hintList_in_hintClause3226 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x2000000000000000L});
	public static final BitSet FOLLOW_STAR_in_hintClause3228 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_DIVIDE_in_hintClause3230 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_hintItem_in_hintList3259 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_COMMA_in_hintList3262 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L,0x0000000000000000L,0x0040000800000000L});
	public static final BitSet FOLLOW_hintItem_in_hintList3264 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_hintName_in_hintItem3293 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_LPAREN_in_hintItem3296 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_hintArgs_in_hintItem3298 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_hintItem3300 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_MAPJOIN_in_hintName3334 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_STREAMTABLE_in_hintName3346 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_HOLD_DDLTIME_in_hintName3358 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_hintArgName_in_hintArgs3383 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_COMMA_in_hintArgs3386 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_hintArgName_in_hintArgs3388 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_identifier_in_hintArgName3420 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_INVOKE_in_invokeStatement3459 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_Identifier_in_invokeStatement3461 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_LPAREN_in_invokeStatement3463 = new BitSet(new long[]{0x0000000000400000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_parameters_in_invokeStatement3465 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_invokeStatement3468 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_parameter_in_parameters3505 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_parameters3507 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_parameter_in_parameters3509 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_Identifier_in_parameter3550 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COLON_in_parameter3552 = new BitSet(new long[]{0x0000000000400080L,0x0000000000000000L,0x4010400000000000L});
	public static final BitSet FOLLOW_parameterValue_in_parameter3554 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_paramValIdentifier_in_parameterValue3595 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_parameterArray_in_parameterValue3599 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LSQUARE_in_parameterArray3629 = new BitSet(new long[]{0x0000000000400080L,0x0000000000000000L,0x4210400000000000L});
	public static final BitSet FOLLOW_parameterValue_in_parameterArray3632 = new BitSet(new long[]{0x0000000000000200L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_COMMA_in_parameterArray3634 = new BitSet(new long[]{0x0000000000400080L,0x0000000000000000L,0x4010400000000000L});
	public static final BitSet FOLLOW_parameterValue_in_parameterArray3636 = new BitSet(new long[]{0x0000000000000200L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_parameterArray3643 = new BitSet(new long[]{0x0000000000000002L});
}
