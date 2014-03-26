package com.ebay.nest._remove.plan;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ebay.nest.plan.Element;
import com.ebay.nest.plan.VisitorException;

public class DepthFirstWalker<O extends Element, P extends OperatorPlan<O>> extends PlanWalker<O, P> {


	public DepthFirstWalker(P plan) {
		super(plan);
	}


	public void walk(PlanVisitor<O, P> visitor) throws VisitorException {
		List<O> roots = mPlan.getRoots();
		Set<O> seen = new HashSet<O>();

		depthFirst(null, roots, seen, visitor);
	}

	public PlanWalker<O, P> spawnChildWalker(P plan) {
		return new DepthFirstWalker<O, P>(plan);
	}


	private void depthFirst(O node, Collection<O> successors, Set<O> seen, PlanVisitor<O, P> visitor)
			throws VisitorException {
		if (successors == null)
			return;

		for (O suc : successors) {
			if (seen.add(suc)) {
				suc.visit(visitor);
				Collection<O> newSuccessors = mergeCollection(mPlan.getSuccessors(suc),
						mPlan.getSoftLinkSuccessors(suc));
				depthFirst(suc, newSuccessors, seen, visitor);
			}
		}
	}

	public static <O> Collection<O> mergeCollection(Collection<O> a, Collection<O> b) {
		if (a == null && b == null)
			return null;
		Collection<O> result = null;
		try {
			if (a != null)
				result = a.getClass().newInstance();
			else
				result = b.getClass().newInstance();
		} catch (Exception e) {
			// Shall not happen
		}
		if (a == null) {
			result.addAll(b);
		} else if (b == null) {
			result.addAll(a);
		} else {
			result.addAll(a);
			for (O o : b) {
				if (!result.contains(o)) {
					result.add(o);
				}
			}
		}

		return result;
	}
}
