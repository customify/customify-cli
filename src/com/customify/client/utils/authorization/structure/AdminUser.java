package com.customify.client.utils.authorization.structure;

public class AdminUser extends User{
    private String title;
    private String business_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }
}
