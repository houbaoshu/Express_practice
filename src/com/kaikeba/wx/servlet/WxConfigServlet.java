package com.kaikeba.wx.servlet;

import com.kaikeba.wx.util.SignatureUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理与微信交互的请求
 */
@WebServlet("/wx/wxconf.do")
public class WxConfigServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        String urlText = req.getParameter("xurl");//登录前先发送与微信通信的请求，服务器向客户端发送资质信息
        try {
            String json = SignatureUtil.getConfig(urlText).toJSON();
            pw.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
