package com.ctn.dao;

import com.ctn.entity.response.TemperatureRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TemperatureDao {


    List<TemperatureRsp> getList();

    int updateEsl(@Param("esl") Integer esl_id, @Param("id") Integer id);

}
