package Zanimaux.Event;


import static Zanimaux.Event.QRMaker.QRCode;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
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



public class detailevenement extends BaseForm  {

    public detailevenement(Resources res,Evenement eve) {
        
        
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        Button precedant = new Button("precedant");
        add(precedant);
            try {
                Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(eve.getImage_evenement()));
                img = img.scaled(200, 200);
    add(img);
            } catch(Exception ex){
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
        
        int c=eve.getNbr_participant();
        String x=String.valueOf(c);
         int b=eve.getNbr_max_participant();
        String y=String.valueOf(b);
        TextField Nbr_participant = new TextField(y);
        Nbr_participant.setUIID("TextFieldBlack");
        addStringValue("Nbr_participant", Nbr_participant);
        
        TextField Nbr_max_participant = new TextField(x);
        Nbr_max_participant.setUIID("TextFieldBlack");
        addStringValue("Nbr_max_participant", Nbr_max_participant);
        
         TextField Description = new TextField(eve.getDescription_evenement());
        Description.setUIID("TextFieldBlack");
        addStringValue("Description", Description);
        int id1 =eve.getId();
            ServiceEvenement ser1 = new ServiceEvenement();
            ArrayList<Participant> a;
      //* a=ser1.existe(id1);
       int idutil=   SignInForm.getIdU();
         ArrayList t=ser1.siexiste(id1);
         Button jeparticipe = new Button("je participe");
       Button jeparticipepas = new Button("annuler pariticpation");
        add(jeparticipe);
           add(jeparticipepas);
           System.out.println( "t.get"+t.get(0));
          System.out.println(t);
        String str  =t.get(0).toString();
                                        float var = Float.parseFloat(t.get(0).toString());

        System.out.println("str="+str);
        System.out.println(str);
        System.out.println("var entier"+var);
char ch='"';
if(eve.getNbr_participant()<=eve.getNbr_max_participant()){
    System.out.println(eve.getNbr_participant());
        System.out.println(eve.getNbr_max_participant());

    jeparticipe.setHidden(true);
                        jeparticipepas.setHidden(true);

}
         else   if (var==1){
        
            jeparticipe.setHidden(true);
            jeparticipepas.setHidden(false);
            System.out.println(t);
            }
           else { 
                
                jeparticipe.setHidden(false);
            jeparticipepas.setHidden(true);}
           precedant.addActionListener(e -> new Affichage(res).show());
       final String nomqr = "E:/"+eve.getNom_evenement()+"MyQR.PNG";
        final String filePath = "E:/"+eve.getNom_evenement()+"MyQR.PNG";
          jeparticipe.addActionListener((e) -> {
              
           Image imgqr =  QRCode(nomqr) ;
              
           try {
                String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + System.currentTimeMillis() +eve.getNom_evenement()+  ".PNG";
           
                OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored );
                ImageIO.getImageIO().save(imgqr, os, ImageIO.FORMAT_JPEG, 0.9f);
                System.out.println(pathToBeStored);
                  Message m = new Message("QR code evenement Z'animaux");
//m.getAttachments().put(textAttachmentUri, "text/plain");
m.getAttachments().put(pathToBeStored, "/jpeg");

        Display.getInstance().sendMessage(new String[]{"djoedon12@gmail.com"}, "Subject of message", m);

         /*       
Message msg = new Message("see attachment");
        msg.setAttachment(pathToBeStored);
        msg.setAttachmentMimeType(Message.MIME_IMAGE_PNG);
        Display.getInstance().sendMessage(new String[]{"djoedon12@gmail.com"}, "QR code evenement Z'animaux", msg);*/
                }
            catch (Exception n) {
                n.printStackTrace();
            }
                
                       try {
                
                imgqr = imgqr.scaled(200, 200);
               
    add(imgqr);
    
            } catch(Exception ex){
                Dialog.show("Error", "Error during image loading: " + ex, "OK", null);
            }
           int id =eve.getId();
       
            ServiceEvenement ser = new ServiceEvenement();
            
          ser.jeparticipe(id);
           new detailevenement(res, eve).show();


        });
          
           ArrayList<Commentaire_evenement> p;
          ServiceEvenement ser = new ServiceEvenement();
         p= ser.getList2Commentaire(id1);
         for(int i = 0 ; i < p.size(); i++){
            Commentaire_evenement commentaire=p.get(i);
            
             TextArea ta = new TextArea(commentaire.getNom_user());
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
             Label comments = new Label(commentaire.getComm());
       FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
            
         SimpleDateFormat sdfr= new SimpleDateFormat("dd/MM/yyyy");
String date = sdfr.format(commentaire.getDateCommentaire());
             System.out.println(commentaire.getDateCommentaire());
       
              Label ldate = new Label(date );
              
            
       ldate.setTextPosition(RIGHT);
        add(ta);
      add(ldate);
              add(comments);
                 System.out.println("n.getIdutil()"+commentaire.getIdutil());
    System.out.println("SignInForm.getIdU()"+SignInForm.getIdU());
            if(commentaire.getIdutil()==SignInForm.getIdU()){
                 Button btnsuppcommentaire = new Button("supprimer mon commentaire");
        add(btnsuppcommentaire);
        btnsuppcommentaire.addActionListener((e) -> {
            ServiceEvenement serv = new ServiceEvenement();
            serv.supprimercom(commentaire);
            new detailevenement(res, eve).show();
            });
        
            }
         }
  
       
               TextField commentaire = new TextField("", "commentaire", 50, TextArea.ANY); 
commentaire.setUIID("TextFieldBlack");
 Label  lcommentaire =new Label("Commentaire");
 lcommentaire.setUIID("TextFieldBlack");
           add(lcommentaire);
        add(commentaire);
        
        Button btncommentaire = new Button("ajouter commentaire");
        add(btncommentaire);
          btncommentaire.addActionListener((e) -> {
          
            Commentaire_evenement Commentaire_evenement=new Commentaire_evenement();
          Commentaire_evenement.setComm(commentaire.getText());
          Commentaire_evenement.setEvenementA(eve);
          int id_user=SignInForm.getIdU();
          String nom_user=SignInForm.getUsernameU();
          String mail_user=SignInForm.getEmailU();
          Commentaire_evenement.setNom_user(nom_user);
          Commentaire_evenement.setEmail_user(mail_user);
          Commentaire_evenement.setIdutil(id_user);
         String format = "dd/MM/yyyy"; 

java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
java.util.Date date = new java.util.Date(); 
Commentaire_evenement.setDateCommentaire(date);
        
  ser.ajoutCommentaire(Commentaire_evenement,id1);
            new detailevenement(res, eve).show();

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