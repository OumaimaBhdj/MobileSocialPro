
package com.pi.socialpro.controllers;

import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.pi.socialpro.entites.Tache;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class TacheNonAffecte {
    
     Form TacheNonAffecte;
    public TacheNonAffecte(Resources theme)
    {
   
        TacheNonAffecte = new Form("Tâches Non Afféctées", BoxLayout.y());
        
         TacheNonAffecte.getToolbar().addCommandToSideMenu("Acceuil", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           Acceuil_chef about = new Acceuil_chef(theme);
           about.getF().show();
            }
        });
     
     TacheNonAffecte.getToolbar().addCommandToSideMenu("Ajouter Tâche", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           AjouterTache about = new AjouterTache(theme);
           about.getF().show();
            }
        });
     
       TacheNonAffecte.getToolbar().addCommandToSideMenu("Tâches Afféctées", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
           TacheAffecte about = new TacheAffecte(theme);
           about.getF().show();
            }
        });
       
      
       

       TacheNonAffecte.getToolbar().addCommandToSideMenu("Taches Non Réalisées", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           TacheNonRealisee about = new TacheNonRealisee(theme);
           about.getF().show();
            }
        });
        TacheNonAffecte.getToolbar().addCommandToSideMenu("Recherche", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
           recherche_Etat about = new recherche_Etat(theme);
           about.getF().show();
            }
        });
     
     
      TacheNonAffecte.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         
         @Override
         public void actionPerformed(ActionEvent evt) {
      Login.userCon=null;
        new Login(theme).getF().show();
         }
     });
     
     SpanLabel sp = new SpanLabel();
      TacheNonAffecte.add(sp);
      
      ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/social_pro/nonAff.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                //System.out.println("aaaa"+getListTache(new String(con.getResponseData())));
             
                
                
                for (Tache t : getListTache(new String(con.getResponseData())))
                 {
                     Container c1 = new Container(BoxLayout.x());
                        Container c2 = new Container(BoxLayout.y());
                        
                        Button supp = new Button("Supprimer");
                        supp.addActionListener(new ActionListener() {

                         @Override
                         public void actionPerformed(ActionEvent evt) {
                       ConnectionRequest req = new ConnectionRequest();
                       req.setUrl("http://localhost/social_pro/delete.php?id="+t.getId()+"");
                       NetworkManager.getInstance().addToQueue(req);
                       
                        Dialog d = new Dialog();
                             
                             if(  
                                 Dialog.show("Confirmation", "Tâche supprmier avec succes", "Ok", null)){
                                 new supprimer().SuppTNAF(t);
                             }
                       
                         }
                     });
                        Label db = new Label("date début : "+t.getDatedebut());
                        Label df = new Label("date fin : "+t.getDatefin());
                        SpanLabel desc = new SpanLabel("Description : "+t.getDescription());
                         Label sep = new Label("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
                        c2.add(db).add(df).add(desc).add(supp).add(sep);
                         c1.add(c2);
                TacheNonAffecte.add(c1);
                TacheNonAffecte.refreshTheme(); 
                 }   
                
              
            }
        });
        NetworkManager.getInstance().addToQueue(con);
    }

    public ArrayList<Tache> getListTache(String json) {
        ArrayList<Tache> listTaches = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();
            Map<String, Object> taches = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) taches.get("tache");
            Date db;
            for (Map<String, Object> obj : list) {
                Tache e = new Tache();
                
                e.setId(Integer.parseInt(obj.get("id").toString()));
                e.setDatedebut(obj.get("datedebut").toString());
                e.setDatefin(obj.get("datefin").toString());
                e.setDescription(obj.get("description").toString());
                listTaches.add(e);
            }

        } catch (IOException ex) {
         }
        return listTaches;
    }
    
    public Form getF() {
        return TacheNonAffecte;
    }

    public void setF(Form f) {
        this.TacheNonAffecte = f;
    }
    
    
    
    public void show(){
     
      TacheNonAffecte.show(); 
 }
    
}
