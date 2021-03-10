package com.customify.client.data_format.billing;

import com.customify.client.Keys;

public class UpdatePlanFormat {
    private Keys key;
    private int planId;
    private String planName;
    private String planDescription;

    public UpdatePlanFormat(Keys key, int planId, String planName, String planDescription) {
        this.key = key;
        this.planId = planId;
        this.planName = planName;
        this.planDescription = planDescription;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
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
