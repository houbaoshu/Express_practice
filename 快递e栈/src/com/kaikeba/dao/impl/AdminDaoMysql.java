package com.kaikeba.dao.impl;

import com.kaikeba.dao.BaseAdminDao;
import com.kaikeba.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AdminDaoMysql implements BaseAdminDao {
    private static final String SQL_UPDATE_LOGIN_TIME = "UPDATE e_admin SET login_time=?,login_ip=? WHERE username=?";
    private static final String SQL_LOGIN = "SELECT id FROM e_admin WHERE username=? AND password=?";
    /**
     * 根据用户名，更新登陆时间和登陆ip
     *
     * @param username
     * @param date
     * @param ip
     */
    @Override
    public void updateLoginTime(String username, Date date, String ip) {
        //1、获取连接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        // 2、预编译sql语句
        try {
            statement = connection.prepareStatement(SQL_UPDATE_LOGIN_TIME);
            statement.setDate(1,new java.sql.Date(date.getTime()));
            statement.setString(2, ip);
            statement.setString(3, username);
            //4、执行
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //5、释放资源
            DruidUtil.close(connection, statement, null);
        }
    }

    /**
     * 管理员根据账号密码登陆
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean login(String username, String password) {
        //1、    获取连接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        //2、    预编译sql语句
        try {
            statement = connection.prepareStatement(SQL_LOGIN);
            //3、填充参数
            statement.setString(1, username);
            statement.setString(2,password);
            //4、执行并获取结果
            resultSet = statement.executeQuery();
            //5、 根据查询结果，返回
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //6、释放资源
            DruidUtil.close(connection,statement,resultSet);
        }
        return false;
    }
}
