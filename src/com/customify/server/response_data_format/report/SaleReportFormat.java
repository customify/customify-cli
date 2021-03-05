package com.customify.server.response_data_format.report;

import java.util.Date;

public class SaleReportFormat {
    private int totalSales;
    private String created_at;
    public  SaleReportFormat(){}
    public  SaleReportFormat(int totalSales, String created_at){
        this.totalSales = totalSales;
        this.created_at = created_at;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public int getTotalSales() {
        return this.totalSales;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return this.created_at;
    }
}
