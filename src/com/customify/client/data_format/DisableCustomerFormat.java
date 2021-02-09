package com.customify.client.data_format;

import com.customify.client.Keys;

/**
 * @author Murenzi Confiance Tracy
 * @role
 * this is the the format for the disable card on the client side
 * */

public class DisableCustomerFormat {

        private Keys key;

        private String code;
        private int createdById;

        public DisableCustomerFormat(){}

        public DisableCustomerFormat(String code , int createdById){
            this.code = code;
            this.createdById = createdById;
            this.key = Keys.DISABLE_CUSTOMER;
        }

        public String getCode() { return code; }

        public void setCode(String code) { this.code = code; }

        public int getCreatedById() { return createdById; }

        public void setCreatedById(int createdById) { this.createdById = createdById; }

        public Keys getKey() { return key; }

        public void setKey(Keys keys) { this.key = keys; }
    }


