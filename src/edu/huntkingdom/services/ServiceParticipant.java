/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.services;

import edu.huntkingdom.entities.EventCours;
import edu.huntkingdom.entities.Guide;
import edu.huntkingdom.entities.Participant;
import edu.huntkingdom.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class ServiceParticipant {

    private Connection con;
    private Statement ste;

    public ServiceParticipant() {
        con = DataBase.getInstance().getConnection();
    }

    public List<Participant> readAll() {
ServiceEvent se=new ServiceEvent();

        List<Participant> arr = new ArrayList<>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from participant ");
            while (rs.next()) {
                //int id=rs.getInt(1);
                int id = rs.getInt("id");
                Boolean confirmation = rs.getBoolean("confirmation");
                int evenement = rs.getInt("evenement");
                Timestamp date_inscrit = rs.getTimestamp("date_inscrit");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
        
                Participant p = new Participant(id, confirmation, evenement, date_inscrit, nom, prenom);
                p.setNomEvent(se.getNom(evenement));
                arr.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticipant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public boolean supprimer(Participant p) throws SQLException {

        String reqeute = "delete from participant  where (id = ?) ;";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
            pst.setInt(1, p.getId());
            // pst.setString(2, m.setDescription(reqeute));
            if (pst.executeUpdate() != 0) {
                System.out.println("participant supprimé");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
    }
    

    public void ajouter(Participant e) {

        try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO participant (`id`, `confirmation`, `evenement`, `date_inscrit`,`nom`, `prenom`) VALUES ( NULL, ?, ?, ?, ?, ?); ");
            pre.setBoolean(1, e.getConfiramtion());
            pre.setInt(2, e.getEvenment());
            pre.setTimestamp(3,e.getDate_inscrit());
            pre.setString(4, e.getNom());
            pre.setString(5, e.getPrenom());

            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

}
