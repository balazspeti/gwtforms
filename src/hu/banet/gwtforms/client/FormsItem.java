package hu.banet.gwtforms.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import java.util.logging.*;


public class FormsItem implements FocusHandler {


  private String name;
  private Widget widget;
  private String value;
  private boolean changed;
  private FormsRecord record;
  private boolean queryable;
  
 
  public FormsItem(String name, Widget widget, boolean queryable) {
    this.name = name;
    this.widget = widget;
    this.value = "";
    this.changed = false;
    this.queryable = queryable;
    
    if ( widget instanceof TextBox ) {
      ((TextBox) widget).addFocusHandler(this);
    }
    else if ( widget instanceof ListBox ) {
      ((ListBox) widget).addFocusHandler(this);   
    }
    else if ( widget instanceof SuggestBox ) {
      ((SuggestBox) widget).getValueBox().addFocusHandler(this);
    }
  } 
  
  
  public String getName() {
    return this.name;
  }
  
  
  public Widget getWidget() {
    return widget;
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
    //Logger.getLogger("").log(Level.SEVERE, "isEnabled: " + this.name);
    if ( this.widget instanceof FocusWidget ) {
      return ((FocusWidget) this.widget).isEnabled();
    }
    else if ( this.widget instanceof SuggestBox ) {
      return ((SuggestBox) this.widget).isEnabled();
    }
    return false;
  }
  
  
  public boolean isSource(FocusEvent event) {
    if ( this.widget == null ) {
      return false;
    }
    if ( this.widget instanceof SuggestBox ) {
      return event.getSource() == ((SuggestBox) this.widget).getValueBox();
    }
    return event.getSource() == this.widget;
  }
  
  
  public void setEnabled(boolean flag) {
    //Logger.getLogger("").log(Level.SEVERE, "setEnabled: " + this.name);
    if ( this.widget instanceof FocusWidget ) {
      ((FocusWidget) this.widget).setEnabled(flag);
    }
    else if ( this.widget instanceof SuggestBox ) {
      ((SuggestBox) this.widget).setEnabled(flag);
    }
  }
  
  
  public void setFocus(boolean flag) {
    //Logger.getLogger("").log(Level.SEVERE, "setFocus: " + this.name);
    if ( this.widget instanceof FocusWidget ) {
      ((FocusWidget) this.widget).setFocus(flag);
    }
    else if ( this.widget instanceof SuggestBox ) {
      ((SuggestBox) this.widget).setFocus(flag);
    }
  }
  
  
  public void addStyleName(String styleName) {
    //Logger.getLogger("").log(Level.SEVERE, "addStyleName: " + this.name);
    if ( this.widget != null ) {
      if ( !( this.widget instanceof RichTextArea ) ) {
        this.widget.addStyleName(styleName);
      }
    }
  }  
  
  
  public void removeStyleName(String styleName) {
    //Logger.getLogger("").log(Level.SEVERE, "removeStyleName: " + this.name);
    if ( this.widget != null ) {
      if ( !( this.widget instanceof RichTextArea ) ) {
        this.widget.removeStyleName(styleName);
      }
    }
  }
  
  
  public void selectAll() {
    //Logger.getLogger("").log(Level.SEVERE, "selectAll: " + this.name);
    if ( this.widget instanceof ValueBoxBase ) {
      if ( !( this.widget instanceof RichTextArea ) ) {      
        ((ValueBoxBase) this.widget).selectAll();
      }
    }
    else if ( this.widget instanceof SuggestBox ) {
      ((SuggestBox) this.widget).getValueBox().selectAll();
    }
  }
  
  
  public String getValue() {
    return this.value;
  }
  
  
  public String getText() {
    //Logger.getLogger("").log(Level.SEVERE, "getText: " + this.name);
    if ( this.widget instanceof ValueBoxBase ) {
      return ((ValueBoxBase) this.widget).getText();
    }
    else if ( this.widget instanceof ListBox ) {
      return ((ListBox) this.widget).getItemText(((ListBox) this.widget).getSelectedIndex());
    }
    else if ( this.widget instanceof RichTextArea ) {
      return ((RichTextArea) this.widget).getHTML();
    }
    else if ( this.widget instanceof SuggestBox ) {
      return ((SuggestBox) this.widget).getText();
    }
    return "";
  }
  
  
  public void setText(String text) {
    //Logger.getLogger("").log(Level.SEVERE, "setText: " + this.name);
    if ( this.widget instanceof ValueBoxBase ) {
      ((ValueBoxBase) this.widget).setText(text);
    }
    else if ( this.widget instanceof ListBox ) {
      for ( int i=0; i<((ListBox) this.widget).getItemCount(); i++ ) {
        if ( text.equals(((ListBox) this.widget).getItemText(i)) ) {
          ((ListBox) this.widget).setSelectedIndex(i);
        }
      }
    }
    else if ( this.widget instanceof RichTextArea ) {
      ((RichTextArea) this.widget).setHTML(text);
    }
    else if ( this.widget instanceof SuggestBox ) {
      ((SuggestBox) this.widget).setText(text);
    }
    this.value = text;
  }
  
  
  public boolean isQueryable() {
    return this.queryable;
  }
  
  
  public void change() {
    //Logger.getLogger("").log(Level.SEVERE, "change: " + this.name);
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
    if ( this.widget instanceof SuggestBox ) {
      ((SuggestBox.DefaultSuggestionDisplay) ((SuggestBox) this.widget).getSuggestionDisplay()).hideSuggestions();
    }
  }
  
  
  public void refresh() {
    if ( !this.value.equals(this.getText()) ) {
      this.setText(this.value);
    }
  }
  
  
}