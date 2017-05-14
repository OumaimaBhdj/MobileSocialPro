

package com.pi.socialpro.controllers;


   
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class UpdateProduit {

 /*   public UpdateProduit(Resources res) {
     
 
     
        
        TextField prenom = new TextField(AffichageProduit.);
        prenom.setUIID("TextFieldBlack");
        addStringValue("Firstname", prenom);
        
        
        TextField nom = new TextField(SignInForm.userCon.getNom());
        nom.setUIID("TextFieldBlack");
        addStringValue("Lastname", nom);
        
        
        

        TextField username = new TextField(SignInForm.userCon.getUsername());
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);
        
        TextField phone = new TextField(""+SignInForm.userCon.getNum_telephone(), "Phone Number", 20, TextField.NUMERIC);
        phone.setUIID("TextFieldBlack");
        addStringValue("Phone Number", phone);

        TextField email = new TextField(SignInForm.userCon.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
       
        
        TextField password = new TextField(SignInForm.userCon.getPassword(), "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
      
       Button bntUpdate = new Button("Update");
        addButton(bntUpdate);
        
        bntUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
              
             

                    ConnectionRequest req = new ConnectionRequest();
                    System.out.println("http://localhost/socialpromobile/updateprofil.php?nom=" + nom.getText() + "&prenom=" + prenom.getText() + "&username=" + username.getText() + "&email=" + email.getText() + "&phone=" + phone.getText()+"&password=" + password.getText()+"&id="+SignInForm.userCon.getId());

                    req.setUrl("http://localhost/socialpromobile/updateprofil.php?nom=" + nom.getText() + "&prenom=" + prenom.getText() + "&username=" + username.getText() + "&email=" + email.getText() + "&phone=" + phone.getText()+"&password=" + password.getText()+"&id="+SignInForm.userCon.getId());

                    req.addResponseListener(new ActionListener<NetworkEvent>() {

                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            byte[] data = (byte[]) evt.getMetaData();
                            String s = new String(data);

                            if (!s.equals("Error")) {
                                
                            
                                
                                Dialog.show("Confirmation", "update ok", "Ok", null);

                
                            } else {
                                Dialog.show("Confirmation", "update failed", "Ok", null);
                            }
                        }
                    });

                    NetworkManager.getInstance().addToQueue(req);

              
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
       
         
        

       
       /* CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
        CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb2.setUIID("Label");
        cb2.setPressedIcon(res.getImage("on-off-on.png"));
        
        addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
        addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2)); 
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
    private void addButton(Component v) {
        add(BorderLayout.west(new Label("", "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
    public User getUser(String json) {
        ArrayList<User> listEtudiants = new ArrayList<>();

        System.out.println("+++++++++++++++++++++++"+json);
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
                listEtudiants.add(u);

            }

        } catch (IOException ex) {
        }
       
        return listEtudiants.get(0);

    }
}


*/
}