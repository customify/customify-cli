package com.customify.server.models.billing;
/**
 * @author Mfuranziza Sekata Aimelyse Moss
 * Created and Wrote Whole Document By Moss
 * */

public class PlanModel<I,T,D>{
    //    S represents data type for title, description
    //    I represents data type for Id
    private I planId;
    private T planTitle;
    private D planDescription;
    public PlanModel(){ }
    public PlanModel(I planId, T planTitle, D planDescription){
        this.planId = planId;
        this.planTitle = planTitle;
        this.planDescription = planDescription;
    }

    public void setPlanId(I planId) {
        this.planId = planId;
    }
    public I getPlanId() {
        return this.planId;
    }
    public void setPlanTitle(T planTitle) {
        this.planTitle = planTitle;
    }
    public T getPlanTitle() {
        return this.planTitle;
    }
    public void setPlanDescription(D planDescription) {
        this.planDescription = planDescription;
    }
    public D getPlanDescription() {
        return this.planDescription;
    }
}
