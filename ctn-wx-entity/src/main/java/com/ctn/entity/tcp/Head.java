package com.ctn.entity.tcp;

/**
 * Created by HLJ on 2015/3/15.
 */
public class Head {
    private String type;
    private String value;

    private Integer headIndex;

    public Integer getHeadIndex() {
        return headIndex;
    }

    public void setHeadIndex(Integer headIndex) {
        this.headIndex = headIndex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
