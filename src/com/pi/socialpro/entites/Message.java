package com.pi.socialpro.entites;

public class Message {
    
    private int id;
    private String objet;
    private String text;
    private int user_id;
    private String nom_user;
    private String datedenvoye;
    
    public Message(){}

    

   

    public int getId() {
        return id;
    }

    public String getObjet() {
        return objet;
    }

    public String getText() {
        return text;
    }

    public int getEmploye_id() {
        return user_id;
    }

    public String getNom_user() {
        return nom_user;
    }

    public String getDatedenvoye() {
        return datedenvoye;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public void setDatedenvoye(String datedenvoye) {
        this.datedenvoye = datedenvoye;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", objet=" + objet + ", text=" + text + ", user_id=" + user_id + ", nom_user=" + nom_user + ", datedenvoye=" + datedenvoye + '}';
    }
    
    
    
    
}
