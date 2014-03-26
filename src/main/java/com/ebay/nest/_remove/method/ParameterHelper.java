package com.ebay.nest._remove.method;

import java.util.List;
import java.util.Map;

public class ParameterHelper {

	private Map<String, Object> map;

	public ParameterHelper(Map<String, Object> map) {
		this.map = map;

	}

	@SuppressWarnings("unchecked")
	public int[] getJoinOnPos() {
		List<String> joinPosList = (List<String>) map.get("join_on");
		int size = joinPosList.size();
		int ret[] = new int[size];
		for (int i = 0; i < joinPosList.size(); i++) {
			String v = joinPosList.get(i);
			int pos = Integer.parseInt(v);
			ret[i] = pos - 1;
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public String[] getJoinOnNames() {
		List<String> joinPosList = (List<String>) map.get("join_on");
		return joinPosList.toArray(new String[0]);
	}

	public boolean isPositionMode() {
		if (!has("type")) {
			return false;
		}
		if (s("type").equals("position")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNameMode() {
		return !isPositionMode();
	}

	public boolean isEmpty() {
		return map == null || map.isEmpty();
	}

	public String s(String name) {
		return (String) map.get(name);
	}

	public int i(String name) {
		return Integer.parseInt((String) map.get(name));
	}

	public boolean has(String name) {
		return map.get(name) != null;
	}

	public Object get(String name) {
		return map.get(name);
	}

	public ColumnMode getColumnMode() {
		if (isPositionMode()) {
			return ColumnMode.POSITION_MODE;
		} else {
			return ColumnMode.NAME_MODE;
		}
	}
}
