package com.ebay.nest._remove.plan;

import com.ebay.nest.plan.Element;
import com.ebay.nest.plan.VisitorException;

public abstract class PlanWalker<O extends Element, P extends OperatorPlan<O>> {

	protected P mPlan;

	public PlanWalker(P plan) {
		mPlan = plan;
	}

	public abstract void walk(PlanVisitor<O, P> visitor) throws VisitorException;

	public abstract PlanWalker<O, P> spawnChildWalker(P plan);

	public P getPlan() {
		return mPlan;
	}

	public void setPlan(P plan) {
		mPlan = plan;
	}

}
