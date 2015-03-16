package com.ctn.entity.tcp;

/**
 * Created by hp on 2015/3/16.
 */
public class FrameData {

    protected Integer index;

    protected String len;

    protected String value;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
