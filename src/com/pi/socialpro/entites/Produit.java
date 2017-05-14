/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pi.socialpro.entites;

import java.util.Date;

/**
 *
 * @author Navoxx
 */
public class Produit {
   private int id ;
  private  String typeproduit;
  private String libelle;
  private double prix ;
  private String description;
  private int quantite;
  private String path;
  private Date datedebut;
  private Date datefin;
  private int rating;
  private double rating2;

    public Produit(int id, String typeproduit, String libelle, double prix, String path, double rating2) {
        this.id = id;
        this.typeproduit = typeproduit;
        this.libelle = libelle;
        this.prix = prix;
        this.path = path;
        this.rating2 = rating2;
    }
  
  
  
  
  
 

    public Produit() {
    }

    public Produit( int id ,String typeproduit, String libelle, double prix, int quantite, String path) {
        this.id=id;
        this.typeproduit = typeproduit;
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
        this.path = path;
    }

    
    
    public Produit(int id, String libelle, double prix, int quantite) {
        this.id = id;
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Produit(String typeproduit, String libelle, double prix) {
        this.typeproduit = typeproduit;
        this.libelle = libelle;
        this.prix = prix;
    }

    
    
    
    public Produit(String typeproduit, String libelle, double prix, int quantite) {
        this.typeproduit = typeproduit;
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Produit(int id, String typeproduit, String libelle, double prix, int quantite) {
        this.id = id;
        this.typeproduit = typeproduit;
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Produit(int id, String typeproduit, String libelle, double prix, String description, int quantite) {
         this.id = id;
        this.typeproduit = typeproduit;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.quantite = quantite;
    }

    public Produit(String typeproduit) {
        this.typeproduit = typeproduit;
    }

    public Produit(int id, String typeproduit, String libelle, double prix, String description, int quantite, String path, Date datedebut, Date datefin, int rating) {
        this.id = id;
        this.typeproduit = typeproduit;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.quantite = quantite;
        this.path = path;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeproduit() {
        return typeproduit;
    }

    public void setTypeproduit(String typeproduit) {
        this.typeproduit = typeproduit;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getRating2() {
        return rating2;
    }

    public void setRating2(double rating2) {
        this.rating2 = rating2;
    }

    public Produit(int id, String typeproduit, String libelle, double prix, int quantite, String path, double rating2) {
        this.id = id;
        this.typeproduit = typeproduit;
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
        this.path = path;
        this.rating2 = rating2;
    }

    public Produit(double prix) {
        this.prix = prix;
    }

    
  
    
    
    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", typeproduit=" + typeproduit + ", libelle=" + libelle + ", prix=" + prix + ", description=" + description + ", quantite=" + quantite + ", path=" + path + ", datedebut=" + datedebut + ", datefin=" + datefin + ", rating=" + rating + '}';
    }
  
    
    
}
