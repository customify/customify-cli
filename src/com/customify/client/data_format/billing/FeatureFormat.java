package com.customify.client.data_format.billing;

import com.customify.client.Keys;

/**
 * @author Patrick Niyogitare
 * @role
 * Modeling the new feature to be registered
 * */
public class FeatureFormat {
    private Keys key;
    private String featureName;
    private String featureDescription;

    public FeatureFormat(){}
    public FeatureFormat(Keys key ,String featureName, String featureDescription) {
        this.key = key;
        this.featureName = featureName;
        this.featureDescription = featureDescription;
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
