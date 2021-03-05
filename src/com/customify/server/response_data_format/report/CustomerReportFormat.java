package com.customify.server.response_data_format.report;

/**
 * @author Mfuranziza Sekata Aimelyse Moss
 * Created and Wrote Whole Document By Moss
 * */

public class CustomerReportFormat {
    private int totalCustomers;
    private String created_at;
    public  CustomerReportFormat(){}
    public  CustomerReportFormat(int totalCustomers, String created_at){
        this.totalCustomers = totalCustomers;
        this.created_at = created_at;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }
    public int getTotalCustomer() {
        return this.totalCustomers;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    public String getCreated_at() {
        return this.created_at;
    }
}
