package com.pyk.canteen.service.impl;

import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.mapper.StudentMapper;
import com.pyk.canteen.mapper.TeacherMapper;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.entity.Student;
import com.pyk.canteen.model.entity.Teacher;
import com.pyk.canteen.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    StudentMapper studentMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Override
    public void setName(Account account, String value) {
        if (account.getType() == 2) {
            Teacher t = teacherMapper.selectById(account.getUid());
            t.setName(value);
            teacherMapper.updateById(t.check());
        } else if (account.getType() == 3) {
            Student s = studentMapper.selectById(account.getUid());
            s.setName(value);
            studentMapper.updateById(s.check());
        }
    }

    @Override
    public void setDes(Account account, String des) {
        if (account.getType() == 2) {
            Teacher t = teacherMapper.selectById(account.getUid());
            t.setDes(des);
            teacherMapper.updateById(t.check());
        } else if (account.getType() == 3) {
            Student s = studentMapper.selectById(account.getUid());
            s.setDes(des);
            studentMapper.updateById(s.check());
        }
    }

    @Override
    public void setPhone(Account account, String phone) {
        if (account.getType() == 2) {
            Teacher t = teacherMapper.selectById(account.getUid());
            t.setPhone(phone);
            teacherMapper.updateById(t.check());
        } else if (account.getType() == 3) {
            Student s = studentMapper.selectById(account.getUid());
            s.setPhone(phone);
            studentMapper.updateById(s.check());
        }
    }

    @Override
    public void setGender(Account account, Integer gender) {
        if (account.getType() == 2) {
            Teacher t = teacherMapper.selectById(account.getUid());
            t.setGender(gender);
            teacherMapper.updateById(t.check());
        } else if (account.getType() == 3) {
            Student s = studentMapper.selectById(account.getUid());
            s.setGender(gender);
            studentMapper.updateById(s.check());
        }
    }

    @Override
    public Teacher getTeacher(Account account) {
        Teacher teacher = teacherMapper.selectById(account.getUid());
        GlobalConstant.dataNotExists.notNull(teacher);
        return teacher;
    }

    @Override
    public Student getStudent(Account account) {
        Student student = studentMapper.selectById(account.getUid());
        GlobalConstant.dataNotExists.notNull(student);
        return student;
    }
}
