package com.customify.server.services;

import com.customify.client.Keys;
import com.customify.client.points.PointsByEmail;
import com.customify.server.Db.Db;
import com.customify.server.models.PointsModelResponse;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.shared.requests_data_formats.PointsByCustomerEmailFormat;
import com.customify.shared.responses_data_format.WinnersDataFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GISA KAZE Fredson
 */
public class PointsController {
    private Socket socket;
    private Request request;
    DataOutputStream output;
    List<String> responseData = new ArrayList<>();

    public PointsController() {
    }

    public PointsController(Socket socket, Request request) {
        this.socket = socket;
        this.request = request;
    }

    /**
     * @author GISA KAZE Fredson
     */
    public void getPointsByCustomerEmail(String jsonData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonData);

        String email = jsonNode.get("email").asText();
        System.out.println("Email: "+email);

        PointsByCustomerEmailFormat data = (PointsByCustomerEmailFormat) request.getObject();
        System.out.println("Customer email: " + data.getEmail());
    }

    public void getWinners() throws SQLException, IOException {
        System.out.println("Request to get winners received at server");
        String sql = "SELECT Points_winning.customer_id,no_points,Points_winning.created_at,first_name,last_name,email,code FROM Points_winning INNER JOIN customers ON Points_winning.customer_id = customers.customer_id AND no_points >= 15";

        Response response;

        try {
            Statement statement = Db.getStatement();
            ResultSet records = statement.executeQuery(sql);

            List<WinnersDataFormat> winners = new ArrayList();

            ObjectMapper objectMapper = new ObjectMapper();
            while (records.next()) {
               PointsModelResponse format = new PointsModelResponse(
                        records.getInt("customer_id"),
                        records.getString("first_name"),
                        records.getString("last_name"),
                        records.getString("email"),
                        records.getFloat("no_points"),
                        records.getString("created_at"),
                        records.getInt("code")
                );
                String json = objectMapper.writeValueAsString(format);
                responseData.add(json);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            response = new Response(500,new Object());
        }

        try{
            OutputStream output = this.socket.getOutputStream();
            ObjectOutputStream objectOutput =  new ObjectOutputStream(output);
            //Sending the response to client
            objectOutput.writeObject(responseData);

        }
        catch(Exception e){
            System.out.println("Error in sending response: "+e.getMessage());
        }
        System.out.println("Response sent to client");

    }
}