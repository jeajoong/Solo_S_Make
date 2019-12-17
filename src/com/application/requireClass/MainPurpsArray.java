package com.application.requireClass;

public class MainPurpsArray {

  // 생성자를 이용한 건물 주용도 배열.
  
    public String[] mainPurpsNM = new String[] { "주용도명칭","단독주택", "다중주택", "다가구주택", "공관", "공동주택", "아파트", "연립주택", "다세대주택", 
        "생활편익시설", "부대시설", "복리시설", "기숙사", "제1종근린생활시설", "소매점", "휴게음식점", "이(미)용원", "일반목욕장", "의원", "체육장", "마을공동시설", "변전소", "양수장", 
        "정수장", "대피소", "공중화장실", "세탁소", "치과의원", "한의원", "침술원", "접골원", "조산소", "탁구장", "체육도장", "마을회관", "마을공동작업소", "마을공동구판장", "지역아동센터",
        "목욕장", "이용원", "미용원", "조산원", "제과점", "슈퍼마켓", "안마원", "공공시설", "동사무소", "경찰서", "파출소", "소방서", "우체국", "전신전화국", "방송국", "보건소", "공공도서관",
        "지역의료보험조합", "제과점", "지역자치센터", "지구대", "지역건강보험조합", "기타공공시설", "기타제1종근생활시설", "제2종근린생활시설",
        "일반음식점", "휴게음식점", "기원", "서점(1종근.생미해당)", "제조업소", "수리점", "게임제공업소", "사진관", "표구점", "학원", "장의사", "동물병원", "어린이집", "독서실", "총포판매소",
        "단란주점", "의약품도매점", "자동차영업소", "안마시술소", "노래연습장", "세탁소", "멀티미디어문화컨텐츠설비제공업소", "복합유통.제공업소", "직업훈련소", "인터넷컴퓨터게임시설제공업소", 
        "청소년게임제공업소", "복합유통게임제공업소", "제과점", "고시원", "의약품판매소", "의료기기판매소", "총포판매사", "소개업", "안마원", "고시원", "제과점", "인터넷컴퓨터게임시설제공업의시설",
        "청소년게임제공업의시설", "복합유통게임제공업의시설", "운동시설", "테니스장", "체력단련장", "에어로빅장", "볼링장", "당구장", "실내낚시터", "골프연습장", "물놀이형시설", "기타운동시설",
        "종교집회장", "교회", "성당", "사찰", "기도원", "수도원", "수녀원", "제실", "사당", "기타종교집회장", "공연장", "극장(영화관)", "음악당", "연예장",
        "비디오물감상실", "비디오물소극장", "극장", "영화관", "서커스장", "기타공연장", "사무소", "금융업소", "사무소", "부동산중개업소", "결혼상담소", "출판사", "부동산중개사무소", "기타사무소",
        "기타제2종근생활시설", "문화및집회시설", "공연장", "극장(영화관)", "음악당", "연예장", "서어커스장", "비디오물감상실", "비디오물소극장", "극장", "영화관", "기타공연장", "집회장", "예식장",
        "회의장", "공회당", "마권장외발매소", "마권전화투표소", "기타집회장", "관람장", "경마장", "자동차경기장", "체육관", "운동장", "경륜장", "경정장", "기타관람장", "전시장", "박물관",
        "미술관", "과학관", "기념관", "산업전시장", "박람회장", "문화관", "체험관", "기타전시장", "동.식물원", "동물원", "식물원", "수족관", "기타동.식물원", "기타문화및집회시설", "종교시설",
        "종교집회장", "교회", "성당", "사찰", "기도원", "수도원", "수녀원", "제실", "사당", "납골당(제2종근생 제외)", "봉안당", "기타종교집회장", 
        "기타종교시설", "판매시설", "도매시장", "소매시장", "시장", "백화점", "대형백화점", "대형점", "쇼핑센터", "기타소매시장", "상점", "게임제공업소", "멀티미디어문화컨텐츠설비제공업소",
        "복합유통.제공업소", "인터넷컴퓨터게임시설제공업소", "청소년게임제공업소", "복합유통게임제공업소", "일반게임제공의시설", "청소년게임제공업의시설", "복합유통게임제공업의시설", "인터넷컴퓨터게임시설제공업의시설",
        "일반게임제공업의시설", "도매시장", "농수산물도매시장", "농수산물공판장", "기타도매시장", "기타판매시설", "운수시설", "여객자동차터미널", "화물터미널", "철도역사", "공항시설", "항만시설(터미널)",
        "종합여객시설", "철도시설", "항만시설", "기타운수시설", "의료시설", "병원", "종합병원", "산부인과병원", "치과병원", "한방병원", "정신병원", "격리병원", "병원", "요양소", "요양병원",
        "기타병원", "격리병원", "전염병원", "마약진료소", "기타격리병원", "장례식장", "기타의료시설", "교육연구시설", "교육(연수)원", "직업훈련소", "학원",
        "연구소", "도서관", "학교", "초등학교", "중학교", "고등학교", "대학교", "전문대학", "대학", "유치원", "기타학교", "교육원", "교육원", "연수원", "기타교육원", "연구소", "연구소",
        "시험소", "계측계량소", "기타연구소", "기타교육연구시설", "노유자시설", "아동관련시설", "유치원", "영유아보육시설", "어린이집", "아동복지시설", "기타아동관련시설", "노인복지시설", "사회복지시설",
        "근로복지시설", "기타노유자시설", "수련시설", "유스호스텔", "생활권수련시설","청소년수련원(관)", "유스호스텔", "청소년문화의집", "청소년특화시설", "청소년수련관", "기타생활권수련시설",
        "자연권수련시설", "청소년수련원(관)", "청소년야영장", "청소년수련원", "기타자연권수련시설", "기타수련시설", "운동시설", "체육관", "운동장시설", "탁구장", "체육도장", "테니스장", "체력단련장",
        "에어로빅장", "볼링장", "당구장", "실내낚시터", "골프연습장", "물놀이형시설", "운동장", "육상장", "구기장", "볼링장", "수영장", "스케이트장", "롤러스케이트장", "승마장", "사격장", "궁도장",
        "골프장", "기타 운동시설", "업무시설", "공공업무시설", "국가기관청사", "자치단체청사", "외국공관", "기타공공업무시설", "일반업무시설", "금융업소", "오피스텔", "신문사", "사무소", "기타일반업무시설",
        "숙박시설", "고시원", "일반숙박시설", "호텔", "여관", "여인숙", "기타일반숙박시설", "관광숙박시설", "관광호텔", "수상관광호텔", "한국전통호텔", "가족호텔", "휴양콘도미니엄", "기타관광숙박시설",
        "고시원", "기타숙박시설", "위락시설", "단란주점", "유흥주점", "특수목욕장", "유기장", "투전기업소", "무도장(학원)", "주점영업", "카지노업소", "유원시설업의시설", "무도장", "무도학원",
        "카지노영업소", "사행행위업소", "기타위락시설", "공장", "일반공장", "유해공장", "물품공장", "물품 제조공장", "물품 가공공장", "물품 염색공장", "물품 도장공장", "물품 표백공장", "물품 재봉공장", 
        "물품 건조공장", "물품 인쇄공장", "물품 수리공장", "기타공장", "창고시설", "창고", "하역장", "물류터미널", "집배송시설", "창고", "일반창고", "냉장창고", "냉동창고", "기타창고시설", "위험물저장및처리시설",
        "주유소", "액화석유가스충전소", "위험물제조소", "위험물저장소", "액화가스취급소", "액화가스판매소", "유독물보관저장시설", "고압가스충전저장소", "석유판매소", "위험물취급소", "액화석유가스판매소",
        "액화석유가스저장소", "유독물보관소", "유독물저장소", "유독물판매시설", "고압가스충전소", "고압가스판매소", "고압가스저장소", "도료류판매소", "도시가스공급시설", "화약류저장소",
        "기타위험물저장처리시설", "자동차관련시설", "주차장", "세차장", "폐차장", "검사장", "매매장", "정비공장", "운전학원", "정비학원", "차고", "주기장",
        "기타자동차관련시설", "동.식물관련시설", "도축장", "도계장", "버섯재배사", "종묘배양시설", "온실", "작물재배사", "축사", "축사", "양잠", "양봉", "양어시설", "부화장",
        "가축시설", "가축용운동시설", "인공수정센터", "관리사", "가축용창고", "가축시장", "동물검역소", "실험동물사육시설", "기타가축시설", "기타동식물관련시설", "분뇨.쓰레기처리시설",
        "분뇨처리시설", "폐기물처리시설", "폐기물재활용시설", "고물상", "폐기물처리시설및폐기물감량화시설", "기타분뇨쓰레기처리시설",
        "교정및군사시설", "감화원", "군사시설", "국방ㆍ군사시설", "보호관찰소", "갱생보호소", "소년원", "소년분류심사원", "교도소", "구치소", "소년원", "소년분류심사원", "교정시설",
        "보호감호소", "구치소", "교도소", "기타교정및군사시설", "방송통신시설", "방송국", "전신전화국", "촬영소", "통신용시설", "방송국", "방송국", "방송프로그램제작시설", "송신시설",
        "수신시설", "중계시설", "기타방송통신시설", "발전시설", "발전소", "기타발전시설", "묘지관련시설", "화장장", "납골당",
        "묘지에 부수되는 건축물", "봉안당", "화장시설", "자연장지에 부수되는 건축물", "기타묘지관련시설", "관광휴게시설", "야외음악당", "야외극장", "어린이회관", "관망탑", "휴게소",
        "관광지시설", "공원에 부수되는 시설", "유원지에 부수되는 시설", "관광지에 부수되는 시설", "기타관광휴게시설", "가설건축물", "재해복구용가설건축물", "가설흥행장", "가설전람회장", 
        "공사용가설건축물", "견본주택", "가설점포", "경비용가설건축물", "임시자동차차고", "임시사무실", "임시창고", "임시숙소", "농,어업용비닐하우스",
        "가축용가설건축물", "농,어업용고정식온실", "창고용천막", "관광문화행사용가설건축물", "기타가설건축물", "장례식장", "장례식장", "근린생활시설", "소매점", "휴게음식점", "이(미)용원",
        "일반목욕장", "의원", "체육장", "마을공동시설", "변전소", "양수장", "정수장", "대피소", "공중화장실", "치과의원", "한의원", "침술원", "접골원", "조산소", "탁구장", "체육도장",
        "마을공회당", "마을공동작업소", "마을공동구판장", "공공시설", "동사무소", "경찰서", "파출소", "소방서", "우체국", "전신전화국", "방송국", "보건소", "공공도서관", "기타공공시설", "일반음식점",
        "휴게음식점", "기원", "서점", "제조업소", "수리점", "게임제공업소", "사진관", "표구점", "학원", "장의사",
        "동물병원", "독서실", "총포판매소", "단란주점", "의약품도매점", "자동차영업소", "안마시술소", "노래연습장", "세탁소", "운동시설", "테니스장", "체력단련장", "에어로빅장", "볼링장", 
        "당구장", "실내낚시터", "골프연습장", "기타운동시설", "종교집회장", "교회", "성당", "사찰", "기타종교집회장", "공연장", "극장(영화관)", "음악당", "연예장", "기타공연장", "사무소", "금융업소",
        "사무소", "부동산중개업소", "결혼상담소", "기타사무소", "기타근린생활시설", "문화및집회시설", "판매및영업시설", "대규모소매점", "기타판매및영업시설", "축사", "가축시설", "교육연구및복지시설",
        "기타교육연구및복지시설", "공공용시설", "교도소", "기타공공용시설"  
    };
  
    
    public String[] mainPurpsCD = new String[] {"코드", "01000", "01001", "01002", "01003", "01004", 
        "02000", "02001", "02002", "02003", "02004", "02005", "02006", "02007", "03000", "03001", "03002", "03003", 
        "03004", "03005", "03006", "03007", "03008", "03009", "03010", "03011", "03012", "03013", "03014", "03015",
        "03016", "03017", "03018", "03019", "03020", "03021", "03022", "03023", "03024", "03025", "03026", "03027",
        "03028", "03029", "03030", "03031", "03100", "03101", "03102", "03103", "03104", "03105", "03106", "03107",
        "03108", "03109", "03110", "03111", "03112", "03113", "03114", "03199", "03999", "04000", "04001", "04002",
        "04003", "04004", "04005", "04006", "04007", "04008", "04009", "04010", "04011", "04012", "04013", "04014",
        "04015", "04016", "04017", "04018", "04019", "04020", "04021", "04022", "04023", "04024", "04025", "04026",
        "04027", "04028", "04029", "04030", "04031", "04032", "04033", "04034", "04035", "04036", "04037", "04038",
        "04039", "04100", "04101", "04102", "04103", "04104", "04105", "04106", "04107", "04108", "04199", "04200",
        "04201", "04202", "04203", "04204", "04205", "04206", "04207", "04208", "04299", "04300", "04301", "04302", 
        "04303", "04304", "04305", "04306", "04307", "04308", "04399", "04400", "04401", "04402", "04403", "04404",
        "04405", "04406", "04499", "04999", "05000", "05100", "05101", "05102", "05103", "05104", "05105", "05106",
        "05107", "05108", "05199", "05200", "05201", "05202", "05203", "05204", "05205", "05299", "05300", "05301",
        "05302", "05303", "05304", "05305", "05306", "05399", "05400", "05401", "05402", "05403", "05404", "05405",
        "05406", "05407", "05408", "05499", "05500", "05501", "05502", "05503", "05599", "05999", "06000", "06100",
        "06101", "06102", "06103", "06104", "06105", "06106", "06107", "06108", "06109", "06110", "06199", "06999",
        "07000", "07001", "07100", "07101", "07102", "07103", "07104", "07105", "07199", "07201", "07202", "07203",
        "07204", "07205", "07206", "07207", "07208", "07209", "07210", "07211", "07212", "07300", "07301", "07302",
        "07399", "07999", "08000", "08001", "08002", "08003", "08004", "08005", "08006", "08007", "08008", "08999",
        "09000", "09100", "09101", "09102", "09103", "09104", "09105", "09106", "09107", "09108", "09109", "09199",
        "09200", "09201", "09202", "09299", "09301", "09999", "10000",
        "10001", "10002", "10003", "10004", "10005", "10100", "10101", "10102", "10103", "10104", "10105", "10106",
        "10107", "10199", "10200", "10201", "10202", "10299", "10300", "10301", "10302", "10303", "10399", "10999",
        "11000", "11100", "11101", "11102", "11103", "11104", "11199", "11201", "11202", "11203", "11999", "12000",
        "12001", "12100", "12101", "12102", "12103", "12104", "12105", "12199", "12200", "12201", "12202", "12203",
        "12299", "12999", "13000", "13001", "13002", "13003", "13004", "13005", "13006", "13007", "13008", "13009",
        "13010", "13011", "13012", "13100", "13101", "13102", "13103", "13104", "13105", "13106", "13107", "13108",
        "13109", "13110", "13999", "14000", "14100", "14101", "14102", "14103", "14199", "14200", "14201", "14202",
        "14203", "14204", "14299", "15000", "15001", "15100", "15101", "15102", "15103", "15199", "15200", "15201",
        "15202", "15203", "15204", "15205", "15299", "15300", "15999", "16000", "16001", "16002", "16003", "16004",
        "16005", "16006", "16007", "16008", "16009", "16010", "16011", "16012", "16013", "16999", "17000", "17100",
        "17200", "17300", "17301", "17302", "17303", "17304", "17305", "17306", "17307", "17308", "17309", "17999",
        "18000", "18001", "18002", "18003", "18004", "18100", "18101", "18102", "18103", "18999", "19000", "19001",
        "19002", "19003", "19004", "19005", "19006", "19007", "19008", "19009", "19010", "19011", "19012", "19013",
        "19014", "19015", "19016", "19017", "19018", "19019", "19020", "19021", "19999", "20000", "20001", "20002",
        "20003", "20004", "20005", "20006", "20007", "20008", "20009", "20010", "20999", "21000", "21001", "21002",
        "21003", "21004", "21005", "21006", "21100", "21101", "21102", "21103", "21104", "21105", "21200", "21201",
        "21202", "21203", "21204", "21205", "21206", "21207", "21299", "21999", "22000", "22001", "22002", "22003",
        "22004", "22005", "22999", "23000", "23001", "23002", "23003", "23004", "23005", "23006", "23007", "23100",
        "23101", "23102", "23103", "23200", "23201", "23202", "23203", "23999", "24000", "24001", "24002", "24003",
        "24004", "24100", "24101", "24102", "24103", "24104", "24105", "24999", "25000", "25001", "25999", "26000",
        "26001", "26002", "26003", "26004", "26005", "26006", "26999", "27000", "27001", "27002", "27003", "27004",
        "27005", "27006", "27007", "27008", "27009", "27999", "28000", "28001", "28002", "28003", "28004", "28005",
        "28006", "28007", "28008", "28009", "28010", "28011", "28012", "28013", "28014", "28015", "28016", "28999",
        "29000", "29001", "Z3000", "Z3001", "Z3002", "Z3003", "Z3004", "Z3005", "Z3006", "Z3007", "Z3008", "Z3009", 
        "Z3010", "Z3011", "Z3012", "Z3014", "Z3015", "Z3016", "Z3017", "Z3018", "Z3019", "Z3020", "Z3021", "Z3022",
        "Z3023", "Z3100", "Z3101", "Z3102", "Z3103", "Z3104", "Z3105", "Z3106", "Z3107", "Z3108", "Z3109", "Z3199",
        "Z3201", "Z3202", "Z3203", "Z3204", "Z3205", "Z3206", "Z3207", "Z3208", "Z3209", "Z3210", "Z3211", "Z3212",
        "Z3214", "Z3215", "Z3216", "Z3217", "Z3218", "Z3219", "Z3220", "Z3221", "Z3300", "Z3301", "Z3302", "Z3303",
        "Z3304", "Z3305", "Z3306", "Z3307", "Z3399", "Z3400", "Z3401", "Z3402", "Z3403", "Z3499", "Z3500", "Z3501",
        "Z3502", "Z3503", "Z3599", "Z3600", "Z3601", "Z3602", "Z3603", "Z3604", "Z3699", "Z3999", "Z5000", "Z6000",
        "Z6205", "Z6999", "Z7001", "Z7002", "Z8000", "Z8999", "Z9000", "Z9001", "Z9999" 
    };
}
