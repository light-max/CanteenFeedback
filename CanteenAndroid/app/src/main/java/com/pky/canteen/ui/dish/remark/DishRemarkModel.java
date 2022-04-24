package com.pky.canteen.ui.dish.remark;

import com.pky.canteen.api.Api;
import com.pky.canteen.async.Async;
import com.pky.canteen.base.mvp.BaseModel;
import com.pky.canteen.data.result.RemarkPager;

public class DishRemarkModel extends BaseModel<DishRemarkFragment> {
    public Async.Builder<RemarkPager> requestNewData() {
        return Api.getRemarkList(1, base.map("id"))
                .success(data -> {
                    int nextPage = data.getPager().getCurrentPage() + 1;
                    base.map("next-page", nextPage);
                })
                .error((message, e) -> base.toast(message));
    }

    public Async.Builder<RemarkPager> requestNextData() {
        return Api.getRemarkList(base.map("next-page"), base.map("id"))
                .success(data -> {
                    int nextPage = data.getPager().getCurrentPage() + 1;
                    base.map("next-page", nextPage);
                })
                .error((message, e) -> base.toast(message));
    }

    public Async.Builder<RemarkPager> requestReplyNewData(int parentId) {
        return Api.getReplyList(1, parentId)
                .success(data -> {
                    int nextPage = data.getPager().getCurrentPage() + 1;
                    base.map("next-page-reply", nextPage);
                })
                .error((message, e) -> base.toast(message));
    }

    public Async.Builder<RemarkPager> requestReplyNextData(int parentId) {
        return Api.getReplyList(base.map("next-page-reply"), parentId)
                .success(data -> {
                    int nextPage = data.getPager().getCurrentPage() + 1;
                    base.map("next-page-reply", nextPage);
                })
                .error((message, e) -> base.toast(message));
    }

    public Async.Builder<?> sendRemark(int dishId, String content) {
        return Api.sendRemark(dishId, content);
    }

    public Async.Builder<?> sendReply(int dishId, int parentId, String content) {
        return Api.sendReply(dishId, parentId, content);
    }
}
