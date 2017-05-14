/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.pi.socialpro.entites.Evenement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GoingEvents {

    Form f;
    List<Evenement> rep;
    private Resources theme;
    public static int id;
       public static Evenement event;


    public GoingEvents(Resources theme) {

        this.theme = theme;
        Toolbar.setGlobalToolbar(true);
//        UIBuilder ui = new UIBuilder();
//        f = ui.createContainer(theme, "EventsList").getComponentForm();

    }

    public void start() {
        f = new Form("Calendrier des évènements", BoxLayout.y());
        ConnectionRequest req = new ConnectionRequest();
        
      
         req.setUrl("http://localhost/socialpromobile/partevents.php?iduser="+Login.userCon.getId());
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                getListEvents(s);

            }
        });

        NetworkManager.getInstance().addToQueue(req);

    }
    

    public ArrayList<Evenement> getListEvents(String json) {
        ArrayList<Evenement> listEtudiants = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("events");

            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();
                e.setId(Integer.parseInt(obj.get("id").toString()));
                e.setNom(obj.get("nom").toString());
                e.setDescription(obj.get("description").toString());
                try {
                    e.setDatedebut(new SimpleDateFormat("dd/MM/yyyy").parse(obj.get("datedebut").toString()));
                    e.setDatefin(new SimpleDateFormat("dd/MM/yyyy").parse(obj.get("datefin").toString()));

                } catch (ParseException ex) {
                    //Logger.getLogger(EventsList.class
                    // .getName()).log(Level.SEVERE, null, ex);
                }
                e.setUser_id(Integer.parseInt(obj.get("user_id").toString()));
                e.setCategorie(obj.get("categorie").toString());
               e.setNbrparticipants(Integer.parseInt(obj.get("nbrparticipants").toString()));
                e.setNbrelimite(Integer.parseInt(obj.get("nbrelimite").toString()));
                              e.setImage(obj.get("image").toString());
                e.setLieu(obj.get("lieu").toString());
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        for (Evenement c : listEtudiants) {
            this.add(c);
        }
        return listEtudiants;

    }

    public void add(Evenement c) {
        Container c1 = new Container(BoxLayout.x());
        ImageViewer v = new ImageViewer(theme.getImage("round.png"));
        if (c.getImage() != null && c.getImage().length() > 0) {
            EncodedImage encoded = EncodedImage.createFromImage(theme.getImage("round.png"), false);

            URLImage urlImage = URLImage.createToStorage(encoded, "test ", c.getImage());
            v.setImage(urlImage);
        }
        
        Container c2 = new Container(BoxLayout.y());
        Label lNom = new Label(c.getNom());
        Label lNum = new Label(c.getLieu());
        
        c2.add(lNom);
        c2.add(lNum);
        c1.add(v);
        c1.add(c2);
        c1.setLeadComponent(lNum);
        lNum.addPointerPressedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               
                 GoingEvents.event = c;
                new ConsultEvent(theme).getF().show();
            }
        });
        f.add(c1);
        
          
        f.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
      Login.userCon=null;
        new Login(theme).getF().show();
         }
     });
         f.getToolbar().addCommandToOverflowMenu("Ajouter évènement", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
     
        new AddEvent(theme).getF().show();
         }
     });
        
       f.getToolbar().addCommandToLeftBar("Back", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
          new EventsList(theme).start();
            }
        });
        f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public List<Evenement> getRep() {
        return rep;
    }

    public void setRep(List<Evenement> rep) {
        this.rep = rep;
    }

    public Resources getTheme() {
        return theme;
    }

    public void setTheme(Resources theme) {
        this.theme = theme;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        GoingEvents.id = id;
    }

}
    
    
