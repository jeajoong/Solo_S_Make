package com.application.db;

public class FindIP {
  // 사업단 내 환경은 250개의 시군구가 모두 8대에 나뉘어져 있었지만, 현재 상황은 그렇지 않으므로
	// 프로그램을 구동하는 IP한개값만 적어둠(로컬).
  public String findCom(String sigunguNum) {
//    int sigunguCD = Integer.parseInt(sigunguNum); 
    // 시군구 CD 값으로 IP정보 돌려줌.
//    if( sigunguCD == 11110 || sigunguCD == 11140 || sigunguCD == 11170 ||  
//        sigunguCD == 11200 || sigunguCD == 11215 || sigunguCD == 28110 ||  
//        sigunguCD == 28140 || sigunguCD == 28177 || sigunguCD == 41111 ||  
//        sigunguCD == 41113 || sigunguCD == 41115 || sigunguCD == 41117 ||  
//        sigunguCD == 41150 || sigunguCD == 41171 || sigunguCD == 41173 ||  
//        sigunguCD == 46110 || sigunguCD == 46130 || sigunguCD == 46150 ||  
//        sigunguCD == 46170 || sigunguCD == 48170 || sigunguCD == 48220 ||  
//        sigunguCD == 48240 || sigunguCD == 48250 || sigunguCD == 26410 ||
//        sigunguCD == 26440 || sigunguCD == 26470 || sigunguCD == 26500 ||
//        sigunguCD == 26530 || sigunguCD == 26710) {
//      String comIP = null;
//      comIP = "11.10.51.161";
//      return comIP;
//    }
//    if( sigunguCD == 11230 || sigunguCD == 11260 || sigunguCD == 11290 ||
//        sigunguCD == 11305 || sigunguCD == 11320 || sigunguCD == 28185 ||  
//        sigunguCD == 28200 || sigunguCD == 28237 || sigunguCD == 41131 ||  
//        sigunguCD == 41133 || sigunguCD == 41135 || sigunguCD == 41190 ||  
//        sigunguCD == 41210 || sigunguCD == 41220 || sigunguCD == 41250 ||  
//        sigunguCD == 46230 || sigunguCD == 46710 || sigunguCD == 46720 ||  
//        sigunguCD == 46730 || sigunguCD == 48270 || sigunguCD == 48310 ||  
//        sigunguCD == 48330 || sigunguCD == 48720 || sigunguCD == 27110 ||
//        sigunguCD == 27140 || sigunguCD == 27170 || sigunguCD == 27200 ||
//        sigunguCD == 27230 || sigunguCD == 27260 || sigunguCD == 27290 ||
//        sigunguCD == 27710) {
//      String comIP = null;
//      comIP = "11.10.51.162";
//      return comIP;
//    }
//    if( sigunguCD == 11350 || sigunguCD == 11380 || sigunguCD == 11410 ||  
//        sigunguCD == 11440 || sigunguCD == 11470 || sigunguCD == 28245 ||  
//        sigunguCD == 28260 || sigunguCD == 28710 || sigunguCD == 28720 ||  
//        sigunguCD == 41271 || sigunguCD == 41273 || sigunguCD == 41290 ||  
//        sigunguCD == 41310 || sigunguCD == 41360 || sigunguCD == 41461 ||  
//        sigunguCD == 41463 || sigunguCD == 41465 || sigunguCD == 46770 ||  
//        sigunguCD == 46780 || sigunguCD == 46790 || sigunguCD == 46800 ||  
//        sigunguCD == 48730 || sigunguCD == 48740 || sigunguCD == 48820 ||  
//        sigunguCD == 48840 || sigunguCD == 48850 || sigunguCD == 31110 ||
//        sigunguCD == 31140 || sigunguCD == 31170 || sigunguCD == 31200 ||
//        sigunguCD == 31710) {
//      String comIP = null;
//      comIP = "11.10.51.163";
//      return comIP;
//    }
//    if( sigunguCD == 11500 || sigunguCD == 11530 || sigunguCD == 11545 ||  
//        sigunguCD == 11560 || sigunguCD == 11590 || sigunguCD == 29110 ||  
//        sigunguCD == 29140 || sigunguCD == 29155 || sigunguCD == 41281 ||  
//        sigunguCD == 41285 || sigunguCD == 41287 || sigunguCD == 41370 ||  
//        sigunguCD == 41390 || sigunguCD == 41410 || sigunguCD == 41430 ||  
//        sigunguCD == 41450 || sigunguCD == 41480 || sigunguCD == 46810 ||  
//        sigunguCD == 46820 || sigunguCD == 46830 || sigunguCD == 46840 ||  
//        sigunguCD == 46860 || sigunguCD == 46870 || sigunguCD == 48860 ||  
//        sigunguCD == 48870 || sigunguCD == 48880 || sigunguCD == 48890 ||
//        sigunguCD == 42770 || sigunguCD == 42780 || sigunguCD == 42790 ||
//        sigunguCD == 42800 || sigunguCD == 42810 || sigunguCD == 42820 ||
//        sigunguCD == 42830 || sigunguCD == 36110) {
//      String comIP = null;
//      comIP = "11.10.51.164";
//      return comIP;
//    }
//    if (sigunguCD == 11620 || sigunguCD == 11650 || sigunguCD == 11680 ||  
//        sigunguCD == 11710 || sigunguCD == 11740 || sigunguCD == 29170 ||  
//        sigunguCD == 29200 || sigunguCD == 41500 || sigunguCD == 41550 ||  
//        sigunguCD == 41570 || sigunguCD == 41590 || sigunguCD == 41610 ||  
//        sigunguCD == 41630 || sigunguCD == 41650 || sigunguCD == 41670 ||  
//        sigunguCD == 41800 || sigunguCD == 41820 || sigunguCD == 41830 ||  
//        sigunguCD == 46880 || sigunguCD == 46890 || sigunguCD == 46900 ||  
//        sigunguCD == 46910 || sigunguCD == 43111 || sigunguCD == 43112 ||
//        sigunguCD == 43113 || sigunguCD == 43114 || sigunguCD == 43760 ||
//        sigunguCD == 43770 || sigunguCD == 43800 || sigunguCD == 43130 ||
//        sigunguCD == 43150 || sigunguCD == 43720 || sigunguCD == 43730 ||
//        sigunguCD == 43740 || sigunguCD == 43745 || sigunguCD == 43750 ||
//        sigunguCD == 44770 || sigunguCD == 44790 || sigunguCD == 44800 ||
//        sigunguCD == 44810 || sigunguCD == 44825) {
//      String comIP = null;
//      comIP = "11.10.51.165";
//      return comIP;
//    }
//    if (sigunguCD == 26110 || sigunguCD == 26140 || sigunguCD == 26170 ||  
//        sigunguCD == 26200 || sigunguCD == 26230 || sigunguCD == 30110 ||  
//        sigunguCD == 30140 || sigunguCD == 30170 || sigunguCD == 42110 ||  
//        sigunguCD == 42130 || sigunguCD == 42150 || sigunguCD == 42170 ||  
//        sigunguCD == 42190 || sigunguCD == 44131 || sigunguCD == 44133 ||  
//        sigunguCD == 44150 || sigunguCD == 44180 || sigunguCD == 44200 ||  
//        sigunguCD == 47111 || sigunguCD == 47113 || sigunguCD == 47130 ||  
//        sigunguCD == 47150 || sigunguCD == 47170 || sigunguCD == 50130 ||
//        sigunguCD == 45111 || sigunguCD == 45113 || sigunguCD == 45130 ||
//        sigunguCD == 45140 || sigunguCD == 45180 || sigunguCD == 45190 ||
//        sigunguCD == 45210 || sigunguCD == 45710 || sigunguCD == 45720 ||
//        sigunguCD == 45730 || sigunguCD == 45740 || sigunguCD == 45750 ||
//        sigunguCD == 45770 || sigunguCD == 45790 || sigunguCD == 45800 ||
//        sigunguCD == 50110) {
//      String comIP = null;
//      comIP = "11.10.51.166";
//      return comIP;
//    }
//    if( sigunguCD == 26260 || sigunguCD == 26290 || sigunguCD == 26320 ||  
//        sigunguCD == 26350 || sigunguCD == 26380 || sigunguCD == 30200 ||  
//        sigunguCD == 30230 || sigunguCD == 42210 || sigunguCD == 42230 ||  
//        sigunguCD == 42720 || sigunguCD == 42730 || sigunguCD == 42750 ||  
//        sigunguCD == 42760 || sigunguCD == 44210 || sigunguCD == 44230 ||  
//        sigunguCD == 44250 || sigunguCD == 44270 || sigunguCD == 44710 ||  
//        sigunguCD == 44760 || sigunguCD == 47190 || sigunguCD == 47210 ||  
//        sigunguCD == 47230 || sigunguCD == 47250 || sigunguCD == 47280 ||  
//        sigunguCD == 47290 || sigunguCD == 47720 || sigunguCD == 47730 ||
//        sigunguCD == 47750 || sigunguCD == 47760 || sigunguCD == 47770 ||
//        sigunguCD == 47820 || sigunguCD == 47830 || sigunguCD == 47840 ||
//        sigunguCD == 47850 || sigunguCD == 47900 || sigunguCD == 47920 ||
//        sigunguCD == 47930 || sigunguCD == 47940 || sigunguCD == 48121 ||
//        sigunguCD == 48123 || sigunguCD == 48125 || sigunguCD == 48127 ||
//        sigunguCD == 48129) {
//      String comIP = null;
//      comIP = "11.10.51.167";
//      return comIP;
//    }
//    
//    else {
    String comIP = "192.168.0.11";
     return comIP;
//     return null;
//    }
    
  }
  
}

