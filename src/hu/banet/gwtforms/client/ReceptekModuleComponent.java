package hu.banet.gwtforms.client;

import java.util.*;
import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;


public class ReceptekModuleComponent extends FormsModuleComponent {
  
  
  public ReceptekModuleComponent() {
    super("receptek", "recept", 5);
  }
  
  
  protected void renderHead(VerticalPanel verticalPanel) {
    super.renderHead(verticalPanel);
    
    Label label1 = new Label("Név");
    label1.addStyleName("hsprompt");
    label1.setWidth("206px");
    
    Label label2 = new Label("Sütési idő");
    label2.addStyleName("hsprompt");
    label2.setWidth("86px");
    
    Label label3 = new Label("Nehézségi fok");
    label3.addStyleName("hsprompt");
    label3.setWidth("106px");
    
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.add(label1);
    horizontalPanel.add(label2);
    horizontalPanel.add(label3);
    
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
    
    TextBox textBox3 = new TextBox();
    textBox3.setWidth("100px");
    textBox3.addStyleName("hsinput");
    
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.add(textBox1);
    horizontalPanel.add(textBox2);
    horizontalPanel.add(textBox3);
    
    verticalPanel.add(horizontalPanel);
    
    this.register(textBox1, "nev", true);
    this.register(textBox2, "sutesi_ido", false);
    this.register(textBox3, "nehezseg", false);
  }
  
  
}
