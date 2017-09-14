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
    label6.setWidth("78px");

    Label label7 = new Label("Megoldó");
    label7.addStyleName("hsprompt");
    label7.setWidth("78px");
 
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
    
    TextBox textBox1 = new TextBox();
    textBox1.setWidth("100px");
    textBox1.addStyleName("hsinput");
    
    /*TextBox textBox2 = new TextBox();
    textBox2.setWidth("80px");
    textBox2.addStyleName("hsinput");*/
    
    String[] temak = {"Cégbíróság-egyablak", 
                      "Bíróság-egyablak", 
                      "Adatlapok",
                      "Adatok",
                      "Egyebek",
                      "Cégjegyzékszám",
                      "Egyéb"};
    MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
    oracle.addAll(Arrays.asList(temak));
    SuggestBox tema = new SuggestBox(oracle);
    tema.setWidth("80px");
    tema.addStyleName("hsinput");   
    
    TextBox textBox3 = new TextBox();
    textBox3.setWidth("350px");
    textBox3.addStyleName("hsinput");
    
    TextBox textBox4 = new TextBox();
    textBox4.setWidth("100px");
    textBox4.addStyleName("hsinput");
    
    /*TextBox textBox5 = new TextBox();
    textBox5.setWidth("60px");
    textBox5.addStyleName("hsinput");*/
    ListBox prioritas = new ListBox(); /*{
      public void onBrowserEvent(Event event) {
        Logger.getLogger("").log(Level.SEVERE, "before " + this.getSelectedIndex());
        event.preventDefault();
        event.stopPropagation();
        Logger.getLogger("").log(Level.SEVERE, "after " + this.getSelectedIndex());
      }
    };*/
    prioritas.addItem("");
    prioritas.addItem("5");
    prioritas.addItem("4");
    prioritas.addItem("3");
    prioritas.addItem("2");
    prioritas.addItem("1");
    prioritas.setWidth("80px");
    prioritas.addStyleName("hsinput");
    
    if ( felhasznalokLOV == null ) {
      felhasznalokLOV = new FelhasznalokLOV(/*connectionUrlTextBox*/);
    }
    
    TextBox letrehozoTextBox = new TextBox();
    letrehozoTextBox.setWidth("50px");
    letrehozoTextBox.addStyleName("hsinput");
    
    final FormsItem letrehozoItem = new FormsItem("letrehozo", letrehozoTextBox, true);
    
    Button letrehozoButton = new Button("?");
    letrehozoButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        felhasznalokLOV.show(letrehozoItem);
      }
    });
    
    TextBox megoldoTextBox = new TextBox();
    megoldoTextBox.setWidth("50px");
    megoldoTextBox.addStyleName("hsinput");
    
    final FormsItem megoldoItem = new FormsItem("megoldo", megoldoTextBox, true);
    
    Button megoldoButton = new Button("?");
    megoldoButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        felhasznalokLOV.show(megoldoItem);
      }
    });    
    
    TextBox textBox7 = new TextBox();
    textBox7.setWidth("50px");
    textBox7.addStyleName("hsinput");
    
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.add(textBox1);
    horizontalPanel.add(tema);
    horizontalPanel.add(textBox3);
    horizontalPanel.add(textBox4);
    horizontalPanel.add(prioritas);
    horizontalPanel.add(letrehozoTextBox);
    horizontalPanel.add(letrehozoButton);
    horizontalPanel.add(megoldoTextBox);
    horizontalPanel.add(megoldoButton);
    
    verticalPanel.add(horizontalPanel);
    
    if ( richTextArea == null ) {
      this.richTextArea = new RichTextArea();
      this.richTextArea.addStyleName("hsinput");
      this.richTextArea.setWidth("100%");
      this.richTextArea.setHeight("500px");
      
      this.richTextToolbar = new RichTextToolbar(this.richTextArea);
      this.richTextToolbar.setWidth("100%");

      HorizontalPanel richTextAreaHorizontalPanel = new HorizontalPanel();
      richTextAreaHorizontalPanel.setWidth("100%");
      SimplePanel richTextAreaEmptySpace = new SimplePanel();
      richTextAreaEmptySpace.setWidth("4px");
      
      richTextAreaHorizontalPanel.add(this.richTextArea);
      richTextAreaHorizontalPanel.add(richTextAreaEmptySpace);
      
      mainPanel.add(this.richTextToolbar);
      mainPanel.add(richTextAreaHorizontalPanel);
      
      this.richTextArea.addFocusHandler(this);
      this.richTextArea.addKeyUpHandler(this); 
    }
    
    this.register(null, "azon", true);
    this.register(textBox1, "kulcs", true);
    this.register(tema, "tema", true);
    this.register(textBox3, "nev", true);
    this.register(this.richTextArea, "leiras", false);
    this.register(textBox4, "statusz", true);
    this.register(prioritas, "prioritas", true);
    this.register(letrehozoItem);
    this.register(megoldoItem);
  }
  
  
}
