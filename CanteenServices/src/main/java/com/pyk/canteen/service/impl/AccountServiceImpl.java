package com.pyk.canteen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.mapper.AccountMapper;
import com.pyk.canteen.mapper.StudentMapper;
import com.pyk.canteen.mapper.TeacherMapper;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.td.AccountTD;
import com.pyk.canteen.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    @Resource
    StudentMapper studentMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Override
    public Page<Account> list(Integer n, Integer type) {
        LambdaQueryWrapper<Account> wrapper = new QueryWrapper<Account>()
                .lambda();
        if (type != null) {
            wrapper.eq(Account::getType, type);
        }
        return page(new Page<>(n == null ? 0 : n, 10), wrapper);
    }

    @Override
    public List<AccountTD> getTD(List<Account> list) {
        return new ArrayList<AccountTD>() {{
            String[] types = {
                    "后勤管理员", "食堂管理员", "教师", "学生"
            };
            for (Account account : list) {
                String name = account.getUid();
                if (account.getType() == 2) {
                    name = teacherMapper.selectById(name).getName();
                } else if (account.getType() == 3) {
                    name = studentMapper.selectById(name).getName();
                }
                add(AccountTD.builder()
                        .uid(account.getUid())
                        .type(account.getType())
                        .typeName(types[account.getType()])
                        .name(name)
                        .build());
            }
        }};
    }

    @Override
    public void setAdminPwd(String uid, String[] pwd) {
        GlobalConstant.passwordsError.isTrue(pwd[1].equals(pwd[2]));
        Account account = getById(uid);
        GlobalConstant.sourcePasswordError.isTrue(pwd[0].equals(account.getPassword()));
        account.setPassword(pwd[1]);
        account.check();
        updateById(account);
    }

    @Override
    public void setCanteenUid(String uid, String value) {
        Account account = getById(uid);
        GlobalConstant.dataNotExists.notNull(account);
        account.setUid(value);
        account.check();
        update(new UpdateWrapper<Account>()
                .lambda()
                .eq(Account::getUid, uid)
                .set(Account::getUid, value));
    }

    @Override
    public void setCanteenPwd(String uid, String pwd) {
        Account account = getById(uid);
        GlobalConstant.dataNotExists.notNull(account);
        account.setPassword(pwd);
        account.check();
        updateById(account);
    }

    @Override
    public void delete(String uid, Integer type) {
        if (type == 1 || type == 2 || type == 3) {
            Account account = getById(uid);
            GlobalConstant.dataNotExists.notNull(account);
            GlobalConstant.error.isTrue(account.getType().equals(type));
            if (type == 1) {
                removeById(uid);
            } else {

                GlobalConstant.error.newException();
            }
        } else {
            GlobalConstant.error.newException();
        }
    }

    @Override
    public void addCanteen(String uid, String pwd) {
        Account account = getById(uid);
        GlobalConstant.usernameExists.isNull(account);
        account = Account.builder()
                .uid(uid)
                .password(pwd)
                .type(1)
                .build()
                .check();
        save(account);
    }
}
