package com.customify.server.models.submodels;
// Create By Moss

public class Feature <I,S>{
    //    S represents data type for name, description
    //    I represents data type for Id
    private I featureId;
    private S featureName;
    private S featureDescription;

    public Feature( I featureId, S featureName, S featureDescription){
        this.featureId = featureId;
        this.featureName = featureName;
        this.featureDescription = featureDescription;
    }

    public void setFeatureId(I featureId) {
        this.featureId = featureId;
    }
    public I getFeatureId() {
        return this.featureId;
    }
    public void setFeatureName(S featureName) {
        this.featureName = featureName;
    }
    public S getFeatureName() {
        return this.featureName;
    }
    public void setFeatureDescription(S featureDescription) {
        this.featureDescription = featureDescription;
    }
    public S getFeatureDescription() {
        return featureDescription;
    }
}
