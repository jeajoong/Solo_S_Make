package com.application.NOTUSEDdao;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.application.vo.BuildInfo;

public class BuildInfoDao {
  
  private SqlSessionFactory factory;
  private static final String BUILDINGNAMESPACE = "com.resource.BuildInfoDao."; 
  
  public BuildInfoDao() {
      try {
          Reader reader = Resources.getResourceAsReader("com/resource/mybatis-config.xml");
          factory = new SqlSessionFactoryBuilder().build(reader);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  
  // SqlSessionFactory 설정 메서드
  public SqlSessionFactory getSqlSessionFactory() {
    if(factory !=null) {
   // 페이지가 변경될때마다 설정파일을 불러오는 작업을 하지않기 위해 선언
   return factory;
   }
   InputStream is = null;
   try {
   is = Resources.getResourceAsStream("com/resource/mybatis-config.xml"); // 설정파일 불러오기
   } catch (IOException e) {
   e.printStackTrace();
   }
   SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder(); // 설정파일을 읽기 위한 객체
   factory= builder.build(is);
   return factory;
   }

  // 건축물대장 리스트 찾는 메서드
  public List<BuildInfo> findBuildList(String sigunguCD, String bjdongCD, String bunText, String jiText) {
    SqlSession sqlSession = factory.openSession();
    Map<String, Object> cdAndBunJiMap= new HashMap<String, Object>();
    cdAndBunJiMap.put("sigunguCD", sigunguCD);
    cdAndBunJiMap.put("bjdongCD", bjdongCD);
    cdAndBunJiMap.put("bunText", bunText);
    cdAndBunJiMap.put("jiText", jiText);
    
    System.out.println("시군구 코드는 : " + sigunguCD);
    System.out.println("법정동 코드는 : " + bjdongCD);
    System.out.println("번 : " + bunText);
    System.out.println("지 : " + jiText);
    
    
    List<BuildInfo> buildingList = sqlSession.selectList(BUILDINGNAMESPACE+"findBuildList", cdAndBunJiMap);
//    List<String> trimList = new ArrayList<>();
//      for (int i = 0; i < buildingList.size(); i++) {
//        String str = 
//                 buildingList.get(i).getBldTypeGBCD()
//                +buildingList.get(i).getBuildingPK()
//                +buildingList.get(i).getRegstrGBCD()
//                +buildingList.get(i).getRegstrKINKCD()
//                +buildingList.get(i).getSidoNM()
//                +buildingList.get(i).getSigunguNM()
//                +buildingList.get(i).getBjdongNM()
//                +buildingList.get(i).getBunNum()
//                +buildingList.get(i).getJiNum()
//                +buildingList.get(i).getBldNM()
//                +buildingList.get(i).getEtcPurps()
//                +buildingList.get(i).getEtcStruct();
//        trimList.add(str);
//      }
    
//    Object[] buildingListArray =  buildingList.toArray(new Object[buildingList.size()]); 
    
    return buildingList;
  }
  
  
  
}