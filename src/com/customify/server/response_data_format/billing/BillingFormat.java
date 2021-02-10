package com.customify.server.response_data_format.billing;
import com.customify.server.models.BillingModel;

//Whole Document Wrote By Moss


public class BillingFormat extends BillingModel<Integer,String,String> {
    public BillingFormat(){}
    public BillingFormat(Integer billingId, String planId, String featureId){
        super(billingId, planId, featureId);
    }
}
