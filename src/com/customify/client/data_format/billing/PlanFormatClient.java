package com.customify.client.data_format.billing;

import com.customify.client.Keys;
import com.customify.server.models.billing.PlanModel;
import java.io.Serializable;

// Created BY Moss Aimelyse whole document

public class PlanFormatClient extends PlanModel<Integer, String, String> {
    private Keys key;

    public PlanFormatClient(Keys key, Integer planId, String planTitle, String planDescription) {
        super(planId, planTitle, planDescription);
        this.key =key;
    }
    public void setKey(Keys key) {
        this.key = key;
    }
    public Keys getKey() {
        return this.key;
    }
}
