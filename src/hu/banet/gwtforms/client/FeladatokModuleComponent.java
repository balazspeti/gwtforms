package hu.banet.gwtforms.client;

import com.google.gwt.cell.client.*;
import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.http.client.*;
//import com.google.gwt.sample.showcase.client.content.text.*;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.xml.client.*;
import java.util.*;
import java.util.logging.*;


public class FeladatokModuleComponent extends FormsModuleComponent {
  
  
  private RichTextArea    richTextArea;
  private RichTextToolbar richTextToolbar;
  private FelhasznalokLOV felhasznalokLOV;
  
  
  public FeladatokModuleComponent() {
    super("feladatok", "feladat", 10);
  }
  
  
  protected void renderHead(VerticalPanel verticalPanel) {
    super.renderHead(verticalPanel);
    
    Label label1 = new Label("SD/IME szám");
    label1.addStyleName("hsprompt");
    label1.setWidth("106px");
    
    Label label2 = new Label("Téma");
    label2.addStyleName("hsprompt");
    label2.setWidth("86px");
    
    Label label3 = new Label("Rövid leírás");
    label3.addStyleName("hsprompt");
    label3.setWidth("356px");
 
    Label label4 = new Label("Státusz");
    label4.addStyleName("hsprompt");
    label4.setWidth("106px");
    
    Label label5 = new Label("Prioritás");
    label5.addStyleName("hsprompt");
    label5.setWidth("80px");
    
    Label label6 = new Label("Létrehozó");
    label6.addStyleName("hsprompt");
    label6.setWidth("79px");

    Label label7 = new Label("Megoldó");
    label7.addStyleName("hsprompt");
    label7.setWidth("79px");
 
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.add(label1);
    horizontalPanel.add(label2);
    horizontalPanel.add(label3);
    horizontalPanel.add(label4);
    horizontalPanel.add(label5);
    horizontalPanel.add(label6);
    horizontalPanel.add(label7);
    
    verticalPanel.add(horizontalPanel);
  }  
  
  
  protected void renderRecord(VerticalPanel verticalPanel) {
    super.renderRecord(verticalPanel);
    
    if ( felhasznalokLOV == null ) {
      felhasznalokLOV = new FelhasznalokLOV(/*connectionUrlTextBox*/);
    }
    
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    verticalPanel.add(horizontalPanel);
    
    // kulcsWidget
    TextBox kulcsWidget = new TextBox();
    kulcsWidget.setWidth("100px");
    kulcsWidget.addStyleName("hsinput");   
    horizontalPanel.add(kulcsWidget);
    
    // temaWidget
    String[] temak = {"Cégbíróság-egyablak", 
                      "Bíróság-egyablak", 
                      "Adatlapok",
                      "Adatok",
                      "Egyebek",
                      "Cégjegyzékszám",
                      "Egyéb"};
    MultiWordSuggestOracle temaOracle = new MultiWordSuggestOracle();
    temaOracle.addAll(Arrays.asList(temak));
    SuggestBox temaWidget = new SuggestBox(temaOracle);
    temaWidget.setWidth("80px");
    temaWidget.addStyleName("hsinput");
    horizontalPanel.add(temaWidget);
    
    // nevWidget
    TextBox nevWidget = new TextBox();
    nevWidget.setWidth("350px");
    nevWidget.addStyleName("hsinput");
    horizontalPanel.add(nevWidget);
    
    // statuszWidget
    TextBox statuszWidget = new TextBox();
    statuszWidget.setWidth("100px");
    statuszWidget.addStyleName("hsinput");
    horizontalPanel.add(statuszWidget);
    
    // prioritasWidget
    ListBox prioritasWidget = new ListBox(); /*{
      public void onBrowserEvent(Event event) {
        Logger.getLogger("").log(Level.SEVERE, "before " + this.getSelectedIndex());
        event.preventDefault();
        event.stopPropagation();
        Logger.getLogger("").log(Level.SEVERE, "after " + this.getSelectedIndex());
      }
    };*/
    prioritasWidget.addItem("");
    prioritasWidget.addItem("5");
    prioritasWidget.addItem("4");
    prioritasWidget.addItem("3");
    prioritasWidget.addItem("2");
    prioritasWidget.addItem("1");
    prioritasWidget.setWidth("80px");
    prioritasWidget.addStyleName("hsinput");    
    horizontalPanel.add(prioritasWidget);
    
    // letrehozoNevWidget
    SuggestBox letrehozoNevWidget = new SuggestBox();
    letrehozoNevWidget.setWidth("50px");
    letrehozoNevWidget.addStyleName("hsinput");
    horizontalPanel.add(letrehozoNevWidget);    
    
    Button letrehozoNevButton = new Button("?");
    horizontalPanel.add(letrehozoNevButton);    
    
    /*// letrehozoTaszWidget
    SuggestBox letrehozoTaszWidget = new SuggestBox();
    letrehozoTaszWidget.setWidth("50px");
    letrehozoTaszWidget.addStyleName("hsinput");
    horizontalPanel.add(letrehozoTaszWidget);*/      
    
    // megoldoWidget
    SuggestBox megoldoWidget = new SuggestBox();    
    megoldoWidget.setWidth("50px");
    megoldoWidget.addStyleName("hsinput");
    horizontalPanel.add(megoldoWidget);
    
    Button megoldoButton = new Button("?");
    horizontalPanel.add(megoldoButton);
    
    // leirasWidget
    if ( richTextArea == null ) {
      richTextArea = new RichTextArea();
      richTextArea.addStyleName("hsinput");
      richTextArea.setWidth("100%");
      richTextArea.setHeight("500px");
      
      richTextToolbar = new RichTextToolbar(richTextArea);
      richTextToolbar.setWidth("100%");

      HorizontalPanel richTextAreaHorizontalPanel = new HorizontalPanel();
      richTextAreaHorizontalPanel.setWidth("100%");
      SimplePanel richTextAreaEmptySpace = new SimplePanel();
      richTextAreaEmptySpace.setWidth("4px");
      
      richTextAreaHorizontalPanel.add(richTextArea);
      richTextAreaHorizontalPanel.add(richTextAreaEmptySpace);
      
      mainPanel.add(this.richTextToolbar);
      mainPanel.add(richTextAreaHorizontalPanel);
    }    
    
    // azonItem
    FormsItem azonItem = new FormsItem("azon", null, true, true);
    register(azonItem);
    
    // kulcsItem
    FormsItem kulcsItem = new FormsItem("kulcs", kulcsWidget, true, true);
    register(kulcsItem);
    
    // temaItem
    FormsItem temaItem = new FormsItem("tema", temaWidget, true, true);
    register(temaItem);
    
    // nevItem
    FormsItem nevItem = new FormsItem("nev", nevWidget, true, true);
    register(nevItem);
    
    // statuszItem
    FormsItem statuszItem = new FormsItem("statusz", statuszWidget, true, true);
    register(statuszItem);
    
    // prioritasItem
    FormsItem prioritasItem = new FormsItem("prioritas", prioritasWidget, true, true);
    register(prioritasItem);    
    
    // letrehozoNevItem
    final FormsItem letrehozoNevItem = new FormsItem("letrehozoNev", letrehozoNevWidget, true, false);
    letrehozoNevItem.setLOV(felhasznalokLOV);
    register(letrehozoNevItem);
    
    letrehozoNevButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        felhasznalokLOV.show(letrehozoNevItem);
      }
    });
    
    // letrehozoTaszItem
    final FormsItem letrehozoTaszItem = new FormsItem("letrehozo", null, true, true);
    register(letrehozoTaszItem);
    
    // megoldoItem
    final FormsItem megoldoItem = new FormsItem("megoldo", megoldoWidget, true, true);
    megoldoItem.setLOV(felhasznalokLOV);
    register(megoldoItem);
    
    megoldoButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        felhasznalokLOV.show(megoldoItem);
      }
    });
    
    // leirasItem
    FormsItem leirasItem = new FormsItem("leiras", richTextArea, false, true);
    register(leirasItem);

    // commands
    letrehozoTaszItem.setPostQuery(new Command() { 
      public void execute() { 
        letrehozoNevItem.setValue(felhasznalokLOV.getByKey(letrehozoTaszItem.getValue()).getNev());
      }
    });
    
    letrehozoNevItem.setPostChange(new Command() {
      public void execute() {
        letrehozoTaszItem.setValue(felhasznalokLOV.getByName(letrehozoNevItem.getValue()).getTasz());
      }
    });
    
  }
  
  
}
