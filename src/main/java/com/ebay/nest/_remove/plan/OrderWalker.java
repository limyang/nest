package com.ebay.nest._remove.plan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ebay.nest.plan.Element;
import com.ebay.nest.plan.VisitorException;

public class OrderWalker<O extends Element, P extends OperatorPlan<O>> extends PlanWalker<O, P> {

	public OrderWalker(P plan) {
		super(plan);
	}

	public void walk(PlanVisitor<O, P> visitor) throws VisitorException {
		List<O> fifo = new ArrayList<O>();
		Set<O> seen = new HashSet<O>();
		List<O> leaves = mPlan.getLeaves();
		if (leaves == null)
			return;
		for (O op : leaves) {
			doAllPredecessors(op, seen, fifo);
		}
		for (O op : fifo) {
			op.visit(visitor);
		}
	}

	public PlanWalker<O, P> spawnChildWalker(P plan) {
		return new OrderWalker<O, P>(plan);
	}

	protected void doAllPredecessors(O node, Set<O> seen, Collection<O> fifo) throws VisitorException {
		if (!seen.contains(node)) {
			Collection<O> preds = mergeCollection(mPlan.getPredecessors(node), mPlan.getSoftLinkPredecessors(node));
			if (preds != null && preds.size() > 0) {
				for (O op : preds) {
					doAllPredecessors(op, seen, fifo);
				}
			}
			seen.add(node);
			fifo.add(node);
		}
	}

	private static <O> Collection<O> mergeCollection(Collection<O> a, Collection<O> b) {
		if (a == null && b == null)
			return null;
		Collection<O> result = null;
		try {
			if (a != null)
				result = a.getClass().newInstance();
			else
				result = b.getClass().newInstance();
		} catch (Exception e) {
			// Never happen!!
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
