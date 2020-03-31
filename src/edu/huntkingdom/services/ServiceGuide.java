/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.services;

import edu.huntkingdom.entities.Guide;
import edu.huntkingdom.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class ServiceGuide {

    private Connection con;
    private Statement ste;

    public ServiceGuide() {
        con = DataBase.getInstance().getConnection();
    }
    public List<Guide> readAll() {

        List<Guide> arr = new ArrayList<>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from guide ");
            while (rs.next()) {
                //int id=rs.getInt(1);
                int idguide = rs.getInt("idguide");
          int evenement = rs.getInt("evenement");
               String nom=rs.getString("nom");
                String prenom=rs.getString("prenom");
                 String mail=rs.getString("mail");
                    int tel = rs.getInt("tel");
                     String eventType=rs.getString("eventType");
                
                Guide e = new Guide(idguide,evenement,nom,prenom,mail,tel,eventType);
                arr.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGuide.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    
    
    public boolean supprimer(Guide g) throws SQLException {

        String reqeute = "delete from guide  where (idguide = ?) ;";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
            pst.setInt(1, g.getIdGuide());
            // pst.setString(2, m.setDescription(reqeute));
            if (pst.executeUpdate() != 0) {
                System.out.println("guide supprimé");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
//To change body of generated methods, choose Tools | Templates.
    }

    public boolean UpdateGuide(String nom, String prenom, String mail, int tel, int idguide) {

        String requete = "UPDATE guide SET  nom= ? , prenom = ? , mail = ?, tel=?  where idguide=?;";
        try {
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setString(3, mail);
            pst.setInt(4, tel);
            pst.setInt(5, idguide);

            if (pst.executeUpdate() != 0) {
                System.out.println("guide Updated");
            } else {
                System.out.println("non");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }


}
