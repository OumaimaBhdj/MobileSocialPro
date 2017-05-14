/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;

import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
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
public class UpdateConge {

    Form congeupd;

    UpdateConge(Resources theme,Conge c) {
      /*  UIBuilder ui = new UIBuilder();
        congeupd = ui.createContainer(theme, "UpdateConge").getComponentForm();*/
          congeupd = new Form("Modifier un congé", BoxLayout.y());
 
        Container modifier = new Container(BoxLayout.y());
        Label db = new Label("Date Début");
        Picker datedebut = new Picker();
        String df = c.getDatedebut();
        
        datedebut.setType(Display.PICKER_TYPE_DATE);

        Label nbr = new Label("Nombre de jours");
        TextField nbrj = new TextField("" + c.getNbrjours(), "Nombre de jours", 20, TextField.NUMERIC);
        
        
        Label et = new Label("Type du congé");
        ComboBox type = new ComboBox(c.getType_conge());
        type.addItem("Repos");
        type.addItem("Maladie");
        type.addItem("Maternite");

        Label res = new Label("Raison");
        TextField raison = new TextField(c.getRaison(), "raison");

        Button btnOk = new Button("modifier");
        modifier.add(db).add(datedebut).add(nbr).add(nbrj).add(et).add(type).add(res).add(raison);
        congeupd.add(modifier).add(btnOk);

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ConnectionRequest req = new ConnectionRequest();

                req.setUrl("http://localhost/social_pro/updateConge.php?datedebut=" + datedebut.getText() + "&nbrjours=" + nbrj.getText() + "&type_conge=" + type.getSelectedItem() + "&raison=" + raison.getText()+"&id="+c.getId());

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (!s.equals("Error")) {

                            Dialog.show("Confirmation", "update ok", "Ok", null);

                        } else {
                            Dialog.show("Confirmation", "update failed", "Ok", null);
                        }
                    }
                    
                    
                });
 
                NetworkManager.getInstance().addToQueue(req);
                new ListeConge(theme).getF().show();
            }
        });
        
          congeupd.getToolbar().addMaterialCommandToSideMenu("Acceuil", 
                FontImage.MATERIAL_DASHBOARD, e -> new Acceuil_user(theme).getF().show());
               congeupd.getToolbar().addMaterialCommandToSideMenu("Demander de conge", 
                FontImage.MATERIAL_ADD_CIRCLE, e -> new Ajouterconge(theme).getF().show());
              congeupd.getToolbar().addMaterialCommandToSideMenu("Liste des congés", 
                FontImage.MATERIAL_LIST, e -> new ListeConge(theme).getF().show());
        
        
        congeupd.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

           
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

                listconge.add(e);
            }

        } catch (IOException ex) {
        }
        return listconge;

    }

    public Form getF() {
        return congeupd;
    }

    public void setF(Form f) {
        this.congeupd = f;
    }

    public void show() {

        congeupd.show();
    }
}
