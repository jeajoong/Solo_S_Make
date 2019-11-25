package com.application.vo;

import java.math.BigDecimal;

public class NOTUSEDBuild { // 순수 BDT_BLDRGST에 있는 인스턴스

  private String bldTypeGBCD;  // 건축물 유형 구분 코드
  private String buildingPK;   // 관리 건축물 PK
  private String regstrGBCD;   // 대장 구분 코드
  private String regstrKINKCD; // 대장 종류 코드
  private String sigunguCD;    // 시군구 코드
  private String bjdongCD;     // 법정동 코드
  private String hjdongCD;     // 행정동 코드
  private String platGBCD;     // 대지구분코드
  private String bunNum;       // 번 숫자
  private String jiNum;        // 지 숫자
  private int byLotCNT;     // 외필지 수
  private String bldNM;        // 건물명
  private String dongNM;       // 동명
  private String hoNM;         // 호명
  private String dongCD;       // 동코드
  private String hoCD;         // 호코드
  private String etcrcdITEM;   // 기타 기재 사항
  private String violbldYN;    // 위반건축물여부
  private int regstrSEQNO;  // 대장 일련번호
  private String spcmt;        // 특이사항
  private BigDecimal platArea;     // 대지면적
  private BigDecimal archArea;     // 건축면적
  private BigDecimal bcRat;        // 건폐율
  private BigDecimal vlRatEstmTotArea; // 용적율 산정 연면적
  private BigDecimal vlRat;        // 용적율
  private BigDecimal totArea;      // 연면적
  private String mainPurpsCD;  // 주 용도 코드
  private String etcPurps;     // 기타용도
  private String strctCD;      // 구조코드
  private String etcStruct;    // 기타구조
  private String roofCD;       // 지붕코드
  private String etcRoof;      // 기타지붕
  private int hhldCNT;      // 세대 수
  private int fmlyCNT;      // 가구 수
  private int hoCNT;        // 호 수
  private float heit;         // 높이
  private int grndFlrCNT;   // 지상 층 수
  private int ugrndFlrCNT;  // 지하 층 수
  private int mainBldCnt;   // 주 건축물 수
  private int atchBldCnt;   // 부속 건축물 수
  private BigDecimal atchBldArea;  // 총 동 연면적
  private int totPkngCNT;   // 총 주차 수
  private String naRoadCD;     // 새주소 대로 로 코드
  private String naBjdongCD;   // 새주소 법정동 코드
  private String naGrndUgrndCD;// 새주소 지상지하 코드
  private String naMainBun;    // 새주소 본번
  private String naSubBun;     // 새주소 부번
  private String operSrvrRflcYN;// 운영서버 반영여부
  
  
  
  /* ---- 제외한 컬럼들  ---- */
  // 블록, 로트 , 별도 건축물 구분코드 , 신 구 대장 구분코드, 주 부속 구분 코드, 주 부속 일련번호, 양성화 여부,
  // 승용 승강기수, 비상용 승강기수, 허가일자, 착공일자, 사용승인 일자, 에너지효율등급, 에너지 절감율, 에너지 효율 EPI,
  // 친환경 건축물 등급, 친환경 건축물 인증점수, 지능형 선축물 등급, 지능형 건축물 인증점수, 관리 상위 건축물 PK,
  // 정비건물명, 정비 동명칭, 정비호명칭
  /* ------------------ */
  
  // 주소 테이블 조인 (시도, 시군구, 법정동,... 명칭)
  private Address address;


  /* ------ 추가해야 하는 것들 ------- */
  // 용도 코드 테이블 조인 (용도코드 업데이트 용도)
  
  // 동 현황 테이블 (동 현황 조회 및 업데이트 용도)
  
  // 건축물 전유 공유 정보 테이블 (전유 부분 정보 조회 및 업데이트 용도)
  
  // 통합건축물 층별 정보 테이블 (건축물 층별 정보 조히 및 업데이트 용도)
  /* -------------------------- */
  
