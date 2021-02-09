package com.customify.server.services;



import com.customify.server.Db.Db;
import com.customify.shared.requests_data_formats.BillingFeature.*;
import com.customify.shared.responses_data_format.BillingFeature.FeatureReadFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingfeatureService {
    Socket socket;
    OutputStream output;
    ObjectOutputStream objectoutput;
    private  int statusCode;

public BillingfeatureService(Socket socket) throws IOException {

this.socket =socket;
this.output = socket.getOutputStream();
this.objectoutput = new ObjectOutputStream(output);
}

public void registerFeature(String data) throws SQLException, JsonProcessingException {
ObjectMapper objectMapper = new ObjectMapper();
JsonNode jsonNode = objectMapper.readTree(data);

    Connection connection = Db.getConnection();
    String sqlQuery = "INSERT INTO BillingFeatures  VALUES(?,?)";
    PreparedStatement statement = connection.prepareStatement(sqlQuery);
    statement.setString(1,jsonNode.get("featureName").asText());
    statement.setString(2,jsonNode.get("featureDescription").asText());

    if(statement.execute()){
        System.out.println("Your query not working .... ");
    }else{
        System.out.println("Query Ok !!! ");
    }

}
public void  update(String data) throws SQLException, JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(data);

    Connection connection = Db.getConnection();
    String sqlQuery = "UPDATE  BillingFeatures SET featureName=?,featureDescription =? WHERE featureId=?";

    PreparedStatement statement = connection.prepareStatement(sqlQuery);
    statement.setString(1,jsonNode.get("featureName").asText());
    statement.setString(2,jsonNode.get("featureDescription").asText());
    statement.setInt(3,jsonNode.get("featureCode").asInt());
    if(statement.execute()){
        System.out.println("Your query not working .... ");
    }else{
        System.out.println("Query Ok !!! ");
    }
}
public  void deleteFeature(String data)throws  IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(data);

    Statement statement = Db.getStatement();
    this.statusCode = 200;
    try {
        int exec = statement.executeUpdate("delete from BillingFeatures where featureId="+jsonNode.get("featureCode"));
        if(exec==1){
            System.out.println("Successfully deleted");
        }
    }
    catch (SQLException e){
        this.statusCode= 400;
        System.out.println("Error occured: "+e.getMessage());
    }

}


    public void getFeatureByCode(String data) throws IOException{
        //setting the response status code
        this.statusCode = 200;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);

        //formatting the response into a dataformat
        Statement statement = Db.getStatement();
        try{
            ResultSet res = statement.executeQuery("select * from BillingFeatures  where featureId="+jsonNode.get("featureCode"));
            if(res.next()){
                FeatureFormat bs = new FeatureFormat(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3)
                );
                String json = objectMapper.writeValueAsString(bs);

                //send
                this.objectoutput.writeObject(json);
                this.objectoutput.close();
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getAllFeature() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //setting the response status code
        this.statusCode = 200;

        //formatting the response into a data format
        Statement statement = Db.getStatement();
        String query = "Select * from businesses";
        List<FeatureFormat> data = new ArrayList<>();
        try {
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                FeatureFormat bs = new FeatureFormat(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3)
                );
                data.add(bs);
            }
            FeatureReadFormat format = new FeatureReadFormat(data);
            String json = objectMapper.writeValueAsString(format);

            //Sending the response to server after it has been formated
            this.objectoutput.writeObject(json);
        }
        catch (Exception e){
            e.printStackTrace();
        }
}
}
