# Solo_S_Make
 >회사 DB를 이용한 나 혼자서 만드는 스윙 프로젝트
 
 >DB에 있는 데이터를 가져와 조회, 수정, 삭제 외부로의 출력 기능을 가능하게 한다.
 
 >스프링은 이용하지 않는다.
 
 >Swing을 이용하여 UI를 처리한다.
 
 
 - DB의 처리.
   >mybatis를 이용하려 했으나, 
   
   >총 10대의 PC에 나뉘어져있는 250개의 시군구DB와 특정한 계정권한으로 접속해야 되는 환경이기에
   
   >mybatis를 도중 제외하였다.
   
   >(스프링, WAS를 이용하지않고 mybatis의 DB정보를 변경할 수 없다.)
   
   >유동적인 DB연결이 되어야 한다.
 
 
 - 로그인기능.
    >해당 PC의 계정 테이블에 있는 작업자 정보로 로그인을 할 수있게 함.
    
    >(12/20)로그인시 사용자가 ID부분에 값을 입력하면 그 값으로 조회하여 관리자인지 일반사용자인지를 표시함.
 
 
 - 회원가입기능.
    >해당 PC의 계정 테이블에 새로 추가할 수 있는 화면.
    
    >ID 입력 텍스트 필드에 공백을 포함하지 않고 글자수 제한과 기존 DB에 있는 사용자의 아이디와 달라야지만 확인되는 동적인 표시.
    
    >비밀번호, 비밀번호 확인 입력 텍스트 필드에 서로 같아야지만 확인이 되는 동적인 표시.
    
    >이름 입력 텍스트 필드에 공백을 포함하지 않고 글자수 제한. 동적인 표시.
    
    >관리자등록번호는 관리자로 등록을 원하면 다른클래스에서 미리 명시해둔 값이 맞아야 관리자 등급으로 회원등록 가능.
     >(맞지않을 시 일반 사용자로 질의.)
 
 
 - 검색기능.
    >콤보박스 시도, 시군구, 법정동을 선택 후 조회버튼을 누르면 조회.(해당 주소의 관련 지번들이 많으면 제한)
    >(이때 해당하는 시군구를 담당하는 다른 PC IP로 접속하여 해당 계정으로 조회한다.)
    
    >추가로 번-지 입력 후 조회 버튼을 누르면 해당 번-지 정보로 조회.
    
    >완전일치 체크박스에 체크후 조회버튼을 누르면 번,지 정보에 0을 이용하여 검색 정확도 높힘

    >>새주소로 검색(12/20)
    
    >>새주소 입력 textField에 시도, 시군구, 도로명 번-지 를 입력 후 조회버튼을 누르면 조회.
    
    >>도로명 번-지 만을 입력해도 조회.
    
    >>시도명 도로명 번-지 만을 입력해도 조회.
    
    >>시군구명 도로명 번-지 만을 입력해도 조회.
    
    >>완전일치 체크박스에 체크후 조회버튼을 누르면 지에 해당하는 값을 '0'으로 조회.
    
    >>(다르게 설정한 이유 : 새주소 테이블에 있는 새주소 번-지는 '0'으로 지번 앞쪽에 채우기가 되있지 않음.)
 
 
 - 조회기능.
    >조회 후 해당 데이터 행을 누르면 대장 종류 번호에 따라 해당 대장 조회.
    
    >조회 폼에서 데이터를 수정할 수 있게 
    
    >콤보박스 주구조명칭, 주구조코드가 서로 동적으로 변경될 수 있게 함.
    
    >콤보박스 주용도명칭, 주용도코드가 서로 동적으로 변경될 수 있게 함.
    
    >콤보박스 지붕명칭, 지붕코드가 서로 동적으로 변경될 수 있게 함.
    
    >콤보박스 층수가 "지상", "지하" 일때 층수 표현 텍스트 입력 구간에 표시될 수 있게 함. 층수(지상,지하) 업데이트 동시에 불가
 
 
 - 출력기능. 
   >주소로 대장을 조회 후 인쇄 버튼을 누르면 해당되는 데이터만 출력하게 처리.
   
   >출력부분에서 어느정도 갯수(한장의 분량)가 넘어간다면, 다음장으로 넘어가게해서 출력처리.(11/29)
   
   >  POI 활용 !!
   
   >모든 건축물대장 유형에 건축물현황, 층별정보, 소유자정보 등 엑셀로 저장 후 출력기능은
   
   >해당 데이터의 수가 엑셀의 표시 칸보다 넘어가게 되면
   
   >2번 시트를 추가하여(형태가 다른 시트) 나머지를 출력되게 함.
   
   
 - 업데이트기능.
   >모든 건축물대장의 건물정보 부분은 업데이트를 가능하게 함. (건축물현황, 층별정보, 소유자정보 관련 정보는 업데이트 x)


 - 디자인관련.
   >모든 폼 디자인변경. 
   
   >백그라운드 색상 및 버튼 색상을 업데이트
