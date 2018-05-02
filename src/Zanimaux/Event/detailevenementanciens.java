package Zanimaux.Event;

import static Zanimaux.Event.QRMaker.QRCode;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.esprit.entities.Evenement;
import com.esprit.entities.Commentaire_evenement;
import com.esprit.services.ServiceEvenement;
import com.esprit.entities.Participant;
import com.google.zxing.WriterException;
import java.io.IOException;

import com.codename1.ui.Image;
import com.codename1.ui.util.ImageIO;
import com.codename1.uikit.cleanmodern.SignInForm;
import java.io.OutputStream;

import java.util.ArrayList;

public class detailevenementanciens extends BaseForm {

    public detailevenementanciens(Resources res, Evenement eve) {

        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });
        Button precedant = new Button("precedant");
        add(precedant);
        try {
            Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(eve.getImage_evenement()));
            img = img.scaled(200, 200);
            add(img);
        } catch (Exception ex) {
            Dialog.show("Error", "Error during image loading: " + ex, "OK", null);
        }

        TextField Nom = new TextField(eve.getNom_evenement());
        Nom.setUIID("TextFieldBlack");
        addStringValue("Nom", Nom);

        TextField Theme = new TextField(eve.getTheme_evenement());
        Theme.setUIID("TextFieldBlack");
        addStringValue("Theme", Theme);

        TextField Lieu = new TextField(eve.getLieu_evenement());
        Lieu.setUIID("TextFieldBlack");
        addStringValue("Lieu", Lieu);

        TextField Nbr_participant = new TextField(eve.getNbr_participant());
        Nbr_participant.setUIID("TextFieldBlack");
        addStringValue("Nbr_participant", Nbr_participant);

        TextField Nbr_max_participant = new TextField(eve.getNbr_max_participant());
        Nbr_max_participant.setUIID("TextFieldBlack");
        addStringValue("Nbr_max_participant", Nbr_max_participant);

        TextField Description = new TextField(eve.getDescription_evenement());
        Description.setUIID("TextFieldBlack");
        addStringValue("Description", Description);
        int id1 = eve.getId();
        ServiceEvenement ser1 = new ServiceEvenement();
        ArrayList<Participant> a;
        //* a=ser1.existe(id1);
        ArrayList t = ser1.siexiste(id1);

        System.out.println("t.get" + t.get(0));
        System.out.println(t);
        String str = t.get(0).toString();
        System.out.println("str=" + str);
        System.out.println(str);

        ArrayList<Commentaire_evenement> p;
        ServiceEvenement ser = new ServiceEvenement();
        p = ser.getList2Commentaire(id1);
        for (int i = 0; i < p.size(); i++) {
            Commentaire_evenement commentaire = p.get(i);

            TextArea ta = new TextArea(commentaire.getNom_user());
            ta.setUIID("NewsTopLine");
            ta.setEditable(false);
            Label comments = new Label(commentaire.getComm());
            FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);

            SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdfr.format(commentaire.getDateCommentaire());
            System.out.println(commentaire.getDateCommentaire());

            Label ldate = new Label(date);

            ldate.setTextPosition(RIGHT);
            add(ta);
            add(ldate);
            add(comments);
            if(commentaire.getIdutil()==SignInForm.getIdU()){
                 Button btnsuppcommentaire = new Button("supprimer mon commentaire");
        add(btnsuppcommentaire);
        btnsuppcommentaire.addActionListener((e) -> {
            ServiceEvenement serv = new ServiceEvenement();
            serv.supprimercom(commentaire);
            });
        
            }
        }

        add(FlowLayout.encloseCenter(createStarRankSlider()));
        TextField commentaire = new TextField("", "commentaire", 50, TextArea.ANY);
        commentaire.setUIID("TextFieldBlack");
        Label lcommentaire = new Label("Commentaire");
        lcommentaire.setUIID("TextFieldBlack");
        add(lcommentaire);
        add(commentaire);

        Button btncommentaire = new Button("ajouter commentaire");
        add(btncommentaire);
        btncommentaire.addActionListener((e) -> {

            Commentaire_evenement Commentaire_evenement = new Commentaire_evenement();
            Commentaire_evenement.setComm(commentaire.getText());
            Commentaire_evenement.setEvenementA(eve);
            Commentaire_evenement.setNom_user(SignInForm.getUsernameU());
            Commentaire_evenement.setEmail_user(SignInForm.getEmailU());
             Commentaire_evenement.setIdutil(SignInForm.getIdU());
            String format = "dd/MM/yyyy";

            java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
            java.util.Date date = new java.util.Date();
            Commentaire_evenement.setDateCommentaire(date);

            ser.ajoutCommentaire(Commentaire_evenement, id1);

        });
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

}
