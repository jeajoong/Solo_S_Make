package com.application.dto;

public class BuildInfo { // 리스트 출력 전용 건물 DTO
  private String bldTypeGBCD;  // 건축물 유형 구분 코드
  private String buildingPK;   // 관리 건축물 PK
  private String regstrGBCD;   // 대장 구분 코드
  private String regstrKINDCD; // 대장 종류 코드
  private String sidoNM;
  private String sigunguNM;
  private String bjdongNM;
  private String bunNum;
  private String jiNum;
  private String bldNM;
  private String mainPurpsNM;     // 주용도
  private String strctNM;    // 주구조
  private String hoNM;       // 리스트에서 검색할 때 전유부(4번)일때 몇 호인지 알수가 없어서 추가적으로 넣음.
  
  @Override
  public String toString() {
    return "BuildInfo [bldTypeGBCD=" + bldTypeGBCD + ", buildingPK=" + buildingPK + ", regstrGBCD="
        + regstrGBCD + ", regstrKINDCD=" + regstrKINDCD + ", sidoNM=" + sidoNM + ", sigunguNM="
        + sigunguNM + ", bjdongNM=" + bjdongNM + ", bunNum=" + bunNum + ", jiNum=" + jiNum
        + ", bldNM=" + bldNM + ", mainPurpsNM=" + mainPurpsNM + ", strctNM=" + strctNM + ", hoNM="
        + hoNM + "]";
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
  public String getRegstrGBCD() {
    return regstrGBCD;
  }
  public void setRegstrGBCD(String regstrGBCD) {
    this.regstrGBCD = regstrGBCD;
  }
  public String getRegstrKINDCD() {
    return regstrKINDCD;
  }
  public void setRegstrKINDCD(String regstrKINDCD) {
    this.regstrKINDCD = regstrKINDCD;
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
  public String getBunNum() {
    return bunNum;
  }
  public void setBunNum(String bunNum) {
    this.bunNum = bunNum;
  }
  public String getJiNum() {
    return jiNum;
  }
  public void setJiNum(String jiNum) {
    this.jiNum = jiNum;
  }
  public String getBldNM() {
    return bldNM;
  }
  public void setBldNM(String bldNM) {
    this.bldNM = bldNM;
  }
  public String getMainPurpsNM() {
    return mainPurpsNM;
  }
  public void setMainPurpsNM(String mainPurpsNM) {
    this.mainPurpsNM = mainPurpsNM;
  }
  public String getStrctNM() {
    return strctNM;
  }
  public void setStrctNM(String strctNM) {
    this.strctNM = strctNM;
  }
  public String getHoNM() {
    return hoNM;
  }
  public void setHoNM(String hoNM) {
    this.hoNM = hoNM;
  }
  
}
