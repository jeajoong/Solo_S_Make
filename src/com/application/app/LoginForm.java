package com.application.app;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.EventListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import com.application.db.DBLogin;
import com.application.dto.Member;
import com.application.requireClass.RoundedButton;
import keeptoo.KGradientPanel;

public class LoginForm extends JFrame implements ActionListener, EventListener{
  private static final long serialVersionUID = 1L;
  
  RoundedButton btnSubmit = new RoundedButton();             //회원가입
  RoundedButton btnLogin = new RoundedButton();              //로그인
  RoundedButton btnExit = new RoundedButton();               //종료
  JButton btnBack = new JButton();               //뒤로가기
  CardLayout card;
  JLabel lbId = new JLabel();                    // 아이디 라벨
  JTextField idText = new JTextField();          // 입력Area
  JLabel lbPwd = new JLabel();                   // 비밀번호 라벨
  JPasswordField pwdText = new JPasswordField(); // 입력 Area  
  JLabel jLabel1 = new JLabel();                 // 관리자,일반 표시하려고 선언만 해둠
  KGradientPanel kGradientPanel1 = new KGradientPanel();

  DBLogin db;
  MainProcess main;
  
  public LoginForm() {
    loginArea();
    setVisible(true);
    btnLogin.addActionListener(this);
    btnSubmit.addActionListener(this);
    btnExit.addActionListener(this);
    btnBack.addActionListener(this);
    
    idText.addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent e) {
        idText = (JTextField) e.getSource();
        if(idText.getText().length() > 2) {
          try {
            idCheck(idText.getText());
          } catch (SQLException e1) {
            e1.printStackTrace();
          }
        } else {
          jLabel1.setText("");
        }
      }
    });
    
    
    pwdText.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if(loginCheck() == 1) {
            main.showMainPage(main);
          }
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    });
  }

  public void loginArea() {
    Dimension frameSize = this.getSize(); // 프레임 사이즈
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈

    this.setLocation((screenSize.width - frameSize.width)/3, (screenSize.height - frameSize.height)/4); // 화면 중앙
    this.setResizable(false); // 화면 크기 임의 변경 금지하는것.
    
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    btnSubmit.setText("회원가입");
    btnLogin.setText("로그인");
    btnExit.setText("종료");
    lbId.setText("아이디");
    idText.setText("");
    lbPwd.setText("비밀번호");
    pwdText.setText("");
    
    lbId.setForeground(Color.WHITE);
    lbPwd.setForeground(Color.WHITE);
    lbId.setFont(new Font("맑은고딕", 1, 14));
    lbPwd.setFont(new Font("맑은고딕", 1, 14));
    jLabel1.setFont(new Font("맑은고딕", 1, 13));
    jLabel1.setText("");
    
    btnSubmit.setBackground(new Color(255,252,222));
    btnLogin.setBackground(new Color(255,243,127));
    btnExit.setBackground(new Color(176,34,47));
    
    kGradientPanel1.setkEndColor(new Color(255, 255, 153));
    kGradientPanel1.setkStartColor(new Color(0, 153, 153));
    
    
    // 디자인 설정구간
    javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
    kGradientPanel1.setLayout(kGradientPanel1Layout);
    kGradientPanel1Layout.setHorizontalGroup(
        kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 500, Short.MAX_VALUE)
    );
    kGradientPanel1Layout.setVerticalGroup(
        kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 334, Short.MAX_VALUE)
    );
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(102, 102, 102)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lbId, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(idText, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lbPwd, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(pwdText, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnSubmit)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnLogin, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))))
            .addContainerGap(119, Short.MAX_VALUE))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(65, 65, 65)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(idText, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addComponent(lbId, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(pwdText, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addComponent(lbPwd, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
            .addContainerGap(58, Short.MAX_VALUE))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
}// loginForm 디자인
  

  
  
  
  public static void main(String args[]) {
    
    /* Create and display the form */
    EventQueue.invokeLater(new Runnable() {
        public void run() {
            new LoginForm().setVisible(true);
        }
    });
  } // main
  
  
  public void setMain(MainProcess main) {
    this.main = main;
  }

  public void setDB(DBLogin db) {
    this.db = db;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    Object btn = e.getSource();
    
    if(btn==btnLogin){
     try {
      if(loginCheck() == 1) {
        main.showMainPage(main);
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
     
    }else if(btn==btnSubmit){ // 회원가입 폼으로 이동 버튼
        main.createSubmitForm();
        main.showSubmitForm();
        main.submitForm.setDB(db);
        main.submitForm.setLoginForm(this);
//     idText.setText("");
//     pwdText.setText("");
//     idText.requestFocus(); // 이름 텍스트 창에 포커스 맞추기
     
    }else if(btn==btnExit){
     System.exit(0);
    }else if(btn==btnBack){
     JOptionPane.showMessageDialog(this, "로그인 페이지로 이동하겠습니다");
     card.show(this, "loginP");
     setBackground(new Color(102,111,123));
    }
  }// actionPerformed
  
  // 입력한 아이디 확인
  protected void idCheck(String inputId) throws SQLException {
    String idCheck = db.idCheck(inputId);
    if(idCheck != null) { // 값이 존재할 때 (회원정보가 있을 때)
      if (idCheck.substring(idCheck.length()-2, idCheck.length()).equals("10")) {
        jLabel1.setForeground(new Color(0, 52, 88));
        jLabel1.setText("관리자");
      } else {
        jLabel1.setForeground(new Color(105, 55, 161));
        jLabel1.setText("일반사용자");
      }
    } else { // 값이 존재하지 않을 때
        jLabel1.setText("");
    }
    
  }
  
  // 로그인 확인
  @SuppressWarnings("deprecation")
  protected int loginCheck() throws SQLException{
    
    String id = idText.getText();
    String pwd = pwdText.getText();
    
    System.out.println("입력한 ID : "+ id);
    System.out.println("입력한 PWD : "+ pwd);
    
    try{
      Member memberInfo = db.login(id, pwd);
      
     if(memberInfo.getId() == null) {
       JOptionPane.showMessageDialog(btnLogin, "아이디와 비밀번호를 확인하세요. 대소문자를 구분합니다.");
       return 0;
     } else {
       JOptionPane.showMessageDialog(btnLogin, "로그인을 환영합니다.");
       setBackground(Color.lightGray);
       
       main.setMemberInfo(memberInfo);
       main.setMain(main);
       
      return 1;
     }
     
    }catch(StringIndexOutOfBoundsException ee){
     String message="아이디, 비밀번호를 모두 입력하세요";
     JOptionPane.showMessageDialog(this, message);
     idText.requestFocus();
    }
    return 0;
  }
  
}
