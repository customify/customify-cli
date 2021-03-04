/**
 * @description
 * The edit business  view this is here to
 * register all the businesses in the project
 *
 * @author IRUMVA HABUMUGISHA Anselme
 * @version 1
 * @since Wednesday, 3 February 2021 - 08:17 - Time in Nyabihu
 * */

package com.customify.client.views.Business;

import com.customify.client.Colors;
import com.customify.client.Keys;
import com.customify.client.services.BusinessService;
import com.customify.client.data_format.business.BusinessFormat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class BusinessEditView {
    Socket socket;
    public BusinessEditView(Socket socket) {
        this.socket = socket;
    }

    /**
     * @author IRUMVA HABUMUGISHA Anselme
     * @role
     * this function is to handle the entrance view of the class and casts it into the format all
     * handlers for this register understands ...
     * */
    public void view() throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        String name, location, address, phone_number;
        int id, representative_id, plan_id;

        System.out.println(Colors.ANSI_YELLOW);
        System.out.println("\n\n\n You are going to edit the business :\n\n\n");
        System.out.println(Colors.ANSI_RESET);

        System.out.print("Enter the new location             : ");
        location = scan.nextLine();
        System.out.print("Enter the new name                 : ");
        name = scan.nextLine();
        System.out.print("Enter the new phone number         : ");
        phone_number = scan.nextLine();
        System.out.print("Enter the new address              : ");
        address = scan.nextLine();
        System.out.print("Enter the new representative id    : ");
        representative_id = scan.nextInt();
        System.out.print("Enter the new plan id              : ");
        plan_id = scan.nextInt();
        System.out.print("Enter the id of the business you are going to edit : ");
        id = scan.nextInt();

        // let me create the format for the business create
        var businessFormat = new BusinessFormat(Keys.EDIT_BUSINESS, name, location, phone_number, address, representative_id, plan_id);

        // let me add the Id of the business to edit
        businessFormat.setId(id);

        // make my service manage by request
        var businessService = new BusinessService(this.socket);

        // then try ti call the function to edit my business
        businessService.update(businessFormat);
    }
}
