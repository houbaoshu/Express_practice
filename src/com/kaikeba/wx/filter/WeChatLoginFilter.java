package com.kaikeba.wx.filter;


import com.kaikeba.util.UserUtil;
import jdk.swing.interop.SwingInterOpUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/wx/index.html")
public class WeChatLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Object wxObject = UserUtil.getWxObject(session);
        if (wxObject == null) {
            response.sendRedirect("login.html");
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
