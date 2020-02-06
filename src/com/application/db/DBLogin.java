package com.application.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.application.dto.Member;

// 사용자가 로그인을 할 때 처리할 DB관련 클래스
public class DBLogin{
  
  String driver = "oracle.jdbc.driver.OracleDriver";
  String url = "jdbc:oracle:thin:@localhost:1521:orcl";
  String user = "nbem2_adm";
  String password = "nbem02";
  
  Connection conn = null;  // Connection 객체는 데이터베이스와 연결하는 객체.(Statement 객체를 생성할 때도 Connection 객체를 사용)
  Statement stmt = null;   // 특정한 SQL 문장을 정의하고 실행 시킬 수 있는 Statement
  ResultSet rs = null;     // SELECT 문을 사용시 결과를 담는 객체.
  
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
      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  
  public Member login(String id, String pwd) throws SQLException {
      Member member = new Member();
        String checkId = null;
        String checkName = null;
        String checkPwd = null;
        String checkUserGrade = null;
        
        try {
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
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          closeDatabase();
        }
      return member;
  }

  // 아이디 확인용 메소드
  public String idCheck(String inputId) throws SQLException {
    String exist = null;
    String grade = null;
      try {
      conn = DriverManager.getConnection(url, user, password);
      stmt = conn.createStatement();
      String sql = " select USER_ID, USER_AUTHRT_CD "
                 + "   from CMC_USER_MGM " 
                 + "  where USER_ID = '"+ inputId +"'";
      
      rs = stmt.executeQuery(sql);
      
      while(rs.next()) {
       exist = rs.getString("USER_ID");
       grade = rs.getString("USER_AUTHRT_CD");
      }
      } catch (Exception e) {
        e.printStackTrace();
        if(exist == null && grade == null) return null;
      } finally {
        closeDatabase();        
      }
    return exist + "/" + grade;
  }

  
  // 사용자 추가할때
  public void createMember(Member member) throws SQLException {
    try {
    conn = DriverManager.getConnection(url, user, password);
    stmt = conn.createStatement();
    
    String sql = " INSERT INTO CMC_USER_MGM(USER_ID, PWD, USER_NM, DEPT_NM, USER_AUTHRT_CD) " 
               + " VALUES ('"+member.getId()+"', '"+member.getPwd()+"', '"+member.getName()+"', '건물에너지 유지관리사업단', '"+member.getUserGrade()+"') ";
    
    stmt.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeDatabase();
    }

  }

}
