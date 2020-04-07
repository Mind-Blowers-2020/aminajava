/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.huntkingdom.entities;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author asus
 */
public class Participant {
    int id;
    Boolean confiramtion;
    int evenment;
    Timestamp date_inscrit;
    String nom;
    String prenom;
    String nomEvent;

    public Participant(int id, Boolean confiramtion, int evenment, Timestamp date_inscrit, String nom, String prenom) {
        this.id = id;
        this.confiramtion = confiramtion;
        this.evenment = evenment;
        this.date_inscrit = date_inscrit;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Participant(String nom, int evenement) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    this.nom=nom;
    this.evenment=evenement;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getConfiramtion() {
        return confiramtion;
    }

    public void setConfiramtion(Boolean confiramtion) {
        this.confiramtion = confiramtion;
    }

    public int getEvenment() {
        return evenment;
    }

    public void setEvenment(int evenment) {
        this.evenment = evenment;
    }

    public Timestamp getDate_inscrit() {
        return date_inscrit;
    }

    public void setDate_inscrit(Timestamp date_inscrit) {
        this.date_inscrit = date_inscrit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Participant{" + "id=" + id + ", confiramtion=" + confiramtion + ", evenment=" + evenment + ", date_inscrit=" + date_inscrit + ", nom=" + nom + ", prenom=" + prenom + '}';
    }
    
    
}
