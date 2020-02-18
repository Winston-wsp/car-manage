package cn.edu.bdu.carmanage.interceptor;

import cn.edu.bdu.carmanage.entity.admin.AdminUser;
import cn.edu.bdu.carmanage.entity.user.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author WU
 * @Date 2020/2/17 19:18
 * @Version 1.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("userSession");
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute("adminUserSession");
        if (user != null || adminUser != null) {
            return true;
        }
        // 跳转登录-------controller类中的登陆方法
        request.setAttribute("message","您还未登录，请登录");
        String url = "/user/user/login";
        response.sendRedirect(url);
        return false;
    }
}
