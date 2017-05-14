/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;


import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;

import com.codename1.ui.TextField;

import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.pi.socialpro.entites.Panier;
import com.pi.socialpro.entites.Produit;
import com.pi.socialpro.forms.AffichageFormU;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author ahmed
 */
public class AffichageProdUser extends AffichageFormU {

    private Resources theme;
    int num;
ArrayList<Panier> listnumpanier = new ArrayList<>(); 
    public void getAllU() {
        AffichageFormU hii = new AffichageFormU();
    /*    SpanLabel sp = new SpanLabel("");
        hii.add(sp);*/

        
        
        
        hii.getToolbar().addMaterialCommandToSideMenu("Panier Personel", 
        FontImage.MATERIAL_ADD_CIRCLE, e -> new ShowPanier().getPanier());
         hii.getToolbar().addMaterialCommandToSideMenu("Connecte to Gmail", 
        FontImage.MATERIAL_MAIL, e -> new Google().GoogleConnect());
        hii.getToolbar().addMaterialCommandToSideMenu("Logout", 
        FontImage.MATERIAL_LOCK, e -> new Login(theme).getF().show());
    
       
        
        Toolbar.setGlobalToolbar(true);
Style s = UIManager.getInstance().getComponentStyle("Title");
        
        TextField searchField = new TextField("", "Recherche"); // <1>
searchField.getHintLabel().setUIID("Title");
searchField.setUIID("Title");
searchField.getAllStyles().setAlignment(Component.LEFT);
hii.getToolbar().setTitleComponent(searchField);
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH,s);
        
        
        hii.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
         new   RechercheProduit().getAllU(searchField.getText());

});
        
  
        hii.show();
 
        
        
        

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/php/selectProd.php");

        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                for (Produit t : getListProduitF(new String(con.getResponseData()))) {
                    Container c1 = new Container(BoxLayout.x());
                    Container c2 = new Container(BoxLayout.y());

                    hii.add(c1).add(c2);
                    Button add = new Button("add");
                    Label libprod = new Label("Libelle : " + t.getLibelle());
                    Label typeprod = new Label("type Produit : " + t.getTypeproduit());
                    Label prix = new Label("prix : " + t.getPrix()+" $");
                    Label quantite = new Label("quanite" + t.getQuantite());
                Label plusinfo = new Label ("plus d'information + ");
                    c2.add(createLineSeparator(0xeeeeee));
                    ImageViewer img = new ImageViewer();

                    Image ma = null;
                    try {
                String x = "";
                x="file://"+t.getPath();
                        ma = Image.createImage(x);

                    } catch (IOException ex) {

                    }
                    img.setImage(ma);

                    plusinfo.addPointerPressedListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Dialog.show("Produit Information", "id : " + t.getId() + "rating" + t.getRating2() + "Lib : " + t.getLibelle() + "\n Description : " + t.getDescription(), "OK", null);

                        }

                    });

                    hii.refreshTheme();

                    
                    
                 FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_SHOPPING_CART);
                 fab.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            ConnectionRequest req = new ConnectionRequest();
                            int iduser=0;
                       iduser = Login.userCon.getId();
                       
                            req.setUrl("http://localhost/php/insertPanier.php?produit=" + t.getId() +"&user="+iduser+ "");
                        
               req.addResponseListener(new ActionListener<NetworkEvent>() {

                                @Override
                                public void actionPerformed(NetworkEvent evt) {
                                    ToastBar.showMessage("Produit Ajouter au panier", FontImage.MATERIAL_INFO);

                                }
                            });

                            NetworkManager.getInstance().addToQueue(req);

                        }

                    });
                    c2.add(img).add(libprod).add(typeprod).add(prix).add(plusinfo);

                    c2.add(fab);

                 
                    
                    
                 /*boucle for produit*/                }

            }

        });
        NetworkManager.getInstance().addToQueue(con);

    }

    
     
        
                  
                  

     /*   conn.addResponseListener(new ActionListener<NetworkEvent>() {

           @Override
            public void actionPerformed(NetworkEvent evt) {

                for (Panier p : GetPanierByetat(new String(conn.getResponseData()))) {

                    num = p.getNumero() + 1;

                    System.out.println("nnn" + num);
         

                    
                    
                    
                    
                    
     }
            }
        });

        NetworkManager.getInstance().addToQueue(conn);   
    
    
  
    
    
    
    */
    
    
    
    
    
    
    
    
    
    
    
    
    
    public ArrayList<Panier> GetPanierByetat(String json) {
        ArrayList<Panier> listnumpanier = new ArrayList<>();
   ConnectionRequest conn = new ConnectionRequest();
        conn.setUrl("http://localhost/php/GetPanierByEtat.php");
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> paniers = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) paniers.get("panier");
            listnumpanier.clear();

            for (Map<String, Object> obj : list) {

                Panier e = new Panier();
                e.getNumero();
           
                e.setNumero(Integer.parseInt(obj.get("numero").toString()));
                listnumpanier.add(e);
            }
        } catch (IOException ex) {

        }
   NetworkManager.getInstance().addToQueue(conn);  
        return listnumpanier;

    }

 
    public ArrayList<Produit> getListProduitF(String json) {
        ArrayList<Produit> listproduit = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> produits = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) produits.get("produit");
            listproduit.clear();

            for (Map<String, Object> obj : list) {
                Produit e = new Produit();
                e.setId(Integer.parseInt(obj.get("id").toString()));
                e.setLibelle(obj.get("libelle").toString());
                e.setTypeproduit(obj.get("typeproduit").toString());
                e.setPrix(Double.parseDouble(obj.get("prix").toString()));
                e.setPath(obj.get("path").toString());
                e.setDescription(obj.get("description").toString());
                listproduit.add(e);
            }

        } catch (IOException ex) {
        }
        return listproduit;
    }

}
