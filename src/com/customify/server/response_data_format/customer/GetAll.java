package com.customify.server.response_data_format.customer;

public class GetAll {
    String firstName;
    String lastName;
    String email;
    String code;
    int customerId;
    int status;


    public GetAll(String firstName, String lastName, String email, String code, int customerId,int status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.code = code;
        this.customerId = customerId;
        this.status = status;
    }
    public GetAll(){}

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

    public int getCustomerId() {
        return customerId;
    }


    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
