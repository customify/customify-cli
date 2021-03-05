package com.customify.server.response_data_format.products;

import java.util.List;

public class GetAllProductsFormat {
    private int status;
    private List<ProductFormat> products;

    public GetAllProductsFormat() {
    }

    public GetAllProductsFormat(int status) {
        this.status = status;
    }

    public GetAllProductsFormat(List<ProductFormat> products) {
        this.products = products;
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

    public List<ProductFormat> getProducts() {
        return products;
    }

    public void setProducts(List<ProductFormat> products) {
        this.products = products;
    }
}
