package com.customify.server.utils;

import com.customify.server.Db.Db;
import com.customify.server.services.NotificationService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MailCustomers extends Thread{
    private String subject;
    private String message;

    public MailCustomers(String productName) {
        this.subject = "[Customify] New product available now";
        this.message = "<p style='color:grey'>Hello customer,</p>" +
                "<h3>New Products are available!</h3> "+
                "<h2><small>Come and shop</small>&nbsp;&nbsp;<span style='color:dodgerblue'>"+ productName+"</span></h2>"+
                "<p style='color:gray;font-size:16px'>With our endless customer care, we brought to you new product<br/> " +
                "with even more satisfaction from your expectations.<br> Come and shop among the first customers to get adorable discounts in this season.</p>"+
                "<h2 style='color:blue'>Customify.</h2>";
    }

    public MailCustomers(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    @Override
    public void run() {
        List<String> customerEmails = null;
        try {
            customerEmails = getCustomerEmails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        mailCustomers(customerEmails);
    }

    private void mailCustomers(List<String> customerEmails) {
        Properties properties = new Properties();
        String mailFrom = "hagenimanayassin84.com@gmail.com";//properties.getProperty("mailFrom");
        String password = "yassin@2020"; //properties.getProperty("mailPassword");
        NotificationService notificationService = new NotificationService();


        for (int i = 0; i < customerEmails.size(); i++) {
            notificationService.SendNotification(mailFrom,password,customerEmails.get(i),subject,message);
        }
    }

    private List<String> getCustomerEmails() throws SQLException {
        Statement statement = Db.getStatement();
        ResultSet resultSet = statement.executeQuery("SELECT email FROM Customer WHERE disable=0");
        List<String> emails = new ArrayList<>();

        while (resultSet.next()){
            emails.add(resultSet.getString("email"));
        }

        return  emails;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
