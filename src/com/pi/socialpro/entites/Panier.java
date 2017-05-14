/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pi.socialpro.entites;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Navoxx
 */
public class Panier {
    
    
 private int id ;
  private  List<Produit> Listproduit=new ArrayList<>();
  private int numero;
   private String etat;
  private int user ;
  private int produit;

    public Panier(int id, int numero, String etat, int user, int produit) {
        this.id = id;
        this.numero = numero;
        this.etat = etat;
        this.user = user;
        this.produit = produit;
    }
  
  

    public Panier() {
    }

    public Panier(int numero, int produit) {
        this.numero = numero;
        this.produit = produit;
    }

    
    public Panier(int id, int numero, int produit) {
        this.id = id;
        this.numero = numero;
        this.produit = produit;
    }

    public Panier(int numero, String etat, int produit) {
        this.numero = numero;
        this.etat = etat;
        this.produit = produit;
    }

    public Panier(String etat) {
        this.etat = etat;
    }

    
    
    
    public Panier(int id, int numero, String etat, int user) {
        this.id = id;
        this.numero = numero;
        this.etat = etat;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Produit> getListproduit() {
        return Listproduit;
    }

    public void setListproduit(List<Produit> Listproduit) {
        this.Listproduit = Listproduit;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    
    
    
    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", Listproduit=" + Listproduit + ", numero=" + numero + ", etat=" + etat + ", user=" + user + '}';
    }
  
  
    
    
}
