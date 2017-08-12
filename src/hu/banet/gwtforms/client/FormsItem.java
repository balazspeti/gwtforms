package hu.banet.gwtforms.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import java.util.logging.*;


public class FormsItem {


  private String name;
  private FocusWidget widget;
  private String value;
  private boolean changed;
  private FormsRecord record;
  private boolean queryable;
  
 
  public FormsItem(String name, FocusWidget widget, boolean queryable) {
    this.name = name;
    this.widget = widget;
    this.value = "";
    this.changed = false;
    this.queryable = queryable;
  } 
  
  
  public String getName() {
    return this.name;
  }
  
  
  public boolean isChanged() {
    return this.changed;
  }
  
  
  public void setNotChanged() {
    this.changed = false;
  }
  
  
  public void setRecord(FormsRecord record) {
    this.record = record;
  }
  
  
  public boolean isEnabled() {
    return this.widget.isEnabled();
  }
  
  
  public boolean isSource(FocusEvent event) {
    return event.getSource() == this.widget;
  }
  
  
  public void setEnabled(boolean flag) {
    this.widget.setEnabled(flag);
  }
  
  
  public void setFocus(boolean flag) {
    this.widget.setFocus(flag);
  }
  
  
  public void addStyleName(String styleName) {
    if ( !( this.widget instanceof RichTextArea ) ) {
      this.widget.addStyleName(styleName);
    }
  }  
  
  
  public void removeStyleName(String styleName) {
    if ( !( this.widget instanceof RichTextArea ) ) {
      this.widget.removeStyleName(styleName);
    }
  }
  
  
  public void selectAll() {
    if ( this.widget instanceof ValueBoxBase ) {
      if ( !( this.widget instanceof RichTextArea ) ) {      
        ((ValueBoxBase) this.widget).selectAll();
      }
    }
  }
  
  
  public String getValue() {
    return this.value;
  }
  
  
  public String getText() {
    if ( this.widget instanceof ValueBoxBase ) {
      return ((ValueBoxBase) this.widget).getText();
    }
    else if ( this.widget instanceof RichTextArea ) {
      return ((RichTextArea) this.widget).getHTML();
    }
    return "";
  }
  
  
  public void setText(String text) {
    if ( this.widget instanceof ValueBoxBase ) {
      ((ValueBoxBase) this.widget).setText(text);
    }
    else if ( this.widget instanceof RichTextArea ) {
      ((RichTextArea) this.widget).setHTML(text);
    }
    this.value = text;
  }
  
  
  public boolean isQueryable() {
    return this.queryable;
  }
  
  
  public void change() {
    Logger.getLogger("").log(Level.SEVERE, "change: " + this.name);
    if ( !this.value.equals(this.getText()) ) {
      this.value = this.getText();
      this.changed = true;
      if ( "NEW".equals(this.record.getState()) ) {
        this.record.setState("INSERT");
      }
      else if ( "QUERY".equals(this.record.getState()) ) {
        this.record.setState("CHANGED");
      }
    }
  }
  
  
  public void refresh() {
    if ( !this.value.equals(this.getText()) ) {
      this.setText(this.value);
    }
  }
  
  
}