package com.customify.client.points;

import com.customify.client.Keys;

public class GetWinners {
    private Keys key;



    public GetWinners(Keys key) {
      this.key = key;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}
