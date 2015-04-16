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
import java.util.Map;

/**
 * Created by barry on 2015/3/6.
 */
public class EventHandler {

    @Handler
    public Message welcomeText(HttpServletRequest request,HttpServletResponse response, Message msg){
        String developer = (String)request.getAttribute("developer");
        String serverUrl = (String)request.getAttribute("serverUrl");
        Map<String,Integer> map = (Map<String,Integer>)request.getAttribute("authMap");
        String content = msg.getContent();
        return handlerContent(developer,serverUrl,content,map,msg);
    }

    private Message handlerContent(String developer,String serverUrl,String content,Map<String,Integer> map,Message msg){
        String[] params = content.split("/");
        //Login function
        if(params.length == 2){
            if(map.get(content) == 1){
                return buildLoginNavi(serverUrl,developer,msg,params[0],"Joanna，能看到吗？进来看看呗，嘻嘻嘻","consult");
            }else if(map.get(content) == 0){
                return buildLoginNavi(serverUrl,developer,msg,params[0],"Joanna，能看到吗？进来看看呗，嘻嘻嘻","consult");
            }else if(map.get(content) == 2){
                return buildLoginNavi(serverUrl,developer,msg,params[0],"给Joel的回信","consultJoel");
            }else{
                return buildTip(developer,msg);
            }
        }else{
            //提示
            return buildTip(developer,msg);
        }
    }

    private Message buildTip(String developer,Message msg){
        Message message = new Message();
        message.setMsgType(MsgType.text);
        message.setToUserName(msg.getFromUserName());
        message.setFromUserName(developer);
        message.setContent("Welcome to B+ word!");
        message.setCreateTime(System.currentTimeMillis());
        return message;
    }

    private Message buildLoginNavi(String serverUrl,String developer,Message msg,String user,String title,String forward){
        Message message = new Message();
        message.setToUserName(msg.getFromUserName());
        message.setFromUserName(developer);
        message.setCreateTime(new Date().getTime());
        message.setMsgType(MsgType.news);
        message.setArticleCount(2);
        MessageArticles articles = new MessageArticles();
        message.setArticles(articles);
        List<MessageArticle> items = new ArrayList<MessageArticle>();
        MessageArticle item = new MessageArticle();
        item.setTitle(title);
        item.setDescription("");
        item.setPicUrl(serverUrl + "consult/img/Rain-l.png");
        item.setUrl(serverUrl + "consult/"+forward+".html?user="+user);
        items.add(item);
        MessageArticle item1 = new MessageArticle();
        item1.setTitle("分享你的故事，分享你的心情，只因感动而在一起");
        item1.setDescription("");
        item1.setPicUrl(serverUrl+"image/heart.png");
        item1.setUrl(serverUrl+"consult/consultPost.html?user="+user);
        items.add(item1);
        articles.setItem(items);
        message.setArticles(articles);
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
