/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ADMIN
 */
public class Request {
    private int id;
    private int idanimal;
    private String Type;
    private int iduser;
    private String Content;
    private int source;
    public ObservableList<Request> requestList = FXCollections.observableArrayList();

    public Request(int idanimal, String Type, int iduser, String Content, int source) {
        this.idanimal = idanimal;
        this.Type = Type;
        this.iduser = iduser;
        this.Content = Content;
        this.source = source;
    }

    public Request(int id, int idanimal, String Type, int iduser, String Content, int source) {
        this.id = id;
        this.idanimal = idanimal;
        this.Type = Type;
        this.iduser = iduser;
        this.Content = Content;
        this.source = source;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    
    public Request() {
    }

    public Request(int id, int idanimal, String Type, int iduser, String Content) {
        this.id = id;
        this.idanimal = idanimal;
        this.Type = Type;
        this.iduser = iduser;
        this.Content = Content;
    }
    
    public Request(int idanimal, String Type, int iduser, String Content) {
        this.idanimal = idanimal;
        this.Type = Type;
        this.iduser = iduser;
        this.Content = Content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdanimal() {
        return idanimal;
    }

    public void setIdanimal(int idanimal) {
        this.idanimal = idanimal;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
    
    
}
