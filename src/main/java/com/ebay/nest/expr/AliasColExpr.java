package com.ebay.nest.expr;

import java.util.List;

public final class AliasColExpr extends ExprDesc {

	private static final long serialVersionUID = 1L;
	private final AliasCol aliasCol;

	public AliasCol getAliasCol() {
		return aliasCol;
	}

	public AliasColExpr(String alias, String column) {
		aliasCol = new AliasCol(alias, column);
	}

	public boolean visit(ExprVisitor v) {
		return v.visitColumn(this);
	}

	public List<ExprDesc> getChildren() {
		return null;
	}

	public boolean supportsMultipleInputs() {
		return false;
	}

	public String getName() {
		return "Table Column" + "(" + aliasCol.getAlias() + ", " + aliasCol.getColumn() + ")" + "["
				+ resultType.getSimpleName() + "]" + " - " + getId();
	}

}
