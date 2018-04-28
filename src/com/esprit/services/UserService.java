/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Nada
 */
public class UserService {
    
    
    /****Affichage partie 2******/
    
    ArrayList<User> listTasks = new ArrayList<>();
    
    public ArrayList<User> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetPi2/web/app_dev.php/Boutique/rechercheU/Nada");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                UserService ser = new UserService();
                listTasks = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
     
    /*****Ajout d'un utilisateur*****/
    
    public void ajoutUser(User ta) 
    {
        ConnectionRequest con = new ConnectionRequest();
       String Url = "http://localhost/ProjetPi2/web/app_dev.php/Boutique/ajouterUser?username="+ta.getUser()+"&email="+ta.getEmail()+"&password="+ta.getPassword();
       con.setUrl(Url);
        //System.out.println("tt");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
     
     
     
     /*****Modifier Profile d'un utilisateur*****/
     
      public void UpdateProfile(User ta) 
    {
        ConnectionRequest con = new ConnectionRequest();
       String Url = "http://localhost/ProjetPi2/web/app_dev.php/Boutique/update2/3?username="+ta.getUser()+"&email="+ta.getEmail()+"&password="+ta.getPassword();
       con.setUrl(Url);
        //System.out.println("tt");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
 
     /*****Affichage partie 1*****/
      
      public ArrayList<User> getListTask(String json) {

        ArrayList<User> listEtudiants = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();


        try {
            System.out.println(json);
            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");  
            User [] tab= new User[3];
            User u = new User();
            //float id = Float.parseFloat(valeur.get("id").toString());
               // System.out.println(id);
                //e.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
//                System.out.println("traaaah"+etudiants.get("username").toString());
                u.setUser(etudiants.get("username").toString());
                u.setEmail(etudiants.get("email").toString());
                u.setPassword(etudiants.get("password").toString());
                u.setAdresse("a");
                u.setAge("1");
                u.setCin("1111");
                u.setEtat("eeee");
                u.setId(1);
                u.setNom("nom");
                u.setTel("tel");
                u.setPrenom("prenom");
                u.setImage("image");
                u.setType("type");
               users.add(u);
              System.out.println("nadaaaaaaa");

        } catch (IOException ex) {
        }
        //System.out.println(users);
        return users;

    }
    
  
      
      public ArrayList<User> rechercheSkill(String text) {
      ArrayList<User> listSkills = new ArrayList<>();
      ConnectionRequest con = new ConnectionRequest();
      con.setUrl("http://localhost/ProjetPi2/web/app_dev.php/Boutique/rechercheU/"+text);
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
                        User skill = new User();
                        float id = Float.parseFloat(obj.get("id").toString());
                        skill.setId((int) id);
                        
                        skill.setUser(obj.get("username").toString());
                        skill.setEmail(obj.get("email").toString());
                        skill.setPassword(obj.get("password").toString());
                        listSkills.add(skill);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listSkills;
    }
    
      
     
}
