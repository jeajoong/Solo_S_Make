# Solo_S_Make
 회사 DB를 이용한 나 혼자서 만드는 스윙 프로젝트
 
 DB에 있는 데이터를 가져와 조회, 수정, 삭제 외부로의 출력 기능을 가능하게 한다.
 
 스프링은 이용하지 않는다.
 
 Swing을 이용하여 UI를 처리한다.
 
 DB의 처리는
 mybatis를 이용하려 했으나, 
 총 10대의 PC에 나뉘어져있는 250개의 시군구DB와 특정한 계정권한으로 접속해야 되는 환경이기에
 mybatis를 도중 제외하였다.
 (스프링, WAS를 이용하지않고 mybatis의 DB정보를 변경할 수 없다.)
 
 
 출력기능.
 
 출력부분에서 어느정도 갯수(한장의 분량)가 넘어간다면, 다음장으로 넘어가게해서 출력처리한다.
 
