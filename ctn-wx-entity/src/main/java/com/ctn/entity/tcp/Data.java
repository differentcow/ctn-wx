package com.ctn.entity.tcp;

/**
 * Created by hp on 2015/3/20.
 */
public class Data {

    private String ref;
    private String filter;
    private String start;
    private String len;

    private Express exFilter;
    private Express exStart;
    private Express exLen;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
        this.exFilter = new Express(filter);
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
        this.exStart = new Express(start);
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
        this.exLen = new Express(len);
    }

    public Express getExFilter() {
        return exFilter;
    }

    public void setExFilter(Express exFilter) {
        this.exFilter = exFilter;
    }

    public Express getExStart() {
        return exStart;
    }

    public void setExStart(Express exStart) {
        this.exStart = exStart;
    }

    public Express getExLen() {
        return exLen;
    }

    public void setExLen(Express exLen) {
        this.exLen = exLen;
    }
}
