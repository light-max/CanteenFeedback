package com.pyk.canteen.fieldcheck.annotation;

import com.pyk.canteen.fieldcheck.Handler;
import com.pyk.canteen.fieldcheck.handler.StringRegexHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 正则表达式
 *
 * @author 李凤强
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Handler(StringRegexHandler.class)
public @interface StringRegex {
    /**
     * 错误信息
     */
    String msg() default "";

    String value();
}
