/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.gui;



import edu.huntkingdom.services.ServiceEvent;
import edu.huntkingdom.entities.Evenement;
import edu.huntkingdom.utils.DataBase;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dr
 */
public class ShoweventController implements Initializable {

    private Connection con=null;
    //private PreparedStatement ste=null;
    private ResultSet rs=null;
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
    private AnchorPane Paneeventsfx11;
    @FXML
    private ImageView imageeventspanefx11;
    @FXML
    private Label subjecteventspanefx11;
    @FXML
    private Label texteventspanefx11;
    @FXML
    private Label DateeventsInteface11;
    private AnchorPane Paneeventsfx2;
    private ImageView imageeventspanefx2;
    private Label subjecteventspanefx2;
    private Label texteventspanefx2;
    private Label DateeventsInteface2;
    private AnchorPane Paneeventsfx12;
    private ImageView imageeventspanefx12;
    private Label subjecteventspanefx12;
    private Label texteventspanefx12;
    private Label DateeventsInteface12;
    private ImageView imageeventspanefx111;
    private Label subjecteventspanefx111;
    private Label texteventspanefx111;
    private Label DateeventsInteface111;
      private ObservableList<Evenement> data;
     ServiceEvent ser = new ServiceEvent();
    @FXML
    private Button participatefx;
    @FXML
    private Button cancelfx;
    @FXML
    private Button printfx;
    @FXML
    private Button participatefx1;
    @FXML
    private Button cancelfx1;
    @FXML
    private Button printfx1;
    @FXML
    private Button participatefx2;
    @FXML
    private Button cancelfx2;
    @FXML
    private Button printfx2;


    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        
          data = FXCollections.observableArrayList();
        con = DataBase.getInstance().getConnection();
        try {
             
       
            data = (ObservableList<Evenement>) ser.readAll();
            getUserData();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        // TODO
    }   
    
     public ArrayList<ImageView> ListImagese = new ArrayList<>();
    public ArrayList<Label> ListTextflowe = new ArrayList<>();
    public ArrayList<Label> Listlabeltitleevent = new ArrayList<>();
    public ArrayList<Label> Listdateevent = new ArrayList<>();
     public ArrayList<AnchorPane> Listpaneevent = new ArrayList<>();
     
      public void getUserData() throws SQLException {
        
        Listpaneevent.add(Paneeventsfx);
        Listpaneevent.add(Paneeventsfx1);
        Listpaneevent.add(Paneeventsfx11);
        Listpaneevent.add(Paneeventsfx11);
        Listpaneevent.add(Paneeventsfx12);
        Listpaneevent.add(Paneeventsfx2);
        ListImagese.add(imageeventspanefx);
        ListImagese.add(imageeventspanefx1);
        ListImagese.add(imageeventspanefx11);
        ListImagese.add(imageeventspanefx111);
        ListImagese.add(imageeventspanefx12);
        ListImagese.add(imageeventspanefx2);
        ListTextflowe.add(texteventspanefx);
        ListTextflowe.add(texteventspanefx1);
        ListTextflowe.add(texteventspanefx11);
        ListTextflowe.add(texteventspanefx111);
        ListTextflowe.add(texteventspanefx12);
        ListTextflowe.add(texteventspanefx2);
        Listlabeltitleevent.add(subjecteventspanefx);
        Listlabeltitleevent.add(subjecteventspanefx1);
        Listlabeltitleevent.add(subjecteventspanefx11);
        Listlabeltitleevent.add(subjecteventspanefx111);
        Listlabeltitleevent.add(subjecteventspanefx12);
        Listlabeltitleevent.add(subjecteventspanefx2);
        Listdateevent.add(DateeventsInteface);
        Listdateevent.add(DateeventsInteface1);
        Listdateevent.add(DateeventsInteface11);
        Listdateevent.add(DateeventsInteface111);
        Listdateevent.add(DateeventsInteface12);
        Listdateevent.add(DateeventsInteface2);
        
        ServiceEvent ser = new ServiceEvent();
        
         data = FXCollections.observableArrayList();
            data = (ObservableList<Evenement>) ser.readAll(); ////nf service  hahahaha lahdha 
       
        int Nombre = ser.numberevent();
        
            
        for (int i = 0; i<Nombre; i++)
        {//hedha chnowa dossier li fih tsawer?ouiatt nbael mteei
             File file = new File("http://localhost/backt/web/"+data.get(i).getImage()+".jpg");
           Image image = new Image(file.toURI().toString());
           String  s= data.get(i).getImage();
            System.out.println(file.toURI().toString());
            ListImagese.get(i).setImage(image);
            Listlabeltitleevent.get(i).setText(data.get(i).getNomEvent());
            Listdateevent.get(i).setText(data.get(i).getDateDebut().toString());
            ListTextflowe.get(i).setText(data.get(i).getDescription());
            Listpaneevent.get(i).setVisible(true);
            //le le mezelt zebi makcj tc
        }
            
    }

    @FXML
    private void participate(ActionEvent event) {
     
    }
        
    

    @FXML
    private void cancel(ActionEvent event) {
       
    }

    @FXML
    private void print(ActionEvent event) {
        
        
    }

    @FXML
    private void participate1(ActionEvent event) {
     
    }

    @FXML
    private void cancel1(ActionEvent event) {
        
        

    }

    @FXML
    private void print1(ActionEvent event) {
    }

    @FXML
    private void participate2(ActionEvent event) {

    }

    @FXML
    private void cancel2(ActionEvent event) {
      
    }

    @FXML
    private void print2(ActionEvent event) {
    }
}

