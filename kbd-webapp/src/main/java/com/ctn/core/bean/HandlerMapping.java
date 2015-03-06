package com.ctn.core.bean;

import com.ctn.entity.type.EventType;
import com.ctn.entity.type.MsgType;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by barry on 2015/3/6.
 */
public class HandlerMapping {

    private Map<String,HandlerBean> beanMap;


    public HandlerBean getHandlerBean(EventType eventType,MsgType msgType){
        if (beanMap.isEmpty()){
            return null;
        }
        if (msgType == MsgType.event.event){
            return beanMap.get("event_"+getEventKey(eventType));
        }else{
            return beanMap.get(getMsgKey(msgType));
        }
    }

    private String getEventKey(EventType type){
        switch (type){
            case unsubscribe:return "unsubscribe";
            case subscribe:return "subscribe";
            case SCAN:return "SCAN";
            case CLICK:return "CLICK";
            case VIEW:return "VIEW";
            case LOCATION:return "LOCATION";
            default: return "subscribe";
        }
    }

    private String getMsgKey(MsgType type){
        switch (type){
            case text: return "text";
            case image:return "image";
            case link:return "link";
            case location:return "location";
            case video:return "video";
            case voice:return "voice";
            default: return "text";
        }
    }

    public Map<String, HandlerBean> getBeanMap() {
        return beanMap;
    }

    public HandlerMapping(){
        beanMap = new HashMap<String,HandlerBean>();
    }

    public void add(EventType eventType,MsgType msgType,String method,Object o){
        HandlerBean bean = build(eventType,msgType,o,method);
        if (StringUtils.isBlank(bean.getType())){
            beanMap.put(bean.getKey(),bean);
        }else{
            beanMap.put(bean.getType() + "_" + bean.getKey(),bean);
        }
    }

    /**
     * 创建HandlerBean
     *
     * @param eventType
     * @param msgType
     * @param obj
     * @param method
     * @return
     */
    private HandlerBean build(EventType eventType,MsgType msgType,Object obj,String method){
        HandlerBean bean = new HandlerBean();
        if (msgType == MsgType.event){
            bean.setType("event");
            bean.setKey(getEventKey(eventType));
        }else{
            bean.setType("");
            bean.setKey(getMsgKey(msgType));
        }
        bean.setValue(obj);
        bean.setClazz(obj.getClass());
        bean.setMethodName(method);
        return bean;
    }

}
