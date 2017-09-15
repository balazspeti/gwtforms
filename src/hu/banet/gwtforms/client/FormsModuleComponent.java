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


public class FormsModuleComponent extends Composite implements KeyDownHandler, FocusHandler, BlurHandler {
  
  
  private static FormsModuleComponent activeComponent;
  
  
  public static FormsModuleComponent getActiveComponent() {
    return activeComponent;
  }
  
  
  public static void setActiveComponent(FormsModuleComponent moduleComponent) {
    activeComponent = moduleComponent;
  }
  

  private   String blockName;
  private   String recordName;
  private   int mode; // 0=Normal; 1=Enter query
  private   int visibleRecords;
  private   int currentRecord;
  private   String currentItem;
  private   String firstItem;
  private   Vector<FormsRecord> records;
  private   FocusPanel    focusPanel;
  protected VerticalPanel mainPanel;
  private   ScrollPanel   scrollPanel;
  private   VerticalPanel contentPanel;
  private   boolean loaded;
  protected TextBox connectionUrlTextBox;
  private   Map<Object, Integer> widgetTable;
  //private   int size;
  private   boolean open;
  
  
  public FormsModuleComponent(String blockName, String recordName, int visibleRecords) {
    super();
    mode = 0;
    this.blockName = blockName;
    this.recordName = recordName;
    this.visibleRecords = visibleRecords;
    currentRecord = 0;
    currentItem = null;
    firstItem = null;
    records = new Vector<FormsRecord>(0);
    loaded = false;
    widgetTable = new HashMap<Object, Integer>();
    open = false;
    
    focusPanel = new FocusPanel();
    focusPanel.addKeyDownHandler(this);
    
    mainPanel = new VerticalPanel();
    renderHead(mainPanel);
    focusPanel.add(mainPanel);
    
    scrollPanel = new ScrollPanel();
    scrollPanel.setHeight("100px");
    mainPanel.add(scrollPanel);
    
    contentPanel = new VerticalPanel();
    scrollPanel.add(contentPanel);
    
    initWidget(focusPanel);
    
    for (int i=0; i<visibleRecords; i++) {
      renderRecord(contentPanel);
    }
  }
  
  
  public void setConnectionUrlTextBox(TextBox connectionUrlTextBox) {
    this.connectionUrlTextBox = connectionUrlTextBox;
  }
  
  
  public void onLoad() {
    if ( !loaded ) {        
      Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
        public void execute() {
          //size = contentPanel.getOffsetHeight();
          scrollPanel.setHeight(contentPanel.getOffsetHeight() + "px");
        }
      });
      loaded = true;
    }
  }


  public void open() {
    if ( !open ) {
      int index = mainPanel.getWidgetIndex(scrollPanel);
      mainPanel.remove(scrollPanel);
      scrollPanel.remove(contentPanel);
      mainPanel.insert(contentPanel, index);
      //scrollPanel.setHeight(contentPanel.getOffsetHeight() + "px");
      open = true;
    }
  }   


  public void close() {
    if ( open ) {
      int index = mainPanel.getWidgetIndex(contentPanel);
      mainPanel.remove(contentPanel);
      scrollPanel.add(contentPanel);
      mainPanel.insert(scrollPanel, index);
      //scrollPanel.setHeight(size + "px");
      open = false;
    }
  }     
  
  
  protected void renderHead(VerticalPanel verticalPanel) {
  }
  
  
  protected void renderRecord(VerticalPanel verticalPanel) {
    this.records.add(new FormsRecord(this.records.size(), this));
  }
  
  
  protected void register(Widget widget,
                          String name,
                          boolean queryable) {
    FormsItem item = new FormsItem(name, widget, queryable);                           
    register(item);
  }
  
  
  protected void register(FormsItem item) {                       
    this.records.lastElement().put(item.getName(), item);
    if ( this.records.size() == 1 && this.visibleRecords > 1 ) {
      item.addStyleName("selected2");
    }
    if ( item.getWidget() instanceof TextBox ) {
      ((TextBox) item.getWidget()).addFocusHandler(this);
      ((TextBox) item.getWidget()).addBlurHandler(this);
      //((TextBox) item.getWidget()).addKeyUpHandler(this);
    }
    else if ( item.getWidget() instanceof ListBox ) {
      ((ListBox) item.getWidget()).addFocusHandler(this);
      ((ListBox) item.getWidget()).addBlurHandler(this);
      //((ListBox) item.getWidget()).addKeyUpHandler(this);     
    }
    else if ( item.getWidget() instanceof RichTextArea ) {
      ((RichTextArea) item.getWidget()).addFocusHandler(this);
      ((RichTextArea) item.getWidget()).addBlurHandler(this);
      //((RichTextArea) item.getWidget()).addKeyUpHandler(this);     
    }    
    else if ( item.getWidget() instanceof SuggestBox ) {
      ((SuggestBox) item.getWidget()).getValueBox().addFocusHandler(this);
      ((SuggestBox) item.getWidget()).getValueBox().addBlurHandler(this);
      //((SuggestBox) item.getWidget()).getValueBox().addKeyUpHandler(this);   
    }
    if ( this.firstItem == null &&
         item.getWidget() != null ) {
      this.firstItem = item.getName();
      this.currentItem = item.getName();
    }
    if ( item.getSource() != null ) {
      if ( widgetTable.containsKey(item.getSource()) ) {
        widgetTable.put(item.getSource(), -1);
      }
      else {
        widgetTable.put(item.getSource(), records.size()-1);
      }
    }
  }  
  
  
