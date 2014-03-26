package com.ebay.nest._remove.plan;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ebay.nest.NestException;
import com.ebay.nest.plan.Element;
import com.ebay.nest.plan.ElementKey;
import com.ebay.nest.plan.PlanException;

public abstract class OperatorPlan<E extends Element> implements Iterable<E>, Serializable, Cloneable {

	private static final long serialVersionUID = -6174863036488054523L;
	protected Map<E, ElementKey> mOps;
	protected Map<ElementKey, E> mKeys;
	protected MultiMap<E, E> mFromEdges;
	protected MultiMap<E, E> mToEdges;
	protected MultiMap<E, E> mSoftFromEdges;
	protected MultiMap<E, E> mSoftToEdges;

	private List<E> mRoots;
	private List<E> mLeaves;
	protected static final Log log = LogFactory.getLog(OperatorPlan.class);

	public OperatorPlan() {
		mRoots = new ArrayList<E>();
		mLeaves = new ArrayList<E>();
		mOps = new HashMap<E, ElementKey>();
		mKeys = new HashMap<ElementKey, E>();
		mFromEdges = new MultiMap<E, E>();
		mToEdges = new MultiMap<E, E>();
		mSoftFromEdges = new MultiMap<E, E>();
		mSoftToEdges = new MultiMap<E, E>();
	}


	public List<E> getRoots() {
		if (mRoots.size() == 0 && mOps.size() > 0) {
			for (E op : mOps.keySet()) {
				if (mToEdges.get(op) == null) {
					mRoots.add(op);
				}
			}
		}
		return mRoots;
	}


	public List<E> getLeaves() {
		if (mLeaves.size() == 0 && mOps.size() > 0) {
			for (E op : mOps.keySet()) {
				if (mFromEdges.get(op) == null) {
					mLeaves.add(op);
				}
			}
		}
		return mLeaves;
	}


	public ElementKey getOperatorKey(E op) {
		return mOps.get(op);
	}


	public E getOperator(ElementKey opKey) {
		return mKeys.get(opKey);
	}


	public Map<ElementKey, E> getKeys() {
		return mKeys;
	}


	public void add(E op) {
		markDirty();
		mOps.put(op, op.getId());
		mKeys.put(op.getId(), op);
	}


	public void connect(E from, E to) throws PlanException {
		markDirty();

		checkInPlan(from);
		checkInPlan(to);

		if (mFromEdges.get(from) != null && !from.supportsMultipleOutputs()) {
			PlanException pe = new PlanException("Attempt to give operator of type " + from.getClass().getName()
					+ " multiple outputs.  This operator does " + "not support multiple outputs.");
			log.error(pe.getMessage());
			throw pe;
		}

		if (mToEdges.get(to) != null && !to.supportsMultipleInputs()) {
			PlanException pe = new PlanException("Attempt to give operator of type " + to.getClass().getName()
					+ " multiple inputs.  This operator does " + "not support multiple inputs.");
			log.error(pe.getMessage());
			throw pe;
		}
		mFromEdges.put(from, to);
		mToEdges.put(to, from);
	}


	public void createSoftLink(E from, E to) throws PlanException {
		// Check that both nodes are in the plan.
		checkInPlan(from);
		checkInPlan(to);

		mSoftFromEdges.put(from, to);
		mSoftToEdges.put(to, from);
	}


	public void removeSoftLink(E from, E to) {
		mSoftFromEdges.remove(from, to);
		mSoftToEdges.remove(to, from);
	}

	public boolean disconnect(E from, E to) {
		markDirty();

		boolean sawNull = false;
		if (mFromEdges.remove(from, to) == null)
			sawNull = true;
		if (mToEdges.remove(to, from) == null)
			sawNull = true;

		return !sawNull;
	}

	public void remove(E op) {
		markDirty();

		removeEdges(op, mFromEdges, mToEdges);
		removeEdges(op, mToEdges, mFromEdges);

		removeEdges(op, mSoftFromEdges, mSoftToEdges);
		removeEdges(op, mSoftToEdges, mSoftFromEdges);

		// Remove the operator from nodes
		mOps.remove(op);
		mKeys.remove(op.getId());
	}

	public void trimBelow(E op) {
		trimBelow(getSuccessors(op));
	}

	private void trimBelow(List<E> ops) {
		if (ops != null) {
			// Make a copy because we'll be messing with the underlying list.
			List<E> copy = new ArrayList<E>(ops);
			for (E op : copy) {
				trimBelow(getSuccessors(op));
				remove(op);
			}
		}
	}

	public void trimAbove(E op) {
		List<E> predecessors = new ArrayList<E>(getPredecessors(op));
		IndexHelper<E> indexHelper = new IndexHelper<E>(predecessors);
		trimAbove(predecessors);
		for (E predecessor : predecessors) {
			try {
				op.rewire(predecessor, indexHelper.getIndex(predecessor), null, true);
			} catch (PlanException pe) {

				throw new RuntimeException("Encountered problems with rewiring operators.", pe);
			}
		}
	}

