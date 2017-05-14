
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
import com.pi.socialpro.entites.Tache;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



public class Res_Recherche {
    Form res_recherche ;
    public Res_Recherche(Resources theme,String nom)
    {
    res_recherche = new Form("Resultat Recherche", BoxLayout.y());
      
     /* res_recherche.getToolbar().addCommandToLeftBar(null,theme.getImage("cal_left_arrow.png"),(a)->{
               recherche_Etat t= new recherche_Etat(theme);
               t.getF().showBack();
            });*/
    
    res_recherche.getToolbar().addCommandToLeftBar(null,theme.getImage("cal_left_arrow.png"),(a)->{
               recherche_Etat t= new recherche_Etat(theme);
               t.getF().showBack();
            });
         
     
     res_recherche.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {
        
         @Override
         public void actionPerformed(ActionEvent evt) {
      Login.userCon=null;
        new Login(theme).getF().show();
         }
     });
            
    
     ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://172.16.164.66/social_pro/recherche.php?etat='" + nom +"'");
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                
             
                
                
                for (Tache t : getListTache(new String(con.getResponseData())))
                 {
                     Container c1 = new Container(BoxLayout.x());
                        Container c2 = new Container(BoxLayout.y());                   
                        Label db = new Label("date début : "+t.getDatedebut());
                        Label df = new Label("date fin : "+t.getDatefin());
                        SpanLabel desc = new SpanLabel("Description : "+t.getDescription());
                         Label user = new Label("Employée : "+t.getUsername());
                         Label sep = new Label("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
                        c2.add(db).add(df).add(desc).add(user).add(sep);
                         c1.add(c2);
                res_recherche.add(c1);
               res_recherche.refreshTheme(); 
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
        return res_recherche;
    }
}
