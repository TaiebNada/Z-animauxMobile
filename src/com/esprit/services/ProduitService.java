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
import com.codename1.ui.util.ImageIO;
import com.esprit.entities.Produit;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Firqs
 */
public class ProduitService {
    
    
     public ArrayList<Produit> getListCategorie(String Categorie) {
        ArrayList<Produit> listProduit = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi/web/app_dev.php/api/mobile/find/"+Categorie);
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();

            try {
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                for (Map<String, Object> obj : list) {
                    int mm = Display.getInstance().convertToPixels(3);
                    Produit a = new Produit();
                    a.setNom(obj.get("nom").toString());
                    a.setPrix(Float.parseFloat(obj.get("prix").toString()));
                    //a.setQuantite_stock(Integer.parseInt(obj.get("quantiteStock").toString()));
                    a.setCategorie(obj.get("categorie").toString());
                    a.setImagePath("file:///C:/wamp64/www/pi/web/uploads/images/" + obj.get("image").toString());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file:///C:/wamp64/www/pi/web/uploads/images/" + obj.get("image").toString()),
                                out,
                                ImageIO.FORMAT_JPEG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    a.setImage(im);
                    listProduit.add(new Produit(a.getNom(), a.getPrix(), a.getCategorie(),a.getQuantite_stock(), a.getImage()));

                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduit;
    }

   public ArrayList<Produit> getList() {
        ArrayList<Produit> listProduit = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi/web/app_dev.php/api/mobile/all");
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();

            try {
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                for (Map<String, Object> obj : list) {
                    int mm = Display.getInstance().convertToPixels(3);
                    Produit a = new Produit();
                    a.setNom(obj.get("nom").toString());
                    a.setPrix(Float.parseFloat(obj.get("prix").toString()));
                    //a.setQuantite_stock(Integer.parseInt(obj.get("quantiteStock").toString()));
                    a.setCategorie(obj.get("categorie").toString());
                    a.setImagePath("file:///C:/wamp64/www/pi/web/uploads/images/" + obj.get("image").toString());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file:///C:/wamp64/www/pi/web/uploads/images/" + obj.get("image").toString()),
                                out,
                                ImageIO.FORMAT_JPEG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    a.setImage(im);
                    listProduit.add(new Produit(a.getNom(), a.getPrix(), a.getCategorie(),a.getQuantite_stock(), a.getImage()));

                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduit;
    }


    public Produit recherche(String categorie) {
        Produit a = new Produit();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi/web/app_dev.php/api/mobile/find/"+ categorie);
       
        con.addResponseListener((NetworkEvent evt) -> {
            //listOffres = getListOffre(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> skills = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(skills);
                System.out.println(skills.keySet());
                System.out.println(skills.values());
                int mm = Display.getInstance().convertToPixels(3);
                List<Map<String, Object>> list = (List<Map<String, Object>>) skills.get("root");
                for (Map<String, Object> obj : list) {
                    float id1 = Float.parseFloat(obj.get("id").toString());
                    a.setId((int) id1);
                    System.out.println(id1);
                    a.setNom(obj.get("nom").toString());
                    a.setPrix(Float.parseFloat(obj.get("prix").toString()));
                    a.setQuantite_stock(Integer.parseInt(obj.get("quantite_stock").toString()));
                   // a.setCategorie(obj.get("categorie").toString());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file:///C:/wamp64/www/pi/web/uploads/images" + obj.get("image").toString()),
                                out,
                                ImageIO.FORMAT_JPEG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    a.setImage(im);
                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return a;
    }
}
