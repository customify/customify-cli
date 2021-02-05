package com.customify.shared.responses_data_format.FeatureFormat;


 import java.io.Serializable;

public class FeatureFormat implements  Serializable {
    private int featureId;
    private  String featureName;
    private  String featureDescription;
    public FeatureFormat(){}
    public FeatureFormat(int featureId ,String featureName,String featureDescription){
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
}
