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
//import com.customify.shared.*;
import com.customify.shared.requests_data_formats.BusinessFormats.BusinessFormat;

import com.customify.shared.Keys;
import com.customify.shared.Request;
import com.customify.shared.Response;

import com.customify.shared.requests_data_formats.BusinessFormats.GetbusinessFormat;
import com.customify.shared.responses_data_format.BusinessFormats.BusinessRFormat;
import com.customify.shared.responses_data_format.BusinessFormats.BusinessReadFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class BusinessService {
    private final Socket socket;
    /**
     * @author IRUMVA Anselme
     * @role Constructor it assigns socket to the variable socket
     * */
    public BusinessService(Socket socket) {
        this.socket = socket;
    }

    /**
     * @author IRUMVA HABUMUGISHA Anselme
     * @role
     * this function is to create a new business
     * We send the request to the backend
     * */
    public void create(BusinessFormat businessFormat) throws IOException, ClassNotFoundException {
        // make my request
        Request request = new Request(Keys.CREATE_BUSINESS , businessFormat);

        // Make the Backend connector
        Common common = new Common(request,this.socket);

        // send to the server
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
        System.out.println("Creaa 1");
        // here I am going to get the data from the server
        InputStream inputStream = this.socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        List<Response> response = (List<Response>) objectInputStream.readObject();

        System.out.println("Creaa 2") ;
        // if the status code is 201 then I am going to output that The business is created
        if(response.get(0).getStatusCode() == 201){
            System.out.println("The business is created successfully ....");
        }
    }

    /**
     * @author Kellia Umuhire
     * @role
     * this function is for getting all business
     * */
    public void getbusinesses (GetbusinessFormat format) throws IOException, ClassNotFoundException{
        Request request = new Request(Keys.GET_BUSINESS,format);
        Common common = new Common(request,this.socket);
        if(common.sendToServer()){
            this.handleGetResponse();
        }
    }

    /**
     * @author Kellia Umuhire
     * @role
     * this function is for handling the response after fetching all the businesses
     * */
    public void handleGetResponse() throws IOException,ClassNotFoundException{
        InputStream inputStream = this.socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        List<Response> response = (List<Response>) objectInputStream.readObject();

        if(response.get(0).getStatusCode()==200){
            BusinessReadFormat data = (BusinessReadFormat) response.get(0).getData();
            System.out.println("------------------List of Businesses------------------");
            System.out.format("%5s%20s%20s%25s%20s\n","ID","Name","Location","Address","Phone number");
            System.out.println();
            for(BusinessRFormat bs:data.getData()){
                System.out.format("%5d%20s%20s%25s%20s\n",bs.getId(),bs.getName(),bs.getLocation(),bs.getAddress(),bs.getPhone_number());
            }

            System.out.println("----------------------------------------------");
        }
    }

    //get business by id
    public void getById(Integer format) throws IOException,ClassNotFoundException{
        Request request = new Request(Keys.GET_BUSS_BYID,format);
        Common common = new Common(request,this.socket);
        if(common.sendToServer()){
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            List<Response> response = (List<Response>) objectInputStream.readObject();
            if(response.get(0).getStatusCode()==200){
                BusinessRFormat data = (BusinessRFormat)  response.get(0).getData();
                System.out.println("-------------------Business "+data.getId()+"------------------");
                System.out.format("%5d%20s%20s%25s%20s\n",data.getId(),data.getName(),data.getLocation(),data.getAddress(),data.getPhone_number());
            }
        }
    }

    //remove business
    public  void deleteBusiness(Integer format) throws IOException,ClassNotFoundException{
        Request request = new Request(Keys.REMOVE_BUSS,format);
        Common common = new Common(request,this.socket);
        if(common.sendToServer()){
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            List<Response> response = (List<Response>) objectInputStream.readObject();
            if(response.get(0).getStatusCode()==200){
                String message = (String) response.get(0).getData();
                System.out.println("--------"+message+"--------");
            }
        }
    }



}
