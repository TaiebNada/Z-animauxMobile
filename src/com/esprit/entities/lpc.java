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
public class lpc {
    private int id;
    private int idc;
    private int quantite;
    private int idproduit;

    public lpc(int id, int idc, int quantite, int idproduit) {
        this.id = id;
        this.idc = idc;
        this.quantite = quantite;
        this.idproduit = idproduit;
    }

    public lpc() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    @Override
    public String toString() {
        return "lpc{" + "id=" + id + ", idc=" + idc + ", quantite=" + quantite + ", idproduit=" + idproduit + '}';
    }

   
    
    
    
}
