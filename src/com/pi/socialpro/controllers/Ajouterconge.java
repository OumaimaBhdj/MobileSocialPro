/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
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


/**
 *
 * @author Yass
 */
public class Ajouterconge {
     
    Form AjouterConge ;
    
  public Ajouterconge(Resources theme){
  
      
      AjouterConge = new Form("Demande de congé", BoxLayout.y());
     
     
        Container ajout = new Container(BoxLayout.y());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
              
        Label db=new Label("Date Début");
       Picker datedebut = new Picker();
         datedebut.setFormatter(format);
       //datedebut.setType(Display.PICKER_TYPE_DATE);
       
       Label nbr= new Label("Nombre de jours");
       TextField nbrj= new TextField("","Nombre de jours",20,TextField.NUMERIC);
       
       
        Label et=new Label("Type du congé");
        ComboBox type = new ComboBox();
        type.addItem("Repos");
        type.addItem("Maladie");
        type.addItem("Maternite");
        
        
         Label res=new Label("Raison");
         TextField raison = new TextField("", "Raison", 20, TextArea.ANY);
        
        
        Button btnOk = new Button("Ajouter");
        ajout.add(db).add(datedebut).add(nbr).add(nbrj).add(et).add(type).add(res).add(raison);
        AjouterConge.add(ajout).add(btnOk);
          btnOk.addActionListener(new ActionListener() {
       
           
          


            @Override
            public void actionPerformed(ActionEvent evt) {
           
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/social_pro/insertConge.php?datedebut=" + datedebut.getText() + "&nbrjours=" + nbrj.getText() + "&type_conge=" + type.getSelectedItem() + "&raison="+ raison.getText()+"&id="+Login.userCon.getId());

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "ajout avec succé", "Ok", null);
                            new ListeConge(theme).getF().show();
                        }
                    }
                });
                
                NetworkManager.getInstance().addToQueue(req);
            }
        });
          
           AjouterConge.getToolbar().addMaterialCommandToSideMenu("Acceuil", 
                FontImage.MATERIAL_DASHBOARD, e -> new Acceuil_user(theme).getF().show());
          AjouterConge.getToolbar().addMaterialCommandToSideMenu("Liste des congés", 
                FontImage.MATERIAL_LIST, e -> new ListeConge(theme).getF().show());
           /*   AjouterConge.getToolbar().addMaterialCommandToSideMenu("Recherche", 
              FontImage.MATERIAL_SEARCH, e -> new RechercheEtat(theme).getF().show());*/
            AjouterConge.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

           
         @Override
         public void actionPerformed(ActionEvent evt) {
      Login.userCon=null;
        new Login(theme).getF().show();
            }
        });
             
         
     
          
   
       
      
  }
  
  
   public Form getF() {
        return AjouterConge;
    }

    public void setF(Form f) {
        this.AjouterConge = f;
    }
    
    
    
 public void show(){
     
      AjouterConge.show(); 
 }
  }
    

