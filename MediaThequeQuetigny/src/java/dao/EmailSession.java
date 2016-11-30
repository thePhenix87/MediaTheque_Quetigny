/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;


/**
 *
 * @author yasser
 */
@Named

@RequestScoped

public class EmailSession {

    @Resource(lookup = "jmail/mailmediatheque")
    
    private Session mailSession;

    public void sendEmail(String to, String subject, String body) {

        MimeMessage message = new MimeMessage(mailSession);

        try {

            message.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));

            InternetAddress[] address = {new InternetAddress(to)};

            message.setRecipients(Message.RecipientType.TO, address);

            message.setSubject(subject);

            message.setSentDate(new Date());

            message.setText(body);

            Transport.send(message);

        } catch (MessagingException ex) {

            ex.printStackTrace();

        }

    }
  
    
    
    

}