package hu.banet.gwtforms.client;

import hu.banet.gwtforms.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.logging.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import java.util.logging.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTForms implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);


  public void onModuleLoad() {
  
    ReceptekModuleComponent receptekModuleComponent = new ReceptekModuleComponent();
  
    final FormsWindow receptekDialogBox = new FormsWindow(false, false);
    receptekDialogBox.setText("Receptek");
    receptekDialogBox.setAnimationEnabled(false);
    receptekDialogBox.setWidget(receptekModuleComponent);    
    
    HozzavalokModuleComponent hozzavalokModuleComponent = new HozzavalokModuleComponent();
  
    final FormsWindow hozzavalokDialogBox = new FormsWindow(false, false);
    hozzavalokDialogBox.setText("Hozzávalók");
    hozzavalokDialogBox.setAnimationEnabled(false);
    hozzavalokDialogBox.setWidget(hozzavalokModuleComponent);    

    FeladatokModuleComponent feladatokModuleComponent = new FeladatokModuleComponent();
  
    final FormsWindow feladatokDialogBox = new FormsWindow(false, false);
    feladatokDialogBox.setText("Feladatok");
    feladatokDialogBox.setAnimationEnabled(false);
    feladatokDialogBox.setWidget(feladatokModuleComponent);            

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
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Új rekord", new Command() { 
      public void execute() {  }});
    szerkesztesAlMenuBar.addItem("Rekord törlése", new Command() { 
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
    ablakokAlMenuBar.addItem("Receptek", new Command() { 
      public void execute() { receptekDialogBox.center(); }});
    ablakokAlMenuBar.addItem("Hozzavalok", new Command() { 
      public void execute() { hozzavalokDialogBox.center(); }});   
    ablakokAlMenuBar.addItem("Feladatok", new Command() { 
      public void execute() { 
        FeladatokModuleComponent feladatokModuleComponent = new FeladatokModuleComponent();
  
        final FormsWindow feladatokDialogBox = new FormsWindow(false, false);
        feladatokDialogBox.setText("Feladatok");
        feladatokDialogBox.setAnimationEnabled(false);
        feladatokDialogBox.setWidget(feladatokModuleComponent);       
        feladatokDialogBox.center(); 
      }
    });         

    MenuBar ablakokFoMenuBar = new MenuBar();
    ablakokFoMenuBar.addItem("Ablakok", ablakokAlMenuBar);
    
    HorizontalPanel menuHorizontalPanel = new HorizontalPanel();
    menuHorizontalPanel.add(fajlFoMenuBar);
    menuHorizontalPanel.add(szerkesztesFoMenuBar);
    menuHorizontalPanel.add(ablakokFoMenuBar);
    
    TextBox tesztTextBox = new TextBox();
    tesztTextBox.setEnabled(false);
    menuHorizontalPanel.add(tesztTextBox);
    
    RootPanel.get("gombContainer").add(menuHorizontalPanel);
    
  }
}
