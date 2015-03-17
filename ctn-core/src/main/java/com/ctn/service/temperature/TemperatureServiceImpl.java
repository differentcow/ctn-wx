package com.ctn.service.temperature;

import com.ctn.entity.model.Temperature;
import com.ctn.entity.query.GenericQueryParam;
import com.ctn.entity.query.QueryKey;
import com.ctn.entity.query.SortCond;
import com.ctn.service.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Barry on 2014/9/28.
 */

@Service
public class TemperatureServiceImpl extends BaseServiceImpl<Temperature> implements TemperatureService<Temperature> {


    public List<Temperature> getAll(){
        GenericQueryParam param = new GenericQueryParam();
        param.addSortCond(new SortCond("update_time", SortCond.Order.DESC));
        return this.findAll(param);
    }

    @Transactional
    public boolean updateColor(String color,Integer id){
        GenericQueryParam param = new GenericQueryParam();
        param.put(new QueryKey("id"),id);
        Temperature t = new Temperature();
        t.setColor(color);
        return this.update(t,param) > 0;
    }

}
