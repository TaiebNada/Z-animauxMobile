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
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.ImageIO;
import com.codename1.uikit.cleanmodern.SignInForm;
import com.esprit.entities.Animal;
import com.esprit.entities.Animaux;
import com.esprit.entities.User;
import com.esprit.entities.vet;
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

      /* Affichage liste Animaux Client */
    public ArrayList<Animaux> getList() {
        ArrayList<Animaux> listAnimal = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/phpStormProjects/ProjetNada/ProjetPi/web/app_dev.php/Animaux1/maListeJson/"+SignInForm.getIdU());
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
                    float id = Float.parseFloat(obj.get("idAnimal").toString());
                System.out.println(id);
                a.setId((int) id);
                    a.setEspece(obj.get("espece").toString());
                    a.setNom(obj.get("nom").toString());

                   // a.setImagePath("file://C:/wamp64/www/phpStormProjects/ProjetNada/ProjetPi/web/uploads/images/evenement/" + obj.get("image").toString());
                    a.setImagePath(obj.get("image").toString());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                       // imageio.save(FileSystemStorage.getInstance().openInputStream("file://C:/wamp64/www/phpStormProjects/ProjetNada/ProjetPi/web/uploads/images/evenement/" + obj.get("image").toString()),
                        imageio.save(FileSystemStorage.getInstance().openInputStream(obj.get("image").toString()),
                                out,
                                ImageIO.FORMAT_JPEG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    a.setImage(im);
                    //a.setId(obj.get("id").toString());
                    a.setEspece(obj.get("espece").toString());
                    a.setDescription(obj.get("description").toString());
                    a.setSexe(obj.get("sexe").toString());
                    a.setNomVet(obj.get("nomVet").toString());
                    float poids = Float.parseFloat(obj.get("poids").toString());
                    int poids2 = (int) poids;
                    a.setPoids(poids2);
                    a.Poids=(obj.get("poids").toString());
                    a.Taille=(obj.get("taille").toString());
                    a.DateNaissance=(obj.get("dateNaissance").toString());
                    a.DateVaccin=(obj.get("dateVaccin").toString());
                    a.DateVisite=(obj.get("dateVisiteD").toString());
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

    /*Ajout Animal */
    public void ajoutAnimal(Animaux ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/phpStormProjects/ProjetNada/ProjetPi/web/app_dev.php/Animaux1/ajoutAnimalJson/"+SignInForm.getIdU()+"?nom=" + ta.getNom() + "&espece=" + ta.getEspece() + "&sexe=" + ta.getSexe()
                + "&description=" + ta.getDescription() + "&nomVet=" + ta.getNomVet()+ "&dateNaissance="+ta.getDateNaissance() +  "&image=" + ta.getImagePath();
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
        System.out.println("Ajout avec succée");

    }
    
    /*Afficher liste Vet*/
       public ArrayList<vet> getListVet() {
        ArrayList<vet> listAnimal = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/phpStormProjects/ProjetNada/ProjetPi/web/app_dev.php/Animaux1/listeVetJson");
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
                   vet a = new vet();
                    float id = Float.parseFloat(obj.get("id").toString());
                    a.setId((int) id);
                    a.setNom(obj.get("nom").toString());
                    a.setEmail(obj.get("email").toString());

                    
                   
                    listAnimal.add(new vet(a.getId(), a.getNom(),a.getEmail()));

                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnimal;
    }
    
   
    /*Update Animal*/
    public void updateAnimal(Animaux ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/phpStormProjects/ProjetNada/ProjetPi/web/app_dev.php/Animaux1/updateAnimalJson?id="+ta.getId()+"&nom=" + ta.getNom() + "&espece=" + ta.getEspece() + "&sexe=" + ta.getSexe()
                + "&description=" + ta.getDescription() + "&nomVet=" + ta.getNomVet()+ "&dateNaissance=2018-12-12" +  "&image=" + ta.getImagePath();
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
        System.out.println("Ajout avec succée");

    }
       
    /*Delete Animal */
    
    /*Find Animal by : nom / espece*/ 
}
