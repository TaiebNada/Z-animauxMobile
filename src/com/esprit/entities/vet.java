/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author RYM
 */
public class vet {
    
    
     private int id ;
    private String nom;
    private String email;
    
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

   


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public vet(int id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public vet() {
    }

   
  

  
 
    
    
}
