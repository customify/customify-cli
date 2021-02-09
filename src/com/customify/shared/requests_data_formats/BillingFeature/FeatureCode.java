package com.customify.shared.requests_data_formats.BillingFeature;

import java.io.Serializable;

public class FeatureCode implements Serializable {
    private int featureCode;
    public  FeatureCode(){}
    public  FeatureCode(int featureCode){
        this.featureCode = featureCode;
    }

    public int getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(int featureCode) {
        this.featureCode = featureCode;
    }
}
