
package com.pi.socialpro.controllers;;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

  
public class AjouterTache {
    
      Form ajouterTache ;
 
 public AjouterTache(Resources theme){
     
 
       ajouterTache = new Form("Ajouter Une Tâche", BoxLayout.y());
      
     
      Container ajout = new Container(BoxLayout.y());
        Label db=new Label("Date Début");
       Picker datedebut = new Picker();
       datedebut.setType(Display.PICKER_TYPE_DATE);
        Label df=new Label("Date Fin");
           Picker datefin = new Picker();
       datefin.setType(Display.PICKER_TYPE_DATE);
       
       
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                datedebut.setFormatter(format);
                
                SimpleDateFormat formatin = new SimpleDateFormat("yyyy-MM-dd");
                datefin.setFormatter(formatin);
                
        Label desc=new Label("Description");
         TextField description = new TextField("", "Description", 20, TextArea.ANY);
        Label et=new Label("Etat");
        ComboBox etat = new ComboBox();
        etat.addItem("pas encore realisee");
        Button btnAjouter = new Button("Ajouter");
        ajout.add(db).add(datedebut).add(df).add(datefin).add(desc).add(description).add(et).add(etat);
        ajouterTache.add(ajout).add(btnAjouter);
        
        
       
        
         btnAjouter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/social_pro/insert.php?datedebut=" + datedebut.getText() + "&datefin=" + datefin.getText() + "&description=" + description.getText() + "&etat="+etat.getSelectedItem()+"");

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "Votre Tâche a été ajouter avec succées", "Ok", null);
                            TacheNonAffecte tnaff = new TacheNonAffecte(theme);
                            tnaff.getF().show();
                            
                        }
                    }
                });
                
                NetworkManager.getInstance().addToQueue(req);
            }
        }); 
         
          ajouterTache.getToolbar().addCommandToSideMenu("Acceuil", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           Acceuil_chef about = new Acceuil_chef(theme);
           about.getF().show();
            }
        });
         
           ajouterTache.getToolbar().addCommandToSideMenu("Tâche Afféctées", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           TacheAffecte about = new TacheAffecte(theme);
           about.getF().show();
            }
        });
           
           ajouterTache.getToolbar().addCommandToSideMenu("Tâche Non Afféctées", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           TacheNonAffecte about = new TacheNonAffecte(theme);
           about.getF().show();
            }
        });
           
           
               ajouterTache.getToolbar().addCommandToSideMenu("Tache Non Réalisées", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
           TacheNonRealisee about = new TacheNonRealisee(theme);
           about.getF().show();
            }
        });
               
            ajouterTache.getToolbar().addCommandToSideMenu("Recherche", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
          recherche_Etat rech = new recherche_Etat(theme);
           rech.getF().show();
            }
        });
            
        
         ajouterTache.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
       
      Login.userCon=null;
        new Login(theme).getF().show();
         }
     });
     
           
 }

   
    public Form getF() {
        return ajouterTache;
    }

    public void setF(Form f) {
        this.ajouterTache = f;
    }
    
}
