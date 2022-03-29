package com.pky.canteen.base.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseModel;
import com.pky.canteen.base.mvp.BaseView;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class BaseFragment<M extends BaseModel, V extends BaseView> extends Fragment implements Base {
    protected Handler mainHandler;
    protected M model;
    protected V view;
    protected Map<Object, Object> globalVariableMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainHandler = new Handler(Looper.getMainLooper());
        if (createMVP()) {
            model.onCreate(getLifecycle(), this, savedInstanceState);
            view.onCreate(getLifecycle(), this, savedInstanceState);
        }
    }

    private boolean createMVP() {
        try {
            ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
            assert superclass != null;
            Type[] types = superclass.getActualTypeArguments();
            Class<?>[] classes = new Class[]{
                    (Class<?>) types[0],
                    (Class<?>) types[1],
            };
            Constructor<?>[] constructors = new Constructor[]{
                    classes[0].getConstructor(),
                    classes[1].getConstructor(),
            };
            model = (M) constructors[0].newInstance();
            view = (V) constructors[1].newInstance();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (this.view != null) {
            this.view.onViewCreated(this, savedInstanceState);
        }
        if (this.model != null) {
            this.model.onViewCreated(this, savedInstanceState);
        }
    }

    protected void onBeforeDestroy() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onBeforeDestroy();
        model = null;
        view = null;
        if (globalVariableMap != null) {
            globalVariableMap.clear();
            globalVariableMap = null;
        }
    }

    @Override
    public Map<Object, Object> map() {
        if (globalVariableMap == null) {
            globalVariableMap = new HashMap<>();
        }
        return globalVariableMap;
    }

    public M getModel() {
        return model;
    }

    public V getV() {
        return view;
    }
}
