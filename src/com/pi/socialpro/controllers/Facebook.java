/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.codename1.social.FacebookConnect;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Dialog;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

/**
 *
 * @author ahmed
 */
public class Facebook {
    public void ConnectFB() {
       
String clientId = "1907718316152730";
        String redirectURI = "http://www.facebook.tn/";
        String clientSecret = "d127954eafc03492c64491f75f5569e0";
        com.codename1.social.Login fb = FacebookConnect.getInstance();
        fb.setClientId(clientId);
        fb.setRedirectURI(redirectURI);
        fb.setClientSecret(clientSecret);
        //Sets a LoginCallback listener
        LoginCallback lc = new LoginCallback() {
        };

        fb.setCallback(lc);
        //trigger the login if not already logged in
        if (!fb.isUserLoggedIn()) {
            fb.doLogin();
        } else {
            //get the token and now you can query the facebook API
            String token = fb.getAccessToken().getToken();

        }
    }
    
    public void partage(){
          String accessToken = "EAACEdEose0cBABOXVBpnZC76URLpMGNmpJX0K1ZAhueG9FNKZAc5fhRoq8tnFJ4CtcHkH6otxVs82TJuNHsYPrtUDrJIvFqlMsMbY2ZCo5oKbdOO4GrW3rXRSEuvVStEpvpZBKCOI6o3V4jLn8ebuv5EE5ilhHinHlzcN1uJyrvPzbkjE7LI0DjmakOVooswZD";
                 FacebookClient fbClient= new DefaultFacebookClient(accessToken);
                 FacebookType response = fbClient.publish("me/feed", FacebookType.class,
                 Parameter.with("message", "visiter notre Réseau social social pro :  " +"http://localhost/projetpi/web/app_dev.php/register/")
         );
                     System.out.println(" à été publiée sur FACEBOOK");
                      System.out.println("fb.com/"+response.getId());
                                                  Dialog.show("Confirmation", "partage avec succes", "Ok", null);

    }
    
}
