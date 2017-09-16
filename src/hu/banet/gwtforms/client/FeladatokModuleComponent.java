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
  private PrioritasLOV    prioritasLOV;
  private StatuszLOV      statuszLOV;
  
  
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
    label2.setWidth("106px");
    
    Label label3 = new Label("Rövid leírás");
    label3.addStyleName("hsprompt");
    label3.setWidth("356px");
 
    Label label4 = new Label("Státusz");
    label4.addStyleName("hsprompt");
    label4.setWidth("129px");
    
    Label label5 = new Label("Prioritás");
    label5.addStyleName("hsprompt");
    label5.setWidth("89px");
    
    Label label6 = new Label("Létrehozó");
    label6.addStyleName("hsprompt");
    label6.setWidth("129px");

    Label label7 = new Label("Megoldó");
    label7.addStyleName("hsprompt");
    label7.setWidth("129px");
 
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
    
    if ( prioritasLOV == null ) {
      prioritasLOV = new PrioritasLOV(/*connectionUrlTextBox*/);
    }    
    
    if ( statuszLOV == null ) {
      statuszLOV = new StatuszLOV(/*connectionUrlTextBox*/);
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
    temaWidget.setWidth("100px");
    temaWidget.addStyleName("hsinput");
    horizontalPanel.add(temaWidget);
    
    // nevWidget
    TextBox nevWidget = new TextBox();
    nevWidget.setWidth("350px");
    nevWidget.addStyleName("hsinput");
    horizontalPanel.add(nevWidget);
    
    // statuszWidget
    SuggestBox statuszWidget = new SuggestBox();
    statuszWidget.setWidth("100px");
    statuszWidget.addStyleName("hsinput");
    horizontalPanel.add(statuszWidget);
    
    Button statuszButton = new Button("?");
    horizontalPanel.add(statuszButton);
    
    // prioritasNevWidget
    /*ListBox prioritasWidget = new ListBox();
    prioritasWidget.addItem("");
    prioritasWidget.addItem("5");
    prioritasWidget.addItem("4");
    prioritasWidget.addItem("3");
    prioritasWidget.addItem("2");
    prioritasWidget.addItem("1");*/
    SuggestBox prioritasNevWidget = new SuggestBox();
    prioritasNevWidget.setWidth("60px");
    prioritasNevWidget.addStyleName("hsinput");    
    horizontalPanel.add(prioritasNevWidget);
    
    Button prioritasNevButton = new Button("?");
    horizontalPanel.add(prioritasNevButton);
    
    // letrehozoNevWidget
    SuggestBox letrehozoNevWidget = new SuggestBox();
    letrehozoNevWidget.setWidth("100px");
    letrehozoNevWidget.addStyleName("hsinput");
    horizontalPanel.add(letrehozoNevWidget);    
    
    Button letrehozoNevButton = new Button("?");
    horizontalPanel.add(letrehozoNevButton);    
    
    /*// letrehozoTaszWidget
    SuggestBox letrehozoTaszWidget = new SuggestBox();
    letrehozoTaszWidget.setWidth("50px");
    letrehozoTaszWidget.addStyleName("hsinput");
    horizontalPanel.add(letrehozoTaszWidget);*/      
    
    // megoldoNevWidget
    SuggestBox megoldoNevWidget = new SuggestBox();    
    megoldoNevWidget.setWidth("100px");
    megoldoNevWidget.addStyleName("hsinput");
    horizontalPanel.add(megoldoNevWidget);
    
    Button megoldoNevButton = new Button("?");
    horizontalPanel.add(megoldoNevButton);
    
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

    statuszButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        statuszLOV.show(statuszItem);
      }
    }); 
    
    // prioritasNevItem
    FormsItem prioritasNevItem = new FormsItem("prioritasNev", prioritasNevWidget, true, false);
    register(prioritasNevItem);    
    
    prioritasNevButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        prioritasLOV.show(prioritasNevItem);
      }
    });    
    
    // prioritasKodItem
    final FormsItem prioritasKodItem = new FormsItem("prioritas", null, true, true);
    register(prioritasKodItem);    
    
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
    
    // megoldoNevItem
    final FormsItem megoldoNevItem = new FormsItem("megoldoNev", megoldoNevWidget, true, false);
    megoldoNevItem.setLOV(felhasznalokLOV);
    register(megoldoNevItem);
    
    megoldoNevButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        felhasznalokLOV.show(megoldoNevItem);
      }
    });
    
    // megoldoTaszItem
    final FormsItem megoldoTaszItem = new FormsItem("megoldo", null, true, true);
    register(megoldoTaszItem);    
    
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
    
    megoldoTaszItem.setPostQuery(new Command() { 
      public void execute() { 
        megoldoNevItem.setValue(felhasznalokLOV.getByKey(megoldoTaszItem.getValue()).getNev());
      }
    });
    
    megoldoNevItem.setPostChange(new Command() {
      public void execute() {
        megoldoTaszItem.setValue(felhasznalokLOV.getByName(megoldoNevItem.getValue()).getTasz());
      }
    });    
    
    prioritasKodItem.setPostQuery(new Command() { 
      public void execute() { 
        prioritasNevItem.setValue(prioritasLOV.getByKey(prioritasKodItem.getValue()).getNev());
      }
    });
    
    prioritasNevItem.setPostChange(new Command() {
      public void execute() {
        prioritasKodItem.setValue(prioritasLOV.getByName(prioritasNevItem.getValue()).getKod());
      }
    });        
    
  }
  
  
}
