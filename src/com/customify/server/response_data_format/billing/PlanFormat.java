package com.customify.server.response_data_format.billing;

/**
 * @author Mfuranziza Sekata Aimelyse Moss
 * Created and Wrote Whole Document By Moss
 * */

import com.customify.server.models.billing.PlanModel;

public class PlanFormat extends PlanModel<Integer, String, String> {
    public PlanFormat(){}
    public PlanFormat(Integer planId, String planTitle, String planDescription) {
        super(planId, planTitle, planDescription);
    }
}
