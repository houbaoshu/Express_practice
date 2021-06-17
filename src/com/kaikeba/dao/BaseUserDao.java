package com.kaikeba.dao;

import com.kaikeba.bean.User;

import java.util.List;
import java.util.Map;

public interface BaseUserDao {
    /**
     * 用于控制台查询用户信息
     *
     * @return 用户总人数numOfUser，新增用户数numOfRegister
     */
    Map<String, Integer> console();

    /**
     * 查找所有
     *
     * @param limit
     * @param offset
     * @param pageNumber
     * @return
     */
    List<User> findAll(boolean limit, int offset, int pageNumber);

    /**
     * 根据手机号码查找快递
     *
     * @param userPhone
     * @return
     */
    User findByUserPhone(String userPhone);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    boolean insert(User user);

    /**
     * 修改用户
     *
     * @param id
     * @param user
     * @return
     */
    boolean update(int id, User user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    boolean delete(int id);
}
