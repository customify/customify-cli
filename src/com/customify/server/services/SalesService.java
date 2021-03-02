//By Makuza Mugabo Verite on 02/03/2021

package com.customify.server.services;

import com.customify.server.Db.Db;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SalesService {
  private Socket socket;
  private Connection connection = Db.getConnection();

    public SalesService(Socket socket) {
        this.socket = socket;
    }

    public void getAllSales(){

        try{
            String query = "SELECT * FROM Sale";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                System.out.println("Id: "+resultSet.getInt(1)+" customerId: "+resultSet.getString(2)+" quantity: "+resultSet.getString(3)+" totalPrice: "+resultSet.getString(4)+" employeeID: "+resultSet.getString(5)+" productID: "+resultSet.getString(6));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
