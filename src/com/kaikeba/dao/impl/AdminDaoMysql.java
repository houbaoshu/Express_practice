package com.kaikeba.dao.impl;

import com.kaikeba.bean.Admin;
import com.kaikeba.dao.BaseAdminDao;
import com.kaikeba.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作管理员表的dao
 *
 * @author houbaoshu
 */
public class AdminDaoMysql implements BaseAdminDao {
/*    # select all admin message
    SELECT * FROM admin;
# select limit
    SELECT * FROM admin LIMIT 0,1;
# insert
    INSERT INTO admin (username, password, userPhone, IDCardNumber,register_time) VALUES('1','1','1','1',NOW())
            # delete
    DELETE FROM admin WHERE id=3;
# update
    UPDATE admin SET username='2',password='2',userPhone='3',IDCardNumber='3'WHERE id=1;
# select by user-phone
    SELECT * FROM admin WHERE userPhone='1';*/

    private static final String SQL_UPDATE_LOGIN_TIME = "UPDATE admin SET last_log_time=?,log_ip=? WHERE username=?";
    private static final String SQL_LOGIN = "SELECT id FROM admin WHERE username=? AND password=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM admin";
    private static final String SQL_FIND_LIMIT = "SELECT * FROM admin LIMIT ?,?";
    private static final String SQL_FIND_BY_USER_PHONE = "SELECT * FROM admin WHERE user_phone=?";
    private static final String SQL_INSERT = "INSERT INTO admin (username, password, user_phone, ID_card_number,register_time) VALUES (?,?,?,?,NOW())";
    private static final String SQL_UPDATE = "UPDATE admin SET username=?,password=?,user_phone=?,ID_card_number=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM admin WHERE id=?";
    private static final String SQL_CONSOLE = "SELECT COUNT(id) AS num_of_admin,COUNT(to_days(register_time)=to_days(now()) OR NULL ) AS num_of_register FROM admin";


    /**
     * 根据用户名，更新登陆时间和登陆ip
     *
     * @param username
     * @param date
     * @param ip
     */
    @Override
    public void updateLoginTimeAndIP(String username, java.util.Date date, String ip) {
        //1、获取连接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        // 2、预编译sql语句
        try {
            statement = connection.prepareStatement(SQL_UPDATE_LOGIN_TIME);
            statement.setDate(1, new java.sql.Date(date.getTime()));
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
            statement.setString(2, password);
            //4、执行并获取结果
            resultSet = statement.executeQuery();
            //5、 根据查询结果，返回
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //6、释放资源
            DruidUtil.close(connection, statement, resultSet);
        }
        return false;
    }

    /**
     * 获取快递员状态信息
     *
     * @return num_of_admin总的快递员人数， num_of_register今日快递员上线人数
     */
    @Override
    public Map<String, Integer> console() {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_CONSOLE);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int numOfAdmin = resultSet.getInt("num_of_admin");
                int numOfRegister = resultSet.getInt("num_of_register");
                Map map = new HashMap();
                map.put("numOfAdmin", numOfAdmin);
                map.put("numOfRegister", numOfRegister);
                return map;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, statement, resultSet);
        }
        return null;
    }


    /**
     * 查找所有管理员
     *
     * @param limit
     * @param offset
     * @param pageNumber
     * @return
     */
    @Override
    public List<Admin> findAll(boolean limit, int offset, int pageNumber) {
        List admins = new ArrayList();
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            if (limit) {
                statement = connection.prepareStatement(SQL_FIND_LIMIT);
                statement.setInt(1, offset);
                statement.setInt(2, pageNumber);
            } else {
                statement = connection.prepareStatement(SQL_FIND_ALL);
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Timestamp lastLogTime = resultSet.getTimestamp("last_log_time");
                Timestamp registerTime = resultSet.getTimestamp("register_time");
                String userPhone = resultSet.getString("user_phone");
                String idCardNumber = resultSet.getString("ID_card_number");
                int numOfDelivered = resultSet.getInt("num_of_delivered");
                Admin admin = new Admin(id, username, password, lastLogTime, registerTime, userPhone, idCardNumber, numOfDelivered);
                admins.add(admin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return admins;
    }


    /**
     * 根据用户手机号码查找管理员信息
     *
     * @param userPhone
     * @return
     */
    @Override
    public Admin findByUserPhone(String userPhone) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_USER_PHONE);
            statement.setString(1, userPhone);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String idCordNumber = resultSet.getString("ID_card_number");
                String password = resultSet.getString("password");
                Admin admin = new Admin(id, username, password, userPhone, idCordNumber);
                return admin;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    /**
     * 添加管理员,需要username,password,userPhone,IDCardNumber
     *
     * @param admin
     * @return
     */
    @Override
    public boolean insert(Admin admin) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, admin.getUsername());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getUserPhone());
            statement.setString(4, admin.getIdCardNumber());
            return statement.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, statement, null);
        }
        return false;
    }

    /**
     * 根据id删除管理员
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, id);
            return statement.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, statement, null);
        }
        return false;
    }

    /**
     * 更新管理员，可更新项username,password,userPhone,IDCardNumber
     *
     * @param id
     * @param admin
     * @return
     */
    @Override
    public boolean update(int id, Admin admin) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, admin.getUsername());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getUserPhone());
            statement.setString(4, admin.getIdCardNumber());
            statement.setInt(5, id);
            return statement.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, statement, null);
        }
        return false;
    }
}
