/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import com.codename1.ui.Image;
import java.util.Date;

/**
 *
 * @author RYM
 */
public class Animaux {
    private int id;
    private String nom;
    private String espece;
    private String sexe;
    private Date dateNaissance;
    private Date dateVisiteD;
    private Date dateVaccin;
    private String description;
    private int taille;
    private int poids;
    private Image image;
     private String imagePath;
    private String nomVet;

    public Animaux(String nom, String espece, String sexe, Date dateNaissance, Date dateVisiteD, Date dateVaccin, String description, int taille, int poids, String nomVet) {
        this.nom = nom;
        this.espece = espece;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.dateVisiteD = dateVisiteD;
        this.dateVaccin = dateVaccin;
        this.description = description;
        this.taille = taille;
        this.poids = poids;
        this.nomVet = nomVet;
    }

    public Animaux() {
    }

    public Animaux(int id, String nom, String espece, String sexe, Image image, String nomVet) {
        this.id = id;
        this.nom = nom;
        this.espece = espece;
        this.sexe = sexe;
        this.image = image;
        this.nomVet = nomVet;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateVisiteD() {
        return dateVisiteD;
    }

    public void setDateVisiteD(Date dateVisiteD) {
        this.dateVisiteD = dateVisiteD;
    }

    public Date getDateVaccin() {
        return dateVaccin;
    }

    public void setDateVaccin(Date dateVaccin) {
        this.dateVaccin = dateVaccin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public String getNomVet() {
        return nomVet;
    }

    public void setNomVet(String nomVet) {
        this.nomVet = nomVet;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    
    
    
    
}
