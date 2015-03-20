package com.ctn.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Barry on 2015/3/5.
 */

@RestController
@RequestMapping("/echart")
public class EChartController {

    private List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    private long oneDay = 24 * 60 * 60 *1000;

    @RequestMapping(value = "/day", method = RequestMethod.GET)
    public Object chartDayDatas(HttpServletRequest request,HttpServletResponse response){
        Long from = Long.valueOf(request.getParameter("from"));
        Long to = Long.valueOf(request.getParameter("to"));
        Map<String,Object> map = new HashMap<String,Object>();
        List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
        for (long i = from; i <= to;){
            Map<String,Object> m = new HashMap<String,Object>();
            m.put("x",i);
            m.put("y",getRandomFloat());
            lists.add(m);
            i += oneDay;
        }
        map.put("ary",lists);
        map.put("state",1);
        return map;
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public Object chartData(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        String mark = request.getParameter("mark");
        if("Y".equals(mark)){
            map.put("state","Y");
            map.put("times",new SimpleDateFormat("HH:mm:ss").format(new Date()));
            map.put("val",getRandomFloat());

           /* Map<String,Object> map2 = new HashMap<String,Object>();
            map2.put("x",System.currentTimeMillis());
            map2.put("y",diff((Float)map.get("y")));
            list.add(map2);*/
            list.add(map);
        }else{
            map.put("state","N");
        }
        return map;
    }

    private Float diff(Float f1){
        return new BigDecimal(f1).subtract(new BigDecimal(5)).floatValue();
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
