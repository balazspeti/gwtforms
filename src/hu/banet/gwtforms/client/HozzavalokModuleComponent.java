package hu.banet.gwtforms.client;

import java.util.*;
import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;


public class HozzavalokModuleComponent extends FormsModuleComponent {
  
  
  public HozzavalokModuleComponent() {
    super("hozzavalok", "hozzavalo", 1);
  }
  
  
  protected void renderHead(VerticalPanel verticalPanel) {
    super.renderHead(verticalPanel);
    
    Label label1 = new Label("Név");
    label1.addStyleName("hsprompt");
    label1.setWidth("206px");
    
    Label label2 = new Label("Mennyiség");
    label2.addStyleName("hsprompt");
    label2.setWidth("86px");
    
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.add(label1);
    horizontalPanel.add(label2);
    
    verticalPanel.add(horizontalPanel);    
  }  
  
  
  protected void renderRecord(VerticalPanel verticalPanel) {
    super.renderRecord(verticalPanel);
    
    TextBox textBox1 = new TextBox();
    textBox1.setWidth("200px");
    textBox1.addStyleName("hsinput");
    
    TextBox textBox2 = new TextBox();
    textBox2.setWidth("80px");
    textBox2.addStyleName("hsinput");
    
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.add(textBox1);
    horizontalPanel.add(textBox2);
    
    verticalPanel.add(horizontalPanel);
    
    this.register(textBox1, "nev", true);
    this.register(textBox2, "mennyiseg", false);
  }
  
  
}
