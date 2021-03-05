package com.customify.client.utils.authorization;

import com.customify.client.utils.authorization.structure.AdminUser;
import com.customify.client.utils.authorization.structure.EmployeeUser;
import com.customify.client.utils.authorization.structure.SuperAdminUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserSession {
    private boolean loggedIn;
    private String userJsonObject;

    public UserSession() throws JsonProcessingException {
        RetrieveLoggedInUser loggedInUser = new RetrieveLoggedInUser();
        setUserJsonObject(loggedInUser.getJson());
        setLoggedIn(loggedInUser.isLoggedIn());
    }


    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getUserJsonObject() {
        return userJsonObject;
    }

    public void setUserJsonObject(String userJsonObject) {
        this.userJsonObject = userJsonObject;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }



    public boolean setEmployee(String json_object) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json_object);
        String email = jsonNode.get("email").asText();
        String business_id = jsonNode.get("business_id").asText();
        String title = jsonNode.get("title").asText();
        String firName = jsonNode.get("firName").asText();
        String lasName = jsonNode.get("lasName").asText();
        String id = jsonNode.get("id").asText();
        String createdAt =  jsonNode.get("createdAt").asText();


        EmployeeUser user = new EmployeeUser();

        user.setEmail(email);
        user.setBusiness_id(business_id);
        user.setFirName(firName);
        user.setLasName(lasName);
        user.setTitle(title);
        user.setId(id);
        user.setCreatedAt(createdAt);

        SaveUser savedUser = new SaveUser("EMPLOYEE",user);
        setUserJsonObject(json_object);
        boolean res = savedUser.isSaved();
        return res;
    }

    public boolean setBusinessAdmin(String json_object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json_object);
        String email = jsonNode.get("email").asText();
//        String password = jsonNode.get("password").asText();
        String business_id = jsonNode.get("business_id").asText();
        String title = jsonNode.get("title").asText();
        String firName = jsonNode.get("firName").asText();
        String lasName = jsonNode.get("lasName").asText();
        String id = jsonNode.get("id").asText();

        AdminUser user = new AdminUser();

        user.setEmail(email);
        user.setBusiness_id(business_id);
        user.setFirName(firName);
        user.setLasName(lasName);
        user.setTitle(title);
        user.setId(id);

        SaveUser savedUser = new SaveUser("ADMIN",user);
        setUserJsonObject(json_object);
        boolean res = savedUser.isSaved();
        return res;
    }

    public boolean setSuperAdmin(String json_object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json_object);
        String email = jsonNode.get("email").asText();
        String firName = jsonNode.get("firName").asText();
        String lasName = jsonNode.get("lasName").asText();
        String tel = jsonNode.get("tel").asText();
        String id = jsonNode.get("id").asText();

        SuperAdminUser user = new SuperAdminUser();

        user.setEmail(email);
        user.setFirName(firName);
        user.setLasName(lasName);
        user.setTel(tel);
        user.setId(id);
        SaveUser savedUser = new SaveUser("SUPER_ADMIN",user);

        setUserJsonObject(json_object);
        boolean res = savedUser.isSaved();
        return res;
    }

    public boolean unSet() throws JsonProcessingException {
        boolean deleted = false;
        if(loggedIn) {
            RemoveUser removeUser = new RemoveUser();
            deleted = removeUser.isDeleted();
        } else {
            System.out.println("\t\t\tNO  SESSION REGISTERED");
        }
        return deleted;
    }
}
