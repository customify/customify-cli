package com.customify.client.data_format.CustomerFeedback;

/**
 * @author: Stecie NIYONZIMA
 * 
 * Description
 * This class describes how the feedback data format will be looking like.
 * */ 
import com.customify.server.Keys;
import java.io.Serializable;

public class CustomerFeedbackFormat implements Serializable {

    private Keys key;
    private int customer_id;

    public CustomerFeedbackFormat(Keys key) {
        this.key = key;
    }

    public CustomerFeedbackFormat(int customer_id, Keys key) {
        this.customer_id = customer_id;
        this.key = key;
    }

    // define getters and setters for the key and customer_id

    public int getCustomerId(){
        return customer_id;
    }

    public void setCustomerId(int customer_id){
        this.customer_id = customer_id;
    }
    
    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}
