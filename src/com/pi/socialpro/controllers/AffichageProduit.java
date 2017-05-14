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
import com.codename1.io.Preferences;
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
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import static com.pi.socialpro.controllers.AjoutProduit.imagename;
import static com.pi.socialpro.controllers.AjoutProduit.imagepath;
import com.pi.socialpro.entites.Produit;
import com.pi.socialpro.forms.AffichageForm;
import com.pi.socialpro.forms.UpdateForm;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ahmed
 */
public class AffichageProduit extends AffichageForm {

    private Resources theme;
    public static Produit ProduitS;

    public void getAll() {
        AffichageForm hi = new AffichageForm();
        SpanLabel sp = new SpanLabel("");
        hi.add(sp);

        UpdateForm f = new UpdateForm();

        hi.getToolbar().addMaterialCommandToSideMenu("Ajouter un produit",
                FontImage.MATERIAL_ADD_CIRCLE, e -> new AjoutProduit().Ajout());

        hi.getToolbar().addMaterialCommandToSideMenu("Statistique", FontImage.MATERIAL_REPORT, e -> new Piechart().createPieChartForm().show());
        /*  hi.getToolbar().addMaterialCommandToSideMenu("Developer Guide", FontImage.MATERIAL_WEB, e -> Display.getInstance().execute("https://www.codenameone.com/files/developer-guide.pdf"));
        hi.getToolbar().addMaterialCommandToSideMenu("JavaDoc (Reference)", FontImage.MATERIAL_WEB, e -> Display.getInstance().execute("https://www.codenameone.com/javadoc/"));
        hi.getToolbar().addMaterialCommandToSideMenu("Source Code", FontImage.MATERIAL_WEB, e -> Display.getInstance().execute("https://github.com/codenameone/KitchenSink"));
         */
        hi.getToolbar().addMaterialCommandToSideMenu("Connecte to Gmail",
                FontImage.MATERIAL_MAIL, e -> new Google().GoogleConnect());

        hi.getToolbar().addMaterialCommandToSideMenu("Logout",
                FontImage.MATERIAL_LOCK, e -> new Login(theme).getF().show());
        hi.getToolbar().addMaterialCommandToSideMenu("About",
                FontImage.MATERIAL_INFO, e -> {
                    Dialog.show("About", "Social Pro est une application multifonction. "
                            + "email : Nadhir.tayachi@esprit.tn", "OK", null);
                });

        hi.getToolbar().setVisible(false);

        hi.addShowListener(e -> {
            hi.getToolbar().setHeight(0);
            hi.getToolbar().setVisible(true);
            hi.animateLayoutFadeAndWait(200, 100);

        });
        hi.setTransitionInAnimator(CommonTransitions.createEmpty());
        hi.show();

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/php/selectProd.php");

        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                for (Produit t : getListProduit(new String(con.getResponseData()))) {
                    Container c1 = new Container(BoxLayout.x());
                    Container c2 = new Container(BoxLayout.y());

                    hi.add(c1).add(c2);
                    Button supp = new Button("Supprimer");
                    Button bntUpdate = new Button("Update");
                    Label libprod = new Label("Libelle : " + t.getLibelle());
                    Label typeprod = new Label("Type Produit : " + t.getTypeproduit());
                    Label prix = new Label("Prix : " + t.getPrix() + " $");
                    Label quantite = new Label("Quanite :" + t.getQuantite());

                    c2.add(createLineSeparator(0x000000));
                    ImageViewer img = new ImageViewer();

                    Image ma = null;
                    try {
                        String x = "";
                        x = "file://" + t.getPath();
                        ma = Image.createImage(x);

                    } catch (IOException ex) {

                    }
                    img.setImage(ma);

                    c2.add(img).add(libprod).add(typeprod).add(prix).add(quantite).add(supp).add(bntUpdate);

                    hi.refreshTheme();

                    supp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            ConnectionRequest req = new ConnectionRequest();

                            req.setUrl("http://localhost/php/SupprimerProduit.php?id=" + t.getId() + "");
                            NetworkManager.getInstance().addToQueue(req);

                            c2.setLeadComponent(supp);
                            Dialog d = new Dialog();

                            if (Dialog.show("Confirmation", "Publication supprmier avec succes", "Ok", null)) {
                                new SupprimerProduit().Supp(t);
                                new AffichageProduit().getAll();
                            }

                        }

                    });

                    Button update = new Button("update");

                    bntUpdate.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Button pic = new Button("choisir image");
                            ImageViewer update1 = new ImageViewer();
                            TextField tfLibelle = new TextField(t.getLibelle(), "Libelle");
                            TextField tfTypeP = new TextField(t.getTypeproduit(), "Type  Produit");
                            TextField tfPrix = new TextField("" + t.getPrix(), "Phone Number", 20, TextField.NUMERIC);
                            TextField tfQuantite = new TextField("" + t.getQuantite(), "Phone Number", 20, TextField.NUMERIC);
        if (t.getPath() != null){
              Image ma = null;
                    try {
                        String x = "";
                        x = "file://" + t.getPath();
                        ma = Image.createImage(x);

                    } catch (IOException ex) {

                    }
                 
                    update1.setImage(ma);
                    
           

}

   pic.addActionListener(ev -> {
            String path = Capture.capturePhoto();
                System.out.println("origine temp file :" + path);
                int ind = path.indexOf("temp") + 4;
                int ind1 = path.indexOf("..");
                String text = path.substring(ind, ind1);
                System.out.println("Name:" + text);
                int intpoint = path.indexOf("..") + 2;
                String format =path.substring(intpoint);
                System.out.println("Format:" + format);
                String fullnameimage = text + "." + format;
                System.out.println("Full name :" + fullnameimage);
                
                imagepath = path;
              imagename = fullnameimage;
                System.out.println("imagepath:" + imagepath);
             try {
                 Image img = Image.createImage(path);
             } catch (IOException ex) {
                 
             }
                
            setImage(path, update1);
            
        });
      




                            f.add(tfLibelle);
                            f.add(tfTypeP);
                            f.add(tfPrix);
                            f.add(tfQuantite);
                            f.add(pic);
                            f.add(update1);
                            f.add(update);

                            f.show();

                            update.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    ConnectionRequest req = new ConnectionRequest();

                                    req.setUrl("http://localhost/php/updateProduit.php?libelle=" + tfLibelle.getText() + "&typeproduit=" + tfTypeP.getText() + "&prix=" + tfPrix.getText() + "&quantite=" + tfQuantite.getText() +"&imagename=" + imagename +"&imagepath=y" +imagepath +"&id=" + t.getId());

                                    req.addResponseListener(new ActionListener<NetworkEvent>() {

                                        @Override
                                        public void actionPerformed(NetworkEvent evt) {
                                            byte[] data = (byte[]) evt.getMetaData();
                                            String s = new String(data);

                                            if (!s.equals("Error")) {

                                                Dialog.show("Confirmation", "update ok", "Ok", null);
                                                new AffichageProduit().getAll();

                                            } else {
                                                Dialog.show("Confirmation", "update failed", "Ok", null);
                                            }
                                        }
                                    });

                                    NetworkManager.getInstance().addToQueue(req);

                                }

                            });
                        }

                    });
                }
            }

        });
        NetworkManager.getInstance().addToQueue(con);
    }

    public ArrayList<Produit> getListProduit(String json) {
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
                e.setQuantite(Integer.parseInt(obj.get("quantite").toString()));
                e.setPath(obj.get("path").toString());
                e.setDescription(obj.get("description").toString());
                listproduit.add(e);
            }

        } catch (IOException ex) {
        }
        return listproduit;
    }
    
    
    
    
    
    
      private void setImage(String filePath, ImageViewer iv) {
            try {
                Image i1 = Image.createImage(filePath);
                
                iv.setImage(i1);
                iv.getParent().revalidate();
            } catch (Exception ex) {
                Log.e(ex);
                Dialog.show("Error", "Error during image loading: " + ex, "OK", null);
            }
    }
    
    
    
    
    
    
    
    
    
    

}
