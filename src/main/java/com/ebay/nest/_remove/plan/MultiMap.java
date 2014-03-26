package com.ebay.nest._remove.plan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiMap<K, V> implements Serializable {

	static final long serialVersionUID = 2L;

	protected Map<K, ArrayList<V>> mMap = null;

	public MultiMap() {
		mMap = new HashMap<K, ArrayList<V>>();
	}

	public MultiMap(int size) {
		mMap = new HashMap<K, ArrayList<V>>(size);
	}

	public void put(K key, V value) {
		ArrayList<V> list = mMap.get(key);
		if (list == null) {
			list = new ArrayList<V>();
			list.add(value);
			mMap.put(key, list);
		} else {
			list.add(value);
		}
	}

	public void put(K key, Collection<V> values) {
		ArrayList<V> list = mMap.get(key);
		if (list == null) {
			list = new ArrayList<V>(values);
			mMap.put(key, list);
		} else {
			list.addAll(values);
		}
	}

	public List<V> get(K key) {
		return mMap.get(key);
	}

	public V remove(K key, V value) {
		ArrayList<V> list = mMap.get(key);
		if (list == null)
			return null;

		Iterator<V> i = list.iterator();
		V keeper = null;
		while (i.hasNext()) {
			keeper = i.next();
			if (keeper.equals(value)) {
				i.remove();
				break;
			}
		}

		if (list.size() == 0) {
			mMap.remove(key);
		}

		return keeper;
	}

	public Collection<V> removeKey(K key) {
		return mMap.remove(key);
	}

	public Set<K> keySet() {
		return mMap.keySet();
	}

	public Collection<V> values() {
		Set<K> keys = mMap.keySet();
		int size = 0;
		for (K k : keys) {
			size += mMap.get(k).size();
		}
		Collection<V> values = new ArrayList<V>(size);
		for (K k : keys) {
			values.addAll(mMap.get(k));
		}
		return values;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		Set<K> keys = mMap.keySet();
		boolean hasNext = false;
		sb.append("{");
		for (K k : keys) {
			if (hasNext) {
				sb.append(",");
			} else {
				hasNext = true;
			}
			sb.append(k.toString() + "=");
			sb.append(mMap.get(k));
		}
		sb.append("}");
		return sb.toString();
	}

	public int size() {
		return mMap.size();
	}

	public boolean isEmpty() {
		return mMap.isEmpty();
	}

	public void clear() {
		mMap.clear();
	}

	public boolean containsKey(K key) {
		return mMap.containsKey(key);
	}

	public boolean containsValue(V val) {
		return mMap.containsValue(val);
	}
}
