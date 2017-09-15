package hu.banet.gwtforms.client;

import com.google.gwt.cell.client.*;
import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.http.client.*;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;
import com.google.gwt.xml.client.*;
import java.util.*;
import java.util.logging.*;


public abstract class FormsLOV<E> {
  
  
  private   FormsItem               item;
  //protected TextBox               connectionUrlTextBox;  
  private   FormsWindow             dialogBox;
  protected DataGrid<E>             dataGrid;
  private   SingleSelectionModel<E> selectionModel;
  protected Map<String, E>          modelMap;
  
  
  protected abstract String getKey(E e);  
  
  
  protected void add(E e) {
    E tmp = modelMap.put(getKey(e), e);
  }
  
  
  public FormsLOV(/*TextBox connectionUrlTextBox*/) {
    modelMap = new HashMap<>();
    //this.connectionUrlTextBox = connectionUrlTextBox;
    
    /*RequestBuilder requestBuilder;
    if ( connectionUrlTextBox == null || 
         connectionUrlTextBox.getText() == null ||
         "".equals(connectionUrlTextBox.getText()) ) {
      requestBuilder = new RequestBuilder(RequestBuilder.GET, "felhasznalok.xml");
    }
    else {
      requestBuilder = new RequestBuilder(RequestBuilder.GET, "http://" + connectionUrlTextBox.getText());
    }
    
    try {
      requestBuilder.sendRequest(null, new RequestCallback() {
        public void onError(Request request, Throwable exception) {
          Window.alert(exception.toString());
        }
        public void onResponseReceived(Request request, Response response) {
          int statusCode = response.getStatusCode();
          if ( statusCode == 200 || statusCode == 201 ) {
            //Logger.getLogger("").log(Level.SEVERE, response.getText());
            List<Felhasznalo> felhasznalok = new ArrayList<>();
            
            com.google.gwt.xml.client.Element entryElement = XMLParser.parse(response.getText()).getDocumentElement();
            com.google.gwt.xml.client.NodeList recordList = entryElement.getChildNodes();
            for (int i=0; i<recordList.getLength(); i++) {
              if ( recordList.item(i).getNodeType() == com.google.gwt.xml.client.Node.ELEMENT_NODE ) {
                com.google.gwt.xml.client.NodeList itemList = recordList.item(i).getChildNodes();
                String nodeName;
                String tasz = null;
                String nev  = null;
                for (int j=0; j<itemList.getLength(); j++) {
                  if ( itemList.item(j).getNodeType() == com.google.gwt.xml.client.Node.ELEMENT_NODE ) {
                    nodeName = itemList.item(j).getNodeName();
                    //Logger.getLogger("").log(Level.SEVERE, "nodeName: " + nodeName);
                    if ( "tasz".equals(nodeName) ) {
                      tasz = itemList.item(j).getChildNodes().item(0).getNodeValue();
                    }
                    else if ( "nev".equals(nodeName) ) {
                      nev = itemList.item(j).getChildNodes().item(0).getNodeValue();
                    }
                  }
                }
                //Logger.getLogger("").log(Level.SEVERE, "nev: " + nev);
                //Logger.getLogger("").log(Level.SEVERE, "tasz: " + tasz);
                if ( tasz != null && nev != null ) {
                  felhasznalok.add(new Felhasznalo(nev, tasz));
                }
              }
            }*/
            
            dataGrid = new DataGrid<E>();
            dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
            
            dataGrid.setRowCount(10, false);
            dataGrid.setHeight("250px");
            dataGrid.setWidth("400px");
            
            selectionModel = new SingleSelectionModel<>();
            dataGrid.setSelectionModel(selectionModel);          
              
            Button cancelButton = new Button("Mégse");
            cancelButton.addClickListener(new ClickListener() {
              public void onClick(Widget sender) {
                dialogBox.hide();
              }
            });
              
            Button okButton = new Button("Kiválaszt");
            okButton.addClickListener(new ClickListener() {
              public void onClick(Widget sender) {
                E e = selectionModel.getSelectedObject();
                //Logger.getLogger("").log(Level.SEVERE, "selected: " + felhasznalo.getNev());   
                if ( e != null ) {
                  item.setText(getKey(e));
                  dialogBox.hide();
                  item.setFocus(true);
                }
              }
            });
            
            HorizontalPanel horizontalPanel = new HorizontalPanel();
            horizontalPanel.add(cancelButton);
            horizontalPanel.add(okButton);
                
            VerticalPanel verticalPanel = new VerticalPanel();
            verticalPanel.add(dataGrid);
            verticalPanel.add(horizontalPanel);
                
            dialogBox = new FormsWindow(false, true);
            dialogBox.setText("Felhasználók");
            dialogBox.setAnimationEnabled(true);
            dialogBox.setWidget(verticalPanel);  
          /*}
        }
      });
    }
    catch (RequestException exception) {
      Window.alert(exception.toString());
    }       */    
  }
  
  
  /*public void setConnectionUrlTextBox(TextBox connectionUrlTextBox) {
    this.connectionUrlTextBox = connectionUrlTextBox;
  }*/
  
  
  public void show(FormsItem item) {
    this.item = item;
    if ( selectionModel.getSelectedObject() != null ) {
      selectionModel.setSelected(selectionModel.getSelectedObject(), false);
    }
    dialogBox.center(); 
    dataGrid.redraw();
    dataGrid.setFocus(true);
  }

  
  public Set<String> getAll() {
    return modelMap.keySet();
  }
  
  
  public E get(String key) {
    return modelMap.get(key);
  }
  
}
