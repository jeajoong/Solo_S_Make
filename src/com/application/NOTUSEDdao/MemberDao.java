package com.application.NOTUSEDdao;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.application.vo.Member;

public class MemberDao{
  private SqlSessionFactory factory;
  private static final String MEMBERNAMESPACE = "com.resource.MemberDao."; 
  
  public MemberDao() {
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
  
  
  public Member login(String id, String pwd) {
    SqlSession sqlSession = factory.openSession();
    
   Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    map.put("pwd", pwd);
    
    Member memberInfo= sqlSession.selectOne(MEMBERNAMESPACE+"selectOneMember", map);
  System.out.println(memberInfo);
    
    
    return memberInfo;
  }
  
  
  

}
