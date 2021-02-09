package com.customify.client.data_format;

import com.customify.client.Keys;

public class SalesFormat {
    private String customer_id;
    private String product_id;
    private String quantity;
    private String totalPrice;
    private Keys key = Keys.ADD_SALE;

    public SalesFormat(){}
    public SalesFormat(String customer_id, String product_id, String quantity, String totalPrice) {
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.totalPrice = totalPrice;

    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
