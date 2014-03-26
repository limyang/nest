package com.ebay.nest.plan;

import java.util.HashMap;
import java.util.Map;

public class NodeIdGenerator {

	private Map<String, Long> scopeToIdMap;
	private static NodeIdGenerator theGenerator = new NodeIdGenerator();

	private NodeIdGenerator() {
		scopeToIdMap = new HashMap<String, Long>();
	}

	public static NodeIdGenerator getGenerator() {
		return theGenerator;
	}

	public long getNextNodeId(String scope) {
		Long val = scopeToIdMap.get(scope);

		long nextId = 0;

		if (val != null) {
			nextId = val.longValue();
		}

		scopeToIdMap.put(scope, nextId + 1);

		return nextId;
	}

	public static void reset(String scope) {
		theGenerator.scopeToIdMap.put(scope, 0L);
	}
}