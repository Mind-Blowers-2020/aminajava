/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import edu.huntkingdom.services.ServiceEventCours;
import java.sql.Timestamp;
import java.time.LocalTime;
import javafx.scene.control.Alert;
import edu.huntkingdom.entities.EventCours;
import edu.huntkingdom.services.UploadServices;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AddEventController implements Initializable {
 ServiceEventCours ser = new ServiceEventCours();
     UploadServices uploadservices= new UploadServices();

    @FXML
    private TextField txtname;
    @FXML
    private TextField txtaddress;
    @FXML
    private ComboBox<String> combotype;
        public ObservableList<String> types = FXCollections.observableArrayList("peche","chasse");

    @FXML
    private TextField txtprice;
    @FXML
    private TextField txtnumber;
    @FXML
    private TextField txtdescription;
    @FXML
    private TextField txtimage;
    @FXML
    private DatePicker combodateD;
    @FXML
    private DatePicker combodateF;
      public int getTxtnumber() {
        return Integer.parseInt(txtnumber.getText());
    }

    public Float getTxtprice() {
        return Float.parseFloat(txtprice.getText());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combotype.setItems(types);
        // TODO
    }    

    @FXML
    private void upload(ActionEvent event) {
     
        FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getName();
            txtimage.setText(imageFile);
        }
    }
    

    @FXML
    private void addevent(ActionEvent event) {
        if (combotype.getSelectionModel().isEmpty()) {
            Alert selectEventAlert = new Alert(Alert.AlertType.WARNING);
            selectEventAlert.setTitle("Select an event");
            selectEventAlert.setHeaderText(null);
            selectEventAlert.setContentText("You need to select a type !");
            selectEventAlert.showAndWait();
            return;
        }
                String FilenameInserver = uploadservices.upload(txtimage.getText());
        System.out.println("fins"+FilenameInserver);

        Timestamp dated = Timestamp.valueOf(combodateD.getValue().atTime(LocalTime.MIDNIGHT));

        Timestamp datef = Timestamp.valueOf(combodateF.getValue().atTime(LocalTime.MIDNIGHT));

         EventCours e= new EventCours(txtname.getText(), txtaddress.getText(), (String) combotype.getValue(), getTxtprice(), getTxtnumber(), txtdescription.getText(), dated, FilenameInserver, datef);
        ser.ajouter(e);

        Alert succAddBookAlert = new Alert(Alert.AlertType.INFORMATION);
        succAddBookAlert.setTitle("Add Event");
        succAddBookAlert.setHeaderText("Results:");
        succAddBookAlert.setContentText("Event added successfully!");
        succAddBookAlert.showAndWait();

    }
    

    
}
