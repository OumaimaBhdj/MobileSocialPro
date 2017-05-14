/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.forms;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;



/**
 *
 * @author Navoxx
 */
public class LoginForm extends Form{
    
      public LoginForm()  {
 
     this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
      setUIID("SignIn");
         this.setTitle("Login");
      }
    
}
