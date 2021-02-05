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
import com.customify.shared.responses_data_format.BusinessFormats.BusinessFormat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BussinessService {
    Socket socket;
    OutputStream output;
    ObjectOutputStream objectOutput;
    private int statusCode;
    Response response;
    List responseData = new ArrayList<>();

    public BussinessService(Socket socket) throws IOException {
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
        List<BusinessFormat> alldata = new ArrayList<>();
        try {
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                BusinessFormat bs = new BusinessFormat(
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


}
