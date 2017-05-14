/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ahmed
 */
public class AffichageForm extends Form{
    
    
    public AffichageForm() {
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
       
        this.setTitle("La liste des Publication");
    }
    
}
