/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import com.codename1.ui.Image;


/**
 *
 * @author Firqs
 */
public class Produit {
    private int id;
    private String nom;
    private float prix;
    private Image image;
    private String imagePath;
    private int quantite_stock;
    private String categorie;
    private int vente;
    private float rating;
    private int nombre_de_vote;
    private int qte;

    public Produit(String nom, float prix,  String categorie,int quantite_stock,Image image) {
        
        this.nom = nom;
        this.prix = prix;
        this.image = image;
        this.quantite_stock = quantite_stock;
        this.categorie = categorie;
      
    }

    public Produit() {
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

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

   

    public int getQuantite_stock() {
        return quantite_stock;
    }

    public void setQuantite_stock(int quantite_stock) {
        this.quantite_stock = quantite_stock;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getVente() {
        return vente;
    }

    public void setVente(int vente) {
        this.vente = vente;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNombre_de_vote() {
        return nombre_de_vote;
    }

    public void setNombre_de_vote(int nombre_de_vote) {
        this.nombre_de_vote = nombre_de_vote;
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

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", image=" + image + ", imagePath=" + imagePath + ", quantite_stock=" + quantite_stock + ", categorie=" + categorie + ", vente=" + vente + ", rating=" + rating + ", nombre_de_vote=" + nombre_de_vote + ", qte=" + qte + '}';
    }




}