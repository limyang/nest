package com.ebay.nest.analyzer;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.runtime.tree.Tree;

import com.ebay.nest.parser.ASTNode;
import com.ebay.nest.parser.ASTNodeOrigin;
import com.ebay.nest.parser.ParserUtil;

public enum ErrorMsg {
	ROOT_ERROR(100, "SQLParser.g did not working, please contact with Developer"),

	GENERIC_ERROR(400, "Exception while processing"),

	INVALID_JOIN_CONDITION_1(101, "Both left and right aliases encountered in JOIN"),

	INVALID_JOIN_CONDITION_2(102, "Neither left nor right aliases encountered in JOIN"),

	INVALID_JOIN_CONDITION_3(103, "OR not supported in JOIN currently"),

	INVALID_JOIN_CONDITION_4(104, "Not supported in JOIN currently"),

	INVALID_POSITION_ALIAS_IN_GROUPBY(105, "Invalid position alias in Group By\n"),

	INVALID_POSITION_ALIAS_IN_ORDERBY(106, "Invalid position alias in Order By\n"),

	NO_SUPPORTED_ORDERBY_ALLCOLREF_POS(107, "Position in ORDER BY is not supported when using SELECT *"),

	NO_INSERT_INSUBQUERY(201, "Cannot insert in a subquery. Inserting to table "),

	NO_SUBQUERY_ALIAS(202, "No alias for subquery"),

	AMBIGUOUS_TABLE_ALIAS(203, "Ambiguous table alias"),

	INVLID_JOIN_TYPE(204, "Did not support this kind Join"),

	META_MISSING_ERROR(205, "Cannot get meta of Table"),

	NO_COLUMN_FOR_ALIAS(206, "Cannot get alias for this column"),

	DUPLICAT_COLUMN_NAME(207, "Duplicate column name, please rename column name"),

	SELECT_DISTINCT_WITH_GROUPBY(208, "SELECT DISTINCT and GROUP BY can not be in the same query"),

	MISSING_ALIAS_DEFINE(209, "Missing alias define"),

	NOT_SUPPORT_CROSS_JOIN(230, "Did not support Cross Join (Production Join)"), 
	
	WRONG_JOIN_COND(231, "Invalid Join Condition"),

	MISSING_DEFINE_FOR_TABLE(232, "Missing define for table"),

	NOT_SUPPORT_RIGHT_NOW(900, "Not Support right now...Thank you for your patient"),

	// Expr
	INVALID_MATH_NUMBER(600, "Invalid math number"),

	EXPR_FUNC_ERROR(610, "Expr Func Error"),

	MISSING_FUNC_DEFINE(611, "Missing Func Define"),

	INVALID_TYPE_FOR_PLUS(620, "Invalid type for plus"),

	INVALID_TYPE_FOR_MINUS(621, "Invalid type for minus"),

	INVALID_CASE_WHEN(622, "Invalid Case When Statement"),

	INVLID_TYPE_WITH_OR(623, "Invalid Type with OR Expression"),

	INVLID_TYPE_WITH_AND(624, "Invalid Type with AND Expression"),

	INVALID_FUNCTION_NAME(625, "Invalid Function Name, Such as function name begin with _"),

	INVALID_PARTIIONBY_CLAUSE(626, "Invalid Partition By Clause, it should take a column reference list"),

	WRONG_PARAMETER_FOR_EXPR(627, "Wrong parameter types for function"),

	INCORRECT_COLUMN_NAME(628, "Column not found in tables"),

	CANNOT_DECIDE_TABLE(629, "Cannot decide the table for column name"),

	NO_SUPPORT_YET_FOR_FUNCTION(630, "Not Support Yet For This Function"),

	ALIAS_NOT_FROM(1000, "There is no corresponding table name for this alias"),

	UPDATE_NOT_ALLOWED(3706, "Joined Tables are not allowed in FROM clause"),

	DERIVED_NOT_ALLOWED_FOR_DELETE(3707, "Derived Table not allowed for delete"),

	DERIVED_NOT_ALLOWED_FOR_UPDATE(3708, "Derived Table not allowed for update"),

