package com.pi.socialpro.controllers;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


public class Convocation {
    
     Form convocation;
    
    public Convocation(Resources theme)
    {
        
        convocation = new Form("Convocation", BoxLayout.y());
        Container c = new Container(BoxLayout.y());
        Label l1= new Label("\n");
         Label l2= new Label("\n");
          Label l3= new Label("\n");
          System.out.println("id user "+ TacheNonRealisee.id);
         TextField text = new TextField("", "Text", 20, TextArea.ANY);
         Button envoyer = new Button("Envoyer");
         c.add(l1).add(l2).add(l3).add(text).add(envoyer);
         convocation.add(c);
         convocation.show();
         
         envoyer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                
              
           
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/social_pro/insertConv.php?objet=" + "convocation" + "&text=" + text.getText() + "&user_id=" + TacheNonRealisee.id+"");

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "Votre Convocation a été envoyer avec succés", "Ok", null);
                            TacheNonAffecte tnaff = new TacheNonAffecte(theme);
                            tnaff.getF().show();
                            
                        }
                    }
                });
                
                NetworkManager.getInstance().addToQueue(req);
            }
                          
        });
         
     
         
         convocation.getToolbar().addCommandToLeftBar(null,theme.getImage("cal_left_arrow.png"),(a)->{
               TacheNonRealisee t= new TacheNonRealisee(theme);
               t.getF().showBack();
            });
         
         convocation.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

       
         @Override
         public void actionPerformed(ActionEvent evt) {
      Login.userCon=null;
        new Login(theme).getF().show();
         }
     });
    }
    
     public Form getF() {
        return convocation;
    }

    public void setF(Form f) {
        this.convocation = f;
    }
     
    public void show(){
     
      convocation.show(); 
 }
    
}
