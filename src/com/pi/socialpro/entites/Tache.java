package com.pi.socialpro.entites;

import java.util.Date;
public class Tache {
     private int id;
    private String datedebut;
    private String datefin;
    private String description;
    private String etat;
    private int user_id;
    private String username;
 
    
    public Tache(){}

    public Tache(int id, String datedebut, String datefin, String description) {
        this.id = id;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
    }

    public Tache(int id, String description) {
        this.id = id;
        this.description = description;
    }
    public int getId() {
        return id;
    }

   public String getDatedebut() {
        return datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public String getDescription() {
        return description;
    }

    public String getEtat() {
        return etat;
    }

    public void setId(int id) {
        this.id = id;
    }

   public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }
 
    
  
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Tache{" + "id=" + id + ", datedebut=" + datedebut + ", datefin=" + datefin + ", description=" + description + ", etat=" + etat + ", user_id=" + user_id + ", username=" + username + '}';
    }

   
    
  
    
}
