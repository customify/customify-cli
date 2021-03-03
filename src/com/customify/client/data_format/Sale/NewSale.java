package com.customify.client.data_format.Sale;

public class NewSale {
     private String productID;
     private String customerID;
     private String amount;
     private String quantity;
     private String employeeID;

    public NewSale(String productID, String customerID, String amount, String quantity, String employeeID) {
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
}
