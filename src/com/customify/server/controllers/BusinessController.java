/**
 * @description
 * The register business front-end services class this is here to
 * register all the businesses in the project must use this service
 *
 * @author IRUMVA HABUMUGISHA Anselme,
 * @version 1
 * @since Wednesday, 3 February 2021 - 08:17 - Time in Nyabihu
 * */

package com.customify.server.controllers;

import com.customify.server.Db.Db;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.shared.requests_data_formats.BusinessFormats.BusinessFormat;
import com.customify.shared.responses_data_format.BusinessFormats.BusinessCreate;
import com.customify.server.services.BusinessService;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessController {

    private final Request request;
    List<Response> responseData = new ArrayList<>();
    ObjectOutputStream objectOutput;
    Socket socket;

    BusinessService businessService;

    public BusinessController(Socket socket, Request request)throws IOException{
        this.request = request;
        this.socket = socket;
        this.businessService = new BusinessService(this.socket);
    }

    /**
     * @author IRUMVA HABUMUGISHA Anselme
     * @role
     * this function is to handle the backend registering into the database
     * and sending back the response
     * */

    public void create() throws SQLException {
        BusinessFormat businessFormat = (BusinessFormat) request.getObject();

        Connection connection = Db.getConnection();
        String query = "INSERT INTO businesses VALUES(NULL, ?, ?, ?, ?, ?, ?, NOW())";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, businessFormat.getLocation());
        statement.setString(2, businessFormat.getAddress());
        statement.setString(3, businessFormat.getPhone_number());
        statement.setString(4, businessFormat.getName());
        statement.setInt(5, businessFormat.getRepresentative());
        statement.setInt(6, businessFormat.getPlan());

        // Let me try to execute the query and write the result ....
        try {
            if(statement.execute()){
                System.out.println("Your query not working .... ");
            }else{
                BusinessCreate businessCreate = new BusinessCreate("Successfully created an business");
                Response response = new Response(201, businessCreate);

                OutputStream output = socket.getOutputStream();
                this.objectOutput = new ObjectOutputStream(output);

                responseData.add(response);
                objectOutput.writeObject(responseData);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @author Kellia Umuhire
     * @role
     * this function calls the service to fetch all businesses
     * */

    public void getall()throws IOException{
        businessService.getAll();
    }

    public void getById()throws IOException{
        Integer format = (Integer) request.getObject();
        businessService.getBusinessById(format);
    }

    public void deleteBusiness()throws IOException{
        Integer format = (Integer) request.getObject();
        businessService.removeBusiness(format);
    }
}
