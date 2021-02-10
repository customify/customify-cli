package com.customify.shared.responses_data_format.AuthFromats;

import java.io.Serializable;

public class SuccessLoginFormat  implements Serializable {
   private String email;

    public SuccessLoginFormat(){}
    public SuccessLoginFormat(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
