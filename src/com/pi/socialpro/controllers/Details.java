/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import Entity.Publication;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import static com.pi.socialpro.controllers.AjoutPublication.imagename;
import static com.pi.socialpro.controllers.AjoutPublication.imagepath;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.pi.socialpro.entites.Jaime;
import social_pro.entites.JaimePas;
/**
 *
 * @author ahmed
 */
public class Details {
    
    int nbjaimepas=0;
    int nbjaime=0;
    int idjaimepas=0;
    int idjaime=0;
    String jj="";
    String jp="";
     SpanLabel sp = new SpanLabel();
    public void showDetails(Publication p){
        Image like = null;
                    
                            try {
                                like = Image.createImage("/like.png");
                                
                            } catch (IOException ex) {
                            }
        
        int r=0;
        r=p.getId();
         ImageViewer im1 = new ImageViewer();
         Container c2 = new Container(BoxLayout.y());
                  Button jaime1 = new Button(like);

         Button jaime = new Button("jaime"+" "+" "+" "+p.getJaime());
          Button jaimePas = new Button("jaimePas"+" "+" "+" "+p.getJaimePas());
        Form hi = new Form("Les details ");
        hi.getToolbar().addMaterialCommandToSideMenu("Ajouter un produit", FontImage.MATERIAL_ADD_CIRCLE, e -> new AjoutPublication().Ajout());

         hi.getToolbar().addMaterialCommandToSideMenu("Statistique", FontImage.MATERIAL_REPORT, e -> new Piechart1().createPieChartForm().show());
   hi.getToolbar().addMaterialCommandToSideMenu("Connecter a facebook", FontImage.MATERIAL_CHAT, e -> new Facebook().ConnectFB());
   hi.getToolbar().addMaterialCommandToSideMenu("Partager notre site", FontImage.MATERIAL_CLOUD, e -> new Facebook().partage());
   hi.getToolbar().addMaterialCommandToSideMenu("Mes Publications", FontImage.MATERIAL_CLOUD, e -> new MesPublications().MesPublications());
   
   Style s =UIManager.getInstance().getComponentStyle("Title");
   
Button back =new Button();
    back.getAllStyles().setAlignment(Component.RIGHT);
    FontImage backIcon= FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK,s);
    
    hi.getToolbar().addCommandToLeftBar("",backIcon,(e)->{new AffichagePublication().getAll();});
        hi.add(sp);
        hi.show();
        Label esp = new Label("\n");
        Label statut = new Label("le statut : "+" "+p.getText());
        Label ajoutePar = new Label("Ajouté par  : "+" "+p.getUsername());
        Label dateAjout = new Label("Ajouté le: "+p.getDateAj());
        Label aime = new Label("Aimé par  : "+" "+p.getJaime()+" "+"personnes");
         Label aimepas = new Label("Signaler par  : "+" "+p.getJaimePas()+" "+"personnes");
          Image image = null;
          String x="";
                        
         if (p.getPath()!=""){
            
                     x="file://"+p.getPath();
                            try {
                                image = Image.createImage(x);
                                
                            } catch (IOException ex) {
                            }
                           im1.setImage(image);
         }
         
         
         c2.add(statut).add(ajoutePar).add(dateAjout).add(aime).add(aimepas).add(im1).add(jaime).add(jaimePas);
         
