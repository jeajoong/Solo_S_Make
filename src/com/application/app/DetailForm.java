package com.application.app;

import java.awt.BorderLayout;
import java.awt.Choice;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.application.db.DBBuildDetail;
import com.application.requireClass.MultiLineHeaderRenderer;
import com.application.vo.Build;
import com.application.vo.BuildInfo;
import com.application.vo.Floor;
import com.application.vo.Owner;

// DetailForm => 일반건축물대장  대장종류 (번호) : 2
@SuppressWarnings("serial")
public class DetailForm extends JFrame implements ActionListener{
  
  MainProcess main;
  BuildInfo buildInfo;
  Build build;
  MainPage mainPage;
  DetailForm detailForm;
  DBBuildDetail dbBuildDetail = new DBBuildDetail();
  
  int grndFlr;
  int urndFlr;
  
  public DetailForm() {
    dtlform();
    returnBtn.addActionListener(this);
    printBtn.addActionListener(this);
    jComboBox1.addActionListener(this);
    jComboBox2.addActionListener(this);
    jComboBox4.addActionListener(this);
               
    jComboBox3.addActionListener(this);
    jComboBox11.addActionListener(this);
               
    jComboBox6.addActionListener(this);
               
    jComboBox8.addActionListener(this);
    jComboBox7.addActionListener(this);
        
    jComboBox9.addActionListener(this);
    jComboBox10.addActionListener(this);
    setVisible(true);
  }
  
  JButton returnBtn = new JButton(); // 돌아가기 버튼(mainpage로)
  JButton jButton2 = new JButton();
  JButton printBtn = new JButton();
  
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable jTable1 = new JTable();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel3 = new JLabel();
  List list2 = new List();
  List list3 = new List();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel12 = new JLabel();
  JLabel jLabel13 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JLabel jLabel15 = new JLabel();
  JLabel jLabel16 = new JLabel();
  JLabel jLabel17 = new JLabel();
  JLabel jLabel18 = new JLabel();
  JLabel jLabel19 = new JLabel();
  JLabel jLabel20 = new JLabel();
  JLabel jLabel21 = new JLabel();
  JLabel jLabel22 = new JLabel();
  JLabel jLabel23 = new JLabel();
  JLabel jLabel24 = new JLabel();
  JLabel jLabel25 = new JLabel();
  JLabel jLabel26 = new JLabel();
  JLabel jLabel27 = new JLabel();
  JLabel jLabel28 = new JLabel();
  JTextField jTextField4 = new JTextField();
  JTextField jTextField5 = new JTextField();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel29 = new JLabel();
  JTextField jTextField6 = new JTextField();
  JTextField jTextField7 = new JTextField();
  JTextField jTextField8 = new JTextField();
  JTextField jTextField9 = new JTextField();
  JTextField jTextField10 = new JTextField();
  JTextField jTextField11 = new JTextField();
  JTextField jTextField12 = new JTextField();
  JTextField jTextField13 = new JTextField();
  JTextField jTextField14 = new JTextField();
  JTextField jTextField15 = new JTextField();
  JTextField jTextField16 = new JTextField();
  JTextField jTextField17 = new JTextField();
  JTextField jTextField18 = new JTextField();
  JTextField jTextField19 = new JTextField();
  JTextField jTextField20 = new JTextField();
  JTextField jTextField22 = new JTextField();
  JTextField jTextField23 = new JTextField();
  JTextField jTextField24 = new JTextField();
  JTextField jTextField25 = new JTextField();
  Choice choice1 = new Choice();
  
  JComboBox jComboBox1 = new JComboBox(); // 지역 콤보박스
  JComboBox jComboBox2 = new JComboBox(); // 지구 콤보박스
  JComboBox jComboBox4 = new JComboBox(); // 구역 콤보박스
  JComboBox jComboBox8 = new JComboBox(); // 주구조1(명칭)
  JComboBox jComboBox7 = new JComboBox(); // 주구조2(코드)
  JComboBox jComboBox3 = new JComboBox();  // 주용도 콤보박스
  JComboBox jComboBox11 = new JComboBox(); // 주용도코드 콤보박스
  JComboBox jComboBox6 = new JComboBox(); // 층수 지상/지하 콤보박스
  JComboBox jComboBox9 = new JComboBox(); // 지붕명칭
  JComboBox jComboBox10 = new JComboBox(); // 지붕코드
  
