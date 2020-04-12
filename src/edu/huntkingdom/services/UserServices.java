/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.services;

import edu.huntkingdom.entities.User;
import edu.huntkingdom.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayadi
 */
public class UserServices {
    
        private Connection con;
    private Statement ste;

    public UserServices() {
        con = DataBase.getInstance().getConnection();
    }
    public User findUserbyID(int id) {
        User u= new User();
        try {
            String query = "select * from fos_user where id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String email =rs.getString("email");
                
                u.setId(id);
                u.setEmail(email);
                u.setUsername(username);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return u;
    }
}
