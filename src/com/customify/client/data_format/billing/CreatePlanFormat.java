package com.customify.client.data_format.billing;

import com.customify.client.Keys;

public class CreatePlanFormat {
    private Keys key;
    private String planTitle;
    private String planDescription;

    public CreatePlanFormat(Keys key, String planTitle, String planDescription) {
        this.key = key;
        this.planTitle = planTitle;
        this.planDescription = planDescription;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    public String getPlanDescription() {
        return planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }
}
