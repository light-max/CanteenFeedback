package com.pky.canteen.ui.home.canteen.today;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;

public class TodayView extends BaseView<TodayFragment> {
    private TodayMenuItemAdapter adapter;
    private RecyclerView recycler;
    private TextView empty;

    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        recycler = get(R.id.recycler);
        empty = get(R.id.empty);
        recycler.setLayoutManager(new GridLayoutManager(base.getContext(), 2));
        int windowWidth = base.getActivity().getWindowManager().getDefaultDisplay().getWidth();
        adapter = new TodayMenuItemAdapter(windowWidth / 2);
        recycler.setAdapter(adapter);
    }

    public TodayMenuItemAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public TextView getEmpty() {
        return empty;
    }
}
