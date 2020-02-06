package com.application.requireClass;
/* 설명...
 * 프로젝트에서 사용할 모저리가 둥근 버튼..*/
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class RoundedButton extends JButton{

  private static final long serialVersionUID = 1L;
  
  public RoundedButton() {
    super();
    decorate();
  }

  public RoundedButton(String text) {
    super(text);
    decorate();
  }
  
  public RoundedButton(Action action) {
    super(action);
    decorate();
  }
  
  public RoundedButton(Icon icon) {
    super(icon);
    decorate();
  }

  public RoundedButton(String text, Icon icon) {
    super(text, icon);
    decorate();
  }
  protected void decorate() {
    setBorderPainted(false);
    setOpaque(false);
  }
  
  @Override protected void paintComponent(Graphics g) {
    int width = getWidth();
    int height = getHeight();
    
    Graphics2D graphics = (Graphics2D) g;
    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    if (getModel().isArmed()) {
      graphics.setColor(getBackground().darker()); 
     } else if (getModel().isRollover()) {
      graphics.setColor(getBackground().brighter());
     } else {
      graphics.setColor(getBackground());
     } graphics.fillRoundRect(0, 0, width, height, 22, 22);
   
     FontMetrics fontMetrics = graphics.getFontMetrics();
     Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
     
     int textX = (width - stringBounds.width - 2) / 2;
     int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
    
     graphics.setColor(getForeground()); 
     graphics.setFont(new Font("나눔고딕", 0, 13));
     graphics.drawString(getText(), textX, textY);
     graphics.dispose();
     
     super.paintComponent(g);
     }

  
}
