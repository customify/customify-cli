package com.customify.shared.responses_data_format.FeatureFormat;

import java.io.Serializable;

public class FeatureIdformat implements Serializable {
    int featureId;
    public  FeatureIdformat(){}
    public FeatureIdformat(int featureId) {
        this.featureId = featureId;
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }
}
