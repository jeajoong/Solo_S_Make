package com.application.app;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.NotActiveException;
import java.sql.SQLException;
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

public class LoginForm extends JFrame implements ActionListener{

  Panel loginP, mainP;
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
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    btnSubmit.setText("회원가입");
    btnLogin.setText("로그인");
    btnExit.setText("종료");
    lbId.setText("아이디");
    idText.setText("");
    lbPwd.setText("비밀번호");
    pwdText.setText("");
    jLabel1.setText("");
    
    btnSubmit.setBackground(new Color(255,252,222));
    btnLogin.setBackground(new Color(255,243,127));
    btnExit.setBackground(new Color(176,34,47));
    
    kGradientPanel1.setkEndColor(new java.awt.Color(255, 255, 153));
    kGradientPanel1.setkStartColor(new java.awt.Color(0, 153, 153));
    
    
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
     
    }else if(btn==btnSubmit){
     idText.setText("");
     pwdText.setText("");
     idText.requestFocus(); // 이름 텍스트 창에 포커스 맞추기
     
    }else if(btn==btnExit){
     System.exit(0);
    }else if(btn==btnBack){
     JOptionPane.showMessageDialog(this, "로그인 페이지로 이동하겠습니다");
     card.show(this, "loginP");
     setBackground(new Color(102,111,123));
    }
  }// actionPerformed
  
  
  // 로그인 확인
  public int loginCheck() throws SQLException{
    
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
