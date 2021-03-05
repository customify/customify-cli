package com.customify.server.response_data_format.report;

public class CouponReportFormat {
    private int totalCustomers;
    private String created_at;
    CouponReportFormat(){}

    public CouponReportFormat(int totalCustomers, String created_at) {
        this.totalCustomers = totalCustomers;
        this.created_at = created_at;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
