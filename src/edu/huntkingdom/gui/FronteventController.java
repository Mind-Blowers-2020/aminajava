/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.gui;

import edu.huntkingdom.entities.Evenement;
import edu.huntkingdom.entities.EventCours;
import edu.huntkingdom.services.ServiceEvent;
import edu.huntkingdom.services.ServiceEventCours;
import edu.huntkingdom.services.UploadServices;
import edu.huntkingdom.utils.DataBase;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.ws.Holder;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FronteventController implements Initializable {

    @FXML
    private AnchorPane Paneeventsfx;
    @FXML
    private ImageView imageeventspanefx;
    @FXML
    private Label subjecteventspanefx;
    @FXML
    private Label texteventspanefx;
    @FXML
    private Label DateeventsInteface;
    @FXML
    private Button participatefx;
    @FXML
    private Button cancelfx;
    @FXML
    private Button printfx;
    @FXML
    private AnchorPane Paneeventsfx1;
    @FXML
    private ImageView imageeventspanefx1;
    @FXML
    private Label subjecteventspanefx1;
    @FXML
    private Label texteventspanefx1;
    @FXML
    private Label DateeventsInteface1;
    @FXML
    private Button participatefx1;
    @FXML
    private Button cancelfx1;
    @FXML
    private Button printfx1;
    @FXML
    private AnchorPane Paneeventsfx11;
    @FXML
    private ImageView imageeventspanefx11;
    @FXML
    private Label subjecteventspanefx11;
    @FXML
    private Label texteventspanefx11;
    @FXML
    private Label DateeventsInteface11;
    @FXML
    private Button participatefx2;
    @FXML
    private Button cancelfx2;
    @FXML
    private Button printfx2;
    private ObservableList<Evenement> data;
    private Connection con = null;
    ServiceEvent se = new ServiceEvent();
    int CurrentEvent = 0;
    public static Holder<String> holdID = new Holder<String>();
    int i = 0;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtaddress;
    @FXML
    private ComboBox<String> combotype;
    public ObservableList<String> types = FXCollections.observableArrayList("peche", "chasse");

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
    ServiceEventCours ser = new ServiceEventCours();
    UploadServices uploadservices = new UploadServices();

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

        data = FXCollections.observableArrayList();
        con = DataBase.getInstance().getConnection();
        data.addAll(se.readAll());

        try {
            getUserData(CurrentEvent);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public ArrayList<ImageView> ListImagese = new ArrayList<>();
    public ArrayList<Label> ListTextflowe = new ArrayList<>();
    public ArrayList<Label> Listlabeltitleevent = new ArrayList<>();
    public ArrayList<Label> Listdateevent = new ArrayList<>();
    public ArrayList<AnchorPane> Listpaneevent = new ArrayList<>();

    public ArrayList<AnchorPane> ListPaneeventsfx = new ArrayList<>();

    public void getUserData(int CurrentEvent) throws SQLException {

        Listpaneevent.add(Paneeventsfx);
        Listpaneevent.add(Paneeventsfx1);
        Listpaneevent.add(Paneeventsfx11);

        ListImagese.add(imageeventspanefx);
        ListImagese.add(imageeventspanefx1);
        ListImagese.add(imageeventspanefx11);

        ListTextflowe.add(texteventspanefx);
        ListTextflowe.add(texteventspanefx1);
        ListTextflowe.add(texteventspanefx11);

        Listlabeltitleevent.add(subjecteventspanefx);
        Listlabeltitleevent.add(subjecteventspanefx1);
        Listlabeltitleevent.add(subjecteventspanefx11);

        Listdateevent.add(DateeventsInteface);
        Listdateevent.add(DateeventsInteface1);
        Listdateevent.add(DateeventsInteface11);

        int Nombre = se.numberevent();
//        int i = CurrentEvent;

        for (i = CurrentEvent; i < CurrentEvent + 3; i++) {//hedha chnowa dossier li fih tsawer?ouiatt nbael mteei
            System.out.println(data.get(i).getImage());
            Image image = new Image("http://localhost/backt/web/" + data.get(i).getImage());
            ListImagese.get(i).setImage(image);
            Listlabeltitleevent.get(i).setText(data.get(i).getNomEvent());
            Listdateevent.get(i).setText(data.get(i).getDateDebut().toString());
            ListTextflowe.get(i).setText(data.get(i).getDescription());
            Listpaneevent.get(i).setVisible(true);

        }
        imageeventspanefx.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//            System.out.println("id:"+data.get(CurrentEvent).getId());
//            String numberAsString = Integer.toString(data.get(CurrentEvent).getId());
//            holdID.value = numberAsString;
                String images = imageeventspanefx.getImage().impl_getUrl();
                int id = se.findbyImage(images.substring(27));
                holdID.value = Integer.toString(id);
                System.out.println("id:" + id);

                System.out.println("imagename:" + images.substring(27));
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("eventdetails.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        imageeventspanefx1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//            System.out.println("id:"+data.get(CurrentEvent+1).getId());
//            String numberAsString = Integer.toString(data.get(CurrentEvent+1).getId());
//            holdID.value = numberAsString;
                String images = imageeventspanefx1.getImage().impl_getUrl();
                int id = se.findbyImage(images.substring(27));
                holdID.value = Integer.toString(id);
                System.out.println("id:" + id);
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("eventdetails.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        imageeventspanefx11.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//            System.out.println("id:"+data.get(CurrentEvent+2).getId());
//            String numberAsString = Integer.toString(data.get(CurrentEvent+2).getId());
//            holdID.value = numberAsString;
                String images = imageeventspanefx11.getImage().impl_getUrl();
                int id = se.findbyImage(images.substring(27));
                holdID.value = Integer.toString(id);
                System.out.println("id:" + id);
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("eventdetails.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException e) {
                    // e.printStackTrace();
                }
            }
        });

    }

    @FXML
    private void participate(ActionEvent event) {
           String images = imageeventspanefx.getImage().impl_getUrl();
                int id = se.findbyImage(images.substring(27));
                holdID.value = Integer.toString(id);
                System.out.println("id:" + id);
                   try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("participate.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException e) {
                    // e.printStackTrace();
                }
    }

    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void print(ActionEvent event) {
    }

    @FXML
    private void participate1(ActionEvent event) {
           String images = imageeventspanefx1.getImage().impl_getUrl();
                int id = se.findbyImage(images.substring(27));
                holdID.value = Integer.toString(id);
                System.out.println("id:" + id);
                 try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("participate.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException e) {
                    // e.printStackTrace();
                }
    }

    @FXML
    private void cancel1(ActionEvent event) {
    }

    @FXML
    private void print1(ActionEvent event) {
    }

    @FXML
    private void participate2(ActionEvent event) {
           String images = imageeventspanefx11.getImage().impl_getUrl();
                int id = se.findbyImage(images.substring(27));
                holdID.value = Integer.toString(id);
                System.out.println("id:" + id);
                 try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("participate.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException e) {
                    // e.printStackTrace();
                }
    }

    @FXML
    private void cancel2(ActionEvent event) {
    }

    @FXML
    private void print2(ActionEvent event) {
    }

    @FXML
    private void viewmore(ActionEvent event) {
        int Nombre = 0;
        try {
            Nombre = se.numberevent();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        CurrentEvent = CurrentEvent + 3;

        int diff = Nombre - CurrentEvent;
        if (diff == 2) {
            CurrentEvent--;
        } else if (diff == 1) {
            CurrentEvent = CurrentEvent - 2;
        } else if (diff <= 0) {
            CurrentEvent = 0;
        }

        try {
            getUserData(CurrentEvent);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addEvent.fxml"));
        //Scene scene = new Scene(root, 1100, 650);
        Scene scene = new Scene(root);//fhemtha faza edhyka imchi hajet tefha le:p hhh
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
        System.out.println("ok");

    }

    @FXML
    private void upload(ActionEvent event) {
        FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getAbsolutePath();
            txtimage.setText(imageFile);
        }
    }
void refrech()
{txtimage.clear();
txtaddress.clear();
txtdescription.clear();
txtname.clear();
txtnumber.clear();
txtprice.clear();
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

        Timestamp dated = Timestamp.valueOf(combodateD.getValue().atTime(LocalTime.MIDNIGHT));

        Timestamp datef = Timestamp.valueOf(combodateF.getValue().atTime(LocalTime.MIDNIGHT));

        EventCours e = new EventCours(txtname.getText(), txtaddress.getText(), (String) combotype.getValue(), getTxtprice(), getTxtnumber(), txtdescription.getText(), dated, FilenameInserver, datef);
        ser.ajouter(e);
        refrech();

        Alert succAddBookAlert = new Alert(Alert.AlertType.INFORMATION);
        succAddBookAlert.setTitle("Add Event");
        succAddBookAlert.setHeaderText("Results:");
        succAddBookAlert.setContentText("Event added successfully!");
        succAddBookAlert.showAndWait();

    }
    

}