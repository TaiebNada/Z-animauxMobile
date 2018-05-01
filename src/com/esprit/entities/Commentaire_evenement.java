/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.util.Date;




/**
 *
 * @author bhk
 */
public class Commentaire_evenement {
    public int id ;
   Evenement EvenementA;
    String comm;
     public String nom_user ;
     String email_user;
     Date dateCommentaire; 
      int idutil;
    public Commentaire_evenement() {
    }

    public Commentaire_evenement(Evenement EvenementA, String comm, String nom_user, String email_user) {
        this.EvenementA = EvenementA;
        this.comm = comm;
        this.nom_user = nom_user;
        this.email_user = email_user;
    }

    public Commentaire_evenement(int id, Evenement EvenementA, String comm, String nom_user, String email_user, Date dateCommentaire) {
        this.id = id;
        this.EvenementA = EvenementA;
        this.comm = comm;
        this.nom_user = nom_user;
        this.email_user = email_user;
        this.dateCommentaire = dateCommentaire;
    }

    public Commentaire_evenement(int id, Evenement EvenementA, String comm, String nom_user, String email_user, Date dateCommentaire, int idutil) {
        this.id = id;
        this.EvenementA = EvenementA;
        this.comm = comm;
        this.nom_user = nom_user;
        this.email_user = email_user;
        this.dateCommentaire = dateCommentaire;
        this.idutil = idutil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evenement getEvenementA() {
        return EvenementA;
    }

    public void setEvenementA(Evenement EvenementA) {
        this.EvenementA = EvenementA;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public Date getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public int getIdutil() {
        return idutil;
    }

    public void setIdutil(int idutil) {
        this.idutil = idutil;
    }

    
    
}
