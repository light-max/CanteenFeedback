package com.pky.canteen.ui.dish.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.viewpager2.widget.ViewPager2;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.data.result.DishDetails;
import com.pky.canteen.ui.home.opinionsend.OpinionSendActivity;

import java.util.List;

public class DishDetailsView extends BaseView<DishDetailsFragment> {
    private ViewPager2 pager;
    private ImagesAdapter adapter;
    private TextView page;

    @SuppressLint("DefaultLocale")
    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        click(R.id.stall, () -> {
            base.toast("档口详情");
        });
        pager = get(R.id.pager);
        page = get(R.id.page);
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onPageSelected(int position) {
                page.setText(String.format("%d/%d",
                        position + 1,
                        adapter != null ? adapter.getItemCount() : 0));
            }
        });
        page.setText(String.format("%d/%d",
                1,
                adapter != null ? adapter.getItemCount() : 0));
        click(R.id.opinion, () -> {
            OpinionSendActivity.start(base.getContext(), base.map("id"));
        });
    }

    public void setData(DishDetails data) {
        TextView name = get(R.id.name);
        TextView cuisine = get(R.id.cuisine);
        TextView stallName = get(R.id.stall_name);
        TextView material = get(R.id.material);
        TextView des = get(R.id.des);
        name.setText(data.getName());
        cuisine.setText(data.getCuisine());
        stallName.setText(data.getStallName());
        material.setText(data.getMaterial());
        des.setText(data.getDes());


        List<String> images = data.getImages();
        if (images.isEmpty()) {
            images.add(data.getCover());
        }
        adapter = new ImagesAdapter(images);
        pager.setAdapter(adapter);
    }
}
