/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.entites;

/**
 *
 * @author ahmed
 */
public class Jaime {
    int id;
    int idUser;
    int idPublication;

    public Jaime(int idUser, int idPublication) {
        this.idUser = idUser;
        this.idPublication = idPublication;
    }
 public Jaime(int id,int idUser, int idPublication) {
     this.id=id;
        this.idUser = idUser;
        this.idPublication = idPublication;
    }
    public Jaime() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }
    
    
    
}
