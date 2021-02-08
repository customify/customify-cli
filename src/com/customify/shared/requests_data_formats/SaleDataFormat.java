package com.customify.shared.requests_data_formats;

public class SaleDataFormat {
    private int customerId;
    private int productId;
    private int quantity;
    private String date;

    public SaleDataFormat() {
    }

    public SaleDataFormat(int customerId, int productId, int quantity, String date) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
