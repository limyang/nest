package com.ebay.nest.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ebay.nest.analyzer.ErrorMsg;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.analyzer.TableDef_remove;
import com.ebay.nest.parser.ASTNode;
import com.ebay.nest.parser.ASTUtils;
import com.ebay.nest.parser.ParseDriver;
import com.ebay.nest.parser.ParseException;
import com.ebay.nest.parser.SQLParser;

public class CQLParserU {

	public static final String[] SPECIAL_KEYWORDS = new String[] { "source",
			"sink", "define", "overwrite", "pipe", "meta", "with", "path" };

	public static Set<TableDef_remove> getSources(String content)
			throws SemanticException, ParseException {
		Set<TableDef_remove> tables = new HashSet<TableDef_remove>();
		ParseDriver pd = new ParseDriver();
		ASTNode rootTree = pd.parse(content, null);
		rootTree = ASTUtils.findRootNonNullToken(rootTree);
		if (rootTree == null || rootTree.getChildCount() == 0) {
			throw new SemanticException();
		}

		if (rootTree.getToken().getType() != SQLParser.TOK_ROOT) {
			throw new SemanticException(ErrorMsg.ROOT_ERROR.getMsg(rootTree));
		}

		int child_count = rootTree.getChildCount();
		for (int child_pos = 0; child_pos < child_count; ++child_pos) {
			ASTNode child = (ASTNode) rootTree.getChild(child_pos);
			switch (child.getToken().getType()) {
			case SQLParser.TOK_DEFINE_SOURCE_STATEMENT:
			case SQLParser.TOK_DEFINE_TARGET_STATEMENT:
				TableDef_remove table = getSource(child);
				if (table != null) {
					tables.add(table);
				}
			default:
				break;
			}
		}
		return tables;
	}

	public static Set<TableDef_remove> getSinks(String content)
			throws SemanticException, ParseException {
		Set<TableDef_remove> tables = new HashSet<TableDef_remove>();
		ParseDriver pd = new ParseDriver();
		ASTNode rootTree = pd.parse(content, null);
		rootTree = ASTUtils.findRootNonNullToken(rootTree);
		if (rootTree == null || rootTree.getChildCount() == 0) {
			throw new SemanticException();
		}

		if (rootTree.getToken().getType() != SQLParser.TOK_ROOT) {
			throw new SemanticException(ErrorMsg.ROOT_ERROR.getMsg(rootTree));
		}

		int child_count = rootTree.getChildCount();
		for (int child_pos = 0; child_pos < child_count; ++child_pos) {
			ASTNode child = (ASTNode) rootTree.getChild(child_pos);
			switch (child.getToken().getType()) {
			case SQLParser.TOK_DEFINE_SOURCE_STATEMENT:
			case SQLParser.TOK_DEFINE_TARGET_STATEMENT:
				TableDef_remove table = getSink(child);
				if (table != null) {
					tables.add(table);
				}
			default:
				break;
			}
		}
		return tables;
	}

	private static TableDef_remove getSource(ASTNode child) {
		ASTNode source = (ASTNode) child.getChild(0);
		String path = null;
		String tb = null;
		String db = null;
		boolean isSource = false;
		for (int source_child_pos = 0; source_child_pos < source
				.getChildCount(); source_child_pos++) {
			ASTNode source_element = (ASTNode) source
					.getChild(source_child_pos);
			switch (source_element.getToken().getType()) {
			case SQLParser.TOK_PATH:
				path = StringUtil.unescapeString(source_element.getChild(0)
						.getText());
				break;
			case SQLParser.TOK_META:
				db = source_element.getChild(0).getText().toLowerCase();
				tb = source_element.getChild(1).getText().toLowerCase();
				break;
			case SQLParser.KW_SOURCE:
				isSource = true;
				break;
			}

		}
		if (isSource) {
			TableDef_remove table = new TableDef_remove(null, db, tb, path, null);
			return table;
		}
		return null;

	}

	private static TableDef_remove getSink(ASTNode child) {
		ASTNode source = (ASTNode) child.getChild(0);
		String path = null;
		String tb = null;
		String db = null;
		boolean isSink = false;
		for (int source_child_pos = 0; source_child_pos < source
				.getChildCount(); source_child_pos++) {
			ASTNode source_element = (ASTNode) source
					.getChild(source_child_pos);
			switch (source_element.getToken().getType()) {
			case SQLParser.TOK_PATH:
				path = StringUtil.unescapeString(source_element.getChild(0)
						.getText());
				break;
			case SQLParser.TOK_META:
				tb = source_element.getChild(1).getText().toLowerCase();
				db = source_element.getChild(0).getText().toLowerCase();
				break;
			case SQLParser.KW_TARGET:
				isSink = true;
				break;
			}

		}
		if (isSink) {
			TableDef_remove table = new TableDef_remove(null, db, tb, path, null);
			return table;
		}
		return null;

	}

	public static String getPathType(String path) {
		Pattern pattern = Pattern
				.compile("^\\s*([^\\s:]+)\\s*(:([^\\s]+))?\\s*$");
		Matcher matcher = pattern.matcher(path);
		String type = null;

		if (matcher.find()) {
			type = matcher.group(1).toLowerCase();
		}
		return type;
	}

	public static String getPathNum(String type) {

		String num = "";
		Pattern pattern2 = Pattern.compile("(\\d+)$");
		Matcher matcher2 = pattern2.matcher(type);
		if (matcher2.find()) {
			if (matcher2.group(1).length() == 1) {
				num = "0" + matcher2.group(1);
			} else {
				num = matcher2.group(1);
			}
		} else {
			num = "00";
		}
		return num;
	}
}
