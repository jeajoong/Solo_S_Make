package com.application.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.application.vo.Build;
import com.application.vo.BuildInfo;
import com.application.vo.Floor;
import com.application.vo.Owner;

public class DBBuildDetail { // MainPage에서 한 행을 클릭했을 때 해당 항목 상세조회 DB클래스
  
  String driver = "oracle.jdbc.driver.OracleDriver";
  String url = "jdbc:oracle:thin:@localhost:1521:nbem2";
  String user = "nbem2_adm";
  String password = "nbem02";
  
  FindIP findIP = new FindIP();
  String ip = null;
  
  String sidoNM = null;
  String sigunguNM = null;
  String bjdongNM = null;
  
  String sigunguCD = null;
  String bjdongCD = null;
  String bunNum = null;
  String jiNum = null;
  
  Connection conn = null;
  Statement stmt = null;
  ResultSet rs = null;
  
  public DBBuildDetail() {
    try {
      Class.forName(driver);
      System.out.println("jdbc driver BuildInfo 로딩 성공");
    }
    catch (ClassNotFoundException e) {
      System.out.println("[로드 오류]\n" + e.getStackTrace());
    }
  }
  
  
  public void closeDatabase() {
      try {
        if(conn != null && !conn.isClosed()) {
          conn.close();
          System.out.println("db 자원 종료");
        }
        if(stmt != null && !stmt.isClosed()) {
          stmt.close();
          System.out.println("db 자원 종료");
        }
        if(rs != null && !rs.isClosed()) {
          rs.close();
          System.out.println("db 자원 종료");
        }
      }
      catch (SQLException e) {
          System.out.println("[닫기 오류]\n" + e.getStackTrace());
      }
  }
  

// 해당 정보로 건축물 대장 상세정보를 찾음.(일반건축물대장 및 총괄대장) 전역 변수 sidoNM, sigunguNM, bjdongNM, sigunguCD, bjdongCD 에 값을 넣음.
public Build findBuild(String bldTypeGBCD, String buildingPK, String getRegstrGBCD, String getRegstrKINKCD,
                          String sidoNM, String sigunguNM,
                          String bjdongNM, String bunNum, String jiNum) throws SQLException {
  
  DBAddress dbAddress = new DBAddress();
  
  this.sidoNM = sidoNM;
  this.sigunguNM = sigunguNM;
  this.bjdongNM = bjdongNM;
  
  String sigunguCD = dbAddress.findSigunguCD(sidoNM, sigunguNM);
  String bjdongCD = dbAddress.findBjdongCD(sidoNM, sigunguNM, bjdongNM);

  this.sigunguCD = sigunguCD;
  this.bjdongCD = bjdongCD;
  this.bunNum = bunNum;
  this.jiNum = jiNum;
  
  ip = findIP.findCom(sigunguCD);
  
  url = "jdbc:oracle:thin:@"+ip+":1521:nbem2";
  user = "nbem2_"+ sigunguCD +"_adm";
  
  conn = DriverManager.getConnection(url, user, password);

  stmt = conn.createStatement();
  
  System.out.println(bldTypeGBCD +"///" + buildingPK +"///" + getRegstrGBCD +"///" +getRegstrKINKCD + "///" 
                     + sigunguCD +"///" + bjdongCD + "///" + bunNum + "///" + jiNum);
  
  String sql1 = "select DISTINCT A.BLD_TYPE_GB_CD, A.MGM_BLD_PK, A.REGSTR_GB_CD, A.REGSTR_KIND_CD, " 
              + "       A.SIGUNGU_CD||A.BJDONG_CD||'-'||A.PLAT_GB_CD||'-'||A.BUN||A.JI AS REGSTR_NO, " 
              + "       A.BLD_NM, " 
              + "       A.SPCMT, "
              + "       B.SIDO_NM||' '||B.SIGUNGU_NM||' '||B.BJDONG_NM AS PLAT_LOC, "
              + "       TO_CHAR(TO_NUMBER(A.BUN))||'-'|| TO_CHAR(TO_NUMBER(A.JI)) AS JIBUN, "
              
              + "       N.SIDO_NM||' '||N.SIGUNGU_NM||' '||N.NA_ROAD_NM|| ' ' ||N.NA_MAIN_BUN ||"
              + "       DECODE(N.NA_SUB_BUN, '0', '', '', '', '-')||DECODE(N.NA_SUB_BUN, '0','') AS PLAT_NEW_LOC, "
              
              + "       A.PLAT_AREA, A.TOTAREA, " 
              + "       A.ARCH_AREA, A.VL_RAT_ESTM_TOTAREA, D.CD_NM AS STRCT_NM, A.STRCT_CD, "
              + "       C.CD_NM AS MAIN_PURPS_NM, A.MAIN_PURPS_CD, A.GRND_FLR_CNT, A.UGRND_FLR_CNT, " 
              + "       A.BC_RAT, A.VL_RAT, HEIT, E.CD_NM AS ROOF_NM, A.ROOF_CD, A.ATCH_BLD_CNT, "
              + "       A.HO_CNT, A.TOT_PKNG_CNT, A.MAIN_BLD_CNT , A.DONG_NM"
              + "  from BDT_BLDRGST A " 
              + "        left outer join CMC_BJDONG_MGM B  on A.SIGUNGU_CD = B.SIGUNGU_CD "
              + "                                         and A.BJDONG_CD  = B.BJDONG_CD " 
              + "        left outer join CMC_NEWADDR_MGM N on B.SIDO_NM    = N.SIDO_NM " 
              + "                                         and B.SIGUNGU_CD = N.SIGUNGU_CD " 
              + "                                         and B.BJDONG_CD  = N.BJDONG_CD " 
              + "                                         and TO_CHAR(TO_NUMBER(A.BUN)) = N.BUN " 
              + "                                         and TO_CHAR(TO_NUMBER(A.JI))  = N.JI " 
              + "                 left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM024') C on A.MAIN_PURPS_CD = C.SGRP_CD " 
              + "                 left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM004') D on A.STRCT_CD = D.SGRP_CD " 
              + "                 left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM036') E on A.ROOF_CD  = E.SGRP_CD " 
              + "     where A.BLD_TYPE_GB_CD = '" + bldTypeGBCD + "' "
              + "       and A.MGM_BLD_PK = '" + buildingPK + "' "
              + "       and A.REGSTR_GB_CD = '" + getRegstrGBCD + "' "
              + "       and A.REGSTR_KIND_CD = '" + getRegstrKINKCD + "' "
              + "       and A.SIGUNGU_CD = '"+ sigunguCD +"' " 
              + "       and A.BJDONG_CD = '"+ bjdongCD +"' "
              + "       and A.BUN = LPAD('"+bunNum+"', 4, '0') "  
              + "       and A.JI  = LPAD('"+jiNum+"' , 4, '0') " ;
  
  rs = stmt.executeQuery(sql1);
  
  Build build = new Build();
  
  while(rs.next()) {
    build.setBldTypeGBCD(rs.getString("BLD_TYPE_GB_CD"));
    build.setBuildingPK(rs.getString("MGM_BLD_PK"));
    build.setRegstrGBCD(rs.getString("REGSTR_GB_CD"));
    build.setRegstrKINKCD(rs.getString("REGSTR_KIND_CD"));
    build.setRegstrNo(rs.getString("REGSTR_NO"));
    build.setBldNM(rs.getString("BLD_NM"));
    build.setSpcmt(rs.getString("SPCMT"));
    build.setPlatLoC(rs.getString("PLAT_LOC"));
    build.setJiBun(rs.getString("JIBUN"));
    build.setPlatNewLoc(rs.getString("PLAT_NEW_LOC"));
    build.setPlatArea(rs.getBigDecimal("PLAT_AREA"));
    build.setTotArea(rs.getBigDecimal("TOTAREA"));
    build.setArchArea(rs.getBigDecimal("ARCH_AREA"));
    build.setVlRatEstmTotArea(rs.getBigDecimal("VL_RAT_ESTM_TOTAREA"));
    build.setStrctNM(rs.getString("STRCT_NM"));
    build.setStrctCD(rs.getString("STRCT_CD"));
    build.setMainPurpsNM(rs.getString("MAIN_PURPS_NM"));
    build.setMainPurpsCD(rs.getString("MAIN_PURPS_CD"));
    build.setGrndFlrCNT(rs.getInt("GRND_FLR_CNT"));
    build.setUgrndFlrCNT(rs.getInt("UGRND_FLR_CNT"));
    build.setBcRat(rs.getBigDecimal("BC_RAT"));
    build.setVlRat(rs.getBigDecimal("VL_RAT"));
    build.setHeit(rs.getFloat("HEIT"));
    build.setRoofNM(rs.getString("ROOF_NM"));
    build.setRoofCD(rs.getString("ROOF_CD"));
    build.setAtchBldCnt(rs.getInt("ATCH_BLD_CNT"));
    
    build.setHoCNT(rs.getInt("HO_CNT"));
    build.setTotPkngCNT(rs.getInt("TOT_PKNG_CNT"));
    build.setMainBldCnt(rs.getInt("MAIN_BLD_CNT"));
    build.setDongNM(rs.getString("DONG_NM"));
  }
  
  return build;
}


// 집합건축물대장에 해당하는 동별 정보(표제부?)를 가져옴.
public List<Build> findDongBuild() throws SQLException {
  conn = DriverManager.getConnection(url, user, password);
  stmt = conn.createStatement();
  
  String sql = "  select DISTINCT A.BLD_TYPE_GB_CD, A.MGM_BLD_PK, A.REGSTR_GB_CD, A.REGSTR_KIND_CD, " 
      + "                          A.MAIN_ATCH_GB_CD, " 
      + "                          A.REGSTR_SEQNO, "
      + "                          A.DONG_NM, "
      + "                          N.SIDO_NM||' '||N.SIGUNGU_NM||' '||N.NA_ROAD_NM|| ' ' ||N.NA_MAIN_BUN ||"
      + "                           DECODE(N.NA_SUB_BUN, '0', '', '', '', '-')||DECODE(N.NA_SUB_BUN, '0','') AS PLAT_NEW_LOC, "
      + "                          D.CD_NM AS STRCT_NM, "
      + "                          E.CD_NM AS ROOF_NM, "
      + "                          TO_CHAR(TO_NUMBER(A.GRND_FLR_CNT) + TO_NUMBER(A.UGRND_FLR_CNT)) AS FLR_CNT, "
      + "                          C.CD_NM AS MAIN_PURPS_NM, " 
      + "                          A.TOTAREA "
      + "            from BDT_BLDRGST A "
      + "                 left outer join CMC_BJDONG_MGM B  on A.SIGUNGU_CD = B.SIGUNGU_CD "
      + "                                                  and A.BJDONG_CD  = B.BJDONG_CD " 
      + "                 left outer join CMC_NEWADDR_MGM N on B.SIDO_NM    = N.SIDO_NM " 
      + "                                                  and B.SIGUNGU_CD = N.SIGUNGU_CD " 
      + "                                                  and B.BJDONG_CD  = N.BJDONG_CD " 
      + "                                                  and TO_CHAR(TO_NUMBER(A.BUN)) = N.BUN " 
      + "                                                  and TO_CHAR(TO_NUMBER(A.JI))  = N.JI " 
      + "                 left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM024') C on A.MAIN_PURPS_CD = C.SGRP_CD " 
      + "                 left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM004') D on A.STRCT_CD = D.SGRP_CD " 
      + "                 left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM036') E on A.ROOF_CD  = E.SGRP_CD " 
      + "           where A.REGSTR_GB_CD = '2' "
      + "             and A.REGSTR_KIND_CD = '3' "
      + "             and A.SIGUNGU_CD = '"+sigunguCD+"' " 
      + "             and A.BJDONG_CD = '"+bjdongCD+"' " 
      + "             and A.BUN = LPAD('"+bunNum+"', 4, '0') "  
      + "             and A.JI = LPAD('"+jiNum+"' , 4, '0') " 
      + "        ORDER BY A.REGSTR_SEQNO ";
  
  rs = stmt.executeQuery(sql);
  
  List<Build> buildingDongList = new ArrayList<>();
  
  while(rs.next()) {
    Build buildingDongInfo = new Build();
    buildingDongInfo.setBldTypeGBCD(rs.getString("BLD_TYPE_GB_CD"));
    buildingDongInfo.setBuildingPK(rs.getString("MGM_BLD_PK"));
    buildingDongInfo.setRegstrGBCD(rs.getString("REGSTR_GB_CD"));
    buildingDongInfo.setRegstrKINKCD(rs.getString("REGSTR_KIND_CD"));
    buildingDongInfo.setMainAtchGBCD(rs.getString("MAIN_ATCH_GB_CD"));
    buildingDongInfo.setDongNM(rs.getString("DONG_NM"));
    buildingDongInfo.setPlatNewLoc(rs.getString("PLAT_NEW_LOC"));
    buildingDongInfo.setStrctNM(rs.getString("STRCT_NM"));
    buildingDongInfo.setRoofNM(rs.getString("ROOF_NM"));
    buildingDongInfo.setUpDownCNT(rs.getInt("FLR_CNT"));
    buildingDongInfo.setMainPurpsNM(rs.getString("MAIN_PURPS_NM"));
    buildingDongInfo.setTotArea(rs.getBigDecimal("TOTAREA"));
    buildingDongList.add(buildingDongInfo);
  }
  return buildingDongList;

}


// 주구조 코드 리스트를 찾음
public Map<String,String> findjugujoCodeList() throws SQLException {
    conn = DriverManager.getConnection(url, user, password);

    stmt = conn.createStatement();
    
    String sql = "select SGRP_CD AS STRCT_CD, CD_NM from CMC_COMM_CD_MGM where LGRP_CD = 'CM004' ORDER BY SGRP_CD";
    
    
    rs = stmt.executeQuery(sql);
    
    Map<String, String> jugujoMap = new HashMap<String, String>();
    
    
    while(rs.next()) {
      jugujoMap.put(rs.getString("STRCT_CD"), rs.getString("CD_NM"));
    }
    
    return jugujoMap;
  }

// 코드를 새로 눌렀을때 해당하는 명칭값을 돌려줌.
public String findjugujoNM(String jgjCD) throws SQLException {
  conn = DriverManager.getConnection(url, user, password);
  stmt = conn.createStatement();
  
  String sql = "select CD_NM from CMC_COMM_CD_MGM where LGRP_CD = 'CM004' and SGRP_CD = '"+ jgjCD +"'";
  rs = stmt.executeQuery(sql);
  
  String jgjNM = null;
  while(rs.next()) {
    jgjNM = rs.getString("CD_NM");
  }
  
  return jgjNM;
}

// 명칭을 새로 눌렀을때 해당하는 코드값을 돌려줌.
public String findjugujoCD(String jgjNM) throws SQLException {
  conn = DriverManager.getConnection(url, user, password);
  stmt = conn.createStatement();
  
  String sql = "select SGRP_CD from CMC_COMM_CD_MGM where LGRP_CD = 'CM004' and CD_NM = '"+ jgjNM +"'";
  rs = stmt.executeQuery(sql);
  
  String jgjCD = null;
  while(rs.next()) {
    jgjCD = rs.getString("SGRP_CD");
  }
  
  return jgjCD;
}

