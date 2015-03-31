package com.ctn.service.temperature;

import com.ctn.dao.TemperatureDao;
import com.ctn.entity.model.Temperature;
import com.ctn.entity.query.GenericQueryParam;
import com.ctn.entity.query.QueryKey;
import com.ctn.entity.response.TemperatureRsp;
import com.ctn.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Barry on 2014/9/28.
 */

@Service
public class TemperatureServiceImpl extends BaseServiceImpl<Temperature> implements TemperatureService<Temperature> {

    @Autowired
    private TemperatureDao dao;

    public List<TemperatureRsp> getAll(){
//        GenericQueryParam param = new GenericQueryParam();
//        param.addSortCond(new SortCond("update_time", SortCond.Order.DESC));
        return dao.getList();
    }


    public boolean updateEsl(Integer esl_id,Integer id){
        return dao.updateEsl(esl_id,id) > 0;
    }

    @Transactional
    public boolean updateColor(String color,Integer id){
        GenericQueryParam param = new GenericQueryParam();
        param.put(new QueryKey("id"),id);
        Temperature t = new Temperature();
        t.setColor(color);
        t.setUpdate_time(System.currentTimeMillis());
        return this.update(t,param) > 0;
    }

    @Transactional
    public boolean updateXY(Integer x,Integer y,Integer id){
        GenericQueryParam param = new GenericQueryParam();
        param.put(new QueryKey("id"),id);
        Temperature t = new Temperature();
        t.setX(x);t.setY(y);
        t.setUpdate_time(System.currentTimeMillis());
        return this.update(t,param) > 0;
    }

}
