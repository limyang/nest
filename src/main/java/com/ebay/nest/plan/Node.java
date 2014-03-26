package com.ebay.nest.plan;

import java.util.List;

public interface Node {

	List<? extends Node> getChildren();

	String getName();
}
