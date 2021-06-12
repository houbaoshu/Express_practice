package com.kaikeba.util;

import javax.servlet.http.HttpSession;

public class UserUtil {
    public static String getUserPhone(HttpSession session) {
        return "18888888888";
    }

    public static String getUserName(HttpSession session) {
        return (String)session.getAttribute("AdminUsername");
    }
}
