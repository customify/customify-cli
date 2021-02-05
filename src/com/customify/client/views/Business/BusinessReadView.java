package com.customify.client.views.Business;

import com.customify.client.services.BusinessService;
import com.customify.shared.requests_data_formats.BusinessFormats.GetbusinessFormat;

import java.io.IOException;
import java.net.Socket;

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
        GetbusinessFormat format = new GetbusinessFormat();
        BusinessService businessService = new BusinessService(socket);
        businessService.getbusinesses(format);
    }
}
