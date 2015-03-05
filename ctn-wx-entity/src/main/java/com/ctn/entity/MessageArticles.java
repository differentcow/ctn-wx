package com.ctn.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;


/**
 * 微信消息通讯载体---图文消息载体
 * 
 * @author Barry.LJ.Huang
 *
 */
public class MessageArticles {
	private List<MessageArticle> item;
	
	@XmlElement
	public List<MessageArticle> getItem() {
		return item;
	}

	public void setItem(List<MessageArticle> item) {
		this.item = item;
	}
	
	
}
