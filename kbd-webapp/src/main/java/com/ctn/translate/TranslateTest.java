package com.ctn.translate;

import com.ctn.receive.RealTimeTemperatureManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by hp on 2015/3/11.
 */
@Component
public class TranslateTest {

    @Autowired
    private RealTimeTemperatureManager manager;

    private GenTemp genTemp;

    @PostConstruct
    public void init(){
        /*genTemp = new GenTemp(manager);
        new Thread(genTemp).start();*/
    }



}
