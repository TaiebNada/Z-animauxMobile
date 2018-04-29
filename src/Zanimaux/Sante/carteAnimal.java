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

/**
 * The user profile form
 *
 * @author Rym
 */
public class carteAnimal extends BaseForm {

        private static int id;
    private static Animaux a;
    Form f;
    
    
    public carteAnimal() {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
       // super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        Animaux an=profilAnimal.a;
        
        Image img = an.getImage();
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
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
                                new Label(an.getImage())),
                            twitter
                    )
                )
        ));
        System.out.println(an.getId());
        System.out.println(an.getNom());
        System.out.println(an.getSexe());
        System.out.println(an.getDescription());
        
        
          TextField Nom= new TextField(an.getNom());
        Nom.setEditable(false);
        Nom.setUIID("TextFieldBlack");
        addStringValue("Nom: ", Nom);

         TextField Sexe= new TextField(an.getSexe());
        Sexe.setEditable(false);
        Sexe.setUIID("TextFieldBlack");
        addStringValue("Sexe: ",Sexe);
        
        
       TextField Espece= new TextField(an.getEspece());
        Espece.setEditable(false);
        Espece.setUIID("TextFieldBlack");
        addStringValue("Espece", Espece);
        
       
         TextField DateNaissance= new TextField(an.getEspece());
        DateNaissance.setEditable(false);
        DateNaissance.setUIID("TextFieldBlack");
        addStringValue("Date de Naissance: ", DateNaissance);
        
         TextField Description= new TextField(an.getDescription());
        Description.setEditable(false);
        Description.setUIID("TextFieldBlack");
        addStringValue("Description: ", Description);
        
         TextField NomVet= new TextField(an.getNomVet());
        NomVet.setEditable(false);
        NomVet.setUIID("TextFieldBlack");
        addStringValue("Veterinaire: ", NomVet);
        
        
         TextField Poids= new TextField(an.getPoids());
        Poids.setEditable(false);
        Poids.setUIID("TextFieldBlack");
        addStringValue("Poids ", Poids);
        
        TextField Taille= new TextField(an.getTaille());
        Taille.setEditable(false);
        Taille.setUIID("TextFieldBlack");
        addStringValue("Taille: ", Taille);
        
        TextField dateVaccin= new TextField(profilAnimal.poids);
        dateVaccin.setEditable(false);
        dateVaccin.setUIID("TextFieldBlack");
        addStringValue("Date Vaccin ", dateVaccin);
        
        TextField dateVisiteD= new TextField(profilAnimal.poids);
        dateVisiteD.setEditable(false);
        dateVisiteD.setUIID("TextFieldBlack");
        addStringValue("Date derniere visite ", dateVisiteD);
        
        
        
        

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
        carteAnimal.id = id;
    }

    public static Animaux getA() {
        return a;
    }

    public static void setA(Animaux a) {
        carteAnimal.a = a;
    }
}
