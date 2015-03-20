package com.ctn.entity.tcp;

import java.util.List;

/**
 * Created by barry on 2015/3/13.
 */
public class Segment extends FrameData{

    private String name;

    private Validate validate;

    private Integer start;

    private Integer end;

    private List<Attribute> attr;

    private String len;

    private List<Data> data;

    public String getLen() {
        return len;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public void setLen(String len) {
        this.len = len;
    }

    public List<Attribute> getAttr() {
        return attr;
    }

    public void setAttr(List<Attribute> attr) {
        this.attr = attr;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Validate getValidate() {
        return validate;
    }

    public void setValidate(Validate validate) {
        this.validate = validate;
    }
}