	private void trimAbove(List<E> ops) {
		if (ops != null) {
			List<E> copy = new ArrayList<E>(ops);
			for (E op : copy) {
				trimAbove(getPredecessors(op));
				remove(op);
			}
		}
	}

	public List<E> getPredecessors(E op) {
		return mToEdges.get(op);
	}

	public List<E> getSuccessors(E op) {
		return mFromEdges.get(op);
	}

	public List<E> getSoftLinkPredecessors(E op) {
		return mSoftToEdges.get(op);
	}

	public List<E> getSoftLinkSuccessors(E op) {
		return mSoftFromEdges.get(op);
	}

	public boolean pathExists(E from, E to) {
		List<E> successors = getSuccessors(from);
		if (successors == null || successors.size() == 0) {
			return false;
		}
		for (E successor : successors) {
			if (successor.equals(to) || pathExists(successor, to)) {
				return true;
			}
		}
		return false;
	}

	public Iterator<E> iterator() {
		return mOps.keySet().iterator();
	}

	private void markDirty() {
		mRoots.clear();
		mLeaves.clear();
	}

	private void removeEdges(E op, MultiMap<E, E> fromMap, MultiMap<E, E> toMap) {
		Collection<E> c = fromMap.get(op);
		if (c == null)
			return;

		ArrayList<E> al = new ArrayList<E>(c);
		Iterator<E> i = al.iterator();
		while (i.hasNext()) {
			E to = i.next();
			toMap.remove(to, op);
			fromMap.remove(op, to);
		}
	}

	private void checkInPlan(E op) throws PlanException {
		if (mOps.get(op) == null) {
			PlanException pe = new PlanException("Attempt to connect operator " + op.getName()
					+ " which is not in the plan.");
			log.error(pe.getMessage());
			throw pe;
		}
	}


	public OperatorPlan<E> merge(OperatorPlan<E> inpPlan) throws PlanException {
		return doMerge(inpPlan, false);
	}


	public OperatorPlan<E> mergeSharedPlan(OperatorPlan<E> inpPlan) throws PlanException {
		return doMerge(inpPlan, true);
	}

	private OperatorPlan<E> doMerge(OperatorPlan<E> inpPlan, boolean allowSharedPlan) throws PlanException {
		Map<E, ElementKey> inpOps = inpPlan.mOps;
		Set<E> curOpsKeySet = mOps.keySet();
		for (Map.Entry<E, ElementKey> mapEnt : inpOps.entrySet()) {
			if (curOpsKeySet.contains(mapEnt.getKey())) {
				if (!allowSharedPlan) {
					PlanException pe = new PlanException(
							"There are operators that are shared across the plans. Merge of "
									+ "mutually exclusive plans is the only supported merge.");
					log.error(pe.getMessage());
					throw pe;
				}
			} else {
				mOps.put(mapEnt.getKey(), mapEnt.getValue());
			}
		}

		Map<ElementKey, E> inpKeys = inpPlan.mKeys;
		Set<ElementKey> curOKKeySet = mKeys.keySet();
		for (Map.Entry<ElementKey, E> mapEnt : inpKeys.entrySet()) {
			if (curOKKeySet.contains(mapEnt.getKey())) {
				if (!allowSharedPlan) {
					PlanException pe = new PlanException(
							"There are operators that are shared across the plans. Merge of "
									+ "mutually exclusive plans is the only supported merge.");
					log.error(pe.getMessage());
					throw pe;
				}
			} else {
				mKeys.put(mapEnt.getKey(), mapEnt.getValue());
			}
		}

		MultiMap<E, E> inpFromEdges = inpPlan.mFromEdges;
		Set<E> curFEKeySet = mFromEdges.keySet();
		for (E fromEdg : inpFromEdges.keySet()) {
			if (curFEKeySet.contains(fromEdg) && !allowSharedPlan) {
				PlanException pe = new PlanException("There are operators that are shared across the plans. Merge of "
						+ "mutually exclusive plans is the only supported merge.");
				log.error(pe.getMessage());
				throw pe;
			}

			for (E e : inpFromEdges.get(fromEdg)) {
				if (mFromEdges.get(fromEdg) == null || !mFromEdges.get(fromEdg).contains(e)) {
					mFromEdges.put(fromEdg, e);
				}
			}
		}

		MultiMap<E, E> inpToEdges = inpPlan.mToEdges;
		Set<E> curTEKeySet = mToEdges.keySet();
		for (E toEdg : inpToEdges.keySet()) {
			if (curTEKeySet.contains(toEdg) && !allowSharedPlan) {
				PlanException pe = new PlanException("There are operators that are shared across the plans. Merge of "
						+ "mutually exclusive plans is the only supported merge.");
				log.error(pe.getMessage());
				throw pe;
			}

			for (E e : inpToEdges.get(toEdg)) {
				if (mToEdges.get(toEdg) == null || !mToEdges.get(toEdg).contains(e)) {
					mToEdges.put(toEdg, e);
				}
			}
		}

		markDirty();
		return this;
	}


	public void addAsLeaf(E leaf) throws PlanException {
		List<E> ret = new ArrayList<E>();
		for (E operator : getLeaves()) {
			ret.add(operator);
		}
		add(leaf);
		for (E oper : ret) {
			connect(oper, leaf);
		}
	}

