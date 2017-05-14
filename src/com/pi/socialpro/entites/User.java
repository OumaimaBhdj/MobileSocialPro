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
public class User {
    
    private int id;
    private String nom;
    private String prenom;
    private String sexe;
    private String username;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private int enabled;
    private int enable;
    private String salt;
    private String password;
    private Date last_login;
    private String confirmation_token;
    private Date password_request_at;
    private String roles;
    private String matricule;
    private String cin;
    private Date datenaissance;
    private int num_telephone;
    private String poste;
    private Date dateembauche;
    private int salaire;
    private String departement;
    private String image;
    
    
    
    private static User instance;

    public static User getInstance() {
        return instance;
    }

    public static void setInstance(User instance) {
        User.instance = instance;
    }
        
      
       public void logout()
    {
        instance=null;
    }

    public User() {
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
//constructeur l'affichage 
    public User(int id,String nom, String prenom, String email, int num_telephone, int enable, String cin, Date datenaissance, String poste, String departement, String roles) {
        this.id = id;
        this.nom=nom;
        this.prenom = prenom;
        this.email = email;
        this.enable = enable;
        this.cin = cin;
        this.datenaissance = datenaissance;
        this.num_telephone = num_telephone;
        this.poste = poste;
        this.departement = departement;
        this.roles=roles;
    }
    
    
    

    public User(String nom,String prenom,String cin, String sexe, String username, String username_canonical, String email, String email_canonical, String password, Date datenaissance, int num_telephone, String poste, String departement,String roles,String image) {
        this.nom = nom;
        this.prenom=prenom;
        this.sexe = sexe;
        this.cin=cin;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        
        this.datenaissance = datenaissance;
        this.num_telephone = num_telephone;
        this.poste = poste;
        this.departement = departement;
        this.roles=roles;
        this.image=image;
    }

    
    
    public User(int id, String username, String email, int enabled, int num_telephone, String poste, String departement) {
        this.id=id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.num_telephone = num_telephone;
        this.poste = poste;
        this.departement = departement;
    }
    
    

    public User(String username, String email, String password, String roles, String matricule, String cin, Date datenaissance, int num_telephone, String poste, Date dateembauche, int salaire, String departement) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.matricule = matricule;
        this.cin = cin;
        this.datenaissance = datenaissance;
        this.num_telephone = num_telephone;
        this.poste = poste;
        this.dateembauche = dateembauche;
        this.salaire = salaire;
        this.departement = departement;
    }

    public User(String username,String username_canonical, String email, String email_canonical, String password, Date datenaissance,Date dateembauche,String cin, int num_telephone, String poste, String departement) {
        this.username = username;
        this.username_canonical=username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.datenaissance = datenaissance;
        this.dateembauche = dateembauche;
        this.cin = cin;
        this.num_telephone = num_telephone;
        this.poste = poste;
        this.departement = departement;
    }

    public User(String username, String username_canonical, String email, String email_canonical, String password, Date datenaissance, int num_telephone, String poste, String departement) {
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.datenaissance = datenaissance;
        this.num_telephone = num_telephone;
        this.poste = poste;
        this.departement = departement;
    }

    public User(String username, String username_canonical, String email, String email_canonical, String password,Date datenaissance,int num_telephone,  String poste, String departement, String roles) {
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
       
        this.datenaissance = datenaissance;
        this.num_telephone = num_telephone;
        this.poste = poste;
        this.departement = departement;
         this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", enabled=" + enabled + ", enable=" + enable + ", salt=" + salt + ", password=" + password + ", last_login=" + last_login + ", confirmation_token=" + confirmation_token + ", password_request_at=" + password_request_at + ", roles=" + roles + ", matricule=" + matricule + ", cin=" + cin + ", datenaissance=" + datenaissance + ", num_telephone=" + num_telephone + ", poste=" + poste + ", dateembauche=" + dateembauche + ", salaire=" + salaire + ", departement=" + departement + ", image=" + image + '}';
    }

    
   
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public Date getPassword_request_at() {
        return password_request_at;
    }

    public void setPassword_request_at(Date password_request_at) {
        this.password_request_at = password_request_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public int getNum_telephone() {
        return num_telephone;
    }

    public void setNum_telephone(int num_telephone) {
        this.num_telephone = num_telephone;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Date getDateembauche() {
        return dateembauche;
    }

    public void setDateembauche(Date dateembauche) {
        this.dateembauche = dateembauche;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }
   
    
    
    
    
}
