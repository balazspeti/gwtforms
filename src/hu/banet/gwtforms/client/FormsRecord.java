package hu.banet.gwtforms.client;

import java.util.*;
//import com.google.gwt.user.client.ui.*;


public class FormsRecord {


  private Map<String, FormsItem> items;
  private int recordNumber;
  private String state;
  
 
  public FormsRecord(int recordNumber) {
    this.recordNumber = recordNumber;
    this.items = new HashMap<String, FormsItem>();
    this.state = "NEW";
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