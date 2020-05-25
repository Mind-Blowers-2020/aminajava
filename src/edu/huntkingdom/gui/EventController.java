/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.gui;




import edu.huntkingdom.entities.EventCours;
import edu.huntkingdom.entities.Evenement;
import edu.huntkingdom.services.ServiceEventCours;
import edu.huntkingdom.services.ServiceEvent;
import edu.huntkingdom.utils.DataBase;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class EventController implements Initializable {

    @FXML
    private TableView<EventCours> eventcoursTV;
    
    @FXML
    private TableColumn<EventCours, String> nomEvent;
    @FXML
    private TableColumn<EventCours, String> adresse;
    @FXML
    private TableColumn<EventCours, String> type;
    @FXML
    private TableColumn<EventCours, Float> prix;
    @FXML
    private TableColumn<EventCours, Integer> nbPlaces;
    @FXML
    private TableColumn<EventCours, String> description;
    @FXML
    private TableColumn<EventCours, Date> dateDebut;
    @FXML
    private TableColumn<EventCours, String> image;
    @FXML
    private TableColumn<EventCours, Date> dateFin;
    @FXML
   
    public ObservableList<EventCours> data = FXCollections.observableArrayList();
    ServiceEventCours sec = new ServiceEventCours();

    @FXML
    private TableView<Evenement> eventTv;
  
    @FXML
    private TableColumn<Evenement, String> nomEventv;
    @FXML
    private TableColumn<Evenement, String> adressev;
    @FXML
    private TableColumn<Evenement, String> typev;
    @FXML
    private TableColumn<Evenement, Float> prixv;
    @FXML
    private TableColumn<Evenement, Integer> nbPlacesv;
    @FXML
    private TableColumn<Evenement, Date> descriptionv;
    @FXML
    private TableColumn<Evenement, Date> dateDebutv;
    @FXML
    private TableColumn<Evenement, String> imagev;
    @FXML
    private TableColumn<Evenement, Date> dateFinv;
    @FXML
    

    public ObservableList<Evenement> dataa = FXCollections.observableArrayList();
    ServiceEvent se = new ServiceEvent();
    @FXML
    private ImageView imageview;
    @FXML
    private WebView webview;
       Connection cn2;
       private Evenement selected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //tab event non confirmés
        data.addAll(sec.readAll());
      // this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nomEvent.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
        this.adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        this.type.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        this.nbPlaces.setCellValueFactory(new PropertyValueFactory<>("nbPlaces"));
        this.description.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.dateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        this.image.setCellValueFactory(new PropertyValueFactory<>("image"));
        this.dateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
      
        this.eventcoursTV.setItems(data);

        //tab event
        dataa.addAll(se.readAll());
       // this.idv.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nomEventv.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
        this.adressev.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        this.typev.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.prixv.setCellValueFactory(new PropertyValueFactory<>("prix"));
        this.nbPlacesv.setCellValueFactory(new PropertyValueFactory<>("nbPlaces"));
        this.descriptionv.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.dateDebutv.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        this.imagev.setCellValueFactory(new PropertyValueFactory<>("image"));
        this.dateFinv.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
  
        this.eventTv.setItems(dataa);
           eventTv.setRowFactory(tv -> {
            TableRow<Evenement> row = new TableRow<>();
            row.setOnMouseClicked(e -> {

                
                Object selectedItems = eventTv.getSelectionModel().getSelectedItems().get(0);
                String Id_Livre = selectedItems.toString().split(",")[0].substring(0);
                System.out.println(Id_Livre);
              String imagename = se.getImage(Integer.parseInt(Id_Livre));
                Image image = new Image("http://localhost/backt/web/" + imagename);
                imageview.setImage(image);
            });
            return row;
        });
       
        //this for edit
        this.eventcoursTV.setEditable(true);
        
        this.nomEvent.setCellFactory(TextFieldTableCell.forTableColumn());
        this.type.setCellFactory(TextFieldTableCell.forTableColumn());
         this.adresse.setCellFactory(TextFieldTableCell.forTableColumn());
         
         
         
         WebEngine webEngine = webview.getEngine();

        URL url1 = this.getClass().getResource("/edu/huntkingdom/gui/webmaps.html");
        webEngine.load(url1.toString());
        webEngine.setJavaScriptEnabled(true);
        
        ServiceEvent evr = new ServiceEvent();
        cn2 = DataBase.getInstance().getConnection();
       
     

        eventTv.setOnMouseClicked(( MouseEvent event) -> {
        if(event.getButton().equals(MouseButton.PRIMARY)){
            System.out.println(eventTv.getSelectionModel().getSelectedItem());
            selected=eventTv.getSelectionModel().getSelectedItem();
            try {
                        System.out.println("Selected item: " + selected.getDescription());
                    String s=    selected.getLatlng();
                    String[] ch=s.split(";");
                    String X=ch[0];
                     String Y=ch[1];
                    
                        System.out.println("addpopup(" + selected.getLatlng()+ ",'" + selected.getDescription() + "')");
                        webEngine.executeScript("addpopup(" + X + "," + Y + ",'" + selected.getNomEvent()+ "')");
                    } catch (Exception e) {
                        System.out.println("problem with script" + e.getMessage());
                    }
           
        }
        
        
    });

    }

    @FXML
    private void confirmer(ActionEvent event) throws SQLException {
        if (eventcoursTV.getSelectionModel().getSelectedItem() != null) {
            Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteBookAlert.setTitle("confirmation événement");
            deleteBookAlert.setHeaderText(null);
            deleteBookAlert.setContentText("vous étes sur de confirmer cet événement ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                EventCours L = eventcoursTV.getSelectionModel().getSelectedItem();
                //  data.clear();
                // data.addAll(lcr.afficherlivre());
                Evenement E = new Evenement(L.getNomEvent(), L.getAdresse(), L.getType(), L.getPrix(), L.getNbPlaces(), L.getDescription(), L.getDateDebut(), L.getImage(), L.getDateFin(), L.getLatlng());

                se.ajouterEvent(E);
                sec.supprimer(L);
                data.clear();
                data.addAll(sec.readAll());
                dataa.clear();
                dataa.addAll(se.readAll());

                //Alert Delete Blog :
                Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteBookAlert.setTitle("confrimation event");
                succDeleteBookAlert.setHeaderText("Results:");
                succDeleteBookAlert.setContentText("evenement confirmé merci!");

                succDeleteBookAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("selctionnez un event");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("vous devez selectionner un event tout d'abord!");
            selectBookAlert.showAndWait();

        }
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        if (eventcoursTV.getSelectionModel().getSelectedItem() != null) {
            Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteBookAlert.setTitle("suppresion événement");
            deleteBookAlert.setHeaderText(null);
            deleteBookAlert.setContentText("vous étes sur de supprimer cet événement ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                EventCours e = eventcoursTV.getSelectionModel().getSelectedItem();
                sec.supprimer(e);
                data.clear();
                data.addAll(sec.readAll());
                dataa.clear();
                dataa.addAll(se.readAll());

                //Alert Delete Blog :
                Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteBookAlert.setTitle("suppression event");
                succDeleteBookAlert.setHeaderText("Results:");
                succDeleteBookAlert.setContentText("evenement supprimé merci!");

                succDeleteBookAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

         
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("selctionnez un event");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("vous devez selectionner un event tout d'abord!");
            selectBookAlert.showAndWait();

        }

    }

    @FXML
    private void modifier(ActionEvent event) {
        if (eventcoursTV.getSelectionModel().getSelectedItem() != null) {

            EventCours ec = eventcoursTV.getSelectionModel().getSelectedItem();

            sec.Updateeventcours(ec.getNomEvent(),ec.getAdresse(),ec.getType(),ec.getId());
            Alert BookAlert = new Alert(Alert.AlertType.INFORMATION);
            BookAlert.setTitle("edit");
            BookAlert.setHeaderText(null);
            BookAlert.setContentText("The event was successfully edit");
            BookAlert.showAndWait();

        } else {
            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Select a event");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("You need to select a event first!");
            selectBookAlert.showAndWait();
            //Alert Select Book !
            
        }
    }
     @FXML
    public void changetypeCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        EventCours eventcours = eventcoursTV.getSelectionModel().getSelectedItem();
        eventcours.setType(edittedCell.getNewValue().toString());
       
    }
    
       @FXML
    public void changeNomEventCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        EventCours eventcours = eventcoursTV.getSelectionModel().getSelectedItem();
        eventcours.setNomEvent(edittedCell.getNewValue().toString());
       
    }
    
       @FXML
    public void changeAdresseCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        EventCours eventcours = eventcoursTV.getSelectionModel().getSelectedItem();
        eventcours.setAdresse(edittedCell.getNewValue().toString());
       
    }
//wala stana hezou kima howa men hne
    @FXML
    private void print(ActionEvent event)  {
   
    }
    
}
