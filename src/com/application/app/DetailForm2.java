package com.application.app;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
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
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.application.db.DBBuildDetail;
import com.application.vo.Build;
import com.application.vo.BuildInfo;

// DetailForm2 => 총괄표제부  대장종류 (번호) : 1
// 각 항목 변수이름 지정.
// 엑셀 저장 출력 완료
public class DetailForm2 extends JFrame implements ActionListener{
  
  MainProcess main;
  BuildInfo buildInfo;
  Build build;
  SearchBuild searchPage;
  DetailForm2 detailForm2;
  DBBuildDetail dbBuildDetail = new DBBuildDetail();
  
  public DetailForm2() {
    dtlform();
    returnBtn.addActionListener(this);
    printBtn.addActionListener(this);
    
    jComboBox1.addActionListener(this);
    jComboBox2.addActionListener(this);
    jComboBox4.addActionListener(this);
    
    mainPurpsNMComboBox.addActionListener(this);
    mainPurpsCDComboBox.addActionListener(this);
    
    
    setVisible(true);
  }
  
  JButton returnBtn = new JButton(); // 돌아가기 버튼
  JButton alterBtn = new JButton();
  JButton printBtn = new JButton(); // 출력버튼
  
  JLabel entirLbl = new JLabel();
  JLabel titleLbl = new JLabel();
  JLabel buildInfoLbl = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JLabel regstrLbl = new JLabel();
  JLabel platLocLbl = new JLabel();
  JLabel jiBunLbl = new JLabel();
  JTextField platLocField = new JTextField();
  JTextField jiBunField = new JTextField();
  JLabel platAreaLbl = new JLabel();
  JLabel archAreaLbl = new JLabel();
  JLabel bcRatLbl = new JLabel();
  JLabel totAreaLbl = new JLabel();
  JLabel vlRatEstmTotAreaLbl = new JLabel();
  JLabel mainBldCntLbl = new JLabel();
  JLabel mainPurpsLbl = new JLabel();
  JLabel vlRatLbl = new JLabel();
  JLabel hoCntLbl = new JLabel();
  JLabel totPkngCntLbl = new JLabel();
  JLabel atchBldCntLbl = new JLabel();
  JTextField platAreaField = new JTextField();
  JTextField regstrField = new JTextField();
  JLabel bldNMLbl = new JLabel();
  JLabel spcmtLbl = new JLabel();
  JTextField bldNMField = new JTextField();
  JTextField spcmtField = new JTextField();
  JTextField archAreaField = new JTextField();
  JTextField totAreaField = new JTextField();
  JTextField vlRatEstmTotAreaField = new JTextField();
  JTextField bcRatField = new JTextField();
  JTextField vlRatField = new JTextField();
  JTextField hoCntField = new JTextField();
  JTextField atchBldCntField = new JTextField();
  JComboBox mainPurpsNMComboBox = new JComboBox();  // 주용도 콤보박스
  JComboBox mainPurpsCDComboBox = new JComboBox(); // 주용도코드 콤보박스
  JTextField mainBldCntField = new JTextField();
  JTextField totPkngCntField = new JTextField();

  JLabel jLabel13 = new JLabel();
  JComboBox jComboBox1 = new JComboBox(); // 지역 콤보박스
  JLabel jLabel14 = new JLabel();
  JComboBox jComboBox2 = new JComboBox(); // 지구 콤보박스
  JLabel jLabel15 = new JLabel();
  JComboBox jComboBox4 = new JComboBox(); // 구역 콤보박스

  JLabel jLabel20 = new JLabel();
  JTextField jTextField15 = new JTextField();
  JLabel jLabel26 = new JLabel();
  JTextField jTextField17 = new JTextField();
  JLabel jLabel27 = new JLabel();
  JTextField jTextField20 = new JTextField();
  JLabel jLabel28 = new JLabel();
  JTextField jTextField25 = new JTextField();
  
