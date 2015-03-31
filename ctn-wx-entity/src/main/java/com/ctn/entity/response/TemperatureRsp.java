package com.ctn.entity.response;

import com.ctn.entity.model.Temperature;

/**
 * Created by hp on 2015/3/17.
 */
public class TemperatureRsp extends Temperature {

    private String eslName;

    public String getEslName() {
        return eslName;
    }

    public void setEslName(String eslName) {
        this.eslName = eslName;
    }
}
