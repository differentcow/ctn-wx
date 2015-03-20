package com.ctn.entity.tcp;

import java.util.Map;

/**
 * Created by hp on 2015/3/20.
 */
public class DataResult {

    private Map<String,DataValue> map;

    public Object getVal(String key){
        if(map.get(key) != null){
            return map.get(key).getVal();
        }
        return null;
    }

    public byte[] getData(String key){
        if(map.get(key) != null){
            return map.get(key).getData();
        }
        return null;
    }

    public String getType(String key){
        if(map.get(key) != null){
            return map.get(key).getType();
        }
        return null;
    }

    public DataValue getObject(String key){
        if(map.get(key) != null){
            return map.get(key);
        }
        return null;
    }

    public Map<String,DataValue> getMapResult(){
        return map;
    }

}
