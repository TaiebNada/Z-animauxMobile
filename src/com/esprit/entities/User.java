/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author Nada
 */
public class User {
    
    
     private int id ;
    private String nom;
    private String prenom;
    private String user;
    private String age;
    private String email;
    private String adresse;
    private String tel;
    private String password;
    private String type;
    private String cin;
    private String etat;
    private String image;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", user=" + user + ", age=" + age + ", email=" + email + ", adresse=" + adresse + ", tel=" + tel + ", password=" + password + ", type=" + type + ", cin=" + cin + ", etat=" + etat + ", image=" + image + '}';
    }

    public User(int id, String nom, String prenom, String user, String age, String email, String adresse, String tel, String password, String type, String cin, String etat, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.user = user;
        this.age = age;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.password = password;
        this.type = type;
        this.cin = cin;
        this.etat = etat;
        this.image = image;
    }

    public User() {
    }

    public User(String user, String email, String password) {
        this.user = user;
        this.email = email;
        this.password = password;
    }

    public User(int id, String user, String email, String password) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.password = password;
    }
    
    
}
