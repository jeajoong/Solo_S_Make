package com.application.vo;

import java.math.BigDecimal;

public class Build { // 조인된 쿼리 내용 추가한 건축물대장 VO객체

  private String bldTypeGBCD;     // 건축물 유형 구분 코드
  private String buildingPK;      // 관리 건축물 PK
  private String regstrGBCD;      // 대장 구분 코드
  private String regstrKINKCD;    // 대장 종류 코드
  private String regstrNo;        // 건축물대장 번호(시군구+법정동+대지구분+번+지)  // BDT_BLDRGST에 실제컬럼 없음
  private String bldNM;           // 건물명
  private String spcmt;           // 특이사항
  private String platLoC;         // 시도+시군구+법정동                                               // BDT_BLDRGST에 실제컬럼 없음
  private String jiBun;           // 번+지                                                                   // BDT_BLDRGST에 실제컬럼 없음
  private BigDecimal platArea;    // 대지면적 (BigDecimal)
  private BigDecimal totArea;     // 연면적    (BigDecimal)
  private BigDecimal archArea;    // 건축면적  (BigDecimal)
  private BigDecimal vlRatEstmTotArea; // 용적율 산정 연면적  (BigDecimal)
  private String strctNM;         // 구조명칭                                                               // BDT_BLDRGST에 실제컬럼 없음
  private String strctCD;         // 구조코드
  private String mainPurpsNM;     // 주 용도 명칭                                                          // BDT_BLDRGST에 실제컬럼 없음
  private String mainPurpsCD;     // 주 용도 코드
  private int grndFlrCNT;         // 지상 층 수
  private int ugrndFlrCNT;        // 지하 층 수
  private BigDecimal bcRat;       // 건폐율  (BigDecimal)
  private BigDecimal vlRat;       // 용적율  (BigDecimal)
  private float heit;             // 높이
  private String roofNM;          // 지붕명칭
  private String roofCD;          // 지붕코드
  private int atchBldCnt;         // 부속 건축물 수
  // 여기까지 일반 건축물 표기사항.
  
  /* 나머지 추가적인 사항들 */
  
  // == 총괄표제부에서 더 필요한 세가지.
  private int hoCNT;        // 호 수
  private int totPkngCNT;   // 총 주차 수
  private int mainBldCnt;   // 주 건축물 수

