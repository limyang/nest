package com.ebay.nest;

import java.io.Serializable;
import java.lang.reflect.Type;

public interface TypeInfo extends Type, Serializable {

	public String getFormat();

	public Class getJavaClass();

	public boolean isNullable();

}
