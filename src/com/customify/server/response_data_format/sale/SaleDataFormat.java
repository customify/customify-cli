/**
 * @author GISA KAZE Fredson
 */

package com.customify.server.response_data_format.sale;

public class SaleDataFormat {
    private String customerID;
    private int quantity;
    private String totalPrice;
    private String employeeID;
    private String productID;
    public SaleDataFormat(String customerID, int quantity, String totalPrice, String employeeID, String productID) {
        this.customerID = customerID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.employeeID = employeeID;
        this.productID = productID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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
