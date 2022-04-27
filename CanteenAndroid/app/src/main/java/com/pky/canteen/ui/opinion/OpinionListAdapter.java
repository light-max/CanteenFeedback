package com.pky.canteen.ui.opinion;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pky.canteen.R;
import com.pky.canteen.api.ExRequestBuilder;
import com.pky.canteen.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.pky.canteen.data.result.Opinion;

class OpinionListAdapter extends SimpleSingleItemRecyclerAdapter<Opinion> {
    @Override
    protected int getItemViewLayout() {
        return R.layout.item_opinion;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, Opinion data, int position) {
        Glide.with(holder.itemView)
                .load(ExRequestBuilder.getUrl(data.getDishCover()))
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(holder.getImage(R.id.cover));
        holder.setText(R.id.content, data.getContent())
                .setText(R.id.dish_name, "菜品：" + data.getDishName())
                .setText(R.id.stall_name, "档口：" + data.getStallName())
                .setText(R.id.time, data.getCreateTimeText());
    }
}
