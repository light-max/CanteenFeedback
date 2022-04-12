package com.pyk.canteen.controller.canteen;

import com.pyk.canteen.constant.GlobalConstant;
import com.pyk.canteen.model.data.Result;
import com.pyk.canteen.model.entity.Account;
import com.pyk.canteen.service.LoginService;
import com.pyk.canteen.util.ump.ViewModelParameter;
import com.pyk.canteen.util.ump.ViewModelParameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class CLoginController {
    @Resource
    LoginService service;

    @GetMapping("/canteen/login")
    @ViewModelParameter(key = "role", value = "食堂管理员")
    public String login(Model model) {
        return "/login";
    }

    /**
     * 提交登陆请求
     *
     * @param session  用于保存{@link Account}已登录的管理员实例
     * @param username 用户名
     * @param pwd      密码
     */
    @PostMapping(value = "/canteen/login", produces = "text/html")
    public String login(Model model, HttpSession session, String username, String pwd) {
        Result<Account> result = service.canteen(username, pwd);
        if (result.isSuccess()) {
            session.setAttribute("canteen", result.getData());
            return "redirect:/canteen";
        } else {
            login(model);
            model.addAttribute("username", username);
            model.addAttribute("pwd", pwd);
            model.addAttribute("error", result.getMessage());
            return "/login";
        }
    }

    /**
     * @see #login(Model, HttpSession, String, String)
     */
    @PostMapping("/canteen/login")
    @ResponseBody
    public Result<Account> login(HttpSession session, String username, String pwd) {
        Result<Account> result = service.admin(username, pwd);
        if (result.isSuccess()) {
            session.setAttribute("canteen", result.getData());
        }
        return result;
    }


    /**
     * 退出登陆，并且重定向到登陆页面
     */
    @GetMapping("/canteen/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("canteen");
        return "redirect:/canteen/login";
    }

    /**
     * 用户未登录的提示页面
     */
    @GetMapping(value = "/canteen/notloggedin", produces = "text/html")
    @ViewModelParameters({
            @ViewModelParameter(key = "action", value = "/canteen/login"),
            @ViewModelParameter(key = "role", value = "食堂管理员"),
            @ViewModelParameter(key = "username", value = "shitang"),
            @ViewModelParameter(key = "pwd", value = "1234")
    })
    public String notLogin(Model model) {
        model.addAttribute("error", GlobalConstant.noAccess.getMessage());
        return "/login";
    }

    /**
     * 返回用户未登录的错误信息
     */
    @GetMapping("/canteen/notloggedin")
    @ResponseBody
    public Result<Object> notLogin() {
        return Result.error(GlobalConstant.noAccess.getMessage());
    }
}
