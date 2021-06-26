package com.kaikeba.dao.impl;

import com.kaikeba.bean.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDaoMysqlTest {

    @Test
    public void findAll() {

        UserDaoMysql userDaoMysql = new UserDaoMysql();
        List<User> all = userDaoMysql.findAll(false, 0, 3);
        System.out.println(all);

    }


    @Test
    public void findByUserPhone() {
        UserDaoMysql userDaoMysql = new UserDaoMysql();
        userDaoMysql.findByUserPhone("");
    }

    @Test
    public void insert() {
        UserDaoMysql userDaoMysql = new UserDaoMysql();
        User user = new User("haha", "haha", "123", "123");
        boolean insert = userDaoMysql.insert(user);
        System.out.println(insert);
    }

    @Test
    public void update() {
        UserDaoMysql userDaoMysql = new UserDaoMysql();
        User user = new User("heihei", "heihei", "321", "321");
        boolean update = userDaoMysql.update(5, user);
        System.out.println(update);

    }

    @Test
    public void delete() {
        UserDaoMysql userDaoMysql = new UserDaoMysql();
        boolean delete = userDaoMysql.delete(28);
        System.out.println(delete);
    }
}