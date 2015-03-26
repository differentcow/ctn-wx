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
@RequestMapping("/echart")
public class EChartController {

    private List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    private long oneDay = 24 * 60 * 60 *1000;
    private long halfhour = 30 * 60 * 1000L;
    private long fifMin = 15*60*1000L;

    @RequestMapping(value = "/his", method = RequestMethod.GET)
    public Object history(HttpServletRequest request,HttpServletResponse response){
        Long from = Long.valueOf(request.getParameter("from"));
        Long to = Long.valueOf(request.getParameter("to"));
        String load = request.getParameter("load");
        boolean flag = StringUtils.isBlank(load)?false:true;
        Map<String,Object> map = new HashMap<String,Object>();
        int cnt = 0;
        List<Long> xdata = new ArrayList<Long>();
        List<Map<String,Object>> max = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> min = new ArrayList<Map<String,Object>>();
        List<Integer> avg = new ArrayList<Integer>();
        List<Integer> top = new ArrayList<Integer>();
        List<Integer> botton = new ArrayList<Integer>();
        long toX = 0L;
        long fromX = 0L;
        for (long i = from;cnt < 48;cnt++){
            xdata.add(i);
            top.add(18);
            botton.add(2);
            Map<String,Object> m1 = new HashMap<String,Object>();
            Map<String,Object> m2 = new HashMap<String,Object>();
            m1.put("val",flag?TestArrayData.max[cnt]+2:TestArrayData.max[cnt]);
            m1.put("real",i-fifMin);
            max.add(m1);
            m2.put("val",flag?TestArrayData.min[cnt]+2:TestArrayData.min[cnt]);
            m2.put("real",i-fifMin);
            min.add(m2);
            avg.add(flag?TestArrayData.avg[cnt]+2:TestArrayData.avg[cnt]);
            if(cnt == 47){
                toX = i;
            }
            if(cnt == 0){
                fromX = i;
            }
            i += halfhour;
        }
        map.put("xdata",xdata);
        map.put("avg",avg);
        map.put("toX",toX);
        map.put("top",top);
        map.put("botton",botton);
        map.put("fromX",fromX);
        map.put("max",max);
        map.put("min",min);
        map.put("state",1);
        return map;
    }

    private String turnHHMM(long time){
        String date = new SimpleDateFormat("yyyy/MM/dd-hh:mm").format(new Date(time));
        return date.split("-")[1];
    }
/*
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
    }*/
/*
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public Object chartData(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        String mark = request.getParameter("mark");
        if("Y".equals(mark)){
            map.put("state","Y");
            map.put("times",new SimpleDateFormat("HH:mm:ss").format(new Date()));
            map.put("val",getRandomFloat());

           *//* Map<String,Object> map2 = new HashMap<String,Object>();
            map2.put("x",System.currentTimeMillis());
            map2.put("y",diff((Float)map.get("y")));
            list.add(map2);*//*
            list.add(map);
        }else{
            map.put("state","N");
        }
        return map;
    }*/

    private Float diff(Float f1){
        return new BigDecimal(f1).subtract(new BigDecimal(5)).floatValue();
    }
/*
    @RequestMapping(value = "/datas", method = RequestMethod.GET)
    public Object chartDatas(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();

        map.put("ary",list);
        map.put("state",1);

        return map;
    }*/

 /*   @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public Object resetData(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();

        map.put("state",1);
        list = new ArrayList<Map<String,Object>>();
        return map;
    }*/

    public float getRandomFloat(){
        return new BigDecimal(Math.random() * 50 + 10).divide(new BigDecimal(10),1, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    private long tenmin = 10 * 60 * 1000L;
    private long tensec = 10 * 1000L;

    @RequestMapping(value = "/realtime", method = RequestMethod.GET)
    public Object realtime(HttpServletRequest request,HttpServletResponse response){
        String isnow = request.getParameter("isnow");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("state",1);
        map.put("topVal",18);
        map.put("bottonVal",2);
        if("1".equals(isnow)){
            List<Object> data = new ArrayList<Object>();
            List<Object> xdata = new ArrayList<Object>();
            List<Object> top = new ArrayList<Object>();
            List<Object> botton = new ArrayList<Object>();
            long from = System.currentTimeMillis() - tenmin;
            int cnt = 0;
            for (long i = from;cnt < 60;cnt++){
                xdata.add(i);
                top.add(18);
                botton.add(2);
                data.add(TestArrayData.real[cnt]);
                i += tensec;
            }
            map.put("xdata",xdata);
            map.put("ary",data);
            map.put("top",top);
            map.put("botton",botton);
        }else{
            //判断是否与上一次传送的时间(x坐标)一致，如一致，返回state=0
            /*String time = request.getParameter("time");
            if (StringUtils.isNumeric(time)){

            }*/
            Map<String,Object> m = new HashMap<String,Object>();
            m.put("x",System.currentTimeMillis());
            m.put("y",(int)(Math.random() * 25 + 10));
            map.put("position",m);
        }

        return map;
    }

    private List<Map<String,Object>> gList;

    @RequestMapping(value = "/store", method = RequestMethod.GET)
    public Object store(HttpServletRequest request,HttpServletResponse response){
        Map m = new HashMap();
        m.put("state",0);
        if (gList != null && gList.size() > 5){
            m.put("state",1);
            List<Map<String,Object>> tmp = new ArrayList<Map<String,Object>>();
            for (Map m1 : gList){
                tmp.add(m1);
            }
            m.put("ary",tmp);
            gList = new ArrayList<Map<String,Object>>();
        }
        return m;
    }

    @RequestMapping(value = "/train", method = RequestMethod.GET)
    public Object train(HttpServletRequest request,HttpServletResponse response){
        if (gList == null){
            gList = new ArrayList<Map<String,Object>>();
        }
        String isFirst = request.getParameter("isFirst");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("state",1);
        if (StringUtils.isNotBlank(isFirst)){
            List<Integer> data = new ArrayList<Integer>();
            List<Integer> top = new ArrayList<Integer>();
            List<Long> xdata = new ArrayList<Long>();
            List<Integer> botton = new ArrayList<Integer>();
            long now = System.currentTimeMillis();
            for (int i = 0;i < 24;i++){
                top.add(18);botton.add(2);
                xdata.add(now);
                data.add(TestArrayData.train[i]);
                Map m2 = new HashMap();
                m2.put("x",now);
                m2.put("y",TestArrayData.train[i]);
                gList.add(m2);
                now += 10000L;
            }
            map.put("real",data);
            map.put("xdata",xdata);
            map.put("top",top);
            map.put("botton",botton);
        }else{
            Map<String,Object> m1 = new HashMap<String,Object>();
            m1.put("x",System.currentTimeMillis());
            m1.put("y",(int)(Math.random() * 25 + 10));
            gList.add(m1);
            map.put("position",m1);
        }
        return map;
    }

}
