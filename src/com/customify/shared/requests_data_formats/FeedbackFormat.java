package com.customify.shared.requests_data_formats;

/**
 * NSENGIYUMVA Gershom
 * 
 * This class is all about defining the format of the data
 * to be input by the customer
 * */ 
import java.io.Serializable;

public class FeedbackFormat implements Serializable{
    private int customer_id, business_id;
    private String title, description;

    public FeedbackFormat(int customer_id, int business_id, String title, String description) {
        this.customer_id = customer_id;
        this.business_id = business_id;
        this.title = title;
        this.description = description;
    }

    public int getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getBusinessId() {
        return business_id;
    }

    public void setBusinessId(int business_id) {
        this.business_id = business_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
