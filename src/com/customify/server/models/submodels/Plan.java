package com.customify.server.models.submodels;
//Created by Moss
//Whole document wrote by Moss

public class Plan <I,T,D>{
    //    S represents data type for title, description
    //    I represents data type for Id
    private I planId;
    private T planTitle;
    private D planDescription;
    public Plan(){ }
    public Plan(I planId, T planTitle, D planDescription){
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
