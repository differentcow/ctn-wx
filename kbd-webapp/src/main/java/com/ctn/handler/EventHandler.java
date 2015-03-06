package com.ctn.handler;

import com.ctn.annotation.Handler;
import com.ctn.entity.Message;
import com.ctn.entity.MessageArticle;
import com.ctn.entity.MessageArticles;
import com.ctn.entity.type.EventType;
import com.ctn.entity.type.MsgType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by barry on 2015/3/6.
 */
public class EventHandler {



    @Handler
    public Message welcomeText(HttpServletRequest request,HttpServletResponse response, Message msg){
        String developer = (String)request.getAttribute("developer");
        Message message = new Message();
        message.setMsgType(MsgType.text);
        message.setToUserName(msg.getFromUserName());
        message.setFromUserName(developer);
        message.setContent("我就叫B++，我为我自己带盐");
        message.setCreateTime(new Date().getTime());
        return message;
    }

    @Handler(value = MsgType.event,type = EventType.subscribe)
    public Message qcsenceSubscribe(HttpServletRequest request,HttpServletResponse response, Message msg){
        String developer = (String)request.getAttribute("developer");
        String serverUrl = (String)request.getAttribute("serverUrl");
        Message message = new Message();
        message.setToUserName(msg.getFromUserName());
        message.setFromUserName(developer);
        message.setCreateTime(new Date().getTime());
        message.setMsgType(MsgType.news);
        message.setArticleCount(3);
        MessageArticles articles = new MessageArticles();
        message.setArticles(articles);
        List<MessageArticle> items = new ArrayList<MessageArticle>();
        MessageArticle item = new MessageArticle();
        item.setTitle("我是水瓜");
        item.setDescription("点击你就知道");
        item.setPicUrl(serverUrl+"image/reg.jpg");
        item.setUrl(serverUrl+"nopage.html");
        items.add(item);
        MessageArticle item3 = new MessageArticle();
        item3.setTitle("你是傻瓜");
        item3.setDescription("点击就真的傻瓜了");
        item3.setPicUrl(serverUrl+"image/msg/ac.png");
        item3.setUrl(serverUrl+"cal.html");
        items.add(item3);

        MessageArticle item2 = new MessageArticle();
        item2.setTitle("他是麻瓜");
        item2.setDescription("点击就懂魔法");
        item2.setPicUrl(serverUrl+"image/msg/jy.png");
        item2.setUrl(serverUrl+"oilrec.html");
        items.add(item2);
        articles.setItem(items);
        return message;
    }

}
