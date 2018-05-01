/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zanimaux.Event;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;

import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.esprit.services.ServiceEvenement;
import com.esprit.entities.Evenement;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author sana
 */
public class Affichage extends BaseForm  {

    Form f;
    SpanLabel lb;
  
    public Affichage(Resources res) {
         super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Newsfeed");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
               addTab(swipe, res.getImage("news-item.jpg"), spacer1);
        addTab(swipe, res.getImage("dog.jpg"), spacer2);
        
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Featured", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Popular", barGroup);
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, featured, popular, myFavorite),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        f = new Form();
        
        
        ServiceEvenement serviceEvenement=new ServiceEvenement();
      
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi2/web/app_dev.php/Bienetre/Alll");
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvenement ser = new ServiceEvenement();
                List<Evenement> list = ser.getListEvenement(new String(con.getResponseData()));
                JSONParser j = new JSONParser();
String json=new String(con.getResponseData());
            Map<String, Object> Evenements;
             try {
            System.out.println(json);
            JSONParser js = new JSONParser();

            Map<String, Object> Evenemets = js.parseJSON(new CharArrayReader(json.toCharArray()));
            
           
            List<Map<String, Object>> list2 = (List<Map<String, Object>>) Evenemets.get("root");

            for (Map<String, Object> obj : list2) {
                Evenement e = new Evenement();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setNom_evenement(obj.get("nomEvenement").toString());
                 e.setTheme_evenement(obj.get("themeEvenement").toString());
               
                  e.setLieu_evenement(obj.get("lieuEvenement").toString());
                   e.setImage_evenement(obj.get("imageEvenement").toString());
                   
                   e.setDescription_evenement(obj.get("descriptionEvenement").toString());
                   
                   
                    float max = Float.parseFloat(obj.get("nbrMAXParticipant").toString());
                   
                e.setNbr_max_participant((int)max);
                
                
                 float nbr = Float.parseFloat(obj.get("nbrParticipant").toString());
                  
                e.setNbr_participant((int)nbr);
       try{
	Date date;
      	//String date;
 
        SimpleDateFormat sdfr= new SimpleDateFormat("dd/MM/yyyy");
       System.out.println(obj.get("dateEvenement").toString());
               String a =obj.get("dateEvenement").toString();
               System.out.println(obj.get("dateEvenement"));
                    date = sdfr.parse(a);
                    e.setDate_evenement(date);
   }catch (Exception ex ){
	System.out.println(ex);
   }
              
                System.out.println("get listeebenemeny");
                        System.out.println(e);
                          ArrayList<Evenement> list1 = new ArrayList<>();
                        list1.add(e);
                       
      
                       addButton(e.getNom_evenement(),e.getLieu_evenement(),e.getTheme_evenement(),e.getImage_evenement(),e.getDate_evenement(),e,res);
             
            }
                 } catch (IOException ex) {
        }

            }
        });
        
         
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
private void addButton( String nom, String lieu,String theme, String image,Date date,Evenement eve,Resources res) throws IOException {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
      
    
      
       TextArea ta = new TextArea(nom);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       
       Label likes = new Label(" situe a : "+lieu );
       likes.setTextPosition(RIGHT);
       
       
      
     
       Label comments = new Label(" theme : "+theme );
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
      
            try {
                Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(image));
                img = img.scaled(200, 200);
    add(img);
            } catch(Exception ex){
                Dialog.show("Error", "Error during image loading: " + ex, "OK", null);
            }
        
      
      
       add(ta);
      
       
        
       
               
                 SimpleDateFormat sdfr= new SimpleDateFormat("dd/MM/yyyy");
String datef = sdfr.format(date);
             System.out.println(date);
       
              Label ldate = new Label(datef );
      add(ldate);
      add(likes);
      
              add(comments);
                       Button detail = new Button("detail evenement");
                       add(detail);
        detail.addActionListener(e -> new detailevenement(res,eve).show());
   }
   private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    private void addTab(Tabs swipe, Image img, Label spacer) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
       
      
     
       
    
      

        
    
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
}
