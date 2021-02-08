package com.customify.shared;

import java.io.Serializable;

public class Request implements Serializable {
    private Keys key;
    private Object data;

//    Added Request Constuctor Overlapping By Moss
    public  Request(Keys key){
        this.key = key;
    }

    public Request(Keys key, Object data) {
        this.key = key;
        this.data = data;
    }

    public Keys getKey() {
        return this.key;
    }
    public void setKey(Keys key){
        this.key = key;
    }

    public Object getObject() {
        return data;
    }
    public void setObject(Object data){
        this.data=data;
    }
}
