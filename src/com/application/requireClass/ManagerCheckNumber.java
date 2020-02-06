package com.application.requireClass;
/* 설명...
 * 회원가입시 관리자등록번호 입력란에 managerNumber 배열 안에 있는 값을 넣어주면 관리자 등급으로 가입이 가능하다 */
public class ManagerCheckNumber { // 관리자 등록 번호(입력하면 관리자)
  protected final String managerNumber[] = new String[] {"1234"};

  public String[] getManagerNumber() {
    return managerNumber;
  }
  
}
