package com.customify.server.models;

import java.util.Date;

public class AwardsNotifications {
    private int id;
    private int customer_id;
    private Date created_at;
    private String description;
    private String title;
    private boolean isRecieved;

    public AwardsNotifications(int id, int customer_id, Date created_at, String description, String title, boolean isRecieved) {
        this.id = id;
        this.customer_id = customer_id;
        this.created_at = created_at;
        this.description = description;
        this.title = title;
        this.isRecieved = isRecieved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRecieved() {
        return isRecieved;
    }

    public void setRecieved(boolean recieved) {
        isRecieved = recieved;
    }
}
