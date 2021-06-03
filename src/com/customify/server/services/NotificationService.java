// Hagenimana Yassin created at 3/2/2021
// this is a service notification which sends email to customer who won an award.
package com.customify.server.services;
import com.customify.server.Db.Db;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class NotificationService {
    /*
     * @author yassin hagenimana
     * this is SendNotification method receives parameters of mail from, mail to, subject of email and descriprion of message to customer
     * who won an award.
     */
    public void SendNotification(String mailFrom, String password, String mailTo, String subject, String msg) {

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
                        return new PasswordAuthentication(mailFrom, password);
                    }
                });

        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
            message.setSubject(subject);
            message.setContent(msg, "text/html");

            //send message
            Transport.send(message);
            System.out.println("MESSAGE SENT SUCCESSFULLY!!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /* this is method saving notification in database before sending it to customer.*/

    public void SaveNotifications(String customer_id,String title,String description){

        Properties prop = new Properties();
        String fileName = "config.properties";
        InputStream is = null;
        try { is = new FileInputStream(fileName); }
            catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage()); }
            try { prop.load(is); }
             catch (IOException ex) {
                System.out.println(ex.getMessage()); }

          try{ String query = "INSERT INTO Awards_Notifications(customer_id,title,description,created_at) VALUES(?,?,?,NOW())";
            Connection connection = Db.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,customer_id);

            statement.setString(2,title);
            statement.setString(3, description);

            if(statement.execute())
                System.out.println("Insertion done");
                Db.closeConnection(); }
                catch (SQLException e){
               System.out.println(e.getMessage());
              }
    }

    /*

    public  void sendEmail(){
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
        SaveNotifications();
       SendNotification(prop.getProperty("mailFrom"), prop.getProperty("mailPassword"), prop.getProperty("mailTo"), prop.getProperty("subject"),
              prop.getProperty("msg"));
    }*/
}