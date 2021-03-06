package hu.banet.gwtforms.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import java.util.logging.*;


public class FormsItem {


  private   String      name;
  private   Widget      widget;
  protected String      value;
  private   boolean     changed;
  private   FormsRecord record;
  private   boolean     queryable;
  private   boolean     bounded;
  private   FormsLOV    lov;
  
  private Command postChange;
  private Command postQuery;
  
 
  public FormsItem(String name, Widget widget, boolean queryable, boolean bounded) {
    this.name      = name;
    this.widget    = widget;
    this.value     = "";
    this.changed   = false;
    this.queryable = queryable;
    this.bounded   = bounded;
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
  
  
  public Object getSource() {
    if ( widget instanceof SuggestBox ) {
      return ((SuggestBox) widget).getValueBox();
    }
    return widget;
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
  
  
  public void setValue(String text) {
    setText(text);
    this.value = text;
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
  }    
  
  
  public boolean isQueryable() {
    return this.queryable;
  }
  
  
  public boolean isBounded() {
    return this.bounded;
  }  
  
  
  public FormsLOV getLOV() {
    return lov;
  }
  
  
  public void setLOV(FormsLOV lov) {
    this.lov = lov;
    if ( widget instanceof SuggestBox ) {
      MultiWordSuggestOracle oracle = (MultiWordSuggestOracle ) ((SuggestBox) widget).getSuggestOracle();
      oracle.addAll(lov.getAllNames());
    }
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
      if ( postChange != null ) {
        postChange.execute();
      }
    }
    if ( this.widget instanceof SuggestBox ) {
      ((SuggestBox.DefaultSuggestionDisplay) ((SuggestBox) this.widget).getSuggestionDisplay()).hideSuggestions();
    }
  }
  
  
  public void refresh() {
    if ( !this.value.equals(this.getText()) ) {
      this.setValue(this.value);
    }
  }
  
  
  public void queried() {
    if ( postQuery != null ) {
      postQuery.execute();
    }
  }
  
  
  public void setPostChange(Command postChange) {
    this.postChange = postChange;
  }
  
  
  public void setPostQuery(Command postQuery) {
    this.postQuery = postQuery;
  }
  
  
}