parser grammar Expr;

options
{
output=AST;
ASTLabelType=CommonTree;
backtrack=false;
k=3;
}

@members {
	@Override
	public Object recoverFromMismatchedSet(IntStream input, RecognitionException re, BitSet follow) throws RecognitionException {
		throw re;
	}
	@Override
	public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
		gParent.errors.add(new ParseError(gParent, e, tokenNames));
  }
}

@rulecatch {
	catch (RecognitionException e) {
		throw e;
	}
}


expression
    :
    precedenceOrExpression
    ;

atomExpression
    :
    KW_NULL -> TOK_NULL
    | constant
    | function
    | castExpression
    | caseExpression
    | whenExpression
    | tableOrColumn
    | LPAREN! expression RPAREN!
    ;


precedenceFieldExpression
    :
    atomExpression ((LSQUARE^ expression RSQUARE!) | (DOT^ identifier))*
    ;

precedenceUnaryOperator
    :
    PLUS | MINUS | TILDE
    ;

nullCondition
    :
    KW_NULL -> ^(TOK_ISNULL)
    | KW_NOT KW_NULL -> ^(TOK_ISNOTNULL)
    ;

precedenceUnaryPrefixExpression
    :
    (precedenceUnaryOperator^)* precedenceFieldExpression
    ;

precedenceUnarySuffixExpression
    : precedenceUnaryPrefixExpression (a=KW_IS nullCondition)?
    -> {$a != null}? ^(TOK_FUNCTION nullCondition precedenceUnaryPrefixExpression)
    -> precedenceUnaryPrefixExpression
    ;


precedenceBitwiseXorOperator
    :
    BITWISEXOR
    ;

precedenceBitwiseXorExpression
    :
    precedenceUnarySuffixExpression (precedenceBitwiseXorOperator^ precedenceUnarySuffixExpression)*
    ;


precedenceStarOperator
    :
    STAR | DIVIDE | MOD | DIV
    ;

precedenceStarExpression
    :
    precedenceBitwiseXorExpression (precedenceStarOperator^ precedenceBitwiseXorExpression)*
    ;


precedencePlusOperator
    :
    PLUS | MINUS
    ;

precedencePlusExpression
    :
    precedenceStarExpression (precedencePlusOperator^ precedenceStarExpression)*
    ;


precedenceAmpersandOperator
    :
    AMPERSAND
    ;

precedenceAmpersandExpression
    :
    precedencePlusExpression (precedenceAmpersandOperator^ precedencePlusExpression)*
    ;


precedenceBitwiseOrOperator
    :
    BITWISEOR
    ;

precedenceBitwiseOrExpression
    :
    precedenceAmpersandExpression (precedenceBitwiseOrOperator^ precedenceAmpersandExpression)*
    ;


precedenceEqualNegatableOperator
    :
    KW_LIKE | KW_RLIKE | KW_REGEXP
    ;

precedenceEqualOperator
    :
    precedenceEqualNegatableOperator | EQUAL | EQUAL_NS | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN
    ;

precedenceEqualExpression
    :
    (left=precedenceBitwiseOrExpression -> $left)
    (
       (KW_NOT precedenceEqualNegatableOperator notExpr=precedenceBitwiseOrExpression)
       -> ^(KW_NOT ^(precedenceEqualNegatableOperator $precedenceEqualExpression $notExpr))
    | (precedenceEqualOperator equalExpr=precedenceBitwiseOrExpression)
       -> ^(precedenceEqualOperator $precedenceEqualExpression $equalExpr)
    | (KW_NOT KW_IN expressions)
       -> ^(TOK_FUNCTION KW_NOT ^(TOK_FUNCTION KW_IN $precedenceEqualExpression expressions))
    | (KW_IN expressions)
       -> ^(TOK_FUNCTION KW_IN $precedenceEqualExpression expressions)
    | ( KW_NOT KW_BETWEEN (min=precedenceBitwiseOrExpression) KW_AND (max=precedenceBitwiseOrExpression) )
       -> ^(TOK_FUNCTION KW_NOT ^(TOK_FUNCTION KW_BETWEEN $left $min $max))
    | ( KW_BETWEEN (min=precedenceBitwiseOrExpression) KW_AND (max=precedenceBitwiseOrExpression) )
       -> ^(TOK_FUNCTION KW_BETWEEN $left $min $max)
    )*
    ;

expressions
    :
    LPAREN expression (COMMA expression)* RPAREN -> expression*
    ;

precedenceNotOperator
    :
    KW_NOT
    ;

precedenceNotExpression
    :
    (precedenceNotOperator^)* precedenceEqualExpression
    ;


precedenceAndOperator
    :
    KW_AND
    ;

precedenceAndExpression
    :
    precedenceNotExpression (precedenceAndOperator^ precedenceNotExpression)*
    ;


precedenceOrOperator
    :
    KW_OR
    ;

