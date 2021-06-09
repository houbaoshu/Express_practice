package com.kaikeba.mvc;


import com.kaikeba.mvc.bean.MVCMapping;
import com.kaikeba.mvc.bean.ResponseType;
import org.apache.http.protocol.HttpRequestHandlerMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * servlet的调度类
 * @author houbaoshu
 */
public class DispatcherServlet extends HttpServlet {
    //重写Servlet的初始化方法
    @Override
    public void init(ServletConfig config) throws ServletException {
        String path = config.getInitParameter("contentConfigLocation");
        InputStream is = DispatcherServlet.class.getClassLoader().getResourceAsStream(path);
        HandleMapping.load(is);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得请求的uri
        String uri = request.getRequestURI();
        //获得 mvcMapping 对象 加载properties文件后所有映射相关的类都被加载，所有mavMapping对象都被实例化，此处应为查找此对象
        MVCMapping mvcMapping = HandleMapping.get(uri);
        if (mvcMapping == null) {
            response.sendError(404,"MVC：映射地址不存在：" + uri);
            return;
        }
        Object object = mvcMapping.getObject();
        Method method = mvcMapping.getMethod();
        Object result = null;
        try {
            result = method.invoke(object,request, response);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        switch (mvcMapping.getType()) {
            case TEXT:
                response.getWriter().write((String) result);
                break;
            case VIEW:
                response.sendRedirect((String) result);
                break;
            default:
        }
    }
}