	public boolean isSingleLeafPlan() {
		List<E> tmpList = getLeaves();
		return tmpList.size() == 1;
	}

	public int size() {
		return mKeys.size();
	}


	public void insertBetween(E after, E newNode, E before) throws PlanException {
		doInsertBetween(after, newNode, before, true);
	}


	public void doInsertBetween(E after, E newNode, E before, boolean rewire) throws PlanException {
		checkInPlan(newNode);
		List<E> newNodePreds = getPredecessors(newNode);
		// assuming that the newNode has zero or one predecessor
		E newNodePred = (newNodePreds == null ? null : newNodePreds.get(0));
		if (!replaceNode(after, newNode, before, mFromEdges) || !replaceNode(before, newNode, after, mToEdges)) {
			int errCode = 1094;
			String msg = "Attempt to insert between two nodes " + "that were not connected.";
			PlanException pe = new PlanException(msg, errCode, NestException.INPUT);
			throw pe;
		}
		mFromEdges.put(newNode, before);
		mToEdges.put(newNode, after);

		if (rewire) {
			if ((newNodePred != null) && !(newNodePred.equals(after))) {
				newNode.rewire(newNodePred, 0, after, true);
			}
			IndexHelper<E> indexHelper = new IndexHelper<E>(getPredecessors(newNode));
			before.rewire(after, indexHelper.getIndex(after), newNode, false);
		}
	}

	// replaces (src -> dst) entry in multiMap with (src -> replacement)
	private boolean replaceNode(E src, E replacement, E dst, MultiMap<E, E> multiMap) {
		if (multiMap == null)
			return false;

		if (src == null)
			return false;

		List<E> nodes = (ArrayList<E>) multiMap.get(src);
		if (nodes == null) {
			// we need to add replacement to the multimap as long as replacement != null
			if (replacement == null) {
				return false;
			} else if (dst == null) {
				ArrayList<E> replacementNodes = new ArrayList<E>();
				replacementNodes.add(replacement);
				multiMap.put(src, replacementNodes);
				return true;
			} else {
				return false;
			}
		}

		if (dst == null)
			return false;

		boolean replaced = false;
		ArrayList<E> replacementNodes = new ArrayList<E>();
		for (int i = 0; i < nodes.size(); ++i) {
			E to = nodes.get(i);
			if (to.equals(dst)) {
				replaced = true;
				if (replacement != null) {
					replacementNodes.add(replacement);
				}
			} else {
				replacementNodes.add(to);
			}
		}

		if (replaced) {
			multiMap.removeKey(src);
			if (replacementNodes.size() > 0) {
				multiMap.put(src, replacementNodes);
			}
		}
		return replaced;
	}

	/**
	 * Replace an existing node in the graph with a new node. The new node will be connected to all the nodes the old
	 * node was. The old node will be removed. The new node is assumed to have no incoming or outgoing edges
	 * 
	 * @param oldNode
	 *            Node to be replaced
	 * @param newNode
	 *            Node to add in place of oldNode
	 * @throws PlanException
	 */
	public void replace(E oldNode, E newNode) throws PlanException {
		checkInPlan(oldNode);
		add(newNode);
		List<E> oldNodeSuccs = (getSuccessors(oldNode) == null ? null : new ArrayList<E>(getSuccessors(oldNode)));
		List<IndexHelper<E>> indexHelpers = new ArrayList<IndexHelper<E>>();
		if (oldNodeSuccs != null) {
			for (int i = 0; i < oldNodeSuccs.size(); ++i) {
				E oldNodeSucc = oldNodeSuccs.get(i);
				indexHelpers.add(new IndexHelper<E>(new ArrayList<E>(getPredecessors(oldNodeSucc))));
			}
		}

		mToEdges = generateNewMap(oldNode, newNode, mToEdges);
		mFromEdges = generateNewMap(oldNode, newNode, mFromEdges);

		// ensure that the oldNode's successors are rewired
		if (oldNodeSuccs != null) {
			for (int i = 0; i < oldNodeSuccs.size(); ++i) {
				E oldNodeSucc = oldNodeSuccs.get(i);
				oldNodeSucc.rewire(oldNode, indexHelpers.get(i).getIndex(oldNode), newNode, true);
			}
		}
		remove(oldNode);

	}

	private MultiMap<E, E> generateNewMap(E oldNode, E newNode, MultiMap<E, E> mm) {
		// First, replace the key
		Collection<E> targets = mm.get(oldNode);
		if (targets != null) {
			mm.removeKey(oldNode);
			mm.put(newNode, targets);
		}

		MultiMap<E, E> newMap = new MultiMap<E, E>(mm.size());
		for (E key : mm.keySet()) {
			Collection<E> c = mm.get(key);
			ArrayList<E> al = new ArrayList<E>(c);
			for (int i = 0; i < al.size(); i++) {
				if (al.get(i) == oldNode)
					al.set(i, newNode);
			}
			newMap.put(key, al);
		}
		return newMap;
	}


