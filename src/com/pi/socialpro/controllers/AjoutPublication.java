/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.MediaPlayer;
import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.social.FacebookConnect;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Command;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.util.Date;




/**
 *
 * @author ahmed
 */
public class AjoutPublication {
   public static String  imagepath;
  //  public static String path;
    public static String  imagename;
    public void Ajout (){
         Form f = new Form("Bienvenue a social pro",BoxLayout.y());
       // AjoutForm f = new AjoutForm();
       
        Label l = new Label();
        l.setText("Ajouter vos Publication"+"\n");
      
       Button pic = new Button("choisissez un fichier");
        ImageViewer l1 = new ImageViewer();
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
                
            setImage(path, l1);
            
            
        });
        TextField tfStatut = new TextField("", "Statut");
        
Button b = new Button();

f.add(l);
        f.add(tfStatut);
      
        

        Button btnOk = new Button("Ajouter");
        Button contact = new Button("contact");
        
        f.add(pic);
        f.add(btnOk);
        
        f.add(l1);
        
        contact.addActionListener(new ActionListener() {
             
             @Override
             public void actionPerformed(ActionEvent evt) {
                 
                new Facebook().ConnectFB();
             
             }
        });
        ///////////////////////////
        
        
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                ConnectionRequest req = new ConnectionRequest();
                String username="";
                
               int iduser = 0;
               username=Login.userCon.getUsername();
               iduser = Login.userCon.getId();
                Date dateAjout = new Date(System.currentTimeMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                
                                sdf.format(dateAjout);
                req.setUrl("http://localhost/php/AjoutPub.php?statut=" + tfStatut.getText()+"&imagename=" + imagename + "&imagepath=" + imagepath + "&dateAjout=" + sdf+ "&iduser=" + iduser+"&username=" + username+ "");
                

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "Ajout avec succes", "Ok", null);
                            new AffichagePublication().getAll();
                        }
                    }
                });
                
                NetworkManager.getInstance().addToQueue(req);
                

            }
        });
        f.animateHierarchyFade(1000, 100);
        f.show();
        
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
