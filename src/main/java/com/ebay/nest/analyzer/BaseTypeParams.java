package com.ebay.nest.analyzer;

import java.io.Serializable;

import org.apache.hadoop.io.Writable;

import com.ebay.nest.serde.SerDeException;

public abstract class BaseTypeParams implements Writable, Serializable {

	private static final long serialVersionUID = 1L;

	public abstract void validateParams() throws SerDeException;

	public abstract void populateParams(String[] params) throws SerDeException;

	public abstract String toString();

	public void set(String[] params) throws SerDeException {
		populateParams(params);
		validateParams();
	}

	public boolean hasCharacterMaximumLength() {
		return false;
	}

	public int getCharacterMaximumLength() {
		return -1;
	}
}
