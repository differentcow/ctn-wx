package com.ctn.web.controller;

import com.ctn.web.controller.test.TestArrayData;
import org.apache.commons.lang.StringUtils;
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
            list.add(map);
        }else{
            map.put("state","N");
        }
        return map;
    }

    private Float diff(Float f1){
        return new BigDecimal(f1).subtract(new BigDecimal(5)).floatValue();
    }

    private long oneDay = 24 * 60 * 60 *1000;

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

    private long halfhour = 30 * 60 * 1000L;
    private long tenmin = 10 * 60 * 1000L;
    private long tensec = 10 * 1000L;

    @RequestMapping(value = "/realtime", method = RequestMethod.GET)
    public Object realtime(HttpServletRequest request,HttpServletResponse response){
        String isnow = request.getParameter("isnow");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("state",1);
        if("1".equals(isnow)){
            List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
            long from = System.currentTimeMillis() - tenmin;
            int cnt = 0;
            for (long i = from;cnt < 60;cnt++){
                Map<String,Object> m = new HashMap<String,Object>();
                m.put("x",i);
                m.put("y",TestArrayData.real[cnt]);
                data.add(m);
                i += tensec;
            }
            map.put("ary",data);
        }else{
            //判断是否与上一次传送的时间(x坐标)一致，如一致，返回state=0
            /*String time = request.getParameter("time");
            if (StringUtils.isNumeric(time)){

            }*/
            map.put("x",System.currentTimeMillis());
            map.put("y",(int)(Math.random() * 25 + 10));
        }

        return map;
    }

    @RequestMapping(value = "/his", method = RequestMethod.GET)
    public Object history(HttpServletRequest request,HttpServletResponse response){
        Long from = Long.valueOf(request.getParameter("from"));
        Long to = Long.valueOf(request.getParameter("to"));
        String load = request.getParameter("load");
        boolean flag = StringUtils.isBlank(load)?false:true;
        Map<String,Object> map = new HashMap<String,Object>();
        int cnt = 0;
        List<Map<String,Object>> max = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> min = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> avg = new ArrayList<Map<String,Object>>();
        for (long i = from;cnt < 48;cnt++){
            Map<String,Object> m = new HashMap<String,Object>();
            Map<String,Object> m1 = new HashMap<String,Object>();
            Map<String,Object> m2 = new HashMap<String,Object>();
            m.put("x",i);
            m.put("y",flag? TestArrayData.max[cnt]+2:TestArrayData.max[cnt]);
            max.add(m);
            m1.put("x",i);
            m1.put("y",flag? TestArrayData.min[cnt]+2:TestArrayData.min[cnt]);
            min.add(m1);
            m2.put("x", i);
            m2.put("y", flag ? TestArrayData.avg[cnt] + 2 : TestArrayData.avg[cnt]);
            avg.add(m2);
            i += halfhour;
        }
        map.put("avg",avg);
        map.put("max",max);
        map.put("min",min);
        map.put("state",1);
        return map;
    }

    public String turnHHMM(Long time){
        return new SimpleDateFormat("MM/dd hh:mm").format(new Date(time));
    }

    public float getRandomFloat(){
        return new BigDecimal(Math.random() * 40+10).divide(new BigDecimal(10),1, BigDecimal.ROUND_HALF_UP).floatValue();
    }


}
