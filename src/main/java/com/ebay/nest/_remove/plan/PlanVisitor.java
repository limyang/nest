package com.ebay.nest._remove.plan;

import java.util.Deque;
import java.util.LinkedList;

import com.ebay.nest.plan.Element;
import com.ebay.nest.plan.Visitor;
import com.ebay.nest.plan.VisitorException;

abstract public class PlanVisitor<O extends Element, P extends OperatorPlan<O>> extends Visitor {
	
	public final static String DEFAULT_SCOPE = "scope";

	protected P mPlan;

	protected PlanWalker<O, P> mCurrentWalker;

	private Deque<PlanWalker<O, P>> mWalkers;

	public void visit() throws VisitorException {
		mCurrentWalker.walk(this);
	}

	public P getPlan() {
		return mPlan;
	}

	protected PlanVisitor(P plan, PlanWalker<O, P> walker) {
		mPlan = plan;
		mCurrentWalker = walker;
		mWalkers = new LinkedList<PlanWalker<O, P>>();
	}

	protected void pushWalker(PlanWalker<O, P> walker) {
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