/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ApplicationScoped;

import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Emprunt;
import model.Utilisateur;

/**
 *
 * @author alex-dev
 */
@Named
@SessionScoped
public class MailSend  implements Serializable{

    @Resource(name = "jndi/mediatheque")
    private Session session;

    public MailSend() {

    }

    public void sendCredentials(Utilisateur u) {
        MimeMessage message = new MimeMessage(session);

        try {

            message.setFrom(new InternetAddress(session.getProperty("mail.from")));

            InternetAddress[] address = {new InternetAddress(u.getMail())};

            message.setRecipients(Message.RecipientType.TO, address);

            message.setSubject("Vos Credentials");

            message.setSentDate(new Date());

            message.setText("user : "+u.getLogin()+ "votre mot de passe : "+ u.getMdp());

            Transport.send(message);

        } catch (MessagingException ex) {

            ex.printStackTrace();

        }
    }
    
        
     public void sendMailNOtification(Emprunt e) {
        
         MimeMessage message = new MimeMessage(session);

        try {

            message.setFrom(new InternetAddress(session.getProperty("mail.from")));

            InternetAddress[] address = {new InternetAddress(e.getIdUtilisateur().getMail())};

            message.setRecipients(Message.RecipientType.TO, address);

            message.setSubject("La date de Retour du livre"+e.getIdExemplaire().getCodeLivre().getTitre()+"est depase");

            message.setSentDate(new Date());

            message.setText("Merci par votre atention");

            Transport.send(message);

        } catch (MessagingException ex) {

            ex.printStackTrace();

        }
    }

}
