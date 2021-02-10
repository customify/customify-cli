// By Makuza Mugabo Verite
// On Feb 08 2020

package com.customify.client.data_format;

import com.customify.client.Keys;

public class RedeemCoupon {
    private String coupon_code;
    private Keys key = Keys.REDEEMING_COUPON;

    public RedeemCoupon() { }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}
