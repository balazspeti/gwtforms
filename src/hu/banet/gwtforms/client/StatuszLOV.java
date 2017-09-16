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


public class StatuszLOV extends FormsLOV<String> {
  
  
  protected String getName(String statusz) {
    return statusz;
  }
  
  
  public StatuszLOV() {
    super();
    
    add("Új");
    add("Szervezés alatt");
    add("Fejlesztés alatt");
    add("Tesztelés alatt");
    add("Telepítésre vár");
    add("Elfogadásra vár");
    add("Kész");
                                   
    Column<String, String> statuszColumn = new Column<String, String>(
        new TextCell()) {
      @Override
      public String getValue(String statusz) {
        return statusz;
      }
    };
    dataGrid.addColumn(statuszColumn, "Státusz");
    dataGrid.setColumnWidth(statuszColumn, 100, Style.Unit.PX);
    
    dataGrid.setRowData(0, new ArrayList<String>(nameMap.values()));
  }
  
  
}
