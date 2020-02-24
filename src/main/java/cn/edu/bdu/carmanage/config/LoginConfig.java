package cn.edu.bdu.carmanage.config;

import cn.edu.bdu.carmanage.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author WU
 * @Date 2020/2/17 19:28
 * @Version 1.0
 */
@Configuration
public class LoginConfig implements  WebMvcConfigurer {
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
        // 排除配置--对下面的不进行拦截
        List<String> patterns = new ArrayList<>();
        patterns.add("/user/user/login");
        patterns.add("/user/user/register");
        patterns.add("/user/user/addUser");
        patterns.add("/user/user/getUser");
        patterns.add("/admin/user/index");
        patterns.add("/admin/user/getAdminUser");
        patterns.add("/static/**");
        interceptorRegistration.excludePathPatterns(patterns);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
