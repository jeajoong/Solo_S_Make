package com.application.NOTUSEDdao;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class AddressDao {
  private SqlSessionFactory factory;
  private static final String ADDRNAMESPACE = "com.resource.AddressDao."; 
  
  public AddressDao() {
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
  
  
  // 시군구 명을 찾기위한 메서드
  public List<Object> findSigunguNM(String sidoNM) {
    SqlSession sqlSession = factory.openSession();
    List<Object> sigunguList = sqlSession.selectList(ADDRNAMESPACE+"findSigunguNM", sidoNM);

    // 돌려받은 값에 null이 포함되어 있으면 제거.
    sigunguList.removeAll(Collections.singleton(null));
    
    return sigunguList;
  }

  
  // 해당하는 시도의 시군구의 법정동을 찾기위한 메서드 (예외상황 처리x)
  public List<Object> findBjdongNM(String sidoSelect, String firstSigunguName) {
    Map<String, Object> sidoSigunguMap = new HashMap<String, Object>();
    sidoSigunguMap.put("sidoSelect", sidoSelect);
    sidoSigunguMap.put("firstSigunguName", firstSigunguName);

    SqlSession sqlSession = factory.openSession();
    List<Object> bjdongList = sqlSession.selectList(ADDRNAMESPACE+"findBjdongNM", sidoSigunguMap);
    
    return bjdongList;
  }

  // 시군구 코드를 찾기위한 메서드 (예외상황 처리 x)
  public String findSigunguCD(String sidoNM, String sigunguNM) {
    Map<String, Object> sidoSigunguMap2 = new HashMap<String, Object>();
    sidoSigunguMap2.put("sidoNM", sidoNM);
    sidoSigunguMap2.put("sigunguNM", sigunguNM);
    
    SqlSession sqlSession = factory.openSession();
    String sigunguCD = sqlSession.selectOne(ADDRNAMESPACE+"findSigunguCD", sidoSigunguMap2);
    
    return sigunguCD;
  }

  // 법정동 코드를 찾기위한 메서드 (예외상황 처리 x)
  public String findBjdongCD(String sidoNM, String sigunguNM, String bjdongNM) {
    Map<String, Object> sidoSigunguMap3 = new HashMap<String, Object>();
    sidoSigunguMap3.put("sidoNM", sidoNM);
    sidoSigunguMap3.put("sigunguNM", sigunguNM);
    sidoSigunguMap3.put("bjdongNM", bjdongNM);
    
    SqlSession sqlSession = factory.openSession();
    String bjdongCD = sqlSession.selectOne(ADDRNAMESPACE+"findBjdongCD", sidoSigunguMap3);
    
    return bjdongCD;
    
  }
  
  
  
  
}
