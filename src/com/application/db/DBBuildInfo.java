package com.application.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.application.vo.BuildInfo;

public class DBBuildInfo { //리스트 출력 조회용
  
  String driver = "oracle.jdbc.driver.OracleDriver";
  String url = "jdbc:oracle:thin:@localhost:1521:nbem2";
  String user = "nbem2_adm";
  String password = "nbem02";
  
  Connection conn = null;
  Statement stmt = null;
  ResultSet rs = null;
  
  public DBBuildInfo() {
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
  
  
//건축물대장 리스트 찾는 메서드
 public List<BuildInfo> findBuildList(
     String sigunguCD, String bjdongCD, String bunText, String jiText) throws SQLException {
   
   FindIP findIP = new FindIP();
   String ip = findIP.findCom(sigunguCD);
   
   String url = "jdbc:oracle:thin:@"+ip+":1521:nbem2";
   String user = "nbem2_"+ sigunguCD +"_adm";
   
   conn = DriverManager.getConnection(url, user, password);

   stmt = conn.createStatement();
   
   System.out.println("[정보!] DB 연결 url : "+url);
   System.out.println("[정보!] DB 연결 user : "+user);
   
   String sql = " select DISTINCT A.BLD_TYPE_GB_CD, A.MGM_BLD_PK, A.REGSTR_GB_CD, A.REGSTR_KIND_CD, "
              + "        B.SIDO_NM, B.SIGUNGU_NM, B.BJDONG_NM, A.BUN, A.JI, A.BLD_NM, "
              + "        C.CD_NM AS MAIN_PURPS_NM, D.CD_NM AS STRCT_NM "
              + "   from BDT_BLDRGST A "
              + "   left outer join CMC_BJDONG_MGM B on A.SIGUNGU_CD = B.SIGUNGU_CD " 
              + "                                   and A.BJDONG_CD  = B.BJDONG_CD " 
              + "   left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM024') C on A.MAIN_PURPS_CD = C.SGRP_CD "  
              + "   left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM004') D on A.STRCT_CD = D.SGRP_CD "  
              + "  where A.SIGUNGU_CD = '" + sigunguCD + "'"  
              + "    and A.BJDONG_CD  = '" + bjdongCD + "'"  
              + "    and A.BUN LIKE '%'||'" + bunText + "'||'%'"
              + "    and A.JI  LIKE '%'||'" + jiText  + "'||'%'"
              + "    and ROWNUM <= 150";
                   // 최대 150개 까지만 출력 (번지 정보 없을 때)
   
   rs = stmt.executeQuery(sql);
   
   List<BuildInfo> buildingList = new ArrayList<>();

   while(rs.next()) {
     BuildInfo buildInfo = new BuildInfo();
     buildInfo.setBldTypeGBCD(rs.getString("BLD_TYPE_GB_CD"));
     buildInfo.setBuildingPK(rs.getString("MGM_BLD_PK"));
     buildInfo.setRegstrGBCD(rs.getString("REGSTR_GB_CD"));
     buildInfo.setRegstrKINKCD(rs.getString("REGSTR_KIND_CD"));
     buildInfo.setSidoNM(rs.getString("SIDO_NM"));
     buildInfo.setSigunguNM(rs.getString("SIGUNGU_NM"));
     buildInfo.setBjdongNM(rs.getString("BJDONG_NM"));
     buildInfo.setBunNum(rs.getString("BUN"));
     buildInfo.setJiNum(rs.getString("JI"));
     buildInfo.setBldNM(rs.getString("BLD_NM"));
     buildInfo.setMainPurpsNM(rs.getString("MAIN_PURPS_NM"));
     buildInfo.setStrctNM(rs.getString("STRCT_NM"));
     buildingList.add(buildInfo);
   }
   
   return buildingList;
 }
  
  
}
