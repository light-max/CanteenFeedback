package com.pyk.canteen.controller.mobile;

import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.entity.Student;
import com.pyk.canteen.model.entity.Teacher;
import com.pyk.canteen.service.UserInfoService;
import com.pyk.canteen.util.UseDefaultSuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;

@Controller("mobile.user")
public class UserController {
    @Resource
    UserInfoService service;

    @PutMapping("/api/info/name")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setName(@SessionAttribute("account") Account account, String value) {
        service.setName(account, value);
    }

    @PutMapping("/api/info/des")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setDes(@SessionAttribute("account") Account account, String value) {
        service.setDes(account, value);
    }

    @PutMapping("/api/info/phone")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setPhone(@SessionAttribute("account") Account account, String value) {
        service.setPhone(account, value);
    }

    @PutMapping("/api/info/gender")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setGender(@SessionAttribute("account") Account account, Integer value) {
        service.setGender(account, value);
    }

    @GetMapping("/api/info/student")
    @ResponseBody
    public Result<Student> getStudentInfo(@SessionAttribute("account") Account account) {
        return Result.success(service.getStudent(account));
    }

    @GetMapping("/api/info/teacher")
    @ResponseBody
    public Result<Teacher> getTeacherInfo(@SessionAttribute("account") Account account) {
        return Result.success(service.getTeacher(account));
    }
}
