package com.ebay.nest._remove.plan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.ebay.nest.Pair;
import com.ebay.nest.plan.Element;

public class PlanEdge extends MultiMap<Element, Element> {

	private static final long serialVersionUID = 1L;

	public PlanEdge() {
		super();
	}

	public PlanEdge(int size) {
		super(size);
	}

	public void put(Element key, Element value, int pos) {
		ArrayList<Element> list = mMap.get(key);
		if (list == null) {
			list = new ArrayList<Element>();
			if (pos != 0) {
				throw new IndexOutOfBoundsException("First edge cannot have position greater than 1");
			}
			list.add(value);
			mMap.put(key, list);
		} else {
			list.add(pos, value);
		}
	}

	public Pair<Element, Integer> removeWithPosition(Element key, Element value) {
		ArrayList<Element> list = mMap.get(key);
		if (list == null)
			return null;

		int index = -1;
		Iterator<Element> i = list.iterator();
		Element keeper = null;
		for (int j = 0; i.hasNext(); j++) {
			keeper = i.next();
			if (keeper == value) {
				i.remove();
				index = j;
				break;
			}
		}

		if (index == -1)
			return null;

		if (list.size() == 0) {
			mMap.remove(key);
		}

		return new Pair<Element, Integer>(keeper, index);
	}

	public PlanEdge shallowClone() {
		PlanEdge result = new PlanEdge();
		for (Map.Entry<Element, ArrayList<Element>> entry : mMap.entrySet()) {
			ArrayList<Element> list = new ArrayList<Element>();
			list.addAll(entry.getValue());
			result.put(entry.getKey(), list);
		}
		return result;
	}
}
