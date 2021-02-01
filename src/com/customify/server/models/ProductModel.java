package com.customify.server.models;

import java.util.Date;

public class ProductModel {
    private int productId;
    private int business_id;
    private String name;
    private float price;
    private int quantity;
    private String description;
    private double bondedPoints;
    private int registered_by;
    private Date createdAt;

    public ProductModel(){}

    public ProductModel(int productId, int business_id, String name, float price, int quantity, String description, double bondedPoints, int registered_by, Date createdAt) {
        this.productId = productId;
        this.business_id = business_id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.bondedPoints = bondedPoints;
        this.registered_by = registered_by;
        this.createdAt = createdAt;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
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

    public int getRegistered_by() {
        return registered_by;
    }

    public void setRegistered_by(int registered_by) {
        this.registered_by = registered_by;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
