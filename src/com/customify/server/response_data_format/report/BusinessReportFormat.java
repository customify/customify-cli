package com.customify.server.response_data_format.report;

public class BusinessReportFormat {
    private int totalBusiness;
    private String created_at;
    public  BusinessReportFormat(){ }

    public int getTotalBusiness() {
        return totalBusiness;
    }

    public void setTotalBusiness(int totalBusiness) {
        this.totalBusiness = totalBusiness;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public BusinessReportFormat(int totalBusiness, String created_at) {
        this.totalBusiness = totalBusiness;
        this.created_at = created_at;
    }
}
