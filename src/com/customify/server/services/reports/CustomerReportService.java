package com.customify.server.services.reports;

/**
 * @author Mfuranziza Sekata Aimelyse Moss
 * Created and Wrote Whole Document By Moss
 * */

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.customify.server.response_data_format.report.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerReportService {
    Socket socket;
    OutputStream output;
    ObjectOutputStream objectOutput;
    Connection connection = Db.getConnection();
    List<String> response = new ArrayList<>();

    public CustomerReportService(){}
    public CustomerReportService(Socket socket) throws IOException {
        this.socket = socket;
        this.output = socket.getOutputStream();
        this.objectOutput = new ObjectOutputStream(output);
    }
    public void getCustomerDaily(){
        ObjectMapper objectMapper = new ObjectMapper();

        //formatting the response into a data format
        Statement statement = Db.getStatement();
        String query = "select count(customer_id) as Total_Users, date(created_at) from Customer group by date(created_at)";
        try {

            ResultSet res = statement.executeQuery(query);
            while(res.next()){

                CustomerReportFormat bs = new CustomerReportFormat(
                        res.getInt(1),
                        res.getDate(2).toString()
                );
                String json = objectMapper.writeValueAsString(bs);
                response.add(json);
            }

            SendToClient sendToClient = new SendToClient(this.socket, this.response);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getCustomerWeekly(){
        ObjectMapper objectMapper = new ObjectMapper();

        //formatting the response into a data format
        Statement statement = Db.getStatement();
        String query = "select count(customer_id) as totalCustomers, date(created_at) from Customer group by week(created_at)";
        try {

            ResultSet res = statement.executeQuery(query);
            while(res.next()){

                CustomerReportFormat bs = new CustomerReportFormat(
                        res.getInt(1),
                        res.getDate(2).toString()
                );
                String json = objectMapper.writeValueAsString(bs);
                response.add(json);
            }

            SendToClient sendToClient = new SendToClient(this.socket, this.response);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getSaleDaily(){
        ObjectMapper objectMapper = new ObjectMapper();

        //formatting the response into a data format
        Statement statement = Db.getStatement();
        String query = "select count(saleId) as totalSales, date(created_at) from Sale group by date(created_at)";
        try {

            ResultSet res = statement.executeQuery(query);
            while(res.next()){

                SaleReportFormat bs = new SaleReportFormat(
                        res.getInt(1),
                        res.getDate(2).toString()
                );
                String json = objectMapper.writeValueAsString(bs);
                response.add(json);
            }

            SendToClient sendToClient = new SendToClient(this.socket, this.response);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getQuantityConsumed(){
        ObjectMapper objectMapper = new ObjectMapper();

        //formatting the response into a data format
        Statement statement = Db.getStatement();
        String query = "select count(quantity) as totalQuantity, date(created_at) from Sale group by date(created_at)";
        try {

            ResultSet res = statement.executeQuery(query);
            while(res.next()){

                QuantityReportFormat bs = new QuantityReportFormat(
                        res.getInt(1),
                        res.getDate(2).toString()
                );
                String json = objectMapper.writeValueAsString(bs);
                response.add(json);
            }

            SendToClient sendToClient = new SendToClient(this.socket, this.response);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
