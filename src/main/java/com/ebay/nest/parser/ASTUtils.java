package com.ebay.nest.parser;

public class ASTUtils {

	public static ASTNode findRootNonNullToken(ASTNode tree) {
		while (tree != null && (tree.getToken() == null) && (tree.getChildCount() > 0)) {
			tree = (ASTNode) tree.getChild(0);
		}
		return tree;
	}

}
