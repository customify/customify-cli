package com.customify.server.response_data_format.billing;

// Created BY Moss Aimelyse whole document

import com.customify.server.models.billing.PlanModel;

public class PlanFormat extends PlanModel<Integer, String, String> {
    public PlanFormat(){}
    public PlanFormat(Integer planId, String planTitle, String planDescription) {
        super(planId, planTitle, planDescription);
    }
}
