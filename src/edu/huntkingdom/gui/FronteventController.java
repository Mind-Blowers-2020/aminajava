/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.gui;

import com.itextpdf.text.pdf.qrcode.QRCode;
import edu.huntkingdom.entities.Email;
import edu.huntkingdom.entities.Evenement;
import edu.huntkingdom.entities.EventCours;
import edu.huntkingdom.entities.Participant;
import edu.huntkingdom.entities.User;
import edu.huntkingdom.services.ServiceEvent;
import edu.huntkingdom.services.ServiceEventCours;
import edu.huntkingdom.services.ServiceParticipant;
import edu.huntkingdom.services.UploadServices;
import edu.huntkingdom.services.UserServices;
import edu.huntkingdom.utils.DataBase;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.E;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private ObservableList<Evenement> data;
    private Connection con = null;
    ServiceEvent se = new ServiceEvent();
    int CurrentEvent = 0;
    public static Holder<String> holdID = new Holder<String>();
    int i = 0;
    @FXML
    private TextField txtname;
    @FXML
    private ComboBox<String> combotype;
    public ObservableList<String> types = FXCollections.observableArrayList("peche", "chasse");
    @FXML
    private ComboBox<String> comboadresse;
    public ObservableList<String> adresse = FXCollections.observableArrayList("tunis", "ariana", "sousse", "ben arbous", "monasitir", "sfax");
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

    int iduserconnect = 1; // normalment bel session
    UserServices us = new UserServices();
    User user = us.findUserbyID(iduserconnect);
    ServiceParticipant sp = new ServiceParticipant();

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
        comboadresse.setItems(adresse);

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

                se.findbyid(id);

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

        imageeventspanefx1.setOnMouseClicked((MouseEvent event) -> {
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
    private void participate(ActionEvent event) throws SQLException {
        String images = imageeventspanefx.getImage().impl_getUrl();
        int id = se.findbyImage(images.substring(27));
        holdID.value = Integer.toString(id);
        String eventid = holdID.value;

        System.out.println("id:" + id);
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());

        Participant p = new Participant(0, Boolean.FALSE, Integer.parseInt(eventid), ts, user.getUsername(), user.getEmail());
        Email email = new Email();
        HashMap<String, String> message = new HashMap<String, String>();
        message.put("Title", "huntkingkdom administartion ");
//         message.put("UpdatedAt","");
//        message.put("Description", "hh");
        String nomevent = se.getnom(Integer.parseInt(eventid));
        QrCodeController q = new QrCodeController();
        Evenement e = new Evenement();
        e = se.findbyid(id);
        q.ini(e);
        message.put("Content", "bonjour  " + user.getUsername() + "    votre inscrit au evenement    " + nomevent + "   a été bien ajoutée");
        try {
            email.sendEmail(user.getEmail(), "huntkingkdom administartion", message);

        } catch (Exception ex) {
            ex.getMessage();
        }

        se.decrementqte(Integer.parseInt(eventid));
        sp.ajouter(p);

        Alert succAddBookAlert = new Alert(Alert.AlertType.INFORMATION);
        succAddBookAlert.setTitle("Add Event");
        succAddBookAlert.setHeaderText("Results:");
        succAddBookAlert.setContentText("Event added successfully!");
        succAddBookAlert.showAndWait();
        refrech();
    }

    @FXML
    private void participate1(ActionEvent event) throws SQLException {
        String images = imageeventspanefx1.getImage().impl_getUrl();
        int id = se.findbyImage(images.substring(27));
        holdID.value = Integer.toString(id);
        System.out.println("id:" + id);
        String eventid = holdID.value;

        System.out.println("id:" + id);
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());

        Participant p = new Participant(0, Boolean.FALSE, Integer.parseInt(eventid), ts, user.getUsername(), user.getEmail());
        Email email = new Email();
        HashMap<String, String> message = new HashMap<String, String>();
        message.put("Title", "huntkingkdom administartion ");
        QrCodeController q = new QrCodeController();
        Evenement e = new Evenement();
        e = se.findbyid(id);
        q.ini(e);
//         message.put("UpdatedAt","");
//        message.put("Description", "hh");
        String nomevent = se.getnom(Integer.parseInt(eventid));
        message.put("Content", "bonjour  " + user.getUsername() + "    votre inscrit au evenement    " + nomevent + "   a été bien ajoutée");
        try {
            email.sendEmail(user.getEmail(), "huntkingkdom administartion", message);

        } catch (Exception ex) {
            ex.getMessage();
        }
        se.decrementqte(Integer.parseInt(eventid));
        sp.ajouter(p);

        Alert succAddBookAlert = new Alert(Alert.AlertType.INFORMATION);
        succAddBookAlert.setTitle("Add Event");
        succAddBookAlert.setHeaderText("Results:");
        succAddBookAlert.setContentText("Event added successfully!");
        succAddBookAlert.showAndWait();
    }

    @FXML
    private void participate2(ActionEvent event) throws SQLException {
        String images = imageeventspanefx11.getImage().impl_getUrl();
        int id = se.findbyImage(images.substring(27));
        holdID.value = Integer.toString(id);
        String eventid = holdID.value;

        System.out.println("id:" + id);
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());

        Participant p = new Participant(0, Boolean.FALSE, Integer.parseInt(eventid), ts, user.getUsername(), user.getEmail());
        Email email = new Email();
        HashMap<String, String> message = new HashMap<String, String>();
        message.put("Title", "huntkingkdom administartion ");
        QrCodeController q = new QrCodeController();
        Evenement e = new Evenement();
        e = se.findbyid(id);
        q.ini(e);
