package com.customify.client.utils.authorization;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.*;

public class RemoveUser {

public RemoveUser() throws JsonProcessingException {
    findLog();
}


    public File getLogFile() {
        return logFile;
    }

    public void setLogFile(File logFile) {
        this.logFile = logFile;
    }

    private File logFile;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    private boolean deleted;

    public void findLog() throws JsonProcessingException {
        RetrieveLoggedInUser loggedInUser =new  RetrieveLoggedInUser();


        if(loggedInUser.getLoggedEdInEmployee() != null)
            this.logFile = loggedInUser.retrieveEmployee();

        else if(loggedInUser.getLoggedEdInAdmin() != null)
            this.logFile = loggedInUser.retrieveAdmin();

        else if(loggedInUser.getLoggedEdInSuperAdmin() != null)
            this.logFile = loggedInUser.retrieveSuperAdmin();
        this.setLogFile(logFile);
        this.unSetSession();
    }

    public void unSetSession(){
        try{
            BufferedReader file = new BufferedReader(new FileReader(this.logFile));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            int counter = 0;
            int check =0;
            int found = 0;
            while((line = file.readLine()) != null)
            {
                String[] listeMots = line.split("\\s+");

                if(counter > 0)
                {

                    if(!listeMots[0].equals(""))
                    {
                        found = 1;
                        check = 1;
                    }else {
                        found = 0;
                    }

                }
                if(found == 0)
                {
                    inputBuffer.append(line);
                    inputBuffer.append("\n");
                }
                counter++;
            }

            file.close();


            FileOutputStream fileOut= new FileOutputStream(this.logFile);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
            if(check == 1)
               setDeleted(true);
            else
                setDeleted(false);
        }catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

    }


}
