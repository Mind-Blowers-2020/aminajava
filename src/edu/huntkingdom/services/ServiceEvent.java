/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.services;

import edu.huntkingdom.entities.Evenement;
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
public class ServiceEvent {

    private Connection con;
    private Statement ste;

    public ServiceEvent() {
        con = DataBase.getInstance().getConnection();
    }

    public List<Evenement> readAll() {

        List<Evenement> arr = new ArrayList<>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from evenement ");
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

                Evenement e = new Evenement(id, nomEvent, adresse, type, prix, nbPlaces, description, dateDebut, image, dateFin, latlng);
                arr.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public void ajouterEvent(Evenement e) {
        try {

            ste = con.createStatement();
            String requeteInsert = "INSERT INTO evenement (nomEvent,adresse,type,prix,nbPlaces,description,dateDebut,image,dateFin,latlng) VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(requeteInsert);
            pst.setString(1, e.getNomEvent());
            pst.setString(2, e.getAdresse());
            pst.setString(3, e.getType());
            pst.setFloat(4, e.getPrix());
            pst.setInt(5, e.getNbPlaces());
            pst.setString(6, e.getDescription());
            pst.setTimestamp(7, (Timestamp) e.getDateDebut());
            pst.setString(8, e.getImage());
            pst.setTimestamp(9, (Timestamp) e.getDateFin());
            pst.setString(10, e.getLatlng());

 if (pst.executeUpdate() != 0) {
                System.out.println("Evenement deleted");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public String getImage(int id) {
        String role = "";
        try {
            PreparedStatement pre = con.prepareStatement("select image from evenement where id=?");
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                role = rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return role;
    }
    
       public String getNom(int id) {
        String role = "";
        try {
            PreparedStatement pre = con.prepareStatement("select nomEvent from evenement where id=?");
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                role = rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return role;
    }
public int numberevent () throws SQLException{
         int y=0;
          ste=con.createStatement() ;
           ResultSet rs=ste.executeQuery("SELECT COUNT(*) as total FROM evenement ");
           while(rs.next())
           {
                y = rs.getInt("total");
               
               
           }
           System.out.println("total number : "+y);
           return y;
         
     }
public Evenement findbyid(int id) {
        Evenement u = new Evenement();
        try {

            PreparedStatement pre = con.prepareStatement("Select * from evenement  WHERE id=? ");
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int nbPlaces =rs.getInt("nbPlaces");
                String nomEvent = rs.getString("nomEvent");
             float prix =rs.getFloat("prix");
              String image = rs.getString("image");
              String adresse=rs.getString("adresse");
             String description=rs.getString("description");
            
          u.setNomEvent(nomEvent);
              u.setPrix(prix);
              u.setImage(image);
              u.setNbPlaces(nbPlaces);
              u.setAdresse(adresse);
              u.setDescription(description);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return u;
    }
public int getquantite(int id) {
        int q = 0;
//akeka mao ?oui
        String requete4 = "select nbPlaces from evenement where id=?;";
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(requete4);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                q = rs.getInt(1);
            }
        } catch (SQLException ex) {
        }
        return q;
    }

    public void decrementqte(int id) {
        int q = getquantite(id);
        q--;
        String requete4 = "update evenement SET nbPlaces=? where id=?;";
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(requete4);
            pst.setInt(1, q);

            pst.setInt(2, id);
            pst.executeUpdate();

        } catch (SQLException ex) {
        }
    }

    public int findbyImage(String image) {
        int u = 0;
        try {

            PreparedStatement pre = con.prepareStatement("Select * from evenement  WHERE image=? ");
            pre.setString(1, image);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
             
                 u = rs.getInt("id");
             
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return u;
    }

}
