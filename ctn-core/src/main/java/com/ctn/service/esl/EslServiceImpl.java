package com.ctn.service.esl;

import com.ctn.dao.EslDao;
import com.ctn.entity.model.Esl;
import com.ctn.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Barry on 2014/9/28.
 */

@Service
public class EslServiceImpl extends BaseServiceImpl<Esl> implements EslService<Esl> {

    @Autowired
    private EslDao dao;



}