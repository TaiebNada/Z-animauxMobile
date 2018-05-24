
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import com.codename1.ui.Image;
import com.codename1.ui.Label;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Animal {

    private int id;
    private String Espece;
    private String Race;
    private String Sexe;
    private String Nom;
    private String Taille;
    private String Description;
    private String imagePath;
    private Image image;
    private String Type;
    private int Numero;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }
    
 public Animal(String Espece, String Race, String Sexe, String Nom, String Taille, String Description, String Type ,String imagePath, int Numero,Date date) {
        this.Espece = Espece;
        this.Race = Race;
        this.Sexe = Sexe;
        this.Nom = Nom;
        this.Taille = Taille;
        this.Description = Description;
        this.imagePath = imagePath;
        this.Type = Type;
        this.Numero= Numero;
        this.date = date;
    }

    public Animal(String Espece, String Race, String Sexe, String Nom, String Taille, String Description, String Type ,String imagePath) {
        this.Espece = Espece;
        this.Race = Race;
        this.Sexe = Sexe;
        this.Nom = Nom;
        this.Taille = Taille;
        this.Description = Description;
        this.imagePath = imagePath;
        this.Type = Type;
    }

    
    
    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public Animal(int id, String Espece, String Race, String Sexe, String Nom, String Taille, String Description, Image image, String Type) {
        this.id = id;
        this.Espece = Espece;
        this.Race = Race;
        this.Sexe = Sexe;
        this.Nom = Nom;
        this.Taille = Taille;
        this.Description = Description;
        this.image = image;
        this.Type = Type;
    }

    public Animal(String Espece, String Race, String Sexe, String Nom, String Taille, String Description, String Type) {
        this.Espece = Espece;
        this.Race = Race;
        this.Sexe = Sexe;
        this.Nom = Nom;
        this.Taille = Taille;
        this.Description = Description;
        this.Type = Type;
    }

    public Animal(int id, String Race, String Nom, Image image) {
        this.id = id;
        this.Race = Race;
        this.Nom = Nom;
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Animal(int id, String Race, String Nom, String imagePath) {
        this.id = id;
        this.Race = Race;
        this.Nom = Nom;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Animal() {
    }

    public Animal(int id, String Espece, String Race, String Sexe, String Nom, String Taille, String Description) {
        this.id = id;
        this.Espece = Espece;
        this.Race = Race;
        this.Sexe = Sexe;
        this.Nom = Nom;
        this.Taille = Taille;
        this.Description = Description;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the Espece
     */
    public String getEspece() {
        return Espece;
    }

    /**
     * @param Espece the Espece to set
     */
    public void setEspece(String Espece) {
        this.Espece = Espece;
    }

    /**
     * @return the Race
     */
    public String getRace() {
        return Race;
    }

    /**
     * @param Race the Race to set
     */
    public void setRace(String Race) {
        this.Race = Race;
    }

    /**
     * @return the Sexe
     */
    public String getSexe() {
        return Sexe;
    }

    /**
     * @param Sexe the Sexe to set
     */
    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }

    /**
     * @return the Nom
     */
    public String getNom() {
        return Nom;
    }

    /**
     * @param Nom the Nom to set
     */
    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    /**
     * @return the Taille
     */
    public String getTaille() {
        return Taille;
    }

    /**
     * @param Taille the Taille to set
     */
    public void setTaille(String Taille) {
        this.Taille = Taille;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

}
