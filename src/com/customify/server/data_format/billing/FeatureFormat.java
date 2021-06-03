package com.customify.server.data_format.billing;

import java.io.Serializable;

public class FeatureFormat implements Serializable {
    private  int featureCode;
    private  String featureName;
    private  String featureDescription;
    public  FeatureFormat() { }
    public  FeatureFormat(int featureCode,String featureName, String featureDescription){
        this.featureCode = featureCode;
        this.featureName = featureName;
        this.featureDescription = featureDescription;
    }

    public int getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(int featureCode) {
        this.featureCode = featureCode;
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
}