  DefaultTableModel model1 = new DefaultTableModel(); //층별정보 모델1
  JScrollPane jScrollPane2 = new JScrollPane(); 
  JTable jTable2 = new JTable(model1);  // 층별정보 테이블
  
  DefaultTableModel model2 = new DefaultTableModel();
  JScrollPane jScrollPane3 = new JScrollPane(); // 소유자 테이블
  JTable jTable3 = new JTable(model2);
  
  
  private void dtlform() {

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    returnBtn.setText("돌아가기");              // (리스트) 돌아가기 버튼
    jButton2.setText("수정하기");               // 수정하기 버튼
    printBtn.setText("저장하기");               // 인쇄하기 버튼
    
    jLabel1.setText("해당 주소의 전체주소 ");        // 상단 전체주소 라벨
    jLabel21.setText("일반건축물대장");           // 일반건축물대장 라벨
    
    jLabel5.setText("대장_일련번호");            // 대장 일련번호 라벨
    jTextField5.setText("대장_일련번호");        // 대장 일련번호 텍스트구역
    jLabel2.setText("명칭");                  // 명칭 라벨
    jTextField6.setText("명칭");              // 명칭 텍스트구역
    jLabel29.setText("특이사항");              // 특이사항 라벨
    jTextField7.setText("특이사항");           // 특이사항 텍스트구역
    jLabel6.setText("대지위치");               // 대지위치 라벨
    jTextField1.setText("대지위치");           // 대지위치 텍스트구역
    jLabel7.setText("지번");                  // 지번 라벨
    jTextField2.setText("지번");              // 지번 텍스트구역
    jLabel8.setText("도로명주소");              // 도로명주소 라벨
    jTextField3.setText("도로명주소");          // 도로명주소 텍스트구역
    jLabel9.setText("대지면적");               // 대지면적 라벨
    jTextField4.setText("대지면적");           // 대지면적 텍스트구역
    jLabel12.setText("연면적");               // 연면적 라벨
    jTextField9.setText("연면적");            // 연면적 텍스트구역
    jLabel13.setText("지역");                // 지역 라벨
    jLabel14.setText("지구");                // 지구 라벨
    jLabel15.setText("구역");                // 구역 라벨
    jLabel10.setText("건축면적");             // 건축면적 라벨
    jTextField8.setText("건축면적");          // 건축면적 텍스트구역
    jLabel16.setText("용적률산정용연면적");      // 용적률 산정용 연면적 라벨
    jTextField13.setText("용적률산정용");      // 용적률 산정용 연면적 텍스트구역
    jLabel17.setText("주구조");              // 주구조 라벨   
    jLabel18.setText("주용도");              // 주용도 라벨
    jLabel19.setText("층수");               // 층수 라벨
    jTextField10.setText("층수");           // 층수 텍스트구역
    jLabel11.setText("건폐율");              // 건폐율 라벨
    jTextField14.setText("건폐율");          // 건폐율 텍스트구역
    jLabel22.setText("용적률");              // 용적률 라벨
    jTextField16.setText("용적률");          // 용적률 텍스트구역
    jLabel4.setText("소유자정보");            // 소유자정보 라벨
    jLabel23.setText("높이");               // 높이 라벨
    jTextField19.setText("높이");           // 높이 텍스트구역
    jLabel24.setText("지붕");               // 지붕 라벨
    jLabel25.setText("부속건축물");           // 부속건축물 라벨
    jTextField24.setText("부속건축물");       // 부속건축물 텍스트구역
    jLabel20.setText("조경면적");            // 조경면적 라벨
    jTextField15.setText("조경면적");        // 조경면적 텍스트구역
    jLabel26.setText("공개 공간의 면적");       //  공개 공간의 면적 라벨
    jTextField17.setText("공개공간면적");     // 공개 공간 면적 텍스트구역
    jLabel27.setText("건축선 후퇴면적");       // 건축선 후퇴면적 라벨
    jTextField20.setText("건축선 후퇴면적");   // 건축선 후퇴면적 텍스트구역
    jLabel28.setText("건축선 후퇴거리");       // 건축선 후퇴거리 라벨
    jTextField25.setText("건축선 후퇴거리");   // 건축선 후퇴거리 텍스트구역
    
    
    jLabel3.setText("층별정보");         // 층별정보 라벨
    
    jLabel21.setFont(new Font("굴림", 1, 15)); 
    jLabel16.setFont(new Font("굴림", 0, 10));
    jLabel18.setFont(new Font("굴림", 0, 10)); 
    jLabel26.setFont(new Font("굴림", 0, 10));

    jComboBox1.setModel(new DefaultComboBoxModel(new String[] { "지역"}));
    jComboBox2.setModel(new DefaultComboBoxModel(new String[] { "지구"}));
    jComboBox4.setModel(new DefaultComboBoxModel(new String[] { "구역"}));

    jComboBox3.setModel(new DefaultComboBoxModel(new String[] { "주용도"}));
    jComboBox11.setModel(new DefaultComboBoxModel(new String[] { "주용도코드"}));

    jComboBox6.setModel(new DefaultComboBoxModel(new String[] { "지상", "지하"}));

    jComboBox8.setModel(new DefaultComboBoxModel(new String[] { "주구조명칭"}));
    jComboBox7.setModel(new DefaultComboBoxModel(new String[] { "코드" }));

    jComboBox9.setModel(new DefaultComboBoxModel(new String[] { "지붕명칭"}));
    jComboBox10.setModel(new DefaultComboBoxModel(new String[] { "코드"}));


    model1.addColumn("구분");
    model1.addColumn("층별");
    model1.addColumn("구조");
    model1.addColumn("용도");
    model1.addColumn("면적(㎡)");
    
      jScrollPane2.setViewportView(jTable2);
      add(jScrollPane2,BorderLayout.CENTER);
      jTable2.setRowHeight(25);
      
      jTable2.getTableHeader().setReorderingAllowed(false); // 층별정보 테이블 컬럼 이동불가하게 만듦
      
      
    
    model2.addColumn("성명(명칭)\n 주민등록번호 \n (부동산등기용등록번호)");
    model2.addColumn("\n\n\n\n\n\n\n\n\n주소");
    model2.addColumn("\n\n\n\n\n\n\n\n소유권지분");
    model2.addColumn("\n\n\n\n변동일 \n 변동원인");
    
    jTable3.getColumn("성명(명칭)\n 주민등록번호 \n (부동산등기용등록번호)").setPreferredWidth(100);
    jTable3.getColumn("\n\n\n\n\n\n\n\n\n주소").setPreferredWidth(100);
    jTable3.getColumn("\n\n\n\n\n\n\n\n소유권지분").setPreferredWidth(18);
    
    
    MultiLineHeaderRenderer renderer = new MultiLineHeaderRenderer();
    Enumeration e = jTable3.getColumnModel().getColumns();
    
    while (e.hasMoreElements()) {
      ((TableColumn) e.nextElement()).setHeaderRenderer(renderer);
    }
    
    getContentPane().add(jScrollPane3);
    add(jScrollPane3,BorderLayout.CENTER);
    
    jTable3.getTableHeader().setReorderingAllowed(false);
    jTable3.setRowHeight(38);
    
    jScrollPane3.setViewportView(jTable3);
    
    
    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(110, 110, 110)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(returnBtn)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButton2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(printBtn)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(29, 29, 29)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField6)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel29)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField8))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(24, 24, 24)
                                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel26)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(58, 58, 58)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel22)
                                    .addGap(59, 59, 59)
                                    .addComponent(jTextField16, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel27)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField20))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel13))
                                        .addComponent(jLabel23))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jTextField19))))
                            .addGap(30, 30, 30)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField25))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel14))
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(9, 9, 9)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(25, 25, 25)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel25)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jTextField24))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel19)
                                                .addComponent(jLabel15))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jTextField10)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))))))))
            .addContainerGap())
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel21)
            .addGap(508, 508, 508))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(31, 31, 31)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(returnBtn)
                .addComponent(jButton2)
                .addComponent(printBtn))
            .addGap(18, 18, 18)
            .addComponent(jLabel21)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2)
                .addComponent(jLabel29)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(jLabel7)
                .addComponent(jLabel8)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(30, 30, 30)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel28)
                                .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel12)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel13)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel9)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel16)
                                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11)
                                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel22))
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
                                        .addComponent(jLabel17)
                                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel23)
                                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel27)
                                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGap(26, 26, 26)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(13, 13, 13)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(30, 30, 30))
    );

    pack();
}
  
  public void setMain(MainProcess main) {
    this.main = main;
  }
  
  public void setMainPage(MainPage mainPage) {
    this.mainPage = mainPage;
  }
  
  public void setDetailForm(DetailForm detailForm) {
    this.detailForm = detailForm;
  }
  
  public void setBuildInfo(BuildInfo buildInfo) {
    this.buildInfo = buildInfo;
  }
  
  public static void main(String args[]) {
    
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
    
    if(select==returnBtn){  // 돌아가기 버튼을 눌렀을 때 returnMainPage 호출
      detailForm.dispose();
      mainPage.setVisible(true);
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
    
 // 주구조 코드 ComboBox의 항목을 선택했을 때.
    if(select== jComboBox7){  
      if(!(jComboBox7.getSelectedItem().toString() == "코드")) {
        try {
         // 주구조명칭리셋메서드에 선택한 코드값을 넘겨주고 명칭을 돌려받는다
         String jugujoNM = jugujoNMReset(jComboBox7.getSelectedItem().toString());
         
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
    if(select== jComboBox8){  
      if(!(jComboBox8.getSelectedItem().toString() == "주구조명칭")) {
        try {
          // 주구조코드리셋메서드에 선택한 명칭값을 넘겨주고 코드값을 돌려받는다
         String jugujoCD =  jugujoCDReset(jComboBox8.getSelectedItem().toString());
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
    if(select== jComboBox11){  
      if(!(jComboBox11.getSelectedItem().toString() == "코드")) {
        try {
         // 주용도명칭 리셋메서드에 선택한 코드값을 넘겨주고 명칭을 돌려받는다
         String juyongdoNM = juyongdoNMReset(jComboBox11.getSelectedItem().toString());
         
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
    if(select== jComboBox3){  
      if(!(jComboBox3.getSelectedItem().toString() == "주용도명칭")) {
        try {
          // 주용도코드 리셋 메서드에 선택한 명칭값을 넘겨주고 코드값을 돌려받는다
         String juyongdoCD =  juyongdoCDReset(jComboBox3.getSelectedItem().toString());
         
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
    if(select== jComboBox10){  
      if(!(jComboBox10.getSelectedItem().toString() == "코드")) {
        try {
         // 지붕코드 명칭 리셋메서드에 선택한 코드값을 넘겨주고 명칭을 돌려받는다
         String roofNM = roofNMReset(jComboBox10.getSelectedItem().toString());
         
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
    if(select== jComboBox9){  
      if(!(jComboBox9.getSelectedItem().toString() == "지붕구조명칭")) {
        try {
          // 지붕구조코드 리셋 메서드에 선택한 명칭값을 넘겨주고 코드값을 돌려받는다
         String roofCD =  roofCDReset(jComboBox9.getSelectedItem().toString());
         
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
    if(select == jComboBox6) {
      setChangeFlrReset(jComboBox6.getSelectedItem().toString());
    }
    
  }
  
  
  
  
  
  // 엑셀에 건축물 정보 저장 후 인쇄하기 (Poi 라이브러리 사용) 
  public void printWork(Build build) throws IOException, SQLException {
    
    FileInputStream fis = new FileInputStream("C:/Users/seoin_01/Desktop/project/Origin_xlxs/일반건축물대장.xlsx");
    Workbook workbook = new XSSFWorkbook(fis);
    
//    CellStyle tableStyle1 = workbook.createCellStyle(); //THICK가 매우 굵은 줄 
//      tableStyle1.setBorderBottom(BorderStyle.THICK); 
//      tableStyle1.setBorderLeft(BorderStyle.THICK); 
    
    Sheet sheet1 = workbook.getSheetAt(0); // 시트가 1개일때 getSheetAt(0);
    Sheet sheet2 = workbook.getSheetAt(1); // 층별 정보가 7개가 넘은 경우. 소유자 정보가 3개가 넘은 경우. 나머지 층별 정보를 sheet2에 넣고 for문으로 계속 출력.
    
    sheet1.getRow(4).getCell(2).setCellValue(jTextField5.getText()); // 고유번호
    sheet1.getRow(4).getCell(9).setCellValue(jTextField6.getText()); // 명칭(건물명)
    sheet1.getRow(4).getCell(11).setCellValue(jTextField7.getText()); // 특이사항
    
    sheet1.getRow(7).getCell(2).setCellValue(jTextField1.getText()); // 대지위치
    sheet1.getRow(7).getCell(6).setCellValue(jTextField2.getText()); // 지번
    sheet1.getRow(7).getCell(9).setCellValue(""); // 도로명주소
    
    sheet1.getRow(9).getCell(2).setCellValue(jTextField4.getText()); // 대지 면적
    sheet1.getRow(9).getCell(4).setCellValue(jTextField9.getText()); // 연면적
    sheet1.getRow(9).getCell(6).setCellValue(""); // 지역
    sheet1.getRow(9).getCell(7).setCellValue(""); // 지구
    sheet1.getRow(9).getCell(9).setCellValue(""); // 구역
    
    sheet1.getRow(11).getCell(2).setCellValue(jTextField8.getText());  // 건축면적
    sheet1.getRow(11).getCell(4).setCellValue(jTextField13.getText()); // 용적률 산정용 연면적
    
    sheet1.getRow(11).getCell(6).setCellValue(jComboBox8.getSelectedItem().toString());   // 주 구조명
    sheet1.getRow(11).getCell(7).setCellValue(jComboBox3.getSelectedItem().toString());   // 주 용도명
    
    sheet1.getRow(11).getCell(10).setCellValue(build.getGrndFlrCNT()); // 지상층
    sheet1.getRow(11).getCell(12).setCellValue(build.getUgrndFlrCNT()); // 지하층
    
    sheet1.getRow(13).getCell(2).setCellValue(jTextField14.getText()); // 건폐율
    sheet1.getRow(13).getCell(4).setCellValue(jTextField16.getText()); // 용적률
    sheet1.getRow(13).getCell(6).setCellValue(jTextField19.getText()); // 높이
    sheet1.getRow(13).getCell(7).setCellValue(jComboBox9.getSelectedItem().toString()); // 지붕구조
    sheet1.getRow(13).getCell(9).setCellValue(jTextField24.getText()); // 부속건축물 수
    
    sheet1.getRow(16).getCell(2).setCellValue("");    // 공간 면적 합계
    sheet1.getRow(16).getCell(4).setCellValue("");    // 공개 공지 면적
    sheet1.getRow(16).getCell(6).setCellValue("");    // 쌈지 공원 면적
    sheet1.getRow(16).getCell(7).setCellValue("");    // 공공보행통로
    sheet1.getRow(16).getCell(9).setCellValue("");    // 건축선 후퇴면적
    sheet1.getRow(16).getCell(11).setCellValue("");   // 그 밖의 면적
    
    java.util.List<Floor> floorList = dbBuildDetail.findFlrInfo(build);
    // 잊지말자 배열은 0부터 ~ 크기는 0부터의 갯수
    
    int checkSum = 0;       
    for (int i = 21; i < 21+floorList.size(); i++) { 
      // 시트 위치는 21 행부터  층별 테이블은 0행부터 가져와서 붙여줘야 함.
      sheet1.getRow(i).getCell(2).setCellValue((String) model1.getValueAt(i % 21, 0)); //구분
      sheet1.getRow(i).getCell(3).setCellValue((String) model1.getValueAt(i % 21, 1)); //층별
      sheet1.getRow(i).getCell(4).setCellValue((String) model1.getValueAt(i % 21, 2)); //구조
      sheet1.getRow(i).getCell(5).setCellValue((String) model1.getValueAt(i % 21, 3)); //용도
//      BigDecimal bdc = new BigDecimal(model1.getValueAt(i % 20 - 1, 4).toString()); 
//      String platArea = bdc.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      
      String platArea = new BigDecimal(model1.getValueAt(i % 20 - 1, 4).toString())
                            .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
      
      sheet1.getRow(i).getCell(6).setCellValue(platArea);                                  //면적
      
      checkSum = checkSum + 1;
      if (checkSum == 7) // checkSum이 7이면 층별정보가 7개.
        break;
    }
    
    java.util.List<Owner> ownerList = dbBuildDetail.findOwnrInfo(build);
    
    int checkSum2 = 0;
    for(int i = 21; i< (2 * ownerList.size()) + 21; i++) { // 21 23 25행에 이름자리
      sheet1.getRow(21 + (2 * checkSum2)).getCell(7).setCellValue((String) model2.getValueAt(checkSum2, 0)); // 이름
      sheet1.getRow(21 + (2 * checkSum2)).getCell(9).setCellValue((String) model2.getValueAt(checkSum2, 2)); //소유권 지분
      
      checkSum2 = checkSum2 + 1;
      if(checkSum2 == 3) // checkSum2이 3이면 소유자정보 3개 0,1,2
        break;
    }
    
    
    if (floorList.size() <= 7 && ownerList.size() <= 3) {
    try {
      File xlsFile = new File("C:/Users/seoin_01/Desktop/project/src/com/resource/일반건축물대장.xlsx");
      FileOutputStream fileOut = new FileOutputStream(xlsFile);
      workbook.write(fileOut);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("엑셀파일 저장 완료.");
    }
    // 엑셀파일 인쇄 설정
    PrintSetup print = sheet1.getPrintSetup();
    print.setPaperSize(PrintSetup.A4_PAPERSIZE);
    print.setLandscape(true); // true면 가로방향 , 선언하지 않으면 기본 세로방향
    
    
    // 더 출력하기.
    if (floorList.size() > 7 || ownerList.size() > 3) { // 층별정보가 8개 이상이거나 소유자 정보가 4개 이상 일 때
      
      int flrPrintSize = (floorList.size() - 7) / 18;   // 층별 프린트 사이즈
      int ownerPrintSize = (ownerList.size() - 3) / 9;  // 소유자 프린트 사이즈 
      int printSize = 0;                                // printSize는 프린트 할 대장 수
      
      if ((floorList.size() - 7) % 18 != 0) {           // 만약  18개로 나눴는데 0이 아니라면 => 층별 정보가 남은 경우
        flrPrintSize = flrPrintSize + 1;                // 한장더 추가한다.
      }
      
      if((2 * ( ownerList.size() - 3)) % 18 != 0) {             // 만약 9개로 나눴는데 0이 아니라면 => 소유자 정보가 남은 경우
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
          workbook.cloneSheet(i+1);  // 1번 시트가 복사되었다.
          sheet2.getRow(4).getCell(2).setCellValue(jTextField5.getText());  // 고유번호
          sheet2.getRow(4).getCell(8).setCellValue(jTextField6.getText());  // 명칭
          sheet2.getRow(4).getCell(10).setCellValue(jTextField7.getText()); // 특이사항
          
          sheet2.getRow(7).getCell(2).setCellValue(jTextField1.getText());  // 대지위치
          sheet2.getRow(7).getCell(6).setCellValue(jTextField2.getText());  // 지번
          
          // 층별 현황을 출력
          if (floorList.size() > 7) {
            for (int j = 12; j < 12 + floorList.size()-flrStartInt; j++) {
              sheet2.getRow(j).getCell(2).setCellValue((String) model1.getValueAt(flrCheckSum + flrStartInt, 0)); //구분
              sheet2.getRow(j).getCell(3).setCellValue((String) model1.getValueAt(flrCheckSum + flrStartInt, 1)); //층별
              sheet2.getRow(j).getCell(4).setCellValue((String) model1.getValueAt(flrCheckSum + flrStartInt, 2)); //구조
              sheet2.getRow(j).getCell(5).setCellValue((String) model1.getValueAt(flrCheckSum + flrStartInt, 3)); //용도
              String platArea = new BigDecimal(model1.getValueAt(flrCheckSum + flrStartInt, 4).toString())
                  .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
              sheet2.getRow(j).getCell(6).setCellValue(platArea);
              
                flrCheckSum = flrCheckSum + 1;
              if(flrCheckSum == 18)  // 18 => 추가건축물대장 층별리스트 크기
                break;
            }
              flrCheckSum = 0;
              flrStartInt = flrStartInt + 18; // 배열 시작위치 재정의
          }
          
          // 소유자 현황을 출력
            if(ownerList.size() > 3) {        
              for(int k = 12; k < 12 + ownerList.size() - ownrStartInt; k++) {
                if(2 * ownrCheckSum < ownerList.size()) {
                sheet2.getRow(12 + (2 * ownrCheckSum)).getCell(7).setCellValue((String) model2.getValueAt(ownrCheckSum + ownrStartInt, 0)); // 이름
                sheet2.getRow(12 + (2 * ownrCheckSum)).getCell(9).setCellValue((String) model2.getValueAt(ownrCheckSum + ownrStartInt, 2)); //소유권 지분
                
                ownrCheckSum = ownrCheckSum + 1;
              if(ownrCheckSum == 9) { 
                break;
              }
              }
              }
              ownrCheckSum = 0; 
              ownrStartInt = ownrStartInt + 9;
            }
            
            sheet2 = workbook.getSheetAt(i+2); // sheet2 객체에 새로 만든 비어있는시트를 넣고 종료 됨

            // 엑셀파일 인쇄 설정
            PrintSetup print2 = sheet2.getPrintSetup();
            print2.setPaperSize(PrintSetup.A4_PAPERSIZE);
            print2.setLandscape(true);      // true면 가로방향 , 선언하지 않으면 기본 세로방향
        }

      System.out.println("저장완료");
      workbook.removeSheetAt(printSize + 1); // 마지막 비어있는 시트 제거
      
      try {
        File xlsFile = new File("C:/Users/seoin_01/Desktop/project/src/com/resource/일반건축물대장.xlsx");
        FileOutputStream fileOut = new FileOutputStream(xlsFile);
        workbook.write(fileOut);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      
      
      
    }
    
  }
  
  
  
  
  

  // 상세정보 폼이 실행되면 MainPage에서 BuildInfo 객체를 받아 Building(건축물대장)의 정보를 조회해서 뿌려준다.
  public void inputBuildInfo(BuildInfo buildInfo) throws SQLException {
    jLabel1.setText(buildInfo.getSidoNM() + " " + buildInfo.getSigunguNM() + " " + buildInfo.getBjdongNM() + " " +
                    buildInfo.getBunNum() + "-" + buildInfo.getJiNum() + buildInfo.getBldNM());

    
    // 리스트에서 선택한 건물 정보로 건축물 대장을 찾음.(대장 구분코드, 대장종류, 대장 PK, bun, ji 정보로 조회)
    Build build = dbBuildDetail.findBuild(
        buildInfo.getBldTypeGBCD(), buildInfo.getBuildingPK(), buildInfo.getRegstrGBCD(), buildInfo.getRegstrKINKCD(),
        buildInfo.getSidoNM(),buildInfo.getSigunguNM(),buildInfo.getBjdongNM(),
        buildInfo.getBunNum(),buildInfo.getJiNum());
    
    
    jTextField5.setText(build.getRegstrNo());
    jTextField6.setText(build.getBldNM());
    jTextField7.setText(build.getSpcmt());
    jTextField1.setText(build.getPlatLoC());
    jTextField2.setText(build.getJiBun());
    jTextField3.setText(""); // 도로명주소 ....
    
    // 대지면적
    try {
      BigDecimal bdc = new BigDecimal(build.getPlatArea().toString());
      String stringPlatArea = bdc.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      jTextField4.setText(stringPlatArea);
    } catch (NullPointerException e) {
      System.out.println("대지면적 데이터가 없습니다." + e);
    } catch (Exception e1) {
      System.out.println("에러!");
    }

    // 연면적
    try {
      BigDecimal bdc2 = new BigDecimal(build.getTotArea().toString());
      String stringTotArea = bdc2.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      jTextField9.setText(stringTotArea);
    } catch (NullPointerException e) {
      System.out.println("연면적 데이터가 없습니다" + e);
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    // 건축면적
    try {
      BigDecimal bdc3 = new BigDecimal(build.getArchArea().toString());
      String stringArchArea = bdc3.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      jTextField8.setText(stringArchArea);
    } catch (NullPointerException e) {
      System.out.println("건축면적 데이터가 없습니다." + e);
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    // 용적률산정용연면적
    try {
      BigDecimal bdc4 = new BigDecimal(build.getVlRatEstmTotArea().toString());
      String stringVlRatEstmTotArea = bdc4.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      jTextField13.setText(stringVlRatEstmTotArea);
    } catch (NullPointerException e) {
      System.out.println("용적률산정용연면적 데이터가 없습니다." + e);
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
    jTextField10.setText(Integer.toString(build.getGrndFlrCNT()));
      grndFlr = build.getGrndFlrCNT();
      urndFlr = build.getUgrndFlrCNT();
    } catch (NullPointerException e) {
      System.out.println("층수 데이터가 없습니다." + e);
    }
    
    
    // 건폐율 
    try {
      BigDecimal bdc5 = new BigDecimal(build.getBcRat().toString());
      String stringBcRat = bdc5.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      jTextField14.setText(stringBcRat);
    } catch (NullPointerException e) {
      System.out.println("건폐율 데이터가 없습니다." + e);
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    // 용적률
    try {
      BigDecimal bdc6 = new BigDecimal(build.getVlRat().toString());
      String stringVlRat = bdc6.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
      jTextField16.setText(stringVlRat);
    } catch (NullPointerException e) {
      System.out.println("용적률 데이터가 없습니다." + e);
    } catch (Exception e1) {
      System.out.println("에러!");
    }
    
    // 높이
    try {
      String stringHeit = Float.toString(build.getHeit());
      jTextField19.setText(stringHeit);
    } catch (NullPointerException e) {
      System.out.println("높이 데이터가 없습니다." + e);
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
      jTextField24.setText(stringAtchBldCnt);
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
    ownrTableSet(build);
    
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
      jTextField10.setText(Integer.toString(grndFlr));
    } else {
      jTextField10.setText(Integer.toString(urndFlr));
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
        model1.addRow(floorListArray);
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
      
      model2.addRow(ownerListArray);
    }
  }
  
  
  
  
  
  
  
  
  
  
//  for (int i = 0; i < bjdongArray.length; i++) {
//    bjdongComboBox.addItem(new String(bjdongArray[i]));
//  }
//  콤보박스에 배열을 추가시키는방법......
  
  

  
  
  
  
  
  
  
  
  
  
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void setStrctNM(String name) {
      jComboBox8.setModel(new DefaultComboBoxModel(new String[] { "주용도목적", "조적구조", "벽돌구조", "블록구조", "석구조", "기타조적구조", "콘크리트구조",
          "철근콘크리트구조", "프리케스트콘크리트구조", "기타콘크리트구조", "철골구조", "일반철골구조", "경량철골구조", "강파이프구조", "기타강구조", "철골철근콘크리트구조", "철골콘크리트구조",
          "철골철근콘크리트구조", "기타철골철근콘크리트구조", "목구조", "일반목구조", "통나무구조", "기타구조" }));
      try {
        jComboBox8.setSelectedItem(name.toString());
      } catch (Exception e) {
      
      }
  }
  
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void setStrctCD(String name) {
      jComboBox7.setModel(new DefaultComboBoxModel(new String[] {"코드", "10", "11", "12", "13", "19", "20", "21", "22", "29", "30", "31",
          "32", "33", "39", "40", "41", "42", "49", "50", "51", "52", "99"}));
      try {
        jComboBox7.setSelectedItem(name.toString());
      } catch (Exception e) {
      }
  }
  
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void setMainPurpsNM(String name) {
      jComboBox3.setModel(new DefaultComboBoxModel(new String[] { "주용도명칭","단독주택", "다중주택", "다가구주택", "공관", "공동주택", "아파트", "연립주택", "다세대주택", 
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
       jComboBox3.setSelectedItem(name.toString());
    } catch (Exception e) {
    }
    
  }
  
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void setMainPurpsCD(String name) {
      jComboBox11.setModel(new DefaultComboBoxModel(new String[] {"코드", "01000", "01001", "01002", "01003", "01004", 
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
        jComboBox11.setSelectedItem(name.toString());
     } catch (Exception e) {
     }
  }
  
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void setRoofNM(String name) {
      jComboBox9.setModel(new DefaultComboBoxModel(new String[] { "지붕구조명칭", "(철근)콘크리트", "기와", "슬레이트", "기타지붕" }));
      try {
        jComboBox9.setSelectedItem(name.toString());
     } catch (Exception e) {
     }
  }
  
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void setRoofCD(String name) {
      jComboBox10.setModel(new DefaultComboBoxModel(new String[] { "코드", "10", "20", "30", "90" }));
      try {
        jComboBox10.setSelectedItem(name.toString());
     } catch (Exception e) {
     }
  }
  

  
}



