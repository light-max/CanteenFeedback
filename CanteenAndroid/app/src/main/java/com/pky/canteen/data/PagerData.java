package com.pky.canteen.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class PagerData<T> {
    private Pager pager;

    @SerializedName("data")
    private List<T> list;

    public Pager getPager() {
        return pager;
    }

    public List<T> getList() {
        return list;
    }
}
