package com.pky.canteen.ui.dish.collect;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pky.canteen.R;
import com.pky.canteen.api.ExRequestBuilder;
import com.pky.canteen.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.pky.canteen.data.result.Collector;

public class DishCollectAdapter extends SimpleSingleItemRecyclerAdapter<Collector> {
    @Override
    protected int getItemViewLayout() {
        return R.layout.item_dish_details_collector;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, Collector data, int position) {
        holder.setText(R.id.name, data.getName())
                .setText(R.id.uid, data.getUid());
        Glide.with(holder.itemView)
                .load(ExRequestBuilder.getUrl(data.getHead()))
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(holder.getImage(R.id.head));
    }
}
