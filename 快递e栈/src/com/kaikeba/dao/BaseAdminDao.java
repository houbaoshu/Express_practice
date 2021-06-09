package com.kaikeba.dao;

import java.util.Date;

/**
 * 用于定义admin表格的操作规范
 * @author houbaoshu
 */
public interface BaseAdminDao {
    /**
     * 根据用户名，更新登陆时间和登陆ip
     * @param username
     * @param date
     * @param ip
     */
    void updateLoginTime (String username, Date date, String ip);

    /**
     * 管理员根据账号密码登陆
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password);
}
