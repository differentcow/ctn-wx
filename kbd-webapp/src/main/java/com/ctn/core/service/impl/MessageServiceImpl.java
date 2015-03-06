/**
 * 
 */
package com.ctn.core.service.impl;

import com.ctn.entity.Message;
import com.ctn.entity.MessageArticle;
import com.ctn.entity.MessageArticles;
import com.ctn.entity.type.MsgType;
import com.ctn.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Zale
 * 
 */
@Service
public class MessageServiceImpl implements MessageService {
	private static Logger logger = Logger.getLogger(MessageServiceImpl.class.getName());
	
	@Value("${wechat.developer.account}")
	private String developer;
	@Value("${server.address}")
	private String serverUrl;
	@Override
	public Message processMsg(Message msg) {
		Message response = new Message();
		
		Date now = new Date();
		if (MsgType.event.equals(msg.getMsgType())) {
			if (msg.getEvent() != null) {
				switch (msg.getEvent()) {
				case subscribe: {
					createSubscribeMsg(msg, response, now);
					break;
				}
				default:
					break;
				}
			}
		}
//		createMsg(msg, response, now);
		return response;
	}
	private void createSubscribeMsg(Message msg, Message response, Date now) {
		response.setToUserName(msg.getFromUserName());;
		response.setFromUserName(developer);
		response.setCreateTime(now.getTime());
		response.setMsgType(MsgType.news);
		response.setArticleCount(3);
		MessageArticles articles = new MessageArticles();
		response.setArticles(articles);
		List<MessageArticle> items = new ArrayList<MessageArticle>();
		MessageArticle item = new MessageArticle();
		item.setTitle("绑定爱车");
		item.setDescription("点击绑定爱车");
		item.setPicUrl(serverUrl+"image/reg.jpg");
		item.setUrl(serverUrl+"nopage.html");
		items.add(item);
		MessageArticle item3 = new MessageArticle();
        item3.setTitle("爱车行程");
        item3.setDescription("查看我的爱车行程");
        item3.setPicUrl(serverUrl+"image/msg/ac.png");
        item3.setUrl(serverUrl+"cal.html");
        items.add(item3);

		MessageArticle item2 = new MessageArticle();
		item2.setTitle("加油日志");
		item2.setDescription("查看我的加油日志");
		item2.setPicUrl(serverUrl+"image/msg/jy.png");
		item2.setUrl(serverUrl+"oilrec.html");
		items.add(item2);
		

		articles.setItem(items);
	}

}
