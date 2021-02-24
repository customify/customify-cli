package com.customify.client.utils.authorization;

import com.customify.client.utils.authorization.structure.AdminUser;
import com.customify.client.utils.authorization.structure.EmployeeUser;
import com.customify.client.utils.authorization.structure.SuperAdminUser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveUser {

    private boolean saved = false;

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public SaveUser(){}
    public  SaveUser(String userType,Object user){
        File file;
        FileWriter writer;
        try {
            switch (userType){
                case "EMPLOYEE":
                    EmployeeUser emp_user =(EmployeeUser)user;
                    file = new File("src/com/customify/client/utils/authorization/files/Employee.csv");
                    writer   = new FileWriter(file,true);
                    writer.write("\n"+emp_user.getId()+"\t"+emp_user.getFirName()+"\t "+emp_user.getLasName()+"\t "+emp_user.getEmail()+"\t"+emp_user.getBusiness_id()+"\t"+emp_user.getTitle()+"\t"+emp_user.getCreatedAt()+"\t5-21-2021");
                    writer.close();
                    this.setSaved(true);
                    break;
                case "ADMIN":
                    AdminUser admin_user =(AdminUser)user;
                    file = new File("src/com/customify/client/utils/authorization/files/Admin.csv");
                    writer = new FileWriter(file,true);
                    writer.write("\n"+admin_user.getId()+"\t"+admin_user.getFirName()+"\t "+admin_user.getLasName()+"\t "+admin_user.getEmail()+"\t"+admin_user.getBusiness_id()+"\t"+admin_user.getTitle()+"\t"+"5-21-2021");
                    writer.close();
                    this.setSaved(true);

                    break;

                case "SUPER_ADMIN":
                    SuperAdminUser super_admin_user =(SuperAdminUser)user;
                    file = new File("src/com/customify/client/utils/authorization/files/SuperAdmin.csv");
                    writer = new FileWriter(file,true);
                    writer.write("\n"+super_admin_user.getId()+"\t"+super_admin_user.getFirName()+"\t "+super_admin_user.getLasName()+"\t "+super_admin_user.getEmail()+"\t"+super_admin_user.getTel()+"\t"+"5-21-2021");
                    writer.close();
                    this.setSaved(true);

                    break;

                default:
                    System.out.println("invalid choice");
            }

        } catch (IOException e) {
            System.out.println("\n\n\t\t\t"+e.getMessage()+"\n\n");
        }
    }
}
