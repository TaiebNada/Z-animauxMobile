/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zanimaux.SOS;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.esprit.entities.Animal;
import com.esprit.services.AnimalService;

/**
 *
 * @author ADMIN
 */
public class Adheration extends BaseForm {

    static int id;
    Form f;

    public Adheration() {
        super("Adheration", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Veuillez Saisir les données");
        getContentPane().setScrollVisible(false);
        f = new Form("Adheration");
        TextField Espece = new TextField("Espece");
        TextField Race = new TextField("Race");
        TextField Sexe = new TextField("Sexe");
        TextField Nom = new TextField("Nom");
        TextField Taille = new TextField("Taille");
        TextField Description = new TextField("Description");
        TextField Type = new TextField("En Attente");
        Button btnajout = new Button("ajouter");
        f.add(Espece);
        f.add(Race);
        f.add(Sexe);
        f.add(Nom);
        f.add(Taille);
        f.add(Description);

        f.add(btnajout);
        btnajout.addActionListener((e) -> {
            AnimalService ser = new AnimalService();
            Animal a = new Animal(Espece.getText(), Race.getText(), Sexe.getText(), Nom.getText(), Taille.getText(), Description.getText(), "En Attente");
            ser.ajoutTask(a);

        });
        Espece.setUIID("TextFieldBlack");
        addStringValue("Espece", Espece);

        Race.setUIID("TextFieldBlack");
        addStringValue("Race", Race);

        Description.setUIID("TextFieldBlack");
        addStringValue("Description", Description);

        Taille.setUIID("TextFieldBlack");
        addStringValue("Taille", Taille);
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Adheration.id = id;
    }

}
