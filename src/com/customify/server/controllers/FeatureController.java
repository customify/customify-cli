package com.customify.server.controllers;
import com.customify.server.Db.Db;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.shared.responses_data_format.FeatureFormat.*;
import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.*;

public class FeatureController {
    DataOutputStream output;
    private final Socket socket;
    private final Request request;
public  FeatureController(Socket socket, Request request)   {
    this.socket = socket;
    this.request = request;
}
public void registerFeature() throws Exception{
    FeatureFormat feature = (FeatureFormat) request.getObject();
    List<Response> responseData = new ArrayList<>();

    System.out.println("Request to create feature  received at backend");
    try {
        Connection connection = Db.getConnection();
        String sql = "INSERT INTO BillingFeatures (featureName,featureDescription) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,feature.getFeatureName());
        preparedStatement.setString(2,feature.getFeatureDescription());

        OutputStream output = this.socket.getOutputStream();
        ObjectOutputStream objectOutput =  new ObjectOutputStream(output);

        if(preparedStatement.execute()){
            MessageFormat responseMessage = new MessageFormat("New feature  added......");
            Response response = new Response(200,responseMessage);
            responseData.add(response);

            //Sending the response to client
            objectOutput.writeObject(responseData);
        }
        else{
            MessageFormat responseMessage = new MessageFormat("Feature failed to be added......");
            Response response = new Response(400,responseMessage);
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
    FeatureFormat feature = (FeatureFormat) request.getObject();
    List<Response> responseData = new ArrayList<>();
    System.out.println("Request to update feature  received at backend");

    try{
        Connection connection = Db.getConnection();
        String sql = "UPDATE  BillingFeatures SET  featureName=?, featureDescription=? WHERE featureId=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,feature.getFeatureName());
        preparedStatement.setString(2,feature.getFeatureDescription());
        preparedStatement.setInt(2,feature.getFeatureId());


        output = new DataOutputStream(this.socket.getOutputStream());
        ObjectOutputStream objectOutput =  new ObjectOutputStream(output);


        if(preparedStatement.execute()) {
            MessageFormat responseMessage = new MessageFormat("Feature  was updated successfully");
            Response response = new Response(200, responseMessage);
            responseData.add(response);

            //Sending the response to client
            objectOutput.writeObject(responseData);
        }
        else {
            MessageFormat responseMessage = new MessageFormat("Failed to update feature ");
            Response response = new Response(200, responseMessage);
            responseData.add(response);

            //Sending the response to client
            objectOutput.writeObject(responseData);
        }
    }
    catch (Exception e) {
        e.printStackTrace();
    }

}

    public  void  deleteFeature() throws  Exception {
        FeatureFormat feature = (FeatureFormat) request.getObject();
        List<Response> responseData = new ArrayList<>();
        System.out.println("Request to delete feature  received at backend");

        try{
            Connection connection = Db.getConnection();
            String sql = "DELETE FROM BillingFeatures WHERE  featureId=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,feature.getFeatureId());


            output = new DataOutputStream(this.socket.getOutputStream());
            ObjectOutputStream objectOutput =  new ObjectOutputStream(output);


            if(preparedStatement.execute()) {
                MessageFormat responseMessage = new MessageFormat("Feature  was deleted successfully");
                Response response = new Response(200, responseMessage);
                responseData.add(response);

                //Sending the response to client
                objectOutput.writeObject(responseData);
            }
            else {
                MessageFormat responseMessage = new MessageFormat("Failed to delete feature ");
                Response response = new Response(200, responseMessage);
                responseData.add(response);

                //Sending the response to client
                objectOutput.writeObject(responseData);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }




    public void getAllFeature() throws IOException {

    ObjectOutputStream objectOutput =  new ObjectOutputStream(output);

    try{
        Connection connection = Db.getConnection();
        String sql= "SELECT * FROM BillingFeatures";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

            List<Response> responseData = new ArrayList<>();
            Response response = new Response(200,resultSet);
            responseData.add(response);
            //Sending the response to client
        objectOutput.writeObject(responseData);
        output = new DataOutputStream(this.socket.getOutputStream());
        output.writeUTF("Products list here...");
    }
    catch (Exception e) {
        e.printStackTrace();
    }

}

public void getFeature() throws IOException {
    FeatureFormat feature = (FeatureFormat) request.getObject();
    ObjectOutputStream objectOutput =  new ObjectOutputStream(output);

    try{
    Connection connection = Db.getConnection();
    String sql = "SELECT * FROM BillingFeatures WHERE featureId=?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1,feature.getFeatureId());
    ResultSet resultSet = preparedStatement.executeQuery();

    List<Response> responseData = new ArrayList<>();
    Response response = new Response(200,resultSet);
    responseData.add(response);

        //Sending the response to client
     objectOutput.writeObject(responseData);

    }
    catch (Exception e) {
        e.printStackTrace();
    }
    output = new DataOutputStream(this.socket.getOutputStream());
    output.writeUTF("Single product with ID: ");
}
}

