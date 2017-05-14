/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Yass
 */
public class Mailling {

    Form MailForm;

    public Mailling(Resources theme) {

        MailForm = new Form("Mail", BoxLayout.y());
        Container c1 = new Container(BoxLayout.x());
        Container c2 = new Container(BoxLayout.y());
        Button env = new Button("Envoyer");
        TextField tfmail = new TextField("", "mail@domain.com", 20, TextField.ANY);

        TextField tfdesc = new TextField("", "description", 20, TextArea.ANY);
        c2.add(tfmail).add(tfdesc).add(env);
        c1.add(c2);
        MailForm.add(c1);
        MailForm.refreshTheme();
        env.addActionListener(new ActionListener() {
            private Object nmail;

            @Override
            public void actionPerformed(ActionEvent evt) {

                try {
                    Properties props = new Properties();
                    props.put("mail.transport.protocol", "smtp");
                    props.put("mail.smtps.host", "smtp.gmail.com");
                    props.put("mail.smtps.auth", "true");
                    Session session = Session.getInstance(props, null);

                    MimeMessage msg = new MimeMessage(session);

                    msg.setFrom(new InternetAddress("SocialPro <my_email@myDomain.com>"));
                    msg.setRecipients(Message.RecipientType.TO, tfmail.getText());
                    msg.setSubject("Bienvenue sur SocialPro ");
                    msg.setSentDate(new Date(System.currentTimeMillis()));

                    String txt = "Bonjour,\n " + tfdesc.getText() + "\n Cordialement";

                    msg.setText(txt);
                    SMTPTransport st = (SMTPTransport) session.getTransport("smtps");
                    st.connect("smtp.gmail.com", "yasminebeya.bennani@esprit.tn", "12800067**//");
                    st.sendMessage(msg, msg.getAllRecipients());
                    System.out.println("ServerResponse : " + st.getLastServerResponse());
                    new ListeCongeAttente(theme).getF().show();

                } catch (MessagingException ex) {
                }

            }
        });
        MailForm.getToolbar().addMaterialCommandToSideMenu("Les congÈs en attente",
                FontImage.MATERIAL_LIST, e -> new ListeCongeAttente(theme).getF().show());
        MailForm.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

          
         @Override
         public void actionPerformed(ActionEvent evt) {
      Login.userCon=null;
        new Login(theme).getF().show();
            }
        });

    }

    public Form getF() {
        return MailForm;
    }

    public void setF(Form f) {
        this.MailForm = f;
    }

    public void show() {

        MailForm.show();
    }
}
