/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;


import Entity.Publication;
import com.codename1.capture.Capture;
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
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Component;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.pi.socialpro.AffichageForm;
import com.pi.socialpro.UpdateForm;

import java.util.Date;



/**
 *
 * @author ahmed
 */
public class AffichagePublication extends AffichageForm{
    
              String  imagepath = "";
              String imagename= "";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    private Resources theme;
     public void getAll(){
        AffichageForm hi = new AffichageForm();
                UpdateForm f = new UpdateForm();
        SpanLabel sp = new SpanLabel("");
                                            ImageViewer update1 = new ImageViewer();
                                            
                                            
              

hi.getToolbar().addMaterialCommandToSideMenu("Ajouter une publication", FontImage.MATERIAL_ADD_CIRCLE, e -> new AjoutPublication().Ajout());
               
        hi.getToolbar().addMaterialCommandToSideMenu("Statistique", FontImage.MATERIAL_REPORT, e -> new Piechart1().createPieChartForm().show());
   hi.getToolbar().addMaterialCommandToSideMenu("Connecter a facebook", FontImage.MATERIAL_CHAT, e -> new Facebook().ConnectFB());
   hi.getToolbar().addMaterialCommandToSideMenu("Partager notre site", FontImage.MATERIAL_CLOUD, e -> new Facebook().partage());
   hi.getToolbar().addMaterialCommandToSideMenu("Mes Publications", FontImage.MATERIAL_CLOUD, e -> new MesPublications().MesPublications());
   hi.getToolbar().addMaterialCommandToSideMenu("Publications les plus aimés", FontImage.MATERIAL_CLOUD, e -> new OrderByJaime().Order());
hi.getToolbar().addMaterialCommandToSideMenu("Logout",
                FontImage.MATERIAL_LOCK, e -> new Login(theme).getF().show());


   
   Toolbar.setGlobalToolbar(true);
Style s = UIManager.getInstance().getComponentStyle("Title");
        
        TextField searchField = new TextField("", "Recherche"); // <1>
searchField.getHintLabel().setUIID("Title");
searchField.setUIID("Title");
searchField.getAllStyles().setAlignment(Component.LEFT);
hi.getToolbar().setTitleComponent(searchField);
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH,s);
        
        
        hi.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
         new   RecherchePublication().Recherche(searchField.getText());

});
   
        hi.add(sp);
        hi.show();

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/php/selectPub.php");
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                 for (Publication t : getListEtudiant(new String(con.getResponseData())))
                 {
                      

                      ImageViewer im1 = new ImageViewer();
                     Container c1 = new Container(BoxLayout.x());
                     Container c2 = new Container(BoxLayout.y());
                    Image delete = null;
                    
//                            try {
//                                delete = Image.createImage("/delete.png");
//                                
//                            } catch (IOException ex) {
//                            }
                           
                     hi.add(c1).add(c2);
                     
                     Label statut = new Label(""+t.getText());
                     if (t.getPath()!=null){
                         String x="";
                         x="file://"+t.getPath();
                    
                     Image ma = null;
                     try {
                        
                         ma = Image.createImage(x);
                    
                     } catch (IOException ex) {
                     }
                      im1.setImage(ma);
                     }
                     Button supp = new Button("Supprimer");
                    
                     
                      Button update = new Button("Modifier");
                       Button details = new Button(" "+"details");
                   
                    
                    Container c22 = new Container(BoxLayout.x());
                     c22.add(supp).add(update).add(details);
                     
                     
                    
                     //Label sep = new Label("-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
                     c2.add(createLineSeparator(0x202020));
                     Label sep = new Label("\n");
                     c2.add(statut).add(im1).add(c22).add(sep);
                     hi.refreshTheme();
                     
                     
                     details.addActionListener(new ActionListener() {
                          @Override
                          public void actionPerformed(ActionEvent evt) {
                              new Details().showDetails(t);
                          }
                      });
                     
                     
                    update.addActionListener(new ActionListener() {
                          @Override
                          public void actionPerformed(ActionEvent evt) {
                             
                            
if (t.getPath()!=null){
                         String x="";
                         x="file://"+t.getPath();
                    
                     Image ma5 = null;
                     try {
                        
                         ma5 = Image.createImage(x);
                    
                     } catch (IOException ex) {
                     }
                      update1.setImage(ma5);
                     }
Button pic = new Button("choisissez un fichier");
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
         TextField tfstatut = new TextField(t.getText(), "statut");
                               Button update11 = new Button("Mise a jour");

                            f.add(tfstatut);
                            f.add(update1);
                                                 Container c4 = new Container(BoxLayout.y());
                                                 c4.add(pic).add(update11);
                                                 Style s =UIManager.getInstance().getComponentStyle("Title");
   
Button back1 =new Button();
    back1.getAllStyles().setAlignment(Component.RIGHT);
    FontImage backIcon= FontImage.createMaterial(FontImage.MATERIAL_FLIP_TO_BACK,s);
    
    f.getToolbar().addCommandToLeftBar("",backIcon,(e)->{new AffichagePublication().getAll();});

                          //  f.add(pic);
                          //  f.add(update11);
                            f.add(c4);
       
                            f.show();
 
                            update11.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    ConnectionRequest req8 = new ConnectionRequest();

                                    req8.setUrl("http://localhost/php/Update.php?statut=" + tfstatut.getText()+"&imagename=" + imagename + "&imagepath=" + imagepath   + "&id=" + t.getId()+ "");
                                       NetworkManager.getInstance().addToQueue(req8);
                                    req8.addResponseListener(new ActionListener<NetworkEvent>() {

                                        @Override
                                        public void actionPerformed(NetworkEvent evt) {
                                            byte[] data = (byte[]) evt.getMetaData();
                                            String s = new String(data);

                                            if (!s.equals("Error")) {

                                                Dialog.show("Confirmation", "update ok", "Ok", null);
                                                new AffichagePublication().getAll();

                                            } else {
                                                Dialog.show("Confirmation", "update failed", "Ok", null);
                                            }
                         
                       }
                         
                     });
                                    
                                      }
                                
                         
                     });
                            
                       }
                         
                     });
                     supp.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent evt) {
                            
                             ConnectionRequest req = new ConnectionRequest();
                             
                             req.setUrl("http://localhost/php/SupprimerPublication.php?id=" + t.getId()+""  );
                             NetworkManager.getInstance().addToQueue(req);
                             
                             c2.setLeadComponent(supp);
                             Dialog d = new Dialog();
                             
                             if(     Dialog.show("Confirmation", "Publication supprmier avec succes", "Ok", null)){
                                 new SupprimerPublication().Supp(t);
                             }
                             
                             
                             
                             
                            
                             
                         }
                         
                     });
                     hi.refreshTheme();
              
               
                 }

                              

            }
            
        });
        NetworkManager.getInstance().addToQueue(con);
        
    }

    public ArrayList<Publication> getListEtudiant(String json) {
        ArrayList<Publication> listEtudiants = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("publication");
                            listEtudiants.clear();

            for (Map<String, Object> obj : list) {
                Publication e = new Publication();
                e.setId(Integer.parseInt(obj.get("id").toString()));
                e.setText(obj.get("text").toString());
                e.setPath(obj.get("path").toString());
                
                e.setUsername(obj.get("username").toString());
                e.setJaime(Integer.parseInt(obj.get("jaime").toString()));
                 e.setJaimePas(Integer.parseInt(obj.get("jaimePas").toString()));
                try {
                    e.setDateAj(sdf.parse((String) obj.get("dateAjout")));
                } catch (ParseException ex) {
                }
                
                listEtudiants.add(e);

            }
            

        } catch (IOException ex) {
         }
        return listEtudiants;
    }
    
     
    private void setImage(String filePath, ImageViewer iv) {
            try {
                Image i1 = Image.createImage(filePath);
                iv.setImage(i1);
                iv.getParent().revalidate();
            } catch (Exception ex) {
              
            }
    }       
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }
}
