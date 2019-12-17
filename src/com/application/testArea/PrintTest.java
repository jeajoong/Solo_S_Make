package com.application.testArea;

import java.awt.Desktop;
import java.io.File;

public class PrintTest {
  public static void main(String args[]) {
    Desktop desktop = null;
    try {
      if(Desktop.isDesktopSupported()) {
        desktop = Desktop.getDesktop();
      }
      desktop.print(new File("C:\\Users\\seoin_01\\Desktop\\pdf\\일반건축물대장.xlsx"));
      
//      PDFPrint pdfPrint = new PDFPrint("C:\\Users\\seoin_01\\Desktop\\pdf\\test.pdf",null);
//      pdfPrint.printToDefaultPrinter(new PrintSettings());
    } catch (Exception e) {
    }
  }
  
}
