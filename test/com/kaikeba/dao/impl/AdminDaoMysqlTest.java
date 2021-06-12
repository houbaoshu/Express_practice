package com.kaikeba.dao.impl;

import com.kaikeba.bean.Admin;
import jdk.swing.interop.SwingInterOpUtils;
import org.junit.Test;

import java.util.List;

public class AdminDaoMysqlTest {

    @Test
    public void updateLoginTime() {

    }

    @Test
    public void login() {
    }

    @Test
    public void findAll() {
        AdminDaoMysql adminDaoMysql = new AdminDaoMysql();
        List<Admin> all = adminDaoMysql.findAll(false, 0, 1);
        System.out.println(all);
    }

    @Test
    public void findByUserPhone() {
        AdminDaoMysql adminDaoMysql = new AdminDaoMysql();
        Admin byUserPhone = adminDaoMysql.findByUserPhone("13843838438");
        System.out.println(byUserPhone);
    }

    @Test
    public void insert() {
        AdminDaoMysql adminDaoMysql = new AdminDaoMysql();
        Admin admin = new Admin("123", "123", "13843838438", "360733199901012323");
        System.out.println(admin);
        boolean insert = adminDaoMysql.insert(admin);
        System.out.println(insert);
    }

    @Test
    public void delete() {
        AdminDaoMysql adminDaoMysql = new AdminDaoMysql();
        boolean delete = adminDaoMysql.delete(7);
        System.out.println(delete);
    }

    @Test
    public void update() {
        Admin admin = new Admin("321", "321", "12322234444", "360733199922223333");
        AdminDaoMysql adminDaoMysql = new AdminDaoMysql();
        boolean update = adminDaoMysql.update(1,admin);
        System.out.println(update);
    }
}