       hi.add(c2);
       hi.show();
       
       
       
       
               jaime.addActionListener(new ActionListener() {
                   Form kk = new Form("La liste des publications");
                   
             @Override
             public void actionPerformed(ActionEvent evt) {
                 ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/php/selectJaime.php");
        
        
        ConnectionRequest selectJaimePas = new ConnectionRequest();
        selectJaimePas.setUrl("http://localhost/php/selectJaimePas.php");
       
        
                             //  System.out.println(getListJaime(new String(con.getResponseData())));
                            
 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
                     @Override
                     public void actionPerformed(NetworkEvent evt) {
                         selectJaimePas.addResponseListener(new ActionListener<NetworkEvent>() {
                     @Override
                     public void actionPerformed(NetworkEvent evt) {
                         
                         
                         for (JaimePas j1 : getListJaimePas(new String(selectJaimePas.getResponseData()))){
                             System.out.println("?????????"+p.getId()+"="+j1.getIdPublication()+"  "+j1.getIdUser()+"="+Login.userCon.getId());
                             if (p.getId()==j1.getIdPublication()&&j1.getIdUser()==Login.userCon.getId()){
                                 idjaimepas=j1.getId();
                                 jp="3ameljaimepas";
                             }
                             if (p.getId()!=j1.getIdPublication()&&j1.getIdUser()!=Login.userCon.getId()){
                                 jp="mech3ameljaimepas";
                             }
                             System.out.println(""+jp);
              }
                     }
                     });
                      // System.out.println(getListJaime(new String(con.getResponseData())));
               // sp.setText(getListJaime(new String(con.getResponseData())) + "");
              // kk.add(sp);
              // kk.show();
               
             
              
               for (Jaime j : getListJaime(new String(con.getResponseData()))){
                  
                   
                   System.out.println(""+j.getIdPublication()+"="+p.getId()+"!!!!!"+j.getIdUser()+"="+Login.userCon.getId());
                                      if (j.getIdPublication()==p.getId()&&j.getIdUser()==Login.userCon.getId()){
                                          jj="3ameljaime";
                                          idjaime=j.getId();
                                      }
                                      if (j.getIdPublication()!=p.getId()&&j.getIdUser()!=Login.userCon.getId()){
                                          jj="mech3ameljaime";
                                      }
                                      
                                      

               }
                         System.out.println("!!!!!!!!!!!!!!!!!!!!"+jj+jp);
                                            if (jj=="3ameljaime"){
                                          Dialog.show("alarme", "deja aimé", "Ok", null);
                                      }
                                            
                                      if (jj=="mech3ameljaime"&&jp=="mech3ameljaimepas"){
                                           ConnectionRequest req = new ConnectionRequest();
                                          
                                          req.setUrl("http://localhost/php/AjoutJaime.php?idpublication=" + p.getId()+"&iduser=" + Login.userCon.getId()+ "");
                                          NetworkManager.getInstance().addToQueue(req);
                                          nbjaime=p.getJaime()+1;
                                          ConnectionRequest updatejaime = new ConnectionRequest();
                                          updatejaime.setUrl("http://localhost/php/updateJaime.php?nbjaime=" + nbjaime+"&id=" + p.getId()+ "");
                                          NetworkManager.getInstance().addToQueue(updatejaime);
                                          hi.refreshTheme();
                                          new AffichagePublication().getAll();
                                      }
                                      if (jj=="mech3ameljaime"&&jp=="3ameljaimepas"){
                                           ConnectionRequest req = new ConnectionRequest();
                                          
                                          req.setUrl("http://localhost/php/AjoutJaime.php?idpublication=" + p.getId()+"&iduser=" + Login.userCon.getId()+ "");
                                          NetworkManager.getInstance().addToQueue(req);
                                          nbjaime=p.getJaime()+1;
                                          ConnectionRequest updatejaime = new ConnectionRequest();
                                          updatejaime.setUrl("http://localhost/php/updateJaime.php?nbjaime=" + nbjaime+"&id=" + p.getId()+ "");
                                          NetworkManager.getInstance().addToQueue(updatejaime);
                                           nbjaimepas=p.getJaimePas()-1;
                                          ConnectionRequest updatejaimepas = new ConnectionRequest();
                                          updatejaimepas.setUrl("http://localhost/php/updateJaimePas.php?nbjaimepas=" + nbjaimepas+"&id=" + p.getId()+ "");
                                          NetworkManager.getInstance().addToQueue(updatejaimepas);
                                          
                                           ConnectionRequest supjaimepas = new ConnectionRequest();
                             
                             supjaimepas.setUrl("http://localhost/php/SupprimerJaimePas.php?idpub=" + p.getId()+"&iduser=" + Login.userCon.getId()+""  );
                             NetworkManager.getInstance().addToQueue(supjaimepas);
                                          hi.refreshTheme();
                                          new AffichagePublication().getAll();
                                      }
                     }
                    
       
                
         });
                    
        
        NetworkManager.getInstance().addToQueue(con);
          NetworkManager.getInstance().addToQueue(selectJaimePas);
       
             }
         });
               
     //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  
               
               
        jaimePas.addActionListener(new ActionListener() {
                   Form kk = new Form("La liste des publications");
                   
             @Override
             public void actionPerformed(ActionEvent evt) {
                 ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/php/selectJaime.php");
        
        
        ConnectionRequest selectJaimePas = new ConnectionRequest();
        selectJaimePas.setUrl("http://localhost/php/selectJaimePas.php");
       
        
                             //  System.out.println(getListJaime(new String(con.getResponseData())));
                            
 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
                     @Override
                     public void actionPerformed(NetworkEvent evt) {
                         selectJaimePas.addResponseListener(new ActionListener<NetworkEvent>() {
                     @Override
                     public void actionPerformed(NetworkEvent evt) { 
                         for (JaimePas j1 : getListJaimePas(new String(selectJaimePas.getResponseData()))){
                             if (p.getId()==j1.getIdPublication()&&j1.getIdUser()==Login.userCon.getId()){
                                 jp="3ameljaimepas";
                                 idjaimepas=j1.getId();
                             }
                             if (p.getId()!=j1.getIdPublication()&&j1.getIdUser()!=Login.userCon.getId()){
                                 jp="mech3ameljaimepas";
                             }
                  System.out.println("hahahaha"+j1.getIdPublication()+"ahahaha"+j1.getIdUser());
              }
                     }
                     });
                      // System.out.println(getListJaime(new String(con.getResponseData())));
               // sp.setText(getListJaime(new String(con.getResponseData())) + "");
              // kk.add(sp);
              // kk.show();
               
             
              
               for (Jaime j : getListJaime(new String(con.getResponseData()))){
                  
                   
                   System.out.println(""+j.getIdPublication()+"="+p.getId()+"!!!!!"+j.getIdUser()+"="+Login.userCon.getId());
                                      if (j.getIdPublication()==p.getId()&&j.getIdUser()==Login.userCon.getId()){
                                          jj="3ameljaime";
                                          idjaime=j.getId();
                                      }
                                      if (j.getIdPublication()!=p.getId()&&j.getIdUser()!=Login.userCon.getId()){
                                          jj="mech3ameljaime";
                                      }
                                      
                                      

               }
                                            if (jp=="3ameljaimepas"){
                                          Dialog.show("alarme", "deja signaler", "Ok", null);
                                      }
                                      if (jp=="mech3ameljaimepas"&&jj=="mech3ameljaime"){
                                           ConnectionRequest ajoutjaimepas = new ConnectionRequest();
                                          
                                          ajoutjaimepas.setUrl("http://localhost/php/AjoutJaimePas.php?idpublication=" + p.getId()+"&iduser=" + Login.userCon.getId()+ "");
                                          NetworkManager.getInstance().addToQueue(ajoutjaimepas);
                                          nbjaimepas=p.getJaimePas()+1;
                                          ConnectionRequest updatejaimepas = new ConnectionRequest();
                                          updatejaimepas.setUrl("http://localhost/php/updateJaimepas.php?nbjaimepas=" + nbjaimepas+"&id=" + p.getId()+ "");
                                          NetworkManager.getInstance().addToQueue(updatejaimepas);
                                          hi.refreshTheme();
                                          new AffichagePublication().getAll();
                                      }
                                      if (jj=="3ameljaime"&&jp=="mech3ameljaimepas"){
                                           ConnectionRequest req = new ConnectionRequest();
                                          
                                          req.setUrl("http://localhost/php/AjoutJaimePas.php?idpublication=" + p.getId()+"&iduser=" + Login.userCon.getId()+ "");
                                         NetworkManager.getInstance().addToQueue(req);
                                          nbjaime=p.getJaime()-1;
                                          ConnectionRequest updatejaime = new ConnectionRequest();
                                          updatejaime.setUrl("http://localhost/php/updateJaime.php?nbjaime=" + nbjaime+"&id=" + p.getId()+ "");
                                          NetworkManager.getInstance().addToQueue(updatejaime);
                                           nbjaimepas=p.getJaimePas()+1;
                                          ConnectionRequest updatejaimepas = new ConnectionRequest();
                                          updatejaimepas.setUrl("http://localhost/php/updateJaimePas.php?nbjaimepas=" + nbjaimepas+"&id=" + p.getId()+ "");
                                          NetworkManager.getInstance().addToQueue(updatejaimepas);
                                          
                                           ConnectionRequest supjaime = new ConnectionRequest();
                             
                             supjaime.setUrl("http://localhost/php/SupprimerJaime.php?idpub=" + p.getId()+"&iduser=" + Login.userCon.getId()+""  );
                             NetworkManager.getInstance().addToQueue(supjaime);
                                          hi.refreshTheme();
                                          new AffichagePublication().getAll();
                                      }
                                      
                                     
                     }
                    
       
                
         });
                    
        
        NetworkManager.getInstance().addToQueue(con);
          NetworkManager.getInstance().addToQueue(selectJaimePas);
       
             }
         });       
               
               
               
               
               
               
     //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!          
    }
    
     public ArrayList<Jaime> getListJaime(String json) {
        ArrayList<Jaime> listJaime = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> jaimes = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) jaimes.get("jaime");
                            listJaime.clear();

            for (Map<String, Object> obj : list) {
                Jaime e = new Jaime();
                e.setIdPublication(Integer.parseInt(obj.get("idPublication").toString()));
                
                 e.setIdUser(Integer.parseInt(obj.get("idUser").toString()));
                
              //  System.out.println("kkkkkkkkkkkkk"+e.getIdPublication());
                               // System.out.println("kkkkkkkkkkkkk"+e.getIdUser());

                listJaime.add(e);

            }
            

        } catch (IOException ex) {
         }
        return listJaime;
    }
     
     
     
      public ArrayList<JaimePas> getListJaimePas(String json) {
        ArrayList<JaimePas> listJaimePas = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> jaimesPas = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) jaimesPas.get("jaimepas");
                            listJaimePas.clear();

            for (Map<String, Object> obj : list) {
                JaimePas e = new JaimePas();
                e.setIdPublication(Integer.parseInt(obj.get("idPublication").toString()));
                
                 e.setIdUser(Integer.parseInt(obj.get("idUser").toString()));
                
                System.out.println("kkkkkkkkkkkkk"+e.getIdPublication());
                                System.out.println("kkkkkkkkkkkkk"+e.getIdUser());

                listJaimePas.add(e);

            }
            

        } catch (IOException ex) {
         }
        return listJaimePas;
    }
    
}
