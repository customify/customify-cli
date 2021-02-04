/**
 * @description
 * The register business front-end services class this is here to
 * register all the businesses in the project must use this service
 *
 * @author IRUMVA HABUMUGISHA Anselme
 * @version 1
 * @since Wednesday, 3 February 2021 - 08:17 - Time in Nyabihu
 * */

package com.customify.client.services;

import com.customify.client.Common;
import com.customify.shared.Keys;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.shared.requests_data_formats.BusinessFormat;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class BusinessService {
    Socket socket;

    public BusinessService(Socket socket) {
        this.socket = socket;
    }

    /**
     * @role
     * @author IRUMVA HABUMUGISHA Anselme
     * this function is to create a new business
     * We send the request to the backend
     * */
    public void create(BusinessFormat businessFormat) throws IOException, ClassNotFoundException {
        Request request = new Request(Keys.CREATE_BUSINESS , businessFormat);
        Common common = new Common(request,this.socket);

        if(common.sendToServer()){
            this.handleCreateBusinessResponse();
        }else{
            System.out.println("There was an error when sending request ....");
        }
    }

    /**
     * @author IRUMVA HABUMUGISHA Anselme
     * @role
     * this function is to handle response on the successfully creation of the business
     * */
    public void handleCreateBusinessResponse() throws IOException, ClassNotFoundException {
        InputStream inputStream = this.socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        List<Response> response = (List<Response>) objectInputStream.readObject();
        if(response.get(0).getStatusCode() == 201){
            System.out.println("The business is created successfully ....");
        }
    }
}
