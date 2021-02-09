package com.customify.client.data_format;

import com.customify.client.Keys;

public class CheckCoupon {
    private String couponCode;
    private Keys key = Keys.CHECK_COUPON;


    public CheckCoupon() {
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}
