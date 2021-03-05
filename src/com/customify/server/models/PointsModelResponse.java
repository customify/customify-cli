package com.customify.server.models;

public class PointsModelResponse {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private double noPoints;
    private String winingDate;
    private int code;

    public PointsModelResponse() {
    }

    public PointsModelResponse(int customerId, String firstName, String lastName, String email, double noPoints, String winingDate, int code) {

        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.noPoints = noPoints;
        this.winingDate = winingDate;
        this.code = code;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
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

    public double getNoPoints() {
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
