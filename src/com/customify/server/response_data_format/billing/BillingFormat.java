package com.customify.server.response_data_format.billing;
import com.customify.server.models.billing.BillingModel;

/**
 * @author Mfuranziza Sekata Aimelyse Moss
 * Created and Wrote Whole Document By Moss
 * */


public class BillingFormat extends BillingModel<Integer,Integer,Integer> {
    private String planName;
    private String featureName;
    public BillingFormat(){}
    public  BillingFormat(Integer billingId, String planName, String featureName){
        this.setBillingId(billingId);
        this.planName=planName;
        this.featureName=featureName;
    }
    public BillingFormat(Integer billingId, Integer planId, Integer featureId){
        super(billingId, planId, featureId);
    }
}
