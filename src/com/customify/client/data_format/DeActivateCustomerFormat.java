package com.customify.client.data_format;

import com.customify.client.Keys;

public class DeActivateCustomerFormat {
    private Keys key;
    private String code;

    public DeActivateCustomerFormat(Keys key, String code) {
        this.key = key;
        this.code = code;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