	INVALID_TABLE_ALIAS(10009, "Invalid table alias"),
	
	MISSING_JOIN_CONDN(10010,"Missing Join Condition"),

	;

	private int errorCode;
	private String mesg;
	private String sqlState;
	private MessageFormat format;

	private static final char SPACE = ' ';
	private static final Pattern ERROR_MESSAGE_PATTERN = Pattern.compile(".*Line [0-9]+:[0-9]+ (.*)");
	private static final Pattern ERROR_CODE_PATTERN = Pattern.compile("Exception:\\s+\\[Error ([0-9]+)\\]: (.*)");
	private static Map<String, ErrorMsg> mesgToErrorMsgMap = new HashMap<String, ErrorMsg>();
	private static Map<Pattern, ErrorMsg> formatToErrorMsgMap = new HashMap<Pattern, ErrorMsg>();
	private static int minMesgLength = -1;
	private static String[] lines;

	static {
		for (ErrorMsg errorMsg : values()) {
			if (errorMsg.format != null) {
				String pattern = errorMsg.mesg.replaceAll("\\{.*\\}", ".*");
				formatToErrorMsgMap.put(Pattern.compile("^" + pattern + "$"), errorMsg);
			} else {
				mesgToErrorMsgMap.put(errorMsg.getMsg().trim(), errorMsg);
				int length = errorMsg.getMsg().trim().length();
				if (minMesgLength == -1 || length < minMesgLength) {
					minMesgLength = length;
				}
			}
		}
	}

	public static void init(String cql) {
		char[] data = cql.toCharArray();
		List<String> lineList = new ArrayList<String>();
		int i = 0;
		int prev = -1;
		int curr = -1;
		while (i < data.length) {

			if (data[i] == '\n') {
				prev = curr + 1;
				curr = i;
				lineList.add(new String(data, prev, curr - prev + 1));
			}
			i++;

		}
		lines = lineList.toArray(new String[lineList.size()]);
	}

	public static String getLineString(int i) {
		return lines[i - 1];
	}

	public static int getLineCount() {
		return lines.length;
	}

	public static ErrorMsg getErrorMsg(String mesg) {
		if (mesg == null) {
			return GENERIC_ERROR;
		}

		ErrorMsg errorMsg = mesgToErrorMsgMap.get(mesg);
		if (errorMsg != null) {
			return errorMsg;
		}

		for (Map.Entry<Pattern, ErrorMsg> entry : formatToErrorMsgMap.entrySet()) {
			if (entry.getKey().matcher(mesg).matches()) {
				return entry.getValue();
			}
		}

		String truncatedMesg = mesg.trim();
		Matcher match = ERROR_MESSAGE_PATTERN.matcher(mesg);
		if (match.matches()) {
			truncatedMesg = match.group(1);
		}

		while (truncatedMesg.length() > minMesgLength) {
			errorMsg = mesgToErrorMsgMap.get(truncatedMesg.trim());
			if (errorMsg != null) {
				return errorMsg;
			}

			int lastSpace = truncatedMesg.lastIndexOf(SPACE);
			if (lastSpace == -1) {
				break;
			}

			// hack off the last word and try again
			truncatedMesg = truncatedMesg.substring(0, lastSpace).trim();
		}

		return GENERIC_ERROR;
	}

	public static ErrorMsg getErrorMsg(int errorCode) {
		for (ErrorMsg errorMsg : values()) {
			if (errorMsg.getErrorCode() == errorCode) {
				return errorMsg;
			}
		}
		return null;
	}

	public static String findSQLState(String mesg) {
		ErrorMsg error = getErrorMsg(mesg);
		return error.getSQLState();
	}

	private ErrorMsg(int errorCode, String mesg) {
		this(errorCode, mesg, "42000", false);
	}

	private ErrorMsg(int errorCode, String mesg, boolean format) {
		// 42000 is the generic SQLState for syntax error.
		this(errorCode, mesg, "42000", format);
	}

