package com.application.app;

import com.application.db.DBLogin;
import com.application.vo.BuildInfo;
import com.application.vo.Member;

public class MainProcess {
  
  Member member;
  LoginForm loginForm;
  MainPage mainPage;
  DetailForm detailForm;   // 일반건축물대장
  DetailForm2 detailForm2; // 총괄표제부
  //DetailForm3 detailForm3; // 집합표제부
  //DetailForm4 detailForm4; // 전유부
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
  }// 로그인 한 회원 객체를 돌려받음
   // USERGRADE를 이용해 관리자만 UPDATE,DELETE를 이용할 수 있게 함
  
  
  public void showMainPage(MainProcess main) {
    loginForm.dispose();
    
    this.main = main;
    this.mainPage = new MainPage();
    mainPage.setMember(member);
    mainPage.setMain(main);
  }

  
  public void createDetailForm() {
    this.detailForm = new DetailForm();
  }
  public void createDetailForm2() {
    this.detailForm2 = new DetailForm2();
  }
//  public void createDetailForm3() {
//    this.detailForm3 = new DetailForm3();
//  }
//  public void createDetailForm4() {
//    this.detailForm4 = new DetailForm4();
//  }
  
  
  // 건축물대장 종류에 따라서 분기
  
  // 일반건축물
  public void showDetailForm(BuildInfo buildInfo) {
    mainPage.dispose();
    detailForm.setMain(main);
    detailForm.setMainPage(mainPage);
    detailForm.setDetailForm(detailForm);
    detailForm.setBuildInfo(buildInfo);
  
  }
  
  // 총괄표제부
  public void showDetailForm2(BuildInfo buildInfo) {
    mainPage.dispose();
    System.out.println(main);
    detailForm2.setMain(main);
    detailForm2.setMainPage(mainPage);
    detailForm2.setDetailForm2(detailForm2);
    detailForm2.setBuildInfo(buildInfo);
  }
  
  
  
  

//  IO 예외 상황: The Network Adapter could not establish the connection
//  => 해결 : DB설정 SID NAME 확인하고 바꿈. (초기값 orcl, xe) nbem2로 변경되있음.
}
