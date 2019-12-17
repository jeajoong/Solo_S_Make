package com.application.dto;

public class Member { // 로그인 확인용 member
  private String id;
  private String name;
  private String pwd;
  private String userGrade;
  
  @Override
  public String toString() {
    return "Member [name=" + name + ", id=" + id + ", pwd=" + pwd + ", userGrade=" + userGrade
        + "]";
  }
  
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPwd() {
    return pwd;
  }
  public void setPwd(String pwd) {
    this.pwd = pwd;
  }
  public String getUserGrade() {
    return userGrade;
  }
  public void setUserGrade(String userGrade) {
    this.userGrade = userGrade;
  }
  

}
