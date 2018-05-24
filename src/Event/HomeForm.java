/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zanimaux.Event;

import com.codename1.capture.Capture;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codename1.uikit.cleanmodern.SignInForm;
import com.codename1.util.Callback;
import com.esprit.services.ServiceEvenement;

import com.esprit.entities.Evenement;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author sana
 */
public class HomeForm extends BaseForm {

    Form f;
    Form ha;
    TextField tnom;
    TextField tetat;
    TextField tnbrmax;
    TextField tlieu;
    TextField ttheme;
    TextField timage;

    TextField tdescription;
    Button btnajout, btnprecedant, btnaff;

    public HomeForm(Resources res) {

        super("ajout", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");

        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        TextField tnom = new TextField();

        TextField tlieu = new TextField("", "Username", 20, TextArea.ANY);
        TextField ttheme = new TextField("", "theme", 20, TextArea.ANY);
        TextField timage = new TextField("", "image", 20, TextArea.ANY);
        TextField tnbrmax = new TextField("", "nbrmax", 20, TextArea.ANY);
        TextField tdescription = new TextField("", "description", 20, TextArea.ANY);
        tnom.setUIID("TextFieldBlack");
        tlieu.setUIID("TextFieldBlack");
        timage.setUIID("TextFieldBlack");
        tnbrmax.setUIID("TextFieldBlack");
        tdescription.setUIID("TextFieldBlack");
        ttheme.setUIID("TextFieldBlack");
        Label lnom = new Label("nom evenement");
        Label ltheme = new Label("theme evenement");
        Label llieu = new Label("lieu evenement");
        Label limage = new Label("image evenement");
        Label lnbrmax = new Label("nbrmax evenement");

        String[] characters = {"Végétarisme",
            "sante animaux",
            "Animaux marins en captivité",
            "Animaux de compagnie ",
            "Chasse ",
            "Cirque ",
            "Corrida ",
            "Droit des animaux ",
            "Élevage",
            "Expérimentation animale",
            "Fourrure",
            "course",
            "Poules pondeuses",
            "Lait",
            "animaux en disparition"
        };

        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);

        datePicker.setDate(new Date());

        datePicker.setUIID("TextFieldBlack");
        Label ldescription = new Label("description evenement");

        btnajout = new Button("ajouter");

        btnprecedant = new Button("precedant");

        add(btnprecedant);
        add(lnom);
        add(tnom);

        add(llieu);
        add(tlieu);

        AutoCompleteTextField act = new AutoCompleteTextField(characters);
        act.addActionListener(e -> ToastBar.showMessage("You picked " + act.getText(), FontImage.MATERIAL_INFO));
        Button down = new Button();
        FontImage.setMaterialIcon(down, FontImage.MATERIAL_KEYBOARD_ARROW_DOWN);
        act.setUIID("TextFieldBlack");
        add(
                BorderLayout.center(act).
                        add(BorderLayout.EAST, down));
        down.addActionListener(e -> act.showPopup());

        add(lnbrmax);
        add(tnbrmax);
        add(ldescription);
        add(tdescription);

        add(datePicker);

        add(limage);
        add(timage);
        timage.setEditable(false);
        Button upload = new Button("upload");

        add(upload);
        add(btnajout);
        btnprecedant.addActionListener(e -> new Affichage(res).show());
        upload.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ev) {

                String filePath = Capture.capturePhoto();
                if (filePath != null) {
                    try {
                        String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + System.currentTimeMillis() + ".jpg";
                        Image img = Image.createImage(filePath);
                        OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                        ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_JPEG, 0.9f);
                        os.close();

                        timage.setText(pathToBeStored);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        int id_user = SignInForm.getIdU();

        btnajout.addActionListener((e) -> {
            ServiceEvenement ser = new ServiceEvenement();

            if (tnom.getText().equals("") || act.getText().equals("") || tlieu.getText().equals("") || timage.getText().equals("") || tdescription.getText().equals("") || tnbrmax.getText().equals("")) {
                Dialog.show("erreur", "champs vide", "OK", "cancel");
            } else {
                   Evenement m = new Evenement(tnom.getText(), act.getText(), tlieu.getText(), 0, Integer.parseInt(tnbrmax.getText()), timage.getText(), tdescription.getText(), datePicker.getDate());

                    ser.ajoutevenement(m);
                                    Dialog.show("succes", "evenement a ete ajoute", "OK", "cancel");

              //  boolean b = test();
               /* if (b == true) {
                    Evenement m = new Evenement(tnom.getText(), act.getText(), tlieu.getText(), 0, Integer.parseInt(tnbrmax.getText()), timage.getText(), tdescription.getText(), datePicker.getDate(), id_user);
                    String a = tnbrmax.getText();
                    ser.ajoutevenement(m);

                } else {
                    Dialog.show("erreur", "champs vide", "OK", "cancel");
                */
            }
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

    public boolean test() {

        String s = tnbrmax.getText();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                return true;
            } else {
                return true;
            }
        }
        return false;
    }
}
