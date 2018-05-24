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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.cleanmodern.SignInForm;
import com.esprit.entities.Commentaire_evenement;
import com.esprit.entities.Evenement;
import com.esprit.entities.Participant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Map;

/**
 *
 * @author sana
 */
public class ServiceEvenement {

    public void ajoutevenement(Evenement e) {
        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/pi2/web/app_dev.php/Bienetre/Ajouttevenement?nomEvenement=" + e.getNom_evenement() + "& themeEvenement=" + e.getTheme_evenement() + "& lieuEvenement=" + e.getLieu_evenement() + "& nbrMAXParticipant=" + 0 + "& nbrParticipant=" + 50 + "& imageEvenement=" + e.getImage_evenement() + "& descriptionEvenement=" + e.getDescription_evenement();

        con.setUrl(Url);

        //System.out.println("tt");
        con.addResponseListener((x) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void ajoutCommentaire(Commentaire_evenement e, int id) {
        ConnectionRequest con = new ConnectionRequest();
String Url = "http://localhost/pi2/web/app_dev.php/Bienetre/AjouttCommentaire"+id+"?Comm=" + e.getComm()+ "& EvenementA="+  e.getEvenementA()+ "& Nom_user=" + e.getNom_user()+ "& Email_user=" + e.getEmail_user()+ "& DateCommentaire=" + e.getDateCommentaire()+ "& idutil=" + e.getIdutil();
        con.setUrl(Url);

        //System.out.println("tt");
        con.addResponseListener((x) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void jeparticipe(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pi2/web/app_dev.php/Bienetre/Participerr" + id;
        con.setUrl(Url);

        //System.out.println("tt");
        con.addResponseListener((x) -> {
            String str = new String(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

   public void supprimereve(Evenement eve) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pi2/web/app_dev.php/Bienetre/supprimerrev"+eve.getId();
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((x) -> {
            String str = new String(con.getResponseData());
            

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void supprimercom(Commentaire_evenement com) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/pi2/web/app_dev.php/Bienetre/supprimerrcom"+com.getId();
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((x) -> {
            String str = new String(con.getResponseData());
            

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
  
    public ArrayList<Evenement> getListEvenement(String json) {

        ArrayList<Evenement> listEvenement = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) Evenements.get("root");

            for (Map<String, Object> obj : list) {
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
                listEvenement.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEvenement);
        return listEvenement;

    }

    ArrayList<Evenement> listEvenements = new ArrayList<>();

    public ArrayList<Evenement> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi2/web/app_dev.php/Bienetre/Alll");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvenement ser = new ServiceEvenement();
                listEvenements = ser.getListEvenement(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvenements;
    }
    String lista;

    public ArrayList<Participant> getListParticipant(String json) {

        ArrayList<Participant> listParticipant = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Participants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) Participants.get("root");

            for (Map<String, Object> obj : list) {
                Participant e = new Participant();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));

                float user_id = Float.parseFloat(obj.get("user_id").toString());

                e.setUser_id((int) user_id);

                float evenement_id = Float.parseFloat(obj.get("evenement_id").toString());

                e.setEvenement_id((int) evenement_id);

                float Participer = Float.parseFloat(obj.get("Participer").toString());

                e.setParticiper((int) Participer);
                System.out.println("get listeebenemeny");
                System.out.println(e);
                listParticipant.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listParticipant);
        return listParticipant;

    }
    ArrayList<Participant> listParticipants = new ArrayList<>();

    public ArrayList<Participant> existe(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = ("http://localhost/pi2/web/app_dev.php/Bienetre/Existe" + id);
        con.setUrl(Url);

        //System.out.println("tt");
        con.addResponseListener((x) -> {
            String str = new String(con.getResponseData());
            ServiceEvenement ser = new ServiceEvenement();
            listParticipants = ser.getListParticipant(new String(con.getResponseData()));

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listParticipants;
    }
    String t;
    ArrayList l = new ArrayList<>();

    public ArrayList siexiste(int id) {

        ConnectionRequest con = new ConnectionRequest();
        String Url = ("http://localhost/pi2/web/app_dev.php/Bienetre/Existe" + id);
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvenement ser = new ServiceEvenement();

                String t = new String(con.getResponseData());
                l.add(t);

                System.out.println(t);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println(t);
        return l;

    }

    ArrayList<Commentaire_evenement> listCommentaire_evenement = new ArrayList<>();

    public ArrayList<Commentaire_evenement> getList2Commentaire(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pi2/web/app_dev.php/Bienetre/AlllCommentaire" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvenement ser = new ServiceEvenement();
                listCommentaire_evenement = ser.getListCommentaire_evenement(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCommentaire_evenement;
    }

     
      public ArrayList<Commentaire_evenement> verifsupp(int id){       
        ConnectionRequest con = new ConnectionRequest();
       con.setUrl("http://localhost/pi2/web/app_dev.php/Bienetre/verifsupp"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvenement ser = new ServiceEvenement();
                listCommentaire_evenement = ser.getListCommentaire_evenement(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCommentaire_evenement;
    }
     
     
     
   
    public ArrayList<Commentaire_evenement> getListCommentaire_evenement(String json) {

        ArrayList<Commentaire_evenement> listCommentaire_evenement = new ArrayList<>();

        try {

            JSONParser jss = new JSONParser();

            Map<String, Object> Commentaire_evenements = jss.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list4 = (List<Map<String, Object>>) Commentaire_evenements.get("root");

            for (Map<String, Object> obj : list4) {
                Commentaire_evenement e = new Commentaire_evenement();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));

                /*       e.setComm(obj.get("comm").toString());
                    e.setNom_user(obj.get("Nom_user").toString());
                    DateFormat format = new SimpleDateFormat("MMMM d, yyyy");
                String a    =obj.get("comm").toString();
Date date;
               
                    date = format.parse(a);
                    e.setDateCommentaire(date);*/
   
    
   try{
	Date date;
      	//String date;
 
        SimpleDateFormat sdfr= new SimpleDateFormat("dd/MM/yyyy");
       System.out.println(obj.get("dateCommentaire").toString());
               String a =obj.get("dateCommentaire").toString();
               System.out.println(obj.get("dateCommentaire"));
                    date = sdfr.parse(a);
                    e.setDateCommentaire(date);
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                e.setComm(obj.get("comm").toString());
                e.setNom_user(obj.get("nomuser").toString());

                listCommentaire_evenement.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listCommentaire_evenement);


  
  return listCommentaire_evenement;}
    
    }


