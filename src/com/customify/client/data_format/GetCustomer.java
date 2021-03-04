package com.customify.client.data_format;

import com.customify.client.Keys;

public class GetCustomer {

    private Keys key;
    private String customerCode;
    public GetCustomer() {}

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public GetCustomer(Keys key, String customerCode) {
        this.key = Keys.GET_CUSTOMER;
        this.customerCode = customerCode;
    }
}
