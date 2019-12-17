package com.application.app;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.application.db.DBBuildDetail;
import com.application.dto.Build;
import com.application.dto.BuildInfo;
import com.application.dto.ExposArea;
import com.application.dto.Floor;
import com.application.dto.Member;
import com.application.dto.Owner;
import com.application.requireClass.MultiLineHeaderRenderer;
import keeptoo.KGradientPanel;

// DetailForm4 => 전유부 대장종류 (번호) : 4
// 각 항목 변수이름 지정해주지 않음
// 조회 가능
@SuppressWarnings("serial")
public class DetailForm4 extends JFrame implements ActionListener{
  
  MainProcess main;
  BuildInfo buildInfo;
  Build build;
  SearchBuild searchPage;
  Member member;
  DetailForm4 detailForm4;
  DBBuildDetail dbBuildDetail = new DBBuildDetail();
  
  public DetailForm4() {
    dtlform();
    returnBtn.addActionListener(this);
    reviseBtn.addActionListener(this);
    printBtn.addActionListener(this);
    
    setVisible(true);
  }
  
  JButton returnBtn = new JButton(); // 돌아가기 버튼(mainpage로)
  JButton reviseBtn = new JButton(); // 수정하기 버튼
  JButton printBtn = new JButton(); // 출력버튼
  
  JLabel jLabel1 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField5 = new JTextField();
  JLabel jLabel21 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel29 = new JLabel();
  JTextField jTextField6 = new JTextField();
  JTextField jTextField7 = new JTextField();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel30 = new JLabel();

  KGradientPanel kGradientPanel1 = new KGradientPanel();
  
  // 전유부분 테이블 
  DefaultTableModel model1 = new DefaultTableModel();
  JScrollPane jScrollPane1 = new JScrollPane(); 
  JTable jTable1 = new JTable(model1); 

  // 공용부분 테이블
  DefaultTableModel model2 = new DefaultTableModel();
  JScrollPane jScrollPane2 = new JScrollPane();
  JTable jTable2 = new JTable(model2);
  
  // 소유자 현황 테이블
  DefaultTableModel model3 = new DefaultTableModel();
  JScrollPane jScrollPane3 = new JScrollPane(); 
  JTable jTable3 = new JTable(model3);
  
  private void dtlform() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    returnBtn.setText("돌아가기");              // (리스트) 돌아가기 버튼
    reviseBtn.setText("수정하기");               // 수정하기 버튼
    printBtn.setText("엑셀저장 후 인쇄");          // 인쇄하기 버튼
    
    
    jLabel1.setText("해당 주소의 전체주소 ");        // 상단 전체주소 라벨
    jLabel21.setText("집합건축물대장(전유부)");
    
    jLabel5.setText("대장_일련번호");            // 대장 일련번호 라벨
    jTextField5.setText("대장_일련번호");        // 대장 일련번호 텍스트구역
    jTextField5.setEnabled(false);

    jLabel2.setText("명칭");                  // 명칭 라벨
    jTextField6.setText("명칭");              // 명칭 텍스트구역
    jLabel29.setText("호 수");                // 호 수 텍스트구역
    jTextField7.setText("호 수");             // 호 수 필드구역
    jLabel6.setText("대지위치");               // 대지위치 라벨구역
    jTextField1.setText("대지위치");           // 대지위치 텍스트구역
    jTextField1.setEnabled(false);
    
    jLabel7.setText("지번");                  // 지번 라벨
    jTextField2.setText("지번");              // 지번 텍스트구역
    jTextField2.setEnabled(false);

    jLabel8.setText("도로명주소");              // 도로명주소 라벨
    jTextField3.setText("도로명주소");          // 도로명주소 텍스트구역
    jTextField3.setEnabled(false);
    
    jLabel3.setText("전유부분");              // 전유부분 라벨
    jLabel4.setText("공용부분");              // 공용부분 라벨
    jLabel30.setText("소유자현황");            // 소유자현황 라벨
    
    jLabel21.setFont(new Font("굴림", 1, 15)); 
    
      jScrollPane1.setViewportView(jTable1);
      jScrollPane2.setViewportView(jTable2);
      jScrollPane3.setViewportView(jTable3);
      
      add(jScrollPane1,BorderLayout.CENTER);
      add(jScrollPane2,BorderLayout.CENTER);
      add(jScrollPane3,BorderLayout.CENTER);
      
