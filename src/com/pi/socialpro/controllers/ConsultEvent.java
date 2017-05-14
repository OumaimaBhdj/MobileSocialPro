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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.util.UIBuilder;
import com.pi.socialpro.entites.Evenement;
import com.pi.socialpro.entites.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Oumaima
 */
public class ConsultEvent {

    Form f = new Form("Bienvenue a social pro", BoxLayout.y());

    public static User userCon;

    private Resources theme;
    TextField prenom, nom, username, phone, datefin, email;
    Button participer, neplusparticiper, update, delete;
    ImageViewer v;

    public ConsultEvent(Resources theme) {
        this.theme = theme;
        
          
        v = new ImageViewer(theme.getImage("round.png"));
        
        EncodedImage encoded = EncodedImage.createFromImage(theme.getImage("round.png").scaled(250, 250), false);

            URLImage urlImage = URLImage.createToStorage(encoded, "Medium_" + EventsList.event.getImage(), EventsList.event.getImage(), URLImage.RESIZE_SCALE);
            v.setImage(urlImage);
            f.add(v);

        participer = new Button("Participer");
        neplusparticiper = new Button("Ne plus Participer");
        update = new Button("Modifier");
        delete = new Button("Supprimer");

        if (Login.userCon.getId() == EventsList.event.getUser_id()) {

            prenom = new TextField(EventsList.event.getNom());
            f.add(prenom);

            nom = new TextField(EventsList.event.getCategorie());

            f.add(nom);

            username = new TextField("" + EventsList.event.getNbrelimite());
            f.add(username);

            phone = new TextField("" + EventsList.event.getDatedebut());
            f.add(phone);

            datefin = new TextField("" + EventsList.event.getDatefin());
            f.add(datefin);

            email = new TextField(EventsList.event.getDescription());
            f.add(email);

            f.add(update);
            f.add(delete);

        } else {
            Label prenom = new Label(EventsList.event.getNom());
            f.add(prenom);

            Label nom = new Label(EventsList.event.getCategorie());

            f.add(nom);

            Label username = new Label("" + EventsList.event.getNbrelimite());
            f.add(username);

            Label phone = new Label("" + EventsList.event.getDatedebut());
            f.add(phone);

            Label datefin = new Label("" + EventsList.event.getDatefin());
            f.add(datefin);

            Label email = new Label(EventsList.event.getDescription());
            f.add(email);
            if (EventsList.event.getNbrelimite() > 0) {

                f.add(participer);
                f.add(neplusparticiper);
                checkParticipation();
            } else {

                f.add(participer);
                f.add(neplusparticiper);
                participer.setVisible(false);
                neplusparticiper.setVisible(false);
            }
        }
        
         f.getToolbar().addCommandToLeftBar("Back", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
          new EventsList(theme).start();
            }
        });

        neplusparticiper.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/socialpromobile/neplusparticiper.php?userid=" + Login.userCon.getId() + "&idevenement=" + EventsList.event.getId() + "&nbrelimite=" + EventsList.event.getNbrelimite()
                        + "&nbrparticipants=" + EventsList.event.getNbrparticipants());

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (!s.equals("0 results")) {
                            Dialog.show("Confirmation", "Participation avec succes", "Ok", null);
                            //checkParticipation();
                            EventsList.event = getEvent(s);
                            new ConsultEvent(theme).getF().show();

                        } else {
                            Dialog.show("Confirmation", " Participation echouée", "Ok", null);
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req);

                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        participer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/socialpromobile/participer.php?userid=" + Login.userCon.getId() + "&idevenement=" + EventsList.event.getId() + "&nbrelimite=" + EventsList.event.getNbrelimite()
                        + "&nbrparticipants=" + EventsList.event.getNbrparticipants());

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println("Response Participer.php : " + s);
                        if (!s.equals("0 results")) {
                            Dialog.show("Confirmation", "Participation avec succes", "Ok", null);
                            //checkParticipation();
                            EventsList.event = getEvent(s);
                            new ConsultEvent(theme).getF().show();

                        } else {
                            Dialog.show("Confirmation", " Participation echouée", "Ok", null);
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req);

                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/socialpromobile/updateevent.php?id=" + EventsList.event.getId() + "&nom=" + prenom.getText() + "&categorie=" + nom.getText() + "&nbrelimite=" + username.getText()
                        + "&datedebut=" + phone.getText() + "&datefin=" + datefin.getText() + "&description=" + email.getText());

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println("Response Participer.php : " + s);
                        if (!s.equals("0 results")) {
                            Dialog.show("Confirmation", "Modification avec succées ", "Ok", null);
                            //checkParticipation();
                            EventsList.event = getEvent(s);
                            new ConsultEvent(theme).getF().show();

                        } else {
                            Dialog.show("Confirmation", " Modification ", "Ok", null);
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req);

                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/socialpromobile/deleteevent.php?id=" + EventsList.event.getId());

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println("Response delete.php : " + s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "delete ok", "Ok", null);

                            new EventsList(theme).getF().show();

                        } else {
                            Dialog.show("Confirmation", "delete failed", "Ok", null);
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req);

                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

    }

    public void checkParticipation() {

        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/socialpromobile/isparticipant.php?iduser=" + Login.userCon.getId() + "&idevent=" + EventsList.event.getId());

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);

                System.out.println(" " + s);
                if (s.equals("ok")) {
                    //Dialog.show("Confirmation", "ajout ok", "Ok", null);
                    participer.setVisible(false);
                    neplusparticiper.setVisible(true);
                } else {
                    participer.setVisible(true);
                    neplusparticiper.setVisible(false);
                }
            }
        });

        NetworkManager.getInstance().addToQueue(req);

    }

    public Evenement getEvent(String json) {
        ArrayList<Evenement> listEtudiants = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("events");

            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();
                e.setId(Integer.parseInt(obj.get("id").toString()));
                e.setNom(obj.get("nom").toString());
                e.setDescription(obj.get("description").toString());

                String pattern = "yyyy-MM-dd";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                try {
                    Date dateDeb = format.parse(obj.get("datedebut") + "");
                    Date dateFin = format.parse(obj.get("datefin").toString());
                    e.setDatedebut(dateDeb);
                    e.setDatefin(dateFin);

                } catch (ParseException exxx) {
                    exxx.printStackTrace();
                }

                e.setUser_id(Integer.parseInt(obj.get("user_id").toString()));
                e.setCategorie(obj.get("categorie").toString());
                e.setNbrparticipants(Integer.parseInt(obj.get("nbrparticipants").toString()));
                e.setNbrelimite(Integer.parseInt(obj.get("nbrelimite").toString()));
                e.setImage(obj.get("image").toString());
                e.setLieu(obj.get("lieu").toString());
                listEtudiants.add(e);

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

    public Resources getTheme() {
        return theme;
    }

    public void setTheme(Resources theme) {
        this.theme = theme;
    }

}
