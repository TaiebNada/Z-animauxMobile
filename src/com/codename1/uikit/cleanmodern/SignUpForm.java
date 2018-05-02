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
//import com.codename1.messaging.Message;
import static com.codename1.messaging.Message.sendMessage;
import com.codename1.ui.Dialog;
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.esprit.services.ReclamationService;
import java.io.IOException;
import java.util.Map;
//import java.net.PasswordAuthentication;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;




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
    
           /* if(!email.getText().contains("@"))
            {
                Dialog.show("Erreur","Le mail doit etre valide","Ok","Cancel");
            }*/
            
            
     
                    // int r1 = r.nextInt(High - Low) + Low;
                    
        int Low = 10000;
        int High = 100000;    
        Random r = new Random();
        int a = r.nextInt(High - Low);
            setCode(a);
       
          
            System.out.println("le a est : "+getCode());
     //Message m = new Message("Votre code d'inscription est : "+getCode());
//m.getAttachments().put(textAttachmentUri, "text/plain");
//m.getAttachments().put(imageAttachmentUri, "image/png");
//sendMessage(new String[] {email.getText()}, "Confirmation de votre code ", m);
                 /*switch(Display.getInstance().getSMSSupport()) {
                case Display.SMS_NOT_SUPPORTED:
                return;
                case Display.SMS_SEAMLESS:
                {
                try {
                showUIDialogToEditMessageData();
                Display.getInstance().sendSMS("54811120", "fffff");
                } catch (Exception ex) {
                System.out.println("eee");            }
                }
                return;
                default:
                {
                try {
                Display.getInstance().sendSMS("54811120", "ffffff");
                } catch (Exception ex) {
                System.out.println("eee");
                }
                }
                return;
                }*/
                
//Display.getInstance().getSMSSupport();
//Display.getInstance().sendSMS("54811120", "My SMS Message");
//Display.getInstance().set("+999999999", "My SMS Message");
             //   System.out.println("succes");



             ReclamationService ser = new ReclamationService();
             ser.Mail(email.getText(),getCode());
             
             
             
             setUsernameU(username.getText());
       setEmailU(email.getText());
       setPasswordU(password.getText());
            System.out.println("nnnn");
       new ActivateForm(res).show();


//Display.getInstance().setSMS("+999999999", "My SMS Message");
//Message m = new Message("essai");
//Message m = new Message("one");

//m.setMimeType(Message.MIME_HTML);
//boolean success = true;
// notice that we provide a plain text alternative as well in the send method
//m.sendMessageViaCloud("nadataieb6@gmail.com", "nada.taieb6@esprit.tn", "aaaaaa", "jjjj", "ffffff");
            
/*if (success) System.out.println("succes");
else System.out.println("errerrr");
            */  





    /*String ACCOUNT_SID =
            "AC4a5965ea5793d5dd6b2c57ef49e03731";
       String AUTH_TOKEN =
            "060436182b9799fc22cd434a9b511cf0";

    
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+21654811120"), // to
                        new PhoneNumber("+21654811120"), // from
                        "essai?")
                .create();
        
       // Message m = Mess

        System.out.println(message.getSid());
    */









});
            

       
        
  
        
        
        
        
        
        



}
    
}
