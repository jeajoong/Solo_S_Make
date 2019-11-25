//package com.application.db;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Scanner;
//
//public class JDBCExam
//{
//    Connection connection;
//    Statement statement;
//    ResultSet resultSet;
//
//    String driverName = "oracle.jdbc.driver.OracleDriver";
//    String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
//    String user = "scott";
//    String password = "tiger";
//
//    public JDBCExam()
//    {
//        try
//        {
//            // ① 로드
//            Class.forName(driverName);
//
//        }
//        catch (ClassNotFoundException e)
//        {
//            System.out.println("[로드 오류]\n" + e.getStackTrace());
//        }
//
//    }
//
//    public void closeDatabase()
//    {
//        try
//        {
//            if( connection != null )
//            {
//                // connection 닫기
//                connection.close();
//            }
//
//            if( statement != null )
//            {
//                // statement 닫기
//                statement.close();
//            }
//
//            if( resultSet != null )
//            {
//                // resultSet 닫기
//                resultSet.close();
//            }
//        }
//        catch (SQLException e)
//        {
//            System.out.println("[닫기 오류]\n" + e.getStackTrace());
//        }
//    }
//
//    public int productInsert(int p_no, String p_name, int p_price, String p_detail)
//    {
//        int resultValue = 0;
//
//        try
//        {
//            String queryString = "INSERT INTO product VALUES (" + p_no + ", '" + p_name + "', " + p_price + ", '" + p_detail + "')";
//
//            // ② 연결 [Connection]
//            connection = DriverManager.getConnection(url, user, password);
//
//            // ② 연결 [Statement]
//            statement = connection.createStatement();
//
//            // ③ 실행 [CRUD]
//            resultValue = statement.executeUpdate(queryString);
//        }
//        catch (SQLException e)
//        {
//            System.out.println("[쿼리 오류]\n" + e.getStackTrace());
//        }
//        finally
//        {
//            // ④ 닫기
//            closeDatabase();
//        }
//
//        return resultValue;
//
//    }
//
//    public void productSelectAll()
//    {
//
//        try
//        {
//            String queryString = "SELECT * FROM product";
//
//            // ② 연결 [Connection]
//            connection = DriverManager.getConnection(url, user, password);
//
//            // ② 연결 [Statement]
//            statement = connection.createStatement();
//            
//            // ③ 실행 [CRUD]
//            resultSet = statement.executeQuery(queryString);
//            
//            // 컬럼 정보 가져오기
//            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//
//            // 컬럼 출력
//            System.out.println(resultSetMetaData.getColumnName(1) + "\t" + resultSetMetaData.getColumnName(2) + "\t" + resultSetMetaData.getColumnName(3) + "\t" + resultSetMetaData.getColumnName(4));
//
//            while (resultSet.next())
//            {
//                System.out.println(resultSet.getInt("p_no") + "\t" + resultSet.getString("p_name") + "\t" + resultSet.getInt("p_price") + "\t" + resultSet.getString("p_detail"));
//            }
//        }
//        catch (SQLException e)
//        {
//            System.out.println("[쿼리 오류]\n" + e.getStackTrace());
//        }
//        finally
//        {
//            // ④ 닫기
//            closeDatabase();
//        }
//
//    }
//    
//    public int productRemove(int p_no)
//    {
//        int resultValue = 0;
//        
//        try
//        {
//            String queryString = "DELETE FROM product WHERE p_no=" + p_no;
//
//            // ② 연결 [Connection]
//            connection = DriverManager.getConnection(url, user, password);
//
//            // ② 연결 [Statement]
//            statement = connection.createStatement();
//
//            // ③ 실행 [CRUD]
//            resultValue = statement.executeUpdate(queryString);
//        }
//        catch (SQLException e)
//        {
//            System.out.println("[쿼리 오류]\n" + e.getStackTrace());
//        }
//        finally
//        {
//            // ④ 닫기
//            closeDatabase();
//        }
//        
//        return resultValue;
//    }
//    
//    
//    public int productUpdate(int p_no, int p_price, String p_detail)
//    {
//        int resultValue = 0;
//
//        try
//        {
//            String queryString = "UPDATE product SET p_price=" + p_price + ", p_detail='" + p_detail + "' WHERE p_no=" + p_no; 
//
//            // ② 연결 [Connection]
//            connection = DriverManager.getConnection(url, user, password);
//
//            // ② 연결 [Statement]
//            statement = connection.createStatement();
//
//            // ③ 실행 [CRUD]
//            resultValue = statement.executeUpdate(queryString);
//        }
//        catch (SQLException e)
//        {
//            System.out.println("[쿼리 오류]\n" + e.getStackTrace());
//        }
//        finally
//        {
//            // ④ 닫기
//            closeDatabase();
//        }
//
//        return resultValue;
//
//    }
//    
//    public void productSelectOne(int p_no)
//    {
//
//        try
//        {
//            String queryString = "SELECT * FROM product WHERE p_no=" + p_no;
//
//            // ② 연결 [Connection]
//            connection = DriverManager.getConnection(url, user, password);
//
//            // ② 연결 [Statement]
//            statement = connection.createStatement();
//
//            // ③ 실행 [CRUD]
//            resultSet = statement.executeQuery(queryString);
//
//            System.out.println("p_no" + "\t" + "p_name" + "\t" + "p_price" + "\t" + "p_detail");
//
//            while (resultSet.next())
//            {
//                System.out.println(resultSet.getInt("p_no") + "\t" + resultSet.getString("p_name") + "\t" + resultSet.getInt("p_price") + resultSet.getString("p_detail"));
//            }
//        }
//        catch (SQLException e)
//        {
//            System.out.println("[쿼리 오류]\n" + e.getStackTrace());
//        }
//        finally
//        {
//            // ④ 닫기
//            closeDatabase();
//        }
//
//    }
//    
//    public void productSearch(String SearchKeyword)
//    {
//
//        try
//        {
//            String queryString = "SELECT * FROM product WHERE p_detail LIKE '%" + SearchKeyword + "%'";
//
//            // ② 연결 [Connection]
//            connection = DriverManager.getConnection(url, user, password);
//
//            // ② 연결 [Statement]
//            statement = connection.createStatement();
//
//            // ③ 실행 [CRUD]
//            resultSet = statement.executeQuery(queryString);
//
//            System.out.println("p_no" + "\t" + "p_name" + "\t" + "p_price" + "\t" + "p_detail");
//
//            while (resultSet.next())
//            {
//                System.out.println(resultSet.getInt("p_no") + "\t" + resultSet.getString("p_name") + "\t" + resultSet.getInt("p_price") + "\t" + resultSet.getString("p_detail"));
//            }
//        }
//        catch (SQLException e)
//        {
//            System.out.println("[쿼리 오류]\n" + e.getStackTrace());
//        }
//        finally
//        {
//            // ④ 닫기
//            closeDatabase();
//        }
//
//    }
//    
//
//    public static void main(String[] args)
//    {
//        JDBCExam jdbcExam = new JDBCExam();
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("번호");
//        int p_no = Integer.valueOf(scanner.nextLine());
//
//        System.out.print("이름");
//        String p_name = scanner.nextLine();
//
//        System.out.print("가격");
//        int p_price = Integer.valueOf(scanner.nextLine());
//
//        System.out.print("내용");
//        String p_detail = scanner.nextLine();
//
//        System.out.println("\n\n" + jdbcExam.productInsert(p_no, p_name, p_price, p_detail) + "개의 물품을 추가했습니다.");
//
//        System.out.println("\n\n" + jdbcExam.productRemove(3) + "개의 물품을 삭제했습니다.");
//        System.out.println("\n\n" + jdbcExam.productUpdate(4, 5000, "수정완료!!") + "개의 물품을 수정했습니다.");
//        
//        jdbcExam.productSelectAll();
//        jdbcExam.productSelectOne(4);
//        jdbcExam.productSearch("비싸");
//    }
//}