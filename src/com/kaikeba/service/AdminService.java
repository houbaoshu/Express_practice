package com.kaikeba.service;

import com.kaikeba.bean.Admin;
import com.kaikeba.dao.BaseAdminDao;
import com.kaikeba.dao.impl.AdminDaoMysql;
import com.kaikeba.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminService {
    private static BaseAdminDao dao = new AdminDaoMysql();
    /**
     * 获取快递员状态信息
     *
     * @return num_of_admin总的快递员人数， num_of_register今日快递员上线人数
     */
    public static Map<String, Integer> console() {
        return dao.console();
    }

    /**
     * 更新登录时间和ip
     *
     * @param username
     * @param date
     * @param ip
     */
    public static void updateLoginTimeAndIP(String username, Date date, String ip) {
        dao.updateLoginTimeAndIP(username, date, ip);
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public static boolean login(String username, String password) {
        return dao.login(username, password);
    }

    /**
     * 查找所有管理员
     *
     * @param limit
     * @param offset
     * @param pageNumber
     * @return
     */
    public static List<Admin> findAll(boolean limit, int offset, int pageNumber) {
        return dao.findAll(limit, offset, pageNumber);
    }

    /**
     * 根据用户手机号码查找管理员信息
     *
     * @param userPhone
     * @return
     */
    public static Admin findByUserPhone(String userPhone) {
        return dao.findByUserPhone(userPhone);
    }

    /**
     * 添加管理员,需要username,password,userPhone,IDCardNumber
     *
     * @param admin
     * @return
     */
    public static boolean insert(Admin admin) {
        return dao.insert(admin);
    }

    /**
     * 根据id删除管理员
     *
     * @param id
     * @return
     */
    public static boolean delete(int id) {
        return dao.delete(id);
    }

    /**
     * 更新管理员，可更新项username,password,userPhone,IDCardNumber
     *
     * @param id
     * @param admin
     * @return
     */
    public static boolean update(int id, Admin admin) {
        return dao.update(id,admin);
    }

}
