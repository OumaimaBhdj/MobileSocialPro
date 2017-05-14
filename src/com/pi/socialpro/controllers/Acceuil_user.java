package com.pi.socialpro.controllers;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


public class Acceuil_user {
    
    
    
    Form acc_user;
    public Acceuil_user(Resources theme)
    {
        
         acc_user = new Form("Bienvenu Sur SocialPro", BoxLayout.y());
         Container c1 = new Container(BoxLayout.y());
         Button profil = new Button("Profil");
         Button btnConge = new Button("Gestion des Congés");
         Button btnTache = new Button("Gestion des Taches");
         Button btnpub = new Button("Gestion des Publications");
         Button btnproduit = new Button("Gestion des Produits");
         Button btnevenement = new Button("Gestion des Evenements");
         c1.add(profil).add(btnConge).add(btnTache).add(btnproduit).add(btnevenement).add(btnpub);
         acc_user.add(c1);
         acc_user.show();
         
         btnpub.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 new AffichagePublication().getAll();
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
         
         btnevenement.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 new EventsList(theme).start();
                 // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
         
         profil.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 new Profil(theme).getF().show();
               //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
         
         btnConge.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent evt) {
               ListeConge l = new ListeConge(theme);
               l.getF().show();
             }
         });
         
          btnproduit.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent evt) {
             new AffichageProdUser().getAllU();
             }
         });
         
         btnTache.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent evt) {
               TacheLoggedUser l = new TacheLoggedUser(theme);
               l.getF().show();
             }
         });
    }
    
    public Form getF() {
        return acc_user;
    }
}
