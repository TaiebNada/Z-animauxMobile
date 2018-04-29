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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.util.ImageIO;
import com.esprit.entities.Animal;
import com.esprit.entities.Animaux;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RYM
 */
public class AnimauxService {

    public ArrayList<Animaux> getListAnimaux() {

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/phpStormProjects/ProjetNada/ProjetPi/web/app_dev.php/Animaux1/maListeJson";
        con.setUrl(Url);
        ArrayList<Animaux> mapPanier = new ArrayList<>();
        con.addResponseListener((e) -> {
            String jsonRes = new String(con.getResponseData());
            try {
                JSONParser j = new JSONParser();

                Map<String, Object> animaux = j.parseJSON(new CharArrayReader(jsonRes.toCharArray()));
                System.out.println(animaux);
                List<Map<String, Object>> list = (List<Map<String, Object>>) animaux.get("root");

                for (Map<String, Object> obj : list) {
                    Animaux an = new Animaux();
                    an.setNomVet(obj.get("nomVet").toString());
                    an.setNom(obj.get("nom").toString());
                    an.setEspece(obj.get("espece").toString());
                    an.setDescription(obj.get("description").toString());
                    an.setSexe(obj.get("sexe").toString());
                    float poids = Float.parseFloat(obj.get("poids").toString());
                    int poids2 = (int) poids;
                    an.setPoids(poids2);
                    float taille = Float.parseFloat(obj.get("taille").toString());
                    int taille2 = (int) taille;
                    an.setTaille(taille2);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    //an.setDateVaccin((Date)obj.get("dateVaccin"));
                    //  an.setDateVaccin(sdf.format(new Date((long)Float.parseFloat(obj.get("dateVaccin").toString()))));
                    //  an.setDateVisiteD((Date)obj.get("dateVisiteD"));
                    System.out.println("blavkaddz" + an.getNomVet());

                    int mm = Display.getInstance().convertToPixels(3);
                    an.setImagePath("file://C:/wamp64/www/phpStormProjects/ProjetNada/ProjetPi/web/uploads/images/evenement/" + obj.get("image").toString());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file://C:/wamp64/www/phpStormProjects/ProjetNada/ProjetPi/web/uploads/images/evenement/" + obj.get("image").toString()),
                                out,
                                ImageIO.FORMAT_JPEG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    an.setImage(im);

                    mapPanier.add(new Animaux(an.getNom(), an.getEspece(), an.getSexe(), an.getDescription(), an.getTaille(), an.getPoids(), an.getImage(), an.getNomVet()));
                  
                    for (Animaux entry : mapPanier) {
                       System.out.println("le nom est"+entry.getNom());
                        
                    }
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });

        NetworkManager.getInstance().addToQueue(con);

        return mapPanier;
    }
    
    public ArrayList<Animaux> getList() {
        ArrayList<Animaux> listAnimal = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/phpStormProjects/ProjetNada/ProjetPi/web/app_dev.php/Animaux1/maListeJson");
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();

            try {
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                //System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                for (Map<String, Object> obj : list) {
                    int mm = Display.getInstance().convertToPixels(3);
                    Animaux a = new Animaux();
                    float id = Float.parseFloat(obj.get("id").toString());
                    a.setId((int) id);
                    a.setEspece(obj.get("espece").toString());
                    a.setNom(obj.get("nom").toString());
                    
                    a.setImagePath("file://C:/wamp64/www/phpStormProjects/ProjetNada/ProjetPi/web/uploads/images/evenement/" + obj.get("image").toString());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file://C:/wamp64/www/phpStormProjects/ProjetNada/ProjetPi/web/uploads/images/evenement/" + obj.get("image").toString()),
                                out,
                                ImageIO.FORMAT_JPEG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    a.setImage(im);
                    a.setEspece(obj.get("espece").toString());
                    a.setDescription(obj.get("description").toString());
                    a.setSexe(obj.get("sexe").toString());
                    a.setNomVet(obj.get("nomVet").toString());
                    float poids = Float.parseFloat(obj.get("poids").toString());
                    int poids2 = (int) poids;
                    a.setPoids(poids2);
                    float taille = Float.parseFloat(obj.get("taille").toString());
                    int taille2 = (int) taille;
                    a.setTaille(taille2);
                     listAnimal.add(new Animaux(a.getNom(), a.getEspece(), a.getSexe(), a.getDescription(), a.getTaille(), a.getPoids(), a.getImage(), a.getNomVet()));

                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnimal;
    }
}
