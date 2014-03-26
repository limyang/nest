package com.ebay.nest.expr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AliasColFinder extends ExprVisitor {

	private static final long serialVersionUID = 1L;
	private Map<String, Set<String>> aliasMap;

	public AliasColFinder(ExprDesc op) {

		super(op, new ExprDepthFirstWalker(op));
		aliasMap = new HashMap<String, Set<String>>();

	}

	public boolean visitColumn(AliasColExpr aliasColExpr) {

		AliasCol as = aliasColExpr.getAliasCol();
		Set<String> sets = aliasMap.get(as.getAlias());
		if (sets == null) {
			sets = new HashSet<String>();
			aliasMap.put(as.getAlias(), sets);
		}
		sets.add(as.getColumn());
		return true;
	}

	public Map<String, Set<String>> getAliasMap() {

		visit();

		return aliasMap;
	}

}
