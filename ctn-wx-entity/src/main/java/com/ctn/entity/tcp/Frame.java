package com.ctn.entity.tcp;

import java.util.List;

/**
 * Created by barry on 2015/3/13.
 */
public class Frame {

    private List<Segment> segment;

    private String id;

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
