package com.ctn.thread;

import com.ctn.entity.temperature.RealTimeTemperature;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

/**
 * Created by barry on 2015/3/11.
 */
public class StoreRealTimeTemperatureThread implements Runnable{

    private BlockingQueue<RealTimeTemperature> queue;

    private RealTimeTemperature temp;

    public RealTimeTemperature getTemp() {
        return temp;
    }

    private boolean isRun = true;

    public void setRun(boolean isRun) {
        this.isRun = isRun;
    }

    @Override
    public void run() {
        if(temp == null){
            temp = new RealTimeTemperature();
        }

        try {
            while(isRun){
                RealTimeTemperature real = queue.take();
                System.out.println("real:"+real.getTemperature());
                if(real != null){

                    temp.setData(real.getTemperature(),real.getTimestamp());

                    //保存到数据库
                    //TODO Save in Database.......
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public StoreRealTimeTemperatureThread(BlockingQueue<RealTimeTemperature> queue){
        this.queue = queue;
    }

    public void add(Float temp,Long timestamp) throws InterruptedException {
        RealTimeTemperature temper = new RealTimeTemperature();
        temper.setData(temp,timestamp);
        queue.add(temper);
    }

    public void add(Float temp) throws InterruptedException {
        RealTimeTemperature temper = new RealTimeTemperature();
        temper.setData(temp,new Date().getTime());
        queue.add(temper);
    }

}
