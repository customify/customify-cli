package com.customify.client.data_format;

import com.customify.client.Keys;

public class AuthenticationDataFormat {

    private Keys key =Keys.AUTHENTICATION;
    private String email;
    private String password;

    public AuthenticationDataFormat(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
