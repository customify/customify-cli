package com.customify.server.response_data_format.report;

/**
 * @author Mfuranziza Sekata Aimelyse Moss
 * Created and Wrote Whole Document By Moss
 * */

public class QuantityReportFormat {
    private int totalQuantity;
    private String created_at;
    public  QuantityReportFormat(){}
    public QuantityReportFormat(int totalQuantity,String created_at){
        this.totalQuantity = totalQuantity;
        this.created_at = created_at;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
    public int getTotalQuantity() {
        return this.totalQuantity;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    public String getCreated_at() {
        return this.created_at;
    }
}
