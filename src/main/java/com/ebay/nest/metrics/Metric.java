package com.ebay.nest.metrics;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Metric {

	public enum Type {
		DEFAULT, COUNTER, GAUGE, TAG
	}

	String[] value() default {};

	String about() default "";

	String sampleName() default "Ops";

	String valueName() default "Time";

	boolean always() default false;

	Type type() default Type.DEFAULT;
}
