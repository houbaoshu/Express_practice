package com.kaikeba.controller;

import com.kaikeba.bean.Admin;
import com.kaikeba.bean.BootstarpTable.AdminBootstrapTable;
import com.kaikeba.bean.Message;
import com.kaikeba.bean.ResultData;
import com.kaikeba.mvc.annotation.ResponseBody;
import com.kaikeba.service.AdminService;
import com.kaikeba.service.ExpressService;
import com.kaikeba.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 管理员控制对象
 *
 * @author houbaoshu
 */
public class AdminController {
    @ResponseBody("/admin/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        //1、    接收参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2、    调用Service传参数，并获取结果
        boolean result = AdminService.login(username, password);
        //3、    根据结果，返回不同的数据
        Message message = null;
        if (result) {
            message = new Message(0, "登录成功");/*{status:0,result:"登录成功"}*/
            //登录时间和ip更新
            Date date = new Date();
            String ip = request.getRemoteAddr();
            AdminService.updateLoginTimeAndIP(username, date, ip);
            request.getSession().setAttribute("name", "username");
        } else {
            message = new Message(-1, "登录失败");
        }
        //4、将数据转换为JSON
        String json = JSONUtil.toJSON(message);
        //5、将JSON回复给ajax
        return json;
    }

    @ResponseBody("/admin/console.do")
    public String console(HttpServletRequest request, HttpServletResponse response) {
        Message msg = new Message();
        Map<String, Integer> resultData = AdminService.console();
        if (resultData.size() == 0) {
            msg.setStatus(-1);
        } else {
            msg.setStatus(0);
        }
        msg.setData(resultData);
        return JSONUtil.toJSON(msg);
    }
    @ResponseBody("/admin/list.do")
    public String findAll(HttpServletRequest request, HttpServletResponse response) {
        int offset = Integer.parseInt(request.getParameter("offset"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        List<Admin> admins = AdminService.findAll(true, offset, pageNumber);
        // admins => adminBootstrapTables
        List<AdminBootstrapTable> adminBootstrapTables = new ArrayList<>();
        for (Admin admin : admins) {
            adminBootstrapTables.add(new AdminBootstrapTable(admin));
        }
        //获得快递员总数和新增快递员人数
        Map<String, Integer> console = AdminService.console();
        int numOfAdmin = console.get("numOfAdmin");
        //将Admin_BootstrapTable对象集合转成BootStrap—table控件所需要的一种数据类型
        ResultData<AdminBootstrapTable> resultData = new ResultData<>();
        resultData.setRows(adminBootstrapTables);
        resultData.setTotal(numOfAdmin);
        return JSONUtil.toJSON(resultData);
    }

    @ResponseBody("/admin/find.do")
    public String findByUserPhone(HttpServletRequest request, HttpServletResponse response) {
        Message msg = new Message();
        String userPhone = request.getParameter("userPhone");
        Admin admin = AdminService.findByUserPhone(userPhone);
        if (admin != null) {
            msg.setStatus(0);
            msg.setResult("查询成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("查询失败");
        }
        msg.setData(admin);
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/admin/update.do")
    public String update(HttpServletRequest request, HttpServletResponse response) {
        Message msg = new Message();
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");
        String idCardNumber = request.getParameter("idCardNumber");
        String password = request.getParameter("password");
        Admin admin = new Admin(username, password, userPhone, idCardNumber);
        boolean update = AdminService.update(id, admin);
        if (update) {
            msg.setStatus(0);
            msg.setResult("修改成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("修改失败");
        }
        msg.setData(update);
        String json = JSONUtil.toJSON(msg);
        return json;
    }
    @ResponseBody("/admin/insert.do")
    public String insert(HttpServletRequest request, HttpServletResponse response) {
        Message msg = new Message();
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");
        String idCardNumber = request.getParameter("idCardNumber");
        String password = request.getParameter("password");
        Admin admin = new Admin(username, password, userPhone, idCardNumber);
        boolean insert = AdminService.insert(admin);
        if (insert) {
            msg.setStatus(0);
            msg.setResult("添加成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("添加失败");
        }
        msg.setData(insert);
        String json = JSONUtil.toJSON(msg);
        return json;
    }
    @ResponseBody("/admin/delete.do")
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        Message msg = new Message();
        int id = Integer.parseInt(request.getParameter("id"));
        boolean delete = AdminService.delete(id);
        if (delete) {
            msg.setStatus(0);
            msg.setResult("删除成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("删除失败");
        }
        msg.setData(delete);
        String json = JSONUtil.toJSON(msg);
        return json;
    }
}
