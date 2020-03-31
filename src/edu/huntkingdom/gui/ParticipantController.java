/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.gui;

import edu.huntkingdom.entities.EventCours;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import edu.huntkingdom.entities.Participant;
import edu.huntkingdom.services.ServiceParticipant;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import edu.huntkingdom.services.ServiceEvent;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class ParticipantController implements Initializable {

    @FXML
    private TableView<Participant> participantTV;
    
  
    @FXML
    private TableColumn<Participant, String> evenement;
    @FXML
    private TableColumn<Participant, Date> date_inscrit;
    @FXML
    private TableColumn<Participant, String> nom;
    @FXML
    private TableColumn<Participant, String> prenom;
  public ObservableList<Participant> data = FXCollections.observableArrayList();
    ServiceParticipant sec = new ServiceParticipant();
    ServiceEvent se = new ServiceEvent();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data.addAll(sec.readAll());
       // this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        //this.confirmation.setCellValueFactory(new PropertyValueFactory<>("confirmation"));
        
        this.evenement.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
        
        this.date_inscrit.setCellValueFactory(new PropertyValueFactory<>("date_inscrit"));
        this.nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        this.participantTV.setItems(data);
    }    

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
         if (participantTV.getSelectionModel().getSelectedItem() != null) {
            Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteBookAlert.setTitle("suppresion participant");
            deleteBookAlert.setHeaderText(null);
            deleteBookAlert.setContentText("vous étes sur de supprimer ce participant ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Participant p = participantTV.getSelectionModel().getSelectedItem();
                sec.supprimer(p);
                data.clear();
                data.addAll(sec.readAll());
            
            

                //Alert Delete Blog :
                Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteBookAlert.setTitle("suppression participant");
                succDeleteBookAlert.setHeaderText("Results:");
                succDeleteBookAlert.setContentText("participant supprimé merci!");

                succDeleteBookAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

         
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("selctionnez un participant");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("vous devez selectionner un particpant tout d'abord!");
            selectBookAlert.showAndWait();

        }
    }

    @FXML
    private void modifier(ActionEvent event) {
    }
    
}
