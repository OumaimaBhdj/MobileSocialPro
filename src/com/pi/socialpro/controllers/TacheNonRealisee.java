
package com.pi.socialpro.controllers;

import com.codename1.components.ImageViewer;
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
import com.codename1.ui.TextField;
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
import javafx.scene.image.ImageView;

  
public class TacheNonRealisee {
     Form TacheNrealise;
     public static int id;
     public static Tache tch;
     
     public TacheNonRealisee(Resources theme)
     {
         
          UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class); 
         TacheNrealise= new Form("Tâches Non Réalisées", BoxLayout.y()); 
      TacheNrealise.getToolbar().addCommandToSideMenu("Ajouter Tâche", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           AjouterTache about = new AjouterTache(theme);
           about.getF().show();
            }
        });
      TacheNrealise.getToolbar().addCommandToSideMenu("Acceuil", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           Acceuil_chef about = new Acceuil_chef(theme);
           about.getF().show();
            }
        });
            TacheNrealise.getToolbar().addCommandToSideMenu("Tâches Afféctées", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           TacheAffecte about = new TacheAffecte(theme);
           about.getF().show();
            }
        });
           TacheNrealise.getToolbar().addCommandToSideMenu("Tâches Non Afféctées", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           TacheNonAffecte about = new TacheNonAffecte(theme);
           about.getF().show();
            }
        });
            TacheNrealise.getToolbar().addCommandToSideMenu("Recherche", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
           recherche_Etat about = new recherche_Etat(theme);
           about.getF().show();
            }
        });
           
            TacheNrealise.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         
         @Override
         public void actionPerformed(ActionEvent evt) {
      Login.userCon=null;
        new Login(theme).getF().show();
         }
     });
            
           
      
              
     
      SpanLabel sp = new SpanLabel();
      TacheNrealise.add(sp);
      
      ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/social_pro/NonRealisee.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(getListTache(new String(con.getResponseData())));
                 for (Tache t : getListTache(new String(con.getResponseData())))
                 {
                     Container c1 = new Container(BoxLayout.x());
                        Container c2 = new Container(BoxLayout.y());
                        Container boutton = new Container(BoxLayout.x());
                        Button conv = new Button("Convocation ");
                        conv.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent evt) {
                            
                             tch=t;
                             id=tch.getUser_id();
                             Convocation c = new Convocation(theme);
                             c.getF().show();
                         }
                     });
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
                                 new supprimer().SuppTNR(t);
                             }
                       
                         }
                     });
                        boutton.add(conv).add(supp);
                        Label db = new Label("date début : "+t.getDatedebut());
                        Label df = new Label("date fin : "+t.getDatefin());
                        SpanLabel desc = new SpanLabel("Description : "+t.getDescription());
                        Label username = new Label("Employée : "+t.getUsername());
                         Label sep = new Label("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
                        c2.add(db).add(df).add(desc).add(username).add(boutton).add(sep);
                         c1.add(c2);
                TacheNrealise.add(c1);
                TacheNrealise.refreshTheme();
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
                   e.setUsername(obj.get("username").toString());
                  e.setUser_id(Integer.parseInt(obj.get("user_id").toString()));
                listTaches.add(e);
            }

        } catch (IOException ex) {
         }
        return listTaches;
     }
    
    public Form getF() {
        return TacheNrealise;
    }

    public void setF(Form f) {
        this.TacheNrealise = f;
    }
     
    public void show(){
     
      TacheNrealise.show(); 
 }
     
    
    
}
