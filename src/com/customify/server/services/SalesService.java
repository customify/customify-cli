//By Makuza Mugabo Verite on 02/03/2021

package com.customify.server.services;

import com.customify.server.CustomizedObjectOutputStream;
import com.customify.server.Db.Db;
import com.customify.server.response_data_format.sale.SaleDataFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SalesService {
  private Socket socket;
  private Connection connection = Db.getConnection();
  private SaleDataFormat saleDataFormat;
  ObjectMapper objectMapper = new ObjectMapper();
  ObjectOutputStream objectOutputStream;
  OutputStream outputStream;

    public SalesService(Socket socket) {
        this.socket = socket;
    }

    public void getAllSales() throws IOException {
      String jsonData = "";
        try{
            String query = "SELECT * FROM Sale";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                this.saleDataFormat = new SaleDataFormat(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6));
                jsonData += this.objectMapper.writeValueAsString(saleDataFormat);
            }
        }catch (SQLException | JsonProcessingException e){
            e.printStackTrace();
            System.out.println("Error while sending to the client");
        }finally {
            outputStream = socket.getOutputStream();
            this.objectOutputStream = new CustomizedObjectOutputStream(this.outputStream);
            objectOutputStream.writeObject(jsonData);
        }
    }
}
