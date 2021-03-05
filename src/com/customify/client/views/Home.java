/*
  Class to display the Home view
*/
package com.customify.client.views;

import com.customify.client.Colors;
import com.customify.client.views.Business.BusinessEditView;
import com.customify.client.views.Business.BusinessRegisterView;
import com.customify.client.views.Business.BusinessView;
import com.customify.client.views.CustomerFeedback.CustomerFeedbackView;
import com.customify.client.views.CustomerFeedback.CustomerFeedbackMainView;
import com.customify.client.views.customer.CustomerMainView;

import java.net.Socket;
import java.util.*;

public class Home {

    private Socket socket;

    public Home(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void view() throws Exception {


        int choice;
        Scanner scan = new Scanner(System.in);

        CustomerMainView customer = new CustomerMainView(this.socket,true);

        ProductView productView = new ProductView(this.socket);

        BusinessView businessView = new BusinessView(this.socket);
        BusinessRegisterView businessRegisterView = new BusinessRegisterView(this.socket);
        BusinessEditView businessEditView = new BusinessEditView(this.socket);
        Coupon couponView = new Coupon(this.socket);
        CustomerFeedbackView feedbackView = new CustomerFeedbackView(this.socket);


        System.out.println("---------------------------------------------");
        System.out.println("--------------CUSTOMIFY HOME-----------------\n");
        System.out.println("           1. SIGN UP");
        System.out.println("           2. LOGIN");
        System.out.println("           3. PRODUCT MANAGEMENT");
        System.out.println("           4. BUSINESS");
        System.out.println("           5. GIVE FEEDBACK");
        System.out.println("           6. PROVIDE FEEDBACK ");
        System.out.println("           7. POINTS");
        System.out.println("           8. CUSTOMER MANAGEMENT");
        System.out.println("           9. COUPONS");
        System.out.println("           10.OPERATE");
        choice = scan.nextInt();

        switch (choice) {
            case 1:
//                signupView.view();
                break;
            case 2:
//                loginView.view();
                break;
            case 3:
                productView.init();
                break;
            case 4:
                businessView.view();
                break;
            case 5:
                System.out.println("Not Yet Done");
                break;
            case 6:
                feedbackView.view();
                break;
            case 7:
//                pointCountingView.view();
                break;
            case 8:
                customer.view();
                break;
            case 9:
                couponView.init();
                break;
            default:
                System.out.println(Colors.ANSI_RED+"\t\t\t\t\t\t\t\t\t\t\t\t\t\tINVALID CHOICE"+Colors.ANSI_RESET);
        }
    }
}