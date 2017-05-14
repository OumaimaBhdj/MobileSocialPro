package com.pi.socialpro;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Toolbar;
import com.pi.socialpro.controllers.Login;


public class MyApplication {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        Toolbar.setGlobalToolbar(true);

    }
    
    public void start() {
       
       
         /*   authentification ath = new authentification(theme);
             ath.getF().show(); */
         new Login(theme).getF().show();
                   
        /*
        recherche_Etat ath = new recherche_Etat(theme);
             ath.getF().show();*/
        
        
       /*camera c = new camera(theme);
       c.getF().show();*/
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    public void destroy() {
    }
}
