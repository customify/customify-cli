package com.customify.shared.responses_data_format.BillingFeature;

import java.io.Serializable;

public class MessageFormat implements Serializable {
    public String message;

    public  MessageFormat(){}
    public  MessageFormat( String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