	public void removeAndReconnect(E node) throws PlanException {
		List<E> preds = getPredecessors(node);
		E pred = null;
		if (preds != null) {
			if (preds.size() > 1) {
				int errCode = 1095;
				String msg = "Attempt to remove " + " and reconnect for node with multiple predecessors.";
				PlanException pe = new PlanException(msg, errCode, NestException.INPUT);
				throw pe;
			}
			pred = preds.get(0);
			disconnect(pred, node);
		}

		int oldPos = -1;
		int newPos = -1;

		List<E> succs = getSuccessors(node);
		E succ = null;
		if (succs != null) {
			if (succs.size() > 1) {
				int errCode = 1095;
				String msg = "Attempt to remove " + " and reconnect for node with multiple successors.";
				PlanException pe = new PlanException(msg, errCode, NestException.INPUT);
				throw pe;
			}
			succ = succs.get(0);
			List<E> plst = getPredecessors(succ);
			for (int i = 0; i < plst.size(); i++) {
				if (plst.get(i).equals(node)) {
					oldPos = i;
				}
			}
			disconnect(node, succ);
		}

		remove(node);
		if (pred != null && succ != null) {
			connect(pred, succ);
			List<E> plst = getPredecessors(succ);
			for (int i = 0; i < plst.size(); i++) {
				if (plst.get(i).equals(pred)) {
					newPos = i;
				}
			}

			if (oldPos < 0 || newPos < 0) {
				throw new PlanException("Invalid position index: " + oldPos + " : " + newPos);
			}

			if (oldPos != newPos) {
				List<E> nlst = new ArrayList<E>();
				for (int i = 0; i < plst.size(); i++) {
					E nod = plst.get(i);
					if (i == oldPos) {
						nlst.add(pred);
					}
					if (i == newPos)
						continue;
					nlst.add(nod);
				}

				if (nlst.size() != plst.size()) {
					throw new PlanException("Invalid list size: " + nlst.size() + " : " + plst.size());
				}

				mToEdges.removeKey(succ);
				mToEdges.put(succ, nlst);
			}

			succ.rewire(node, oldPos, pred, true);
		}
	}

	private void reconnectSuccessors(E node, boolean successorRequired, boolean removeNode) throws PlanException {
		// Before:
		// A (predecessor (only one) )
		// / |
		// X B(nodeB) Y(some predecessor of a Cn)
		// / | \ /
		// C1 C2 C3 ... (Successors)
		// should become
		// After:
		// ___ A Y
		// / / | \ /
		// X C1 C2 C3 ...
		// the variable names are from above example

		E nodeB = node;
		List<E> preds = getPredecessors(nodeB);
		// checking pre-requisite conditions
		if (preds == null || preds.size() != 1) {
			Integer size = null;
			if (preds != null)
				size = preds.size();
			int errCode = 1096;
			String msg = "Attempt to remove " + " and reconnect for node with  " + size + " predecessors.";
			PlanException pe = new PlanException(msg, errCode, NestException.INPUT);
			throw pe;
		}

		// A and C
		E nodeA = preds.get(0);
		Collection<E> nodeC = (mFromEdges.get(nodeB) == null ? null : new ArrayList<E>(mFromEdges.get(nodeB)));

		// checking pre-requisite conditions
		if (successorRequired) {
			if (nodeC == null || nodeC.size() == 0) {
				int errCode = 1096;
				String msg = "Attempt to remove " + " and reconnect for node with no successors.";
				PlanException pe = new PlanException(msg, errCode, NestException.INPUT);
				throw pe;
			}
		}

		List<IndexHelper<E>> indexHelpers = new ArrayList<IndexHelper<E>>();
		if (nodeC != null) {
			for (int i = 0; i < nodeC.size(); ++i) {
				E c = ((List<E>) nodeC).get(i);
				indexHelpers.add(new IndexHelper<E>(new ArrayList<E>(getPredecessors(c))));
			}
		}

		// replace B in A.succesors and add B.successors(ie C) to it
		replaceAndAddSucessors(nodeA, nodeB);

		// for all C(succs) , replace B(node) in predecessors, with A(pred)
		if (nodeC != null) {
			for (int i = 0; i < nodeC.size(); ++i) {
				E c = ((List<E>) nodeC).get(i);
				Collection<E> sPreds = mToEdges.get(c);
				ArrayList<E> newPreds = new ArrayList<E>(sPreds.size());
				for (E p : sPreds) {
					if (p == nodeB) {
						// replace
						newPreds.add(nodeA);
					} else {
						newPreds.add(p);
					}
				}
				mToEdges.removeKey(c);
				mToEdges.put(c, newPreds);

			}
		}

		if (removeNode) {
			remove(nodeB);
		} else {
			// make sure that the node does not have any dangling from and to edges
			mFromEdges.removeKey(nodeB);
			mToEdges.removeKey(nodeB);
		}

		// ensure that any existing successor of nodeB is rewired to have nodeA in place of nodeB
		if (nodeC != null) {
			for (int i = 0; i < nodeC.size(); ++i) {
				E c = ((List<E>) nodeC).get(i);
				c.rewire(nodeB, indexHelpers.get(i).getIndex(nodeB), nodeA, true);
			}
		}
	}

