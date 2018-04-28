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
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import static com.codename1.ui.layouts.BoxLayout.x;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entities.User;
import com.esprit.services.UserService;
import java.util.Random;

import com.codename1.io.Log;
//import java.net.PasswordAuthentication;




/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {
    
   
    public  static String usernameU;
    public  static String emailU;
    public  static String passwordU;
    public  static int code;
    
    public  static void setUsernameU(String username) {
        SignUpForm.usernameU = username;
    }

    public  static  String getUsernameU() 
    {
        return usernameU;
    }
    
    
    public  static void setEmailU(String email) {
        SignUpForm.emailU = email;
    }
    public  static  String getEmailU() 
    {
        return emailU;
    }
    
    
    public  static void setPasswordU(String password) {
        SignUpForm.passwordU = password;
    }

    public  static  String getPasswordU() 
    {
        return passwordU;
    }
    

    public  static  int getCode() 
    {
        /*int Low = 10000;
        int High = 100000;    
        Random r = new Random();
        int a = r.nextInt(High - Low);
        return a;*/
        return code;
        
    }
    public  static void setCode(int code) {
        SignUpForm.code = code;
    }
    
    
    
    
   
    
    public SignUpForm(Resources res)  {
        
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        
        
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField tel = new TextField("", "Telephone", 20, TextField.PASSWORD);
        //TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        tel.setSingleLineTextArea(false);
        
      //  confirmPassword.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(tel),
                createLineSeparator()
             //   new FloatingHint(confirmPassword),
              //  createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
  
        next.addActionListener((e) -> {
    
     
                    // int r1 = r.nextInt(High - Low) + Low;
                    
        int Low = 10000;
        int High = 100000;    
        Random r = new Random();
        int a = r.nextInt(High - Low);
            setCode(a);
       
          
            System.out.println("le a est : "+getCode());
     Message m = new Message("Votre code d'inscription est : "+getCode());
//m.getAttachments().put(textAttachmentUri, "text/plain");
//m.getAttachments().put(imageAttachmentUri, "image/png");
Display.getInstance().sendMessage(new String[] {email.getText()}, "Confirmation de votre code ", m);


            
//UserService ser = new UserService();
          //  User R = new User(username.getText(),email.getText(),password.getText());
            //User R = new User("nadddou","email de nada","123456");
            //ser.ajoutUser(R);
           // System.out.println(username.getText());
       setUsernameU(username.getText());
       setEmailU(email.getText());
       setPasswordU(password.getText());
       
          //  System.out.println(getCode());
       
       
       new ActivateForm(res).show();

        });
  



}
    
}