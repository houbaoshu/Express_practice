package com.kaikeba.dao.impl;

import com.kaikeba.bean.Express;
import com.kaikeba.dao.BaseExpressDao;
import com.kaikeba.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作快递的dao
 *
 * @author houbaoshu
 */
public class ExpressDaoMysql implements BaseExpressDao {

    private static final String SQL_CONSOLE = "SELECT COUNT(id) AS data1_size,COUNT(TO_DAYS(in_time)=to_days(now()) OR NULL) AS data1_day,COUNT(status=0 OR NULL) data2_size,COUNT(TO_DAYS(in_time)=TO_DAYS(NOW()) AND status=0 OR NULL) AS data2_day FROM Express";
    private static final String SQL_FIND_ALL = "SELECT * FROM Express";
    private static final String SQL_FIND_LIMIT = "SELECT * FROM Express LIMIT ?,?";
    private static final String SQL_FIND_BY_NUMBER = "SELECT * FROM Express WHERE number=?";
    private static final String SQL_FIND_BY_CODE = "SELECT * FROM Express WHERE code=?";
    private static final String SQL_FIND_BY_USERPHONE = "SELECT * FROM Express WHERE user_phone=?";
    private static final String SQL_FIND_BY_SYSPHONE = "SELECT * FROM Express WHERE sys_phone=?";
    private static final String SQL_INSERT = "INSERT INTO Express(number, username, user_phone, company, code, sys_phone,in_time) VALUES(?,?,?,?,?,?,NOW())";
    private static final String SQL_UPDATE = "UPDATE Express SET number=?,username=?,company=?,status=? WHERE id=?";
    private static final String SQL_UPDATE_STATUS = "UPDATE Express SET status=1,out_time=NOW(),code=NULL WHERE code=?";
    private static final String SQL_DELETE = "DELETE FROM Express WHERE id=?";


    /**
     * 用于查询数据库中的全部快递（总数+新增），待取件快递（总数+新增）
     *
     * @return [{size:总数,day:新增},{size:总数,day:新增}]
     */
    @Override
    public List<Map<String, Integer>> console() {
        ArrayList<Map<String, Integer>> data = new ArrayList<>();
        //1、获取连接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        // 2、预编译sql语句
        try {
            statement = connection.prepareStatement(SQL_CONSOLE);
            //4、执行
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int data1_size = resultSet.getInt("data1_size");
                int data1_day = resultSet.getInt("data1_day");
                int data2_size = resultSet.getInt("data2_size");
                int data2_day = resultSet.getInt("data2_day");
                Map data1 = new HashMap();
                data1.put("data1_size", data1_size);
                data1.put("data1_day", data1_day);
                Map data2 = new HashMap();
                data2.put("data2_size", data2_size);
                data2.put("data2_day", data2_size);
                data.add(data1);
                data.add(data2);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //5、释放资源
            DruidUtil.close(connection, statement, resultSet);
        }
        return data;
    }

    /**
     * 用于查询所有快递
     *
     * @param limit      是否分页的标记，true表示分页。false表示查询所有快递
     * @param offset     SQL语句的起始索引
     * @param pageNumber 页查询的数量
     * @return 快递的集合
     */
    @Override
    public List<Express> findAll(boolean limit, int offset, int pageNumber) {
        List expresses = new ArrayList();
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
                String number = resultSet.getString("number");
                String username = resultSet.getString("username");
                String userPhone = resultSet.getString("user_phone");
                String company = resultSet.getString("company");
                String code = resultSet.getString("code");
                Timestamp inTime = resultSet.getTimestamp("in_time");
                Timestamp outTime = resultSet.getTimestamp("out_time");
                int status = resultSet.getInt("status");
                String sysPhone = resultSet.getString("sys_phone");
                Express express = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                expresses.add(express);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return expresses;
    }

