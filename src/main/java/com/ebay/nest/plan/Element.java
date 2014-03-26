package com.ebay.nest.plan;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

abstract public class Element<V extends Visitor> implements Serializable, Comparable<Element<V>>, Cloneable {
	private static final long serialVersionUID = 1L;

	private static AtomicInteger seqId;

	protected transient String id;

	static {
		seqId = new AtomicInteger(0);
	}

	public Element() {
		id = String.valueOf(seqId.getAndIncrement());
	}

	public String getId() {
		return id;
	}

	public abstract boolean visit(V v);

	public abstract boolean supportsMultipleInputs();

	public abstract boolean supportsMultipleOutputs();

	public abstract String getName();

	public String toString() {
		return "(Name: " + getName() + " Element Id: " + getId() + ")";
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		else
			return false;
	}

	public int hashCode() {
		return id.hashCode();
	}

	public int compareTo(Element o) {
		return id.compareTo(o.id);
	}

	protected Object clone() throws CloneNotSupportedException {
		Object o = super.clone();
		Element opClone = (Element) o;
		opClone.id = id;
		return opClone;
	}

	public void rewire(Element<V> oldPred, int oldPredIndex, Element<V> newPred, boolean useOldPred)
			throws PlanException {
		if (oldPred == null) {
			return;
		}
	}

}
