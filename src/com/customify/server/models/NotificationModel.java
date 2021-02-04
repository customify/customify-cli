package com.customify.server.models;

import java.util.Date;

public class NotificationModel {
  private   int Notification_id;
  private   int Customer_id;
  private   Date Created_at;
  private   String description;
  private   String Title;
  private   String isSeen;


    public NotificationModel(int notification_id, int customer_id, Date created_at, String description, String title, String isSeen) {
        this.Notification_id = notification_id;
        this.Customer_id = customer_id;
        this.Created_at = created_at;
        this.description = description;
        this.Title = title;
        this.isSeen = isSeen;
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

    public String getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(String isSeen) {
        this.isSeen = isSeen;
    }
}
