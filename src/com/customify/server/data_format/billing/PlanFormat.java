package com.customify.server.data_format.billing;
/**
 * @author Patrick Niyogitare
 * @role defined a model of a plan
 * */
public class PlanFormat {
    private int planId;
    private String planName;
    private String planDescription;

    public PlanFormat(){}
    public PlanFormat(int planId, String planName, String planDescription) {
        this.planId = planId;
        this.planName = planName;
        this.planDescription = planDescription;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanDescription() {
        return planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }
}
