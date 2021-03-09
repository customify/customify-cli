package com.customify.server.response_data_format.report;

public class ProductReportFormat {
    private int totalProducts;
    private String created_at;

    public ProductReportFormat(int totalProducts, String created_at) {
        this.totalProducts = totalProducts;
        this.created_at = created_at;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
