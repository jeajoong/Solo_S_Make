package com.application.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.EventListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import com.application.db.DBLogin;
import com.application.dto.Member;
import com.application.requireClass.ManagerCheckNumber;
import com.application.requireClass.RoundedButton;
import keeptoo.KGradientPanel;

public class SubmitForm extends JFrame implements ActionListener, EventListener, FocusListener{
  private static final long serialVersionUID = 1L;
  
  RoundedButton submitBtn = new RoundedButton();
  RoundedButton cancelBtn = new RoundedButton();
  JLabel idLbl = new JLabel();
  JLabel pwLbl = new JLabel();
  JLabel pwCheckLbl = new JLabel();
  JLabel nameLbl = new JLabel();
  JLabel managerLbl = new JLabel();
  
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  
  JTextField idField = new JTextField();
  JPasswordField pwField = new JPasswordField();
  JPasswordField pwCheckField = new JPasswordField();
  JTextField nameField = new JTextField();
  JTextField managerNumField = new JTextField();
  KGradientPanel kGradientPanel1 = new KGradientPanel();
  
  Member member = new Member();
  LoginForm loginForm;
  DBLogin db;
  MainProcess main;
  
  int idCheckStatus = 0;
  int pwCheckStatus = 0;
  int nameCheckStatus = 0;
  
