/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package Zanimaux.Sante;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.uikit.cleanmodern.BaseForm;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.ProfileForm;
import com.esprit.entities.Animaux;
import com.esprit.services.AnimauxService;
import java.util.Date;

/**
 * The user profile form
 *
 * @author Rym
 */
public class profilAnimal extends BaseForm {

    public static int id;
    public static int poids;
    public static int taille;
    public static Animaux a;
    Form f;

    public profilAnimal() {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);

        // super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Image img = a.getImage();
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers");
        Label twitter = new Label("486 followers");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                facebook,
                                FlowLayout.encloseCenter(
                                        new Label(a.getImage())),
                                twitter
                        )
                )
        ));
        System.out.println(a.getId());
        System.out.println(a.getNom());
        System.out.println(a.getSexe());
        System.out.println(a.getDescription());
        System.out.println(a.getPoids());

        TextField Nom = new TextField(a.getNom());
        Nom.setEditable(false);
        Nom.setUIID("TextFieldBlack");
        addStringValue("Nom: ", Nom);

        TextField Sexe = new TextField(a.getSexe());
        Sexe.setEditable(false);
        Sexe.setUIID("TextFieldBlack");
        addStringValue("Sexe: ", Sexe);

        TextField Espece = new TextField(a.getEspece());
        Espece.setEditable(false);
        Espece.setUIID("TextFieldBlack");
        addStringValue("Espece", Espece);

        TextField DateNaissance = new TextField(a.getEspece());
        DateNaissance.setEditable(false);
        DateNaissance.setUIID("TextFieldBlack");
        addStringValue("Date de Naissance: ", DateNaissance);

        TextField Description = new TextField(a.getDescription());
        Description.setEditable(false);
        Description.setUIID("TextFieldBlack");
        addStringValue("Description: ", Description);

        TextField NomVet = new TextField(a.getNomVet());
        NomVet.setEditable(false);
        NomVet.setUIID("TextFieldBlack");
        addStringValue("Veterinaire: ", NomVet);

         poids=a.getPoids();
         System.out.println(poids);
        taille= a.getTaille();
        System.out.println(taille);
         /*dateVaccin = a.getDateVaccin();
         dateVisiteD = a.getDateVisiteD();*/
        Button btnajout = new Button("Afficher carte Santé");

        add(btnajout);
        
        
       btnajout.addActionListener((e) -> {
         new carteAnimal().show();

        });
        
        

       /* CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
        CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb2.setUIID("Label");
        cb2.setPressedIcon(res.getImage("on-off-on.png"));
        
        addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
        addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2));*/
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
        profilAnimal.id = id;
    }

    public static Animaux getA() {
        return a;
    }

    public static void setA(Animaux a) {
        profilAnimal.a = a;
    }
}
