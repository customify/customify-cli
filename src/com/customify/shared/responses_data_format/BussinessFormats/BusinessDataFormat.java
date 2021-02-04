package com.customify.shared.responses_data_format.BussinessFormats;

import com.customify.shared.responses_data_format.BussinessFormats.BusinessFormat;

import java.io.Serializable;
import  java.util.*;

public class BusinessDataFormat implements Serializable{
    private List<BusinessFormat> data;
    public BusinessDataFormat(){};
    public BusinessDataFormat(List<BusinessFormat> bs){this.data=bs;}

    public List<BusinessFormat> getData() {
        return data;
    }
}
