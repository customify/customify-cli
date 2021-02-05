package com.customify.shared.responses_data_format.AuthFromats;

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
