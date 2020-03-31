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
public class Evenement {
        private int id;
    private String nomEvent;
    private String adresse;
    private String type;
    private float prix;
    private int nbPlaces;
    private String description;
    private Timestamp dateDebut;
    private String image;
    private Timestamp dateFin;
    private String latlng;

    public Evenement() {
    }
    

    public Evenement(String nomEvent, String adresse, String type, float prix, int nbPlaces, String description, Timestamp dateDebut, String image, Timestamp dateFin, String latlng) {
        this.nomEvent = nomEvent;
        this.adresse = adresse;
        this.type = type;
        this.prix = prix;
        this.nbPlaces = nbPlaces;
        this.description = description;
        this.dateDebut = dateDebut;
        this.image = image;
        this.dateFin = dateFin;
        this.latlng = latlng;
    }


    public Evenement(int id, String nomEvent, String adresse, String type, float prix, int nbPlaces, String description, Timestamp dateDebut, String image, Timestamp dateFin, String latlng) {
        this.id = id;
        this.nomEvent = nomEvent;
        this.adresse = adresse;
        this.type = type;
        this.prix = prix;
        this.nbPlaces = nbPlaces;
        this.description = description;
        this.dateDebut = dateDebut;
        this.image = image;
        this.dateFin = dateFin;
        this.latlng = latlng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    public String getLatlng() {
        return latlng;
    }

    public void setLatlng(String latlng) {
        this.latlng = latlng;
    }

    @Override
    public String toString() {
        return  id + ", nomEvent=" + nomEvent + ", adresse=" + adresse + ", type=" + type + ", prix=" + prix + ", nbPlaces=" + nbPlaces + ", description=" + description + ", dateDebut=" + dateDebut + ", image=" + image + ", dateFin=" + dateFin + ", latlng=" + latlng + '}';
    }
    
    
    
}
