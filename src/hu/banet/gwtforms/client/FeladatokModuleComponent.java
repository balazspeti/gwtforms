package hu.banet.gwtforms.client;

import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.*;
//import com.google.gwt.sample.showcase.client.content.text.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;


public class FeladatokModuleComponent extends FormsModuleComponent {
  
  
  private RichTextArea richTextArea;
  private RichTextToolbar richTextToolbar;
  
  
  public FeladatokModuleComponent() {
    super("feladatok", "feladat", 3);
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
    label5.setWidth("66px");
    
    Label label6 = new Label("Feladó");
    label6.addStyleName("hsprompt");
    label6.setWidth("56px");

    Label label7 = new Label("Megoldó");
    label7.addStyleName("hsprompt");
    label7.setWidth("56px");
 
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
    
    TextBox textBox2 = new TextBox();
    textBox2.setWidth("80px");
    textBox2.addStyleName("hsinput");
    
    TextBox textBox3 = new TextBox();
    textBox3.setWidth("350px");
    textBox3.addStyleName("hsinput");
    
    TextBox textBox4 = new TextBox();
    textBox4.setWidth("100px");
    textBox4.addStyleName("hsinput");
    
    TextBox textBox5 = new TextBox();
    textBox5.setWidth("60px");
    textBox5.addStyleName("hsinput");
    
    TextBox textBox6 = new TextBox();
    textBox6.setWidth("50px");
    textBox6.addStyleName("hsinput");
    
    TextBox textBox7 = new TextBox();
    textBox7.setWidth("50px");
    textBox7.addStyleName("hsinput");
    
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.add(textBox1);
    horizontalPanel.add(textBox2);
    horizontalPanel.add(textBox3);
    horizontalPanel.add(textBox4);
    horizontalPanel.add(textBox5);
    horizontalPanel.add(textBox6);
    horizontalPanel.add(textBox7);
    
    verticalPanel.add(horizontalPanel);
    
    if ( richTextArea == null ) {
      this.richTextArea = new RichTextArea();
      this.richTextArea.addStyleName("hsinput");
      this.richTextArea.setWidth("100%");
      
      this.richTextToolbar = new RichTextToolbar(this.richTextArea);
      this.richTextToolbar.setWidth("100%");

      HorizontalPanel richTextAreaHorizontalPanel = new HorizontalPanel();
      richTextAreaHorizontalPanel.setWidth("100%");
      SimplePanel richTextAreaEmptySpace = new SimplePanel();
      richTextAreaEmptySpace.setWidth("4px");
      
      richTextAreaHorizontalPanel.add(this.richTextArea);
      richTextAreaHorizontalPanel.add(richTextAreaEmptySpace);
      
      this.add(this.richTextToolbar);
      this.add(richTextAreaHorizontalPanel);
      
      this.richTextArea.addFocusHandler(this);
      this.richTextArea.addKeyUpHandler(this); 
    }
    
    this.register(textBox1, "kulcs", true);
    this.register(textBox2, "tema", true);
    this.register(textBox3, "nev", true);
    this.register(this.richTextArea, "leiras", false);
    this.register(textBox4, "statusz", false);
    this.register(textBox5, "prioritas", false);
    this.register(textBox6, "felado", true);
    this.register(textBox7, "megoldo", true);
  }
  
  
}
