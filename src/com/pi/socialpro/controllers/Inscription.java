/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.pi.socialpro.entites.BCrypt;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.pi.socialpro.entites.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.io.IOException;

/**
 *
 * @author Oumaima
 */
public class Inscription {

    public static final String ACCOUNT_SID = "AC8690552d691d1b8662e7f9e9568c043e";
    public static final String AUTH_TOKEN = "785a98e86a9eb861e4ccbfdd0108c61d";

    Image userPicture;
    //BufferedImage bufImage;
    TextField username, nom1, pre, email, pwd, confPwd, phone;
    Image capturedImage;
    String result;
    Boolean usrBool = false;
    Boolean mailBool = false;
    ComboBox cb ;
    Form f;
    ImageViewer v;
    String role;

    public static User userCon;
    Button inscrip;
    private Resources theme;

    public Inscription(Resources theme) {
        this.theme=theme;
        userPicture = theme.getImage("round.png").scaled(200, 200);
         f = new Form("Inscription", BoxLayout.y());
        Button btnChooseImage = new Button("Choose Image");
        v = new ImageViewer(roundImage(theme.getImage("def.jpg").scaled(150, 150)));
        nom1 = new TextField("", "Nom", 100, TextField.ANY);
        pre = new TextField("", "Prenom", 100, TextField.ANY);
        username = new TextField("", "Username", 100, TextField.ANY);
        email = new TextField("", "E-Mail", 100, TextField.EMAILADDR);
        Validator valid = new Validator();
        valid.addConstraint(email, RegexConstraint.validEmail());
        valid.setShowErrorMessageForFocusedComponent(true);
        pwd = new TextField("", "Mot de passe", 100, TextField.PASSWORD);
        confPwd = new TextField("", "Confirmer mot de passe", 100, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirm Password", 100, TextField.PASSWORD);
        phone = new TextField("", "Telephone", 100, TextField.NUMERIC);
        cb = new ComboBox();
        cb.addItem("simple user");
        cb.addItem("chef");
        
        
        

        f.add(v);
        f.add(nom1);
        f.add(pre);
        f.add(username);
        f.add(email);
        f.add(pwd);
        f.add(confPwd);
        f.add(phone);
        f.add(cb);
        
        inscrip = new Button("inscription");
        Button annuler = new Button("Annuler");
        f.add(btnChooseImage);
        inscrip.setVisible(false);
        f.add(inscrip);
        
        f.add(annuler);

        username.addDataChangedListener(new DataChangedListener() {

            @Override
            public void dataChanged(int type, int index) {
                System.out.println("mayma : " + username.getText());
                ConnectionRequest req = new ConnectionRequest();
                    req.setUrl("http://localhost/socialpromobile/username.php?username="+username.getText() );
                            
                    req.addResponseListener(new ActionListener<NetworkEvent>() {

                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            byte[] data = (byte[]) evt.getMetaData();
                            String s = new String(data);

                            System.out.println("Server Response : " + s);
                            if (s.equals("used")) {
                                username.setText(username.getText()+" : ce psuedo n'est pas valable");
                                usrBool = false;

                            } else {
                                usrBool = true;
                                
                            }
                        }
                    });

                    NetworkManager.getInstance().addToQueue(req);

            }
        });

        email.addDataChangedListener(new DataChangedListener() {

            @Override
            public void dataChanged(int type, int index) {
                ConnectionRequest req = new ConnectionRequest();
                    req.setUrl("http://localhost/socialpromobile/email.php?email="+email.getText() );
                            
                    req.addResponseListener(new ActionListener<NetworkEvent>() {

                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            byte[] data = (byte[]) evt.getMetaData();
                            String s = new String(data);

                            System.out.println("Server Response mail : " + s);
                            if (s.equals("used")) {
                                email.setText(email.getText()+" : cet email n'est pas valable");
                                mailBool = false;
                                
                            
                            } else {
                                mailBool = true;
                            }
                        }
                    });

                    NetworkManager.getInstance().addToQueue(req);

            }
        });
        
        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Login(theme).getF().show();
                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        inscrip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if (cb.getSelectedIndex() == 0){
                    role = "a:0:{}";
                }else{
                    role = "a:1:{i:0;s:9:\"ROLE_CHEF\";}";
                }
                
