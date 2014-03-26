package com.ebay.nest.parser;

import java.util.List;

public interface Node {

	List<? extends Node> getChildren();

	String getName();
}