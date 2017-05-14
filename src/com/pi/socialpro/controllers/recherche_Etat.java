package com.pi.socialpro.controllers;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

 
public class recherche_Etat {
    
   public static String et="";
    Form recherche ;
   
    public recherche_Etat(Resources theme){
    
     recherche = new Form("Recherche", BoxLayout.y());
     Label n=new Label("\n");
     Label n2=new Label("\n");
      Label l=new Label("Veuillez choisir l'Etat");
      
       ComboBox etats = new ComboBox("");
        etats.addItem("realisee");
        etats.addItem("pas encore realisee");
        etats.addItem("en train de realisation");

         Button rech = new Button("Chercher");
        
         rech.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
             String zer = (String) etats.getSelectedItem();
           System.out.println("************************" + zer);
            
             Res_Recherche rs = new Res_Recherche(theme,zer);
             rs.getF().show();
         }
     });
        recherche.add(n).add(etats).add(n2).add(rech);
        recherche.show();
        
         recherche.getToolbar().addCommandToSideMenu("Acceuil", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           Acceuil_chef about = new Acceuil_chef(theme);
           about.getF().show();
            }
        });
          recherche.getToolbar().addCommandToSideMenu("Ajouter Tâche", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
          AjouterTache rech = new  AjouterTache(theme);
           rech.getF().show();
            }
        });
        
          recherche.getToolbar().addCommandToSideMenu("Tâche Afféctées", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           TacheAffecte about = new TacheAffecte(theme);
           about.getF().show();
            }
        });
           
           recherche.getToolbar().addCommandToSideMenu("Tâche Non Afféctées", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           TacheNonAffecte about = new TacheNonAffecte(theme);
           about.getF().show();
            }
        });
           
           
              recherche.getToolbar().addCommandToSideMenu("Tache Non Réalisées", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           TacheNonRealisee about = new TacheNonRealisee(theme);
           about.getF().show();
            }
        });
        recherche.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent evt) {
      Login.userCon=null;
        new Login(theme).getF().show();
         }
     });
        
       
             
     
    }
    public Form getF() {
        return recherche;
    }
}
