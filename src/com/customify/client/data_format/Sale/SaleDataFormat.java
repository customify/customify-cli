package com.customify.client.data_format.Sale;

import com.customify.client.Keys;

public class SaleDataFormat {
    private String customerID;
    private String quantity;
    private String totalPrice;
    private String employeeID;
    private String productID;
    private Keys key = Keys.ADD_SALE;


    public SaleDataFormat(String customerID, String quantity, String totalPrice, String employeeID, String productID) {
        this.customerID = customerID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.employeeID = employeeID;
        this.productID = productID;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}
