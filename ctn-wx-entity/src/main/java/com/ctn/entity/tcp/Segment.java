package com.ctn.entity.tcp;

/**
 * Created by barry on 2015/3/13.
 */
public class Segment extends FrameData{

    private String name;

    private Validate validate;

    private Integer start;

    private Integer end;

    private Attribute attr;

    private String len;

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
    }

    public Attribute getAttr() {
        return attr;
    }

    public void setAttr(Attribute attr) {
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
