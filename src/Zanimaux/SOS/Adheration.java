/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zanimaux.SOS;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
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
        super("Newsfeed",new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Adheration");

        TextField Espece = new TextField("", "Espece", 20, TextField.ANY);
        TextField Race = new TextField("", "Race", 20, TextField.ANY);
        TextField Sexe = new TextField("", "Sexe", 20, TextField.ANY);
        TextField Taille = new TextField("", "Taille", 20, TextField.ANY);
        TextField Description = new TextField("", "Description", 20, TextField.ANY);
        TextField Nom = new TextField("", "Nom", 20, TextField.ANY);

        Espece.setSingleLineTextArea(false);
        Race.setSingleLineTextArea(false);
        Sexe.setSingleLineTextArea(false);
        Taille.setSingleLineTextArea(false);
        Description.setSingleLineTextArea(false);
        Nom.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Retour Au Menu");

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
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener((evt) -> {
            AnimalService ser = new AnimalService();
            Animal a = new Animal(Espece.getText(), Race.getText(), Sexe.getText(), Nom.getText(), Taille.getText(), Description.getText(), "En Attente");
            ser.ajoutTask(a);
           // new Adoption(res).show();

        });

    }

}
