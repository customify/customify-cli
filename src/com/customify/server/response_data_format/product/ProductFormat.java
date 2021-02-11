package com.customify.server.response_data_format.product;

/*@author: Jacques TWIZEYIMANA
* @description: response data for sending product to client */

import java.util.Objects;

public class ProductFormat {
    private int id;
    private long productCode;
    private int businessId;
    private String name;
    private float price;
    private int quantity;
    private String description;
    private double bondedPoints;
    private int registeredBy;
    private String createdAt;

    public ProductFormat() {
    }

    public ProductFormat(int id, long productCode, int businessId, String name, float price, int quantity, String description, double bondedPoints, int registeredBy, String createdAt) {
        this.id = id;
        this.productCode = productCode;
        this.businessId = businessId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.bondedPoints = bondedPoints;
        this.registeredBy = registeredBy;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductFormat that = (ProductFormat) o;
        return getId() == that.getId() && getProductCode() == that.getProductCode() && getBusinessId() == that.getBusinessId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProductCode(), getBusinessId());
    }

    public long getProductCode() {
        return productCode;
    }

    public void setProductCode(long productCode) {
        this.productCode = productCode;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBondedPoints() {
        return bondedPoints;
    }

    public void setBondedPoints(double bondedPoints) {
        this.bondedPoints = bondedPoints;
    }

    public int getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(int registeredBy) {
        this.registeredBy = registeredBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
