package com.kaikeba.filter;

import com.kaikeba.util.UserUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter({"/admin/index.html","/admin/views/*","/express/*","/index.jsp"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String name = UserUtil.getAdminName(request.getSession());
        if(name == null){
            //用户未登陆,跳转到登陆页面
            response.sendRedirect("/admin/login.html");
        }else{
            //用户已登陆，放行
            filterChain.doFilter(request,response);
        }
    }
}
