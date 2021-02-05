/**
 * @description
 * server-side business service for handling operations related to the business
 *
 * @author Kellia Umuhire
 * @since Wednesday, 3 February 2021
 * */

package com.customify.server.services;
import com.customify.shared.Response;
import com.customify.server.Db.Db;
import com.customify.shared.responses_data_format.BusinessFormats.BusinessReadFormat;
import com.customify.shared.responses_data_format.BusinessFormats.BusinessRFormat;

import java.io.IOException;
import java.sql.SQLException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BusinessService {
    Socket socket;
    OutputStream output;
    ObjectOutputStream objectOutput;
    private int statusCode;
    Response response;
    List responseData = new ArrayList<>();

    public BusinessService(Socket socket) throws IOException {
        this.socket = socket;
        this.output = socket.getOutputStream();
        this.objectOutput = new ObjectOutputStream(output);
    }
    public void getAll() throws IOException {
        //setting the response status code
        this.statusCode = 200;

        //formatting the response into a dataformat
        Statement statement = Db.getStatement();
        String query = "Select * from businesses";
        List<BusinessRFormat> alldata = new ArrayList<>();
        try {
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                BusinessRFormat bs = new BusinessRFormat(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7)
                );
                alldata.add(bs);
            }
            BusinessReadFormat format = new BusinessReadFormat(alldata);
            response = new Response(statusCode,format);
            responseData.add(response);

            //Sending the response to server after it has been formated
            objectOutput.writeObject(responseData);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void getBusinessById(Integer businessId) throws IOException{
        //setting the response status code
        this.statusCode = 200;

        //formatting the response into a dataformat
        Statement statement = Db.getStatement();
        try{
            ResultSet res = statement.executeQuery("select * from businesses where id="+businessId);
            if(res.next()){
                BusinessRFormat bs = new BusinessRFormat(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7)
                );
                response = new Response(statusCode,bs);
                responseData.add(response);

                //send
                objectOutput.writeObject(responseData);
                objectOutput.close();
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeBusiness(Integer format) throws IOException{
        Statement statement = Db.getStatement();
        String message = "Successfully removed business";
        this.statusCode = 200;
        try {
            int ret = statement.executeUpdate("delete from businesses where id="+format);
            if(ret==1){
                response = new Response(statusCode,message);
                responseData.add(response);

                //Sending the response to server after it has been formated
                objectOutput.writeObject(responseData);
            }
        }
        catch (SQLException e){
            this.statusCode= 400;
            message= e.getMessage();
            response=new Response(statusCode,message);
            responseData.add(response);
            //Sending the response to server after it has been formated
            objectOutput.writeObject(responseData);
            objectOutput.close();
        }

    }


}