	private ErrorMsg(int errorCode, String mesg, String sqlState) {
		this(errorCode, mesg, sqlState, false);
	}

	private ErrorMsg(int errorCode, String mesg, String sqlState, boolean format) {
		this.errorCode = errorCode;
		this.mesg = mesg;
		this.sqlState = sqlState;
		this.format = format ? new MessageFormat(mesg) : null;
	}

	private static int getLine(ASTNode tree) {
		if (tree.getChildCount() == 0) {
			return tree.getToken().getLine();
		}

		return getLine((ASTNode) tree.getChild(0));
	}

	private static int getCharPositionInLine(ASTNode tree) {
		if (tree.getChildCount() == 0) {
			return tree.getToken().getCharPositionInLine();
		}

		return getCharPositionInLine((ASTNode) tree.getChild(0));
	}

	public static String getText(ASTNode tree) {
		if (tree.getChildCount() == 0) {
			return tree.getText();
		}
		return "\n" + tree.prettyDump();

		// return getText((ASTNode) tree.getChild(tree.getChildCount() - 1));
	}

	public static String getLines(ASTNode tree, int prev, int last) {
		int line = tree.getLine();

		prev = line - prev;
		if (prev < 1) {
			prev = 1;
		}
		last = line + last;
		if (last > getLineCount()) {
			last = getLineCount();
		}
		StringBuilder sb = new StringBuilder();
		for (int i = prev; i < last + 1; i++) {
			sb.append(i + "\t" + getLineString(i));
		}
		return sb.toString();
	}

	public String getMsg(ASTNode tree) {
		StringBuilder sb = new StringBuilder();
		sb.append(tree.getLine());
		sb.append(":");
		sb.append(tree.getCharPositionInLine());
		sb.append(" ");
		sb.append(mesg);
		sb.append(" ");
		sb.append("\n");
		sb.append(ErrorMsg.getLines(tree, 2, 2));
		return sb.toString();
	}

	public static void renderOrigin(StringBuilder sb, ASTNodeOrigin origin) {
		while (origin != null) {
			sb.append(" in definition of ");
			sb.append(origin.getObjectType());
			sb.append(" ");
			sb.append(origin.getObjectName());
			sb.append(" [");
			sb.append(ParserUtil.LINE_SEP);
			sb.append(origin.getObjectDefinition());
			sb.append(ParserUtil.LINE_SEP);
			sb.append("] used as ");
			sb.append(origin.getUsageAlias());
			sb.append(" at ");
			ASTNode usageNode = origin.getUsageNode();
			renderPosition(sb, usageNode);
			origin = usageNode.getOrigin();
		}
	}

	private static void renderPosition(StringBuilder sb, ASTNode tree) {
		sb.append("Line ");
		sb.append(getLine(tree));
		sb.append(":");
		sb.append(getCharPositionInLine(tree));
	}

	public String getMsg(Tree tree) {
		return getMsg((ASTNode) tree);
	}

	public String getMsg(ASTNode tree, String reason) {
		return getMsg(tree) + ": " + reason;
	}

	public String getMsg(Tree tree, String reason) {
		return getMsg((ASTNode) tree, reason);
	}

	public String getMsg(String reason) {
		return mesg + " " + reason;
	}

	public String format(String reason) {
		return format(new String[] { reason });
	}

	public String format(String... reasons) {

		if (reasons == null || reasons.length == 0) {
			return getMsg();
		}
		if (format != null) {
			return format.format(reasons);
		}
		if (reasons.length > 1) {
			StringBuilder sb = new StringBuilder();
			for (String re : reasons) {
				if (re != null) {
					if (sb.length() > 0) {
						sb.append(" ");
					}
					sb.append(re);
				}
			}
			return getMsg(sb.toString());
		}
		return getMsg(reasons[0]);
	}

	public String getErrorCodedMsg() {
		return "[Error " + errorCode + "]: " + mesg;
	}

	public static Pattern getErrorCodePattern() {
		return ERROR_CODE_PATTERN;
	}

	public String getMsg() {
		return mesg;
	}

	public String getSQLState() {
		return sqlState;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
