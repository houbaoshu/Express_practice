package com.kaikeba.wx.controller;

import com.kaikeba.bean.Message;
import com.kaikeba.mvc.annotation.ResponseBody;
import com.kaikeba.mvc.annotation.ResponseView;
import com.kaikeba.util.JSONUtil;
import com.kaikeba.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QrCodeControl {
    /**
     * 生成相应二维码，有两种方式
     * 1.通过主页的取货二维码发起请求，二维码应包含phone  传递参数：type=user
     * 2.通过主页的我的快件，点击未取件的快递生成取件二维码，应包含code，传递参数：type=express&code=取件码数据
     * @param request
     * @param response
     * @return
     */
    //注解注释：此方法返回一个页面，直接跳转至该页面
    @ResponseView("/wx/createQRCode.do")
    public String createQrCode(HttpServletRequest request, HttpServletResponse response){
        String type = request.getParameter("type");
        String qrCodeContent = null;
        if("express".equals(type)){
            //包含该快递信息，需要获取code
            String code = request.getParameter("code");
            qrCodeContent = "express_"+code;
        }else if("user".equals(type)){
            //展示该手机号的所有快递，需要获取phone
            String phone = UserUtil.getWxPhone(request.getSession());
            qrCodeContent = "phone_"+phone;
        }
        //将二维码需要的数据放入Session中
        request.getSession().setAttribute("qrcode",qrCodeContent);
        //跳转至二维码界面
        return "personQRcode.html";
    }

    /**
     * 获取到session中二维码数据，向二维码页面传递数据
     * @param request
     * @param response
     * @return
     */
    @ResponseBody("/wx/qrcode.do")
    public String getQrCode(HttpServletRequest request, HttpServletResponse response){
        //获取session中包含的二维码数据
        String qrCodeContent = (String) request.getSession().getAttribute("qrcode");
        Message msg = new Message();
        if(qrCodeContent == null){
            //没有获取到二维码数据
            msg.setStatus(-1);
            msg.setResult("二维码获取出错");
        }else{
            //获取到二维码数据
            msg.setStatus(0);
            msg.setResult("二维码获取成功");
            msg.setData(qrCodeContent);
        }

        return JSONUtil.toJSON(msg);

    }
}
