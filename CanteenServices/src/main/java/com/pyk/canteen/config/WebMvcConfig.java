package com.pyk.canteen.config;

import com.pyk.canteen.config.interceptor.AdminInterceptor;
import com.pyk.canteen.config.interceptor.GlobalInterceptor;
import com.pyk.canteen.config.interceptor.StudentInterceptor;
import com.pyk.canteen.config.interceptor.TeacherInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/notloggedin");
        registry.addInterceptor(new TeacherInterceptor())
                .addPathPatterns("/teacher/**")
                .excludePathPatterns("/teacher/login")
                .excludePathPatterns("/teacher/notloggedin");
        registry.addInterceptor(new StudentInterceptor())
                .addPathPatterns("/student/**")
                .excludePathPatterns("/student/login")
                .excludePathPatterns("/student/notloggedin");
        registry.addInterceptor(new GlobalInterceptor())
                .addPathPatterns("/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("/admin/home");
        registry.addViewController("/teacher").setViewName("/teacher/home");
    }
}
