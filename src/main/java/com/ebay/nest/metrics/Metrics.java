package com.ebay.nest.metrics;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Metrics {

	String name() default "";

	String about() default "";

	String context();
}
