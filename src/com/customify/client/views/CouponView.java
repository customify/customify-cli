// By Makuza  Mugabo Verite on Feb 08 2021
// This handles all views related to coupons

package com.customify.client.views;

import com.customify.client.data_format.CouponFormat;
import com.customify.client.services.CouponService;

import java.net.Socket;
import java.util.Scanner;

public class CouponView {
    Socket socket;

    public CouponView(Socket socket) {
        this.socket = socket;
    }

    public void init(){

        Scanner reader = new Scanner(System.in);

        int option;

        System.out.println("|------------------------------------|");
        System.out.println("| COUPONS                            |");
        System.out.println("|____________________________________|");
        System.out.println("|1. create a coupon                  |");
        System.out.println("|2. Redeem a coupon                  |");
        System.out.println("|3. Check a coupon                   |");
        System.out.println("|4. Get available coupons            |");
        System.out.println("|____________________________________|");
        System.out.println("\n\n");
        System.out.println("Enter option here -> ");

        option = reader.nextInt();

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
            default:
                System.out.println("You entered incorrect option");
        }

    }

    public void createCoupon(){

        CouponFormat couponFormat = new CouponFormat();
        Scanner reader = new Scanner(System.in);

        System.out.println("|--------------------------------------------|");
        System.out.println("|     Creating a coupon                      |");
        System.out.println("|--------------------------------------------|");


        System.out.println("Enter customer id ");
        couponFormat.setCustomer_id(reader.nextLine());

        System.out.println("Enter the a coupon source \n1. Reached Maximum Points\n 2. Other");
        couponFormat.setSource(reader.nextLine());

        System.out.println("Enter the coupon value ");
        couponFormat.setValue(reader.nextLine());

        System.out.println("Enter the coupon expiry date ");
        couponFormat.setExpiry(reader.nextLine());

        CouponService couponService = new CouponService(this.socket);
        //couponService.create(couponFormat);
    }
    public void redeemCoupon(){ }
    public void getAllCoupons(){}
    public void checkCoupon(){}


}
