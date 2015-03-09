package com.ctn.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Barry on 2015/3/5.
 */

@RestController
@RequestMapping("/chart")
public class ChartController {

    private List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();


    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public Object chartData(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        String mark = request.getParameter("mark");
        if("Y".equals(mark)){
            map.put("state","Y");
            map.put("x",System.currentTimeMillis());
            map.put("y",getRandomFloat());
            map.put("type","column");
            list.add(map);
        }else{
            map.put("state","N");
        }
        return map;
    }

    @RequestMapping(value = "/datas", method = RequestMethod.GET)
    public Object chartDatas(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();

        map.put("ary",list);
        map.put("state",1);

        return map;
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public Object resetData(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();

        map.put("state",1);
        list = new ArrayList<Map<String,Object>>();
        return map;
    }

    public float getRandomFloat(){
        return new BigDecimal(50 - Math.random() * 200).divide(new BigDecimal(10),1, BigDecimal.ROUND_HALF_UP).floatValue();
    }


}
