package com.application.requireClass;
/* 설명...
 * JtextField에 숫자만 입력받을 수 있게 하는 클래스. (JtextField 객체에 해당 클래스를 세팅)*/
import javax.swing.text.*;

// JtextField 에서 숫자만 입력하게 할 수 있는 기능.
@SuppressWarnings("serial")
public class IntegerDocument extends PlainDocument {

  int currentValue = 0;

  public IntegerDocument() {
  }

  public int getValue() {
    return currentValue;
  }

  public void insertString(int offset, String string, AttributeSet attributes) throws BadLocationException {

    if (string == null) {
      return;
    } else {
      String newValue;
      int length = getLength();
      if (length == 0) {
        newValue = string;
      } else {
        String currentContent = getText(0, length);
        StringBuffer currentBuffer = new StringBuffer(currentContent);
        currentBuffer.insert(offset, string);
        newValue = currentBuffer.toString();
      }
      currentValue = checkInput(newValue, offset);
      super.insertString(offset, string, attributes);
    }
  }
  public void remove(int offset, int length) throws BadLocationException {
    int currentLength = getLength();
    String currentContent = getText(0, currentLength);
    String before = currentContent.substring(0, offset);
    String after = currentContent.substring(length+offset, currentLength);
    String newValue = before + after;
    currentValue = checkInput(newValue, offset);
    super.remove(offset, length);
  
  }
  
  public int checkInput(String proposedValue, int offset) throws BadLocationException {
    if (proposedValue.length() > 0) {
      try {
        int newValue = Integer.parseInt(proposedValue);
        
       return newValue;
      } catch (NumberFormatException e) {
        throw new BadLocationException(proposedValue, offset);
      }
    } else {
      return 0;
    }
  }
}


