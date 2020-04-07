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

/**
 * FXML Controller class
 *
 * @author asus
 */
public class BarChartEventController implements Initializable {

    @FXML
    private BarChart<?, ?> chart;
    @FXML
    private Button returntxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            XYChart.Series set1=new XYChart.Series<>();
            ServiceEvent serD = new ServiceEvent();
            ServiceEvent serR = new ServiceEvent();
            List<Evenement> x=serD.eventn();
            
            for (int i=0;i<x.size();i++)
            {
                String event = x.get(i).getNomEvent();
                 int events = x.get(i).getNbPlaces();
             
                set1.getData().add(new XYChart.Data(event,events));
                
            }

            
            chart.getData().addAll(set1);
        } catch (SQLException ex) {
            Logger.getLogger(BarChartEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML
    private void returnstat(ActionEvent event) {
         Stage stage = (Stage) returntxt.getScene().getWindow();
            stage.close();
    }
    
}
