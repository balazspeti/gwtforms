package hu.banet.gwtforms.client;

import java.util.*;
import com.google.gwt.event.dom.client.*;


public class FormsRecord {


  private Map<String, FormsItem> items;
  private int number;
  private String state;
  private FormsModuleComponent moduleComponent;
  
 
  public FormsRecord(int number, FormsModuleComponent moduleComponent) {
    this.number = number;
    this.items = new HashMap<String, FormsItem>();
    this.state = "NEW";
    this.moduleComponent = moduleComponent;
  } 
  
  
  public String getState() {
    return this.state;
  }
  
  
  public void setState(String state) {
    this.state = state;
  }
  
  
  public FormsItem get(String name) {
    return items.get(name);
  }
  
  
  public Set<String> itemNames() {
    return items.keySet();
  }
  
  
  public void put(String name, FormsItem item) {
    items.put(name, item);
    item.setRecord(this);
  } 
  

}