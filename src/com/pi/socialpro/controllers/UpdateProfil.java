/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.pi.socialpro.entites.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UpdateProfil {

    Form f = new Form("Bienvenue a social pro", BoxLayout.y());

  
    public static User userCon;

    private Resources theme;
    ImageViewer v;
    public UpdateProfil(Resources theme) {
            this.theme=theme;
            
            
        v = new ImageViewer();

       v.setImage(Login.img);
        f.add(v);
        TextField prenom = new TextField(Login.userCon.getPrenom());
        f.add(prenom);

        TextField nom = new TextField(Login.userCon.getNom());
       
          f.add(nom);

        TextField username = new TextField(Login.userCon.getUsername());
          f.add(username);

        TextField phone = new TextField("" + Login.userCon.getNum_telephone());
          f.add(phone);

        TextField email = new TextField(Login.userCon.getEmail());
        f.add(email);

       TextField password = new TextField(Login.userCon.getPassword());
        f.add(password);


        Button update = new Button("modifier mon compte");
        Button btnDes = new Button("Annuler");
       
        f.add(update);
        f.add(btnDes);
           f.getToolbar().addCommandToLeftBar("Back", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
          new Profil(theme).getF().show();
            }
        });
        
    

       
        update.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
         ConnectionRequest req = new ConnectionRequest();
                System.out.println("http://localhost/socialpromobile/updateprofil.php?nom=" + nom.getText() + "&prenom=" + prenom.getText() + "&username=" + username.getText() + "&email=" + email.getText() + "&phone=" + phone.getText() + "&password=" + password.getText() + "&id=" + Login.userCon.getId());

                req.setUrl("http://localhost/socialpromobile/updateprofil.php?nom=" + nom.getText() + "&prenom=" + prenom.getText() + "&username=" + username.getText() + "&email=" + email.getText() + "&phone=" + phone.getText() + "&password=" + password.getText() + "&id=" + Login.userCon.getId());

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (!s.equals("Error")) {

                            Login.userCon = getUser(s);
                            System.out.println("UserConnected Updated : "+Login.userCon);
                            Dialog.show("Confirmation", "update ok", "Ok", null);
                        } else {
                            Dialog.show("Confirmation", "update failed", "Ok", null);
                        }
                    }
                });
                NetworkManager.getInstance().addToQueue(req);
        
        
  
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
});
        btnDes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new Profil(theme).getF().show();

    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

    
f.show();
    }
       public User getUser(String json) {
        ArrayList<User> listEtudiants = new ArrayList<>();

        System.out.println("+++++++++++++++++++++++" + json);
        try {

            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("user");

            for (Map<String, Object> obj : list) {
                User u = new User();
                u.setId(Integer.parseInt(obj.get("id").toString()));
                u.setNom(obj.get("nom").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setEmail(obj.get("email").toString());
                u.setPassword(obj.get("password").toString());
                u.setImage(obj.get("image").toString());
                u.setUsername(obj.get("username").toString());
                u.setNum_telephone(Integer.parseInt(obj.get("num_telephone").toString()));
                u.setRoles(obj.get("roles").toString());
                listEtudiants.add(u);

            }

        } catch (IOException ex) {
        }

        return listEtudiants.get(0);

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }


    public static User getUserCon() {
        return userCon;
    }

    public static void setUserCon(User userCon) {
        Profil.userCon = userCon;
    }

    public Resources getTheme() {
        return theme;
    }

    public void setTheme(Resources theme) {
        this.theme = theme;
    }
       




    
}
