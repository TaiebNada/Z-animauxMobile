/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zanimaux.Sante;

import com.codename1.capture.Capture;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.esprit.entities.Animaux;
import com.esprit.services.AnimauxService;
import java.io.OutputStream;
import java.util.Date;

/**
 *
 * @author RYM
 */
public class upadateProfil extends BaseForm{
     Form f;

    Button btnajout, btnaff;
    AnimauxService serviceTask = new AnimauxService();
    public static String filePath;
    public static String sexe;

    public static String Vet;
    Animaux an=profilAnimal.a;

    public upadateProfil() {

        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout");
        getContentPane().setScrollVisible(false);
        //super.addSideMenu(res);

        TextField nom = new TextField("",an.getNom() , 20, TextArea.ANY);
        TextField description = new TextField("", an.getDescription(), 20, TextArea.ANY);
        TextField image = new TextField("", an.getImagePath(), 20, TextArea.ANY);
        System.out.println(an.getImage());
        System.out.println(an.getImagePath());
        Picker dateNaissance = new Picker();
        dateNaissance.setType(Display.PICKER_TYPE_DATE);
        dateNaissance.getFormatter();
        dateNaissance.setDate(an.getDateNaissance());

        Picker vetPicker = new Picker();
        vetPicker.setType(Display.PICKER_TYPE_STRINGS);
        vetPicker.setStrings("validation", "skander", "amany");
        vetPicker.setSelectedString(an.getNomVet());
        
        Picker sexePicker = new Picker();
        sexePicker.setType(Display.PICKER_TYPE_STRINGS);
        sexePicker.setStrings("Male", "Female");
        sexePicker.setSelectedString(an.getSexe());
        
        Picker especePicker = new Picker();
        especePicker.setType(Display.PICKER_TYPE_STRINGS);
        especePicker.setStrings("Chien", "Chat","Cheval","Oiseaux","Poisson");
        especePicker.setSelectedString(an.getEspece());
        

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
        
        btnajout = new Button("Modifier");
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
            Animaux a = new Animaux(an.getId(),
                    nom.getText(), especePicker.getSelectedString(),sexePicker.getSelectedString(), description.getText(), image.getText(),vetPicker.getSelectedString());
            ser.updateAnimal(a);
           System.out.println(an.getId());
        System.out.println(dateNaissance.getDate());
        System.out.println(vetPicker.getSelectedString());
        System.out.println(especePicker.getSelectedString());
        
        final Button showPopup = new Button("Show Popup");
            Dialog d = new Dialog("Modfication Réussie");
        TextArea popupBody = new TextArea("Profil modifié avec succées ", 3, 10);
        popupBody.setUIID("PopupBody");
        popupBody.setEditable(false);
        d.setLayout(new BorderLayout());
        d.add(BorderLayout.CENTER, popupBody);
        d.showPopupDialog(showPopup);
        new profilAnimal().show();

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
