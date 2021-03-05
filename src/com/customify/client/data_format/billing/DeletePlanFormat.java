package com.customify.client.data_format.billing;
/**
 * @author Patrick Niyogitare
 * @role The class models the request for deleting a plan
 * */
import com.customify.client.Keys;

public class DeletePlanFormat {
    private Keys key;

    public DeletePlanFormat(){}

    public DeletePlanFormat(Keys key) {
        this.key = key;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}
