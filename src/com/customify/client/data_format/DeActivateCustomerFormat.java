package com.customify.client.data_format;

import com.customify.client.Keys;

/**
 * @author Murenzi Confiance Tracy
 * @role
 * this is the the format for the deactivating the card on the client side
 * */

public class DeActivateCustomerFormat {
    private Keys key;
    private String code;

    public DeActivateCustomerFormat(String code) {
        this.code = code;
        this.key = Keys.RENABLE_CUSTOMER;
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
