package com.customify.shared.requests_data_formats;
import java.io.Serializable;
public class RemoveBusinessFormat implements  Serializable {
    private int businessId;
    public RemoveBusinessFormat(int businessId){this.businessId=businessId;}

    public int getBusinessId() {
        return businessId;
    }
}
