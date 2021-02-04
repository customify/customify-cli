package com.customify.shared.responses_data_format.AuthFromats;

import java.io.Serializable;

public class FeedbackSuccessFormat implements Serializable{
    private int customer_id;

    public FeedbackSuccessFormat(){}
    public FeedbackSuccessFormat(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCustomerID() {
        return customer_id;
    }

    public void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }   
}
