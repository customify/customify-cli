package com.customify.client.data_format;

import com.customify.client.Keys;

public class SalesFormat {
    private String productID;
    private String customerID;
    private String amount;
    private String quantity;
    private String employeeID;
    private Keys key = Keys.ADD_SALE;


    public SalesFormat(String productID, String customerID, String amount, String quantity, String employeeID) {
        this.productID = productID;
        this.customerID = customerID;
        this.amount = amount;
        this.quantity = quantity;
        this.employeeID = employeeID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}