  JScrollPane jScrollPane = new JScrollPane(); 
  DefaultTableModel model = new DefaultTableModel(); //건축물 현황 모델
  JTable dongBuildInfoTable = new JTable(model);  // 건축물 현황 jTable

  
  @SuppressWarnings({"unchecked", "rawtypes"})
  private void dtlform() {

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    entirLbl.setText("해당 주소의 전체주소 ");             // 상단 전체주소 라벨
    titleLbl.setText("총괄표제부");                   // 총괄표제부 라벨
    buildInfoLbl.setText("건축물현황");               // 하단 건축물현황 라벨

    returnBtn.setText("돌아가기");                   // 돌아가기 버튼
    alterBtn.setText("수정하기");                    // 수정하기 버튼
    printBtn.setText("엑셀 저장 후 인쇄하기");            // 인쇄하기 버튼

    regstrLbl.setText("고유번호");                     // 고유번호 라벨
    regstrField.setText("고유번호(시군구법정동-대지구분-번지)"); // 고유번호 텍스트구역
    platLocLbl.setText("대지위치");                     // 대지위치 라벨
    platLocField.setText("대지위치");                 // 대지위치 텍스트구역
    jiBunLbl.setText("지번");                        // 지번 라벨
    jiBunField.setText("지번");                    // 지번 텍스트구역
    bldNMLbl.setText("명칭");                        // 명칭 라벨(건물명)
    bldNMField.setText("명칭");                    // 명칭 텍스트구역(건물
    spcmtLbl.setText("특이사항");                    // 특이사항 라벨
    spcmtField.setText("특이사항");                 // 특이사항 텍스트구역
    platAreaLbl.setText("대지면적");                     // 대지면적 라벨
    platAreaField.setText("대지면적");                 // 대지면적 텍스트구역
    totAreaLbl.setText("연면적");                      // 연면적 라벨
    totAreaField.setText("연면적");                   // 연면적 텍스트구역
    
    jLabel13.setText("지역");                       // 지역 라벨
    jComboBox1.setModel(new DefaultComboBoxModel(new String[] { "지역" }));
    jLabel14.setText("지구");                       // 지구 라벨 
    jComboBox2.setModel(new DefaultComboBoxModel(new String[] { "지구" }));
    jLabel15.setText("구역");                       // 구역 라벨
    jComboBox4.setModel(new DefaultComboBoxModel(new String[] { "구역" })); 

    archAreaLbl.setText("건축면적");                    // 건축면적 라벨
    archAreaField.setText("건축면적");                  // 건축면적 텍스트구역
    vlRatEstmTotAreaLbl.setText("용적률산정용연면적");              // 용적률산정용연면적 라벨
    vlRatEstmTotAreaField.setText("용적률산정용");              // 용적률산정용 텍스트구역
    mainBldCntLbl.setText("건축물 수");                    // 건축물 수 라벨
    mainBldCntField.setText("건축물 수");                // 건축물 수 텍스트구역
    mainPurpsLbl.setText("주용도");                      // 주용도 라벨
    mainPurpsNMComboBox.setModel(new DefaultComboBoxModel(new String[] { "주용도명" }));
    mainPurpsCDComboBox.setModel(new DefaultComboBoxModel(new String[] { "코드" }));

    bcRatLbl.setText("건폐율");                      // 건폐율 라벨
    bcRatField.setText("건폐율");                  // 건폐율 텍스트구역
    vlRatLbl.setText("용적률");                      // 용적률 라벨
    vlRatField.setText("용적률");                  // 용적률 텍스트구역
    hoCntLbl.setText("총 호수");                      // 총 호수 라벨
    hoCntField.setText("총 호수");                 // 총 호수 텍스트구역
    totPkngCntLbl.setText("총 주차 대수");                  // 총 주차 대수 라벨
    totPkngCntField.setText("총 주차 대수");               // 총 주차대수 텍스트구역
    atchBldCntLbl.setText("부속건축물");                   // 부석건축물 라벨
    atchBldCntField.setText("부속건축물");               // 부석건축물 텍스트구역

    jLabel20.setText("조경면적");                     // 조경면적 라벨
    jTextField15.setText("조경면적");                 // 조경면적 텍스트구역
    jLabel26.setText("공개 공간의 면적");                // 공개 공간의 면적 라벨
    jTextField17.setText("공개공간면적");              // 공개공간면적 텍스트구역
    jLabel27.setText("건축선 후퇴면적");                // 건축선 후퇴면적 라벨
    jTextField20.setText("건축선 후퇴면적");            // 건축선 후퇴면적 텍스트구역
    jLabel28.setText("건축선 후퇴거리");                // 건축선 후퇴거리 라벨
    jTextField25.setText("건축선 후퇴거리");            // 건축선 후퇴거리 텍스트구역

    vlRatEstmTotAreaLbl.setFont(new java.awt.Font("굴림", 0, 10)); // NOI18N
    jLabel26.setFont(new java.awt.Font("굴림", 0, 10)); // NOI18N
    totPkngCntLbl.setFont(new java.awt.Font("굴림", 0, 10)); // NOI18N
    titleLbl.setFont(new java.awt.Font("굴림", 1, 15)); // NOI18N
    
    
    model.addColumn("구분");
    model.addColumn("명칭");
    model.addColumn("도로명주소");
    model.addColumn("건축물 주구조");
    model.addColumn("건축물 지붕");
    model.addColumn("층수");
    model.addColumn("용도");
    model.addColumn("연면적(㎡)");
    model.addColumn("변동일");
    model.addColumn("변동원인");
    
      jScrollPane.setViewportView(dongBuildInfoTable);
      add(jScrollPane,BorderLayout.CENTER);
      
      dongBuildInfoTable.getTableHeader().setReorderingAllowed(false);
    
    jScrollPane.setViewportView(dongBuildInfoTable);
    
    dongBuildInfoTable.getColumn("구분").setPreferredWidth(20);
    dongBuildInfoTable.getColumn("명칭").setPreferredWidth(30);
    dongBuildInfoTable.getColumn("도로명주소").setPreferredWidth(200);
    dongBuildInfoTable.getColumn("층수").setPreferredWidth(20);
    dongBuildInfoTable.getColumn("연면적(㎡)").setPreferredWidth(40);
    
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    dongBuildInfoTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // "구분" 컬럼 중앙정렬
    dongBuildInfoTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // "명칭" 컬럼 중앙정렬  
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(regstrLbl)
                                .addComponent(platLocLbl))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(platLocField)
                                    .addGap(650, 650, 650))
                                .addComponent(regstrField)))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spcmtLbl)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(spcmtField, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)))
                    .addGap(70, 70, 70))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel20)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jTextField15, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(platAreaLbl)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(platAreaField))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(archAreaLbl)
                                                .addComponent(bcRatLbl))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(archAreaField)
                                                .addComponent(bcRatField))))
                                    .addGap(30, 30, 30)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel26)
                                            .addGap(31, 31, 31)
                                            .addComponent(jTextField17, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(vlRatLbl)
                                            .addGap(71, 71, 71)
                                            .addComponent(vlRatField, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(vlRatEstmTotAreaLbl)
                                            .addGap(17, 17, 17)
                                            .addComponent(vlRatEstmTotAreaField, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(totAreaLbl)
                                            .addGap(71, 71, 71)
                                            .addComponent(totAreaField, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
                                    .addGap(25, 25, 25)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jiBunLbl)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jiBunField, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                            .addGap(15, 15, 15))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                            .addComponent(mainBldCntLbl, GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel13))
                                                        .addComponent(hoCntLbl))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jComboBox1, 0, 132, Short.MAX_VALUE)
                                                        .addComponent(mainBldCntField)
                                                        .addComponent(hoCntField)))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel27)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextField20, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
                                            .addGap(45, 45, 45)))
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel28, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField25, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
                                            .addGap(205, 205, 205))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel15)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBox4, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(totPkngCntLbl)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(15, 15, 15)
                                                    .addComponent(bldNMLbl))
                                                .addComponent(mainPurpsLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(bldNMField, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(totPkngCntField, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(atchBldCntLbl)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(atchBldCntField, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(mainPurpsNMComboBox, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(mainPurpsCDComboBox, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))))))))
                                .addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 1075, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(110, 110, 110)
                            .addComponent(entirLbl, GroupLayout.PREFERRED_SIZE, 710, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(returnBtn)
                            .addGap(18, 18, 18)
                            .addComponent(alterBtn)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(printBtn))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(543, 543, 543)
                            .addComponent(buildInfoLbl)))
                    .addGap(0, 37, Short.MAX_VALUE)))
            .addContainerGap())
        .addGroup(layout.createSequentialGroup()
            .addGap(537, 537, 537)
            .addComponent(titleLbl)
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(31, 31, 31)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(entirLbl, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addComponent(returnBtn)
                .addComponent(alterBtn)
                .addComponent(printBtn))
            .addGap(14, 14, 14)
            .addComponent(titleLbl)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(regstrLbl)
                .addComponent(regstrField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(platLocLbl)
                .addComponent(platLocField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(bldNMField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(spcmtLbl)
                .addComponent(spcmtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(jiBunLbl)
                .addComponent(jiBunField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(bldNMLbl))
            .addGap(23, 23, 23)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jComboBox4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(mainPurpsCDComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mainPurpsNMComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mainPurpsLbl))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(atchBldCntLbl)
                        .addComponent(atchBldCntField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(jTextField25, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(totAreaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(platAreaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(totAreaLbl)
                            .addComponent(platAreaLbl)))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(vlRatEstmTotAreaLbl)
                                .addComponent(archAreaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(archAreaLbl))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(bcRatLbl)
                                .addComponent(bcRatField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(vlRatLbl))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(jTextField15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel26)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(mainBldCntLbl)
                                .addComponent(mainBldCntField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(vlRatEstmTotAreaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(hoCntLbl)
                                .addComponent(hoCntField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(totPkngCntLbl)
                                .addComponent(totPkngCntField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(vlRatField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27)
                                .addComponent(jTextField20, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField17, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
            .addGap(33, 33, 33)
            .addComponent(buildInfoLbl)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
            .addGap(30, 30, 30))
    );

    pack();
}
  
  public void setMain(MainProcess main) {
    this.main = main;
  }
  
  public void setSearchPage(SearchBuild mainPage) {
    this.searchPage = mainPage;
  }
  
  public void setDetailForm2(DetailForm2 detailForm2) {
    this.detailForm2 = detailForm2;
  }
  
  public void setBuildInfo(BuildInfo buildInfo) {
    this.buildInfo = buildInfo;
  }
  
  public static void main(String args[]) {
    
    
    EventQueue.invokeLater(new Runnable() {
        public void run() {
            new DetailForm2().setVisible(true);
        }
    });

  }


  
  // 동작이벤트 처리 메서드
  @Override
  public void actionPerformed(ActionEvent e) {
    Object select = e.getSource();
    
    if(select==returnBtn){  // 돌아가기 버튼을 눌렀을 때 returnSearchPage 호출
      this.detailForm2.dispose();
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
  }

  
    // 엑셀에 건축물 정보 저장 후 인쇄하기 (Poi 라이브러리 사용) 
  private void printWork(Build build) throws IOException, SQLException {
    System.out.println("건축물 정보 제대로 받았는지 확인 : "+build);
    
    FileInputStream fis = new FileInputStream("C:/Users/seoin_01/Desktop/project/Origin_xlxs/총괄표제부.xlsx");
    Workbook workbook = new XSSFWorkbook(fis);
    
    Sheet sheet1 = workbook.getSheetAt(0); // 시트1번
    Sheet sheet2 = workbook.getSheetAt(1); // 동별 정보가 7개가 넘은 경우. 나머지 동별 정보를 sheet2에 넣고 for문으로 계속 출력.
    
    sheet1.getRow(4).getCell(2).setCellValue(regstrField.getText()); // 고유번호
    
    sheet1.getRow(7).getCell(2).setCellValue(platLocField.getText()); // 대지위치
    sheet1.getRow(7).getCell(6).setCellValue(jiBunField.getText()); // 지번
    sheet1.getRow(7).getCell(7).setCellValue(bldNMField.getText()); // 건축물명칭
    sheet1.getRow(7).getCell(10).setCellValue(spcmtField.getText()); // 특이사항
    
    sheet1.getRow(9).getCell(2).setCellValue(platAreaField.getText()); // 대지면적
    sheet1.getRow(9).getCell(4).setCellValue(totAreaField.getText()); // 연면적
    sheet1.getRow(9).getCell(6).setCellValue(""); // 지역
    sheet1.getRow(9).getCell(7).setCellValue(""); // 지구
    sheet1.getRow(9).getCell(10).setCellValue(""); // 구역
    
    sheet1.getRow(11).getCell(2).setCellValue(archAreaField.getText()); // 건축면적
    sheet1.getRow(11).getCell(4).setCellValue(vlRatEstmTotAreaField.getText()); // 용적률 산정용
    sheet1.getRow(11).getCell(6).setCellValue(mainBldCntField.getText()); // 건축물 수
    sheet1.getRow(11).getCell(7).setCellValue(mainPurpsNMComboBox.getSelectedItem().toString()); // 주용도
    
    sheet1.getRow(13).getCell(2).setCellValue(bcRatField.getText()); // 건폐율
    sheet1.getRow(13).getCell(4).setCellValue(vlRatField.getText()); // 용적률
    sheet1.getRow(13).getCell(6).setCellValue(hoCntField.getText()); // 총 호수
    sheet1.getRow(13).getCell(7).setCellValue(totPkngCntField.getText()); // 총 주차 대 수
    sheet1.getRow(13).getCell(10).setCellValue(atchBldCntField.getText()); // 부속 건축물
    
    sheet1.getRow(16).getCell(4).setCellValue(""); // 공개 공지 면적
    sheet1.getRow(16).getCell(6).setCellValue(""); // 쌈지 공원 면적
    sheet1.getRow(16).getCell(7).setCellValue(""); // 공공보행통로 면적
    sheet1.getRow(16).getCell(10).setCellValue(""); // 건축선 후퇴 면적
    sheet1.getRow(16).getCell(11).setCellValue(""); // 그 밖의 면적
    
    java.util.List<Build> buildList = dbBuildDetail.findDongBuild();
    
    // 처음장 건축물 7개 
    int checkSum = 0;
    for (int i = 20; i < 20 + buildList.size(); i++) {
      // 시트 위치는 20 행부터 모델테이블은 0행부터 가져와서 붙여줘야 함.
      sheet1.getRow(i).getCell(2).setCellValue((String) model.getValueAt(i % 20, 0)); // 구분
      sheet1.getRow(i).getCell(3).setCellValue((String) model.getValueAt(i % 20, 1)); // 명칭
      sheet1.getRow(i).getCell(4).setCellValue((String) model.getValueAt(i % 20, 2)); // 도로명주소
      sheet1.getRow(i).getCell(5).setCellValue((String) model.getValueAt(i % 20, 3)); // 건축물 주구조
      sheet1.getRow(i).getCell(6).setCellValue((String) model.getValueAt(i % 20, 4)); // 건축물 지붕
      sheet1.getRow(i).getCell(7).setCellValue((int) model.getValueAt(i % 20, 5)); // 층수
      sheet1.getRow(i).getCell(8).setCellValue((String) model.getValueAt(i % 20, 6)); // 용도
      
//      String platArea = new BigDecimal(model.getValueAt(i % 20, 4).toString())
//                            .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
      sheet1.getRow(i).getCell(9).setCellValue((String) model.getValueAt(i % 20, 4).toString());                              // 연면적
      sheet1.getRow(i).getCell(10).setCellValue((String) model.getValueAt(i % 20, 8)); // 변동일
      sheet1.getRow(i).getCell(11).setCellValue((String) model.getValueAt(i % 20, 9)); // 변동원인
      
      checkSum = checkSum + 1;
      if (checkSum == 7) // checkSum이 7이면 층별정보가 7개.
        break;
      
    }
    // 엑셀파일 인쇄 설정
    PrintSetup print = sheet1.getPrintSetup();
    print.setPaperSize(PrintSetup.A4_PAPERSIZE);
    print.setLandscape(true); // true면 가로방향 , 선언하지 않으면 기본 세로방향
    
    if (buildList.size() <= 7 ) {
      try {
        // 만약에 동 정보가 7개 이하이면 추가대장 시트를 제거하고 출력한다.
        workbook.removeSheetAt(1);
        
        // 파일출력
        File xlsFile = new File("C:/Users/seoin_01/Desktop/project/src/com/resource/"+regstrField.getText()+"_총괄표제부.xlsx");
        FileOutputStream fileOut = new FileOutputStream(xlsFile);
        workbook.write(fileOut);
        
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println("엑셀파일 저장 및 인쇄 완료.");
      }
    
    // 더 출력하기.
    if (buildList.size() > 7) { // 동별 정보가 7개 이상일때,
    
      int printSize = (buildList.size() - 7) / 18;   // 동별 프린트 사이즈
      
        if ((buildList.size() - 7) % 18 > 0) {            // 만약  18개로 나눴는데 0이 아니라면 => 동별 정보가 남은 경우
          printSize = printSize + 1;                      // 한장더 추가한다.
        }
      
      int dongStartInt = 7;                              // 동별 테이블의 모델에서의 값을 가져올 시작할 위치(배열의 시작위치값)
      int dongCheckSum = 0;                              // 동별 테이블관련  내부 for문에서 갯수 제한. 및 model 데이터 위치 참조값
     
      for (int i = 0; i < printSize; i++) {
        workbook.cloneSheet(i+1);  // 1 시트가 복사되었다.(두번째 시트) (시트도 0번부터)
        
        sheet2.getRow(4).getCell(2).setCellValue(regstrField.getText()); // 고유번호
        
        sheet2.getRow(7).getCell(2).setCellValue(platLocField.getText()); // 대지위치
        sheet2.getRow(7).getCell(6).setCellValue(jiBunField.getText()); // 지번
        sheet2.getRow(7).getCell(7).setCellValue(bldNMField.getText()); // 건축물명칭
        sheet2.getRow(7).getCell(10).setCellValue(spcmtField.getText()); // 특이사항
        
        // 동별 현황을 출력
          for (int j = 11; j < 11 + buildList.size()-dongStartInt; j++) {
            sheet2.getRow(j).getCell(2).setCellValue((String) model.getValueAt(dongCheckSum + dongStartInt, 0)); // 구분
            sheet2.getRow(j).getCell(3).setCellValue((String) model.getValueAt(dongCheckSum + dongStartInt, 1)); // 명칭
            sheet2.getRow(j).getCell(4).setCellValue((String) model.getValueAt(dongCheckSum + dongStartInt, 2)); // 도로명주소
            sheet2.getRow(j).getCell(5).setCellValue((String) model.getValueAt(dongCheckSum + dongStartInt, 3)); // 건축물 주구조
            sheet2.getRow(j).getCell(6).setCellValue((String) model.getValueAt(dongCheckSum + dongStartInt, 4)); // 건축물 지붕
            sheet2.getRow(j).getCell(7).setCellValue((int) model.getValueAt(dongCheckSum + dongStartInt, 5)); // 층수
            sheet2.getRow(j).getCell(8).setCellValue((String) model.getValueAt(dongCheckSum + dongStartInt, 6)); // 용도
            
            String platArea = new BigDecimal(model.getValueAt(dongCheckSum + dongStartInt, 7).toString())
                                  .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            sheet2.getRow(j).getCell(9).setCellValue(platArea);                              // 연면적
            sheet2.getRow(j).getCell(10).setCellValue(""); // 변동일
            sheet2.getRow(j).getCell(11).setCellValue(""); // 변동원인
            
            dongCheckSum = dongCheckSum + 1;
            if(dongCheckSum == 18)  // 18 => 추가건축물대장 동별리스트 크기
              break;
          }
          dongCheckSum = 0;
          dongStartInt = dongStartInt + 18; // 모델 시작위치 재정의
      
        sheet2.setSelected(true);          // 해당 시트를 선택함! => 모든 시트를 출력하기 위해서
        sheet2 = workbook.getSheetAt(i+2); // sheet2 객체에 새로 만든 비어있는시트를 넣고 종료 됨

        // 엑셀파일 인쇄 설정
        print = sheet2.getPrintSetup();
        print.setPaperSize(PrintSetup.A4_PAPERSIZE);
        print.setLandscape(true);          // true면 가로방향 , 선언하지 않으면 기본 세로방향
      }
      
      workbook.removeSheetAt(printSize + 1); // 마지막 비어있는 시트 제거
      
      try {
        File xlsFile = new File("C:/Users/seoin_01/Desktop/project/src/com/resource/"+regstrField.getText()+"_총괄표제부.xlsx");
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

    desktop.print(new File("C:/Users/seoin_01/Desktop/project/src/com/resource/"+regstrField.getText()+"_총괄표제부.xlsx"));
  }






  //상세정보 폼이 실행되면 SearchPage에서 BuildInfo 객체를 받아 Building(건축물대장)의 정보를 조회해서 뿌려준다
  public void inputBuildInfo(BuildInfo buildInfo2) throws SQLException {
    entirLbl.setText(buildInfo.getSidoNM() + " " + buildInfo.getSigunguNM() + " " + buildInfo.getBjdongNM() + " " +
                    buildInfo.getBunNum() + "-" + buildInfo.getJiNum() + buildInfo.getBldNM());

    // 리스트에서 선택한 건물 정보로 건축물 대장을 찾음.(대장 구분코드, 대장종류, 대장 PK, bun, ji 정보로 조회)
    Build build = dbBuildDetail.findBuild(
        buildInfo.getBldTypeGBCD(), buildInfo.getBuildingPK(), buildInfo.getRegstrGBCD(), buildInfo.getRegstrKINKCD(),
        buildInfo.getSidoNM(),buildInfo.getSigunguNM(),buildInfo.getBjdongNM(),
        buildInfo.getBunNum(),buildInfo.getJiNum());
    
    regstrField.setText(build.getRegstrNo());
    platLocField.setText(build.getPlatLoC());
    jiBunField.setText(build.getJiBun());
    bldNMField.setText(build.getBldNM());
    spcmtField.setText(build.getSpcmt());
    
    
    // 대지면적
    try {
      BigDecimal bdc = new BigDecimal(build.getPlatArea().toString());
      String stringPlatArea = bdc.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      platAreaField.setText(stringPlatArea);
    } catch (NullPointerException e) {
      System.out.println("대지면적 데이터가 없습니다." + e);
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
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    
    // 건축물 수.
    mainBldCntField.setText(Integer.toString(build.getMainBldCnt()));
    
    
    // 주용도
    setMainPurpsNM(build.getMainPurpsNM());
    
    // 주용도 코드
    setMainPurpsCD(build.getMainPurpsCD());
    
    
    // 건폐율 
    try {
      BigDecimal bdc5 = new BigDecimal(build.getBcRat().toString());
      String stringBcRat = bdc5.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      bcRatField.setText(stringBcRat);
    } catch (NullPointerException e) {
      System.out.println("건폐율 데이터가 없습니다." + e);
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
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    
    // 총 호수.
    hoCntField.setText(Integer.toString(build.getHoCNT()));
    
    // 총 주차 대수.
    totPkngCntField.setText(Integer.toString(build.getTotPkngCNT()));
    
    
    // 부속건축물 수
    try {
      String stringAtchBldCnt = Integer.toString(build.getAtchBldCnt());
      atchBldCntField.setText(stringAtchBldCnt);
    } catch (NullPointerException e) {
      System.out.println("부속건축물 수 데이터가 없습니다."+ e);
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    
    // 조경면적, 공개공간면적, 건축선 후퇴면적, 건축선 후퇴거리 정보 어디에 있는지 모르겠음....
    jTextField15.setText("");
    jTextField17.setText("");
    jTextField20.setText("");
    jTextField25.setText("");
    
    dongTableSet(); // 파라미터로 건물정보 넘기지 않아도 됨, DB처리 클래스에서 내부적으로 값 공유
    
    this.build = build;
    
  }

  // 집합건축물대장 해당하는 건물 동명 정보 리스트를 모델 테이블에 넣어주는 메서드
  private void dongTableSet() throws SQLException {
    
    java.util.List<Build> buildingInfoList = dbBuildDetail.findDongBuild(); 
    Object[] dongListArray = new Object[10]; 
    
    for (int i = 0; i < buildingInfoList.size(); i++) {
      
      if(buildingInfoList.get(i).getMainAtchGBCD().equals("0")) {
        dongListArray[0] = "주";
      } else if(buildingInfoList.get(i).getMainAtchGBCD().equals("1")){
        dongListArray[0] = "부";
      } else {
        dongListArray[0] = "";
      }
      
      dongListArray[1] = buildingInfoList.get(i).getDongNM();
      dongListArray[2] = buildingInfoList.get(i).getPlatNewLoc();
      dongListArray[3] = buildingInfoList.get(i).getStrctNM();
      dongListArray[4] = buildingInfoList.get(i).getStrctNM();
      dongListArray[5] = buildingInfoList.get(i).getUpDownCNT();
      dongListArray[6] = buildingInfoList.get(i).getMainPurpsNM();
      dongListArray[7] = buildingInfoList.get(i).getTotArea();
      model.addRow(dongListArray);
    }
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
  
  
  
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void setMainPurpsNM(String name) {
      mainPurpsNMComboBox.setModel(new DefaultComboBoxModel(new String[] { "주용도명칭","단독주택", "다중주택", "다가구주택", "공관", "공동주택", "아파트", "연립주택", "다세대주택", 
          "생활편익시설", "부대시설", "복리시설", "기숙사", "제1종근린생활시설", "소매점", "휴게음식점", "이(미)용원", "일반목욕장", "의원", "체육장", "마을공동시설", "변전소", "양수장", 
          "정수장", "대피소", "공중화장실", "세탁소", "치과의원", "한의원", "침술원", "접골원", "조산소", "탁구장", "체육도장", "마을회관", "마을공동작업소", "마을공동구판장", "지역아동센터",
          "목욕장", "이용원", "미용원", "조산원", "제과점", "슈퍼마켓", "안마원", "공공시설", "동사무소", "경찰서", "파출소", "소방서", "우체국", "전신전화국", "방송국", "보건소", "공공도서관",
          "지역의료보험조합", "제과점", "지역자치센터", "지구대", "지역건강보험조합", "기타공공시설", "기타제1종근생활시설", "제2종근린생활시설",
          "일반음식점", "휴게음식점", "기원", "서점(1종근.생미해당)", "제조업소", "수리점", "게임제공업소", "사진관", "표구점", "학원", "장의사", "동물병원", "어린이집", "독서실", "총포판매소",
          "단란주점", "의약품도매점", "자동차영업소", "안마시술소", "노래연습장", "세탁소", "멀티미디어문화컨텐츠설비제공업소", "복합유통.제공업소", "직업훈련소", "인터넷컴퓨터게임시설제공업소", 
          "청소년게임제공업소", "복합유통게임제공업소", "제과점", "고시원", "의약품판매소", "의료기기판매소", "총포판매사", "소개업", "안마원", "고시원", "제과점", "인터넷컴퓨터게임시설제공업의시설",
          "청소년게임제공업의시설", "복합유통게임제공업의시설", "운동시설", "테니스장", "체력단련장", "에어로빅장", "볼링장", "당구장", "실내낚시터", "골프연습장", "물놀이형시설", "기타운동시설",
          "종교집회장", "교회", "성당", "사찰", "기도원", "수도원", "수녀원", "제실", "사당", "기타종교집회장", "공연장", "극장(영화관)", "음악당", "연예장",
          "비디오물감상실", "비디오물소극장", "극장", "영화관", "서커스장", "기타공연장", "사무소", "금융업소", "사무소", "부동산중개업소", "결혼상담소", "출판사", "부동산중개사무소", "기타사무소",
          "기타제2종근생활시설", "문화및집회시설", "공연장", "극장(영화관)", "음악당", "연예장", "서어커스장", "비디오물감상실", "비디오물소극장", "극장", "영화관", "기타공연장", "집회장", "예식장",
          "회의장", "공회당", "마권장외발매소", "마권전화투표소", "기타집회장", "관람장", "경마장", "자동차경기장", "체육관", "운동장", "경륜장", "경정장", "기타관람장", "전시장", "박물관",
          "미술관", "과학관", "기념관", "산업전시장", "박람회장", "문화관", "체험관", "기타전시장", "동.식물원", "동물원", "식물원", "수족관", "기타동.식물원", "기타문화및집회시설", "종교시설",
          "종교집회장", "교회", "성당", "사찰", "기도원", "수도원", "수녀원", "제실", "사당", "납골당(제2종근생 제외)", "봉안당", "기타종교집회장", 
          "기타종교시설", "판매시설", "도매시장", "소매시장", "시장", "백화점", "대형백화점", "대형점", "쇼핑센터", "기타소매시장", "상점", "게임제공업소", "멀티미디어문화컨텐츠설비제공업소",
          "복합유통.제공업소", "인터넷컴퓨터게임시설제공업소", "청소년게임제공업소", "복합유통게임제공업소", "일반게임제공의시설", "청소년게임제공업의시설", "복합유통게임제공업의시설", "인터넷컴퓨터게임시설제공업의시설",
          "일반게임제공업의시설", "도매시장", "농수산물도매시장", "농수산물공판장", "기타도매시장", "기타판매시설", "운수시설", "여객자동차터미널", "화물터미널", "철도역사", "공항시설", "항만시설(터미널)",
          "종합여객시설", "철도시설", "항만시설", "기타운수시설", "의료시설", "병원", "종합병원", "산부인과병원", "치과병원", "한방병원", "정신병원", "격리병원", "병원", "요양소", "요양병원",
          "기타병원", "격리병원", "전염병원", "마약진료소", "기타격리병원", "장례식장", "기타의료시설", "교육연구시설", "교육(연수)원", "직업훈련소", "학원",
          "연구소", "도서관", "학교", "초등학교", "중학교", "고등학교", "대학교", "전문대학", "대학", "유치원", "기타학교", "교육원", "교육원", "연수원", "기타교육원", "연구소", "연구소",
          "시험소", "계측계량소", "기타연구소", "기타교육연구시설", "노유자시설", "아동관련시설", "유치원", "영유아보육시설", "어린이집", "아동복지시설", "기타아동관련시설", "노인복지시설", "사회복지시설",
          "근로복지시설", "기타노유자시설", "수련시설", "유스호스텔", "생활권수련시설","청소년수련원(관)", "유스호스텔", "청소년문화의집", "청소년특화시설", "청소년수련관", "기타생활권수련시설",
          "자연권수련시설", "청소년수련원(관)", "청소년야영장", "청소년수련원", "기타자연권수련시설", "기타수련시설", "운동시설", "체육관", "운동장시설", "탁구장", "체육도장", "테니스장", "체력단련장",
          "에어로빅장", "볼링장", "당구장", "실내낚시터", "골프연습장", "물놀이형시설", "운동장", "육상장", "구기장", "볼링장", "수영장", "스케이트장", "롤러스케이트장", "승마장", "사격장", "궁도장",
          "골프장", "기타 운동시설", "업무시설", "공공업무시설", "국가기관청사", "자치단체청사", "외국공관", "기타공공업무시설", "일반업무시설", "금융업소", "오피스텔", "신문사", "사무소", "기타일반업무시설",
          "숙박시설", "고시원", "일반숙박시설", "호텔", "여관", "여인숙", "기타일반숙박시설", "관광숙박시설", "관광호텔", "수상관광호텔", "한국전통호텔", "가족호텔", "휴양콘도미니엄", "기타관광숙박시설",
          "고시원", "기타숙박시설", "위락시설", "단란주점", "유흥주점", "특수목욕장", "유기장", "투전기업소", "무도장(학원)", "주점영업", "카지노업소", "유원시설업의시설", "무도장", "무도학원",
          "카지노영업소", "사행행위업소", "기타위락시설", "공장", "일반공장", "유해공장", "물품공장", "물품 제조공장", "물품 가공공장", "물품 염색공장", "물품 도장공장", "물품 표백공장", "물품 재봉공장", 
          "물품 건조공장", "물품 인쇄공장", "물품 수리공장", "기타공장", "창고시설", "창고", "하역장", "물류터미널", "집배송시설", "창고", "일반창고", "냉장창고", "냉동창고", "기타창고시설", "위험물저장및처리시설",
          "주유소", "액화석유가스충전소", "위험물제조소", "위험물저장소", "액화가스취급소", "액화가스판매소", "유독물보관저장시설", "고압가스충전저장소", "석유판매소", "위험물취급소", "액화석유가스판매소",
          "액화석유가스저장소", "유독물보관소", "유독물저장소", "유독물판매시설", "고압가스충전소", "고압가스판매소", "고압가스저장소", "도료류판매소", "도시가스공급시설", "화약류저장소",
          "기타위험물저장처리시설", "자동차관련시설", "주차장", "세차장", "폐차장", "검사장", "매매장", "정비공장", "운전학원", "정비학원", "차고", "주기장",
          "기타자동차관련시설", "동.식물관련시설", "도축장", "도계장", "버섯재배사", "종묘배양시설", "온실", "작물재배사", "축사", "축사", "양잠", "양봉", "양어시설", "부화장",
          "가축시설", "가축용운동시설", "인공수정센터", "관리사", "가축용창고", "가축시장", "동물검역소", "실험동물사육시설", "기타가축시설", "기타동식물관련시설", "분뇨.쓰레기처리시설",
          "분뇨처리시설", "폐기물처리시설", "폐기물재활용시설", "고물상", "폐기물처리시설및폐기물감량화시설", "기타분뇨쓰레기처리시설",
          "교정및군사시설", "감화원", "군사시설", "국방ㆍ군사시설", "보호관찰소", "갱생보호소", "소년원", "소년분류심사원", "교도소", "구치소", "소년원", "소년분류심사원", "교정시설",
          "보호감호소", "구치소", "교도소", "기타교정및군사시설", "방송통신시설", "방송국", "전신전화국", "촬영소", "통신용시설", "방송국", "방송국", "방송프로그램제작시설", "송신시설",
          "수신시설", "중계시설", "기타방송통신시설", "발전시설", "발전소", "기타발전시설", "묘지관련시설", "화장장", "납골당",
          "묘지에 부수되는 건축물", "봉안당", "화장시설", "자연장지에 부수되는 건축물", "기타묘지관련시설", "관광휴게시설", "야외음악당", "야외극장", "어린이회관", "관망탑", "휴게소",
          "관광지시설", "공원에 부수되는 시설", "유원지에 부수되는 시설", "관광지에 부수되는 시설", "기타관광휴게시설", "가설건축물", "재해복구용가설건축물", "가설흥행장", "가설전람회장", 
          "공사용가설건축물", "견본주택", "가설점포", "경비용가설건축물", "임시자동차차고", "임시사무실", "임시창고", "임시숙소", "농,어업용비닐하우스",
          "가축용가설건축물", "농,어업용고정식온실", "창고용천막", "관광문화행사용가설건축물", "기타가설건축물", "장례식장", "장례식장", "근린생활시설", "소매점", "휴게음식점", "이(미)용원",
          "일반목욕장", "의원", "체육장", "마을공동시설", "변전소", "양수장", "정수장", "대피소", "공중화장실", "치과의원", "한의원", "침술원", "접골원", "조산소", "탁구장", "체육도장",
          "마을공회당", "마을공동작업소", "마을공동구판장", "공공시설", "동사무소", "경찰서", "파출소", "소방서", "우체국", "전신전화국", "방송국", "보건소", "공공도서관", "기타공공시설", "일반음식점",
          "휴게음식점", "기원", "서점", "제조업소", "수리점", "게임제공업소", "사진관", "표구점", "학원", "장의사",
          "동물병원", "독서실", "총포판매소", "단란주점", "의약품도매점", "자동차영업소", "안마시술소", "노래연습장", "세탁소", "운동시설", "테니스장", "체력단련장", "에어로빅장", "볼링장", 
          "당구장", "실내낚시터", "골프연습장", "기타운동시설", "종교집회장", "교회", "성당", "사찰", "기타종교집회장", "공연장", "극장(영화관)", "음악당", "연예장", "기타공연장", "사무소", "금융업소",
          "사무소", "부동산중개업소", "결혼상담소", "기타사무소", "기타근린생활시설", "문화및집회시설", "판매및영업시설", "대규모소매점", "기타판매및영업시설", "축사", "가축시설", "교육연구및복지시설",
          "기타교육연구및복지시설", "공공용시설", "교도소", "기타공공용시설"  
      }));
     try {
       mainPurpsNMComboBox.setSelectedItem(name.toString());
    } catch (Exception e) {
    }
    
  }
  
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void setMainPurpsCD(String name) {
      mainPurpsCDComboBox.setModel(new DefaultComboBoxModel(new String[] {"코드", "01000", "01001", "01002", "01003", "01004", 
          "02000", "02001", "02002", "02003", "02004", "02005", "02006", "02007", "03000", "03001", "03002", "03003", 
          "03004", "03005", "03006", "03007", "03008", "03009", "03010", "03011", "03012", "03013", "03014", "03015",
          "03016", "03017", "03018", "03019", "03020", "03021", "03022", "03023", "03024", "03025", "03026", "03027",
          "03028", "03029", "03030", "03031", "03100", "03101", "03102", "03103", "03104", "03105", "03106", "03107",
          "03108", "03109", "03110", "03111", "03112", "03113", "03114", "03199", "03999", "04000", "04001", "04002",
          "04003", "04004", "04005", "04006", "04007", "04008", "04009", "04010", "04011", "04012", "04013", "04014",
          "04015", "04016", "04017", "04018", "04019", "04020", "04021", "04022", "04023", "04024", "04025", "04026",
          "04027", "04028", "04029", "04030", "04031", "04032", "04033", "04034", "04035", "04036", "04037", "04038",
          "04039", "04100", "04101", "04102", "04103", "04104", "04105", "04106", "04107", "04108", "04199", "04200",
          "04201", "04202", "04203", "04204", "04205", "04206", "04207", "04208", "04299", "04300", "04301", "04302", 
          "04303", "04304", "04305", "04306", "04307", "04308", "04399", "04400", "04401", "04402", "04403", "04404",
          "04405", "04406", "04499", "04999", "05000", "05100", "05101", "05102", "05103", "05104", "05105", "05106",
          "05107", "05108", "05199", "05200", "05201", "05202", "05203", "05204", "05205", "05299", "05300", "05301",
          "05302", "05303", "05304", "05305", "05306", "05399", "05400", "05401", "05402", "05403", "05404", "05405",
          "05406", "05407", "05408", "05499", "05500", "05501", "05502", "05503", "05599", "05999", "06000", "06100",
          "06101", "06102", "06103", "06104", "06105", "06106", "06107", "06108", "06109", "06110", "06199", "06999",
          "07000", "07001", "07100", "07101", "07102", "07103", "07104", "07105", "07199", "07201", "07202", "07203",
          "07204", "07205", "07206", "07207", "07208", "07209", "07210", "07211", "07212", "07300", "07301", "07302",
          "07399", "07999", "08000", "08001", "08002", "08003", "08004", "08005", "08006", "08007", "08008", "08999",
          "09000", "09100", "09101", "09102", "09103", "09104", "09105", "09106", "09107", "09108", "09109", "09199",
          "09200", "09201", "09202", "09299", "09301", "09999", "10000",
          "10001", "10002", "10003", "10004", "10005", "10100", "10101", "10102", "10103", "10104", "10105", "10106",
          "10107", "10199", "10200", "10201", "10202", "10299", "10300", "10301", "10302", "10303", "10399", "10999",
          "11000", "11100", "11101", "11102", "11103", "11104", "11199", "11201", "11202", "11203", "11999", "12000",
          "12001", "12100", "12101", "12102", "12103", "12104", "12105", "12199", "12200", "12201", "12202", "12203",
          "12299", "12999", "13000", "13001", "13002", "13003", "13004", "13005", "13006", "13007", "13008", "13009",
          "13010", "13011", "13012", "13100", "13101", "13102", "13103", "13104", "13105", "13106", "13107", "13108",
          "13109", "13110", "13999", "14000", "14100", "14101", "14102", "14103", "14199", "14200", "14201", "14202",
          "14203", "14204", "14299", "15000", "15001", "15100", "15101", "15102", "15103", "15199", "15200", "15201",
          "15202", "15203", "15204", "15205", "15299", "15300", "15999", "16000", "16001", "16002", "16003", "16004",
          "16005", "16006", "16007", "16008", "16009", "16010", "16011", "16012", "16013", "16999", "17000", "17100",
          "17200", "17300", "17301", "17302", "17303", "17304", "17305", "17306", "17307", "17308", "17309", "17999",
          "18000", "18001", "18002", "18003", "18004", "18100", "18101", "18102", "18103", "18999", "19000", "19001",
          "19002", "19003", "19004", "19005", "19006", "19007", "19008", "19009", "19010", "19011", "19012", "19013",
          "19014", "19015", "19016", "19017", "19018", "19019", "19020", "19021", "19999", "20000", "20001", "20002",
          "20003", "20004", "20005", "20006", "20007", "20008", "20009", "20010", "20999", "21000", "21001", "21002",
          "21003", "21004", "21005", "21006", "21100", "21101", "21102", "21103", "21104", "21105", "21200", "21201",
          "21202", "21203", "21204", "21205", "21206", "21207", "21299", "21999", "22000", "22001", "22002", "22003",
          "22004", "22005", "22999", "23000", "23001", "23002", "23003", "23004", "23005", "23006", "23007", "23100",
          "23101", "23102", "23103", "23200", "23201", "23202", "23203", "23999", "24000", "24001", "24002", "24003",
          "24004", "24100", "24101", "24102", "24103", "24104", "24105", "24999", "25000", "25001", "25999", "26000",
          "26001", "26002", "26003", "26004", "26005", "26006", "26999", "27000", "27001", "27002", "27003", "27004",
          "27005", "27006", "27007", "27008", "27009", "27999", "28000", "28001", "28002", "28003", "28004", "28005",
          "28006", "28007", "28008", "28009", "28010", "28011", "28012", "28013", "28014", "28015", "28016", "28999",
          "29000", "29001", "Z3000", "Z3001", "Z3002", "Z3003", "Z3004", "Z3005", "Z3006", "Z3007", "Z3008", "Z3009", 
          "Z3010", "Z3011", "Z3012", "Z3014", "Z3015", "Z3016", "Z3017", "Z3018", "Z3019", "Z3020", "Z3021", "Z3022",
          "Z3023", "Z3100", "Z3101", "Z3102", "Z3103", "Z3104", "Z3105", "Z3106", "Z3107", "Z3108", "Z3109", "Z3199",
          "Z3201", "Z3202", "Z3203", "Z3204", "Z3205", "Z3206", "Z3207", "Z3208", "Z3209", "Z3210", "Z3211", "Z3212",
          "Z3214", "Z3215", "Z3216", "Z3217", "Z3218", "Z3219", "Z3220", "Z3221", "Z3300", "Z3301", "Z3302", "Z3303",
          "Z3304", "Z3305", "Z3306", "Z3307", "Z3399", "Z3400", "Z3401", "Z3402", "Z3403", "Z3499", "Z3500", "Z3501",
          "Z3502", "Z3503", "Z3599", "Z3600", "Z3601", "Z3602", "Z3603", "Z3604", "Z3699", "Z3999", "Z5000", "Z6000",
          "Z6205", "Z6999", "Z7001", "Z7002", "Z8000", "Z8999", "Z9000", "Z9001", "Z9999" 
      }));
      try {
        mainPurpsCDComboBox.setSelectedItem(name.toString());
     } catch (Exception e) {
     }
  }
  
}



