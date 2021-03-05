package com.customify.client.data_format.billing;

import com.customify.client.Keys;

public class GetFeaturesFormat {
    private Keys key;
    public GetFeaturesFormat(){}
    public GetFeaturesFormat(Keys key){
        this.key = key;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}
