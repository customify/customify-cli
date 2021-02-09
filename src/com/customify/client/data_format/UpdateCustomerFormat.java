package com.customify.client.data_format;
/**
 * @author Nshimiye Emmy
 * @role
 * this is the class format update customer data so that they can be sent and to the server
 * */
import com.customify.client.Keys;

public class UpdateCustomerFormat {
    private Keys key;
    private String customer_code;
    private String email;
    private String firstName;
    private String lastName;

    public UpdateCustomerFormat() { }

    public UpdateCustomerFormat( String customer_code,String email, String lastName, String firstName) {
        this.customer_code = customer_code;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.key = Keys.UPDATE_CUSTOMER;
    }
    public String customer_code() {
        return this.customer_code;
    }

    public void setCustomer_code(String customer_codes) {
        this.customer_code = customer_codes;
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
