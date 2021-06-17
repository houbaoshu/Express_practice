package com.kaikeba.dao;

import com.kaikeba.bean.Admin;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    void updateLoginTimeAndIP (String username, Date date, String ip);

    /**
     * 管理员根据账号密码登陆
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password);

    /**
     * 获取快递员状态信息
     * @return num_of_admin总的快递员人数， num_of_register今日快递员上线人数
     */
    Map<String, Integer> console();

    /**
     * 查找所有管理员
     * @param limit
     * @param offset
     * @param pageNumber
     * @return
     */
    List<Admin> findAll(boolean limit, int offset, int pageNumber);

    /**
     * 根据用户手机号码查找管理员信息
     * @param userPhone
     * @return
     */
    Admin findByUserPhone(String userPhone);

    /**
     * 添加管理员,需要username,password,userPhone,IDCardNumber
     * @param admin
     * @return
     */
    boolean insert(Admin admin);

    /**
     * 根据id删除管理员
     * @param id
     * @return
     */
    boolean delete(int id);

    /**
     * 更新管理员，可更新项username,password,userPhone,IDCardNumber
     * @param id
     * @param admin
     * @return
     */
    boolean update(int id,Admin admin);

}
