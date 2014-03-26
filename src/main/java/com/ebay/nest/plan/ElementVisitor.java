package com.ebay.nest.plan;

import java.util.Deque;
import java.util.LinkedList;

public class ElementVisitor<O extends Element> extends Visitor {

	private static final long serialVersionUID = 1L;

	protected Walker<O> mCurrentWalker;

	private Deque<Walker<O>> mWalkers;

	private Element op;

	public void visit() {

		mCurrentWalker.walk(this);

	}

	protected ElementVisitor(O op, Walker<O> walker) {
		this.op = op;
		mCurrentWalker = walker;
		mWalkers = new LinkedList<Walker<O>>();
	}

	protected Element getOperator() {
		return this.op;
	}

	protected void pushWalker(Walker<O> walker) {
		mWalkers.push(mCurrentWalker);
		mCurrentWalker = walker;
	}

	protected void popWalker() throws VisitorException {
		if (mWalkers.isEmpty()) {
			throw new VisitorException("No more walkers to pop.");
		}
		mCurrentWalker = mWalkers.pop();
	}

}
