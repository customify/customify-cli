package com.customify.server.response_data_format;

import java.io.Serializable;

public class AuthenticationResponseFormat {

    private String email;
    private String firName;
    private String lasName;
    private String id;
    private int business_id;
    private String title;
private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void AuthenticationDataFormat(){}

    public AuthenticationResponseFormat(String email, String firName, String lasName, String id,String title,int business_id,int status) {
        this.email = email;
        this.firName = firName;
        this.lasName = lasName;
        this.id = id;
        this.business_id = business_id;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirName() {
        return firName;
    }

    public void setFirName(String firName) {
        this.firName = firName;
    }

    public String getLasName() {
        return lasName;
    }

    public void setLasName(String lasName) {
        this.lasName = lasName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
