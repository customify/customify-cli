package com.customify.client.data_format;

import com.customify.client.Keys;


public class GetWinnersDataFormat {
    private Keys key;
    private String data;

    public GetWinnersDataFormat() {
        this.key = Keys.GET_WINNERS;
    }

    public GetWinnersDataFormat(Keys key, String data) {
        this.key = key;
        this.data = data;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
