/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zanimaux.magasin;

import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import Zanimaux.magasin.BaseForm;
import com.esprit.entities.Produit;

/**
 *
 * @author Firqs
 */
public class Details extends BaseForm {

    private static int id;
    private static Produit p;
    Form f;

    public Details() {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle(p.getNom());
        getContentPane().setScrollVisible(false);

      /*  Image img = a.getImage();
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);*/
        add(LayeredLayout.encloseIn(
               // sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                FlowLayout.encloseCenter(
                                        new Label(p.getImage())
                                )
                        )
                )));
        TextField Espece= new TextField(p.getCategorie());
        Espece.setEditable(false);
        Espece.setUIID("TextFieldBlack");
        addStringValue("Espece", Espece);
        
        TextField Race= new TextField(p.getQuantite_stock());
        Race.setEditable(false);
        Race.setUIID("TextFieldBlack");
        addStringValue("Race", Race);
        
        TextArea Description= new TextArea("hello");
        Description.setEditable(false);
        Description.setUIID("TextFieldBlack");
        addStringValue("Description", Description);
        
        
       
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
        Details.id = id;
    }

    public static Produit getP() {
        return p;
    }

    public static void setP(Produit p) {
        Details.p = p;
    }

}
