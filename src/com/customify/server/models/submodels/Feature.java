package com.customify.server.models.submodels;
// Create By Moss
//Whole Document Wrote by Moss

public class Feature <I,N,D>{
    //    S represents data type for name, description
    //    I represents data type for Id
    private I featureId;
    private N featureName;
    private D featureDescription;
    public Feature(){}
    public Feature( I featureId, N featureName, D featureDescription){
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
    public void setFeatureName(N featureName) {
        this.featureName = featureName;
    }
    public N getFeatureName() {
        return this.featureName;
    }
    public void setFeatureDescription(D featureDescription) {
        this.featureDescription = featureDescription;
    }
    public D getFeatureDescription() {
        return featureDescription;
    }
}
