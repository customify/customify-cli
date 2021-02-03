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

    public BusinessService(Socket socket) throws IOException {
        this.socket = socket;
    }

    public void create(BusinessFormat businessFormat) throws IOException {
        Request request = new Request(Keys.CREATE_BUSINESS , businessFormat);
        Common common = new Common(request,this.socket);

        if(common.sendToServer()){
            System.out.println("Wait for the response from the server ... ");
        }else{
            System.out.println("There was an error when sending request ....");
        }
    }

}
