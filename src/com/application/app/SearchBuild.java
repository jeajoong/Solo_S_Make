package com.application.app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.application.db.DBAddress;
import com.application.db.DBBuildInfo;
import com.application.dto.Address;
import com.application.dto.BuildInfo;
import com.application.dto.Member;
import com.application.requireClass.IntegerDocument;
import com.application.requireClass.RoundedButton;
import keeptoo.KGradientPanel;

// 이클립스 AWT에서 한글이 깨질 때 (checkBoxLabel 깨짐.)
// 해결법. 
@SuppressWarnings("serial")
public class SearchBuild extends JFrame implements ActionListener{

  DBAddress dbAddress = new DBAddress();
  DBBuildInfo dbBuildInfo = new DBBuildInfo(); 
  
  String status; // checkBox1 체크유무 확인용
  
  public SearchBuild() {
    Components();
    sidoComboBox.addActionListener(this);
    sigunguComboBox.addActionListener(this);
    bjdongComboBox.addActionListener(this);
    searchBtn.addActionListener(this);
    printBtn.addActionListener(this);
    bunField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { // Bun Enterkey 작동
        try {
          searchAddress();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    });
    bunField.addKeyListener(new KeyAdapter() {// Bun 글자수 제한! 
      public void keyTyped(KeyEvent ke) {
        JTextField src = (JTextField) ke.getSource();
        if(src.getText().length() >= 4) ke.consume();
      }
    });
    
    jiField.addActionListener(new ActionListener() { // Ji Enterkey 작동 
      public void actionPerformed(ActionEvent e) {
        try {
          searchAddress();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    });
    jiField.addKeyListener(new KeyAdapter() {// ji 글자수 제한! 
      public void keyTyped(KeyEvent ke) {
        JTextField src = (JTextField) ke.getSource();
        if(src.getText().length() >= 4) ke.consume();
      }
    });
    
    newAddrField.addActionListener(new ActionListener() { // newAddr Enterkey 작동 
      public void actionPerformed(ActionEvent e) {
        try {
          searchAddress();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    });
    
    
    
    checkbox1.addItemListener(new ItemListener() { // 체크박스 리스너
      @Override
      public void itemStateChanged(ItemEvent e) {
        status = (e.getStateChange() == 1 ? "checked" : "unchecked");
      }
    });
    
    
    
    listForm.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
       // buildInfo2는 Jtable에서 선택한 값을 객체에 담음
          BuildInfo buildInfo2 = new BuildInfo(); 
          
          if(listForm.getValueAt(listForm.getSelectedRow(), 0) == null) { // 0은 첫번째 컬럼(건축물 유형 구분)이 null일때
           buildInfo2.setBldTypeGBCD("");
          } else {
            String bldTypeGBCD = listForm.getValueAt(listForm.getSelectedRow(), 0).toString(); 
            buildInfo2.setBldTypeGBCD(bldTypeGBCD);
          } 
          if(listForm.getValueAt(listForm.getSelectedRow(), 1) == null) { // 1은 두번째 컬럼(관리건축물 PK)이 null일때
            buildInfo2.setBuildingPK("");
          } else {
            String buildingPK = listForm.getValueAt(listForm.getSelectedRow(), 1).toString();  
            buildInfo2.setBuildingPK(buildingPK);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(), 2) == null) { // 2는 세번째 컬럼(대장구분 코드)이 null일때
            buildInfo2.setRegstrGBCD("");
          } else {
            String regstrGBCD = listForm.getValueAt(listForm.getSelectedRow(), 2).toString();  
            buildInfo2.setRegstrGBCD(regstrGBCD);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(), 3) == null) { // 3은 네번째 컬럼(대장종류 코드)이 null일때
            buildInfo2.setRegstrKINKCD("");
          } else {
            String regstrKINKCD = listForm.getValueAt(listForm.getSelectedRow(), 3).toString();  
            buildInfo2.setRegstrKINKCD(regstrKINKCD);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(), 4) == null) { // 4는 다섯번째 컬럼(시도명)이 null일때
            buildInfo2.setSidoNM("");
          } else {
            String sidoNM = listForm.getValueAt(listForm.getSelectedRow(), 4).toString();
            buildInfo2.setSidoNM(sidoNM);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(),5) == null) { // 5는 여섯번째 컬럼(시군구명)이 null일때 
           buildInfo2.setSigunguNM("");
          } else {
           String sigunguNM = listForm.getValueAt(listForm.getSelectedRow(), 5).toString();
           buildInfo2.setSigunguNM(sigunguNM);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(), 6) == null) {  // 6은 일곱번째 컬럼(법정동명)이 null일때
            buildInfo2.setBjdongNM("");
          } else {
            String bjdongNM = listForm.getValueAt(listForm.getSelectedRow(), 6).toString();
            buildInfo2.setBjdongNM(bjdongNM);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(), 7) == null) {  // 7은 여덟번째 컬럼 (번)이 null일때
            buildInfo2.setBunNum("");
          } else {
            String bunNum = listForm.getValueAt(listForm.getSelectedRow(), 7).toString();
            buildInfo2.setBunNum(bunNum);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(), 8) == null) { // 8은 아홉번째 컬럼 (지)이 null일때
            buildInfo2.setJiNum("");
          } else {
            String jiNum = listForm.getValueAt(listForm.getSelectedRow(), 8).toString();
            buildInfo2.setJiNum(jiNum);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(), 9) == null) { // 9은 열번째 컬럼 (건물명)이 null일때
            buildInfo2.setBldNM("");
          } else {
            String bldNM = listForm.getValueAt(listForm.getSelectedRow(), 9).toString();       
            buildInfo2.setBldNM(bldNM);
          }
            try {
              selectOne(buildInfo2);
            } catch (SQLException e1) {
              e1.printStackTrace();
            }
        }
      }
      
    });

