package com.kaikeba.bean.BootstarpTable;

import com.kaikeba.bean.User;
import com.kaikeba.util.DateFormatUtil;


public class UserBootstrapTable {

    private int id;
    private String username;
    private String userPhone;
    private String password;
    private String idCardNumber;
    private String registerTime;
    private String lastLogTime;

    public UserBootstrapTable() {
    }

    public UserBootstrapTable(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.userPhone = user.getUserPhone();
        this.idCardNumber = user.getIdCardNumber();
        this.registerTime = user.getRegisterTime() == null ? "未注册" : DateFormatUtil.format(user.getRegisterTime());
        this.lastLogTime = user.getLastLogTime() == null ? "未登录" : DateFormatUtil.format(user.getLastLogTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = UserBootstrapTable.this.username;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getLastLogTime() {
        return lastLogTime;
    }

    public void setLastLogTime(String lastLogTime) {
        this.lastLogTime = lastLogTime;
    }
}
