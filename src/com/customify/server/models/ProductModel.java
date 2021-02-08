package com.customify.server.models;

import java.io.Serializable;
/*
* Created by Jacques Twizeyimana
* Product model is a blueprint of how our products will be represented in database
* all fields that product table will have are blueprinted here
* It will also be used as a data format for products to or from server/client
*/

import java.util.Date;

public class ProductModel implements Serializable {
    private long productCode;
    private int business_id;
    private String name;
    private float price;
    private int quantity;
    private String description;
    private double bondedPoints;
    private int registered_by;
    private String createdAt;

    public ProductModel(){
        this.productCode = this.generateId();
    }

    public ProductModel(int business_id, String name, float price, int quantity, String description, double bondedPoints, int registered_by, String createdAt) {
        this.productCode = this.generateId();
        this.business_id = business_id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.bondedPoints = bondedPoints;
        this.registered_by = registered_by;
        this.createdAt = createdAt;
    }
    public long generateId(){
        int min = 1000000;
        int max = 999999999;

        return Math.round(Math.random() * (max - min + 1) + min);
    }

    public long getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
