package com.customify.client.data_format.products;

/*
* @author: Jacques Twizeyimana
* @description: response format for sending a list of products and HTTP status code*/

import java.util.List;

public class GetAllProductsFormat {
    int status;
    List<ProductFormat> products;

    public GetAllProductsFormat() {
    }

    public GetAllProductsFormat(int status, List<ProductFormat> products) {
        this.status = status;
        this.products = products;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GetAllProductFormat{" +
                "status=" + status +
                ", products=" + products +
                '}';
    }

    public List<ProductFormat> getProducts() {
        return products;
    }

    public void setProducts(List<ProductFormat> products) {
        this.products = products;
    }
}
