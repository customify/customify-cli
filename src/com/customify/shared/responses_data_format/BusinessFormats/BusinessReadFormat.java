/**
 * @description
 * Read businesses  format class
 * Used as data format for reading businesses
 *
 * @author Kellia Umuhire
 * @since Wednesday, 3 February 2021
 * */
package com.customify.shared.responses_data_format.BusinessFormats;

import java.io.Serializable;
import  java.util.*;

public class BusinessReadFormat implements Serializable{
    private List<BusinessRFormat> data;
    public BusinessReadFormat(){};
    public BusinessReadFormat(List<BusinessRFormat> bs){this.data=bs;}

    public List<BusinessRFormat> getData() {
        return data;
    }
}
