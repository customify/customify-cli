package com.customify.client.data_format.business;

import com.customify.server.Keys;

import java.io.Serializable;

public class GetBusinessFormat implements Serializable{
    private Keys key;
    private int businessId;
    public GetBusinessFormat(Keys key){this.key=key;}
    public GetBusinessFormat(int businessId,Keys key){
        this.businessId = businessId;
        this.key = key;
    }

    public Keys getKey() {
        return key;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}
