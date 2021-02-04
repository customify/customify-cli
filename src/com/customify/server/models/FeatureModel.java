package com.customify.server.models;

public class FeatureModel {
    private int featureId;
    private  String featureName;
    private  String featureDescription;
    public FeatureModel(){}
    public FeatureModel(int featureId ,String featureName,String featureDescription){
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
