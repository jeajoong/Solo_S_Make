package com.application.app;

import java.sql.SQLException;
import com.application.db.DBLogin;
import com.application.dto.BuildInfo;
import com.application.dto.Member;

public class MainProcess {
  
  Member member;
  LoginForm loginForm;
  SearchBuild searchPage;
  DetailForm  detailForm;  // 일반건축물대장
  DetailForm2 detailForm2; // 총괄표제부
  DetailForm3 detailForm3; // 집합표제부
  DetailForm4 detailForm4; // 전유부
  MainProcess main;
  
  public static void main(String[] args) {
    
    MainProcess main = new MainProcess();
       main.loginForm = new LoginForm();
       main.loginForm.setMain(main);
       main.loginForm.setDB(new DBLogin());
  }
  
  public void setMain(MainProcess main) {
    this.main= main;
  }
  
  public void setMemberInfo(Member memberInfo) {
    this.member = memberInfo;
  }
  /* 로그인 한 회원 객체를 돌려받음 */
  // 나중에 USERGRADE를 이용해 관리자만 UPDATE,DELETE를 이용 할 수 있게
  
  public void showMainPage(MainProcess main) {
    loginForm.dispose();
    
    this.searchPage = new SearchBuild();
    searchPage.setMember(member);
    searchPage.setMain(main);
  }

  public void createDetailForm() {
    this.detailForm = new DetailForm();
  }
  public void createDetailForm2() {
    this.detailForm2 = new DetailForm2();
  }
  public void createDetailForm3() {
    this.detailForm3 = new DetailForm3();
  }
  public void createDetailForm4() {
    this.detailForm4 = new DetailForm4();
  }
  
    /* 건축물대장 종류에 따라서 분기 */
  // 일반건축물
  public void showDetailForm(BuildInfo buildInfo) throws SQLException {
    searchPage.dispose();
    detailForm.setMain(main);
    detailForm.setSearchPage(searchPage);
    detailForm.setDetailForm(detailForm);
    detailForm.setMember(member);
    detailForm.setBuildInfo(buildInfo);
    detailForm.inputBuildInfo(buildInfo);
  }
  // 총괄표제부
  public void showDetailForm2(BuildInfo buildInfo) throws SQLException {
    searchPage.dispose();
    detailForm2.setMain(main);
    detailForm2.setSearchPage(searchPage);
    detailForm2.setDetailForm2(detailForm2);
    detailForm2.setMember(member);
    detailForm2.setBuildInfo(buildInfo);
    detailForm2.inputBuildInfo(buildInfo);
  }
  // 집합표제부
  public void showDetailForm3(BuildInfo buildInfo) throws SQLException {
    searchPage.dispose();
    detailForm3.setMain(main);
    detailForm3.setSearchPage(searchPage);
    detailForm3.setDetailForm3(detailForm3);
    detailForm3.setMember(member);
    detailForm3.setBuildInfo(buildInfo);
    detailForm3.inputBuildInfo(buildInfo);
  }
  // 전유부
  public void showDetailForm4(BuildInfo buildInfo) throws SQLException {
    searchPage.dispose();
    detailForm4.setMain(main);
    detailForm4.setSearchPage(searchPage);
    detailForm4.setDetailForm4(detailForm4);
    detailForm4.setMember(member);
    detailForm4.setBuildInfo(buildInfo);
    detailForm4.inputBuildInfo(buildInfo);
  }
  
//  IO 예외 상황: The Network Adapter could not establish the connection
//  => 해결 : DB설정 SID NAME 확인하고 바꿈. (초기값 orcl, xe) nbem2로 변경되있음.
}
