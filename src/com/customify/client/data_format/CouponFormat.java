// By Makuza Mugabo Verite on Feb 08 2020
// Formatting of data for coupons

package com.customify.client.data_format;

import java.text.DateFormat;

public class CouponFormat {
    private String customer_id;
    private String source;
    private String value;
    private String expiry;

    public CouponFormat(){}

    public CouponFormat(String customer_id, String source, String value, String expiry) {
        this.customer_id = customer_id;
        this.source = source;
        this.value = value;
        this.expiry = expiry;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
}
