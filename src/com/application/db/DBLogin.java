package com.application.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.application.vo.Member;

public class DBLogin {
  
  String driver = "oracle.jdbc.driver.OracleDriver";
  String url = "jdbc:oracle:thin:@localhost:1521:nbem2";
  String user = "nbem2_adm";
  String password = "nbem02";
  
  Connection conn = null;
  Statement stmt = null;
  ResultSet rs = null;
  
  public DBLogin() {
    try {
      Class.forName(driver);
      System.out.println("jdbc driver Login로딩 성공");
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
  
  
  public Member login(String id, String pwd) throws SQLException {
      Member member = new Member();
        String checkId = null;
        String checkName = null;
        String checkPwd = null;
        String checkUserGrade = null;
        
          conn = DriverManager.getConnection(url, user, password);

          stmt = conn.createStatement();
          
          // SQL 쿼리 작성.
          // 주의사항
          // 1) JDBC에서 쿼리를 작성할 때는 세미콜론(;)을 뺴고 작성
          // 2) SELECT 할 때 *으로 모든 컬럼을 가져오는 것보다 가져와야할 컬럼을 직접 명시해주는 것이 좋다.
          // 3) 원하는 결과는 쿼리로써 마무리 짓고, java 코드로 후작업하는 것은 권하지 않음
          // 4) 쿼리를 한 줄로 쓰기 어려운 경우 들여쓰기를 사용해도 되지만 띄어쓰기에 유의!!
          String sql = "select USER_ID, USER_NM, PWD, USER_AUTHRT_CD from CMC_USER_MGM "+
                       " where USER_ID = '"+ id + "' and PWD = '"+ pwd + "'";
          // 테이블명과 where 사이의 공백이 필요하거나
          // 작은따옴표도 필요한지 생각해야함.
          
          rs = stmt.executeQuery(sql);
          while(rs.next()) {
            checkId = rs.getString("USER_ID");
            checkName = rs.getString("USER_NM");
            checkPwd = rs.getString("PWD");
            checkUserGrade = rs.getString("USER_AUTHRT_CD");
          }
          
          member.setId(checkId);
          member.setName(checkName);
          member.setPwd(checkPwd);
          member.setUserGrade(checkUserGrade);
          
          System.out.println(checkId +" " + checkName + " " + checkPwd + " " +checkUserGrade);
          
      return member;
  }
  

}