  private String sigunguCD;    // 시군구 코드
  private String bjdongCD;     // 법정동 코드
  private String hjdongCD;     // 행정동 코드
  private String platGBCD;     // 대지구분코드
  private String bunNum;       // 번 숫자
  private String jiNum;        // 지 숫자
  private int byLotCNT;     // 외필지 수
  private String dongNM;       // 동명
  private String hoNM;         // 호명
  private String dongCD;       // 동코드
  private String hoCD;         // 호코드
  private String etcrcdITEM;   // 기타 기재 사항
  private String violbldYN;    // 위반건축물여부
  private int regstrSEQNO;  // 대장 일련번호
  private String etcPurps;     // 기타용도
  private String etcStruct;    // 기타구조
  private String etcRoof;      // 기타지붕
  private int hhldCNT;      // 세대 수
  private int fmlyCNT;      // 가구 수
  private BigDecimal atchBldArea;  // 총 동 연면적   (BigDecimal)
  private String naRoadCD;     // 새주소 대로 로 코드
  private String naBjdongCD;   // 새주소 법정동 코드
  private String naGrndUgrndCD;// 새주소 지상지하 코드
  private String naMainBun;    // 새주소 본번
  private String naSubBun;     // 새주소 부번
  private String operSrvrRflcYN;// 운영서버 반영여부
  
  
  @Override
  public String toString() {
    return "IilBanBuild [bldTypeGBCD=" + bldTypeGBCD + ", buildingPK=" + buildingPK
        + ", regstrGBCD=" + regstrGBCD + ", regstrKINKCD=" + regstrKINKCD + ", regstrNo=" + regstrNo
        + ", bldNM=" + bldNM + ", spcmt=" + spcmt + ", platLoC=" + platLoC + ", jiBun=" + jiBun
        + ", platArea=" + platArea + ", totArea=" + totArea + ", archArea=" + archArea
        + ", vlRatEstmTotArea=" + vlRatEstmTotArea + ", strctNM=" + strctNM + ", strctCD=" + strctCD
        + ", mainPurpsNM=" + mainPurpsNM + ", mainPurpsCD=" + mainPurpsCD + ", grndFlrCNT="
        + grndFlrCNT + ", ugrndFlrCNT=" + ugrndFlrCNT + ", bcRat=" + bcRat + ", vlRat=" + vlRat
        + ", heit=" + heit + ", roofNM=" + roofNM + ", roofCD=" + roofCD + ", atchBldCnt="
        + atchBldCnt + ", sigunguCD=" + sigunguCD + ", bjdongCD=" + bjdongCD + ", hjdongCD="
        + hjdongCD + ", platGBCD=" + platGBCD + ", bunNum=" + bunNum + ", jiNum=" + jiNum
        + ", byLotCNT=" + byLotCNT + ", dongNM=" + dongNM + ", hoNM=" + hoNM + ", dongCD=" + dongCD
        + ", hoCD=" + hoCD + ", etcrcdITEM=" + etcrcdITEM + ", violbldYN=" + violbldYN
        + ", regstrSEQNO=" + regstrSEQNO + ", etcPurps=" + etcPurps + ", etcStruct=" + etcStruct
        + ", etcRoof=" + etcRoof + ", hhldCNT=" + hhldCNT + ", fmlyCNT=" + fmlyCNT + ", hoCNT="
        + hoCNT + ", mainBldCnt=" + mainBldCnt + ", atchBldArea=" + atchBldArea + ", totPkngCNT="
        + totPkngCNT + ", naRoadCD=" + naRoadCD + ", naBjdongCD=" + naBjdongCD + ", naGrndUgrndCD="
        + naGrndUgrndCD + ", naMainBun=" + naMainBun + ", naSubBun=" + naSubBun
        + ", operSrvrRflcYN=" + operSrvrRflcYN + "]";
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


  public String getRegstrKINKCD() {
    return regstrKINKCD;
  }


  public void setRegstrKINKCD(String regstrKINKCD) {
    this.regstrKINKCD = regstrKINKCD;
  }


  public String getRegstrNo() {
    return regstrNo;
  }


  public void setRegstrNo(String regstrNo) {
    this.regstrNo = regstrNo;
  }


  public String getBldNM() {
    return bldNM;
  }


  public void setBldNM(String bldNM) {
    this.bldNM = bldNM;
  }


  public String getSpcmt() {
    return spcmt;
  }


  public void setSpcmt(String spcmt) {
    this.spcmt = spcmt;
  }


  public String getPlatLoC() {
    return platLoC;
  }


  public void setPlatLoC(String platLoC) {
    this.platLoC = platLoC;
  }


  public String getJiBun() {
    return jiBun;
  }


  public void setJiBun(String jiBun) {
    this.jiBun = jiBun;
  }


  public BigDecimal getPlatArea() {
    return platArea;
  }


  public void setPlatArea(BigDecimal platArea) {
    this.platArea = platArea;
  }


  public BigDecimal getTotArea() {
    return totArea;
  }


  public void setTotArea(BigDecimal totArea) {
    this.totArea = totArea;
  }


  public BigDecimal getArchArea() {
    return archArea;
  }


  public void setArchArea(BigDecimal archArea) {
    this.archArea = archArea;
  }


  public BigDecimal getVlRatEstmTotArea() {
    return vlRatEstmTotArea;
  }


  public void setVlRatEstmTotArea(BigDecimal vlRatEstmTotArea) {
    this.vlRatEstmTotArea = vlRatEstmTotArea;
  }


  public String getStrctNM() {
    return strctNM;
  }


  public void setStrctNM(String strctNM) {
    this.strctNM = strctNM;
  }


  public String getStrctCD() {
    return strctCD;
  }


  public void setStrctCD(String strctCD) {
    this.strctCD = strctCD;
  }


  public String getMainPurpsNM() {
    return mainPurpsNM;
  }


  public void setMainPurpsNM(String mainPurpsNM) {
    this.mainPurpsNM = mainPurpsNM;
  }


  public String getMainPurpsCD() {
    return mainPurpsCD;
  }


  public void setMainPurpsCD(String mainPurpsCD) {
    this.mainPurpsCD = mainPurpsCD;
  }


  public int getGrndFlrCNT() {
    return grndFlrCNT;
  }


  public void setGrndFlrCNT(int grndFlrCNT) {
    this.grndFlrCNT = grndFlrCNT;
  }


  public int getUgrndFlrCNT() {
    return ugrndFlrCNT;
  }


  public void setUgrndFlrCNT(int ugrndFlrCNT) {
    this.ugrndFlrCNT = ugrndFlrCNT;
  }


  public BigDecimal getBcRat() {
    return bcRat;
  }


  public void setBcRat(BigDecimal bcRat) {
    this.bcRat = bcRat;
  }


  public BigDecimal getVlRat() {
    return vlRat;
  }


  public void setVlRat(BigDecimal vlRat) {
    this.vlRat = vlRat;
  }


  public float getHeit() {
    return heit;
  }


  public void setHeit(float heit) {
    this.heit = heit;
  }


  public String getRoofNM() {
    return roofNM;
  }


  public void setRoofNM(String roofNM) {
    this.roofNM = roofNM;
  }


  public String getRoofCD() {
    return roofCD;
  }


  public void setRoofCD(String roofCD) {
    this.roofCD = roofCD;
  }


  public int getAtchBldCnt() {
    return atchBldCnt;
  }


  public void setAtchBldCnt(int atchBldCnt) {
    this.atchBldCnt = atchBldCnt;
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


  public String getPlatGBCD() {
    return platGBCD;
  }


  public void setPlatGBCD(String platGBCD) {
    this.platGBCD = platGBCD;
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


  public int getByLotCNT() {
    return byLotCNT;
  }


  public void setByLotCNT(int byLotCNT) {
    this.byLotCNT = byLotCNT;
  }


  public String getDongNM() {
    return dongNM;
  }


  public void setDongNM(String dongNM) {
    this.dongNM = dongNM;
  }


  public String getHoNM() {
    return hoNM;
  }


  public void setHoNM(String hoNM) {
    this.hoNM = hoNM;
  }


  public String getDongCD() {
    return dongCD;
  }


  public void setDongCD(String dongCD) {
    this.dongCD = dongCD;
  }


  public String getHoCD() {
    return hoCD;
  }


  public void setHoCD(String hoCD) {
    this.hoCD = hoCD;
  }


  public String getEtcrcdITEM() {
    return etcrcdITEM;
  }


  public void setEtcrcdITEM(String etcrcdITEM) {
    this.etcrcdITEM = etcrcdITEM;
  }


  public String getViolbldYN() {
    return violbldYN;
  }


  public void setViolbldYN(String violbldYN) {
    this.violbldYN = violbldYN;
  }


  public int getRegstrSEQNO() {
    return regstrSEQNO;
  }


  public void setRegstrSEQNO(int regstrSEQNO) {
    this.regstrSEQNO = regstrSEQNO;
  }


  public String getEtcPurps() {
    return etcPurps;
  }


  public void setEtcPurps(String etcPurps) {
    this.etcPurps = etcPurps;
  }


  public String getEtcStruct() {
    return etcStruct;
  }


  public void setEtcStruct(String etcStruct) {
    this.etcStruct = etcStruct;
  }


  public String getEtcRoof() {
    return etcRoof;
  }


  public void setEtcRoof(String etcRoof) {
    this.etcRoof = etcRoof;
  }


  public int getHhldCNT() {
    return hhldCNT;
  }


  public void setHhldCNT(int hhldCNT) {
    this.hhldCNT = hhldCNT;
  }


  public int getFmlyCNT() {
    return fmlyCNT;
  }


  public void setFmlyCNT(int fmlyCNT) {
    this.fmlyCNT = fmlyCNT;
  }


  public int getHoCNT() {
    return hoCNT;
  }


  public void setHoCNT(int hoCNT) {
    this.hoCNT = hoCNT;
  }


  public int getMainBldCnt() {
    return mainBldCnt;
  }


  public void setMainBldCnt(int mainBldCnt) {
    this.mainBldCnt = mainBldCnt;
  }


  public BigDecimal getAtchBldArea() {
    return atchBldArea;
  }


  public void setAtchBldArea(BigDecimal atchBldArea) {
    this.atchBldArea = atchBldArea;
  }


  public int getTotPkngCNT() {
    return totPkngCNT;
  }


  public void setTotPkngCNT(int totPkngCNT) {
    this.totPkngCNT = totPkngCNT;
  }


  public String getNaRoadCD() {
    return naRoadCD;
  }


  public void setNaRoadCD(String naRoadCD) {
    this.naRoadCD = naRoadCD;
  }


  public String getNaBjdongCD() {
    return naBjdongCD;
  }


  public void setNaBjdongCD(String naBjdongCD) {
    this.naBjdongCD = naBjdongCD;
  }


  public String getNaGrndUgrndCD() {
    return naGrndUgrndCD;
  }


  public void setNaGrndUgrndCD(String naGrndUgrndCD) {
    this.naGrndUgrndCD = naGrndUgrndCD;
  }


  public String getNaMainBun() {
    return naMainBun;
  }


  public void setNaMainBun(String naMainBun) {
    this.naMainBun = naMainBun;
  }


  public String getNaSubBun() {
    return naSubBun;
  }


  public void setNaSubBun(String naSubBun) {
    this.naSubBun = naSubBun;
  }


  public String getOperSrvrRflcYN() {
    return operSrvrRflcYN;
  }


  public void setOperSrvrRflcYN(String operSrvrRflcYN) {
    this.operSrvrRflcYN = operSrvrRflcYN;
  }
  
  
  
}
