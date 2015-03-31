package com.ctn.entity.model;

/**
 * Created by hp on 2015/3/17.
 */
public class Temperature {

    private Integer id;
    private Long create_time;
    private Long update_time;
    private String humidity;
    private String color;
    private Integer x;
    private Integer y;
    private String temp_code;
    private String light;
    private String celsius;
    private String electric;
    private String temp_name;
    private Integer esl_id;

    public Integer getEsl_id() {
        return esl_id;
    }

    public void setEsl_id(Integer esl_id) {
        this.esl_id = esl_id;
    }

    public String getTemp_name() {
        return temp_name;
    }

    public void setTemp_name(String temp_name) {
        this.temp_name = temp_name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }

    public String getTemp_code() {
        return temp_code;
    }

    public void setTemp_code(String temp_code) {
        this.temp_code = temp_code;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getCelsius() {
        return celsius;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }

    public String getElectric() {
        return electric;
    }

    public void setElectric(String electric) {
        this.electric = electric;
    }
}
