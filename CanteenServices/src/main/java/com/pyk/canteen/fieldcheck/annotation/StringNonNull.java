package com.pyk.canteen.fieldcheck.annotation;

import com.pyk.canteen.fieldcheck.Handler;
import com.pyk.canteen.fieldcheck.handler.StringNonNullHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字符串不能为空
 *
 * @author 李凤强
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Handler(StringNonNullHandler.class)
public @interface StringNonNull {
    /**
     * 错误信息
     */
    String value() default "";

    /**
     * 是否调用{@link String#trim()}函数
     */
    boolean trim() default true;
}
