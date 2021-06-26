package com.kaikeba.util;

import com.kaikeba.bean.Admin;
import com.kaikeba.bean.User;

import javax.servlet.http.HttpSession;

public class UserUtil {
    /**
     * @param session
     * @return 录入人手机号码
     */
    public static String getUserPhone(HttpSession session) {
        return "18888888888";
    }

    /**
     * @param session
     * @return 录入人姓名
     */
    public static String getUserName(HttpSession session) {
        return (String) session.getAttribute("userName");
    }

    //微信的对象哪生成的？
    public static Object getWxObject(HttpSession session) {
        return session.getAttribute("WxObject");
    }

    //字符识别身份？
    public static String getIdentity(HttpSession session) {
        //session中获得身份信息
        return (String) session.getAttribute("identity");
    }

    public static String getWxPhone(HttpSession session) {
        String identity = getIdentity(session);
        String userPhone = null;
        //是管理员则获得管理员用户对象,再获得手机号码
        if ("admin".equals(identity)) {
            Admin admin = (Admin) session.getAttribute("WxObject");
            if (admin != null) {
                userPhone = admin.getUserPhone();
            }
        } else if ("user".equals(identity)) {
            //是普通用户则获得普通用户对象
            User user = (User) session.getAttribute("WxObject");
            if (user != null) {
                userPhone = user.getUserPhone();
            }
        }
        return userPhone;
    }

    //将用户的手机号与登陆验证码放入session对象中相关联
    public static void setLoginSms(HttpSession session, String userPhone, String code) {
        session.setAttribute(userPhone, code);
    }

    //得到放入用户session对象中的登陆验证码
    public static String getLoginSms(HttpSession session, String userPhone) {

        return (String) session.getAttribute(userPhone);
    }

    public static void setWxObjectAndIdentity(HttpSession session, Object object, String identify) {
        session.setAttribute("WxObject", object);
        session.setAttribute("identity", identify);
    }

    public static String getAdminName(HttpSession session) {
        return (String) session.getAttribute("name");
    }
}
