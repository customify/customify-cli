package com.customify.server.services;

import com.customify.server.Db.Db;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.shared.responses_data_format.FeatureFormat.FeatureFormat;
import com.customify.shared.responses_data_format.FeatureFormat.FeatureIdformat;
import com.customify.shared.responses_data_format.FeatureFormat.MessageFormat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 * @description
 * Services to provide operations to be done on billing feature 
 *
 * @author Fiston Nshimiyandinze
 * @version 1
 * */
public class FeatureService {
    DataOutputStream output;
    private final Socket socket;
    private final Request request;
    public  FeatureService(Socket socket, Request request)   {
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
            if (preparedStatement.execute()){
                {
                    MessageFormat responseMessage = new MessageFormat(feature.getFeatureName()+"Feature failed to be added......");
                    Response response = new Response(400,responseMessage);
                    responseData.add(response);

                    objectOutput.writeObject(responseData);

                }
            }
            else{
                MessageFormat responseMessage = new MessageFormat(feature.getFeatureName()+" feature  added......");
                Response response = new Response(200,responseMessage);
                responseData.add(response);

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
                MessageFormat responseMessage = new MessageFormat(feature.getFeatureName()+"Feature  was updated successfully");
                Response response = new Response(200, responseMessage);
                responseData.add(response);

                //Sending the response to client
                objectOutput.writeObject(responseData);
            }
            else {
                MessageFormat responseMessage = new MessageFormat("Failed to update feature "+feature.getFeatureName());
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
        FeatureIdformat feature = (FeatureIdformat) request.getObject();
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

            List features = new ArrayList<FeatureFormat>();
            while (resultSet.next()){
                features.add(new FeatureFormat(
                        resultSet.getInt("featureId"),
                        resultSet.getString("featureName"),
                        resultSet.getString("featureDescription")


                ));
            }
            Response response = new Response(200,features);

            features.add(response);
            output.writeUTF("Products list here...");

            objectOutput.writeObject(features);
            output = new DataOutputStream(this.socket.getOutputStream());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getFeaturebyId() throws IOException {
        FeatureIdformat feature = (FeatureIdformat) request.getObject();

        ObjectOutputStream objectOutput =  new ObjectOutputStream(output);

        try{
            Connection connection = Db.getConnection();
            String sql= "SELECT * FROM BillingFeatures WHERE featureId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,feature.getFeatureId());
            ResultSet resultSet = statement.executeQuery();

            List features = new ArrayList<FeatureFormat>();
            while (resultSet.next()){
                features.add(new FeatureFormat(
                        resultSet.getInt("featureId"),
                        resultSet.getString("featureName"),
                        resultSet.getString("featureDescription")


                ));
            }
            Response response = new Response(200,features);

            features.add(response);
            output.writeUTF("Products matching given conditions...");

            objectOutput.writeObject(features);
            output = new DataOutputStream(this.socket.getOutputStream());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


}

