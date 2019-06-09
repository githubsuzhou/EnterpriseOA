package com.zhao.oa.service;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhao
 * 2019/6/2 22:15
 * 登陆拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    public LoginInterceptor() {
        super();
    }

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取请求路径
        String url=httpServletRequest.getRequestURI();
        //查询有没有login的关键字，有直接放行 toLowerCase()转换为小写的函数
        if(url.toLowerCase().indexOf("login")>=0){
            return true;
        }
        //之后的登陆之后才能访问
        HttpSession session=httpServletRequest.getSession();
        if(session.getAttribute("employee")!=null){
            return true;
        }
        //重新去登陆
        httpServletResponse.sendRedirect("/to_login");
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
