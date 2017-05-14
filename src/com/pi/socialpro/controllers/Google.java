/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.codename1.social.GoogleConnect;
import com.codename1.social.LoginCallback;

/**
 *
 * @author Navoxx
 */
public class Google {
    
    
    public void GoogleConnect(){
        
      String clientId = "197708199948-vv66f8vkghute277mq9eg1v6272fvmkf.apps.googleusercontent.com";
                String redirectURI = "https://mail.google.com/mail/";
                String clientSecret = "NqCs3Wn6yQg5KmQ4G6gtAnKi";
                GoogleConnect gc = GoogleConnect.getInstance();
                gc.setClientId(clientId);
     gc.setRedirectURI(redirectURI);
     gc.setClientSecret(clientSecret);
   gc.setScope("profile email https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.me");

         LoginCallback lc = new LoginCallback() {
        };

        gc.setCallback(lc);
 if(!gc.isUserLoggedIn()){
                    gc.doLogin();
                }else{
                    //get the token and now you can query the gplus API
                    String token = gc.getAccessToken().getToken();
                   
                }
                
    }

}
