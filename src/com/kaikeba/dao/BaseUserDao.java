package com.kaikeba.dao;

import com.kaikeba.bean.User;
import com.kaikeba.util.UserUtil;

import java.util.Date;
import java.util.List;

public interface BaseUserDao {
    /**
     * 根据用户名，更新登陆时间和登陆ip
     * @param username
     * @param date
     * @param ip
     */
    void updateLoginTimeAndIP (String username, Date date, String ip);
    /**
     * 查找所有
     * @param limit
     * @param offset
     * @param pageNumber
     * @return
     */
    List<User> findAll(boolean limit,int offset,int pageNumber);

    /**
     * 根据手机号码查找快递
     * @param userPhone
     * @return
     */
    User findByUserPhone(String userPhone);

    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean insert(User user);

    /**
     * 修改用户
     * @param id
     * @param user
     * @return
     */
    boolean update(int id, User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    boolean delete(int id);
}
