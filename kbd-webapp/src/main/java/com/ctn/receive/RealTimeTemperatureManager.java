package com.ctn.receive;

import com.ctn.entity.temperature.RealTimeTemperature;
import com.ctn.thread.StoreRealTimeTemperatureThread;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by barry on 2015/3/11.
 */
@Component
public class RealTimeTemperatureManager {

    private StoreRealTimeTemperatureThread thread;

    @PostConstruct
    public void init(){
        /*thread = new StoreRealTimeTemperatureThread(new ArrayBlockingQueue<RealTimeTemperature>(50));
        new Thread(thread).start();*/
    }

    public RealTimeTemperature getRealTimeTemperature(){
        return thread.getTemp();
    }

    public void addRealTimeTemperature(Float temp,Long timestamp) throws InterruptedException {
        thread.add(temp,timestamp);
    }

    public void addRealTimeTemperature(Float temp) throws InterruptedException {
        thread.add(temp);
    }


}
