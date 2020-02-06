package com.application.app;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.application.db.DBBuildDetail;
import com.application.dto.Build;
import com.application.dto.BuildInfo;
import com.application.dto.Floor;
import com.application.dto.Member;
import com.application.requireClass.MainPurpsArray;
import com.application.requireClass.RoofArray;
import com.application.requireClass.StrctArray;
import keeptoo.KGradientPanel;

// DetailForm3 => 표제부  대장종류 (번호) : 3
// 각 항목 변수이름 지정해주지 않음
// 엑셀 저장 출력 완료
@SuppressWarnings("serial")
public class DetailForm3 extends JFrame implements ActionListener{
  
  MainProcess main;
  BuildInfo buildInfo;
  Build build;
  SearchBuild searchPage;
  Member member;
  DetailForm3 detailForm3;
  DBBuildDetail dbBuildDetail = new DBBuildDetail();
  
  int grndFlr;
  int urndFlr;
  int printStatus;
  
  public DetailForm3() {
    dtlform();
    returnBtn.addActionListener(this);
    reviseBtn.addActionListener(this);
    printBtn.addActionListener(this);
    
    jComboBox1.addActionListener(this);
    jComboBox2.addActionListener(this);
    jComboBox4.addActionListener(this);
               
    mainPurpsNMComboBox.addActionListener(this);
    mainPurpsCDComboBox.addActionListener(this);
               
    grndFlrNMComboBox.addActionListener(this);
               
    strctNMComboBox.addActionListener(this);
    strctCDComboBox.addActionListener(this);
        
    roofNMComboBox.addActionListener(this);
    roofCDComboBox.addActionListener(this);
    
    setVisible(true);
  }
  
  JButton returnBtn = new JButton(); // 돌아가기 버튼(searchPage로)
  JButton reviseBtn = new JButton();
  JButton printBtn = new JButton(); // 출력버튼
  
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable jTable1 = new JTable();
  JLabel entirLbl = new JLabel();
  JLabel jLabel3 = new JLabel();
  List list2 = new List();
  List list3 = new List();
  JLabel regstrLbl = new JLabel();
  JLabel platLocLbl = new JLabel();
  JLabel jiBunLbl = new JLabel();
  JLabel platNewLocLbl = new JLabel();
  JTextField platLocField = new JTextField();
  JTextField jiBunField = new JTextField();
  JTextField platNewLocField = new JTextField();
  JLabel platAreaLbl = new JLabel();
  JLabel archAreaLbl = new JLabel();
  JLabel bcRatLbl = new JLabel();
  JLabel totAreaLbl = new JLabel();
  JLabel jLabel13 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JLabel jLabel15 = new JLabel();
  JLabel vlRatEstmTotAreaLbl = new JLabel();
  JLabel strctLbl = new JLabel();
  JLabel mainPurpsLbl = new JLabel();
  JLabel grndFlrCntLbl = new JLabel();
  JLabel jLabel20 = new JLabel();
  JLabel buildInfoLbl = new JLabel();
  JLabel vlRatLbl = new JLabel();
  JLabel heitLbl = new JLabel();
  JLabel roofLbl = new JLabel();
  JLabel atchBldLbl = new JLabel();
  JLabel jLabel26 = new JLabel();
  JLabel jLabel27 = new JLabel();
  JLabel jLabel28 = new JLabel();
  JTextField platAreaField = new JTextField();
  JTextField regstrField = new JTextField();
  JLabel bldNMLbl = new JLabel();
  JLabel hoCntLbl = new JLabel();
  JTextField bldNMField = new JTextField();
  JTextField hoCntField = new JTextField();
  JTextField archAreaField = new JTextField();
  JTextField totAreaField = new JTextField();
  JTextField grndFlrField = new JTextField();
  JTextField jTextField11 = new JTextField();
  JTextField jTextField12 = new JTextField();
  JTextField vlRatEstmTotAreaField = new JTextField();
  JTextField bcRatField = new JTextField();
  JTextField jTextField15 = new JTextField();
  JTextField vlRatField = new JTextField();
  JTextField jTextField17 = new JTextField();
  JTextField jTextField18 = new JTextField();
  JTextField heitField = new JTextField();
  JTextField jTextField20 = new JTextField();
  JTextField jTextField22 = new JTextField();
  JTextField jTextField23 = new JTextField();
  JTextField atchBldField = new JTextField();
  JTextField jTextField25 = new JTextField();
  Choice choice1 = new Choice();
  
  JComboBox jComboBox1 = new JComboBox(); // 지역 콤보박스
  JComboBox jComboBox2 = new JComboBox(); // 지구 콤보박스
  JComboBox jComboBox4 = new JComboBox(); // 구역 콤보박스
  JComboBox strctNMComboBox = new JComboBox(); // 주구조1(명칭)
  JComboBox strctCDComboBox = new JComboBox(); // 주구조2(코드)
  JComboBox mainPurpsNMComboBox = new JComboBox();  // 주용도 콤보박스
  JComboBox mainPurpsCDComboBox = new JComboBox(); // 주용도코드 콤보박스
  JComboBox grndFlrNMComboBox = new JComboBox(); // 층수 지상/지하 콤보박스
  JComboBox roofNMComboBox = new JComboBox(); // 지붕명칭
  JComboBox roofCDComboBox = new JComboBox(); // 지붕코드
  
  KGradientPanel kGradientPanel1 = new KGradientPanel();
  
