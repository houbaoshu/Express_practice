package com.kaikeba.dao.impl;

import com.kaikeba.bean.Express;
import com.kaikeba.dao.impl.ExpressDaoMysql;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ExpressDaoMysqlTest {

    @Test
    public void console() {
        ExpressDaoMysql expressDaoMysql = new ExpressDaoMysql();
        List<Map<String, Integer>> console = expressDaoMysql.console();
        System.out.println(console);
    }

    @Test
    public void findAll() {
        ExpressDaoMysql expressDaoMysql = new ExpressDaoMysql();
        List<Express> all = expressDaoMysql.findAll(true, 1, 1);
        System.out.println(all);
    }

    @Test
    public void findByNumber() {
        ExpressDaoMysql expressDaoMysql = new ExpressDaoMysql();
        Express byNumber = expressDaoMysql.findByNumber("123");
        System.out.println(byNumber);
    }

    @Test
    public void findByCode() {
        ExpressDaoMysql expressDaoMysql = new ExpressDaoMysql();
        Express byCode = expressDaoMysql.findByCode("666666");
        System.out.println(byCode);
    }

    @Test
    public void findByUserPhone() {
        ExpressDaoMysql expressDaoMysql = new ExpressDaoMysql();
        List<Express> byUserPhone = expressDaoMysql.findByUserPhone("13843838438");
        for (Express express : byUserPhone) {
            System.out.println(express);
        }
    }

    @Test
    public void findBySysPhone() {
        ExpressDaoMysql expressDaoMysql = new ExpressDaoMysql();
        List<Express> bySysPhone = expressDaoMysql.findBySysPhone("15555555555");
        for (Express express : bySysPhone) {
            System.out.println(express);

        }
    }
/*
    @Test
    public void insert() {
        ExpressDaoMysql expressDaoMysql = new ExpressDaoMysql();
        Express express = new Express("6","6","13843838438","123", "666672", "15555555555");
        boolean insert = expressDaoMysql.insert(express);
        System.out.println(insert);
    }

    @Test
    public void update() {
        ExpressDaoMysql expressDaoMysql = new ExpressDaoMysql();
        Express express = new Express("7","6","13843838438","123", "666673", "15555555555");
        boolean update = expressDaoMysql.update(1, express);
        System.out.println(update);

    }*/

    @Test
    public void updateStatus() {
        ExpressDaoMysql expressDaoMysql = new ExpressDaoMysql();
        boolean b = expressDaoMysql.updateStatus("666669");
        System.out.println(b);
    }

    @Test
    public void delete() {
        ExpressDaoMysql expressDaoMysql = new ExpressDaoMysql();
        boolean delete = expressDaoMysql.delete(4);
        System.out.println(delete);
    }
    @Test
    public void findByUserPhoneAndStatus() {
        ExpressDaoMysql expressDaoMysql = new ExpressDaoMysql();
        List<Express> byUserPhoneAndStatus = expressDaoMysql.findByUserPhoneAndStatus("13843838438", 1);
        System.out.println(byUserPhoneAndStatus);
    }
}