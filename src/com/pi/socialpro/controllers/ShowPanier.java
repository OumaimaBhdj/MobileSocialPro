/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;


import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.pi.socialpro.entites.Produit;
import com.pi.socialpro.forms.AffichageFormU;
import com.pi.socialpro.forms.ShowPanierForm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Navoxx
 */
public class ShowPanier   extends AffichageFormU {

    private Resources theme;

    final double somme = 0;

    public void getPanier(){
        ShowPanierForm hi = new ShowPanierForm();

         
        hi.getToolbar().addMaterialCommandToSideMenu("Liste des produits", 
        FontImage.MATERIAL_ADD_CIRCLE, e -> new AffichageProdUser().getAllU());
         hi.getToolbar().addMaterialCommandToSideMenu("Connecte to Gmail", 
        FontImage.MATERIAL_MAIL, e -> new Google().GoogleConnect());
        hi.getToolbar().addMaterialCommandToSideMenu("Logout", 
        FontImage.MATERIAL_LOCK, e -> new Login(theme).getF().show());
        
        hi.show();

        
        
        
        ConnectionRequest con = new ConnectionRequest();
        int iduser = 0;
        iduser = Login.userCon.getId();
        con.setUrl("http://localhost/php/showpanier.php?user=" + iduser + "");

        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                Container c1 = new Container(BoxLayout.x());
                Container c2 = new Container(BoxLayout.y());

                hi.add(c1).add(c2);
                double somme = 0;
                for (Produit t : getListProduitF(new String(con.getResponseData()))) {
                    Label libprod = new Label("Produit : " + t.getLibelle());
               


                    Label prix = new Label("Prix : " + t.getPrix());
                    c2.add(createLineSeparator(0xeeeeee));
                    somme = somme + t.getPrix();
                    c2.add(libprod).add(prix);

                }

                Label sommee = new Label("La somme du Panier est : " + somme+" $");
                sommee.getAllStyles().setFgColor(0xfff00);
                c2.add(sommee);
                hi.refreshTheme();

                Button valider = new Button("Valider");
              
                c2.add(valider);

                valider.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        ConnectionRequest req = new ConnectionRequest();
                        int iduser = 0;
                        iduser = Login.userCon.getId();

                        req.setUrl("http://localhost/php/validerpanier.php?user=" + iduser + "");

                        req.addResponseListener(new ActionListener<NetworkEvent>() {

                            @Override
                            public void actionPerformed(NetworkEvent evt) {
                               byte[] data = (byte[]) evt.getMetaData();
                                            String s = new String(data);

                                            if (!s.equals("Error")) {

                                                Dialog.show("Confirmation", "Votre Panier est valider ", "Ok", null);

                                            } else {
                                                Dialog.show("Confirmation", " failed", "Ok", null);
                                            }
                            hi.refreshTheme();
                                           }
                                           });
                    
                        NetworkManager.getInstance().addToQueue(req);
                        hi.refreshTheme();
                             }

                            });
                        }
                   
    
                   
                });
                NetworkManager.getInstance().addToQueue(con);
            }
        
            public ArrayList<Produit> getListProduitF(String json) {
                ArrayList<Produit> listproduit = new ArrayList<>();

                try {

                    JSONParser j = new JSONParser();

                    Map<String, Object> produits = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    System.out.println();
                    List<Map<String, Object>> list = (List<Map<String, Object>>) produits.get("panier");
                    listproduit.clear();

                    for (Map<String, Object> obj : list) {
                        Produit e = new Produit();

                        e.setLibelle(obj.get("libelle").toString());

                        e.setPrix(Double.parseDouble(obj.get("prix").toString()));

                        listproduit.add(e);
                    }

                } catch (IOException ex) {
                }
                return listproduit;
            }

}