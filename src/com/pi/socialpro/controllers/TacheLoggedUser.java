package com.pi.socialpro.controllers;

import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.pi.socialpro.entites.Message;
import com.pi.socialpro.entites.Tache;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TacheLoggedUser {
    
    Form hi;
    Form aa;
      Form msg;
     
    public TacheLoggedUser(Resources theme)
    {
         hi = new Form("Bienvenu Sur SocialPro", new BorderLayout());
         aa = new Form("", BoxLayout.y());
         msg = new Form("",BoxLayout.y());
         
         //******************************************** Taches
           ConnectionRequest con = new ConnectionRequest();
          
        con.setUrl("http://localhost/social_pro/logged.php?id=6");
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                //System.out.println("aaaa"+getListTache(new String(con.getResponseData())));
             
                
                
                for (Tache t : getListTache(new String(con.getResponseData())))
                 {
                     Container c1 = new Container(BoxLayout.x());
                        Container c2 = new Container(BoxLayout.y());
                        
                        
                    
                         ComboBox cmbetat = new ComboBox();
                        cmbetat.addItem("en train de realisation");
                         cmbetat.addItem("realisee");
                        Button btnetat = new Button("Modifier Etat");
                        Label db = new Label("Date Début : "+t.getDatedebut());
                        Label df = new Label("Date Fin : "+t.getDatefin());
                        SpanLabel desc = new SpanLabel("Description : "+t.getDescription());
                        Label etat = new Label("Etat : " + t.getEtat());
                         Label sep = new Label("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
                        c2.add(db).add(df).add(desc).add(etat).add(cmbetat).add(btnetat).add(sep);
                         c1.add(c2);
                aa.add(c1);
               aa.refreshTheme(); 
               
               btnetat.addActionListener(new ActionListener() {

                         @Override
                         public void actionPerformed(ActionEvent evt) {
                ConnectionRequest modif= new ConnectionRequest();
                modif.setUrl("http://172.16.164.66/social_pro/updateEtat.php?id=" + t.getId() + "&etat='" + cmbetat.getSelectedItem()+"'");

                modif.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println("---------"+s);        
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "L'état de Votre Tâche à été Modifier avec succées", "Ok", null);  
                            new TacheLoggedUser(theme).show();
                            
                        }
                        else 
                        {
                             Dialog.show("failed", "failed", "Ok", null);
                        }
                    }
                });
                
                NetworkManager.getInstance().addToQueue(modif);
            
                         }
                     });
               
                 }   
              
                Container email = new Container(BoxLayout.y());
                 Button btnsms = new Button("Envoyer SMS");
                 
                 btnsms.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                      /*Twilio.init("AC829ab1b92a62916667de2200ac6db1ee", "b6d08a4949465f2ff850fbb8dd534ac0");
                                com.twilio.rest.api.v2010.account.Message message =
                               com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+21627404323"),
                                new PhoneNumber("+15612885165"),"test social pro").create();
                                System.out.println("Message Envoyer");*/
                        
                        SMS sms = new SMS(theme);
                        sms.getF().show();
                        
                    }
                });
                SpanLabel sp = new SpanLabel(" Veuillez contactez votre chef de Projet en cas de besoin");
                Label retour1 = new Label("\n");
                Label retour2 = new Label("\n");
               
                email.add(sp).add(btnsms).add(retour1).add(retour2);
                    aa.add(email);
                  aa.refreshTheme(); 
            }
           
        });
        NetworkManager.getInstance().addToQueue(con);
        
        //********************************************************
        
         msg = new Form("", BoxLayout.y());
     ConnectionRequest con2 = new ConnectionRequest();
        con2.setUrl("http://localhost/social_pro/messageUser.php?id=6");
        con2.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
               
             
              
                for (Message m : getListMessage(new String(con2.getResponseData())))
                 {
                     Container c1 = new Container(BoxLayout.x());
                        Container c2 = new Container(BoxLayout.y());
                        Label dateenvo = new Label("Envoyé Le : "+m.getDatedenvoye());
                        Button supp = new Button("Supprimer");
                        supp.addActionListener(new ActionListener() {

                         @Override
                         public void actionPerformed(ActionEvent evt) {
                       ConnectionRequest req = new ConnectionRequest();
                       req.setUrl("http://localhost/social_pro/deleteMsg.php?id="+m.getId()+"");
                       NetworkManager.getInstance().addToQueue(req);
                       
                        Dialog d = new Dialog();
                             
                             if(  
                                 Dialog.show("Confirmation", "Message supprmier avec succes", "Ok", null)){
                                 new supprimer().Suppmsg(m);
                             }
                       
                         }
                     });
                        SpanLabel txt = new SpanLabel("text :"+m.getText());
                         Label sep = new Label("_________________________________________");
                        c2.add(dateenvo).add(txt).add(supp).add(sep);
                         c1.add(c2);
                msg.add(c1);
                msg.refreshTheme(); 
                 }  
                
              
            }
        });
        NetworkManager.getInstance().addToQueue(con2);
        //*****************************************

        Tabs t = new Tabs();
        Style s = UIManager.getInstance().getComponentStyle("Tab");
        FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_QUESTION_ANSWER, s);
        FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_ACCESSIBILITY, s);

        Container container1 = BoxLayout.encloseY(aa);
        Container containermsg = BoxLayout.encloseY(msg);
        t.addTab("Tâche(s)", icon2, container1);
        t.addTab("Message(s)",icon1, containermsg);

        hi.add(BorderLayout.CENTER, t);
        
        
        
        hi.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

       
         @Override
         public void actionPerformed(ActionEvent evt) {
      Login.userCon=null;
        new Login(theme).getF().show();
         }
     });
    }
    
     public ArrayList<Tache> getListTache(String json) {
        ArrayList<Tache> listTaches = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();
            Map<String, Object> taches = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) taches.get("tache");
            Date db;
            for (Map<String, Object> obj : list) {
                Tache e = new Tache();
                
                e.setId(Integer.parseInt(obj.get("id").toString()));
                e.setDatedebut(obj.get("datedebut").toString());
                e.setDatefin(obj.get("datefin").toString());
                e.setDescription(obj.get("description").toString());
                e.setEtat(obj.get("etat").toString());
                listTaches.add(e);
            }

        } catch (IOException ex) {
         }
        return listTaches;
    }
     public ArrayList<Message> getListMessage(String json) {
        ArrayList<Message> listTaches = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> messages = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) messages.get("message");
            Date db;
            for (Map<String, Object> obj : list) {
                Message m = new Message();

                
                m.setId(Integer.parseInt(obj.get("id").toString()));
                m.setText(obj.get("text").toString());
                m.setDatedenvoye(obj.get("datedenvoye").toString());
               listTaches.add(m);
               
            }

        } catch (IOException ex) {
         }
        return listTaches;
     
    }


     public Form getF() {
        return hi;
    }

    public void setF(Form f) {
        this.hi= f;
    }
    public void show(){
     
      hi.show(); 
 }
}