                 if (pwd.getText().equals(confPwd.getText()) && phone.getText().length() == 8 && (username.getText().length() > 0) && usrBool && mailBool) {

                    ConnectionRequest req = new ConnectionRequest();
                    req.setUrl("http://localhost/socialpromobile/inscri.php?nom=" + nom1.getText() + "&pre=" + pre.getText() + "&username=" + username.getText() + "&email=" + email.getText() + "&pwd=" + hashPassword(pwd.getText()) + "&phone=" + phone.getText()+"&role="+role);

                    req.addResponseListener(new ActionListener<NetworkEvent>() {

                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            byte[] data = (byte[]) evt.getMetaData();
                            String s = new String(data);

                            
                            System.out.println("Server Response : " + s);
                            if (s.equals("success")) {
                                //Dialog.show("Confirmation", "ajout ok", "Ok", null);
                                uploadImageByMayma();
                                new Login(theme).getF().show();
                               
                                
                                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

                                Message message = Message.creator(new com.twilio.type.PhoneNumber("+21650911705"),
                                        new com.twilio.type.PhoneNumber("(201) 380-7485"),
                                        " : Félicitations !" + nom1.getText() + " " + pre.getText() + " Vous etes maintenant inscrit dans la plateforme Social Pro !").create();
                                System.out.println(message.getSid());
                            } else {
                                Dialog.show("Confirmation", "Inscri failed here", "Ok", null);
                            }
                        }
                    });

                    NetworkManager.getInstance().addToQueue(req);

                } else {
                    Dialog.show("Confirmation", "Verifiez vos coordonnées", "OK", null);
                }

            }

        });

        //1) img picker
        btnChooseImage.addActionListener(e -> {
            userPicture = captureRoundImage();
            v.setImage(userPicture);
            inscrip.setVisible(true);
        });
          
       //f.show();

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

    private Image captureRoundImage() {
        try {
            int width = userPicture.getWidth();
            result = Capture.capturePhoto(width, -1);
            if (result == null) {
                return userPicture;
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
            return roundImage(capturedImage);
        } catch (IOException err) {
            err.printStackTrace();
            return userPicture;
        }

    }
    
    //2) Request to PHP 
        public void uploadImageByMayma() {

        try {

            MultipartRequest request = new MultipartRequest();
            request.setUrl("http://localhost/socialpromobile/uploadImage.php");

            request.addArgument("username", username.getText());
            request.addData("file", result, "image/jpg");

            NetworkManager.getInstance().addToQueue(request);
        } catch (IOException ex) {
            //Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    public Image getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(Image userPicture) {
        this.userPicture = userPicture;
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public TextField getNom1() {
        return nom1;
    }

    public void setNom1(TextField nom1) {
        this.nom1 = nom1;
    }

    public TextField getPre() {
        return pre;
    }

    public void setPre(TextField pre) {
        this.pre = pre;
    }

    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public TextField getPwd() {
        return pwd;
    }

    public void setPwd(TextField pwd) {
        this.pwd = pwd;
    }

    public TextField getConfPwd() {
        return confPwd;
    }

    public void setConfPwd(TextField confPwd) {
        this.confPwd = confPwd;
    }

    public TextField getPhone() {
        return phone;
    }

    public void setPhone(TextField phone) {
        this.phone = phone;
    }

    public Image getCapturedImage() {
        return capturedImage;
    }

    public void setCapturedImage(Image capturedImage) {
        this.capturedImage = capturedImage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Boolean getUsrBool() {
        return usrBool;
    }

    public void setUsrBool(Boolean usrBool) {
        this.usrBool = usrBool;
    }

    public Boolean getMailBool() {
        return mailBool;
    }

    public void setMailBool(Boolean mailBool) {
        this.mailBool = mailBool;
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
        Inscription.userCon = userCon;
    }

    public Resources getTheme() {
        return theme;
    }

    public void setTheme(Resources theme) {
        this.theme = theme;
    }
    
    private final int workload = 13;

 public String hashPassword(String password) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password, salt);
        String FixedPass = hashed_password.substring(0, 2) + 'y' + hashed_password.substring(3);
        return (FixedPass);
    }
}
