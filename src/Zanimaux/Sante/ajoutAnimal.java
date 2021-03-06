/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zanimaux.Sante;

import com.codename1.capture.Capture;
import com.codename1.components.MultiButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codename1.util.Callback;
import com.esprit.entities.Animaux;
import com.esprit.entities.vet;

import com.esprit.services.AnimauxService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javafx.scene.control.DatePicker;

/**
 *
 * @author RYM
 */
public class ajoutAnimal extends BaseForm {

    Form f;

    Button btnajout, btnaff;
    AnimauxService serviceTask = new AnimauxService();
    public static String filePath;
    public static String sexe;

    public static String Vet;

    public ajoutAnimal(Resources res) {

        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);

        TextField nom = new TextField("", "nom", 20, TextArea.ANY);
        TextField description = new TextField("", "description", 20, TextArea.ANY);
        TextField image = new TextField("", "image", 20, TextArea.ANY);
        
        Picker dateNaissance = new Picker();
        dateNaissance.setType(Display.PICKER_TYPE_DATE);
        dateNaissance.getFormatter();
        dateNaissance.setDate(new Date());

        Picker vetPicker = new Picker();
        vetPicker.setType(Display.PICKER_TYPE_STRINGS);
        vetPicker.setStrings("validation", "skander", "amany");
        vetPicker.setSelectedString("validation");
        
        Picker sexePicker = new Picker();
        sexePicker.setType(Display.PICKER_TYPE_STRINGS);
        sexePicker.setStrings("Male", "Female");
        sexePicker.setSelectedString("Male");
        
        Picker especePicker = new Picker();
        especePicker.setType(Display.PICKER_TYPE_STRINGS);
        especePicker.setStrings("Chien", "Chat","Cheval","Oiseaux","Poisson");
        especePicker.setSelectedString("Chien");
        

        nom.setUIID("TextFieldBlack");
        especePicker.setUIID("TextFieldBlack");
        sexePicker.setUIID("TextFieldBlack");
        description.setUIID("TextFieldBlack");
        image.setUIID("TextFieldBlack");
        vetPicker.setUIID("TextFieldBlack");
        dateNaissance.setUIID("TextFieldBlack");

        Label lnom = new Label("nom");
        Label lsexe = new Label("sexe");
        Label lespece = new Label("espece");
        Label ldate = new Label("date Naissance");
        Label ldescription = new Label("description");
        Label limage = new Label("image");
        Label lvet = new Label("vet");
        
        btnajout = new Button("ajouter");
        Button upload = new Button("upload");
        
        
         Form f = new Form("Ajout", BoxLayout.y());
        f.setScrollVisible(false);
        add(lnom).add(nom).add(lsexe).add(sexePicker).add(lespece).add(especePicker).add(ldescription).add(description).add(limage).add(image). add(upload).add(lvet).add(vetPicker).add(ldate).add(dateNaissance);
        add(btnajout);
        System.out.println(dateNaissance.getDate());
        System.out.println(vetPicker.getSelectedString());
        System.out.println(especePicker.getSelectedString());
        
         upload.addActionListener ( new ActionListener() {
                public void actionPerformed(ActionEvent ev) {

                    filePath = Capture.capturePhoto();
                    if (filePath != null) {
                        try {
                            String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + System.currentTimeMillis() + ".jpg";
                            Image img = Image.createImage(filePath);
                            OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                            ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_JPEG, 0.9f);
                            os.close();

                            image.setText(filePath);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                });

       btnajout.addActionListener((e) -> {
            AnimauxService ser = new AnimauxService();
            Animaux a = new Animaux(
                    nom.getText(), especePicker.getSelectedString(),sexePicker.getSelectedString(),dateNaissance.getDate(), description.getText(), image.getText(),vetPicker.getSelectedString());
            ser.ajoutAnimal(a);
        System.out.println(dateNaissance.getDate());
        System.out.println(vetPicker.getSelectedString());
        System.out.println(especePicker.getSelectedString());
        
        

        });
       

        
        
        
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    /* public TextField getTnom() {
        return nom;
    }

    public void setTnom(TextField nom) {
        this.nom = nom;
    }*/
}
