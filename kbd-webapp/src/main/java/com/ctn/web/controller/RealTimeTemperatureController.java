package com.ctn.web.controller;

import com.ctn.entity.temperature.RealTimeTemperature;
import com.ctn.receive.RealTimeTemperatureManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by barry on 2015/3/11.
 */

@RestController
@RequestMapping("/temp")
public class RealTimeTemperatureController {

    @Autowired
    private RealTimeTemperatureManager manager;

    @RequestMapping(value = "/real", method = RequestMethod.GET)
    public Object realTime(){

        RealTimeTemperature temp = manager.getRealTimeTemperature();

        if(temp != null)
            return temp;
        else
            return mapResp;

    }

    private Map<String,String> mapResp;

    @PostConstruct
    public void init(){
        mapResp = new HashMap<String,String>();
        mapResp.put("state","0");
    }

}
