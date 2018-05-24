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
import com.codename1.uikit.cleanmodern.BaseForm;
import com.esprit.services.PanierService;
import com.esprit.entities.Produit;
import com.codename1.ui.Button;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Firqs
 */
public class Details extends BaseForm {

    private static int id;
    private static Produit p;
     PanierService panierservice = new PanierService();
    Form f;
int a=30;
    public Details(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle(p.getNom());
        getContentPane().setScrollVisible(false);
 Label all = new Label("Back");
        addStringValue("", all);

        all.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Details.setP(p);
          new ProduitForm(res).show();
            }
        });
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
        addStringValue("Categorie", Espece);
        
        
        Label prix = new Label("                                "+Float.toString(p.getPrix()));
            prix.setUIID("Produit");
            addStringValue("Prix", prix);
            
        
        TextField Race= new TextField(Integer.toString(p.getQuantiteStock()+30));
        
        Race.setEditable(false);
        Race.setUIID("TextFieldBlack");
        addStringValue("Quantite stock", Race);
        
        
        
        
       Button co = new Button("Ajouter au panier");
            
            addStringValue("", co);
            
            TextField spacer3 = new TextField("", "Choisir quantite", 20, TextArea.ANY);
        spacer3.getAllStyles().setFgColor(0x8B1800);
        addStringValue("", spacer3);
            
                 
                 co.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
           if ((Integer.parseInt(spacer3.getText()))<=(p.getQuantiteStock()+30))
           {p.setQte(Integer.parseInt(spacer3.getText()));
           
            panierservice.ajouterLigneDeCommande(p, p.getQte());
            p.setQuantiteStock(p.getQuantiteStock()-Integer.parseInt(spacer3.getText()));
            panierForm.setP(p);
            new panierForm(res,p).show();}


                }
       });
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
