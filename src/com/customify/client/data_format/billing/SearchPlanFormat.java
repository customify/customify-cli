package com.customify.client.data_format.billing;
/**
 * @author Patrick Niyogitare
 * @role The class models the request for deleting a plan
 * */
import com.customify.client.Keys;

public class SearchPlanFormat {
    private Keys key;
    private int planId;

    public SearchPlanFormat(){}

    public SearchPlanFormat(Keys key, int planId) {
        this.key = key;
        this.planId = planId;
    }

    public Keys getKey() {
        return key;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}
