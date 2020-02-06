package com.application.app;
/*  기능...
 * - 회원가입 화면
 *   아이디, 비밀번호, 비밀번호 확인, 이름, 관리자등록번호 텍스트 필드로 구성
 *   아이디 입력시 기존 테이블(CMC_USER_MGM)의 데이터와 일치하면 경고 표시. (3글자 이상 입력해야 함)
 *   비밀번호 입력시 비밀번호 확인과 두개의 값이 일치하지 않으면 경고 표시.
 *   이름은 2글자 이상
 *   관리자등록번호는 해당 번호값이 맞다면 관리자 등급으로 회원가입(비어두거나 틀리면 일반사용자로 등록)
 *   관리자등록번호 관리 클래스 (requireClass 패키지 안의 ManagerCheckNumber에 배열로 구성.)
 *   
 * - 로그인 화면
 *   CMC_USER_MGM 테이블의 USER_ID와 PWD의 값으로 로그인을 할 수 있음
 *   USER_AUTHRT_CD에 따라서 로그인 하기전 관리자인지 일반 유저인지 표기됨
 * 
 * - 검색 화면
 *   시도, 시군구, 법정동 까지만 선택 후 조회하면 느려지거나 멈출 수 있지만 조회 가능.
 *   번지 입력 후 조회 버튼을 누르면 상세 조회 가능.
 *   새주소로 검색을 하려면 번지에 해당하는 부분이 비어 있어야 새주소로 검색 가능 (둘다 있는 경우 번지 조회 우선)
 *   새주소는 도로명과 번이 필수 값. (시도 시군구 없어도 검색 가능)
 *   전유부제외 체크박스를 체크할 시 해당 검색 값에서 대장종류 4(전유부)를 제외하고 출력 
 *   완전일치 체크박스를 체크할 시 해당 지번 앞 뒤에 0으로 채워서 검색 (새주소는 '지'에 해당하는 부분에 0을 넣음)
 *   인쇄 버튼을 누를 시 현재 목록을 프린트
 *   <<이전 버튼은 해당 검색결과 페이지 이전으로 이동
 *   다음>> 버튼은 해당 검색결과 페이지 다음으로 이동
 * 
 * - 상세 조회 화면
 *   검색 화면에서 하나의 데이터값을 선택했을 시
 *   해당 값으로 상세 정보 조회 ( 지역, 지구, 구역, 조경면적, 공개 공간의 면적, 건축선 후퇴면적, 건축선 후퇴거리 부분은 제외)
 *   주구조, 주용도, 층수, 지붕 선택 박스는 초기에 값이 있다면 값이 선택되어 있음
 *   명칭으로 찾거나 코드값으로 지정할 수 있음. 층수 관련 업데이트는 지상 또는 지하 하나의 경우만 가능함.
 *   
 *   돌아가기 버튼은 검색 화면으로 돌아감(아까 검색했던 결과 그대로)
 *   수정하기 버튼은 관리자 등급만 가능함.(CMC_USER_MGM 테이블의 USER_AUTHRT_CD 값이 10이여야 함.)
 *   엑셀저장 후 인쇄 버튼을 누르면 엑셀만 저장할 것인지 선택할 수 있는 창이 나옴.
 *   
 */
import java.sql.SQLException;
import com.application.db.DBLogin;
import com.application.dto.BuildInfo;
import com.application.dto.Member;

public class MainProcess {
  
  Member member;
  LoginForm loginForm;
  SubmitForm submitForm;
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
  
  public void createSubmitForm() {
    this.submitForm = new SubmitForm();
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

  /* 로그인 한 회원 객체를 돌려받음  관리자만 UPDATE */
  public void showMainPage(MainProcess main) {
    loginForm.dispose();
    
    this.searchPage = new SearchBuild();
    searchPage.setMember(member);
    searchPage.setMain(main);
  }
  
  /* 회원가입 폼 띄워주기 */  
  public void showSubmitForm() {
    loginForm.dispose();
    
    submitForm.setMain(main);
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
