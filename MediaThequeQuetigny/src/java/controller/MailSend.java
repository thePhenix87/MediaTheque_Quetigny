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

/**
 *
 * @author alex-dev
 */
@Named
@SessionScoped
public class MailSend  implements Serializable{

    @Resource(name = "mail/smtp")
    private Session session;

    public MailSend() {

    }

    public void send() {
        MimeMessage message = new MimeMessage(session);

        try {

            message.setFrom(new InternetAddress(session.getProperty("mail.from")));

            InternetAddress[] address = {new InternetAddress("bob@bob.com")};

            message.setRecipients(Message.RecipientType.TO, address);

            message.setSubject("SUJET DU MAIL");

            message.setSentDate(new Date());

            message.setText("TEXTE DU MAIL");

            Transport.send(message);

        } catch (MessagingException ex) {

            ex.printStackTrace();

        }
    }
    
        
     public void send(Emprunt e) {
        
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
