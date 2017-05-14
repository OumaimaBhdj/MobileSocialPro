package com.pi.socialpro.controllers;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class Acceuil_chef {

    Form acc;

    public Acceuil_chef(Resources theme) {
        acc = new Form("Acceuil", BoxLayout.y());
        
         Container c1 = new Container(BoxLayout.y());
         
         Button btnConge = new Button("Gestion des Congés");
         Button btnTache = new Button("Gestion des Tache");
         
         c1.add(btnConge).add(btnTache);
         acc.add(c1);
         
         btnConge.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               ListeCongeAttente conge = new ListeCongeAttente(theme);
              conge.getF().show();
              
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
