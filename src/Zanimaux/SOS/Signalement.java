/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zanimaux.SOS;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.esprit.services.AnimalService;
import java.io.IOException;

/**
 *
 * @author ADMIN
 */
public class Signalement extends BaseForm {

    public Signalement(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Signalement");
        getContentPane().setScrollVisible(false);
      
         String i = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
        if (i != null) {
            try {
                
                Image img = Image.createImage(i);
                System.out.println("image est :"+img.getImageName());
                TextArea content = new TextArea();
        Button envoyer = new Button("Envoyer");
        add(LayeredLayout.encloseIn(
                // sl,
                BorderLayout.south(
                        GridLayout.encloseIn(1,
                                FlowLayout.encloseCenter(
                                        new Label(img),
                                        content,envoyer
                                        
                                )
                        )
                )));
        envoyer.addActionListener((evt) -> {
            AnimalService a = new AnimalService();
<<<<<<< HEAD
           // a.ajoutRequest(content.getText(), i,1);
=======
            //;
>>>>>>> 96f3eea390abce1d4ac6e8fbb4ca2d8cc2e715a9
            new Adoption(res).show();
        });
            } catch (IOException ex) {
               
            }
                
           
        }
        
       
    }
    
    private void setImage(String filePath, ImageViewer iv) {
            try {
                Image i1 = Image.createImage(filePath);
                iv.setImage(i1);
                iv.getParent().revalidate();
            } catch (IOException ex) {
                Log.e(ex);
                Dialog.show("Error", "Error during image loading: " + ex, "OK", null);
            }
    }
    
    private void showToast(String text) {
        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();
    }
}
