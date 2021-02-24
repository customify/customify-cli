package com.customify.client.utils.authorization;

import com.customify.client.utils.authorization.structure.AdminUser;
import com.customify.client.utils.authorization.structure.EmployeeUser;
import com.customify.client.utils.authorization.structure.SuperAdminUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RetrieveLoggedInUser {

    private EmployeeUser loggedEdInEmployee = null;

    public AdminUser getLoggedEdInAdmin() {
        return loggedEdInAdmin;
    }

    public SuperAdminUser getLoggedEdInSuperAdmin() {
        return loggedEdInSuperAdmin;
    }

    private AdminUser loggedEdInAdmin = null;
    private SuperAdminUser loggedEdInSuperAdmin = null;
    private boolean loggedIn;
     private String json;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public RetrieveLoggedInUser() throws JsonProcessingException {

        if(retrieveEmployee() != null || retrieveSuperAdmin() != null  || retrieveAdmin() != null)
        {
            loggedIn = true;
            this.setUserJson();
        }
        else
            loggedIn = false;
    }

    public EmployeeUser getLoggedEdInEmployee() {
        return loggedEdInEmployee;
    }

    public void setLoggedEdInEmployee(EmployeeUser loggedEdInEmployee) {
        this.loggedEdInEmployee = loggedEdInEmployee;
    }


    public void setLoggedEdInAdmin(AdminUser loggedEdInAdmin) {
        this.loggedEdInAdmin = loggedEdInAdmin;
    }

    public void setLoggedEdInSuperAdmin(SuperAdminUser loggedEdInSuperAdmin) {
        this.loggedEdInSuperAdmin = loggedEdInSuperAdmin;
    }





    public File retrieveEmployee(){
        File file = new File("src/com/customify/client/utils/authorization/files/Employee.csv");
        EmployeeUser user = new EmployeeUser();

        boolean exists = false;

        try{
            BufferedReader objReader = new BufferedReader(new FileReader(file));
            String currentLn = "";
            int counter = 0;
            int check = 0;
            while( (currentLn = objReader.readLine()) != null)
            {
                if(counter > 0)
                {
                    String[] listeMots = currentLn.split("\\s+");

                    if(!listeMots[0].equals(""))
                    {
                        exists = true;
                        user.setId(listeMots[0]);
                        user.setFirName(listeMots[1]);
                        user.setLasName(listeMots[2]);
                        user.setEmail(listeMots[3]);
                        user.setBusiness_id(listeMots[4]);
                        user.setTitle(listeMots[5]);
                        user.setSessionStart(listeMots[6]);
                        user.setCreatedAt(listeMots[7]);

                        user.setAppUser("EMPLOYEE");

                        this.setLoggedEdInEmployee(user);
                    }

                }

                counter++;
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        if(exists)
            return file;
            else
                return null;
    }

    public File retrieveAdmin(){
        File   file = new File("src/com/customify/client/utils/authorization/files/Admin.csv");
        AdminUser user = new AdminUser();

        boolean exists = false;

        try{
            BufferedReader objReader = new BufferedReader(new FileReader(file));
            String currentLn = "";
            int counter = 0;
            int check = 0;
            while( (currentLn = objReader.readLine()) != null)
            {
                if(counter > 0)
                {
                    String[] listeMots = currentLn.split("\\s+");

                    if(!listeMots[0].equals(""))
                    {
                        exists = true;
                        user.setId(listeMots[0]);
                        user.setFirName(listeMots[1]);
                        user.setLasName(listeMots[2]);
                        user.setEmail(listeMots[3]);
                        user.setBusiness_id(listeMots[4]);
                        user.setTitle(listeMots[5]);
                        user.setSessionStart(listeMots[6]);
                        user.setAppUser("BUSINESS_ADMIN");

                        this.setLoggedEdInAdmin(user);
                    }

                }

                counter++;
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        if(exists)
            return file;
        else
            return null;    }



    public File retrieveSuperAdmin(){
        File   file = new File("src/com/customify/client/utils/authorization/files/SuperAdmin.csv");
        SuperAdminUser user = new SuperAdminUser();

        boolean exists = false;

        try{
            BufferedReader objReader = new BufferedReader(new FileReader(file));
            String currentLn = "";
            int counter = 0;
            int check = 0;
            while( (currentLn = objReader.readLine()) != null)
            {
                if(counter > 0)
                {
                    String[] listeMots = currentLn.split("\\s+");

                    if(!listeMots[0].equals(""))
                    {
                        exists = true;
                        user.setId(listeMots[0]);
                        user.setFirName(listeMots[1]);
                        user.setLasName(listeMots[2]);
                        user.setEmail(listeMots[3]);
                        user.setTel(listeMots[4]);
                        user.setSessionStart(listeMots[5]);
                        user.setAppUser("SUPER_ADMIN");
                        this.setLoggedEdInSuperAdmin(user);
                    }
                }
                counter++;
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        if(exists)
            return file;
        else
            return null;    }

public void setUserJson() throws JsonProcessingException {
        String json = null;
        ObjectMapper objectMapper = new ObjectMapper();
        if(loggedEdInEmployee != null)
            json = objectMapper.writeValueAsString(loggedEdInEmployee);

        else if(loggedEdInAdmin != null)
            json = objectMapper.writeValueAsString(loggedEdInAdmin);

        else if(loggedEdInSuperAdmin != null)
            json = objectMapper.writeValueAsString(loggedEdInSuperAdmin);

        setJson(json);
}

}
