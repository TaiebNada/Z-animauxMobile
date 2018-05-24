
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zanimaux.SOS;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.esprit.entities.Animal;
import com.esprit.services.AnimalService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

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
        Picker Taille = new Picker();
        Taille.setType(Display.PICKER_TYPE_STRINGS);
        Taille.setStrings("Petit", "Moyen", "Grand");
        Taille.setSelectedString("Male");
        
        Picker Espece = new Picker();
        Espece.setType(Display.PICKER_TYPE_STRINGS);
        Espece.setStrings("Chien", "Chat","Cheval","Oiseaux","Poisson");
        Espece.setSelectedString("Chien");
        
        Picker Sexe = new Picker();
        Sexe.setType(Display.PICKER_TYPE_STRINGS);
        Sexe.setStrings("Male", "Female");
        Sexe.setSelectedString("Male");

        TextField Race = new TextField("", "Race", 20, TextField.ANY);
        TextField Description = new TextField("", "Description", 20, TextField.ANY);
        TextField Numero = new TextField("", "Numero", 20, TextField.NUMERIC);
        TextField Nom = new TextField("", "Nom", 20, TextField.ANY);
        Picker d =new Picker();
        d.setType(Display.PICKER_TYPE_DATE);
        d.getFormatter();
        d.setDate(new Date());
        Label image = new Label();
        Label ltaille = new Label("Taille");
        Label lespece = new Label("Espece");
        Label lsexe = new Label("Sexe");
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
        Race.setSingleLineTextArea(false);
        Description.setSingleLineTextArea(false);
        Nom.setSingleLineTextArea(false);
        Numero.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        Espece.setUIID("TextFieldBlack");
        Race.setUIID("TextFieldBlack");
        Sexe.setUIID("TextFieldBlack");
        Taille.setUIID("TextFieldBlack");
        Nom.setUIID("TextFieldBlack");
        Numero.setUIID("TextFieldBlack");
        Description.setUIID("TextFieldBlack");
        d.setUIID("TextFieldBlack");
        
        
        signIn.setUIID("Link");
        Button alreadHaveAnAccount = new Button("Retour Au Menu");
        alreadHaveAnAccount.addActionListener((evt) -> {
            new Adoption(res).show();
        });
        /*
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
         */
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                lespece,
                Espece,
                new FloatingHint(Race),
                createLineSeparator(),lsexe,
                Sexe,
                new FloatingHint(Description),
                createLineSeparator(),
                new FloatingHint(Nom),
                createLineSeparator(),d,ltaille,
                Taille,
                new FloatingHint(Numero),
                createLineSeparator(), next, upload, alreadHaveAnAccount
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
            if (filePath.isEmpty()) {
                filePath = "C:\\Users\\ADMIN\\Documents\\NetBeansProjects\\Zanimaux\\src\\image\\animal.png";
            }
            if ( Race.getText().isEmpty() || Description.getText().isEmpty() || Numero.getText().isEmpty()) {
                Dialog.show("Champs vide", "Tout Les Champs doit etre Remplie", "Ok", "Cancel");

            } else if (Numero.getText().length() != 8) {

                Dialog.show("Erreur Numero", "Le Numero Est Invalide", "Ok", "Cancel");
            } 
            else {
                AnimalService ser = new AnimalService();
                Animal a = new Animal(Espece.getSelectedString(), Race.getText(), Sexe.getSelectedString(), Nom.getText(), Taille.getSelectedString(), Description.getText(), "En Attente", filePath,Integer.parseInt(Numero.getText()),d.getDate());
                ser.ajoutTask(a);
                System.out.println(d.getDate());

                new Adoption(res).show();
            }

        });

    }

}
