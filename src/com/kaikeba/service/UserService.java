package com.kaikeba.service;

import com.kaikeba.bean.User;
import com.kaikeba.dao.BaseUserDao;
import com.kaikeba.dao.impl.UserDaoMysql;

import java.util.List;

public class UserService  {
    private static BaseUserDao dao = new UserDaoMysql();

    /**
     * 查找所有
     *
     * @param limit
     * @param offset
     * @param pageNumber
     * @return
     */
    public static List<User> findAll(boolean limit, int offset, int pageNumber) {
        return dao.findAll(limit,offset,pageNumber);
    }

    /**
     * 根据手机号码查找快递
     *
     * @param userPhone
     * @return
     */
    public static User findByUserPhone(String userPhone) {
        return dao.findByUserPhone(userPhone);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    public static boolean insert(User user) {
        return dao.insert(user);
    }

    /**
     * 修改用户
     *
     * @param id
     * @param user
     * @return
     */
    public static boolean update(int id, User user) {
        return dao.update(id,user);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public static boolean delete(int id) {
        return dao.delete(id);
    }
}
