/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;




/**
 *
 * @author bhk
 */
public class Participant {
    public int id ;
    public int user_id;
    public int evenement_id;
    public int participer;

    public Participant() {
    }

    public Participant(int id, int user_id, int evenement_id, int participer) {
        this.id = id;
        this.user_id = user_id;
        this.evenement_id = evenement_id;
        this.participer = participer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    public int getParticiper() {
        return participer;
    }

    public void setParticiper(int participer) {
        this.participer = participer;
    }
   

   
    

    
}
