package com.pi.socialpro.controllers;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.twilio.Twilio;
import com.twilio.base.Resource;
import com.twilio.type.PhoneNumber;



public class SMS {
    
     public static final String ACCOUNT_SID = "AC829ab1b92a62916667de2200ac6db1ee";
    public static final String AUTH_TOKEN = "b6d08a4949465f2ff850fbb8dd534ac0";
    Form sms;
    SMS(Resources theme)
    {
       Label n= new Label("\n");
         Label n2= new Label("\n");
           Label n3= new Label("\n");
       
          sms = new Form("Envoyer SMS", BoxLayout.y());
           Label l = new Label("Veuillez décrire vos lacunes ... ");
          TextField msg = new TextField("", "Description", 20, TextArea.ANY);
           Button Envoyer = new Button("Envoyer");
           
           sms.add(n).add(n2).add(l).add(msg).add(n3).add(Envoyer);
           sms.getToolbar().addCommandToLeftBar(null,theme.getImage("cal_left_arrow.png"),(a)->{
               TacheLoggedUser t= new TacheLoggedUser(theme);
               t.getF().showBack();
            });
           sms.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         
         @Override
         public void actionPerformed(ActionEvent evt) {
      Login.userCon=null;
        new Login(theme).getF().show();
         }
     });
           Envoyer.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent evt) {
                  Twilio.init("AC829ab1b92a62916667de2200ac6db1ee", "b6d08a4949465f2ff850fbb8dd534ac0");
                                com.twilio.rest.api.v2010.account.Message message =
                               com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+21627404323"),
                                new PhoneNumber("+15612885165"),msg.getText()).create();
                              
                                
                                Dialog.show("Confirmation", "Message à été envoyer avec succées ,"
                                       + " Votre chef de projet se chargera de vous répondre le plus proche possible", "Ok", null);
                            TacheLoggedUser tchl = new  TacheLoggedUser(theme);
                            tchl.getF().show();
              }
          });
        
   /**/
                           
    
    }

     public Form getF() {
        return sms;
    }
    
}
