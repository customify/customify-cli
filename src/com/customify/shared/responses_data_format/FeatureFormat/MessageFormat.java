package com.customify.shared.responses_data_format.FeatureFormat;

import java.io.Serializable;

public class MessageFormat implements Serializable {
    public  String message;
    public  MessageFormat(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public  MessageFormat(String message){
        this.message = message;
    }

}
