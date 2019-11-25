package com.application.MultipleRowHeader;

import java.awt.Dimension;

/**
* @version 1.0 11/22/98
*/

interface CellAttribute {
  public void addColumn();
  public void addRow();
  public void insertRow(int row);
  public Dimension getSize();
  public void setSize(Dimension size);

}
