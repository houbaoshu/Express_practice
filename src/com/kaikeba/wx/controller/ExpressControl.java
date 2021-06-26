package com.kaikeba.wx.controller;

import com.kaikeba.bean.BootstarpTable.ExpressBootstrapTable;
import com.kaikeba.bean.Express;
import com.kaikeba.bean.Message;
import com.kaikeba.mvc.annotation.ResponseBody;
import com.kaikeba.service.ExpressService;
import com.kaikeba.util.JSONUtil;
import com.kaikeba.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理快递相关请求的类，如获得快递列表、查询某个快递、更新快递状态、
 */
public class ExpressControl {
    //根据电话号返回快递列表到我的快件页面(用户查快递）
    @ResponseBody("/wx/findExpressByUserPhone.do")
    public String findByUserPhone(HttpServletRequest request, HttpServletResponse response) {
        //从前端接收手机号码(从 session 中获取)
        String userPhone = UserUtil.getWxPhone(request.getSession());
        //委托服务对象查询快递信息
        List<Express> expresses = ExpressService.findByUserPhone(userPhone);
        //将数据用消息封装
        Message msg = new Message();
        if (expresses.size() != 0) {
            //将查到的数据进行流过滤
            //状态 0 -> 未取件 状态 1 -> 已取件
            msg.setStatus(0);//查询成功
            List<Express> e0 = ExpressService.findByUserPhoneAndStatus(userPhone, 0);
            List<ExpressBootstrapTable> bootstrapTables0 = new ArrayList<>();
            for (Express e : e0) {
                bootstrapTables0.add(new ExpressBootstrapTable(e));
            }
            //未取件
            List<Express> e1 = ExpressService.findByUserPhoneAndStatus(userPhone, 1);
            List<ExpressBootstrapTable> bootstrapTables1 = new ArrayList<>();
            for (Express e : e1) {
                bootstrapTables1.add(new ExpressBootstrapTable(e));
            }
            // 对象转对象数组
            Object[] s0 = bootstrapTables0.toArray();
            Object[] s1 = bootstrapTables1.toArray();
            Map map = new HashMap();
            map.put("status0",s0);
            map.put("status1",s1);
            msg.setResult("查询成功");
            msg.setData(map);
        } else {
            msg.setStatus(-1);
            msg.setResult("该用户无查询记录");
        }
        return JSONUtil.toJSON(msg);
    }
    //根据用户手机号，在取件助手页面返回相应的快递列表（是快递员还是普通用户）
    @ResponseBody("/wx/findExpressListByPhone.do")
    public String getExpressListByPhone(HttpServletRequest request ,HttpServletResponse response ) {
        Message msg = new Message();
        String userPhone = request.getParameter("phone");
        //查询未取件 0 表示未取件
        List<Express> expresses = ExpressService.findByUserPhoneAndStatus(userPhone, 0);
        if (expresses.size() != 0) {
            msg.setStatus(0);
            //进行boostraptable封装
            List<ExpressBootstrapTable> bootstrapTables = new ArrayList();
            for (Express express : expresses) {
                bootstrapTables.add(new ExpressBootstrapTable(express));
            }
            msg.setResult("查询成功");
            msg.setData(bootstrapTables);
        } else {
            msg.setStatus(-1);
            msg.setResult("未查询到该快递");
        }

        return  JSONUtil.toJSON(msg);
    }

    //根据取件码返回快递数据到取件助手页面
    @ResponseBody("/wx/findExpressByCode.do")
    public String getExpressByCode(HttpServletRequest request, HttpServletResponse response) {
        Message msg = new Message();
        String code = request.getParameter("code");
        Express express = ExpressService.findByCode(code);
        if (express != null) {
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(express);
        } else {
            msg.setStatus(-1);
            msg.setResult("未查询到该快递");
        }

        return JSONUtil.toJSON(msg);
    }

    //更新快递状态
    @ResponseBody("/wx/updateStatus.do")
    public String updateStatus(HttpServletRequest request, HttpServletResponse response) {
        Message msg = new Message();
        String code = request.getParameter("code");
        boolean updateStatus = ExpressService.updateStatus(code);
        if (updateStatus) {
            msg.setStatus(0);
            msg.setResult("取件成功");

        } else {
            msg.setStatus(-1);
            msg.setResult("取件失败");
        }
        return JSONUtil.toJSON(msg);
    }

}
