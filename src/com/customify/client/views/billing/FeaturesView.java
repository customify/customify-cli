/**
 * @description
 * Specific view for the features
 *
 * @author Patrick Niyogitare
 * */
package com.customify.client.views.billing;

import com.customify.client.Keys;
import com.customify.client.data_format.billing.DeleteFeatureFormat;
import com.customify.client.data_format.billing.FeatureFormat;
import com.customify.client.data_format.billing.GetFeatureByIdFormat;
import com.customify.client.data_format.billing.UpdatedFeatureFormat;
import com.customify.client.services.BillingService;
import com.customify.client.services.FeatureService;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class FeaturesView {
    private Socket socket;
    private BillingService billingService;
    private FeatureService featureService;
    Scanner scan = new Scanner(System.in);

    public FeaturesView(Socket socket){
        this.socket = socket;
        this.billingService = new BillingService(socket);
        this.featureService = new FeatureService(socket);

    }
    public Socket getSocket()
    {
        return socket;
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }

    public void view() throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        int choice;
        System.out.println("\t\t\t\t------------------ SUPER ADMIN > BILLING > FEATURES ---------------------");
        System.out.println("\n\t\t\t\t         00. Return Home");
        System.out.println("\t\t\t\t         1. View Featurs");
        System.out.println("\t\t\t\t         2. Add a Feature");
        System.out.println("\t\t\t\t         3. Update a Feature");
        System.out.println("\t\t\t\t         4. Delete a Delete a feature");
        System.out.println("\t\t\t\t         5. Get feature by Id");


        choice = scan.nextInt();

        switch (choice){
            case 1:
                featureService.getFeatures();
                break;
            case 2:
                createNewFeatureView();
                break;
            case 3:
                updateFeature();
                break;
            case 4:
                deleteFeature();
                break;
            case 5:
                getFeatureById();
                break;
            default:
                System.out.println("\t\t\t\tInvalid choice");
                loop = false;
        }
    }


    public void createNewFeatureView() throws IOException {
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        String featureName="", featureDesc="";
        System.out.println("\t\t\t\t\t------------------ SUPER ADMIN > BILLING > FEATURES > CREATE FEATURE ---------------------");
        System.out.println("\n\t\t\t\t\t         00. Return Home");
        System.out.println("\t\t\t\t\t         Enter feature name: ");
        featureName +=  scan.nextLine();
        if(featureName.equals("00")){
            loop = false;
        }
        System.out.println("\t\t\t\t\t         Enter feature description: ");
        featureDesc +=  scan.nextLine();
        if(featureDesc.equals("00")){
            loop = false;
        }

        FeatureFormat format = new FeatureFormat(Keys.REGISTER_FEATURE, featureName, featureDesc);
        featureService.RegisterFeature(format);
    }
    public void getFeatureById() throws IOException, ClassNotFoundException {
        boolean loop = true;
        int featureId;
        System.out.println("\t\t\t\t\t------------------ SUPER ADMIN > BILLING > FEATURES > DELETE FEATURE ---------------------");
        System.out.println("\n\t\t\t\t\t         00. Return Home");
        System.out.println("\t\t\t\t\t         Enter Feature Id: ");
        featureId =  scan.nextInt();
        if(featureId == 0){
            loop = false;
        }
        GetFeatureByIdFormat format = new GetFeatureByIdFormat(Keys.GET_FEATURE_BY_ID, featureId);
        featureService.getFeaturesById(format);
    }
    public void deleteFeature() throws IOException, ClassNotFoundException {
        boolean loop = true;
        int featureId;
        System.out.println("\t\t\t\t\t------------------ SUPER ADMIN > BILLING > FEATURES > DELETE FEATURE ---------------------");
        System.out.println("\n\t\t\t\t\t         00. Return Home");
        System.out.println("\t\t\t\t\t         Enter Feature Id: ");
        featureId =  scan.nextInt();
        if(featureId == 0){
            loop = false;
        }
        DeleteFeatureFormat format = new DeleteFeatureFormat(Keys.DELETE_FEATURE, featureId);
        featureService.deleteFeature(format);
    }

    public void updateFeature() throws IOException {
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        String updatedDeatureName="", updatedFeatureDesc="";
        int featureId;
        System.out.println("\t\t\t\t\t------------------ SUPER ADMIN > BILLING > FEATURES > UPDATE FEATURE ---------------------");
        System.out.println("\n\t\t\t\t\t         00. Return Home");

        System.out.println("\t\t\t\t\t         Enter feature Id : ");
        featureId =  scan.nextInt();
        if(featureId == 0){
            loop = false;
        }

        System.out.println("\t\t\t\t\t         Enter feature name : ");
        updatedDeatureName =  scan.next();
        if(updatedDeatureName.equals("00")){
            loop = false;
        }
        System.out.println("\t\t\t\t\t         Enter feature description: ");
        updatedFeatureDesc +=  scan.next();

        if(updatedFeatureDesc.equals("00")){
            loop = false;
        }

        UpdatedFeatureFormat format = new UpdatedFeatureFormat(Keys.UPDATE_FEATURE, featureId, updatedDeatureName, updatedFeatureDesc);
        featureService.update(format);
    }

}