precedenceOrExpression
    :
    precedenceAndExpression (precedenceOrOperator^ precedenceAndExpression)*
    ;


booleanValue
    :
    KW_TRUE^ | KW_FALSE^
    ;
    
function
    :
    functionName
    LPAREN
      (
        (star=STAR)
        | (dist=KW_DISTINCT)? (selectExpression (COMMA selectExpression)*)?
      )
    RPAREN (KW_OVER ws=window_specification)?
           -> {$star != null}? ^(TOK_FUNCTIONSTAR functionName $ws?)
           -> {$dist == null}? ^(TOK_FUNCTION functionName (selectExpression+)? $ws?)
                            -> ^(TOK_FUNCTIONDI functionName (selectExpression+)?)
    ;
window_specification 
	:
	 LPAREN  partitioningSpec? window_frame? RPAREN
		-> ^(TOK_WINDOWSPEC  partitioningSpec? window_frame?)
	;
partitioningSpec
   :
	partitionByClause orderByClause? 
		->^(TOK_PARTITIONINGSPEC partitionByClause orderByClause?) |
	orderByClause 
		->^(TOK_PARTITIONINGSPEC orderByClause)
   ;
partitionByClause
    :
	KW_PARTITION KW_BY
	LPAREN expression (COMMA expression)* RPAREN -> ^(TOK_DISTRIBUTEBY expression+)
	|
	KW_PARTITION KW_BY
	expression ((COMMA)=> COMMA expression)* -> ^(TOK_DISTRIBUTEBY expression+)
    ;
    
window_frame:
	window_range_expression |
	window_value_expression
;

window_range_expression 
	:
	KW_ROWS sb=window_frame_start_boundary -> ^(TOK_WINDOWRANGE $sb) |
	KW_ROWS KW_BETWEEN s=window_frame_boundary KW_AND end=window_frame_boundary -> ^(TOK_WINDOWRANGE $s $end)
	;

window_value_expression 
	:
	KW_RANGE sb=window_frame_start_boundary -> ^(TOK_WINDOWVALUES $sb) |
	KW_RANGE KW_BETWEEN s=window_frame_boundary KW_AND end=window_frame_boundary -> ^(TOK_WINDOWVALUES $s $end)
	;

window_frame_start_boundary 
	:
	KW_UNBOUNDED KW_PRECEDING  -> ^(KW_PRECEDING KW_UNBOUNDED) | 
	KW_CURRENT KW_ROW  -> ^(KW_CURRENT) |
	Number KW_PRECEDING -> ^(KW_PRECEDING Number)
;

window_frame_boundary 
	:
	KW_UNBOUNDED (r=KW_PRECEDING|r=KW_FOLLOWING)  -> ^($r KW_UNBOUNDED) | 
	KW_CURRENT KW_ROW  -> ^(KW_CURRENT) |
	Number (d=KW_PRECEDING | d=KW_FOLLOWING ) -> ^($d Number)
	;   

    
functionName
    :
    identifier
    ;

castExpression
    :
    KW_CAST
    LPAREN
          expression
          KW_AS
          primitiveType
    RPAREN -> ^(TOK_FUNCTION primitiveType expression)
    ;

caseExpression
    :
    KW_CASE expression
    (KW_WHEN expression KW_THEN expression)+
    (KW_ELSE expression)?
    KW_END -> ^(TOK_FUNCTION KW_CASE expression*)
    ;

whenExpression
    :
    KW_CASE
     ( KW_WHEN expression KW_THEN expression)+
    (KW_ELSE expression)?
    KW_END -> ^(TOK_FUNCTION KW_WHEN expression*)
    ;

constant
    :
    Number
    | dateLiteral
    | StringLiteral
    | stringLiteralSequence
    | DecimalLiteral
    | booleanValue
    ;

stringLiteralSequence
    :
    StringLiteral StringLiteral+ -> ^(TOK_STRINGLITERALSEQUENCE StringLiteral StringLiteral+)
    ;

dateLiteral
    :
    KW_DATE StringLiteral
    
    	->^(TOK_DATELITERAL  StringLiteral)
	;
    
primitiveType
    :
    | KW_SMALLINT		-> TOK_SMALLINT
    | KW_BYTEINT		-> TOK_BYTEINT
    | (KW_INTEGER|KW_INT)       	-> TOK_INTEGER
    | KW_BIGINT			-> TOK_BIGINT
    | KW_FLOAT         	-> TOK_FLOAT
    | KW_DECIMAL (LPAREN Number COMMA Number RPAREN)?	-> TOK_DECIMAL Number*
    | KW_DATE          	-> TOK_DATE
    | KW_TIME      		-> TOK_TIME
    | KW_TIMESTAMP     	-> TOK_TIMESTAMP
    | KW_CHAR        	-> TOK_CHAR
    | KW_BINARY        	-> TOK_BINARY
    ;

identifier
    :
    Identifier
    ;


 

