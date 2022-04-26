package com.pky.canteen.ui.login;

import com.pky.canteen.api.Api;
import com.pky.canteen.async.Async;
import com.pky.canteen.base.mvp.BaseModel;
import com.pky.canteen.data.livedata.StudentData;
import com.pky.canteen.data.livedata.TeacherData;
import com.pky.canteen.ui.home.HomeActivity;

public class LoginModel extends BaseModel<LoginActivity> {
    public void login(boolean isStudent, String username, String password) {
        Async.Builder<?> async;
        if (isStudent) {
            async = Api.sLogin(username, password)
                    .success(data -> {
                        StudentData.newInstance().postValue(data);
                        TeacherData.newInstance();
                    });
        } else {
            async = Api.tLogin(username, password)
                    .success(data -> {
                        StudentData.newInstance();
                        TeacherData.newInstance().postValue(data);
                    });
        }
        async.before(() -> base.getView().showDialog())
                .after(() -> base.getView().dismissDialog())
                .error(((message, e) -> base.toast(message)))
                .success(() -> {
                    base.getView().saveStatus();
                    base.toast("登录成功");
                    base.finish();
                    base.open(HomeActivity.class);
                })
                .run();
    }
}
