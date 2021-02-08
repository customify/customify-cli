/**
 * @description
 * The register business  view this is here to
 * register all the businesses in the project
 *
 * @author IRUMVA HABUMUGISHA Anselme
 * @version 1
 * @since Wednesday, 3 February 2021 - 08:17 - Time in Nyabihu
 * */

package com.customify.client.views.Business;

import com.customify.client.services.BusinessService;
import com.customify.shared.requests_data_formats.BusinessFormats.BusinessFormat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class BusinessRegisterView {
    private final Socket socket;

    // constructor created by anselme
    public BusinessRegisterView(Socket socket) {
        this.socket = socket;
    }

    /**
     * @author IRUMVA HABUMUGISHA Anselme
     * @role this function is to handle the entrance view of the class and casts it
     *       into the format all handlers for this register understands ...
     */

    public void view() throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        String name, location, address, phone_number;
        int representative_id, plan_id;

        System.out.println("\n\n\n\n You are going to register the business ");
        System.out.print("Enter the Business name           :  ");
        name = scan.nextLine();

        System.out.print("Enter the business location       :  ");
        location = scan.nextLine();

        System.out.print("Enter the business address        :  ");
        address = scan.nextLine();

        System.out.print("Enter the business phone number   :  ");
        phone_number = scan.nextLine();
        System.out.print("Enter the business representative :  ");
        representative_id = scan.nextInt();

        System.out.print("Enter the business plan           :  ");
        plan_id = scan.nextInt();

        // let me create the format for the business create
        var businessFormat = new BusinessFormat(name, location, phone_number, address, representative_id, plan_id);

        // make my service manage by request
        var businessService = new BusinessService(this.socket);

        // then try ti create the service
        businessService.create(businessFormat);
    }
}
