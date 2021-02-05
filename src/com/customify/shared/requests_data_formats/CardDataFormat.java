package com.customify.shared.requests_data_formats;

public class CardDataFormat {
    long code;
    int createdById;

    public CardDataFormat() {
    }

    public CardDataFormat(long code, int createdById) {
        this.code = code;
        this.createdById = createdById;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public int getCreatedById() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }
}
