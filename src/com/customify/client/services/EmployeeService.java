package com.customify.client.services;


import com.customify.client.Colors;
import com.customify.client.Keys;
import com.customify.client.SendToServer;
import com.customify.client.data_format.*;

import com.customify.client.data_format.products.ProductFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private Socket socket;
    private ObjectMapper objectMapper;

    public EmployeeService() {
    }

    public EmployeeService(Socket socket) {
        this.socket = socket;
    }


    public void create() throws IOException, ClassNotFoundException {

    }

    public void handleCreateCustomerResponse() throws IOException, ClassNotFoundException {

    }

    public Socket getSocket() {
        return socket;
    }

    public void handleUpdateCustomerSuccess() throws IOException, ClassNotFoundException {

    }

    public void update() throws IOException, ClassNotFoundException {

    }


    public List getAll() throws IOException {
        String json = "{ \"key\" : \"" + Keys.GET_ALL_EMPLOYEES + "\"}";
        SendToServer serverSend = new SendToServer(json, this.socket);
        List<String> res = new ArrayList<>();
        if (serverSend.send()) {

            try {
                InputStream input = this.socket.getInputStream();
                ObjectInputStream objectInput = new ObjectInputStream(input);
                res = (List) objectInput.readObject();

                objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res.get(0));

                if (jsonNode.get("status").asInt() == 500) {
                    System.out.println("\t\t\t\t---- INTERNAL SERVER ERROR -----");
                    return null;
                } else if (jsonNode.get("status").asInt() == 404) {
                    System.out.println("\n\t\t\t*******************************************************************************************************");
                    System.out.println("                                                 NO EMPLOYEES FOUND                                            ");
                    System.out.println("\t\t\t*******************************************************************************************************");
                    return null;
                }


            } catch (Exception e) {
                System.out.println("RESPONSE ERROR" + e.getMessage());
            }
        }
        return res;

    }

    public List get(GetEmployee format) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        List<String> res = new ArrayList<>();
        if (serverSend.send()) {
            try {
                InputStream input = this.socket.getInputStream();
                ObjectInputStream objectInput = new ObjectInputStream(input);
                res = (List) objectInput.readObject();
                JsonNode jsonNode = objectMapper.readTree(res.get(0));

                if (jsonNode.get("status").asInt() == 404) {
                    System.out.println("\n\t\t\t*******************************************************************************************************");
                    System.out.println("                                                  NO EMPLOYEE FOUND                                            ");
                    System.out.println("\t\t\t*******************************************************************************************************");
                    return null;
                }

            } catch (Exception e) {
                System.out.println("RESPONSE ERROR" + e.getMessage());
            }
        }
        return res;
    }
}

