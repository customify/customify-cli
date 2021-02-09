package com.customify.server.response_data_format.billing;

import com.customify.client.Keys;
import com.customify.server.models.submodels.Plan;

// Created BY Moss Aimelyse whole document

public class PlanFormat extends Plan<Integer, String, String>{
    public PlanFormat(Keys key, Integer planId, String planTitle, String planDescription) {
        super(planId, planTitle, planDescription);
    }
}
