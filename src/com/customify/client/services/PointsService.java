package com.customify.client.services;

import com.customify.client.Common;
import com.customify.shared.Keys;
import com.customify.shared.Request;
import com.customify.shared.Response;
import com.customify.shared.requests_data_formats.PointsByCustomerEmailFormat;
import com.customify.shared.responses_data_format.WinnersDataFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

/**
 * @author GISA KAZE Fredson
 * */
public class PointsService {
    private Socket socket;
    private Common common;

    InputStream inputStream;
    ObjectInputStream objectInputStream;

    public PointsService(Socket socket) {
        this.socket = socket;
    }

    /**
     * @author GISA KAZE Fredson
     */
    public void getWinners() {
        try {
            PointsByCustomerEmailFormat data = new PointsByCustomerEmailFormat();

            Request request = new Request(Keys.GET_WINNERS, data);
            this.common = new Common(request, this.socket);

            if (common.sendToServer()) {
                System.out.println("\n\t\t   Request Sent successfully\n");
                this.getAllWinnersSuccess();
            } else {
                System.out.println("\nError occurred when sending request to server\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getPointsByCustomerEmail(PointsByCustomerEmailFormat format) {
        try {
//                System.out.println(format);
            Request request = new Request(Keys.POINTS_BY_CUSTOMER_EMAIL, format);
            this.common = new Common(request, this.socket);
            if (common.sendToServer()) {
                System.out.println("\n\t\t   Request Sent successfully\n");
            } else {
                System.out.println("\nError occurred when sending request to server\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllWinnersSuccess() throws IOException {

        inputStream = this.getSocket().getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

        try {
            List<Response> response = (List<Response>) objectInputStream.readObject();

            if(response.get(0).getStatusCode() == 200){
                List<WinnersDataFormat> winners =(List<WinnersDataFormat>) response.get(0).getData();

                if (winners.size() == 0) {
                    System.out.println("We don't have any winner.");
                    return;
                }

                System.out.println(String.format("%-10s %-20s %-20s %-24s %10s %-10s %10s", "Customer Id", "First name", "Last name", "Email", "Points", "Winning date", "Customer code"));

                for (int i = 0; i < winners.size(); i++) {
                    System.out.println(String.format("%-10s %-20s %-20s %-24s %10s %-10s %10s", winners.get(i).getCustomerId(), winners.get(i).getFirstName(), winners.get(i).getLastName(), winners.get(i).getEmail(), winners.get(i).getNoPoints(), winners.get(i).getWiningDate(), winners.get(i).getCode()));
                }
            }
            else{
                System.out.println("\n Unknown error occurred.Check your internet connection \n");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}