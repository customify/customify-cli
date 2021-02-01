package com.customify.shared.requests_data_formats;

import java.io.Serializable;

public class LoginFormat  implements Serializable {
    private String email;
    private String password;

    public LoginFormat(String email,String password){
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return  this.password;
    }
}
