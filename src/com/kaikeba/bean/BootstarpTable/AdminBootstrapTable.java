package com.kaikeba.bean.BootstarpTable;


import com.kaikeba.bean.Admin;
import com.kaikeba.util.DateFormatUtil;


public class AdminBootstrapTable {
    private int id;
    private String username;
    private String password;
    private String userPhone;
    private String idCardNumber;
    private String numOfDelivered;
    private String registerTime;
    private String lastLogTime;

    public AdminBootstrapTable(Admin admin) {
        this.id = admin.getId();
        this.username = admin.getUsername();
        this.password = admin.getPassword();
        this.userPhone = admin.getUserPhone();
        this.idCardNumber = admin.getIdCardNumber();
        this.numOfDelivered = String.valueOf(admin.getNumOfDelivered());
        this.registerTime = admin.getRegisterTime() == null ? "未注册" : DateFormatUtil.format(admin.getRegisterTime());
        this.lastLogTime = admin.getLastLogTime() == null ? "未登录" : DateFormatUtil.format(admin.getLastLogTime());
    }

    public AdminBootstrapTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getNumOfDelivered() {
        return numOfDelivered;
    }

    public void setNumOfDelivered(String numOfDelivered) {
        this.numOfDelivered = numOfDelivered;
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
