/**
 * @author GISA KAZE Fredson
 */

package com.customify.server.services;

import com.customify.server.CustomizedObjectOutputStream;
import com.customify.server.Db.Db;
import com.customify.server.data_format.sales.SaleDataFormat;
import com.customify.server.response_data_format.WinnersDataFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.customify.server.data_format.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PointsService {
    private Socket socket;
//    private Request request;
    DataOutputStream output;

    public PointsService(Socket socket) {
        this.socket = socket;
    }

    /**
     * @author GISA KAZE Fredson
     */
//    public void getPointsByCustomerEmail() {
//        PointsByCustomerEmailFormat data = (PointsByCustomerEmailFormat) request.getObject();
//        System.out.println("Customer email: " + data.getEmail());
//    }

    public void getWinners() throws SQLException, IOException {
//        System.out.println("Request to get winners received at server");
        String sql = "SELECT Points_winning.customer_id,no_points,Points_winning.created_at,first_name,last_name,email,code FROM Points_winning INNER JOIN Customer ON Points_winning.customer_id = Customer.customer_id AND no_points >= 15";

//        Response response;

        try {
            Statement statement = Db.getStatement();
            ResultSet records = statement.executeQuery(sql);

            List<String> winners = new ArrayList();
            CustomizedObjectOutputStream outputStream = new CustomizedObjectOutputStream(new ObjectOutputStream(socket.getOutputStream()));

            while (records.next()) {
                winners.add(
                        new ObjectMapper().writeValueAsString(
                                new WinnersDataFormat(
                                        records.getString("customer_id"),
                                        records.getString("first_name"),
                                        records.getString("last_name"),
                                        records.getString("email"),
                                        records.getFloat("no_points"),
                                        records.getString("created_at"),
                                        records.getString("code")
                                )
                        )
                );
            }
            outputStream.writeObject(winners);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
//            response = new Response(500,new Object());
        }

//        OutputStream output = this.socket.getOutputStream();
//        ObjectOutputStream objectOutput =  new ObjectOutputStream(output);
//
//        List responseData = new ArrayList<>();
//        responseData.add(response);

        //Sending the response to client
//        objectOutput.writeObject(responseData);
    }


    public static boolean recordPointsAfterSale(SaleDataFormat saleDataFormat) throws SQLException {
        try {
            Connection connection = Db.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT bonded_points FROM products WHERE products.id =?");
            preparedStatement.setInt(1, saleDataFormat.getProductId());

            ResultSet resultSet = preparedStatement.executeQuery();
            float product_points = 0;
            if (preparedStatement.executeUpdate() == 0) return false;


            while (resultSet.next()) {
                product_points = saleDataFormat.getQuantity() * resultSet.getFloat("bonded_points");
            }

            preparedStatement = connection.prepareStatement("INSERT INTO Points(customer_id,number_of_points,`source`) VALUES (?,?,'product')");
            preparedStatement.setString(1, saleDataFormat.getCustomerId());
            preparedStatement.setFloat(2, product_points);

            if (preparedStatement.executeUpdate() == 0) return false;

            preparedStatement  = connection.prepareStatement("UPDATE Points_winning SET no_points = no_points+? WHERE customer_id = ?");
            preparedStatement.setFloat(1,product_points);
            preparedStatement.setString(2,saleDataFormat.getCustomerId());

            if (preparedStatement.executeUpdate() < 1){
                preparedStatement  = connection.prepareStatement("INSERT INTO Points_winning(customer_id,no_points) values (?,?)");
                preparedStatement.setString(1,saleDataFormat.getCustomerId());
                preparedStatement.setFloat(2,product_points);
            }

            return true;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

//    public String customer() {
//        try {
//            ResultSet product = Db.getStatement().executeQuery("SELECT * FROM products");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return "Home";
//    }
}