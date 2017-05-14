/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author asus
 */
public class Acceuil_admin {
    
    
    Form acc;

    public Acceuil_admin(Resources theme) {
        acc = new Form("Acceuil admin", BoxLayout.y());
        
         Container c1 = new Container(BoxLayout.y());
         
         Button btnProd = new Button("Gestion des produit");
         Button btnTache = new Button("Gestion des evenement");
         
         c1.add(btnProd);
         acc.add(c1);
         
         btnProd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           
             new AffichageProduit().getAll();
              
            }
        });
         
         
         btnTache.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
              TacheNonRealisee tch = new TacheNonRealisee(theme);
              tch.getF().show();
            }
        });
         
        
    }
      public Form getF() {
        return acc;
    }


}

    
