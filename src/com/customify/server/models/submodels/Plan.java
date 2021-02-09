package com.customify.server.models.submodels;
//Created by Moss

public class Plan <S, I>{
    //    S represents data type for title, description
    //    I represents data type for Id
    private I planId;
    private S planTitle;
    private S planDescription;

    public Plan(I planId, S planTitle, S planDescription){
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
    public void setPlanTitle(S planTitle) {
        this.planTitle = planTitle;
    }
    public S getPlanTitle() {
        return this.planTitle;
    }
    public void setPlanDescription(S planDescription) {
        this.planDescription = planDescription;
    }
    public S getPlanDescription() {
        return this.planDescription;
    }
}
