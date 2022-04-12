package com.pyk.canteen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pyk.canteen.constant.AssertException;
import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.mapper.AccountMapper;
import com.pyk.canteen.mapper.StudentMapper;
import com.pyk.canteen.mapper.TeacherMapper;
import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.entity.Student;
import com.pyk.canteen.model.entity.Teacher;
import com.pyk.canteen.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    AccountMapper mapper;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    StudentMapper studentMapper;

    private Result<Account> query(String uid, String password, int type) {
        LambdaQueryWrapper<Account> wrapper = new QueryWrapper<Account>()
                .lambda()
                .eq(Account::getUid, uid)
                .eq(Account::getPassword, password);
        Account account = mapper.selectOne(wrapper);
        try {
            GlobalConstant.loginError.notNull(account);
            GlobalConstant.loginError1.isTrue(account.getType() == type);
            return Result.success(account);
        } catch (AssertException e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result<Account> admin(String uid, String password) {
        return query(uid, password, 0);
    }

    @Override
    public Result<Account> canteen(String uid, String password) {
        return query(uid, password, 1);
    }

    @Override
    public Result<Account> teacher(String uid, String password) {
        return query(uid, password, 2);
    }

    @Override
    public Result<Account> student(String uid, String password) {
        return query(uid, password, 3);
    }

    @Override
    public Result<Account> register(String uid, String name, String password, int type) {
        if (type == 2 || type == 3) {
            GlobalConstant.usernameExists.isNull(mapper.selectById(uid));
            Teacher teacher = null;
            Student student = null;
            if (type == 2) {
                teacher = Teacher.builder()
                        .uid(uid)
                        .name(name)
                        .gender(0)
                        .phone("")
                        .des("")
                        .build()
                        .check();
            } else {
                student = Student.builder()
                        .uid(uid)
                        .name(name)
                        .gender(0)
                        .phone("")
                        .des("")
                        .build()
                        .check();
            }
            Account account = Account.builder()
                    .uid(uid)
                    .password(password)
                    .type(type)
                    .build()
                    .check();
            if (teacher != null) {
                teacherMapper.insert(teacher);
            }
            if (student != null) {
                studentMapper.insert(student);
            }
            mapper.insert(account);
            return Result.success(account);
        } else {
            return Result.error(GlobalConstant.error.getMessage());
        }
    }
}
