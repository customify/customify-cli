package com.customify.server.models;

import java.util.Date;

//Hagenimana Yassin created at 3/2/2020
//this is notification model shows all entities or properties of notifications sent to customer when an award is won

public class NotificationModel {
  private   int Notification_id;
  private   int Customer_id;
  private   Date Created_at;
  private   String description;
  private   String Title;



    public NotificationModel(int notification_id, int customer_id, Date created_at, String description, String title) {
        this.Notification_id = notification_id;
        this.Customer_id = customer_id;
        this.Created_at = created_at;
        this.description = description;
        this.Title = title;

    }

    public int getNotification_id() {
        return Notification_id;
    }

    public void setNotification_id(int notification_id) {
        this.Notification_id = notification_id;
    }

    public int getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(int customer_id) {
       this.Customer_id = customer_id;
    }

    public Date getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(Date created_at) {
        this.Created_at = created_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

}
