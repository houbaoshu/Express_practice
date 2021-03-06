package com.kaikeba.controller;

import com.kaikeba.bean.BootstarpTable.UserBootstrapTable;
import com.kaikeba.bean.Message;
import com.kaikeba.bean.ResultData;
import com.kaikeba.bean.User;
import com.kaikeba.mvc.annotation.ResponseBody;
import com.kaikeba.service.UserService;
import com.kaikeba.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户控制对象
 *
 * @author houbaoshu
 */
public class UserController {
    @ResponseBody("/user/console.do")
    public String console(HttpServletRequest request ,HttpServletResponse response ) {
        Message msg = new Message();
        Map<String, Integer> console = UserService.console();
        if (console.size() == 0) {
            msg.setStatus(-1);
        } else {
            msg.setStatus(0);
        }
        msg.setData(console);
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/user/list.do")
    public String findAll(HttpServletRequest request, HttpServletResponse response) {
        int offset = Integer.parseInt(request.getParameter("offset"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        List<User> users = UserService.findAll(true, offset, pageNumber);
        // user => userBootstrapTable
        ArrayList<UserBootstrapTable> userBootstrapTable = new ArrayList<>();
        for (User user : users) {
            userBootstrapTable.add(new UserBootstrapTable(user));
        }
        //userBootstrapTable => resultData
        ResultData<UserBootstrapTable> resultData = new ResultData<>();
        resultData.setRows(userBootstrapTable);
        //获得总的用户人数
        Map<String, Integer> console = UserService.console();
        int numOfUser = console.get("numOfUser");
        resultData.setTotal(numOfUser);
        return JSONUtil.toJSON(resultData);
    }

    @ResponseBody("/user/find.do")
    public String findByUserPhone(HttpServletRequest request, HttpServletResponse response) {
        Message msg = new Message();
        String userPhone = request.getParameter("userPhone");
        User user = UserService.findByUserPhone(userPhone);
        if (user != null) {
            msg.setStatus(0);
            msg.setResult("查询成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("查询失败");
        }
        msg.setData(user);
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/user/update.do")
    public String update(HttpServletRequest request, HttpServletResponse response) {
        Message msg = new Message();
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");
        String idCardNumber = request.getParameter("idCardNumber");
        String password = request.getParameter("password");
        User uesr = new User(username, password, userPhone, idCardNumber);
        boolean update = UserService.update(id, uesr);
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

    @ResponseBody("/user/insert.do")
    public String insert(HttpServletRequest request, HttpServletResponse response) {
        Message msg = new Message();
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");
        String idCardNumber = request.getParameter("idCardNumber");
        String password = request.getParameter("password");
        User uesr = new User(username, password, userPhone, idCardNumber);
        boolean insert = UserService.insert(uesr);
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

    @ResponseBody("/user/delete.do")
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        Message msg = new Message();
        int id = Integer.parseInt(request.getParameter("id"));
        boolean delete = UserService.delete(id);
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