	private void reconnectPredecessors(E node, boolean predecessorRequired, boolean removeNode) throws PlanException {
		// Before:
		// C1 C2 C3 ... (Predecessors)
		// \ | / \
		// X B(nodeB) Y(some successor of a Cn)
		// \ |
		// A (successor (only one) )

		// should become
		// After:
		// X C1 C2 C3 ...
		// \ \ | / \
		// A Y
		// the variable names are from above example

		E nodeB = node;
		List<E> nodeBsuccessors = getSuccessors(nodeB);
		// checking pre-requisite conditions
		if (nodeBsuccessors == null || nodeBsuccessors.size() != 1) {
			Integer size = null;
			if (nodeBsuccessors != null)
				size = nodeBsuccessors.size();

			int errCode = 1096;
			String msg = "Attempt to remove " + " and reconnect for node with  " + size + " successors.";
			PlanException pe = new PlanException(msg, errCode, NestException.INPUT);
			throw pe;
		}

		// A and C
		E nodeA = nodeBsuccessors.get(0);
		Collection<E> nodeC = (mToEdges.get(nodeB) == null ? null : new ArrayList<E>(mToEdges.get(nodeB)));

		// checking pre-requisite conditions
		if (predecessorRequired) {
			if (nodeC == null || nodeC.size() == 0) {
				int errCode = 1096;
				String msg = "Attempt to remove " + " and reconnect for node with no predecessors.";
				PlanException pe = new PlanException(msg, errCode, NestException.INPUT);
				throw pe;
			}
		}

		// replace B in A.predecessors and add B.predecessors(ie C) to it
		replaceAndAddPredecessors(nodeA, nodeB);

		// for all C(predecessors) , replace B(node) in successors, with A(successor)
		if (nodeC != null) {

			for (E c : nodeC) {
				Collection<E> sPreds = mFromEdges.get(c);
				ArrayList<E> newPreds = new ArrayList<E>(sPreds.size());
				for (E p : sPreds) {
					if (p == nodeB) {
						// replace
						newPreds.add(nodeA);
					} else {
						newPreds.add(p);
					}
				}
				mFromEdges.removeKey(c);
				mFromEdges.put(c, newPreds);

				// rewire nodeA
				nodeA.rewire(nodeB, 0, c, true);
			}
		}

		if (removeNode) {
			remove(nodeB);
		} else {
			// make sure that the node does not have any dangling from and to edges
			mFromEdges.removeKey(nodeB);
			mToEdges.removeKey(nodeB);
		}
	}

	private void replaceAndAddSucessors(E node, E successor) throws PlanException {
		Collection<E> oldSuccessors = mFromEdges.get(node);
		Collection<E> replacementSuccessors = mFromEdges.get(successor);
		ArrayList<E> newSuccessors = new ArrayList<E>();
		for (E s : oldSuccessors) {
			if (s == successor) {
				if (replacementSuccessors != null) {
					newSuccessors.addAll(replacementSuccessors);
				}
			} else {
				newSuccessors.add(s);
			}
		}
		mFromEdges.removeKey(node);
		if (!newSuccessors.isEmpty()) {
			mFromEdges.put(node, newSuccessors);
		}
	}

	private void replaceAndAddPredecessors(E node, E predecessor) throws PlanException {
		Collection<E> oldPredecessors = mToEdges.get(node);
		Collection<E> replacementPredecessors = mToEdges.get(predecessor);
		ArrayList<E> newPredecessors = new ArrayList<E>();
		for (E p : oldPredecessors) {
			if (p == predecessor) {
				if (replacementPredecessors != null) {
					newPredecessors.addAll(replacementPredecessors);
				}
			} else {
				newPredecessors.add(p);
			}
		}
		mToEdges.removeKey(node);
		if (!newPredecessors.isEmpty()) {
			mToEdges.put(node, newPredecessors);
		}
	}

	public void removeAndReconnectMultiSucc(E node) throws PlanException {
		reconnectSuccessors(node, true, true);
	}

	public void dump(PrintStream ps) {
		ps.println("Ops");
		for (E op : mOps.keySet()) {
			ps.println(op.getName());
		}
		ps.println("from edges");
		for (E op : mFromEdges.keySet()) {
			for (E to : mFromEdges.get(op)) {
				ps.println(op.getName() + " -> " + to.getName());
			}
		}
		ps.println("to edges");
		for (E op : mToEdges.keySet()) {
			for (E to : mToEdges.get(op)) {
				ps.println(op.getName() + " -> " + to.getName());
			}
		}
	}


