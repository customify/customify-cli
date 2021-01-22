package customify;

public class Request {
    private String key;
    private Object requestData;

    public Request(String key, Object requestData) {
        this.key = key;
        this.requestData = requestData;
    }

    public String getKey() {
        return key;
    }


    public Object getRequestData() {
        return requestData;
    }
}
