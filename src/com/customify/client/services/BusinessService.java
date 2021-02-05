package com.customify.client.services;
import com.customify.shared.Keys;
import com.customify.client.Common;
import com.customify.shared.Request;
import com.customify.shared.Response;

import com.customify.shared.requests_data_formats.GetbusinessFormat;
import com.customify.shared.responses_data_format.BussinessFormats.BusinessFormat;
import com.customify.shared.responses_data_format.BussinessFormats.BusinessDataFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class BusinessService {
    private Socket socket;
    InputStream inputStream;
    ObjectInputStream objectInputStream;

    public BusinessService(Socket socket){this.socket=socket;}
    public Socket getSocket()
    {
        return socket;
    }
    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = this.socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

    }

    public void getbusinesses (GetbusinessFormat format) throws IOException, ClassNotFoundException{
        Request request = new Request(Keys.GET_BUSS,format);
        Common common = new Common(request,this.socket);
        if(common.sendToServer()){
            this.handleResponse();
        }
    }

    public void handleResponse() throws IOException,ClassNotFoundException{
        inputStream = this.socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
        List<Response> response = (List<Response>) objectInputStream.readObject();
        if(response.get(0).getStatusCode()==200){
            BusinessDataFormat data = (BusinessDataFormat) response.get(0).getData();
            System.out.println("------------------List of Businesses------------------");
            System.out.format("%5s%20s%20s%25s%20s\n","ID","Name","Location","Address","Phone number");
            System.out.println();
            for(BusinessFormat bs:data.getData()){
                System.out.format("%5d%20s%20s%25s%20s\n",bs.getId(),bs.getName(),bs.getLocation(),bs.getAddress(),bs.getPhone_number());
            }

            System.out.println("----------------------------------------------");
        }
    }



}
