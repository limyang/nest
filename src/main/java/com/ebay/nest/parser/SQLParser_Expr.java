// $ANTLR 3.5 Expr.g 2013-11-27 01:50:16

package com.ebay.nest.parser;

import java.util.Collection;
import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class SQLParser_Expr extends Parser {
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
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators
	public SQLParser gSQLParser;
	public SQLParser gParent;


	public SQLParser_Expr(TokenStream input, SQLParser gSQLParser) {
		this(input, new RecognizerSharedState(), gSQLParser);
	}
	public SQLParser_Expr(TokenStream input, RecognizerSharedState state, SQLParser gSQLParser) {
		super(input, state);
		this.gSQLParser = gSQLParser;
		gParent = gSQLParser;
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return SQLParser.tokenNames; }
	@Override public String getGrammarFileName() { return "Expr.g"; }


		@Override
		public Object recoverFromMismatchedSet(IntStream input, RecognitionException re, BitSet follow) throws RecognitionException {
			throw re;
		}
		@Override
		public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
			gParent.errors.add(new ParseError(gParent, e, tokenNames));
	  }


	public static class expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expression"
	// Expr.g:29:1: expression : precedenceOrExpression ;
	public final SQLParser_Expr.expression_return expression() throws RecognitionException {
		SQLParser_Expr.expression_return retval = new SQLParser_Expr.expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope precedenceOrExpression1 =null;


		try {
			// Expr.g:30:5: ( precedenceOrExpression )
			// Expr.g:31:5: precedenceOrExpression
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_precedenceOrExpression_in_expression58);
			precedenceOrExpression1=precedenceOrExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceOrExpression1.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expression"


	public static class atomExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "atomExpression"
	// Expr.g:34:1: atomExpression : ( KW_NULL -> TOK_NULL | constant | function | castExpression | caseExpression | whenExpression | tableOrColumn | LPAREN ! expression RPAREN !);
	public final SQLParser_Expr.atomExpression_return atomExpression() throws RecognitionException {
		SQLParser_Expr.atomExpression_return retval = new SQLParser_Expr.atomExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_NULL2=null;
		Token LPAREN9=null;
		Token RPAREN11=null;
		ParserRuleReturnScope constant3 =null;
		ParserRuleReturnScope function4 =null;
		ParserRuleReturnScope castExpression5 =null;
		ParserRuleReturnScope caseExpression6 =null;
		ParserRuleReturnScope whenExpression7 =null;
		ParserRuleReturnScope tableOrColumn8 =null;
		ParserRuleReturnScope expression10 =null;

		CommonTree KW_NULL2_tree=null;
		CommonTree LPAREN9_tree=null;
		CommonTree RPAREN11_tree=null;
		RewriteRuleTokenStream stream_KW_NULL=new RewriteRuleTokenStream(adaptor,"token KW_NULL");

		try {
			// Expr.g:35:5: ( KW_NULL -> TOK_NULL | constant | function | castExpression | caseExpression | whenExpression | tableOrColumn | LPAREN ! expression RPAREN !)
			int alt1=8;
			alt1 = dfa1.predict(input);
			switch (alt1) {
				case 1 :
					// Expr.g:36:5: KW_NULL
					{
					KW_NULL2=(Token)match(input,KW_NULL,FOLLOW_KW_NULL_in_atomExpression79); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_NULL.add(KW_NULL2);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 36:13: -> TOK_NULL
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_NULL, "TOK_NULL"));
					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// Expr.g:37:7: constant
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_constant_in_atomExpression91);
					constant3=constant();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, constant3.getTree());

					}
					break;
				case 3 :
					// Expr.g:38:7: function
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_function_in_atomExpression99);
					function4=function();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, function4.getTree());

					}
					break;
				case 4 :
					// Expr.g:39:7: castExpression
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_castExpression_in_atomExpression107);
					castExpression5=castExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, castExpression5.getTree());

					}
					break;
				case 5 :
					// Expr.g:40:7: caseExpression
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_caseExpression_in_atomExpression115);
					caseExpression6=caseExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, caseExpression6.getTree());

					}
					break;
				case 6 :
					// Expr.g:41:7: whenExpression
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_whenExpression_in_atomExpression123);
					whenExpression7=whenExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, whenExpression7.getTree());

					}
					break;
				case 7 :
					// Expr.g:42:7: tableOrColumn
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_tableOrColumn_in_atomExpression131);
					tableOrColumn8=gSQLParser.tableOrColumn();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, tableOrColumn8.getTree());

					}
					break;
				case 8 :
					// Expr.g:43:7: LPAREN ! expression RPAREN !
					{
					root_0 = (CommonTree)adaptor.nil();


					LPAREN9=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_atomExpression139); if (state.failed) return retval;
					pushFollow(FOLLOW_expression_in_atomExpression142);
					expression10=expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, expression10.getTree());

					RPAREN11=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_atomExpression144); if (state.failed) return retval;
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atomExpression"


	public static class precedenceFieldExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceFieldExpression"
	// Expr.g:47:1: precedenceFieldExpression : atomExpression ( ( LSQUARE ^ expression RSQUARE !) | ( DOT ^ identifier ) )* ;
	public final SQLParser_Expr.precedenceFieldExpression_return precedenceFieldExpression() throws RecognitionException {
		SQLParser_Expr.precedenceFieldExpression_return retval = new SQLParser_Expr.precedenceFieldExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LSQUARE13=null;
		Token RSQUARE15=null;
		Token DOT16=null;
		ParserRuleReturnScope atomExpression12 =null;
		ParserRuleReturnScope expression14 =null;
		ParserRuleReturnScope identifier17 =null;

		CommonTree LSQUARE13_tree=null;
		CommonTree RSQUARE15_tree=null;
		CommonTree DOT16_tree=null;

		try {
			// Expr.g:48:5: ( atomExpression ( ( LSQUARE ^ expression RSQUARE !) | ( DOT ^ identifier ) )* )
			// Expr.g:49:5: atomExpression ( ( LSQUARE ^ expression RSQUARE !) | ( DOT ^ identifier ) )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_atomExpression_in_precedenceFieldExpression167);
			atomExpression12=atomExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, atomExpression12.getTree());

			// Expr.g:49:20: ( ( LSQUARE ^ expression RSQUARE !) | ( DOT ^ identifier ) )*
			loop2:
			while (true) {
				int alt2=3;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==LSQUARE) ) {
					alt2=1;
				}
				else if ( (LA2_0==DOT) ) {
					alt2=2;
				}

				switch (alt2) {
				case 1 :
					// Expr.g:49:21: ( LSQUARE ^ expression RSQUARE !)
					{
					// Expr.g:49:21: ( LSQUARE ^ expression RSQUARE !)
					// Expr.g:49:22: LSQUARE ^ expression RSQUARE !
					{
					LSQUARE13=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_precedenceFieldExpression171); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LSQUARE13_tree = (CommonTree)adaptor.create(LSQUARE13);
					root_0 = (CommonTree)adaptor.becomeRoot(LSQUARE13_tree, root_0);
					}

					pushFollow(FOLLOW_expression_in_precedenceFieldExpression174);
					expression14=expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, expression14.getTree());

					RSQUARE15=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_precedenceFieldExpression176); if (state.failed) return retval;
					}

					}
					break;
				case 2 :
					// Expr.g:49:54: ( DOT ^ identifier )
					{
					// Expr.g:49:54: ( DOT ^ identifier )
					// Expr.g:49:55: DOT ^ identifier
					{
					DOT16=(Token)match(input,DOT,FOLLOW_DOT_in_precedenceFieldExpression183); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DOT16_tree = (CommonTree)adaptor.create(DOT16);
					root_0 = (CommonTree)adaptor.becomeRoot(DOT16_tree, root_0);
					}

					pushFollow(FOLLOW_identifier_in_precedenceFieldExpression186);
					identifier17=identifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier17.getTree());

					}

					}
					break;

				default :
					break loop2;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceFieldExpression"


	public static class precedenceUnaryOperator_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceUnaryOperator"
	// Expr.g:52:1: precedenceUnaryOperator : ( PLUS | MINUS | TILDE );
	public final SQLParser_Expr.precedenceUnaryOperator_return precedenceUnaryOperator() throws RecognitionException {
		SQLParser_Expr.precedenceUnaryOperator_return retval = new SQLParser_Expr.precedenceUnaryOperator_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set18=null;

		CommonTree set18_tree=null;

		try {
			// Expr.g:53:5: ( PLUS | MINUS | TILDE )
			// Expr.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set18=input.LT(1);
			if ( input.LA(1)==MINUS||input.LA(1)==PLUS||input.LA(1)==TILDE ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set18));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceUnaryOperator"


	public static class nullCondition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "nullCondition"
	// Expr.g:57:1: nullCondition : ( KW_NULL -> ^( TOK_ISNULL ) | KW_NOT KW_NULL -> ^( TOK_ISNOTNULL ) );
	public final SQLParser_Expr.nullCondition_return nullCondition() throws RecognitionException {
		SQLParser_Expr.nullCondition_return retval = new SQLParser_Expr.nullCondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_NULL19=null;
		Token KW_NOT20=null;
		Token KW_NULL21=null;

		CommonTree KW_NULL19_tree=null;
		CommonTree KW_NOT20_tree=null;
		CommonTree KW_NULL21_tree=null;
		RewriteRuleTokenStream stream_KW_NULL=new RewriteRuleTokenStream(adaptor,"token KW_NULL");
		RewriteRuleTokenStream stream_KW_NOT=new RewriteRuleTokenStream(adaptor,"token KW_NOT");

		try {
			// Expr.g:58:5: ( KW_NULL -> ^( TOK_ISNULL ) | KW_NOT KW_NULL -> ^( TOK_ISNOTNULL ) )
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==KW_NULL) ) {
				alt3=1;
			}
			else if ( (LA3_0==KW_NOT) ) {
				alt3=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// Expr.g:59:5: KW_NULL
					{
					KW_NULL19=(Token)match(input,KW_NULL,FOLLOW_KW_NULL_in_nullCondition239); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_NULL.add(KW_NULL19);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 59:13: -> ^( TOK_ISNULL )
					{
						// Expr.g:59:16: ^( TOK_ISNULL )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_ISNULL, "TOK_ISNULL"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// Expr.g:60:7: KW_NOT KW_NULL
					{
					KW_NOT20=(Token)match(input,KW_NOT,FOLLOW_KW_NOT_in_nullCondition253); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_NOT.add(KW_NOT20);

					KW_NULL21=(Token)match(input,KW_NULL,FOLLOW_KW_NULL_in_nullCondition255); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_NULL.add(KW_NULL21);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 60:22: -> ^( TOK_ISNOTNULL )
					{
						// Expr.g:60:25: ^( TOK_ISNOTNULL )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_ISNOTNULL, "TOK_ISNOTNULL"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "nullCondition"


	public static class precedenceUnaryPrefixExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceUnaryPrefixExpression"
	// Expr.g:63:1: precedenceUnaryPrefixExpression : ( precedenceUnaryOperator ^)* precedenceFieldExpression ;
	public final SQLParser_Expr.precedenceUnaryPrefixExpression_return precedenceUnaryPrefixExpression() throws RecognitionException {
		SQLParser_Expr.precedenceUnaryPrefixExpression_return retval = new SQLParser_Expr.precedenceUnaryPrefixExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope precedenceUnaryOperator22 =null;
		ParserRuleReturnScope precedenceFieldExpression23 =null;


		try {
			// Expr.g:64:5: ( ( precedenceUnaryOperator ^)* precedenceFieldExpression )
			// Expr.g:65:5: ( precedenceUnaryOperator ^)* precedenceFieldExpression
			{
			root_0 = (CommonTree)adaptor.nil();


			// Expr.g:65:5: ( precedenceUnaryOperator ^)*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==MINUS||LA4_0==PLUS||LA4_0==TILDE) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// Expr.g:65:6: precedenceUnaryOperator ^
					{
					pushFollow(FOLLOW_precedenceUnaryOperator_in_precedenceUnaryPrefixExpression283);
					precedenceUnaryOperator22=precedenceUnaryOperator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(precedenceUnaryOperator22.getTree(), root_0);
					}
					break;

				default :
					break loop4;
				}
			}

			pushFollow(FOLLOW_precedenceFieldExpression_in_precedenceUnaryPrefixExpression288);
			precedenceFieldExpression23=precedenceFieldExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceFieldExpression23.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceUnaryPrefixExpression"


	public static class precedenceUnarySuffixExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceUnarySuffixExpression"
	// Expr.g:68:1: precedenceUnarySuffixExpression : precedenceUnaryPrefixExpression (a= KW_IS nullCondition )? -> {$a != null}? ^( TOK_FUNCTION nullCondition precedenceUnaryPrefixExpression ) -> precedenceUnaryPrefixExpression ;
	public final SQLParser_Expr.precedenceUnarySuffixExpression_return precedenceUnarySuffixExpression() throws RecognitionException {
		SQLParser_Expr.precedenceUnarySuffixExpression_return retval = new SQLParser_Expr.precedenceUnarySuffixExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token a=null;
		ParserRuleReturnScope precedenceUnaryPrefixExpression24 =null;
		ParserRuleReturnScope nullCondition25 =null;

		CommonTree a_tree=null;
		RewriteRuleTokenStream stream_KW_IS=new RewriteRuleTokenStream(adaptor,"token KW_IS");
		RewriteRuleSubtreeStream stream_precedenceUnaryPrefixExpression=new RewriteRuleSubtreeStream(adaptor,"rule precedenceUnaryPrefixExpression");
		RewriteRuleSubtreeStream stream_nullCondition=new RewriteRuleSubtreeStream(adaptor,"rule nullCondition");

		try {
			// Expr.g:69:5: ( precedenceUnaryPrefixExpression (a= KW_IS nullCondition )? -> {$a != null}? ^( TOK_FUNCTION nullCondition precedenceUnaryPrefixExpression ) -> precedenceUnaryPrefixExpression )
			// Expr.g:69:7: precedenceUnaryPrefixExpression (a= KW_IS nullCondition )?
			{
			pushFollow(FOLLOW_precedenceUnaryPrefixExpression_in_precedenceUnarySuffixExpression305);
			precedenceUnaryPrefixExpression24=precedenceUnaryPrefixExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_precedenceUnaryPrefixExpression.add(precedenceUnaryPrefixExpression24.getTree());
			// Expr.g:69:39: (a= KW_IS nullCondition )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==KW_IS) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// Expr.g:69:40: a= KW_IS nullCondition
					{
					a=(Token)match(input,KW_IS,FOLLOW_KW_IS_in_precedenceUnarySuffixExpression310); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_IS.add(a);

					pushFollow(FOLLOW_nullCondition_in_precedenceUnarySuffixExpression312);
					nullCondition25=nullCondition();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_nullCondition.add(nullCondition25.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: precedenceUnaryPrefixExpression, precedenceUnaryPrefixExpression, nullCondition
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 70:5: -> {$a != null}? ^( TOK_FUNCTION nullCondition precedenceUnaryPrefixExpression )
			if (a != null) {
				// Expr.g:70:22: ^( TOK_FUNCTION nullCondition precedenceUnaryPrefixExpression )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_FUNCTION, "TOK_FUNCTION"), root_1);
				adaptor.addChild(root_1, stream_nullCondition.nextTree());
				adaptor.addChild(root_1, stream_precedenceUnaryPrefixExpression.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}

			else // 71:5: -> precedenceUnaryPrefixExpression
			{
				adaptor.addChild(root_0, stream_precedenceUnaryPrefixExpression.nextTree());
			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceUnarySuffixExpression"


	public static class precedenceBitwiseXorOperator_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceBitwiseXorOperator"
	// Expr.g:75:1: precedenceBitwiseXorOperator : BITWISEXOR ;
	public final SQLParser_Expr.precedenceBitwiseXorOperator_return precedenceBitwiseXorOperator() throws RecognitionException {
		SQLParser_Expr.precedenceBitwiseXorOperator_return retval = new SQLParser_Expr.precedenceBitwiseXorOperator_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token BITWISEXOR26=null;

		CommonTree BITWISEXOR26_tree=null;

		try {
			// Expr.g:76:5: ( BITWISEXOR )
			// Expr.g:77:5: BITWISEXOR
			{
			root_0 = (CommonTree)adaptor.nil();


			BITWISEXOR26=(Token)match(input,BITWISEXOR,FOLLOW_BITWISEXOR_in_precedenceBitwiseXorOperator360); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			BITWISEXOR26_tree = (CommonTree)adaptor.create(BITWISEXOR26);
			adaptor.addChild(root_0, BITWISEXOR26_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceBitwiseXorOperator"


	public static class precedenceBitwiseXorExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceBitwiseXorExpression"
	// Expr.g:80:1: precedenceBitwiseXorExpression : precedenceUnarySuffixExpression ( precedenceBitwiseXorOperator ^ precedenceUnarySuffixExpression )* ;
	public final SQLParser_Expr.precedenceBitwiseXorExpression_return precedenceBitwiseXorExpression() throws RecognitionException {
		SQLParser_Expr.precedenceBitwiseXorExpression_return retval = new SQLParser_Expr.precedenceBitwiseXorExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope precedenceUnarySuffixExpression27 =null;
		ParserRuleReturnScope precedenceBitwiseXorOperator28 =null;
		ParserRuleReturnScope precedenceUnarySuffixExpression29 =null;


		try {
			// Expr.g:81:5: ( precedenceUnarySuffixExpression ( precedenceBitwiseXorOperator ^ precedenceUnarySuffixExpression )* )
			// Expr.g:82:5: precedenceUnarySuffixExpression ( precedenceBitwiseXorOperator ^ precedenceUnarySuffixExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_precedenceUnarySuffixExpression_in_precedenceBitwiseXorExpression381);
			precedenceUnarySuffixExpression27=precedenceUnarySuffixExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceUnarySuffixExpression27.getTree());

			// Expr.g:82:37: ( precedenceBitwiseXorOperator ^ precedenceUnarySuffixExpression )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==BITWISEXOR) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// Expr.g:82:38: precedenceBitwiseXorOperator ^ precedenceUnarySuffixExpression
					{
					pushFollow(FOLLOW_precedenceBitwiseXorOperator_in_precedenceBitwiseXorExpression384);
					precedenceBitwiseXorOperator28=precedenceBitwiseXorOperator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(precedenceBitwiseXorOperator28.getTree(), root_0);
					pushFollow(FOLLOW_precedenceUnarySuffixExpression_in_precedenceBitwiseXorExpression387);
					precedenceUnarySuffixExpression29=precedenceUnarySuffixExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceUnarySuffixExpression29.getTree());

					}
					break;

				default :
					break loop6;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceBitwiseXorExpression"


	public static class precedenceStarOperator_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceStarOperator"
	// Expr.g:86:1: precedenceStarOperator : ( STAR | DIVIDE | MOD | DIV );
	public final SQLParser_Expr.precedenceStarOperator_return precedenceStarOperator() throws RecognitionException {
		SQLParser_Expr.precedenceStarOperator_return retval = new SQLParser_Expr.precedenceStarOperator_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set30=null;

		CommonTree set30_tree=null;

		try {
			// Expr.g:87:5: ( STAR | DIVIDE | MOD | DIV )
			// Expr.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set30=input.LT(1);
			if ( input.LA(1)==DIVIDE||input.LA(1)==MOD||input.LA(1)==STAR||input.LA(1)==DIV ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set30));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceStarOperator"


	public static class precedenceStarExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceStarExpression"
	// Expr.g:91:1: precedenceStarExpression : precedenceBitwiseXorExpression ( precedenceStarOperator ^ precedenceBitwiseXorExpression )* ;
	public final SQLParser_Expr.precedenceStarExpression_return precedenceStarExpression() throws RecognitionException {
		SQLParser_Expr.precedenceStarExpression_return retval = new SQLParser_Expr.precedenceStarExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope precedenceBitwiseXorExpression31 =null;
		ParserRuleReturnScope precedenceStarOperator32 =null;
		ParserRuleReturnScope precedenceBitwiseXorExpression33 =null;


		try {
			// Expr.g:92:5: ( precedenceBitwiseXorExpression ( precedenceStarOperator ^ precedenceBitwiseXorExpression )* )
			// Expr.g:93:5: precedenceBitwiseXorExpression ( precedenceStarOperator ^ precedenceBitwiseXorExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_precedenceBitwiseXorExpression_in_precedenceStarExpression444);
			precedenceBitwiseXorExpression31=precedenceBitwiseXorExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceBitwiseXorExpression31.getTree());

			// Expr.g:93:36: ( precedenceStarOperator ^ precedenceBitwiseXorExpression )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==DIVIDE||LA7_0==MOD||LA7_0==STAR||LA7_0==DIV) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// Expr.g:93:37: precedenceStarOperator ^ precedenceBitwiseXorExpression
					{
					pushFollow(FOLLOW_precedenceStarOperator_in_precedenceStarExpression447);
					precedenceStarOperator32=precedenceStarOperator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(precedenceStarOperator32.getTree(), root_0);
					pushFollow(FOLLOW_precedenceBitwiseXorExpression_in_precedenceStarExpression450);
					precedenceBitwiseXorExpression33=precedenceBitwiseXorExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceBitwiseXorExpression33.getTree());

					}
					break;

				default :
					break loop7;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceStarExpression"


	public static class precedencePlusOperator_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedencePlusOperator"
	// Expr.g:97:1: precedencePlusOperator : ( PLUS | MINUS );
	public final SQLParser_Expr.precedencePlusOperator_return precedencePlusOperator() throws RecognitionException {
		SQLParser_Expr.precedencePlusOperator_return retval = new SQLParser_Expr.precedencePlusOperator_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set34=null;

		CommonTree set34_tree=null;

		try {
			// Expr.g:98:5: ( PLUS | MINUS )
			// Expr.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set34=input.LT(1);
			if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set34));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedencePlusOperator"


	public static class precedencePlusExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedencePlusExpression"
	// Expr.g:102:1: precedencePlusExpression : precedenceStarExpression ( precedencePlusOperator ^ precedenceStarExpression )* ;
	public final SQLParser_Expr.precedencePlusExpression_return precedencePlusExpression() throws RecognitionException {
		SQLParser_Expr.precedencePlusExpression_return retval = new SQLParser_Expr.precedencePlusExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope precedenceStarExpression35 =null;
		ParserRuleReturnScope precedencePlusOperator36 =null;
		ParserRuleReturnScope precedenceStarExpression37 =null;


		try {
			// Expr.g:103:5: ( precedenceStarExpression ( precedencePlusOperator ^ precedenceStarExpression )* )
			// Expr.g:104:5: precedenceStarExpression ( precedencePlusOperator ^ precedenceStarExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_precedenceStarExpression_in_precedencePlusExpression499);
			precedenceStarExpression35=precedenceStarExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceStarExpression35.getTree());

			// Expr.g:104:30: ( precedencePlusOperator ^ precedenceStarExpression )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==MINUS||LA8_0==PLUS) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// Expr.g:104:31: precedencePlusOperator ^ precedenceStarExpression
					{
					pushFollow(FOLLOW_precedencePlusOperator_in_precedencePlusExpression502);
					precedencePlusOperator36=precedencePlusOperator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(precedencePlusOperator36.getTree(), root_0);
					pushFollow(FOLLOW_precedenceStarExpression_in_precedencePlusExpression505);
					precedenceStarExpression37=precedenceStarExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceStarExpression37.getTree());

					}
					break;

				default :
					break loop8;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedencePlusExpression"


	public static class precedenceAmpersandOperator_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceAmpersandOperator"
	// Expr.g:108:1: precedenceAmpersandOperator : AMPERSAND ;
	public final SQLParser_Expr.precedenceAmpersandOperator_return precedenceAmpersandOperator() throws RecognitionException {
		SQLParser_Expr.precedenceAmpersandOperator_return retval = new SQLParser_Expr.precedenceAmpersandOperator_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token AMPERSAND38=null;

		CommonTree AMPERSAND38_tree=null;

		try {
			// Expr.g:109:5: ( AMPERSAND )
			// Expr.g:110:5: AMPERSAND
			{
			root_0 = (CommonTree)adaptor.nil();


			AMPERSAND38=(Token)match(input,AMPERSAND,FOLLOW_AMPERSAND_in_precedenceAmpersandOperator529); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			AMPERSAND38_tree = (CommonTree)adaptor.create(AMPERSAND38);
			adaptor.addChild(root_0, AMPERSAND38_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceAmpersandOperator"


	public static class precedenceAmpersandExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceAmpersandExpression"
	// Expr.g:113:1: precedenceAmpersandExpression : precedencePlusExpression ( precedenceAmpersandOperator ^ precedencePlusExpression )* ;
	public final SQLParser_Expr.precedenceAmpersandExpression_return precedenceAmpersandExpression() throws RecognitionException {
		SQLParser_Expr.precedenceAmpersandExpression_return retval = new SQLParser_Expr.precedenceAmpersandExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope precedencePlusExpression39 =null;
		ParserRuleReturnScope precedenceAmpersandOperator40 =null;
		ParserRuleReturnScope precedencePlusExpression41 =null;


		try {
			// Expr.g:114:5: ( precedencePlusExpression ( precedenceAmpersandOperator ^ precedencePlusExpression )* )
			// Expr.g:115:5: precedencePlusExpression ( precedenceAmpersandOperator ^ precedencePlusExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_precedencePlusExpression_in_precedenceAmpersandExpression550);
			precedencePlusExpression39=precedencePlusExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, precedencePlusExpression39.getTree());

			// Expr.g:115:30: ( precedenceAmpersandOperator ^ precedencePlusExpression )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0==AMPERSAND) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// Expr.g:115:31: precedenceAmpersandOperator ^ precedencePlusExpression
					{
					pushFollow(FOLLOW_precedenceAmpersandOperator_in_precedenceAmpersandExpression553);
					precedenceAmpersandOperator40=precedenceAmpersandOperator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(precedenceAmpersandOperator40.getTree(), root_0);
					pushFollow(FOLLOW_precedencePlusExpression_in_precedenceAmpersandExpression556);
					precedencePlusExpression41=precedencePlusExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, precedencePlusExpression41.getTree());

					}
					break;

				default :
					break loop9;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceAmpersandExpression"


	public static class precedenceBitwiseOrOperator_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceBitwiseOrOperator"
	// Expr.g:119:1: precedenceBitwiseOrOperator : BITWISEOR ;
	public final SQLParser_Expr.precedenceBitwiseOrOperator_return precedenceBitwiseOrOperator() throws RecognitionException {
		SQLParser_Expr.precedenceBitwiseOrOperator_return retval = new SQLParser_Expr.precedenceBitwiseOrOperator_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token BITWISEOR42=null;

		CommonTree BITWISEOR42_tree=null;

		try {
			// Expr.g:120:5: ( BITWISEOR )
			// Expr.g:121:5: BITWISEOR
			{
			root_0 = (CommonTree)adaptor.nil();


			BITWISEOR42=(Token)match(input,BITWISEOR,FOLLOW_BITWISEOR_in_precedenceBitwiseOrOperator580); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			BITWISEOR42_tree = (CommonTree)adaptor.create(BITWISEOR42);
			adaptor.addChild(root_0, BITWISEOR42_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceBitwiseOrOperator"


	public static class precedenceBitwiseOrExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceBitwiseOrExpression"
	// Expr.g:124:1: precedenceBitwiseOrExpression : precedenceAmpersandExpression ( precedenceBitwiseOrOperator ^ precedenceAmpersandExpression )* ;
	public final SQLParser_Expr.precedenceBitwiseOrExpression_return precedenceBitwiseOrExpression() throws RecognitionException {
		SQLParser_Expr.precedenceBitwiseOrExpression_return retval = new SQLParser_Expr.precedenceBitwiseOrExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope precedenceAmpersandExpression43 =null;
		ParserRuleReturnScope precedenceBitwiseOrOperator44 =null;
		ParserRuleReturnScope precedenceAmpersandExpression45 =null;


		try {
			// Expr.g:125:5: ( precedenceAmpersandExpression ( precedenceBitwiseOrOperator ^ precedenceAmpersandExpression )* )
			// Expr.g:126:5: precedenceAmpersandExpression ( precedenceBitwiseOrOperator ^ precedenceAmpersandExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_precedenceAmpersandExpression_in_precedenceBitwiseOrExpression601);
			precedenceAmpersandExpression43=precedenceAmpersandExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceAmpersandExpression43.getTree());

			// Expr.g:126:35: ( precedenceBitwiseOrOperator ^ precedenceAmpersandExpression )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( (LA10_0==BITWISEOR) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// Expr.g:126:36: precedenceBitwiseOrOperator ^ precedenceAmpersandExpression
					{
					pushFollow(FOLLOW_precedenceBitwiseOrOperator_in_precedenceBitwiseOrExpression604);
					precedenceBitwiseOrOperator44=precedenceBitwiseOrOperator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(precedenceBitwiseOrOperator44.getTree(), root_0);
					pushFollow(FOLLOW_precedenceAmpersandExpression_in_precedenceBitwiseOrExpression607);
					precedenceAmpersandExpression45=precedenceAmpersandExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceAmpersandExpression45.getTree());

					}
					break;

				default :
					break loop10;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceBitwiseOrExpression"


	public static class precedenceEqualNegatableOperator_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceEqualNegatableOperator"
	// Expr.g:130:1: precedenceEqualNegatableOperator : ( KW_LIKE | KW_RLIKE | KW_REGEXP );
	public final SQLParser_Expr.precedenceEqualNegatableOperator_return precedenceEqualNegatableOperator() throws RecognitionException {
		SQLParser_Expr.precedenceEqualNegatableOperator_return retval = new SQLParser_Expr.precedenceEqualNegatableOperator_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set46=null;

		CommonTree set46_tree=null;

		try {
			// Expr.g:131:5: ( KW_LIKE | KW_RLIKE | KW_REGEXP )
			// Expr.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set46=input.LT(1);
			if ( input.LA(1)==KW_LIKE||(input.LA(1) >= KW_REGEXP && input.LA(1) <= KW_RLIKE) ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set46));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceEqualNegatableOperator"


	public static class precedenceEqualOperator_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceEqualOperator"
	// Expr.g:135:1: precedenceEqualOperator : ( precedenceEqualNegatableOperator | EQUAL | EQUAL_NS | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN );
	public final SQLParser_Expr.precedenceEqualOperator_return precedenceEqualOperator() throws RecognitionException {
		SQLParser_Expr.precedenceEqualOperator_return retval = new SQLParser_Expr.precedenceEqualOperator_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token EQUAL48=null;
		Token EQUAL_NS49=null;
		Token NOTEQUAL50=null;
		Token LESSTHANOREQUALTO51=null;
		Token LESSTHAN52=null;
		Token GREATERTHANOREQUALTO53=null;
		Token GREATERTHAN54=null;
		ParserRuleReturnScope precedenceEqualNegatableOperator47 =null;

		CommonTree EQUAL48_tree=null;
		CommonTree EQUAL_NS49_tree=null;
		CommonTree NOTEQUAL50_tree=null;
		CommonTree LESSTHANOREQUALTO51_tree=null;
		CommonTree LESSTHAN52_tree=null;
		CommonTree GREATERTHANOREQUALTO53_tree=null;
		CommonTree GREATERTHAN54_tree=null;

		try {
			// Expr.g:136:5: ( precedenceEqualNegatableOperator | EQUAL | EQUAL_NS | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN )
			int alt11=8;
			switch ( input.LA(1) ) {
			case KW_LIKE:
			case KW_REGEXP:
			case KW_RLIKE:
				{
				alt11=1;
				}
				break;
			case EQUAL:
				{
				alt11=2;
				}
				break;
			case EQUAL_NS:
				{
				alt11=3;
				}
				break;
			case NOTEQUAL:
				{
				alt11=4;
				}
				break;
			case LESSTHANOREQUALTO:
				{
				alt11=5;
				}
				break;
			case LESSTHAN:
				{
				alt11=6;
				}
				break;
			case GREATERTHANOREQUALTO:
				{
				alt11=7;
				}
				break;
			case GREATERTHAN:
				{
				alt11=8;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// Expr.g:137:5: precedenceEqualNegatableOperator
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_precedenceEqualNegatableOperator_in_precedenceEqualOperator660);
					precedenceEqualNegatableOperator47=precedenceEqualNegatableOperator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceEqualNegatableOperator47.getTree());

					}
					break;
				case 2 :
					// Expr.g:137:40: EQUAL
					{
					root_0 = (CommonTree)adaptor.nil();


					EQUAL48=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_precedenceEqualOperator664); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EQUAL48_tree = (CommonTree)adaptor.create(EQUAL48);
					adaptor.addChild(root_0, EQUAL48_tree);
					}

					}
					break;
				case 3 :
					// Expr.g:137:48: EQUAL_NS
					{
					root_0 = (CommonTree)adaptor.nil();


					EQUAL_NS49=(Token)match(input,EQUAL_NS,FOLLOW_EQUAL_NS_in_precedenceEqualOperator668); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EQUAL_NS49_tree = (CommonTree)adaptor.create(EQUAL_NS49);
					adaptor.addChild(root_0, EQUAL_NS49_tree);
					}

					}
					break;
				case 4 :
					// Expr.g:137:59: NOTEQUAL
					{
					root_0 = (CommonTree)adaptor.nil();


					NOTEQUAL50=(Token)match(input,NOTEQUAL,FOLLOW_NOTEQUAL_in_precedenceEqualOperator672); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NOTEQUAL50_tree = (CommonTree)adaptor.create(NOTEQUAL50);
					adaptor.addChild(root_0, NOTEQUAL50_tree);
					}

					}
					break;
				case 5 :
					// Expr.g:137:70: LESSTHANOREQUALTO
					{
					root_0 = (CommonTree)adaptor.nil();


					LESSTHANOREQUALTO51=(Token)match(input,LESSTHANOREQUALTO,FOLLOW_LESSTHANOREQUALTO_in_precedenceEqualOperator676); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LESSTHANOREQUALTO51_tree = (CommonTree)adaptor.create(LESSTHANOREQUALTO51);
					adaptor.addChild(root_0, LESSTHANOREQUALTO51_tree);
					}

					}
					break;
				case 6 :
					// Expr.g:137:90: LESSTHAN
					{
					root_0 = (CommonTree)adaptor.nil();


					LESSTHAN52=(Token)match(input,LESSTHAN,FOLLOW_LESSTHAN_in_precedenceEqualOperator680); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LESSTHAN52_tree = (CommonTree)adaptor.create(LESSTHAN52);
					adaptor.addChild(root_0, LESSTHAN52_tree);
					}

					}
					break;
				case 7 :
					// Expr.g:137:101: GREATERTHANOREQUALTO
					{
					root_0 = (CommonTree)adaptor.nil();


					GREATERTHANOREQUALTO53=(Token)match(input,GREATERTHANOREQUALTO,FOLLOW_GREATERTHANOREQUALTO_in_precedenceEqualOperator684); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					GREATERTHANOREQUALTO53_tree = (CommonTree)adaptor.create(GREATERTHANOREQUALTO53);
					adaptor.addChild(root_0, GREATERTHANOREQUALTO53_tree);
					}

					}
					break;
				case 8 :
					// Expr.g:137:124: GREATERTHAN
					{
					root_0 = (CommonTree)adaptor.nil();


					GREATERTHAN54=(Token)match(input,GREATERTHAN,FOLLOW_GREATERTHAN_in_precedenceEqualOperator688); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					GREATERTHAN54_tree = (CommonTree)adaptor.create(GREATERTHAN54);
					adaptor.addChild(root_0, GREATERTHAN54_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceEqualOperator"


	public static class precedenceEqualExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceEqualExpression"
	// Expr.g:140:1: precedenceEqualExpression : (left= precedenceBitwiseOrExpression -> $left) ( ( KW_NOT precedenceEqualNegatableOperator notExpr= precedenceBitwiseOrExpression ) -> ^( KW_NOT ^( precedenceEqualNegatableOperator $precedenceEqualExpression $notExpr) ) | ( precedenceEqualOperator equalExpr= precedenceBitwiseOrExpression ) -> ^( precedenceEqualOperator $precedenceEqualExpression $equalExpr) | ( KW_NOT KW_IN expressions ) -> ^( TOK_FUNCTION KW_NOT ^( TOK_FUNCTION KW_IN $precedenceEqualExpression expressions ) ) | ( KW_IN expressions ) -> ^( TOK_FUNCTION KW_IN $precedenceEqualExpression expressions ) | ( KW_NOT KW_BETWEEN (min= precedenceBitwiseOrExpression ) KW_AND (max= precedenceBitwiseOrExpression ) ) -> ^( TOK_FUNCTION KW_NOT ^( TOK_FUNCTION KW_BETWEEN $left $min $max) ) | ( KW_BETWEEN (min= precedenceBitwiseOrExpression ) KW_AND (max= precedenceBitwiseOrExpression ) ) -> ^( TOK_FUNCTION KW_BETWEEN $left $min $max) )* ;
	public final SQLParser_Expr.precedenceEqualExpression_return precedenceEqualExpression() throws RecognitionException {
		SQLParser_Expr.precedenceEqualExpression_return retval = new SQLParser_Expr.precedenceEqualExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_NOT55=null;
		Token KW_NOT58=null;
		Token KW_IN59=null;
		Token KW_IN61=null;
		Token KW_NOT63=null;
		Token KW_BETWEEN64=null;
		Token KW_AND65=null;
		Token KW_BETWEEN66=null;
		Token KW_AND67=null;
		ParserRuleReturnScope left =null;
		ParserRuleReturnScope notExpr =null;
		ParserRuleReturnScope equalExpr =null;
		ParserRuleReturnScope min =null;
		ParserRuleReturnScope max =null;
		ParserRuleReturnScope precedenceEqualNegatableOperator56 =null;
		ParserRuleReturnScope precedenceEqualOperator57 =null;
		ParserRuleReturnScope expressions60 =null;
		ParserRuleReturnScope expressions62 =null;

		CommonTree KW_NOT55_tree=null;
		CommonTree KW_NOT58_tree=null;
		CommonTree KW_IN59_tree=null;
		CommonTree KW_IN61_tree=null;
		CommonTree KW_NOT63_tree=null;
		CommonTree KW_BETWEEN64_tree=null;
		CommonTree KW_AND65_tree=null;
		CommonTree KW_BETWEEN66_tree=null;
		CommonTree KW_AND67_tree=null;
		RewriteRuleTokenStream stream_KW_IN=new RewriteRuleTokenStream(adaptor,"token KW_IN");
		RewriteRuleTokenStream stream_KW_BETWEEN=new RewriteRuleTokenStream(adaptor,"token KW_BETWEEN");
		RewriteRuleTokenStream stream_KW_AND=new RewriteRuleTokenStream(adaptor,"token KW_AND");
		RewriteRuleTokenStream stream_KW_NOT=new RewriteRuleTokenStream(adaptor,"token KW_NOT");
		RewriteRuleSubtreeStream stream_precedenceEqualNegatableOperator=new RewriteRuleSubtreeStream(adaptor,"rule precedenceEqualNegatableOperator");
		RewriteRuleSubtreeStream stream_precedenceEqualOperator=new RewriteRuleSubtreeStream(adaptor,"rule precedenceEqualOperator");
		RewriteRuleSubtreeStream stream_precedenceBitwiseOrExpression=new RewriteRuleSubtreeStream(adaptor,"rule precedenceBitwiseOrExpression");
		RewriteRuleSubtreeStream stream_expressions=new RewriteRuleSubtreeStream(adaptor,"rule expressions");

		try {
			// Expr.g:141:5: ( (left= precedenceBitwiseOrExpression -> $left) ( ( KW_NOT precedenceEqualNegatableOperator notExpr= precedenceBitwiseOrExpression ) -> ^( KW_NOT ^( precedenceEqualNegatableOperator $precedenceEqualExpression $notExpr) ) | ( precedenceEqualOperator equalExpr= precedenceBitwiseOrExpression ) -> ^( precedenceEqualOperator $precedenceEqualExpression $equalExpr) | ( KW_NOT KW_IN expressions ) -> ^( TOK_FUNCTION KW_NOT ^( TOK_FUNCTION KW_IN $precedenceEqualExpression expressions ) ) | ( KW_IN expressions ) -> ^( TOK_FUNCTION KW_IN $precedenceEqualExpression expressions ) | ( KW_NOT KW_BETWEEN (min= precedenceBitwiseOrExpression ) KW_AND (max= precedenceBitwiseOrExpression ) ) -> ^( TOK_FUNCTION KW_NOT ^( TOK_FUNCTION KW_BETWEEN $left $min $max) ) | ( KW_BETWEEN (min= precedenceBitwiseOrExpression ) KW_AND (max= precedenceBitwiseOrExpression ) ) -> ^( TOK_FUNCTION KW_BETWEEN $left $min $max) )* )
			// Expr.g:142:5: (left= precedenceBitwiseOrExpression -> $left) ( ( KW_NOT precedenceEqualNegatableOperator notExpr= precedenceBitwiseOrExpression ) -> ^( KW_NOT ^( precedenceEqualNegatableOperator $precedenceEqualExpression $notExpr) ) | ( precedenceEqualOperator equalExpr= precedenceBitwiseOrExpression ) -> ^( precedenceEqualOperator $precedenceEqualExpression $equalExpr) | ( KW_NOT KW_IN expressions ) -> ^( TOK_FUNCTION KW_NOT ^( TOK_FUNCTION KW_IN $precedenceEqualExpression expressions ) ) | ( KW_IN expressions ) -> ^( TOK_FUNCTION KW_IN $precedenceEqualExpression expressions ) | ( KW_NOT KW_BETWEEN (min= precedenceBitwiseOrExpression ) KW_AND (max= precedenceBitwiseOrExpression ) ) -> ^( TOK_FUNCTION KW_NOT ^( TOK_FUNCTION KW_BETWEEN $left $min $max) ) | ( KW_BETWEEN (min= precedenceBitwiseOrExpression ) KW_AND (max= precedenceBitwiseOrExpression ) ) -> ^( TOK_FUNCTION KW_BETWEEN $left $min $max) )*
			{
			// Expr.g:142:5: (left= precedenceBitwiseOrExpression -> $left)
			// Expr.g:142:6: left= precedenceBitwiseOrExpression
			{
			pushFollow(FOLLOW_precedenceBitwiseOrExpression_in_precedenceEqualExpression712);
			left=precedenceBitwiseOrExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_precedenceBitwiseOrExpression.add(left.getTree());
			// AST REWRITE
			// elements: left
			// token labels: 
			// rule labels: retval, left
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 142:41: -> $left
			{
				adaptor.addChild(root_0, stream_left.nextTree());
			}


			retval.tree = root_0;
			}

			}

			// Expr.g:143:5: ( ( KW_NOT precedenceEqualNegatableOperator notExpr= precedenceBitwiseOrExpression ) -> ^( KW_NOT ^( precedenceEqualNegatableOperator $precedenceEqualExpression $notExpr) ) | ( precedenceEqualOperator equalExpr= precedenceBitwiseOrExpression ) -> ^( precedenceEqualOperator $precedenceEqualExpression $equalExpr) | ( KW_NOT KW_IN expressions ) -> ^( TOK_FUNCTION KW_NOT ^( TOK_FUNCTION KW_IN $precedenceEqualExpression expressions ) ) | ( KW_IN expressions ) -> ^( TOK_FUNCTION KW_IN $precedenceEqualExpression expressions ) | ( KW_NOT KW_BETWEEN (min= precedenceBitwiseOrExpression ) KW_AND (max= precedenceBitwiseOrExpression ) ) -> ^( TOK_FUNCTION KW_NOT ^( TOK_FUNCTION KW_BETWEEN $left $min $max) ) | ( KW_BETWEEN (min= precedenceBitwiseOrExpression ) KW_AND (max= precedenceBitwiseOrExpression ) ) -> ^( TOK_FUNCTION KW_BETWEEN $left $min $max) )*
			loop12:
			while (true) {
				int alt12=7;
				switch ( input.LA(1) ) {
				case KW_NOT:
					{
					switch ( input.LA(2) ) {
					case KW_IN:
						{
						alt12=3;
						}
						break;
					case KW_BETWEEN:
						{
						alt12=5;
						}
						break;
					case KW_LIKE:
					case KW_REGEXP:
					case KW_RLIKE:
						{
						alt12=1;
						}
						break;
					}
					}
					break;
				case EQUAL:
				case EQUAL_NS:
				case GREATERTHAN:
				case GREATERTHANOREQUALTO:
				case KW_LIKE:
				case LESSTHAN:
				case LESSTHANOREQUALTO:
				case NOTEQUAL:
				case KW_REGEXP:
				case KW_RLIKE:
					{
					alt12=2;
					}
					break;
				case KW_IN:
					{
					alt12=4;
					}
					break;
				case KW_BETWEEN:
					{
					alt12=6;
					}
					break;
				}
				switch (alt12) {
				case 1 :
					// Expr.g:144:8: ( KW_NOT precedenceEqualNegatableOperator notExpr= precedenceBitwiseOrExpression )
					{
					// Expr.g:144:8: ( KW_NOT precedenceEqualNegatableOperator notExpr= precedenceBitwiseOrExpression )
					// Expr.g:144:9: KW_NOT precedenceEqualNegatableOperator notExpr= precedenceBitwiseOrExpression
					{
					KW_NOT55=(Token)match(input,KW_NOT,FOLLOW_KW_NOT_in_precedenceEqualExpression734); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_NOT.add(KW_NOT55);

					pushFollow(FOLLOW_precedenceEqualNegatableOperator_in_precedenceEqualExpression736);
					precedenceEqualNegatableOperator56=precedenceEqualNegatableOperator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_precedenceEqualNegatableOperator.add(precedenceEqualNegatableOperator56.getTree());
					pushFollow(FOLLOW_precedenceBitwiseOrExpression_in_precedenceEqualExpression740);
					notExpr=precedenceBitwiseOrExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_precedenceBitwiseOrExpression.add(notExpr.getTree());
					}

					// AST REWRITE
					// elements: precedenceEqualNegatableOperator, KW_NOT, notExpr, precedenceEqualExpression
					// token labels: 
					// rule labels: retval, notExpr
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_notExpr=new RewriteRuleSubtreeStream(adaptor,"rule notExpr",notExpr!=null?notExpr.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 145:8: -> ^( KW_NOT ^( precedenceEqualNegatableOperator $precedenceEqualExpression $notExpr) )
					{
						// Expr.g:145:11: ^( KW_NOT ^( precedenceEqualNegatableOperator $precedenceEqualExpression $notExpr) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_KW_NOT.nextNode(), root_1);
						// Expr.g:145:20: ^( precedenceEqualNegatableOperator $precedenceEqualExpression $notExpr)
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot(stream_precedenceEqualNegatableOperator.nextNode(), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_notExpr.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// Expr.g:146:7: ( precedenceEqualOperator equalExpr= precedenceBitwiseOrExpression )
					{
					// Expr.g:146:7: ( precedenceEqualOperator equalExpr= precedenceBitwiseOrExpression )
					// Expr.g:146:8: precedenceEqualOperator equalExpr= precedenceBitwiseOrExpression
					{
					pushFollow(FOLLOW_precedenceEqualOperator_in_precedenceEqualExpression773);
					precedenceEqualOperator57=precedenceEqualOperator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_precedenceEqualOperator.add(precedenceEqualOperator57.getTree());
					pushFollow(FOLLOW_precedenceBitwiseOrExpression_in_precedenceEqualExpression777);
					equalExpr=precedenceBitwiseOrExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_precedenceBitwiseOrExpression.add(equalExpr.getTree());
					}

					// AST REWRITE
					// elements: precedenceEqualExpression, precedenceEqualOperator, equalExpr
					// token labels: 
					// rule labels: equalExpr, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_equalExpr=new RewriteRuleSubtreeStream(adaptor,"rule equalExpr",equalExpr!=null?equalExpr.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 147:8: -> ^( precedenceEqualOperator $precedenceEqualExpression $equalExpr)
					{
						// Expr.g:147:11: ^( precedenceEqualOperator $precedenceEqualExpression $equalExpr)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_precedenceEqualOperator.nextNode(), root_1);
						adaptor.addChild(root_1, stream_retval.nextTree());
						adaptor.addChild(root_1, stream_equalExpr.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// Expr.g:148:7: ( KW_NOT KW_IN expressions )
					{
					// Expr.g:148:7: ( KW_NOT KW_IN expressions )
					// Expr.g:148:8: KW_NOT KW_IN expressions
					{
					KW_NOT58=(Token)match(input,KW_NOT,FOLLOW_KW_NOT_in_precedenceEqualExpression806); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_NOT.add(KW_NOT58);

					KW_IN59=(Token)match(input,KW_IN,FOLLOW_KW_IN_in_precedenceEqualExpression808); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_IN.add(KW_IN59);

					pushFollow(FOLLOW_expressions_in_precedenceEqualExpression810);
					expressions60=expressions();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expressions.add(expressions60.getTree());
					}

					// AST REWRITE
					// elements: precedenceEqualExpression, KW_NOT, expressions, KW_IN
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 149:8: -> ^( TOK_FUNCTION KW_NOT ^( TOK_FUNCTION KW_IN $precedenceEqualExpression expressions ) )
					{
						// Expr.g:149:11: ^( TOK_FUNCTION KW_NOT ^( TOK_FUNCTION KW_IN $precedenceEqualExpression expressions ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_FUNCTION, "TOK_FUNCTION"), root_1);
						adaptor.addChild(root_1, stream_KW_NOT.nextNode());
						// Expr.g:149:33: ^( TOK_FUNCTION KW_IN $precedenceEqualExpression expressions )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_FUNCTION, "TOK_FUNCTION"), root_2);
						adaptor.addChild(root_2, stream_KW_IN.nextNode());
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_expressions.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 4 :
					// Expr.g:150:7: ( KW_IN expressions )
					{
					// Expr.g:150:7: ( KW_IN expressions )
					// Expr.g:150:8: KW_IN expressions
					{
					KW_IN61=(Token)match(input,KW_IN,FOLLOW_KW_IN_in_precedenceEqualExpression846); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_IN.add(KW_IN61);

					pushFollow(FOLLOW_expressions_in_precedenceEqualExpression848);
					expressions62=expressions();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expressions.add(expressions62.getTree());
					}

					// AST REWRITE
					// elements: expressions, KW_IN, precedenceEqualExpression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 151:8: -> ^( TOK_FUNCTION KW_IN $precedenceEqualExpression expressions )
					{
						// Expr.g:151:11: ^( TOK_FUNCTION KW_IN $precedenceEqualExpression expressions )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_FUNCTION, "TOK_FUNCTION"), root_1);
						adaptor.addChild(root_1, stream_KW_IN.nextNode());
						adaptor.addChild(root_1, stream_retval.nextTree());
						adaptor.addChild(root_1, stream_expressions.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 5 :
					// Expr.g:152:7: ( KW_NOT KW_BETWEEN (min= precedenceBitwiseOrExpression ) KW_AND (max= precedenceBitwiseOrExpression ) )
					{
					// Expr.g:152:7: ( KW_NOT KW_BETWEEN (min= precedenceBitwiseOrExpression ) KW_AND (max= precedenceBitwiseOrExpression ) )
					// Expr.g:152:9: KW_NOT KW_BETWEEN (min= precedenceBitwiseOrExpression ) KW_AND (max= precedenceBitwiseOrExpression )
					{
					KW_NOT63=(Token)match(input,KW_NOT,FOLLOW_KW_NOT_in_precedenceEqualExpression879); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_NOT.add(KW_NOT63);

					KW_BETWEEN64=(Token)match(input,KW_BETWEEN,FOLLOW_KW_BETWEEN_in_precedenceEqualExpression881); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_BETWEEN.add(KW_BETWEEN64);

					// Expr.g:152:27: (min= precedenceBitwiseOrExpression )
					// Expr.g:152:28: min= precedenceBitwiseOrExpression
					{
					pushFollow(FOLLOW_precedenceBitwiseOrExpression_in_precedenceEqualExpression886);
					min=precedenceBitwiseOrExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_precedenceBitwiseOrExpression.add(min.getTree());
					}

					KW_AND65=(Token)match(input,KW_AND,FOLLOW_KW_AND_in_precedenceEqualExpression889); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_AND.add(KW_AND65);

					// Expr.g:152:70: (max= precedenceBitwiseOrExpression )
					// Expr.g:152:71: max= precedenceBitwiseOrExpression
					{
					pushFollow(FOLLOW_precedenceBitwiseOrExpression_in_precedenceEqualExpression894);
					max=precedenceBitwiseOrExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_precedenceBitwiseOrExpression.add(max.getTree());
					}

					}

					// AST REWRITE
					// elements: left, KW_BETWEEN, KW_NOT, max, min
					// token labels: 
					// rule labels: min, retval, max, left
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_min=new RewriteRuleSubtreeStream(adaptor,"rule min",min!=null?min.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_max=new RewriteRuleSubtreeStream(adaptor,"rule max",max!=null?max.getTree():null);
					RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 153:8: -> ^( TOK_FUNCTION KW_NOT ^( TOK_FUNCTION KW_BETWEEN $left $min $max) )
					{
						// Expr.g:153:11: ^( TOK_FUNCTION KW_NOT ^( TOK_FUNCTION KW_BETWEEN $left $min $max) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_FUNCTION, "TOK_FUNCTION"), root_1);
						adaptor.addChild(root_1, stream_KW_NOT.nextNode());
						// Expr.g:153:33: ^( TOK_FUNCTION KW_BETWEEN $left $min $max)
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_FUNCTION, "TOK_FUNCTION"), root_2);
						adaptor.addChild(root_2, stream_KW_BETWEEN.nextNode());
						adaptor.addChild(root_2, stream_left.nextTree());
						adaptor.addChild(root_2, stream_min.nextTree());
						adaptor.addChild(root_2, stream_max.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 6 :
					// Expr.g:154:7: ( KW_BETWEEN (min= precedenceBitwiseOrExpression ) KW_AND (max= precedenceBitwiseOrExpression ) )
					{
					// Expr.g:154:7: ( KW_BETWEEN (min= precedenceBitwiseOrExpression ) KW_AND (max= precedenceBitwiseOrExpression ) )
					// Expr.g:154:9: KW_BETWEEN (min= precedenceBitwiseOrExpression ) KW_AND (max= precedenceBitwiseOrExpression )
					{
					KW_BETWEEN66=(Token)match(input,KW_BETWEEN,FOLLOW_KW_BETWEEN_in_precedenceEqualExpression937); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_BETWEEN.add(KW_BETWEEN66);

					// Expr.g:154:20: (min= precedenceBitwiseOrExpression )
					// Expr.g:154:21: min= precedenceBitwiseOrExpression
					{
					pushFollow(FOLLOW_precedenceBitwiseOrExpression_in_precedenceEqualExpression942);
					min=precedenceBitwiseOrExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_precedenceBitwiseOrExpression.add(min.getTree());
					}

					KW_AND67=(Token)match(input,KW_AND,FOLLOW_KW_AND_in_precedenceEqualExpression945); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_AND.add(KW_AND67);

					// Expr.g:154:63: (max= precedenceBitwiseOrExpression )
					// Expr.g:154:64: max= precedenceBitwiseOrExpression
					{
					pushFollow(FOLLOW_precedenceBitwiseOrExpression_in_precedenceEqualExpression950);
					max=precedenceBitwiseOrExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_precedenceBitwiseOrExpression.add(max.getTree());
					}

					}

					// AST REWRITE
					// elements: max, min, KW_BETWEEN, left
					// token labels: 
					// rule labels: min, retval, max, left
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_min=new RewriteRuleSubtreeStream(adaptor,"rule min",min!=null?min.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_max=new RewriteRuleSubtreeStream(adaptor,"rule max",max!=null?max.getTree():null);
					RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 155:8: -> ^( TOK_FUNCTION KW_BETWEEN $left $min $max)
					{
						// Expr.g:155:11: ^( TOK_FUNCTION KW_BETWEEN $left $min $max)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_FUNCTION, "TOK_FUNCTION"), root_1);
						adaptor.addChild(root_1, stream_KW_BETWEEN.nextNode());
						adaptor.addChild(root_1, stream_left.nextTree());
						adaptor.addChild(root_1, stream_min.nextTree());
						adaptor.addChild(root_1, stream_max.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

				default :
					break loop12;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceEqualExpression"


	public static class expressions_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expressions"
	// Expr.g:159:1: expressions : LPAREN expression ( COMMA expression )* RPAREN -> ( expression )* ;
	public final SQLParser_Expr.expressions_return expressions() throws RecognitionException {
		SQLParser_Expr.expressions_return retval = new SQLParser_Expr.expressions_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LPAREN68=null;
		Token COMMA70=null;
		Token RPAREN72=null;
		ParserRuleReturnScope expression69 =null;
		ParserRuleReturnScope expression71 =null;

		CommonTree LPAREN68_tree=null;
		CommonTree COMMA70_tree=null;
		CommonTree RPAREN72_tree=null;
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");

		try {
			// Expr.g:160:5: ( LPAREN expression ( COMMA expression )* RPAREN -> ( expression )* )
			// Expr.g:161:5: LPAREN expression ( COMMA expression )* RPAREN
			{
			LPAREN68=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_expressions1005); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN68);

			pushFollow(FOLLOW_expression_in_expressions1007);
			expression69=expression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_expression.add(expression69.getTree());
			// Expr.g:161:23: ( COMMA expression )*
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( (LA13_0==COMMA) ) {
					alt13=1;
				}

				switch (alt13) {
				case 1 :
					// Expr.g:161:24: COMMA expression
					{
					COMMA70=(Token)match(input,COMMA,FOLLOW_COMMA_in_expressions1010); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_COMMA.add(COMMA70);

					pushFollow(FOLLOW_expression_in_expressions1012);
					expression71=expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expression.add(expression71.getTree());
					}
					break;

				default :
					break loop13;
				}
			}

			RPAREN72=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_expressions1016); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN72);

			// AST REWRITE
			// elements: expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 161:50: -> ( expression )*
			{
				// Expr.g:161:53: ( expression )*
				while ( stream_expression.hasNext() ) {
					adaptor.addChild(root_0, stream_expression.nextTree());
				}
				stream_expression.reset();

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expressions"


	public static class precedenceNotOperator_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceNotOperator"
	// Expr.g:164:1: precedenceNotOperator : KW_NOT ;
	public final SQLParser_Expr.precedenceNotOperator_return precedenceNotOperator() throws RecognitionException {
		SQLParser_Expr.precedenceNotOperator_return retval = new SQLParser_Expr.precedenceNotOperator_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_NOT73=null;

		CommonTree KW_NOT73_tree=null;

		try {
			// Expr.g:165:5: ( KW_NOT )
			// Expr.g:166:5: KW_NOT
			{
			root_0 = (CommonTree)adaptor.nil();


			KW_NOT73=(Token)match(input,KW_NOT,FOLLOW_KW_NOT_in_precedenceNotOperator1042); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			KW_NOT73_tree = (CommonTree)adaptor.create(KW_NOT73);
			adaptor.addChild(root_0, KW_NOT73_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceNotOperator"


	public static class precedenceNotExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceNotExpression"
	// Expr.g:169:1: precedenceNotExpression : ( precedenceNotOperator ^)* precedenceEqualExpression ;
	public final SQLParser_Expr.precedenceNotExpression_return precedenceNotExpression() throws RecognitionException {
		SQLParser_Expr.precedenceNotExpression_return retval = new SQLParser_Expr.precedenceNotExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope precedenceNotOperator74 =null;
		ParserRuleReturnScope precedenceEqualExpression75 =null;


		try {
			// Expr.g:170:5: ( ( precedenceNotOperator ^)* precedenceEqualExpression )
			// Expr.g:171:5: ( precedenceNotOperator ^)* precedenceEqualExpression
			{
			root_0 = (CommonTree)adaptor.nil();


			// Expr.g:171:5: ( precedenceNotOperator ^)*
			loop14:
			while (true) {
				int alt14=2;
				int LA14_0 = input.LA(1);
				if ( (LA14_0==KW_NOT) ) {
					alt14=1;
				}

				switch (alt14) {
				case 1 :
					// Expr.g:171:6: precedenceNotOperator ^
					{
					pushFollow(FOLLOW_precedenceNotOperator_in_precedenceNotExpression1064);
					precedenceNotOperator74=precedenceNotOperator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(precedenceNotOperator74.getTree(), root_0);
					}
					break;

				default :
					break loop14;
				}
			}

			pushFollow(FOLLOW_precedenceEqualExpression_in_precedenceNotExpression1069);
			precedenceEqualExpression75=precedenceEqualExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceEqualExpression75.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceNotExpression"


	public static class precedenceAndOperator_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceAndOperator"
	// Expr.g:175:1: precedenceAndOperator : KW_AND ;
	public final SQLParser_Expr.precedenceAndOperator_return precedenceAndOperator() throws RecognitionException {
		SQLParser_Expr.precedenceAndOperator_return retval = new SQLParser_Expr.precedenceAndOperator_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_AND76=null;

		CommonTree KW_AND76_tree=null;

		try {
			// Expr.g:176:5: ( KW_AND )
			// Expr.g:177:5: KW_AND
			{
			root_0 = (CommonTree)adaptor.nil();


			KW_AND76=(Token)match(input,KW_AND,FOLLOW_KW_AND_in_precedenceAndOperator1091); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			KW_AND76_tree = (CommonTree)adaptor.create(KW_AND76);
			adaptor.addChild(root_0, KW_AND76_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceAndOperator"


	public static class precedenceAndExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceAndExpression"
	// Expr.g:180:1: precedenceAndExpression : precedenceNotExpression ( precedenceAndOperator ^ precedenceNotExpression )* ;
	public final SQLParser_Expr.precedenceAndExpression_return precedenceAndExpression() throws RecognitionException {
		SQLParser_Expr.precedenceAndExpression_return retval = new SQLParser_Expr.precedenceAndExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope precedenceNotExpression77 =null;
		ParserRuleReturnScope precedenceAndOperator78 =null;
		ParserRuleReturnScope precedenceNotExpression79 =null;


		try {
			// Expr.g:181:5: ( precedenceNotExpression ( precedenceAndOperator ^ precedenceNotExpression )* )
			// Expr.g:182:5: precedenceNotExpression ( precedenceAndOperator ^ precedenceNotExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_precedenceNotExpression_in_precedenceAndExpression1112);
			precedenceNotExpression77=precedenceNotExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceNotExpression77.getTree());

			// Expr.g:182:29: ( precedenceAndOperator ^ precedenceNotExpression )*
			loop15:
			while (true) {
				int alt15=2;
				int LA15_0 = input.LA(1);
				if ( (LA15_0==KW_AND) ) {
					alt15=1;
				}

				switch (alt15) {
				case 1 :
					// Expr.g:182:30: precedenceAndOperator ^ precedenceNotExpression
					{
					pushFollow(FOLLOW_precedenceAndOperator_in_precedenceAndExpression1115);
					precedenceAndOperator78=precedenceAndOperator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(precedenceAndOperator78.getTree(), root_0);
					pushFollow(FOLLOW_precedenceNotExpression_in_precedenceAndExpression1118);
					precedenceNotExpression79=precedenceNotExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceNotExpression79.getTree());

					}
					break;

				default :
					break loop15;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceAndExpression"


	public static class precedenceOrOperator_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceOrOperator"
	// Expr.g:186:1: precedenceOrOperator : KW_OR ;
	public final SQLParser_Expr.precedenceOrOperator_return precedenceOrOperator() throws RecognitionException {
		SQLParser_Expr.precedenceOrOperator_return retval = new SQLParser_Expr.precedenceOrOperator_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_OR80=null;

		CommonTree KW_OR80_tree=null;

		try {
			// Expr.g:187:5: ( KW_OR )
			// Expr.g:188:5: KW_OR
			{
			root_0 = (CommonTree)adaptor.nil();


			KW_OR80=(Token)match(input,KW_OR,FOLLOW_KW_OR_in_precedenceOrOperator1142); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			KW_OR80_tree = (CommonTree)adaptor.create(KW_OR80);
			adaptor.addChild(root_0, KW_OR80_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceOrOperator"


	public static class precedenceOrExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "precedenceOrExpression"
	// Expr.g:191:1: precedenceOrExpression : precedenceAndExpression ( precedenceOrOperator ^ precedenceAndExpression )* ;
	public final SQLParser_Expr.precedenceOrExpression_return precedenceOrExpression() throws RecognitionException {
		SQLParser_Expr.precedenceOrExpression_return retval = new SQLParser_Expr.precedenceOrExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope precedenceAndExpression81 =null;
		ParserRuleReturnScope precedenceOrOperator82 =null;
		ParserRuleReturnScope precedenceAndExpression83 =null;


		try {
			// Expr.g:192:5: ( precedenceAndExpression ( precedenceOrOperator ^ precedenceAndExpression )* )
			// Expr.g:193:5: precedenceAndExpression ( precedenceOrOperator ^ precedenceAndExpression )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_precedenceAndExpression_in_precedenceOrExpression1163);
			precedenceAndExpression81=precedenceAndExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceAndExpression81.getTree());

			// Expr.g:193:29: ( precedenceOrOperator ^ precedenceAndExpression )*
			loop16:
			while (true) {
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( (LA16_0==KW_OR) ) {
					alt16=1;
				}

				switch (alt16) {
				case 1 :
					// Expr.g:193:30: precedenceOrOperator ^ precedenceAndExpression
					{
					pushFollow(FOLLOW_precedenceOrOperator_in_precedenceOrExpression1166);
					precedenceOrOperator82=precedenceOrOperator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(precedenceOrOperator82.getTree(), root_0);
					pushFollow(FOLLOW_precedenceAndExpression_in_precedenceOrExpression1169);
					precedenceAndExpression83=precedenceAndExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, precedenceAndExpression83.getTree());

					}
					break;

				default :
					break loop16;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "precedenceOrExpression"


	public static class booleanValue_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "booleanValue"
	// Expr.g:197:1: booleanValue : ( KW_TRUE ^| KW_FALSE ^);
	public final SQLParser_Expr.booleanValue_return booleanValue() throws RecognitionException {
		SQLParser_Expr.booleanValue_return retval = new SQLParser_Expr.booleanValue_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_TRUE84=null;
		Token KW_FALSE85=null;

		CommonTree KW_TRUE84_tree=null;
		CommonTree KW_FALSE85_tree=null;

		try {
			// Expr.g:198:5: ( KW_TRUE ^| KW_FALSE ^)
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==KW_TRUE) ) {
				alt17=1;
			}
			else if ( (LA17_0==KW_FALSE) ) {
				alt17=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}

			switch (alt17) {
				case 1 :
					// Expr.g:199:5: KW_TRUE ^
					{
					root_0 = (CommonTree)adaptor.nil();


					KW_TRUE84=(Token)match(input,KW_TRUE,FOLLOW_KW_TRUE_in_booleanValue1193); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					KW_TRUE84_tree = (CommonTree)adaptor.create(KW_TRUE84);
					root_0 = (CommonTree)adaptor.becomeRoot(KW_TRUE84_tree, root_0);
					}

					}
					break;
				case 2 :
					// Expr.g:199:16: KW_FALSE ^
					{
					root_0 = (CommonTree)adaptor.nil();


					KW_FALSE85=(Token)match(input,KW_FALSE,FOLLOW_KW_FALSE_in_booleanValue1198); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					KW_FALSE85_tree = (CommonTree)adaptor.create(KW_FALSE85);
					root_0 = (CommonTree)adaptor.becomeRoot(KW_FALSE85_tree, root_0);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "booleanValue"


	public static class function_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "function"
	// Expr.g:202:1: function : functionName LPAREN ( (star= STAR ) | (dist= KW_DISTINCT )? ( selectExpression ( COMMA selectExpression )* )? ) RPAREN ( KW_OVER ws= window_specification )? -> {$star != null}? ^( TOK_FUNCTIONSTAR functionName ( $ws)? ) -> {$dist == null}? ^( TOK_FUNCTION functionName ( ( selectExpression )+ )? ( $ws)? ) -> ^( TOK_FUNCTIONDI functionName ( ( selectExpression )+ )? ) ;
	public final SQLParser_Expr.function_return function() throws RecognitionException {
		SQLParser_Expr.function_return retval = new SQLParser_Expr.function_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token star=null;
		Token dist=null;
		Token LPAREN87=null;
		Token COMMA89=null;
		Token RPAREN91=null;
		Token KW_OVER92=null;
		ParserRuleReturnScope ws =null;
		ParserRuleReturnScope functionName86 =null;
		ParserRuleReturnScope selectExpression88 =null;
		ParserRuleReturnScope selectExpression90 =null;

		CommonTree star_tree=null;
		CommonTree dist_tree=null;
		CommonTree LPAREN87_tree=null;
		CommonTree COMMA89_tree=null;
		CommonTree RPAREN91_tree=null;
		CommonTree KW_OVER92_tree=null;
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
		RewriteRuleTokenStream stream_KW_OVER=new RewriteRuleTokenStream(adaptor,"token KW_OVER");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_KW_DISTINCT=new RewriteRuleTokenStream(adaptor,"token KW_DISTINCT");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_window_specification=new RewriteRuleSubtreeStream(adaptor,"rule window_specification");
		RewriteRuleSubtreeStream stream_selectExpression=new RewriteRuleSubtreeStream(adaptor,"rule selectExpression");
		RewriteRuleSubtreeStream stream_functionName=new RewriteRuleSubtreeStream(adaptor,"rule functionName");

		try {
			// Expr.g:203:5: ( functionName LPAREN ( (star= STAR ) | (dist= KW_DISTINCT )? ( selectExpression ( COMMA selectExpression )* )? ) RPAREN ( KW_OVER ws= window_specification )? -> {$star != null}? ^( TOK_FUNCTIONSTAR functionName ( $ws)? ) -> {$dist == null}? ^( TOK_FUNCTION functionName ( ( selectExpression )+ )? ( $ws)? ) -> ^( TOK_FUNCTIONDI functionName ( ( selectExpression )+ )? ) )
			// Expr.g:204:5: functionName LPAREN ( (star= STAR ) | (dist= KW_DISTINCT )? ( selectExpression ( COMMA selectExpression )* )? ) RPAREN ( KW_OVER ws= window_specification )?
			{
			pushFollow(FOLLOW_functionName_in_function1224);
			functionName86=functionName();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_functionName.add(functionName86.getTree());
			LPAREN87=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_function1230); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN87);

			// Expr.g:206:7: ( (star= STAR ) | (dist= KW_DISTINCT )? ( selectExpression ( COMMA selectExpression )* )? )
			int alt21=2;
			int LA21_0 = input.LA(1);
			if ( (LA21_0==STAR) ) {
				alt21=1;
			}
			else if ( (LA21_0==DecimalLiteral||LA21_0==Identifier||LA21_0==KW_CASE||LA21_0==KW_CAST||LA21_0==KW_DATE||LA21_0==KW_DISTINCT||LA21_0==KW_FALSE||(LA21_0 >= KW_NOT && LA21_0 <= KW_NULL)||LA21_0==KW_TRUE||LA21_0==LPAREN||LA21_0==MINUS||(LA21_0 >= Number && LA21_0 <= PLUS)||LA21_0==RPAREN||(LA21_0 >= StringLiteral && LA21_0 <= TILDE)) ) {
				alt21=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 21, 0, input);
				throw nvae;
			}

			switch (alt21) {
				case 1 :
					// Expr.g:207:9: (star= STAR )
					{
					// Expr.g:207:9: (star= STAR )
					// Expr.g:207:10: star= STAR
					{
					star=(Token)match(input,STAR,FOLLOW_STAR_in_function1251); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_STAR.add(star);

					}

					}
					break;
				case 2 :
					// Expr.g:208:11: (dist= KW_DISTINCT )? ( selectExpression ( COMMA selectExpression )* )?
					{
					// Expr.g:208:11: (dist= KW_DISTINCT )?
					int alt18=2;
					int LA18_0 = input.LA(1);
					if ( (LA18_0==KW_DISTINCT) ) {
						alt18=1;
					}
					switch (alt18) {
						case 1 :
							// Expr.g:208:12: dist= KW_DISTINCT
							{
							dist=(Token)match(input,KW_DISTINCT,FOLLOW_KW_DISTINCT_in_function1267); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_KW_DISTINCT.add(dist);

							}
							break;

					}

					// Expr.g:208:31: ( selectExpression ( COMMA selectExpression )* )?
					int alt20=2;
					int LA20_0 = input.LA(1);
					if ( (LA20_0==DecimalLiteral||LA20_0==Identifier||LA20_0==KW_CASE||LA20_0==KW_CAST||LA20_0==KW_DATE||LA20_0==KW_FALSE||(LA20_0 >= KW_NOT && LA20_0 <= KW_NULL)||LA20_0==KW_TRUE||LA20_0==LPAREN||LA20_0==MINUS||(LA20_0 >= Number && LA20_0 <= PLUS)||(LA20_0 >= STAR && LA20_0 <= TILDE)) ) {
						alt20=1;
					}
					switch (alt20) {
						case 1 :
							// Expr.g:208:32: selectExpression ( COMMA selectExpression )*
							{
							pushFollow(FOLLOW_selectExpression_in_function1272);
							selectExpression88=gSQLParser.selectExpression();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_selectExpression.add(selectExpression88.getTree());
							// Expr.g:208:49: ( COMMA selectExpression )*
							loop19:
							while (true) {
								int alt19=2;
								int LA19_0 = input.LA(1);
								if ( (LA19_0==COMMA) ) {
									alt19=1;
								}

								switch (alt19) {
								case 1 :
									// Expr.g:208:50: COMMA selectExpression
									{
									COMMA89=(Token)match(input,COMMA,FOLLOW_COMMA_in_function1275); if (state.failed) return retval; 
									if ( state.backtracking==0 ) stream_COMMA.add(COMMA89);

									pushFollow(FOLLOW_selectExpression_in_function1277);
									selectExpression90=gSQLParser.selectExpression();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) stream_selectExpression.add(selectExpression90.getTree());
									}
									break;

								default :
									break loop19;
								}
							}

							}
							break;

					}

					}
					break;

			}

			RPAREN91=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_function1295); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN91);

			// Expr.g:210:12: ( KW_OVER ws= window_specification )?
			int alt22=2;
			int LA22_0 = input.LA(1);
			if ( (LA22_0==KW_OVER) ) {
				alt22=1;
			}
			switch (alt22) {
				case 1 :
					// Expr.g:210:13: KW_OVER ws= window_specification
					{
					KW_OVER92=(Token)match(input,KW_OVER,FOLLOW_KW_OVER_in_function1298); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_OVER.add(KW_OVER92);

					pushFollow(FOLLOW_window_specification_in_function1302);
					ws=window_specification();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_window_specification.add(ws.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: selectExpression, ws, functionName, functionName, ws, selectExpression, functionName
			// token labels: 
			// rule labels: retval, ws
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_ws=new RewriteRuleSubtreeStream(adaptor,"rule ws",ws!=null?ws.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 211:12: -> {$star != null}? ^( TOK_FUNCTIONSTAR functionName ( $ws)? )
			if (star != null) {
				// Expr.g:211:32: ^( TOK_FUNCTIONSTAR functionName ( $ws)? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_FUNCTIONSTAR, "TOK_FUNCTIONSTAR"), root_1);
				adaptor.addChild(root_1, stream_functionName.nextTree());
				// Expr.g:211:65: ( $ws)?
				if ( stream_ws.hasNext() ) {
					adaptor.addChild(root_1, stream_ws.nextTree());
				}
				stream_ws.reset();

				adaptor.addChild(root_0, root_1);
				}

			}

			else // 212:12: -> {$dist == null}? ^( TOK_FUNCTION functionName ( ( selectExpression )+ )? ( $ws)? )
			if (dist == null) {
				// Expr.g:212:32: ^( TOK_FUNCTION functionName ( ( selectExpression )+ )? ( $ws)? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_FUNCTION, "TOK_FUNCTION"), root_1);
				adaptor.addChild(root_1, stream_functionName.nextTree());
				// Expr.g:212:60: ( ( selectExpression )+ )?
				if ( stream_selectExpression.hasNext() ) {
					if ( !(stream_selectExpression.hasNext()) ) {
						throw new RewriteEarlyExitException();
					}
					while ( stream_selectExpression.hasNext() ) {
						adaptor.addChild(root_1, stream_selectExpression.nextTree());
					}
					stream_selectExpression.reset();

				}
				stream_selectExpression.reset();

				// Expr.g:212:82: ( $ws)?
				if ( stream_ws.hasNext() ) {
					adaptor.addChild(root_1, stream_ws.nextTree());
				}
				stream_ws.reset();

				adaptor.addChild(root_0, root_1);
				}

			}

			else // 213:29: -> ^( TOK_FUNCTIONDI functionName ( ( selectExpression )+ )? )
			{
				// Expr.g:213:32: ^( TOK_FUNCTIONDI functionName ( ( selectExpression )+ )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_FUNCTIONDI, "TOK_FUNCTIONDI"), root_1);
				adaptor.addChild(root_1, stream_functionName.nextTree());
				// Expr.g:213:62: ( ( selectExpression )+ )?
				if ( stream_selectExpression.hasNext() ) {
					if ( !(stream_selectExpression.hasNext()) ) {
						throw new RewriteEarlyExitException();
					}
					while ( stream_selectExpression.hasNext() ) {
						adaptor.addChild(root_1, stream_selectExpression.nextTree());
					}
					stream_selectExpression.reset();

				}
				stream_selectExpression.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "function"


	public static class window_specification_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "window_specification"
	// Expr.g:215:1: window_specification : LPAREN ( partitioningSpec )? ( window_frame )? RPAREN -> ^( TOK_WINDOWSPEC ( partitioningSpec )? ( window_frame )? ) ;
	public final SQLParser_Expr.window_specification_return window_specification() throws RecognitionException {
		SQLParser_Expr.window_specification_return retval = new SQLParser_Expr.window_specification_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LPAREN93=null;
		Token RPAREN96=null;
		ParserRuleReturnScope partitioningSpec94 =null;
		ParserRuleReturnScope window_frame95 =null;

		CommonTree LPAREN93_tree=null;
		CommonTree RPAREN96_tree=null;
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_window_frame=new RewriteRuleSubtreeStream(adaptor,"rule window_frame");
		RewriteRuleSubtreeStream stream_partitioningSpec=new RewriteRuleSubtreeStream(adaptor,"rule partitioningSpec");

		try {
			// Expr.g:216:2: ( LPAREN ( partitioningSpec )? ( window_frame )? RPAREN -> ^( TOK_WINDOWSPEC ( partitioningSpec )? ( window_frame )? ) )
			// Expr.g:217:3: LPAREN ( partitioningSpec )? ( window_frame )? RPAREN
			{
			LPAREN93=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_window_specification1418); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN93);

			// Expr.g:217:11: ( partitioningSpec )?
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==KW_ORDER||LA23_0==KW_PARTITION) ) {
				alt23=1;
			}
			switch (alt23) {
				case 1 :
					// Expr.g:217:11: partitioningSpec
					{
					pushFollow(FOLLOW_partitioningSpec_in_window_specification1421);
					partitioningSpec94=partitioningSpec();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_partitioningSpec.add(partitioningSpec94.getTree());
					}
					break;

			}

			// Expr.g:217:29: ( window_frame )?
			int alt24=2;
			int LA24_0 = input.LA(1);
			if ( (LA24_0==KW_RANGE||LA24_0==KW_ROWS) ) {
				alt24=1;
			}
			switch (alt24) {
				case 1 :
					// Expr.g:217:29: window_frame
					{
					pushFollow(FOLLOW_window_frame_in_window_specification1424);
					window_frame95=window_frame();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_window_frame.add(window_frame95.getTree());
					}
					break;

			}

			RPAREN96=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_window_specification1427); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN96);

			// AST REWRITE
			// elements: window_frame, partitioningSpec
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 218:3: -> ^( TOK_WINDOWSPEC ( partitioningSpec )? ( window_frame )? )
			{
				// Expr.g:218:6: ^( TOK_WINDOWSPEC ( partitioningSpec )? ( window_frame )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_WINDOWSPEC, "TOK_WINDOWSPEC"), root_1);
				// Expr.g:218:24: ( partitioningSpec )?
				if ( stream_partitioningSpec.hasNext() ) {
					adaptor.addChild(root_1, stream_partitioningSpec.nextTree());
				}
				stream_partitioningSpec.reset();

				// Expr.g:218:42: ( window_frame )?
				if ( stream_window_frame.hasNext() ) {
					adaptor.addChild(root_1, stream_window_frame.nextTree());
				}
				stream_window_frame.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "window_specification"


	public static class partitioningSpec_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "partitioningSpec"
	// Expr.g:220:1: partitioningSpec : ( partitionByClause ( orderByClause )? -> ^( TOK_PARTITIONINGSPEC partitionByClause ( orderByClause )? ) | orderByClause -> ^( TOK_PARTITIONINGSPEC orderByClause ) );
	public final SQLParser_Expr.partitioningSpec_return partitioningSpec() throws RecognitionException {
		SQLParser_Expr.partitioningSpec_return retval = new SQLParser_Expr.partitioningSpec_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope partitionByClause97 =null;
		ParserRuleReturnScope orderByClause98 =null;
		ParserRuleReturnScope orderByClause99 =null;

		RewriteRuleSubtreeStream stream_partitionByClause=new RewriteRuleSubtreeStream(adaptor,"rule partitionByClause");
		RewriteRuleSubtreeStream stream_orderByClause=new RewriteRuleSubtreeStream(adaptor,"rule orderByClause");

		try {
			// Expr.g:221:4: ( partitionByClause ( orderByClause )? -> ^( TOK_PARTITIONINGSPEC partitionByClause ( orderByClause )? ) | orderByClause -> ^( TOK_PARTITIONINGSPEC orderByClause ) )
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==KW_PARTITION) ) {
				alt26=1;
			}
			else if ( (LA26_0==KW_ORDER) ) {
				alt26=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 26, 0, input);
				throw nvae;
			}

			switch (alt26) {
				case 1 :
					// Expr.g:222:2: partitionByClause ( orderByClause )?
					{
					pushFollow(FOLLOW_partitionByClause_in_partitioningSpec1455);
					partitionByClause97=partitionByClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_partitionByClause.add(partitionByClause97.getTree());
					// Expr.g:222:20: ( orderByClause )?
					int alt25=2;
					int LA25_0 = input.LA(1);
					if ( (LA25_0==KW_ORDER) ) {
						alt25=1;
					}
					switch (alt25) {
						case 1 :
							// Expr.g:222:20: orderByClause
							{
							pushFollow(FOLLOW_orderByClause_in_partitioningSpec1457);
							orderByClause98=gSQLParser.orderByClause();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_orderByClause.add(orderByClause98.getTree());
							}
							break;

					}

					// AST REWRITE
					// elements: partitionByClause, orderByClause
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 223:3: -> ^( TOK_PARTITIONINGSPEC partitionByClause ( orderByClause )? )
					{
						// Expr.g:223:5: ^( TOK_PARTITIONINGSPEC partitionByClause ( orderByClause )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_PARTITIONINGSPEC, "TOK_PARTITIONINGSPEC"), root_1);
						adaptor.addChild(root_1, stream_partitionByClause.nextTree());
						// Expr.g:223:46: ( orderByClause )?
						if ( stream_orderByClause.hasNext() ) {
							adaptor.addChild(root_1, stream_orderByClause.nextTree());
						}
						stream_orderByClause.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// Expr.g:224:2: orderByClause
					{
					pushFollow(FOLLOW_orderByClause_in_partitioningSpec1476);
					orderByClause99=gSQLParser.orderByClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_orderByClause.add(orderByClause99.getTree());
					// AST REWRITE
					// elements: orderByClause
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 225:3: -> ^( TOK_PARTITIONINGSPEC orderByClause )
					{
						// Expr.g:225:5: ^( TOK_PARTITIONINGSPEC orderByClause )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_PARTITIONINGSPEC, "TOK_PARTITIONINGSPEC"), root_1);
						adaptor.addChild(root_1, stream_orderByClause.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "partitioningSpec"


	public static class partitionByClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "partitionByClause"
	// Expr.g:227:1: partitionByClause : ( KW_PARTITION KW_BY LPAREN expression ( COMMA expression )* RPAREN -> ^( TOK_DISTRIBUTEBY ( expression )+ ) | KW_PARTITION KW_BY expression ( ( COMMA )=> COMMA expression )* -> ^( TOK_DISTRIBUTEBY ( expression )+ ) );
	public final SQLParser_Expr.partitionByClause_return partitionByClause() throws RecognitionException {
		SQLParser_Expr.partitionByClause_return retval = new SQLParser_Expr.partitionByClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_PARTITION100=null;
		Token KW_BY101=null;
		Token LPAREN102=null;
		Token COMMA104=null;
		Token RPAREN106=null;
		Token KW_PARTITION107=null;
		Token KW_BY108=null;
		Token COMMA110=null;
		ParserRuleReturnScope expression103 =null;
		ParserRuleReturnScope expression105 =null;
		ParserRuleReturnScope expression109 =null;
		ParserRuleReturnScope expression111 =null;

		CommonTree KW_PARTITION100_tree=null;
		CommonTree KW_BY101_tree=null;
		CommonTree LPAREN102_tree=null;
		CommonTree COMMA104_tree=null;
		CommonTree RPAREN106_tree=null;
		CommonTree KW_PARTITION107_tree=null;
		CommonTree KW_BY108_tree=null;
		CommonTree COMMA110_tree=null;
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_KW_PARTITION=new RewriteRuleTokenStream(adaptor,"token KW_PARTITION");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleTokenStream stream_KW_BY=new RewriteRuleTokenStream(adaptor,"token KW_BY");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");

		try {
			// Expr.g:228:5: ( KW_PARTITION KW_BY LPAREN expression ( COMMA expression )* RPAREN -> ^( TOK_DISTRIBUTEBY ( expression )+ ) | KW_PARTITION KW_BY expression ( ( COMMA )=> COMMA expression )* -> ^( TOK_DISTRIBUTEBY ( expression )+ ) )
			int alt29=2;
			int LA29_0 = input.LA(1);
			if ( (LA29_0==KW_PARTITION) ) {
				int LA29_1 = input.LA(2);
				if ( (LA29_1==KW_BY) ) {
					int LA29_2 = input.LA(3);
					if ( (LA29_2==LPAREN) ) {
						alt29=1;
					}
					else if ( (LA29_2==DecimalLiteral||LA29_2==Identifier||LA29_2==KW_CASE||LA29_2==KW_CAST||LA29_2==KW_DATE||LA29_2==KW_FALSE||(LA29_2 >= KW_NOT && LA29_2 <= KW_NULL)||LA29_2==KW_TRUE||LA29_2==MINUS||(LA29_2 >= Number && LA29_2 <= PLUS)||(LA29_2 >= StringLiteral && LA29_2 <= TILDE)) ) {
						alt29=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 29, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 29, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 29, 0, input);
				throw nvae;
			}

			switch (alt29) {
				case 1 :
					// Expr.g:229:2: KW_PARTITION KW_BY LPAREN expression ( COMMA expression )* RPAREN
					{
					KW_PARTITION100=(Token)match(input,KW_PARTITION,FOLLOW_KW_PARTITION_in_partitionByClause1502); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_PARTITION.add(KW_PARTITION100);

					KW_BY101=(Token)match(input,KW_BY,FOLLOW_KW_BY_in_partitionByClause1504); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_BY.add(KW_BY101);

					LPAREN102=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_partitionByClause1507); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN102);

					pushFollow(FOLLOW_expression_in_partitionByClause1509);
					expression103=expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expression.add(expression103.getTree());
					// Expr.g:230:20: ( COMMA expression )*
					loop27:
					while (true) {
						int alt27=2;
						int LA27_0 = input.LA(1);
						if ( (LA27_0==COMMA) ) {
							alt27=1;
						}

						switch (alt27) {
						case 1 :
							// Expr.g:230:21: COMMA expression
							{
							COMMA104=(Token)match(input,COMMA,FOLLOW_COMMA_in_partitionByClause1512); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_COMMA.add(COMMA104);

							pushFollow(FOLLOW_expression_in_partitionByClause1514);
							expression105=expression();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_expression.add(expression105.getTree());
							}
							break;

						default :
							break loop27;
						}
					}

					RPAREN106=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_partitionByClause1518); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN106);

					// AST REWRITE
					// elements: expression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 230:47: -> ^( TOK_DISTRIBUTEBY ( expression )+ )
					{
						// Expr.g:230:50: ^( TOK_DISTRIBUTEBY ( expression )+ )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DISTRIBUTEBY, "TOK_DISTRIBUTEBY"), root_1);
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

					}
					break;
				case 2 :
					// Expr.g:232:2: KW_PARTITION KW_BY expression ( ( COMMA )=> COMMA expression )*
					{
					KW_PARTITION107=(Token)match(input,KW_PARTITION,FOLLOW_KW_PARTITION_in_partitionByClause1533); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_PARTITION.add(KW_PARTITION107);

					KW_BY108=(Token)match(input,KW_BY,FOLLOW_KW_BY_in_partitionByClause1535); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_BY.add(KW_BY108);

					pushFollow(FOLLOW_expression_in_partitionByClause1538);
					expression109=expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expression.add(expression109.getTree());
					// Expr.g:233:13: ( ( COMMA )=> COMMA expression )*
					loop28:
					while (true) {
						int alt28=2;
						int LA28_0 = input.LA(1);
						if ( (LA28_0==COMMA) && (synpred1_Expr())) {
							alt28=1;
						}

						switch (alt28) {
						case 1 :
							// Expr.g:233:14: ( COMMA )=> COMMA expression
							{
							COMMA110=(Token)match(input,COMMA,FOLLOW_COMMA_in_partitionByClause1546); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_COMMA.add(COMMA110);

							pushFollow(FOLLOW_expression_in_partitionByClause1548);
							expression111=expression();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_expression.add(expression111.getTree());
							}
							break;

						default :
							break loop28;
						}
					}

					// AST REWRITE
					// elements: expression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 233:43: -> ^( TOK_DISTRIBUTEBY ( expression )+ )
					{
						// Expr.g:233:46: ^( TOK_DISTRIBUTEBY ( expression )+ )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DISTRIBUTEBY, "TOK_DISTRIBUTEBY"), root_1);
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

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "partitionByClause"


	public static class window_frame_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "window_frame"
	// Expr.g:236:1: window_frame : ( window_range_expression | window_value_expression );
	public final SQLParser_Expr.window_frame_return window_frame() throws RecognitionException {
		SQLParser_Expr.window_frame_return retval = new SQLParser_Expr.window_frame_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope window_range_expression112 =null;
		ParserRuleReturnScope window_value_expression113 =null;


		try {
			// Expr.g:236:13: ( window_range_expression | window_value_expression )
			int alt30=2;
			int LA30_0 = input.LA(1);
			if ( (LA30_0==KW_ROWS) ) {
				alt30=1;
			}
			else if ( (LA30_0==KW_RANGE) ) {
				alt30=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 30, 0, input);
				throw nvae;
			}

			switch (alt30) {
				case 1 :
					// Expr.g:237:2: window_range_expression
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_window_range_expression_in_window_frame1576);
					window_range_expression112=window_range_expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, window_range_expression112.getTree());

					}
					break;
				case 2 :
					// Expr.g:238:2: window_value_expression
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_window_value_expression_in_window_frame1581);
					window_value_expression113=window_value_expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, window_value_expression113.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "window_frame"


	public static class window_range_expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "window_range_expression"
	// Expr.g:241:1: window_range_expression : ( KW_ROWS sb= window_frame_start_boundary -> ^( TOK_WINDOWRANGE $sb) | KW_ROWS KW_BETWEEN s= window_frame_boundary KW_AND end= window_frame_boundary -> ^( TOK_WINDOWRANGE $s $end) );
	public final SQLParser_Expr.window_range_expression_return window_range_expression() throws RecognitionException {
		SQLParser_Expr.window_range_expression_return retval = new SQLParser_Expr.window_range_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_ROWS114=null;
		Token KW_ROWS115=null;
		Token KW_BETWEEN116=null;
		Token KW_AND117=null;
		ParserRuleReturnScope sb =null;
		ParserRuleReturnScope s =null;
		ParserRuleReturnScope end =null;

		CommonTree KW_ROWS114_tree=null;
		CommonTree KW_ROWS115_tree=null;
		CommonTree KW_BETWEEN116_tree=null;
		CommonTree KW_AND117_tree=null;
		RewriteRuleTokenStream stream_KW_BETWEEN=new RewriteRuleTokenStream(adaptor,"token KW_BETWEEN");
		RewriteRuleTokenStream stream_KW_ROWS=new RewriteRuleTokenStream(adaptor,"token KW_ROWS");
		RewriteRuleTokenStream stream_KW_AND=new RewriteRuleTokenStream(adaptor,"token KW_AND");
		RewriteRuleSubtreeStream stream_window_frame_boundary=new RewriteRuleSubtreeStream(adaptor,"rule window_frame_boundary");
		RewriteRuleSubtreeStream stream_window_frame_start_boundary=new RewriteRuleSubtreeStream(adaptor,"rule window_frame_start_boundary");

		try {
			// Expr.g:242:2: ( KW_ROWS sb= window_frame_start_boundary -> ^( TOK_WINDOWRANGE $sb) | KW_ROWS KW_BETWEEN s= window_frame_boundary KW_AND end= window_frame_boundary -> ^( TOK_WINDOWRANGE $s $end) )
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==KW_ROWS) ) {
				int LA31_1 = input.LA(2);
				if ( (LA31_1==KW_BETWEEN) ) {
					alt31=2;
				}
				else if ( (LA31_1==Number||LA31_1==KW_CURRENT||LA31_1==KW_UNBOUNDED) ) {
					alt31=1;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 31, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}

			switch (alt31) {
				case 1 :
					// Expr.g:243:2: KW_ROWS sb= window_frame_start_boundary
					{
					KW_ROWS114=(Token)match(input,KW_ROWS,FOLLOW_KW_ROWS_in_window_range_expression1593); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_ROWS.add(KW_ROWS114);

					pushFollow(FOLLOW_window_frame_start_boundary_in_window_range_expression1597);
					sb=window_frame_start_boundary();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_window_frame_start_boundary.add(sb.getTree());
					// AST REWRITE
					// elements: sb
					// token labels: 
					// rule labels: retval, sb
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_sb=new RewriteRuleSubtreeStream(adaptor,"rule sb",sb!=null?sb.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 243:41: -> ^( TOK_WINDOWRANGE $sb)
					{
						// Expr.g:243:44: ^( TOK_WINDOWRANGE $sb)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_WINDOWRANGE, "TOK_WINDOWRANGE"), root_1);
						adaptor.addChild(root_1, stream_sb.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// Expr.g:244:2: KW_ROWS KW_BETWEEN s= window_frame_boundary KW_AND end= window_frame_boundary
					{
					KW_ROWS115=(Token)match(input,KW_ROWS,FOLLOW_KW_ROWS_in_window_range_expression1611); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_ROWS.add(KW_ROWS115);

					KW_BETWEEN116=(Token)match(input,KW_BETWEEN,FOLLOW_KW_BETWEEN_in_window_range_expression1613); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_BETWEEN.add(KW_BETWEEN116);

					pushFollow(FOLLOW_window_frame_boundary_in_window_range_expression1617);
					s=window_frame_boundary();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_window_frame_boundary.add(s.getTree());
					KW_AND117=(Token)match(input,KW_AND,FOLLOW_KW_AND_in_window_range_expression1619); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_AND.add(KW_AND117);

					pushFollow(FOLLOW_window_frame_boundary_in_window_range_expression1623);
					end=window_frame_boundary();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_window_frame_boundary.add(end.getTree());
					// AST REWRITE
					// elements: end, s
					// token labels: 
					// rule labels: retval, s, end
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.getTree():null);
					RewriteRuleSubtreeStream stream_end=new RewriteRuleSubtreeStream(adaptor,"rule end",end!=null?end.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 244:78: -> ^( TOK_WINDOWRANGE $s $end)
					{
						// Expr.g:244:81: ^( TOK_WINDOWRANGE $s $end)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_WINDOWRANGE, "TOK_WINDOWRANGE"), root_1);
						adaptor.addChild(root_1, stream_s.nextTree());
						adaptor.addChild(root_1, stream_end.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "window_range_expression"


	public static class window_value_expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "window_value_expression"
	// Expr.g:247:1: window_value_expression : ( KW_RANGE sb= window_frame_start_boundary -> ^( TOK_WINDOWVALUES $sb) | KW_RANGE KW_BETWEEN s= window_frame_boundary KW_AND end= window_frame_boundary -> ^( TOK_WINDOWVALUES $s $end) );
	public final SQLParser_Expr.window_value_expression_return window_value_expression() throws RecognitionException {
		SQLParser_Expr.window_value_expression_return retval = new SQLParser_Expr.window_value_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_RANGE118=null;
		Token KW_RANGE119=null;
		Token KW_BETWEEN120=null;
		Token KW_AND121=null;
		ParserRuleReturnScope sb =null;
		ParserRuleReturnScope s =null;
		ParserRuleReturnScope end =null;

		CommonTree KW_RANGE118_tree=null;
		CommonTree KW_RANGE119_tree=null;
		CommonTree KW_BETWEEN120_tree=null;
		CommonTree KW_AND121_tree=null;
		RewriteRuleTokenStream stream_KW_BETWEEN=new RewriteRuleTokenStream(adaptor,"token KW_BETWEEN");
		RewriteRuleTokenStream stream_KW_AND=new RewriteRuleTokenStream(adaptor,"token KW_AND");
		RewriteRuleTokenStream stream_KW_RANGE=new RewriteRuleTokenStream(adaptor,"token KW_RANGE");
		RewriteRuleSubtreeStream stream_window_frame_boundary=new RewriteRuleSubtreeStream(adaptor,"rule window_frame_boundary");
		RewriteRuleSubtreeStream stream_window_frame_start_boundary=new RewriteRuleSubtreeStream(adaptor,"rule window_frame_start_boundary");

		try {
			// Expr.g:248:2: ( KW_RANGE sb= window_frame_start_boundary -> ^( TOK_WINDOWVALUES $sb) | KW_RANGE KW_BETWEEN s= window_frame_boundary KW_AND end= window_frame_boundary -> ^( TOK_WINDOWVALUES $s $end) )
			int alt32=2;
			int LA32_0 = input.LA(1);
			if ( (LA32_0==KW_RANGE) ) {
				int LA32_1 = input.LA(2);
				if ( (LA32_1==KW_BETWEEN) ) {
					alt32=2;
				}
				else if ( (LA32_1==Number||LA32_1==KW_CURRENT||LA32_1==KW_UNBOUNDED) ) {
					alt32=1;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 32, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 32, 0, input);
				throw nvae;
			}

			switch (alt32) {
				case 1 :
					// Expr.g:249:2: KW_RANGE sb= window_frame_start_boundary
					{
					KW_RANGE118=(Token)match(input,KW_RANGE,FOLLOW_KW_RANGE_in_window_value_expression1648); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_RANGE.add(KW_RANGE118);

					pushFollow(FOLLOW_window_frame_start_boundary_in_window_value_expression1652);
					sb=window_frame_start_boundary();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_window_frame_start_boundary.add(sb.getTree());
					// AST REWRITE
					// elements: sb
					// token labels: 
					// rule labels: retval, sb
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_sb=new RewriteRuleSubtreeStream(adaptor,"rule sb",sb!=null?sb.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 249:42: -> ^( TOK_WINDOWVALUES $sb)
					{
						// Expr.g:249:45: ^( TOK_WINDOWVALUES $sb)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_WINDOWVALUES, "TOK_WINDOWVALUES"), root_1);
						adaptor.addChild(root_1, stream_sb.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// Expr.g:250:2: KW_RANGE KW_BETWEEN s= window_frame_boundary KW_AND end= window_frame_boundary
					{
					KW_RANGE119=(Token)match(input,KW_RANGE,FOLLOW_KW_RANGE_in_window_value_expression1666); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_RANGE.add(KW_RANGE119);

					KW_BETWEEN120=(Token)match(input,KW_BETWEEN,FOLLOW_KW_BETWEEN_in_window_value_expression1668); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_BETWEEN.add(KW_BETWEEN120);

					pushFollow(FOLLOW_window_frame_boundary_in_window_value_expression1672);
					s=window_frame_boundary();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_window_frame_boundary.add(s.getTree());
					KW_AND121=(Token)match(input,KW_AND,FOLLOW_KW_AND_in_window_value_expression1674); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_AND.add(KW_AND121);

					pushFollow(FOLLOW_window_frame_boundary_in_window_value_expression1678);
					end=window_frame_boundary();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_window_frame_boundary.add(end.getTree());
					// AST REWRITE
					// elements: s, end
					// token labels: 
					// rule labels: retval, s, end
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.getTree():null);
					RewriteRuleSubtreeStream stream_end=new RewriteRuleSubtreeStream(adaptor,"rule end",end!=null?end.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 250:79: -> ^( TOK_WINDOWVALUES $s $end)
					{
						// Expr.g:250:82: ^( TOK_WINDOWVALUES $s $end)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_WINDOWVALUES, "TOK_WINDOWVALUES"), root_1);
						adaptor.addChild(root_1, stream_s.nextTree());
						adaptor.addChild(root_1, stream_end.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "window_value_expression"


	public static class window_frame_start_boundary_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "window_frame_start_boundary"
	// Expr.g:253:1: window_frame_start_boundary : ( KW_UNBOUNDED KW_PRECEDING -> ^( KW_PRECEDING KW_UNBOUNDED ) | KW_CURRENT KW_ROW -> ^( KW_CURRENT ) | Number KW_PRECEDING -> ^( KW_PRECEDING Number ) );
	public final SQLParser_Expr.window_frame_start_boundary_return window_frame_start_boundary() throws RecognitionException {
		SQLParser_Expr.window_frame_start_boundary_return retval = new SQLParser_Expr.window_frame_start_boundary_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_UNBOUNDED122=null;
		Token KW_PRECEDING123=null;
		Token KW_CURRENT124=null;
		Token KW_ROW125=null;
		Token Number126=null;
		Token KW_PRECEDING127=null;

		CommonTree KW_UNBOUNDED122_tree=null;
		CommonTree KW_PRECEDING123_tree=null;
		CommonTree KW_CURRENT124_tree=null;
		CommonTree KW_ROW125_tree=null;
		CommonTree Number126_tree=null;
		CommonTree KW_PRECEDING127_tree=null;
		RewriteRuleTokenStream stream_KW_PRECEDING=new RewriteRuleTokenStream(adaptor,"token KW_PRECEDING");
		RewriteRuleTokenStream stream_Number=new RewriteRuleTokenStream(adaptor,"token Number");
		RewriteRuleTokenStream stream_KW_CURRENT=new RewriteRuleTokenStream(adaptor,"token KW_CURRENT");
		RewriteRuleTokenStream stream_KW_ROW=new RewriteRuleTokenStream(adaptor,"token KW_ROW");
		RewriteRuleTokenStream stream_KW_UNBOUNDED=new RewriteRuleTokenStream(adaptor,"token KW_UNBOUNDED");

		try {
			// Expr.g:254:2: ( KW_UNBOUNDED KW_PRECEDING -> ^( KW_PRECEDING KW_UNBOUNDED ) | KW_CURRENT KW_ROW -> ^( KW_CURRENT ) | Number KW_PRECEDING -> ^( KW_PRECEDING Number ) )
			int alt33=3;
			switch ( input.LA(1) ) {
			case KW_UNBOUNDED:
				{
				alt33=1;
				}
				break;
			case KW_CURRENT:
				{
				alt33=2;
				}
				break;
			case Number:
				{
				alt33=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 33, 0, input);
				throw nvae;
			}
			switch (alt33) {
				case 1 :
					// Expr.g:255:2: KW_UNBOUNDED KW_PRECEDING
					{
					KW_UNBOUNDED122=(Token)match(input,KW_UNBOUNDED,FOLLOW_KW_UNBOUNDED_in_window_frame_start_boundary1703); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_UNBOUNDED.add(KW_UNBOUNDED122);

					KW_PRECEDING123=(Token)match(input,KW_PRECEDING,FOLLOW_KW_PRECEDING_in_window_frame_start_boundary1705); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_PRECEDING.add(KW_PRECEDING123);

					// AST REWRITE
					// elements: KW_PRECEDING, KW_UNBOUNDED
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 255:29: -> ^( KW_PRECEDING KW_UNBOUNDED )
					{
						// Expr.g:255:32: ^( KW_PRECEDING KW_UNBOUNDED )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_KW_PRECEDING.nextNode(), root_1);
						adaptor.addChild(root_1, stream_KW_UNBOUNDED.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// Expr.g:256:2: KW_CURRENT KW_ROW
					{
					KW_CURRENT124=(Token)match(input,KW_CURRENT,FOLLOW_KW_CURRENT_in_window_frame_start_boundary1720); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_CURRENT.add(KW_CURRENT124);

					KW_ROW125=(Token)match(input,KW_ROW,FOLLOW_KW_ROW_in_window_frame_start_boundary1722); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_ROW.add(KW_ROW125);

					// AST REWRITE
					// elements: KW_CURRENT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 256:21: -> ^( KW_CURRENT )
					{
						// Expr.g:256:24: ^( KW_CURRENT )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_KW_CURRENT.nextNode(), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// Expr.g:257:2: Number KW_PRECEDING
					{
					Number126=(Token)match(input,Number,FOLLOW_Number_in_window_frame_start_boundary1734); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_Number.add(Number126);

					KW_PRECEDING127=(Token)match(input,KW_PRECEDING,FOLLOW_KW_PRECEDING_in_window_frame_start_boundary1736); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_PRECEDING.add(KW_PRECEDING127);

					// AST REWRITE
					// elements: KW_PRECEDING, Number
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 257:22: -> ^( KW_PRECEDING Number )
					{
						// Expr.g:257:25: ^( KW_PRECEDING Number )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_KW_PRECEDING.nextNode(), root_1);
						adaptor.addChild(root_1, stream_Number.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "window_frame_start_boundary"


	public static class window_frame_boundary_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "window_frame_boundary"
	// Expr.g:260:1: window_frame_boundary : ( KW_UNBOUNDED (r= KW_PRECEDING |r= KW_FOLLOWING ) -> ^( $r KW_UNBOUNDED ) | KW_CURRENT KW_ROW -> ^( KW_CURRENT ) | Number (d= KW_PRECEDING |d= KW_FOLLOWING ) -> ^( $d Number ) );
	public final SQLParser_Expr.window_frame_boundary_return window_frame_boundary() throws RecognitionException {
		SQLParser_Expr.window_frame_boundary_return retval = new SQLParser_Expr.window_frame_boundary_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token r=null;
		Token d=null;
		Token KW_UNBOUNDED128=null;
		Token KW_CURRENT129=null;
		Token KW_ROW130=null;
		Token Number131=null;

		CommonTree r_tree=null;
		CommonTree d_tree=null;
		CommonTree KW_UNBOUNDED128_tree=null;
		CommonTree KW_CURRENT129_tree=null;
		CommonTree KW_ROW130_tree=null;
		CommonTree Number131_tree=null;
		RewriteRuleTokenStream stream_KW_PRECEDING=new RewriteRuleTokenStream(adaptor,"token KW_PRECEDING");
		RewriteRuleTokenStream stream_Number=new RewriteRuleTokenStream(adaptor,"token Number");
		RewriteRuleTokenStream stream_KW_FOLLOWING=new RewriteRuleTokenStream(adaptor,"token KW_FOLLOWING");
		RewriteRuleTokenStream stream_KW_CURRENT=new RewriteRuleTokenStream(adaptor,"token KW_CURRENT");
		RewriteRuleTokenStream stream_KW_ROW=new RewriteRuleTokenStream(adaptor,"token KW_ROW");
		RewriteRuleTokenStream stream_KW_UNBOUNDED=new RewriteRuleTokenStream(adaptor,"token KW_UNBOUNDED");

		try {
			// Expr.g:261:2: ( KW_UNBOUNDED (r= KW_PRECEDING |r= KW_FOLLOWING ) -> ^( $r KW_UNBOUNDED ) | KW_CURRENT KW_ROW -> ^( KW_CURRENT ) | Number (d= KW_PRECEDING |d= KW_FOLLOWING ) -> ^( $d Number ) )
			int alt36=3;
			switch ( input.LA(1) ) {
			case KW_UNBOUNDED:
				{
				alt36=1;
				}
				break;
			case KW_CURRENT:
				{
				alt36=2;
				}
				break;
			case Number:
				{
				alt36=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 36, 0, input);
				throw nvae;
			}
			switch (alt36) {
				case 1 :
					// Expr.g:262:2: KW_UNBOUNDED (r= KW_PRECEDING |r= KW_FOLLOWING )
					{
					KW_UNBOUNDED128=(Token)match(input,KW_UNBOUNDED,FOLLOW_KW_UNBOUNDED_in_window_frame_boundary1756); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_UNBOUNDED.add(KW_UNBOUNDED128);

					// Expr.g:262:15: (r= KW_PRECEDING |r= KW_FOLLOWING )
					int alt34=2;
					int LA34_0 = input.LA(1);
					if ( (LA34_0==KW_PRECEDING) ) {
						alt34=1;
					}
					else if ( (LA34_0==KW_FOLLOWING) ) {
						alt34=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 34, 0, input);
						throw nvae;
					}

					switch (alt34) {
						case 1 :
							// Expr.g:262:16: r= KW_PRECEDING
							{
							r=(Token)match(input,KW_PRECEDING,FOLLOW_KW_PRECEDING_in_window_frame_boundary1761); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_KW_PRECEDING.add(r);

							}
							break;
						case 2 :
							// Expr.g:262:31: r= KW_FOLLOWING
							{
							r=(Token)match(input,KW_FOLLOWING,FOLLOW_KW_FOLLOWING_in_window_frame_boundary1765); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_KW_FOLLOWING.add(r);

							}
							break;

					}

					// AST REWRITE
					// elements: KW_UNBOUNDED, r
					// token labels: r
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleTokenStream stream_r=new RewriteRuleTokenStream(adaptor,"token r",r);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 262:48: -> ^( $r KW_UNBOUNDED )
					{
						// Expr.g:262:51: ^( $r KW_UNBOUNDED )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_r.nextNode(), root_1);
						adaptor.addChild(root_1, stream_KW_UNBOUNDED.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// Expr.g:263:2: KW_CURRENT KW_ROW
					{
					KW_CURRENT129=(Token)match(input,KW_CURRENT,FOLLOW_KW_CURRENT_in_window_frame_boundary1782); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_CURRENT.add(KW_CURRENT129);

					KW_ROW130=(Token)match(input,KW_ROW,FOLLOW_KW_ROW_in_window_frame_boundary1784); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_ROW.add(KW_ROW130);

					// AST REWRITE
					// elements: KW_CURRENT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 263:21: -> ^( KW_CURRENT )
					{
						// Expr.g:263:24: ^( KW_CURRENT )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_KW_CURRENT.nextNode(), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// Expr.g:264:2: Number (d= KW_PRECEDING |d= KW_FOLLOWING )
					{
					Number131=(Token)match(input,Number,FOLLOW_Number_in_window_frame_boundary1796); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_Number.add(Number131);

					// Expr.g:264:9: (d= KW_PRECEDING |d= KW_FOLLOWING )
					int alt35=2;
					int LA35_0 = input.LA(1);
					if ( (LA35_0==KW_PRECEDING) ) {
						alt35=1;
					}
					else if ( (LA35_0==KW_FOLLOWING) ) {
						alt35=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 35, 0, input);
						throw nvae;
					}

					switch (alt35) {
						case 1 :
							// Expr.g:264:10: d= KW_PRECEDING
							{
							d=(Token)match(input,KW_PRECEDING,FOLLOW_KW_PRECEDING_in_window_frame_boundary1801); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_KW_PRECEDING.add(d);

							}
							break;
						case 2 :
							// Expr.g:264:27: d= KW_FOLLOWING
							{
							d=(Token)match(input,KW_FOLLOWING,FOLLOW_KW_FOLLOWING_in_window_frame_boundary1807); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_KW_FOLLOWING.add(d);

							}
							break;

					}

					// AST REWRITE
					// elements: Number, d
					// token labels: d
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleTokenStream stream_d=new RewriteRuleTokenStream(adaptor,"token d",d);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 264:44: -> ^( $d Number )
					{
						// Expr.g:264:47: ^( $d Number )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_d.nextNode(), root_1);
						adaptor.addChild(root_1, stream_Number.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "window_frame_boundary"


	public static class functionName_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "functionName"
	// Expr.g:268:1: functionName : identifier ;
	public final SQLParser_Expr.functionName_return functionName() throws RecognitionException {
		SQLParser_Expr.functionName_return retval = new SQLParser_Expr.functionName_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope identifier132 =null;


		try {
			// Expr.g:269:5: ( identifier )
			// Expr.g:270:5: identifier
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_identifier_in_functionName1844);
			identifier132=identifier();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier132.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "functionName"


	public static class castExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "castExpression"
	// Expr.g:273:1: castExpression : KW_CAST LPAREN expression KW_AS primitiveType RPAREN -> ^( TOK_FUNCTION primitiveType expression ) ;
	public final SQLParser_Expr.castExpression_return castExpression() throws RecognitionException {
		SQLParser_Expr.castExpression_return retval = new SQLParser_Expr.castExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_CAST133=null;
		Token LPAREN134=null;
		Token KW_AS136=null;
		Token RPAREN138=null;
		ParserRuleReturnScope expression135 =null;
		ParserRuleReturnScope primitiveType137 =null;

		CommonTree KW_CAST133_tree=null;
		CommonTree LPAREN134_tree=null;
		CommonTree KW_AS136_tree=null;
		CommonTree RPAREN138_tree=null;
		RewriteRuleTokenStream stream_KW_AS=new RewriteRuleTokenStream(adaptor,"token KW_AS");
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_KW_CAST=new RewriteRuleTokenStream(adaptor,"token KW_CAST");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
		RewriteRuleSubtreeStream stream_primitiveType=new RewriteRuleSubtreeStream(adaptor,"rule primitiveType");

		try {
			// Expr.g:274:5: ( KW_CAST LPAREN expression KW_AS primitiveType RPAREN -> ^( TOK_FUNCTION primitiveType expression ) )
			// Expr.g:275:5: KW_CAST LPAREN expression KW_AS primitiveType RPAREN
			{
			KW_CAST133=(Token)match(input,KW_CAST,FOLLOW_KW_CAST_in_castExpression1865); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_KW_CAST.add(KW_CAST133);

			LPAREN134=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_castExpression1871); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN134);

			pushFollow(FOLLOW_expression_in_castExpression1883);
			expression135=expression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_expression.add(expression135.getTree());
			KW_AS136=(Token)match(input,KW_AS,FOLLOW_KW_AS_in_castExpression1895); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_KW_AS.add(KW_AS136);

			pushFollow(FOLLOW_primitiveType_in_castExpression1907);
			primitiveType137=primitiveType();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_primitiveType.add(primitiveType137.getTree());
			RPAREN138=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_castExpression1913); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN138);

			// AST REWRITE
			// elements: expression, primitiveType
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 280:12: -> ^( TOK_FUNCTION primitiveType expression )
			{
				// Expr.g:280:15: ^( TOK_FUNCTION primitiveType expression )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_FUNCTION, "TOK_FUNCTION"), root_1);
				adaptor.addChild(root_1, stream_primitiveType.nextTree());
				adaptor.addChild(root_1, stream_expression.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "castExpression"


	public static class caseExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "caseExpression"
	// Expr.g:283:1: caseExpression : KW_CASE expression ( KW_WHEN expression KW_THEN expression )+ ( KW_ELSE expression )? KW_END -> ^( TOK_FUNCTION KW_CASE ( expression )* ) ;
	public final SQLParser_Expr.caseExpression_return caseExpression() throws RecognitionException {
		SQLParser_Expr.caseExpression_return retval = new SQLParser_Expr.caseExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_CASE139=null;
		Token KW_WHEN141=null;
		Token KW_THEN143=null;
		Token KW_ELSE145=null;
		Token KW_END147=null;
		ParserRuleReturnScope expression140 =null;
		ParserRuleReturnScope expression142 =null;
		ParserRuleReturnScope expression144 =null;
		ParserRuleReturnScope expression146 =null;

		CommonTree KW_CASE139_tree=null;
		CommonTree KW_WHEN141_tree=null;
		CommonTree KW_THEN143_tree=null;
		CommonTree KW_ELSE145_tree=null;
		CommonTree KW_END147_tree=null;
		RewriteRuleTokenStream stream_KW_THEN=new RewriteRuleTokenStream(adaptor,"token KW_THEN");
		RewriteRuleTokenStream stream_KW_CASE=new RewriteRuleTokenStream(adaptor,"token KW_CASE");
		RewriteRuleTokenStream stream_KW_WHEN=new RewriteRuleTokenStream(adaptor,"token KW_WHEN");
		RewriteRuleTokenStream stream_KW_END=new RewriteRuleTokenStream(adaptor,"token KW_END");
		RewriteRuleTokenStream stream_KW_ELSE=new RewriteRuleTokenStream(adaptor,"token KW_ELSE");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");

		try {
			// Expr.g:284:5: ( KW_CASE expression ( KW_WHEN expression KW_THEN expression )+ ( KW_ELSE expression )? KW_END -> ^( TOK_FUNCTION KW_CASE ( expression )* ) )
			// Expr.g:285:5: KW_CASE expression ( KW_WHEN expression KW_THEN expression )+ ( KW_ELSE expression )? KW_END
			{
			KW_CASE139=(Token)match(input,KW_CASE,FOLLOW_KW_CASE_in_caseExpression1944); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_KW_CASE.add(KW_CASE139);

			pushFollow(FOLLOW_expression_in_caseExpression1946);
			expression140=expression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_expression.add(expression140.getTree());
			// Expr.g:286:5: ( KW_WHEN expression KW_THEN expression )+
			int cnt37=0;
			loop37:
			while (true) {
				int alt37=2;
				int LA37_0 = input.LA(1);
				if ( (LA37_0==KW_WHEN) ) {
					alt37=1;
				}

				switch (alt37) {
				case 1 :
					// Expr.g:286:6: KW_WHEN expression KW_THEN expression
					{
					KW_WHEN141=(Token)match(input,KW_WHEN,FOLLOW_KW_WHEN_in_caseExpression1953); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_WHEN.add(KW_WHEN141);

					pushFollow(FOLLOW_expression_in_caseExpression1955);
					expression142=expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expression.add(expression142.getTree());
					KW_THEN143=(Token)match(input,KW_THEN,FOLLOW_KW_THEN_in_caseExpression1957); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_THEN.add(KW_THEN143);

					pushFollow(FOLLOW_expression_in_caseExpression1959);
					expression144=expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expression.add(expression144.getTree());
					}
					break;

				default :
					if ( cnt37 >= 1 ) break loop37;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(37, input);
					throw eee;
				}
				cnt37++;
			}

			// Expr.g:287:5: ( KW_ELSE expression )?
			int alt38=2;
			int LA38_0 = input.LA(1);
			if ( (LA38_0==KW_ELSE) ) {
				alt38=1;
			}
			switch (alt38) {
				case 1 :
					// Expr.g:287:6: KW_ELSE expression
					{
					KW_ELSE145=(Token)match(input,KW_ELSE,FOLLOW_KW_ELSE_in_caseExpression1968); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_ELSE.add(KW_ELSE145);

					pushFollow(FOLLOW_expression_in_caseExpression1970);
					expression146=expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expression.add(expression146.getTree());
					}
					break;

			}

			KW_END147=(Token)match(input,KW_END,FOLLOW_KW_END_in_caseExpression1978); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_KW_END.add(KW_END147);

			// AST REWRITE
			// elements: KW_CASE, expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 288:12: -> ^( TOK_FUNCTION KW_CASE ( expression )* )
			{
				// Expr.g:288:15: ^( TOK_FUNCTION KW_CASE ( expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_FUNCTION, "TOK_FUNCTION"), root_1);
				adaptor.addChild(root_1, stream_KW_CASE.nextNode());
				// Expr.g:288:38: ( expression )*
				while ( stream_expression.hasNext() ) {
					adaptor.addChild(root_1, stream_expression.nextTree());
				}
				stream_expression.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "caseExpression"


	public static class whenExpression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "whenExpression"
	// Expr.g:291:1: whenExpression : KW_CASE ( KW_WHEN expression KW_THEN expression )+ ( KW_ELSE expression )? KW_END -> ^( TOK_FUNCTION KW_WHEN ( expression )* ) ;
	public final SQLParser_Expr.whenExpression_return whenExpression() throws RecognitionException {
		SQLParser_Expr.whenExpression_return retval = new SQLParser_Expr.whenExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_CASE148=null;
		Token KW_WHEN149=null;
		Token KW_THEN151=null;
		Token KW_ELSE153=null;
		Token KW_END155=null;
		ParserRuleReturnScope expression150 =null;
		ParserRuleReturnScope expression152 =null;
		ParserRuleReturnScope expression154 =null;

		CommonTree KW_CASE148_tree=null;
		CommonTree KW_WHEN149_tree=null;
		CommonTree KW_THEN151_tree=null;
		CommonTree KW_ELSE153_tree=null;
		CommonTree KW_END155_tree=null;
		RewriteRuleTokenStream stream_KW_THEN=new RewriteRuleTokenStream(adaptor,"token KW_THEN");
		RewriteRuleTokenStream stream_KW_CASE=new RewriteRuleTokenStream(adaptor,"token KW_CASE");
		RewriteRuleTokenStream stream_KW_WHEN=new RewriteRuleTokenStream(adaptor,"token KW_WHEN");
		RewriteRuleTokenStream stream_KW_END=new RewriteRuleTokenStream(adaptor,"token KW_END");
		RewriteRuleTokenStream stream_KW_ELSE=new RewriteRuleTokenStream(adaptor,"token KW_ELSE");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");

		try {
			// Expr.g:292:5: ( KW_CASE ( KW_WHEN expression KW_THEN expression )+ ( KW_ELSE expression )? KW_END -> ^( TOK_FUNCTION KW_WHEN ( expression )* ) )
			// Expr.g:293:5: KW_CASE ( KW_WHEN expression KW_THEN expression )+ ( KW_ELSE expression )? KW_END
			{
			KW_CASE148=(Token)match(input,KW_CASE,FOLLOW_KW_CASE_in_whenExpression2010); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_KW_CASE.add(KW_CASE148);

			// Expr.g:294:6: ( KW_WHEN expression KW_THEN expression )+
			int cnt39=0;
			loop39:
			while (true) {
				int alt39=2;
				int LA39_0 = input.LA(1);
				if ( (LA39_0==KW_WHEN) ) {
					alt39=1;
				}

				switch (alt39) {
				case 1 :
					// Expr.g:294:8: KW_WHEN expression KW_THEN expression
					{
					KW_WHEN149=(Token)match(input,KW_WHEN,FOLLOW_KW_WHEN_in_whenExpression2019); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_WHEN.add(KW_WHEN149);

					pushFollow(FOLLOW_expression_in_whenExpression2021);
					expression150=expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expression.add(expression150.getTree());
					KW_THEN151=(Token)match(input,KW_THEN,FOLLOW_KW_THEN_in_whenExpression2023); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_THEN.add(KW_THEN151);

					pushFollow(FOLLOW_expression_in_whenExpression2025);
					expression152=expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expression.add(expression152.getTree());
					}
					break;

				default :
					if ( cnt39 >= 1 ) break loop39;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(39, input);
					throw eee;
				}
				cnt39++;
			}

			// Expr.g:295:5: ( KW_ELSE expression )?
			int alt40=2;
			int LA40_0 = input.LA(1);
			if ( (LA40_0==KW_ELSE) ) {
				alt40=1;
			}
			switch (alt40) {
				case 1 :
					// Expr.g:295:6: KW_ELSE expression
					{
					KW_ELSE153=(Token)match(input,KW_ELSE,FOLLOW_KW_ELSE_in_whenExpression2034); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_ELSE.add(KW_ELSE153);

					pushFollow(FOLLOW_expression_in_whenExpression2036);
					expression154=expression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expression.add(expression154.getTree());
					}
					break;

			}

			KW_END155=(Token)match(input,KW_END,FOLLOW_KW_END_in_whenExpression2044); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_KW_END.add(KW_END155);

			// AST REWRITE
			// elements: KW_WHEN, expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 296:12: -> ^( TOK_FUNCTION KW_WHEN ( expression )* )
			{
				// Expr.g:296:15: ^( TOK_FUNCTION KW_WHEN ( expression )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_FUNCTION, "TOK_FUNCTION"), root_1);
				adaptor.addChild(root_1, stream_KW_WHEN.nextNode());
				// Expr.g:296:38: ( expression )*
				while ( stream_expression.hasNext() ) {
					adaptor.addChild(root_1, stream_expression.nextTree());
				}
				stream_expression.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "whenExpression"


	public static class constant_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "constant"
	// Expr.g:299:1: constant : ( Number | dateLiteral | StringLiteral | stringLiteralSequence | DecimalLiteral | booleanValue );
	public final SQLParser_Expr.constant_return constant() throws RecognitionException {
		SQLParser_Expr.constant_return retval = new SQLParser_Expr.constant_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token Number156=null;
		Token StringLiteral158=null;
		Token DecimalLiteral160=null;
		ParserRuleReturnScope dateLiteral157 =null;
		ParserRuleReturnScope stringLiteralSequence159 =null;
		ParserRuleReturnScope booleanValue161 =null;

		CommonTree Number156_tree=null;
		CommonTree StringLiteral158_tree=null;
		CommonTree DecimalLiteral160_tree=null;

		try {
			// Expr.g:300:5: ( Number | dateLiteral | StringLiteral | stringLiteralSequence | DecimalLiteral | booleanValue )
			int alt41=6;
			alt41 = dfa41.predict(input);
			switch (alt41) {
				case 1 :
					// Expr.g:301:5: Number
					{
					root_0 = (CommonTree)adaptor.nil();


					Number156=(Token)match(input,Number,FOLLOW_Number_in_constant2076); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					Number156_tree = (CommonTree)adaptor.create(Number156);
					adaptor.addChild(root_0, Number156_tree);
					}

					}
					break;
				case 2 :
					// Expr.g:302:7: dateLiteral
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_dateLiteral_in_constant2084);
					dateLiteral157=dateLiteral();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, dateLiteral157.getTree());

					}
					break;
				case 3 :
					// Expr.g:303:7: StringLiteral
					{
					root_0 = (CommonTree)adaptor.nil();


					StringLiteral158=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_constant2092); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					StringLiteral158_tree = (CommonTree)adaptor.create(StringLiteral158);
					adaptor.addChild(root_0, StringLiteral158_tree);
					}

					}
					break;
				case 4 :
					// Expr.g:304:7: stringLiteralSequence
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_stringLiteralSequence_in_constant2100);
					stringLiteralSequence159=stringLiteralSequence();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, stringLiteralSequence159.getTree());

					}
					break;
				case 5 :
					// Expr.g:305:7: DecimalLiteral
					{
					root_0 = (CommonTree)adaptor.nil();


					DecimalLiteral160=(Token)match(input,DecimalLiteral,FOLLOW_DecimalLiteral_in_constant2108); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DecimalLiteral160_tree = (CommonTree)adaptor.create(DecimalLiteral160);
					adaptor.addChild(root_0, DecimalLiteral160_tree);
					}

					}
					break;
				case 6 :
					// Expr.g:306:7: booleanValue
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_booleanValue_in_constant2116);
					booleanValue161=booleanValue();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, booleanValue161.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "constant"


	public static class stringLiteralSequence_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "stringLiteralSequence"
	// Expr.g:309:1: stringLiteralSequence : StringLiteral ( StringLiteral )+ -> ^( TOK_STRINGLITERALSEQUENCE StringLiteral ( StringLiteral )+ ) ;
	public final SQLParser_Expr.stringLiteralSequence_return stringLiteralSequence() throws RecognitionException {
		SQLParser_Expr.stringLiteralSequence_return retval = new SQLParser_Expr.stringLiteralSequence_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token StringLiteral162=null;
		Token StringLiteral163=null;

		CommonTree StringLiteral162_tree=null;
		CommonTree StringLiteral163_tree=null;
		RewriteRuleTokenStream stream_StringLiteral=new RewriteRuleTokenStream(adaptor,"token StringLiteral");

		try {
			// Expr.g:310:5: ( StringLiteral ( StringLiteral )+ -> ^( TOK_STRINGLITERALSEQUENCE StringLiteral ( StringLiteral )+ ) )
			// Expr.g:311:5: StringLiteral ( StringLiteral )+
			{
			StringLiteral162=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_stringLiteralSequence2137); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_StringLiteral.add(StringLiteral162);

			// Expr.g:311:19: ( StringLiteral )+
			int cnt42=0;
			loop42:
			while (true) {
				int alt42=2;
				int LA42_0 = input.LA(1);
				if ( (LA42_0==StringLiteral) ) {
					alt42=1;
				}

				switch (alt42) {
				case 1 :
					// Expr.g:311:19: StringLiteral
					{
					StringLiteral163=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_stringLiteralSequence2139); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_StringLiteral.add(StringLiteral163);

					}
					break;

				default :
					if ( cnt42 >= 1 ) break loop42;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(42, input);
					throw eee;
				}
				cnt42++;
			}

			// AST REWRITE
			// elements: StringLiteral, StringLiteral
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 311:34: -> ^( TOK_STRINGLITERALSEQUENCE StringLiteral ( StringLiteral )+ )
			{
				// Expr.g:311:37: ^( TOK_STRINGLITERALSEQUENCE StringLiteral ( StringLiteral )+ )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_STRINGLITERALSEQUENCE, "TOK_STRINGLITERALSEQUENCE"), root_1);
				adaptor.addChild(root_1, stream_StringLiteral.nextNode());
				if ( !(stream_StringLiteral.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_StringLiteral.hasNext() ) {
					adaptor.addChild(root_1, stream_StringLiteral.nextNode());
				}
				stream_StringLiteral.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "stringLiteralSequence"


	public static class dateLiteral_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "dateLiteral"
	// Expr.g:314:1: dateLiteral : KW_DATE StringLiteral -> ^( TOK_DATELITERAL StringLiteral ) ;
	public final SQLParser_Expr.dateLiteral_return dateLiteral() throws RecognitionException {
		SQLParser_Expr.dateLiteral_return retval = new SQLParser_Expr.dateLiteral_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_DATE164=null;
		Token StringLiteral165=null;

		CommonTree KW_DATE164_tree=null;
		CommonTree StringLiteral165_tree=null;
		RewriteRuleTokenStream stream_StringLiteral=new RewriteRuleTokenStream(adaptor,"token StringLiteral");
		RewriteRuleTokenStream stream_KW_DATE=new RewriteRuleTokenStream(adaptor,"token KW_DATE");

		try {
			// Expr.g:315:5: ( KW_DATE StringLiteral -> ^( TOK_DATELITERAL StringLiteral ) )
			// Expr.g:316:5: KW_DATE StringLiteral
			{
			KW_DATE164=(Token)match(input,KW_DATE,FOLLOW_KW_DATE_in_dateLiteral2172); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_KW_DATE.add(KW_DATE164);

			StringLiteral165=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_dateLiteral2174); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_StringLiteral.add(StringLiteral165);

			// AST REWRITE
			// elements: StringLiteral
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 318:6: -> ^( TOK_DATELITERAL StringLiteral )
			{
				// Expr.g:318:8: ^( TOK_DATELITERAL StringLiteral )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TOK_DATELITERAL, "TOK_DATELITERAL"), root_1);
				adaptor.addChild(root_1, stream_StringLiteral.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dateLiteral"


	public static class primitiveType_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "primitiveType"
	// Expr.g:321:1: primitiveType : (| KW_SMALLINT -> TOK_SMALLINT | KW_BYTEINT -> TOK_BYTEINT | ( KW_INTEGER | KW_INT ) -> TOK_INTEGER | KW_BIGINT -> TOK_BIGINT | KW_FLOAT -> TOK_FLOAT | KW_DECIMAL ( LPAREN Number COMMA Number RPAREN )? -> TOK_DECIMAL ( Number )* | KW_DATE -> TOK_DATE | KW_TIME -> TOK_TIME | KW_TIMESTAMP -> TOK_TIMESTAMP | KW_CHAR -> TOK_CHAR | KW_BINARY -> TOK_BINARY );
	public final SQLParser_Expr.primitiveType_return primitiveType() throws RecognitionException {
		SQLParser_Expr.primitiveType_return retval = new SQLParser_Expr.primitiveType_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token KW_SMALLINT166=null;
		Token KW_BYTEINT167=null;
		Token KW_INTEGER168=null;
		Token KW_INT169=null;
		Token KW_BIGINT170=null;
		Token KW_FLOAT171=null;
		Token KW_DECIMAL172=null;
		Token LPAREN173=null;
		Token Number174=null;
		Token COMMA175=null;
		Token Number176=null;
		Token RPAREN177=null;
		Token KW_DATE178=null;
		Token KW_TIME179=null;
		Token KW_TIMESTAMP180=null;
		Token KW_CHAR181=null;
		Token KW_BINARY182=null;

		CommonTree KW_SMALLINT166_tree=null;
		CommonTree KW_BYTEINT167_tree=null;
		CommonTree KW_INTEGER168_tree=null;
		CommonTree KW_INT169_tree=null;
		CommonTree KW_BIGINT170_tree=null;
		CommonTree KW_FLOAT171_tree=null;
		CommonTree KW_DECIMAL172_tree=null;
		CommonTree LPAREN173_tree=null;
		CommonTree Number174_tree=null;
		CommonTree COMMA175_tree=null;
		CommonTree Number176_tree=null;
		CommonTree RPAREN177_tree=null;
		CommonTree KW_DATE178_tree=null;
		CommonTree KW_TIME179_tree=null;
		CommonTree KW_TIMESTAMP180_tree=null;
		CommonTree KW_CHAR181_tree=null;
		CommonTree KW_BINARY182_tree=null;
		RewriteRuleTokenStream stream_KW_TIMESTAMP=new RewriteRuleTokenStream(adaptor,"token KW_TIMESTAMP");
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_KW_DATE=new RewriteRuleTokenStream(adaptor,"token KW_DATE");
		RewriteRuleTokenStream stream_KW_TIME=new RewriteRuleTokenStream(adaptor,"token KW_TIME");
		RewriteRuleTokenStream stream_Number=new RewriteRuleTokenStream(adaptor,"token Number");
		RewriteRuleTokenStream stream_KW_CHAR=new RewriteRuleTokenStream(adaptor,"token KW_CHAR");
		RewriteRuleTokenStream stream_KW_BINARY=new RewriteRuleTokenStream(adaptor,"token KW_BINARY");
		RewriteRuleTokenStream stream_KW_SMALLINT=new RewriteRuleTokenStream(adaptor,"token KW_SMALLINT");
		RewriteRuleTokenStream stream_KW_INT=new RewriteRuleTokenStream(adaptor,"token KW_INT");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_KW_DECIMAL=new RewriteRuleTokenStream(adaptor,"token KW_DECIMAL");
		RewriteRuleTokenStream stream_KW_BYTEINT=new RewriteRuleTokenStream(adaptor,"token KW_BYTEINT");
		RewriteRuleTokenStream stream_KW_INTEGER=new RewriteRuleTokenStream(adaptor,"token KW_INTEGER");
		RewriteRuleTokenStream stream_KW_FLOAT=new RewriteRuleTokenStream(adaptor,"token KW_FLOAT");
		RewriteRuleTokenStream stream_KW_BIGINT=new RewriteRuleTokenStream(adaptor,"token KW_BIGINT");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");

		try {
			// Expr.g:322:5: (| KW_SMALLINT -> TOK_SMALLINT | KW_BYTEINT -> TOK_BYTEINT | ( KW_INTEGER | KW_INT ) -> TOK_INTEGER | KW_BIGINT -> TOK_BIGINT | KW_FLOAT -> TOK_FLOAT | KW_DECIMAL ( LPAREN Number COMMA Number RPAREN )? -> TOK_DECIMAL ( Number )* | KW_DATE -> TOK_DATE | KW_TIME -> TOK_TIME | KW_TIMESTAMP -> TOK_TIMESTAMP | KW_CHAR -> TOK_CHAR | KW_BINARY -> TOK_BINARY )
			int alt45=12;
			switch ( input.LA(1) ) {
			case RPAREN:
				{
				alt45=1;
				}
				break;
			case KW_SMALLINT:
				{
				alt45=2;
				}
				break;
			case KW_BYTEINT:
				{
				alt45=3;
				}
				break;
			case KW_INT:
			case KW_INTEGER:
				{
				alt45=4;
				}
				break;
			case KW_BIGINT:
				{
				alt45=5;
				}
				break;
			case KW_FLOAT:
				{
				alt45=6;
				}
				break;
			case KW_DECIMAL:
				{
				alt45=7;
				}
				break;
			case KW_DATE:
				{
				alt45=8;
				}
				break;
			case KW_TIME:
				{
				alt45=9;
				}
				break;
			case KW_TIMESTAMP:
				{
				alt45=10;
				}
				break;
			case KW_CHAR:
				{
				alt45=11;
				}
				break;
			case KW_BINARY:
				{
				alt45=12;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 45, 0, input);
				throw nvae;
			}
			switch (alt45) {
				case 1 :
					// Expr.g:323:5: 
					{
					root_0 = (CommonTree)adaptor.nil();


					}
					break;
				case 2 :
					// Expr.g:323:7: KW_SMALLINT
					{
					KW_SMALLINT166=(Token)match(input,KW_SMALLINT,FOLLOW_KW_SMALLINT_in_primitiveType2216); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_SMALLINT.add(KW_SMALLINT166);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 323:20: -> TOK_SMALLINT
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_SMALLINT, "TOK_SMALLINT"));
					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// Expr.g:324:7: KW_BYTEINT
					{
					KW_BYTEINT167=(Token)match(input,KW_BYTEINT,FOLLOW_KW_BYTEINT_in_primitiveType2229); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_BYTEINT.add(KW_BYTEINT167);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 324:19: -> TOK_BYTEINT
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_BYTEINT, "TOK_BYTEINT"));
					}


					retval.tree = root_0;
					}

					}
					break;
				case 4 :
					// Expr.g:325:7: ( KW_INTEGER | KW_INT )
					{
					// Expr.g:325:7: ( KW_INTEGER | KW_INT )
					int alt43=2;
					int LA43_0 = input.LA(1);
					if ( (LA43_0==KW_INTEGER) ) {
						alt43=1;
					}
					else if ( (LA43_0==KW_INT) ) {
						alt43=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 43, 0, input);
						throw nvae;
					}

					switch (alt43) {
						case 1 :
							// Expr.g:325:8: KW_INTEGER
							{
							KW_INTEGER168=(Token)match(input,KW_INTEGER,FOLLOW_KW_INTEGER_in_primitiveType2243); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_KW_INTEGER.add(KW_INTEGER168);

							}
							break;
						case 2 :
							// Expr.g:325:19: KW_INT
							{
							KW_INT169=(Token)match(input,KW_INT,FOLLOW_KW_INT_in_primitiveType2245); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_KW_INT.add(KW_INT169);

							}
							break;

					}

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 325:34: -> TOK_INTEGER
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_INTEGER, "TOK_INTEGER"));
					}


					retval.tree = root_0;
					}

					}
					break;
				case 5 :
					// Expr.g:326:7: KW_BIGINT
					{
					KW_BIGINT170=(Token)match(input,KW_BIGINT,FOLLOW_KW_BIGINT_in_primitiveType2265); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_BIGINT.add(KW_BIGINT170);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 326:19: -> TOK_BIGINT
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_BIGINT, "TOK_BIGINT"));
					}


					retval.tree = root_0;
					}

					}
					break;
				case 6 :
					// Expr.g:327:7: KW_FLOAT
					{
					KW_FLOAT171=(Token)match(input,KW_FLOAT,FOLLOW_KW_FLOAT_in_primitiveType2279); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_FLOAT.add(KW_FLOAT171);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 327:25: -> TOK_FLOAT
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_FLOAT, "TOK_FLOAT"));
					}


					retval.tree = root_0;
					}

					}
					break;
				case 7 :
					// Expr.g:328:7: KW_DECIMAL ( LPAREN Number COMMA Number RPAREN )?
					{
					KW_DECIMAL172=(Token)match(input,KW_DECIMAL,FOLLOW_KW_DECIMAL_in_primitiveType2300); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_DECIMAL.add(KW_DECIMAL172);

					// Expr.g:328:18: ( LPAREN Number COMMA Number RPAREN )?
					int alt44=2;
					int LA44_0 = input.LA(1);
					if ( (LA44_0==LPAREN) ) {
						alt44=1;
					}
					switch (alt44) {
						case 1 :
							// Expr.g:328:19: LPAREN Number COMMA Number RPAREN
							{
							LPAREN173=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_primitiveType2303); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN173);

							Number174=(Token)match(input,Number,FOLLOW_Number_in_primitiveType2305); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_Number.add(Number174);

							COMMA175=(Token)match(input,COMMA,FOLLOW_COMMA_in_primitiveType2307); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_COMMA.add(COMMA175);

							Number176=(Token)match(input,Number,FOLLOW_Number_in_primitiveType2309); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_Number.add(Number176);

							RPAREN177=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_primitiveType2311); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN177);

							}
							break;

					}

					// AST REWRITE
					// elements: Number
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 328:55: -> TOK_DECIMAL ( Number )*
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_DECIMAL, "TOK_DECIMAL"));
						// Expr.g:328:70: ( Number )*
						while ( stream_Number.hasNext() ) {
							adaptor.addChild(root_0, stream_Number.nextNode());
						}
						stream_Number.reset();

					}


					retval.tree = root_0;
					}

					}
					break;
				case 8 :
					// Expr.g:329:7: KW_DATE
					{
					KW_DATE178=(Token)match(input,KW_DATE,FOLLOW_KW_DATE_in_primitiveType2328); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_DATE.add(KW_DATE178);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 329:25: -> TOK_DATE
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_DATE, "TOK_DATE"));
					}


					retval.tree = root_0;
					}

					}
					break;
				case 9 :
					// Expr.g:330:7: KW_TIME
					{
					KW_TIME179=(Token)match(input,KW_TIME,FOLLOW_KW_TIME_in_primitiveType2350); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_TIME.add(KW_TIME179);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 330:22: -> TOK_TIME
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_TIME, "TOK_TIME"));
					}


					retval.tree = root_0;
					}

					}
					break;
				case 10 :
					// Expr.g:331:7: KW_TIMESTAMP
					{
					KW_TIMESTAMP180=(Token)match(input,KW_TIMESTAMP,FOLLOW_KW_TIMESTAMP_in_primitiveType2369); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_TIMESTAMP.add(KW_TIMESTAMP180);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 331:25: -> TOK_TIMESTAMP
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_TIMESTAMP, "TOK_TIMESTAMP"));
					}


					retval.tree = root_0;
					}

					}
					break;
				case 11 :
					// Expr.g:332:7: KW_CHAR
					{
					KW_CHAR181=(Token)match(input,KW_CHAR,FOLLOW_KW_CHAR_in_primitiveType2386); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_CHAR.add(KW_CHAR181);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 332:23: -> TOK_CHAR
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_CHAR, "TOK_CHAR"));
					}


					retval.tree = root_0;
					}

					}
					break;
				case 12 :
					// Expr.g:333:7: KW_BINARY
					{
					KW_BINARY182=(Token)match(input,KW_BINARY,FOLLOW_KW_BINARY_in_primitiveType2406); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_KW_BINARY.add(KW_BINARY182);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 333:25: -> TOK_BINARY
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(TOK_BINARY, "TOK_BINARY"));
					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "primitiveType"


	public static class identifier_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "identifier"
	// Expr.g:336:1: identifier : Identifier ;
	public final SQLParser_Expr.identifier_return identifier() throws RecognitionException {
		SQLParser_Expr.identifier_return retval = new SQLParser_Expr.identifier_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token Identifier183=null;

		CommonTree Identifier183_tree=null;

		try {
			// Expr.g:337:5: ( Identifier )
			// Expr.g:338:5: Identifier
			{
			root_0 = (CommonTree)adaptor.nil();


			Identifier183=(Token)match(input,Identifier,FOLLOW_Identifier_in_identifier2439); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			Identifier183_tree = (CommonTree)adaptor.create(Identifier183);
			adaptor.addChild(root_0, Identifier183_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

			catch (RecognitionException e) {
				throw e;
			}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "identifier"

	// $ANTLR start synpred1_Expr
	public final void synpred1_Expr_fragment() throws RecognitionException {
		// Expr.g:233:14: ( COMMA )
		// Expr.g:233:15: COMMA
		{
		match(input,COMMA,FOLLOW_COMMA_in_synpred1_Expr1542); if (state.failed) return;

		}

	}
	// $ANTLR end synpred1_Expr

	// Delegated rules

	public final boolean synpred1_Expr() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred1_Expr_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}


	protected DFA1 dfa1 = new DFA1(this);
	protected DFA41 dfa41 = new DFA41(this);
	static final String DFA1_eotS =
		"\123\uffff";
	static final String DFA1_eofS =
		"\10\uffff\1\15\112\uffff";
	static final String DFA1_minS =
		"\1\16\7\uffff\1\4\1\uffff\1\16\110\uffff";
	static final String DFA1_maxS =
		"\1\u00be\7\uffff\1\u00f2\1\uffff\1\u00bf\110\uffff";
	static final String DFA1_acceptS =
		"\1\uffff\1\1\1\2\6\uffff\1\4\1\uffff\1\10\1\3\1\7\67\uffff\1\5\14\uffff"+
		"\1\6";
	static final String DFA1_specialS =
		"\123\uffff}>";
	static final String[] DFA1_transitionS = {
			"\1\2\7\uffff\1\10\16\uffff\1\12\1\uffff\1\11\7\uffff\1\2\24\uffff\1\2"+
			"\51\uffff\1\1\51\uffff\1\2\24\uffff\1\13\6\uffff\1\2\11\uffff\1\2",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\3\15\2\uffff\1\15\1\uffff\1\15\1\uffff\1\15\2\uffff\2\15\1\uffff\2"+
			"\15\1\uffff\1\15\2\uffff\1\15\1\uffff\2\15\1\uffff\1\15\14\uffff\2\15"+
			"\7\uffff\2\15\1\uffff\1\15\4\uffff\1\15\1\uffff\1\15\13\uffff\2\15\2"+
			"\uffff\2\15\3\uffff\1\15\1\uffff\1\15\1\uffff\1\15\4\uffff\5\15\14\uffff"+
			"\1\15\4\uffff\2\15\4\uffff\1\15\5\uffff\2\15\3\uffff\2\15\3\uffff\1\15"+
			"\12\uffff\1\15\11\uffff\1\15\7\uffff\2\15\4\uffff\2\15\1\14\1\15\1\uffff"+
			"\1\15\1\uffff\2\15\1\uffff\1\15\2\uffff\2\15\1\uffff\1\15\1\uffff\1\15"+
			"\7\uffff\1\15\53\uffff\2\15",
			"",
			"\1\105\7\uffff\1\105\16\uffff\1\105\1\uffff\1\105\7\uffff\1\105\24\uffff"+
			"\1\105\50\uffff\2\105\51\uffff\1\105\14\uffff\1\122\7\uffff\1\105\2\uffff"+
			"\1\105\3\uffff\2\105\10\uffff\2\105",
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
			"",
			"",
			"",
			"",
			"",
			""
	};

	static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
	static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
	static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
	static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
	static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
	static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
	static final short[][] DFA1_transition;

	static {
		int numStates = DFA1_transitionS.length;
		DFA1_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
		}
	}

	protected class DFA1 extends DFA {

		public DFA1(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 1;
			this.eot = DFA1_eot;
			this.eof = DFA1_eof;
			this.min = DFA1_min;
			this.max = DFA1_max;
			this.accept = DFA1_accept;
			this.special = DFA1_special;
			this.transition = DFA1_transition;
		}
		@Override
		public String getDescription() {
			return "34:1: atomExpression : ( KW_NULL -> TOK_NULL | constant | function | castExpression | caseExpression | whenExpression | tableOrColumn | LPAREN ! expression RPAREN !);";
		}
	}

	static final String DFA41_eotS =
		"\100\uffff";
	static final String DFA41_eofS =
		"\3\uffff\1\7\74\uffff";
	static final String DFA41_minS =
		"\1\16\2\uffff\1\4\74\uffff";
	static final String DFA41_maxS =
		"\1\u00be\2\uffff\1\u00f2\74\uffff";
	static final String DFA41_acceptS =
		"\1\uffff\1\1\1\2\1\uffff\1\5\1\6\1\uffff\1\3\67\uffff\1\4";
	static final String DFA41_specialS =
		"\100\uffff}>";
	static final String[] DFA41_transitionS = {
			"\1\4\40\uffff\1\2\24\uffff\1\5\123\uffff\1\5\33\uffff\1\1\11\uffff\1"+
			"\3",
			"",
			"",
			"\3\7\2\uffff\1\7\1\uffff\1\7\1\uffff\1\7\2\uffff\2\7\1\uffff\2\7\1\uffff"+
			"\1\7\2\uffff\1\7\1\uffff\2\7\1\uffff\1\7\14\uffff\2\7\7\uffff\2\7\1\uffff"+
			"\1\7\4\uffff\1\7\1\uffff\1\7\13\uffff\2\7\2\uffff\2\7\3\uffff\1\7\1\uffff"+
			"\1\7\1\uffff\1\7\4\uffff\5\7\14\uffff\1\7\4\uffff\2\7\4\uffff\1\7\5\uffff"+
			"\2\7\3\uffff\2\7\3\uffff\1\7\12\uffff\1\7\11\uffff\1\7\7\uffff\2\7\4"+
			"\uffff\2\7\1\uffff\1\7\1\uffff\1\7\1\uffff\2\7\1\uffff\1\7\2\uffff\2"+
			"\7\1\uffff\1\7\1\uffff\1\7\1\77\6\uffff\1\7\53\uffff\2\7",
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
			"",
			"",
			"",
			"",
			""
	};

	static final short[] DFA41_eot = DFA.unpackEncodedString(DFA41_eotS);
	static final short[] DFA41_eof = DFA.unpackEncodedString(DFA41_eofS);
	static final char[] DFA41_min = DFA.unpackEncodedStringToUnsignedChars(DFA41_minS);
	static final char[] DFA41_max = DFA.unpackEncodedStringToUnsignedChars(DFA41_maxS);
	static final short[] DFA41_accept = DFA.unpackEncodedString(DFA41_acceptS);
	static final short[] DFA41_special = DFA.unpackEncodedString(DFA41_specialS);
	static final short[][] DFA41_transition;

	static {
		int numStates = DFA41_transitionS.length;
		DFA41_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA41_transition[i] = DFA.unpackEncodedString(DFA41_transitionS[i]);
		}
	}

	protected class DFA41 extends DFA {

		public DFA41(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 41;
			this.eot = DFA41_eot;
			this.eof = DFA41_eof;
			this.min = DFA41_min;
			this.max = DFA41_max;
			this.accept = DFA41_accept;
			this.special = DFA41_special;
			this.transition = DFA41_transition;
		}
		@Override
		public String getDescription() {
			return "299:1: constant : ( Number | dateLiteral | StringLiteral | stringLiteralSequence | DecimalLiteral | booleanValue );";
		}
	}

	public static final BitSet FOLLOW_precedenceOrExpression_in_expression58 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_NULL_in_atomExpression79 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constant_in_atomExpression91 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_function_in_atomExpression99 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_castExpression_in_atomExpression107 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_caseExpression_in_atomExpression115 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_whenExpression_in_atomExpression123 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tableOrColumn_in_atomExpression131 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_atomExpression139 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_atomExpression142 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_atomExpression144 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_atomExpression_in_precedenceFieldExpression167 = new BitSet(new long[]{0x0000000000002002L,0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_LSQUARE_in_precedenceFieldExpression171 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_precedenceFieldExpression174 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_precedenceFieldExpression176 = new BitSet(new long[]{0x0000000000002002L,0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_DOT_in_precedenceFieldExpression183 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_identifier_in_precedenceFieldExpression186 = new BitSet(new long[]{0x0000000000002002L,0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_KW_NULL_in_nullCondition239 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_NOT_in_nullCondition253 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_KW_NULL_in_nullCondition255 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_precedenceUnaryOperator_in_precedenceUnaryPrefixExpression283 = new BitSet(new long[]{0x000080A000404000L,0x0000400000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_precedenceFieldExpression_in_precedenceUnaryPrefixExpression288 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_precedenceUnaryPrefixExpression_in_precedenceUnarySuffixExpression305 = new BitSet(new long[]{0x0000000000000002L,0x0000000020000000L});
	public static final BitSet FOLLOW_KW_IS_in_precedenceUnarySuffixExpression310 = new BitSet(new long[]{0x0000000000000000L,0x0000600000000000L});
	public static final BitSet FOLLOW_nullCondition_in_precedenceUnarySuffixExpression312 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BITWISEXOR_in_precedenceBitwiseXorOperator360 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_precedenceUnarySuffixExpression_in_precedenceBitwiseXorExpression381 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_precedenceBitwiseXorOperator_in_precedenceBitwiseXorExpression384 = new BitSet(new long[]{0x000080A000404000L,0x0000400000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_precedenceUnarySuffixExpression_in_precedenceBitwiseXorExpression387 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_precedenceBitwiseXorExpression_in_precedenceStarExpression444 = new BitSet(new long[]{0x0000000000000802L,0x0000000000000000L,0x2004000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_precedenceStarOperator_in_precedenceStarExpression447 = new BitSet(new long[]{0x000080A000404000L,0x0000400000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_precedenceBitwiseXorExpression_in_precedenceStarExpression450 = new BitSet(new long[]{0x0000000000000802L,0x0000000000000000L,0x2004000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_precedenceStarExpression_in_precedencePlusExpression499 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0021000000000000L});
	public static final BitSet FOLLOW_precedencePlusOperator_in_precedencePlusExpression502 = new BitSet(new long[]{0x000080A000404000L,0x0000400000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_precedenceStarExpression_in_precedencePlusExpression505 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0021000000000000L});
	public static final BitSet FOLLOW_AMPERSAND_in_precedenceAmpersandOperator529 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_precedencePlusExpression_in_precedenceAmpersandExpression550 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_precedenceAmpersandOperator_in_precedenceAmpersandExpression553 = new BitSet(new long[]{0x000080A000404000L,0x0000400000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_precedencePlusExpression_in_precedenceAmpersandExpression556 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_BITWISEOR_in_precedenceBitwiseOrOperator580 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_precedenceAmpersandExpression_in_precedenceBitwiseOrExpression601 = new BitSet(new long[]{0x0000000000000022L});
	public static final BitSet FOLLOW_precedenceBitwiseOrOperator_in_precedenceBitwiseOrExpression604 = new BitSet(new long[]{0x000080A000404000L,0x0000400000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_precedenceAmpersandExpression_in_precedenceBitwiseOrExpression607 = new BitSet(new long[]{0x0000000000000022L});
	public static final BitSet FOLLOW_precedenceEqualNegatableOperator_in_precedenceEqualOperator660 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EQUAL_in_precedenceEqualOperator664 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EQUAL_NS_in_precedenceEqualOperator668 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOTEQUAL_in_precedenceEqualOperator672 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LESSTHANOREQUALTO_in_precedenceEqualOperator676 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LESSTHAN_in_precedenceEqualOperator680 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GREATERTHANOREQUALTO_in_precedenceEqualOperator684 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GREATERTHAN_in_precedenceEqualOperator688 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_precedenceBitwiseOrExpression_in_precedenceEqualExpression712 = new BitSet(new long[]{0x00000000401B0002L,0x0000200100080000L,0x0008180000000000L,0x0006000000000000L});
	public static final BitSet FOLLOW_KW_NOT_in_precedenceEqualExpression734 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L,0x0000000000000000L,0x0006000000000000L});
	public static final BitSet FOLLOW_precedenceEqualNegatableOperator_in_precedenceEqualExpression736 = new BitSet(new long[]{0x000080A000404000L,0x0000400000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_precedenceBitwiseOrExpression_in_precedenceEqualExpression740 = new BitSet(new long[]{0x00000000401B0002L,0x0000200100080000L,0x0008180000000000L,0x0006000000000000L});
	public static final BitSet FOLLOW_precedenceEqualOperator_in_precedenceEqualExpression773 = new BitSet(new long[]{0x000080A000404000L,0x0000400000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_precedenceBitwiseOrExpression_in_precedenceEqualExpression777 = new BitSet(new long[]{0x00000000401B0002L,0x0000200100080000L,0x0008180000000000L,0x0006000000000000L});
	public static final BitSet FOLLOW_KW_NOT_in_precedenceEqualExpression806 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_KW_IN_in_precedenceEqualExpression808 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_expressions_in_precedenceEqualExpression810 = new BitSet(new long[]{0x00000000401B0002L,0x0000200100080000L,0x0008180000000000L,0x0006000000000000L});
	public static final BitSet FOLLOW_KW_IN_in_precedenceEqualExpression846 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_expressions_in_precedenceEqualExpression848 = new BitSet(new long[]{0x00000000401B0002L,0x0000200100080000L,0x0008180000000000L,0x0006000000000000L});
	public static final BitSet FOLLOW_KW_NOT_in_precedenceEqualExpression879 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_KW_BETWEEN_in_precedenceEqualExpression881 = new BitSet(new long[]{0x000080A000404000L,0x0000400000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_precedenceBitwiseOrExpression_in_precedenceEqualExpression886 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_KW_AND_in_precedenceEqualExpression889 = new BitSet(new long[]{0x000080A000404000L,0x0000400000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_precedenceBitwiseOrExpression_in_precedenceEqualExpression894 = new BitSet(new long[]{0x00000000401B0002L,0x0000200100080000L,0x0008180000000000L,0x0006000000000000L});
	public static final BitSet FOLLOW_KW_BETWEEN_in_precedenceEqualExpression937 = new BitSet(new long[]{0x000080A000404000L,0x0000400000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_precedenceBitwiseOrExpression_in_precedenceEqualExpression942 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_KW_AND_in_precedenceEqualExpression945 = new BitSet(new long[]{0x000080A000404000L,0x0000400000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_precedenceBitwiseOrExpression_in_precedenceEqualExpression950 = new BitSet(new long[]{0x00000000401B0002L,0x0000200100080000L,0x0008180000000000L,0x0006000000000000L});
	public static final BitSet FOLLOW_LPAREN_in_expressions1005 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_expressions1007 = new BitSet(new long[]{0x0000000000000200L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_COMMA_in_expressions1010 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_expressions1012 = new BitSet(new long[]{0x0000000000000200L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_expressions1016 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_NOT_in_precedenceNotOperator1042 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_precedenceNotOperator_in_precedenceNotExpression1064 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_precedenceEqualExpression_in_precedenceNotExpression1069 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_AND_in_precedenceAndOperator1091 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_precedenceNotExpression_in_precedenceAndExpression1112 = new BitSet(new long[]{0x0000000002000002L});
	public static final BitSet FOLLOW_precedenceAndOperator_in_precedenceAndExpression1115 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_precedenceNotExpression_in_precedenceAndExpression1118 = new BitSet(new long[]{0x0000000002000002L});
	public static final BitSet FOLLOW_KW_OR_in_precedenceOrOperator1142 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_precedenceAndExpression_in_precedenceOrExpression1163 = new BitSet(new long[]{0x0000000000000002L,0x0004000000000000L});
	public static final BitSet FOLLOW_precedenceOrOperator_in_precedenceOrExpression1166 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_precedenceAndExpression_in_precedenceOrExpression1169 = new BitSet(new long[]{0x0000000000000002L,0x0004000000000000L});
	public static final BitSet FOLLOW_KW_TRUE_in_booleanValue1193 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_FALSE_in_booleanValue1198 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_functionName_in_function1224 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_LPAREN_in_function1230 = new BitSet(new long[]{0x020080A000404000L,0x0000600000000010L,0xE131200001000000L});
	public static final BitSet FOLLOW_STAR_in_function1251 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_KW_DISTINCT_in_function1267 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xE131200001000000L});
	public static final BitSet FOLLOW_selectExpression_in_function1272 = new BitSet(new long[]{0x0000000000000200L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_COMMA_in_function1275 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xE031200001000000L});
	public static final BitSet FOLLOW_selectExpression_in_function1277 = new BitSet(new long[]{0x0000000000000200L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_function1295 = new BitSet(new long[]{0x0000000000000002L,0x0080000000000000L});
	public static final BitSet FOLLOW_KW_OVER_in_function1298 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_window_specification_in_function1302 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_window_specification1418 = new BitSet(new long[]{0x0000000000000000L,0x8208000000000000L,0x0100000000000010L});
	public static final BitSet FOLLOW_partitioningSpec_in_window_specification1421 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L,0x0100000000000010L});
	public static final BitSet FOLLOW_window_frame_in_window_specification1424 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_window_specification1427 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_partitionByClause_in_partitioningSpec1455 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
	public static final BitSet FOLLOW_orderByClause_in_partitioningSpec1457 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_orderByClause_in_partitioningSpec1476 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_PARTITION_in_partitionByClause1502 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_KW_BY_in_partitionByClause1504 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_LPAREN_in_partitionByClause1507 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_partitionByClause1509 = new BitSet(new long[]{0x0000000000000200L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_COMMA_in_partitionByClause1512 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_partitionByClause1514 = new BitSet(new long[]{0x0000000000000200L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_partitionByClause1518 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_PARTITION_in_partitionByClause1533 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_KW_BY_in_partitionByClause1535 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_partitionByClause1538 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_COMMA_in_partitionByClause1546 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_partitionByClause1548 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_window_range_expression_in_window_frame1576 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_window_value_expression_in_window_frame1581 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_ROWS_in_window_range_expression1593 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L,0x1000000004000000L});
	public static final BitSet FOLLOW_window_frame_start_boundary_in_window_range_expression1597 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_ROWS_in_window_range_expression1611 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_KW_BETWEEN_in_window_range_expression1613 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L,0x1000000004000000L});
	public static final BitSet FOLLOW_window_frame_boundary_in_window_range_expression1617 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_KW_AND_in_window_range_expression1619 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L,0x1000000004000000L});
	public static final BitSet FOLLOW_window_frame_boundary_in_window_range_expression1623 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_RANGE_in_window_value_expression1648 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L,0x1000000004000000L});
	public static final BitSet FOLLOW_window_frame_start_boundary_in_window_value_expression1652 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_RANGE_in_window_value_expression1666 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_KW_BETWEEN_in_window_value_expression1668 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L,0x1000000004000000L});
	public static final BitSet FOLLOW_window_frame_boundary_in_window_value_expression1672 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_KW_AND_in_window_value_expression1674 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L,0x1000000004000000L});
	public static final BitSet FOLLOW_window_frame_boundary_in_window_value_expression1678 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_UNBOUNDED_in_window_frame_start_boundary1703 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_KW_PRECEDING_in_window_frame_start_boundary1705 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_CURRENT_in_window_frame_start_boundary1720 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0008000000000000L});
	public static final BitSet FOLLOW_KW_ROW_in_window_frame_start_boundary1722 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Number_in_window_frame_start_boundary1734 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_KW_PRECEDING_in_window_frame_start_boundary1736 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_UNBOUNDED_in_window_frame_boundary1756 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000800400000000L});
	public static final BitSet FOLLOW_KW_PRECEDING_in_window_frame_boundary1761 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_FOLLOWING_in_window_frame_boundary1765 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_CURRENT_in_window_frame_boundary1782 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0008000000000000L});
	public static final BitSet FOLLOW_KW_ROW_in_window_frame_boundary1784 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Number_in_window_frame_boundary1796 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000800400000000L});
	public static final BitSet FOLLOW_KW_PRECEDING_in_window_frame_boundary1801 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_FOLLOWING_in_window_frame_boundary1807 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_functionName1844 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_CAST_in_castExpression1865 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_LPAREN_in_castExpression1871 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_castExpression1883 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_KW_AS_in_castExpression1895 = new BitSet(new long[]{0x0004811180000000L,0x0000000003000100L,0x0100000000300400L});
	public static final BitSet FOLLOW_primitiveType_in_castExpression1907 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_castExpression1913 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_CASE_in_caseExpression1944 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_caseExpression1946 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_KW_WHEN_in_caseExpression1953 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_caseExpression1955 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_KW_THEN_in_caseExpression1957 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_caseExpression1959 = new BitSet(new long[]{0x5000000000000000L,0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_KW_ELSE_in_caseExpression1968 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_caseExpression1970 = new BitSet(new long[]{0x4000000000000000L});
	public static final BitSet FOLLOW_KW_END_in_caseExpression1978 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_CASE_in_whenExpression2010 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_KW_WHEN_in_whenExpression2019 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_whenExpression2021 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_KW_THEN_in_whenExpression2023 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_whenExpression2025 = new BitSet(new long[]{0x5000000000000000L,0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_KW_ELSE_in_whenExpression2034 = new BitSet(new long[]{0x000080A000404000L,0x0000600000000010L,0xC031200001000000L});
	public static final BitSet FOLLOW_expression_in_whenExpression2036 = new BitSet(new long[]{0x4000000000000000L});
	public static final BitSet FOLLOW_KW_END_in_whenExpression2044 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Number_in_constant2076 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dateLiteral_in_constant2084 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_StringLiteral_in_constant2092 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_stringLiteralSequence_in_constant2100 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DecimalLiteral_in_constant2108 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_booleanValue_in_constant2116 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_StringLiteral_in_stringLiteralSequence2137 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_StringLiteral_in_stringLiteralSequence2139 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_KW_DATE_in_dateLiteral2172 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_StringLiteral_in_dateLiteral2174 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_SMALLINT_in_primitiveType2216 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_BYTEINT_in_primitiveType2229 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_INTEGER_in_primitiveType2243 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_INT_in_primitiveType2245 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_BIGINT_in_primitiveType2265 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_FLOAT_in_primitiveType2279 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_DECIMAL_in_primitiveType2300 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_LPAREN_in_primitiveType2303 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_primitiveType2305 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COMMA_in_primitiveType2307 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_Number_in_primitiveType2309 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_RPAREN_in_primitiveType2311 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_DATE_in_primitiveType2328 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_TIME_in_primitiveType2350 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_TIMESTAMP_in_primitiveType2369 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_CHAR_in_primitiveType2386 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KW_BINARY_in_primitiveType2406 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Identifier_in_identifier2439 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COMMA_in_synpred1_Expr1542 = new BitSet(new long[]{0x0000000000000002L});
}