      jTable1.setRowHeight(25);
      
      model1.addColumn("구분");
      model1.addColumn("층별");
      model1.addColumn("구조");
      model1.addColumn("용도");
      model1.addColumn("면적(㎡)");
      
      model2.addColumn("구분");
      model2.addColumn("층별");
      model2.addColumn("구조");
      model2.addColumn("용도");
      model2.addColumn("면적(㎡)");
    
      model3.addColumn("성명(명칭)\n 주민등록번호 \n (부동산등기용등록번호)");
      model3.addColumn("\n\n\n\n\n\n\n\n\n주소");
      model3.addColumn("\n\n\n\n\n\n\n\n소유권지분");
      model3.addColumn("\n\n\n\n변동일 \n 변동원인");
      
      jTable3.getColumn("성명(명칭)\n 주민등록번호 \n (부동산등기용등록번호)").setPreferredWidth(100);
      jTable3.getColumn("\n\n\n\n\n\n\n\n\n주소").setPreferredWidth(100);
      jTable3.getColumn("\n\n\n\n\n\n\n\n소유권지분").setPreferredWidth(18);
    
      jTable1.getColumn("구분").setPreferredWidth(10);
      jTable1.getColumn("층별").setPreferredWidth(10);
      jTable1.getColumn("구조").setPreferredWidth(60);
      jTable1.getColumn("용도").setPreferredWidth(120);
      jTable1.getColumn("면적(㎡)").setPreferredWidth(10);
      
      jTable2.getColumn("구분").setPreferredWidth(10);
      jTable2.getColumn("층별").setPreferredWidth(10);
      jTable2.getColumn("구조").setPreferredWidth(60);
      jTable2.getColumn("용도").setPreferredWidth(120);
      jTable2.getColumn("면적(㎡)").setPreferredWidth(10);
    
      DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
      centerRenderer.setHorizontalAlignment(JLabel.CENTER);
      jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
      jTable1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
      jTable2.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
      jTable2.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
      jTable3.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
      jTable1.setAutoCreateRowSorter(true); // 정렬 기능 추가.
      jTable2.setAutoCreateRowSorter(true); // 정렬 기능 추가.
      jTable3.setAutoCreateRowSorter(true); // 정렬 기능 추가.
      
      
    MultiLineHeaderRenderer renderer = new MultiLineHeaderRenderer();
    Enumeration e = jTable3.getColumnModel().getColumns();
    
    while (e.hasMoreElements()) {
      ((TableColumn) e.nextElement()).setHeaderRenderer(renderer);
    }
    
    getContentPane().add(jScrollPane3);
    add(jScrollPane3,BorderLayout.CENTER);
    
    jTable1.getTableHeader().setReorderingAllowed(false);
    jTable2.getTableHeader().setReorderingAllowed(false);
    jTable3.getTableHeader().setReorderingAllowed(false); // 테이블 컬럼 움직이지 않게
    
    jTable3.setRowHeight(38);
    
    
    kGradientPanel1.setkEndColor(new Color(255, 255, 153));
    kGradientPanel1.setkStartColor(new Color(0, 153, 153));
    
