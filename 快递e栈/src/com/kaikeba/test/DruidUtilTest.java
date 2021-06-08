package com.kaikeba.test;

import com.kaikeba.util.DruidUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DruidUtilTest {

    @Test
    public void getConnection() {
        //1、    获取连接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        //2、    预编译sql语句
        try {
            statement = connection.prepareStatement("select * from admin;");
            //3、填充参数
            //4、执行并获取结果
            resultSet = statement.executeQuery();
            //5、 根据查询结果，返回
            System.out.println(resultSet.next());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //6、释放资源
            DruidUtil.close(connection,statement,resultSet);
        }
    }

    @Test
    public void close() {
    }
}