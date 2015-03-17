package com.ctn.service.temperature;

import com.ctn.entity.model.Temperature;
import com.ctn.service.BaseService;

import java.util.List;

/**
 * Created by Barry on 2014/9/28.
 */
public interface TemperatureService<T> extends BaseService<T> {

    List<Temperature> getAll();

    boolean updateColor(String color, Integer id);
}
