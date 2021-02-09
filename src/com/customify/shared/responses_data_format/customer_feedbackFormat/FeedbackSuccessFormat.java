package com.customify.shared.responses_data_format.customer_feedbackFormat;

/*
 * @author: NSENGIYUMVA Gershom
 * 
 * @description
 * This class sets the messages as response to the every action going on.
 * */

import java.io.Serializable;

public class FeedbackSuccessFormat implements Serializable{
    private String successMessage;

    public FeedbackSuccessFormat(){}
    public FeedbackSuccessFormat(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getMessage() {
        return successMessage;
    }

    public void setMessage(String successMessage) {
        this.successMessage = successMessage;
    }   
}
