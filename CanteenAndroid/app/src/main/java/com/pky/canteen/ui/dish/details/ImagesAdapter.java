package com.pky.canteen.ui.dish.details;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pky.canteen.R;
import com.pky.canteen.api.ExRequestBuilder;
import com.pky.canteen.base.recycler.SimpleSingleItemRecyclerAdapter;

import java.util.List;

class ImagesAdapter extends SimpleSingleItemRecyclerAdapter<String> {
    public ImagesAdapter(List<String> data) {
        super(data);
    }

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_dish_details_image;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, String data, int position) {
        ImageView image = holder.getImage(R.id.image);
        Glide.with(holder.itemView)
                .load(ExRequestBuilder.getUrl(data))
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(image);
    }
}
