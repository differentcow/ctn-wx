package com.ctn.entity.tcp;

//import com.ctn.entity.tcp.validate.FrameValidate;

import java.util.List;

/**
 * Created by barry on 2015/3/13.
 */
public class Frame{

    private List<Segment> segment;

    private String id;

    private Head head;

    private Validate validate;

    private List<Express> express;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Validate getValidate() {
        return validate;
    }

    public void setValidate(Validate validate) {
        this.validate = validate;
    }

    public List<Express> getExpress() {
        return express;
    }

    public void setExpress(List<Express> express) {
        this.express = express;
    }

    public List<Segment> getSegment() {
        return segment;
    }

    public void setSegment(List<Segment> segment) {
        this.segment = segment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
