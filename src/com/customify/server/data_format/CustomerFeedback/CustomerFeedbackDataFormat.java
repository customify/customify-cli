package com.customify.server.data_format.CustomerFeedback;

/**
 * @author: NSENGIYUMVA Gershom
 * 
 * @Description
 * 
 * this class is for formatting the data to displayed to the users
 * 
 * done 7 Feb 2021
 * */
import java.io.Serializable;

public class CustomerFeedbackDataFormat implements Serializable {
    private int customer_id, business_id;
    private String title, description, creation_date;

    public CustomerFeedbackDataFormat(int customer_id, int business_id, String title, String description,
            String creation_date) {
        this.customer_id = customer_id;
        this.business_id = business_id;
        this.title = title;
        this.description = description;
        this.creation_date = creation_date;
    }

    // define the getters and setters
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

    public String getCreationDate() {
        return creation_date;
    }

    public void setCreationDate(String creation_date) {
        this.creation_date = creation_date;
    }

}
