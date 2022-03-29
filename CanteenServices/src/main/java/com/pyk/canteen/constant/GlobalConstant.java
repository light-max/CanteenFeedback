package com.pyk.canteen.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalConstant implements Assert {
    error("系统错误"),

    noAccess("没有访问权限，请重新登录"),

    usernameExists("用户名已存在"),
    usernameFormat("用户名格式错误，只能使用英文字母和数字何下划线，并且长度在4-16位"),
    passwordFormat("密码格式错误，只能使用英文字母、数字、下划线和小数点，并且长度在4-16位"),
    desFormat("账号描述不能不能超过120个字"),
    userDeleteError1("不能删除还有书没还的账号"),

    loginError("登陆错误，用户名或密码不正确"),
    loginError1("登录错误，权限错误"),
    accountDisable("登陆失败，此账号已被禁用"),

    dataNotExists("数据不存在"),

    sourcePasswordError("原密码错误"),
    passwordsError("两次输入的密码不一致"),

    resourceNotExists("资源文件不存在"),
    resourceEmpty("没有资源文件"),

    borrowError("这本书已经被人借走了捏"),

    novelExists("同名电子资源已存在，不可重复添加"),

    collectError("已收藏，不能重复收藏"),
    ;

    private String message;
}