// jlist에서 한 행 클릭시에 메서드 두번 호출 막음.
//    listForm.addListSelectionListener(new ListSelectionListener() {
//      @Override
//      public void valueChanged(ListSelectionEvent e) {
//        if (!e.getValueIsAdjusting()) { // 마우스 눌렀다 떼면서 메서드가 두번씩 호출되는 현상이 생김 막기위해 사용
//        Object selectOneBuildingInfo =  listForm.getSelectedValue();
//        selectList(selectOneBuildingInfo);
//        }
//      }
//    });
    setVisible(true);
  } // searchPage 설정 끝
  
  Panel choice; 
  JTextField bunField = new JTextField();
  JTextField jiField = new JTextField();
  JTextField newAddrField = new JTextField();
  
  JScrollPane listBuildingInfo = new JScrollPane();
  
  @SuppressWarnings("rawtypes")
  JComboBox sidoComboBox = new JComboBox();
  @SuppressWarnings("rawtypes")
  JComboBox sigunguComboBox = new JComboBox();
  @SuppressWarnings("rawtypes")
  JComboBox bjdongComboBox = new JComboBox();
  
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  RoundedButton searchBtn = new RoundedButton("조회");
  RoundedButton printBtn = new RoundedButton("인쇄");
  Checkbox checkbox1 = new Checkbox();
  JLabel jLabel5 = new JLabel();
  
  CardLayout card;
  KGradientPanel kGradientPanel1 = new KGradientPanel();
  
  Member member;
  MainProcess main;
  
  
  DefaultTableModel model = new DefaultTableModel() { 
    public boolean isCellEditable(int i, int c) { // 요 부분은 셀 수정 못하게 막는것 (더블클릭시 해당 셀 입력모드로 바뀌는거 방지)
      return false;
    }
  };
  
  JTable listForm = new JTable(model);
  int checkList = 0;
  
  @SuppressWarnings({"unchecked", "rawtypes"})
  private void Components() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    bunField.setText("");
    jiField.setText("");

    // Bun, ji 텍스트 필드에 숫자만 입력하게 하는 자바클래스를 넣음.
    bunField.setDocument(new IntegerDocument());
    jiField.setDocument(new IntegerDocument());
    
    sidoComboBox.setModel(new DefaultComboBoxModel(new String[] {"선택","서울특별시","부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도", "세종특별자치시"}));
    sigunguComboBox.setModel(new DefaultComboBoxModel(new String[] { "선택" }));
    bjdongComboBox.setModel(new DefaultComboBoxModel(new String[] { "선택" }));

    jLabel1.setText("-");
    jLabel2.setText("시도");
    jLabel3.setText("시군구");
    jLabel4.setText("법정동");
    jLabel5.setText("완전일치");
    jLabel5.setFont(new Font("돋움", 0, 10));
    
    searchBtn.setBackground(new Color(35, 145, 246));
    printBtn.setBackground(new Color(193, 193, 193));
    
    model.addColumn("건축물 유형");
    model.addColumn("관리건축물 PK");
    model.addColumn("대장 구분");
    model.addColumn("대장 종류");
    model.addColumn("시도명");
    model.addColumn("시군구명");
    model.addColumn("법정동명");
    model.addColumn("번");
    model.addColumn("지");
    model.addColumn("건물명");
    model.addColumn("주용도");
    model.addColumn("주구조");

    JScrollPane scrollPane = new JScrollPane(listForm);
    add(scrollPane,BorderLayout.CENTER);
    
    listBuildingInfo.setViewportView(listForm);  
    listForm.getColumn("건축물 유형").setPreferredWidth(40);
    listForm.getColumn("관리건축물 PK").setPreferredWidth(100);
    listForm.getColumn("대장 구분").setPreferredWidth(30);
    listForm.getColumn("대장 종류").setPreferredWidth(30);
    listForm.getColumn("시도명").setPreferredWidth(50);
    listForm.getColumn("시군구명").setPreferredWidth(60);
    listForm.getColumn("법정동명").setPreferredWidth(60);
    listForm.getColumn("번").setPreferredWidth(20);
    listForm.getColumn("지").setPreferredWidth(20);
    listForm.getColumn("건물명").setPreferredWidth(200);
    listForm.getColumn("주용도").setPreferredWidth(80);
    listForm.getColumn("주구조").setPreferredWidth(30);
    
    listForm.getTableHeader().setFont(new Font("굴림", 0, 11)); // 폰트설정 

    listForm.getTableHeader().setReorderingAllowed(false); // 테이블 열 이동 불가하게 만듦
    listForm.setAutoCreateRowSorter(true); // 열 정렬기능 추가

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
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(layout.createSequentialGroup()
                    .addGap(116, 116, 116)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(sidoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGap(26, 26, 26)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(sigunguComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGap(26, 26, 26)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(bjdongComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(bunField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jiField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(newAddrField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(jLabel5)
                    .addGap(54, 54, 54)
                    .addComponent(printBtn))
                .addGroup(layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(listBuildingInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(40, Short.MAX_VALUE))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
   
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(75, 75, 75)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkbox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sidoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sigunguComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bjdongComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(printBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bunField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jiField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(newAddrField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(9, 9, 9)
            .addComponent(listBuildingInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(34, Short.MAX_VALUE))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    pack();// 윈도우 프레임, 프레임내에 서브컴포넌트들의 레이아웃과 Perfered Size에 맞도록 윈도우의 사이즈를 맞추는 작업..
    
}
  
  public void setMain(MainProcess main) {
    this.main = main;
  }
  
  public void setMember(Member member) {
   this.member = member;
  }
  
  public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
      public void run() {
          new SearchBuild().setVisible(true);
      }
  });
  }
  
  
  @Override
  public void actionPerformed(ActionEvent e) {
    Object select = e.getSource();
    
      if(select==sidoComboBox){  // sidoComboBox의 항목을 선택했을 때.
        if(!(sidoComboBox.getSelectedItem().toString() == "선택")) {
          sidoComboBox.removeItem("선택");
           try {
            sidoComboBoxActionPerformed(e);
          } catch (SQLException e1) {
            e1.printStackTrace();
          }
         }
       }
      if(select == sigunguComboBox){  // sigunguComboBox의 항목을 선택했을 때.
         if(!(sigunguComboBox.getSelectedItem().toString() == "선택")) {
            try {
              sigunguComboBoxActionPerformed(e);
            } catch (SQLException e1) {
              e1.printStackTrace();
            }
            
         }
       }
  
      if(select== bjdongComboBox){  // bjdongComboBox의 항목을 선택했을 때.
         if(!(bjdongComboBox.getSelectedItem().toString() == "선택")) {
           bjdongComboBoxActionPerformed(e);
         }
      }
      if(select == searchBtn) {
        try {
          if(sidoComboBox.getSelectedItem().toString() == "선택" && newAddrField == null) {
            JOptionPane.showMessageDialog(searchBtn, "주소를 선택하세요.");
          } else {
            searchAddress();
          }
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
      if(select == printBtn) {
        try {
            listForm.print();
        } catch (PrinterException e1) {
          e1.printStackTrace();
        }
//        performPrint();
      }
      
  }// actionPerformed

  
  /* ================== 시도, 시군구, 법정동 콤보박스 메서드 관련 부분 ================== */
  // 시도, 시군구, 법정동을 선택할 때 나오는 값들은 localhost의 테이블에서 조회하는 것임.
  
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void sidoComboBoxActionPerformed(ActionEvent e) throws SQLException { 
    String sidoSelect = sidoComboBox.getSelectedItem().toString();
    System.out.println("선택한 시도명 : "+ sidoSelect);
    
    // sigunguList에 선택한 시도의 시군구 리스트를 넣음
    List<Object> sigunguList = dbAddress.findSigunguNM(sidoSelect); 
    
    // 콤보박스를 다시 세팅하기 위해 배열을 만들고 그 배열에 list를 담아줌. 
    String[] sigunguArray = sigunguList.toArray(new String[sigunguList.size()]); 
    
     // 세종시를 선택해서 시군구 값이 없을 때 처리 => 법정동 테이블에 세종특별자치시에 해당하는 시군구명이 없음. 
    if(sigunguList.size() == 0) {
      sigunguComboBox.setModel(new DefaultComboBoxModel(new String[] { "세종특별자치시" }));
      List<Object> sejongBejongList = dbAddress.findBjdongNM(sidoSelect, "");
      String[] bjdongArray = sejongBejongList.toArray(new String[sejongBejongList.size()]);
      bjdongComboBox.setModel(new DefaultComboBoxModel(bjdongArray));
      
    }else {
      // 시군구 콤보박스를 해당 시도명의 시군구명들로 업데이트
      sigunguComboBox.setModel(new DefaultComboBoxModel(sigunguArray));
      
      // 시도명을 누르면 해당하는 시군구명 리스트 중에서 첫번째 값으로 법정동까지 콤보박스를 업데이트 시킨다.
      // firstsigunguName에 리스트 중에서 첫번째 값을 넣는다.
      
    String firstSigunguName = (String) sigunguList.get(0);
    System.out.println("해당 시도의 첫번째 시군구명 : "+ firstSigunguName );
    
    /*시도를 누르면 첫번째 시군구명으로 법정동 콤보박스 업데이트*/
    //법정동 리스트를 배열에 담아서 콤보박스 값들을 업데이트
    List<Object> bjdongList = dbAddress.findBjdongNM(sidoSelect, firstSigunguName);
    String[] bjdongArray = bjdongList.toArray(new String[bjdongList.size()]);
    bjdongComboBox.setModel(new DefaultComboBoxModel(bjdongArray));
    }
  }
  
  // 시군구를 선택했을 때
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void sigunguComboBoxActionPerformed(ActionEvent e) throws SQLException {
    String sidoSelect = sidoComboBox.getSelectedItem().toString();
    String sigunguSelect = sigunguComboBox.getSelectedItem().toString();
    System.out.println("선택한 시군구명 : " + sigunguSelect);
    
    if(sigunguSelect == "세종특별자치시") {
      List<Object> sejongBejongList = dbAddress.findBjdongNM(sidoSelect, "");
      String[] bjdongArray = sejongBejongList.toArray(new String[sejongBejongList.size()]);
      bjdongComboBox.setModel(new DefaultComboBoxModel(bjdongArray));
    } else {
    List<Object> bjdongList = dbAddress.findBjdongNM(sidoSelect, sigunguSelect);
    String[] bjdongArray = bjdongList.toArray(new String[bjdongList.size()]);
    bjdongComboBox.setModel(new DefaultComboBoxModel(bjdongArray));
    }
    
  }
  // 법정동을 선택했을 때
  public void bjdongComboBoxActionPerformed(ActionEvent e) {
    String bjdongSelect = bjdongComboBox.getSelectedItem().toString();
    System.out.println("선택한 법정동명 : " + bjdongSelect);
  }
  
  
  // 조회 버튼을 눌렀을 때
  @SuppressWarnings("unused")
  public void searchAddress() throws SQLException {
    System.out.println("조회버튼 누름");
    String sidoSelect = sidoComboBox.getSelectedItem().toString();
    String sigunguSelect = sigunguComboBox.getSelectedItem().toString();
    String bjdongSelect = bjdongComboBox.getSelectedItem().toString();
    String bunText = bunField.getText();
    String jiText = jiField.getText();
    String newAddrText = newAddrField.getText();
    
    if (status == "checked") { // 완전일치 체크박스를 선택하면 bun과 ji에 0을 붙여 4자리를 맞춰줘야 한다. 
      int checkBunLength = bunText.toString().length() - 4;
      int checkJiLength = jiText.toString().length() - 4;
        
      if(checkBunLength != 0) {
        for (int i = 0; i < Math.abs(checkBunLength); i++) {
          bunText = "0" + bunText;
        }
      }
      if(checkJiLength != 0) {
        for (int i = 0; i < Math.abs(checkJiLength); i++) {
          jiText = "0" + jiText;
        }
      }
    };
    
    if(newAddrText != null || newAddrText != "") {
        String[] result = new String[10];                      // 입력받은 값을 넣기위한 10 크기의 배열(도로명)
        String[] newAddrBunji = null;                          // 입력받은 값을 넣기위한 10 크기의 배열(도로명 번지)
        result = newAddrText.split(" ");                       // result[]에 사용자가 입력한 주소를 ' ' 기준으로 잘라 넣음. 
        HashMap<Integer, String> newAddrMap = new HashMap<>(); // 조건에 참이 되면 해당 자릿수를 키로, 자릿수에 해당하는 값을 값으로 저장.
        String[] resultSplitTotal;                             // split된 값 하나를 담을 배열
        String appendString = "";                              // 구분된 값들을 합칠 문자 객체
        List<String> processBunji = new ArrayList<String>(10); // 합쳐진 문자 객체를 담을 배열
        String matching = "^[0-9-]*$";                         // '-' 포함하고 숫자일때 조건을 판별하기 위한 문자 객체
        
        System.out.println("입력한 주소는 ? : "+newAddrText);
          for (int i = 0; i < result.length; i++) {
            if(result[i].contains("길")||result[i].contains("로")||result[i].contains("번길")||result[i].contains("번안길")||result[i].contains("거리")) {
              if(!result[i].contains("로구")) {
                newAddrMap.put(i, result[i]);                           // 해당 되는 조건으로 도로명을 담음.

                  newAddrBunji = new String[newAddrMap.size()];
                for (int m = 0; m < newAddrMap.size(); m++) {           // 도로명이 포함된 위치 + 1 번지정보를 newAddrBunji에 넣음
                  if(i < 9 && result[i+1].matches(matching) ) {         // bunji 정보를 얻기 위함.
                    newAddrBunji[m] = result[i+1];
                  
                  for (int j = 0; j < newAddrBunji.length; j++) {       // 해당하는 bunji정보의 갯수만큼 꺼내서 split 배열에 넣음.
                    resultSplitTotal = new String[newAddrBunji[j].split("").length];
                    resultSplitTotal = newAddrBunji[j].split("");
                    
                    for (int k = 0; k < resultSplitTotal.length; k++) { // split 배열에 있는 값들로 조건에 맞으면 계속 append 시킴
                      if(resultSplitTotal[k].matches(matching)) 
                        appendString = appendString + resultSplitTotal[k];
                    }
                    processBunji.add(appendString);                     // 최종적으로 맞는 조건들만 append 된 String 객체를 배열에 넣음.
                  }
                  } else {
                    // 새주소 번지명이 없는 경우.
                    break;
                  }
                }
                
              }
            }
          }
          for (int i = 0; i < processBunji.size(); i++) {
            List<Address> addressList = dbBuildInfo.findNewAddr(result, newAddrMap, processBunji.get(i));
            System.out.println(addressList);
          }
          
    }
    
    System.out.println(sidoSelect+" "+sigunguSelect+" "+bjdongSelect+" "+bunText+"-"+jiText);
    
    String sigunguCD = dbAddress.findSigunguCD(sidoSelect, sigunguSelect);
    String bjdongCD = dbAddress.findBjdongCD(sidoSelect, sigunguSelect, bjdongSelect);
    
    System.out.println("시군구 코드 : "+ sigunguCD + " 법정동 코드 : "+ bjdongCD);
    
    // db에 값들로 조회.
    List<BuildInfo> buildingList = dbBuildInfo.findBuildList(sigunguCD, bjdongCD, bunText, jiText);
    System.out.println("조회한 건축물 간단정보" + buildingList);

    checkStatus();
    
    if(checkList == 1) { // checkList가  1이면 이미 출력을 한번 한것이므로
      model.setRowCount(0); // model의 행을 0으로 만들어준다
     checkList = 0; // 다시 숫자는 0으로
    }
    Object[] buildingListArray = new Object[12]; 
      buildingList.toArray(new Object[buildingList.size()]);
    
    for (int i = 0; i < buildingList.size(); i++) {
      buildingListArray[0] = buildingList.get(i).getBldTypeGBCD();
      buildingListArray[1] = buildingList.get(i).getBuildingPK();
      buildingListArray[2] = buildingList.get(i).getRegstrGBCD();
      buildingListArray[3] = buildingList.get(i).getRegstrKINKCD();
      buildingListArray[4] = buildingList.get(i).getSidoNM();
      buildingListArray[5] = buildingList.get(i).getSigunguNM();
      buildingListArray[6] = buildingList.get(i).getBjdongNM();
      buildingListArray[7] = buildingList.get(i).getBunNum();
      buildingListArray[8] = buildingList.get(i).getJiNum();
      buildingListArray[9] = buildingList.get(i).getBldNM();
      buildingListArray[10] = buildingList.get(i).getMainPurpsNM();
      buildingListArray[11] = buildingList.get(i).getStrctNM();
      model.addRow(buildingListArray);
    }
    
    checkList = 1; // 다시검색했을 때 list 초기화용 번호
    
  }
  /* ================== 시도 시군구 법정동 관련부분 끝 ================== */
  
  
  private void checkStatus() {
    int status = dbBuildInfo.getStatus();
    
    if(status == 1) JOptionPane.showMessageDialog(searchBtn, "번 부분을 입력하지 않아 최대 500개 까지만 조회");
    if(status == 3) JOptionPane.showMessageDialog(searchBtn, "전유부를 제외 후 최대 700개 !");
  }

  //Jtable(listForm)에서 값 하나를 선택하면 해당 대장 정보를 가지고 detail 폼으로 넘어감
  // ++ build에 대장종류 번호로 분기
  // 대장종류=> 2일때 DetailForm (일반건축물대장)
  // 대장종류=> 1일때 DetailForm2(총괄표제부)
  // 대장종류=> 3일때 DetailForm3(집합표제부)
  // 대장종류=> 4일때 DetailForm4(전유부)
  public void selectOne(BuildInfo buildInfo) throws SQLException {

    if(buildInfo.getRegstrKINKCD().equals("2")) { // 일반건축물
      main.createDetailForm();
      main.showDetailForm(buildInfo);
    } 
    else if (buildInfo.getRegstrKINKCD().equals("1")) { // 총괄표제부
      main.createDetailForm2();
      main.showDetailForm2(buildInfo);
    }
    else if (buildInfo.getRegstrKINKCD().equals("3")) { // 집합표제부
      main.createDetailForm3();
      main.showDetailForm3(buildInfo);
    }
    else if (buildInfo.getRegstrKINKCD().equals("4")) { // 전유부
      main.createDetailForm4();
      main.showDetailForm4(buildInfo);
    }
    else {
      System.out.println("잘못된 대장정보입니다.");
    }
  
  }
  
}
