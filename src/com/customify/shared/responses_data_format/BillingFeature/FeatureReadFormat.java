package com.customify.shared.responses_data_format.BillingFeature;

import com.customify.shared.requests_data_formats.BillingFeature.FeatureFormat;

import java.io.Serializable;
import java.util.List;
public class FeatureReadFormat  implements Serializable {
    private List<FeatureFormat> data;
    public FeatureReadFormat(){};
    public FeatureReadFormat(List<FeatureFormat> bs){this.data=bs;}

    public List<FeatureFormat> getData() {
        return data;
    }
}
