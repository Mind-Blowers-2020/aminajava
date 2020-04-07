/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.gui;
import edu.huntkingdom.entities.Participant;
import static edu.huntkingdom.gui.FronteventController.holdID;
import edu.huntkingdom.services.ServiceEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import edu.huntkingdom.services.ServiceParticipant;
import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class ParticipateController extends FronteventController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    ServiceParticipant ser = new ServiceParticipant();
       String eventid = holdID.value;
       
ServiceEvent se=new ServiceEvent();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    void refrech()
    {
    nom.clear();
    prenom.clear();
    }

    @FXML
    private void participer(ActionEvent event) {
        Date date = new Date();  
                Timestamp ts=new Timestamp(date.getTime()); 
                if (verifNom()&&verifPrenom())
                {
        Participant p=new Participant(0, Boolean.FALSE, Integer.parseInt(eventid), ts, nom.getText(), prenom.getText());;
        se.decrementqte(Integer.parseInt(eventid));
        ser.ajouter(p);
        refrech();}
       
    }

    @FXML
    private void retour(ActionEvent event)   {
        
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.close();
        
    }
    
    private boolean verifNom() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(nom.getText());
        if (m.find() && m.group().equals(nom.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le nom de participant");
            alert.showAndWait();
            return false;
        }
      }
    
     private boolean verifPrenom() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(nom.getText());
        if (m.find() && m.group().equals(prenom.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le prenom de participant");
            alert.showAndWait();
            return false;
        }
      }
    
}
