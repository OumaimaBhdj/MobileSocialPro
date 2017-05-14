/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.entites;


/**
 *
 * @author Yass
 */
public class Conge {
     private int id;
    private String datedebut;
    private int nbrjours;
    private String raison;
    private String type_conge;
    private String etat;
   private int user;
   private String username;

    public Conge() {
    }

    public Conge(String datedebut, int nbrjours,String type_conge, String raison, String etat) {
        this.datedebut = datedebut;
        this.nbrjours = nbrjours;
         this.type_conge = type_conge;
        this.raison = raison;
        this.etat = etat;
    }

    public Conge(int id,  String datedebut, int nbrjours, String type_conge,String raison, String etat, int user) {
       this.id = id;
        this.datedebut = datedebut;
        this.nbrjours = nbrjours;
        this.raison = raison;
        this.type_conge = type_conge;
        this.etat = etat;
        this.user = user;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Conge(int id, String datedebut, int nbrjours, String type_conge,String raison) {
        this.id = id;
        this.datedebut = datedebut;
        this.nbrjours = nbrjours;
        this.raison = raison;
        this.type_conge = type_conge;
        
    }

    public Conge(String datedebut, int nbrjours,String type_conge, String raison) {
        
        this.datedebut = datedebut;
        this.nbrjours = nbrjours;
        this.raison = raison;
        this.type_conge = type_conge;
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datebedut) {
        this.datedebut = datebedut;
    }

    public int getNbrjours() {
        return nbrjours;
    }

    public void setNbrjours(int nbrjours) {
        this.nbrjours = nbrjours;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getType_conge() {
        return type_conge;
    }

    public void setType_conge(String type_conge) {
        this.type_conge = type_conge;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Conge{" + "id=" + id + ", datebedut=" + datedebut + ", nbrjours=" + nbrjours + ", raison=" + raison + ", type_conge=" + type_conge + ", user=" + user + '}';
    }

   /* public ObservableList<String> getType_conge(ObservableList<String> options) {
              return options;
    }*/

    public Conge(int id, String datedebut, int nbrjours, String type_conge,String raison, String etat) {
        this.id = id;
        this.datedebut = datedebut;
        this.nbrjours = nbrjours;
        this.raison = raison;
        this.type_conge = type_conge;
        this.etat = etat;
    }

    public Conge(String etat) {
        this.etat = etat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    

    
    
}
