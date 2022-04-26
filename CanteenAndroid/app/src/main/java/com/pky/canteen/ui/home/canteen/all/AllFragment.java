package com.pky.canteen.ui.home.canteen.all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pky.canteen.R;
import com.pky.canteen.base.fragment.BaseFragment;
import com.pky.canteen.base.recycler.OnLoadMoreListener;

public class AllFragment extends BaseFragment<AllModel, AllView> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_canteen_all, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getModel().requestNewData().success(data -> {
            getV().getAdapter().setNewData(data.getList());
        }).run();

        getV().getRecycler().addOnScrollListener(new OnLoadMoreListener(listener -> {
            getModel().requestNextData()
                    .after(() -> listener.setLoadMoreIng(false))
                    .success(data -> {
                        if (data.getList().isEmpty()) {
                            toast("没有更多了");
                        } else {
                            getV().getAdapter().addNewData(data.getList());
                        }
                    }).run();
        }));
    }
}