 // 코드를 새로 눌렀을때 해당하는 명칭값을 돌려줌.
public String findjuyongdoNM(String jydCD) throws SQLException {
  conn = DriverManager.getConnection(url, user, password);
  stmt = conn.createStatement();
  
  String sql = "select CD_NM from CMC_COMM_CD_MGM where LGRP_CD = 'CM024' and SGRP_CD = '"+ jydCD +"'";
  rs = stmt.executeQuery(sql);
  
  String jydNM = null;
  while(rs.next()) {
    jydNM = rs.getString("CD_NM");
  }
  
  return jydNM;
}

// 명칭을 새로 눌렀을때 해당하는 코드값을 돌려줌.
public String findjuyongdoCD(String jydNM) throws SQLException {
  conn = DriverManager.getConnection(url, user, password);
  stmt = conn.createStatement();
  
  String sql = "select SGRP_CD from CMC_COMM_CD_MGM where LGRP_CD = 'CM024' and CD_NM = '"+ jydNM +"'";
  rs = stmt.executeQuery(sql);
  
  String jyjCD = null;
  while(rs.next()) {
    jyjCD = rs.getString("SGRP_CD");
  }
  return jyjCD;
}



// 코드를 새로 눌렀을때 해당하는 명칭값을 돌려줌.
public String findRoofNM(String roofCD) throws SQLException {
  conn = DriverManager.getConnection(url, user, password);
  stmt = conn.createStatement();
  
  String sql = "select CD_NM from CMC_COMM_CD_MGM where LGRP_CD = 'CM036' and SGRP_CD = '"+ roofCD +"'";
  rs = stmt.executeQuery(sql);
  
  String rfNM = null;
  while(rs.next()) {
    rfNM = rs.getString("CD_NM");
  }
  return rfNM;
}

// 명칭을 새로 눌렀을때 해당하는 코드값을 돌려줌.
public String findRoofCD(String roofNM) throws SQLException {
  conn = DriverManager.getConnection(url, user, password);
  stmt = conn.createStatement();
  
  String sql = "select SGRP_CD from CMC_COMM_CD_MGM where LGRP_CD = 'CM036' and CD_NM = '"+ roofNM +"'";
  rs = stmt.executeQuery(sql);
  
  String rfCD = null;
  while(rs.next()) {
    rfCD = rs.getString("SGRP_CD");
  }
  return rfCD;
}





// 층별정보 조회.
public List<Floor> findFlrInfo(Build build) throws SQLException {
  
  conn = DriverManager.getConnection(url, user, password);
  stmt = conn.createStatement();
  
  String sql = "select BLD_TYPE_GB_CD, MGM_BLD_PK, MGM_FLR_OULN_PK, FLR_GB_CD, FLR_NO, FLR_NO_NM, " 
     + "               FLR_CD, MAIN_PURPS_CD, ETC_PURPS, STRCT_CD, ETC_STRCT, AREA, MAIN_ATCH_GB_CD, "
     + "               MAIN_ATCH_SEQNO, MGM_MAIN_BLD_PK, AREA_EXCT_YN, FLR_SEQNO "
     + "          from BDT_BLD_FLR_OULN "
     + "         where BLD_TYPE_GB_CD = '"+ build.getBldTypeGBCD().toString() + "' " 
     + "           and MGM_BLD_PK = '"+ build.getBuildingPK().toString() + "' " 
     + "           order by FLR_NO_NM ";

  rs = stmt.executeQuery(sql);

  
  List<Floor> floorList = new ArrayList<>();
  while(rs.next()) {
    Floor floor = new Floor();
    floor.setBldTypeGBCD(rs.getString("BLD_TYPE_GB_CD"));
    floor.setBuildingPK(rs.getString("MGM_BLD_PK"));
    floor.setFlrPK(rs.getString("MGM_FLR_OULN_PK"));
    floor.setFlrGBCD(rs.getString("FLR_GB_CD"));
    floor.setFlrNo(rs.getInt("FLR_NO"));
    floor.setFlrNoNM(rs.getString("FLR_NO_NM"));
    floor.setFlrCD(rs.getString("FLR_CD"));
    floor.setMainPurpsCD(rs.getString("MAIN_PURPS_CD"));
    floor.setMainPurpsNM(rs.getString("ETC_PURPS"));
    floor.setStrctCD(rs.getString("STRCT_CD"));
    floor.setStrctNM(rs.getString("ETC_STRCT"));
    floor.setArea(rs.getBigDecimal("AREA"));
    floor.setMainAtchGBCD(rs.getString("MAIN_ATCH_GB_CD"));
    floor.setMainAtchSeqNo(rs.getInt("MAIN_ATCH_SEQNO"));
    floor.setMainbuildingPK(rs.getString("MGM_MAIN_BLD_PK"));
    floor.setAreaExctYn(rs.getString("AREA_EXCT_YN"));
    floor.setFlrSeqNo(rs.getInt("FLR_SEQNO"));
    floorList.add(floor);
  }
  return floorList;
}

// 소유자정보 조회
public List<Owner> findOwnrInfo(Build build) throws SQLException {
  conn = DriverManager.getConnection(url, user, password);
  stmt = conn.createStatement();
  
  String sql = "select BLD_TYPE_GB_CD, MGM_BLD_PK, MGM_OWNR_INFO_PK, NM, OWN_GB_CD, QUOTA1, QUOTA2, OWNSH_QUOTA "
             + "  from BDT_BLD_OWNR "
             + " where BLD_TYPE_GB_CD = '"+ build.getBldTypeGBCD().toString() + " ' "
             + "   and MGM_BLD_PK = '"+ build.getBuildingPK() +"' "
             + " order by MGM_OWNR_INFO_PK ";
  
  rs = stmt.executeQuery(sql);
  
  List<Owner> ownerList = new ArrayList<>();
  while(rs.next()) {
    Owner owner = new Owner();
    owner.setBldTypeGBCD(rs.getString("BLD_TYPE_GB_CD"));
    owner.setBuildingPK(rs.getString("MGM_BLD_PK"));
    owner.setOwnrPK(rs.getString("MGM_OWNR_INFO_PK"));
    owner.setName(rs.getString("NM"));
    owner.setOwnrGBCD(rs.getString("OWN_GB_CD"));
    owner.setQuota1(rs.getString("QUOTA1"));
    owner.setQuota2(rs.getString("QUOTA2"));
    owner.setOwnshQuota(rs.getString("OWNSH_QUOTA"));
    ownerList.add(owner);
  }
  
  return ownerList;
}

}
