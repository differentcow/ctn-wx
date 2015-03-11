package com.ctn.translate;

import com.ctn.receive.RealTimeTemperatureManager;

import java.math.BigDecimal;

/**
 * Created by hp on 2015/3/11.
 */
public class GenTemp implements Runnable{

    @Override
    public void run() {
        genTemp();
    }

    public GenTemp(RealTimeTemperatureManager manager){
        this.manager = manager;
    }

    private RealTimeTemperatureManager manager;

    private boolean flag = true;

    public void setFlag(boolean flag){
        this.flag = flag;
    }

    public float getRandomFloat(){
        return new BigDecimal(1000 - Math.random() * 1100).divide(new BigDecimal(10),1, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public void genTemp(){
        try {
            while (flag){
                float tt = getRandomFloat();
                System.out.println(tt);
                manager.addRealTimeTemperature(tt, System.currentTimeMillis());
                Thread.sleep(5000L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
