/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.socialpro.controllers;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.contacts.Contact;
import com.codename1.contacts.ContactsManager;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ahmed
 */
public class Contacts {
    public Form contact(){
        Form hi = new Form("Contacts", new BoxLayout(BoxLayout.Y_AXIS));
hi.add(new InfiniteProgress());
int size = Display.getInstance().convertToPixels(5, true);
FontImage fi = FontImage.createFixed("" + FontImage.MATERIAL_PERSON, FontImage.getMaterialDesignFont(), 0xff, size, size);

Display.getInstance().scheduleBackgroundTask(() -> {
    Contact[] contacts = Display.getInstance().getAllContacts(true, true, false, true, false, false);
    Display.getInstance().callSerially(() -> {
        hi.removeAll();
        for(Contact c : contacts) {
            MultiButton mb = new MultiButton(c.getDisplayName());
            mb.setIcon(fi);
            mb.setTextLine2(c.getPrimaryPhoneNumber());
            hi.add(mb);
            mb.putClientProperty("id", c.getId());
            Display.getInstance().scheduleBackgroundTask(() -> {
                Contact cc = ContactsManager.getContactById(c.getId(), false, true, false, false, false);
                Display.getInstance().callSerially(() -> {
                    Image photo = cc.getPhoto();
                    if(photo != null) {
                        mb.setIcon(photo.fill(size, size));
                        mb.revalidate();
                    }
                });
            });
        }
        hi.getContentPane().animateLayout(150);
    });
});
return hi;
    }
    
}
