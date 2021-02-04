package com.customify.server.controllers;
import com.customify.server.Db.Db;
import com.customify.server.models.FeatureModel;
import com.customify.shared.Request;
import com.customify.shared.Response;
import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.*;
public class FeatureController {
    DataOutputStream output;
    private Socket socket;
    private Request request;
public  FeatureController(Socket socket, Request request) throws IOException {
    this.socket = socket;
    this.request = request;
}
public void registerProduct() throws Exception {
    FeatureModel feature = (FeatureModel) request.getObject();
    System.out.println("Request to create feature  received at backend");
    try {
        Connection connection = Db.getConnection();
        String sql = "INSERT INTO Features (featureName,featureDescription) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,feature.getFeatureName());
        preparedStatement.setString(2,feature.getFeatureDescription());

        OutputStream output = this.socket.getOutputStream();
        ObjectOutputStream objectOutput =  new ObjectOutputStream(output);

        if(preparedStatement.execute()){
            List responseData = new ArrayList<>();
            Response response = new Response(200,feature);
            responseData.add(response);

            //Sending the response to client
            objectOutput.writeObject(responseData);
        }
        else{
            List responseData = new ArrayList<>();
            Response response = new Response(400,feature);
            responseData.add(response);

            //Sending the response to client
            objectOutput.writeObject(responseData);

        }

    }
    catch (Exception e) {
        e.printStackTrace();
    }
}
public  void  updateFeature() throws  Exception {
    
}
}
