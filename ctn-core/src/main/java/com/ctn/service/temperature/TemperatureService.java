package com.ctn.service.temperature;

import com.ctn.entity.response.TemperatureRsp;
import com.ctn.service.BaseService;

import java.util.List;

/**
 * Created by Barry on 2014/9/28.
 */
public interface TemperatureService<T> extends BaseService<T> {

    List<TemperatureRsp> getAll();

    boolean updateEsl(Integer esl_id, Integer id);

    boolean updateColor(String color, Integer id);

    boolean updateXY(Integer x, Integer y, Integer id);
}
