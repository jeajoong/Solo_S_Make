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
import com.application.vo.NOTUSEDBuild;

public class EntirDao {
// 아직 사용하지 않음
  
  
  
  private SqlSessionFactory factory;
  private static final String ENTIRNAMESPACE = "com.resource.EntirDao."; 
  
  public EntirDao() {
      try {
          Reader reader = Resources.getResourceAsReader("com/resource/mybatis-config.xml");
          factory = new SqlSessionFactoryBuilder().build(reader);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  
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
  
  
  //해당하는 전체주소와 동일한 값들을 찾는 메소드
  public List<NOTUSEDBuild> searchAddress(String sidoNM, 
      String sigunguNM, String bjdongNM,
      String bunText, String jiText) {
    
    Map<String, Object> searchBldList = new HashMap<String, Object>();
    searchBldList.put("sidoNM", sidoNM);
    searchBldList.put("sigunguNM", sigunguNM);
    searchBldList.put("bjdongNM", bjdongNM);
    searchBldList.put("bunText", bunText);
    searchBldList.put("jiText", jiText);
    
    SqlSession sqlSession = factory.openSession();
    List<NOTUSEDBuild> bldList = sqlSession.selectList(ENTIRNAMESPACE+"searchAddress", searchBldList);
    
    return bldList;
  }
  
}
