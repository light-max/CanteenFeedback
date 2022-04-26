package com.pky.canteen.ui.opinionsend;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pky.canteen.R;
import com.pky.canteen.base.recycler.SimpleSingleItemRecyclerAdapter;

class ImageAdapter extends SimpleSingleItemRecyclerAdapter<String> {
    private final int imageWidth;
    private final int imageHeight;

    public ImageAdapter(int imageSize) {
        imageWidth = imageSize;
        imageHeight = (int) (imageWidth * 0.9);
    }

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_opinion_image;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, String data, int position) {
        ImageView image = holder.getImage(R.id.image);
        ViewGroup.LayoutParams params = image.getLayoutParams();
        params.width = imageWidth;
        params.height = imageHeight;
        image.setLayoutParams(params);
        Glide.with(holder.itemView)
                .load(data)
                .into(image);
        holder.click(R.id.delete, () -> {
            getData().remove(position);
            notifyDataSetChanged();
        });
    }
}
