package com.customify.client.data_format;

import com.customify.client.Keys;

public class GetCouponsFormat {
    private Keys key = Keys.GET_COUPONS;

    public GetCouponsFormat() {}

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}
