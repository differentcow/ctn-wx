package com.ctn.entity.temperature;

import java.util.Date;

/**
 * Created by barry on 2015/3/11.
 */
public class RealTimeTemperature {

    private Float temperature;

    private Long timestamp;

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Float getTemperature() {
        return temperature;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setData(Float temperature,Date now){
        this.temperature = temperature;
        timestamp = now.getTime();
    }

    public void setData(Float temperature,Long now){
        this.temperature = temperature;
        timestamp = now;
    }
}
