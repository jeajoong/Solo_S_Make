package com.application.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JOptionPane;
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
import com.application.dto.Build;
import com.application.dto.BuildInfo;
import com.application.dto.Member;
import com.application.requireClass.MainPurpsArray;
import keeptoo.KGradientPanel;

// DetailForm2 => 총괄표제부  대장종류 (번호) : 1
// 각 항목 변수이름 지정.
// 엑셀 저장 출력 완료
public class DetailForm2 extends JFrame implements ActionListener{
  
  private static final long serialVersionUID = 1L;
  MainProcess main;
  BuildInfo buildInfo;
  Build build;
  SearchBuild searchPage;
  Member member;
  DetailForm2 detailForm2;
  DBBuildDetail dbBuildDetail = new DBBuildDetail();
  
  int printStatus;
  
  public DetailForm2() {
    dtlform();
    returnBtn.addActionListener(this);
    reviseBtn.addActionListener(this);
    printBtn.addActionListener(this);
    
    jComboBox1.addActionListener(this);
    jComboBox2.addActionListener(this);
    jComboBox4.addActionListener(this);
    
    mainPurpsNMComboBox.addActionListener(this);
    mainPurpsCDComboBox.addActionListener(this);
    
    // 총괄표제부에서 해당 건축물현황 누르면 해당건축물을 조회 되게 해야함.
    dongBuildInfoTable.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
       // buildInfo2는 Jtable에서 선택한 값을 객체에 담음
          BuildInfo buildInfo2 = new BuildInfo();
          
          if(dongBuildInfoTable.getValueAt(dongBuildInfoTable.getSelectedRow(), 0) == null) {
            buildInfo2.setBuildingPK("");
          } else {
            String buildingPK = dongBuildInfoTable.getValueAt(dongBuildInfoTable.getSelectedRow(), 0).toString();  
            buildInfo2.setBuildingPK(buildingPK);
          }
            try {
              BuildInfo buildInfo3 = dbBuildDetail.findBuild(buildInfo2.getBuildingPK());
              selectOne(buildInfo3);
            } catch (SQLException e1) {
              e1.printStackTrace();
            }
        }
      }
      
    });
    setVisible(true);
  }

  JButton returnBtn = new JButton(); // 돌아가기 버튼
  JButton reviseBtn = new JButton();
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
  
  KGradientPanel kGradientPanel1 = new KGradientPanel();
  
  JScrollPane jScrollPane = new JScrollPane(); 
  DefaultTableModel model23 = new DefaultTableModel() { //건축물 현황 모델 
    private static final long serialVersionUID = 2L; // 이거는 다른 클래스 테이블의 모델들과 충돌나지 않게...
    public boolean isCellEditable(int i, int c) { // 요 부분은 셀 수정 못하게 막는것 (더블클릭시 해당 셀 입력모드로 바뀌는거 방지)
      return false;
    }
  };
  JTable dongBuildInfoTable = new JTable(model23);  // 건축물 현황 jTable

  @SuppressWarnings({"unchecked", "rawtypes"})
  private void dtlform() {

    Dimension frameSize = this.getSize(); // 프레임 사이즈
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈

    this.setLocation((screenSize.width - frameSize.width)/5, (screenSize.height - frameSize.height)/7); // 화면 중앙
    this.setResizable(false); // 화면 크기 임의 변경 금지하는것.
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    entirLbl.setText("해당 주소의 전체주소 ");             // 상단 전체주소 라벨
    titleLbl.setText("총괄표제부");                   // 총괄표제부 라벨
    buildInfoLbl.setText("건축물현황");               // 하단 건축물현황 라벨

    returnBtn.setText("돌아가기");                   // 돌아가기 버튼
    reviseBtn.setText("수정하기");                    // 수정하기 버튼
    printBtn.setText("엑셀 저장 후 인쇄하기");            // 인쇄하기 버튼

    regstrLbl.setText("고유번호");                     // 고유번호 라벨
    regstrField.setText("고유번호(시군구법정동-대지구분-번지)"); // 고유번호 텍스트구역
    regstrField.setEnabled(false);

    platLocLbl.setText("대지위치");                     // 대지위치 라벨
    platLocField.setText("대지위치");                 // 대지위치 텍스트구역
    platLocField.setEnabled(false);
    
    jiBunLbl.setText("지번");                        // 지번 라벨
    jiBunField.setText("지번");                    // 지번 텍스트구역
    jiBunField.setEnabled(false);
    
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

    entirLbl.setFont(new Font("나눔고딕", 0 , 15));
    titleLbl.setFont(new Font("나눔고딕", 0, 25));
    vlRatEstmTotAreaLbl.setFont(new java.awt.Font("굴림", 0, 10)); // NOI18N
    jLabel26.setFont(new java.awt.Font("굴림", 0, 10)); // NOI18N
    totPkngCntLbl.setFont(new java.awt.Font("굴림", 0, 10)); // NOI18N
    buildInfoLbl.setFont(new Font("나눔고딕", 1, 15));
    
    model23.addColumn("관리건축물 PK");
    model23.addColumn("구분");
    model23.addColumn("명칭");
    model23.addColumn("도로명주소");
    model23.addColumn("건축물 주구조");
    model23.addColumn("건축물 지붕");
    model23.addColumn("층수");
    model23.addColumn("용도");
    model23.addColumn("연면적(㎡)");
    model23.addColumn("변동일");
    model23.addColumn("변동원인");
    
      jScrollPane.setViewportView(dongBuildInfoTable);
      add(jScrollPane,BorderLayout.CENTER);
      
      dongBuildInfoTable.getTableHeader().setReorderingAllowed(false);
    
    jScrollPane.setViewportView(dongBuildInfoTable);
    
    dongBuildInfoTable.getColumn("관리건축물 PK").setPreferredWidth(90);
    dongBuildInfoTable.getColumn("구분").setPreferredWidth(20);
    dongBuildInfoTable.getColumn("명칭").setPreferredWidth(30);
    dongBuildInfoTable.getColumn("도로명주소").setPreferredWidth(200);
    dongBuildInfoTable.getColumn("층수").setPreferredWidth(20);
    dongBuildInfoTable.getColumn("연면적(㎡)").setPreferredWidth(40);
    
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    dongBuildInfoTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // "구분" 컬럼 중앙정렬
    dongBuildInfoTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // "명칭" 컬럼 중앙정렬
    dongBuildInfoTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer); // "층수" 컬럼 중앙정렬
    dongBuildInfoTable.setAutoCreateRowSorter(true); // 정렬 기능 추가
    
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
                            .addComponent(reviseBtn)
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
            .addContainerGap(10, Short.MAX_VALUE))
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
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
  
  public void setMember(Member member) {
    this.member = member;
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

  
  // 업데이트 해주는 메소드
  public void updateInfo() throws SQLException {
    if(member.getUserGrade().equals("10")) {
      Build reviseBuildInfo = this.build;
      reviseBuildInfo.setBldNM(bldNMField.getText());  // 건물명(명칭)
      reviseBuildInfo.setSpcmt(spcmtField.getText());  // 특이사항
  
      reviseBuildInfo.setPlatArea(new BigDecimal(platAreaField.getText()));  // 대지면적
      reviseBuildInfo.setTotArea(new BigDecimal(totAreaField.getText()));    // 연면적
      
      // 지역, 지구, 구역 
      reviseBuildInfo.setArchArea(new BigDecimal(archAreaField.getText()));                // 건축면적
      reviseBuildInfo.setVlRatEstmTotArea(new BigDecimal(vlRatEstmTotAreaField.getText()));// 용적률 산정용연면적
      reviseBuildInfo.setMainBldCnt(Integer.parseInt(mainBldCntField.getText()));          // 건축물 수
      reviseBuildInfo.setMainPurpsCD(mainPurpsCDComboBox.getSelectedItem().toString());    // 주용도코드
      
      reviseBuildInfo.setBcRat(new BigDecimal(bcRatField.getText()));             // 건폐율
      reviseBuildInfo.setVlRat(new BigDecimal(vlRatField.getText()));             // 용적률
      reviseBuildInfo.setHoCNT(Integer.parseInt(hoCntField.getText()));           // 총 호수
      reviseBuildInfo.setTotPkngCNT(Integer.parseInt(totPkngCntField.getText())); // 총 주차 대수
      reviseBuildInfo.setAtchBldCnt(Integer.parseInt(atchBldCntField.getText())); // 부속건축물 수
      
      System.out.println("수정할 객체 : "+reviseBuildInfo);
      
       // 업데이트 확인창 띄우기
      String[] buttons = {"확인", "취소하기"};
      
      int checkStatus = 0;
      checkStatus = JOptionPane.showOptionDialog(null, "업데이트 하시겠습니까?", "업데이트", 
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "확인");
      // 확인을 눌렀을 때 0, 취소를 눌렀을 때 1
      
      if(checkStatus == 0) { 
          dbBuildDetail.updateBuild2(reviseBuildInfo); // 총괄표제부 업데이트
      }
    } else {
      JOptionPane.showMessageDialog(reviseBtn, "관리자 등급만 업데이트 할 수 있습니다.");
    }
  }
  
    // 엑셀에 건축물 정보 저장 후 인쇄하기 (Poi 라이브러리 사용) 
  private void printWork(Build build) throws IOException, SQLException {
    
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
      sheet1.getRow(i).getCell(2).setCellValue((String) model23.getValueAt(i % 20, 1)); // 구분
      sheet1.getRow(i).getCell(3).setCellValue((String) model23.getValueAt(i % 20, 2)); // 명칭
      sheet1.getRow(i).getCell(4).setCellValue((String) model23.getValueAt(i % 20, 3)); // 도로명주소
      sheet1.getRow(i).getCell(5).setCellValue((String) model23.getValueAt(i % 20, 4)); // 건축물 주구조
      sheet1.getRow(i).getCell(6).setCellValue((String) model23.getValueAt(i % 20, 5)); // 건축물 지붕
      sheet1.getRow(i).getCell(7).setCellValue((int) model23.getValueAt(i % 20, 6)); // 층수
      sheet1.getRow(i).getCell(8).setCellValue((String) model23.getValueAt(i % 20, 7)); // 용도
      
//      String platArea = new BigDecimal(model.getValueAt(i % 20, 4).toString())
//                            .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
      sheet1.getRow(i).getCell(9).setCellValue((String) model23.getValueAt(i % 20, 8).toString());// 연면적
      sheet1.getRow(i).getCell(10).setCellValue((String) model23.getValueAt(i % 20, 9)); // 변동일
      sheet1.getRow(i).getCell(11).setCellValue((String) model23.getValueAt(i % 20, 10)); // 변동원인
      
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
        File xlsFile = new File("C:/Users/seoin_01/Desktop/project/src/com/resource/"+build.getBuildingPK()+"_총괄표제부.xlsx");
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
            sheet2.getRow(j).getCell(2).setCellValue((String) model23.getValueAt(dongCheckSum + dongStartInt, 1)); // 구분
            sheet2.getRow(j).getCell(3).setCellValue((String) model23.getValueAt(dongCheckSum + dongStartInt, 2)); // 명칭
            sheet2.getRow(j).getCell(4).setCellValue((String) model23.getValueAt(dongCheckSum + dongStartInt, 3)); // 도로명주소
            sheet2.getRow(j).getCell(5).setCellValue((String) model23.getValueAt(dongCheckSum + dongStartInt, 4)); // 건축물 주구조
            sheet2.getRow(j).getCell(6).setCellValue((String) model23.getValueAt(dongCheckSum + dongStartInt, 5)); // 건축물 지붕
            sheet2.getRow(j).getCell(7).setCellValue((int) model23.getValueAt(dongCheckSum + dongStartInt, 6)); // 층수
            sheet2.getRow(j).getCell(8).setCellValue((String) model23.getValueAt(dongCheckSum + dongStartInt, 7)); // 용도
            
            String platArea = new BigDecimal(model23.getValueAt(dongCheckSum + dongStartInt, 8).toString())
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
        File xlsFile = new File("C:/Users/seoin_01/Desktop/project/src/com/resource/"+build.getBuildingPK()+"_총괄표제부.xlsx");
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
    desktop.print(new File("C:/Users/seoin_01/Desktop/project/src/com/resource/"+build.getBuildingPK()+"_총괄표제부.xlsx"));
    System.out.println("엑셀파일 인쇄 완료.");
    }
  }


  //상세정보 폼이 실행되면 SearchPage에서 BuildInfo 객체를 받아 Building(건축물대장)의 정보를 조회해서 뿌려준다
  public void inputBuildInfo(BuildInfo buildInfo2) throws SQLException {
    entirLbl.setText(buildInfo.getSidoNM() + " " + buildInfo.getSigunguNM() + " " + buildInfo.getBjdongNM() + " " +
                    buildInfo.getBunNum() + "-" + buildInfo.getJiNum() + " " + buildInfo.getBldNM());

    // 리스트에서 선택한 건물 정보로 건축물 대장을 찾음.(대장 구분코드, 대장종류, 대장 PK, bun, ji 정보로 조회)
    Build build = dbBuildDetail.findBuild(
        buildInfo.getBldTypeGBCD(), buildInfo.getBuildingPK(), buildInfo.getRegstrGBCD(), buildInfo.getRegstrKINDCD(),
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
    Object[] dongListArray = new Object[11]; 
    
    for (int i = 0; i < buildingInfoList.size(); i++) {
      
      dongListArray[0] = buildingInfoList.get(i).getBuildingPK();
      
      if(buildingInfoList.get(i).getMainAtchGBCD().equals("0")) {
        dongListArray[1] = "주";
      } else if(buildingInfoList.get(i).getMainAtchGBCD().equals("1")){
        dongListArray[1] = "부";
      } else {
        dongListArray[1] = "";
      }
      
      dongListArray[2] = buildingInfoList.get(i).getDongNM();
      dongListArray[3] = buildingInfoList.get(i).getPlatNewLoc();
      dongListArray[4] = buildingInfoList.get(i).getStrctNM();
      dongListArray[5] = buildingInfoList.get(i).getStrctNM();
      dongListArray[6] = buildingInfoList.get(i).getUpDownCNT();
      dongListArray[7] = buildingInfoList.get(i).getMainPurpsNM();
      dongListArray[8] = buildingInfoList.get(i).getTotArea();
      model23.addRow(dongListArray);
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
  
  // 건축물현황에서 데이터를 눌렀을 시 해당데이터로 창을 띄움.
  protected void selectOne(BuildInfo buildInfo3) throws SQLException {
    if(buildInfo3.getRegstrGBCD().equals("2")) { // 일반건축물
      main.createDetailForm();
      main.showDetailForm(buildInfo);
    } 
    else if (buildInfo3.getRegstrGBCD().equals("1")) { // 총괄표제부
      main.createDetailForm2();
      main.showDetailForm2(buildInfo);
    }
    else if (buildInfo3.getRegstrGBCD().equals("3")) { // 집합표제부
      main.createDetailForm3();
      main.showDetailForm3(buildInfo);
    }
    else if (buildInfo3.getRegstrGBCD().equals("4")) { // 전유부
      main.createDetailForm4();
      main.showDetailForm4(buildInfo);
    }
    else {
      System.out.println("잘못된 대장정보입니다.");
    }
  }
  
}



