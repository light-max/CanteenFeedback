package com.pyk.canteen.fieldcheck.handler;

import com.pyk.canteen.fieldcheck.FieldCheckException;
import com.pyk.canteen.fieldcheck.annotation.StringNonNull;

import java.lang.annotation.Annotation;

/**
 * 字符串不为空的注解处理程序
 *
 * @author 李凤强
 */
public class StringNonNullHandler implements BaseHandler {
    @Override
    public void process(Object field, Annotation rule) throws FieldCheckException {
        String string = String.valueOf(field);
        StringNonNull a = (StringNonNull) rule;
        string = a.trim() ? string.trim() : string;
        if (string.isEmpty()) {
            throw new FieldCheckException(a.value());
        }
    }
}
