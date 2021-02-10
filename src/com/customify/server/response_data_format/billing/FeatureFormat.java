package com.customify.server.response_data_format.billing;

import com.customify.server.models.submodels.Feature;

//Whole Document Wrote By Moss

public class FeatureFormat extends Feature<Integer, String, String> {
    public FeatureFormat(){}
    public FeatureFormat(Integer featureId, String featureName, String featureDescription) {
        super(featureId, featureName, featureDescription);
    }
}