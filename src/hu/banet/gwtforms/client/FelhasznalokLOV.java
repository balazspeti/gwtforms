package hu.banet.gwtforms.client;

import com.google.gwt.cell.client.*;
import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.http.client.*;
//import com.google.gwt.sample.showcase.client.content.text.*;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;
import com.google.gwt.xml.client.*;
import java.util.*;
import java.util.logging.*;


public class FelhasznalokLOV {
  
  
  private   FormsItem                         formsItem;
  //protected TextBox                           connectionUrlTextBox;  
  private   FormsWindow                       dialogBox;
  private   DataGrid<Felhasznalo>             felhasznalokLOV;
  private   SingleSelectionModel<Felhasznalo> selectionModel;
  private   List<Felhasznalo>                 felhasznalok; 
  
  
  public FelhasznalokLOV(/*TextBox connectionUrlTextBox*/) {
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
            
            //Logger.getLogger("").log(Level.SEVERE, "nev: " + nev);
            
            Felhasznalo[] felhasznaloTomb = { new Felhasznalo("Balázs Péter", "011161"),
                                              new Felhasznalo("Gipsz Jakab", "000000") };
            
            felhasznalok = Arrays.asList(felhasznaloTomb);
                                           
            //Logger.getLogger("").log(Level.SEVERE, "nev: " + nev);
            
            felhasznalokLOV = new DataGrid<Felhasznalo>();
            felhasznalokLOV.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
                
            Column<Felhasznalo, String> taszColumn = new Column<Felhasznalo, String>(
                new TextCell()) {
              @Override
              public String getValue(Felhasznalo felhasznalo) {
                return felhasznalo.getTasz();
              }
            };
            felhasznalokLOV.addColumn(taszColumn, "TASZ");
            felhasznalokLOV.setColumnWidth(taszColumn, 50, Style.Unit.PX);
              
            Column<Felhasznalo, String> nevColumn = new Column<Felhasznalo, String>(
                new TextCell()) {
              @Override
              public String getValue(Felhasznalo felhasznalo) {
                return felhasznalo.getNev();
              }
            };
            felhasznalokLOV.addColumn(nevColumn, "Név");
            felhasznalokLOV.setColumnWidth(nevColumn, 250, Style.Unit.PX);
            
            felhasznalokLOV.setRowCount(felhasznalok.size(), true);
            felhasznalokLOV.setRowData(0, felhasznalok);
            felhasznalokLOV.setHeight("250px");
            felhasznalokLOV.setWidth("400px");
            
            selectionModel = new SingleSelectionModel<>();
            felhasznalokLOV.setSelectionModel(selectionModel);          
              
            Button cancelButton = new Button("Mégse");
            cancelButton.addClickListener(new ClickListener() {
              public void onClick(Widget sender) {
                dialogBox.hide();
              }
            });
              
            Button okButton = new Button("Kiválaszt");
            okButton.addClickListener(new ClickListener() {
              public void onClick(Widget sender) {
                Felhasznalo felhasznalo = selectionModel.getSelectedObject();
                //Logger.getLogger("").log(Level.SEVERE, "selected: " + felhasznalo.getNev());   
                if ( felhasznalo != null ) {
                  formsItem.setText(felhasznalo.getTasz());
                  dialogBox.hide();
                  formsItem.setFocus(true);
                }
              }
            });
            
            HorizontalPanel horizontalPanel = new HorizontalPanel();
            horizontalPanel.add(cancelButton);
            horizontalPanel.add(okButton);
                
            VerticalPanel verticalPanel = new VerticalPanel();
            verticalPanel.add(felhasznalokLOV);
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
  
  
  public List<Felhasznalo> getFelhasznalok() {
    return felhasznalok;
  }
  
  
  public void onLoad() {
  }  
  
  
  public void show(FormsItem formsItem) {
    this.formsItem = formsItem;
    dialogBox.center(); 
    felhasznalokLOV.redraw();
    felhasznalokLOV.setFocus(true);
  }
  
  
}
