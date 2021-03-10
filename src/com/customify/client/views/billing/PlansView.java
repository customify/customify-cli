/**
 * @description
 *Main view for plans
 *
 * @author Patrick Niyogitare
 * 
 * */
package com.customify.client.views.billing;

import com.customify.client.Keys;
import com.customify.client.data_format.billing.*;
import com.customify.client.services.billing.PlanService;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class PlansView {
    private Socket socket;
    private PlanService planService;
    private Scanner scan = new Scanner(System.in);

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
        System.out.println("\t\t\t\t\t         5. Search a plan by Id");

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
                updatePlan();
                break;
            case 4:
                deletePlan();
                break;
            case 5:
                getPlanById();
                break;
            case 0:

                loop = false;
            default:
                System.out.println("\t\t\t\t\tInvalid choice");
                loop = false;
        }
    }

    public void createPlanView() throws Exception {
        boolean loop = true;
        String planTitle="", planDesc="";
        System.out.println("\t\t\t\t\t------------------ SUPER ADMIN > BILLING > FEATURES > CREATE PLAN ---------------------");
        System.out.println("\n\t\t\t\t\t         00. Return Home");
        System.out.println("\t\t\t\t\t         Enter  plan title: ");
        planTitle =  scan.nextLine();
        if(planTitle.equals("00")){
            loop = false;
        }
        System.out.println("\t\t\t\t\t         Enter plan description: ");
        planDesc =  scan.nextLine();
        if(planDesc.equals("00")){
            loop = false;
        }

        CreatePlanFormat format = new CreatePlanFormat(Keys.CREATE_PLAN, planTitle, planDesc);
        planService.createPlan(format);
    }

    public void updatePlan() throws Exception {
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        String updatedPlanName="", updatedPlanDesc="";
        int planId;
        System.out.println("\t\t\t\t\t------------------ SUPER ADMIN > BILLING > PLANS > UPDATE PLAN ---------------------");

        System.out.println("\n\t\t\t\t\t         00. Return Home");
        System.out.println("\n\t\t\t\t         Enter planId Id: ");
        String idStr = scan.nextLine();
        try {
            planId = Integer.parseInt(idStr);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Error: "+e.getMessage());
            planId = 0;
        }

        System.out.println("\t\t\t\t\t         Enter new plan name: ");
        updatedPlanName =  scan.nextLine();
        if(updatedPlanName.equals("00")){
            loop = false;
        }
        System.out.println("\t\t\t\t\t         Enter new plan description: ");
        updatedPlanDesc =  scan.nextLine();
        if(updatedPlanDesc.equals("00")){
            loop = false;
        }

        UpdatePlanFormat format = new UpdatePlanFormat(Keys.UPDATE_PLAN,planId, updatedPlanName, updatedPlanDesc);
        planService.updatePlan(format);
    }

    public void deletePlan() throws IOException, ClassNotFoundException {
        boolean loop = true;
        int planId;
        System.out.println("\t\t\t\t\t------------------ SUPER ADMIN > BILLING > PLANS > DELETE PLAN ---------------------");
        System.out.println("\n\t\t\t\t\t         00. Return Home");
        System.out.println("\t\t\t\t\t         Enter plan Id: ");
        planId =  scan.nextInt();
        if(planId == 0){
            loop = false;
        }
        SearchPlanFormat format = new SearchPlanFormat(Keys.DELETE_PLAN, planId);
        planService.deletePlan(format);
    }

    public void getPlanById() throws Exception {
        boolean loop = true;
        int planId;
        System.out.println("\t\t\t\t\t------------------ SUPER ADMIN > BILLING > PLANS > SEARCH PLAN ---------------------");
        System.out.println("\n\t\t\t\t\t         00. Return Home");
        System.out.println("\t\t\t\t\t         Enter plan Id: ");
        planId =  scan.nextInt();
        if(planId == 0){
            loop = false;
        }
        SearchPlanFormat format = new SearchPlanFormat(Keys.GET_PLAN_BY_ID, planId);
        planService.getPlanById(format);
    }

}

