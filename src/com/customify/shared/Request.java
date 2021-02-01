package com.customify.shared;
import com.customify.shared.Keys;

import java.io.Serializable;

public class Request implements Serializable {
    private String key;
    private Object data;


    public Request(Keys key, Object data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key){
        this.key = key;
    }

    public Object getObject() {
        return data;
    }
    public void setObject(Object data){
        this.data=data;
    }
}
