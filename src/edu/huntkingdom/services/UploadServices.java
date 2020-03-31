/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.services;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author asus
 */
public class UploadServices {
    public String upload(String Path) {
        String fileNameInServer = "";
        try {
            FileUploader fu = new FileUploader("localhost/backt");

            //Upload
             fileNameInServer = fu.upload(Path);
             System.out.println(fileNameInServer);
             if(fileNameInServer.equals(""))
                 System.out.println("ddd");
             else
                 System.out.println("ouiiiiiii");

            return fileNameInServer;
        } catch (ProtocolException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
        return fileNameInServer;

    }

    public boolean delete(String fileNameInServer) {
        try {
            FileUploader fu = new FileUploader("localhost/backt");

            if (fu.delete(fileNameInServer)) {

                System.out.println("File " + fileNameInServer + " deleted.");
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(UploadServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public static void main(String[] args) {
        UploadServices us = new UploadServices();
        System.out.println("gggg");
        System.out.println(us.upload("C:/Users/asus/Desktop/event chasse/téléchargement.jpg"));
    }  
//ryt mch yhot feha f backt
    
}
