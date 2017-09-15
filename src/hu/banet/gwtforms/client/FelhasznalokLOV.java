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


public class FelhasznalokLOV extends FormsLOV<Felhasznalo> {
  
  
  private List<Felhasznalo> felhasznalok;
  
  
  public FelhasznalokLOV() {
    
    Felhasznalo[] felhasznaloTomb = { new Felhasznalo("Balázs Péter", "011161"),
                                      new Felhasznalo("Gipsz Jakab", "000000") };
    
    felhasznalok = Arrays.asList(felhasznaloTomb);
                                   
    Column<Felhasznalo, String> taszColumn = new Column<Felhasznalo, String>(
        new TextCell()) {
      @Override
      public String getValue(Felhasznalo felhasznalo) {
        return felhasznalo.getTasz();
      }
    };
    dataGrid.addColumn(taszColumn, "TASZ");
    dataGrid.setColumnWidth(taszColumn, 50, Style.Unit.PX);
      
    Column<Felhasznalo, String> nevColumn = new Column<Felhasznalo, String>(
        new TextCell()) {
      @Override
      public String getValue(Felhasznalo felhasznalo) {
        return felhasznalo.getNev();
      }
    };
    dataGrid.addColumn(nevColumn, "Név");
    dataGrid.setColumnWidth(nevColumn, 250, Style.Unit.PX);
    
    dataGrid.setRowData(0, felhasznalok); 
    
  }
  
  
  public String getSelected(Felhasznalo felhasznalo) {
    return felhasznalo.getTasz();
  }
  

  public List<String> getAll() {
    List<String> felhasznaloTaszok = new ArrayList<>();
    for ( Felhasznalo felhasznalo : felhasznalok ) {
      felhasznaloTaszok.add(felhasznalo.getTasz());
    }
    return felhasznaloTaszok;
  }
  
  
}
