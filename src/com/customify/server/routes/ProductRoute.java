/*
  @description
 * Routing for Product Requests
 * @author SAUVE Jean-Luc
 * @version 1
 * */

/*
   Description: Routing for Product Requests
   Contributors: SAUVE
 */
package com.customify.server.routes;

import com.customify.server.controllers.ProductController;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ProductRoute {

    Socket socket;
    ProductController productController;

    public ProductRoute(Socket socket) {
        this.socket = socket;
    }


    public void  registerProduct() throws IOException, SQLException {
        productController.registerProduct();
    }
    public void  getProductById() throws IOException, SQLException {
        productController.getProductById();
    }
    /*
     * @description
     * delete route for products
     * @author: Tamara Iradukunda
     */

    public void  deleteProduct() throws IOException, SQLException {
        productController.deleteProduct();
    }
}
