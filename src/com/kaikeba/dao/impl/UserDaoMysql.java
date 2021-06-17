package com.kaikeba.dao.impl;

import com.kaikeba.bean.User;
import com.kaikeba.dao.BaseUserDao;
import com.kaikeba.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoMysql implements BaseUserDao {
    private static final String SQL_FIND_ALL = "SELECT * FROM user";
    private static final String SQL_FIND_LIMIT = "SELECT * FROM user LIMIT ?,?";
    private static final String SQL_FIND_BY_USER_PHONE = "SELECT * FROM user WHERE user_phone=?";
    private static final String SQL_INSERT = "INSERT INTO user (username,password,user_phone,ID_card_number,register_time) VALUES(?,?,?,?,NOW())";
    private static final String SQL_UPDATE = "UPDATE user SET username=?,password=?,user_phone=?,ID_card_number=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM user WHERE id=?";
    private static final String SQL_CONSOLE = "SELECT COUNT(id) AS num_of_user, COUNT(to_days(register_time)=to_days(now()) OR NULL) AS num_of_register FROM user";

    /**
     * 用于控制台查询用户信息
     *
     * @return 用户总人数numOfUser，新增用户数numOfRegister
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
                int numOfUser = resultSet.getInt("num_of_user");
                int numOfRegister = resultSet.getInt("num_of_register");
                Map map = new HashMap();
                map.put("numOfUser", numOfUser);
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
     * 查找所有
     *
     * @param limit
     * @param offset
     * @param pageNumber
     * @return
     */
    @Override
    public List<User> findAll(boolean limit, int offset, int pageNumber) {
        List users = new ArrayList();
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
                Timestamp registerTime = resultSet.getTimestamp("register_time");
                Timestamp lastLogTime = resultSet.getTimestamp("last_log_time");
                String userPhone = resultSet.getString("user_phone");
                String idCardNumber = resultSet.getString("ID_card_number");
                User user = new User(id, username, password, registerTime, lastLogTime, userPhone, idCardNumber);
                users.add(user);
            }
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    /**
     * 根据手机号码查找快递
     *
     * @param userPhone
     * @return
     */
    @Override
    public User findByUserPhone(String userPhone) {
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
                String password = resultSet.getString("password");
                String idCardNumber = resultSet.getString("ID_card_number");
                User user = new User(id, username, password, userPhone, idCardNumber);
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, statement, resultSet);
        }


        return null;
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean insert(User user) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUserPhone());
            statement.setString(4, user.getIdCardNumber());
            return statement.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, statement, null);
        }
        return false;
    }

    /**
     * 修改用户
     *
     * @param id
     * @param user
     * @return
     */
    @Override
    public boolean update(int id, User user) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUserPhone());
            statement.setString(4, user.getIdCardNumber());
            statement.setInt(5, id);
            return statement.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, statement, null);
        }
        return false;
    }

    /**
     * 删除用户
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
}
