package com.customify.shared.requests_data_formats.BusinessFormats;

import com.customify.shared.Keys;

import java.io.Serializable;

public class DeleteBusinessFormat implements Serializable{
    private int businessId;
    private Keys key = Keys.REMOVE_BUSINESS;
    public DeleteBusinessFormat(){}
    public DeleteBusinessFormat(int businessId){this.businessId=businessId;}

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}
