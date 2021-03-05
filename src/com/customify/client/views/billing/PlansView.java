/**
 * @description
 *Main view for plans
 *
 * @author Patrick Niyogitare
 * 
 * */
package com.customify.client.views.billing;

import com.customify.client.Keys;
import com.customify.client.data_format.billing.CreatePlanFormat;
import com.customify.client.data_format.billing.FeatureFormat;
import com.customify.client.data_format.billing.PlanFormatClient;
import com.customify.client.services.billing.PlanService;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class PlansView {
    private Socket socket;
    private PlanService planService;

    public PlansView(Socket socket){

        this.socket = socket;
        this.planService = new PlanService(this.socket);
    }
    public Socket getSocket()
    {
        return socket;
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }
    public void view() throws Exception {
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        int choice;
        System.out.println("\t\t\t\t\t------------------ SUPER ADMIN > BILLING > PLANS ---------------------");
        System.out.println("\n\t\t\t\t\t         00. Return Home");
        System.out.println("\t\t\t\t\t         1. View plans");
        System.out.println("\t\t\t\t\t         2. Add a plan");
        System.out.println("\t\t\t\t\t         3. Update a plan");
        System.out.println("\t\t\t\t\t         4. Delete a plan");

        choice = scan.nextInt();

        switch (choice){
            case 1:
                PlanService planService = new PlanService(this.socket);
                planService.getAllPlan(Keys.GET_PLANS);
                break;
            case 2:
                createPlanView();
                break;
            case 3:

                break;
            case 4:

                break;
            case 0:

                loop = false;
            default:
                System.out.println("\t\t\t\t\tInvalid choice");
                loop = false;
        }
    }

    public void createPlanView() throws Exception {
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        String planTitle="", planDesc="";
        System.out.println("\t\t\t\t\t------------------ SUPER ADMIN > BILLING > FEATURES > CREATE PLAN ---------------------");
        System.out.println("\n\t\t\t\t\t         00. Return Home");
        System.out.println("\t\t\t\t\t         Enter  plan title: ");
        planTitle +=  scan.nextLine();
        if(planTitle.equals("00")){
            loop = false;
        }
        System.out.println("\t\t\t\t\t         Enter plan description: ");
        planDesc +=  scan.nextLine();
        if(planDesc.equals("00")){
            loop = false;
        }

        CreatePlanFormat format = new CreatePlanFormat(Keys.CREATE_PLAN, planTitle, planDesc);
        planService.createPlan(format);
    }

}

