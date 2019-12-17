package com.application.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
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
import com.application.dto.Floor;
import com.application.dto.Member;
import com.application.dto.Owner;
import com.application.requireClass.MainPurpsArray;
import com.application.requireClass.MultiLineHeaderRenderer;
import com.application.requireClass.RoofArray;
import com.application.requireClass.StrctArray;
import keeptoo.KGradientPanel;

// DetailForm => 일반건축물대장  대장종류 (번호) : 2
// 각 항목 변수이름 지정.
// 엑셀 저장 출력 완료
@SuppressWarnings("serial")
public class DetailForm extends JFrame implements ActionListener{
  
  MainProcess main;
  BuildInfo buildInfo;
  Build build;
  SearchBuild searchPage;
  DetailForm detailForm;
  Member member;
  DBBuildDetail dbBuildDetail = new DBBuildDetail();
  
  int grndFlr;
  int urndFlr;
  
  public DetailForm() {
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
  
  JButton returnBtn = new JButton(); // 돌아가기 버튼(searchBuild로)
  JButton reviseBtn = new JButton();  // 수정하기 버튼
  JButton printBtn = new JButton(); // 출력버튼
  
  JScrollPane jScrollPane1 = new JScrollPane();
  JLabel entirLbl = new JLabel();
  
  JLabel regstrLbl = new JLabel();
  JLabel platLocLbl = new JLabel();
  JTextField platLocField = new JTextField();
  JLabel jiBunLbl = new JLabel();
  JTextField jiBunField = new JTextField();
  JLabel platNewLocLbl = new JLabel();
  JTextField platNewLocField = new JTextField();
  JLabel platAreaLbl = new JLabel();
  JLabel archAreaLbl = new JLabel();
  JLabel bcRatLbl = new JLabel();
  JLabel totAreaLbl = new JLabel();
  JLabel vlRatEstmTotAreaLbl = new JLabel();
  JLabel strctLbl = new JLabel();
  JLabel mainPurpsLbl = new JLabel();
  JLabel grndFlrCntLbl = new JLabel();
  JLabel buildInfoLbl = new JLabel();
  JLabel vlRatLbl = new JLabel();
  JLabel heitLbl = new JLabel();
  JLabel roofLbl = new JLabel();
  JLabel atchBldLbl = new JLabel();
  JTextField platAreaField = new JTextField();
  JTextField regstrField = new JTextField();
  JLabel bldNMLbl = new JLabel();
  JLabel spcmtLbl = new JLabel();
  JTextField bldNMField = new JTextField();
  JTextField spcmtField = new JTextField();
  JTextField archAreaField = new JTextField();
  JTextField totAreaField = new JTextField();
  JTextField grndFlrField = new JTextField();
  JTextField vlRatEstmTotAreaField = new JTextField();
  JTextField bcRatField = new JTextField();
  JTextField vlRatField = new JTextField();
  JTextField heitField = new JTextField();
  JTextField atchBldField = new JTextField();
  
  JLabel jLabel13 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JLabel jLabel15 = new JLabel();
  JLabel jLabel20 = new JLabel();
  JLabel jLabel26 = new JLabel();
  JLabel jLabel27 = new JLabel();
  JLabel jLabel28 = new JLabel();
  JTextField jTextField11 = new JTextField();
  JTextField jTextField12 = new JTextField();
  JTextField jTextField15 = new JTextField();
  JTextField jTextField17 = new JTextField();
  JTextField jTextField18 = new JTextField();
  JTextField jTextField20 = new JTextField();
  JTextField jTextField22 = new JTextField();
  JTextField jTextField23 = new JTextField();
  JTextField jTextField25 = new JTextField();
  
  JLabel floorInfoLbl = new JLabel();
  JLabel ownerInfoLbl = new JLabel();
  
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
  
  DefaultTableModel floorModel = new DefaultTableModel(); // 층별 정보 모델1
  JScrollPane jScrollPane2 = new JScrollPane(); 
  JTable floorInfoTable = new JTable(floorModel);         // 층별정보 테이블
  
  DefaultTableModel ownerModel = new DefaultTableModel(); // 소유자 정보 모델1
  JScrollPane jScrollPane3 = new JScrollPane(); 
  JTable ownerInfoTable = new JTable(ownerModel);         // 소유자 테이블
  
  
  
  private void dtlform() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    returnBtn.setText("돌아가기");              // (리스트) 돌아가기 버튼
    reviseBtn.setText("수정하기");               // 수정하기 버튼
    printBtn.setText("엑셀저장 후 인쇄");          // 인쇄하기 버튼
    
    entirLbl.setText("해당 주소의 전체주소 ");        // 상단 전체주소 라벨
    buildInfoLbl.setText("일반건축물대장");           // 일반건축물대장 라벨

    regstrLbl.setText("대장_일련번호");            // 대장 일련번호 라벨
    regstrField.setText("대장_일련번호");        // 대장 일련번호 텍스트구역
    regstrField.setEnabled(false);
    
    bldNMLbl.setText("명칭");                  // 명칭 라벨
    bldNMField.setText("명칭");              // 명칭 텍스트구역
    spcmtLbl.setText("특이사항");              // 특이사항 라벨
    spcmtField.setText("특이사항");           // 특이사항 텍스트구역
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
    jComboBox1.setModel(new DefaultComboBoxModel(new String[] { "지역"}));
    jLabel14.setText("지구");                // 지구 라벨
    jComboBox2.setModel(new DefaultComboBoxModel(new String[] { "지구"}));
    jLabel15.setText("구역");                // 구역 라벨
    jComboBox4.setModel(new DefaultComboBoxModel(new String[] { "구역"}));
    
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
    
    floorInfoLbl.setText("층별정보");         // 층별정보 라벨
    ownerInfoLbl.setText("소유자정보");            // 소유자정보 라벨
    
    buildInfoLbl.setFont(new Font("굴림", 1, 15)); 
    vlRatEstmTotAreaLbl.setFont(new Font("굴림", 0, 10));
    mainPurpsLbl.setFont(new Font("굴림", 0, 10)); 
    jLabel26.setFont(new Font("굴림", 0, 10));


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
      
      floorInfoTable.getTableHeader().setReorderingAllowed(false); // 층별정보 테이블 컬럼 이동불가하게 만듦
      
    floorInfoTable.getColumn("구분").setPreferredWidth(10);
    floorInfoTable.getColumn("층별").setPreferredWidth(10);
    floorInfoTable.getColumn("구조").setPreferredWidth(60);
    floorInfoTable.getColumn("용도").setPreferredWidth(120);
    floorInfoTable.getColumn("면적(㎡)").setPreferredWidth(10);
      
    ownerModel.addColumn("성명(명칭)\n 주민등록번호 \n (부동산등기용등록번호)");
    ownerModel.addColumn("\n\n\n\n\n\n\n\n\n주소");
    ownerModel.addColumn("\n\n\n\n\n\n\n\n소유권지분");
    ownerModel.addColumn("\n\n\n\n변동일 \n 변동원인");
    
    ownerInfoTable.getColumn("성명(명칭)\n 주민등록번호 \n (부동산등기용등록번호)").setPreferredWidth(100);
    ownerInfoTable.getColumn("\n\n\n\n\n\n\n\n\n주소").setPreferredWidth(100);
    ownerInfoTable.getColumn("\n\n\n\n\n\n\n\n소유권지분").setPreferredWidth(18);
    
    
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    floorInfoTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    floorInfoTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
    ownerInfoTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
    
    MultiLineHeaderRenderer renderer = new MultiLineHeaderRenderer();
    Enumeration e = ownerInfoTable.getColumnModel().getColumns();
    
    while (e.hasMoreElements()) {
      ((TableColumn) e.nextElement()).setHeaderRenderer(renderer);
    }
    
    getContentPane().add(jScrollPane3);
    add(jScrollPane3,BorderLayout.CENTER);
    
    ownerInfoTable.getTableHeader().setReorderingAllowed(false);
    ownerInfoTable.setRowHeight(38);
    
    floorInfoTable.setAutoCreateRowSorter(true); // 층 테이블 정렬기능
    ownerInfoTable.setAutoCreateRowSorter(true); // 소유자 테이블 정렬기능
    
    jScrollPane3.setViewportView(ownerInfoTable);
    
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
        .addGroup(layout.createSequentialGroup()
            .addGap(110, 110, 110)
            .addComponent(entirLbl, GroupLayout.PREFERRED_SIZE, 710, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(returnBtn)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(reviseBtn)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(printBtn)
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(regstrLbl)
                        .addComponent(platLocLbl))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(platLocField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                            .addComponent(jiBunLbl)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jiBunField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(platNewLocLbl)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(platNewLocField, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(regstrField, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
                            .addGap(29, 29, 29)
                            .addComponent(bldNMLbl)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(bldNMField)
                            .addGap(18, 18, 18)
                            .addComponent(spcmtLbl)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(spcmtField, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(floorInfoLbl)
                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 515, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(ownerInfoLbl)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(archAreaLbl)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(archAreaField))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(bcRatLbl)
                                        .addGap(24, 24, 24)
                                        .addComponent(bcRatField, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField15, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(platAreaLbl)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(platAreaField, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel26)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField17, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(totAreaLbl)
                                    .addGap(58, 58, 58)
                                    .addComponent(totAreaField, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(vlRatLbl)
                                    .addGap(59, 59, 59)
                                    .addComponent(vlRatField, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(vlRatEstmTotAreaLbl)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(vlRatEstmTotAreaField, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel27)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField20))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(strctLbl, GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel13))
                                        .addComponent(heitLbl))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBox1, GroupLayout.Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(strctNMComboBox, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(strctCDComboBox, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(heitField))))
                            .addGap(30, 30, 30)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel28, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField25))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(roofLbl)
                                            .addComponent(jLabel14))
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(roofNMComboBox, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(roofCDComboBox, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
                                        .addGap(9, 9, 9)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(mainPurpsLbl, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(mainPurpsNMComboBox, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(mainPurpsCDComboBox, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                                    .addGap(25, 25, 25)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(atchBldLbl)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(atchBldField))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(grndFlrCntLbl)
                                                .addComponent(jLabel15))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jComboBox4, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(grndFlrField)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(grndFlrNMComboBox, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))))))))))
            .addContainerGap())
        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buildInfoLbl)
            .addGap(508, 508, 508))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(31, 31, 31)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(entirLbl, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addComponent(returnBtn)
                .addComponent(reviseBtn)
                .addComponent(printBtn))
            .addGap(18, 18, 18)
            .addComponent(buildInfoLbl)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(regstrLbl)
                .addComponent(regstrField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(bldNMLbl)
                .addComponent(spcmtLbl)
                .addComponent(bldNMField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(spcmtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(platLocLbl)
                .addComponent(jiBunLbl)
                .addComponent(platNewLocLbl)
                .addComponent(platLocField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(jiBunField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(platNewLocField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGap(30, 30, 30)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(jComboBox4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(grndFlrCntLbl)
                                .addComponent(grndFlrField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(grndFlrNMComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(atchBldLbl)
                                .addComponent(atchBldField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel28)
                                .addComponent(jTextField25, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(totAreaLbl)
                                        .addComponent(totAreaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18))
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel13)
                                            .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(platAreaLbl)
                                            .addComponent(platAreaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(archAreaLbl)
                                            .addComponent(archAreaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(vlRatEstmTotAreaLbl)
                                            .addComponent(vlRatEstmTotAreaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(vlRatField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bcRatLbl)
                                        .addComponent(bcRatField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(vlRatLbl))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel20)
                                            .addComponent(jTextField15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel26)
                                            .addComponent(jTextField17, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(strctLbl)
                                        .addComponent(strctNMComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(strctCDComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(heitLbl)
                                        .addComponent(heitField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel27)
                                        .addComponent(jTextField20, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
                    .addGap(26, 26, 26)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(floorInfoLbl)
                        .addComponent(ownerInfoLbl)))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(mainPurpsLbl)
                        .addComponent(mainPurpsNMComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mainPurpsCDComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(roofLbl)
                        .addComponent(roofNMComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(roofCDComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
            .addGap(13, 13, 13)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
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
  
  public void setDetailForm(DetailForm detailForm) {
    this.detailForm = detailForm;
  }
  
  public void setBuildInfo(BuildInfo buildInfo) {
    this.buildInfo = buildInfo;
  }
  
  public void setMember(Member member) {
    this.member = member;
  }
  
  public static void main(String args[]) {
    // invokeLater는 전달된 Runnable 객체가 event dispatch thread에서 실행되도록 큐에 넣고 곧바로 리턴한다
    EventQueue.invokeLater(new Runnable() {
        public void run() {
            new DetailForm().setVisible(true);
        }
    });

  }

  // 동작이벤트 처리 메서드
  @Override
  public void actionPerformed(ActionEvent e) {
    Object select = e.getSource();
    
    if(select==returnBtn){  // 돌아가기 버튼을 눌렀을 때 returnSearchPage 호출
      detailForm.dispose();
      searchPage.setVisible(true);
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
    
    if(select == reviseBtn) {
      try {
        updateInfo();
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
      try {  // 만약 type 오류가 나면 처리해야 함. ( 면적부분에 텍스트를 쓸 때)
        
      Build reviseBuildInfo = this.build;
      reviseBuildInfo.setBldNM(bldNMField.getText());  // 건물명(명칭)
      reviseBuildInfo.setSpcmt(spcmtField.getText());  // 특이사항
  
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
      reviseBuildInfo.setVlRat(new BigDecimal(vlRatField.getText())); // 용적률
      reviseBuildInfo.setHeit(Float.parseFloat(heitField.getText()));            // 높이
      reviseBuildInfo.setRoofCD(roofCDComboBox.getSelectedItem().toString());    // 지붕구조코드
      reviseBuildInfo.setAtchBldCnt(Integer.parseInt(atchBldField.getText()));  // 부속건축물 수
      
      
       // 업데이트 확인창 띄우기
      String[] buttons = {"확인", "취소하기"};
      
      int checkStatus = 0;
      checkStatus = JOptionPane.showOptionDialog(null, "업데이트 하시겠습니까?", "업데이트", 
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "확인");
      // 확인을 눌렀을 때 0, 취소를 눌렀을 때 1
      
      if(checkStatus == 0) { 
          dbBuildDetail.updateBuild(reviseBuildInfo);
      }

      } catch (Exception e) {
      
      }
    } else {
      JOptionPane.showMessageDialog(reviseBtn, "관리자 등급만 업데이트 할 수 있습니다.");
    }
  }

  
  // 엑셀에 건축물 정보 저장 후 인쇄하기 (Poi 라이브러리 사용) 
  public void printWork(Build build) throws IOException, SQLException {
    
    FileInputStream fis = new FileInputStream("C:/Users/seoin_01/Desktop/project/Origin_xlxs/일반건축물대장.xlsx");
    Workbook workbook = new XSSFWorkbook(fis);
    
    Sheet sheet1 = workbook.getSheetAt(0); // 시트가 1개일때 getSheetAt(0);
    Sheet sheet2 = workbook.getSheetAt(1); // 층별 정보가 7개가 넘은 경우. 소유자 정보가 3개가 넘은 경우. 나머지 층별 정보를 sheet2에 넣고 for문으로 계속 출력.
    
    sheet1.getRow(4).getCell(2).setCellValue(regstrField.getText()); // 고유번호
    sheet1.getRow(4).getCell(8).setCellValue(bldNMField.getText()); // 명칭(건물명)
    sheet1.getRow(4).getCell(11).setCellValue(spcmtField.getText()); // 특이사항
    
    sheet1.getRow(7).getCell(2).setCellValue(platLocField.getText()); // 대지위치
    sheet1.getRow(7).getCell(6).setCellValue(jiBunField.getText()); // 지번
    sheet1.getRow(7).getCell(9).setCellValue(platNewLocField.getText()); // 도로명주소
    
    sheet1.getRow(9).getCell(2).setCellValue(platAreaField.getText()); // 대지 면적
    sheet1.getRow(9).getCell(4).setCellValue(totAreaField.getText()); // 연면적
    sheet1.getRow(9).getCell(6).setCellValue(""); // 지역
    sheet1.getRow(9).getCell(7).setCellValue(""); // 지구
    sheet1.getRow(9).getCell(9).setCellValue(""); // 구역
    
    sheet1.getRow(11).getCell(2).setCellValue(archAreaField.getText());  // 건축면적
    sheet1.getRow(11).getCell(4).setCellValue(vlRatEstmTotAreaField.getText()); // 용적률 산정용 연면적
    
    sheet1.getRow(11).getCell(6).setCellValue(strctNMComboBox.getSelectedItem().toString());   // 주 구조명
    sheet1.getRow(11).getCell(7).setCellValue(mainPurpsNMComboBox.getSelectedItem().toString());   // 주 용도명
    
    sheet1.getRow(11).getCell(10).setCellValue(build.getGrndFlrCNT()); // 지상층
    sheet1.getRow(11).getCell(12).setCellValue(build.getUgrndFlrCNT()); // 지하층
    
    sheet1.getRow(13).getCell(2).setCellValue(bcRatField.getText()); // 건폐율
    sheet1.getRow(13).getCell(4).setCellValue(vlRatField.getText()); // 용적률
    sheet1.getRow(13).getCell(6).setCellValue(heitField.getText()); // 높이
    sheet1.getRow(13).getCell(7).setCellValue(roofNMComboBox.getSelectedItem().toString()); // 지붕구조
    sheet1.getRow(13).getCell(9).setCellValue(atchBldField.getText()); // 부속건축물 수
    
    sheet1.getRow(16).getCell(2).setCellValue("");    // 공간 면적 합계
    sheet1.getRow(16).getCell(4).setCellValue("");    // 공개 공지 면적
    sheet1.getRow(16).getCell(6).setCellValue("");    // 쌈지 공원 면적
    sheet1.getRow(16).getCell(7).setCellValue("");    // 공공보행통로
    sheet1.getRow(16).getCell(9).setCellValue("");    // 건축선 후퇴면적
    sheet1.getRow(16).getCell(11).setCellValue("");   // 그 밖의 면적
    
    java.util.List<Floor> floorList = dbBuildDetail.findFlrInfo(build);
    
    int checkSum = 0;       
    for (int i = 21; i < 21+floorList.size(); i++) { 
      // 시트 위치는 21 행부터  층별 모델테이블은 0행부터 가져와서 붙여줘야 함.
      sheet1.getRow(i).getCell(2).setCellValue((String) floorModel.getValueAt(i % 21, 0)); //구분
      sheet1.getRow(i).getCell(3).setCellValue((String) floorModel.getValueAt(i % 21, 1)); //층별
      sheet1.getRow(i).getCell(4).setCellValue((String) floorModel.getValueAt(i % 21, 2)); //구조
      sheet1.getRow(i).getCell(5).setCellValue((String) floorModel.getValueAt(i % 21, 3)); //용도
      
//      BigDecimal bdc = new BigDecimal(model1.getValueAt(i % 21 , 4).toString()); 
//      String platArea = bdc.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      String platArea = new BigDecimal(floorModel.getValueAt(i % 21, 4).toString())
                            .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
      
      sheet1.getRow(i).getCell(6).setCellValue(platArea);                                  //면적
      
      checkSum = checkSum + 1;
      if (checkSum == 7) // checkSum이 7이면 층별정보가 7개.
        break;
    }
    
    java.util.List<Owner> ownerList = dbBuildDetail.findOwnrInfo(build);
    
    int checkSum2 = 0;
    for(int i = 21; i< ownerList.size() + 21; i++) { // 21 23 25행에 이름자리
      sheet1.getRow(21 + (2 * checkSum2)).getCell(7).setCellValue((String) ownerModel.getValueAt(checkSum2, 0)); // 이름
      sheet1.getRow(21 + (2 * checkSum2)).getCell(9).setCellValue((String) ownerModel.getValueAt(checkSum2, 2)); //소유권 지분
      
      checkSum2 = checkSum2 + 1;
      if(checkSum2 == 3) // checkSum2이 3이면 소유자정보 3개 0,1,2
        break;
    }

    // 엑셀파일 인쇄 설정
    PrintSetup print = sheet1.getPrintSetup();
    print.setPaperSize(PrintSetup.A4_PAPERSIZE);
    print.setLandscape(true); // true면 가로방향 , 선언하지 않으면 기본 세로방향
    
    if (floorList.size() <= 7 && ownerList.size() <= 3) {
    try {
      // 만약에 층별정보가 7개 이하거나 소유자정보가 3개 이하이면 추가 대장을 제거한다.
      workbook.removeSheetAt(1);
      
      // 파일출력
      File xlsFile = new File("C:/Users/seoin_01/Desktop/project/src/com/resource/"+build.getBuildingPK()+"_일반건축물대장.xlsx");
      FileOutputStream fileOut = new FileOutputStream(xlsFile);
      workbook.write(fileOut);
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("엑셀파일 저장 및 인쇄 완료");
    }
    
    // 더 출력하기.
    if (floorList.size() > 7 || ownerList.size() > 3) { // 층별정보가 8개 이상이거나 소유자 정보가 4개 이상 일 때
      
      int flrPrintSize = (floorList.size() - 7) / 18;   // 층별 프린트 사이즈
      int ownerPrintSize = (ownerList.size() - 3) / 9;  // 소유자 프린트 사이즈 
      int printSize = 0;                                // printSize는 프린트 할 대장 수
      
        if ((floorList.size() - 7) % 18 > 0) {           // 만약  18개로 나눴는데 0넘는다면 => 층별 정보가 남은 경우
          flrPrintSize = flrPrintSize + 1;                // 한장더 추가한다.
        }
       
        if((2 * ( ownerList.size() - 3)) % 18 > 0) {     // 만약 9개로 나눴는데 0을 넘는다면 => 소유자 정보가 남은 경우
          ownerPrintSize = ownerPrintSize +1;             // 한장더 추가한다.
        }
      
      if (flrPrintSize > ownerPrintSize) {              // 층별 프린트 사이즈와 소유자 프린트 사이즈를 비교한다.  
        printSize = flrPrintSize;                       // 더 큰 수를 printSize에 넣는다.
      } else if (ownerPrintSize > flrPrintSize) {
        printSize = ownerPrintSize;
      }
      
      int flrStartInt = 7;                              // 층별 테이블의 모델에서의 값을 가져올 시작할 위치(배열의 시작위치값)
      int flrCheckSum = 0;                              // 층별 테이블관련  내부 for문에서 갯수 제한. 및 model 데이터 위치 참조
      int ownrStartInt = 3;                             // 소유자 테이블의 모델에서 값을 가져올 시작할 위치(배열의 시작위치값)
      int ownrCheckSum = 0;                             // 소유자 테이블 관련 내부  for문에서 갯수 제한.
      
      // sheet2가 여러장이 될수 있으므로 for문 선언.
      for (int i = 0; i < printSize; i++) {
          workbook.cloneSheet(i+1);  // 1 시트가 복사되었다.(두번째 시트) (시트도 0번부터)
          sheet2.getRow(4).getCell(2).setCellValue(regstrField.getText());  // 고유번호
          sheet2.getRow(4).getCell(8).setCellValue(bldNMField.getText());  // 명칭
          sheet2.getRow(4).getCell(10).setCellValue(spcmtField.getText()); // 특이사항
          
          sheet2.getRow(7).getCell(2).setCellValue(platLocField.getText());  // 대지위치
          sheet2.getRow(7).getCell(6).setCellValue(jiBunField.getText());  // 지번
          
          // 층별 현황 출력
          if (floorList.size() > 7) {
            for (int j = 12; j < 12 + floorList.size()-flrStartInt; j++) {
              sheet2.getRow(j).getCell(2).setCellValue((String) floorModel.getValueAt(flrCheckSum + flrStartInt, 0)); //구분
              sheet2.getRow(j).getCell(3).setCellValue((String) floorModel.getValueAt(flrCheckSum + flrStartInt, 1)); //층별
              sheet2.getRow(j).getCell(4).setCellValue((String) floorModel.getValueAt(flrCheckSum + flrStartInt, 2)); //구조
              sheet2.getRow(j).getCell(5).setCellValue((String) floorModel.getValueAt(flrCheckSum + flrStartInt, 3)); //용도
              String platArea = new BigDecimal(floorModel.getValueAt(flrCheckSum + flrStartInt, 4).toString())
                  .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
              sheet2.getRow(j).getCell(6).setCellValue(platArea);
              
                flrCheckSum = flrCheckSum + 1;
              if(flrCheckSum == 18)  // 18 => 추가건축물대장 층별리스트 크기
                break;
            }
              flrCheckSum = 0;
              flrStartInt = flrStartInt + 18; // 배열 시작위치 재정의
          }
          
          // 소유자 현황 출력
            if(ownerList.size() > 3) {        
              for(int k = 12; k < 12 + ownerList.size() - ownrStartInt; k++) {
                if(2 * ownrCheckSum < ownerList.size()) {
                sheet2.getRow(12 + (2 * ownrCheckSum)).getCell(7).setCellValue((String) ownerModel.getValueAt(ownrCheckSum + ownrStartInt, 0)); // 이름
                sheet2.getRow(12 + (2 * ownrCheckSum)).getCell(9).setCellValue((String) ownerModel.getValueAt(ownrCheckSum + ownrStartInt, 2)); //소유권 지분
                
                ownrCheckSum = ownrCheckSum + 1;
              if(ownrCheckSum == 9) 
                break;
              }
              }
              ownrCheckSum = 0; 
              ownrStartInt = ownrStartInt + 9;
            }
            sheet2.setSelected(true); // 해당 시트를 선택함! => 모든 시트를 출력하기 위해서
            sheet2 = workbook.getSheetAt(i+2); // sheet2 객체에 새로 만든 비어있는시트를 넣고 종료 됨

            // 엑셀파일 인쇄 설정
            print = sheet2.getPrintSetup();
            print.setPaperSize(PrintSetup.A4_PAPERSIZE);
            print.setLandscape(true);      // true면 가로방향 , 선언하지 않으면 기본 세로방향
        }

      workbook.removeSheetAt(printSize + 1); // 마지막 비어있는 시트 제거
      
      try {
        File xlsFile = new File("C:/Users/seoin_01/Desktop/project/src/com/resource/"+build.getBuildingPK()+"_일반건축물대장.xlsx");
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
      
      desktop.print(new File("C:/Users/seoin_01/Desktop/project/src/com/resource/"+build.getBuildingPK()+"_일반건축물대장.xlsx"));
    
  }
  

  // 상세정보 폼이 실행되면 SearchPage에서 BuildInfo 객체를 받아 Building(건축물대장)의 정보를 조회해서 뿌려준다.
  public void inputBuildInfo(BuildInfo buildInfo) throws SQLException {
    entirLbl.setText(buildInfo.getSidoNM() + " " + buildInfo.getSigunguNM() + " " + buildInfo.getBjdongNM() + " " +
                     buildInfo.getBunNum() + "-" + buildInfo.getJiNum() + " " + buildInfo.getBldNM());

    
    // 리스트에서 선택한 건물 정보로 건축물 대장을 찾음.(대장 구분코드, 대장종류, 대장 PK, bun, ji 정보로 조회)
    Build build = dbBuildDetail.findBuild(
        buildInfo.getBldTypeGBCD(), buildInfo.getBuildingPK(), buildInfo.getRegstrGBCD(), buildInfo.getRegstrKINKCD(),
        buildInfo.getSidoNM(),buildInfo.getSigunguNM(),buildInfo.getBjdongNM(),
        buildInfo.getBunNum(),buildInfo.getJiNum());
    
    
    regstrField.setText(build.getRegstrNo());
    bldNMField.setText(build.getBldNM());
    spcmtField.setText(build.getSpcmt());
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
      platAreaField.setText("0");
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
      totAreaField.setText("0");
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
      archAreaField.setText("0");
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
      vlRatEstmTotAreaField.setText("0");
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
      bcRatField.setText("0");
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
      vlRatField.setText("0");
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    // 높이
    try {
      String stringHeit = Float.toString(build.getHeit());
      heitField.setText(stringHeit);
    } catch (NullPointerException e) {
      System.out.println("높이 데이터가 없습니다." + e);
      heitField.setText("0");
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
      atchBldField.setText("0");
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    // 조경면적, 공개공간면적, 건축선 후퇴면적, 건축선 후퇴거리 컬럼명 몰라서 모르겠음....
    jTextField15.setText("");
    jTextField17.setText("");
    jTextField20.setText("");
    jTextField25.setText("");
    
    
    flrTableSet(build);
    ownrTableSet(build);
    
    this.build = build;
    this.buildInfo = buildInfo;
    
  } // inputBuildInfo() 끝

  
  
  /* 코드나 명칭을 전달하면 명칭이나 코드를 리턴하는 메서드 관련목록 */  
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
      
      ownerModel.addRow(ownerListArray);
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



