package com.pky.canteen.base.call;

public interface ValueCall<T, E> {
    void call(T value, E e);
}
