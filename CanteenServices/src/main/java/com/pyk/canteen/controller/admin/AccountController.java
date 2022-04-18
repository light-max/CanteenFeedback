package com.pyk.canteen.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pyk.canteen.mapper.StudentMapper;
import com.pyk.canteen.mapper.TeacherMapper;
import com.pyk.canteen.model.AccountAddRequest;
import com.pyk.canteen.model.data.Pager;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.model.td.AccountTD;
import com.pyk.canteen.service.AccountService;
import com.pyk.canteen.util.UseDefaultSuccessResponse;
import com.pyk.canteen.util.ump.ViewModelParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AccountController {
    @Resource
    AccountService service;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    StudentMapper studentMapper;

    @GetMapping({"/admin/account", "/admin/account/list", "/admin/account/list/{n}"})
    @ViewModelParameter(key = "view", value = "account")
    public String list(
            Model model,
            @PathVariable(required = false) Integer n,
            @RequestParam(required = false) Integer type
    ) {
        Page<Account> page = service.list(n, type);
        List<Account> records = page.getRecords();
        List<AccountTD> list = service.getTD(records);
        Pager pager = new Pager(page, "/admin/account/list");
        if (type != null) {
            pager.setTailAppend("?type=" + type);
        }
        model.addAttribute("type", type);
        model.addAttribute("pager", pager);
        model.addAttribute("list", list);
        return "/admin/account";
    }

    @GetMapping("/admin/account/update/{id}")
    @ViewModelParameter(key = "view", value = "account")
    public String update(Model model, @PathVariable String id) {
        Account account = service.getById(id);
        model.addAttribute("account", account);
        switch (account.getType()) {
            case 0:
                return "/admin/aupdate/admin";
            case 1:
                return "/admin/aupdate/canteen";
            case 2:
                model.addAttribute("teacher", teacherMapper.selectById(account.getUid()));
                return "/admin/aupdate/teacher";
            case 3:
                model.addAttribute("student", studentMapper.selectById(account.getUid()));
                return "/admin/aupdate/student";
        }
        return "redirect:/admin/account";
    }

    @GetMapping("/admin/account/add/{type}")
    @ViewModelParameter(key = "view", value = "account")
    public String add(Model model, @PathVariable String type) {
        if ("student".equals(type)) {
            return "/admin/account_add_student";
        } else if ("teacher".equals(type)) {
            return "/admin/account_add_teacher";
        } else {
            return "redirect:/admin/account";
        }
    }

    @PostMapping("/admin/account/password/admin")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setAdminPwd(String uid, String[] pwd) {
        service.setAdminPwd(uid, pwd);
    }

    @PostMapping("/admin/account/uid/canteen")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setCanteenUid(String uid, String value) {
        service.setCanteenUid(uid, value);
    }

    @PostMapping("/admin/account/password/canteen")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setCanteenPwd(String uid, String pwd) {
        service.setCanteenPwd(uid, pwd);
    }

    @DeleteMapping("/admin/account")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void delete(String uid, Integer type) {
        service.delete(uid, type);
    }

    @PostMapping("/admin/account/canteen")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void addCanteen(String uid, String pwd) {
        service.addCanteen(uid, pwd);
    }

    @PostMapping({"/admin/account/password/teacher", "/admin/account/password/student"})
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setAccountPwd(String uid, String pwd) {
        service.setAccountPwd(uid, pwd);
    }

    @PutMapping("/admin/account/teacher")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setTeacher(String uid, String name, Integer gender, String phone, String des) {
        service.setTeacherInfo(uid, name, gender, phone, des);
    }

    @PutMapping("/admin/account/student")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setStudent(String uid, String name, Integer gender, String phone, String des) {
        service.setStudentInfo(uid, name, gender, phone, des);
    }

    @PostMapping("/admin/account/student/list")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void addStudentList(@RequestBody List<AccountAddRequest> list) {
        service.addStudentList(list);
    }

    @PostMapping("/admin/account/teacher/list")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void addTeacherList(@RequestBody List<AccountAddRequest> list) {
        service.addTeacherList(list);
    }
}
