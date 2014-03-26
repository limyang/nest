package com.ebay.nest.expr;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ebay.nest.plan.ElementVisitor;
import com.ebay.nest.plan.Walker;

public class ExprDepthFirstWalker extends Walker<ExprDesc> {

	private static final long serialVersionUID = 1L;

	public ExprDepthFirstWalker(ExprDesc op) {
		super(op);

	}

	private void depthFirst(ExprDesc node, Set<ExprDesc> seen, ExprVisitor visitor) {
		List<ExprDesc> childs = node.getChildren();
		if (childs != null) {
			for (ExprDesc op : childs) {
				depthFirst(op, seen, visitor);
			}
		}
		if (seen.add(node)) {
			if (node.visit(visitor)) {
				return;
			}
		}

	}

	public void walk(ElementVisitor<ExprDesc> visitor) {
		Set<ExprDesc> seen = new HashSet<ExprDesc>();

		depthFirst(op, seen, (ExprVisitor) visitor);

	}

}
