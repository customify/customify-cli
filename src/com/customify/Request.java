package customify;

import java.io.Serializable;

public class Request implements Serializable {
    private String key;
    private Object requestData;

    public Request() {

    }

    public Request(String key, Object requestData) {
        this.key = key;
        this.requestData = requestData;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key){
        this.key = key;
    }

    public Object getRequestData() {
        return requestData;
    }
    public void setRequestData(Object data){
        this.requestData = data;
    }
}
