package com.kaikeba.bean;

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
    private String adminPhone;

    public Express() {
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
                ", adminPhone='" + adminPhone + '\'' +
                '}';
    }

    public long getId() {
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


    public long getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

}
