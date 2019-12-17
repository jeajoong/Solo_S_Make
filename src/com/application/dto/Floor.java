package com.application.dto;

import java.math.BigDecimal;

public class Floor { // 층별정보 DTO(테이블의 컬렁과 동일)
  
  private String bldTypeGBCD;
  private String buildingPK;
  private String flrPK;
  private String flrGBCD;
  private int flrNo;       //층수
  private String flrNoNM;     //층이름
  private String flrCD;     //층코드
  private String mainPurpsCD; 
  private String mainPurpsNM;
  private String strctCD;
  private String strctNM;
  private BigDecimal area;
  private String mainAtchGBCD;
  private int mainAtchSeqNo;
  private String mainbuildingPK;
  private String areaExctYn;
  private int flrSeqNo;
  
  
  @Override
  public String toString() {
    return "Floor [bldTypeGBCD=" + bldTypeGBCD + ", buildingPK=" + buildingPK + ", flrPK=" + flrPK
        + ", flrGBCD=" + flrGBCD + ", flrNo=" + flrNo + ", flrNoNM=" + flrNoNM + ", flrCD=" + flrCD
        + ", mainPurpsCD=" + mainPurpsCD + ", mainPurpsNM=" + mainPurpsNM + ", strctCD=" + strctCD
        + ", strctNM=" + strctNM + ", area=" + area + ", mainAtchGBCD=" + mainAtchGBCD
        + ", mainAtchSeqNo=" + mainAtchSeqNo + ", mainbuildingPK=" + mainbuildingPK
        + ", areaExctYn=" + areaExctYn + ", flrSeqNo=" + flrSeqNo + "]";
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
  public String getFlrPK() {
    return flrPK;
  }
  public void setFlrPK(String flrPK) {
    this.flrPK = flrPK;
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
  public String getFlrCD() {
    return flrCD;
  }
  public void setFlrCD(String flrCD) {
    this.flrCD = flrCD;
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
  public String getStrctCD() {
    return strctCD;
  }
  public void setStrctCD(String strctCD) {
    this.strctCD = strctCD;
  }
  public String getStrctNM() {
    return strctNM;
  }
  public void setStrctNM(String strctNM) {
    this.strctNM = strctNM;
  }
  public BigDecimal getArea() {
    return area;
  }
  public void setArea(BigDecimal area) {
    this.area = area;
  }
  public String getMainAtchGBCD() {
    return mainAtchGBCD;
  }
  public void setMainAtchGBCD(String mainAtchGBCD) {
    this.mainAtchGBCD = mainAtchGBCD;
  }
  public int getMainAtchSeqNo() {
    return mainAtchSeqNo;
  }
  public void setMainAtchSeqNo(int mainAtchSeqNo) {
    this.mainAtchSeqNo = mainAtchSeqNo;
  }
  public String getMainbuildingPK() {
    return mainbuildingPK;
  }
  public void setMainbuildingPK(String mainbuildingPK) {
    this.mainbuildingPK = mainbuildingPK;
  }
  public String getAreaExctYn() {
    return areaExctYn;
  }
  public void setAreaExctYn(String areaExctYn) {
    this.areaExctYn = areaExctYn;
  }
  public int getFlrSeqNo() {
    return flrSeqNo;
  }
  public void setFlrSeqNo(int flrSeqNo) {
    this.flrSeqNo = flrSeqNo;
  }
  
  
  
}
