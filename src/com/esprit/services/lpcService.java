/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.entities.lpc;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;



/**
 *
 * @author Firqs
 */
public class lpcService {
    
    

        
    
   public void ajoutTask(lpc ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ProjetPI2018/web/app_dev.php/api/animalSOS/Ajout/" + ta.getId() + "/" + ta.getIdc() + "/" + ta.getQuantite() + "/" + ta.getIdproduit() ;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
}