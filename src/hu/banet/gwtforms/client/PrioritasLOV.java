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


public class PrioritasLOV extends CodeLOV<Prioritas> {
  
  
  protected String getName(Prioritas prioritas) {
    return prioritas.getNev();
  }
  
  
  protected String getKey(Prioritas prioritas) {
    return prioritas.getKod();
  }
  
  
  public PrioritasLOV() {
    super();
    
    add(new Prioritas("1", "Sürgős"));
    add(new Prioritas("2", "Fontos"));
    add(new Prioritas("3", "Átlagos"));
    add(new Prioritas("4", "Alacsony"));
    add(new Prioritas("5", "Mellékes"));
                                   
    Column<Prioritas, String> nevColumn = new Column<Prioritas, String>(
        new TextCell()) {
      @Override
      public String getValue(Prioritas prioritas) {
        return prioritas.getNev();
      }
    };
    dataGrid.addColumn(nevColumn, "Név");
    dataGrid.setColumnWidth(nevColumn, 80, Style.Unit.PX);
                                   
    Column<Prioritas, String> kodColumn = new Column<Prioritas, String>(
        new TextCell()) {
      @Override
      public String getValue(Prioritas prioritas) {
        return prioritas.getKod();
      }
    };
    dataGrid.addColumn(kodColumn, "Kód");
    dataGrid.setColumnWidth(kodColumn, 30, Style.Unit.PX);
    
    dataGrid.setRowData(0, new ArrayList<Prioritas>(nameMap.values()));
  }
  
  
}
