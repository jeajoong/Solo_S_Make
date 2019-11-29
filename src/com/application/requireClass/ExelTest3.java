package com.application.requireClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExelTest3 {

    public static void main(String[] args) throws IOException {
      
      FileInputStream fis = new FileInputStream("C:/Users/seoin_01/Desktop/project/Origin_xlxs/일반건축물대장.xlsx");
      Workbook workbook = new XSSFWorkbook(fis);
      
//      CellStyle tableStyle1 = workbook.createCellStyle(); //THICK가 매우 굵은 줄 
//        tableStyle1.setBorderBottom(BorderStyle.THICK); 
//        tableStyle1.setBorderLeft(BorderStyle.THICK); 
      
      Sheet sheet = workbook.getSheetAt(1); // 시트가 1개일때 getSheetAt(0);
      
      sheet.getRow(4).getCell(2).setCellValue("고유번호테스트");
      sheet.getRow(4).getCell(9).setCellValue("명칭테스트");
      sheet.getRow(4).getCell(11).setCellValue("특이사항테스트");
      
      sheet.getRow(7).getCell(2).setCellValue("대지위치테스트");
      sheet.getRow(7).getCell(6).setCellValue("지번테스트");
      sheet.getRow(7).getCell(9).setCellValue("도로명주소");
      
      sheet.getRow(9).getCell(2).setCellValue("대지면적테스트");
      sheet.getRow(9).getCell(4).setCellValue("연면적테스트");
      sheet.getRow(9).getCell(6).setCellValue("지역테스트");
      sheet.getRow(9).getCell(7).setCellValue("지구테스트");
      sheet.getRow(9).getCell(9).setCellValue("구역테스트");
      
      sheet.getRow(11).getCell(2).setCellValue("건축면적테스트");
      sheet.getRow(11).getCell(4).setCellValue("용적률 산정용 연면적 테스트");
      sheet.getRow(11).getCell(6).setCellValue("주구조 테스트");
      sheet.getRow(11).getCell(7).setCellValue("주용도");
      sheet.getRow(11).getCell(10).setCellValue("123");
      sheet.getRow(11).getCell(12).setCellValue("321");
      
      sheet.getRow(13).getCell(2).setCellValue("건폐율 테스트");
      sheet.getRow(13).getCell(4).setCellValue("용적률 테스트");
      sheet.getRow(13).getCell(6).setCellValue("높이 테스트");
      sheet.getRow(13).getCell(7).setCellValue("지붕 테스트");
      sheet.getRow(13).getCell(9).setCellValue("부속건축물테스트");
      
      sheet.getRow(16).getCell(2).setCellValue("공간 면적 합계 테스트");
      sheet.getRow(16).getCell(4).setCellValue("공개 공지 면적 테스트");
      sheet.getRow(16).getCell(6).setCellValue("쌈지 공원 면적 테스트");
      sheet.getRow(16).getCell(7).setCellValue("공공보행통로 테스트");
      sheet.getRow(16).getCell(9).setCellValue("건축선 후퇴면적 테스트");
      sheet.getRow(16).getCell(11).setCellValue("그 밖의 면적 테스트");
      
      for (int i = 21; i < 28; i++) { // 여기서 한페이지! 층별 배열 사이즈까지  (최대7개)
        sheet.getRow(i).getCell(2).setCellValue("구분 테스트");
        sheet.getRow(i).getCell(3).setCellValue("층별 테스트");
        sheet.getRow(i).getCell(4).setCellValue("구조 테스트");
        sheet.getRow(i).getCell(5).setCellValue("용도 테스트");
        sheet.getRow(i).getCell(6).setCellValue("면적 테스트");
      }
      
      for(int i = 21; i< 27; i++) { // 21 23 25가 들어가야 함
        sheet.getRow(i / 2 * 2 - 1).getCell(7).setCellValue("성명테스트");
        sheet.getRow(i).getCell(9).setCellValue("소유권 지분테스트");
      }
      
      
      // 층별 배열 사이즈가 7개가 넘을때 시트를 추가하여 거기에 추가하기.
      // 그리고 나서 엑셀을 프린트 할때 조건도 층별 배열 사이즈가 7개가 넘으면 두번째 시트까지 출력 아니면 1장만 출력되게 하면 됨.
      // 소유자 현황도 마찬가지로 .
      try {
        File xlsFile = new File("C:/Users/seoin_01/Desktop/project/src/com/resource/일반건축물대장.xlsx");
        FileOutputStream fileOut = new FileOutputStream(xlsFile);
        workbook.write(fileOut);
        System.out.println("파일저장 완료");
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    
    }
    

}




