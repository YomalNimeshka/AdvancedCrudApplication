package com.util;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author pemit
 */
public class EmailSend {

    public void send(String to, String subject, String content) throws AddressException, MessagingException {
        try {
            
            Properties p = System.getProperties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", "587");
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");

            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    PasswordAuthentication passwordAuthentication = new PasswordAuthentication("epiccrud123@gmail.com", "123456epic");
                    return passwordAuthentication;
                }

            };
            Session session = Session.getInstance(p, authenticator);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("epiccrud123@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            String html = content;
            message.setSubject(subject);
            message.setContent(html, "text/html");

            Transport.send(message);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
