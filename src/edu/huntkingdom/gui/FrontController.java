/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FrontController implements Initializable {

    @FXML
    private BorderPane BorderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void loadUi(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            ex.getMessage();
        }
        BorderPane.setCenter(root);
       //hal bhim l ahahahahhahahahahaha
       //fik le nabda nakfer aalik l 'appeel appel appel mtaaa l fct mteek litnavigui biha men page l page wini
       //cp abgne  loki  tdifjaqdskfqfsdlfkfqjisdkcfhqlsdjkfiqsd p fdsjfqsdfs oss asba c bn
    }

    @FXML
    private void profileAction(ActionEvent event) {
       // loadUi("front");
       
    
try {
            Parent root = FXMLLoader.load(getClass().getResource("front.fxml"));
            //Scene scene = new Scene(root, 1100, 650);
            Scene scene = new Scene(root);//fhemtha faza edhyka imchi hajet tefha le:p hhh
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
            } catch (IOException ex) {
               ex.getMessage();
            }

    }

    @FXML
    private void eventAction(ActionEvent event) {
        loadUi("frontevent");
    }
    @FXML
    private void produitAction(ActionEvent event) {
    }

    @FXML
    private void deconOnAction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            //Scene scene = new Scene(root, 1100, 650);
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
            System.out.println("deco");
    }

}
