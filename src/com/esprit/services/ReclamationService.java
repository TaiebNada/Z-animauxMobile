<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.ImageIO;
import com.esprit.entities.Animaux;
import com.esprit.entities.Reclamation;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nada
 */
public class ReclamationService {
    
    public ArrayList<Reclamation> getListReclamations() {
         
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ProjetPi2/web/app_dev.php/Boutique/all/nadataieb@esprit.tn";
        con.setUrl(Url);
        ArrayList<Reclamation> mapPanier = new ArrayList<>();
        con.addResponseListener((e) -> {
            String jsonRes = new String(con.getResponseData());
            System.out.println(jsonRes);
            try {
                JSONParser j = new JSONParser();

                Map<String, Object> animaux = j.parseJSON(new CharArrayReader(jsonRes.toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) animaux.get("root");

                for (Map<String, Object> obj : list) {
                    Reclamation an = new Reclamation();
                    an.setNom(obj.get("nom").toString());
                    an.setTitre(obj.get("titre").toString());
                    an.setEmail(obj.get("email").toString());
                    an.setTel(obj.get("mobile").toString());
                    an.setObjet(obj.get("subject").toString());
                   // an.setImage(obj.get("image").toString());
                 //  an.setImage((Integer)obj.get("poids"));
                    //an.setTaille((Integer)obj.get("taille"));
                    //an.setDateVaccin((Date)obj.get("dateVaccin"));
                   // an.setDateVisiteD((Date)obj.get("dateVisiteD"));
                    
                    System.out.println(obj.get("image").toString());
                      int mm = Display.getInstance().convertToPixels(3);
                    an.setImagePath("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/" + obj.get("image").toString());
                    System.out.println(an.getImagePath());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm* 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/" + obj.get("image").toString()),
                                out,
                                ImageIO.FORMAT_PNG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    an.setImage(im);
                    
                
                    mapPanier.add(an);
             
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });

        NetworkManager.getInstance().addToQueue(con);
        System.out.println(mapPanier);
        return mapPanier;
    }
    
    
    
     public ArrayList<Reclamation> rechercheSkill() {
      ArrayList<Reclamation> listSkills = new ArrayList<>();
      ConnectionRequest con = new ConnectionRequest();
      con.setUrl("http://localhost/ProjetPi2/web/app_dev.php/Boutique/all/nadataieb@esprit.tn");
      con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listOffres = getListOffre(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> skills = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(skills);
                    System.out.println(skills.keySet());
                    System.out.println(skills.values());
                    List<Map<String, Object>> list = (List<Map<String, Object>>) skills.get("root");
                    for (Map<String, Object> obj : list) {
                        Reclamation skill = new Reclamation();
                        float id = Float.parseFloat(obj.get("id").toString());
                        skill.setId((int) id);
                        
                        skill.setNom(obj.get("nom").toString());
                        skill.setEmail(obj.get("email").toString());
                        skill.setObjet(obj.get("subject").toString());
                        
                        
                        int mm = Display.getInstance().convertToPixels(3);
                    skill.setImagePath("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/smile.png"/* + obj.get("image").toString()*/);
                    System.out.println(skill.getImagePath());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm* 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/smile.png" /*+ obj.get("image").toString()*/),
                                out,
                                ImageIO.FORMAT_PNG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    skill.setImage(im);
                        
                        
                        
                        listSkills.add(skill);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listSkills;
    }
 
     
     public void ajoutReclamation(Reclamation ta) 
    {
        ConnectionRequest con = new ConnectionRequest();
       String Url = "http://localhost/ProjetPi2/web/app_dev.php/Boutique/ajouter?nom="+ta.getNom()+"&email="+ta.getEmail()+"&titre="+ta.getTitre()+"&mobile="+ta.getTel()+"&subject="+ta.getObjet()+"&image="+ta.getImagePath();
       con.setUrl(Url);
        //System.out.println("tt");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     
     
     public void Mail(String email,int code) 
    {
        ConnectionRequest con = new ConnectionRequest();
       String Url = "http://localhost/ProjetPi2/web/app_dev.php/Boutique/mail2?sendTo="+email+"&sujet= votre code est : "+code;
       con.setUrl(Url);
        //System.out.println("tt");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
     
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.ImageIO;
import com.esprit.entities.Animaux;
import com.esprit.entities.Reclamation;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nada
 */
public class ReclamationService {
    
    public ArrayList<Reclamation> getListReclamations() {
         
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ProjetPi2/web/app_dev.php/Boutique/all/nadataieb@esprit.tn";
        con.setUrl(Url);
        ArrayList<Reclamation> mapPanier = new ArrayList<>();
        con.addResponseListener((e) -> {
            String jsonRes = new String(con.getResponseData());
            System.out.println(jsonRes);
            try {
                JSONParser j = new JSONParser();

                Map<String, Object> animaux = j.parseJSON(new CharArrayReader(jsonRes.toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) animaux.get("root");

                for (Map<String, Object> obj : list) {
                    Reclamation an = new Reclamation();
                    an.setNom(obj.get("nom").toString());
                    an.setTitre(obj.get("titre").toString());
                    an.setEmail(obj.get("email").toString());
                    an.setTel(obj.get("mobile").toString());
                    an.setObjet(obj.get("subject").toString());
                   // an.setImage(obj.get("image").toString());
                 //  an.setImage((Integer)obj.get("poids"));
                    //an.setTaille((Integer)obj.get("taille"));
                    //an.setDateVaccin((Date)obj.get("dateVaccin"));
                   // an.setDateVisiteD((Date)obj.get("dateVisiteD"));
                    
                    System.out.println(obj.get("image").toString());
                      int mm = Display.getInstance().convertToPixels(3);
                    an.setImagePath("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/" + obj.get("image").toString());
                    System.out.println(an.getImagePath());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm* 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/" + obj.get("image").toString()),
                                out,
                                ImageIO.FORMAT_PNG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    an.setImage(im);
                    
                
                    mapPanier.add(an);
             
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });

        NetworkManager.getInstance().addToQueue(con);
        System.out.println(mapPanier);
        return mapPanier;
    }
    
    
    
     public ArrayList<Reclamation> rechercheSkill() {
      ArrayList<Reclamation> listSkills = new ArrayList<>();
      ConnectionRequest con = new ConnectionRequest();
      con.setUrl("http://localhost/ProjetPi2/web/app_dev.php/Boutique/all/nadataieb@esprit.tn");
      con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listOffres = getListOffre(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> skills = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(skills);
                    System.out.println(skills.keySet());
                    System.out.println(skills.values());
                    List<Map<String, Object>> list = (List<Map<String, Object>>) skills.get("root");
                    for (Map<String, Object> obj : list) {
                        Reclamation skill = new Reclamation();
                        float id = Float.parseFloat(obj.get("id").toString());
                        skill.setId((int) id);
                        
                        skill.setNom(obj.get("nom").toString());
                        skill.setEmail(obj.get("email").toString());
                        skill.setObjet(obj.get("subject").toString());
                        
                        
                        int mm = Display.getInstance().convertToPixels(3);
                    skill.setImagePath("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/smile.png"/* + obj.get("image").toString()*/);
                    System.out.println(skill.getImagePath());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm* 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/smile.png" /*+ obj.get("image").toString()*/),
                                out,
                                ImageIO.FORMAT_PNG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    skill.setImage(im);
                        
                        
                        
                        listSkills.add(skill);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listSkills;
    }
 
     
     public void ajoutReclamation(Reclamation ta) 
    {
        ConnectionRequest con = new ConnectionRequest();
       String Url = "http://localhost/ProjetPi2/web/app_dev.php/Boutique/ajouter?nom="+ta.getNom()+"&email="+ta.getEmail()+"&titre="+ta.getTitre()+"&mobile="+ta.getTel()+"&subject="+ta.getObjet();
       con.setUrl(Url);
        //System.out.println("tt");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
     
}
>>>>>>> 6f653f94b21c45119cab9af9574c918f07aefd0e
