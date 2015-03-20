package com.ctn.entity.tcp;

/**
 * Created by hp on 2015/3/18.
 */
public class SegmentData {

    private Integer len;
    private String name;
    private Object value;
    private byte[] bytes;
    private boolean isHead;
    private boolean isTail;

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean isHead) {
        this.isHead = isHead;
    }

    public boolean isTail() {
        return isTail;
    }

    public void setTail(boolean isTail) {
        this.isTail = isTail;
    }
}
