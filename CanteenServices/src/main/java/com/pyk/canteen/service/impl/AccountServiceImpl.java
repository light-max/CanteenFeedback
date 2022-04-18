package com.pyk.canteen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pyk.canteen.constant.AssertException;
import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.mapper.AccountMapper;
import com.pyk.canteen.mapper.StudentMapper;
import com.pyk.canteen.mapper.TeacherMapper;
import com.pyk.canteen.model.AccountAddRequest;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.entity.Student;
import com.pyk.canteen.model.entity.Teacher;
import com.pyk.canteen.model.td.AccountTD;
import com.pyk.canteen.service.AccountService;
import com.pyk.canteen.service.LoginService;
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

    @Resource
    LoginService loginService;

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

    @Override
    public void setAccountPwd(String uid, String pwd) {
        Account account = getById(uid);
        GlobalConstant.error.isTrue(account.getType() == 2 || account.getType() == 3);
        account.setPassword(pwd);
        account.check();
        updateById(account);
    }

    @Override
    public void setTeacherInfo(String uid, String name, Integer gender, String phone, String des) {
        Teacher teacher = teacherMapper.selectById(uid);
        GlobalConstant.dataNotExists.notNull(teacher);
        teacher.setName(name);
        teacher.setGender(gender);
        teacher.setPhone(phone);
        teacher.setDes(des);
        teacher.check();
        teacherMapper.updateById(teacher);
    }

    @Override
    public void setStudentInfo(String uid, String name, Integer gender, String phone, String des) {
        Student student = studentMapper.selectById(uid);
        GlobalConstant.dataNotExists.notNull(student);
        student.setName(name);
        student.setGender(gender);
        student.setPhone(phone);
        student.setDes(des);
        student.check();
        studentMapper.updateById(student);
    }

    private void addAccountList(List<AccountAddRequest> list, int type) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                AccountAddRequest a = list.get(i);
                AccountAddRequest b = list.get(j);
                if (a.getUid().equals(b.getUid())) {
                    throw new AssertException(String.format(
                            "序号为%d的项和序号为%d的项账号重复",
                            a.getIndex(), b.getIndex()
                    ));
                }
            }
        }
        for (AccountAddRequest data : list) {
            try {
                GlobalConstant.usernameExists.isNull(getById(data.getUid()));
                Account.builder()
                        .uid(data.getUid())
                        .password(data.getPwd())
                        .build()
                        .check();
                Student.builder()
                        .name(data.getName())
                        .build()
                        .check();
            } catch (AssertException e) {
                throw new AssertException("序号为" + data.getIndex() + "的项发生了错误: " + e.getMessage());
            }
        }
        for (AccountAddRequest data : list) {
            loginService.register(data.getUid(), data.getName(), data.getPwd(), type);
        }
    }

    @Override
    public void addStudentList(List<AccountAddRequest> list) {
        addAccountList(list, 3);
    }

    @Override
    public void addTeacherList(List<AccountAddRequest> list) {
        addAccountList(list, 2);
    }
}
