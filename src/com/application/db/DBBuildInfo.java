package com.application.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.application.dto.Address;
import com.application.dto.BuildInfo;
import com.application.requireClass.SidoSigunguNMArray;

public class DBBuildInfo { //리스트 조회용
  
  String driver = "oracle.jdbc.driver.OracleDriver";
  String url = "jdbc:oracle:thin:@localhost:1521:orcl";
  String user = "nbem2_adm";
  String password = "nbem02";
  
  Connection conn = null;
  Statement stmt = null;
  ResultSet rs = null;
  
  private static int totalRows;
  String sigunguCD;
  String bjdongCD;
  String bunText;
  String jiText;
  
  
  public DBBuildInfo() {
    try {
      Class.forName(driver);
      System.out.println("jdbc driver BuildInfo 로딩 성공");
    }
    catch (ClassNotFoundException e) {
      System.out.println("[로드 오류]\n" + e.getStackTrace());
    }
  }
  
  @SuppressWarnings("static-access")
  private void setTotalRows(int rows) {  // 페이징 처리에 꼭 필요한 메서드
    this.totalRows = rows;
 }

 public int getTotalRows() {             // 페이징 처리에 꼭 필요한 메서드
    return totalRows;
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
  
  
  // 도로명 주소를 입력했을 때 처리할 메서드
  public List<Address> findNewAddr(String[] result, String newAddrNM, String bunOrji) throws SQLException {
    // result는 도로명에 부합하지 않는것들을 포함하는 list
    // newAddrNMList은 도로명에 부합하는 것을 포함하는 list
    // bunOrji는 번지정보를 포함하고 있음.
    String matching = "^[0-9-]*$";
    String bun = "";
    String ji  = "";
    if(bunOrji != null) {
      int loc = 0;
      if(bunOrji.indexOf("-") > 0) {
        loc = bunOrji.indexOf("-");
        bun = bunOrji.substring(0, loc);
        ji  = bunOrji.substring(loc+1); 
      } else {
        bun = bunOrji;
      }
    }
    
    String sidoNM = "";
    String sigunguNM = "";
    
    if(result != null) {   // result가 null 이면 도로명 한개만 입력한 경우.  
      String checkSido = null;
      String checkSigungu = null;
      SidoSigunguNMArray checkArray = new SidoSigunguNMArray(); 
  
        for (int i = 0; i < checkArray.sidoNM.length; i++) {
          checkSido = checkArray.sidoNM[i];
  
          for (int j = 0; j < result.length; j++) {
            if(result[j].length() >= 2) {
              if(result[j].substring(0,2).matches(checkSido) && !(result[j].matches(matching))) {
                sidoNM = result[j].substring(0,2);
                break;
              }
            }
          }
          if(sidoNM != "") break;
        }
      
      if(!sidoNM.equals("세종")) {
        for (int i = 0; i < checkArray.sigunguNM.length; i++) { // 세종시 제외한 249개 시군구 데이터
          checkSigungu = checkArray.sigunguNM[i].substring(0, 2); // 시군구명은 길어서 최소 2글자만 sql where 조건에 추가
  
          for (int j = 0; j < result.length; j++) {
            if(result[j].length() >= 2) {
              if(result[j].substring(0,2).matches(checkSigungu) && !(result[j].matches(matching))) {
                sigunguNM = result[j].substring(0,2);
                break;
              }
            }
          }
          if(sigunguNM != "") break;
        }
      }
    }
      
    System.out.println("문자열을 나눈 결과 : ");
    System.out.println("시도명 : "+sidoNM + " / 시군구명 : " + sigunguNM);
    System.out.println("/ 도로명 : " + newAddrNM + "/ 번 : " + bun + " / 지 : " + ji);
    
    List<Address> addressList = new ArrayList<>();
    try {
      // DB에 도로명주소 테이블이 있을 때.
      conn = DriverManager.getConnection(url, user, password);
      stmt = conn.createStatement();
      
      String sql = null;
          if(sidoNM.equals("세종")) {
            sql = " select SIGUNGU_CD, BJDONG_CD, BUN, JI " 
                + "   from CMC_NEWADDR_MGM "
                + "  where SIDO_NM    like '%"+sidoNM+ "%' "        // sidoNM이 없어도 조회. 있어도 조회
                + "    and NA_ROAD_NM like '%"+newAddrNM +"%' " 
                + "    and NA_MAIN_BUN =    '"+bun+"' "             // 번 정보는 꼭 있어야 함.
                + "    and NA_SUB_BUN like '%"+ji+"%' ";
          } else {
            sql = " select SIGUNGU_CD, BJDONG_CD, BUN, JI " 
                + "   from CMC_NEWADDR_MGM "
                + "  where SIDO_NM    like '%"+sidoNM+ "%' "        // sidoNM이 없어도 조회. 있어도 조회
                + "    and SIGUNGU_NM like '%"+sigunguNM +"%' "     // sigunguNM이 없어도 조회. 있어도 조회
                + "    and NA_ROAD_NM like '%"+newAddrNM +"%' " 
                + "    and NA_MAIN_BUN =    '"+bun+"' "             // 번 정보는 꼭 있어야 함.
                + "    and NA_SUB_BUN like '%"+ji+"%' ";
          }
          rs = stmt.executeQuery(sql);
          
          while(rs.next()) {
            Address address = new Address();
            address.setSigunguCD(rs.getString("SIGUNGU_CD"));
            address.setBjdongCD(rs.getString("BJDONG_CD"));
            address.setBun(rs.getString("BUN"));
            address.setJi(rs.getString("JI"));
            addressList.add(address);
          }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        closeDatabase();
      }
        return addressList;
  }

  
  
  // 새주소로 건축물대장을 불러올 메서드
  public List<BuildInfo> findBuildListNewAddr(String sigunguCD, String bjdongCD, List<Address> addressList, String includeCheck) throws SQLException {
    List<BuildInfo> buildingList = new ArrayList<>();
    try {
      conn = DriverManager.getConnection(url, user, password);
      stmt = conn.createStatement();
      
      this.sigunguCD = sigunguCD;
      this.bjdongCD = bjdongCD;
      
      String sql = null;
      for (int i = 0; i < addressList.size(); i++) {
           if(includeCheck.equals("unchecked")) {
              sql = " SELECT *                                       "
                  + "   FROM                                         "
                  + "        (SELECT T1.*,                           "
                  + "               ROWNUM AS RN,                    "
                  + "               FLOOR((ROWNUM-1)/100+1) AS PAGE, "
                  + "               COUNT(*) OVER() AS TTCNT         "
                  + "           FROM                                 "
                  + "                (                               "
                  + "                 select DISTINCT A.BLD_TYPE_GB_CD, A.MGM_BLD_PK, A.REGSTR_GB_CD, A.REGSTR_KIND_CD, " 
                  + "                                 B.SIDO_NM, B.SIGUNGU_NM, B.BJDONG_NM, A.BUN, A.JI, A.BLD_NM, "
                  + "                                 C.CD_NM AS MAIN_PURPS_NM, D.CD_NM AS STRCT_NM, A.HO_NM  "
                  + "                   from BDT_BLDRGST A " 
                  + "                        left outer join CMC_BJDONG_MGM B on A.SIGUNGU_CD = B.SIGUNGU_CD " 
                  + "                                                       and A.BJDONG_CD  = B.BJDONG_CD "
                  + "                        left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM024') C on A.MAIN_PURPS_CD = C.SGRP_CD "
                  + "                        left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM004') D on A.STRCT_CD = D.SGRP_CD "
                  + "                  where A.SIGUNGU_CD = '" + addressList.get(i).getSigunguCD() + "' "
                  + "                    and A.BJDONG_CD  = '" + addressList.get(i).getBjdongCD() + "' " 
                  + "                    and A.BUN LIKE '%'||'" + addressList.get(i).getBun() + "'||'%'"
                  + "                    and A.JI  LIKE '%'||'" + addressList.get(i).getJi()  + "'||'%'"
                  + "               ORDER BY REGSTR_KIND_CD   "
                  + "                ) T1                            "
                  + "        )                                       "
                  + "  WHERE PAGE = 1";
           } else if(includeCheck.equals("checked")) {
              sql = " SELECT *                                       "
                  + "   FROM                                         "
                  + "        (SELECT T1.*,                           "
                  + "               ROWNUM AS RN,                    "
                  + "               FLOOR((ROWNUM-1)/100+1) AS PAGE, "
                  + "               COUNT(*) OVER() AS TTCNT         "
                  + "           FROM                                 "
                  + "                (                               "
                  + "                 select DISTINCT A.BLD_TYPE_GB_CD, A.MGM_BLD_PK, A.REGSTR_GB_CD, A.REGSTR_KIND_CD, " 
                  + "                                 B.SIDO_NM, B.SIGUNGU_NM, B.BJDONG_NM, A.BUN, A.JI, A.BLD_NM, "
                  + "                                 C.CD_NM AS MAIN_PURPS_NM, D.CD_NM AS STRCT_NM, A.HO_NM  "
                  + "                   from BDT_BLDRGST A " 
                  + "                        left outer join CMC_BJDONG_MGM B on A.SIGUNGU_CD = B.SIGUNGU_CD " 
                  + "                                                       and A.BJDONG_CD  = B.BJDONG_CD "
                  + "                        left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM024') C on A.MAIN_PURPS_CD = C.SGRP_CD "
                  + "                        left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM004') D on A.STRCT_CD = D.SGRP_CD "
                  + "                  where A.SIGUNGU_CD = '" + addressList.get(i).getSigunguCD() + "' "
                  + "                    and A.BJDONG_CD  = '" + addressList.get(i).getBjdongCD() + "' " 
                  + "                    and A.BUN LIKE '%'||'" + addressList.get(i).getBun() + "'||'%'"
                  + "                    and A.JI  LIKE '%'||'" + addressList.get(i).getJi()  + "'||'%'"
                  + "                    and A.REGSTR_KIND_CD NOT LIKE '4' "
                  + "               ORDER BY REGSTR_KIND_CD   "
                  + "                ) T1                            "
                  + "        )                                       "
                  + "  WHERE PAGE = 1";
            }
            
            
          PreparedStatement pstmt = conn.prepareStatement(sql); 
          rs = pstmt.executeQuery(sql);
         
        while(rs.next()) {
          BuildInfo buildInfo = new BuildInfo();
          buildInfo.setBldTypeGBCD(rs.getString("BLD_TYPE_GB_CD"));
          buildInfo.setBuildingPK(rs.getString("MGM_BLD_PK"));
          buildInfo.setRegstrGBCD(rs.getString("REGSTR_GB_CD"));
          buildInfo.setRegstrKINDCD(rs.getString("REGSTR_KIND_CD"));
          buildInfo.setSidoNM(rs.getString("SIDO_NM"));
          buildInfo.setSigunguNM(rs.getString("SIGUNGU_NM"));
          buildInfo.setBjdongNM(rs.getString("BJDONG_NM"));
          buildInfo.setBunNum(rs.getString("BUN"));
          buildInfo.setJiNum(rs.getString("JI"));
          buildInfo.setBldNM(rs.getString("BLD_NM"));
          buildInfo.setMainPurpsNM(rs.getString("MAIN_PURPS_NM"));
          buildInfo.setStrctNM(rs.getString("STRCT_NM"));
          buildInfo.setHoNM(rs.getString("HO_NM"));
          buildingList.add(buildInfo);
          
          this.setTotalRows(rs.getInt("TTCNT"));
        }
      }// for 종료
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeDatabase();
    }

    return buildingList;
  }
 
  
//건축물대장 리스트 찾는 메서드
 public List<BuildInfo> findBuildList(String includeCheck, String sigunguCD, String bjdongCD, String bunText, String jiText) throws SQLException {
   try {
     conn = DriverManager.getConnection(url, user, password);
     stmt = conn.createStatement();
     stmt.setQueryTimeout(3);
  
     this.sigunguCD = sigunguCD;
     this.bjdongCD = bjdongCD;
     this.bunText = bunText;
     this.jiText = jiText;
     
     System.out.println("[정보!] DB 연결 url : "+url);
     System.out.println("[정보!] DB 연결 user : "+user);
     
     String sql = null;
     if(includeCheck.equals("unchecked")) {
       sql = " SELECT *                                       "
           + "   FROM                                         "
           + "        (SELECT T1.*,                           "
           + "               ROWNUM AS RN,                    "
           + "               FLOOR((ROWNUM-1)/100+1) AS PAGE, "
           + "               COUNT(*) OVER() AS TTCNT         "
           + "           FROM                                 "
           + "                (                               "
           + "                 select DISTINCT A.BLD_TYPE_GB_CD, A.MGM_BLD_PK, A.REGSTR_GB_CD, A.REGSTR_KIND_CD, " 
           + "                                 B.SIDO_NM, B.SIGUNGU_NM, B.BJDONG_NM, A.BUN, A.JI, A.BLD_NM, "
           + "                                 C.CD_NM AS MAIN_PURPS_NM, D.CD_NM AS STRCT_NM, A.HO_NM  "
           + "                   from BDT_BLDRGST A " 
           + "                        left outer join CMC_BJDONG_MGM B on A.SIGUNGU_CD = B.SIGUNGU_CD " 
           + "                                                       and A.BJDONG_CD  = B.BJDONG_CD "
           + "                        left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM024') C on A.MAIN_PURPS_CD = C.SGRP_CD "
           + "                        left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM004') D on A.STRCT_CD = D.SGRP_CD "
           + "                  where A.SIGUNGU_CD = '" + sigunguCD + "' "
           + "                    and A.BJDONG_CD  = '" + bjdongCD + "' " 
           + "                    and A.BUN LIKE '%'||'" + bunText + "'||'%'"
           + "                    and A.JI  LIKE '%'||'" + jiText  + "'||'%'"
           + "               ORDER BY REGSTR_KIND_CD   "
           + "                ) T1                            "
           + "        )                                       " 
           + "  WHERE PAGE = 1";
     } else if(includeCheck.equals("checked")) {
       sql = " SELECT *                                       "
           + "   FROM                                         "
           + "        (SELECT T1.*,                           "
           + "               ROWNUM AS RN,                    "
           + "               FLOOR((ROWNUM-1)/100+1) AS PAGE, "
           + "               COUNT(*) OVER() AS TTCNT         "
           + "           FROM                                 "
           + "                (                               "
           + "                 select DISTINCT A.BLD_TYPE_GB_CD, A.MGM_BLD_PK, A.REGSTR_GB_CD, A.REGSTR_KIND_CD, " 
           + "                                 B.SIDO_NM, B.SIGUNGU_NM, B.BJDONG_NM, A.BUN, A.JI, A.BLD_NM, "
           + "                                 C.CD_NM AS MAIN_PURPS_NM, D.CD_NM AS STRCT_NM, A.HO_NM  "
           + "                   from BDT_BLDRGST A " 
           + "                        left outer join CMC_BJDONG_MGM B on A.SIGUNGU_CD = B.SIGUNGU_CD " 
           + "                                                       and A.BJDONG_CD  = B.BJDONG_CD "
           + "                        left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM024') C on A.MAIN_PURPS_CD = C.SGRP_CD "
           + "                        left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM004') D on A.STRCT_CD = D.SGRP_CD "
           + "                  where A.SIGUNGU_CD = '" + sigunguCD + "' "
           + "                    and A.BJDONG_CD  = '" + bjdongCD + "' " 
           + "                    and A.BUN LIKE '%'||'" + bunText + "'||'%'"
           + "                    and A.JI  LIKE '%'||'" + jiText  + "'||'%'"
           + "                    and A.REGSTR_KIND_CD NOT LIKE '4' "
           + "               ORDER BY REGSTR_KIND_CD   "
           + "                ) T1                            "
           + "        )                                       " 
           + "  WHERE PAGE = 1";
     }
     try {
       PreparedStatement pstmt = conn.prepareStatement(sql); 
       rs = pstmt.executeQuery(sql);
       
       List<BuildInfo> buildingList = new ArrayList<>();
       
       while(rs.next()) {
         BuildInfo buildInfo = new BuildInfo();
         buildInfo.setBldTypeGBCD(rs.getString("BLD_TYPE_GB_CD"));
         buildInfo.setBuildingPK(rs.getString("MGM_BLD_PK"));
         buildInfo.setRegstrGBCD(rs.getString("REGSTR_GB_CD"));
         buildInfo.setRegstrKINDCD(rs.getString("REGSTR_KIND_CD"));
         buildInfo.setSidoNM(rs.getString("SIDO_NM"));
         buildInfo.setSigunguNM(rs.getString("SIGUNGU_NM"));
         buildInfo.setBjdongNM(rs.getString("BJDONG_NM"));
         buildInfo.setBunNum(rs.getString("BUN"));
         buildInfo.setJiNum(rs.getString("JI"));
         buildInfo.setBldNM(rs.getString("BLD_NM"));
         buildInfo.setMainPurpsNM(rs.getString("MAIN_PURPS_NM"));
         buildInfo.setStrctNM(rs.getString("STRCT_NM"));
         buildInfo.setHoNM(rs.getString("HO_NM"));
         buildingList.add(buildInfo);
         
         this.setTotalRows(rs.getInt("TTCNT"));
       }
         return buildingList;
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        closeDatabase();
      }
     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       closeDatabase();
     }
    return null;
  }
   
  
  // 리스트 페이징 처리
 public List<BuildInfo> getfindBuildList(int rowsPerPage, int page, String includeCheck) throws SQLException {
   try {
     conn = DriverManager.getConnection(url, user, password);
     stmt = conn.createStatement();
     
     String sql = null;
     if(includeCheck.equals("unchecked")) {
       sql = " SELECT *                                                     "
           + "   FROM                                                       "
           + "        (SELECT T1.*,                                         "
           + "                ROWNUM AS RN,                                 "
           + "                 FLOOR((ROWNUM-1)/"+rowsPerPage+"+1) AS PAGE, "
           + "                 COUNT(*) OVER() AS TTCNT                     "
           + "           FROM                                               "
           + "                (                                             "
           + "                 select DISTINCT A.BLD_TYPE_GB_CD, A.MGM_BLD_PK, A.REGSTR_GB_CD, A.REGSTR_KIND_CD, " 
           + "                                 B.SIDO_NM, B.SIGUNGU_NM, B.BJDONG_NM, A.BUN, A.JI, A.BLD_NM, "
           + "                                 C.CD_NM AS MAIN_PURPS_NM, D.CD_NM AS STRCT_NM, A.HO_NM  "
           + "                   from BDT_BLDRGST A " 
           + "                        left outer join CMC_BJDONG_MGM B on A.SIGUNGU_CD = B.SIGUNGU_CD " 
           + "                                                        and A.BJDONG_CD  = B.BJDONG_CD "
           + "                        left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM024') C on A.MAIN_PURPS_CD = C.SGRP_CD "
           + "                        left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM004') D on A.STRCT_CD = D.SGRP_CD "
           + "                  where A.SIGUNGU_CD = '" + sigunguCD + "' "
           + "                    and A.BJDONG_CD  = '" + bjdongCD + "' " 
           + "                    and A.BUN LIKE '%'||'" + bunText + "'||'%'"
           + "                    and A.JI  LIKE '%'||'" + jiText  + "'||'%'"
           + "               ORDER BY BUN              "
           + "                ) T1                            "
           + "        )                                       " 
           + "  WHERE PAGE = "+page+"";
     }
     if(includeCheck.equals("checked")) {
       sql = " SELECT *                                                     "
           + "   FROM                                                       "
           + "        (SELECT T1.*,                                         "
           + "                ROWNUM AS RN,                                 "
           + "                 FLOOR((ROWNUM-1)/"+rowsPerPage+"+1) AS PAGE, "
           + "                 COUNT(*) OVER() AS TTCNT                     "
           + "           FROM                                               "
           + "                (                                             "
           + "                 select DISTINCT A.BLD_TYPE_GB_CD, A.MGM_BLD_PK, A.REGSTR_GB_CD, A.REGSTR_KIND_CD, " 
           + "                                 B.SIDO_NM, B.SIGUNGU_NM, B.BJDONG_NM, A.BUN, A.JI, A.BLD_NM, "
           + "                                 C.CD_NM AS MAIN_PURPS_NM, D.CD_NM AS STRCT_NM, A.HO_NM  "
           + "                   from BDT_BLDRGST A " 
           + "                        left outer join CMC_BJDONG_MGM B on A.SIGUNGU_CD = B.SIGUNGU_CD " 
           + "                                                        and A.BJDONG_CD  = B.BJDONG_CD "
           + "                        left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM024') C on A.MAIN_PURPS_CD = C.SGRP_CD "
           + "                        left outer join (SELECT * FROM CMC_COMM_CD_MGM WHERE LGRP_CD = 'CM004') D on A.STRCT_CD = D.SGRP_CD "
           + "                  where A.SIGUNGU_CD = '" + sigunguCD + "' "
           + "                    and A.BJDONG_CD  = '" + bjdongCD + "' " 
           + "                    and A.BUN LIKE '%'||'" + bunText + "'||'%'"
           + "                    and A.JI  LIKE '%'||'" + jiText  + "'||'%'"
           + "                    and A.REGSTR_KIND_CD NOT LIKE '4' "
           + "               ORDER BY BUN              "
           + "                ) T1                            "
           + "        )                                       " 
           + "  WHERE PAGE = "+page+"";
     }
     try {
       PreparedStatement pstmt = conn.prepareStatement(sql); 
       rs = pstmt.executeQuery(sql);
       
       List<BuildInfo> buildingList = new ArrayList<>();
       
       while(rs.next()) {
         BuildInfo buildInfo = new BuildInfo();
         buildInfo.setBldTypeGBCD(rs.getString("BLD_TYPE_GB_CD"));
         buildInfo.setBuildingPK(rs.getString("MGM_BLD_PK"));
         buildInfo.setRegstrGBCD(rs.getString("REGSTR_GB_CD"));
         buildInfo.setRegstrKINDCD(rs.getString("REGSTR_KIND_CD"));
         buildInfo.setSidoNM(rs.getString("SIDO_NM"));
         buildInfo.setSigunguNM(rs.getString("SIGUNGU_NM"));
         buildInfo.setBjdongNM(rs.getString("BJDONG_NM"));
         buildInfo.setBunNum(rs.getString("BUN"));
         buildInfo.setJiNum(rs.getString("JI"));
         buildInfo.setBldNM(rs.getString("BLD_NM"));
         buildInfo.setMainPurpsNM(rs.getString("MAIN_PURPS_NM"));
         buildInfo.setStrctNM(rs.getString("STRCT_NM"));
         buildInfo.setHoNM(rs.getString("HO_NM"));
         buildingList.add(buildInfo);
         
         this.setTotalRows(rs.getInt("TTCNT"));
       }
       return buildingList;
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeDatabase();
    }
     
   } catch (Exception e) {
     e.printStackTrace();
   } finally {
     closeDatabase();
   }
  return null;
 }
  
}
