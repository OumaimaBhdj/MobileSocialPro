/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.pi.socialpro.entites.Evenement;
import com.pi.socialpro.entites.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author azizbessrour
 */
public class AddEvent {

    Form f = new Form("Bienvenue a social pro", BoxLayout.y());
    Image eventPicture;

    public static User userCon;
    String baz;
    private Resources theme;
    Picker datedebut, datefin;
    String result;
    Image capturedImage; 
    ImageViewer v;
    public AddEvent(Resources theme) {
        this.theme = theme;
        eventPicture = theme.getImage("event.jpg").scaled(200, 200);
        v = new ImageViewer(eventPicture);
        f.add(v);
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        f.add(nom);

        f.getToolbar().addCommandToLeftBar("Back", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new EventsList(theme).start();
            }
        });
        TextField categorie = new TextField("", "Categorie", 20, TextField.ANY);

        f.add(categorie);

        TextField nbrelimite = new TextField("", "Nombre de places ", 20, TextField.NUMERIC);
        f.add(nbrelimite);

        datedebut = new Picker();
        
        datedebut.setFormatter(new SimpleDateFormat("yyyy/MM/dd"));

        datefin = new Picker();
        datefin.setFormatter(new SimpleDateFormat("yyyy/MM/dd"));
        f.add(datedebut);
        f.add(datefin);
        TextField lieu = new TextField("", "Lieu ", 20, TextField.ANY);
        f.add(lieu);

        TextField email = new TextField("", "Description ", 20, TextField.ANY);
        f.add(email);

        Button choosePic = new Button("Choose Picture");
        f.add(choosePic);
        Button btnAdd = new Button("Ajouter");
        btnAdd.setVisible(false);
        f.add(btnAdd);
        
        Button btnDes = new Button("Annuler");
        f.add(btnDes);

        choosePic.addActionListener(e -> {
            eventPicture = captureRoundImage();
            v.setImage(eventPicture.scaled(200, 200));
            btnAdd.setVisible(true);

        });
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                ConnectionRequest req = new ConnectionRequest();


                System.out.println("http://localhost/socialpromobile/addevent.php?nom=" + nom.getText() + "&categorie=" + categorie.getText() + "&nbrelimite=" + nbrelimite.getText()
                        + "&datedebut=" + datedebut.getText() + "&datefin=" + datefin.getText() + "&description=" + email.getText() + "&user_id=" + Login.userCon.getId() + "&baz=" + baz + "&lieu=" + lieu.getText());
                req.setUrl("http://localhost/socialpromobile/addevent.php?nom=" + nom.getText() + "&categorie=" + categorie.getText() + "&nbrelimite=" + nbrelimite.getText()
                        + "&datedebut=" + datedebut.getText() + "&datefin=" + datefin.getText() + "&description=" + email.getText() + "&user_id=" + Login.userCon.getId() + "&lieu=" + lieu.getText());

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (s.equals("DATE ERROR")) {
                            Dialog.show("ALERTE", "CHECK DATES", "Ok", null);
                        } else {
                            if (!s.equals("error")) {
                                uploadImageByMayma(s);
                                Dialog.show("Confirmation", "ajout ok", "Ok", null);

                                //NewsfeedForm.event = getEvent(s);
                                // new ConsultEvent(res).show();
                            } else {
                                Dialog.show("Confirmation", "Add Event failed", "Ok", null);
                            }
                        }

                    }
                });

                NetworkManager.getInstance().addToQueue(req);

                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        Button btnConsult = new Button("Consulter mes evenements");

        f.add(btnConsult);
        btnConsult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

              new MyEvents(theme).start();
            }
        });

    }

 
    private Image captureRoundImage() {
        try {
            int width = eventPicture.getWidth();
            result = Capture.capturePhoto(width, -1);
            if (result == null) {
                return eventPicture;
            }
            //bufImage = ImageIO.read(new File(result));
            capturedImage = Image.createImage(result);
            if (capturedImage.getHeight() != width) {
                if (capturedImage.getWidth() < capturedImage.getHeight()) {
                    capturedImage = capturedImage.subImage(0, capturedImage.getHeight() / 2 - width / 2, width, width, false);
                } else {
                    Image n = Image.createImage(width, width);
                    n.getGraphics().drawImage(capturedImage, 0, width / 2 - capturedImage.getHeight() / 2);
                    capturedImage = n;
                }
            }
            return capturedImage;
        } catch (IOException err) {
            err.printStackTrace();
            return eventPicture;
        }

    }
    public void uploadImageByMayma(String id) {

        try {

            MultipartRequest request = new MultipartRequest();
            request.setUrl("http://localhost/socialpromobile/uploadImageEvent.php");

            request.addArgument("nom", id);
            request.addData("file", result, "image/jpg");

            NetworkManager.getInstance().addToQueue(request);
            System.out.println("HERE WSELT");
        } catch (IOException ex) {
            //Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    public static User getUserCon() {
        return userCon;
    }

    public static void setUserCon(User userCon) {
        AddEvent.userCon = userCon;
    }
    
    
    
}