// F6=117; F7=118; F8=119;
// Down=40; Up=38; Left=37; Right=39;
// Tab=9; Esc=27; Enter=13;
  public void onKeyDown(KeyDownEvent event) {
    int keyCode = event.getNativeEvent().getKeyCode();
    //Logger.getLogger("").log(Level.SEVERE, "keyCode: " + keyCode);
    if ( keyCode == 38 ) {
      if ( !(records.get(currentRecord).get(currentItem).getWidget() instanceof ListBox) ) {
        this.up();
      }
    }
    else if ( keyCode == 40 ) {
      if ( !(records.get(currentRecord).get(currentItem).getWidget() instanceof ListBox) ) {
        this.down();
      }
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
      if ( this.records.get(this.currentRecord).get(item).getSource() == event.getSource() ) {
        if ( !item.equals(this.currentItem) ) {
          this.records.get(this.currentRecord).get(this.currentItem).change();
          this.currentItem = item;
          this.records.get(this.currentRecord).get(this.currentItem).selectAll();
        }
        return;
      }
    }
    int record = widgetTable.get(event.getSource());
    for (String item : records.get(record).itemNames()) {
      if ( this.records.get(record).get(item).getSource() == event.getSource() ) {
        this.records.get(this.currentRecord).get(this.currentItem).change();
        if ( this.currentRecord != record && this.visibleRecords > 1 ) {
          for (String l : records.get(this.currentRecord).itemNames()) {
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
        return;
      }
    }
  }

  
  public void onBlur(BlurEvent event) {
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
      contentPanel.clear();
      records.clear();
      for (int i=0; i<visibleRecords; i++) {
        renderRecord(contentPanel);
      }    
      records.get(0).get(firstItem).setFocus(true);
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
    records.get(currentRecord).get(currentItem).change();  
    String queryParams = "";
    for (String item : records.get(0).itemNames()) {
      if ( records.get(0).get(item).getValue() != null && 
           !"".equals(records.get(0).get(item).getValue()) ) {
        if ( "".equals(queryParams) ) {
          queryParams = "?" + item + "=" + records.get(0).get(item).getValue();
        }
        else {
          queryParams = queryParams + "&" + item + "=" + records.get(0).get(item).getValue();
        }
      }
    }
    
    Logger.getLogger("").log(Level.SEVERE, "GET: " + this.blockName + ".xml" + queryParams);
    RequestBuilder requestBuilder;
    if ( connectionUrlTextBox == null || 
         connectionUrlTextBox.getText() == null ||
         "".equals(connectionUrlTextBox.getText()) ) {
      requestBuilder = new RequestBuilder(RequestBuilder.GET, this.blockName + ".xml" + queryParams);
    }
    else {
      requestBuilder = new RequestBuilder(RequestBuilder.GET, "http://" + connectionUrlTextBox.getText() + queryParams);
    }
    try {
      requestBuilder.sendRequest(null, new RequestCallback() {
        public void onError(Request request, Throwable exception) {
          Window.alert(exception.toString());
        }
        public void onResponseReceived(Request request, Response response) {
          int statusCode = response.getStatusCode();
          if ( statusCode == 200 || statusCode == 201 ) {
            mode = 0;
            clearBlock();
            for (String item : records.get(0).itemNames()) {
              records.get(0).get(item).removeStyleName("enterQuery");
              records.get(0).get(item).setEnabled(true);
            }    
            records.get(0).get(firstItem).setFocus(true);
            
            Element entryElement = XMLParser.parse(response.getText()).getDocumentElement();
            NodeList recordList = entryElement.getChildNodes();
            int record = 0;
            for (int i=0; i<recordList.getLength(); i++) {
              if ( recordList.item(i).getNodeType() == Node.ELEMENT_NODE ) {
                NodeList itemList = recordList.item(i).getChildNodes();
                for (int j=0; j<itemList.getLength(); j++) {
                  if ( itemList.item(j).getNodeType() == Node.ELEMENT_NODE ) {
                    if ( records.size() <= record ) {
                      renderRecord(contentPanel);
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
      records.get(currentRecord).get(currentItem).change();   
      Document document = XMLParser.createDocument();
      Element blockElement = document.createElement(this.blockName);
      document.appendChild(blockElement);
      for (int record=0; record<this.records.size(); record++) {
        boolean changedRecord = false;
        for (String item : records.get(record).itemNames()) {
          if ( !changedRecord &&
               ( ( records.get(record).get(item).getValue() != null && 
                   !"".equals(records.get(record).get(item).getValue()) ) ||
                 records.get(record).get(item).isChanged() ) ) {
            changedRecord = true;
          }
        }
        if ( changedRecord ) {
          Element recordElement = document.createElement(this.recordName);
          blockElement.appendChild(recordElement);
          for (String item : records.get(record).itemNames()) {
            Element itemElement = document.createElement(item);
            recordElement.appendChild(itemElement);
            Text itemText = document.createTextNode(records.get(record).get(item).getValue());
            itemElement.appendChild(itemText);
            records.get(record).get(item).setNotChanged();
            //Logger.getLogger("").log(Level.SEVERE, item + record + " : " + records.get(record).get(item).getValue());
          }
          records.get(record).setState("QUERY");
        }
      }
      Window.alert(document.toString());
      
      RequestBuilder requestBuilder;
      if ( connectionUrlTextBox == null || 
           connectionUrlTextBox.getText() == null ||
           "".equals(connectionUrlTextBox.getText()) ) {
        requestBuilder = new RequestBuilder(RequestBuilder.POST, this.blockName + ".xml");
      }
      else {
        requestBuilder = new RequestBuilder(RequestBuilder.POST, "http://" + connectionUrlTextBox.getText());
      }
      
      requestBuilder.setHeader("Content-Type", "text/xml");
      try {
        requestBuilder.sendRequest(document.toString(), new RequestCallback() {
          public void onError(Request request, Throwable exception) {
            Window.alert(exception.toString());
          }
          public void onResponseReceived(Request request, Response response) {
            int statusCode = response.getStatusCode();
            if ( statusCode == 200 || statusCode == 201 ) {
              mode = 0;
              clearBlock();
              for (String item : records.get(0).itemNames()) {
                records.get(0).get(item).removeStyleName("enterQuery");
                records.get(0).get(item).setEnabled(true);
              }    
              records.get(0).get(firstItem).setFocus(true);
              
              Element entryElement = XMLParser.parse(response.getText()).getDocumentElement();
              NodeList recordList = entryElement.getChildNodes();
              int record = 0;
              for (int i=0; i<recordList.getLength(); i++) {
                if ( recordList.item(i).getNodeType() == Node.ELEMENT_NODE ) {
                  NodeList itemList = recordList.item(i).getChildNodes();
                  for (int j=0; j<itemList.getLength(); j++) {
                    if ( itemList.item(j).getNodeType() == Node.ELEMENT_NODE ) {
                      if ( records.size() <= record ) {
                        renderRecord(contentPanel);
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
  }
  
  
  public void createRecord() {
    if ( mode == 0 ) {
      renderRecord(contentPanel);
      records.lastElement().get(firstItem).setFocus(true);
    }
  }
  

}
