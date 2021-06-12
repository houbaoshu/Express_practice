package com.kaikeba.controller;

import com.google.gson.Gson;
import com.kaikeba.bean.Express;
import com.kaikeba.bean.Message;
import com.kaikeba.mvc.annotation.ResponseBody;
import com.kaikeba.mvc.annotation.ResponseView;
import com.kaikeba.service.ExpressService;
import com.kaikeba.util.JSONUtil;
import com.kaikeba.util.RandomUtil;
import com.kaikeba.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Provider;
import java.util.List;
import java.util.Map;
import java.util.Random;

/** 快递控制对象
 * @author houbaoshu
 */
public class ExpressController {
    @ResponseBody("/express/console.do")
    public String console(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Integer>> data = ExpressService.console();
        System.out.println("后台查询控制台数据为："+data);
        Gson gson = new Gson();
        //将数据库查询信息和状态信息一并返回，封装在Message里
        Message msg = new Message();
        if (data.size() == 0) {
            msg.setStatus(-1);
        } else {
            msg.setStatus(0);
        }
        msg.setData(data);
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/express/list.do")
    public String findAll(HttpServletRequest request, HttpServletResponse response) {
        boolean limit = Boolean.parseBoolean(request.getParameter("limit"));
        int offset = Integer.parseInt(request.getParameter("offset"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        List<Express> expresses = ExpressService.findAll(limit, offset, pageNumber);
        System.out.println("后台查询快递信息为：" + expresses);
        //转成前台可以理解的格式
        /* List<BootStrapTableExpress> list2 = new ArrayList<>();*/
        return JSONUtil.toJSON(expresses);
    }

   /* @ResponseBody("/express/findByNumber.do")*/
    @ResponseBody("/express/find.do")//地址区分大小写吗？
    public String findByNumber(HttpServletRequest request, HttpServletResponse response) {
        String number = request.getParameter("number");
        Express express = ExpressService.findByNumber(number);
        System.out.println("后台查找快递为：" + express);
        Message msg = new Message();
        if (express == null) {
            msg.setStatus(-1);
            msg.setResult("单号不存在");
        } else {
            msg.setStatus(0);
            msg.setResult("查询成功");
        }
        msg.setData(express);
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/express/findByCode.do")
    public String findByCode(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        Express express = ExpressService.findByCode(code);
        Message msg = new Message();
        if (express != null) {
            msg.setStatus(0);
            msg.setResult("查询成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("查询失败");
        }
        msg.setData(express);
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/express/findByUserPhone.do")
    public String findByUserPhone(HttpServletRequest request, HttpServletResponse response) {
        int status = Integer.parseInt(request.getParameter("status"));
        String userPhone = request.getParameter("userPhone");
        List<Express> expresses = null;
        switch (status) {
            case 0:
            case 1:
            case 2:
                expresses = ExpressService.findByUserPhone(userPhone);
        }
        return JSONUtil.toJSON(expresses);
    }
    @ResponseBody("/express/insert.do")
    public String insert(HttpServletRequest request, HttpServletResponse response) {
        String number = request.getParameter("number");
        String username = request.getParameter("username");
        String company = request.getParameter("company");
        String userPhone = request.getParameter("userPhone");
        Express express = new Express(number, username, userPhone, company, UserUtil.getUserPhone(request.getSession()));
        boolean insert = ExpressService.insert(express);
        Message msg = new Message();
        if (insert) {
            msg.setStatus(0);
            msg.setResult("快递录入成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("快递录入失败");
        }
        return JSONUtil.toJSON(msg);
    }
    @ResponseBody("/express/update.do")
    public String update(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String number = request.getParameter("number");
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");
        String company = request.getParameter("company");
        int status = Integer.parseInt(request.getParameter("status"));
        Express newExpress = new Express(number,username,userPhone,company,status);
        boolean update = ExpressService.update(id, newExpress);
        Message msg = new Message();
        if (update) {
            msg.setStatus(0);
            msg.setResult("快递修改成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("快递修改失败");
        }
        return JSONUtil.toJSON(msg);
    }
    @ResponseBody("/express/updateStatus.do")
    public String updateStatus(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        boolean updateStatus = ExpressService.updateStatus(code);
        Message msg = new Message();
        if (updateStatus) {
            msg.setStatus(0);
        } else {
            msg.setStatus(-1);
        }
        msg.setData(msg);
        return  JSONUtil.toJSON(msg);
    }
    @ResponseBody("/express/delete.do")
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean delete = ExpressService.delete(id);
        Message msg = new Message();
        if (delete) {
            msg.setStatus(0);
        } else {
            msg.setStatus(-1);
        }
        msg.setData(msg);
        return  JSONUtil.toJSON(msg);
    }
}
