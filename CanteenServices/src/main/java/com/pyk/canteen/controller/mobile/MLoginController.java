package com.pyk.canteen.controller.mobile;

import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.mapper.StudentMapper;
import com.pyk.canteen.mapper.TeacherMapper;
import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.entity.Student;
import com.pyk.canteen.model.entity.Teacher;
import com.pyk.canteen.service.LoginService;
import com.pyk.canteen.util.UseDefaultSuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class MLoginController {
    @Resource
    LoginService service;

    @Resource
    StudentMapper studentMapper;

    @Resource
    TeacherMapper teacherMapper;

    @PostMapping("/s/register")
    @ResponseBody
    public Result<Account> sregister(String uid, String name, String password) {
        return service.register(uid, name, password, 3);
    }

    @PostMapping("/t/register")
    @ResponseBody
    public Result<Account> tregister(String uid, String name, String password) {
        return service.register(uid, name, password, 2);
    }

    @PostMapping("/s/login")
    @ResponseBody
    public Result<Student> slogin(HttpSession session, String uid, String password) {
        Result<Account> result = service.student(uid, password);
        if (result.isSuccess()) {
            Student student = studentMapper.selectById(result.getData().getUid());
            GlobalConstant.dataNotExists.notNull(student);
            session.setAttribute("account", result.getData());
            return Result.success(student);
        } else {
            return Result.error(result.getMessage());
        }
    }

    @PostMapping("/t/login")
    @ResponseBody
    public Result<Teacher> tlogin(HttpSession session, String uid, String password) {
        Result<Account> result = service.teacher(uid, password);
        if (result.isSuccess()) {
            Teacher teacher = teacherMapper.selectById(result.getData().getUid());
            GlobalConstant.dataNotExists.notNull(teacher);
            session.setAttribute("account", result.getData());
            return Result.success(teacher);
        } else {
            return Result.error(result.getMessage());
        }
    }

    @GetMapping("/api/notloggedin")
    @ResponseBody
    public Result<Object> notLogin() {
        return Result.error(GlobalConstant.noAccess.getMessage());
    }

    @PostMapping("/api/logout")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void logout(HttpSession session) {
        session.removeAttribute("account");
    }
}
