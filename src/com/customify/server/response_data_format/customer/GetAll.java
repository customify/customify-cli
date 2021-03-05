package com.customify.server.response_data_format.customer;

public class GetAll {
    String firstName;
    String lastName;
    String email;
    String code;
    String customerId;
    String stateDesc;
    int status;



    public GetAll(String firstName, String lastName, String email, String code, String customerId, int status, String stateDesc) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.code = code;
        this.customerId = customerId;
        this.status = status;
        this.stateDesc = stateDesc;
    }
    public GetAll(){}
    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
