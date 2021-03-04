package com.customify.client.views;

//import com.customify.client.services.AuthService;
import com.customify.client.services.PointsService;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author GISA KAZE Fredson
 * */
public class PointCountingView {
    private Socket socket;

    public PointCountingView() {

    }
    public PointCountingView(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * @author GISA KAZE Fredson
     * */
    public void view() throws IOException {
        boolean points = true;
        Scanner scan = new Scanner(System.in);
        String choice;
        String customerEmail = null;

        pointsLoop:do{
            System.out.println("------------------POINTS---------------------");
            System.out.println("\n\t\t00. Return Home");
            System.out.println("\t\t1. Winners");
            System.out.println("\t\t2. Points by customer");
            System.out.println("\t\t3. Back");

            System.out.print("\n\n\t\tEnter your choice: ");
            choice=scan.nextLine();

            switch (choice){
                case "1":
                    PointsService pointsService = new PointsService(this.socket);
                    pointsService.getWinners();
                    break;
                case "2":
                    System.out.print("\n\t\tEnter Customer Email: ");
//                    customerEmail = scan.nextLine();
//                    PointsByCustomerEmailFormat format = new PointsByCustomerEmailFormat(customerEmail);
//                    PointsService pointsService = new PointsService(socket);
//                    pointsService.getPointsByCustomerEmail(format);
                    break;
                case "3":
                    break pointsLoop;
                default:
                    System.out.println("INVALID CHOICE");

            }
            points = false;
        }while(points);
    }
}