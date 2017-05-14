/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.pi.socialpro.forms.AjoutProduitForm;

import java.io.IOException;

/**
 *
 * @author ahmed
 */
public class AjoutProduit {
   public static String  imagepath;
    public static String path;
    public static String  imagename;
    
    public void Ajout() {
        AjoutProduitForm f = new AjoutProduitForm();
        Capture cap = new Capture();

        TextField tfLibelle = new TextField("", "Libelle");
        TextField tfTypeP = new TextField("", "Type  Produit");
        TextField tfPrix = new TextField("", "prix");
        TextField tfQuantite = new TextField("", "quantite");
        
        f.add(tfLibelle);
        f.add(tfTypeP);
        f.add(tfPrix);
        f.add(tfQuantite);
          Button pic = new Button("Choisissez une image ");
         f.add(pic);
         Button btnOk = new Button("Insert");
          f.add(btnOk);
      
        ImageViewer l1 = new ImageViewer();
       
        f.add(l1);
       
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
      
        btnOk.addActionListener(new ActionListener() {

            
            @Override
            public void actionPerformed(ActionEvent evt) {

                
                ConnectionRequest req = new ConnectionRequest();
 req.setUrl("http://localhost/php/insert.php?typeproduit=" + tfTypeP.getText() + "&libelle=" + tfLibelle.getText() +"&prix=" + tfPrix.getText() +"&quantite=" + tfQuantite.getText() +"&imagename=" + imagename + "&imagepath=" + imagepath +"");
              
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "Ajout avec succes", "Ok", null);
                            new AffichageProduit().getAll();
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req);

            }
        });

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
