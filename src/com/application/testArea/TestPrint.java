package com.application.testArea;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

public class TestPrint {
  
  public TestPrint(){
    // 기본 프린터를 찾아 service에 넣는다.
    PrintService service = PrintServiceLookup.lookupDefaultPrintService();
    
    // 새로운 프린터 작업
    DocPrintJob job = service.createPrintJob();
    
    String commands = "안녕";
    DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
    
    Doc doc = new SimpleDoc(commands.getBytes(), flavor, null);
  }

  public static void main(String[] args) {
    new TestPrint();
  }
  
  
}
