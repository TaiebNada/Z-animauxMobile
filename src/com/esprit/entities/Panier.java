/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Firqs
 */

public class Panier {
    
   
  private Map<Produit,Integer> LignedeCommande = new HashMap<Produit, Integer>() ;
   // private Commandes commande;
  //  private User idUser;
   // private Produits idProduit;

    public Panier() {
    }

    public Panier(Map<Produit, Integer> LignedeCommande) {
        this.LignedeCommande = LignedeCommande;
    }

    public Map<Produit, Integer> getLignedeCommande() {
        return LignedeCommande;
    }

    public void setLignedeCommande(Map<Produit, Integer> LignedeCommande) {
        this.LignedeCommande = LignedeCommande;
    }

  
    
 
    
}
