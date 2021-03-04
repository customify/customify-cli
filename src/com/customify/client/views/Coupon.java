// By Makuza  Mugabo Verite on Feb 08 2021
// This handles all views related to coupons

package com.customify.client.views;

import com.customify.client.Colors;
import com.customify.client.data_format.CouponFormat;
import com.customify.client.data_format.RedeemCoupon;
import com.customify.client.services.CouponService;

import java.net.Socket;
import java.util.Scanner;

public class Coupon {
    Socket socket;
    boolean isLoggedIn;

    public Coupon(Socket socket) {
        this.socket = socket;
    }


    public Coupon(Socket socket, boolean isLoggedIn) throws Exception {
        this.socket = socket;
        this.setLoggedIn(isLoggedIn);

        this.init();
    }

    public void init() throws Exception {

        Scanner reader = new Scanner(System.in);

        boolean isViewEnabled = true;

        do {
            this.Header();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t1.  create a coupon");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t2.  Redeem a coupon");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t3.  Get available coupons");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t00. Back\n");
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter your choice"+Colors.ANSI_YELLOW+" <1-00>"+Colors.ANSI_RESET+": ");

            int option = reader.nextInt();

            switch (option){
                case 1:
                    this.createCoupon();
                    break;
                case 2:
                    this.redeemCoupon();
                    break;
                case 3:
                    this.getAllCoupons();
                    break;
                case 00:
                    isViewEnabled = false;
                    break;
                default:
                    System.out.println(Colors.ANSI_RED+"\t\t\t\t\t\t\t\t\t\t\t\t\t\tINVALID CHOICE"+Colors.ANSI_RESET);
            }
        }while (isViewEnabled);
    }

    public void createCoupon() throws Exception {

        CouponFormat couponFormat = new CouponFormat();
        Scanner reader = new Scanner(System.in);

        this.Header();

        this.Title("Creating a coupon");


        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter customer id: ");
        couponFormat.setCustomer_id(reader.nextLine());

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter the a coupon source");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t1. Reached Maximum Points");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t2. Other");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tChoice here: ");
        couponFormat.setSource(reader.nextLine());

        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter the coupon value ");
        couponFormat.setValue(reader.nextLine());

        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter the coupon expiry date ");
        couponFormat.setExpiry(reader.nextLine());

        CouponService couponService = new CouponService(this.socket);
        couponService.create(couponFormat);

        this.init();
    }
    public void redeemCoupon() throws Exception {
        Scanner reader = new Scanner(System.in);
        RedeemCoupon redeemCoupon = new RedeemCoupon();


        this.Header();
        this.Title("Redeem coupon");


        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter coupon code: ");
        redeemCoupon.setCoupon_code(reader.nextLine());

        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter customerID: ");
        redeemCoupon.setCustomerID(reader.nextLine());

        CouponService couponService = new CouponService(this.socket);
        couponService.redeemCoupon(redeemCoupon);
        this.init();
    }
    public void getAllCoupons() throws Exception {

        this.Header();

        this.Title("Coupon list");
      CouponService couponService = new CouponService(this.socket);
      couponService.getCoupons();
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public void Header(){
            System.out.println(Colors.ANSI_GREEN);
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tCUSTOMIFY COUPON MANAGEMENT");
            System.out.println(Colors.ANSI_RESET);
    }

    public void Title(String title){
            System.out.println(Colors.ANSI_BLUE);
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+title.toUpperCase());
            System.out.println(Colors.ANSI_RESET);
    }

    public void notYetImplemented(){
        System.out.println(Colors.ANSI_RED);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tNOTHING HERE YET");
        System.out.println(Colors.ANSI_RESET);
    }

}
