package com.zhao.oa.global;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhao
 * 2019/5/30 23:44
 * 配置过滤器
 */

public class EncodingFilter implements Filter {
    private  String encoding="utf-8";
//    初始化方法
    public void init(FilterConfig filterConfig) throws ServletException {
     if(filterConfig.getInitParameter("encoding")!=null){
         encoding=filterConfig.getInitParameter("encoding");
        }
    }
//  过滤
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest)servletRequest;
        HttpServletResponse servletResponse1=(HttpServletResponse)servletResponse;
        //设置字符集
        request.setCharacterEncoding(encoding);
        servletResponse1.setCharacterEncoding(encoding);
        //让他继续执行
        filterChain.doFilter(request,servletResponse);
    }

    public void destroy() {

    }
}
