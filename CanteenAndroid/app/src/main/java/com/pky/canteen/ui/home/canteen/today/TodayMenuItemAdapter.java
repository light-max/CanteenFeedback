package com.pky.canteen.ui.home.canteen.today;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pky.canteen.R;
import com.pky.canteen.api.ExRequestBuilder;
import com.pky.canteen.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.pky.canteen.data.result.MenuItem;

public class TodayMenuItemAdapter extends SimpleSingleItemRecyclerAdapter<MenuItem> {
    private final int imageWidth;
    private final int imageHeight;

    public TodayMenuItemAdapter(int imageSize) {
        imageWidth = imageSize;
        imageHeight = (int) (imageSize * 0.8);
    }

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_today_menu;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, MenuItem data, int position) {
        ImageView image = holder.getImage(R.id.icon);
        ViewGroup.LayoutParams params = image.getLayoutParams();
        params.width = imageWidth;
        params.height = imageHeight;
        image.setLayoutParams(params);
        Glide.with(holder.itemView)
                .load(ExRequestBuilder.getUrl(data.getDishCover()))
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(image);
        holder.setText(R.id.name, data.getDishName());
    }
}
