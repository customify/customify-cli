package com.customify.server.response_data_format;

public class CouponFormat {
    private int coupon_id;
    private int customer_id;
    private String coupon_code;
    private String created_at;
    private String expire_date;
    private String getCoupon_status;
    private String coupon_value;

    public CouponFormat(int coupon_id, int customer_id, String coupon_code, String created_at, String expire_date, String getCoupon_status, String coupon_value) {
        this.coupon_id = coupon_id;
        this.customer_id = customer_id;
        this.coupon_code = coupon_code;
        this.created_at = created_at;
        this.expire_date = expire_date;
        this.getCoupon_status = getCoupon_status;
        this.coupon_value = coupon_value;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    public String getGetCoupon_status() {
        return getCoupon_status;
    }

    public void setGetCoupon_status(String getCoupon_status) {
        this.getCoupon_status = getCoupon_status;
    }

    public String getCoupon_value() {
        return coupon_value;
    }

    public void setCoupon_value(String coupon_value) {
        this.coupon_value = coupon_value;
    }
}
