package com.customify.client.data_format.billing;

import com.customify.client.Keys;

public class UpdatedFeatureFormat {
    private Keys key;
    private int featureId;
    private String featureName;
    private String featureDescription;

    public UpdatedFeatureFormat(){}
    public UpdatedFeatureFormat(Keys key, int featureId, String featureName, String featureDescription) {
        this.key = key;
        this.featureId = featureId;
        this.featureName = featureName;
        this.featureDescription = featureDescription;
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureDescription() {
        return featureDescription;
    }

    public void setFeatureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}