    // 디자인 설정구간
    javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
    kGradientPanel1.setLayout(kGradientPanel1Layout);
    kGradientPanel1Layout.setHorizontalGroup(
        kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 1100, Short.MAX_VALUE)
    );
    kGradientPanel1Layout.setVerticalGroup(
        kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 600, Short.MAX_VALUE)
    );
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel21)
            .addGap(476, 476, 476))
        .addGroup(layout.createSequentialGroup()
            .addGap(40, 40, 40)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
                            .addGap(29, 29, 29)
                            .addComponent(jLabel2))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel7)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField3))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jTextField6)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel29)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField7, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(224, 224, 224)
                            .addComponent(jLabel4))
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                        .addComponent(jScrollPane2))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel30)
                            .addGap(274, 274, 274))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46))))))
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(110, 110, 110)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 710, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(returnBtn)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(reviseBtn)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(printBtn))
                .addGroup(layout.createSequentialGroup()
                    .addGap(259, 259, 259)
                    .addComponent(jLabel3)))
            .addContainerGap(45, Short.MAX_VALUE))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(31, 31, 31)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                        .addComponent(returnBtn)
                        .addComponent(reviseBtn)
                        .addComponent(printBtn))
                    .addGap(18, 18, 18)
                    .addComponent(jLabel21)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel29)
                        .addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(22, 22, 22)
                    .addComponent(jLabel3)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                    .addGap(32, 32, 32)
                    .addComponent(jLabel4)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(jLabel30)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    pack();
}
  
  public void setMain(MainProcess main) {
    this.main = main;
  }
  
  public void setSearchPage(SearchBuild searchPage) {
    this.searchPage = searchPage;
  }
  
  public void setDetailForm4(DetailForm4 detailForm4) {
    this.detailForm4 = detailForm4;
  }
  
  public void setBuildInfo(BuildInfo buildInfo) {
    this.buildInfo = buildInfo;
  }
  
  public void setMember(Member member) {
    this.member = member;
  }
  
  public static void main(String args[]) {
    EventQueue.invokeLater(new Runnable() {
        public void run() {
            new DetailForm4().setVisible(true);
        }
    });

  }


  
  // 동작이벤트 처리 메서드
  @Override
  public void actionPerformed(ActionEvent e) {
    Object select = e.getSource();
    
    if(select==returnBtn){  // 돌아가기 버튼을 눌렀을 때 returnsearchPage 호출
      detailForm4.dispose();
      searchPage.setVisible(true);
     }
    
    if(select == reviseBtn) {
      try {
        updateInfo();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    }
    
    if(select == printBtn) {
        try {
          printWork(build);
        } catch (IOException e1) {
          e1.printStackTrace();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
    }
    
  }
  
  // 업데이트 해주는 메소드
  public void updateInfo() throws SQLException {
    if(member.getUserGrade().equals("10")) {
      Build reviseBuildInfo = this.build;
      reviseBuildInfo.setBldNM(jTextField6.getText());                   // 건물명(명칭)
      reviseBuildInfo.setHoCNT(Integer.parseInt(jTextField7.getText())); // 호 수
      
      System.out.println("수정할 객체 : "+reviseBuildInfo);
      
       // 업데이트 확인창 띄우기
      String[] buttons = {"확인", "취소하기"};
      
      int checkStatus = 0;
      checkStatus = JOptionPane.showOptionDialog(null, "업데이트 하시겠습니까?", "업데이트", 
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "확인");
      // 확인을 눌렀을 때 0, 취소를 눌렀을 때 1
      
      if(checkStatus == 0) { 
          dbBuildDetail.updateBuild4(reviseBuildInfo);
      }
    } else {
      JOptionPane.showMessageDialog(reviseBtn, "관리자 등급만 업데이트 할 수 있습니다.");
    }
  }
  
  // 엑셀에 건축물 정보 저장 후 인쇄하기 (Poi 라이브러리 사용) 
  public void printWork(Build build) throws IOException, SQLException {
    
    FileInputStream fis = new FileInputStream("C:/Users/seoin_01/Desktop/project/Origin_xlxs/집합건축물대장_전유부.xlsx");
    Workbook workbook = new XSSFWorkbook(fis);
    
    Sheet sheet1 = workbook.getSheetAt(0); // 시트가 1개일때 getSheetAt(0);
    Sheet sheet2 = workbook.getSheetAt(1); // 전유부 2개, 공용부 4개, 소유자 정보가 4개 넘은 경우. 나머지 층별 정보를 sheet2에 넣고 for문으로 계속 출력.
    
    sheet1.getRow(5).getCell(2).setCellValue(jTextField5.getText()); // 고유번호
    sheet1.getRow(5).getCell(13).setCellValue(jTextField6.getText()); // 명칭(건물명)
    sheet1.getRow(5).getCell(16).setCellValue(jTextField7.getText()); // 호명칭
    
    sheet1.getRow(8).getCell(2).setCellValue(jTextField1.getText()); // 대지위치
    sheet1.getRow(8).getCell(10).setCellValue(jTextField2.getText()); // 지번
    sheet1.getRow(8).getCell(13).setCellValue(jTextField3.getText()); // 도로명주소
    
    int exposListRow  = model1.getRowCount();  // 전유 행 갯수
    int exposList2Row = model2.getRowCount();  // 공유 행 갯수
    int ownerListRow  = model3.getRowCount();  // 소유자 행 갯수
    System.out.println(exposListRow + "|||" + exposList2Row + "|||" + ownerListRow);
    
    // 전유부 엑셀에 옮기기.
    int checkSum = 0;
    for (int i = 14; i < 14 + exposListRow; i++) { 
      // 시트 위치는 14 행부터  층별 모델테이블은 0행부터 가져와서 붙여줘야 함.
      sheet1.getRow(i).getCell(2).setCellValue((String) model1.getValueAt(i % 14, 0)); //구분
      sheet1.getRow(i).getCell(3).setCellValue((String) model1.getValueAt(i % 14, 1)); //층별
      sheet1.getRow(i).getCell(4).setCellValue((String) model1.getValueAt(i % 14, 2)); //구조
      sheet1.getRow(i).getCell(7).setCellValue((String) model1.getValueAt(i % 14, 3)); //용도
      
      String platArea = new BigDecimal(model1.getValueAt(i % 14, 4).toString())
                            .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
      
      sheet1.getRow(i).getCell(9).setCellValue(platArea);                              //면적
      
      checkSum = checkSum + 1;
      if (checkSum == 2) // checkSum이 2이면 2개의 행
        break;
    }
    
    // 공유부 엑셀에 옮기기.
    int checkSum2 = 0;
    for (int i = 18; i < 18 + exposList2Row; i++) {
   // 시트 위치는 14 행부터  층별 모델테이블은 0행부터 가져와서 붙여줘야 함.
      sheet1.getRow(i).getCell(2).setCellValue((String) model2.getValueAt(i % 18, 0)); //구분
      sheet1.getRow(i).getCell(3).setCellValue((String) model2.getValueAt(i % 18, 1)); //층별
      sheet1.getRow(i).getCell(4).setCellValue((String) model2.getValueAt(i % 18, 2)); //구조
      sheet1.getRow(i).getCell(7).setCellValue((String) model2.getValueAt(i % 18, 3)); //용도
      
      String platArea = new BigDecimal(model2.getValueAt(i % 18, 4).toString())
                            .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
      
      sheet1.getRow(i).getCell(9).setCellValue(platArea);                              //면적
      
      checkSum2 = checkSum2 + 1;
      if (checkSum2 == 4) // checkSum2가  4이면 4개의 행
        break;
    }
    
    // 소유권 현황 엑셀에 옮기기.
    int checkSum3 = 0;
    for(int i = 14; i < ownerListRow + 14; i++) { // 21 23 25행에 이름자리
      sheet1.getRow(14 + (2 * checkSum3)).getCell(10).setCellValue((String) model3.getValueAt(checkSum3, 0)); // 이름
      sheet1.getRow(14 + (2 * checkSum3)).getCell(17).setCellValue((String) model3.getValueAt(checkSum3, 2)); // 소유권 지분
      
      checkSum3 = checkSum3 + 1;
      if(checkSum3 == 4) // checkSum3이 4이면 소유자정보 4개
        break;
    }

    // 엑셀파일 인쇄 설정
    PrintSetup print = sheet1.getPrintSetup();
    print.setPaperSize(PrintSetup.A4_PAPERSIZE);
    print.setLandscape(true); // true면 가로방향 , 선언하지 않으면 기본 세로방향
    
    if (exposListRow <= 2 && exposList2Row <= 4 && ownerListRow <= 4) {
    try {
      // 파일출력
      File xlsFile = new File("C:/Users/seoin_01/Desktop/project/src/com/resource/집합건축물대장_전유부.xlsx");
      FileOutputStream fileOut = new FileOutputStream(xlsFile);
      workbook.write(fileOut);
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("엑셀파일 저장 완료.");
    }
    
    // 더 출력하기.
    if (exposListRow > 2 || exposList2Row > 4 || ownerListRow > 4) { // 전유부, 공용부, 소유자현황 에 해당하는 데이터가 그 이상일 시
      
      int exposListPrintSize = (exposListRow - 2) / 6;   // 전유부 프린트 사이즈
      int exposList2PrintSize = (exposList2Row - 4) / 6; // 공용부 프린트 사이즈
      int ownerPrintSize = (2 * (ownerListRow - 4)) / 7; // 소유자 프린트 사이즈 
      int printSize = 0;                              // printSize는 진짜 프린트 할 대장 수
      
      if ((exposListRow - 2) % 6 > 0) {                  // 만약  6개로 나눴는데 0을 넘는다면 => 전유 정보가 남은 경우
        exposListPrintSize = exposListPrintSize + 1;  // 한장더 추가한다.
      }
      
      if ((exposList2Row - 4) % 6 > 0) {                 // 만약 6개로 나눴는데 0을 넘는다면 => 공용 정보가 남은 경우
        exposList2PrintSize = exposList2PrintSize +1; // 한장더 추가한다.
      }
      
      if((2 * (ownerListRow - 4)) % 7 > 0) {             // 만약 7개로 나눴는데 0을 넘는다면 => 소유자 정보가 남은 경우
        ownerPrintSize = ownerPrintSize +1;           // 한장더 추가한다.
      }
      
      
      if(exposListPrintSize > exposList2PrintSize && exposListPrintSize > ownerPrintSize) {
        printSize = exposListPrintSize;
      }else if(exposList2PrintSize > exposListPrintSize && exposList2PrintSize > ownerPrintSize) {
        printSize = exposList2PrintSize;
      }else if(ownerPrintSize > exposListPrintSize && ownerPrintSize > exposList2PrintSize) {
        printSize = ownerPrintSize;
      }

      int exposListStartInt = 2;                  // 전유부 테이블의 모델에서 값을 가져올 시작할 위치(배열의 시작위치값)
      int exposCheckSum     = 0;                  // 전유부 테이블 관련 내부 for문에서 갯수 제한.
      int exposList2StartInt = 4;                 // 공용부 테이블의 모델에서 값을 가져올 시작할 위치(배열의 시작위치값)
      int expos2CheckSum     = 0;                 // 공용부 테이블 관련 내부 for문에서 갯수 제한.
      int ownrStartInt = 3;                       // 소유자 테이블의 모델에서 값을 가져올 시작할 위치(배열의 시작위치값)
      int ownrCheckSum = 0;                       // 소유자 테이블 관련 내부  for문에서 갯수 제한.
      
      // sheet2가 여러장이 될 수 있으므로 for문 선언.
      for (int i = 0; i < printSize; i++) {
        workbook.cloneSheet(i+1);
        sheet2.getRow(5).getCell(2).setCellValue(jTextField5.getText()); // 고유번호
        sheet2.getRow(5).getCell(13).setCellValue(jTextField6.getText()); // 명칭(건물명)
        sheet2.getRow(5).getCell(16).setCellValue(jTextField7.getText()); // 호명칭
        
        sheet2.getRow(8).getCell(2).setCellValue(jTextField1.getText()); // 대지위치
        sheet2.getRow(8).getCell(10).setCellValue(jTextField2.getText()); // 지번
        sheet2.getRow(8).getCell(13).setCellValue(jTextField3.getText()); // 도로명주소
        
        // 전유부 출력.
        if(exposListRow > 2) {
          for (int j = 13; j < 13 + exposListRow - exposListStartInt; j++) {
            sheet2.getRow(j).getCell(2).setCellValue((String) model1.getValueAt(exposCheckSum + exposListStartInt, 0)); //구분
            sheet2.getRow(j).getCell(3).setCellValue((String) model1.getValueAt(exposCheckSum + exposListStartInt, 1)); //층별
            sheet2.getRow(j).getCell(4).setCellValue((String) model1.getValueAt(exposCheckSum + exposListStartInt, 2)); //구조
            sheet2.getRow(j).getCell(7).setCellValue((String) model1.getValueAt(exposCheckSum + exposListStartInt, 3)); //용도
            
            String platArea = new BigDecimal(model1.getValueAt(exposCheckSum + exposListStartInt, 4).toString())
                                  .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            
            sheet2.getRow(j).getCell(9).setCellValue(platArea);                                                        //면적
            
            exposCheckSum = exposCheckSum + 1;
          if(exposCheckSum == 6)  // 추가건축물대장 전유부리스트 크기
            break;
         }
            exposCheckSum = 0;
            exposListStartInt = exposListStartInt + 6;  // 배열 시작위치 재정의
        }
        
        // 공용부 출력.
        if(exposList2Row > 4) {
          for (int j = 21; j < 21 + exposList2Row - exposList2StartInt; j++) {
            sheet2.getRow(j).getCell(2).setCellValue((String) model2.getValueAt(expos2CheckSum + exposList2StartInt, 0)); // 구분
            sheet2.getRow(j).getCell(3).setCellValue((String) model2.getValueAt(expos2CheckSum + exposList2StartInt, 1)); // 구분
            sheet2.getRow(j).getCell(4).setCellValue((String) model2.getValueAt(expos2CheckSum + exposList2StartInt, 2)); // 구분
            sheet2.getRow(j).getCell(7).setCellValue((String) model2.getValueAt(expos2CheckSum + exposList2StartInt, 3)); // 구분
            
            String platArea = new BigDecimal(model2.getValueAt(expos2CheckSum + exposList2StartInt, 4).toString())
                .setScale(2, BigDecimal.ROUND_HALF_UP).toString();

            sheet2.getRow(j).getCell(9).setCellValue(platArea);                                                           //면적
            
            expos2CheckSum = expos2CheckSum + 1;
            if(expos2CheckSum == 6) // 추가건축물대장 공용부분리스트 크기
              break;
            
          }
            expos2CheckSum = 0;
            exposList2StartInt = exposList2StartInt + 6; // 배열 시작위치 재정의
        }
        // 소유자 현황 출력.
        
        if(ownerListRow > 4) {
          for (int j = 14; j < 14 + ownerListRow - ownrStartInt; j++) {
            if(2 * ownrCheckSum < ownerListRow) {
              sheet2.getRow(14 + (2 * ownrCheckSum)).getCell(10).setCellValue((String) model3.getValueAt(ownrCheckSum + ownrStartInt, 0)); // 이름
              sheet2.getRow(14 + (2 * ownrCheckSum)).getCell(13).setCellValue((String) model3.getValueAt(ownrCheckSum + ownrStartInt, 2));
            
              ownrCheckSum = ownrCheckSum + 1;
              if(ownrCheckSum == 4)
                break;
            }
          }
            ownrCheckSum = 0;
            ownrStartInt = ownrStartInt + 4;
        }
        
        sheet2.setSelected(true);  // 해당 시트 선택! => 모든 시트를 출력하기 위해서
        sheet2 = workbook.getSheetAt(i+2); // sheet2 객체에 새로 만든 비어있는 시트를 넣고 종료
        
        // 엑셀파일 인쇄 설정
        print = sheet2.getPrintSetup();
        print.setPaperSize(PrintSetup.A4_PAPERSIZE);
        print.setLandscape(true);
        
      } // sheet2 for문
      
      workbook.removeSheetAt(printSize + 1); // 마지막 비어있는 시트 제거
      
      try {
        File xlsFile = new File("C:/Users/seoin_01/Desktop/project/src/com/resource/"+build.getBuildingPK()+"_집합건축물대장_전유부.xlsx");
        FileOutputStream fileOut = new FileOutputStream(xlsFile);
        workbook.write(fileOut);
        System.out.println("저장완료");
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      
    } // if 조건 종료
    
    // 프린트하기 (Desktop을 생성한 이유는 그냥 출력하게 되면 DEMO 라벨이 붙어서 출력되기때문이다..)
    Desktop desktop = null;
      if(Desktop.isDesktopSupported()) {
        desktop = Desktop.getDesktop();
      }
      
      desktop.print(new File("C:/Users/seoin_01/Desktop/project/src/com/resource/"+build.getBuildingPK()+"_집합건축물대장_전유부.xlsx"));
      
  }
  

  // 상세정보 폼이 실행되면 searchPage에서 BuildInfo 객체를 받아 Building(건축물대장)의 정보를 조회해서 뿌려준다.
  public void inputBuildInfo(BuildInfo buildInfo) throws SQLException {
    jLabel1.setText(buildInfo.getSidoNM() + " " + buildInfo.getSigunguNM() + " " + buildInfo.getBjdongNM() + " " +
                    buildInfo.getBunNum() + "-" + buildInfo.getJiNum() + " " + buildInfo.getBldNM());

    // 리스트에서 선택한 건물 정보로 건축물 대장을 찾음.(대장 구분코드, 대장종류, 대장 PK, bun, ji 정보로 조회)
    Build build = dbBuildDetail.findBuild(
        buildInfo.getBldTypeGBCD(), buildInfo.getBuildingPK(), buildInfo.getRegstrGBCD(), buildInfo.getRegstrKINKCD(),
        buildInfo.getSidoNM(),buildInfo.getSigunguNM(),buildInfo.getBjdongNM(),
        buildInfo.getBunNum(),buildInfo.getJiNum());
    
    Build build2 = null;
    // UPPER 컬럼을 이용해서 부모 키 정보로 건물명을 찾아서 가져옴.
    // build2에는 해당 전유부의 상위 대장 정보를 가져옴.
    if(build.getBldNM() == null || build.getBldNM() == "") {
      build2 = dbBuildDetail.findBuild(
          null, build.getUpperBuildingPK(), null, null, buildInfo.getSidoNM(), buildInfo.getSigunguNM(), buildInfo.getBjdongNM(),
          buildInfo.getBunNum(), buildInfo.getJiNum());
    }
    
    
    jTextField5.setText(build.getRegstrNo());
    jTextField6.setText(build.getBldNM());
    if(build.getBldNM() == null || build.getBldNM() == "")
      jTextField6.setText(build2.getBldNM());
    jTextField7.setText(build.getHoNM());
    jTextField1.setText(build.getPlatLoC());
    jTextField2.setText(build.getJiBun());
    // 도로명주소 
    jTextField3.setText(build.getPlatNewLoc());
    
    
    exposTableSet(build);
    ownrTableSet(build);
    
    this.build = build;

  } // inputBuildInfo() 끝

  
  // 전유공유 정보를 조회해 전공유 부분 테이블에 각각 뿌려주는 메서드
  private void exposTableSet(Build build) throws SQLException {
    java.util.List<ExposArea> exposList = dbBuildDetail.findExposInfo(build);
    Object[] exposListArray = new Object[5];  // 전유부 담을 공간
    Object[] exposListArray2 = new Object[5]; // 공유부 담을 공간
      
      for (int i = 0; i < exposList.size(); i++) {
        if(exposList.get(i).getExposGBCD().equals("1")) {                // 전유부 일 때
          if(exposList.get(i).getMainAtchGBCD().equals("0")) {            // 0이면 '주'
            exposListArray[0] = "주";
          } else if (exposList.get(i).getMainAtchGBCD().equals("1")) {    // 1이면 '부'
            exposListArray[0] = "부";
          } else {
            exposListArray[0] = "";
          }
            exposListArray[1] = exposList.get(i).getFlrNoNM();
            exposListArray[2] = exposList.get(i).getStructNM();
            exposListArray[3] = exposList.get(i).getMainPurpsNM();
            exposListArray[4] = exposList.get(i).getArea();
            model1.addRow(exposListArray);
        }
        if(exposList.get(i).getExposGBCD().equals("2")) {                // 공용부 일 때
          if(exposList.get(i).getMainAtchGBCD().equals("0")) {            // 0이면 '주'
            exposListArray2[0] = "주";
          } else if (exposList.get(i).getMainAtchGBCD().equals("1")) {    // 1이면 '부'
            exposListArray2[0] = "부";
          } else {
            exposListArray2[0] = "";
          }
            exposListArray2[1] = exposList.get(i).getFlrNoNM();
            exposListArray2[2] = exposList.get(i).getStructNM();
            exposListArray2[3] = exposList.get(i).getMainPurpsNM();
            exposListArray2[4] = exposList.get(i).getArea();
            model2.addRow(exposListArray2);
        }
    }
  }
  
  // 소유자정보를 조회해 소유자정보테이블에 뿌려주는 메서드
  private void ownrTableSet(Build build) throws SQLException {
    java.util.List<Owner> ownerList = dbBuildDetail.findOwnrInfo(build);
    Object[] ownerListArray = new Object[4];
    
    for (int i = 0; i < ownerList.size(); i++) {
      if(ownerList.get(i).getName() != null) {
        ownerListArray[0] = ownerList.get(i).getName();
      }
      
      ownerListArray[1] = "";  // 소유자의 주민등록번호와 주소 정보가 없음.
      
      if(ownerList.get(i).getOwnshQuota() != null) {
        ownerListArray[2] = ownerList.get(i).getOwnshQuota();
      }
      
      ownerListArray[3] = "";
      
      model3.addRow(ownerListArray);
    }
  }
  
}



