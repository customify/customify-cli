package com.customify.client.data_format.billing;

import com.customify.client.Keys;

public class DeleteFeatureFormat {
    private Keys key;
    private int featureId;

    public DeleteFeatureFormat(Keys key,int featureId) {
        this.key = key;
        this.featureId = featureId;
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}
