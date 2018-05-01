<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zanimaux.SAV;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.messaging.Message;
import static com.codename1.messaging.Message.sendMessage;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codename1.uikit.cleanmodern.SignInForm;
import com.esprit.entities.Animal;
import com.esprit.entities.Reclamation;
import com.esprit.entities.User;
import com.esprit.services.AnimalService;
import com.esprit.services.ReclamationService;
import com.esprit.services.UserService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 *
 * @author Nada
 */
public class AjoutReclamation extends BaseForm{
    
    static int id;
    Form f;
    TextField tnom;
    //TextField tetat;
    TextField ttitre;
    TextField tsujet;
    TextField temail;
    TextField ttel;
    TextField timage;
    Label l1 = new Label("Utilisateur : ");
    Label l2 = new Label("Email : ");
    Label l3 = new Label("Telephone");
    Label l4 = new Label("Titre du reclamation");
    Label l5 = new Label("Votre reclamation");
    //File file ;
    


    Button btnajout;

    public AjoutReclamation() {
        f = new Form("Passer réclamation");
        tnom = new TextField();
        tnom.setUIID("TextFieldBlack") ;
        ttitre = new TextField();
        ttitre.setUIID("TextFieldBlack") ;
        tsujet = new TextField();
        tsujet.setUIID("TextFieldBlack") ;
        temail = new TextField();
        temail.setUIID("TextFieldBlack") ;
        ttel = new TextField();
        ttel.setUIID("TextFieldBlack") ;
        timage = new TextField();
        timage.setUIID("TextFieldBlack") ;
        btnajout = new Button("Passer");
        //btnaff=new Button("Afficher mes reclamations");
        f.add(l1);
        f.add(tnom);
        f.add(l2);
        f.add(temail);
        f.add(l3);
        f.add(ttitre);
        f.add(l4);
        f.add(tsujet);
        f.add(l5);
        f.add(ttel);
        Button upload = new Button("ajouter image");
        f.add(upload);
        f.add(timage);
        
        f.add(btnajout);
        getF().show();
        
        
        
        btnajout.addActionListener((e) -> {
            ReclamationService ser = new ReclamationService();
            //Reclamation R = new Reclamation("email","user","tel","titre","sujet");
            Reclamation R = new Reclamation(tnom.getText(),temail.getText(),ttitre.getText(),tsujet.getText(),ttel.getText(),timage.getText());
            //Reclamation R = new Reclamation(temail.getText(),tnom.getText(),ttitre.getText());
             //Reclamation R = new Reclamation("emaaail","nadaaa","titreee","1111","reclamationnnnn");
            ser.ajoutReclamation(R);
            Message m = new Message("Reclamation : "+tsujet.getText());
//m.getAttachments().put(textAttachmentUri, "text/plain");
//m.getAttachments().put(imageAttachmentUri, "image/png");
sendMessage(new String[] {"nada.taieb@esprit.tn"}, "titre de reclamation : "+ttitre.getText(), m);
             

        });
         
         
        upload.addActionListener((e) -> {
            
            String filePath = Capture.capturePhoto();
        if (filePath != null) {
            
                try
                {
                    String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() +  ".jpg";
                    //String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + System.currentTimeMillis() +  ".jpg";
                    //String pathToBeStored = "C:/wampNada/www/Images/";
                    //String path =  e.getSource().toString();
                    Image img = Image.createImage(filePath);
                    OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored );
                    ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_PNG, 0.9f);
                    os.close();
                    
                    timage.setText(pathToBeStored);
                    
                    
                    
                   /* 
                    FileChooser fileChooser = new FileChooser();
                    FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                    FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                    fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
                    fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(".png", ".jpg", "*.gif")
                    ); 
                
                
             //File f;   
                
        Stage stage = null;
        java.io.File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println(file.getAbsolutePath());
            String img1 = file.getAbsolutePath();
           
String path = "C:/wampNada/www/Images/";
                
                Files.copy(file.toPath(),
                        (new java.io.File(path + file.getName())).toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
                System.out.println(img);
                System.out.println("jawek béhi");
               // imageDe1.setText(img);
                //an.setImage(img);
                //imageDe=img;
                
               // String str = img.substring(20);
            //System.out.println(str);
          // javafx.scene.image.Image img1 = new javafx.scene.image.Image("file:C:/wampNada/www/Images/"+str);
          // System.out.println("C:/wampNada/www/Images/"+str);
           // inscri2.setImage(img1);

*/
                }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            
        }
              

        });
        
        
       
       /* upload.addActionListener(new ActionListener() {
        
            
           
            public void actionPerformed(ActionEvent ev) {
               
                   
   
   String filePath = Capture.capturePhoto();
        if (filePath != null) {
            try {
                String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + System.currentTimeMillis() +  ".jpg";
                Image img = Image.createImage(filePath);
                OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored );
                ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_JPEG, 0.9f);
                os.close();
                
                 timage.setText(pathToBeStored);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    
} 
   });
   
     */   
        
    }
      
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    }

 
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zanimaux.SAV;

import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.File;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codename1.uikit.cleanmodern.SignInForm;
import com.esprit.entities.Animal;
import com.esprit.entities.Reclamation;
import com.esprit.entities.User;
import com.esprit.services.AnimalService;
import com.esprit.services.ReclamationService;
import com.esprit.services.UserService;
import java.util.ArrayList;


/**
 *
 * @author Nada
 */
public class AjoutReclamation extends BaseForm{
    
    static int id;
    Form f;
    TextField tnom;
    //TextField tetat;
    TextField ttitre;
    TextField tsujet;
    TextField temail;
    TextField ttel;
    Label l1 = new Label("Utilisateur : ");
    Label l2 = new Label("Email : ");
    Label l3 = new Label("Telephone");
    Label l4 = new Label("Titre du reclamation");
    Label l5 = new Label("Votre reclamation");
    //File file ;
    


    Button btnajout;

    public AjoutReclamation() {
        f = new Form("Passer réclamation");
        tnom = new TextField();
        tnom.setUIID("TextFieldBlack") ;
        ttitre = new TextField();
        ttitre.setUIID("TextFieldBlack") ;
        tsujet = new TextField();
        tsujet.setUIID("TextFieldBlack") ;
        temail = new TextField();
        temail.setUIID("TextFieldBlack") ;
        ttel = new TextField();
        ttel.setUIID("TextFieldBlack") ;
        btnajout = new Button("Passer");
        //btnaff=new Button("Afficher mes reclamations");
        f.add(l1);
        f.add(tnom);
        f.add(l2);
        f.add(temail);
        f.add(l3);
        f.add(ttitre);
        f.add(l4);
        f.add(tsujet);
        f.add(l5);
        f.add(ttel);
        f.add(btnajout);
        getF().show();
        btnajout.addActionListener((e) -> {
            ReclamationService ser = new ReclamationService();
            //Reclamation R = new Reclamation("email","user","tel","titre","sujet");
            Reclamation R = new Reclamation(tnom.getText(),temail.getText(),ttitre.getText(),tsujet.getText(),ttel.getText());
            //Reclamation R = new Reclamation(temail.getText(),tnom.getText(),ttitre.getText());
             //Reclamation R = new Reclamation("emaaail","nadaaa","titreee","1111","reclamationnnnn");
            ser.ajoutReclamation(R);
            

        });
        
        
    }
      
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    }

 
>>>>>>> 6f653f94b21c45119cab9af9574c918f07aefd0e
