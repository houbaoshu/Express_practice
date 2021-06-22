package com.kaikeba.service;

import com.kaikeba.bean.Express;
import com.kaikeba.dao.BaseExpressDao;
import com.kaikeba.dao.impl.ExpressDaoMysql;
import com.kaikeba.exception.DuplicateCodeException;
import com.kaikeba.util.RandomUtil;

import java.util.List;
import java.util.Map;

public class ExpressService {
    private static BaseExpressDao dao = new ExpressDaoMysql();

    /**
     * 用于查询数据库中的全部快递（总数+新增），待取件快递（总数+新增）
     *
     * @return [{size:总数,day:新增},{size:总数,day:新增}]
     */
    public static List<Map<String, Integer>> console() {
        return dao.console();
    }

    /**
     * 用于查询所有快递
     *
     * @param limit      是否分页的标记，true表示分页。false表示查询所有快递
     * @param offset     SQL语句的起始索引
     * @param pageNumber 页查询的数量
     * @return 快递的集合
     */
    public static List<Express> findAll(boolean limit, int offset, int pageNumber) {
        return dao.findAll(limit, offset, pageNumber);
    }

    /**
     * 根据快递单号，查询快递信息
     *
     * @param number 单号
     * @return 查询的快递信息，单号不存在时返回null
     */
    public static Express findByNumber(String number) {
        return dao.findByNumber(number);
    }

    /**
     * 根据快递取件码，查询快递信息
     *
     * @param code 取件码
     * @return 查询的快递信息，取件码不存在时返回null
     */
    public static Express findByCode(String code) {
        return dao.findByCode(code);
    }

    /**
     * 根据用户手机号码，查询他所有的快递信息
     *
     * @param userPhone 手机号码
     * @return 查询的快递信息列表
     */
    public static List<Express> findByUserPhone(String userPhone) {
        return dao.findByUserPhone(userPhone);
    }

    /**
     * 根据录入人手机号码，查询录入的所有记录
     *
     * @param sysPhone 手机号码
     * @return 查询的快递信息列表
     */
    public static List<Express> findBySysPhone(String sysPhone) {
        return dao.findBySysPhone(sysPhone);
    }

    /**
     * 快递的录入
     *
     * @param express 要录入的快递对象
     * @return 录入的结果，true表示成功，false表示失败
     */
    public static boolean insert(Express express) {
        //controller对象传来的对象没有取件码，生成后插入数据库，若取件码重复则再次生成
        String code = String.valueOf(RandomUtil.getCode());
        express.setCode(code);
        boolean insert = false;
        try {
            insert = dao.insert(express);
            //插入成功则发送验证码
            if (insert) {
                System.out.println("收件人手机号码：" + express.getUserPhone() + " 验证码：" + express.getCode());
            }
            return insert;
        } catch (DuplicateCodeException duplicateCodeException) {
            return insert(express);
        }
    }

    /**
     * 快递的修改
     *
     * @param id         要修改的快递id
     * @param newExpress 新的快递对象（number,username,userPhone,company,status）
     * @return 修改的结果，true表示成功，false表示失败
     */
    public static boolean update(int id, Express newExpress) {
        //快递手机号码已修改，安全起见，则需重新生成验证码，重新发送短信（已签收的空快递已要给收件人发信息？）
        if (newExpress.getUserPhone() != null) {
            delete(id);
            return insert(newExpress);
        } else {
            //更新number,company,userName,status
            boolean update = dao.update(id, newExpress);
            //未取件改已取件，先查找到该快递获得其取件码后更新
            Express express = findByNumber(newExpress.getNumber());
            if (newExpress.getStatus() == 1) {
                updateStatus(express.getCode());
            }
            return update;
        }

    }


    /**
     * 更改快递的状态为1，表示取件完成
     *
     * @param code 要修改的快递取件码
     * @return 修改的结果，true表示成功，false表示失败
     */
    public static boolean updateStatus(String code) {
        return dao.updateStatus(code);
    }

    /**
     * 根据id，删除单个快递信息
     *
     * @param id 要删除的快递id
     * @return 删除的结果，true表示成功，false表示失败
     */
    public static boolean delete(int id) {
        return dao.delete(id);
    }
    /**
     *  根据手机号码和状态码查询快递
     * @param userPhone
     * @param status
     * @return
     */
    public static List<Express> findByUserPhoneAndStatus(String userPhone,int status){
        return dao.findByUserPhoneAndStatus(userPhone, status);
    }
}
