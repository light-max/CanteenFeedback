package com.pky.canteen.ui.stall.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.viewpager2.widget.ViewPager2;

import com.pky.canteen.R;
import com.pky.canteen.base.call.Base;
import com.pky.canteen.base.mvp.BaseView;
import com.pky.canteen.data.result.StallDetails;

import java.util.List;

public class StallDetailsView extends BaseView<StallDetailsFragment> {
    private ViewPager2 pager;
    private ImagesAdapter adapter;
    private TextView page;

    @SuppressLint("DefaultLocale")
    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        super.onViewCreated(base, savedInstanceState);
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
    }

    public void setData(StallDetails data) {
        TextView name = get(R.id.name);
        TextView des = get(R.id.des);
        name.setText(data.getName());
        des.setText(data.getDes());
        List<String> images = data.getImages();
        if (images.isEmpty()) {
            images.add(data.getCover());
        }
        adapter = new ImagesAdapter(images);
        pager.setAdapter(adapter);
    }
}
