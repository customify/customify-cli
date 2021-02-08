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
    private Long productCode;

    public SuccessProductFormat(){}

    public SuccessProductFormat(String name) {
        this.name = name;
    }

    public SuccessProductFormat(Long productCode){
        this.productCode=productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public Long getProductCode() {
        return productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
