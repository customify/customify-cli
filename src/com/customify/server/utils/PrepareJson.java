package com.customify.server.utils;

public class PrepareJson {

    private String values;
    private String meta_data;
    private String json;

    public PrepareJson(String values,String meta_data)
    {
        this.values = values;
        this.meta_data = meta_data;
        String[] metaData = meta_data.split("\\s*,\\s*");
        String[] metaValues = values.split(",");

        if(metaValues.length != metaData.length)
            System.out.println("META DATA LENGTH IS NOT EQUAL TO THE LENGTH OF CORRESPONDING VALUES");
        else
            setJson();
    }

    public void setJson()
    {
        String[] list;
        String[] metaData = meta_data.split("\\s*,\\s*");
        String[] metaValues = values.split(",");
        this.json  ="{"+metaData[0]+':'+metaValues[0];
        for(int i=1;i<metaData.length;i++){
            this.json  +=","+metaData[i]+':'+metaValues[i];
        }
        this.json += "}";
    }

    public String getJson(){
        return this.json;
    }

}
