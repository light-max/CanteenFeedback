package com.pky.canteen.api;

import com.pky.canteen.async.Async;
import com.pky.canteen.async.AsyncTaskError;
import com.pky.canteen.data.Res;
import com.pky.canteen.net.ResultError;
import com.pky.canteen.net.request.Method;
import com.pky.canteen.net.request.RequestBuilder;
import com.pky.canteen.net.result.Result;

import java.util.List;

public class ExRequestBuilder extends RequestBuilder {
    private static final String baseUrl = "http://10.0.2.2:8080";

    public static String getUrl(String url) {
        if (url.startsWith("http")) {
            return url;
        } else if (url.startsWith("/")) {
            return baseUrl + url;
        } else {
            return baseUrl + "/" + url;
        }
    }

    @Override
    protected String modifyBuildUrl(String url) {
        return getUrl(url);
    }

    public <T> Async.Builder<List<T>> asyncList(Class<T> type) {
        return Async.<List<T>>builder().task(() -> {
            Result result = execute();
            ResultError error = result.error();
            if (error == null) {
                ResultType rt = ResultType.list(type);
                Res<T> res = result.json(rt);
                if (res.isSuccess()) {
                    return res.getData();
                } else {
                    return new AsyncTaskError(res.getMessage(), null);
                }
            }
            return new AsyncTaskError(error.getMessage(), error.getException());
        });
    }

    public <T> Async.Builder<T> async(Class<T> type) {
        return Async.<T>builder().task(() -> {
            Result result = execute();
            ResultError error = result.error();
            if (error == null) {
                ResultType rt = ResultType.create(type);
                Res<T> res = result.json(rt);
                if (res.isSuccess()) {
                    return res.getData();
                } else {
                    return new AsyncTaskError(res.getMessage(), null);
                }
            }
            return new AsyncTaskError(error.getMessage(), error.getException());
        });
    }

    @Override
    public Async.Builder<?> async() {
        return Async.<Result>builder().task(() -> {
            Result result = execute();
            ResultError error = result.error();
            if (error == null) {
                Res<?> res = result.json(Res.class);
                if (res.isSuccess()) {
                    return null;
                } else {
                    return new AsyncTaskError(res.getMessage(), null);
                }
            }
            return new AsyncTaskError(error.getMessage(), error.getException());
        });
    }

    public static ExRequestBuilder get(String url) {
        return new ExRequestBuilder().method(Method.GET).url(url).as();
    }

    public static ExRequestBuilder post(String url) {
        return new ExRequestBuilder().method(Method.POST).url(url).as();
    }

    public static ExRequestBuilder put(String url) {
        return new ExRequestBuilder().method(Method.PUT).url(url).as();
    }

    public static ExRequestBuilder delete(String url) {
        return new ExRequestBuilder().method(Method.DELETE).url(url).as();
    }
}