  DefaultTableModel floorModel = new DefaultTableModel() { // 층별 정보 모델1
    public boolean isCellEditable(int i, int c) {         // 셀 수정 못하게 막는것 (더블클릭시 해당 셀 입력모드로 바뀌는거 방지)
    return false;
    }
  }; 
  JScrollPane jScrollPane2 = new JScrollPane(); 
  JTable floorInfoTable = new JTable(floorModel);  // 층별정보 테이블
  
  
  private void dtlform() {
    Dimension frameSize = this.getSize(); // 프레임 사이즈
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈

    this.setLocation((screenSize.width - frameSize.width)/5, (screenSize.height - frameSize.height)/7); // 화면 중앙
    this.setResizable(false); // 화면 크기 임의 변경 금지하는것.
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    returnBtn.setText("돌아가기");              // (리스트) 돌아가기 버튼
    reviseBtn.setText("수정하기");               // 수정하기 버튼
    printBtn.setText("엑셀저장 후 인쇄");          // 인쇄하기 버튼
    
    entirLbl.setText("해당 주소의 전체주소 ");        // 상단 전체주소 라벨
    buildInfoLbl.setText("집합건축물대장(표제부)");  // 일반건축물대장 라벨
    
    regstrLbl.setText("대장_일련번호");            // 대장 일련번호 라벨
    regstrField.setText("대장_일련번호");        // 대장 일련번호 텍스트구역
    regstrField.setEnabled(false);
    
    bldNMLbl.setText("명칭");                  // 명칭 라벨
    bldNMField.setText("명칭");              // 명칭 텍스트구역
    hoCntLbl.setText("호 수");                 // 호 수 라벨
    hoCntField.setText("호 수");              // 호 수 텍스트구역
    platLocLbl.setText("대지위치");               // 대지위치 라벨
    platLocField.setText("대지위치");           // 대지위치 텍스트구역
    platLocField.setEnabled(false);

    jiBunLbl.setText("지번");                  // 지번 라벨
    jiBunField.setText("지번");              // 지번 텍스트구역
    jiBunField.setEnabled(false);

    platNewLocLbl.setText("도로명주소");              // 도로명주소 라벨
    platNewLocField.setText("도로명주소");          // 도로명주소 텍스트구역
    platNewLocField.setEnabled(false);
    
    platAreaLbl.setText("대지면적");               // 대지면적 라벨
    platAreaField.setText("대지면적");           // 대지면적 텍스트구역
    totAreaLbl.setText("연면적");               // 연면적 라벨
    totAreaField.setText("연면적");            // 연면적 텍스트구역
    
    jLabel13.setText("지역");                // 지역 라벨
    jLabel14.setText("지구");                // 지구 라벨
    jLabel15.setText("구역");                // 구역 라벨
    
    archAreaLbl.setText("건축면적");             // 건축면적 라벨
    archAreaField.setText("건축면적");          // 건축면적 텍스트구역
    vlRatEstmTotAreaLbl.setText("용적률산정용연면적");      // 용적률 산정용 연면적 라벨
    vlRatEstmTotAreaField.setText("용적률산정용");      // 용적률 산정용 연면적 텍스트구역
    strctLbl.setText("주구조");              // 주구조 라벨   
    mainPurpsLbl.setText("주용도");              // 주용도 라벨
    grndFlrCntLbl.setText("층수");               // 층수 라벨
    grndFlrField.setText("층수");           // 층수 텍스트구역
    grndFlrField.setText(Integer.toString(grndFlr));
    
    bcRatLbl.setText("건폐율");              // 건폐율 라벨
    bcRatField.setText("건폐율");          // 건폐율 텍스트구역
    vlRatLbl.setText("용적률");              // 용적률 라벨
    vlRatField.setText("용적률");          // 용적률 텍스트구역
    heitLbl.setText("높이");               // 높이 라벨
    heitField.setText("높이");           // 높이 텍스트구역
    roofLbl.setText("지붕");               // 지붕 라벨
    atchBldLbl.setText("부속건축물");           // 부속건축물 라벨
    atchBldField.setText("부속건축물");       // 부속건축물 텍스트구역
    
    jLabel20.setText("조경면적");            // 조경면적 라벨
    jTextField15.setText("조경면적");        // 조경면적 텍스트구역
    jLabel26.setText("공개 공간의 면적");       //  공개 공간의 면적 라벨
    jTextField17.setText("공개공간면적");     // 공개 공간 면적 텍스트구역
    jLabel27.setText("건축선 후퇴면적");       // 건축선 후퇴면적 라벨
    jTextField20.setText("건축선 후퇴면적");   // 건축선 후퇴면적 텍스트구역
    jLabel28.setText("건축선 후퇴거리");       // 건축선 후퇴거리 라벨
    jTextField25.setText("건축선 후퇴거리");   // 건축선 후퇴거리 텍스트구역
    
    
    jLabel3.setText("건축물현황");         // 건축물현황 라벨
    
    entirLbl.setFont(new Font("나눔고딕", 0 , 15));
    buildInfoLbl.setFont(new Font("나눔고딕", 0, 25));
    vlRatEstmTotAreaLbl.setFont(new Font("굴림", 0, 10));
    mainPurpsLbl.setFont(new Font("굴림", 0, 11)); 
    jLabel26.setFont(new Font("굴림", 0, 10));
    jLabel3.setFont(new Font("나눔고딕", 1, 15));
    
    jComboBox1.setModel(new DefaultComboBoxModel(new String[] { "지역"}));
    jComboBox2.setModel(new DefaultComboBoxModel(new String[] { "지구"}));
    jComboBox4.setModel(new DefaultComboBoxModel(new String[] { "구역"}));

    mainPurpsNMComboBox.setModel(new DefaultComboBoxModel(new String[] { "주용도"}));
    mainPurpsCDComboBox.setModel(new DefaultComboBoxModel(new String[] { "주용도코드"}));

    grndFlrNMComboBox.setModel(new DefaultComboBoxModel(new String[] { "지상", "지하"}));

    strctNMComboBox.setModel(new DefaultComboBoxModel(new String[] { "주구조명칭"}));
    strctCDComboBox.setModel(new DefaultComboBoxModel(new String[] { "코드" }));

    roofNMComboBox.setModel(new DefaultComboBoxModel(new String[] { "지붕명칭"}));
    roofCDComboBox.setModel(new DefaultComboBoxModel(new String[] { "코드"}));


    floorModel.addColumn("구분");
    floorModel.addColumn("층별");
    floorModel.addColumn("구조");
    floorModel.addColumn("용도");
    floorModel.addColumn("면적(㎡)");
    
      jScrollPane2.setViewportView(floorInfoTable);
      add(jScrollPane2,BorderLayout.CENTER);
      floorInfoTable.setRowHeight(25);
      
      floorInfoTable.getColumn("구분").setPreferredWidth(50);
      floorInfoTable.getColumn("층별").setPreferredWidth(50);
      floorInfoTable.getColumn("구조").setPreferredWidth(110);
      floorInfoTable.getColumn("용도").setPreferredWidth(160);
      floorInfoTable.getColumn("면적(㎡)").setPreferredWidth(50);
      
      DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
      centerRenderer.setHorizontalAlignment(JLabel.CENTER);
      floorInfoTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
      floorInfoTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
      
      floorInfoTable.getTableHeader().setReorderingAllowed(false); // 층별정보 테이블 컬럼 이동불가하게 만듦
      jTable1.setAutoCreateRowSorter(true); // 정렬기능 추가
      floorInfoTable.setAutoCreateRowSorter(true); // 정렬기능 추가
      
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
      
      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
              .addGap(110, 110, 110)
              .addComponent(entirLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(returnBtn)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(reviseBtn)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(printBtn)
              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                      .addGap(40, 40, 40)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addComponent(regstrLbl)
                          .addComponent(platLocLbl))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(layout.createSequentialGroup()
                              .addComponent(platLocField, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(jiBunLbl)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                              .addComponent(jiBunField, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(27, 27, 27)
                              .addComponent(platNewLocLbl)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                              .addComponent(platNewLocField, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                              .addComponent(regstrField, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(29, 29, 29)
                              .addComponent(bldNMLbl)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                              .addComponent(bldNMField)
                              .addGap(18, 18, 18)
                              .addComponent(hoCntLbl)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                              .addComponent(hoCntField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                  .addGroup(layout.createSequentialGroup()
                      .addGap(42, 42, 42)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                  .addGroup(layout.createSequentialGroup()
                                      .addComponent(platAreaLbl)
                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                      .addComponent(platAreaField))
                                  .addGroup(layout.createSequentialGroup()
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                          .addComponent(archAreaLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                          .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                          .addComponent(bcRatLbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                              .addGroup(layout.createSequentialGroup()
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                              .addGroup(layout.createSequentialGroup()
                                                  .addGap(12, 12, 12)
                                                  .addComponent(bcRatField)))
                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                              .addGap(12, 12, 12)
                                              .addComponent(archAreaField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                  .addGroup(layout.createSequentialGroup()
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                          .addComponent(vlRatEstmTotAreaLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                          .addComponent(totAreaLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(totAreaField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(vlRatEstmTotAreaField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                  .addGroup(layout.createSequentialGroup()
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                          .addComponent(vlRatLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                          .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                                      .addGap(5, 5, 5)
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(vlRatField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                  .addGroup(layout.createSequentialGroup()
                                      .addComponent(jLabel27)
                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                      .addComponent(jTextField20))
                                  .addGroup(layout.createSequentialGroup()
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                              .addComponent(strctLbl, javax.swing.GroupLayout.Alignment.TRAILING)
                                              .addComponent(jLabel13))
                                          .addComponent(heitLbl))
                                      .addGap(18, 18, 18)
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                          .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                              .addComponent(strctNMComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                              .addComponent(strctCDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addComponent(heitField))))
                              .addGap(30, 30, 30)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                      .addGroup(layout.createSequentialGroup()
                                          .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(jTextField25, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                                      .addGroup(layout.createSequentialGroup()
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                              .addComponent(roofLbl)
                                              .addComponent(jLabel14))
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                              .addGroup(layout.createSequentialGroup()
                                                  .addGap(30, 30, 30)
                                                  .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                  .addComponent(roofNMComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(roofCDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                  .addGroup(layout.createSequentialGroup()
                                      .addComponent(mainPurpsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                      .addGap(18, 18, 18)
                                      .addComponent(mainPurpsNMComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                      .addComponent(mainPurpsCDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                      .addGap(25, 25, 25)
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                          .addGroup(layout.createSequentialGroup()
                                              .addComponent(atchBldLbl)
                                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                              .addComponent(atchBldField))
                                          .addGroup(layout.createSequentialGroup()
                                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                  .addComponent(grndFlrCntLbl)
                                                  .addComponent(jLabel15))
                                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                  .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGroup(layout.createSequentialGroup()
                                                      .addComponent(grndFlrField)
                                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                      .addComponent(grndFlrNMComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                          .addGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                  .addComponent(jLabel3)
                                  .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGap(0, 0, Short.MAX_VALUE)))))
              .addContainerGap())
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(buildInfoLbl)
              .addGap(476, 476, 476))
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
      layout.setVerticalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
              .addGap(31, 31, 31)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(entirLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(returnBtn)
                  .addComponent(reviseBtn)
                  .addComponent(printBtn))
              .addGap(18, 18, 18)
              .addComponent(buildInfoLbl)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(regstrLbl)
                  .addComponent(regstrField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(bldNMLbl)
                  .addComponent(hoCntLbl)
                  .addComponent(bldNMField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(hoCntField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(platLocLbl)
                  .addComponent(jiBunLbl)
                  .addComponent(platNewLocLbl)
                  .addComponent(platLocField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jiBunField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(platNewLocField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGap(30, 30, 30)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                          .addGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                  .addComponent(jLabel15)
                                  .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGap(18, 18, 18)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                  .addComponent(grndFlrNMComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                      .addComponent(grndFlrCntLbl)
                                      .addComponent(grndFlrField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addGap(18, 18, 18)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                  .addComponent(atchBldLbl)
                                  .addComponent(atchBldField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                  .addComponent(roofCDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                  .addComponent(roofNMComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGap(18, 18, 18)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                  .addComponent(jLabel28)
                                  .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                          .addGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                  .addGroup(layout.createSequentialGroup()
                                      .addGap(6, 6, 6)
                                      .addComponent(totAreaLbl)
                                      .addGap(18, 18, 18))
                                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                              .addComponent(jLabel13)
                                              .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                              .addComponent(totAreaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                              .addComponent(platAreaLbl)
                                              .addComponent(platAreaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                      .addGap(18, 18, 18)))
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                  .addGroup(layout.createSequentialGroup()
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                              .addComponent(archAreaLbl)
                                              .addComponent(archAreaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                              .addComponent(vlRatEstmTotAreaLbl)
                                              .addComponent(vlRatEstmTotAreaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(bcRatLbl)
                                          .addComponent(bcRatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(vlRatLbl))
                                      .addGap(18, 18, 18)
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                              .addComponent(jLabel20)
                                              .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                              .addComponent(jLabel26)
                                              .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                  .addGroup(layout.createSequentialGroup()
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(strctLbl)
                                          .addComponent(strctNMComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(strctCDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                      .addGap(18, 18, 18)
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(heitLbl)
                                          .addComponent(heitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(vlRatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                      .addGap(18, 18, 18)
                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(jLabel27)
                                          .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                      .addGap(23, 23, 23)
                      .addComponent(jLabel3))
                  .addGroup(layout.createSequentialGroup()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                          .addComponent(jLabel14)
                          .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addGap(18, 18, 18)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                          .addComponent(mainPurpsLbl)
                          .addComponent(mainPurpsNMComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addComponent(mainPurpsCDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addGap(18, 18, 18)
                      .addComponent(roofLbl)))
              .addGap(13, 13, 13)
              .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addGap(30, 30, 30))
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
  
  public void setDetailForm3(DetailForm3 detailForm3) {
    this.detailForm3 = detailForm3;
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
            new DetailForm3().setVisible(true);
        }
    });

  }


  
  // 동작이벤트 처리 메서드
  @Override
  public void actionPerformed(ActionEvent e) {
    Object select = e.getSource();
    
    if(select==returnBtn){  // 돌아가기 버튼을 눌렀을 때 returnSearchPage 호출
      detailForm3.dispose();
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
          // 업데이트 확인창 띄우기
          String[] buttons = {"엑셀 저장만", "저장후 인쇄", "취소하기"};
          
          int choiceButton = JOptionPane.showOptionDialog(null, "프린트 하시겠습니까?", "프린트", 
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[1]);
          
          if(choiceButton == JOptionPane.CLOSED_OPTION || choiceButton == JOptionPane.CANCEL_OPTION) {
            
          } // x를 눌러 창을 닫거나 => -1 , 취소를 누를 때 => 2 
          else if (choiceButton == JOptionPane.YES_OPTION) { // 엑셀저장만 => 0
            printStatus = 0;
            printWork(build);
          } else if (choiceButton == JOptionPane.NO_OPTION) { // 저장후 인쇄 => 1
            printStatus = 1;
            printWork(build);
          }
        } catch (IOException e1) {
          e1.printStackTrace();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
    }
    
 // 주구조 코드 ComboBox의 항목을 선택했을 때.
    if(select== strctCDComboBox){  
      if(!(strctCDComboBox.getSelectedItem().toString() == "코드")) {
        try {
         // 주구조명칭리셋메서드에 선택한 코드값을 넘겨주고 명칭을 돌려받는다
         String jugujoNM = jugujoNMReset(strctCDComboBox.getSelectedItem().toString());
         
         // 돌려받은 명칭으로 콤보박스를 다시 세팅한다.
         setStrctNM(jugujoNM);
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      } else {
        setStrctNM(null);
      }
    }
 // 주구조명칭 ComboBox의 항목을 선택했을 때.
    if(select== strctNMComboBox){  
      if(!(strctNMComboBox.getSelectedItem().toString() == "주구조명칭")) {
        try {
          // 주구조코드리셋메서드에 선택한 명칭값을 넘겨주고 코드값을 돌려받는다
         String jugujoCD =  jugujoCDReset(strctNMComboBox.getSelectedItem().toString());
         // 돌려받은 코드값으로 콤보박스를 다시 세팅한다.
         setStrctCD(jugujoCD);
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      } else {
        setStrctCD(null);
      }
    }
 // 주용도 코드 ComboBox의 항목을 선택했을 때.
    if(select== mainPurpsCDComboBox){  
      if(!(mainPurpsCDComboBox.getSelectedItem().toString() == "코드")) {
        try {
         // 주용도명칭 리셋메서드에 선택한 코드값을 넘겨주고 명칭을 돌려받는다
         String juyongdoNM = juyongdoNMReset(mainPurpsCDComboBox.getSelectedItem().toString());
         
         // 돌려받은 명칭으로 콤보박스를 다시 세팅한다.
         setMainPurpsNM(juyongdoNM);
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      } else {
        setMainPurpsNM(null);
      }
    }
 // 주용도명칭 ComboBox의 항목을 선택했을 때.
    if(select== mainPurpsNMComboBox){  
      if(!(mainPurpsNMComboBox.getSelectedItem().toString() == "주용도명칭")) {
        try {
          // 주용도코드 리셋 메서드에 선택한 명칭값을 넘겨주고 코드값을 돌려받는다
         String juyongdoCD =  juyongdoCDReset(mainPurpsNMComboBox.getSelectedItem().toString());
         
         // 돌려받은 코드값으로 콤보박스를 다시 세팅한다.
         setMainPurpsCD(juyongdoCD);
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      } else {
        setMainPurpsCD(null);
      }
    }   
    
    // 지붕 코드 ComboBox의 항목을 선택했을 때.
    if(select== roofCDComboBox){  
      if(!(roofCDComboBox.getSelectedItem().toString() == "코드")) {
        try {
         // 지붕코드 명칭 리셋메서드에 선택한 코드값을 넘겨주고 명칭을 돌려받는다
         String roofNM = roofNMReset(roofCDComboBox.getSelectedItem().toString());
         
         // 돌려받은 명칭으로 콤보박스를 다시 세팅한다.
         setRoofNM(roofNM);
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      } else {
        setRoofNM(null);
      }
    }
 // 지붕구조명칭 ComboBox의 항목을 선택했을 때.
    if(select== roofNMComboBox){  
      if(!(roofNMComboBox.getSelectedItem().toString() == "지붕구조명칭")) {
        try {
          // 지붕구조코드 리셋 메서드에 선택한 명칭값을 넘겨주고 코드값을 돌려받는다
         String roofCD =  roofCDReset(roofNMComboBox.getSelectedItem().toString());
         
         // 돌려받은 코드값으로 콤보박스를 다시 세팅한다.
         setRoofCD(roofCD);
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      } else {
        setRoofCD(null);
      }
    } 
    // 층수 지상/지하의 항목을 선택했을 때.
    if(select == grndFlrNMComboBox) {
      setChangeFlrReset(grndFlrNMComboBox.getSelectedItem().toString());
    }
    
  }
  
  
  // 업데이트 해주는 메소드
  public void updateInfo() throws SQLException {
    if(member.getUserGrade().equals("10")) {
      Build reviseBuildInfo = this.build;
      reviseBuildInfo.setBldNM(bldNMField.getText());  // 건물명(명칭)
      reviseBuildInfo.setHoCNT(Integer.parseInt(hoCntField.getText())); // 호 수
      
      reviseBuildInfo.setPlatArea(new BigDecimal(platAreaField.getText())); // 대지면적
      reviseBuildInfo.setTotArea(new BigDecimal(totAreaField.getText()));  // 연면적
      
      // 지역, 지구, 구역 
      reviseBuildInfo.setArchArea(new BigDecimal(archAreaField.getText()));                // 건축면적
      reviseBuildInfo.setVlRatEstmTotArea(new BigDecimal(vlRatEstmTotAreaField.getText()));// 용적률 산정용연면적
      reviseBuildInfo.setStrctCD(strctCDComboBox.getSelectedItem().toString());  // 주구조코드
      reviseBuildInfo.setMainPurpsCD(mainPurpsCDComboBox.getSelectedItem().toString()); // 주용도코드
      
      // reviseBuildInfo에 해당 건축물의 데이터가 이미 들어가있기 때문에 이렇게 구분지어도 둘 중에 한개만 업데이트 됨.
      if (grndFlrNMComboBox.getSelectedItem().equals("지상"))  // 콤보박스가 지상이면
        reviseBuildInfo.setGrndFlrCNT(Integer.parseInt(grndFlrField.getText())); // 지상층수
      if (grndFlrNMComboBox.getSelectedItem().equals("지하"))  // 콤보박스가 지하이면
        reviseBuildInfo.setUgrndFlrCNT(Integer.parseInt(grndFlrField.getText())); // 지하층수
      
      
      reviseBuildInfo.setBcRat(new BigDecimal(bcRatField.getText()));            // 건폐율
      reviseBuildInfo.setVlRat(new BigDecimal(vlRatEstmTotAreaField.getText())); // 용적률
      reviseBuildInfo.setHeit(Float.parseFloat(heitField.getText()));            // 높이
      reviseBuildInfo.setRoofCD(roofCDComboBox.getSelectedItem().toString());    // 지붕구조코드
      reviseBuildInfo.setAtchBldCnt(Integer.parseInt(atchBldField.getText()));  // 부속건축물 수
      
      System.out.println("수정할 객체 : "+reviseBuildInfo);
      
       // 업데이트 확인창 띄우기
      String[] buttons = {"확인", "취소하기"};
      
      int checkStatus = 0;
      checkStatus = JOptionPane.showOptionDialog(null, "업데이트 하시겠습니까?", "업데이트", 
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "확인");
      // 확인을 눌렀을 때 0, 취소를 눌렀을 때 1
      
      if(checkStatus == 0) { 
          dbBuildDetail.updateBuild3(reviseBuildInfo);
      }
    } else {
      JOptionPane.showMessageDialog(reviseBtn, "관리자 등급만 업데이트 할 수 있습니다.");
    }
  }
  
  
  // 엑셀에 건축물 정보 저장 후 인쇄하기 (Poi 라이브러리 사용) 
  public void printWork(Build build) throws IOException, SQLException {
    
    FileInputStream fis = new FileInputStream("C:/Users/seoin_01/Desktop/project/Origin_xlxs/집합건축물대장_표제부.xlsx");
    Workbook workbook = new XSSFWorkbook(fis);
    
    Sheet sheet1 = workbook.getSheetAt(0); // 시트가 1개일때 getSheetAt(0);
    Sheet sheet2 = workbook.getSheetAt(1); // 층별 정보가 12개가 넘은 경우. 나머지 층별 정보를 sheet2에 넣고 for문으로 계속 출력.
    
    sheet1.getRow(4).getCell(2).setCellValue(regstrField.getText()); // 고유번호
    sheet1.getRow(4).getCell(13).setCellValue(bldNMField.getText()); // 명칭(건물명)
    sheet1.getRow(4).getCell(16).setCellValue(hoCntField.getText()); // 호 수
    
    sheet1.getRow(7).getCell(2).setCellValue(platLocField.getText()); // 대지위치
    sheet1.getRow(7).getCell(9).setCellValue(jiBunField.getText()); // 지번
    sheet1.getRow(7).getCell(13).setCellValue(platNewLocField.getText()); // 도로명주소
    
    sheet1.getRow(9).getCell(2).setCellValue(platAreaField.getText()); // 대지 면적
    sheet1.getRow(9).getCell(6).setCellValue(totAreaField.getText()); // 연면적
    sheet1.getRow(9).getCell(9).setCellValue(""); // 지역
    sheet1.getRow(9).getCell(13).setCellValue(""); // 지구
    sheet1.getRow(9).getCell(16).setCellValue(""); // 구역
    
    sheet1.getRow(11).getCell(2).setCellValue(archAreaField.getText());  // 건축면적
    sheet1.getRow(11).getCell(6).setCellValue(vlRatEstmTotAreaField.getText()); // 용적률 산정용 연면적
    sheet1.getRow(11).getCell(9).setCellValue(strctNMComboBox.getSelectedItem().toString());   // 주 구조명
    sheet1.getRow(11).getCell(13).setCellValue(mainPurpsNMComboBox.getSelectedItem().toString());   // 주 용도명
    sheet1.getRow(11).getCell(17).setCellValue(build.getGrndFlrCNT()); // 지상층
    sheet1.getRow(11).getCell(19).setCellValue(build.getUgrndFlrCNT()); // 지하층
    
    sheet1.getRow(13).getCell(2).setCellValue(bcRatField.getText()); // 건폐율
    sheet1.getRow(13).getCell(6).setCellValue(vlRatField.getText()); // 용적률
    sheet1.getRow(13).getCell(9).setCellValue(heitField.getText()); // 높이
    sheet1.getRow(13).getCell(13).setCellValue(roofNMComboBox.getSelectedItem().toString()); // 지붕구조
    sheet1.getRow(12).getCell(18).setCellValue(atchBldField.getText()); // 부속건축물 수
    
    sheet1.getRow(16).getCell(2).setCellValue("");    // 공간 면적 합계
    sheet1.getRow(16).getCell(6).setCellValue("");    // 공개 공지 면적
    sheet1.getRow(16).getCell(9).setCellValue("");    // 쌈지 공원 면적
    sheet1.getRow(16).getCell(12).setCellValue("");    // 공공보행통로
    sheet1.getRow(16).getCell(15).setCellValue("");    // 건축선 후퇴면적
    sheet1.getRow(16).getCell(18).setCellValue("");   // 그 밖의 면적
    
    java.util.List<Floor> floorList = dbBuildDetail.findFlrInfo(build);
    
    int checkSum = 0;       
    for (int i = 20; i < 20+floorList.size(); i++) {
      // 시트 위치는 20 행부터  층별 모델테이블은 0행부터 가져와서 붙여줘야 함.
      sheet1.getRow(i).getCell(2).setCellValue((String) floorModel.getValueAt(i % 20, 0)); //구분
      sheet1.getRow(i).getCell(3).setCellValue((String) floorModel.getValueAt(i % 20, 1)); //층별
      sheet1.getRow(i).getCell(4).setCellValue((String) floorModel.getValueAt(i % 20, 2)); //구조
      sheet1.getRow(i).getCell(7).setCellValue((String) floorModel.getValueAt(i % 20, 3)); //용도
      
      String platArea = new BigDecimal(floorModel.getValueAt(i % 20, 4).toString())
                            .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
      sheet1.getRow(i).getCell(9).setCellValue(platArea);                              //면적
      
      // 같은 행의 오른쪽 건축물현황 부분에 데이터 지정 6번째 부터 시작
      sheet1.getRow(i).getCell(11).setCellValue((String) floorModel.getValueAt(i % 20 + 6, 0));
      sheet1.getRow(i).getCell(12).setCellValue((String) floorModel.getValueAt(i % 20 + 6, 1));
      sheet1.getRow(i).getCell(13).setCellValue((String) floorModel.getValueAt(i % 20 + 6, 2));
      sheet1.getRow(i).getCell(16).setCellValue((String) floorModel.getValueAt(i % 20 + 6, 3));
      
      String platArea2 = new BigDecimal(floorModel.getValueAt(i % 20 + 6, 4).toString())
                             .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
      sheet1.getRow(i).getCell(18).setCellValue(platArea2);
      
      checkSum = checkSum + 1;
      if (checkSum == 6) // checkSum이 6이면 층별정보가 12개. (양쪽으로)
        break;
    }
    
    // 엑셀파일 인쇄 설정
    PrintSetup print = sheet1.getPrintSetup();
    print.setPaperSize(PrintSetup.A4_PAPERSIZE);
    print.setLandscape(true); // true면 가로방향 , 선언하지 않으면 기본 세로방향
    
    if (floorList.size() <= 12) {
    try {
      // 만약에 층별정보가 12개 이하이면 추가 대장을 제거한다.
      workbook.removeSheetAt(1);
      
      // 파일출력
      File xlsFile = new File("C:/Users/seoin_01/Desktop/project/src/com/resource/"+build.getBuildingPK()+"집합건축물대장_표제부.xlsx");
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
    if (floorList.size() > 12 ) { // 층별정보가 12개 이상 일 때
      
      int flrPrintSize = (floorList.size() - 12) / 36;   // 층별 프린트 사이즈
      
        if ((floorList.size() - 12) % 36 > 0) {           // 만약  36개로 나눴는데 0넘는다면 => 층별 정보가 남은 경우
          flrPrintSize = flrPrintSize + 1;                // 또한 층별리스트의 크기에서 12를 빼고 나서 36보다 작은 경우도 1장 더해야 된다.
        }                                                 // 작은수 % 큰수 => 작은수 > 0
      
      int flrStartInt = 12;                             // 층별 테이블의 모델에서의 값을 가져올 시작할 위치(배열의 시작위치값)
      int flrCheckSum = 0;                              // 층별 테이블관련  내부 for문에서 갯수 제한. 및 model 데이터 위치 참조
      int flrCheckSum2 = 18;                            // 세번째 장 (즉 두번째 추가대장)에 출력을 시작할 위치에 더해야하는 수
      int plus = 0;                                     // 왼쪽 층별정보와 오른쪽 층별정보의 위치에 차이를 두기위한 수
      /* 설명. */
      // 층별 현황 출력 for문을 돌 때  12개의 층별 정보를 제외하기 위해 시작위치 12이고 18번만 돌아야 되기때문에 flrCheckSum을 만들었다
      // 또한 flrCheckSum은 1씩 더해지기 때문에 모델 테이블의 위치값 또한 이것으로 참조했다. 
      // 그리고 18번 for문을 돌면서 같은행인 오른쪽 층별정보에 18개씩 차이가 나야되므로 flrCheckSum2를 만들었다.
      // plus를 만든이유는 두번째 장에 왼쪽 층별정보에서는 그냥 출력되면 되지만,
      // 오른쪽 층별정보는 왼쪽과 18차이가 나야하기 때문이다.
                                                        
      // sheet2가 여러장이 될수 있으므로 for문 선언.
      for (int i = 0; i < flrPrintSize; i++) {
          workbook.cloneSheet(i+1);  // 1 시트가 복사되었다.(두번째 시트) (시트도 0번부터)
          sheet2.getRow(4).getCell(2).setCellValue(regstrField.getText());  // 고유번호
          sheet2.getRow(4).getCell(13).setCellValue(bldNMField.getText());  // 명칭
          sheet2.getRow(4).getCell(16).setCellValue(hoCntField.getText()); // 특이사항
          
          sheet2.getRow(7).getCell(2).setCellValue(platLocField.getText());  // 대지위치
          sheet2.getRow(7).getCell(9).setCellValue(jiBunField.getText());  // 지번
          sheet2.getRow(7).getCell(13).setCellValue(platNewLocField.getText()); // 도로명주소
          
          // 층별 현황 출력  
            for (int j = 11; j < 11 + floorList.size()-flrStartInt; j++) {
                sheet2.getRow(j).getCell(2).setCellValue((String) floorModel.getValueAt(flrCheckSum + flrStartInt + plus, 0)); //구분
                sheet2.getRow(j).getCell(3).setCellValue((String) floorModel.getValueAt(flrCheckSum + flrStartInt + plus, 1)); //층별
                sheet2.getRow(j).getCell(4).setCellValue((String) floorModel.getValueAt(flrCheckSum + flrStartInt + plus, 2)); //구조
                sheet2.getRow(j).getCell(7).setCellValue((String) floorModel.getValueAt(flrCheckSum + flrStartInt + plus, 3)); //용도
                String platArea = new BigDecimal(floorModel.getValueAt(flrCheckSum + flrStartInt + plus, 4).toString())
                    .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                sheet2.getRow(j).getCell(9).setCellValue(platArea);                                                 //면적
              
              if(floorList.size() > flrCheckSum + flrStartInt + flrCheckSum2) { // 배열보다 커져버리면 오류발생된다.
                  sheet2.getRow(j).getCell(11).setCellValue((String) floorModel.getValueAt(flrCheckSum + flrStartInt + flrCheckSum2, 0)); // 구분
                  sheet2.getRow(j).getCell(12).setCellValue((String) floorModel.getValueAt(flrCheckSum + flrStartInt + flrCheckSum2, 1)); // 층별
                  sheet2.getRow(j).getCell(13).setCellValue((String) floorModel.getValueAt(flrCheckSum + flrStartInt + flrCheckSum2, 2)); // 구조
                  sheet2.getRow(j).getCell(16).setCellValue((String) floorModel.getValueAt(flrCheckSum + flrStartInt + flrCheckSum2, 3)); // 용도
                  String platArea2 = new BigDecimal(floorModel.getValueAt(flrCheckSum + flrStartInt + flrCheckSum2, 4).toString())
                      .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                  sheet2.getRow(j).getCell(18).setCellValue(platArea2);                                               // 면적
                }
                
                flrCheckSum = flrCheckSum + 1;
                if(flrCheckSum == 18) break;
            }
              plus = flrCheckSum2;
              flrCheckSum2 = flrCheckSum2 + 18;  //   출력을 시작할 위치에 더해야하는 수  18을 더한다
              
              flrCheckSum = 0;
              flrStartInt = flrStartInt + 18; // 배열 시작위치 재정의
              
            sheet2.setSelected(true);           // 해당 시트를 선택함! => 모든 시트를 출력하기 위해서
            sheet2 = workbook.getSheetAt(i+2); // sheet2 객체에 새로 만든 비어있는시트를 넣고 종료 됨

            // 엑셀파일 인쇄 설정
            print = sheet2.getPrintSetup();
            print.setPaperSize(PrintSetup.A4_PAPERSIZE);
            print.setLandscape(true);      // true면 가로방향 , 선언하지 않으면 기본 세로방향
        }

      workbook.removeSheetAt(flrPrintSize + 1); // 마지막 비어있는 시트 제거
      
      try {
        File xlsFile = new File("C:/Users/seoin_01/Desktop/project/src/com/resource/"+build.getBuildingPK()+"집합건축물대장_표제부.xlsx");
        FileOutputStream fileOut = new FileOutputStream(xlsFile);
        workbook.write(fileOut);
        Desktop.getDesktop().open(xlsFile);
        
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      
    } // if 조건 종료
    
    if(printStatus == 1) {
    // 프린트하기 (Desktop을 생성한 이유는 그냥 출력하게 되면 DEMO 라벨이 붙어서 출력되기때문이다..)
    Desktop desktop = null;
      if(Desktop.isDesktopSupported()) {
        desktop = Desktop.getDesktop();
      }
      
      desktop.print(new File("C:/Users/seoin_01/Desktop/project/src/com/resource/"+build.getBuildingPK()+"집합건축물대장_표제부.xlsx"));
    System.out.println("엑셀파일 인쇄 완료");
    }
  }
  
  
  // 상세정보 폼이 실행되면 SearchPage에서 BuildInfo 객체를 받아 Building(건축물대장)의 정보를 조회해서 뿌려준다.
  public void inputBuildInfo(BuildInfo buildInfo) throws SQLException {
    entirLbl.setText(buildInfo.getSidoNM() + " " + buildInfo.getSigunguNM() + " " + buildInfo.getBjdongNM() + " " +
                    buildInfo.getBunNum() + "-" + buildInfo.getJiNum() + " " + buildInfo.getBldNM());

    
    // 리스트에서 선택한 건물 정보로 건축물 대장을 찾음.(대장 구분코드, 대장종류, 대장 PK, bun, ji 정보로 조회)
    Build build = dbBuildDetail.findBuild(
        buildInfo.getBldTypeGBCD(), buildInfo.getBuildingPK(), buildInfo.getRegstrGBCD(), buildInfo.getRegstrKINDCD(),
        buildInfo.getSidoNM(),buildInfo.getSigunguNM(),buildInfo.getBjdongNM(),
        buildInfo.getBunNum(),buildInfo.getJiNum());
    
    
    regstrField.setText(build.getRegstrNo());
    bldNMField.setText(build.getBldNM());
    hoCntField.setText(Integer.toString(build.getHoCNT()));
    platLocField.setText(build.getPlatLoC());
    jiBunField.setText(build.getJiBun());
    
    
    // 도로명주소 
    platNewLocField.setText(build.getPlatNewLoc());
    
    // 대지면적
    try {
      BigDecimal bdc = new BigDecimal(build.getPlatArea().toString());
      String stringPlatArea = bdc.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      platAreaField.setText(stringPlatArea);
    } catch (NullPointerException e) {
      System.out.println("대지면적 데이터가 없습니다." + e);
      platAreaField.setText("");
    } catch (Exception e1) {
      System.out.println("에러!");
    }

    // 연면적
    try {
      BigDecimal bdc2 = new BigDecimal(build.getTotArea().toString());
      String stringTotArea = bdc2.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      totAreaField.setText(stringTotArea);
    } catch (NullPointerException e) {
      System.out.println("연면적 데이터가 없습니다" + e);
      totAreaField.setText("");
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    // 건축면적
    try {
      BigDecimal bdc3 = new BigDecimal(build.getArchArea().toString());
      String stringArchArea = bdc3.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      archAreaField.setText(stringArchArea);
    } catch (NullPointerException e) {
      System.out.println("건축면적 데이터가 없습니다." + e);
      archAreaField.setText("");
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    // 용적률산정용연면적
    try {
      BigDecimal bdc4 = new BigDecimal(build.getVlRatEstmTotArea().toString());
      String stringVlRatEstmTotArea = bdc4.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      vlRatEstmTotAreaField.setText(stringVlRatEstmTotArea);
    } catch (NullPointerException e) {
      System.out.println("용적률산정용연면적 데이터가 없습니다." + e);
      vlRatEstmTotAreaField.setText("");
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    // 주구조
    setStrctNM(build.getStrctNM());
    
    // 주구조 코드
    setStrctCD(build.getStrctCD());
    
    
    // 주용도
    setMainPurpsNM(build.getMainPurpsNM());
    
    // 주용도 코드
    setMainPurpsCD(build.getMainPurpsCD());

    
    // 층수
    try {
    grndFlrField.setText(Integer.toString(build.getGrndFlrCNT()));
      grndFlr = build.getGrndFlrCNT();
      urndFlr = build.getUgrndFlrCNT();
    } catch (NullPointerException e) {
      System.out.println("층수 데이터가 없습니다." + e);
    }
    
    
    // 건폐율 
    try {
      BigDecimal bdc5 = new BigDecimal(build.getBcRat().toString());
      String stringBcRat = bdc5.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      bcRatField.setText(stringBcRat);
    } catch (NullPointerException e) {
      System.out.println("건폐율 데이터가 없습니다." + e);
      bcRatField.setText("");
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    // 용적률
    try {
      BigDecimal bdc6 = new BigDecimal(build.getVlRat().toString());
      String stringVlRat = bdc6.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      vlRatField.setText(stringVlRat);
    } catch (NullPointerException e) {
      System.out.println("용적률 데이터가 없습니다." + e);
      vlRatField.setText("");
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    // 높이
    try {
      String stringHeit = Float.toString(build.getHeit());
      heitField.setText(stringHeit);
    } catch (NullPointerException e) {
      System.out.println("높이 데이터가 없습니다." + e);
      heitField.setText("");
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    // 지붕
    setRoofNM(build.getRoofNM());
    
    // 지붕 코드
    setRoofCD(build.getRoofCD());
    
    // 부속건축물 수
    try {
      String stringAtchBldCnt = Integer.toString(build.getAtchBldCnt());
      atchBldField.setText(stringAtchBldCnt);
    } catch (NullPointerException e) {
      System.out.println("부속건축물 수 데이터가 없습니다."+ e);
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    // 조경면적, 공개공간면적, 건축선 후퇴면적, 건축선 후퇴거리 컬럼명 몰라서 모르겠음....
    jTextField15.setText("");
    jTextField17.setText("");
    jTextField20.setText("");
    jTextField25.setText("");
    
    
    flrTableSet(build);
    
    this.build = build;

  } // inputBuildInfo() 끝

  
  
  
  // 코드나 명칭을 전달하면 명칭이나 코드를 리턴하는 메서드 관련목록  
  // 주구조 명칭세팅 메서드
  private String jugujoNMReset(String jgjCD) throws SQLException {
    String jugujoNM = dbBuildDetail.findjugujoNM(jgjCD);
    return jugujoNM;
  }
  // 주구조 코드세팅 메서드
  private String jugujoCDReset(String jgjNM) throws SQLException {
    String jugujoCD = dbBuildDetail.findjugujoCD(jgjNM);
    return jugujoCD;
  }  
  
  // 주용도 명칭세팅 메서드
  private String juyongdoNMReset(String jydCD) throws SQLException {
    String juyongdoNM = dbBuildDetail.findjuyongdoNM(jydCD);
    return juyongdoNM;
  }
  // 주용도 코드세팅 메서드
  private String juyongdoCDReset(String jydNM) throws SQLException {
    String juyongdoCD = dbBuildDetail.findjuyongdoCD(jydNM);
    return juyongdoCD;
  }  
  
  // 지붕 명칭세팅 메서드
  private String roofNMReset(String roofCD) throws SQLException {
    String roofNM = dbBuildDetail.findRoofNM(roofCD);
    return roofNM;
  }
  // 지붕 코드세팅 메서드
  private String roofCDReset(String roofNM) throws SQLException {
    String roofCD = dbBuildDetail.findRoofCD(roofNM);
    return roofCD;
  }
  
  // 지상/지하 선택시 층수 바꾸기.
  private void setChangeFlrReset(String groundNM) {
    if(groundNM == "지상") {
      grndFlrField.setText(Integer.toString(grndFlr));
    } else {
      grndFlrField.setText(Integer.toString(urndFlr));
    }
  }
  
  
  // 층별정보를 조회해 층별테이블에 뿌려주는 메서드
  private void flrTableSet(Build build) throws SQLException {
    java.util.List<Floor> floorList = dbBuildDetail.findFlrInfo(build);
    Object[] floorListArray = new Object[5]; 
      
      for (int i = 0; i < floorList.size(); i++) {
        
      // 건축물대장에서 구분은 "주","부" 로 나뉨.
          if(floorList.get(i).getMainAtchGBCD().equals("0")) { 
            floorListArray[0] = ("주"+floorList.get(i).getMainAtchSeqNo()).toString();
          } else if(floorList.get(i).getMainAtchGBCD().equals("1")) {
            floorListArray[0] = ("부"+floorList.get(i).getMainAtchSeqNo()).toString();
          } else {
            floorListArray[0] = "";
          }
          
      // 건축물대장에서 층별 코드로 층명칭 구분
          if(floorList.get(i).getFlrGBCD().equals("20") ) {
            floorListArray[1] = ("주"+floorList.get(i).getFlrNo()+"층").toString();
          } else if(floorList.get(i).getFlrGBCD().equals("10")) {
            floorListArray[1] = ("지하"+floorList.get(i).getFlrNo()+"층").toString();
          } else if(floorList.get(i).getFlrGBCD().equals("30")) {
            floorListArray[1] = ("옥탑"+floorList.get(i).getFlrNo()+"층").toString();
          } else {
            floorListArray[1] = (floorList.get(i).getFlrNo()+"층").toString();
          }
          
        floorListArray[2] = floorList.get(i).getStrctNM();
        floorListArray[3] = floorList.get(i).getMainPurpsNM();
        floorListArray[4] = floorList.get(i).getArea();
        floorModel.addRow(floorListArray);
    }
  
  }
  
  
  
  
//  for (int i = 0; i < bjdongArray.length; i++) {
//    bjdongComboBox.addItem(new String(bjdongArray[i]));
//  }
//  콤보박스에 배열을 추가시키는방법......
  
  

  
  
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void setStrctNM(String name) {
      strctNMComboBox.setModel(new DefaultComboBoxModel(new StrctArray().strctNM));
      try {
        strctNMComboBox.setSelectedItem(name.toString());
      } catch (Exception e) {
      
      }
  }
  
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void setStrctCD(String name) {
      strctCDComboBox.setModel(new DefaultComboBoxModel(new StrctArray().strctCD));
      try {
        strctCDComboBox.setSelectedItem(name.toString());
      } catch (Exception e) {
      }
  }
  
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void setMainPurpsNM(String name) {
      mainPurpsNMComboBox.setModel(new DefaultComboBoxModel(new MainPurpsArray().mainPurpsNM));
     try {
       mainPurpsNMComboBox.setSelectedItem(name.toString());
    } catch (Exception e) {
    }
    
  }
  
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void setMainPurpsCD(String name) {
      mainPurpsCDComboBox.setModel(new DefaultComboBoxModel(new MainPurpsArray().mainPurpsCD));
      try {
        mainPurpsCDComboBox.setSelectedItem(name.toString());
     } catch (Exception e) {
     }
  }
  
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void setRoofNM(String name) {
      roofNMComboBox.setModel(new DefaultComboBoxModel(new RoofArray().roofNM));
      try {
        roofNMComboBox.setSelectedItem(name.toString());
     } catch (Exception e) {
     }
  }
  
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void setRoofCD(String name) {
      roofCDComboBox.setModel(new DefaultComboBoxModel(new RoofArray().roofCD));
      try {
        roofCDComboBox.setSelectedItem(name.toString());
     } catch (Exception e) {
     }
  }
  

  
}



