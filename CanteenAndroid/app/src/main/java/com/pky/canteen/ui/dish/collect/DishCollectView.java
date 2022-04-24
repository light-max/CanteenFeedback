package com.pky.canteen.ui.dish.collect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;

public class DishCollectView extends BaseView<DishCollectFragment> {
    private RecyclerView recycler;
    private DishCollectAdapter adapter;
    private TextView count;
    private Button collect;

    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        recycler = get(R.id.recycler);
        collect = get(R.id.collect);
        adapter = new DishCollectAdapter();
        recycler.setAdapter(adapter);
        View view = View.inflate(base.getContext(), R.layout.view_remark_count_text, null);
        count = view.findViewById(R.id.text);
        adapter.setHeadView(view);
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public DishCollectAdapter getAdapter() {
        return adapter;
    }

    public TextView getCount() {
        return count;
    }

    public Button getCollect() {
        return collect;
    }

    public void setCollectState(boolean collected) {
        if (collected) {
            getCollect().setText("取消收藏");
        } else {
            getCollect().setText("收藏");
        }
    }
}
