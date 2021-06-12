package com.kaikeba.bean;


import java.sql.Timestamp;

public class Admin {

    private int id;
    private String username;
    private String password;
    private String logIp;
    private java.sql.Timestamp lastLogTime;
    private java.sql.Timestamp registerTime;
    private String userPhone;
    private String idCardNumber;
    private int numOfDelivered;

    public Admin() {
    }

    public Admin(int id, String username, String password, Timestamp lastLogTime, Timestamp registerTime, String userPhone, String idCardNumber, int numOfDelivered) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastLogTime = lastLogTime;
        this.registerTime = registerTime;
        this.userPhone = userPhone;
        this.idCardNumber = idCardNumber;
        this.numOfDelivered = numOfDelivered;
    }

    public Admin(String username, String password, String userPhone, String idCardNumber) {
        this.username = username;
        this.password = password;
        this.userPhone = userPhone;
        this.idCardNumber = idCardNumber;
    }

    public Admin(int id, String username, String password, String userPhone, String idCardNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userPhone = userPhone;
        this.idCardNumber = idCardNumber;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", logIp='" + logIp + '\'' +
                ", lastLogTime=" + lastLogTime +
                ", registerTime=" + registerTime +
                ", userPhone='" + userPhone + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", numOfDelivered=" + numOfDelivered +
                '}';
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


    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }


    public java.sql.Timestamp getLastLogTime() {
        return lastLogTime;
    }

    public void setLastLogTime(java.sql.Timestamp lastLogTime) {
        this.lastLogTime = lastLogTime;
    }


    public java.sql.Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(java.sql.Timestamp registerTime) {
        this.registerTime = registerTime;
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


    public int getNumOfDelivered() {
        return numOfDelivered;
    }

    public void setNumOfDelivered(int numOfDelivered) {
        this.numOfDelivered = numOfDelivered;
    }

}
