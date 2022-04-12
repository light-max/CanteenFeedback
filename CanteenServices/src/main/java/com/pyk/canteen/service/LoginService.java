package com.pyk.canteen.service;

import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Account;

public interface LoginService {
    Result<Account> admin(String uid, String password);

    Result<Account> canteen(String uid, String password);

    Result<Account> teacher(String uid, String password);

    Result<Account> student(String uid, String password);

    Result<Account> register(String uid, String name, String password, int type);
}
