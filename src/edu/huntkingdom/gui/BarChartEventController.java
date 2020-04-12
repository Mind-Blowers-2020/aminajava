/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import edu.huntkingdom.services.ServiceEvent;
import edu.huntkingdom.entities.Evenement;
import edu.huntkingdom.utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class BarChartEventController implements Initializable {

    private BarChart<?, ?> chart;
    @FXML
    private Button returntxt;
    @FXML
    private PieChart piechart;
     ObservableList< PieChart.Data> piechartdata;
  ArrayList< String> p = new ArrayList< String>();
    ArrayList< Integer> c = new ArrayList< Integer>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       loadData();
       
        piechart.setData(piechartdata);

    }  
    public void loadData() {

        String query = "select COUNT(*) as count ,nomEvent from evenement GROUP BY nomEvent "; //ORDER BY P asc

        piechartdata = FXCollections.observableArrayList();

        Connection con = DataBase.getInstance().getConnection();

        try {

            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {

                piechartdata.add(new PieChart.Data(rs.getString("nomEvent"), rs.getInt("count")));
                p.add(rs.getString("nomEvent"));
                c.add(rs.getInt("count"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    private void returnstat(ActionEvent event) {
         Stage stage = (Stage) returntxt.getScene().getWindow();
            stage.close();
    }
    
}
