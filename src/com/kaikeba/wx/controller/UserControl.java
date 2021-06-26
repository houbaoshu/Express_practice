package com.kaikeba.wx.controller;

import com.kaikeba.bean.Admin;
import com.kaikeba.bean.Message;
import com.kaikeba.bean.User;
import com.kaikeba.mvc.annotation.ResponseBody;
import com.kaikeba.service.AdminService;
import com.kaikeba.service.UserService;
import com.kaikeba.util.JSONUtil;
import com.kaikeba.util.RandomUtil;
import com.kaikeba.util.SmsUtil;
import com.kaikeba.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 处理用户相关请求的类，如登录、退出、获取用户身份(快递员或普通用户)
 */
public class UserControl {
    /**
     * 发送登陆验证码，并将验证码保存到用户的session对象中
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody("/wx/loginSms.do")
    public String loginSms(HttpServletRequest request, HttpServletResponse response) {
        String userPhone = request.getParameter("userPhone");
        //产生登录验证码
        String code = String.valueOf(RandomUtil.getCode());
        System.out.println("登录短信验证码为：" + code);//拟发送短信
        //发送短信
        boolean login = SmsUtil.login(userPhone, code);
        Message msg = new Message();
        if (login) {
            msg.setStatus(0);
            msg.setResult("发送成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("发送失败");
        }
        //将取件码放入用户的session对象中，与用户手机号相关联
        UserUtil.setLoginSms(request.getSession(), userPhone, code);
        return JSONUtil.toJSON(msg);
    }


    /**
     * 对用户进行登陆/注册，快递员不开放入口，快递员的注册只能通过web管理端
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody("/wx/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        Message msg = new Message();
        //接收userPhone code
        String userPhone = request.getParameter("userPhone");
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        //sms码从session获得
        String loginSmsCode = UserUtil.getLoginSms(session, userPhone);
        //与sms验证码比对
        if (loginSmsCode == null) {//用户未获取登录验证码
            msg.setStatus(-1);
            msg.setResult("用户未获取登录验证码");
        } else if (code.equals(loginSmsCode)) {
            //是否是新用户
            if (UserService.findByUserPhone(userPhone) == null) {//是新用户
                UserService.insert(new User(null, null, userPhone, null));
            }
            //判断是用户还是管理员
            Admin admin = AdminService.findByUserPhone(userPhone);
            if (admin != null) {
                msg.setStatus(1);
                msg.setResult("快递员登录");
                //将快递员的Bean对象数据及身份信息放入对应的session中
                UserUtil.setWxObjectAndIdentity(session, admin, "admin");
            } else {
                msg.setStatus(0);
                msg.setResult("用户登录");
                //将普通用户的Bean对象数据及身份信息放入对应的session中
                User user = UserService.findByUserPhone(userPhone);
                UserUtil.setWxObjectAndIdentity(session, user, "user");
            }
        } else {
            msg.setStatus(-2);
            msg.setResult("验证码不正确");
        }
        return JSONUtil.toJSON(msg);
    }

    /**
     * 获取登陆者的身份信息
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody("/wx/userInfo.do")
    public String userInfo(HttpServletRequest request, HttpServletResponse response) {
        Message msg = new Message();
        HttpSession session = request.getSession();
        String identity = UserUtil.getIdentity(session);//session获得用户身份
        if ("admin".equals(identity)) {
            msg.setStatus(1);
            msg.setResult(identity);
            //获得管理员对象
            Admin admin = (Admin) UserUtil.getWxObject(session);
            msg.setData(admin);
        } else if ("user".equals(identity)) {
            msg.setStatus(0);
            msg.setResult(identity);
            //获得用户对象
            User user = (User) UserUtil.getWxObject(session);
            msg.setData(user);
        }
        return JSONUtil.toJSON(msg);

    }

    /**
     * 退出系统，销毁session
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody("/wx/logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        //销毁session
        request.getSession().invalidate();
        //给前端回复
        Message message = new Message(0, "退出系统", null);
        HttpSession session = request.getSession();//重置session？
        return JSONUtil.toJSON(message);
    }

}
