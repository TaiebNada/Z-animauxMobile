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
import com.codename1.io.NetworkManager;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.util.ImageIO;
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

                List<Map<String, Object>> list = (List<Map<String, Object>>) animaux.get("root");

                for (Map<String, Object> obj : list) {
                    Animaux an = new Animaux();
                    an.setNomVet(obj.get("nomVet").toString());
                    an.setNom(obj.get("nom").toString());
                    an.setEspece(obj.get("espece").toString());
                    an.setDescription(obj.get("description").toString());
                    an.setSexe(obj.get("sexe").toString());
                   an.setPoids((Integer)obj.get("poids"));
                    an.setTaille((Integer)obj.get("taille"));
                    an.setDateVaccin((Date)obj.get("dateVaccin"));
                    an.setDateVisiteD((Date)obj.get("dateVisiteD"));
                    
                      int mm = Display.getInstance().convertToPixels(3);
                    an.setImagePath("file://C:/wamp64/www/phpStormProjects/ProjetNada/ProjetPi/web/uploads/images" + obj.get("image").toString());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm* 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file://C:/wamp64/www/phpStormProjects/ProjetNada/ProjetPi/web/uploads/images" + obj.get("image").toString()),
                                out,
                                ImageIO.FORMAT_JPEG,
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
        
        return mapPanier;
    }
}
