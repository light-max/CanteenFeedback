package com.pky.canteen.api;

import com.pky.canteen.data.Res;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author gyj
 */
class ResultType implements ParameterizedType {
    private final Class<?> raw;
    private final Type[] args;

    private ResultType(Class<?> raw, Type[] args) {
        this.raw = raw;
        this.args = args != null ? args : new Type[0];
    }

    /**
     * 创建单个
     */
    static ResultType create(Class<?> type) {
        return new ResultType(Res.class, new Class<?>[]{type});
    }

    static ResultType list(Class<?> type) {
        Type listType = new ResultType(List.class, new Class<?>[]{type});
        return new ResultType(Res.class, new Type[]{listType});
    }

    @NotNull
    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }

    @NotNull
    @Override
    public Type getRawType() {
        return raw;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
