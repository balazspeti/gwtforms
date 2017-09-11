package hu.banet.gwtforms.client;

import com.google.gwt.cell.client.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.logging.client.*;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;
import java.util.*;
import java.util.logging.*;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTForms implements EntryPoint {

  public void onModuleLoad() {
  
    final TextBox connectionUrlTextBox = new TextBox();
  
    /*ReceptekModuleComponent receptekModuleComponent = new ReceptekModuleComponent();
  
    final FormsWindow receptekDialogBox = new FormsWindow(false, false);
    receptekDialogBox.setText("Receptek");
    receptekDialogBox.setAnimationEnabled(false);
    receptekDialogBox.setWidget(receptekModuleComponent);    
    
    HozzavalokModuleComponent hozzavalokModuleComponent = new HozzavalokModuleComponent();
  
    final FormsWindow hozzavalokDialogBox = new FormsWindow(false, false);
    hozzavalokDialogBox.setText("Hozzávalók");
    hozzavalokDialogBox.setAnimationEnabled(false);
    hozzavalokDialogBox.setWidget(hozzavalokModuleComponent);  */           

    MenuBar fajlAlMenuBar = new MenuBar(true);
    /*fajlAlMenuBar.addItem("Bezárás", new Command() { 
      public void execute() {  }});   
    fajlAlMenuBar.addItem("Mindent bezár", new Command() { 
      public void execute() {  }});*/
    fajlAlMenuBar.addItem("Jóváhagyás", new Command() { 
      public void execute() { FormsModuleComponent.getActiveComponent().commitForm(); }});   
    /*fajlAlMenuBar.addItem("Jóváhagyás és folytatás", new Command() { 
      public void execute() {  }});
    fajlAlMenuBar.addItem("Nyomtatás", new Command() { 
      public void execute() {  }});
    fajlAlMenuBar.addItem("Frissít", new Command() { 
      public void execute() {  }});
    fajlAlMenuBar.addItem("Kilépés", new Command() { 
      public void execute() {  }});*/
      
    MenuBar fajlFoMenuBar = new MenuBar();
    fajlFoMenuBar.addItem("Fájl", fajlAlMenuBar);
    
    MenuBar szerkesztesAlMenuBar = new MenuBar(true);
    /*szerkesztesAlMenuBar.addItem("Kivágás", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Másolás", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Beillesztés", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Mező ürítése", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Előző mező lemásolása", new Command() { 
      public void execute() {  }});*/
    szerkesztesAlMenuBar.addItem("Blokk ürítése", new Command() { 
      public void execute() { FormsModuleComponent.getActiveComponent().clearBlock(); }});
    /*szerkesztesAlMenuBar.addItem("Forma ürítése", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Keresés", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Kijelölés váltás", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Mindent kijelöl", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Megszűnteti a kijelölést", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Kiválasztás hatóköre", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Lekérdezés", new Command() { 
      public void execute() {  }});*/
    szerkesztesAlMenuBar.addItem("Új rekord", new Command() { 
      public void execute() { FormsModuleComponent.getActiveComponent().createRecord(); }});
    /*szerkesztesAlMenuBar.addItem("Rekord törlése", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Rekord ürítése", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Visszavonás", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Előző rekord lemásolása", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Navigálás", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Értéklista", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Szerkesztő", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Mező szerinti rendezés", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Rendezési feltétel törlése", new Command() { 
      public void execute() {  }});*/
    szerkesztesAlMenuBar.addItem("Lekérdezés mód", new Command() { 
      public void execute() { FormsModuleComponent.getActiveComponent().enterQuery(); }});
    szerkesztesAlMenuBar.addItem("Lekérdezés végrehajtása", new Command() { 
      public void execute() { FormsModuleComponent.getActiveComponent().executeQuery(); }});   
    szerkesztesAlMenuBar.addItem("Kilépés a lekérdezésből", new Command() { 
      public void execute() { FormsModuleComponent.getActiveComponent().cancelQuery(); }});   
   
    MenuBar szerkesztesFoMenuBar = new MenuBar();
    szerkesztesFoMenuBar.addItem("Szerkesztés", szerkesztesAlMenuBar);
    
    MenuBar ablakokAlMenuBar = new MenuBar(true);
    /*ablakokAlMenuBar.addItem("Receptek", new Command() { 
      public void execute() { receptekDialogBox.center(); }});
    ablakokAlMenuBar.addItem("Hozzavalok", new Command() { 
      public void execute() { hozzavalokDialogBox.center(); }});*/   
    ablakokAlMenuBar.addItem("Feladatok", new Command() { 
      public void execute() { 
        FeladatokModuleComponent feladatokModuleComponent = new FeladatokModuleComponent();
        feladatokModuleComponent.setConnectionUrlTextBox(connectionUrlTextBox);
  
        final FormsWindow feladatokDialogBox = new FormsWindow(false, false);
        feladatokDialogBox.setText("Feladatok");
        feladatokDialogBox.setAnimationEnabled(false);
        feladatokDialogBox.setWidget(feladatokModuleComponent);       
        feladatokDialogBox.center(); 
      }
    });
    ablakokAlMenuBar.addItem("Feladatok 2", new Command() {
      public void execute() {
        List<Feladat> feladatok = Arrays.asList(
          new Feladat("1",
                      "SD0352352",
                      "Cégbíróság-egyablak",
                      "Bankszámlaszámok feldolgozásának javítása",
                      "Ajjaj",
                      "Fejlesztés alatt",
                      "1",
                      "011161",
                      "011161"),
          new Feladat("2",
                      "0123456789",
                      "Bíróság-egyablak",
                      "Szervezet típus adat javítása",
                      "Ez meg mi lesz vajon?",
                      "Fejlesztés alatt",
                      "2",
                      "011161",
                      "011161"));
          
        CellTable<Feladat> cellTable = new CellTable<Feladat>();
        cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
        
        Column<Feladat, String> kulcsColumn = new Column<Feladat, String>(
            new TextInputCell()) {
          @Override
          public String getValue(Feladat feladat) {
            return feladat.getKulcs();
          }
        };
        cellTable.addColumn(kulcsColumn, "Kulcs");
        cellTable.setColumnWidth(kulcsColumn, 100, Style.Unit.PX);
      
        Column<Feladat, String> temaColumn = new Column<Feladat, String>(
            new TextInputCell()) {
          @Override
          public String getValue(Feladat feladat) {
            return feladat.getTema();
          }
        };
        cellTable.addColumn(temaColumn, "Téma");
        cellTable.setColumnWidth(temaColumn, 80, Style.Unit.PX);
        
        Column<Feladat, String> nevColumn = new Column<Feladat, String>(
            new TextInputCell()) {
          @Override
          public String getValue(Feladat feladat) {
            return feladat.getNev();
          }
        };
        cellTable.addColumn(nevColumn, "Név");
        cellTable.setColumnWidth(nevColumn, 350, Style.Unit.PX);
        
        Column<Feladat, String> statuszColumn = new Column<Feladat, String>(
            new TextInputCell()) {
          @Override
          public String getValue(Feladat feladat) {
            return feladat.getStatusz();
          }
        };
        cellTable.addColumn(statuszColumn, "Státusz");
        cellTable.setColumnWidth(statuszColumn, 100, Style.Unit.PX);

        Column<Feladat, String> prioritasColumn = new Column<Feladat, String>(
            new TextInputCell()) {
          @Override
          public String getValue(Feladat feladat) {
            return feladat.getPrioritas();
          }
        };
        cellTable.addColumn(prioritasColumn, "Prioritás");
        cellTable.setColumnWidth(prioritasColumn, 80, Style.Unit.PX);   

        Column<Feladat, String> letrehozoColumn = new Column<Feladat, String>(
            new TextInputCell()) {
          @Override
          public String getValue(Feladat feladat) {
            return feladat.getLetrehozo();
          }
        };
        cellTable.addColumn(letrehozoColumn, "Létrehozó");
        cellTable.setColumnWidth(letrehozoColumn, 50, Style.Unit.PX);
        
        Column<Feladat, String> megoldoColumn = new Column<Feladat, String>(
            new TextInputCell()) {
          @Override
          public String getValue(Feladat feladat) {
            return feladat.getMegoldo();
          }
        };
        cellTable.addColumn(megoldoColumn, "Megoldó");
        cellTable.setColumnWidth(megoldoColumn, 50, Style.Unit.PX);        
        
        /*final SingleSelectionModel<Employee> singleSelectionModel = new SingleSelectionModel<Employee>();
        cellTable.setSelectionModel(singleSelectionModel);
        singleSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
          public void onSelectionChange(SelectionChangeEvent event) {
            Employee selectedEmployee = singleSelectionModel.getSelectedObject();
            if (selectedEmployee != null) {
              Window.alert("Selected: " + selectedEmployee.getName());
            }
          }
        });*/

        cellTable.setRowCount(feladatok.size(), true);
        cellTable.setRowData(0, feladatok);
        
        final FormsWindow feladatokDialogBox = new FormsWindow(false, false);
        feladatokDialogBox.setText("Feladatok");
        feladatokDialogBox.setAnimationEnabled(false);
        feladatokDialogBox.setWidget(cellTable);       
        feladatokDialogBox.center(); 
      }
    });

    MenuBar ablakokFoMenuBar = new MenuBar();
    ablakokFoMenuBar.addItem("Ablakok", ablakokAlMenuBar);
    
    HorizontalPanel menuHorizontalPanel = new HorizontalPanel();
    menuHorizontalPanel.add(fajlFoMenuBar);
    menuHorizontalPanel.add(szerkesztesFoMenuBar);
    menuHorizontalPanel.add(ablakokFoMenuBar);
    
    menuHorizontalPanel.add(connectionUrlTextBox);
    
    RootPanel.get("gombContainer").add(menuHorizontalPanel);
    
  }
  
}
