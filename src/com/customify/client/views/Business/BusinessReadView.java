/**
 * @description
 * View Class for reading  businesses/business
 *
 * @author Kellia Umuhire
 * @since Wednesday, 3 February 2021
 * */

package com.customify.client.views.Business;

import com.customify.client.services.BusinessService;
import com.customify.client.Keys;


import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class BusinessReadView {
    private Socket socket;

    public BusinessReadView(Socket socket){
        this.socket = socket;
    }
    public Socket getSocket()
    {
        return socket;
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }

    public void viewAll()throws IOException,ClassNotFoundException{
        String json = "{ \"key\" : \""+ Keys.GET_ALL_BUSINESSES +"\" }";
        BusinessService businessService = new BusinessService(socket);
        businessService.getbusinesses(json);
    }

    public void viewById()throws IOException, ClassNotFoundException{
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter businessId: \t");
        int businessId = scan.nextInt();
        String json = "{ \"businessId\" : \""+businessId+"\", \"key\" : \""+ Keys.GET_BUSINESS +"\" }";
        BusinessService businessService = new BusinessService(socket);
        businessService.getById(json);
    }

    public void deleteBusiness()throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter businessId: \t");
        int businessId = scan.nextInt();
        String json = "{ \"businessId\" : \""+businessId+"\", \"key\" : \""+ Keys.REMOVE_BUSINESS +"\" }";
        BusinessService businessService = new BusinessService(socket);
        businessService.deleteBusiness(json);
    }

}
