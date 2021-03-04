package com.customify.client.views;

//import com.customify.client.services.AuthService;
import com.customify.client.Colors;
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
            this.Header();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t1. WINNERS");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t2. POINTS BY EMAIL");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t3. BACK");

            System.out.print("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter your choice: ");
            choice=scan.nextLine();

            switch (choice){
                case "1":
                    PointsService pointsService = new PointsService(this.socket);
                    pointsService.getWinners();
                    break;
                case "2":
                    System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter Customer Email: ");
//                    customerEmail = scan.nextLine();
//                    PointsByCustomerEmailFormat format = new PointsByCustomerEmailFormat(customerEmail);
//                    PointsService pointsService = new PointsService(socket);
//                    pointsService.getPointsByCustomerEmail(format);
                    break;
                case "3":
                    break pointsLoop;
                default:
                    System.out.println(Colors.ANSI_RED+"\t\t\t\t\t\t\t\t\t\t\t\t\t\tINVALID CHOICE"+Colors.ANSI_RESET);

            }
            points = false;
        }while(points);
    }
    public void Header(){
        System.out.println(Colors.ANSI_CYAN);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWINNERS");
        System.out.println(Colors.ANSI_RESET);
    }
}