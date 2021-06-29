package com.kaikeba.bean;


import java.sql.Timestamp;

public class User {

  private int id;
  private String username;
  private String password;
  private int numOfExpress;
  private java.sql.Timestamp registerTime;
  private java.sql.Timestamp lastLogTime;
  private String userPhone;
  private String idCardNumber;

  public User() {
  }

  public User(int id, String username, String password, Timestamp registerTime, Timestamp lastLogTime, String userPhone, String idCardNumber) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.registerTime = registerTime;
    this.lastLogTime = lastLogTime;
    this.userPhone = userPhone;
    this.idCardNumber = idCardNumber;
  }

    public User(String username, String password, String userPhone) {
      this.username = username;
      this.password = password;
      this.userPhone = userPhone;
    }

    @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", registerTime=" + registerTime +
            ", lastLogTime=" + lastLogTime +
            ", userPhone='" + userPhone + '\'' +
            ", idCardNumber='" + idCardNumber + '\'' +
            '}';
  }

  public User(int id, String username, String password, String userPhone, String idCardNumber) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.userPhone = userPhone;
    this.idCardNumber = idCardNumber;
  }

  public User(String username, String password, String userPhone, String idCardNumber) {
    this.username = username;
    this.password = password;
    this.userPhone = userPhone;
    this.idCardNumber = idCardNumber;
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


  public int getNumOfExpress() {
    return numOfExpress;
  }

  public void setNumOfExpress(int numOfExpress) {
    this.numOfExpress = numOfExpress;
  }


  public java.sql.Timestamp getRegisterTime() {
    return registerTime;
  }

  public void setRegisterTime(java.sql.Timestamp registerTime) {
    this.registerTime = registerTime;
  }


  public java.sql.Timestamp getLastLogTime() {
    return lastLogTime;
  }

  public void setLastLogTime(java.sql.Timestamp lastLogTime) {
    this.lastLogTime = lastLogTime;
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

}
