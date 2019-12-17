package com.application.dto;

public class Address { // 주소를 찾을 때 사용할 DTO

  private String sidoNM;
  private String sigunguNM;
  private String bjdongNM;
  private String hjdongNM;
  private String sigunguCD;
  private String bjdongCD;
  private String hjdongCD;
  private String bun;
  private String ji;
  private String applyStartDay;
  private String applyExpDay;
  
  
  @Override
  public String toString() {
    return "Address [sidoNM=" + sidoNM + ", sigunguNM=" + sigunguNM + ", bjdongNM=" + bjdongNM
        + ", hjdongNM=" + hjdongNM + ", sigunguCD=" + sigunguCD + ", bjdongCD=" + bjdongCD
        + ", hjdongCD=" + hjdongCD + ", bun=" + bun + ", ji=" + ji + ", applyStartDay="
        + applyStartDay + ", applyExpDay=" + applyExpDay + "]";
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

  public String getBun() {
    return bun;
  }
  
  public void setBun(String bun) {
    this.bun = bun;
  }

  public String getJi() {
    return ji;
  }

  public void setJi(String ji) {
    this.ji = ji;
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
