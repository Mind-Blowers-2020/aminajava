/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.services;

import edu.huntkingdom.utils.DataBase;
import edu.huntkingdom.entities.EventCours;
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
import java.sql.Timestamp;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class ServiceEventCours {

    private Connection con;
    private Statement ste;

    public ServiceEventCours() {
        con = DataBase.getInstance().getConnection();
    }

    public List<EventCours> readAll() {

        List<EventCours> arr = new ArrayList<>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from eventcours ");
            while (rs.next()) {
                //int id=rs.getInt(1);
                int id = rs.getInt("id");
                String nomEvent = rs.getString("nomEvent");
                String adresse = rs.getString("adresse");
                String type = rs.getString("type");
                float prix = rs.getFloat("prix");
                int nbPlaces = rs.getInt("nbPlaces");
                String description = rs.getString("description");
                Timestamp dateDebut = rs.getTimestamp("dateDebut");
                String image = rs.getString("image");
                Timestamp dateFin = rs.getTimestamp("dateFin");
                String latlng = rs.getString("latlng");

                EventCours e = new EventCours(id, nomEvent, adresse, type, prix, nbPlaces, description, dateDebut, image, dateFin, latlng);
                arr.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEventCours.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public boolean supprimer(EventCours e) throws SQLException {

        String reqeute = "delete from eventcours  where (id = ?) ;";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
            pst.setInt(1, e.getId());
            // pst.setString(2, m.setDescription(reqeute));
            if (pst.executeUpdate() != 0) {
                System.out.println("Evenement supprimer");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
//To change body of generated methods, choose Tools | Templates.
    }

    public boolean Updateeventcours(String nomEvent, String adresse, String type, int id) {

        String requete = "UPDATE eventcours SET  nomEvent= ? , adresse = ? , type = ?  where id=?;";
        try {
            PreparedStatement pst = con.prepareStatement(requete);

            pst.setString(1, nomEvent);
            pst.setString(2, adresse);
            pst.setString(3, type);
            pst.setInt(4, id);

            if (pst.executeUpdate() != 0) {
                System.out.println("Evenement Updated");
            } else {
                System.out.println("non");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }

    public void ajouter(EventCours e) {

        try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO eventcours ( `id`, `nomEvent`, `adresse`, `type`,`prix`, `nbPlaces` ,`description`, `dateDebut`, `image` , `dateFin`,`latlng` ) VALUES ( NULL, ?, ?, ?, ?, ? , ? ,? ,? ,?,?); ");
            pre.setString(1, e.getNomEvent());
            pre.setString(2, e.getAdresse());
            pre.setString(3, e.getType());
            pre.setFloat(4, e.getPrix());
     
          // pre.setDouble(4, 0);
            pre.setInt(5, e.getNbPlaces());
            pre.setString(6, e.getDescription());
            pre.setTimestamp(7, e.getDateDebut());
            pre.setString(8, e.getImage());
            pre.setTimestamp(9, e.getDateFin());
                   pre.setString(10,e.getLatlng());

            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

}
