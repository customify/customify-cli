package com.customify.server.response_data_format.authentication;

import java.io.Serializable;

public class AuthAdmin {

    private String email;
    private String firName;
    private String lasName;
    private String id;
    private String business_id;
    private String title;
    private int status;
    private String appUser;

    public String getAppUser() {
        return appUser;
    }

    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }

    public AuthAdmin(String appUser, String email, String firName, String lasName, String id, String business_id, String title, int status) {
        this.email = email;
        this.firName = firName;
        this.lasName = lasName;
        this.id = id;
        this.business_id = business_id;
        this.title = title;
        this.status = status;
        this.appUser = appUser;

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

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
