package com.pyk.canteen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pyk.canteen.model.AccountAddRequest;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.td.AccountTD;

import java.util.List;

public interface AccountService extends IService<Account> {
    Page<Account> list(Integer n, Integer type);

    List<AccountTD> getTD(List<Account> list);

    void setAdminPwd(String uid, String[] pwd);

    void setCanteenUid(String uid, String value);

    void setCanteenPwd(String uid, String pwd);

    void delete(String uid, Integer type);

    void addCanteen(String uid, String pwd);

    void setAccountPwd(String uid, String pwd);

    void setTeacherInfo(String uid, String name, Integer gender, String phone, String des);

    void setStudentInfo(String uid, String name, Integer gender, String phone, String des);

    void addStudentList(List<AccountAddRequest> list);

    void addTeacherList(List<AccountAddRequest> list);
}
