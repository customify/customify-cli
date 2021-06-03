/**
 * @author GISA KAZE Fredson
 */


package com.customify.server.response_data_format;

import java.io.Serializable;

public class WinnersDataFormat implements Serializable {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private float noPoints;
    private String winingDate;
    private String code;

    public WinnersDataFormat() {
    }

    public WinnersDataFormat(String customerId, String firstName, String lastName, String email, float noPoints, String winingDate, String code) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.noPoints = noPoints;
        this.winingDate = winingDate;
        this.code = code;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public float getNoPoints() {
        return noPoints;
    }

    public void setNoPoints(float noPoints) {
        this.noPoints = noPoints;
    }

    public String getWiningDate() {
        return winingDate;
    }

    public void setWiningDate(String winingDate) {
        this.winingDate = winingDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
