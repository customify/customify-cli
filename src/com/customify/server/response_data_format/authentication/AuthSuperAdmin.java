package com.customify.server.response_data_format.authentication;

public class AuthSuperAdmin {

    private String email;
    private String firName;
    private String lasName;
    private String id;
    private String tel;
    private int status;
    private String appUser;

    public String getAppUser() {
        return appUser;
    }

    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public AuthSuperAdmin(String appUser,String email, String firName, String lasName, String id, String tel, int status) {
        this.email = email;
        this.firName = firName;
        this.lasName = lasName;
        this.id = id;
        this.status = status;
        this.tel = tel;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
