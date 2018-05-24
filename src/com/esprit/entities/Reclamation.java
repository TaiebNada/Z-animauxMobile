

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import com.codename1.ui.Image;

/**
 *
 * @author Nada
 */
public class Reclamation {
    
    private int id;
    private String email;
    private String nom;
    private String tel;
    private String titre ;
    private String objet ;
    private Image image;
    private String imagePath;
    //private Date dateR;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    /*public Date getDateR() {
        return dateR;
    }

    public void setDateR(Date dateR) {
        this.dateR = dateR;
    }
*/
    
    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", email=" + email + ", nom=" + nom + ", tel=" + tel + ", titre=" + titre + ", objet=" + objet + ", image=" + image + ", imagePath=" + imagePath + '}';
    }

    public Reclamation() {
    }

    public Reclamation(int id, String email, String nom, String tel, String titre, String objet, Image image) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.tel = tel;
        this.titre = titre;
        this.objet = objet;
        this.image = image;
        //this.dateR = dateR;
    }

    public Reclamation(String email, String nom, String tel, String titre, String objet) {
        this.email = email;
        this.nom = nom;
        this.tel = tel;
        this.titre = titre;
        this.objet = objet;
    }

    public Reclamation(String email, String nom, String titre) {
        this.email = email;
        this.nom = nom;
        this.titre = titre;
    }

    public Reclamation(int id, String email, String nom, String tel, String titre, String objet, Image image, String imagePath) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.tel = tel;
        this.titre = titre;
        this.objet = objet;
        this.image = image;
        this.imagePath = imagePath;
    }

    public Reclamation(String email, String nom, String tel, String titre, String objet, String imagePath) {
        this.email = email;
        this.nom = nom;
        this.tel = tel;
        this.titre = titre;
        this.objet = objet;
        this.imagePath = imagePath;
    }
    
    
}

