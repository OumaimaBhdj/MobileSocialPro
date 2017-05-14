/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.util.Resources;
import com.pi.socialpro.entites.Conge;

/**
 *
 * @author Yass
 */
public class SupprimerConge {
    Resources theme ;
     public void Supp(Conge c){
        
         ConnectionRequest req = new ConnectionRequest();
                                 
                req.setUrl("http://localhost/social_pro/supprimerConge.php?id=" + c.getId()+"");
                 NetworkManager.getInstance().addToQueue(req);

          new ListeConge(theme).show();
            
    }

}
