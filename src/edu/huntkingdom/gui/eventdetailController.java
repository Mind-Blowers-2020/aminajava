/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.gui;

import edu.huntkingdom.entities.Evenement;
import edu.huntkingdom.services.ServiceEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class eventdetailController extends FronteventController implements Initializable {
   
    @FXML
    private Label lblStatus;
    @FXML
    private Button btnSave1;
    @FXML
    private Label nomEvent;
    @FXML
    private Label txtprix1;
    @FXML
    private Label lblStatuss;
    @FXML
    private ImageView imagedetails;
    @FXML
    private HBox ratinghbox;
    @FXML
    private Label yourratingtxt;
        String productid = holdID.value;
ServiceEvent se=new ServiceEvent();
 private Evenement selected;
    @FXML
    private Label description;
   // private Label adresse;
    @FXML
    private WebView webview;

    public eventdetailController() {
        //this.id = id;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Evenement e= se.findbyid(Integer.parseInt(productid));
        System.out.println("image:"+e.getImage());
        System.out.println("nomevent:"+e.getNomEvent());
        System.out.println("prix:"+e.getPrix());
        Image image = new Image("http://localhost/backt/web/" + e.getImage());
            imagedetails.setImage(image);
            nomEvent.setText(e.getNomEvent());
            txtprix1.setText(String.valueOf(e.getPrix()));
            description.setText(e.getDescription());
          ///  adresse.setText(e.getAdresse());
          WebEngine webEngine = webview.getEngine();

        URL url1 = this.getClass().getResource("/edu/huntkingdom/gui/webmaps.html");
        webEngine.load(url1.toString());
        webEngine.setJavaScriptEnabled(true);
        System.out.println("Selected item: " + e.getDescription());
        Evenement selected = new Evenement();
                   selected=e;
            try {
                        System.out.println("Selected item: " + e.getDescription());
                    String s=    e.getLatlng();
                    String[] ch=s.split(";");
                    String X=ch[0];
                     String Y=ch[1];
                    
                        System.out.println("addpopup(" + e.getLatlng()+ ",'" + e.getDescription() + "')");
                        webEngine.executeScript("addpopup(" + X + "," + Y + ",'" + e.getNomEvent()+ "')");
                    } catch (Exception ex) {
                        System.out.println("problem with script" + ex.getMessage());
                    }
           
        }
        

                 
        // TODO
        
       

    @FXML
    private void HandleEvents(MouseEvent event) {
    }

    @FXML
    private void closeOnAction(ActionEvent event) {
        
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.close();
    }
    
}
