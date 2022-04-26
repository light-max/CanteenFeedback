package com.pky.canteen.ui.userinfo;

import com.pky.canteen.api.Api;
import com.pky.canteen.async.Async;
import com.pky.canteen.base.mvp.BaseModel;
import com.pky.canteen.data.livedata.StudentData;
import com.pky.canteen.data.livedata.TeacherData;

import java.io.File;
import java.util.Objects;

public class UserInfoModel extends BaseModel<UserInfoActivity> {
    private boolean isStudent = true;

    public void setHeadImage(File file) {
        UserInfoView view = base.getView();
        Api.setHeadImage(file).before(view::showDialog)
                .after(view::dismissDialog)
                .success(() -> {
                    if (isStudent) {
                        StudentData instance = StudentData.getInstance();
                        instance.postValue(instance.getValue());
                    } else {
                        TeacherData instance = TeacherData.getInstance();
                        instance.postValue(instance.getValue());
                    }
                }).run();
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public int getGender() {
        if (isStudent()) {
            return Objects.requireNonNull(StudentData.getInstance().getValue()).getGender();
        } else {
            return Objects.requireNonNull(TeacherData.getInstance().getValue()).getGender();
        }
    }

    private void executeRequest(Async.Builder<?> builder) {
        builder.error((message, e) -> {
            base.toast(message);
        }).success(() -> {
            if (isStudent()) {
                Api.getUserStudent()
                        .error((message, e) -> base.toast(message))
                        .success((student) -> {
                            StudentData.getInstance().postValue(student);
                        }).run();
            } else {
                Api.getUserTeacher()
                        .error((message, e) -> base.toast(message))
                        .success((teacher) -> {
                            TeacherData.getInstance().postValue(teacher);
                        }).run();
            }
            base.toast("修改成功");
        }).run();
    }

    public void setName(String name) {
        executeRequest(Api.setUserInfo("name", name));
    }

    public void setDes(String des) {
        executeRequest(Api.setUserInfo("des", des));
    }

    public void setPhone(String phone) {
        executeRequest(Api.setUserInfo("phone", phone));
    }

    public void setGender(Integer gender) {
        executeRequest(Api.setUserInfo("gender", gender));
    }
}