  @Override
  public String toString() {
    return "Building [bldTypeGBCD=" + bldTypeGBCD + ", buildingPK=" + buildingPK + ", regstrGBCD="
        + regstrGBCD + ", regstrKINKCD=" + regstrKINKCD + ", sigunguCD=" + sigunguCD + ", bjdongCD="
        + bjdongCD + ", hjdongCD=" + hjdongCD + ", platGBCD=" + platGBCD + ", bunNum=" + bunNum
        + ", jiNum=" + jiNum + ", byLotCNT=" + byLotCNT + ", bldNM=" + bldNM + ", dongNM=" + dongNM
        + ", hoNM=" + hoNM + ", dongCD=" + dongCD + ", hoCD=" + hoCD + ", etcrcdITEM=" + etcrcdITEM
        + ", violbldYN=" + violbldYN + ", regstrSEQNO=" + regstrSEQNO + ", spcmt=" + spcmt
        + ", platArea=" + platArea + ", archArea=" + archArea + ", bcRat=" + bcRat
        + ", vlRatEstmTotArea=" + vlRatEstmTotArea + ", vlRat=" + vlRat + ", totArea=" + totArea
        + ", mainPurpsCD=" + mainPurpsCD + ", etcPurps=" + etcPurps + ", strctCD=" + strctCD
        + ", etcStruct=" + etcStruct + ", roofCD=" + roofCD + ", etcRoof=" + etcRoof + ", hhldCNT="
        + hhldCNT + ", fmlyCNT=" + fmlyCNT + ", hoCNT=" + hoCNT + ", heit=" + heit + ", grndFlrCNT="
        + grndFlrCNT + ", ugrndFlrCNT=" + ugrndFlrCNT + ", mainBldCNT=" + mainBldCnt
        + ", atchBldCnt=" + atchBldCnt + ", atchBldArea=" + atchBldArea + ", totPkngCNT="
        + totPkngCNT + ", naRoadCD=" + naRoadCD + ", naBjdongCD=" + naBjdongCD + ", naGrndUgrndCD="
        + naGrndUgrndCD + ", naMainBun=" + naMainBun + ", naSubBun=" + naSubBun
        + ", operSrvrRflcYN=" + operSrvrRflcYN + ", address=" + address + "]";
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


  public String getBldNM() {
    return bldNM;
  }


  public void setBldNM(String bldNM) {
    this.bldNM = bldNM;
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


  public String getSpcmt() {
    return spcmt;
  }


  public void setSpcmt(String spcmt) {
    this.spcmt = spcmt;
  }


  public BigDecimal getPlatArea() {
    return platArea;
  }


  public void setPlatArea(BigDecimal platArea) {
    this.platArea = platArea;
  }


  public BigDecimal getArchArea() {
    return archArea;
  }


  public void setArchArea(BigDecimal archArea) {
    this.archArea = archArea;
  }


  public BigDecimal getBcRat() {
    return bcRat;
  }


  public void setBcRat(BigDecimal bcRat) {
    this.bcRat = bcRat;
  }


  public BigDecimal getVlRatEstmTotArea() {
    return vlRatEstmTotArea;
  }


  public void setVlRatEstmTotArea(BigDecimal vlRatEstmTotArea) {
    this.vlRatEstmTotArea = vlRatEstmTotArea;
  }


  public BigDecimal getVlRat() {
    return vlRat;
  }


  public void setVlRat(BigDecimal vlRat) {
    this.vlRat = vlRat;
  }


  public BigDecimal getTotArea() {
    return totArea;
  }


  public void setTotArea(BigDecimal totArea) {
    this.totArea = totArea;
  }


  public String getMainPurpsCD() {
    return mainPurpsCD;
  }


  public void setMainPurpsCD(String mainPurpsCD) {
    this.mainPurpsCD = mainPurpsCD;
  }


  public String getEtcPurps() {
    return etcPurps;
  }


  public void setEtcPurps(String etcPurps) {
    this.etcPurps = etcPurps;
  }


  public String getStrctCD() {
    return strctCD;
  }


  public void setStrctCD(String strctCD) {
    this.strctCD = strctCD;
  }


  public String getEtcStruct() {
    return etcStruct;
  }


  public void setEtcStruct(String etcStruct) {
    this.etcStruct = etcStruct;
  }


  public String getRoofCD() {
    return roofCD;
  }


  public void setRoofCD(String roofCD) {
    this.roofCD = roofCD;
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


  public float getHeit() {
    return heit;
  }


  public void setHeit(float heit) {
    this.heit = heit;
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


  public int getMainBldCNT() {
    return mainBldCnt;
  }


  public void setMainBldCNT(int mainBldCNT) {
    this.mainBldCnt = mainBldCNT;
  }


  public int getAtchBldCnt() {
    return atchBldCnt;
  }


  public void setAtchBldCnt(int atchBldCnt) {
    this.atchBldCnt = atchBldCnt;
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


  public Address getAddress() {
    return address;
  }


  public void setAddress(Address address) {
    this.address = address;
  }


  
  
  
  
}
