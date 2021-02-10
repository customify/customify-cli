package com.customify.client.data_format.BillingFeature;

import com.customify.server.Keys;

public class GetFeatureFormat {
    private Keys key;
    private int featureCode;
    public  GetFeatureFormat(){}
    public GetFeatureFormat(Keys key,int featureCode){
   this.key = key;
   this.featureCode = featureCode;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }

    public int getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(int featureCode) {
        this.featureCode = featureCode;
    }
}
