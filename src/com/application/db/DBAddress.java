package com.application.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//시도,시군구,법정동 콤보박스를 선택했을때 처리할 DB관련 클래스
public class DBAddress { 
  
  String driver = "oracle.jdbc.driver.OracleDriver";
  String url = "jdbc:oracle:thin:@localhost:1521:orcl";
  String user = "nbem2_adm";
  String password = "nbem02";
  
  Connection conn = null; // Connection 객체는 데이터베이스와 연결하는 객체 (Statement 객체를 생성할 때도 Connection 객체를 사용)
  Statement stmt = null;  // 특정한 SQL 문장을 정의하고 실행 시킬 수 있는 Statement
  ResultSet rs = null;    // SELECT 문을 사용시 결과를 담는 객체.
  
  public DBAddress() {
    try {
      Class.forName(driver);
      System.out.println("jdbc driver Address 로딩 성공");
    }
    catch (ClassNotFoundException e) {
      System.out.println("[로드 오류]\n" + e.getStackTrace());
    }
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
  
  
  // 시군구 명을 찾기위한 메서드
  public List<Object> findSigunguNM(String sidoNM) throws SQLException {
    
    List<Object> sigunguList = new ArrayList<>();
    try {
      conn = DriverManager.getConnection(url, user, password);
      stmt = conn.createStatement();
  
      
      // SQL 쿼리 작성.
      // 주의사항
      // 1) JDBC에서 쿼리를 작성할 때는 세미콜론(;)을 뺴고 작성
      // 2) SELECT 할 때 *으로 모든 컬럼을 가져오는 것보다 가져와야할 컬럼을 직접 명시해주는 것이 좋다.
      // 3) 원하는 결과는 쿼리로써 마무리 짓고, java 코드로 후작업하는 것은 권하지 않음
      // 4) 쿼리를 한 줄로 쓰기 어려운 경우 들여쓰기를 사용해도 되지만 띄어쓰기에 유의!!
      // 테이블명과 where 사이의 공백이 필요하거나 작은따옴표도 필요한지 생각해야함.
      
      String sql = " select DISTINCT SIGUNGU_NM "
                 + "   from CMC_BJDONG_MGM "
                 + "  where SIDO_NM = '"+ sidoNM +"'"
                 + "    and SIGUNGU_NM NOT LIKE '%출장소%'"
                 + "    and APPLY_EXP_DAY = '99991231'"
                 + "    order by SIGUNGU_NM";
      
        rs = stmt.executeQuery(sql);
      
      while(rs.next()) {
        sigunguList.add(rs.getString("SIGUNGU_NM"));
      }
      
      sigunguList.removeAll(Collections.singleton(null));
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeDatabase();
    }
    return sigunguList;
  }
  
  // 해당하는 시도의 시군구의 법정동을 찾기위한 메서드
  public List<Object> findBjdongNM(String sidoSelect, String firstSigunguName) throws SQLException {
    List<Object> bjdongList = new ArrayList<>();

    try {
      conn = DriverManager.getConnection(url, user, password);
      stmt = conn.createStatement();
      
      
   // 시군구 명이 있으면 시도이름과 시도명으로 법정동명을 찾는다.
      if(firstSigunguName != null) { 
        
      String sql = " select DISTINCT BJDONG_NM " 
                 + "   from CMC_BJDONG_MGM "  
                 + "  where SIDO_NM    =  '" + sidoSelect + "'"
                 + "    and SIGUNGU_NM =  '" + firstSigunguName + "'"
                 + "    and APPLY_EXP_DAY = '99991231'"
                 + "    order by BJDONG_NM";
      
        rs = stmt.executeQuery(sql);
      }
      
   // 시군구 명이 없다면 시도이름으로 법정동을 찾는다 => 법정동 테이블에서 시도이름중에 세종특별자치시는 시군구 명이 없다 (시도 이름으로만 법정동을 찾는다)
      if(firstSigunguName == "") { 
        String sql = " select DISTINCT BJDONG_NM " 
            + "   from CMC_BJDONG_MGM "  
            + "  where SIDO_NM    =  '" + sidoSelect + "'"
            + "    and APPLY_EXP_DAY = '99991231'"
            + "    order by BJDONG_NM";
        
        rs = stmt.executeQuery(sql);
      }
      
      while(rs.next()) {
        bjdongList.add(rs.getString("BJDONG_NM"));
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeDatabase();
    }
    
    return bjdongList;
  }
  
  
  // 시군구 코드를 찾기위한 메서드
  public String findSigunguCD(String sidoNM, String sigunguNM) throws SQLException {
    String sigunguCD = null;
    try {
      conn = DriverManager.getConnection(url, user, password);
      stmt = conn.createStatement();
      
      // 일반적인 상황은 법정동 테이블에서 시군구 명이 존재
      if(sigunguNM != "세종특별자치시") {
      String sql = " select DISTINCT SIGUNGU_CD "
                 + "   from CMC_BJDONG_MGM "  
                 + "  where SIDO_NM    = '" + sidoNM + "'" 
                 + "    and SIGUNGU_NM = '" + sigunguNM + "'"
                 + "    and APPLY_EXP_DAY = '99991231'";
  
        rs = stmt.executeQuery(sql);
      }
      
      // 법정동 테이블에 시도명이 "세종특별자치시" 일 때 SIGUNGU_NM의 값이 없음 그래서 SIGUNGU_CD값을 조회할때 SIGUNGU_NM을 제외해야 함.
      if(sigunguNM == "세종특별자치시") {
        String sql = " select DISTINCT SIGUNGU_CD "
            + "   from CMC_BJDONG_MGM "  
            + "  where SIDO_NM    = '" + sidoNM + "'"
            + "    and APPLY_EXP_DAY = '99991231'";
      
        rs = stmt.executeQuery(sql);
      }
      while(rs.next()) {
        sigunguCD = rs.getString("SIGUNGU_CD");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeDatabase();
    }
    return sigunguCD;
  }
  
  
  // 법정동 코드를 찾기위한 메서드 (예외상황 처리 x)
  public String findBjdongCD(String sidoNM, String sigunguNM, String bjdongNM) throws SQLException {
    String bjdongCD = null;
    try {
      conn = DriverManager.getConnection(url, user, password);
      stmt = conn.createStatement();
      
      // 일반적인 상황은 법정동 테이블에서 시군구 명이 존재
      if(sigunguNM != "세종특별자치시") {
      String sql = " select DISTINCT BJDONG_CD "
                 + "   from CMC_BJDONG_MGM "  
                 + "  where SIDO_NM    = '" + sidoNM + "'"
                 + "    and SIGUNGU_NM = '" + sigunguNM + "'"
                 + "    and BJDONG_NM  = '" + bjdongNM + "'"
                 + "    and APPLY_EXP_DAY = '99991231'";
      
        rs = stmt.executeQuery(sql);
      }
      
      // 법정동 테이블에 시도명이 "세종특별자치시" 일 때 SIGUNGU_NM의 값이 없음 그래서 BJDONG_CD값을 조회할때 SIGUNGU_NM을 제외해야 함.
      if(sigunguNM == "세종특별자치시") {
        String sql = " select DISTINCT BJDONG_CD "
                   + "   from CMC_BJDONG_MGM "  
                   + "  where SIDO_NM    = '" + sidoNM + "'"
                   + "    and BJDONG_NM  = '" + bjdongNM + "'"
                   + "    and APPLY_EXP_DAY = '99991231'";
  
        rs = stmt.executeQuery(sql);
      }
      
      while(rs.next()) {
        bjdongCD = rs.getString("BJDONG_CD");
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeDatabase();
    }
    return bjdongCD;
  }




}
