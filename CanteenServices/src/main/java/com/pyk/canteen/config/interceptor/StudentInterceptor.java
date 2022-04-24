package com.pyk.canteen.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object student = request.getSession().getAttribute("account");
        if (student == null) {
            response.sendRedirect("/api/notloggedin");
            return false;
        }
        return true;
    }
}
