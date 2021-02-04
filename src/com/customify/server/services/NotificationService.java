package com.customify.server.services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Hagenimana Yassin created at 3/2/2021
// this is a service notification which sends email to customer who won an award.

public class NotificationService {
    public static void send(String mailFrom, String password, String mailTo, String subject, String msg){
        //Get properties object

        Properties props = new Properties();


        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailFrom,password);
                    }
                });


        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(mailTo));
            message.setSubject(subject);
            message.setText(msg);
            //send message
            Transport.send(message);
            System.out.println("MESAGE SENT SUCCESSFULLY!!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws Exception {

        Properties prop = new Properties();
        String fileName = "config.properties";
        InputStream is = null;

        try {
            is = new FileInputStream(fileName);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            prop.load(is);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        send(prop.getProperty("mailFrom"),prop.getProperty("mailPassword"),prop.getProperty("mailTo"),"Heading"," HI,How are u? you have won " +
                "an award from our shop located in mukamira secor, nyabihu district.if you have time you can come to pick it.");
    }
}
