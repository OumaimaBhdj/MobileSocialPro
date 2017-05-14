/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social_pro.entites;

/**
 *
 * @author ahmed
 */
public class JaimePas {
     int id;
    int idUser;
    int idPublication;

    public JaimePas(int idUser, int idPublication) {
        this.idUser = idUser;
        this.idPublication = idPublication;
    }
 public JaimePas(int id,int idUser, int idPublication) {
     this.id=id;
        this.idUser = idUser;
        this.idPublication = idPublication;
    }
    public JaimePas() {
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
