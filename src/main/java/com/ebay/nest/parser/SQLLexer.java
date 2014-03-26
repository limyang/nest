// $ANTLR 3.5 SQLLexer.g 2013-11-27 01:50:14
package com.ebay.nest.parser;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class SQLLexer extends Lexer {
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

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public SQLLexer() {} 
	public SQLLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public SQLLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "SQLLexer.g"; }

	// $ANTLR start "KW_TRUE"
	public final void mKW_TRUE() throws RecognitionException {
		try {
			int _type = KW_TRUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:5:9: ( 'TRUE' )
			// SQLLexer.g:5:11: 'TRUE'
			{
			match("TRUE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_TRUE"

	// $ANTLR start "KW_FALSE"
	public final void mKW_FALSE() throws RecognitionException {
		try {
			int _type = KW_FALSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:6:10: ( 'FALSE' )
			// SQLLexer.g:6:12: 'FALSE'
			{
			match("FALSE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_FALSE"

	// $ANTLR start "KW_ALL"
	public final void mKW_ALL() throws RecognitionException {
		try {
			int _type = KW_ALL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:7:8: ( 'ALL' )
			// SQLLexer.g:7:10: 'ALL'
			{
			match("ALL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_ALL"

	// $ANTLR start "KW_AND"
	public final void mKW_AND() throws RecognitionException {
		try {
			int _type = KW_AND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:8:8: ( 'AND' )
			// SQLLexer.g:8:10: 'AND'
			{
			match("AND"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_AND"

	// $ANTLR start "KW_OR"
	public final void mKW_OR() throws RecognitionException {
		try {
			int _type = KW_OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:9:7: ( 'OR' )
			// SQLLexer.g:9:9: 'OR'
			{
			match("OR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_OR"

	// $ANTLR start "KW_NOT"
	public final void mKW_NOT() throws RecognitionException {
		try {
			int _type = KW_NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:10:8: ( 'NOT' | '!' )
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0=='N') ) {
				alt1=1;
			}
			else if ( (LA1_0=='!') ) {
				alt1=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}

			switch (alt1) {
				case 1 :
					// SQLLexer.g:10:10: 'NOT'
					{
					match("NOT"); 

					}
					break;
				case 2 :
					// SQLLexer.g:10:18: '!'
					{
					match('!'); 
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_NOT"

	// $ANTLR start "KW_LIKE"
	public final void mKW_LIKE() throws RecognitionException {
		try {
			int _type = KW_LIKE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:11:9: ( 'LIKE' )
			// SQLLexer.g:11:11: 'LIKE'
			{
			match("LIKE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_LIKE"

	// $ANTLR start "KW_IF"
	public final void mKW_IF() throws RecognitionException {
		try {
			int _type = KW_IF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:13:7: ( 'IF' )
			// SQLLexer.g:13:9: 'IF'
			{
			match("IF"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_IF"

	// $ANTLR start "KW_EXISTS"
	public final void mKW_EXISTS() throws RecognitionException {
		try {
			int _type = KW_EXISTS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:14:11: ( 'EXISTS' )
			// SQLLexer.g:14:13: 'EXISTS'
			{
			match("EXISTS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_EXISTS"

	// $ANTLR start "KW_ASC"
	public final void mKW_ASC() throws RecognitionException {
		try {
			int _type = KW_ASC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:16:8: ( 'ASC' )
			// SQLLexer.g:16:10: 'ASC'
			{
			match("ASC"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_ASC"

	// $ANTLR start "KW_DESC"
	public final void mKW_DESC() throws RecognitionException {
		try {
			int _type = KW_DESC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:17:9: ( 'DESC' )
			// SQLLexer.g:17:11: 'DESC'
			{
			match("DESC"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_DESC"

	// $ANTLR start "KW_ORDER"
	public final void mKW_ORDER() throws RecognitionException {
		try {
			int _type = KW_ORDER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:18:10: ( 'ORDER' )
			// SQLLexer.g:18:12: 'ORDER'
			{
			match("ORDER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_ORDER"

	// $ANTLR start "KW_GROUP"
	public final void mKW_GROUP() throws RecognitionException {
		try {
			int _type = KW_GROUP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:19:10: ( 'GROUP' )
			// SQLLexer.g:19:12: 'GROUP'
			{
			match("GROUP"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_GROUP"

	// $ANTLR start "KW_BY"
	public final void mKW_BY() throws RecognitionException {
		try {
			int _type = KW_BY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:20:7: ( 'BY' )
			// SQLLexer.g:20:9: 'BY'
			{
			match("BY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_BY"

	// $ANTLR start "KW_HAVING"
	public final void mKW_HAVING() throws RecognitionException {
		try {
			int _type = KW_HAVING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:21:11: ( 'HAVING' )
			// SQLLexer.g:21:13: 'HAVING'
			{
			match("HAVING"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_HAVING"

	// $ANTLR start "KW_WHERE"
	public final void mKW_WHERE() throws RecognitionException {
		try {
			int _type = KW_WHERE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:22:10: ( 'WHERE' )
			// SQLLexer.g:22:12: 'WHERE'
			{
			match("WHERE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_WHERE"

	// $ANTLR start "KW_FROM"
	public final void mKW_FROM() throws RecognitionException {
		try {
			int _type = KW_FROM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:23:9: ( 'FROM' )
			// SQLLexer.g:23:11: 'FROM'
			{
			match("FROM"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_FROM"

	// $ANTLR start "KW_AS"
	public final void mKW_AS() throws RecognitionException {
		try {
			int _type = KW_AS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:24:7: ( 'AS' )
			// SQLLexer.g:24:9: 'AS'
			{
			match("AS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_AS"

	// $ANTLR start "KW_SELECT"
	public final void mKW_SELECT() throws RecognitionException {
		try {
			int _type = KW_SELECT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:25:11: ( 'SELECT' )
			// SQLLexer.g:25:13: 'SELECT'
			{
			match("SELECT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_SELECT"

	// $ANTLR start "KW_DISTINCT"
	public final void mKW_DISTINCT() throws RecognitionException {
		try {
			int _type = KW_DISTINCT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:26:13: ( 'DISTINCT' )
			// SQLLexer.g:26:15: 'DISTINCT'
			{
			match("DISTINCT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_DISTINCT"

	// $ANTLR start "KW_INSERT"
	public final void mKW_INSERT() throws RecognitionException {
		try {
			int _type = KW_INSERT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:27:11: ( 'INSERT' )
			// SQLLexer.g:27:13: 'INSERT'
			{
			match("INSERT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_INSERT"

	// $ANTLR start "KW_OVERWRITE"
	public final void mKW_OVERWRITE() throws RecognitionException {
		try {
			int _type = KW_OVERWRITE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:28:14: ( 'OVERWRITE' )
			// SQLLexer.g:28:16: 'OVERWRITE'
			{
			match("OVERWRITE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_OVERWRITE"

	// $ANTLR start "KW_JOIN"
	public final void mKW_JOIN() throws RecognitionException {
		try {
			int _type = KW_JOIN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:29:9: ( 'JOIN' )
			// SQLLexer.g:29:11: 'JOIN'
			{
			match("JOIN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_JOIN"

	// $ANTLR start "KW_LEFT"
	public final void mKW_LEFT() throws RecognitionException {
		try {
			int _type = KW_LEFT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:30:9: ( 'LEFT' )
			// SQLLexer.g:30:11: 'LEFT'
			{
			match("LEFT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_LEFT"

	// $ANTLR start "KW_RIGHT"
	public final void mKW_RIGHT() throws RecognitionException {
		try {
			int _type = KW_RIGHT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:31:10: ( 'RIGHT' )
			// SQLLexer.g:31:12: 'RIGHT'
			{
			match("RIGHT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_RIGHT"

	// $ANTLR start "KW_FULL"
	public final void mKW_FULL() throws RecognitionException {
		try {
			int _type = KW_FULL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:32:9: ( 'FULL' )
			// SQLLexer.g:32:11: 'FULL'
			{
			match("FULL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_FULL"

	// $ANTLR start "KW_ON"
	public final void mKW_ON() throws RecognitionException {
		try {
			int _type = KW_ON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:33:7: ( 'ON' )
			// SQLLexer.g:33:9: 'ON'
			{
			match("ON"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_ON"

	// $ANTLR start "KW_PARTITION"
	public final void mKW_PARTITION() throws RecognitionException {
		try {
			int _type = KW_PARTITION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:34:14: ( 'PARTITION' )
			// SQLLexer.g:34:16: 'PARTITION'
			{
			match("PARTITION"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_PARTITION"

	// $ANTLR start "KW_TABLE"
	public final void mKW_TABLE() throws RecognitionException {
		try {
			int _type = KW_TABLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:35:9: ( 'TABLE' )
			// SQLLexer.g:35:11: 'TABLE'
			{
			match("TABLE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_TABLE"

	// $ANTLR start "KW_INDEX"
	public final void mKW_INDEX() throws RecognitionException {
		try {
			int _type = KW_INDEX;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:36:9: ( 'INDEX' )
			// SQLLexer.g:36:11: 'INDEX'
			{
			match("INDEX"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_INDEX"

	// $ANTLR start "KW_USING"
	public final void mKW_USING() throws RecognitionException {
		try {
			int _type = KW_USING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:37:9: ( 'USING' )
			// SQLLexer.g:37:11: 'USING'
			{
			match("USING"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_USING"

	// $ANTLR start "KW_DISTRIBUTE"
	public final void mKW_DISTRIBUTE() throws RecognitionException {
		try {
			int _type = KW_DISTRIBUTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:38:14: ( 'DISTRIBUTE' )
			// SQLLexer.g:38:16: 'DISTRIBUTE'
			{
			match("DISTRIBUTE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_DISTRIBUTE"

	// $ANTLR start "KW_SORT"
	public final void mKW_SORT() throws RecognitionException {
		try {
			int _type = KW_SORT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:39:8: ( 'SORT' )
			// SQLLexer.g:39:10: 'SORT'
			{
			match("SORT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_SORT"

	// $ANTLR start "KW_UNION"
	public final void mKW_UNION() throws RecognitionException {
		try {
			int _type = KW_UNION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:40:9: ( 'UNION' )
			// SQLLexer.g:40:11: 'UNION'
			{
			match("UNION"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_UNION"

	// $ANTLR start "KW_LOAD"
	public final void mKW_LOAD() throws RecognitionException {
		try {
			int _type = KW_LOAD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:41:8: ( 'LOAD' )
			// SQLLexer.g:41:10: 'LOAD'
			{
			match("LOAD"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_LOAD"

	// $ANTLR start "KW_EXPORT"
	public final void mKW_EXPORT() throws RecognitionException {
		try {
			int _type = KW_EXPORT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:42:10: ( 'EXPORT' )
			// SQLLexer.g:42:12: 'EXPORT'
			{
			match("EXPORT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_EXPORT"

	// $ANTLR start "KW_IMPORT"
	public final void mKW_IMPORT() throws RecognitionException {
		try {
			int _type = KW_IMPORT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:43:10: ( 'IMPORT' )
			// SQLLexer.g:43:12: 'IMPORT'
			{
			match("IMPORT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_IMPORT"

	// $ANTLR start "KW_IS"
	public final void mKW_IS() throws RecognitionException {
		try {
			int _type = KW_IS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:44:6: ( 'IS' )
			// SQLLexer.g:44:8: 'IS'
			{
			match("IS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_IS"

	// $ANTLR start "KW_NULL"
	public final void mKW_NULL() throws RecognitionException {
		try {
			int _type = KW_NULL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:45:8: ( 'NULL' )
			// SQLLexer.g:45:10: 'NULL'
			{
			match("NULL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_NULL"

	// $ANTLR start "KW_CREATE"
	public final void mKW_CREATE() throws RecognitionException {
		try {
			int _type = KW_CREATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:46:10: ( 'CREATE' )
			// SQLLexer.g:46:12: 'CREATE'
			{
			match("CREATE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_CREATE"

	// $ANTLR start "KW_BOOLEAN"
	public final void mKW_BOOLEAN() throws RecognitionException {
		try {
			int _type = KW_BOOLEAN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:47:11: ( 'BOOLEAN' )
			// SQLLexer.g:47:13: 'BOOLEAN'
			{
			match("BOOLEAN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_BOOLEAN"

	// $ANTLR start "KW_INTEGER"
	public final void mKW_INTEGER() throws RecognitionException {
		try {
			int _type = KW_INTEGER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:48:11: ( 'INTEGER' )
			// SQLLexer.g:48:13: 'INTEGER'
			{
			match("INTEGER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_INTEGER"

	// $ANTLR start "KW_FLOAT"
	public final void mKW_FLOAT() throws RecognitionException {
		try {
			int _type = KW_FLOAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:49:9: ( 'FLOAT' )
			// SQLLexer.g:49:11: 'FLOAT'
			{
			match("FLOAT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_FLOAT"

	// $ANTLR start "KW_DOUBLE"
	public final void mKW_DOUBLE() throws RecognitionException {
		try {
			int _type = KW_DOUBLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:50:10: ( 'DOUBLE' )
			// SQLLexer.g:50:12: 'DOUBLE'
			{
			match("DOUBLE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_DOUBLE"

	// $ANTLR start "KW_DATE"
	public final void mKW_DATE() throws RecognitionException {
		try {
			int _type = KW_DATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:51:8: ( 'DATE' )
			// SQLLexer.g:51:10: 'DATE'
			{
			match("DATE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_DATE"

	// $ANTLR start "KW_DATETIME"
	public final void mKW_DATETIME() throws RecognitionException {
		try {
			int _type = KW_DATETIME;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:52:12: ( 'DATETIME' )
			// SQLLexer.g:52:14: 'DATETIME'
			{
			match("DATETIME"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_DATETIME"

	// $ANTLR start "KW_TIME"
	public final void mKW_TIME() throws RecognitionException {
		try {
			int _type = KW_TIME;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:53:8: ( 'TIME' )
			// SQLLexer.g:53:10: 'TIME'
			{
			match("TIME"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_TIME"

	// $ANTLR start "KW_TIMESTAMP"
	public final void mKW_TIMESTAMP() throws RecognitionException {
		try {
			int _type = KW_TIMESTAMP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:54:13: ( 'TIMESTAMP' )
			// SQLLexer.g:54:15: 'TIMESTAMP'
			{
			match("TIMESTAMP"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_TIMESTAMP"

	// $ANTLR start "KW_DECIMAL"
	public final void mKW_DECIMAL() throws RecognitionException {
		try {
			int _type = KW_DECIMAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:55:11: ( 'DECIMAL' )
			// SQLLexer.g:55:13: 'DECIMAL'
			{
			match("DECIMAL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_DECIMAL"

	// $ANTLR start "KW_STRING"
	public final void mKW_STRING() throws RecognitionException {
		try {
			int _type = KW_STRING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:56:10: ( 'STRING' )
			// SQLLexer.g:56:12: 'STRING'
			{
			match("STRING"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_STRING"

	// $ANTLR start "KW_ARRAY"
	public final void mKW_ARRAY() throws RecognitionException {
		try {
			int _type = KW_ARRAY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:57:9: ( 'ARRAY' )
			// SQLLexer.g:57:11: 'ARRAY'
			{
			match("ARRAY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_ARRAY"

	// $ANTLR start "KW_STRUCT"
	public final void mKW_STRUCT() throws RecognitionException {
		try {
			int _type = KW_STRUCT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:58:10: ( 'STRUCT' )
			// SQLLexer.g:58:12: 'STRUCT'
			{
			match("STRUCT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_STRUCT"

	// $ANTLR start "KW_MAP"
	public final void mKW_MAP() throws RecognitionException {
		try {
			int _type = KW_MAP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:59:7: ( 'MAP' )
			// SQLLexer.g:59:9: 'MAP'
			{
			match("MAP"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_MAP"

	// $ANTLR start "KW_INTO"
	public final void mKW_INTO() throws RecognitionException {
		try {
			int _type = KW_INTO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:60:8: ( 'INTO' )
			// SQLLexer.g:60:10: 'INTO'
			{
			match("INTO"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_INTO"

	// $ANTLR start "KW_FORMAT"
	public final void mKW_FORMAT() throws RecognitionException {
		try {
			int _type = KW_FORMAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:61:10: ( 'FORMAT' )
			// SQLLexer.g:61:12: 'FORMAT'
			{
			match("FORMAT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_FORMAT"

	// $ANTLR start "KW_DELIMITED"
	public final void mKW_DELIMITED() throws RecognitionException {
		try {
			int _type = KW_DELIMITED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:62:13: ( 'DELIMITED' )
			// SQLLexer.g:62:15: 'DELIMITED'
			{
			match("DELIMITED"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_DELIMITED"

	// $ANTLR start "KW_FIELDS"
	public final void mKW_FIELDS() throws RecognitionException {
		try {
			int _type = KW_FIELDS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:63:10: ( 'FIELDS' )
			// SQLLexer.g:63:12: 'FIELDS'
			{
			match("FIELDS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_FIELDS"

	// $ANTLR start "KW_TERMINATED"
	public final void mKW_TERMINATED() throws RecognitionException {
		try {
			int _type = KW_TERMINATED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:64:14: ( 'TERMINATED' )
			// SQLLexer.g:64:16: 'TERMINATED'
			{
			match("TERMINATED"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_TERMINATED"

	// $ANTLR start "KW_ESCAPED"
	public final void mKW_ESCAPED() throws RecognitionException {
		try {
			int _type = KW_ESCAPED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:65:11: ( 'ESCAPED' )
			// SQLLexer.g:65:13: 'ESCAPED'
			{
			match("ESCAPED"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_ESCAPED"

	// $ANTLR start "KW_FILEFORMAT"
	public final void mKW_FILEFORMAT() throws RecognitionException {
		try {
			int _type = KW_FILEFORMAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:66:14: ( 'FILEFORMAT' )
			// SQLLexer.g:66:16: 'FILEFORMAT'
			{
			match("FILEFORMAT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_FILEFORMAT"

	// $ANTLR start "KW_INPUTFORMAT"
	public final void mKW_INPUTFORMAT() throws RecognitionException {
		try {
			int _type = KW_INPUTFORMAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:67:15: ( 'INPUTFORMAT' )
			// SQLLexer.g:67:17: 'INPUTFORMAT'
			{
			match("INPUTFORMAT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_INPUTFORMAT"

	// $ANTLR start "KW_OUTPUTFORMAT"
	public final void mKW_OUTPUTFORMAT() throws RecognitionException {
		try {
			int _type = KW_OUTPUTFORMAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:68:16: ( 'OUTPUTFORMAT' )
			// SQLLexer.g:68:18: 'OUTPUTFORMAT'
			{
			match("OUTPUTFORMAT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_OUTPUTFORMAT"

	// $ANTLR start "KW_ENABLE"
	public final void mKW_ENABLE() throws RecognitionException {
		try {
			int _type = KW_ENABLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:69:10: ( 'ENABLE' )
			// SQLLexer.g:69:12: 'ENABLE'
			{
			match("ENABLE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_ENABLE"

	// $ANTLR start "KW_DISABLE"
	public final void mKW_DISABLE() throws RecognitionException {
		try {
			int _type = KW_DISABLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:70:11: ( 'DISABLE' )
			// SQLLexer.g:70:13: 'DISABLE'
			{
			match("DISABLE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_DISABLE"

	// $ANTLR start "KW_READONLY"
	public final void mKW_READONLY() throws RecognitionException {
		try {
			int _type = KW_READONLY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:71:12: ( 'READONLY' )
			// SQLLexer.g:71:14: 'READONLY'
			{
			match("READONLY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_READONLY"

	// $ANTLR start "KW_LOCATION"
	public final void mKW_LOCATION() throws RecognitionException {
		try {
			int _type = KW_LOCATION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:72:12: ( 'LOCATION' )
			// SQLLexer.g:72:14: 'LOCATION'
			{
			match("LOCATION"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_LOCATION"

	// $ANTLR start "KW_OUT"
	public final void mKW_OUT() throws RecognitionException {
		try {
			int _type = KW_OUT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:73:7: ( 'OUT' )
			// SQLLexer.g:73:9: 'OUT'
			{
			match("OUT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_OUT"

	// $ANTLR start "KW_OF"
	public final void mKW_OF() throws RecognitionException {
		try {
			int _type = KW_OF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:74:6: ( 'OF' )
			// SQLLexer.g:74:8: 'OF'
			{
			match("OF"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_OF"

	// $ANTLR start "KW_PERCENT"
	public final void mKW_PERCENT() throws RecognitionException {
		try {
			int _type = KW_PERCENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:75:11: ( 'PERCENT' )
			// SQLLexer.g:75:13: 'PERCENT'
			{
			match("PERCENT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_PERCENT"

	// $ANTLR start "KW_CAST"
	public final void mKW_CAST() throws RecognitionException {
		try {
			int _type = KW_CAST;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:76:8: ( 'CAST' )
			// SQLLexer.g:76:10: 'CAST'
			{
			match("CAST"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_CAST"

	// $ANTLR start "KW_ADD"
	public final void mKW_ADD() throws RecognitionException {
		try {
			int _type = KW_ADD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:77:7: ( 'ADD' )
			// SQLLexer.g:77:9: 'ADD'
			{
			match("ADD"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_ADD"

	// $ANTLR start "KW_VOLATILE"
	public final void mKW_VOLATILE() throws RecognitionException {
		try {
			int _type = KW_VOLATILE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:78:12: ( 'VOLATILE' )
			// SQLLexer.g:78:14: 'VOLATILE'
			{
			match("VOLATILE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_VOLATILE"

	// $ANTLR start "KW_TEMPORARY"
	public final void mKW_TEMPORARY() throws RecognitionException {
		try {
			int _type = KW_TEMPORARY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:79:13: ( 'TEMPORARY' )
			// SQLLexer.g:79:15: 'TEMPORARY'
			{
			match("TEMPORARY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_TEMPORARY"

	// $ANTLR start "KW_FUNCTION"
	public final void mKW_FUNCTION() throws RecognitionException {
		try {
			int _type = KW_FUNCTION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:80:12: ( 'FUNCTION' )
			// SQLLexer.g:80:14: 'FUNCTION'
			{
			match("FUNCTION"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_FUNCTION"

	// $ANTLR start "KW_MACRO"
	public final void mKW_MACRO() throws RecognitionException {
		try {
			int _type = KW_MACRO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:81:9: ( 'MACRO' )
			// SQLLexer.g:81:11: 'MACRO'
			{
			match("MACRO"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_MACRO"

	// $ANTLR start "KW_EXPLAIN"
	public final void mKW_EXPLAIN() throws RecognitionException {
		try {
			int _type = KW_EXPLAIN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:82:11: ( 'EXPLAIN' )
			// SQLLexer.g:82:13: 'EXPLAIN'
			{
			match("EXPLAIN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_EXPLAIN"

	// $ANTLR start "KW_WITH"
	public final void mKW_WITH() throws RecognitionException {
		try {
			int _type = KW_WITH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:83:8: ( 'WITH' )
			// SQLLexer.g:83:10: 'WITH'
			{
			match("WITH"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_WITH"

	// $ANTLR start "KW_LIMIT"
	public final void mKW_LIMIT() throws RecognitionException {
		try {
			int _type = KW_LIMIT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:84:9: ( 'LIMIT' )
			// SQLLexer.g:84:11: 'LIMIT'
			{
			match("LIMIT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_LIMIT"

	// $ANTLR start "KW_SET"
	public final void mKW_SET() throws RecognitionException {
		try {
			int _type = KW_SET;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:85:7: ( 'SET' )
			// SQLLexer.g:85:9: 'SET'
			{
			match("SET"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_SET"

	// $ANTLR start "KW_CASE"
	public final void mKW_CASE() throws RecognitionException {
		try {
			int _type = KW_CASE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:86:8: ( 'CASE' )
			// SQLLexer.g:86:10: 'CASE'
			{
			match("CASE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_CASE"

	// $ANTLR start "KW_WHEN"
	public final void mKW_WHEN() throws RecognitionException {
		try {
			int _type = KW_WHEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:87:8: ( 'WHEN' )
			// SQLLexer.g:87:10: 'WHEN'
			{
			match("WHEN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_WHEN"

	// $ANTLR start "KW_THEN"
	public final void mKW_THEN() throws RecognitionException {
		try {
			int _type = KW_THEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:88:8: ( 'THEN' )
			// SQLLexer.g:88:10: 'THEN'
			{
			match("THEN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_THEN"

	// $ANTLR start "KW_ELSE"
	public final void mKW_ELSE() throws RecognitionException {
		try {
			int _type = KW_ELSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:89:8: ( 'ELSE' )
			// SQLLexer.g:89:10: 'ELSE'
			{
			match("ELSE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_ELSE"

	// $ANTLR start "KW_END"
	public final void mKW_END() throws RecognitionException {
		try {
			int _type = KW_END;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:90:7: ( 'END' )
			// SQLLexer.g:90:9: 'END'
			{
			match("END"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_END"

	// $ANTLR start "KW_MAPJOIN"
	public final void mKW_MAPJOIN() throws RecognitionException {
		try {
			int _type = KW_MAPJOIN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:91:11: ( 'MAPJOIN' )
			// SQLLexer.g:91:13: 'MAPJOIN'
			{
			match("MAPJOIN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_MAPJOIN"

	// $ANTLR start "KW_UTC"
	public final void mKW_UTC() throws RecognitionException {
		try {
			int _type = KW_UTC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:92:7: ( 'UTC' )
			// SQLLexer.g:92:9: 'UTC'
			{
			match("UTC"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_UTC"

	// $ANTLR start "KW_UTCTIMESTAMP"
	public final void mKW_UTCTIMESTAMP() throws RecognitionException {
		try {
			int _type = KW_UTCTIMESTAMP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:93:16: ( 'UTC_TMESTAMP' )
			// SQLLexer.g:93:18: 'UTC_TMESTAMP'
			{
			match("UTC_TMESTAMP"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_UTCTIMESTAMP"

	// $ANTLR start "KW_LONG"
	public final void mKW_LONG() throws RecognitionException {
		try {
			int _type = KW_LONG;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:94:8: ( 'LONG' )
			// SQLLexer.g:94:10: 'LONG'
			{
			match("LONG"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_LONG"

	// $ANTLR start "KW_DELETE"
	public final void mKW_DELETE() throws RecognitionException {
		try {
			int _type = KW_DELETE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:95:10: ( 'DELETE' )
			// SQLLexer.g:95:12: 'DELETE'
			{
			match("DELETE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_DELETE"

	// $ANTLR start "KW_FETCH"
	public final void mKW_FETCH() throws RecognitionException {
		try {
			int _type = KW_FETCH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:96:9: ( 'FETCH' )
			// SQLLexer.g:96:11: 'FETCH'
			{
			match("FETCH"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_FETCH"

	// $ANTLR start "KW_VIEW"
	public final void mKW_VIEW() throws RecognitionException {
		try {
			int _type = KW_VIEW;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:97:8: ( 'VIEW' )
			// SQLLexer.g:97:10: 'VIEW'
			{
			match("VIEW"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_VIEW"

	// $ANTLR start "KW_IN"
	public final void mKW_IN() throws RecognitionException {
		try {
			int _type = KW_IN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:98:6: ( 'IN' )
			// SQLLexer.g:98:8: 'IN'
			{
			match("IN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_IN"

	// $ANTLR start "KW_DATABASE"
	public final void mKW_DATABASE() throws RecognitionException {
		try {
			int _type = KW_DATABASE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:99:12: ( 'DATABASE' )
			// SQLLexer.g:99:14: 'DATABASE'
			{
			match("DATABASE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_DATABASE"

	// $ANTLR start "KW_GRANT"
	public final void mKW_GRANT() throws RecognitionException {
		try {
			int _type = KW_GRANT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:100:9: ( 'GRANT' )
			// SQLLexer.g:100:11: 'GRANT'
			{
			match("GRANT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_GRANT"

	// $ANTLR start "KW_REVOKE"
	public final void mKW_REVOKE() throws RecognitionException {
		try {
			int _type = KW_REVOKE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:101:10: ( 'REVOKE' )
			// SQLLexer.g:101:12: 'REVOKE'
			{
			match("REVOKE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_REVOKE"

	// $ANTLR start "KW_UNDO"
	public final void mKW_UNDO() throws RecognitionException {
		try {
			int _type = KW_UNDO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:102:8: ( 'UNDO' )
			// SQLLexer.g:102:10: 'UNDO'
			{
			match("UNDO"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_UNDO"

	// $ANTLR start "KW_LOCK"
	public final void mKW_LOCK() throws RecognitionException {
		try {
			int _type = KW_LOCK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:103:8: ( 'LOCK' )
			// SQLLexer.g:103:10: 'LOCK'
			{
			match("LOCK"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_LOCK"

	// $ANTLR start "KW_LOCKS"
	public final void mKW_LOCKS() throws RecognitionException {
		try {
			int _type = KW_LOCKS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:104:9: ( 'LOCKS' )
			// SQLLexer.g:104:11: 'LOCKS'
			{
			match("LOCKS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_LOCKS"

	// $ANTLR start "KW_UNLOCK"
	public final void mKW_UNLOCK() throws RecognitionException {
		try {
			int _type = KW_UNLOCK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:105:10: ( 'UNLOCK' )
			// SQLLexer.g:105:12: 'UNLOCK'
			{
			match("UNLOCK"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_UNLOCK"

	// $ANTLR start "KW_SHARED"
	public final void mKW_SHARED() throws RecognitionException {
		try {
			int _type = KW_SHARED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:106:10: ( 'SHARED' )
			// SQLLexer.g:106:12: 'SHARED'
			{
			match("SHARED"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_SHARED"

	// $ANTLR start "KW_EXCLUSIVE"
	public final void mKW_EXCLUSIVE() throws RecognitionException {
		try {
			int _type = KW_EXCLUSIVE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:107:13: ( 'EXCLUSIVE' )
			// SQLLexer.g:107:15: 'EXCLUSIVE'
			{
			match("EXCLUSIVE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_EXCLUSIVE"

	// $ANTLR start "KW_PROCEDURE"
	public final void mKW_PROCEDURE() throws RecognitionException {
		try {
			int _type = KW_PROCEDURE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:108:13: ( 'PROCEDURE' )
			// SQLLexer.g:108:15: 'PROCEDURE'
			{
			match("PROCEDURE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_PROCEDURE"

	// $ANTLR start "KW_UNSIGNED"
	public final void mKW_UNSIGNED() throws RecognitionException {
		try {
			int _type = KW_UNSIGNED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:109:12: ( 'UNSIGNED' )
			// SQLLexer.g:109:14: 'UNSIGNED'
			{
			match("UNSIGNED"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_UNSIGNED"

	// $ANTLR start "KW_WHILE"
	public final void mKW_WHILE() throws RecognitionException {
		try {
			int _type = KW_WHILE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:110:9: ( 'WHILE' )
			// SQLLexer.g:110:11: 'WHILE'
			{
			match("WHILE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_WHILE"

	// $ANTLR start "KW_READ"
	public final void mKW_READ() throws RecognitionException {
		try {
			int _type = KW_READ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:111:8: ( 'READ' )
			// SQLLexer.g:111:10: 'READ'
			{
			match("READ"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_READ"

	// $ANTLR start "KW_RANGE"
	public final void mKW_RANGE() throws RecognitionException {
		try {
			int _type = KW_RANGE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:112:9: ( 'RANGE' )
			// SQLLexer.g:112:11: 'RANGE'
			{
			match("RANGE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_RANGE"

	// $ANTLR start "KW_BEFORE"
	public final void mKW_BEFORE() throws RecognitionException {
		try {
			int _type = KW_BEFORE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:113:10: ( 'BEFORE' )
			// SQLLexer.g:113:12: 'BEFORE'
			{
			match("BEFORE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_BEFORE"

	// $ANTLR start "KW_BETWEEN"
	public final void mKW_BETWEEN() throws RecognitionException {
		try {
			int _type = KW_BETWEEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:114:11: ( 'BETWEEN' )
			// SQLLexer.g:114:13: 'BETWEEN'
			{
			match("BETWEEN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_BETWEEN"

	// $ANTLR start "KW_BOTH"
	public final void mKW_BOTH() throws RecognitionException {
		try {
			int _type = KW_BOTH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:115:8: ( 'BOTH' )
			// SQLLexer.g:115:10: 'BOTH'
			{
			match("BOTH"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_BOTH"

	// $ANTLR start "KW_BINARY"
	public final void mKW_BINARY() throws RecognitionException {
		try {
			int _type = KW_BINARY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:116:10: ( 'BINARY' )
			// SQLLexer.g:116:12: 'BINARY'
			{
			match("BINARY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_BINARY"

	// $ANTLR start "KW_CROSS"
	public final void mKW_CROSS() throws RecognitionException {
		try {
			int _type = KW_CROSS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:117:9: ( 'CROSS' )
			// SQLLexer.g:117:11: 'CROSS'
			{
			match("CROSS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_CROSS"

	// $ANTLR start "KW_CONTINUE"
	public final void mKW_CONTINUE() throws RecognitionException {
		try {
			int _type = KW_CONTINUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:118:12: ( 'CONTINUE' )
			// SQLLexer.g:118:14: 'CONTINUE'
			{
			match("CONTINUE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_CONTINUE"

	// $ANTLR start "KW_CURSOR"
	public final void mKW_CURSOR() throws RecognitionException {
		try {
			int _type = KW_CURSOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:119:10: ( 'CURSOR' )
			// SQLLexer.g:119:12: 'CURSOR'
			{
			match("CURSOR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_CURSOR"

	// $ANTLR start "KW_TRIGGER"
	public final void mKW_TRIGGER() throws RecognitionException {
		try {
			int _type = KW_TRIGGER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:120:11: ( 'TRIGGER' )
			// SQLLexer.g:120:13: 'TRIGGER'
			{
			match("TRIGGER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_TRIGGER"

	// $ANTLR start "KW_SEMI"
	public final void mKW_SEMI() throws RecognitionException {
		try {
			int _type = KW_SEMI;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:121:8: ( 'SEMI' )
			// SQLLexer.g:121:10: 'SEMI'
			{
			match("SEMI"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_SEMI"

	// $ANTLR start "KW_USE"
	public final void mKW_USE() throws RecognitionException {
		try {
			int _type = KW_USE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:122:7: ( 'USE' )
			// SQLLexer.g:122:9: 'USE'
			{
			match("USE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_USE"

	// $ANTLR start "KW_OPTION"
	public final void mKW_OPTION() throws RecognitionException {
		try {
			int _type = KW_OPTION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:123:10: ( 'OPTION' )
			// SQLLexer.g:123:12: 'OPTION'
			{
			match("OPTION"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_OPTION"

	// $ANTLR start "KW_CONCATENATE"
	public final void mKW_CONCATENATE() throws RecognitionException {
		try {
			int _type = KW_CONCATENATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:124:15: ( 'CONCATENATE' )
			// SQLLexer.g:124:17: 'CONCATENATE'
			{
			match("CONCATENATE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_CONCATENATE"

	// $ANTLR start "KW_UPDATE"
	public final void mKW_UPDATE() throws RecognitionException {
		try {
			int _type = KW_UPDATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:125:10: ( 'UPDATE' )
			// SQLLexer.g:125:12: 'UPDATE'
			{
			match("UPDATE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_UPDATE"

	// $ANTLR start "KW_INNER"
	public final void mKW_INNER() throws RecognitionException {
		try {
			int _type = KW_INNER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:126:9: ( 'INNER' )
			// SQLLexer.g:126:11: 'INNER'
			{
			match("INNER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_INNER"

	// $ANTLR start "KW_SOURCE"
	public final void mKW_SOURCE() throws RecognitionException {
		try {
			int _type = KW_SOURCE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:127:10: ( 'SOURCE' )
			// SQLLexer.g:127:12: 'SOURCE'
			{
			match("SOURCE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_SOURCE"

	// $ANTLR start "KW_PATH"
	public final void mKW_PATH() throws RecognitionException {
		try {
			int _type = KW_PATH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:128:8: ( 'PATH' )
			// SQLLexer.g:128:10: 'PATH'
			{
			match("PATH"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_PATH"

	// $ANTLR start "KW_META"
	public final void mKW_META() throws RecognitionException {
		try {
			int _type = KW_META;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:129:8: ( 'META' )
			// SQLLexer.g:129:10: 'META'
			{
			match("META"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_META"

	// $ANTLR start "KW_INVOKE"
	public final void mKW_INVOKE() throws RecognitionException {
		try {
			int _type = KW_INVOKE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:130:10: ( 'INVOKE' )
			// SQLLexer.g:130:12: 'INVOKE'
			{
			match("INVOKE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_INVOKE"

	// $ANTLR start "KW_DEFINE"
	public final void mKW_DEFINE() throws RecognitionException {
		try {
			int _type = KW_DEFINE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:131:10: ( 'DEFINE' )
			// SQLLexer.g:131:12: 'DEFINE'
			{
			match("DEFINE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_DEFINE"

	// $ANTLR start "KW_OUTER"
	public final void mKW_OUTER() throws RecognitionException {
		try {
			int _type = KW_OUTER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:132:9: ( 'OUTER' )
			// SQLLexer.g:132:11: 'OUTER'
			{
			match("OUTER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_OUTER"

	// $ANTLR start "KW_ROWS"
	public final void mKW_ROWS() throws RecognitionException {
		try {
			int _type = KW_ROWS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:133:8: ( 'ROWS' )
			// SQLLexer.g:133:10: 'ROWS'
			{
			match("ROWS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_ROWS"

	// $ANTLR start "KW_OVER"
	public final void mKW_OVER() throws RecognitionException {
		try {
			int _type = KW_OVER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:134:8: ( 'OVER' )
			// SQLLexer.g:134:10: 'OVER'
			{
			match("OVER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_OVER"

	// $ANTLR start "KW_QUALIFY"
	public final void mKW_QUALIFY() throws RecognitionException {
		try {
			int _type = KW_QUALIFY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:135:11: ( 'QUALIFY' )
			// SQLLexer.g:135:13: 'QUALIFY'
			{
			match("QUALIFY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_QUALIFY"

	// $ANTLR start "KW_SMALLINT"
	public final void mKW_SMALLINT() throws RecognitionException {
		try {
			int _type = KW_SMALLINT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:136:12: ( 'SMALLINT' )
			// SQLLexer.g:136:14: 'SMALLINT'
			{
			match("SMALLINT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_SMALLINT"

	// $ANTLR start "KW_BYTEINT"
	public final void mKW_BYTEINT() throws RecognitionException {
		try {
			int _type = KW_BYTEINT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:137:11: ( 'BYTEINT' )
			// SQLLexer.g:137:13: 'BYTEINT'
			{
			match("BYTEINT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_BYTEINT"

	// $ANTLR start "KW_BIGINT"
	public final void mKW_BIGINT() throws RecognitionException {
		try {
			int _type = KW_BIGINT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:138:10: ( 'BIGINT' )
			// SQLLexer.g:138:12: 'BIGINT'
			{
			match("BIGINT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_BIGINT"

	// $ANTLR start "KW_INT"
	public final void mKW_INT() throws RecognitionException {
		try {
			int _type = KW_INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:139:7: ( 'INT' )
			// SQLLexer.g:139:9: 'INT'
			{
			match("INT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_INT"

	// $ANTLR start "KW_TARGET"
	public final void mKW_TARGET() throws RecognitionException {
		try {
			int _type = KW_TARGET;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:140:10: ( 'TARGET' )
			// SQLLexer.g:140:12: 'TARGET'
			{
			match("TARGET"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_TARGET"

	// $ANTLR start "KW_CHAR"
	public final void mKW_CHAR() throws RecognitionException {
		try {
			int _type = KW_CHAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:141:8: ( 'CHAR' )
			// SQLLexer.g:141:10: 'CHAR'
			{
			match("CHAR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_CHAR"

	// $ANTLR start "KW_UPPERCASE"
	public final void mKW_UPPERCASE() throws RecognitionException {
		try {
			int _type = KW_UPPERCASE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:142:13: ( 'UPPERCASE' )
			// SQLLexer.g:142:15: 'UPPERCASE'
			{
			match("UPPERCASE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_UPPERCASE"

	// $ANTLR start "KW_TIME_ZONE"
	public final void mKW_TIME_ZONE() throws RecognitionException {
		try {
			int _type = KW_TIME_ZONE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:143:13: ( 'TIMEZONE' )
			// SQLLexer.g:143:15: 'TIMEZONE'
			{
			match("TIMEZONE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_TIME_ZONE"

	// $ANTLR start "KW_MONTH"
	public final void mKW_MONTH() throws RecognitionException {
		try {
			int _type = KW_MONTH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:144:9: ( 'MONTH' )
			// SQLLexer.g:144:11: 'MONTH'
			{
			match("MONTH"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_MONTH"

	// $ANTLR start "KW_DAY"
	public final void mKW_DAY() throws RecognitionException {
		try {
			int _type = KW_DAY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:145:7: ( 'DAY' )
			// SQLLexer.g:145:9: 'DAY'
			{
			match("DAY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_DAY"

	// $ANTLR start "KW_YEAR"
	public final void mKW_YEAR() throws RecognitionException {
		try {
			int _type = KW_YEAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:146:8: ( 'YEAR' )
			// SQLLexer.g:146:10: 'YEAR'
			{
			match("YEAR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_YEAR"

	// $ANTLR start "KW_HOUR"
	public final void mKW_HOUR() throws RecognitionException {
		try {
			int _type = KW_HOUR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:147:8: ( 'HOUR' )
			// SQLLexer.g:147:10: 'HOUR'
			{
			match("HOUR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_HOUR"

	// $ANTLR start "KW_MINUTE"
	public final void mKW_MINUTE() throws RecognitionException {
		try {
			int _type = KW_MINUTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:148:10: ( 'MINUTE' )
			// SQLLexer.g:148:12: 'MINUTE'
			{
			match("MINUTE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_MINUTE"

	// $ANTLR start "KW_SECOND"
	public final void mKW_SECOND() throws RecognitionException {
		try {
			int _type = KW_SECOND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:149:10: ( 'SECOND' )
			// SQLLexer.g:149:12: 'SECOND'
			{
			match("SECOND"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_SECOND"

	// $ANTLR start "KW_INTERVAL"
	public final void mKW_INTERVAL() throws RecognitionException {
		try {
			int _type = KW_INTERVAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:150:12: ( 'INTERVAL' )
			// SQLLexer.g:150:14: 'INTERVAL'
			{
			match("INTERVAL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_INTERVAL"

	// $ANTLR start "KW_PERIOD"
	public final void mKW_PERIOD() throws RecognitionException {
		try {
			int _type = KW_PERIOD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:151:10: ( 'PERIOD' )
			// SQLLexer.g:151:12: 'PERIOD'
			{
			match("PERIOD"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_PERIOD"

	// $ANTLR start "KW_DEFAULT"
	public final void mKW_DEFAULT() throws RecognitionException {
		try {
			int _type = KW_DEFAULT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:152:11: ( 'DEFAULT' )
			// SQLLexer.g:152:13: 'DEFAULT'
			{
			match("DEFAULT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_DEFAULT"

	// $ANTLR start "DOT"
	public final void mDOT() throws RecognitionException {
		try {
			int _type = DOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:155:5: ( '.' )
			// SQLLexer.g:155:7: '.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOT"

	// $ANTLR start "COLON"
	public final void mCOLON() throws RecognitionException {
		try {
			int _type = COLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:156:7: ( ':' )
			// SQLLexer.g:156:9: ':'
			{
			match(':'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COLON"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:157:7: ( ',' )
			// SQLLexer.g:157:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "SEMICOLON"
	public final void mSEMICOLON() throws RecognitionException {
		try {
			int _type = SEMICOLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:158:11: ( ';' )
			// SQLLexer.g:158:13: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEMICOLON"

	// $ANTLR start "LPAREN"
	public final void mLPAREN() throws RecognitionException {
		try {
			int _type = LPAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:159:8: ( '(' )
			// SQLLexer.g:159:10: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LPAREN"

	// $ANTLR start "RPAREN"
	public final void mRPAREN() throws RecognitionException {
		try {
			int _type = RPAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:160:8: ( ')' )
			// SQLLexer.g:160:10: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RPAREN"

	// $ANTLR start "LSQUARE"
	public final void mLSQUARE() throws RecognitionException {
		try {
			int _type = LSQUARE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:161:9: ( '[' )
			// SQLLexer.g:161:11: '['
			{
			match('['); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LSQUARE"

	// $ANTLR start "RSQUARE"
	public final void mRSQUARE() throws RecognitionException {
		try {
			int _type = RSQUARE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:162:9: ( ']' )
			// SQLLexer.g:162:11: ']'
			{
			match(']'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RSQUARE"

	// $ANTLR start "LCURLY"
	public final void mLCURLY() throws RecognitionException {
		try {
			int _type = LCURLY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:163:8: ( '{' )
			// SQLLexer.g:163:10: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LCURLY"

	// $ANTLR start "RCURLY"
	public final void mRCURLY() throws RecognitionException {
		try {
			int _type = RCURLY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:164:8: ( '}' )
			// SQLLexer.g:164:10: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RCURLY"

	// $ANTLR start "EQUAL"
	public final void mEQUAL() throws RecognitionException {
		try {
			int _type = EQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:165:7: ( '=' | '==' )
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0=='=') ) {
				int LA2_1 = input.LA(2);
				if ( (LA2_1=='=') ) {
					alt2=2;
				}

				else {
					alt2=1;
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// SQLLexer.g:165:9: '='
					{
					match('='); 
					}
					break;
				case 2 :
					// SQLLexer.g:165:15: '=='
					{
					match("=="); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQUAL"

	// $ANTLR start "EQUAL_NS"
	public final void mEQUAL_NS() throws RecognitionException {
		try {
			int _type = EQUAL_NS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:166:10: ( '<=>' )
			// SQLLexer.g:166:12: '<=>'
			{
			match("<=>"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQUAL_NS"

	// $ANTLR start "NOTEQUAL"
	public final void mNOTEQUAL() throws RecognitionException {
		try {
			int _type = NOTEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:167:10: ( '<>' | '!=' )
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0=='<') ) {
				alt3=1;
			}
			else if ( (LA3_0=='!') ) {
				alt3=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// SQLLexer.g:167:12: '<>'
					{
					match("<>"); 

					}
					break;
				case 2 :
					// SQLLexer.g:167:19: '!='
					{
					match("!="); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOTEQUAL"

	// $ANTLR start "LESSTHANOREQUALTO"
	public final void mLESSTHANOREQUALTO() throws RecognitionException {
		try {
			int _type = LESSTHANOREQUALTO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:168:19: ( '<=' )
			// SQLLexer.g:168:21: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LESSTHANOREQUALTO"

	// $ANTLR start "LESSTHAN"
	public final void mLESSTHAN() throws RecognitionException {
		try {
			int _type = LESSTHAN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:169:10: ( '<' )
			// SQLLexer.g:169:12: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LESSTHAN"

	// $ANTLR start "GREATERTHANOREQUALTO"
	public final void mGREATERTHANOREQUALTO() throws RecognitionException {
		try {
			int _type = GREATERTHANOREQUALTO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:170:22: ( '>=' )
			// SQLLexer.g:170:24: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GREATERTHANOREQUALTO"

	// $ANTLR start "GREATERTHAN"
	public final void mGREATERTHAN() throws RecognitionException {
		try {
			int _type = GREATERTHAN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:171:13: ( '>' )
			// SQLLexer.g:171:15: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GREATERTHAN"

	// $ANTLR start "DIVIDE"
	public final void mDIVIDE() throws RecognitionException {
		try {
			int _type = DIVIDE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:172:8: ( '/' )
			// SQLLexer.g:172:10: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIVIDE"

	// $ANTLR start "PLUS"
	public final void mPLUS() throws RecognitionException {
		try {
			int _type = PLUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:173:6: ( '+' )
			// SQLLexer.g:173:8: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLUS"

	// $ANTLR start "MINUS"
	public final void mMINUS() throws RecognitionException {
		try {
			int _type = MINUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:174:7: ( '-' )
			// SQLLexer.g:174:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUS"

	// $ANTLR start "STAR"
	public final void mSTAR() throws RecognitionException {
		try {
			int _type = STAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:175:6: ( '*' )
			// SQLLexer.g:175:8: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STAR"

	// $ANTLR start "MOD"
	public final void mMOD() throws RecognitionException {
		try {
			int _type = MOD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:176:5: ( 'MOD' )
			// SQLLexer.g:176:7: 'MOD'
			{
			match("MOD"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MOD"

	// $ANTLR start "AMPERSAND"
	public final void mAMPERSAND() throws RecognitionException {
		try {
			int _type = AMPERSAND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:177:11: ( '&' )
			// SQLLexer.g:177:13: '&'
			{
			match('&'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AMPERSAND"

	// $ANTLR start "TILDE"
	public final void mTILDE() throws RecognitionException {
		try {
			int _type = TILDE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:178:7: ( '~' )
			// SQLLexer.g:178:9: '~'
			{
			match('~'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TILDE"

	// $ANTLR start "BITWISEOR"
	public final void mBITWISEOR() throws RecognitionException {
		try {
			int _type = BITWISEOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:179:11: ( '|' )
			// SQLLexer.g:179:13: '|'
			{
			match('|'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BITWISEOR"

	// $ANTLR start "BITWISEXOR"
	public final void mBITWISEXOR() throws RecognitionException {
		try {
			int _type = BITWISEXOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:180:12: ( '^' )
			// SQLLexer.g:180:14: '^'
			{
			match('^'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BITWISEXOR"

	// $ANTLR start "QUESTION"
	public final void mQUESTION() throws RecognitionException {
		try {
			int _type = QUESTION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:181:10: ( '?' )
			// SQLLexer.g:181:12: '?'
			{
			match('?'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "QUESTION"

	// $ANTLR start "DOLLAR"
	public final void mDOLLAR() throws RecognitionException {
		try {
			int _type = DOLLAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:182:8: ( '$' )
			// SQLLexer.g:182:10: '$'
			{
			match('$'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOLLAR"

	// $ANTLR start "KW_CASESPECIFIC"
	public final void mKW_CASESPECIFIC() throws RecognitionException {
		try {
			int _type = KW_CASESPECIFIC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:183:16: ( 'CASESPECIFIC' )
			// SQLLexer.g:183:18: 'CASESPECIFIC'
			{
			match("CASESPECIFIC"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KW_CASESPECIFIC"

	// $ANTLR start "Letter"
	public final void mLetter() throws RecognitionException {
		try {
			// SQLLexer.g:190:2: ( 'a' .. 'z' | 'A' .. 'Z' )
			// SQLLexer.g:
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Letter"

	// $ANTLR start "HexDigit"
	public final void mHexDigit() throws RecognitionException {
		try {
			// SQLLexer.g:196:2: ( 'a' .. 'f' | 'A' .. 'F' )
			// SQLLexer.g:
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HexDigit"

	// $ANTLR start "Digit"
	public final void mDigit() throws RecognitionException {
		try {
			// SQLLexer.g:202:2: ( '0' .. '9' )
			// SQLLexer.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Digit"

	// $ANTLR start "Exponent"
	public final void mExponent() throws RecognitionException {
		try {
			// SQLLexer.g:208:2: ( ( 'e' | 'E' ) ( PLUS | MINUS )? ( Digit )+ )
			// SQLLexer.g:209:2: ( 'e' | 'E' ) ( PLUS | MINUS )? ( Digit )+
			{
			if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// SQLLexer.g:209:14: ( PLUS | MINUS )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0=='+'||LA4_0=='-') ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// SQLLexer.g:
					{
					if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			// SQLLexer.g:209:30: ( Digit )+
			int cnt5=0;
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// SQLLexer.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt5 >= 1 ) break loop5;
					EarlyExitException eee = new EarlyExitException(5, input);
					throw eee;
				}
				cnt5++;
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Exponent"

	// $ANTLR start "RegexComponent"
	public final void mRegexComponent() throws RecognitionException {
		try {
			// SQLLexer.g:214:2: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | PLUS | STAR | QUESTION | MINUS | DOT | LPAREN | RPAREN | LSQUARE | RSQUARE | LCURLY | RCURLY | BITWISEXOR | BITWISEOR | DOLLAR )
			// SQLLexer.g:
			{
			if ( input.LA(1)=='$'||(input.LA(1) >= '(' && input.LA(1) <= '+')||(input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||input.LA(1)=='?'||(input.LA(1) >= 'A' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '_')||(input.LA(1) >= 'a' && input.LA(1) <= '}') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RegexComponent"

	// $ANTLR start "StringLiteral"
	public final void mStringLiteral() throws RecognitionException {
		try {
			int _type = StringLiteral;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:222:2: ( ( '\\'' (~ ( '\\'' | '\\\\' ) | ( '\\\\' . ) )* '\\'' | '\\\"' (~ ( '\\\"' | '\\\\' ) | ( '\\\\' . ) )* '\\\"' )+ )
			// SQLLexer.g:223:2: ( '\\'' (~ ( '\\'' | '\\\\' ) | ( '\\\\' . ) )* '\\'' | '\\\"' (~ ( '\\\"' | '\\\\' ) | ( '\\\\' . ) )* '\\\"' )+
			{
			// SQLLexer.g:223:2: ( '\\'' (~ ( '\\'' | '\\\\' ) | ( '\\\\' . ) )* '\\'' | '\\\"' (~ ( '\\\"' | '\\\\' ) | ( '\\\\' . ) )* '\\\"' )+
			int cnt8=0;
			loop8:
			while (true) {
				int alt8=3;
				int LA8_0 = input.LA(1);
				if ( (LA8_0=='\'') ) {
					alt8=1;
				}
				else if ( (LA8_0=='\"') ) {
					alt8=2;
				}

				switch (alt8) {
				case 1 :
					// SQLLexer.g:223:4: '\\'' (~ ( '\\'' | '\\\\' ) | ( '\\\\' . ) )* '\\''
					{
					match('\''); 
					// SQLLexer.g:223:9: (~ ( '\\'' | '\\\\' ) | ( '\\\\' . ) )*
					loop6:
					while (true) {
						int alt6=3;
						int LA6_0 = input.LA(1);
						if ( ((LA6_0 >= '\u0000' && LA6_0 <= '&')||(LA6_0 >= '(' && LA6_0 <= '[')||(LA6_0 >= ']' && LA6_0 <= '\uFFFF')) ) {
							alt6=1;
						}
						else if ( (LA6_0=='\\') ) {
							alt6=2;
						}

						switch (alt6) {
						case 1 :
							// SQLLexer.g:223:11: ~ ( '\\'' | '\\\\' )
							{
							if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;
						case 2 :
							// SQLLexer.g:223:26: ( '\\\\' . )
							{
							// SQLLexer.g:223:26: ( '\\\\' . )
							// SQLLexer.g:223:27: '\\\\' .
							{
							match('\\'); 
							matchAny(); 
							}

							}
							break;

						default :
							break loop6;
						}
					}

					match('\''); 
					}
					break;
				case 2 :
					// SQLLexer.g:224:4: '\\\"' (~ ( '\\\"' | '\\\\' ) | ( '\\\\' . ) )* '\\\"'
					{
					match('\"'); 
					// SQLLexer.g:224:9: (~ ( '\\\"' | '\\\\' ) | ( '\\\\' . ) )*
					loop7:
					while (true) {
						int alt7=3;
						int LA7_0 = input.LA(1);
						if ( ((LA7_0 >= '\u0000' && LA7_0 <= '!')||(LA7_0 >= '#' && LA7_0 <= '[')||(LA7_0 >= ']' && LA7_0 <= '\uFFFF')) ) {
							alt7=1;
						}
						else if ( (LA7_0=='\\') ) {
							alt7=2;
						}

						switch (alt7) {
						case 1 :
							// SQLLexer.g:224:11: ~ ( '\\\"' | '\\\\' )
							{
							if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;
						case 2 :
							// SQLLexer.g:224:26: ( '\\\\' . )
							{
							// SQLLexer.g:224:26: ( '\\\\' . )
							// SQLLexer.g:224:27: '\\\\' .
							{
							match('\\'); 
							matchAny(); 
							}

							}
							break;

						default :
							break loop7;
						}
					}

					match('\"'); 
					}
					break;

				default :
					if ( cnt8 >= 1 ) break loop8;
					EarlyExitException eee = new EarlyExitException(8, input);
					throw eee;
				}
				cnt8++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "StringLiteral"

	// $ANTLR start "DecimalLiteral"
	public final void mDecimalLiteral() throws RecognitionException {
		try {
			int _type = DecimalLiteral;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:229:2: ( Number 'B' 'D' )
			// SQLLexer.g:230:2: Number 'B' 'D'
			{
			mNumber(); 

			match('B'); 
			match('D'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DecimalLiteral"

	// $ANTLR start "ByteLengthLiteral"
	public final void mByteLengthLiteral() throws RecognitionException {
		try {
			int _type = ByteLengthLiteral;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:234:2: ( ( Digit )+ ( 'b' | 'B' | 'k' | 'K' | 'm' | 'M' | 'g' | 'G' ) )
			// SQLLexer.g:235:2: ( Digit )+ ( 'b' | 'B' | 'k' | 'K' | 'm' | 'M' | 'g' | 'G' )
			{
			// SQLLexer.g:235:2: ( Digit )+
			int cnt9=0;
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( ((LA9_0 >= '0' && LA9_0 <= '9')) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// SQLLexer.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt9 >= 1 ) break loop9;
					EarlyExitException eee = new EarlyExitException(9, input);
					throw eee;
				}
				cnt9++;
			}

			if ( input.LA(1)=='B'||input.LA(1)=='G'||input.LA(1)=='K'||input.LA(1)=='M'||input.LA(1)=='b'||input.LA(1)=='g'||input.LA(1)=='k'||input.LA(1)=='m' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ByteLengthLiteral"

	// $ANTLR start "Number"
	public final void mNumber() throws RecognitionException {
		try {
			int _type = Number;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:239:2: ( ( Digit )+ ( DOT ( Digit )* ( Exponent )? | Exponent )? )
			// SQLLexer.g:240:2: ( Digit )+ ( DOT ( Digit )* ( Exponent )? | Exponent )?
			{
			// SQLLexer.g:240:2: ( Digit )+
			int cnt10=0;
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( ((LA10_0 >= '0' && LA10_0 <= '9')) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// SQLLexer.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt10 >= 1 ) break loop10;
					EarlyExitException eee = new EarlyExitException(10, input);
					throw eee;
				}
				cnt10++;
			}

			// SQLLexer.g:240:11: ( DOT ( Digit )* ( Exponent )? | Exponent )?
			int alt13=3;
			int LA13_0 = input.LA(1);
			if ( (LA13_0=='.') ) {
				alt13=1;
			}
			else if ( (LA13_0=='E'||LA13_0=='e') ) {
				alt13=2;
			}
			switch (alt13) {
				case 1 :
					// SQLLexer.g:240:13: DOT ( Digit )* ( Exponent )?
					{
					mDOT(); 

					// SQLLexer.g:240:17: ( Digit )*
					loop11:
					while (true) {
						int alt11=2;
						int LA11_0 = input.LA(1);
						if ( ((LA11_0 >= '0' && LA11_0 <= '9')) ) {
							alt11=1;
						}

						switch (alt11) {
						case 1 :
							// SQLLexer.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop11;
						}
					}

					// SQLLexer.g:240:26: ( Exponent )?
					int alt12=2;
					int LA12_0 = input.LA(1);
					if ( (LA12_0=='E'||LA12_0=='e') ) {
						alt12=1;
					}
					switch (alt12) {
						case 1 :
							// SQLLexer.g:240:27: Exponent
							{
							mExponent(); 

							}
							break;

					}

					}
					break;
				case 2 :
					// SQLLexer.g:240:40: Exponent
					{
					mExponent(); 

					}
					break;

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Number"

	// $ANTLR start "Identifier"
	public final void mIdentifier() throws RecognitionException {
		try {
			int _type = Identifier;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:244:2: ( ( Letter | Digit ) ( Letter | Digit | '_' )* | '`' ( RegexComponent )+ '`' )
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( ((LA16_0 >= '0' && LA16_0 <= '9')||(LA16_0 >= 'A' && LA16_0 <= 'Z')||(LA16_0 >= 'a' && LA16_0 <= 'z')) ) {
				alt16=1;
			}
			else if ( (LA16_0=='`') ) {
				alt16=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}

			switch (alt16) {
				case 1 :
					// SQLLexer.g:245:2: ( Letter | Digit ) ( Letter | Digit | '_' )*
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					// SQLLexer.g:245:19: ( Letter | Digit | '_' )*
					loop14:
					while (true) {
						int alt14=2;
						int LA14_0 = input.LA(1);
						if ( ((LA14_0 >= '0' && LA14_0 <= '9')||(LA14_0 >= 'A' && LA14_0 <= 'Z')||LA14_0=='_'||(LA14_0 >= 'a' && LA14_0 <= 'z')) ) {
							alt14=1;
						}

						switch (alt14) {
						case 1 :
							// SQLLexer.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop14;
						}
					}

					}
					break;
				case 2 :
					// SQLLexer.g:246:4: '`' ( RegexComponent )+ '`'
					{
					match('`'); 
					// SQLLexer.g:246:8: ( RegexComponent )+
					int cnt15=0;
					loop15:
					while (true) {
						int alt15=2;
						int LA15_0 = input.LA(1);
						if ( (LA15_0=='$'||(LA15_0 >= '(' && LA15_0 <= '+')||(LA15_0 >= '-' && LA15_0 <= '.')||(LA15_0 >= '0' && LA15_0 <= '9')||LA15_0=='?'||(LA15_0 >= 'A' && LA15_0 <= '[')||(LA15_0 >= ']' && LA15_0 <= '_')||(LA15_0 >= 'a' && LA15_0 <= '}')) ) {
							alt15=1;
						}

						switch (alt15) {
						case 1 :
							// SQLLexer.g:
							{
							if ( input.LA(1)=='$'||(input.LA(1) >= '(' && input.LA(1) <= '+')||(input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||input.LA(1)=='?'||(input.LA(1) >= 'A' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '_')||(input.LA(1) >= 'a' && input.LA(1) <= '}') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt15 >= 1 ) break loop15;
							EarlyExitException eee = new EarlyExitException(15, input);
							throw eee;
						}
						cnt15++;
					}

					match('`'); 
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Identifier"

	// $ANTLR start "CharSetName"
	public final void mCharSetName() throws RecognitionException {
		try {
			int _type = CharSetName;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:251:2: ( '_' ( Letter | Digit | '_' | '-' | '.' | ':' )+ )
			// SQLLexer.g:252:2: '_' ( Letter | Digit | '_' | '-' | '.' | ':' )+
			{
			match('_'); 
			// SQLLexer.g:252:6: ( Letter | Digit | '_' | '-' | '.' | ':' )+
			int cnt17=0;
			loop17:
			while (true) {
				int alt17=2;
				int LA17_0 = input.LA(1);
				if ( ((LA17_0 >= '-' && LA17_0 <= '.')||(LA17_0 >= '0' && LA17_0 <= ':')||(LA17_0 >= 'A' && LA17_0 <= 'Z')||LA17_0=='_'||(LA17_0 >= 'a' && LA17_0 <= 'z')) ) {
					alt17=1;
				}

				switch (alt17) {
				case 1 :
					// SQLLexer.g:
					{
					if ( (input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= ':')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt17 >= 1 ) break loop17;
					EarlyExitException eee = new EarlyExitException(17, input);
					throw eee;
				}
				cnt17++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CharSetName"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:256:2: ( ( ' ' | '\\r' | '\\t' | '\\n' ) )
			// SQLLexer.g:257:2: ( ' ' | '\\r' | '\\t' | '\\n' )
			{
			if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "SL_COMMENT"
	public final void mSL_COMMENT() throws RecognitionException {
		try {
			int _type = SL_COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:261:4: ( ( '--' | '#' ) (~ ( '\\n' | '\\r' ) )* )
			// SQLLexer.g:262:4: ( '--' | '#' ) (~ ( '\\n' | '\\r' ) )*
			{
			// SQLLexer.g:262:4: ( '--' | '#' )
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0=='-') ) {
				alt18=1;
			}
			else if ( (LA18_0=='#') ) {
				alt18=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 18, 0, input);
				throw nvae;
			}

			switch (alt18) {
				case 1 :
					// SQLLexer.g:262:5: '--'
					{
					match("--"); 

					}
					break;
				case 2 :
					// SQLLexer.g:262:10: '#'
					{
					match('#'); 
					}
					break;

			}

			// SQLLexer.g:262:15: (~ ( '\\n' | '\\r' ) )*
			loop19:
			while (true) {
				int alt19=2;
				int LA19_0 = input.LA(1);
				if ( ((LA19_0 >= '\u0000' && LA19_0 <= '\t')||(LA19_0 >= '\u000B' && LA19_0 <= '\f')||(LA19_0 >= '\u000E' && LA19_0 <= '\uFFFF')) ) {
					alt19=1;
				}

				switch (alt19) {
				case 1 :
					// SQLLexer.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop19;
				}
			}

			 _channel=HIDDEN; 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SL_COMMENT"

	// $ANTLR start "ML_COMMENT"
	public final void mML_COMMENT() throws RecognitionException {
		try {
			int _type = ML_COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SQLLexer.g:265:2: ( '/*' ( options {greedy=false; } : . )* '*/' )
			// SQLLexer.g:266:2: '/*' ( options {greedy=false; } : . )* '*/'
			{
			match("/*"); 

			// SQLLexer.g:266:7: ( options {greedy=false; } : . )*
			loop20:
			while (true) {
				int alt20=2;
				int LA20_0 = input.LA(1);
				if ( (LA20_0=='*') ) {
					int LA20_1 = input.LA(2);
					if ( (LA20_1=='/') ) {
						alt20=2;
					}
					else if ( ((LA20_1 >= '\u0000' && LA20_1 <= '.')||(LA20_1 >= '0' && LA20_1 <= '\uFFFF')) ) {
						alt20=1;
					}

				}
				else if ( ((LA20_0 >= '\u0000' && LA20_0 <= ')')||(LA20_0 >= '+' && LA20_0 <= '\uFFFF')) ) {
					alt20=1;
				}

				switch (alt20) {
				case 1 :
					// SQLLexer.g:266:35: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop20;
				}
			}

			match("*/"); 

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ML_COMMENT"

	@Override
	public void mTokens() throws RecognitionException {
		// SQLLexer.g:1:8: ( KW_TRUE | KW_FALSE | KW_ALL | KW_AND | KW_OR | KW_NOT | KW_LIKE | KW_IF | KW_EXISTS | KW_ASC | KW_DESC | KW_ORDER | KW_GROUP | KW_BY | KW_HAVING | KW_WHERE | KW_FROM | KW_AS | KW_SELECT | KW_DISTINCT | KW_INSERT | KW_OVERWRITE | KW_JOIN | KW_LEFT | KW_RIGHT | KW_FULL | KW_ON | KW_PARTITION | KW_TABLE | KW_INDEX | KW_USING | KW_DISTRIBUTE | KW_SORT | KW_UNION | KW_LOAD | KW_EXPORT | KW_IMPORT | KW_IS | KW_NULL | KW_CREATE | KW_BOOLEAN | KW_INTEGER | KW_FLOAT | KW_DOUBLE | KW_DATE | KW_DATETIME | KW_TIME | KW_TIMESTAMP | KW_DECIMAL | KW_STRING | KW_ARRAY | KW_STRUCT | KW_MAP | KW_INTO | KW_FORMAT | KW_DELIMITED | KW_FIELDS | KW_TERMINATED | KW_ESCAPED | KW_FILEFORMAT | KW_INPUTFORMAT | KW_OUTPUTFORMAT | KW_ENABLE | KW_DISABLE | KW_READONLY | KW_LOCATION | KW_OUT | KW_OF | KW_PERCENT | KW_CAST | KW_ADD | KW_VOLATILE | KW_TEMPORARY | KW_FUNCTION | KW_MACRO | KW_EXPLAIN | KW_WITH | KW_LIMIT | KW_SET | KW_CASE | KW_WHEN | KW_THEN | KW_ELSE | KW_END | KW_MAPJOIN | KW_UTC | KW_UTCTIMESTAMP | KW_LONG | KW_DELETE | KW_FETCH | KW_VIEW | KW_IN | KW_DATABASE | KW_GRANT | KW_REVOKE | KW_UNDO | KW_LOCK | KW_LOCKS | KW_UNLOCK | KW_SHARED | KW_EXCLUSIVE | KW_PROCEDURE | KW_UNSIGNED | KW_WHILE | KW_READ | KW_RANGE | KW_BEFORE | KW_BETWEEN | KW_BOTH | KW_BINARY | KW_CROSS | KW_CONTINUE | KW_CURSOR | KW_TRIGGER | KW_SEMI | KW_USE | KW_OPTION | KW_CONCATENATE | KW_UPDATE | KW_INNER | KW_SOURCE | KW_PATH | KW_META | KW_INVOKE | KW_DEFINE | KW_OUTER | KW_ROWS | KW_OVER | KW_QUALIFY | KW_SMALLINT | KW_BYTEINT | KW_BIGINT | KW_INT | KW_TARGET | KW_CHAR | KW_UPPERCASE | KW_TIME_ZONE | KW_MONTH | KW_DAY | KW_YEAR | KW_HOUR | KW_MINUTE | KW_SECOND | KW_INTERVAL | KW_PERIOD | KW_DEFAULT | DOT | COLON | COMMA | SEMICOLON | LPAREN | RPAREN | LSQUARE | RSQUARE | LCURLY | RCURLY | EQUAL | EQUAL_NS | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN | DIVIDE | PLUS | MINUS | STAR | MOD | AMPERSAND | TILDE | BITWISEOR | BITWISEXOR | QUESTION | DOLLAR | KW_CASESPECIFIC | StringLiteral | DecimalLiteral | ByteLengthLiteral | Number | Identifier | CharSetName | WS | SL_COMMENT | ML_COMMENT )
		int alt21=184;
		alt21 = dfa21.predict(input);
		switch (alt21) {
			case 1 :
				// SQLLexer.g:1:10: KW_TRUE
				{
				mKW_TRUE(); 

				}
				break;
			case 2 :
				// SQLLexer.g:1:18: KW_FALSE
				{
				mKW_FALSE(); 

				}
				break;
			case 3 :
				// SQLLexer.g:1:27: KW_ALL
				{
				mKW_ALL(); 

				}
				break;
			case 4 :
				// SQLLexer.g:1:34: KW_AND
				{
				mKW_AND(); 

				}
				break;
			case 5 :
				// SQLLexer.g:1:41: KW_OR
				{
				mKW_OR(); 

				}
				break;
			case 6 :
				// SQLLexer.g:1:47: KW_NOT
				{
				mKW_NOT(); 

				}
				break;
			case 7 :
				// SQLLexer.g:1:54: KW_LIKE
				{
				mKW_LIKE(); 

				}
				break;
			case 8 :
				// SQLLexer.g:1:62: KW_IF
				{
				mKW_IF(); 

				}
				break;
			case 9 :
				// SQLLexer.g:1:68: KW_EXISTS
				{
				mKW_EXISTS(); 

				}
				break;
			case 10 :
				// SQLLexer.g:1:78: KW_ASC
				{
				mKW_ASC(); 

				}
				break;
			case 11 :
				// SQLLexer.g:1:85: KW_DESC
				{
				mKW_DESC(); 

				}
				break;
			case 12 :
				// SQLLexer.g:1:93: KW_ORDER
				{
				mKW_ORDER(); 

				}
				break;
			case 13 :
				// SQLLexer.g:1:102: KW_GROUP
				{
				mKW_GROUP(); 

				}
				break;
			case 14 :
				// SQLLexer.g:1:111: KW_BY
				{
				mKW_BY(); 

				}
				break;
			case 15 :
				// SQLLexer.g:1:117: KW_HAVING
				{
				mKW_HAVING(); 

				}
				break;
			case 16 :
				// SQLLexer.g:1:127: KW_WHERE
				{
				mKW_WHERE(); 

				}
				break;
			case 17 :
				// SQLLexer.g:1:136: KW_FROM
				{
				mKW_FROM(); 

				}
				break;
			case 18 :
				// SQLLexer.g:1:144: KW_AS
				{
				mKW_AS(); 

				}
				break;
			case 19 :
				// SQLLexer.g:1:150: KW_SELECT
				{
				mKW_SELECT(); 

				}
				break;
			case 20 :
				// SQLLexer.g:1:160: KW_DISTINCT
				{
				mKW_DISTINCT(); 

				}
				break;
			case 21 :
				// SQLLexer.g:1:172: KW_INSERT
				{
				mKW_INSERT(); 

				}
				break;
			case 22 :
				// SQLLexer.g:1:182: KW_OVERWRITE
				{
				mKW_OVERWRITE(); 

				}
				break;
			case 23 :
				// SQLLexer.g:1:195: KW_JOIN
				{
				mKW_JOIN(); 

				}
				break;
			case 24 :
				// SQLLexer.g:1:203: KW_LEFT
				{
				mKW_LEFT(); 

				}
				break;
			case 25 :
				// SQLLexer.g:1:211: KW_RIGHT
				{
				mKW_RIGHT(); 

				}
				break;
			case 26 :
				// SQLLexer.g:1:220: KW_FULL
				{
				mKW_FULL(); 

				}
				break;
			case 27 :
				// SQLLexer.g:1:228: KW_ON
				{
				mKW_ON(); 

				}
				break;
			case 28 :
				// SQLLexer.g:1:234: KW_PARTITION
				{
				mKW_PARTITION(); 

				}
				break;
			case 29 :
				// SQLLexer.g:1:247: KW_TABLE
				{
				mKW_TABLE(); 

				}
				break;
			case 30 :
				// SQLLexer.g:1:256: KW_INDEX
				{
				mKW_INDEX(); 

				}
				break;
			case 31 :
				// SQLLexer.g:1:265: KW_USING
				{
				mKW_USING(); 

				}
				break;
			case 32 :
				// SQLLexer.g:1:274: KW_DISTRIBUTE
				{
				mKW_DISTRIBUTE(); 

				}
				break;
			case 33 :
				// SQLLexer.g:1:288: KW_SORT
				{
				mKW_SORT(); 

				}
				break;
			case 34 :
				// SQLLexer.g:1:296: KW_UNION
				{
				mKW_UNION(); 

				}
				break;
			case 35 :
				// SQLLexer.g:1:305: KW_LOAD
				{
				mKW_LOAD(); 

				}
				break;
			case 36 :
				// SQLLexer.g:1:313: KW_EXPORT
				{
				mKW_EXPORT(); 

				}
				break;
			case 37 :
				// SQLLexer.g:1:323: KW_IMPORT
				{
				mKW_IMPORT(); 

				}
				break;
			case 38 :
				// SQLLexer.g:1:333: KW_IS
				{
				mKW_IS(); 

				}
				break;
			case 39 :
				// SQLLexer.g:1:339: KW_NULL
				{
				mKW_NULL(); 

				}
				break;
			case 40 :
				// SQLLexer.g:1:347: KW_CREATE
				{
				mKW_CREATE(); 

				}
				break;
			case 41 :
				// SQLLexer.g:1:357: KW_BOOLEAN
				{
				mKW_BOOLEAN(); 

				}
				break;
			case 42 :
				// SQLLexer.g:1:368: KW_INTEGER
				{
				mKW_INTEGER(); 

				}
				break;
			case 43 :
				// SQLLexer.g:1:379: KW_FLOAT
				{
				mKW_FLOAT(); 

				}
				break;
			case 44 :
				// SQLLexer.g:1:388: KW_DOUBLE
				{
				mKW_DOUBLE(); 

				}
				break;
			case 45 :
				// SQLLexer.g:1:398: KW_DATE
				{
				mKW_DATE(); 

				}
				break;
			case 46 :
				// SQLLexer.g:1:406: KW_DATETIME
				{
				mKW_DATETIME(); 

				}
				break;
			case 47 :
				// SQLLexer.g:1:418: KW_TIME
				{
				mKW_TIME(); 

				}
				break;
			case 48 :
				// SQLLexer.g:1:426: KW_TIMESTAMP
				{
				mKW_TIMESTAMP(); 

				}
				break;
			case 49 :
				// SQLLexer.g:1:439: KW_DECIMAL
				{
				mKW_DECIMAL(); 

				}
				break;
			case 50 :
				// SQLLexer.g:1:450: KW_STRING
				{
				mKW_STRING(); 

				}
				break;
			case 51 :
				// SQLLexer.g:1:460: KW_ARRAY
				{
				mKW_ARRAY(); 

				}
				break;
			case 52 :
				// SQLLexer.g:1:469: KW_STRUCT
				{
				mKW_STRUCT(); 

				}
				break;
			case 53 :
				// SQLLexer.g:1:479: KW_MAP
				{
				mKW_MAP(); 

				}
				break;
			case 54 :
				// SQLLexer.g:1:486: KW_INTO
				{
				mKW_INTO(); 

				}
				break;
			case 55 :
				// SQLLexer.g:1:494: KW_FORMAT
				{
				mKW_FORMAT(); 

				}
				break;
			case 56 :
				// SQLLexer.g:1:504: KW_DELIMITED
				{
				mKW_DELIMITED(); 

				}
				break;
			case 57 :
				// SQLLexer.g:1:517: KW_FIELDS
				{
				mKW_FIELDS(); 

				}
				break;
			case 58 :
				// SQLLexer.g:1:527: KW_TERMINATED
				{
				mKW_TERMINATED(); 

				}
				break;
			case 59 :
				// SQLLexer.g:1:541: KW_ESCAPED
				{
				mKW_ESCAPED(); 

				}
				break;
			case 60 :
				// SQLLexer.g:1:552: KW_FILEFORMAT
				{
				mKW_FILEFORMAT(); 

				}
				break;
			case 61 :
				// SQLLexer.g:1:566: KW_INPUTFORMAT
				{
				mKW_INPUTFORMAT(); 

				}
				break;
			case 62 :
				// SQLLexer.g:1:581: KW_OUTPUTFORMAT
				{
				mKW_OUTPUTFORMAT(); 

				}
				break;
			case 63 :
				// SQLLexer.g:1:597: KW_ENABLE
				{
				mKW_ENABLE(); 

				}
				break;
			case 64 :
				// SQLLexer.g:1:607: KW_DISABLE
				{
				mKW_DISABLE(); 

				}
				break;
			case 65 :
				// SQLLexer.g:1:618: KW_READONLY
				{
				mKW_READONLY(); 

				}
				break;
			case 66 :
				// SQLLexer.g:1:630: KW_LOCATION
				{
				mKW_LOCATION(); 

				}
				break;
			case 67 :
				// SQLLexer.g:1:642: KW_OUT
				{
				mKW_OUT(); 

				}
				break;
			case 68 :
				// SQLLexer.g:1:649: KW_OF
				{
				mKW_OF(); 

				}
				break;
			case 69 :
				// SQLLexer.g:1:655: KW_PERCENT
				{
				mKW_PERCENT(); 

				}
				break;
			case 70 :
				// SQLLexer.g:1:666: KW_CAST
				{
				mKW_CAST(); 

				}
				break;
			case 71 :
				// SQLLexer.g:1:674: KW_ADD
				{
				mKW_ADD(); 

				}
				break;
			case 72 :
				// SQLLexer.g:1:681: KW_VOLATILE
				{
				mKW_VOLATILE(); 

				}
				break;
			case 73 :
				// SQLLexer.g:1:693: KW_TEMPORARY
				{
				mKW_TEMPORARY(); 

				}
				break;
			case 74 :
				// SQLLexer.g:1:706: KW_FUNCTION
				{
				mKW_FUNCTION(); 

				}
				break;
			case 75 :
				// SQLLexer.g:1:718: KW_MACRO
				{
				mKW_MACRO(); 

				}
				break;
			case 76 :
				// SQLLexer.g:1:727: KW_EXPLAIN
				{
				mKW_EXPLAIN(); 

				}
				break;
			case 77 :
				// SQLLexer.g:1:738: KW_WITH
				{
				mKW_WITH(); 

				}
				break;
			case 78 :
				// SQLLexer.g:1:746: KW_LIMIT
				{
				mKW_LIMIT(); 

				}
				break;
			case 79 :
				// SQLLexer.g:1:755: KW_SET
				{
				mKW_SET(); 

				}
				break;
			case 80 :
				// SQLLexer.g:1:762: KW_CASE
				{
				mKW_CASE(); 

				}
				break;
			case 81 :
				// SQLLexer.g:1:770: KW_WHEN
				{
				mKW_WHEN(); 

				}
				break;
			case 82 :
				// SQLLexer.g:1:778: KW_THEN
				{
				mKW_THEN(); 

				}
				break;
			case 83 :
				// SQLLexer.g:1:786: KW_ELSE
				{
				mKW_ELSE(); 

				}
				break;
			case 84 :
				// SQLLexer.g:1:794: KW_END
				{
				mKW_END(); 

				}
				break;
			case 85 :
				// SQLLexer.g:1:801: KW_MAPJOIN
				{
				mKW_MAPJOIN(); 

				}
				break;
			case 86 :
				// SQLLexer.g:1:812: KW_UTC
				{
				mKW_UTC(); 

				}
				break;
			case 87 :
				// SQLLexer.g:1:819: KW_UTCTIMESTAMP
				{
				mKW_UTCTIMESTAMP(); 

				}
				break;
			case 88 :
				// SQLLexer.g:1:835: KW_LONG
				{
				mKW_LONG(); 

				}
				break;
			case 89 :
				// SQLLexer.g:1:843: KW_DELETE
				{
				mKW_DELETE(); 

				}
				break;
			case 90 :
				// SQLLexer.g:1:853: KW_FETCH
				{
				mKW_FETCH(); 

				}
				break;
			case 91 :
				// SQLLexer.g:1:862: KW_VIEW
				{
				mKW_VIEW(); 

				}
				break;
			case 92 :
				// SQLLexer.g:1:870: KW_IN
				{
				mKW_IN(); 

				}
				break;
			case 93 :
				// SQLLexer.g:1:876: KW_DATABASE
				{
				mKW_DATABASE(); 

				}
				break;
			case 94 :
				// SQLLexer.g:1:888: KW_GRANT
				{
				mKW_GRANT(); 

				}
				break;
			case 95 :
				// SQLLexer.g:1:897: KW_REVOKE
				{
				mKW_REVOKE(); 

				}
				break;
			case 96 :
				// SQLLexer.g:1:907: KW_UNDO
				{
				mKW_UNDO(); 

				}
				break;
			case 97 :
				// SQLLexer.g:1:915: KW_LOCK
				{
				mKW_LOCK(); 

				}
				break;
			case 98 :
				// SQLLexer.g:1:923: KW_LOCKS
				{
				mKW_LOCKS(); 

				}
				break;
			case 99 :
				// SQLLexer.g:1:932: KW_UNLOCK
				{
				mKW_UNLOCK(); 

				}
				break;
			case 100 :
				// SQLLexer.g:1:942: KW_SHARED
				{
				mKW_SHARED(); 

				}
				break;
			case 101 :
				// SQLLexer.g:1:952: KW_EXCLUSIVE
				{
				mKW_EXCLUSIVE(); 

				}
				break;
			case 102 :
				// SQLLexer.g:1:965: KW_PROCEDURE
				{
				mKW_PROCEDURE(); 

				}
				break;
			case 103 :
				// SQLLexer.g:1:978: KW_UNSIGNED
				{
				mKW_UNSIGNED(); 

				}
				break;
			case 104 :
				// SQLLexer.g:1:990: KW_WHILE
				{
				mKW_WHILE(); 

				}
				break;
			case 105 :
				// SQLLexer.g:1:999: KW_READ
				{
				mKW_READ(); 

				}
				break;
			case 106 :
				// SQLLexer.g:1:1007: KW_RANGE
				{
				mKW_RANGE(); 

				}
				break;
			case 107 :
				// SQLLexer.g:1:1016: KW_BEFORE
				{
				mKW_BEFORE(); 

				}
				break;
			case 108 :
				// SQLLexer.g:1:1026: KW_BETWEEN
				{
				mKW_BETWEEN(); 

				}
				break;
			case 109 :
				// SQLLexer.g:1:1037: KW_BOTH
				{
				mKW_BOTH(); 

				}
				break;
			case 110 :
				// SQLLexer.g:1:1045: KW_BINARY
				{
				mKW_BINARY(); 

				}
				break;
			case 111 :
				// SQLLexer.g:1:1055: KW_CROSS
				{
				mKW_CROSS(); 

				}
				break;
			case 112 :
				// SQLLexer.g:1:1064: KW_CONTINUE
				{
				mKW_CONTINUE(); 

				}
				break;
			case 113 :
				// SQLLexer.g:1:1076: KW_CURSOR
				{
				mKW_CURSOR(); 

				}
				break;
			case 114 :
				// SQLLexer.g:1:1086: KW_TRIGGER
				{
				mKW_TRIGGER(); 

				}
				break;
			case 115 :
				// SQLLexer.g:1:1097: KW_SEMI
				{
				mKW_SEMI(); 

				}
				break;
			case 116 :
				// SQLLexer.g:1:1105: KW_USE
				{
				mKW_USE(); 

				}
				break;
			case 117 :
				// SQLLexer.g:1:1112: KW_OPTION
				{
				mKW_OPTION(); 

				}
				break;
			case 118 :
				// SQLLexer.g:1:1122: KW_CONCATENATE
				{
				mKW_CONCATENATE(); 

				}
				break;
			case 119 :
				// SQLLexer.g:1:1137: KW_UPDATE
				{
				mKW_UPDATE(); 

				}
				break;
			case 120 :
				// SQLLexer.g:1:1147: KW_INNER
				{
				mKW_INNER(); 

				}
				break;
			case 121 :
				// SQLLexer.g:1:1156: KW_SOURCE
				{
				mKW_SOURCE(); 

				}
				break;
			case 122 :
				// SQLLexer.g:1:1166: KW_PATH
				{
				mKW_PATH(); 

				}
				break;
			case 123 :
				// SQLLexer.g:1:1174: KW_META
				{
				mKW_META(); 

				}
				break;
			case 124 :
				// SQLLexer.g:1:1182: KW_INVOKE
				{
				mKW_INVOKE(); 

				}
				break;
			case 125 :
				// SQLLexer.g:1:1192: KW_DEFINE
				{
				mKW_DEFINE(); 

				}
				break;
			case 126 :
				// SQLLexer.g:1:1202: KW_OUTER
				{
				mKW_OUTER(); 

				}
				break;
			case 127 :
				// SQLLexer.g:1:1211: KW_ROWS
				{
				mKW_ROWS(); 

				}
				break;
			case 128 :
				// SQLLexer.g:1:1219: KW_OVER
				{
				mKW_OVER(); 

				}
				break;
			case 129 :
				// SQLLexer.g:1:1227: KW_QUALIFY
				{
				mKW_QUALIFY(); 

				}
				break;
			case 130 :
				// SQLLexer.g:1:1238: KW_SMALLINT
				{
				mKW_SMALLINT(); 

				}
				break;
			case 131 :
				// SQLLexer.g:1:1250: KW_BYTEINT
				{
				mKW_BYTEINT(); 

				}
				break;
			case 132 :
				// SQLLexer.g:1:1261: KW_BIGINT
				{
				mKW_BIGINT(); 

				}
				break;
			case 133 :
				// SQLLexer.g:1:1271: KW_INT
				{
				mKW_INT(); 

				}
				break;
			case 134 :
				// SQLLexer.g:1:1278: KW_TARGET
				{
				mKW_TARGET(); 

				}
				break;
			case 135 :
				// SQLLexer.g:1:1288: KW_CHAR
				{
				mKW_CHAR(); 

				}
				break;
			case 136 :
				// SQLLexer.g:1:1296: KW_UPPERCASE
				{
				mKW_UPPERCASE(); 

				}
				break;
			case 137 :
				// SQLLexer.g:1:1309: KW_TIME_ZONE
				{
				mKW_TIME_ZONE(); 

				}
				break;
			case 138 :
				// SQLLexer.g:1:1322: KW_MONTH
				{
				mKW_MONTH(); 

				}
				break;
			case 139 :
				// SQLLexer.g:1:1331: KW_DAY
				{
				mKW_DAY(); 

				}
				break;
			case 140 :
				// SQLLexer.g:1:1338: KW_YEAR
				{
				mKW_YEAR(); 

				}
				break;
			case 141 :
				// SQLLexer.g:1:1346: KW_HOUR
				{
				mKW_HOUR(); 

				}
				break;
			case 142 :
				// SQLLexer.g:1:1354: KW_MINUTE
				{
				mKW_MINUTE(); 

				}
				break;
			case 143 :
				// SQLLexer.g:1:1364: KW_SECOND
				{
				mKW_SECOND(); 

				}
				break;
			case 144 :
				// SQLLexer.g:1:1374: KW_INTERVAL
				{
				mKW_INTERVAL(); 

				}
				break;
			case 145 :
				// SQLLexer.g:1:1386: KW_PERIOD
				{
				mKW_PERIOD(); 

				}
				break;
			case 146 :
				// SQLLexer.g:1:1396: KW_DEFAULT
				{
				mKW_DEFAULT(); 

				}
				break;
			case 147 :
				// SQLLexer.g:1:1407: DOT
				{
				mDOT(); 

				}
				break;
			case 148 :
				// SQLLexer.g:1:1411: COLON
				{
				mCOLON(); 

				}
				break;
			case 149 :
				// SQLLexer.g:1:1417: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 150 :
				// SQLLexer.g:1:1423: SEMICOLON
				{
				mSEMICOLON(); 

				}
				break;
			case 151 :
				// SQLLexer.g:1:1433: LPAREN
				{
				mLPAREN(); 

				}
				break;
			case 152 :
				// SQLLexer.g:1:1440: RPAREN
				{
				mRPAREN(); 

				}
				break;
			case 153 :
				// SQLLexer.g:1:1447: LSQUARE
				{
				mLSQUARE(); 

				}
				break;
			case 154 :
				// SQLLexer.g:1:1455: RSQUARE
				{
				mRSQUARE(); 

				}
				break;
			case 155 :
				// SQLLexer.g:1:1463: LCURLY
				{
				mLCURLY(); 

				}
				break;
			case 156 :
				// SQLLexer.g:1:1470: RCURLY
				{
				mRCURLY(); 

				}
				break;
			case 157 :
				// SQLLexer.g:1:1477: EQUAL
				{
				mEQUAL(); 

				}
				break;
			case 158 :
				// SQLLexer.g:1:1483: EQUAL_NS
				{
				mEQUAL_NS(); 

				}
				break;
			case 159 :
				// SQLLexer.g:1:1492: NOTEQUAL
				{
				mNOTEQUAL(); 

				}
				break;
			case 160 :
				// SQLLexer.g:1:1501: LESSTHANOREQUALTO
				{
				mLESSTHANOREQUALTO(); 

				}
				break;
			case 161 :
				// SQLLexer.g:1:1519: LESSTHAN
				{
				mLESSTHAN(); 

				}
				break;
			case 162 :
				// SQLLexer.g:1:1528: GREATERTHANOREQUALTO
				{
				mGREATERTHANOREQUALTO(); 

				}
				break;
			case 163 :
				// SQLLexer.g:1:1549: GREATERTHAN
				{
				mGREATERTHAN(); 

				}
				break;
			case 164 :
				// SQLLexer.g:1:1561: DIVIDE
				{
				mDIVIDE(); 

				}
				break;
			case 165 :
				// SQLLexer.g:1:1568: PLUS
				{
				mPLUS(); 

				}
				break;
			case 166 :
				// SQLLexer.g:1:1573: MINUS
				{
				mMINUS(); 

				}
				break;
			case 167 :
				// SQLLexer.g:1:1579: STAR
				{
				mSTAR(); 

				}
				break;
			case 168 :
				// SQLLexer.g:1:1584: MOD
				{
				mMOD(); 

				}
				break;
			case 169 :
				// SQLLexer.g:1:1588: AMPERSAND
				{
				mAMPERSAND(); 

				}
				break;
			case 170 :
				// SQLLexer.g:1:1598: TILDE
				{
				mTILDE(); 

				}
				break;
			case 171 :
				// SQLLexer.g:1:1604: BITWISEOR
				{
				mBITWISEOR(); 

				}
				break;
			case 172 :
				// SQLLexer.g:1:1614: BITWISEXOR
				{
				mBITWISEXOR(); 

				}
				break;
			case 173 :
				// SQLLexer.g:1:1625: QUESTION
				{
				mQUESTION(); 

				}
				break;
			case 174 :
				// SQLLexer.g:1:1634: DOLLAR
				{
				mDOLLAR(); 

				}
				break;
			case 175 :
				// SQLLexer.g:1:1641: KW_CASESPECIFIC
				{
				mKW_CASESPECIFIC(); 

				}
				break;
			case 176 :
				// SQLLexer.g:1:1657: StringLiteral
				{
				mStringLiteral(); 

				}
				break;
			case 177 :
				// SQLLexer.g:1:1671: DecimalLiteral
				{
				mDecimalLiteral(); 

				}
				break;
			case 178 :
				// SQLLexer.g:1:1686: ByteLengthLiteral
				{
				mByteLengthLiteral(); 

				}
				break;
			case 179 :
				// SQLLexer.g:1:1704: Number
				{
				mNumber(); 

				}
				break;
			case 180 :
				// SQLLexer.g:1:1711: Identifier
				{
				mIdentifier(); 

				}
				break;
			case 181 :
				// SQLLexer.g:1:1722: CharSetName
				{
				mCharSetName(); 

				}
				break;
			case 182 :
				// SQLLexer.g:1:1734: WS
				{
				mWS(); 

				}
				break;
			case 183 :
				// SQLLexer.g:1:1737: SL_COMMENT
				{
				mSL_COMMENT(); 

				}
				break;
			case 184 :
				// SQLLexer.g:1:1748: ML_COMMENT
				{
				mML_COMMENT(); 

				}
				break;

		}
	}


	protected DFA21 dfa21 = new DFA21(this);
	static final String DFA21_eotS =
		"\1\uffff\5\62\1\120\22\62\13\uffff\1\u0088\1\u008a\1\u008c\1\uffff\1\u008d"+
		"\10\uffff\1\u0090\4\uffff\16\62\1\u00a8\2\62\1\u00ac\1\62\1\u00ae\1\62"+
		"\1\u00b0\3\62\2\uffff\3\62\1\u00ba\1\u00c1\1\62\1\u00c3\11\62\1\u00d6"+
		"\45\62\1\u010f\6\uffff\1\u0090\1\62\1\uffff\1\u0116\1\u0090\1\u0116\21"+
		"\62\1\u0128\1\u0129\1\u012a\1\uffff\1\62\1\u012c\1\62\1\uffff\1\62\1\uffff"+
		"\1\u0131\1\uffff\1\62\1\120\7\62\1\uffff\2\62\1\u013f\3\62\1\uffff\1\62"+
		"\1\uffff\5\62\1\u014a\10\62\1\u0157\3\62\1\uffff\14\62\1\u0168\22\62\1"+
		"\u017d\4\62\1\u0183\10\62\1\u018f\3\62\1\u0193\5\62\2\uffff\1\u0090\3"+
		"\uffff\1\u0090\1\u0112\1\uffff\1\u019d\3\62\1\u01a3\2\62\1\u01a6\1\62"+
		"\1\u01a8\1\u01a9\6\62\3\uffff\1\62\1\uffff\1\62\1\u01b3\2\62\1\uffff\1"+
		"\62\1\u01b7\1\u01b8\1\62\1\u01ba\1\u01bb\1\62\1\u01be\1\u01bf\3\62\1\u01c4"+
		"\1\uffff\12\62\1\uffff\1\u01cf\1\u01d0\10\62\1\u01db\1\62\1\uffff\4\62"+
		"\1\u01e1\5\62\1\u01e7\1\62\1\u01e9\1\62\1\u01eb\1\62\1\uffff\1\u01ed\1"+
		"\62\1\u01ef\5\62\1\u01f5\1\62\1\u01f8\2\62\1\u01fb\1\62\1\u01fd\4\62\1"+
		"\uffff\1\62\1\u0203\3\62\1\uffff\4\62\1\u020b\1\u020d\3\62\1\u0211\1\62"+
		"\1\uffff\1\62\1\u0214\1\62\1\uffff\2\62\1\u0218\1\62\1\u021a\1\uffff\2"+
		"\u0090\1\62\1\uffff\1\62\1\u021c\3\62\1\uffff\2\62\1\uffff\1\u0222\2\uffff"+
		"\1\62\1\u0224\3\62\1\u0228\1\u0229\1\u022a\1\62\1\uffff\1\62\1\u022d\1"+
		"\62\2\uffff\1\u022f\2\uffff\1\62\1\u0231\2\uffff\1\62\1\u0233\2\62\1\uffff"+
		"\1\62\1\u0237\10\62\2\uffff\12\62\1\uffff\1\62\1\u024b\1\u024c\2\62\1"+
		"\uffff\5\62\1\uffff\1\u0254\1\uffff\1\u0255\1\uffff\1\62\1\uffff\1\62"+
		"\1\uffff\5\62\1\uffff\1\u025d\1\62\1\uffff\1\62\1\u0260\1\uffff\1\62\1"+
		"\uffff\3\62\1\u0265\1\u0266\1\uffff\6\62\1\u026d\1\uffff\1\62\1\uffff"+
		"\3\62\1\uffff\1\62\1\u0273\1\uffff\1\u0274\2\62\1\uffff\1\62\1\uffff\1"+
		"\62\1\uffff\1\u0279\4\62\1\uffff\1\62\1\uffff\1\u027f\1\u0280\1\62\3\uffff"+
		"\2\62\1\uffff\1\u0284\1\uffff\1\62\1\uffff\1\u0286\1\uffff\3\62\1\uffff"+
		"\1\u028a\1\u028b\1\u028c\1\u028d\3\62\1\u0291\2\62\1\u0294\1\u0295\4\62"+
		"\1\u029a\2\62\2\uffff\2\62\1\u029f\1\62\1\u02a1\1\u02a2\1\u02a3\2\uffff"+
		"\1\u02a4\1\u02a5\1\u02a6\1\u02a7\1\u02a8\1\u02a9\1\62\1\uffff\1\62\1\u02ac"+
		"\1\uffff\2\62\1\u02af\1\62\2\uffff\1\u02b1\2\62\1\u02b4\1\62\1\u02b6\1"+
		"\uffff\3\62\1\u02ba\1\62\2\uffff\1\u02bc\2\62\1\u02bf\1\uffff\5\62\2\uffff"+
		"\3\62\1\uffff\1\62\1\uffff\1\u02c9\2\62\4\uffff\1\u02cc\1\62\1\u02ce\1"+
		"\uffff\1\u02cf\1\62\2\uffff\1\u02d1\2\62\1\u02d4\1\uffff\2\62\1\u02d7"+
		"\1\u02d8\1\uffff\1\u02d9\11\uffff\2\62\1\uffff\1\62\1\u02dd\1\uffff\1"+
		"\62\1\uffff\2\62\1\uffff\1\62\1\uffff\3\62\1\uffff\1\u02e5\1\uffff\1\62"+
		"\1\u02e7\1\uffff\1\62\1\u02e9\2\62\1\u02ec\3\62\1\u02f0\1\uffff\1\u02f1"+
		"\1\62\1\uffff\1\62\2\uffff\1\62\1\uffff\1\u02f5\1\62\1\uffff\1\u02f7\1"+
		"\u02f8\3\uffff\1\u02f9\1\u02fa\1\62\1\uffff\1\62\1\u02fd\3\62\1\u0301"+
		"\1\62\1\uffff\1\u0303\1\uffff\1\u0304\1\uffff\1\62\1\u0306\1\uffff\1\62"+
		"\1\u0308\1\62\2\uffff\1\62\1\u030b\1\u030c\1\uffff\1\62\4\uffff\1\u030e"+
		"\1\u030f\1\uffff\1\62\1\u0311\1\62\1\uffff\1\62\2\uffff\1\u0314\1\uffff"+
		"\1\u0315\1\uffff\2\62\2\uffff\1\u0318\2\uffff\1\62\1\uffff\2\62\2\uffff"+
		"\1\62\1\u031d\1\uffff\2\62\1\u0320\1\u0321\1\uffff\1\u0322\1\u0323\4\uffff";
	static final String DFA21_eofS =
		"\u0324\uffff";
	static final String DFA21_minS =
		"\1\11\2\101\1\104\1\106\1\117\1\75\1\105\1\106\1\114\1\101\1\122\1\105"+
		"\1\101\1\110\1\105\1\117\2\101\1\116\2\101\1\111\1\125\1\105\13\uffff"+
		"\2\75\1\52\1\uffff\1\55\10\uffff\1\56\4\uffff\1\111\1\102\2\115\1\105"+
		"\1\114\1\117\1\114\1\117\1\122\1\105\1\124\1\114\1\104\1\60\1\122\1\104"+
		"\1\60\1\105\1\60\1\124\1\60\2\124\1\114\2\uffff\1\113\1\106\1\101\2\60"+
		"\1\120\1\60\2\103\1\101\1\123\1\103\1\123\1\125\1\124\1\101\1\60\1\117"+
		"\1\106\1\107\1\126\1\125\1\105\1\124\1\103\2\122\2\101\1\111\1\107\1\101"+
		"\1\116\1\127\2\122\1\117\1\105\1\104\1\103\1\104\1\105\1\123\1\116\1\122"+
		"\1\101\1\103\1\124\1\104\1\116\1\114\1\105\2\101\1\76\6\uffff\1\60\1\53"+
		"\1\uffff\1\60\1\56\1\60\1\105\1\107\1\114\1\107\1\105\1\115\1\120\1\116"+
		"\1\123\1\115\1\114\1\103\1\101\1\115\1\114\1\105\1\103\3\60\1\uffff\1"+
		"\101\1\60\1\105\1\uffff\1\122\1\uffff\1\60\1\uffff\1\111\1\60\1\114\1"+
		"\105\1\111\1\124\1\104\1\101\1\107\1\uffff\2\105\1\60\1\125\1\105\1\117"+
		"\1\uffff\1\117\1\uffff\1\123\2\114\1\101\1\102\1\60\1\105\1\103\1\111"+
		"\1\105\2\101\1\102\1\101\1\60\1\125\1\116\1\105\1\uffff\1\114\1\110\1"+
		"\117\1\127\1\101\2\111\1\122\1\116\1\114\1\110\1\105\1\60\1\111\1\117"+
		"\1\124\1\122\1\111\1\122\1\114\1\116\1\110\1\104\1\117\1\107\1\123\1\124"+
		"\1\110\2\103\1\116\1\60\3\117\1\111\1\60\1\101\1\105\1\101\1\123\1\105"+
		"\1\103\1\123\1\122\1\60\1\122\1\101\1\124\1\60\1\125\1\101\1\127\1\114"+
		"\1\122\2\uffff\1\60\1\53\1\uffff\3\60\1\uffff\1\60\1\107\2\105\1\60\1"+
		"\111\1\117\1\60\1\105\2\60\2\124\1\101\1\104\1\106\1\110\3\uffff\1\131"+
		"\1\uffff\1\122\1\60\1\125\1\122\1\uffff\1\117\2\60\1\124\2\60\1\124\2"+
		"\60\1\122\1\130\1\107\1\60\1\uffff\1\124\1\122\1\113\1\122\1\124\1\122"+
		"\1\101\1\125\1\120\1\114\1\uffff\2\60\2\115\1\124\1\116\1\125\1\111\1"+
		"\102\1\114\1\60\1\102\1\uffff\1\120\1\124\1\111\1\105\1\60\1\122\1\105"+
		"\1\122\2\116\1\60\1\105\1\60\1\105\1\60\1\103\1\uffff\1\60\1\116\1\60"+
		"\1\103\1\116\1\103\1\105\1\114\1\60\1\124\1\60\1\113\1\105\1\60\1\111"+
		"\1\60\1\105\1\117\1\105\1\107\1\uffff\1\116\1\60\1\103\1\107\1\124\1\uffff"+
		"\1\124\1\122\1\124\1\123\2\60\1\111\1\101\1\117\1\60\1\117\1\uffff\1\117"+
		"\1\60\1\110\1\uffff\2\124\1\60\1\111\4\60\1\104\1\uffff\1\105\1\60\2\124"+
		"\1\117\1\uffff\1\116\1\122\1\uffff\1\60\2\uffff\1\111\1\60\1\124\1\123"+
		"\1\117\3\60\1\122\1\uffff\1\124\1\60\1\116\2\uffff\1\60\2\uffff\1\111"+
		"\1\60\2\uffff\1\124\1\60\1\105\1\126\1\uffff\1\106\1\60\1\105\1\124\1"+
		"\123\1\124\1\111\1\123\2\105\2\uffff\1\101\1\111\2\105\1\114\1\116\1\111"+
		"\1\114\1\105\1\111\1\uffff\1\101\2\60\1\116\1\101\1\uffff\2\105\1\131"+
		"\1\124\1\107\1\uffff\1\60\1\uffff\1\60\1\uffff\1\124\1\uffff\1\104\1\uffff"+
		"\1\105\1\107\1\124\1\104\1\111\1\uffff\1\60\1\116\1\uffff\1\105\1\60\1"+
		"\uffff\1\124\1\uffff\1\116\2\104\2\60\1\uffff\1\113\1\116\1\115\1\105"+
		"\1\103\1\105\1\60\1\uffff\1\120\1\uffff\1\116\1\124\1\122\1\uffff\1\111"+
		"\1\60\1\uffff\1\60\1\105\1\111\1\uffff\1\106\1\uffff\1\122\1\uffff\1\60"+
		"\1\101\1\116\2\101\1\uffff\1\117\1\uffff\2\60\1\122\3\uffff\1\111\1\106"+
		"\1\uffff\1\60\1\uffff\1\117\1\uffff\1\60\1\uffff\1\122\1\101\1\117\1\uffff"+
		"\4\60\1\116\1\111\1\104\1\60\1\114\1\124\2\60\1\124\1\103\1\102\1\105"+
		"\1\60\1\115\1\123\2\uffff\1\124\1\116\1\60\1\116\3\60\2\uffff\6\60\1\116"+
		"\1\uffff\1\114\1\60\1\uffff\1\111\1\124\1\60\1\125\2\uffff\1\60\2\105"+
		"\1\60\1\101\1\60\1\uffff\1\105\1\125\1\105\1\60\1\116\2\uffff\1\60\1\114"+
		"\1\131\1\60\1\uffff\1\115\1\105\1\124\1\122\1\116\2\uffff\1\115\1\124"+
		"\1\117\1\uffff\1\116\1\uffff\1\60\1\114\1\122\4\uffff\1\60\1\126\1\60"+
		"\1\uffff\1\60\1\105\2\uffff\1\60\1\124\1\125\1\60\1\uffff\2\105\2\60\1"+
		"\uffff\1\60\11\uffff\1\124\1\131\1\uffff\1\117\1\60\1\uffff\1\122\1\uffff"+
		"\1\104\1\123\1\uffff\1\123\1\uffff\1\103\1\105\1\116\1\uffff\1\60\1\uffff"+
		"\1\105\1\60\1\uffff\1\120\1\60\1\105\1\131\1\60\1\101\1\105\1\122\1\60"+
		"\1\uffff\1\60\1\115\1\uffff\1\105\2\uffff\1\104\1\uffff\1\60\1\124\1\uffff"+
		"\2\60\3\uffff\2\60\1\116\1\uffff\1\105\1\60\1\124\1\105\1\111\1\60\1\101"+
		"\1\uffff\1\60\1\uffff\1\60\1\uffff\1\104\1\60\1\uffff\1\124\1\60\1\115"+
		"\2\uffff\1\101\2\60\1\uffff\1\105\4\uffff\2\60\1\uffff\1\101\1\60\1\106"+
		"\1\uffff\1\124\2\uffff\1\60\1\uffff\1\60\1\uffff\1\101\1\124\2\uffff\1"+
		"\60\2\uffff\1\115\1\uffff\1\111\1\105\2\uffff\1\124\1\60\1\uffff\1\120"+
		"\1\103\2\60\1\uffff\2\60\4\uffff";
	static final String DFA21_maxS =
		"\1\176\1\122\1\125\1\123\1\126\1\125\1\75\1\117\1\123\1\130\1\117\1\122"+
		"\1\131\1\117\1\111\1\124\2\117\1\122\1\124\1\125\2\117\1\125\1\105\13"+
		"\uffff\1\76\1\75\1\52\1\uffff\1\55\10\uffff\1\172\4\uffff\1\125\1\122"+
		"\1\115\1\122\1\105\1\114\1\117\1\116\1\117\1\122\1\114\1\124\1\114\1\104"+
		"\1\172\1\122\1\104\1\172\1\105\1\172\1\124\1\172\2\124\1\114\2\uffff\1"+
		"\115\1\106\1\116\2\172\1\120\1\172\1\120\1\103\1\104\3\123\1\125\1\131"+
		"\1\117\1\172\2\124\1\116\1\126\1\125\1\111\2\124\1\125\1\122\2\101\1\111"+
		"\1\107\1\126\1\116\1\127\1\124\1\122\1\117\1\111\1\123\1\103\1\120\1\117"+
		"\1\123\1\116\1\122\1\101\1\120\1\124\2\116\1\114\1\105\2\101\1\76\6\uffff"+
		"\1\145\1\71\1\uffff\3\172\1\105\1\107\1\114\1\107\1\105\1\115\1\120\1"+
		"\116\1\123\1\115\1\114\1\103\1\101\1\115\1\114\1\105\1\103\3\172\1\uffff"+
		"\1\101\1\172\1\105\1\uffff\1\122\1\uffff\1\172\1\uffff\1\111\1\172\1\114"+
		"\1\105\1\111\1\124\1\104\1\113\1\107\1\uffff\2\105\1\172\1\125\1\105\1"+
		"\117\1\uffff\1\117\1\uffff\1\123\1\117\1\114\1\101\1\102\1\172\1\105\1"+
		"\103\3\111\1\124\1\102\1\105\1\172\1\125\1\116\1\105\1\uffff\1\114\1\110"+
		"\1\117\1\127\1\101\2\111\2\122\1\114\1\110\1\105\1\172\1\111\1\117\1\124"+
		"\1\122\1\125\1\122\1\114\1\116\1\110\1\104\1\117\1\107\1\123\1\124\1\110"+
		"\1\111\1\103\1\116\1\172\3\117\1\111\1\172\1\101\1\105\1\101\1\123\2\124"+
		"\1\123\1\122\1\172\1\122\1\101\1\124\1\172\1\125\1\101\1\127\1\114\1\122"+
		"\2\uffff\1\145\1\71\1\uffff\1\71\2\172\1\uffff\1\172\1\107\2\105\1\172"+
		"\1\111\1\117\1\172\1\105\2\172\2\124\1\101\1\104\1\106\1\110\3\uffff\1"+
		"\131\1\uffff\1\122\1\172\1\125\1\122\1\uffff\1\117\2\172\1\124\2\172\1"+
		"\124\2\172\1\122\1\130\1\122\1\172\1\uffff\1\124\1\122\1\113\1\122\1\124"+
		"\1\122\1\101\1\125\1\120\1\114\1\uffff\2\172\2\115\1\124\1\116\1\125\1"+
		"\122\1\102\1\114\1\172\1\102\1\uffff\1\120\1\124\1\111\1\105\1\172\1\122"+
		"\1\105\1\122\2\116\1\172\1\105\1\172\1\105\1\172\1\103\1\uffff\1\172\1"+
		"\116\1\172\1\103\1\116\1\103\1\105\1\114\1\172\1\124\1\172\1\113\1\105"+
		"\1\172\1\111\1\172\1\105\1\117\1\105\1\107\1\uffff\1\116\1\172\1\103\1"+
		"\107\1\124\1\uffff\1\124\1\122\1\124\1\123\2\172\1\111\1\101\1\117\1\172"+
		"\1\117\1\uffff\1\117\1\172\1\110\1\uffff\2\124\1\172\1\111\1\172\1\71"+
		"\2\102\1\104\1\uffff\1\105\1\172\2\124\1\117\1\uffff\1\116\1\122\1\uffff"+
		"\1\172\2\uffff\1\111\1\172\1\124\1\123\1\117\3\172\1\122\1\uffff\1\124"+
		"\1\172\1\116\2\uffff\1\172\2\uffff\1\111\1\172\2\uffff\1\124\1\172\1\105"+
		"\1\126\1\uffff\1\106\1\172\1\105\1\124\1\123\1\124\1\111\1\123\2\105\2"+
		"\uffff\1\101\1\111\2\105\1\114\1\116\1\111\1\114\1\105\1\111\1\uffff\1"+
		"\101\2\172\1\116\1\101\1\uffff\2\105\1\131\1\124\1\107\1\uffff\1\172\1"+
		"\uffff\1\172\1\uffff\1\124\1\uffff\1\104\1\uffff\1\105\1\107\1\124\1\104"+
		"\1\111\1\uffff\1\172\1\116\1\uffff\1\105\1\172\1\uffff\1\124\1\uffff\1"+
		"\116\2\104\2\172\1\uffff\1\113\1\116\1\115\1\105\1\103\1\105\1\172\1\uffff"+
		"\1\120\1\uffff\1\116\1\124\1\122\1\uffff\1\111\1\172\1\uffff\1\172\1\105"+
		"\1\111\1\uffff\1\106\1\uffff\1\122\1\uffff\1\172\1\101\1\116\2\101\1\uffff"+
		"\1\117\1\uffff\2\172\1\122\3\uffff\1\111\1\106\1\uffff\1\172\1\uffff\1"+
		"\117\1\uffff\1\172\1\uffff\1\122\1\101\1\117\1\uffff\4\172\1\116\1\111"+
		"\1\104\1\172\1\114\1\124\2\172\1\124\1\103\1\102\1\105\1\172\1\115\1\123"+
		"\2\uffff\1\124\1\116\1\172\1\116\3\172\2\uffff\6\172\1\116\1\uffff\1\114"+
		"\1\172\1\uffff\1\111\1\124\1\172\1\125\2\uffff\1\172\2\105\1\172\1\101"+
		"\1\172\1\uffff\1\105\1\125\1\105\1\172\1\116\2\uffff\1\172\1\114\1\131"+
		"\1\172\1\uffff\1\115\1\105\1\124\1\122\1\116\2\uffff\1\115\1\124\1\117"+
		"\1\uffff\1\116\1\uffff\1\172\1\114\1\122\4\uffff\1\172\1\126\1\172\1\uffff"+
		"\1\172\1\105\2\uffff\1\172\1\124\1\125\1\172\1\uffff\2\105\2\172\1\uffff"+
		"\1\172\11\uffff\1\124\1\131\1\uffff\1\117\1\172\1\uffff\1\122\1\uffff"+
		"\1\104\1\123\1\uffff\1\123\1\uffff\1\103\1\105\1\116\1\uffff\1\172\1\uffff"+
		"\1\105\1\172\1\uffff\1\120\1\172\1\105\1\131\1\172\1\101\1\105\1\122\1"+
		"\172\1\uffff\1\172\1\115\1\uffff\1\105\2\uffff\1\104\1\uffff\1\172\1\124"+
		"\1\uffff\2\172\3\uffff\2\172\1\116\1\uffff\1\105\1\172\1\124\1\105\1\111"+
		"\1\172\1\101\1\uffff\1\172\1\uffff\1\172\1\uffff\1\104\1\172\1\uffff\1"+
		"\124\1\172\1\115\2\uffff\1\101\2\172\1\uffff\1\105\4\uffff\2\172\1\uffff"+
		"\1\101\1\172\1\106\1\uffff\1\124\2\uffff\1\172\1\uffff\1\172\1\uffff\1"+
		"\101\1\124\2\uffff\1\172\2\uffff\1\115\1\uffff\1\111\1\105\2\uffff\1\124"+
		"\1\172\1\uffff\1\120\1\103\2\172\1\uffff\2\172\4\uffff";
	static final String DFA21_acceptS =
		"\31\uffff\1\u0093\1\u0094\1\u0095\1\u0096\1\u0097\1\u0098\1\u0099\1\u009a"+
		"\1\u009b\1\u009c\1\u009d\3\uffff\1\u00a5\1\uffff\1\u00a7\1\u00a9\1\u00aa"+
		"\1\u00ab\1\u00ac\1\u00ad\1\u00ae\1\u00b0\1\uffff\1\u00b4\1\u00b5\1\u00b6"+
		"\1\u00b7\31\uffff\1\u009f\1\6\67\uffff\1\u00a1\1\u00a2\1\u00a3\1\u00b8"+
		"\1\u00a4\1\u00a6\2\uffff\1\u00b3\27\uffff\1\22\3\uffff\1\5\1\uffff\1\33"+
		"\1\uffff\1\104\11\uffff\1\10\6\uffff\1\134\1\uffff\1\46\22\uffff\1\16"+
		"\67\uffff\1\u009e\1\u00a0\2\uffff\1\u00b1\3\uffff\1\u00b2\21\uffff\1\3"+
		"\1\4\1\12\1\uffff\1\107\4\uffff\1\103\15\uffff\1\u0085\12\uffff\1\124"+
		"\14\uffff\1\u008b\20\uffff\1\117\24\uffff\1\164\5\uffff\1\126\13\uffff"+
		"\1\65\3\uffff\1\u00a8\11\uffff\1\1\5\uffff\1\57\2\uffff\1\122\1\uffff"+
		"\1\21\1\32\11\uffff\1\u0080\3\uffff\1\47\1\7\1\uffff\1\30\1\43\2\uffff"+
		"\1\141\1\130\4\uffff\1\66\12\uffff\1\123\1\13\12\uffff\1\55\5\uffff\1"+
		"\155\5\uffff\1\u008d\1\uffff\1\121\1\uffff\1\115\1\uffff\1\163\1\uffff"+
		"\1\41\5\uffff\1\27\2\uffff\1\151\2\uffff\1\177\1\uffff\1\172\5\uffff\1"+
		"\140\7\uffff\1\106\1\uffff\1\120\3\uffff\1\u0087\2\uffff\1\173\3\uffff"+
		"\1\133\1\uffff\1\u008c\1\uffff\1\35\5\uffff\1\2\1\uffff\1\53\3\uffff\1"+
		"\132\1\63\1\14\2\uffff\1\176\1\uffff\1\116\1\uffff\1\142\1\uffff\1\36"+
		"\3\uffff\1\170\23\uffff\1\15\1\136\7\uffff\1\20\1\150\7\uffff\1\31\2\uffff"+
		"\1\152\4\uffff\1\37\1\42\6\uffff\1\157\5\uffff\1\113\1\u008a\4\uffff\1"+
		"\u0086\5\uffff\1\67\1\71\3\uffff\1\165\1\uffff\1\25\3\uffff\1\174\1\45"+
		"\1\11\1\44\3\uffff\1\77\2\uffff\1\131\1\175\4\uffff\1\54\4\uffff\1\153"+
		"\1\uffff\1\156\1\u0084\1\17\1\23\1\u008f\1\171\1\62\1\64\1\144\2\uffff"+
		"\1\137\2\uffff\1\u0091\1\uffff\1\143\2\uffff\1\167\1\uffff\1\50\3\uffff"+
		"\1\161\1\uffff\1\u008e\2\uffff\1\162\11\uffff\1\52\2\uffff\1\114\1\uffff"+
		"\1\73\1\61\1\uffff\1\u0092\2\uffff\1\100\2\uffff\1\u0083\1\51\1\154\3"+
		"\uffff\1\105\7\uffff\1\125\1\uffff\1\u0081\1\uffff\1\u0089\2\uffff\1\112"+
		"\3\uffff\1\102\1\u0090\3\uffff\1\24\1\uffff\1\56\1\135\1\u0082\1\101\2"+
		"\uffff\1\147\3\uffff\1\160\1\uffff\1\110\1\60\1\uffff\1\111\1\uffff\1"+
		"\26\2\uffff\1\145\1\70\1\uffff\1\34\1\146\1\uffff\1\u0088\2\uffff\1\72"+
		"\1\74\2\uffff\1\40\4\uffff\1\75\2\uffff\1\166\1\76\1\127\1\u00af";
	static final String DFA21_specialS =
		"\u0324\uffff}>";
	static final String[] DFA21_transitionS = {
			"\2\64\2\uffff\1\64\22\uffff\1\64\1\6\1\60\1\65\1\57\1\uffff\1\52\1\60"+
			"\1\35\1\36\1\51\1\47\1\33\1\50\1\31\1\46\12\61\1\32\1\34\1\44\1\43\1"+
			"\45\1\56\1\uffff\1\3\1\14\1\24\1\12\1\11\1\2\1\13\1\15\1\10\1\20\1\62"+
			"\1\7\1\25\1\5\1\4\1\22\1\27\1\21\1\17\1\1\1\23\1\26\1\16\1\62\1\30\1"+
			"\62\1\37\1\uffff\1\40\1\55\1\63\33\62\1\41\1\54\1\42\1\53",
			"\1\67\3\uffff\1\71\2\uffff\1\72\1\70\10\uffff\1\66",
			"\1\73\3\uffff\1\101\3\uffff\1\100\2\uffff\1\76\2\uffff\1\77\2\uffff"+
			"\1\74\2\uffff\1\75",
			"\1\106\7\uffff\1\102\1\uffff\1\103\3\uffff\1\105\1\104",
			"\1\113\7\uffff\1\111\1\uffff\1\114\1\uffff\1\107\2\uffff\1\112\1\110",
			"\1\115\5\uffff\1\116",
			"\1\117",
			"\1\122\3\uffff\1\121\5\uffff\1\123",
			"\1\124\6\uffff\1\126\1\125\4\uffff\1\127",
			"\1\133\1\uffff\1\132\4\uffff\1\131\4\uffff\1\130",
			"\1\137\3\uffff\1\134\3\uffff\1\135\5\uffff\1\136",
			"\1\140",
			"\1\143\3\uffff\1\144\5\uffff\1\142\11\uffff\1\141",
			"\1\145\15\uffff\1\146",
			"\1\147\1\150",
			"\1\151\2\uffff\1\154\4\uffff\1\155\1\uffff\1\152\4\uffff\1\153",
			"\1\156",
			"\1\161\3\uffff\1\160\3\uffff\1\157\5\uffff\1\162",
			"\1\163\3\uffff\1\164\14\uffff\1\165",
			"\1\167\1\uffff\1\171\2\uffff\1\166\1\170",
			"\1\173\6\uffff\1\176\6\uffff\1\174\2\uffff\1\172\2\uffff\1\175",
			"\1\177\3\uffff\1\u0080\3\uffff\1\u0082\5\uffff\1\u0081",
			"\1\u0084\5\uffff\1\u0083",
			"\1\u0085",
			"\1\u0086",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\u0087\1\117",
			"\1\u0089",
			"\1\u008b",
			"",
			"\1\65",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\u008e\1\uffff\12\u0092\7\uffff\1\62\1\u0091\2\62\1\u008f\1\62\1\u0093"+
			"\3\62\1\u0093\1\62\1\u0093\15\62\4\uffff\1\62\1\uffff\1\62\1\u0093\2"+
			"\62\1\u008f\1\62\1\u0093\3\62\1\u0093\1\62\1\u0093\15\62",
			"",
			"",
			"",
			"",
			"\1\u0095\13\uffff\1\u0094",
			"\1\u0096\17\uffff\1\u0097",
			"\1\u0098",
			"\1\u009a\4\uffff\1\u0099",
			"\1\u009b",
			"\1\u009c",
			"\1\u009d",
			"\1\u009e\1\uffff\1\u009f",
			"\1\u00a0",
			"\1\u00a1",
			"\1\u00a2\6\uffff\1\u00a3",
			"\1\u00a4",
			"\1\u00a5",
			"\1\u00a6",
			"\12\62\7\uffff\2\62\1\u00a7\27\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u00a9",
			"\1\u00aa",
			"\12\62\7\uffff\3\62\1\u00ab\26\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u00ad",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u00af",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u00b1",
			"\1\u00b2",
			"\1\u00b3",
			"",
			"",
			"\1\u00b4\1\uffff\1\u00b5",
			"\1\u00b6",
			"\1\u00b7\1\uffff\1\u00b8\12\uffff\1\u00b9",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\3\62\1\u00bc\11\62\1\u00bf\1\62\1\u00be\2\62\1\u00bb"+
			"\1\u00bd\1\62\1\u00c0\4\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u00c2",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u00c6\5\uffff\1\u00c4\6\uffff\1\u00c5",
			"\1\u00c7",
			"\1\u00c8\2\uffff\1\u00c9",
			"\1\u00ca",
			"\1\u00cc\2\uffff\1\u00ce\5\uffff\1\u00cd\6\uffff\1\u00cb",
			"\1\u00cf",
			"\1\u00d0",
			"\1\u00d1\4\uffff\1\u00d2",
			"\1\u00d4\15\uffff\1\u00d3",
			"\12\62\7\uffff\23\62\1\u00d5\6\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u00d7\4\uffff\1\u00d8",
			"\1\u00d9\15\uffff\1\u00da",
			"\1\u00dc\6\uffff\1\u00db",
			"\1\u00dd",
			"\1\u00de",
			"\1\u00df\3\uffff\1\u00e0",
			"\1\u00e1",
			"\1\u00e5\10\uffff\1\u00e2\1\u00e4\6\uffff\1\u00e3",
			"\1\u00e6\2\uffff\1\u00e7",
			"\1\u00e8",
			"\1\u00e9",
			"\1\u00ea",
			"\1\u00eb",
			"\1\u00ec",
			"\1\u00ed\24\uffff\1\u00ee",
			"\1\u00ef",
			"\1\u00f0",
			"\1\u00f1\1\uffff\1\u00f2",
			"\1\u00f3",
			"\1\u00f4",
			"\1\u00f6\3\uffff\1\u00f5",
			"\1\u00f8\4\uffff\1\u00f7\2\uffff\1\u00f9\6\uffff\1\u00fa",
			"\1\u00fb",
			"\1\u00fc\13\uffff\1\u00fd",
			"\1\u00fe\11\uffff\1\u00ff",
			"\1\u0100",
			"\1\u0101",
			"\1\u0102",
			"\1\u0103",
			"\1\u0105\14\uffff\1\u0104",
			"\1\u0106",
			"\1\u0108\11\uffff\1\u0107",
			"\1\u0109",
			"\1\u010a",
			"\1\u010b",
			"\1\u010c",
			"\1\u010d",
			"\1\u010e",
			"",
			"",
			"",
			"",
			"",
			"",
			"\12\u0110\10\uffff\1\u0112\2\uffff\1\u0111\37\uffff\1\u0111",
			"\1\u0113\1\uffff\1\u0113\2\uffff\12\u0114",
			"",
			"\12\62\7\uffff\3\62\1\u0115\26\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u008e\1\uffff\12\u0092\7\uffff\1\62\1\u0091\2\62\1\u008f\1\62\1\u0093"+
			"\3\62\1\u0093\1\62\1\u0093\15\62\4\uffff\1\62\1\uffff\1\62\1\u0093\2"+
			"\62\1\u008f\1\62\1\u0093\3\62\1\u0093\1\62\1\u0093\15\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0117",
			"\1\u0118",
			"\1\u0119",
			"\1\u011a",
			"\1\u011b",
			"\1\u011c",
			"\1\u011d",
			"\1\u011e",
			"\1\u011f",
			"\1\u0120",
			"\1\u0121",
			"\1\u0122",
			"\1\u0123",
			"\1\u0124",
			"\1\u0125",
			"\1\u0126",
			"\1\u0127",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u012b",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u012d",
			"",
			"\1\u012e",
			"",
			"\12\62\7\uffff\4\62\1\u0130\12\62\1\u012f\12\62\4\uffff\1\62\1\uffff"+
			"\32\62",
			"",
			"\1\u0132",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0133",
			"\1\u0134",
			"\1\u0135",
			"\1\u0136",
			"\1\u0137",
			"\1\u0138\11\uffff\1\u0139",
			"\1\u013a",
			"",
			"\1\u013b",
			"\1\u013c",
			"\12\62\7\uffff\4\62\1\u013d\11\62\1\u013e\13\62\4\uffff\1\62\1\uffff"+
			"\32\62",
			"\1\u0140",
			"\1\u0141",
			"\1\u0142",
			"",
			"\1\u0143",
			"",
			"\1\u0144",
			"\1\u0146\2\uffff\1\u0145",
			"\1\u0147",
			"\1\u0148",
			"\1\u0149",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u014b",
			"\1\u014c",
			"\1\u014d",
			"\1\u014f\3\uffff\1\u014e",
			"\1\u0151\7\uffff\1\u0150",
			"\1\u0153\22\uffff\1\u0152",
			"\1\u0154",
			"\1\u0156\3\uffff\1\u0155",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0158",
			"\1\u0159",
			"\1\u015a",
			"",
			"\1\u015b",
			"\1\u015c",
			"\1\u015d",
			"\1\u015e",
			"\1\u015f",
			"\1\u0160",
			"\1\u0161",
			"\1\u0162",
			"\1\u0164\3\uffff\1\u0163",
			"\1\u0165",
			"\1\u0166",
			"\1\u0167",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0169",
			"\1\u016a",
			"\1\u016b",
			"\1\u016c",
			"\1\u016d\13\uffff\1\u016e",
			"\1\u016f",
			"\1\u0170",
			"\1\u0171",
			"\1\u0172",
			"\1\u0173",
			"\1\u0174",
			"\1\u0175",
			"\1\u0176",
			"\1\u0177",
			"\1\u0178",
			"\1\u0179\5\uffff\1\u017a",
			"\1\u017b",
			"\1\u017c",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u017e",
			"\1\u017f",
			"\1\u0180",
			"\1\u0181",
			"\12\62\7\uffff\32\62\4\uffff\1\u0182\1\uffff\32\62",
			"\1\u0184",
			"\1\u0185",
			"\1\u0186",
			"\1\u0187",
			"\1\u0189\16\uffff\1\u0188",
			"\1\u018b\20\uffff\1\u018a",
			"\1\u018c",
			"\1\u018d",
			"\12\62\7\uffff\11\62\1\u018e\20\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0190",
			"\1\u0191",
			"\1\u0192",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0194",
			"\1\u0195",
			"\1\u0196",
			"\1\u0197",
			"\1\u0198",
			"",
			"",
			"\12\u0110\10\uffff\1\u0112\2\uffff\1\u0111\37\uffff\1\u0111",
			"\1\u0199\1\uffff\1\u0199\2\uffff\12\u019a",
			"",
			"\12\u019b",
			"\12\u0114\7\uffff\1\62\1\u019c\30\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u019e",
			"\1\u019f",
			"\1\u01a0",
			"\12\62\7\uffff\22\62\1\u01a1\6\62\1\u01a2\4\uffff\1\62\1\uffff\32\62",
			"\1\u01a4",
			"\1\u01a5",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01a7",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01aa",
			"\1\u01ab",
			"\1\u01ac",
			"\1\u01ad",
			"\1\u01ae",
			"\1\u01af",
			"",
			"",
			"",
			"\1\u01b0",
			"",
			"\1\u01b1",
			"\12\62\7\uffff\26\62\1\u01b2\3\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01b4",
			"\1\u01b5",
			"",
			"\1\u01b6",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01b9",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01bc",
			"\12\62\7\uffff\22\62\1\u01bd\7\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01c0",
			"\1\u01c1",
			"\1\u01c2\12\uffff\1\u01c3",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u01c5",
			"\1\u01c6",
			"\1\u01c7",
			"\1\u01c8",
			"\1\u01c9",
			"\1\u01ca",
			"\1\u01cb",
			"\1\u01cc",
			"\1\u01cd",
			"\1\u01ce",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01d1",
			"\1\u01d2",
			"\1\u01d3",
			"\1\u01d4",
			"\1\u01d5",
			"\1\u01d6\10\uffff\1\u01d7",
			"\1\u01d8",
			"\1\u01d9",
			"\12\62\7\uffff\23\62\1\u01da\6\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01dc",
			"",
			"\1\u01dd",
			"\1\u01de",
			"\1\u01df",
			"\1\u01e0",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01e2",
			"\1\u01e3",
			"\1\u01e4",
			"\1\u01e5",
			"\1\u01e6",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01e8",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01ea",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01ec",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01ee",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01f0",
			"\1\u01f1",
			"\1\u01f2",
			"\1\u01f3",
			"\1\u01f4",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01f6",
			"\12\62\7\uffff\16\62\1\u01f7\13\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01f9",
			"\1\u01fa",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01fc",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u01fe",
			"\1\u01ff",
			"\1\u0200",
			"\1\u0201",
			"",
			"\1\u0202",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0204",
			"\1\u0205",
			"\1\u0206",
			"",
			"\1\u0207",
			"\1\u0208",
			"\1\u0209",
			"\1\u020a",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\22\62\1\u020c\7\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u020e",
			"\1\u020f",
			"\1\u0210",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0212",
			"",
			"\1\u0213",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0215",
			"",
			"\1\u0216",
			"\1\u0217",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0219",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\u019a",
			"\12\u019a\10\uffff\1\u0112",
			"\12\u019b\10\uffff\1\u0112",
			"\1\u0115",
			"",
			"\1\u021b",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u021d",
			"\1\u021e",
			"\1\u021f",
			"",
			"\1\u0220",
			"\1\u0221",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"",
			"\1\u0223",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0225",
			"\1\u0226",
			"\1\u0227",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u022b",
			"",
			"\1\u022c",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u022e",
			"",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"",
			"\1\u0230",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"",
			"\1\u0232",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0234",
			"\1\u0235",
			"",
			"\1\u0236",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0238",
			"\1\u0239",
			"\1\u023a",
			"\1\u023b",
			"\1\u023c",
			"\1\u023d",
			"\1\u023e",
			"\1\u023f",
			"",
			"",
			"\1\u0240",
			"\1\u0241",
			"\1\u0242",
			"\1\u0243",
			"\1\u0244",
			"\1\u0245",
			"\1\u0246",
			"\1\u0247",
			"\1\u0248",
			"\1\u0249",
			"",
			"\1\u024a",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u024d",
			"\1\u024e",
			"",
			"\1\u024f",
			"\1\u0250",
			"\1\u0251",
			"\1\u0252",
			"\1\u0253",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u0256",
			"",
			"\1\u0257",
			"",
			"\1\u0258",
			"\1\u0259",
			"\1\u025a",
			"\1\u025b",
			"\1\u025c",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u025e",
			"",
			"\1\u025f",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u0261",
			"",
			"\1\u0262",
			"\1\u0263",
			"\1\u0264",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u0267",
			"\1\u0268",
			"\1\u0269",
			"\1\u026a",
			"\1\u026b",
			"\1\u026c",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u026e",
			"",
			"\1\u026f",
			"\1\u0270",
			"\1\u0271",
			"",
			"\1\u0272",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0275",
			"\1\u0276",
			"",
			"\1\u0277",
			"",
			"\1\u0278",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u027a",
			"\1\u027b",
			"\1\u027c",
			"\1\u027d",
			"",
			"\1\u027e",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0281",
			"",
			"",
			"",
			"\1\u0282",
			"\1\u0283",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u0285",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u0287",
			"\1\u0288",
			"\1\u0289",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u028e",
			"\1\u028f",
			"\1\u0290",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0292",
			"\1\u0293",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0296",
			"\1\u0297",
			"\1\u0298",
			"\1\u0299",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u029b",
			"\1\u029c",
			"",
			"",
			"\1\u029d",
			"\1\u029e",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02a0",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02aa",
			"",
			"\1\u02ab",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u02ad",
			"\1\u02ae",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02b0",
			"",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02b2",
			"\1\u02b3",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02b5",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u02b7",
			"\1\u02b8",
			"\1\u02b9",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02bb",
			"",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02bd",
			"\1\u02be",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u02c0",
			"\1\u02c1",
			"\1\u02c2",
			"\1\u02c3",
			"\1\u02c4",
			"",
			"",
			"\1\u02c5",
			"\1\u02c6",
			"\1\u02c7",
			"",
			"\1\u02c8",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02ca",
			"\1\u02cb",
			"",
			"",
			"",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02cd",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02d0",
			"",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02d2",
			"\1\u02d3",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u02d5",
			"\1\u02d6",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\u02da",
			"\1\u02db",
			"",
			"\1\u02dc",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u02de",
			"",
			"\1\u02df",
			"\1\u02e0",
			"",
			"\1\u02e1",
			"",
			"\1\u02e2",
			"\1\u02e3",
			"\1\u02e4",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u02e6",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u02e8",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02ea",
			"\1\u02eb",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02ed",
			"\1\u02ee",
			"\1\u02ef",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02f2",
			"",
			"\1\u02f3",
			"",
			"",
			"\1\u02f4",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02f6",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02fb",
			"",
			"\1\u02fc",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u02fe",
			"\1\u02ff",
			"\1\u0300",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0302",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u0305",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u0307",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0309",
			"",
			"",
			"\1\u030a",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u030d",
			"",
			"",
			"",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u0310",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\1\u0312",
			"",
			"\1\u0313",
			"",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u0316",
			"\1\u0317",
			"",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"",
			"\1\u0319",
			"",
			"\1\u031a",
			"\1\u031b",
			"",
			"",
			"\1\u031c",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\1\u031e",
			"\1\u031f",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62",
			"",
			"",
			"",
			""
	};

	static final short[] DFA21_eot = DFA.unpackEncodedString(DFA21_eotS);
	static final short[] DFA21_eof = DFA.unpackEncodedString(DFA21_eofS);
	static final char[] DFA21_min = DFA.unpackEncodedStringToUnsignedChars(DFA21_minS);
	static final char[] DFA21_max = DFA.unpackEncodedStringToUnsignedChars(DFA21_maxS);
	static final short[] DFA21_accept = DFA.unpackEncodedString(DFA21_acceptS);
	static final short[] DFA21_special = DFA.unpackEncodedString(DFA21_specialS);
	static final short[][] DFA21_transition;

	static {
		int numStates = DFA21_transitionS.length;
		DFA21_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA21_transition[i] = DFA.unpackEncodedString(DFA21_transitionS[i]);
		}
	}

	protected class DFA21 extends DFA {

		public DFA21(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 21;
			this.eot = DFA21_eot;
			this.eof = DFA21_eof;
			this.min = DFA21_min;
			this.max = DFA21_max;
			this.accept = DFA21_accept;
			this.special = DFA21_special;
			this.transition = DFA21_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( KW_TRUE | KW_FALSE | KW_ALL | KW_AND | KW_OR | KW_NOT | KW_LIKE | KW_IF | KW_EXISTS | KW_ASC | KW_DESC | KW_ORDER | KW_GROUP | KW_BY | KW_HAVING | KW_WHERE | KW_FROM | KW_AS | KW_SELECT | KW_DISTINCT | KW_INSERT | KW_OVERWRITE | KW_JOIN | KW_LEFT | KW_RIGHT | KW_FULL | KW_ON | KW_PARTITION | KW_TABLE | KW_INDEX | KW_USING | KW_DISTRIBUTE | KW_SORT | KW_UNION | KW_LOAD | KW_EXPORT | KW_IMPORT | KW_IS | KW_NULL | KW_CREATE | KW_BOOLEAN | KW_INTEGER | KW_FLOAT | KW_DOUBLE | KW_DATE | KW_DATETIME | KW_TIME | KW_TIMESTAMP | KW_DECIMAL | KW_STRING | KW_ARRAY | KW_STRUCT | KW_MAP | KW_INTO | KW_FORMAT | KW_DELIMITED | KW_FIELDS | KW_TERMINATED | KW_ESCAPED | KW_FILEFORMAT | KW_INPUTFORMAT | KW_OUTPUTFORMAT | KW_ENABLE | KW_DISABLE | KW_READONLY | KW_LOCATION | KW_OUT | KW_OF | KW_PERCENT | KW_CAST | KW_ADD | KW_VOLATILE | KW_TEMPORARY | KW_FUNCTION | KW_MACRO | KW_EXPLAIN | KW_WITH | KW_LIMIT | KW_SET | KW_CASE | KW_WHEN | KW_THEN | KW_ELSE | KW_END | KW_MAPJOIN | KW_UTC | KW_UTCTIMESTAMP | KW_LONG | KW_DELETE | KW_FETCH | KW_VIEW | KW_IN | KW_DATABASE | KW_GRANT | KW_REVOKE | KW_UNDO | KW_LOCK | KW_LOCKS | KW_UNLOCK | KW_SHARED | KW_EXCLUSIVE | KW_PROCEDURE | KW_UNSIGNED | KW_WHILE | KW_READ | KW_RANGE | KW_BEFORE | KW_BETWEEN | KW_BOTH | KW_BINARY | KW_CROSS | KW_CONTINUE | KW_CURSOR | KW_TRIGGER | KW_SEMI | KW_USE | KW_OPTION | KW_CONCATENATE | KW_UPDATE | KW_INNER | KW_SOURCE | KW_PATH | KW_META | KW_INVOKE | KW_DEFINE | KW_OUTER | KW_ROWS | KW_OVER | KW_QUALIFY | KW_SMALLINT | KW_BYTEINT | KW_BIGINT | KW_INT | KW_TARGET | KW_CHAR | KW_UPPERCASE | KW_TIME_ZONE | KW_MONTH | KW_DAY | KW_YEAR | KW_HOUR | KW_MINUTE | KW_SECOND | KW_INTERVAL | KW_PERIOD | KW_DEFAULT | DOT | COLON | COMMA | SEMICOLON | LPAREN | RPAREN | LSQUARE | RSQUARE | LCURLY | RCURLY | EQUAL | EQUAL_NS | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN | DIVIDE | PLUS | MINUS | STAR | MOD | AMPERSAND | TILDE | BITWISEOR | BITWISEXOR | QUESTION | DOLLAR | KW_CASESPECIFIC | StringLiteral | DecimalLiteral | ByteLengthLiteral | Number | Identifier | CharSetName | WS | SL_COMMENT | ML_COMMENT );";
		}
	}

}
