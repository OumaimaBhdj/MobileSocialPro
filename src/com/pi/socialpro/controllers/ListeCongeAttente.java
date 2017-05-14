
package com.pi.socialpro.controllers;

import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.pi.socialpro.entites.Conge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Yass
 */
public class ListeCongeAttente {

    Form listecongeAt;

    public ListeCongeAttente(Resources theme) {

        listecongeAt = new Form("Liste des congés en attente", BoxLayout.y());

        /* listeconge.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
         authentification login =  new authentification(theme);
         login.getF().showBack();
         }
         });*/
        SpanLabel sp = new SpanLabel();
        listecongeAt.add(sp);
     // int iduser = 0;
        //  iduser = Login.userCon.getId();
        ConnectionRequest con = new ConnectionRequest();
       
        con.setUrl("http://localhost/social_pro/Attente.php");
            con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                for (Conge t : getListConges(new String(con.getResponseData()))) {
                    Container c1 = new Container(BoxLayout.x());
                    Container c2 = new Container(BoxLayout.y());

                    Button supp = new Button("Supprimer");

                    supp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            ConnectionRequest req = new ConnectionRequest();

                            req.setUrl("http://localhost/social_pro/supprimerConge.php?id=" + t.getId() + "");
                            NetworkManager.getInstance().addToQueue(req);

                            c2.setLeadComponent(supp);
                            Dialog d = new Dialog();

                            if (Dialog.show("Confirmation", "Conge supprmier avec succes", "Ok", null)) {
                                new SupprimerConge().Supp(t);
                            }
                        }
                    });
                    Label db = new Label("date début : " + t.getDatedebut());
                    Label nbr = new Label(" nombre de jours : " + t.getNbrjours());
                    Label type = new Label(" type du congé: " + t.getType_conge());
                    Label res = new Label(" Raison : " + t.getRaison());
                 //   Label us = new Label(" Raison : " + t.getUsername());
                    Label et = new Label("etat :");
                    ComboBox eta = new ComboBox(t.getEtat());
                    eta.addItem("En attente");
                    eta.addItem("Accepter");
                    eta.addItem("Refuser");
                  

                    Label sep = new Label("================================================");
                    c2.add(db).add(nbr).add(type).add(res).add(et).add(eta).add(supp).add(sep);
                    c1.add(c2);
                    listecongeAt.add(c1);
                    

                    listecongeAt.refreshTheme();
                     eta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ConnectionRequest req = new ConnectionRequest();

                req.setUrl("http://localhost/social_pro/UpdateEtatConge.php?etat=" + eta.getSelectedItem() + "&id=" + t.getId());

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (!s.equals("Error")) {

                            Dialog.show("Confirmation", "Demande traitée avec succée", "Ok", null);

                        } else {
                            Dialog.show("Confirmation", "Traitemet failed", "Ok", null);
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
            }
        });

                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);
        
          listecongeAt.getToolbar().addMaterialCommandToSideMenu("Acceuil", 
                FontImage.MATERIAL_DASHBOARD, e -> new Acceuil_chef(theme).getF().show());
     
          listecongeAt.getToolbar().addMaterialCommandToSideMenu("Envoyer un e-mail", 
                FontImage.MATERIAL_MAIL, e -> new Mailling(theme).getF().show());
        listecongeAt.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

           
         @Override
         public void actionPerformed(ActionEvent evt) {
      Login.userCon=null;
        new Login(theme).getF().show();
            }
        });

    }

    public ArrayList<Conge> getListConges(String json) {
        ArrayList<Conge> listconge = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();
            Map<String, Object> conge = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) conge.get("conge");
            Date db;
            for (Map<String, Object> obj : list) {
                Conge e = new Conge();
                e.setId(Integer.parseInt(obj.get("id").toString()));
                e.setDatedebut(obj.get("datedebut").toString());
                e.setNbrjours(Integer.parseInt(obj.get("nbrjours").toString()));
                e.setType_conge(obj.get("type_conge").toString());
                e.setRaison(obj.get("raison").toString());
                e.setEtat(obj.get("etat").toString());
              

                listconge.add(e);
            }

        } catch (IOException ex) {
        }
        return listconge;

    }

    public Form getF() {
        return listecongeAt;
    }

    public void setF(Form f) {
        this.listecongeAt = f;
    }

    public void show() {

        listecongeAt.show();
    }

}
