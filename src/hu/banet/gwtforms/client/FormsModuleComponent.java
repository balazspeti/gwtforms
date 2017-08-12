package hu.banet.gwtforms.client;

import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.xml.client.*;
import java.util.*;
import java.util.logging.*;


public class FormsModuleComponent extends VerticalPanel implements KeyUpHandler, FocusHandler {
  
  
  private static FormsModuleComponent activeComponent;
  
  
  public static FormsModuleComponent getActiveComponent() {
    return activeComponent;
  }
  

  private String blockName;
  private String recordName;
  private int mode; // 0=Normal; 1=Enter query
  private int visibleRecords;
  private int currentRecord;
  private String currentItem;
  private String firstItem;
  private Vector<FormsRecord> records;
  private ScrollPanel   scrollPanel;
  private VerticalPanel verticalPanel;
  private boolean loaded;
  
  
  public FormsModuleComponent(String blockName, String recordName, int visibleRecords) {
    super();
    this.mode = 0;
    this.blockName = blockName;
    this.recordName = recordName;
    this.visibleRecords = visibleRecords;
    this.currentRecord = 0;
    this.currentItem = null;
    this.firstItem = null;
    this.records = new Vector<FormsRecord>(0);
    this.loaded = false;
    
    this.renderHead(this);
    
    this.verticalPanel = new VerticalPanel();
    this.scrollPanel = new ScrollPanel();
    this.scrollPanel.setHeight("100px");
    this.scrollPanel.add(this.verticalPanel);
    this.add(scrollPanel);
    
    for (int i=0; i<this.visibleRecords; i++) {
      //Logger.getLogger("").log(Level.SEVERE, "renderRecord");
      this.renderRecord(this.verticalPanel);
    }
    
  }
  
  
  public void onLoad() {
    if ( !loaded ) {        
      Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
        public void execute() {
          scrollPanel.setHeight(verticalPanel.getOffsetHeight() + "px");
        }
      });
      loaded = true;
    }
  }  
  
  
  protected void renderHead(VerticalPanel verticalPanel) {
  }
  
  
  protected void renderRecord(VerticalPanel verticalPanel) {
    this.records.add(new FormsRecord(this.records.size()));
  }
  
  
  protected void register(FocusWidget widget,
                          String name,
                          boolean queryable) {
    this.records.lastElement().put(name, new FormsItem(name, widget, queryable));
    if ( widget instanceof TextBox ) {
      widget.addFocusHandler(this);
      widget.addKeyUpHandler(this);
      if ( this.records.size() == 1 && this.visibleRecords > 1 ) {
        widget.addStyleName("selected2");
      }      
    }
    if ( this.firstItem == null ) {
      this.firstItem = name;
      this.currentItem = name;
    }
  }
  
  
