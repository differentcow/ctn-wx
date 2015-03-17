package com.ctn.web.controller.temp;

import com.ctn.entity.model.Temperature;
import com.ctn.service.temperature.TemperatureService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Barry on 2015/3/5.
 */

@RestController
@RequestMapping("/temperature")
public class TempController {

    private Map<String,Object> map;

    @PostConstruct
    public void init(){
        map = new HashMap<String,Object>();
    }

    @Autowired
    private TemperatureService<Temperature> tempService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(HttpServletRequest request,HttpServletResponse response){
        try {
            List<Temperature> temps = tempService.getAll();
            if (!temps.isEmpty()){
                map.put("state",1);
                map.put("array",temps);
            }else{
                map.put("state",0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/color/upt", method = RequestMethod.PUT)
    public Object uptColor(@RequestBody Map<String,String> value){
        try {
            String color = value.get("color");
            if(StringUtils.isNotBlank(color)){
                String idStr = value.get("id");
                if (StringUtils.isNumeric(idStr) && tempService.updateColor(color,Integer.valueOf(idStr))){
                    map.put("color_state",1);
                    return map;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("color_state",0);
        return map;
    }
}
