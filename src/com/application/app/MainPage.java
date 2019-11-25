package com.application.app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
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
import javax.swing.table.DefaultTableModel;
import com.application.db.DBAddress;
import com.application.db.DBBuildInfo;
import com.application.vo.BuildInfo;
import com.application.vo.Member;

@SuppressWarnings("serial")
public class MainPage extends JFrame implements ActionListener{

  DBAddress dbAddress = new DBAddress();
  DBBuildInfo dbBuildInfo = new DBBuildInfo(); 
  
  public MainPage() {
    Components();
    sidoComboBox.addActionListener(this);
    sigunguComboBox.addActionListener(this);
    bjdongComboBox.addActionListener(this);
    searchBtn.addActionListener(this);
    bunField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          searchAddress();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    });
    
    jiField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          searchAddress();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    });
    
    listForm.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
          
       // buildInfo2는 Jtable에서 선택한 값을 객체에 담음
          BuildInfo buildInfo2 = new BuildInfo(); 
          
          if(listForm.getValueAt(listForm.getSelectedRow(), 0) == null) { // 0은 첫번째 컬럼(건축물 유형 구분)이 null일때
           String bldTypeGBCD = "";
           buildInfo2.setBldTypeGBCD(bldTypeGBCD);
          } else {
            String bldTypeGBCD = listForm.getValueAt(listForm.getSelectedRow(), 0).toString(); 
            buildInfo2.setBldTypeGBCD(bldTypeGBCD);
          } 
          if(listForm.getValueAt(listForm.getSelectedRow(), 1) == null) { // 1은 두번째 컬럼(관리건축물 PK)이 null일때
            String buildingPK = "";
            buildInfo2.setBuildingPK(buildingPK);
          } else {
            String buildingPK = listForm.getValueAt(listForm.getSelectedRow(), 1).toString();  
            buildInfo2.setBuildingPK(buildingPK);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(), 2) == null) { // 2는 세번째 컬럼(대장구분 코드)이 null일때
            String regstrGBCD = "";
            buildInfo2.setRegstrGBCD(regstrGBCD);
          } else {
            String regstrGBCD = listForm.getValueAt(listForm.getSelectedRow(), 2).toString();  
            buildInfo2.setRegstrGBCD(regstrGBCD);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(), 3) == null) { // 3은 네번째 컬럼(대장종류 코드)이 null일때
            String regstrKINKCD = "";
            buildInfo2.setRegstrKINKCD(regstrKINKCD);
          } else {
            String regstrKINKCD = listForm.getValueAt(listForm.getSelectedRow(), 3).toString();  
            buildInfo2.setRegstrKINKCD(regstrKINKCD);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(), 4) == null) { // 4는 다섯번째 컬럼(시도명)이 null일때
            String sidoNM = "";
            buildInfo2.setSidoNM(sidoNM);
          } else {
            String sidoNM = listForm.getValueAt(listForm.getSelectedRow(), 4).toString();
            buildInfo2.setSidoNM(sidoNM);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(),5) == null) { // 5는 여섯번째 컬럼(시군구명)이 null일때 
           String sigunguNM = "";
           buildInfo2.setSigunguNM(sigunguNM);
          } else {
           String sigunguNM = listForm.getValueAt(listForm.getSelectedRow(), 5).toString();
           buildInfo2.setSigunguNM(sigunguNM);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(), 6) == null) {  // 6은 일곱번째 컬럼(법정동명)이 null일때
            String bjdongNM = "";
            buildInfo2.setBjdongNM(bjdongNM);
          } else {
            String bjdongNM = listForm.getValueAt(listForm.getSelectedRow(), 6).toString();
            buildInfo2.setBjdongNM(bjdongNM);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(), 7) == null) {  // 7은 여덟번째 컬럼 (번)이 null일때
            String bunNum = "";
            buildInfo2.setBunNum(bunNum);
          } else {
            String bunNum = listForm.getValueAt(listForm.getSelectedRow(), 7).toString();
            buildInfo2.setBunNum(bunNum);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(), 8) == null) { // 8은 아홉번째 컬럼 (지)이 null일때
            String jiNum = "";
            buildInfo2.setJiNum(jiNum);
          } else {
            String jiNum = listForm.getValueAt(listForm.getSelectedRow(), 8).toString();
            buildInfo2.setJiNum(jiNum);
          }
          if(listForm.getValueAt(listForm.getSelectedRow(), 9) == null) { // 9은 열번째 컬럼 (건물명)이 null일때
            String bldNM = "";
            buildInfo2.setBldNM(bldNM);
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
    
  } // mainPage 설정 끝
  
  Panel choice; 
  JTextField bunField = new JTextField();
  JTextField jiField = new JTextField();
  
  JScrollPane listbuildinginfo = new JScrollPane();
  
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
  JLabel jLabel5 = new JLabel();
  JButton searchBtn = new JButton();
  
  CardLayout card;
  
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

    sidoComboBox.setModel(new DefaultComboBoxModel(new String[] {"선택","서울특별시","부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도", "세종특별자치시"}));
    sigunguComboBox.setModel(new DefaultComboBoxModel(new String[] { "선택" }));
    bjdongComboBox.setModel(new DefaultComboBoxModel(new String[] { "선택" }));

    
    jLabel1.setText("-");
    jLabel2.setText("시도");
    jLabel3.setText("시군구");
    jLabel4.setText("법정동");

    searchBtn.setText("조회");

    model.addColumn("건축물 유형 구분");
    model.addColumn("관리 건축물 PK");
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
    
    listbuildinginfo.setViewportView(listForm);
    listForm.getColumn("건축물 유형 구분").setPreferredWidth(95);
    listForm.getColumn("관리 건축물 PK").setPreferredWidth(110);
    listForm.getColumn("대장 구분").setPreferredWidth(50);
    listForm.getColumn("대장 종류").setPreferredWidth(50);
    listForm.getColumn("시도명").setPreferredWidth(50);
    listForm.getColumn("시군구명").setPreferredWidth(60);
    listForm.getColumn("법정동명").setPreferredWidth(60);
    listForm.getColumn("번").setPreferredWidth(20);
    listForm.getColumn("지").setPreferredWidth(20);
    listForm.getColumn("주용도").setPreferredWidth(90);
    
    listForm.getTableHeader().setReorderingAllowed(false); // 테이블 열 이동 불가하게 만듦
    
    
    // 폼 디자인 관련부분
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(116, 116, 116)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sidoComboBox, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGap(26, 26, 26)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sigunguComboBox, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGap(26, 26, 26)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(bjdongComboBox, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46)
                            .addComponent(bunField, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jiField, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(searchBtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(listbuildinginfo, GroupLayout.PREFERRED_SIZE, 1020, GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(42, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(75, 75, 75)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(jLabel3)
                .addComponent(jLabel4))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(sidoComboBox, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addComponent(sigunguComboBox, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addComponent(bjdongComboBox, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addComponent(bunField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1)
                .addComponent(jiField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addComponent(searchBtn, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
            .addGap(49, 49, 49)
            .addComponent(listbuildinginfo, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
            .addContainerGap(21, Short.MAX_VALUE))
    );
    pack();
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
          new MainPage().setVisible(true);
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
          searchAddress();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
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
  public void searchAddress() throws SQLException {
    System.out.println("조회버튼 누름");
    String sidoSelect = sidoComboBox.getSelectedItem().toString();
    String sigunguSelect = sigunguComboBox.getSelectedItem().toString();
    String bjdongSelect = bjdongComboBox.getSelectedItem().toString();
    String bunText = bunField.getText();
    String jiText = jiField.getText();
    
    System.out.println(sidoSelect+" "+sigunguSelect+" "+bjdongSelect+" "+bunText+"-"+jiText);
    
    
    String sigunguCD = dbAddress.findSigunguCD(sidoSelect, sigunguSelect);
    String bjdongCD = dbAddress.findBjdongCD(sidoSelect, sigunguSelect, bjdongSelect);
    
    System.out.println("시군구 코드 : "+ sigunguCD + " 법정동 코드 : "+ bjdongCD);
    
    // db에 값들로 조회.
    List<BuildInfo> buildingList = dbBuildInfo.findBuildList(sigunguCD, bjdongCD, bunText, jiText);
    System.out.println("조회한 건축물 간단정보" + buildingList);

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
  
  
  //Jtable(listForm)에서 값 하나를 선택하면 해당 대장 정보를 가지고 detail 폼으로 넘어감
  // ++ build에 대장종류 번호로 분기
  // 대장종류=> 2일때 DetailForm (일반건축물대장)
  // 대장종류=> 1일때 DetailForm2(총괄표제부)
  // 대장종류=> 3일때 DetailForm3(집합표제부)
  // 대장종류=> 4일때 DetailForm4(전유부)
  public void selectOne(BuildInfo buildInfo) throws SQLException {

    if(buildInfo.getRegstrKINKCD().equals("2")) {
      main.createDetailForm();
      
      main.showDetailForm(buildInfo);
      main.detailForm.inputBuildInfo(buildInfo);
    } 
    
    if (buildInfo.getRegstrKINKCD().equals("1")) {
      main.createDetailForm2();
      
      main.showDetailForm2(buildInfo);
      main.detailForm2.inputBuildInfo(buildInfo);
    }
    
  
  }


  
  
  
  
  
}