//         message.put("UpdatedAt","");
//        message.put("Description", "hh");
        String nomevent = se.getnom(Integer.parseInt(eventid));
        message.put("Content", "bonjour  " + user.getUsername() + "    votre inscrit au evenement    " + nomevent + "   a été bien ajoutée");
        try {
            email.sendEmail(user.getEmail(), "huntkingkdom administartion", message);

        } catch (Exception ex) {
            ex.getMessage();
        }
        se.decrementqte(Integer.parseInt(eventid));
        sp.ajouter(p);
        Alert succAddBookAlert = new Alert(Alert.AlertType.INFORMATION);
        succAddBookAlert.setTitle("Add Event");
        succAddBookAlert.setHeaderText("Results:");
        succAddBookAlert.setContentText("Event added successfully!");
        succAddBookAlert.showAndWait();
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

    private void ajouter(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addEvent.fxml"));
        //Scene scene = new Scene(root, 1100, 650);
        Scene scene = new Scene(root);
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

    void refrech() {
        txtimage.clear();
//txtaddress.clear();
        txtdescription.clear();
        txtname.clear();
        txtnumber.clear();
        txtprice.clear();

    }

    @FXML
    private void addevent(ActionEvent event) {
        if (verifNomEvent() && verifDate() && verifNumber() && verifprix()&& verifDatedebut()) {
            String l = "";
            if (combotype.getSelectionModel().isEmpty()) {
                Alert selectEventAlert = new Alert(Alert.AlertType.WARNING);
                selectEventAlert.setTitle("séléctionnez un événement");
                selectEventAlert.setHeaderText(null);
                selectEventAlert.setContentText("tu dois choisir un type !");
                selectEventAlert.showAndWait();
                return;
            }
            String FilenameInserver = uploadservices.upload(txtimage.getText());

            Timestamp dated = Timestamp.valueOf(combodateD.getValue().atTime(LocalTime.MIDNIGHT));

            Timestamp datef = Timestamp.valueOf(combodateF.getValue().atTime(LocalTime.MIDNIGHT));
            String s = comboadresse.getValue();
            System.out.println(s);
            if (s.equals("ariana")) {
                l = 36.85724000000005 + ";" + 10.189320000000066;
            }
            if (s.equals("tunis")) {
                l = 33.8439408 + ";" + 11.8801133;
            }
            if (s.equals("sousse")) {
                l = 35.829030000000046 + ";" + 10.63778000000002;
            }
            if (s.equals("ben arous")) {
            }
            if (s.equals("sfax")) {
                l = 36.85724000000005 + ";" + 10.189320000000066;
            }

            if (s.equals("monastir")) {
                l = 36.85724000000005 + ";" + 10.189320000000066;
            }
            EventCours e = new EventCours(txtname.getText(), comboadresse.getValue(), (String) combotype.getValue(), getTxtprice(), getTxtnumber(), txtdescription.getText(), dated, FilenameInserver, datef, l);

            ser.ajouter(e);
            refrech();

            Alert succAddBookAlert = new Alert(Alert.AlertType.INFORMATION);
            succAddBookAlert.setTitle("Ajout événement");
            succAddBookAlert.setHeaderText("Resultat:");
            succAddBookAlert.setContentText("événement ajouté!");
            succAddBookAlert.showAndWait();

        }
    }

    private boolean verifNomEvent() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(txtname.getText());
        if (m.find() && m.group().equals(txtname.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le nom de l'event");
            alert.showAndWait();
            return false;
        }
    }

    private boolean verifDate() {
        Timestamp dated = Timestamp.valueOf(combodateD.getValue().atTime(LocalTime.MIDNIGHT));

        Timestamp datef = Timestamp.valueOf(combodateF.getValue().atTime(LocalTime.MIDNIGHT));
        if (dated.before(datef)) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("date début inférieur à date fin");
            alert.showAndWait();
            return false;
        }
    }
private boolean verifDatedebut() {
        Timestamp dated = Timestamp.valueOf(combodateD.getValue().atTime(LocalTime.MIDNIGHT));
 Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        
        if (ts.before(dated)) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("date début inférieur à date système");
            alert.showAndWait();
            return false;
        }
    }

    private boolean verifNumber() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(txtnumber.getText());

        if (m.find() && m.group().equals(txtnumber.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation de nombre de places");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le nombre de places saisis");
            alert.showAndWait();
            return false;
        }
    }

    private boolean verifprix() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(txtprice.getText());

        if (m.find() && m.group().equals(txtprice.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation de prix");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le prix saisis");
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void calendar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FullCalendar.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            calendarController controller = fxmlLoader.getController();
            controller.calendarPane.getChildren().add(new edu.huntkingdom.gui.FullCalendarView(YearMonth.now()).getView());
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
