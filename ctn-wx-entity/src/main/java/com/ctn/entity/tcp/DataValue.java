package com.ctn.entity.tcp;

/**
 * Created by hp on 2015/3/20.
 */
public class DataValue {

    private String type;

    private Object val;

    private String key;

    private byte[] data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
