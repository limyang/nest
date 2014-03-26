package com.ebay.nest.expr;

public class ModExpr extends BinaryExpr {

	private static final long serialVersionUID = 1L;

	public ModExpr() {
		super();

	}

	public boolean visit(ExprVisitor v) {
		return v.visitMod(this);
	}

	public String getName() {

		return "Mod" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
	}

}