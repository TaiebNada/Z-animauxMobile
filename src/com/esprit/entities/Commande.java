/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;



/**
 *
 * @author Firqs
 */
public class Commande {
    private int id;
    private String date_commande;
    private float montant;
    private String mode_paiement;

    public Commande( String date_commande, float montant, String mode_paiement) {
        this.date_commande = date_commande;
        this.montant = montant;
        this.mode_paiement = mode_paiement;
    }

    public Commande() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getMode_paiement() {
        return mode_paiement;
    }

    public void setMode_paiement(String mode_paiement) {
        this.mode_paiement = mode_paiement;
    }


    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", date_commande=" + date_commande + ", montant=" + montant + ", mode_paiement=" + mode_paiement + '}';
    }
    
    
    
    
}
