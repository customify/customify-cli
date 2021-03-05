package com.customify.client.data_format.billing;

import com.customify.client.Keys;

public class GetPlansFormat {
    private Keys key;

    public GetPlansFormat(Keys key) {
        this.key = key;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}
