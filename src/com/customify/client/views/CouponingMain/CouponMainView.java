// By Makuza  Mugabo Verite on Feb 08 2021
// This handles all views related to coupons

package com.customify.client.views.CouponingMain;

import com.customify.client.data_format.CheckCoupon;
import com.customify.client.data_format.CouponFormat;
import com.customify.client.data_format.RedeemCoupon;
import com.customify.client.services.CouponService;

import java.net.Socket;
import java.util.Scanner;

public class CouponMainView {
    Socket socket;
    boolean isLoggedIn;

    public CouponMainView(Socket socket) {
        this.socket = socket;
    }



    public CouponMainView(Socket socket, boolean isLoggedIn) throws Exception {
        this.socket = socket;
        this.setLoggedIn(isLoggedIn);

        this.init();
    }

    public void init() throws Exception {

        Scanner reader = new Scanner(System.in);

        boolean isViewEnabled = true;

        do {
            System.out.println("|-------------------------------------|");
            System.out.println("|           COUPON MANAGEMENT         |");
            System.out.println("|_____________________________________|");
            System.out.println("|1.  create a coupon                  |");
            System.out.println("|2.  Redeem a coupon                  |");
            System.out.println("|3.  Check a coupon                   |");
            System.out.println("|4.  Get available coupons            |");
            System.out.println("|00. Back                             |");
            System.out.println("|_____________________________________|");
            System.out.println("Enter option here -> ");

            int option = reader.nextInt();

            switch (option){
                case 1:
                    this.createCoupon();
                    break;
                case 2:
                    this.redeemCoupon();
                    break;
                case 3:
                    this.checkCoupon();
                    break;
                case 4:
                    this.getAllCoupons();
                    break;
                case 00:
                    isViewEnabled = false;
                default:
                    System.out.println("You entered incorrect option");
            }
        }while (isViewEnabled);
    }

    public void createCoupon() throws Exception {

        CouponFormat couponFormat = new CouponFormat();
        Scanner reader = new Scanner(System.in);

        System.out.println("|--------------------------------------------|");
        System.out.println("|     Creating a coupon                      |");
        System.out.println("|--------------------------------------------|");


        System.out.println("Enter customer id ");
        couponFormat.setCustomer_id(reader.nextLine());

        System.out.println("Enter the a coupon source\n");
        System.out.println("1. Reached Maximum Points");
        System.out.println("2. Other");
        couponFormat.setSource(reader.nextLine());

        System.out.println("Enter the coupon value ");
        couponFormat.setValue(reader.nextLine());

        System.out.println("Enter the coupon expiry date ");
        couponFormat.setExpiry(reader.nextLine());

        CouponService couponService = new CouponService(this.socket);
        couponService.create(couponFormat);

        this.init();
    }
    public void redeemCoupon() throws Exception {
        Scanner scanner = new Scanner(System.in);
        RedeemCoupon redeemCoupon = new RedeemCoupon();
        System.out.println("|--------------------------------------------|");
        System.out.println("|     Redeeming a coupon                     |");
        System.out.println("|--------------------------------------------|");

        System.out.println("Enter a coupon code");
        redeemCoupon.setCoupon_code(scanner.nextLine());

        CouponService couponService = new CouponService(this.socket);
        couponService.reedemCoupon(redeemCoupon);

         this.init();
    }
    public void getAllCoupons() throws Exception {
        System.out.println("|--------------------------------------------|");
        System.out.println("|     All available coupons                  |");
        System.out.println("|--------------------------------------------|");
        System.out.println("Coupon list");
        this.init();
    }
    public void checkCoupon() throws Exception {
        Scanner scanner = new Scanner(System.in);
        CheckCoupon checkCoupon = new CheckCoupon();

        System.out.println("|--------------------------------------------|");
        System.out.println("|    Check Coupon                            |");
        System.out.println("|--------------------------------------------|");

        System.out.println("Enter a coupon code");
        checkCoupon.setCouponCode(scanner.nextLine());

        //TODO: add response from the server here
        System.out.println("Response from server here");
        this.init();
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
