package com.pky.canteen.base.recycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OnLoadMoreListener extends RecyclerView.OnScrollListener {
    private boolean isLoadMoreIng = false;
    private final Call call;
    // 用来标记是否正在向上滑动
    private boolean isSlidingUpward = false;

    public OnLoadMoreListener(Call call) {
        this.call = call;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        // 当不滑动时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            //获取最后一个完全显示的itemPosition
            assert manager != null;
            int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
            int itemCount = manager.getItemCount();

            // 判断是否滑动到了最后一个item，并且是向上滑动
            if (lastItemPosition == (itemCount - 1) && isSlidingUpward) {
                //加载更多
                isLoadMoreIng = true;
                call.onLoadMore(this);
            }
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//        if (!isLoadMoreIng) {
//            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//            if (manager != null) {
//                int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
//                int itemCount = manager.getItemCount();
//                if (lastItemPosition >= (itemCount - 3) && dy >= 0) {
//                    isLoadMoreIng = true;
//                    call.onLoadMore(this);
//                }
//            }
//        }
        super.onScrolled(recyclerView, dx, dy);
        // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
        isSlidingUpward = dy > 0;
    }

    public void setLoadMoreIng(boolean loadMoreIng) {
        isLoadMoreIng = loadMoreIng;
    }

    public boolean isLoadMoreIng() {
        return isLoadMoreIng;
    }

    public interface Call {
        void onLoadMore(OnLoadMoreListener listener);
    }
}
