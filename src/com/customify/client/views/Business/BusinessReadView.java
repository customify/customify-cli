/**
 * @description
 * View Class for reading  businesses/business
 *
 * @author Kellia Umuhire
 * @since Wednesday, 3 February 2021
 * */

package com.customify.client.views.Business;

import com.customify.client.services.BusinessService;
import com.customify.server.Keys;
import com.customify.shared.requests_data_formats.BusinessFormats.GetbusinessFormat;
import com.customify.shared.requests_data_formats.BusinessFormats.DeleteBusinessFormat;
import com.customify.client.data_format.business.GetBusinessFormat;

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
        GetBusinessFormat format = new GetBusinessFormat(Keys.GET_ALL_BUSINESSES);
        BusinessService businessService = new BusinessService(socket);
        businessService.getbusinesses(format);
    }

    public void viewById()throws IOException, ClassNotFoundException{
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter businessId: \t");
        int businessId = scan.nextInt();
        GetBusinessFormat format = new GetBusinessFormat(businessId,Keys.GET_BUSINESS);
        BusinessService businessService = new BusinessService(socket);
        businessService.getById(format);
    }

    public void deleteBusiness()throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter businessId: \t");
        int businessId = scan.nextInt();
        DeleteBusinessFormat format = new DeleteBusinessFormat(businessId);
        BusinessService businessService = new BusinessService(socket);
        businessService.deleteBusiness(format);
    }

}
