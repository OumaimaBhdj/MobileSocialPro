/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.pi.socialpro.entites.BCrypt;
import com.pi.socialpro.entites.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Oumaima
 */
public class Login {

    Form f = new Form("Bienvenue a social pro", BoxLayout.y());

    TextField tfusr = new TextField("", "Username", 20, TextField.ANY);
    TextField tfpwd = new TextField("", "Password", 20, TextField.PASSWORD);

    public static User userCon;

    private Resources theme;
    public static Image img;

    public Login(Resources theme) {

//        tfusr.setText("mayma2017");
//        tfpwd.setText("Mayma2017");
        this.theme = theme;

        f.add(tfusr);
        f.add(tfpwd);
        Button btnOk = new Button("Se connecter");
        Button inscrip = new Button("S'inscrire");
        f.add(btnOk);
        f.add(inscrip);

        inscrip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Inscription(theme).getF().show();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/socialpromobile/Login.php?usr=" + tfusr.getText());

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (!s.equals("0 results")) {

                            userCon = getUser(s);
                            String passwordStored = userCon.getPassword();
                            passwordStored = passwordStored.substring(4, passwordStored.length());
                            passwordStored = "$2a$" + passwordStored;
                            System.out.println("++++++ passwordStored" + passwordStored);

                            System.out.println("+++++++++" + tfpwd.getText());

                            boolean passwordMatch = checkPassword(tfpwd.getText(), passwordStored);
                            EncodedImage encoded = EncodedImage.createFromImage(theme.getImage("round.png").scaled(250, 250), false);
                            System.out.println("passwordMatch :" +passwordMatch);
                            URLImage urlImage = URLImage.createToStorage(encoded, "Medium_" + Login.userCon.getImage(), Login.userCon.getImage(), URLImage.RESIZE_SCALE);
                            img = urlImage;
                            System.out.println(userCon);
                            //  EventsList e = new EventsList(theme);
                            //  e.start(); 

                            //   Profil profil = new Profil(theme);
                            System.out.println("ROLEEE" + userCon.getRoles());
                            if (passwordMatch == true && userCon.getRoles().equalsIgnoreCase("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
                                Acceuil_admin t = new Acceuil_admin(theme);
                                t.getF().show();
                            } else if (passwordMatch == true && userCon.getRoles().equalsIgnoreCase("a:1:{i:0;s:9:\"ROLE_CHEF\";}")) {
                                Acceuil_chef t = new Acceuil_chef(theme);
                                t.getF().show();
                            } else {
                                new Acceuil_user(theme).getF().show();

                                //AddEvents ev = new AddEvents();
                                //ev.start();
                            }
                        } else {
                            Dialog.show("Confirmation", "Login failed", "Ok", null);
                        }

                    }
                });

                NetworkManager.getInstance().addToQueue(req);
            }
        });
        f.animateHierarchyFade(1000, 100);
        f.show();

    }

    public User getUser(String json) {
        ArrayList<User> listEtudiants = new ArrayList<>();

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
                u.setRoles(obj.get("roles").toString());
                u.setNum_telephone(Integer.parseInt(obj.get("num_telephone").toString()));
                listEtudiants.add(u);

            }

        } catch (IOException ex) {
        }

        return listEtudiants.get(0);

    }

    public TextField getTfusr() {
        return tfusr;
    }

    public void setTfusr(TextField tfusr) {
        this.tfusr = tfusr;
    }

    public TextField getTfpwd() {
        return tfpwd;
    }

    public void setTfpwd(TextField tfpwd) {
        this.tfpwd = tfpwd;
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public void show() {

        f.show();
    }

    private Image roundImage(Image img) {
        int width = img.getWidth();
        Image roundMask = Image.createImage(width, width, 0xff000000);
        Graphics gr = roundMask.getGraphics();
        gr.setColor(0xffffff);
        gr.fillArc(0, 0, width, width, 0, 360);
        Object mask = roundMask.createMask();
        img = img.applyMask(mask);
        return img;
    }

    public boolean checkPassword(String password, String DbHash) {
        boolean password_verified = false;
        System.out.println(DbHash);

        /*  if (null == DbHash || !DbHash.startsWith("$2a$")) {
            System.out.println(DbHash);
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }*/
        password_verified = BCrypt.checkpw(password, DbHash);
        // System.out.println(BCrypt.hashpw(password, DbHash));
//        BCrypt.hashpw(password, DbHash);
        return (password_verified);
    }
}
