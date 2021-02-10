package com.customify.client.data_format;

import com.customify.client.Keys;

public class CreateCustomerFormat {

    private Keys key;

    private String email;
    private String firName;
    private String lasName;

    public CreateCustomerFormat() {}


    public CreateCustomerFormat(String email, String lasName, String firName) {
        this.email = email;
        this.firName = firName;
        this.lasName = lasName;
        this.key = Keys.CREATE_CUSTOMER;
    }

    public String getEmail() {
        return this.email;
    }

    public Keys getKey() {
        return this.key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirName() {
        return this.firName;
    }

    public void setFirName(String firName) {
        this.firName = firName;
    }

    public String getLasName() {
        return this.lasName;
    }

    public void setLasName(String lasName) {
        this.lasName = lasName;
    }
}
