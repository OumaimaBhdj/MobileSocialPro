/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;


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
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.pi.socialpro.entites.Produit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ahmed
 */
public class SupprimerProduit {
    public void Supp(Produit t){
        
         ConnectionRequest req = new ConnectionRequest();
                                 
                req.setUrl("http://localhost/php/SupprimerProduit.php?id=" + t.getId()+""  );
                 NetworkManager.getInstance().addToQueue(req);
                 
                 
                  
              
              new AffichageProduit().getAll();
    }
     
           
    
    
    
}
