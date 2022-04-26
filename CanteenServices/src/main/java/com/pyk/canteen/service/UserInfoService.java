package com.pyk.canteen.service;

import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.entity.Student;
import com.pyk.canteen.model.entity.Teacher;

public interface UserInfoService {
    void setName(Account account, String value);

    void setDes(Account account, String des);

    void setPhone(Account account, String phone);

    void setGender(Account account, Integer gender);

    Teacher getTeacher(Account account);

    Student getStudent(Account account);
}
