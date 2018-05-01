
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zanimaux.SOS;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.esprit.entities.Animal;
import com.esprit.services.AnimalService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 *
 * @author ADMIN
 */
public class Adheration extends BaseForm {
    
    static int id;
    Form f;
    static String filePath;
    
    public Adheration(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        TextField Espece = new TextField("", "Espece", 20, TextField.ANY);
        TextField Race = new TextField("", "Race", 20, TextField.ANY);
        TextField Sexe = new TextField("", "Sexe", 20, TextField.ANY);
        TextField Taille = new TextField("", "Taille", 20, TextField.ANY);
        TextField Description = new TextField("", "Description", 20, TextField.ANY);
        TextField Nom = new TextField("", "Nom", 20, TextField.ANY);
        Label image = new Label();
        
        Button upload = new Button("upload");
        upload.addActionListener((ActionListener) (ActionEvent ev) -> {
            filePath = Capture.capturePhoto();
            
            if (filePath != null) {
                try {
                    String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + System.currentTimeMillis() + ".jpg";
                    
                    Image img = Image.createImage(filePath);
                    
                    try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored)) {
                        ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_JPEG, 0.9f);
                    }
                    
                    image.setText(pathToBeStored);
                } catch (IOException e) {
                }
            }
        });
        Espece.setSingleLineTextArea(false);
        Race.setSingleLineTextArea(false);
        Sexe.setSingleLineTextArea(false);
        Taille.setSingleLineTextArea(false);
        Description.setSingleLineTextArea(false);
        Nom.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Retour Au Menu");
        Button spot = new Button("Activate Spot");
        spot.addActionListener((evt) -> {
            InfiniteProgress ip = new InfiniteProgress();
            Dialog ipDlg = ip.showInifiniteBlocking();
            Location location = LocationManager.getLocationManager().getCurrentLocationSync(30000);
            ipDlg.dispose();
            if (location == null) {
                try {
                    location = LocationManager.getLocationManager().getCurrentLocation();
                } catch (IOException err) {
                    Dialog.show("Location Error", "Unable to find your current location, please be sure that your GPS is turned on", "OK", null);
                    return;
                }
            }
            Double loc1 = location.getLatitude();
            Double loc2 = location.getLongitude();
            Log.p("Latitude: " + loc1);
            Log.p("Longitude: " + loc2);
            AnimalService a = new AnimalService();
            a.ajoutSpot(loc1, loc2, filePath, Nom.getText());
        });
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(Espece),
                createLineSeparator(),
                new FloatingHint(Race),
                createLineSeparator(),
                new FloatingHint(Sexe),
                createLineSeparator(),
                new FloatingHint(Description),
                createLineSeparator(),
                new FloatingHint(Nom),
                createLineSeparator(),
                new FloatingHint(Taille),
                createLineSeparator(), next, upload,spot
        );
        //  content.setScrollableY(true);
        add(content);
        /* add(next);
        add(upload);*/
 /*  add( BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn),upload
        ));*/
        next.requestFocus();
        next.addActionListener((evt) -> {
            AnimalService ser = new AnimalService();
            Animal a = new Animal(Espece.getText(), Race.getText(), Sexe.getText(), Nom.getText(), Taille.getText(), Description.getText(), "En Attente", filePath);
            ser.ajoutTask(a);
            
            new Adoption(res).show();
            
        });
        
    }
    
}