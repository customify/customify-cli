/**
 * @description
 * Services to provide operations to be done on Products to be used in Product Controller
 *
 * @author SAUVE Jean-Luc
 * @version 1
 * */

package com.customify.server.services;

import com.customify.shared.*;
import com.customify.shared.requests_data_formats.ProductFormat;
import com.customify.shared.responses_data_format.ProductFormats.SuccessProductFormat;
import com.sun.mail.iap.Response;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    // Declaring variables Required variables
    Socket socket;
    OutputStream output;
    ObjectOutputStream objectOutput;
    private int statusCode;

    Response response;
    List responseData = new ArrayList<>();

    public ProductService(Socket socket) throws IOException {
        this.socket = socket;
        this.output = socket.getOutputStream();
        this.objectOutput = new ObjectOutputStream(output);
    }
    /*
      Method to send feedback to client.
      A serializable class 'Response' Is used to to cast the response
      Like SuccessLoginFormat is used, each response is to have Class to format a response type
      In this case we consider the login process to have been successful & returning 200 statusCode
    */
    public void registerProduct(ProductFormat productFormat) throws IOException {
        //setting the response status code
        this.statusCode = 200;

        //formatting the response into a successProductFormat
        SuccessProductFormat format = new SuccessProductFormat(productFormat.getName());
        response = new Response(statusCode,format);
        responseData.add(response);

        //Sending the response to server after it has been formated
        objectOutput.writeObject(responseData);
    }
    public void deleteProduct(Long productCode) throws IOException {
        //setting the response status code
        this.statusCode = 200;

        //formatting the response into a successProductFormat
        SuccessProductFormat format = new SuccessProductFormat(productCode);
        response = new Response(statusCode,format);
        responseData.add(response);

        //Sending the response to server after it has been formated
        objectOutput.writeObject(responseData);
    }

}
