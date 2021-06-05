package com.kaikeba.service;

import com.kaikeba.dao.BaseAdminDao;
import com.kaikeba.dao.impl.AdminDaoMysql;

import java.util.Date;

public class AdminService {
    private static BaseAdminDao dao = new AdminDaoMysql();

    /**
     * 更新登录时间和ip
     * @param username
     * @param date
     * @param ip
     */
    public static void updateLoginTimeAndIP(String username, Date date, String ip) {
        dao.updateLoginTime(username, date,ip);
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public static boolean login(String username, String password) {
        return dao.login(username,password);
    }

}