    /**
     * 根据快递单号，查询快递信息
     *
     * @param number 单号
     * @return 查询的快递信息，单号不存在时返回null
     */
    @Override
    public Express findByNumber(String number) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_NUMBER);
            statement.setString(1, number);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String userPhone = resultSet.getString("user_phone");
                String company = resultSet.getString("company");
                String code = resultSet.getString("code");
                Timestamp inTime = resultSet.getTimestamp("in_time");
                Timestamp outTime = resultSet.getTimestamp("out_time");
                int status = resultSet.getInt("status");
                String sysPhone = resultSet.getString("sys_phone");
                Express express = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                return express;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    /**
     * 根据快递取件码，查询快递信息
     *
     * @param code 取件码
     * @return 查询的快递信息，取件码不存在时返回null
     */
    @Override
    public Express findByCode(String code) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_CODE);
            statement.setString(1, code);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String userPhone = resultSet.getString("user_phone");
                String company = resultSet.getString("company");
                String number = resultSet.getString("number");
                Timestamp inTime = resultSet.getTimestamp("in_time");
                Timestamp outTime = resultSet.getTimestamp("out_time");
                int status = resultSet.getInt("status");
                String sysPhone = resultSet.getString("sys_phone");
                Express express = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                return express;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    /**
     * 根据用户手机号码，查询他所有的快递信息
     *
     * @param userPhone 手机号码
     * @return 查询的快递信息列表
     */
    @Override
    public List<Express> findByUserPhone(String userPhone) {
        List expresses = new ArrayList();
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_USERPHONE);
            statement.setString(1, userPhone);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String number = resultSet.getString("number");
                String company = resultSet.getString("company");
                String code = resultSet.getString("code");
                Timestamp inTime = resultSet.getTimestamp("in_time");
                Timestamp outTime = resultSet.getTimestamp("out_time");
                int status = resultSet.getInt("status");
                String sysPhone = resultSet.getString("sys_phone");
                Express express = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                expresses.add(express);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, statement, resultSet);
        }
        return expresses;
    }

    /**
     * 根据录入人手机号码，查询录入的所有记录
     *
     * @param sysPhone 手机号码
     * @return 查询的快递信息列表
     */
    @Override
    public List<Express> findBySysPhone(String sysPhone) {
        List expresses = new ArrayList();
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;

        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_BY_SYSPHONE);
            statement.setString(1, sysPhone);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String number = resultSet.getString("number");
                String company = resultSet.getString("company");
                String code = resultSet.getString("code");
                Timestamp inTime = resultSet.getTimestamp("in_time");
                Timestamp outTime = resultSet.getTimestamp("out_time");
                int status = resultSet.getInt("status");
                String userPhone = resultSet.getString("user_phone");
                Express express = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                expresses.add(express);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, statement, resultSet);
        }
        return expresses;
    }

    /**
     * 快递的录入
     *
     * @param express 要录入的快递对象
     * @return 录入的结果，true表示成功，false表示失败
     */
    @Override
    public boolean insert(Express express) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, express.getNumber());
            statement.setString(2, express.getUsername());
            statement.setString(3, express.getUserPhone());
            statement.setString(4, express.getCompany());
            statement.setString(5, express.getCode());
            statement.setString(6, express.getSysPhone());
            return statement.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, statement, null);
        }
        return false;
    }

    /**
     * 快递的修改
     *
     * @param id         要修改的快递id
     * @param newExpress 新的快递对象（number,username,userPhone,company）
     * @return 修改的结果，true表示成功，false表示失败
     */
    @Override
    public boolean update(int id, Express newExpress) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, newExpress.getNumber());
            statement.setString(2, newExpress.getUsername());
            statement.setString(3, newExpress.getCompany());
            statement.setInt(4, newExpress.getStatus());
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
     * 更改快递的状态为1，表示取件完成
     *
     * @param code 要修改的快递取件码
     * @return 修改的结果，true表示成功，false表示失败
     */
    @Override
    public boolean updateStatus(String code) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_STATUS);
            statement.setString(1, code);
            return statement.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, statement, null);
        }
        return false;
    }

    /**
     * 根据id，删除单个快递信息
     *
     * @param id 要删除的快递id
     * @return 删除的结果，true表示成功，false表示失败
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
