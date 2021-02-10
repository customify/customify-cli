/**
 * @description
 * The register business  format class this is here to
 * register all the businesses in the project must use this format
 *
 * @author IRUMVA HABUMUGISHA Anselme
 * @version 1
 * @since Wednesday, 3 February 2021 - 08:17 - Time in Nyabihu
 * */

package com.customify.client.data_format.business;

import com.customify.client.Keys;

import java.io.Serializable;

public class BusinessFormat implements Serializable {

    private Keys key;
    private int id;
    private String name;
    private String location;
    private String phone_number;
    private String address;
    private int representative;
    private int plan;

    public BusinessFormat(){}

    public BusinessFormat(Keys key, String name, String location, String phone_number, String address, int representative, int plan) {
        this.key = key;
        this.name = name;
        this.location = location;
        this.phone_number = phone_number;
        this.address = address;
        this.representative = representative;
        this.plan = plan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRepresentative() {
        return representative;
    }

    public void setRepresentative(int representative) {
        this.representative = representative;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }
}
