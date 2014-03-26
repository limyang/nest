package com.ebay.nest.expr;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ebay.nest.plan.ElementVisitor;
import com.ebay.nest.plan.Walker;

public class ExprDepthLastWalker extends Walker<ExprDesc> {

	private static final long serialVersionUID = 1L;

	public ExprDepthLastWalker(ExprDesc op) {
		super(op);

	}

	private void depthLast(ExprDesc node, Set<ExprDesc> seen, ExprVisitor visitor) {
		if (seen.add(node)) {
			if (node.visit(visitor)) {
				return;
			}
		}

		List<ExprDesc> childs = node.getChildren();

		if (childs == null) {
			return;
		}
		for (ExprDesc op : childs) {

			depthLast(op, seen, visitor);
		}

	}

	public void walk(ElementVisitor<ExprDesc> visitor) {
		Set<ExprDesc> seen = new HashSet<ExprDesc>();

		depthLast(op, seen, (ExprVisitor) visitor);

	}

}
