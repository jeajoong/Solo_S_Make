package com.application.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.application.dto.Address;
import com.application.dto.BuildInfo;

public class DBBuildInfo { //리스트 조회용
  
  String driver = "oracle.jdbc.driver.OracleDriver";
  String url = "jdbc:oracle:thin:@localhost:1521:nbem2";
  String user = "nbem2_adm";
  String password = "nbem02";
  
  Connection conn = null;
  Statement stmt = null;
  ResultSet rs = null;
  
  int status = 0;
  
  public DBBuildInfo() {
    try {
      Class.forName(driver);
      System.out.println("jdbc driver BuildInfo 로딩 성공");
    }
    catch (ClassNotFoundException e) {
      System.out.println("[로드 오류]\n" + e.getStackTrace());
    }
  }
  
  public int getStatus() {
    return status;
  }
  
  public void closeDatabase() {
    try {
      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  
//건축물대장 리스트 찾는 메서드 (갯수가 너무 많을때 전유부를 제외하고 그래도 많다면 최대 700개까지만 조회)
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

   // 주소 데이터를 가져오기 전에 데이터의 크기 조회
   String checkSql = "  select count(*) " 
                   + "    from BDT_BLDRGST A " 
                   + "    left outer join CMC_BJDONG_MGM B on A.SIGUNGU_CD = B.SIGUNGU_CD " 
                   + "                                    and A.BJDONG_CD  = B.BJDONG_CD  " 
                   + "   where A.SIGUNGU_CD = '"  + sigunguCD + "' " 
                   + "     and A.BJDONG_CD  = '"  + bjdongCD  + "' " 
                   + "     and A.BUN LIKE '%'||'" + bunText   + "'||'%'" 
                   + "     and A.JI  LIKE '%'||'" + jiText    + "'||'%'" ;
   
   rs = stmt.executeQuery(checkSql);
   int rowCount = 0;
   
   while(rs.next()) {
   rowCount = rs.getInt("count(*)");
   }
   System.out.println("조회된 데이터 갯수 : " + rowCount);
   System.out.println("bun을 조회 : "+ bunText);
   
   if(bunText.equals("")) status = 1; 
   if(rowCount < 500) status = 2;
   
     if (rowCount < 500 || bunText.equals("")) {
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
           + "    and ROWNUM < 501 "
           + "  ORDER BY REGSTR_KIND_CD ";
        
         System.out.println("데이터의 수가 500 미만이거나 번 정보를 입력하지 않으면 최대 500개만 출력.");
       rs = stmt.executeQuery(sql);
    } else if(rowCount >= 500 ) { // 최대 500개 이상이거나 번지 정보가 있다면
      
      // 주소 데이터를 가져오기 전에 전유부를 제외한 데이터 갯수 조회.
      String checkSql2 = "  select count(*) " 
                       + "    from BDT_BLDRGST A " 
                       + "    left outer join CMC_BJDONG_MGM B on A.SIGUNGU_CD = B.SIGUNGU_CD " 
                       + "                                    and A.BJDONG_CD  = B.BJDONG_CD  " 
                       + "   where A.SIGUNGU_CD = '"  + sigunguCD + "' " 
                       + "     and A.BJDONG_CD  = '"  + bjdongCD  + "' " 
                       + "     and A.BUN LIKE '%'||'" + bunText   + "'||'%'" 
                       + "     and A.JI  LIKE '%'||'" + jiText    + "'||'%'"
                       + "     and A.REGSTR_KIND_CD NOT LIKE '4' ";
      
      rs = stmt.executeQuery(checkSql2);
      int rowCount2 = 0;
      
      while(rs.next()) {
        rowCount2 = rs.getInt("count(*)");
        }
      System.out.println("처음조회 데이터가 많아서 해당정보로 전유부를 제외한 데이터의 갯수 : " + rowCount2);
      
      // rowCount2가 전유부를 제외한 숫자인데 그래도 700개 보다 많다면 700개만 출력
      if (rowCount2 > 700) { // 전유부를 제외하고도 데이터수가 많아서 700개 까지만
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
            + "    and A.REGSTR_KIND_CD NOT LIKE '4' "
            + "    and ROWNUM < 701 "
            + "  ORDER BY REGSTR_KIND_CD ";
        // 700개도 불러오면 버벅인다....
          status = 3;
         rs = stmt.executeQuery(sql);
         
      } else { // 전유부를 제외한 수가 700 이하라면 출력
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
            + "    and A.REGSTR_KIND_CD NOT LIKE '4' "
            + "  ORDER BY REGSTR_KIND_CD ";
        
        status = 4;
        rs = stmt.executeQuery(sql);
      }
    } 
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
   
   closeDatabase();
   return buildingList;
 }

  // 도로명 주소를 입력했을 때 처리할 메서드
  public List<Address> findNewAddr(String[] result, HashMap<Integer, String> newAddrMap, String bunOrji) throws SQLException {
    // result는 도로명에 부합하지 않는것들을 포함하는 list
    // newAddrMap은 도로명에 부합하는 것을 포함하는 Map(null값 존재)
    // bunOrji는 번지정보를 포함하고 있음.
    String bun = "";
    String ji  = "";
    if(bunOrji.matches("-")) {
      int loc = bunOrji.indexOf("-");
       bun = bunOrji.substring(0, loc);
       ji  = bunOrji.substring(loc+1); 
    } else {
      bun = bunOrji;
    }
    
    
    // 나의 DB에 도로명주소 테이블이 있을 때.
    conn = DriverManager.getConnection(url, user, password);
    stmt = conn.createStatement();
    
    System.out.println("[정보!] DB 연결 url : "+url);
    System.out.println("[정보!] DB 연결 user : "+user);
    
    for (int i = 0; i < newAddrMap.size(); i++) {
      if(newAddrMap.get(i) != null) {
        String sql = " select SIGUNGU_CD, BJDONG_CD, BUN, JI " 
                   + "   from CMC_NEWADDR_MGM "
                   + "  where SIDO_NM LIKE '%'||" + result[0].substring(0, 2)+"||'%' "  
                   + "    and SIGUNGU_NM LIKE '%'||" + result[1].substring(0, 2)+"||'%' " 
                   + "    and NA_ROAD_NM LIKE '%'||" + newAddrMap.get(i)+ "||'%' " 
                   + "    and NA_MAIN_BUN = '"+ bun +"' "
                   + "    and NA_SUB_BUN LIKE '%'||"+ ji +"||'%' ";
       
        rs = stmt.executeQuery(sql);
        
        List<Address> addressList = new ArrayList<>();
        while(rs.next()) {
          Address address = new Address();
          address.setSigunguCD(rs.getString("SIGUNGU_CD"));
          address.setBjdongCD(rs.getString("BJDONG_CD"));
          address.setBun(rs.getString("BUN"));
          address.setJi(rs.getString("JI"));
          addressList.add(address);
          
          return addressList;
        }
      }
    }
    // bunOrji 가  null 이면 도로명주소 아닌것을 입력한것임.
    if(bunOrji == null)
      return null;
  
    return null;
  }
 
  
}
