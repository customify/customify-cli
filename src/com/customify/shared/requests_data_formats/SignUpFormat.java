package com.customify.shared.requests_data_formats;

import java.io.Serializable;

public class SignUpFormat  implements Serializable {
    private String email;
    private String lastName;
    private String firstName;

    public SignUpFormat(String email,String lastName,String firstName){
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getFirstName(){
        return  this.firstName;
    }

    public String getLastName(){
        return  this.lastName;
    }

}
