package com.customify.client.points;

import com.customify.client.Keys;

public class PointsByEmail {
    private Keys key;
    private String email;

    public PointsByEmail() {
    }
    
    public PointsByEmail(Keys key, String email) {
        this.key = key;
        this.email = email;
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
}
