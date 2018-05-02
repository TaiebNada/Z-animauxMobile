/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entities.User;
import com.esprit.services.UserService;
import java.util.ArrayList;






/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

    
    public  static String usernameU;
    public  static String emailU;
    public  static String passwordU;
    public  static Image imageU;
     public  static int idU;
    public  static int code;
    
    public  static void setUsernameU(String username) {
        SignInForm.usernameU = username;
    }

    public  static  String getUsernameU() 
    {
        return usernameU;
    }
    
    
    public  static void setIdU(int id) {
        SignInForm.idU = id;
    }

    public  static  int getIdU() 
    {
        return idU;
    }
    
    public  static void setEmailU(String email) {
        SignInForm.emailU = email;
    }
    public  static  String getEmailU() 
    {
        return emailU;
    }
    
    
    public  static void setPasswordU(String password) {
        SignInForm.passwordU = password;
    }

    public  static  String getPasswordU() 
    {
        return passwordU;
    }
    
    
    public  static void setImageU(Image im) {
        SignInForm.imageU = im;
    }
    public  static  Image getImageU() 
    {
        return imageU;
    }
    
    
    
     
    
    public SignInForm(Resources res) {
        super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));
        
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        signIn.addActionListener(e -> 
        {
           
<<<<<<< HEAD
            UserService ser= new UserService();
            //System.out.println(ser.rechercheSkill(username.getText()));
            for (int i =0 ; i<1 ;i++)
=======
            /**UserService ser= new UserService();
            System.out.println(ser.rechercheSkill(username.getText()));
            for (int i =0 ; i<ser.rechercheSkill(username.getText()).size() ;i++)
>>>>>>> 96f3eea390abce1d4ac6e8fbb4ca2d8cc2e715a9
                
            {
                
                //System.out.println(ser.rechercheSkill(username.getText()).get(i).getUser());
                if (username.getText().equals(ser.rechercheSkill(username.getText()).get(i).getUser())&& (password.getText().equals(ser.rechercheSkill(username.getText()).get(i).getPassword())) )
                {
                    setUsernameU(username.getText());
                    setPasswordU(password.getText());
                    setEmailU(ser.rechercheSkill(username.getText()).get(i).getEmail());
                    setIdU(ser.rechercheSkill(username.getText()).get(i).getId());
                    //setImageU(ser.rechercheSkill(username.getText()).get(i).getImage());

                    
                    new NewsfeedForm(res).show();
                }
                else 
                    { final Button showPopup = new Button("Show Popup");
            Dialog d = new Dialog("Popup Title");
        TextArea popupBody = new TextArea("login or password incorrect ! ", 3, 10);
        popupBody.setUIID("PopupBody");
        popupBody.setEditable(false);
        d.setLayout(new BorderLayout());
        d.add(BorderLayout.CENTER, popupBody);
        d.showPopupDialog(showPopup);}
        }
                
            */    
            
            /*for (int i =0 ; i<1 ;i++)
            {
                System.out.println("men houni");
                if (username.getText().equals(ser.getList2().get(i).getUser()) && (password.getText().equals(ser.getList2().get(i).getPassword())))
                {//System.out.println(ser.getList2().get(i).getUser());
                
                    setPasswordU(password.getText());
                    setUsernameU(username.getText());
                    System.out.println(usernameU);
                    System.out.println(passwordU);
                    System.out.println("aha");
                new NewsfeedForm(res).show();
                
                }
                else 
                    { final Button showPopup = new Button("Show Popup");
            Dialog d = new Dialog("Popup Title");
        TextArea popupBody = new TextArea("login or password incorrect ! ", 3, 10);
        popupBody.setUIID("PopupBody");
        popupBody.setEditable(false);
        d.setLayout(new BorderLayout());
        d.add(BorderLayout.CENTER, popupBody);
        d.showPopupDialog(showPopup);}
        }*/
                                new NewsfeedForm(res).show();

            });
        
        
        //setImageU("1.png");
        
    }
    
}

