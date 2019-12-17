package com.application.dto;

import java.math.BigDecimal;

public class ExposArea { //구역별 전유 정보 및 공유 정보를 담는 클래스.
  private String bldTypeGBCD;   // 건축물 유형 구분코드
  private String buildingPK;    // 관리건축물 PK
  private String exposPubusePK; // 해당 테이블의 주 PK (전유공유 테이블 키)
  private String exposGBCD;     // 전유부분인지 공유부분인지 구분 키
  private String mainAtchGBCD;  // 주건물 구분코드
  private String flrGBCD;       // 층 구분코드
  private int flrNo;            // 층수
  private String flrNoNM;       // 층수 명
  private String structCD;      // 구조 코드
  private String structNM;      // 구조 명칭
  private String mainPurpsCD;   // 주 용도 코드
  private String mainPurpsNM;   // 주 용도 목적
  private BigDecimal area;      // 해당 면적
  
  
  @Override
  public String toString() {
    return "ExposArea [bldTypeGBCD=" + bldTypeGBCD + ", buildingPK=" + buildingPK
        + ", exposPubusePK=" + exposPubusePK + ", exposGBCD=" + exposGBCD + ", mainAtchGBCD="
        + mainAtchGBCD + ", flrGBCD=" + flrGBCD + ", flrNo=" + flrNo + ", flrNoNM=" + flrNoNM
        + ", structCD=" + structCD + ", structNM=" + structNM + ", mainPurpsCD=" + mainPurpsCD
        + ", mainPurpsNM=" + mainPurpsNM + ", area=" + area + "]";
  }

  
  public String getBldTypeGBCD() {
    return bldTypeGBCD;
  }

  public void setBldTypeGBCD(String bldTypeGBCD) {
    this.bldTypeGBCD = bldTypeGBCD;
  }

  public String getBuildingPK() {
    return buildingPK;
  }

  public void setBuildingPK(String buildingPK) {
    this.buildingPK = buildingPK;
  }

  public String getExposPubusePK() {
    return exposPubusePK;
  }

  public void setExposPubusePK(String exposPubusePK) {
    this.exposPubusePK = exposPubusePK;
  }

  public String getExposGBCD() {
    return exposGBCD;
  }

  public void setExposGBCD(String exposGBCD) {
    this.exposGBCD = exposGBCD;
  }

  public String getMainAtchGBCD() {
    return mainAtchGBCD;
  }

  public void setMainAtchGBCD(String mainAtchGBCD) {
    this.mainAtchGBCD = mainAtchGBCD;
  }

  public String getFlrGBCD() {
    return flrGBCD;
  }

  public void setFlrGBCD(String flrGBCD) {
    this.flrGBCD = flrGBCD;
  }

  public int getFlrNo() {
    return flrNo;
  }

  public void setFlrNo(int flrNo) {
    this.flrNo = flrNo;
  }

  public String getFlrNoNM() {
    return flrNoNM;
  }

  public void setFlrNoNM(String flrNoNM) {
    this.flrNoNM = flrNoNM;
  }

  public String getStructCD() {
    return structCD;
  }

  public void setStructCD(String structCD) {
    this.structCD = structCD;
  }

  public String getStructNM() {
    return structNM;
  }

  public void setStructNM(String structNM) {
    this.structNM = structNM;
  }

  public String getMainPurpsCD() {
    return mainPurpsCD;
  }

  public void setMainPurpsCD(String mainPurpsCD) {
    this.mainPurpsCD = mainPurpsCD;
  }

  public String getMainPurpsNM() {
    return mainPurpsNM;
  }

  public void setMainPurpsNM(String mainPurpsNM) {
    this.mainPurpsNM = mainPurpsNM;
  }

  public BigDecimal getArea() {
    return area;
  }

  public void setArea(BigDecimal area) {
    this.area = area;
  }
}
