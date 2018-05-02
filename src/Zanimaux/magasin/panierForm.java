/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zanimaux.magasin;

import com.codename1.components.FloatingHint;
import com.codename1.components.SpanLabel;
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
import com.codename1.googlemaps.MapContainer;
import com.codename1.googlemaps.MapLayout;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.messaging.Message;
import com.esprit.services.PanierService;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.RadioButton;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import static Zanimaux.magasin.NewsfeedForm.myMap;
import java.util.Map;

/**
 *
 * @author Firqs
 */
public class panierForm extends BaseForm {

    private static int id;
    private static Produit p;
    public static Map<Produit, Integer> myMap = PanierService.lc.getLignedeCommande();
    PanierService panierservice = new PanierService();
    Form f;

    public panierForm(Resources res) {
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
                new NewsfeedForm(res).show();
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
                                FlowLayout.encloseCenter()
                        )
                )
        ));

        Label pro = new Label("        Produit                                Qte           Prix");

        pro.getAllStyles().setFgColor(0x180967);
        addStringValue("", pro);
        for (Produit pr : myMap.keySet()) {

            Label pros = new Label(pr.getNom() + "    " + Integer.toString(pr.getQte())+"      "+Float.toString(pr.getPrix()));
            pros.setUIID("Produit");

            addStringValue(" ", pros);

            Label Supp = new Label("Supprimer");
            Supp.getAllStyles().setFgColor(0x8B1800);
            addStringValue("", Supp);

            Supp.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    myMap.remove(pr);
                    new panierForm(res).show();
                }
            });
Button co = new Button("Valider Commande");
            
            addStringValue("", co);

            co.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
//                    Message m = new Message("Body of message");
//Display.getInstance().sendMessage(new String[] {"miropodolski77@gmail.com"}, "Subject of message", m);


Form mapDemo = new Form("Maps", new LayeredLayout());
           /* mapDemo.getToolbar().addMaterialCommandToSideMenu("Hi", FontImage.MATERIAL_3D_ROTATION, e -> {
            });*/
            if (BrowserComponent.isNativeBrowserSupported()) {
                //MapContainer mc = new MapContainer("AIzaSyDwoZZyXXZHqY_ud-_-yaNdxwzAdaePKls");
                MapContainer mc = new MapContainer();
                mapDemo.add(mc);
                Container markers = new Container();
                markers.setLayout(new MapLayout(mc, markers));
                mapDemo.add(markers);

                Coord moscone = new Coord(37.7831, -122.401558);
                Button mosconeButton = new Button("");
                FontImage.setMaterialIcon(mosconeButton, FontImage.MATERIAL_PLACE);
                markers.add(moscone, mosconeButton);
                
                
                
                
               

                mc.zoom(moscone, 5);
            } else {
                // iOS Screenshot process...
                mapDemo.add(new Label("Loading, please wait...."));
            }
            mapDemo.show();


                }
       });}}
        
                    

// product.setEditable(false);
//             product.setUIID("TextFieldBlack");
//             addStringValue("Produit Quantite", product);}
//         SpanLabel sp= new SpanLabel("");
//         for (int i : myMap.values()) {
//         TextArea Quantite= new TextArea(String.valueOf(i));
//        Quantite.setEditable(false);
//        Quantite.setUIID("TextFieldBlack");
//        addStringValue("Quantite", Quantite);}
//        add(LayeredLayout.encloseIn(
//                GridLayout.encloseIn(1, all)
//        ));
//        all.addActionListener((evt) -> {
//            new NewsfeedForm(res).show();
//        });
    
    

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));

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
        panierForm.id = id;
    }

    public static Produit getP() {
        return p;
    }

    public static void setP(Produit p) {
        panierForm.p = p;
    }

}
