package com.customify.client.data_format;
/**
 * @author Nshimiye Emmy
 * @role
 * this is the class format update customer data so that they can be sent and to the server
 * */
import com.customify.client.Keys;

public class UpdateCustomerFormat {
    private Keys key;
    private String customerCode;
    private String email;
    private String firstName;
    private String lastName;

    public UpdateCustomerFormat() { }

    public UpdateCustomerFormat(Keys key, String customerCode, String email, String firstName, String lastName) {
        this.key = key;
        this.customerCode = customerCode;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String customer_code() {
        return this.customerCode;
    }

    public void setCustomerCode(String customer_codes) {
        this.customerCode = customer_codes;
    }

    public String getEmail() {
        return this.email;
    }

    public Keys getKey() {
        return this.key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firName) {
        this.firstName = firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lasName) {
        this.lastName = lastName;
    }
}
