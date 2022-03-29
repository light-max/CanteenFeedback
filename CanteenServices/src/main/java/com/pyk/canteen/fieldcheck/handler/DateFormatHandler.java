package com.pyk.canteen.fieldcheck.handler;

import com.pyk.canteen.fieldcheck.FieldCheckException;
import com.pyk.canteen.fieldcheck.annotation.DateFormat;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 日期格式化检测程序
 */
public class DateFormatHandler implements BaseHandler {
    @Override
    public void process(Object field, Annotation rule) throws FieldCheckException {
        String s = String.valueOf(field);
        DateFormat a = (DateFormat) rule;
        try {
            LocalDate.parse(s, DateTimeFormatter.ofPattern(a.value()));
        } catch (DateTimeParseException e) {
            throw new FieldCheckException(a.msg());
        }
    }
}
