package hu.banet.gwtforms.client;

import com.google.gwt.cell.client.*;
import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;
import java.util.*;
import java.util.logging.*;


public class FelhasznalokLOV extends CodeLOV<Felhasznalo> {
  
  
  protected String getName(Felhasznalo felhasznalo) {
    return felhasznalo.getNev();
  }
  
  
  protected String getKey(Felhasznalo felhasznalo) {
    return felhasznalo.getTasz();
  }
  
  
  public FelhasznalokLOV() {
    super();
    
    add(new Felhasznalo("Balázs Péter", "011161"));
    add(new Felhasznalo("Gipsz Jakab", "000000"));
                                   
    Column<Felhasznalo, String> nevColumn = new Column<Felhasznalo, String>(
        new TextCell()) {
      @Override
      public String getValue(Felhasznalo felhasznalo) {
        return felhasznalo.getNev();
      }
    };
    dataGrid.addColumn(nevColumn, "Név");
    dataGrid.setColumnWidth(nevColumn, 250, Style.Unit.PX);
                                   
    Column<Felhasznalo, String> taszColumn = new Column<Felhasznalo, String>(
        new TextCell()) {
      @Override
      public String getValue(Felhasznalo felhasznalo) {
        return felhasznalo.getTasz();
      }
    };
    dataGrid.addColumn(taszColumn, "TASZ");
    dataGrid.setColumnWidth(taszColumn, 50, Style.Unit.PX);
    
    dataGrid.setRowData(0, new ArrayList<Felhasznalo>(nameMap.values()));
  }
  
  
}