	public void swap(E first, E second) throws PlanException {
		E firstNode = first;
		E secondNode = second;

		if (firstNode == null) {
			int errCode = 1092;
			String msg = "First operator in swap is null. Cannot swap null operators.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		if (secondNode == null) {
			int errCode = 1092;
			String msg = "Second operator in swap is null. Cannot swap null operators.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		checkInPlan(firstNode);
		checkInPlan(secondNode);

		List<E> firstNodePredecessors = (ArrayList<E>) mToEdges.get(firstNode);

		if (firstNodePredecessors != null && firstNodePredecessors.size() > 1) {
			int errCode = 1093;
			String msg = "Swap supports swap of operators with at most one input." + " Found first operator with "
					+ firstNodePredecessors.size() + " inputs.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		List<E> firstNodeSuccessors = (ArrayList<E>) mFromEdges.get(firstNode);

		if (firstNodeSuccessors != null && firstNodeSuccessors.size() > 1) {
			int errCode = 1093;
			String msg = "Swap supports swap of operators with at most one output." + " Found first operator with "
					+ firstNodeSuccessors.size() + " outputs.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		List<E> secondNodePredecessors = (ArrayList<E>) mToEdges.get(secondNode);

		if (secondNodePredecessors != null && secondNodePredecessors.size() > 1) {
			int errCode = 1093;
			String msg = "Swap supports swap of operators with at most one input." + " Found second operator with "
					+ secondNodePredecessors.size() + " inputs.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		List<E> secondNodeSuccessors = (ArrayList<E>) mFromEdges.get(secondNode);

		if (secondNodeSuccessors != null && secondNodeSuccessors.size() > 1) {
			int errCode = 1093;
			String msg = "Swap supports swap of operators with at most one output." + " Found second operator with "
					+ secondNodeSuccessors.size() + " outputs.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		E firstNodePredecessor = null;
		E firstNodeSuccessor = null;
		E secondNodePredecessor = null;
		E secondNodeSuccessor = null;

		if (firstNodePredecessors != null) {
			firstNodePredecessor = firstNodePredecessors.get(0);
		}

		if (firstNodeSuccessors != null) {
			firstNodeSuccessor = firstNodeSuccessors.get(0);
		}

		if (secondNodePredecessors != null) {
			secondNodePredecessor = secondNodePredecessors.get(0);
		}

		if (secondNodeSuccessors != null) {
			secondNodeSuccessor = secondNodeSuccessors.get(0);
		}

		boolean immediateNodes = false;

		if ((firstNodeSuccessor == secondNode) && (secondNodePredecessor == firstNode)) {
			immediateNodes = true;
		} else if ((secondNodeSuccessor == firstNode) && (firstNodePredecessor == secondNode)) {
			immediateNodes = true;
			// swap the firstNode and secondNode
			E tmpNode = firstNode;
			firstNode = secondNode;
			secondNode = tmpNode;

			// swap the predecessor and successor nodes
			tmpNode = firstNodePredecessor;
			firstNodePredecessor = secondNodePredecessor;
			secondNodePredecessor = tmpNode;

			tmpNode = firstNodeSuccessor;
			firstNodeSuccessor = secondNodeSuccessor;
			secondNodeSuccessor = tmpNode;
		}

		if (immediateNodes) {
			// Replace the predecessors and successors of first and second in their respective edge lists
			replaceNode(firstNode, secondNodeSuccessor, firstNodeSuccessor, mFromEdges);
			replaceNode(firstNode, secondNode, firstNodePredecessor, mToEdges);
			replaceNode(secondNode, firstNode, secondNodeSuccessor, mFromEdges);
			replaceNode(secondNode, firstNodePredecessor, secondNodePredecessor, mToEdges);

			// rewire the two nodes
			secondNode.rewire(firstNode, 0, firstNodePredecessor, true);
			firstNode.rewire(firstNodePredecessor, 0, secondNode, false);

		} else {
			// Replace the predecessors and successors of first and second in their respective edge lists
			replaceNode(firstNode, secondNodeSuccessor, firstNodeSuccessor, mFromEdges);
			replaceNode(firstNode, secondNodePredecessor, firstNodePredecessor, mToEdges);
			replaceNode(secondNode, firstNodeSuccessor, secondNodeSuccessor, mFromEdges);
			replaceNode(secondNode, firstNodePredecessor, secondNodePredecessor, mToEdges);

			// rewire the two nodes
			// here the use of true as the final parameter is questionable
			// there is no knowledge about how to use the projection maps
			firstNode.rewire(firstNodePredecessor, 0, secondNodePredecessor, true);
			secondNode.rewire(secondNodePredecessor, 0, firstNodePredecessor, true);
		}

		// Replace first with second in the edges list for first's predecessor and successor
		replaceNode(firstNodePredecessor, secondNode, firstNode, mFromEdges);
		replaceNode(firstNodeSuccessor, secondNode, firstNode, mToEdges);

		// Replace second with first in the edges list for second's predecessor and successor
		replaceNode(secondNodePredecessor, firstNode, secondNode, mFromEdges);
		replaceNode(secondNodeSuccessor, firstNode, secondNode, mToEdges);

		if (firstNodeSuccessor != null) {
			// here the use of true as the final parameter is questionable
			// there is no knowledge about how to use the projection maps
			firstNodeSuccessor.rewire(firstNode, 0, secondNode, true);
		}

		if (secondNodeSuccessor != null) {
			// here the use of true as the final parameter is questionable
			// there is no knowledge about how to use the projection maps
			secondNodeSuccessor.rewire(secondNode, 0, firstNode, true);
		}

		markDirty();
	}


	public void pushBefore(E first, E second, int inputNum) throws PlanException {
		E firstNode = first;
		E secondNode = second;

		if (firstNode == null) {
			int errCode = 1085;
			String msg = "First operator in pushBefore is null. Cannot pushBefore null operators.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		if (secondNode == null) {
			int errCode = 1085;
			String msg = "Second operator in pushBefore is null. Cannot pushBefore null operators.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		checkInPlan(firstNode);
		checkInPlan(secondNode);

		List<E> firstNodePredecessors = (mToEdges.get(firstNode) == null ? null : new ArrayList<E>(
				mToEdges.get(firstNode)));

		if (firstNodePredecessors == null || firstNodePredecessors.size() <= 1) {
			int size = (firstNodePredecessors == null ? 0 : firstNodePredecessors.size());
			int errCode = 1086;
			String msg = "First operator in pushBefore should have multiple inputs." + " Found first operator with "
					+ size + " inputs.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		if (inputNum >= firstNodePredecessors.size()) {
			int errCode = 1087;
			String msg = "The inputNum " + inputNum
					+ " should be lesser than the number of inputs of the first operator."
					+ " Found first operator with " + firstNodePredecessors.size() + " inputs.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		List<E> firstNodeSuccessors = (mFromEdges.get(firstNode) == null ? null : new ArrayList<E>(
				mFromEdges.get(firstNode)));

		if (firstNodeSuccessors == null) {
			int errCode = 1088;
			String msg = "First operator in pushBefore should have at least one output."
					+ " Found first operator with no outputs.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		List<E> secondNodePredecessors = (mToEdges.get(secondNode) == null ? null : new ArrayList<E>(
				mToEdges.get(secondNode)));

		if (secondNodePredecessors == null || secondNodePredecessors.size() > 1) {
			int size = (secondNodePredecessors == null ? 0 : secondNodePredecessors.size());
			int errCode = 1088;
			String msg = "Second operator in pushBefore should have one input." + " Found second operator with " + size
					+ " inputs.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		List<E> secondNodeSuccessors = (mFromEdges.get(secondNode) == null ? null : new ArrayList<E>(
				mFromEdges.get(secondNode)));

		// check for multiple edges from first to second
		int edgesFromFirstToSecond = 0;
		for (E node : firstNodeSuccessors) {
			if (node == secondNode) {
				++edgesFromFirstToSecond;
			}
		}

		if (edgesFromFirstToSecond == 0) {
			int errCode = 1089;
			String msg = "Second operator in pushBefore should be the successor of the First operator.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		} else if (edgesFromFirstToSecond > 1) {
			int errCode = 1090;
			String msg = "Second operator can have at most one incoming edge from First operator." + " Found "
					+ edgesFromFirstToSecond + " edges.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		// check if E (i.e., firstNode) can support multiple outputs before we short-circuit

		if (!firstNode.supportsMultipleOutputs()) {
			int numSecondNodeSuccessors = (secondNodeSuccessors == null ? 0 : secondNodeSuccessors.size());
			if ((firstNodeSuccessors.size() > 0) || (numSecondNodeSuccessors > 0)) {
				int errCode = 1091;
				String msg = "First operator does not support multiple outputs."
						+ " On completing the pushBefore operation First operator will end up with "
						+ (firstNodeSuccessors.size() + numSecondNodeSuccessors) + " edges.";
				throw new PlanException(msg, errCode, NestException.INPUT);
			}
		}

		// Assume that we have a graph which is like
		// A B C D
		// \ | | /
		// E
		// / | \
		// F G H
		// / | \
		// I J K
		//
		// Now pushBefore(E, G, 1)
		// This can be done using the following sequence of transformations
		// 1. Promote G's successors as E's successors using reconnectSuccessors(G)
		// 2. Insert G between B and E using insertBetween(B, G, E)
		// The graphs after each step
		// Step 1 - Note that G is standing alone
		// A B C D G
		// \ | | /
		// E
		// / / | \ \
		// F I J K H
		// Step 2
		// B
		// |
		// A G C D
		// \ | | /
		// E
		// / / | \ \
		// F I J K H

		reconnectSuccessors(secondNode, false, false);
		doInsertBetween(firstNodePredecessors.get(inputNum), secondNode, firstNode, false);

		// A note on the use of rewire
		// Rewire is used within reconnectPredecessors. However, rewire is explicitly turned off in insertBetween
		// The rewiring is done explicitly here to avoid exceptions that are generated due to precondition
		// violations in insertBetween
		secondNode.rewire(firstNode, inputNum, firstNodePredecessors.get(inputNum), true);
		firstNode.rewire(firstNodePredecessors.get(inputNum), 0, secondNode, false);

		markDirty();
		return;
	}


	public void pushAfter(E first, E second, int outputNum) throws PlanException {
		E firstNode = first;
		E secondNode = second;

		if (firstNode == null) {
			int errCode = 1085;
			String msg = "First operator in pushAfter is null. Cannot pushBefore null operators.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		if (secondNode == null) {
			int errCode = 1085;
			String msg = "Second operator in pushAfter is null. Cannot pushBefore null operators.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		checkInPlan(firstNode);
		checkInPlan(secondNode);

		List<E> firstNodePredecessors = (mToEdges.get(firstNode) == null ? null : new ArrayList<E>(
				mToEdges.get(firstNode)));

		if (firstNodePredecessors == null) {
			int errCode = 1088;
			String msg = "First operator in pushAfter should have at least one input."
					+ " Found first operator with no inputs.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		List<E> firstNodeSuccessors = (mFromEdges.get(firstNode) == null ? null : new ArrayList<E>(
				mFromEdges.get(firstNode)));

		if (firstNodeSuccessors == null || firstNodeSuccessors.size() <= 1) {
			int size = (firstNodeSuccessors == null ? 0 : firstNodeSuccessors.size());
			int errCode = 1086;
			String msg = "First operator in pushAfter should have multiple outputs." + " Found first operator with "
					+ size + " outputs.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		if (outputNum >= firstNodeSuccessors.size()) {
			int errCode = 1087;
			String msg = "The outputNum " + outputNum
					+ " should be lesser than the number of outputs of the first operator."
					+ " Found first operator with " + firstNodeSuccessors.size() + " outputs.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		List<E> secondNodePredecessors = (mToEdges.get(secondNode) == null ? null : new ArrayList<E>(
				mToEdges.get(secondNode)));

		List<E> secondNodeSuccessors = (mFromEdges.get(secondNode) == null ? null : new ArrayList<E>(
				mFromEdges.get(secondNode)));

		if (secondNodeSuccessors == null || secondNodeSuccessors.size() > 1) {
			int size = (secondNodeSuccessors == null ? 0 : secondNodeSuccessors.size());
			int errCode = 1088;
			String msg = "Second operator in pushAfter should have one output." + " Found second operator with " + size
					+ " outputs.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		// check for multiple edges from second to first
		int edgesFromSecondToFirst = 0;
		for (E node : secondNodeSuccessors) {
			if (node == firstNode) {
				++edgesFromSecondToFirst;
			}
		}

		if (edgesFromSecondToFirst == 0) {
			int errCode = 1089;
			String msg = "Second operator in pushAfter should be the predecessor of the First operator.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		} else if (edgesFromSecondToFirst > 1) {
			int errCode = 1090;
			String msg = "Second operator can have at most one outgoing edge from First operator." + " Found "
					+ edgesFromSecondToFirst + " edges.";
			throw new PlanException(msg, errCode, NestException.INPUT);
		}

		// check if E (i.e., firstNode) can support multiple outputs before we short-circuit

		if (!firstNode.supportsMultipleInputs()) {
			int numSecondNodePredecessors = (secondNodePredecessors == null ? 0 : secondNodePredecessors.size());
			// if((firstNodePredecessors.size() > 0) || (numSecondNodePredecessors > 0)) {
			if (numSecondNodePredecessors > 1) {
				int errCode = 1091;
				String msg = "First operator does not support multiple inputs."
						+ " On completing the pushAfter operation First operator will end up with "
						+ (firstNodePredecessors.size() + numSecondNodePredecessors) + " edges.";
				throw new PlanException(msg, errCode, NestException.INPUT);
			}
		}

		// Assume that we have a graph which is like
		// A B C D
		// \ | | /
		// E
		// |
		// G
		// / | \
		// I J K
		//
		// Now pushAfter(G, E, 1)
		// This can be done using the following sequence of transformations
		// 1. Promote E's predecessors as G's predecessors using reconnectPredecessors(E)
		// 2. Insert E between G and J using insertBetween(G, E, J)
		// The graphs after each step
		// Step 1 - Note that E is standing alone
		// A B C D E
		// \ | | /
		// G
		// / | \
		// I J K
		// Step 2
		// A B C D
		// \ | | /
		// G
		// / | \
		// I E K
		// |
		// J

		reconnectPredecessors(secondNode, false, false);
		doInsertBetween(firstNode, secondNode, firstNodeSuccessors.get(outputNum), false);
		// A note on the use of rewire
		// Rewire is used within reconnectPredecessors. However, rewire is explicitly turned off in insertBetween
		// The rewiring is done explicitly here to avoid exceptions that are generated due to precodition
		// violations in insertBetween

		if (secondNodePredecessors != null) {
			for (int i = 0; i < secondNodePredecessors.size(); ++i) {
				E secondNodePred = secondNodePredecessors.get(i);
				secondNode.rewire(secondNodePred, i, firstNode, true);
			}
		}

		firstNodeSuccessors.get(outputNum).rewire(firstNode, 0, secondNode, false);

		markDirty();
		return;

	}

	/*
	 * A helper class that computes the index of each reference in a list for a quick lookup
	 */
	public static class IndexHelper<E> {

		private Map<E, Integer> mIndex = null;

		public IndexHelper(List<E> list) {
			if (list != null) {
				if (list.size() != 0) {
					mIndex = new HashMap<E, Integer>();
					for (int i = 0; i < list.size(); ++i) {
						mIndex.put(list.get(i), i);
					}
				}
			}
		}

		public int getIndex(E e) {
			if (mIndex == null || mIndex.size() == 0)
				return -1;
			return mIndex.get(e);
		}
	}

}
