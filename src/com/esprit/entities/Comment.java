/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 *
 * @author ADMIN
 */
public class Comment {

    private int id;
    private String Commentaire;
//    private Blob imageA;
    private String imagePath;
    public Image image;
   // private Date dateinput;
    private int idanimal;
    public ObservableList<Comment> commentList = FXCollections.observableArrayList();

    public Comment(String Commentaire, Image image, int idanimal) {
        this.Commentaire = Commentaire;
        this.image = image;
        this.idanimal = idanimal;
    }

    

    public int getIdanimal() {
        return idanimal;
    }

    public void setIdanimal(int idanimal) {
        this.idanimal = idanimal;
    }

    public Comment(String Commentaire) {
        this.Commentaire = Commentaire;
    }
  
 
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public void setCommentaire(String Commentaire) {
        this.Commentaire = Commentaire;
    }
   

}
