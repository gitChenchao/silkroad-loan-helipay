package com.danning.silkroad.helipay.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 不参与签名
 */

@Target(FIELD)
@Retention(RUNTIME)
@Documented
public @interface SignExclude {
	String value() default "";
}
