package com.application.vo;

public class Address {

  private String sidoNM;
  private String sigunguNM;
  private String bjdongNM;
  private String hjdongNM;
  private String sigunguCD;
  private String bjdongCD;
  private String hjdongCD;
  private String applyStartDay;
  private String applyExpDay;
  
  



  @Override
  public String toString() {
    return "Address [sidoNM=" + sidoNM + ", sigunguNM=" + sigunguNM + ", bjdongNM=" + bjdongNM
        + ", hjdongNM=" + hjdongNM + ", sigunguCD=" + sigunguCD + ", bjdongCD=" + bjdongCD
        + ", hjdongCD=" + hjdongCD + ", applyStartDay=" + applyStartDay + ", applyExpDay="
        + applyExpDay + "]";
  }

  
  public String getSidoNM() {
    return sidoNM;
  }

  public void setSidoNM(String sidoNM) {
    this.sidoNM = sidoNM;
  }

  public String getSigunguNM() {
    return sigunguNM;
  }

  public void setSigunguNM(String sigunguNM) {
    this.sigunguNM = sigunguNM;
  }

  public String getBjdongNM() {
    return bjdongNM;
  }

  public void setBjdongNM(String bjdongNM) {
    this.bjdongNM = bjdongNM;
  }

  public String getHjdongNM() {
    return hjdongNM;
  }

  public void setHjdongNM(String hjdongNM) {
    this.hjdongNM = hjdongNM;
  }

  public String getSigunguCD() {
    return sigunguCD;
  }

  public void setSigunguCD(String sigunguCD) {
    this.sigunguCD = sigunguCD;
  }

  public String getBjdongCD() {
    return bjdongCD;
  }

  public void setBjdongCD(String bjdongCD) {
    this.bjdongCD = bjdongCD;
  }

  public String getHjdongCD() {
    return hjdongCD;
  }

  public void setHjdongCD(String hjdongCD) {
    this.hjdongCD = hjdongCD;
  }

  public String getApplyStartDay() {
    return applyStartDay;
  }

  public void setApplyStartDay(String applyStartDay) {
    this.applyStartDay = applyStartDay;
  }

  public String getApplyExpDay() {
    return applyExpDay;
  }

  public void setApplyExpDay(String applyExpDay) {
    this.applyExpDay = applyExpDay;
  }
  
  
  
}