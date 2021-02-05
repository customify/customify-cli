/**
 * @description
 * Response to Send When Product transactions goes successfull
 *
 * @author SAUVE Jean-Luc
 * @version 1
 * */

package com.customify.shared.responses_data_format.ProductFormats;



import java.io.Serializable;

public class SuccessProductFormat  implements Serializable {
    private String name;

    public SuccessProductFormat(){}
    public SuccessProductFormat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
