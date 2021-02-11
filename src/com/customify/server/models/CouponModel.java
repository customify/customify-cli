/*
* created by veritem on 04 FEB 2020
*
* */

package com.customify.server.models;

import com.customify.server.utils.CouponStatus;

import java.util.Date;

public class CouponModel {
    private int coupon_id;
    private String customer_id;
    private String coupon_code;
    private Date expire_date;
    private Date created_at;
    private CouponStatus  couponStatus;
    private String coupon_value;

    public CouponModel(int coupon_id, String customer_id, String coupon_code, Date expire_date, Date created_at, CouponStatus couponStatus, String coupon_value) {
        this.coupon_id = coupon_id;
        this.customer_id = customer_id;
        this.coupon_code = coupon_code;
        this.expire_date = expire_date;
        this.created_at = created_at;
        this.couponStatus = couponStatus;
        this.coupon_value = coupon_value;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public Date getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(Date expire_date) {
        this.expire_date = expire_date;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public CouponStatus getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(CouponStatus couponStatus) {
        this.couponStatus = couponStatus;
    }

    public String getCoupon_value() {
        return coupon_value;
    }

    public void setCoupon_value(String coupon_value) {
        this.coupon_value = coupon_value;
    }

    @Override
    public String toString() {
       return  "{"+this.coupon_id+","+this.coupon_code+","+this.coupon_value+","+this.couponStatus+","+this.created_at+","+this.customer_id+"}";
    }
}
