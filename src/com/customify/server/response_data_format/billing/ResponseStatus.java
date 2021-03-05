package com.customify.server.response_data_format.billing;
/**
 * @author Patrick Niyogitare
 * @role
 * formating a response with only status code
 * */
public class ResponseStatus {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResponseStatus(){}
    public ResponseStatus(int status) {
        this.status = status;
    }
}
