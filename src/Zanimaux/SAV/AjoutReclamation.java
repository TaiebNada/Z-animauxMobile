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

 
