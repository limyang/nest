package com.ebay.nest.analyzer;

import com.ebay.nest.parser.ASTNode;
import com.ebay.nest.parser.SQLParser;
import com.ebay.nest.utils.StringUtil;

public class QueryUtil {

	public final static String DOT_ALIAS = "$";

	public static String getAliasFromNode(ASTNode node) {
		assert node.getChildCount() <= 2;
		if (node.getChildCount() == 2) {
			return node.getChild(1).getText();
		}
		return node.getChild(0).getText();

	}

	public static boolean isJoinToken(ASTNode node) {
		if ((node.getToken().getType() == SQLParser.TOK_JOIN) || (node.getToken().getType() == SQLParser.TOK_CROSSJOIN)
				|| (node.getToken().getType() == SQLParser.TOK_LEFTOUTERJOIN)
				|| (node.getToken().getType() == SQLParser.TOK_RIGHTOUTERJOIN)
				|| (node.getToken().getType() == SQLParser.TOK_FULLOUTERJOIN)
				|| (node.getToken().getType() == SQLParser.TOK_LEFTSEMIJOIN)
				|| (node.getToken().getType() == SQLParser.TOK_UNIQUEJOIN)) {
			return true;
		}

		return false;
	}

	public static String getTabNameFromNode(ASTNode tabNameNode) {
		return getTabNameFromNode(tabNameNode, null);
	}

	public static String getTabNameFromNode(ASTNode tabNameNode, String currentDatabase) {
		if (tabNameNode.getToken().getType() == SQLParser.TOK_TABNAME) {
			// table node
			if (tabNameNode.getChildCount() == 2) {
				String dbName = tabNameNode.getChild(0).getText();
				String tableName = tabNameNode.getChild(1).getText();
				return dbName + "." + tableName;
			}
			String tableName = tabNameNode.getChild(0).getText();
			if (currentDatabase != null) {
				return currentDatabase + "." + tableName;
			}
			return tableName;
		}
		return tabNameNode.getText();
	}

	public static String[] processDotNode(ASTNode dotNode) {

		if (dotNode.getToken().getType() != SQLParser.DOT) {
			return null;
		}
		String tab = null, col = null, db = null;
		ASTNode tableNode = (ASTNode) dotNode.getChild(0);

		if (tableNode.getToken().getType() == SQLParser.TOK_TABLE_OR_COL) {
			tab = StringUtil.unescapeIdentifier(tableNode.getChild(0).getText().toLowerCase());
			col = StringUtil.unescapeIdentifier(dotNode.getChild(1).getText().toLowerCase());
		} else if (tableNode.getToken().getType() == SQLParser.DOT) {
			ASTNode dbNode = (ASTNode) tableNode.getChild(0);
			if (dbNode.getToken().getType() == SQLParser.TOK_TABLE_OR_COL) {
				db = StringUtil.unescapeIdentifier(dbNode.getChild(0).getText().toLowerCase());
				tab = StringUtil.unescapeIdentifier(tableNode.getChild(1).getText().toLowerCase());
				col = StringUtil.unescapeIdentifier(dotNode.getChild(1).getText().toLowerCase());
			}
		}

		return new String[] { tab, col, db };

	}

}