  public SubmitForm() {
    submitArea();
    setVisible(true);
    idField.addFocusListener(this);
    
    idField.addKeyListener(new KeyAdapter() { // 키 입력 이벤트 추가! // a-Z 0-9 가-힣
     public void keyTyped(KeyEvent e) {
       idField = (JTextField) e.getSource();
       idCheckStatus = 0;
       idField.setBorder(new LineBorder(Color.WHITE, 1));
       if(idField.getText().length() > 2) {
           try {
            idCheck(idField.getText());
          } catch (SQLException e1) {
            e1.printStackTrace();
          }
       } else {
         jLabel1.setForeground(Color.RED);
         jLabel1.setText("3글자 이상 입력해주세요.");
       }
     }
    });

    pwField.addFocusListener(this);
    pwField.addKeyListener(new KeyAdapter() { // 키 입력 이벤트 추가!
      public void keyReleased(KeyEvent e) {
        pwField = (JPasswordField) e.getSource();
        pwCheckStatus = 0;
        pwField.setBorder(new LineBorder(Color.WHITE, 1));
        if(pwField.getPassword().length > 3) {
          passwordCheck(pwField);
        } else if (pwCheckField.getPassword().length == pwField.getPassword().length) {
          pwCheckField.setText("");
          jLabel2.setForeground(Color.RED);
          jLabel8.setForeground(Color.RED);
        } else {
          jLabel2.setForeground(Color.RED);
          jLabel2.setText("4글자 이상 입력해주세요.");
          jLabel8.setText("");
          pwCheckField.setBorder(new LineBorder(Color.WHITE, 1));
        }
      }
    });
    
    pwCheckField.addFocusListener(this);
    pwCheckField.addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent e) {
        pwCheckField = (JPasswordField) e.getSource();
        pwCheckStatus = 0;
        pwCheckField.setBorder(new LineBorder(Color.WHITE, 1));
        if(pwCheckField.getPassword().length == pwField.getPassword().length) {
          passwordCheck(pwCheckField);
        } else {
          pwField.setBorder(new LineBorder(Color.RED, 1));
          pwCheckField.setBorder(new LineBorder(Color.RED, 1));
          jLabel2.setForeground(Color.RED);
          jLabel8.setForeground(Color.RED);
          jLabel2.setText("비밀번호가 일치하지 않습니다.");
          jLabel8.setText("비밀번호가 일치하지 않습니다.");
        }
      }
    });
    
    nameField.addFocusListener(this);
    nameField.addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent e) {
        nameField = (JTextField) e.getSource();
        nameCheckStatus = 0;
        nameField.setBorder(new LineBorder(Color.WHITE, 1));
        if(nameField.getText().length() > 1) {
          nameCheck(nameField.getText());
        } else {
          jLabel9.setForeground(Color.RED);
          jLabel9.setText("2글자 이상 입력해주세요.");
        }
      }
    });
    managerNumField.addFocusListener(this);
    managerNumField.addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent e) {
        managerNumField = (JTextField) e.getSource();
        if(managerNumField.getText().length() > 1) {
          managerNumField.setBorder(new LineBorder(Color.GREEN, 1));
          jLabel10.setText("");
        } else {
          managerNumField.setBorder(new LineBorder(Color.WHITE, 1));
          jLabel10.setText("일반사용자로 등록");
        }
      }
    });
    
    submitBtn.addActionListener(this);
    cancelBtn.addActionListener(this);
  }
  
  private void submitArea() {
    
    Dimension frameSize = this.getSize(); // 프레임 사이즈
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈

    this.setLocation((screenSize.width - frameSize.width)/3, (screenSize.height - frameSize.height)/4); // 화면 중앙
    this.setResizable(false); // 화면 크기 임의 변경 금지하는것.
    
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setBackground(new Color(153, 255, 153));

    kGradientPanel1.setkEndColor(new Color(51, 204, 204));
    kGradientPanel1.setkStartColor(new Color(124, 23, 153));

    submitBtn.setBackground(new Color(051, 204, 204));
    cancelBtn.setBackground(new Color(204, 204, 204));
    
    
    idLbl.setText("아이디");
    idLbl.setForeground(Color.white);
    idField.setText("");
    idField.setFocusable(true);
    
    
    pwLbl.setText("비밀번호");
    pwLbl.setForeground(Color.white);
    pwField.setText("");
    pwField.setName("PW1");
    
    pwCheckLbl.setText("비밀번호 확인");
    pwCheckLbl.setForeground(Color.white);
    pwCheckField.setText("");
    pwCheckField.setName("PW2");

    nameLbl.setText("이름");
    nameLbl.setForeground(Color.white);
    nameField.setText("");

    managerLbl.setText("관리자등록번호");
    managerLbl.setForeground(Color.white);
    managerNumField.setText("");

    submitBtn.setText("등록하기");
    cancelBtn.setText("취소");
    
    jLabel1.setFont(new Font("돋움", 1, 11));
    jLabel2.setFont(new Font("돋움", 1, 11));
    jLabel8.setFont(new Font("돋움", 1, 11));
    jLabel9.setFont(new Font("돋움", 1, 11));
    jLabel10.setFont(new Font("돋움", 1, 11));
    
    idField.setBorder(new LineBorder(Color.WHITE, 1));
    pwField.setBorder(new LineBorder(Color.WHITE, 1));
    pwCheckField.setBorder(new LineBorder(Color.WHITE, 1));
    nameField.setBorder(new LineBorder(Color.WHITE, 1));
    managerNumField.setBorder(new LineBorder(Color.WHITE, 1));
    
    javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
    kGradientPanel1.setLayout(kGradientPanel1Layout);
    kGradientPanel1Layout.setHorizontalGroup(
        kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(kGradientPanel1Layout.createSequentialGroup()
            .addGap(155, 155, 155)
            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel1Layout.createSequentialGroup()
                    .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pwCheckLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pwLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(idLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(managerLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(kGradientPanel1Layout.createSequentialGroup()
                            .addComponent(managerNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel10))
                        .addGroup(kGradientPanel1Layout.createSequentialGroup()
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel9))
                        .addGroup(kGradientPanel1Layout.createSequentialGroup()
                            .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel1))
                        .addGroup(kGradientPanel1Layout.createSequentialGroup()
                            .addComponent(pwField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel2))
                        .addGroup(kGradientPanel1Layout.createSequentialGroup()
                            .addComponent(pwCheckField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel8))))
                .addGroup(kGradientPanel1Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(49, 49, 49)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(184, Short.MAX_VALUE))
    );
    kGradientPanel1Layout.setVerticalGroup(
        kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(kGradientPanel1Layout.createSequentialGroup()
            .addGap(28, 28, 28)
            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(idLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1))
            .addGap(18, 18, 18)
            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(pwField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(pwLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2))
            .addGap(18, 18, 18)
            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(pwCheckField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(pwCheckLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel8))
            .addGap(18, 18, 18)
            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(nameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel9))
            .addGap(18, 18, 18)
            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(managerNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(managerLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel10))
            .addGap(38, 38, 38)
            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(57, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
}

  public static void main(String[] args) {
    
    EventQueue.invokeLater(new Runnable() {
        public void run() {
            new SubmitForm().setVisible(true);
        }
    });
  }

  public void setMain(MainProcess main) {
    this.main = main;
  }

  public void setDB(DBLogin db) {
    this.db = db;
  }

  public void setLoginForm(LoginForm loginForm) {
    this.loginForm = loginForm;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) { // 마우스로 클릭할 때
    Object btn = e.getSource();
    
    if(btn == submitBtn) {
      try {
        submit();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    }
    if(btn == cancelBtn) {
      cancel();
    }
  }

  @Override
  public void focusGained(FocusEvent e) { // 텍스트필드에서 포커스를 얻을 때 작동되는 메소드
    Object fuc = e.getSource();
    
    if (fuc == idField) {
        try {
            idCheck(idField.getText());
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
    } else if (fuc == pwField) {
        passwordCheck(pwField);
    } else if (fuc == pwCheckField) {
        passwordCheck(pwCheckField);
    } else if (fuc == nameField) {
        nameCheck(nameField.getText());
    } else if (fuc == managerNumField && managerNumField.getText().length() == 0) {
      jLabel10.setText("일반사용자로 등록");
    }
    
  }
  @Override
  public void focusLost(FocusEvent e) { // 텍스트필드에서 포커스를 잃을 때 작동되는 메소드
    Object fuc = e.getSource();
    
    if (fuc == idField) {
      try {
        idCheck(idField.getText());
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    } else if (fuc == pwField) {
      passwordCheck(pwField);
    } else if (fuc == pwCheckField) {
      passwordCheck(pwCheckField);
    } else if (fuc == nameField) {
      nameCheck(nameField.getText());
    } else if (fuc == managerNumField) {
      jLabel10.setText("");
    }
  }


  
  // 아이디 입력 텍스트필드에서 아이디 체크
  public void idCheck(String idText) throws SQLException {
    jLabel1.setForeground(Color.RED);
    String checkText = "^[a-zA-Z0-9가-힣]*$";
    String whitespaceCheck = "\\s*.+\\s*";
    if (idText.length() > 2 && idText.matches(checkText)) {
        if(db.idCheck(idField.getText()) != null) { // 여기서 에러나면 loginForm에서 DB객체 안넘어온것임(NullPointer)
          jLabel1.setText("사용할 수 없는 아이디입니다.");
          idField.setBorder(new LineBorder(Color.RED, 1));
        } else {
          jLabel1.setForeground(Color.GREEN);
          idField.setBorder(new LineBorder(Color.GREEN, 1));
          jLabel1.setText("확인완료!");
          idCheckStatus = 1;
        }
    } else if(idText.length() <= 2 && idText.length() > 0) {
      idField.setBorder(new LineBorder(Color.RED, 1));
      jLabel1.setText("3글자 이상 입력해주세요");
    } else if(idText.length() > 2 && !idText.matches(checkText) && idText.matches(whitespaceCheck)) {
      idField.setBorder(new LineBorder(Color.RED, 1));
      jLabel1.setText("공백은 사용할 수 없습니다.");
    } else {
      jLabel1.setText("");
    }
    
  }
  
  // 비밀번호 입력 텍스트필드에서 비밀번호 체크
  protected void passwordCheck(JTextField passwordField) {
    jLabel2.setForeground(Color.RED);
    jLabel8.setForeground(Color.RED);
    if(passwordField.getName().equals("PW1")) {
      if(passwordField.getText().length() > 3 && pwCheckField.getPassword().length == 0) { // 4자리 이상이고 확인 필드가 비었을 때
        jLabel2.setText("");
        jLabel8.setText("비밀번호를 한번 더 입력하세요.");
        pwCheckField.setBorder(new LineBorder(Color.WHITE, 1));
      } else if (passwordField.getText().length() > 3 && pwCheckField.getPassword().length > 0 // 4자리 이상, 확인필드가 값이 있을 때, 길이가 같지 않을 때
          && pwField.getPassword().length != pwCheckField.getPassword().length){
        pwCheckField.setBorder(new LineBorder(Color.WHITE, 1));
        pwCheckField.setText("");
        jLabel2.setText("");
        jLabel8.setText("");
      } else if (passwordField.getText().length() > 3 && pwCheckField.getPassword().length > 0 // 4자리 이상, 확인필드가 값이 있을 때, 길이가 같을 때
          && pwField.getPassword().length == pwCheckField.getPassword().length){
        passwordCheck(pwCheckField); // 확인할 필요가 있어서 호출.
      } else if(passwordField.getText().length() <= 3 && passwordField.getText().length() > 0) { // 4자리 이하 , 0보다 클때
        jLabel2.setText("4글자 이상 입력해주세요");
      } else { 
        jLabel2.setText("");
        jLabel8.setText("");
      }
    } else if(passwordField.getName().equals("PW2")) {
      if(pwField.getPassword().length == pwCheckField.getPassword().length && pwField.getPassword().length != 0) {
        int forLength;
        if(pwField.getPassword().length > pwCheckField.getPassword().length) {
          forLength = pwField.getPassword().length;
        } else {
          forLength = pwCheckField.getPassword().length;
        }
        char[] pw1 = pwField.getPassword();
        char[] pw2 = pwCheckField.getPassword();
        String p1 = "";
        String p2 = "";
        for (int i = 0; i < forLength; i++) {
          p1 = p1 + pw1[i];
          p2 = p2 + pw2[i];
        }
        if(p1.equals(p2) && pw1.length > 3 && pw2.length > 3) {
          jLabel2.setForeground(Color.GREEN);
          jLabel8.setForeground(Color.GREEN);
          pwField.setBorder(new LineBorder(Color.GREEN, 1));
          pwCheckField.setBorder(new LineBorder(Color.GREEN, 1));
          jLabel2.setText("확인완료!");
          jLabel8.setText("확인완료!");
          pwCheckStatus = 1;
        } else if (pw1.length <= 3 && pw2.length <= 3) {
          pwField.setBorder(new LineBorder(Color.RED, 1));
          pwCheckField.setBorder(new LineBorder(Color.RED, 1));
          jLabel2.setText("4글자 이상 입력해주세요");
          jLabel8.setText("4글자 이상 입력해주세요");
        } else {
          pwField.setBorder(new LineBorder(Color.RED, 1));
          pwCheckField.setBorder(new LineBorder(Color.RED, 1));
          jLabel2.setText("비밀번호가 일치하지 않습니다.");
          jLabel8.setText("비밀번호가 일치하지 않습니다.");
        }
      } else if (pwField.getPassword().length != pwCheckField.getPassword().length) {
        pwField.setBorder(new LineBorder(Color.RED, 1));
        pwCheckField.setBorder(new LineBorder(Color.RED, 1));
        jLabel2.setText("비밀번호가 일치하지 않습니다.");
        jLabel8.setText("비밀번호가 일치하지 않습니다.");
      } else {
        jLabel2.setText("");
        jLabel8.setText("");
      }

    }
  }
  
  // 이름 입력 텍스트필드에서 이름 체크
  protected void nameCheck(String nameText) {
    String checkText = "^[a-zA-Z0-9가-힣]*$";
    String whitespaceCheck = "\\s*.+\\s*";
    jLabel9.setForeground(Color.RED);
    if (nameText.length() > 1 && nameText.matches(checkText)) {
      jLabel9.setForeground(Color.GREEN);
      nameField.setBorder(new LineBorder(Color.GREEN, 1));
      jLabel9.setText("확인완료!");
      nameCheckStatus = 1;
    } else if (nameText.length() > 1 && !(nameText.matches(checkText)) && nameText.matches(whitespaceCheck)) {
      jLabel9.setForeground(Color.RED);
      jLabel9.setText("공백 및 특수문자는 포함할 수 없습니다.");
    } else if(nameText.length() == 1) {
      jLabel9.setForeground(Color.RED);
      jLabel9.setText("2글자 이상 입력해주세요");
    } else {
      jLabel9.setText("");
    }
  }
  
  // 등록하기 버튼을 누를 때.(managerNumCheck)
  @SuppressWarnings("deprecation")
  public void submit() throws SQLException {
    if(idCheckStatus == 1 && pwCheckStatus == 1 && nameCheckStatus == 1) {
      member.setId(idField.getText());
      member.setPwd(pwCheckField.getText());
      member.setName(nameField.getText());
      
      ManagerCheckNumber cn = new ManagerCheckNumber(); // 관리자등록번호만 따로 관리하려고 클래스 만듦
      String[] buttons = {"확인", "취소하기"};
      int checkStatus = 0;

      if (managerNumField.getText().length() > 0) { // 관리자등록번호가 작성되있다면 
        for(String check : cn.getManagerNumber()) {
          if(check.equals(managerNumField.getText())) { // 관리자등록번호중 한개라도 맞을 때.
            member.setUserGrade("10"); // 관리자
            
            checkStatus = JOptionPane.showOptionDialog(null, "(관리자)사용자 추가 하시겠습니까?", "등록하기", 
                          JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "확인");
            // 확인을 눌렀을 때 0, 취소를 눌렀을 때 1
            
            if(checkStatus == 0) {
                db.createMember(member);
                cancel();
            }
          } else { // 관리자등록번호가 맞지 않은경우...
            member.setUserGrade("20"); // 일반
            managerNumField.setBorder(new LineBorder(Color.RED, 1));
            jLabel10.setForeground(Color.RED);
            jLabel10.setText("일치하지 않습니다.");  
            
            checkStatus = JOptionPane.showOptionDialog(null, "(일반)사용자 추가 하시겠습니까?", "관리자번호 맞지않음!", 
                          JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "확인");
            
              if(checkStatus == 0) {
                db.createMember(member);
              }
              
          }
        }
        
      } else { // 관리자등록번호가 없는 경우
        member.setUserGrade("20"); // 일반
        
        checkStatus = JOptionPane.showOptionDialog(null, "(일반)사용자 추가 하시겠습니까?", "등록하기", 
                      JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "확인");
      
        if(checkStatus == 0) {
          db.createMember(member);
          cancel();
        }
      }
    } else { // 기존 입력 필드의 값이 잘못된 경우.
      if(idCheckStatus == 0) {
        idField.setBorder(new LineBorder(Color.RED, 1));
        jLabel1.setForeground(Color.RED);
        jLabel1.setText("확인해주세요");
      }
      if(pwCheckStatus == 0) {
        pwField.setBorder(new LineBorder(Color.RED, 1));
        pwCheckField.setBorder(new LineBorder(Color.RED, 1));
        jLabel2.setForeground(Color.RED);
        jLabel8.setForeground(Color.RED);
        jLabel2.setText("확인해주세요");
        jLabel8.setText("확인해주세요");
      }
      if(nameCheckStatus == 0) {
        nameField.setBorder(new LineBorder(Color.RED, 1));
        jLabel9.setForeground(Color.RED);
        jLabel9.setText("확인해주세요");
      }
      
    }
    
  }
  // 취소 버튼을 누를 때 , 입력이 종료됐을 때
  public void cancel() {
    this.dispose();
    
    loginForm.setVisible(true);
  }
}
