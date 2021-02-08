// By Makuza  Mugabo Verite on Feb 08 2021
// This handles all views related to coupons

package com.customify.client.views;

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
        System.out.println("|____________________________________|");
        System.out.println("\n\n");
        System.out.println("Enter option here -> ");

        option = Integer.parseInt(reader.nextLine());

        switch (option){
            case 0:
                System.out.println("Hello world!");
                break;
            default:
                System.out.println("You entered incorrect option");
        }

    }
}
