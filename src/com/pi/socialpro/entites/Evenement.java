/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.entites;

import java.util.Date;

/**
 *
 * @author Oumaima
 */
public class Evenement {
    
    private int id;
    private String nom; 
    private String categorie;
    private String description;
    private Date datedebut;
    private Date datefin;
    private float lon;
    private float lat;
    private int nbrelimite;
    private int nbrparticipants;
    private int pourcentage;
    private int flag;
    private String image_name;
    private Date updated_at; 
    private int user_id;
    private String lieu;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    
    
      public Evenement( int id,String categorie,String nom, String description, int nbrelimite, Date datedebut, Date datefin, String lieu) {
        this.id=id;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.nbrelimite = nbrelimite;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.lieu=lieu;
        
    }

    public Evenement(String nom, String categorie, Date datedebut, Date datefin, String description, int nbrelimite, String lieu,  String image) {
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.nbrelimite = nbrelimite;
        this.lieu = lieu;
        this.image = image;
    }
      
      
      
    
        public Evenement( int id,String categorie,String nom, String description, int nbrelimite,int nbrparticipants , Date datedebut, Date datefin, int pourcentage) {
        this.id=id;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.nbrelimite = nbrelimite;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.nbrparticipants=nbrparticipants;
        this.pourcentage=pourcentage;
        
    }
        
        public Evenement( int id,String categorie,String nom, String description, int nbrelimite,int nbrparticipants , Date datedebut, Date datefin, String lieu) {
        this.id=id;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.nbrelimite = nbrelimite;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.nbrparticipants=nbrparticipants;
        this.lieu=lieu;
        
    }
        

    public Evenement( int id,String categorie,String nom, String description, int nbrelimite, Date datedebut, Date datefin,String lieu, int flag,String image) {
        this.id=id;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.nbrelimite = nbrelimite;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.flag= flag;
        this.lieu=lieu;
        this.image=image;
    }
    
    
    

    public Evenement() {
    }

    public Evenement(String nom, String categorie) {
        this.nom = nom;
        this.categorie = categorie;
    }
    
    

    public Evenement( String nom, String categorie, Date datedebut, Date datefin,  String description, int nbrelimite,String lieu) {
        
       
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.nbrelimite = nbrelimite;
        this.lieu=lieu;
        
    }


    public Evenement( String nom, String categorie, Date datedebut, Date datefin,  String description, int nbrelimite) {
      
        this.nom = nom;
        this.categorie = categorie;
      
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        this.nbrelimite = nbrelimite;
     
    }

    @Override
    public String toString() {
        return "Evenement{" + "nom=" + nom + ", categorie=" + categorie + ", description=" + description + ", datedebut=" + datedebut + ", datefin=" + datefin + ", nbrelimite=" + nbrelimite + '}';
    }

   
    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public int getNbrelimite() {
        return nbrelimite;
    }

    public void setNbrelimite(int nbrelimite) {
        this.nbrelimite = nbrelimite;
    }

    public int getNbrparticipants() {
        return nbrparticipants;
    }

    public void setNbrparticipants(int nbrparticipants) {
        this.nbrparticipants = nbrparticipants;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    
    
    
    
    
}
