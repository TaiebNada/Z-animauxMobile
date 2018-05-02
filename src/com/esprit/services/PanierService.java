/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.entities.Panier;
import com.esprit.entities.Produit;
import javafx.collections.ObservableMap;


/**
 *
 * @author Firqs
 */
public class PanierService{
    
 
    
    public static Panier lc = new Panier();

   

    public void ajouterLigneDeCommande(Produit p, int qt) {
        if (lc.getLignedeCommande().containsKey(p)) {
            lc.getLignedeCommande().put(p, lc.getLignedeCommande().get(p) + qt);
        } else {
            lc.getLignedeCommande().put(p, qt);
        }
        System.out.println("Produit ajouté!");
//        HttpCookie session = new HttpCookie("panier", null);
//        session = HttpCookie.parse("panier");
//        System.out.println(session.getValue());
    }

    
    public boolean supprimerLigneDecommande(Produit p, int qt) {
        if(lc.getLignedeCommande().containsKey(p))
        {
        if (lc.getLignedeCommande().get(p) >= qt) {
            lc.getLignedeCommande().put(p, lc.getLignedeCommande().get(p) - qt);
            if(lc.getLignedeCommande().get(p)<=0)
            {
                lc.getLignedeCommande().remove(p);
            }
            return true;
        }
        }
        return false;
        
    }


    
    public ObservableMap<Produit, Integer> consulterLigneDeCommandes() {
    return (ObservableMap) lc.getLignedeCommande();
    }

 
    public void modifierLigneDeCommande(Produit p) {
    }

}

