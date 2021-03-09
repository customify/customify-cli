package com.customify.client.views.employee;

import com.customify.client.Colors;
import com.customify.client.Login;
import com.customify.client.views.employee.*;
import com.customify.client.views.employee.ReadAll;
import com.customify.client.views.employee.ReadOne;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class EmployeeMainView {

    private Socket socket;
    private boolean loggedIn =false;

    public EmployeeMainView(){}


    public EmployeeMainView(Socket socket,boolean loggedIn)throws Exception {
        this.socket = socket;
        Login login;
        setLoggedIn(loggedIn);
        if(this.loggedIn)
            this.view();
        else
            login = new Login(socket);
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void view() throws IOException, ClassNotFoundException {
        boolean employeeView = true;

        if(loggedIn)
        {
            label:do {
                this.Header();
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t1. Add New Employee");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t2. Get All Employees");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t3. Search Employee");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t4. Update Employee");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t00. Back");
                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\tEnter your choice"+Colors.ANSI_YELLOW+" <1-00>"+Colors.ANSI_RESET+": ");
                Scanner scan = new Scanner(System.in);
                String choice = scan.nextLine();

                switch (choice) {
                    case "1":
                        break;
                    case "2":
                        ReadAll readAll = new ReadAll(this.socket);
                        readAll.view();

                        break;
                    case "3":
                        ReadOne readOne = new ReadOne(this.socket);
                        readOne.view();
                        break;
                    case "4":
                        break;
                    case "00":
                        employeeView = false;
                        break;
                    default:
                        System.out.println(Colors.ANSI_RED+"\t\t\t\t\t\t\t\t\t\t\t\t\t\tINVALID CHOICE"+Colors.ANSI_RESET);
                }
            } while (employeeView);
        }

    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void Header(){
        System.out.println(Colors.ANSI_GREEN);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tCUSTOMIFY EMPLOYEE MANAGEMENT");
        System.out.println(Colors.ANSI_RESET);
    }
}
