package com.customify.server.models.billing;

/**
 * @author Mfuranziza Sekata Aimelyse Moss
 * Created and Wrote Whole Document By Moss
 * */


public class BillingModel<I,P,F>{
    private I billingId;
    private P planId;
    private F featureId;

    public BillingModel(){}
    public BillingModel(I billingId, P planId, F featureId){
        this.billingId=billingId;
        this.planId = planId;
        this.featureId = featureId;
    }

    public void setBillingId(I billingId) {
        this.billingId = billingId;
    }
    public I getBillingId() {
        return this.billingId;
    }

    public void setPlanId(P planId) {
        this.planId = planId;
    }
    public P getPlanId() {
        return this.planId;
    }
    public void setFeatureId(F featureId) {
        this.featureId = featureId;
    }
    public F getFeatureId() {
        return this.featureId;
    }
}