// F6=117; F7=118; F8=119;
// Down=40; Up=38; Left=37; Right=39;
// Tab=9; Esc=27; Enter=13;
  public void onKeyUp(KeyUpEvent event) {
    int keyCode = event.getNativeEvent().getKeyCode();
    if ( keyCode == 38 ) {
      this.up();
    }
    else if ( keyCode == 40 ) {
      this.down();
    }
    else if ( keyCode == 117 ) {
      this.clearBlock();
    }
    else if ( keyCode == 118 ) {
      this.enterQuery();
    }
    else if ( keyCode == 119 ) {
      this.executeQuery();
    }
    else if ( keyCode == 27 ) {
      this.teszt();
    }
    else {
      //Window.alert("keycode=" + keyCode);
    }     
  }
  
  
  public void onFocus(FocusEvent event) {
    activeComponent = this;
    for (String item : records.get(this.currentRecord).itemNames()) {
      if ( this.records.get(this.currentRecord).get(item).isSource(event) ) {
        Logger.getLogger("").log(Level.SEVERE, "onFocus: " + item + currentRecord);
        if ( !item.equals(this.currentItem) ) {
          this.records.get(this.currentRecord).get(item).change();
          this.currentItem   = item;
          this.records.get(this.currentRecord).get(this.currentItem).selectAll();
        }
        return;
      }
    }
    for (int record=0; record<this.records.size(); record++) {
      if ( record != this.currentRecord ) {
        for (String item : records.get(record).itemNames()) {
          if ( this.records.get(record).get(item).isSource(event) ) {
            Logger.getLogger("").log(Level.SEVERE, "onFocus: " + item + record);
            /*if ( 0 != record && this.mode == 1 ) {
              Logger.getLogger("").log(Level.SEVERE, "setFocus: " + this.currentItem + this.currentRecord);
              this.records.get(record).get(item).setFocus(false);
              this.records.get(this.currentRecord).get(this.currentItem).setFocus(true);
            }
            else {*/
              this.records.get(this.currentRecord).get(this.currentItem).change();
              if ( this.currentRecord != record && this.visibleRecords > 1 ) {
                for (String l : records.get(this.currentRecord).itemNames()) {
                  //Logger.getLogger("").log(Level.SEVERE, "Ez van: " + l);
                  this.records.get(this.currentRecord).get(l).removeStyleName("selected2");
                }
                for (String l : records.get(record).itemNames()) {
                  this.records.get(record).get(l).addStyleName("selected2");
                  this.records.get(record).get(l).refresh();
                }
              }
              this.currentRecord = record;
              this.currentItem   = item;
              this.records.get(this.currentRecord).get(this.currentItem).selectAll();
            //}
            return;
          }
        }
      }
    }
  }


  private void up() {
    if ( this.currentRecord > 0 ) {
      this.records.get(this.currentRecord-1).get(this.currentItem).setFocus(true);
    }
  }
  
  
  private void down() {
    if ( this.currentRecord < this.records.size()-1 ) {
      this.records.get(this.currentRecord+1).get(this.currentItem).setFocus(true);
    }
  }
  
  
  public void clearBlock() {
    if ( this.mode == 0 ) {
      this.verticalPanel.clear();
      this.records.clear();
      for (int i=0; i<this.visibleRecords; i++) {
        this.renderRecord(this.verticalPanel);
      }    
      this.records.get(0).get(this.firstItem).setFocus(true);
      for (String item : records.get(0).itemNames()) {
        records.get(0).get(item).setText("");
      }
    }
  }
  
  
  public void enterQuery() {
    if ( this.mode == 0 ) {
      this.clearBlock();
      this.mode = 1;
      
      for (int record=0; record<records.size(); record++) {
        for (String item : records.get(record).itemNames()) {
          records.get(record).get(item).setEnabled(false);
          //Logger.getLogger("").log(Level.SEVERE, "Disbale: " + item + record);
        }
      }
      for (String item : records.get(0).itemNames()) {
        if ( records.get(0).get(item).isQueryable() ) {
          records.get(0).get(item).setEnabled(true);
          this.records.get(0).get(item).addStyleName("enterQuery");
        }
        this.records.get(0).get(item).removeStyleName("selected2");
      }
      this.records.get(0).get(firstItem).setFocus(true);
    }
  }
  
  
  private void teszt() {
  }
  
  
  public void executeQuery() {
    this.mode = 0;
    this.clearBlock();
    for (String item : records.get(0).itemNames()) {
      this.records.get(0).get(item).removeStyleName("enterQuery");
      this.records.get(0).get(item).setEnabled(true);
    }    
    
    this.records.get(0).get(firstItem).setFocus(true);
    
    RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, this.blockName + ".xml");
    try {
      requestBuilder.sendRequest(null, new RequestCallback() {
        public void onError(Request request, Throwable exception) {
          Window.alert(exception.toString());
        }
        public void onResponseReceived(Request request, Response response) {
          int statusCode = response.getStatusCode();
          if ( statusCode == 200 || statusCode == 201 ) {
            Element entryElement = XMLParser.parse(response.getText()).getDocumentElement();
            NodeList recordList = entryElement.getChildNodes();
            int record = 0;
            for (int i=0; i<recordList.getLength(); i++) {
              if ( recordList.item(i).getNodeType() == Node.ELEMENT_NODE ) {
                NodeList itemList = recordList.item(i).getChildNodes();
                for (int j=0; j<itemList.getLength(); j++) {
                  if ( itemList.item(j).getNodeType() == Node.ELEMENT_NODE ) {
                    if ( records.size() <= record ) {
                      renderRecord(verticalPanel);
                    }
                    String item = itemList.item(j).getNodeName();
                    FormsItem formsItem = records.get(record).get(item);
                    if ( formsItem != null ) {
                      formsItem.setText(itemList.item(j).getChildNodes().item(0).getNodeValue());
                    }
                  }
                }
                records.get(record).setState("QUERY");
                record++;
              }
            }
            for (String l : records.get(0).itemNames()) {
              records.get(0).get(l).refresh();
            }
          }
        }
      });
    }
    catch (RequestException exception) {
      Window.alert(exception.toString());
    }    
  } 


  public void cancelQuery() {
    if ( this.mode == 1 ) {
      this.mode = 0;
      this.clearBlock();
      for (String item : records.get(0).itemNames()) {
        this.records.get(0).get(item).removeStyleName("enterQuery");
        this.records.get(0).get(item).setEnabled(true);
      }
      this.records.get(0).get(firstItem).setFocus(true);
    }
  }  
  
  
  public void commitForm() {
    if ( this.mode == 0 ) {
      Document document = XMLParser.createDocument();
      Element blockElement = document.createElement(this.blockName);
      document.appendChild(blockElement);
      for (int record=0; record<this.records.size(); record++) {
        Element recordElement = document.createElement(this.recordName);
        blockElement.appendChild(recordElement);
        for (String item : records.get(record).itemNames()) {
          Element itemElement = document.createElement(item);
          recordElement.appendChild(itemElement);
          Text itemText = document.createTextNode(records.get(record).get(item).getValue());
          itemElement.appendChild(itemText);
          records.get(record).get(item).setNotChanged();
        }
        records.get(record).setState("QUERY");
      }
      Window.alert(document.toString());
      
      RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, this.blockName + ".xml");
      requestBuilder.setHeader("Content-Type", "text/xml");
      try {
        requestBuilder.sendRequest(document.toString(), new RequestCallback() {
          public void onError(Request request, Throwable exception) {
            Window.alert(exception.toString());
          }
          public void onResponseReceived(Request request, Response response) {
            int statusCode = response.getStatusCode();
            if ( statusCode == 200 || statusCode == 201 ) {
              Window.alert("siker!");
            }
          }
        });
      }
      catch (RequestException exception) {
        Window.alert(exception.toString());
      }  
    }
  }
  
  
}