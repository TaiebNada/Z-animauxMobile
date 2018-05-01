
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
import com.esprit.entities.Animal;
import com.esprit.entities.Spot;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class AnimalService {

    


  public void ajoutSpot(double latitude,double longitude,String image,String nom) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ProjetPI2018/web/app_dev.php/api/animalSOS/AjoutSpot?latitude=" + latitude + "&longitude=" +  longitude + "&image=" +  image+"&nom=" +  nom;
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

       public void ajoutRequest(String content,String img,int iduser) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ProjetPI2018/web/app_dev.php/api/animalSOS/AjoutRequest?content=" + content + "&img=" +  img + "&iduser=" +  iduser;
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void ajoutTask(Animal ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url= "http://localhost/ProjetPI2018/web/app_dev.php/api/animalSOS/Ajout?Espece=" + ta.getEspece()+ "& Race="+  ta.getRace()+ "& Nom=" + ta.getNom()+ "& Taille=" + ta.getTaille()+ "& Description=" + ta.getDescription()+"& image1=" + ta.getImagePath();
       // String Url = "http://localhost/ProjetPI2018/web/app_dev.php/api/animalSOS/Ajout/" + ta.getEspece() + "/" + ta.getRace() + "/" + ta.getNom() + "/" + ta.getTaille() + "/" + ta.getDescription();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Map<String, Object>> getList2() {
        ArrayList<Map<String, Object>> listAnimal = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetPI2018/web/app_dev.php/api/animalSOS/All");
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();

            try {
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                //System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                for (Map<String, Object> obj : list) {
                    Animal a = new Animal();
                    float id = Float.parseFloat(obj.get("id").toString());
                    int mm = Display.getInstance().convertToPixels(3);
                    a.setId((int) id);
                    a.setRace(obj.get("race").toString());
                    a.setNom(obj.get("nom").toString());
                    a.setImagePath("file:///C:/xampp/htdocs/ProjetPI2018/web/uploads/images/evenement/" + obj.get("image1").toString());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file:///C:/xampp/htdocs/ProjetPI2018/web/uploads/images/evenement/" + obj.get("image1").toString()),
                                out,
                                ImageIO.FORMAT_JPEG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    a.setImage(im);
                    listAnimal.add(createListEntry(a.getNom(), "17/12/2001", im));
                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnimal;
    }

    public ArrayList<Animal> getList() {
        ArrayList<Animal> listAnimal = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetPI2018/web/app_dev.php/api/animalSOS/All");
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
                    Animal a = new Animal();
                    float id = Float.parseFloat(obj.get("id").toString());
                    a.setId((int) id);
                    a.setRace(obj.get("race").toString());
                    a.setNom(obj.get("nom").toString());

                    a.setImagePath("file:///C:/xampp/htdocs/ProjetPI2018/web/uploads/images/evenement/" + obj.get("image1").toString());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file:///C:/xampp/htdocs/ProjetPI2018/web/uploads/images/evenement/" + obj.get("image1").toString()),
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
                    a.setTaille(obj.get("taille").toString());
                    a.setType(obj.get("type").toString());
                    listAnimal.add(new Animal(a.getId(), a.getEspece(), a.getRace(), a.getSexe(), a.getNom(), a.getTaille(), a.getDescription(), im, a.getType()));

                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnimal;
    }

    private Map<String, Object> createListEntry(String name, String date, Image icon) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("Line2", date);
        entry.put("icon", icon);
        return entry;
    }

    private Map<String, Object> createListEntry2(String name, Image icon) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("icon", icon);
        entry.put("Nom :", name);
        return entry;
    }

    public Animal recherche(int id) {
        Animal a = new Animal();
        ConnectionRequest con = new ConnectionRequest();
        System.out.println("idddddddddd" + id);
        con.setUrl("http://localhost/ProjetPI2018/web/app_dev.php/api/animalSOS/Find/" + id);

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
                    a.setEspece(obj.get("espece").toString());
                    a.setNom(obj.get("nom").toString());
                    a.setDescription(obj.get("description").toString());
                    a.setSexe(obj.get("sexe").toString());
                    a.setTaille(obj.get("taille").toString());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file:///C:/xampp/htdocs/ProjetPI2018/web/uploads/images/evenement/" + obj.get("image1").toString()),
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
public ArrayList<Spot> getListSpot() {
        ArrayList<Spot> listSpot = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetPI2018/web/app_dev.php/api/animalSOS/AllSpot");
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();

            try {
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                //System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                for (Map<String, Object> obj : list) {
                  listSpot.add(new Spot(Double.parseDouble(obj.get("longitude").toString()),Double.parseDouble(obj.get("latitude").toString()), obj.get("image").toString(), obj.get("nom").toString()));
                   // listAnimal.add(new Animal(a.getId(), a.getEspece(), a.getRace(), a.getSexe(), a.getNom(), a.getTaille(), a.getDescription(), im, a.getType()));

                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listSpot;
    }
  
}
