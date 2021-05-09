package com.customify.server.data_format_CustomerFeedback;

/**
 * @author: NSENGIYUMVA Gershom
 *
 * @Description
 *
 * Setting and getting feedback data to be formatted
 *
 * done 7 Feb 2021
 * */
import java.io.Serializable;

public class CustomerFeedbackDataFormat implements Serializable {
    private String business_name, customer_name;
    private String title, description, creation_date;

    public CustomerFeedbackDataFormat( String customer_name, String business_name, String title, String description,
                                      String creation_date) {

        this.customer_name = customer_name;
       this.business_name = business_name;
        this.title = title;
        this.description = description;
        this.creation_date = creation_date;
    }

    // define the getters and setters

    public String getCustomer_name(){
        return customer_name;
    }

    public void setCustomer_name(String customer_name){
        this.customer_name = customer_name;
    }

    public String getBusiness_name(){
        return  business_name;
    }

    public void setBusiness_name(String business_name){
        this.business_name = business_name;
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
