package com.kaikeba.bean;

import java.sql.Timestamp;

public class Express {

    private int id;
    private String number;
    private String username;
    private String userPhone;
    private String company;
    private String code;
    private java.sql.Timestamp inTime;
    private java.sql.Timestamp outTime;
    private int status;
    private String sysPhone;

    public Express() {
    }

    public Express(String number, String username, String userPhone, String company, int status) {
        this.number = number;
        this.username = username;
        this.userPhone = userPhone;
        this.company = company;
        this.status = status;
    }

    public Express(String number, String username, String userPhone, String company, String sysPhone) {
        this.number = number;
        this.username = username;
        this.userPhone = userPhone;
        this.company = company;
        this.sysPhone = sysPhone;
    }

    public Express(int id, String number, String username, String userPhone, String company, String code, Timestamp inTime, Timestamp outTime, int status, String sysPhone) {
        this.id = id;
        this.number = number;
        this.username = username;
        this.userPhone = userPhone;
        this.company = company;
        this.code = code;
        this.inTime = inTime;
        this.outTime = outTime;
        this.status = status;
        this.sysPhone = sysPhone;
    }

    @Override
    public String toString() {
        return "Express{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", username='" + username + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", company='" + company + '\'' +
                ", code='" + code + '\'' +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", status=" + status +
                ", sysPhone='" + sysPhone + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public java.sql.Timestamp getInTime() {
        return inTime;
    }

    public void setInTime(java.sql.Timestamp inTime) {
        this.inTime = inTime;
    }


    public java.sql.Timestamp getOutTime() {
        return outTime;
    }

    public void setOutTime(java.sql.Timestamp outTime) {
        this.outTime = outTime;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getSysPhone() {
        return sysPhone;
    }

    public void setSysPhone(String sysPhone) {
        this.sysPhone = sysPhone;
    }

}
