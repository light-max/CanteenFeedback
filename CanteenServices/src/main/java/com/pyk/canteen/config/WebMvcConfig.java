package com.pyk.canteen.config;

import com.pyk.canteen.config.interceptor.*;
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
        registry.addInterceptor(new CanteenInterceptor())
                .addPathPatterns("/canteen/**")
                .excludePathPatterns("/canteen/login")
                .excludePathPatterns("/canteen/notloggedin");
        registry.addInterceptor(new TeacherInterceptor())
                .addPathPatterns("/t/**")
                .excludePathPatterns("/t/login")
                .excludePathPatterns("/t/notloggedin");
        registry.addInterceptor(new StudentInterceptor())
                .addPathPatterns("/s/**")
                .excludePathPatterns("/s/login")
                .excludePathPatterns("/s/notloggedin");
        registry.addInterceptor(new GlobalInterceptor())
                .addPathPatterns("/**");
        registry.addInterceptor(new ApiInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/notloggedin");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("/admin/home");
        registry.addViewController("/canteen").setViewName("/canteen/home");
    }
}
