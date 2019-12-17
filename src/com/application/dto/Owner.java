package com.application.dto;

public class Owner { // 통합 건축물 소유자 정보
  private String bldTypeGBCD;
  private String buildingPK;
  private String ownrPK;
  private String name;
  private String ownrGBCD;
  private String quota1;
  private String quota2;
  private String ownshQuota;
  
  @Override
  public String toString() {
    return "Owner [bldTypeGBCD=" + bldTypeGBCD + ", buildingPK=" + buildingPK + ", ownrPK=" + ownrPK
        + ", name=" + name + ", ownrGBCD=" + ownrGBCD + ", quota1=" + quota1 + ", quota2=" + quota2
        + ", ownshQuota=" + ownshQuota + "]";
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
  public String getOwnrPK() {
    return ownrPK;
  }
  public void setOwnrPK(String ownrPK) {
    this.ownrPK = ownrPK;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getOwnrGBCD() {
    return ownrGBCD;
  }
  public void setOwnrGBCD(String ownrGBCD) {
    this.ownrGBCD = ownrGBCD;
  }
  public String getQuota1() {
    return quota1;
  }
  public void setQuota1(String quota1) {
    this.quota1 = quota1;
  }
  public String getQuota2() {
    return quota2;
  }
  public void setQuota2(String quota2) {
    this.quota2 = quota2;
  }
  public String getOwnshQuota() {
    return ownshQuota;
  }
  public void setOwnshQuota(String ownshQuota) {
    this.ownshQuota = ownshQuota;
  }
  
  
}
