/*
* By Veritem on 4 Feb 2020
*
* */


package com.customify.server.models;

public class PointModel {
    private int id;
    private String customer_id;
    private float number_of_points;
    private String last_update;

    public PointModel(int id, String customer_id, float number_of_points, String last_update) {
        this.id = id;
        this.customer_id = customer_id;
        this.number_of_points = number_of_points;
        this.last_update = last_update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public float getNumber_of_points() {
        return number_of_points;
    }

    public void setNumber_of_points(float number_of_points) {
        this.number_of_points = number_of_points;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}
