package com.customify.shared;

import java.io.Serializable;

public class Response implements Serializable {
    private int statusCode;
    private Object data;

    public Response(int status, Object data) {
        this.statusCode = status;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
