package com.application.app;

import com.qoppa.pdf.PrintSettings;
import com.qoppa.pdfPrint.PDFPrint;

public class PrintTest {
  public static void main(String[] args) {
    try {
      PDFPrint pdfPrint = new PDFPrint("peftest.pdf",null);
      pdfPrint.printToDefaultPrinter(new PrintSettings());
      //pdfPrint.print(new PrintSettings());
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
