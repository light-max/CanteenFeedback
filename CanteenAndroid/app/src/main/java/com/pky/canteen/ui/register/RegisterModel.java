package com.pky.canteen.ui.register;

import com.pky.canteen.api.Api;
import com.pky.canteen.async.Async;
import com.pky.canteen.base.mvp.BaseModel;

public class RegisterModel extends BaseModel<RegisterActivity> {
    public void login(boolean isStudent, String uid, String name, String password) {
        Async.Builder<?> async;
        if (isStudent) {
            async = Api.sRegister(uid, name, password);
        } else {
            async = Api.tRegister(uid, name, password);
        }
        async.before(() -> base.getView().showDialog())
                .after(() -> base.getView().dismissDialog())
                .error((message, e) -> base.toast(message))
                .success(data -> {
                    base.toast("注册成功, 快去登录吧");
                    base.finish();
                }).run();
    }
}
