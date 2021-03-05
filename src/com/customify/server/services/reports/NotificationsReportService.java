/**
 * @author Fiston Nshimiyandinze
 * @role
 * For handling reports concerned with business
 * */
package com.customify.server.services.reports;

import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.customify.server.response_data_format.report.BusinessReportFormat;
import com.customify.server.response_data_format.report.NotificationReportFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NotificationsReportService {
    Socket socket;
    OutputStream output;
    ObjectOutputStream objectOutput;
    Connection connection = Db.getConnection();
    List<String> response = new ArrayList<>();
    String statusCode;
    public NotificationsReportService(){}
    public  NotificationsReportService(Socket socket) throws IOException {
        this.socket = socket;
        this.output = socket.getOutputStream();
        this.objectOutput = new ObjectOutputStream(output);
    }
    public  void getAllNotificationDaily(){
        ObjectMapper objectMapper = new ObjectMapper();
        //formatting the response into a data format
        Statement statement = Db.getStatement();
        String query = "Select count(id)  as TotalNotification ,date(created_at) from Awards_Notifications group by date(created_at)";

        try {
            this.statusCode = "200";
            response.add(this.statusCode);
            ResultSet res = statement.executeQuery(query);
            String data;
            while(res.next()){

                NotificationReportFormat bs = new NotificationReportFormat(
                        res.getInt(1),
                        res.getString(2)
                );
                data = objectMapper.writeValueAsString(bs);
                response.add(data);
            }
        }
        catch (Exception e){
            this.statusCode = "500";
            response.add(this.statusCode);
        }
        finally{
            SendToClient sendToClient = new SendToClient(this.socket, this.response);
        }
    }


    public  void getAllNotificationWeekly(){
        ObjectMapper objectMapper = new ObjectMapper();
        //formatting the response into a data format
        Statement statement = Db.getStatement();
        String query = "Select count(id)  as TotalNotifications ,date(created_at) from Awards_Notifications group by week(created_at)";

        try {
            this.statusCode = "200";
            response.add(this.statusCode);
            ResultSet res = statement.executeQuery(query);
            String data;
            while(res.next()){

                NotificationReportFormat bs = new NotificationReportFormat(
                        res.getInt(1),
                        res.getString(2)
                );
                data = objectMapper.writeValueAsString(bs);
                response.add(data);
            }
        }
        catch (Exception e){
            this.statusCode = "500";
            response.add(this.statusCode);
        }
        finally{
            SendToClient sendToClient = new SendToClient(this.socket, this.response);
        }
    }




}
