/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.util.Resources;
import com.pi.socialpro.entites.Message;
import com.pi.socialpro.entites.Tache;

/**
 *
 * @author asus
 */
public class supprimer {
    Resources theme;
    
   public void Modifetat(Tache t,String etat)
    {
                 ConnectionRequest req = new ConnectionRequest();
             req.setUrl("http://localhost/social_pro/updateEtat.php?id=" + t.getId()+"&etat="+etat +""  );
                 NetworkManager.getInstance().addToQueue(req);
              new TacheLoggedUser(theme).show();
    }
    public void SuppTNR(Tache t)
    {
        
         ConnectionRequest req = new ConnectionRequest();
                                 
                req.setUrl("http://localhost/social_pro/delete.php?id=" + t.getId()+""  );
                 NetworkManager.getInstance().addToQueue(req);
              new TacheNonRealisee(theme).show();
    }   
    public void SuppTAF(Tache t)
    {
        
         ConnectionRequest req = new ConnectionRequest();
                                 
                req.setUrl("http://localhost/social_pro/delete.php?id=" + t.getId()+""  );
                 NetworkManager.getInstance().addToQueue(req);
              new TacheAffecte(theme).show();
    }  
    
     public void SuppTNAF(Tache t)
    {
        
         ConnectionRequest req = new ConnectionRequest();
                                 
                req.setUrl("http://localhost/social_pro/delete.php?id=" + t.getId()+""  );
                 NetworkManager.getInstance().addToQueue(req);
              new TacheNonAffecte(theme).show();
    } 
    public void Suppmsg(Message m){
        
         ConnectionRequest req = new ConnectionRequest();
                                 
                req.setUrl("http://localhost/social_pro/deleteMsg.php?id=" + m.getId()+""  );
                 NetworkManager.getInstance().addToQueue(req);
              new TacheLoggedUser(theme).show();
    }
    
}
