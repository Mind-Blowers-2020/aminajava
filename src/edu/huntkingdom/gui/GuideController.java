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
import edu.huntkingdom.entities.Guide;
import edu.huntkingdom.services.ServiceGuide;
import javafx.scene.control.cell.TextFieldTableCell;
import java.sql.SQLException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class GuideController implements Initializable {

    @FXML
    private TableView<Guide> guideTV;

    @FXML
    private TableColumn<Guide, Integer> evenement;
    @FXML
    private TableColumn<Guide, String> nom;
    @FXML
    private TableColumn<Guide, String> prenom;
    @FXML
    private TableColumn<Guide, String> mail;
    @FXML
    private TableColumn<Guide, Integer> tel;
    @FXML
    private TableColumn<Guide, String> typeEvent1;
 public ObservableList<Guide> data = FXCollections.observableArrayList();
    ServiceGuide sec = new ServiceGuide();
    @FXML
    private TextField searchTF;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      data.addAll(sec.readAll());
       // this.idguide.setCellValueFactory(new PropertyValueFactory<>("idguide"));
        this.evenement.setCellValueFactory(new PropertyValueFactory<>("evenement"));
        this.nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        this.mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        this.tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        this.typeEvent1.setCellValueFactory(new PropertyValueFactory<>("eventType"));
        this.guideTV.setItems(data); 
                //this for edit
        this.guideTV.setEditable(true);
        
        this.nom.setCellFactory(TextFieldTableCell.forTableColumn());
        this.prenom.setCellFactory(TextFieldTableCell.forTableColumn());
         this.mail.setCellFactory(TextFieldTableCell.forTableColumn());
        // this.tel.setCellFactory(TextFieldTableCell.forTableColumn());
                 this.tel.setCellFactory(TextFieldTableCell.<Guide, Integer>forTableColumn(new IntegerStringConverter()));


    }      
    @FXML
    private void supprimerOnAction(ActionEvent event) throws SQLException {
        if (guideTV.getSelectionModel().getSelectedItem() != null) {
            Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteBookAlert.setTitle("suppresion guide");
            deleteBookAlert.setHeaderText(null);
            deleteBookAlert.setContentText("vous étes sur de supprimer ce guide ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Guide g = guideTV.getSelectionModel().getSelectedItem();
                sec.supprimer(g);
                data.clear();
                data.addAll(sec.readAll());
            
            

                //Alert Delete Blog :
                Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteBookAlert.setTitle("suppression guide");
                succDeleteBookAlert.setHeaderText("Results:");
                succDeleteBookAlert.setContentText("guide supprimé merci!");

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
    private void modifierguide(ActionEvent event) {
        if (guideTV.getSelectionModel().getSelectedItem() != null) {

            Guide ec = guideTV.getSelectionModel().getSelectedItem();

            sec.UpdateGuide(ec.getNom(),ec.getPrenom(),ec.getMail(),ec.getTel(),ec.getIdGuide());
            Alert BookAlert = new Alert(Alert.AlertType.INFORMATION);
            BookAlert.setTitle("edit");
            BookAlert.setHeaderText(null);
            BookAlert.setContentText("The guide was successfully edit");
            BookAlert.showAndWait();

        } else {
            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Select a guide");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("You need to select a guide first!");
            selectBookAlert.showAndWait();
    
        }

    }
     @FXML
    public void changeNomCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Guide Guide = guideTV.getSelectionModel().getSelectedItem();
        Guide.setNom(edittedCell.getNewValue().toString());
       
    }
       @FXML
    public void changePrenomCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Guide Guide = guideTV.getSelectionModel().getSelectedItem();
        Guide.setPrenom(edittedCell.getNewValue().toString());
       
    }
       @FXML
    public void changeMailCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Guide Guide = guideTV.getSelectionModel().getSelectedItem();
        Guide.setMail(edittedCell.getNewValue().toString());
       
    }
    
       @FXML
    public void changeTelCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Guide Guide = guideTV.getSelectionModel().getSelectedItem();
        Guide.setTel(Integer.parseInt(edittedCell.getNewValue().toString()));
       
    }

    @FXML
    private void filter(ActionEvent event) {
    }
    
}
